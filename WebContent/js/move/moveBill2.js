function addMoveRow(data){
	var chkHtml = "<input type='checkbox' name='chk'/>"
		+ "<input type='hidden' name='lineid' value=''/>"
		+ "<input type='hidden' name='ornaCode' value='" + data.ornaCode + "'/>"
		+ "<input type='hidden' name='barCode' value='" + data.barCode + "'/>"
		+ "<input type='hidden' name='allWeight' value='" + (data.allWeight?data.allWeight:"") + "'/>"
		+ "<input type='hidden' name='posCost' value='" + (data.posCost?data.posCost:"") + "'/>"
		+ "<input type='hidden' name='posAmount' value='" + (data.posAmount?data.posAmount:"") + "'/>"
		+ "<input type='hidden' name='newPosCost' value='" + (data.newPosCost?data.newPosCost:"") + "'/>"
		+ "<input type='hidden' name='grains' value='" + (data.grains?data.grains:"") + "'/>";
	var isDblLabelHtml = "1" == data.isDblLabel?"是":"否";
	var printLabelHtml = "1" == data.printLabel?"是":"否";
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
	var newPosMoneyHtml = data.newPosMoney;
	var mainWeight = data.mainWeight;
	var partWeight = data.partWeight;
	var statusHtml = "保存";
	var deleteHtml = "<input type='button' name='deleteBtn' class='btn' value='删除' onclick='deleteMoveRow(this)'/>";
	var htmlArr = ["", chkHtml, isDblLabelHtml, printLabelHtml, ornaCodeHtml, ornaDscHtml, itemClassHtml, ornaClassHtml, analysisNameHtml, styleNameHtml, unitHtml, 
	               allWeightHtml, nowQtyHtml, grainsHtml, newPosMoneyHtml, mainWeight, partWeight, statusHtml, deleteHtml];
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
	if("1" == jQuery("#billType").val() || "2" == jQuery("#billType").val() || "3" == jQuery("#billType").val()){		
		if(isNull(jQuery("#inOrgId").val())){
			alert("请先选择调入组织");
			return false;
		}
	}
	if("4" == jQuery("#billType").val() && isNull(jQuery("#outStockId").val())){
		alert("请先选择调出仓库");
		return false;
	}
	if("5" == jQuery("#billType").val() && isNull(jQuery("#outGroup").val())){
		alert("请先选择调出柜组");
		return false;
	}
	return true;
}
function disableOutInfo(){
	if("1" == jQuery("#billType").val()){
		jQuery("#inOrgId").attr("disabled", $n("ornaCode").length>0);
		jQuery("#inOrgName").attr("disabled", $n("ornaCode").length>0);
	}else if("2" == jQuery("#billType").val()){		
		jQuery("#inOrgId").attr("disabled", $n("ornaCode").length>0);
		jQuery("#backIn48Flag").attr("disabled", $n("ornaCode").length>0);
	}else if("3" == jQuery("#billType").val()){		
		jQuery("#inOrgId").attr("disabled", $n("ornaCode").length>0);
	}
	if("4" == jQuery("#billType").val()){
		jQuery("#outStockId").attr("disabled", $n("ornaCode").length>0);
	}else if("5" == jQuery("#billType").val()){
		jQuery("#outGroup").attr("disabled", $n("ornaCode").length>0);		
	}
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
	MoveBillDwr.getMaterActiveInfo(code, ornaFlag, jQuery("#outOrgId").val(), jQuery("#billType").val(), jQuery("#jmFlag").val(), jQuery("#inOrgId").val(), function(data){
		if(("1" == jQuery("#billType").val() || "2" == jQuery("#billType").val()
				|| "3" == jQuery("#billType").val() || "4" == jQuery("#billType").val())
			&& data.stockId != jQuery("#outStockId").val()){
			alert("饰品所在仓库不为当前选择的调出仓库");
			return ;
		}
		if("2" == jQuery("#billType").val() && jQuery("#backIn48Flag").attr("checked")){
			if(data.toshopdateAfter48 < data.currentDate){
				alert("到店时间超过48小时，不能退货调拨");
				return ;
			}
		}
		if("5" == jQuery("#billType").val() && data.outGroupNo != jQuery("#outGroup").val()){
			alert("饰品所在柜组["+data.outGroupNo+"]不为当前选择的调出柜组");
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
	var sumWeight="0", sumGrains="0", sumCost="0", sumMoney="0", sumNewPosCost = "0";
	for(var i=0;i<$n("ornaCode").length;i++){
		$("tbl").rows[i].cells[0].innerHTML = $n("ornaCode").length-i;
		sumWeight = floatAdd(sumWeight, nullToZero($n("allWeight")[i].value));
		sumGrains = floatAdd(sumGrains, nullToZero($n("grains")[i].value));
		sumCost = floatAdd(sumCost, nullToZero($n("posCost")[i].value));
		sumMoney = floatAdd(sumMoney, nullToZero($n("posAmount")[i].value));
		sumNewPosCost = floatAdd(sumNewPosCost, nullToZero($n("newPosCost")[i].value));
	}
	jQuery("#sumCount").val($n("ornaCode").length);//件数合计
	jQuery("#sumWeight").val(sumWeight);//重量合计
	jQuery("#sumGrains").val(sumGrains);//粒数合计
	jQuery("#sumCost").val(sumCost);//成本合计
	jQuery("#sumMoney").val(sumMoney);//金额合计
	if("1" == jQuery("#billType").val()){//新总成本
		jQuery("#sumNewPosCost").val(sumNewPosCost);//新总成本
	}
	var flag = ("1" == jQuery("#billType").val() || "2" == jQuery("#billType").val() || "3" == jQuery("#billType").val());
	if(flag && $n("ornaCode").length>1 && (sumCostLimit == null)){
		startSync();
		MoveBillDwr.getSumCostLimit(function(data){
			sumCostLimit = data;
		});
		endSync();
	}
	if(flag && $n("ornaCode").length>1 && (sumCostLimit != null) && (sumCost>parseFloat(sumCostLimit))){
		alert("调拨单总成本已经超上限[" + sumCostLimit + "]");
	}
}
function nullToZero(val){
	if(!val)
		return "0";
	return val;
}

// -----------------------------------------------------------------------------------------------------------------------------------
/**
 * 显示选择调拨指令窗口
 */
function showSelectMoveCmdWin (){
	var outStockId = jQuery("#outStockId").val();
	var inStockId = jQuery("#inStockId").val();
	var outOrgId = jQuery("#outOrgId").val();
	var inOrgId = jQuery("#inOrgId").val();
	var headid = jQuery("#headid").val();
	if(!inOrgId) {
		alert("请选择调入组织");
		return;
	}
	var _iframeId = "selectMoveCmdIframe";
	var options = {
		title : '调拨指令单',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 900,
		height : 300,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			insertRows(result, box);
//			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/move/moveCmd.vm?user_action=selectMoveCmd&inOrgId='+inOrgId + "&outOrgId=" + outOrgId+ "&outStockId=" + outStockId+ "&inStockId=" + inStockId + "&moveId=" + headid;
	jQuery.weeboxs.open(url, options);
}

function insertRows(res, box) {
	var oldMoveCmdId = jQuery("#moveCmdId").val();
	if(!res) {
		if(oldMoveCmdId.length >0) {
			confirm("确定不选择调拨指令单?", function(){
				clearLines();
				box.close();
			});
		} else {
			box.close();
		}
		jQuery("#moveCmdNo").val("");
		jQuery("#moveCmdId").val("");
		jQuery("#ornaCode_in").attr("disabled", false);
		jQuery("#barCode_in").attr("disabled", false);
	} else {
		box.close();
		jQuery("#ornaCode_in").attr("disabled", true);
		jQuery("#barCode_in").attr("disabled", true);
		var newMoveCmdId = res.moveCmdId,
			moveCmdNo = res.moveCmdNo,
			ornaCodes = res.ornaCodes;
		jQuery("#moveCmdNo").val(moveCmdNo);
		jQuery("#moveCmdId").val(newMoveCmdId);
		if(newMoveCmdId != oldMoveCmdId){
			if(oldMoveCmdId.length >0){
				clearLines();
			}
			addLines(ornaCodes);
		}
	}
}
/**
 * 清空行
 */
function clearLines (){
	while($n("ornaCode").length >0){
		deleteOrnaCodeArr.push($n("ornaCode")[0].value);
		deleteRow($n("ornaCode")[0], 'tbl');
	}
	calcSum();
	disableOutInfo();
}

/**
 * 增加饰品行
 * @param {} ornaCodes
 */
function addLines(ornaCodes) {
	var ornaCodeArr = ornaCodes.split(",");
	for(var i=0; i<ornaCodeArr.length; i++){
		var ornaCode = ornaCodeArr[i];
		getMaterActiveInfo2(ornaCode);
	}
}

/**
 * 获取饰品信息
 * @param {} code
 */
function getMaterActiveInfo2(code){
	if(!checkOrnaCodeExists2(code))
		return;
	MoveBillDwr.getMaterActiveInfo2(code, jQuery("#outOrgId").val(), jQuery("#billType").val(), jQuery("#inOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		disableOutInfo();
		$n("deleteBtn")[0].disabled = true;
	});
}
/**
 * 判断饰品编码是否已经存在行中
 * @param {} code
 * @return {Boolean}
 */
function checkOrnaCodeExists2(code){
	for(var i=0;i<$n("ornaCode").length;i++){
		if(ornaFlag && $n("ornaCode")[i].value == code){
			return false;
		}
	}
	return true;
}

function initMoveCmdData(){
	var moveCmdId = jQuery("#moveCmdId").val();
	if(moveCmdId && moveCmdId.length >0){
		jQuery("#ornaCode_in").attr("disabled", true);
		jQuery("#barCode_in").attr("disabled", true);
		
		for(var i=0; i< $n("deleteBtn").length ; i++){
			$n("deleteBtn")[i].disabled = true;
		}
	}
}