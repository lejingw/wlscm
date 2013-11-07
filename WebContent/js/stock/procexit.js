
/**
 * 增加行
 */
function addRow(){
	procexitCondition();
}


function insertRows(result) {
	var existBills = "";
	for(var i=0 ;i<result.length; i++) {
		var rowData = result[i];
		if(isExistBillno(rowData['outBillno']+":"+rowData['ornaCode'])) {
			existBills = existBills + "【" + rowData['outBillno'] + "】";
			continue;
		}
		var rowLen = $("tbl").rows.length;
		var outBillno = rowData['outBillno'],
			outBillDate = rowData['outBillDate'],
			ornaCode = rowData['ornaCode'],
			materialTypeName = rowData['materialTypeName'],
			itemClassName = rowData['itemClassName'],
			analysisName = rowData['analysisName'],
			colorGradeName = rowData['colorGradeName'],
			cleanName = rowData['cleanName'],
			unitName = rowData['cashUnitName'],
			billNums = rowData['billNums'],
			oldNums = rowData['oldNums'],
			costPrice = rowData['costPrice'],
			exitNums = "<input type='text' name='exitNums' value='' style='width:50px;' onblur='checkField()' dataType='Require'  msg='不能为空'/>",
			isDiff = "<select name='isDiff' dataType='Require'  msg='不能为空'>$StringUtil.emptyOption()</select>" ,
			deleteBtn = "<input type='button' value='删除' style='width:60px;' onclick='deleteExitLineData(this, \"\")'/>" +
					"<input type='hidden' name='accountId'        value = '" + rowData["accountId"]+ "'/>" +
					"<input type='hidden' name='outBillno'        value = '" + rowData["outBillno"]+ "'/>" + 
					"<input type='hidden' name='outBillDate'      value = '" + rowData["outBillDate"]+ "'/>" + 
					"<input type='hidden' name='ornaNo'         value = '" + rowData["ornaCode"]+ "'/>" + 
					"<input type='hidden' name='materialType'     value = '" + rowData["materialType"]+ "'/>" + 
					"<input type='hidden' name='itemClassId'      value = '" + rowData["itemClassId"]+ "'/>" + 
					"<input type='hidden' name='alaysisId'       value = '" + rowData["analysisId"]+ "'/>" + 
					"<input type='hidden' name='mainColorGradeId'     value = '" + rowData["colorGradeId"]+ "'/>" + 
					"<input type='hidden' name='cleanId'          value = '" + rowData["cleanId"]+ "'/>" + 
					"<input type='hidden' name='cashUnit'       value = '" + rowData["cashUnitId"]+ "'/>" + 
					"<input type='hidden' name='unitId'           value = '" + rowData["unitId"]+ "'/>" + 
					"<input type='hidden' name='billNums'         value = '" + rowData["billNums"]+ "'/>" + 
					"<input type='hidden' name='oldNums'          value = '" + rowData["oldNums"]+ "'/>" +
					"<input type='hidden' name='costPrice'          value = '" + rowData["costPrice"]+ "'/>" +
					"<input type='hidden' name='memo' value=''/>" + 
					"<input type='hidden' name='isDiffOld' value=''/>" +
					"<input type='hidden' name='oldExitNums' value=''/>" +
					 "<input type='hidden' name='lineid' value=''/>" ;
		
		insertRow("tbl",["", outBillno,outBillDate,ornaCode, costPrice, materialTypeName,itemClassName,
		                 analysisName,colorGradeName,cleanName, unitName, billNums, oldNums,
		                 exitNums, isDiff, deleteBtn],false);
		dwr.engine.setAsync(false);//设置同步
		DictDwr.getDictsForSlt("yesorno", function(data){
			addOptions2("isDiff", rowLen, data, null, null, true, true, $n("isDiffOld")[rowLen].value);
		});
		dwr.engine.setAsync(true);//取消同步
		enablePackageType();
		changeSeq("tbl");
	}
	if(existBills){
		alert("发料单" + existBills + " 已经存在。");
	}
}

/**
 * 判断出料单是否已经在行中
 * @param billid
 * @returns {Boolean}
 */
function isExistBillno(billno) {
	var chLen = $("tbl").rows.length;
	for(var i=0; i<chLen; i++) {
		if(($n("outBillno")[i].value +":"+$n("ornaNo")[i].value) == billno) {
			return true;
		}
	}
	return false;
}

