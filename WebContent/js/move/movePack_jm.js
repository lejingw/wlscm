function addMoveRow(info){
	var chkHtml = "<input type='checkbox' name='chk'/>"
		+ "<input type='hidden' name='lineid' value=''/>"
		+ "<input type='hidden' name='billId' value='" + info.billId + "'/>"
		+ "<input type='hidden' name='billNo' value='" + info.billNo + "'/>"
		+ "<input type='hidden' name='billUserId' value='" + info.billUserId + "'/>"
		+ "<input type='hidden' name='billCount' value='" + info.billCount + "'/>"
		+ "<input type='hidden' name='billCost' value='" + info.billCost + "'/>";
	var htmlArr = ["", chkHtml, info.billNo];
	if("1" == jQuery("#jmFlag").val()){
		htmlArr.push(info.estimateNo);
	}
	htmlArr.push(info.billUserName);
	htmlArr.push(info.billCount);
	if(viewCostFlag){
		htmlArr.push(info.billCost);
	}
	var deleteHtml = "<input type='button' class='btn' value='删除' onclick='deleteMoveRow(this)'/>";
	htmlArr.push(deleteHtml);
	insertRow('tbl', htmlArr, true);
}
function deleteMoveRow(obj){
	var index = obj.parentNode.parentNode.rowIndex - 1;
	if(!isNull($n("lineid")[index].value)){
		deleteMoveIdArr.push($n("billId")[index].value);
	}
	deleteRow(obj, 'tbl');
	calcSum();
	disableInput();
}
/**
 * 检查是否已经选择调入组织
 */
function checkBeforeAdd(){
	if(isNull(jQuery("#inOrgId").val())){
		alert("请先选择调入组织");
		return false;
	}
	return true;
}
function disableInput(){
	if($n("billId").length>0 || !isNull(jQuery("#receUserIds").val())){
		jQuery("#inOrgId").attr("disabled", true);
	}else{
		jQuery("#inOrgId").attr("disabled", false);
	}
}
/**
 * 根据调拨单号获取调拨单信息
 */
function getMoveBillInfo(){
	if(!checkBeforeAdd())return;
	if(!checkMoveNoExists(jQuery("#moveNo_in").val()))
		return;
	MovePackDwr.getMoveBillInfo(jQuery("#billType").val(), jQuery("#jmFlag").val(), jQuery("#headid").val(), jQuery("#moveNo_in").val(), jQuery("#outOrgId").val(), jQuery("#inOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		disableInput();
		jQuery("#moveNo_in").val("");
	});
}
function checkMoveNoExists(billNo){
	for(var i=0;i<$n("billNo").length;i++){
		if($n("billNo")[i].value == billNo){
			alert("第"+(i+1)+"行 调拨单号[" + billNo + "]已经存在");
			return false;
		}
	}
	return true;
}
var sumCostLimit = null;
function calcSum(){
	var sumCount="0", sumCost="0";
	for(var i=0;i<$n("billNo").length;i++){
		$("tbl").rows[i].cells[0].innerHTML = $n("billNo").length - i;
		sumCount = floatAdd(sumCount, nullToZero($n("billCount")[i].value));
		sumCost = floatAdd(sumCost, nullToZero($n("billCost")[i].value));
	}
	jQuery("#sumCount").val(sumCount);//件数合计
	jQuery("#sumCost").val(sumCost);//成本合计
	if($n("billNo").length>1 && (sumCostLimit == null)){
		startSync();
		MovePackDwr.getSumCostLimit(function(data){
			sumCostLimit = data;
		});
		endSync();
	}
	if(!isNull(sumCostLimit) && sumCost>sumCostLimit){
		alert("装箱单总成本已经超上限[" + sumCostLimit + "]");
	}
}
function nullToZero(val){
	if(!val)
		return "0";
	return val;
}