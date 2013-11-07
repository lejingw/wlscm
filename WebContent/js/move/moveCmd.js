function addMoveRow(data){
	var chkHtml = "<input type='checkbox' name='chk'/>"
		+ "<input type='hidden' name='lineid' value=''/>"
		+ "<input type='hidden' name='ornaCode' value='" + data.ornaCode + "'/>"
		+ "<input type='hidden' name='barCode' value='" + data.barCode + "'/>"
		+ "<input type='hidden' name='allWeight' value='" + (data.allWeight?data.allWeight:"") + "'/>"
		+ "<input type='hidden' name='posCost' value='" + (data.posCost?data.posCost:"") + "'/>"
		+ "<input type='hidden' name='posAmount' value='" + (data.posAmount?data.posAmount:"") + "'/>"
		+ "<input type='hidden' name='grains' value='" + (data.grains?data.grains:"") + "'/>";
	var isDblLabelHtml = "1" == data.isDblLabel?"是":"否";
	var ornaCodeHtml = data.ornaCode;
	var ornaDscHtml = data.ornaDsc;
	var itemClassHtml = data.itemClassName;
	var ornaClassHtml = data.ornaClassName;
	var analysisNameHtml = data.analysisName;
	var styleNameHtml = printStyle(data.styleId, data.styleName, data.bigGraph);
	var unitHtml = data.unitName;
	var allWeightHtml = data.allWeight;
	var nowQtyHtml = data.nowQty;
	var grainsHtml = data.grains;
	var posAmountHtml = data.posAmount;
	var mainWeight = data.mainWeight;
	var partWeight = data.partWeight;
	var statusHtml = "保存";
	var deleteHtml = "<input type='button' class='btn' value='删除' onclick='deleteMoveRow(this)'/>";
	var htmlArr = ["", chkHtml, isDblLabelHtml, ornaCodeHtml, ornaDscHtml, itemClassHtml, ornaClassHtml, analysisNameHtml, styleNameHtml, unitHtml, 
	               allWeightHtml, nowQtyHtml, grainsHtml, posAmountHtml, mainWeight, partWeight, statusHtml, deleteHtml];
	insertRow('tbl', htmlArr, true);
}
function deleteMoveRow(obj){
	var index = obj.parentNode.parentNode.rowIndex - 1;
	if(!isNull($n("lineid")[index].value)){
		deleteOrnaCodeArr.push($n("ornaCode")[index].value);
	}
	deleteRow(obj, 'tbl');
	calcSum();
	disableOutInfo();
}
/**
 * 检查是否已经选择调出组织、调出仓库
 */
function checkBeforeAdd(){	
		if(isNull(jQuery("#outOrgId").val())){
			alert("请先选择调出组织");
			return false;
		}
	return true;
}
function disableOutInfo(){
		jQuery("#outOrgId").attr("disabled", $n("ornaCode").length>0);
		jQuery("#outOrgName").attr("disabled", $n("ornaCode").length>0);
}
/**
 * 根据饰品编码获取饰品信息
 */
function getMaterActiveByOrnaCode(){
	if(!checkBeforeAdd())return;
	getMaterActiveInfo(jQuery("#ornaCode_in").val(), true);
}
/**
 * 根据饰品条码获取饰品信息
 */
function getMaterActiveByBarCode(){
	if(!checkBeforeAdd())return;
	getMaterActiveInfo(jQuery("#barCode_in").val(), false);
}
function getMaterActiveInfo(code, ornaFlag){
	if(!checkOrnaCodeExists(code, ornaFlag))
		return;
	MoveCmdDwr.getMaterActiveInfo(code, ornaFlag, jQuery("#outOrgId").val(), jQuery("#inOrgId").val(), function(data){
		if(data.stockId != jQuery("#outStockId").val()){
			alert("饰品所在仓库不为当前选择的调出仓库");
			return ;
		}
		addMoveRow(data);
		calcSum();
		disableOutInfo();
		if(ornaFlag){
			jQuery("#ornaCode_in").val("");
		}else{
			jQuery("#barCode_in").val("");
		}
	});
}
function checkOrnaCodeExists(code, ornaFlag){
	for(var i=0;i<$n("ornaCode").length;i++){
		if(ornaFlag && $n("ornaCode")[i].value == code){
			alert("第"+(i+1)+"行 饰品编码[" + code + "]已经存在");
			return false;
		}
		if(!ornaFlag && $n("barCode")[i].value == code){
			alert("第"+(i+1)+"行 饰品条码[" + code + "]已经存在");
			return false;
		}
	}
	return true;
}
var sumCostLimit = null;
function calcSum(){
	var sumWeight="0", sumGrains="0", sumCost="0", sumMoney="0";
	for(var i=0;i<$n("ornaCode").length;i++){
		$("tbl").rows[i].cells[0].innerHTML = $n("ornaCode").length-i;
		sumWeight = floatAdd(sumWeight, nullToZero($n("allWeight")[i].value));
		sumGrains = floatAdd(sumGrains, nullToZero($n("grains")[i].value));
		sumCost = floatAdd(sumCost, nullToZero($n("posCost")[i].value));
		sumMoney = floatAdd(sumMoney, nullToZero($n("posAmount")[i].value));
	}
	jQuery("#sumCount").val($n("ornaCode").length);//件数合计
	jQuery("#sumWeight").val(sumWeight);//重量合计
	jQuery("#sumGrains").val(sumGrains);//粒数合计
	jQuery("#sumCost").val(sumCost);//成本合计
	jQuery("#sumMoney").val(sumMoney);//金额合计
	if($n("ornaCode").length>1 && (sumCostLimit == null)){
		startSync();
		MoveCmdDwr.getSumCostLimit(function(data){
			sumCostLimit = data;
		});
		endSync();
	}
	if($n("ornaCode").length>1 && (sumCostLimit != null) && (sumCost>parseFloat(sumCostLimit))){
		alert("调拨单总成本已经超上限[" + sumCostLimit + "]");
	}
}
function nullToZero(val){
	if(!val)
		return "0";
	return val;
}

function invalidCmd(){
	var headid = jQuery("#headid").val();
	confirm("确定要作废?", function(){
		showLayer(true);
		MoveCmdDwr.invalidMoveCmd(headid, function(data){
    		showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("处理成功", function(){
						window.location = "moveCmd.vm?user_action=toEdit&headid=" + headid;
					});
				} else {
					alert(data.msg);
				}
			} else {
				alert("处理失败");
			}
		});
	});
}