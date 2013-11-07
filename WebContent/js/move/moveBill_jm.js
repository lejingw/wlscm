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
	var htmlArr = [];
	htmlArr.push("");
	htmlArr.push(chkHtml);
	if("2" == jQuery("#billType").val()){
		htmlArr.push("1"==data.freeReturnFlag?"是":"否");
		htmlArr.push(data.freeLeftDays);
		htmlArr.push(data.createDate);
	}
	htmlArr.push("1" == data.isDblLabel?"是":"否");
	htmlArr.push("1" == data.printLabel?"是":"否");
	htmlArr.push(data.ornaCode);
	htmlArr.push(data.ornaDsc);
	htmlArr.push(data.itemClassName);
	htmlArr.push(data.ornaClassName);
	htmlArr.push(data.analysisName);
	htmlArr.push(printStyle(data.styleId, data.styleName, data.bigGraph));
	htmlArr.push(data.unitName);
	htmlArr.push(data.allWeight);
	htmlArr.push(data.nowQty);
	htmlArr.push(data.grains);
	htmlArr.push(data.newPosMoney);
	htmlArr.push(data.mainWeight);
	htmlArr.push(data.partWeight);
	htmlArr.push("保存");
	htmlArr.push("<input type='button' class='btn' value='删除' onclick='deleteMoveRow(this)'/>");
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
	return true;
}
function disableOutInfo(){
	if("1" == jQuery("#billType").val()){
		jQuery("#inOrgId").attr("disabled", $n("ornaCode").length>0);
		jQuery("#inOrgName").attr("disabled", $n("ornaCode").length>0);
	}else{
		if("2" == jQuery("#billType").val()){
			jQuery("#backIn48Flag").attr("disabled", $n("ornaCode").length>0);
		}
		jQuery("#inOrgId").attr("disabled", $n("ornaCode").length>0);
	//	jQuery("#licenseCompleteFlag").attr("disabled", $n("ornaCode").length>0);
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
		if(data.stockId != jQuery("#outStockId").val()){
			alert("饰品所在仓库不为当前选择的调出仓库");
			return ;
		}
		if("2" == jQuery("#billType").val() && jQuery("#backIn48Flag").attr("checked")){
			if(data.toshopdateAfter48 < data.currentDate){
				alert("到店时间超过48小时，不能退货调拨");
				return ;
			}
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