function deleteExitLineData(button, lineid) {
	confirm("确定要删除?", function(){
		
		var ri = button.parentNode.parentNode.rowIndex;
		var lineid = $n("lineid")[ri-1].value;
		if(lineid) {
			var deleteOldIds = $("deleteOldIds").value;
			var oldIdsArray = deleteOldIds.split(";");
			var isExist = false;
			for(var i=0;i<oldIdsArray.length;i++) {
				if(oldIdsArray[i] == lineid) {
					// 已经存在
					isExist = true;
				}
			}
			if(!isExist) {
				if(deleteOldIds) {
					deleteOldIds = deleteOldIds + ";" + lineid;				
				} else {
					deleteOldIds = lineid;
				}
			}
			$("deleteOldIds").value = deleteOldIds;
		}
		deleteRow(button,"tbl");
		enablePackageType();
		changeValues();
		changeSeq("tbl");
	});
}


function enablePackageType() {
	var chLen = $("tbl").rows.length;
	if (chLen>0) {
		jQuery("#vendorName").attr("disabled", true);
	} else {
		jQuery("#vendorName").attr("disabled", false);
	}
}


function checkField(){
	var rowLen = $("tbl").rows.length;
	var sumMoney = jQuery("#sumMoney").val();
	if(sumMoney){
		if(!isDecimal(sumMoney)) {
			alert("总金额必须是整数");
			return false;
		}
		if(floatSub(sumMoney, 0)<0){
			alert("总金额不能小于0");
			return false;
		}
	}
	for(var i=0; i<rowLen; i++) {
		var oldNums = $n("oldNums")[i].value;
		var exitNums = $n("exitNums")[i].value,
			cashUnit = $n("cashUnit")[i].value;
		if(exitNums) {
			if(!isDecimal(exitNums)){
				alert("第["+(i+1)+"]行，退料数量不合法。");
				return false;
			}
			if(parseFloat(exitNums) <= 0) {
				alert("第["+(i+1)+"]行，退料数量必须是大于0的数");
				return false;
			}
			if(cashUnit == "CT" || cashUnit == "J"){
				if(floatSub(exitNums, Math.round(exitNums)) != 0){
					alert("第["+(i+1)+"]行，退料数量必须是整数");
					return false;
				}
			} 
			if(!oldNums) {
				oldNums = 0;
			}
			if(floatSub(oldNums, exitNums) < 0) {
				alert("第["+(i+1)+"]行，退料数量 不允许 大于 可退料数量");
				return false;
			}
			
		}
	}
	changeValues();
	return true;
}

function changeValues() {
	// ====================== 重量合计 和 粒数合计  件数合计==============
	var rowLen = $("tbl").rows.length,
		allWeight = 0,
		allGrains = 0,
		allCount = 0,
		allCost = 0;
	
	for(var i=0; i<rowLen; i++) {
		var exitNums = $n("exitNums")[i].value;
		var cashUnit = $n("cashUnit")[i].value;
		if(exitNums && cashUnit) {
			if("J" == cashUnit) {// 件
				allCount = floatAdd(allCount , exitNums);
			} else if("K" == cashUnit) {// 重量
				allWeight = floatAdd(allWeight , exitNums);
			} else if("CT" == cashUnit){// 粒数
				allGrains = floatAdd(allGrains , exitNums);
			}
		}
		var costPrice = $n("costPrice")[i].value;
		if(costPrice && isDecimal(costPrice)){
			allCost = floatAdd(allCost, costPrice);
		}
	}
	jQuery("#sumCount").val(allCount);
	jQuery("#sumWeight").val(allWeight);
	jQuery("#sumGrains").val(allGrains);
	jQuery("#sumCost").val(allCost);
}


//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField()) return false;
	var oLen = $("tbl").rows.length;
	if (oLen==0) {
		alert("尚未添加退料行数据！！！");
		return false;
	}

	for(var i=0; i<oLen; i++) {
		var exitNums = $n("exitNums")[i].value;
		var oldNums = $n("oldNums")[i].value;
		if(parseFloat(exitNums) > parseFloat(oldNums)) {
			alert("第[" + (i+1) + "]行 退料数据不合法.");
			return false;
		}
	}
	return true;
}

/**
 * 保存表单
 */
function saveform() {
	if(!checkForm()){
		return ;
	}
	confirm("确定要保存？", function(){
		var lines = getItemList();
		var exitHead = getExitHeadData();
		var deleteOIds = jQuery("#deleteOldIds").val();
		showLayer(true);
		ProcExitDwr.saveOrUpdatePackage(exitHead, lines, deleteOIds, function(msg){
			showLayer(false);
			if(msg) {
				alert(msg);
			} else {
				alert("保存成功", function(){
					window.location = "procExit.vm";
				});
			}
		});
	});
}

