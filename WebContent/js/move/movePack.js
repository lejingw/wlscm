function addMoveRow(info){
	var chkHtml = "<input type='checkbox' name='chk'/>"
		+ "<input type='hidden' name='lineid' value=''/>"
		+ "<input type='hidden' name='billId' value='" + info.billId + "'/>"
		+ "<input type='hidden' name='billNo' value='" + info.billNo + "'/>"
		+ "<input type='hidden' name='billUserId' value='" + info.billUserId + "'/>"
		+ "<input type='hidden' name='billCount' value='" + info.billCount + "'/>";
	var billNoHtml = info.billNo;
	var billUserNameHtml = info.billUserName;
	var billCountHtml = info.billCount;
	var deleteHtml = "<input type='button' class='btn' value='删除' onclick='deleteMoveRow(this)'/>";
	var htmlArr = null;
	if("2" != jQuery("#billType").val()){
		chkHtml += "<input type='hidden' name='billCost' value='" + info.billCost + "'/>";
		if(viewCostFlag){			
			var billCostHtml = info.billCost;
			htmlArr = ["", chkHtml, billNoHtml, billUserNameHtml, billCountHtml, billCostHtml, deleteHtml];
		}else{			
			htmlArr = ["", chkHtml, billNoHtml, billUserNameHtml, billCountHtml, deleteHtml];
		}
	} else{
		htmlArr = ["", chkHtml, billNoHtml, billUserNameHtml, billCountHtml, deleteHtml];		
	}
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
	//交接单添加行前不必须选择调出组织
	if(jQuery("#billType").val() != "3" && jQuery("#billType").val() != "4"){
		if(isNull(jQuery("#inOrgId").val())){
			alert("请先选择调入组织");
			return false;
		}
	}
	return true;
}
function disableInput(){
	if((jQuery("#billType").val() != "3" && jQuery("#billType").val() != "4" && $n("billId").length>0) || !isNull(jQuery("#receUserIds").val())){
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
	MovePackDwr.getMoveBillInfo(jQuery("#billType").val(), "0", jQuery("#headid").val(), jQuery("#moveNo_in").val(), jQuery("#outOrgId").val(), jQuery("#inOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		disableInput();
		jQuery("#moveNo_in").val("");
	});
}
/**
 * 根据维修单号获取维修单信息
 */
function getFixBillInfo(){
	if(!checkBeforeAdd())return;
	if(!checkMoveNoExists(jQuery("#fixNo_in").val()))
		return;
	MovePackDwr.getFixBillInfo(jQuery("#billType").val(), "0", jQuery("#headid").val(), jQuery("#fixNo_in").val(), jQuery("#outOrgId").val(), jQuery("#inOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		disableInput();
		jQuery("#fixNo_in").val("");
	});
}
/**
 * 根据交接单号获取交接单信息
 */
function getHandoverBillInfo(){
	if(!checkBeforeAdd())return;
	if(!checkMoveNoExists(jQuery("#handoverNo_in").val()))
		return;
	MovePackDwr.getHandoverBillInfo(jQuery("#billType").val(), "0", jQuery("#headid").val(), jQuery("#handoverNo_in").val(), jQuery("#outOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		disableInput();
		jQuery("#handoverNo_in").val("");
	});
}
/**
 * 根据形态转换单号获取形态转换单信息
 */
function getProcChangeBillInfo(){
	if(!checkBeforeAdd())return;
	if(!checkMoveNoExists(jQuery("#procChangeNo_in").val()))
		return;
	MovePackDwr.getProcChangeBillInfo(jQuery("#billType").val(), "0", jQuery("#headid").val(), jQuery("#procChangeNo_in").val(), jQuery("#outOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		disableInput();
		jQuery("#procChangeNo_in").val("");
	});
}
function checkMoveNoExists(billNo){
	for(var i=0;i<$n("billNo").length;i++){
		if($n("billNo")[i].value == billNo){
			alert("第"+(i+1)+"行 单号[" + billNo + "]已经存在");
			return false;
		}
	}
	return true;
}
var sumCostLimit = null;
function calcSum(){
	var sumCount="0", sumCost="0";
	var flag = "2" != jQuery("#billType").val();
	for(var i=0;i<$n("billNo").length;i++){
		$("tbl").rows[i].cells[0].innerHTML = $n("billNo").length - i;
		sumCount = floatAdd(sumCount, nullToZero($n("billCount")[i].value));
		if(flag){
			sumCost = floatAdd(sumCost, nullToZero($n("billCost")[i].value));
		}
	}
	jQuery("#sumCount").val(sumCount);//件数合计
	if(flag){
		jQuery("#sumCost").val(sumCost);//成本合计
	}
	if(flag && $n("billNo").length>1 && (sumCostLimit == null)){
		startSync();
		MovePackDwr.getSumCostLimit(function(data){
			sumCostLimit = data;
		});
		endSync();
	}
	if(flag && !isNull(sumCostLimit) && sumCost>sumCostLimit){
		alert("装箱单总成本已经超上限[" + sumCostLimit + "]");
	}
}
function nullToZero(val){
	if(!val)
		return "0";
	return val;
}