function checkBill(){
	if(!checkForm()){
		return ;
	}
	
	confirm("确认要提交审核？", function(){
		var lines = getItemList();
		var exitHead = getExitHeadData();
		var deleteOIds = jQuery("#deleteOldIds").val();
		showLayer(true);
		ProcExitDwr.saveAndCheckExitHead(exitHead, lines, deleteOIds, function(data){
			showLayer(false);
			if(data) {
				if(data['isSuccess'] == "true"){
					alert("单据提交成功", function(){
						window.location = "procExit.vm?user_action=toEdit&billid=" + data['billid'];
					});
				} else {
					alert(data['msg']);
				}
			} else {
				alert("提交审核失败");
			}
		});
	});
}

/**
 * 取头表数据
 */
function getExitHeadData(){
	var exitHead = {
			billid		:jQuery("#billid").val(),
			billno		:jQuery("#billno").val(),
			dodate		:jQuery("#dodate").val(),
			orgId		:jQuery("#orgId").val(),
			status		:"1",
			stockId		:"1",
			sumCount	:jQuery("#sumCount").val(),
			sumWeight	:jQuery("#sumWeight").val(),
			sumGrains	:jQuery("#sumGrains").val(),
			sumCost 	:jQuery("#sumCost").val(),
			sumMoney	:jQuery("#sumMoney").val(),
			vendorId	:jQuery("#vendorId").val(),
			memo		:jQuery("#memo").val()
		};
	return exitHead;
}

/**
 * 取行表数据
 * @returns {Array}
 */
function getItemList() {
	var oldRows = $("tbl").rows.length;
	var oldItemList = [];
	for (var i=0; i<oldRows; i++) {
		var oldItem = {
				billno		:$n("outBillno")[i].value,
				billdate	:$n("outBillDate")[i].value,
				ornaNo		:$n("ornaNo")[i].value,
				materialType:$n("materialType")[i].value,
				itemClassId	:$n("itemClassId")[i].value,
				alaysisId	:$n("alaysisId")[i].value,
				mainColorGradeId:$n("mainColorGradeId")[i].value,
				cleanId		:$n("cleanId")[i].value,
				cashUnit	:$n("cashUnit")[i].value,
				unitId		:$n("unitId")[i].value,
				billNums	:$n("billNums")[i].value,
				oldNums		:$n("oldNums")[i].value,
				memo		:$n("memo")[i].value,
				lineid		:$n("lineid")[i].value,
				exitNums	:$n("exitNums")[i].value,
				oldExitNums	:$n("oldExitNums")[i].value,
				isDiff		:$n("isDiff")[i].value,
				costPrice	:$n("costPrice")[i].value
			};
		oldItemList[i] = oldItem;
	}
	return oldItemList;
}

/**
 * 关闭单据
 */
function closeBill(){
	var billid = jQuery("#billid").val();
	if(billid) {
		confirm("确认要关闭单据？", function(){
			showLayer(true);
			ProcExitDwr.closeExitHead(billid, function(msg){
				showLayer(false);
				if(msg) {
					alert(msg);
				} else {
					alert("单据关闭成功", function(){
						window.location = "procExit.vm";
					});
				}
			});
		});
	} else {
		alert("关闭异常");
	}
}


function showHandover(){
	var _iframeId = "procChangeMiniIframe";
	var options = {
		title : '其他交接单',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 900,
		height : 500,
		okBtnName : '关闭',
		//cancelBtnName : '',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/stock/handover.vm?user_action=miniHandover&_b_t=exit&billid='+jQuery("#billid").val() + "&billCode="+jQuery("#billCode").val();
	jQuery.weeboxs.open(url, options);
}
//=====================================================================================================================
function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}

function cashCharge(){
	var billid = jQuery("#billid").val(),
		charge = jQuery("#charge").val();
	if(charge){
		if(!isDecimal(charge)){
			alert("加工费必须是数字格式");
			return;
		}
		if(floatSub(charge, 0)<0){
			alert("加工费必须大于等于0的数");
			return;
		}
	}
	if(billid){
		confirm("确定要修改加工费？", function(){
			showLayer(true);
			ProcExitDwr.cashCharge(billid, charge, function(data){
				showLayer(false);
				if(data){
					if(data.isSuccess == "true"){
						alert("修改成功", function(){
							window.location.reload();
						});
					} else {
						alert(data.msg);
					}
				} else {
					alert("修改失败");
				}
			});
		});
	} else {
		alert("单据不是关闭状态或者单据已经不存在");
	}
}