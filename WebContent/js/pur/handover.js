
function addRow(){
	var rowLen = $("tbl").rows.length;
	var itemClass = "<select name='itemClassId' dataType='Require' value='' onchange='checkitemClass(this)'  msg='不能为空' ></select>" +
					'<input type="button" name="itemBtn" class="analyz" style="display:none;" onclick="showLineChild(this)"/>';
	var unitNo = "<select name='unitNo' dataType='Require' value='' msg='不能为空'></select>";
	
	var hangNum = "<input type='text' name='hangNum' style='width:50px;' value='' onblur='checkField(this)' dataType='Require' msg='不能为空'>" ;
	var goldPrice = "<input type='text' name='goldPrice' style='width:60px;' value='' onblur='checkField(this)' dataType='Require' msg='不能为空'>" ;
	var hangPrice = "<input type='text' name='hangPrice' style='width:60px;' value='' onblur='checkField(this)' dataType='Require'  msg='不能为空'>" ;
	var handCharge = "<input type='text' name='handCharge'  style='width:60px;' value='' onblur='checkField(this)' dataType='Require'  msg='不能为空'>";
	var handMoney = "<input type='text' name='handMoney'  style='width:60px;' value='' readonly dataType='Require'  msg='不能为空'>";
	var noNumLast = "<input type='text' name='noNumLast'  style='width:50px;' value='' onblur='checkField(this)' >";
	var noNumNow = "<input type='text' name='noNumNow'  style='width:50px;' value='' onblur='checkField(this)' >";
	// var noMoney = "<input type='text' name='noMoney'  style='width:50px;' value='' readonly  dataType='Require'  msg='不能为空'>";
	var yesNum = "<input type='text' name='yesNum' value='' readonly style='width:50px;' >" ;
	var yesMoney = "<input type='text' name='yesMoney' readonly value='' style='width:60px;'>" ;
	var diffNum = "<input type='text' name='diffNum'  style='width:50px;' value='' >";
	var diffMoney = "<input type='text' name='diffMoney'  style='width:60px;' value='' readonly >";
	var deleteField = "<input type='button' value='' class='delrow' onclick='deleteHandoverRow(this,\"tbl\")'/>" +
					"<input type='hidden' name='lineid' readonly value=''>" + 
					"<input type='hidden' name='olditemClassId' readonly value=''>" +
					"<input type='hidden' name='noNumLastOld' readonly value=''>" +
					"<input type='hidden' name='noNumNowOld' readonly value=''>" +
					"<input type='hidden' name='oldUnitNo' readonly value=''>" +
					'<input type="hidden" name="oldHangNum" value="" />'+
					'<input type="hidden" name="child_list" value=""/>'+
					'<input type="hidden" name="delete_child_ids" value=""/>';
					
	insertRow("tbl",["", itemClass,unitNo, hangNum, goldPrice,hangPrice, 
	                 handCharge, handMoney, noNumLast, noNumNow, yesNum, yesMoney,  
	                 diffNum, diffMoney, deleteField],false);
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions2("itemClassId", rowLen,  data, null, null, true, true);
	});
	DictDwr.getDictsForSlt('purunit', function(data){
		addOptions2("unitNo", rowLen,  data, null, null, true, true);
	});
	changeSeq("tbl");
}


function checkField(obj){
	var row = obj.parentNode.parentNode.rowIndex-1;
	var hangNum = $n("hangNum")[row].value;
	if(hangNum && !isDecimal(hangNum)){
		alert("交接数量不合法");
		$n("hangNum")[row].value = "";
		return;
	}
	var hangPrice = $n("hangPrice")[row].value;
	if(hangPrice){
		if(!isDecimal(hangPrice)){
			alert("单价不合法");
			$n("hangPrice")[row].value = "";
			return ;
		}
	}
	var handCharge = $n("handCharge")[row].value;
	if(handCharge){
		if(!isDecimal(handCharge)){
			alert("交接工费不合法");
			$n("handCharge")[row].value = "";
			return ;
		}
	}
	var noNumLast = $n("noNumLast")[row].value;
	if(noNumLast){
		if(!isDecimal(noNumLast)){
			alert("前次不合格数量不合法。");
			$n("noNumLast")[row].value = "";
			return ;
		}
	}
	var noNumNow = $n("noNumNow")[row].value;
	if(noNumNow){
		if(!isDecimal(noNumNow)){
			alert("本次不合格数量不合法。");
			$n("noNumNow")[row].value = "";
			return ;
		}
		if(hangNum){
			if(floatSub(hangNum, noNumNow) < 0){
				alert("本次不合格数量不能大于交接数量。");
				$n("noNumNow")[row].value = "";
				return;
			}
			$n("yesNum")[row].value = floatSub(hangNum, noNumNow);
			if(hangPrice){
				$n("yesMoney")[row].value = floatMul(floatSub(hangNum, noNumNow), hangPrice);
			}
		}else {
			alert("请填写交接数量。");
			$n("noNumNow")[row].value = "";
			return;
		}
		
	}
	var goldPrice = $n("goldPrice")[row].value;
	if(goldPrice){
		if(!isDecimal(goldPrice)){
			alert("金价不合法。");
			$n("goldPrice")[row].value = "";
			return ;
		}
	}
	
	if(hangNum && hangPrice) {
		var result = floatMul(hangNum, hangPrice);
		$n("handMoney")[row].value=result;
	}

	/*if(yesNum && hangPrice) {
		var result = floatMul(yesNum, hangPrice);
		$n("yesMoney")[row].value=result;
	}
	if(diffNum && hangPrice) {
		var result = floatMul(diffNum, hangPrice);
		$n("diffMoney")[row].value=result;
	}*/
	
	changeValues();
}

function checkitemClass(itemClass){
	var rowIdx = itemClass.parentNode.parentNode.rowIndex-1;
	if(itemClass.value == '156'){
		$n("itemBtn")[rowIdx].style.display = "";
	} else {
		$n("itemBtn")[rowIdx].style.display = "none";
	}
}


/**
 * 用于重新计算 总数量和总金额
 */
function changeValues(){
	var len = $("tbl").rows.length;
	var sunNum = 0,
		sumCharge = 0,
		sumMoney = 0;
	for(var i=0; i< len; i++) {
		var hangNum = $n("hangNum")[i].value,
			handCharge = $n("handCharge")[i].value,
			handMoney = $n("handMoney")[i].value;
		if(hangNum){
			sunNum = floatAdd(sunNum, hangNum);
		}
		if(handCharge){
			sumCharge = floatAdd(sumCharge, handCharge);
		}
		if(handMoney){
			sumMoney = floatAdd(sumMoney, handMoney);
		}
	}
	jQuery("#sumNum").val(sunNum>0?sunNum:"");
	jQuery("#sumCharge").val(sumCharge>0?sumCharge:"");
	jQuery("#sumMoney").val(sumMoney>0?sumMoney:"");
}

function deleteHandoverRow(button, domId) {
	confirm("确定要删除", function(){
		var rowIndex = button.parentNode.parentNode.rowIndex;
		var lineid = $n("lineid")[rowIndex-1].value;
		var delIds = jQuery("#deleteIds").val();
		if(delIds) {
			jQuery("#deleteIds").val(delIds + ";" + lineid);
		} else {
			jQuery("#deleteIds").val(lineid);
		}
		deleteRow(button, domId);
		changeValues();
		changeSeq("tbl");
	});
}


function changeCashRows(){
	if(jQuery("#dotype").val() == "0"){
		jQuery("select[name='cash_itemClassId']").each(function(){
			jQuery(this).attr("disabled", true);
		});
	}else {
		jQuery("select[name='cash_itemClassId']").each(function(){
			jQuery(this).attr("disabled", false);
		});
	}
}


/**
 * 插入结算数据行
 */
function addCashRow(){
	var dotype = jQuery("#dotype").val();
	var cashRows = $("tbl_cash").rows.length;
	var disable = "";
	if(!dotype){
		alert("请选择业务类型");
		return;
	}
	if(dotype == "2"){alert("不允许选择[自有转换]");return;}
	if(dotype == "0"){ // 采购只允许有一行结算数据行
		disable = "disabled";
		if(cashRows > 0 ) return;
	}
	var cash_itemClassId = "<select name='cash_itemClassId' " + disable + " value='' ></select></td>";
	var cash_memo = "<input type='text' name='cash_memo' style='width:400px;'></td>";
	var cash_money = "<input type='text' name='cash_money' dataType='Require'  msg='不能为空'/> ";
	var deleteBtn = "<input type='button' value='' class='delrow' onclick='deleteCashRow(this,\"tbl_cash\")'/>"+
					 "<input type='hidden' name='cash_itemClassId_old' value=''/>" +
					 "<input type='hidden' name='cash_money_old' value='0'/>" + 
					 "<input type='hidden' name='cash_lineid' value=''/>";
	insertRow("tbl_cash",["", cash_itemClassId,cash_memo, cash_money, deleteBtn],false);
	
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions2("cash_itemClassId", cashRows,  data, null, null, true, true);
	});
	changeSeq("tbl_cash");
}

function deleteCashRow(button, domId) {
	confirm("确定要删除", function(){
		var rowIndex = button.parentNode.parentNode.rowIndex;
		var lineid = $n("cash_lineid")[rowIndex-1].value;
		var delIds = jQuery("#deleteCashIds").val();
		if(delIds) {
			jQuery("#deleteCashIds").val(delIds + ";" + lineid);
		} else {
			jQuery("#deleteCashIds").val(lineid);
		}
		deleteRow(button, domId);
		changeSeq("tbl_cash");
	});
}

//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var oLen = $("tbl").rows.length;
	if (oLen==0) {
		alert("尚未添加交接标签！！！");
		return false;
	}
	var itemClassList = [];
	for(var i=0; i<oLen;i++){
		var itemClassId = $n("itemClassId")[i].value;
		if(itemClassList.contains(itemClassId)){
			alert("交接标签不允许出现重复大类。");
			return false;
		}else{
			if(itemClassId == "156"){
				if(!$n("child_list")[i].value){
					alert("原料钻石 尚未增加分析范围行表。");
					return false;
				}
				var childList = $n("child_list")[i].value;
				if(childList){
					childList = jQuery.parseJSON(childList);
					var childCount = 0;
					jQuery.each(childList, function(idx, child){
						childCount = floatAdd(childCount , child.stoneNum);
					});
					if(childCount != $n("hangNum")[i].value){
						alert("原料分析范围 数量之和 必须等于交接数量");
						return false;
					}
				}
			}
			itemClassList.push(itemClassId);
		}
	}
	if(jQuery("#dotype").val() == "1"){
		var nLen = $("tbl_cash").rows.length;
		var cashItemList = [],
			moneyCount = 0;
		for(var i=0; i<nLen;i++){
			var cashItem = $n("cash_itemClassId")[i].value;
			if(cashItem){
				if(cashItemList.contains(cashItem)){
					alert("结算标签不允许出现重复大类。");
					return false;
				}else {
					cashItemList.push(cashItem);
				}
			} else {
				moneyCount = moneyCount +1;
			}
		}
		if(moneyCount>1){
			alert("无大类的结算行，最多只允许一行");
			return ;
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
	var cashRowLen = $("tbl_cash").rows.length;
	if(jQuery("#dotype").val() == "0" && cashRowLen >1){
		alert("采购业务只允许一行结算数据。");
		return;
	}
	confirm("确定要保存？", function(){
		var lines = getItemList();
		var cashLines = getCashData();
		var handoverHead = getHeadData();
		var deleteIds = [jQuery("#deleteIds").val(), jQuery("#deleteCashIds").val()];
		showLayer(true);
		HandoverDwr.saveOrUpdateHandover(handoverHead, lines, cashLines, deleteIds, function(msg){
			showLayer(false);
			if(msg) {
				alert(msg);
			} else {
				alert("保存成功", function(){
					window.location = "handover.vm";
				});
			}
		});
	});
}


function cashForm(){
	if(!checkForm()){
		return ;
	}
	var cashRowLen = $("tbl_cash").rows.length;
	if(cashRowLen <=0){
		alert("结算行表数据未添加 不允许 结算。");
		return;
	}
	if(jQuery("#dotype").val() == "0" && cashRowLen >1){
		alert("采购业务只允许一行结算数据。");
		return;
	}
	confirm("确定要结算？", function(){
		var head = getHeadData();
		var cashs = getCashData();
		var deleteIds = jQuery("#deleteCashIds").val();
		showLayer(true);
		HandoverDwr.saveOrUpdateCash(head, cashs, deleteIds, function(msg){
			showLayer(false);
			if(msg) {
				alert(msg);
			} else {
				alert("结算成功", function(){
					window.location = "handover.vm";
				});
			}
		});
	});
}


function getHeadData(){
	var head = {
		billid		:jQuery("#billid").val(),
		billno		:jQuery("#billno").val(),
		orgId		:jQuery("#orgId").val(),
		verdorId	:jQuery("#verdorId").val(),
		status		:"1",
		dodate		:jQuery("#dodate").val(),
		dotype		:jQuery("#dotype").val(),
		sourceType	:"CM",
		sourceNo	:jQuery("#sourceNo").val(),
		sourceId	:jQuery("#sourceId").val(),
		expressNo	:jQuery("#expressNo").val(),
		receUser	:jQuery("#receUser").val(),
		receDate	:jQuery("#receDate").val(),
		isPack		:jQuery("#isPack").val(),
		packNo		:jQuery("#packNo").val(),
		isPsale		:jQuery("#isPsale").val(),
		sumNum		:jQuery("#sumNum").val(),
		sumCharge	:jQuery("#sumCharge").val(),
		sumMoney	:jQuery("#sumMoney").val(),
		memo		:jQuery("#memo").val(),
		materialId  :jQuery("#materialId").val()
	};
	return head;
}
/**
 * 取行表数据
 * @returns {Array}
 */
function getItemList() {
	var oldRows = $("tbl").rows.length;
	var oldItemList = [];
	for (var i=0; i<oldRows; i++) {
		childList = $n("child_list")[i].value;
		if(childList) {
			childList = jQuery.parseJSON(childList);
		} else {
			childList = [];
		}
		var oldItem = {
				lineid	:$n("lineid")[i].value,
				itemClassId:$n("itemClassId")[i].value,
				unitNo :$n("unitNo")[i].value,
				hangNum:$n("hangNum")[i].value,
				hangPrice:$n("hangPrice")[i].value,
				yesNum:$n("yesNum")[i].value,
				yesMoney:$n("yesMoney")[i].value,
				isIniv:'0',
				status:'1',
				handCharge:$n("handCharge")[i].value,
				handMoney:$n("handMoney")[i].value,
				noNumLast:$n("noNumLast")[i].value,
				noNumNow:$n("noNumNow")[i].value,
				noNumLastOld:$n("noNumLastOld")[i].value,
				noNumNowOld:$n("noNumNowOld")[i].value,
				//noMoney:$n("noMoney")[i].value,
				diffNum:$n("diffNum")[i].value,
				diffMoney:$n("diffMoney")[i].value,
				goldPrice:$n("goldPrice")[i].value,
				children :childList,
				deleteChildIds:$n("delete_child_ids")[i].value
			};
		oldItemList[i] = oldItem;
	}
	return oldItemList;
}

function getCashData(){
	var rows = $("tbl_cash").rows.length;
	var cashList = [];
	for (var i=0; i<rows; i++) {
		var item = {
				billid:jQuery("#billid").val(),
				lineid	:$n("cash_lineid")[i].value,
				itemClassId:$n("cash_itemClassId")[i].value,
				itemClassIdOld:$n("cash_itemClassId_old")[i].value,
				memo :$n("cash_memo")[i].value,
				moneys:$n("cash_money")[i].value,
				moneysOld:$n("cash_money_old")[i].value
			};
		cashList[i] = item;
	}
	return cashList;
}

/**
 * 审核单据
 */
function checkBill() {
	if(!checkForm()){
		return ;
	}
	var cashRowLen = $("tbl_cash").rows.length;
	if(jQuery("#dotype").val() == "0" && cashRowLen >1){
		alert("采购业务只允许一行结算数据。");
		return;
	}
	confirm("确定要提交审核？", function(){
		var lines = getItemList();
		var cashLines = getCashData();
		var handoverHead = getHeadData();
		var deleteIds = [jQuery("#deleteIds").val(), jQuery("#deleteCashIds").val()];
		showLayer(true);
		HandoverDwr.saveAndCheckHandover(handoverHead, lines, cashLines, deleteIds, function(data){
			showLayer(false);
			redirectBill(data['billid'], data, "单据提交成功");
		});
	});
}

/**
 * 关闭单据
 */
function closeBill() {
	confirm("确定要关闭？", function(){
		var billid = jQuery("#billid").val();
		showLayer(true);
		HandoverDwr.closeBill(billid, function(data){
			showLayer(false);
			redirectBill(billid, data, "单据关闭成功");
    	});		
	});
}

/**
 * 重开单据
 */
function reopenBill() {
	confirm("确定要重开单据？", function(){
		var billid = jQuery("#billid").val();
		showLayer(true);
		HandoverDwr.reopenBill(billid, function(data){
			showLayer(false);
			redirectBill(billid, data, "单据重开成功");
    	});		
	});
}

function receiveBill(){
	var billid = jQuery("#billid").val();
	confirm2("确定要接收单据？", 
		"接收", function(){
			var lines = getItemList();
			showLayer(true);
			HandoverDwr.receiveBill(billid, lines, function(data){
				showLayer(false);
				redirectBill(billid, data, "单据接收成功");
	    	});		
		}, "不接收", function(){
			showLayer(true);
			HandoverDwr.receiveNoBill(billid, function(data){
				showLayer(false);
				redirectBill(billid, data, "处理成功");
			});
		});
}

/**
 * 跳转url
 * @param billid
 * @param data
 * @param msg
 */
function redirectBill(billid, data, msg){
	if(data['isSuccess'] == "true") {
		alert(msg, function(){
			window.location = "handover.vm?user_action=toEdit&billid=" + billid;
		});
	} else {
		alert(data['msg']);
	}
}

/**
 * 修改不合格 数量 及 台账
 */
function updateNoNum(){
	confirm("确定要修改？", function(){
		var billid = jQuery("#billid").val();
		var lines = getItemList();
		var handoverHead = getHeadData();
		showLayer(true);
		HandoverDwr.modifyNoNum(handoverHead, lines,function(data){
			showLayer(false);
			redirectBill(billid, data, "处理成功");
		});
	});
}

/**
 * 采购入库
 */
function materInStock(){
	confirm("确定要入库？", function(){
		var billid = jQuery("#billid").val();
		var handoverHead = getHeadData();
		showLayer(true);
		HandoverDwr.materInStock(handoverHead,function(data){
			showLayer(false);
			redirectBill(billid, data, "处理成功");
		});
	});
}

/**
 * 显示入库统计
 */
function showInstockWin(){
	var _iframeId = "inStockIframe";
	var options = {
		title : '入库统计',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 800,
		height : 400,
		okBtnName : '确定',
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
	var url = ctxPath + '/pur/handover.vm?user_action=instockList&billid='+jQuery("#billid").val();
	jQuery.weeboxs.open(url, options);
}

function selectPurchaseWin(){
	var verdorId = jQuery("#verdorId").val();
	if(verdorId){
		var _iframeId = "selectPurchaseIframe";
		var options = {
			title : '选择采购单',
			contentType : 'iframe',
			iframeId : _iframeId,
			width : 700,
			height : 350,
			okBtnName : '确定',
			closeable:true,
			boxid:'winDiv',
			onok : function(box) {
				var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
				if(result){
					var sourceBills = result.headids,
						sourceNos = result.billnos,
						bizType = result.bizType;
					jQuery("#sourceId").val(sourceBills);
					jQuery("#sourceNo").val(sourceNos);
					jQuery("#dotype").val(bizType);
					jQuery("#dotype").attr("disabled", true);
					box.close();
				}
			},
			oncancel : function(box) {
				box.close();
			}
		};
		var dotype = jQuery("#dotype").val(),
			url = ctxPath + '/pur/handover.vm?user_action=getPurchaseList&verdorId='+verdorId+"&dotype="+dotype;
		jQuery.weeboxs.open(url, options);
	} else {
		alert("请先选择供应商");
	}
	
}
// =============================================================================================================
// --------------------------------------- 孙表- 行表的子表处理--------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------
/**
 * 显示行表 的 子表
 * @param btn
 */
function showLineChild(btn){
	var rowIndex = btn.parentNode.parentNode.rowIndex;
	beforeShowChidren(rowIndex-1);
	var _iframeId = "lineChildrenIframe";
	var options = {
		title : '原料钻石',
		contentType : 'selector',
		iframeId : _iframeId,
		width : 418,
		height : 200,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		lineRow:rowIndex-1,
		onok : function(box) {
			if(status != 1) {box.close();return;}
			if(changeChildValues(this.lineRow)){
				box.close();
			}
		},
		oncancel : function(box) {
			box.close();
		}
	};
	jQuery.weeboxs.open(jQuery("#line_children_head"), options);
	changeAnalysCount();
}

function addChildRow(){
	insertChildRow("", "", "", "");
}

function insertChildRow(childid, alaysisId, stoneNum, unitPrice){
	var childLen = $("line_children").rows.length,
		alaysisIdField = '<select name="child_alaysisId"></select>'+
					'<input type="hidden" name="childid" value="' + childid + '"/>',
		stoneNumField = '<input type="text" name="stoneNum" value="' + stoneNum + '" onblur="checkStoneNum(this)" style="width:70px;"/>',
		unitPriceField = '<input type="text" name="unitPrice" value="' + unitPrice + '" onblur="checkUnitPrice(this)" style="width:70px;"/>',
		deleteBtn = "<input type='button' value='' class='delrow' onclick='deleteChildRow(this)'/>";
	insertRow("line_children", [alaysisIdField, stoneNumField, unitPriceField, deleteBtn], false);
	HandoverDwr.getAnalysisList(function(data){
		addOptions2("child_alaysisId", childLen,  data, null, null, true, true, alaysisId);
	});
}

function beforeShowChidren(rowIndex){
	$("line_children").innerHTML = "";
	jQuery("#deleteChildIds").val($n("delete_child_ids")[rowIndex].value);
	var childList = $n("child_list")[rowIndex].value;
	if(childList){
		childList = jQuery.parseJSON(childList);
		jQuery.each(childList, function(idx, child){
			insertChildRow(child.childid, child.alaysisId, child.stoneNum, child.unitPrice);
		});
	}
	
//	info(childList.name);
//	$n("child_list")[rowIndex].value = '{"childid":"1","stoneNum":"0"}';
}

function deleteChildRow(btn){
	var rowIndex = btn.parentNode.parentNode.rowIndex;
	confirm("确定要删除", function(){
		var childid = $n("childid")[rowIndex-1].value;
		var deleteChildIds = jQuery("#deleteChildIds").val();
		if(deleteChildIds) {
			jQuery("#deleteChildIds").val(deleteChildIds + ";" + childid);
		} else {
			jQuery("#deleteChildIds").val(childid);
		}
		deleteRow(btn, "line_children");
		changeAnalysCount();
	});
}

function changeChildValues(lineRow){
	var childLen = $("line_children").rows.length,
		childList = [],
		alaysisIds = [],
		isSuccess = true,
		j =0;
	for(var i=0; i< childLen ; i++){
		var alaysisId = $n("child_alaysisId")[i].value,
			stoneNum = $n("stoneNum")[i].value,
			unitPrice = $n("unitPrice")[i].value;
		if(!alaysisId){
			alert("分析范围不允许为空");
			isSuccess = false;
			break;
		}
		if(alaysisIds.contains(alaysisId)){
			alert("分析范围不允许重复出现");
			isSuccess = false;
			break;
		}
		if(!stoneNum || floatSub(stoneNum, 0) <= 0){
			alert("数量必须是大于0的数。");
			isSuccess = false;
			break;
		}
		if(!unitPrice || floatSub(unitPrice, 0) <= 0){
			alert("单价必须是大于0的数。");
			isSuccess = false;
			break;
		}
		alaysisIds.push(alaysisId);
		var child = {
			childid		: $n("childid")[i].value,
			lineid		: $n("lineid")[lineRow].value,
			alaysisId	: alaysisId,
			stoneNum	: stoneNum,
			unitPrice	: unitPrice
		};
		childList[j] = child;
		j++;
	}
	if(childLen <= 0){
		alert("尚未增加数据行。");
		isSuccess = false;
	}
	if(isSuccess){
		$n("child_list")[lineRow].value = JSON.stringify(childList);
		$n("delete_child_ids")[lineRow].value = jQuery("#deleteChildIds").val();
		jQuery("#deleteChildIds").val("");
	}
	return isSuccess;
}


function checkStoneNum(obj){
	var stoneNum = obj.value;
	var isSucc = true;
	if(stoneNum){
		if(!isDecimal(stoneNum)){
			alert("数量格式不合法");
			isSucc = false;
		} else {
			if(floatSub(stoneNum, 0) <= 0){
				alert("数量必须是大于0的数。");
				isSucc = false;
			}
		}
	}
	if(!isSucc){
		obj.value = "";
	}
	changeAnalysCount();
}

function changeAnalysCount(){
	var len = $("line_children").rows.length,
	count = 0;
	for(var i=0; i<len; i++){
		var num = $n("stoneNum")[i].value;
		if(num && isDecimal(num) && floatSub(num, 0) >0){
			count = floatAdd(count, num);
		}
	}
	jQuery("#analysCount").val(count);
}

function checkUnitPrice(obj){
	var unitPrice = obj.value;
	var isSucc = true;
	if(unitPrice){
		if(!isDecimal(unitPrice)){
			alert("单价格式不合法");
			isSucc = false;
		} else {
			if(floatSub(unitPrice, 0) <= 0){
				alert("单价必须是大于0的数。");
				isSucc = false;
			}
		}
	}
	if(!isSucc){
		obj.value = "";
	}
}

JSON.stringify = JSON.stringify || function (obj) {
    var t = typeof (obj);
    if (t != "object" || obj === null) {
        // simple data type
        if (t == "string") obj = '"'+obj+'"';
        return String(obj);
    }
    else {
        // recurse array or object
        var n, v, json = [], arr = (obj && obj.constructor == Array);
        for (n in obj) {
            v = obj[n]; t = typeof(v);
            if (t == "string") v = '"'+v+'"';
            else if (t == "object" && v !== null) v = JSON.stringify(v);
            json.push((arr ? "" : '"' + n + '":') + String(v));
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    }
};


function changeVendor(){
	jQuery("#sourceId").val("");
	jQuery("#sourceNo").val("");
	jQuery("#dotype").val("");
	jQuery("#dotype").attr("disabled", false);
}

// --------------------------------------------修改行信息-----------------------------------------------------

function editLine(){
	var billid = jQuery("#billid").val();
	var _iframeId = "EditLineIframe";
	var options = {
		title : '交接单行表',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 800,
		height : 350,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			if(result ){
				box.close();
				saveLine(result);
			}
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/pur/handover.vm?user_action=toEditLine&billid='+billid;
	jQuery.weeboxs.open(url, options);
}

function saveLine(result){
	var len = $("tbl").rows.length,
		isChange = false,
		lines = result.lines;
	/*for(var i=0;i<len;i++){
		var lineid = $n("lineid")[i].value,
			hangNum = $n("hangNum")[i].value,
			unitNo = $n("unitNo")[i].value,
			itemClassId = $n("itemClassId")[i].value,
			children = $n("child_list")[i].value;
		for(var j=0;j<lines.length;j++){
			var line = lines[j];
			if(lineid == line.lineId){
				if(itemClassId != line.itemClassId || floatSub(hangNum, line.hangNum) !=0 || unitNo != line.unitNo){
					isChange = true;
					break;
				}
				var oldChildList = jQuery.parseJSON(children);
				if(floatSub(oldChildList.length - line.children.length) != 0){
					isChange = true;
					break;
				}
				
				for(var k=0; k<line.children.length;k++){
					var newChild = line.children[k];
					if(!newChild.childid){
						isChange = true;
						break;
					} else {
						for(var m=0; m<oldChildList.length; m++){
							var oldChild = oldChildList[m];
							if(newChild.childid == oldChild.childid){
								if(newChild.alaysisId != oldChild.alaysisId || floatSub(newChild.stoneNum, oldChild.stoneNum) != 0 || floatSub(newChild.unitPrice, oldChild.unitPrice) !=0) {
									isChange = true;
									break;
								}
							}
						}
						if(isChange){
							break;
						}
					}
				}
				if(isChange){
					break;
				}
			}
		}
	}*/
	isChange = checkLineChange(lines);
	if(isChange){
		confirm("确定要修改行信息？", function(){
			var deleteLineIds = result.deleteLineIds,
				sumCount = 0,
				sumCharge = 0,
				sumMoney = 0,
				handLines = [],
				billid = jQuery("#billid").val(),
				handHead = {
					billid:billid,
					updateDate:jQuery("#modifyDate").val(),
					status:jQuery("#status").val(),
					sourceType:jQuery("#sourceType").val(),
					billno:jQuery("#billno").val(),
					dodate:jQuery("#dodate").val(),
					orgId:jQuery("#orgId").val(),
					verdorId:jQuery("#verdorId").val()
				};
			for(var i=0; i<lines.length; i++){
				var line = lines[i];
				var childList = line.child_list;
				if(childList) {
					childList = jQuery.parseJSON(childList);
				} else {
					childList = [];
				}
				var handLine = {
					billid		:billid,
					lineid		:line.lineid,
					itemClassId	:line.itemClassId,
					oldItemClassId:line.oldItemClassId,
					unitNo		:line.unitNo,
					hangNum		:line.hangNum,
					handMoney	:line.handMoney,
					yesMoney	:line.yesMoney,
					yesNum		:line.yesNum,
					handMoney	:line.handMoney,
					handCharge	:line.handCharge,
					hangPrice	:line.hangPrice,
					goldPrice	:line.goldPrice,
					noNumLast	:line.noNumLast,
					noNumNow	:line.noNumNow,
					children	:childList,
					deleteChildIds:line.delete_child_ids
				};
				handLines.push(handLine);
				if(line.hangNum){
					sumCount = floatAdd(sumCount, line.hangNum);
				}
				if(line.handCharge){
					sumCharge = floatAdd(sumCharge, line.handCharge);
				}
				if(line.handMoney){
					sumMoney = floatAdd(sumMoney, line.handMoney);
				}
			}
			handHead.sumNum = sumCount;
			handHead.sumCharge = sumCharge;
			handHead.sumMoney = sumMoney;
			
			showLayer(true);
			HandoverDwr.changeHandoverLine(handHead, handLines, deleteLineIds, function(data){
				showLayer(false);
				if(data){
					if(data.isSuccess == "true"){
						alert("行信息修改成功", function(){
							window.location = "handover.vm?user_action=toEdit&billid=" + jQuery("#billid").val();
						});
					} else {
						alert(data.msg);
					}
				} else {
					alert("修改失败");
				}
			});
		});
	}
}

function checkLineChange(lines){
	var len = $("tbl").rows.length,
		isChange = false;
	if(floatSub(len , lines.length) != 0){
		return true;
	}
	for(var i=0;i<len;i++){
		var lineid = $n("lineid")[i].value,
			hangNum = $n("hangNum")[i].value,
			unitNo = $n("unitNo")[i].value,
			itemClassId = $n("itemClassId")[i].value,
			goldPrice = $n("goldPrice")[i].value,
			hangPrice = $n("hangPrice")[i].value,
			handCharge = $n("handCharge")[i].value,
			children = $n("child_list")[i].value;
		for(var j=0;j<lines.length;j++){
			var line = lines[j];
			if(lineid == line.lineid){
				if(itemClassId != line.itemClassId || floatSub(hangNum, line.hangNum) !=0 
						|| unitNo != line.unitNo || floatSub(goldPrice, line.goldPrice) !=0 
						|| floatSub(hangPrice, line.hangPrice) !=0 || floatSub(handCharge, line.handCharge) !=0){
					isChange = true;
					//info(1);
					break;
				}
				var oldChildList = jQuery.parseJSON(children),
					newChildList = jQuery.parseJSON(line.child_list);
				//info(line.child_list);
				//info(children);
				//info(oldChildList.length);
				///info(newChildList.length);
				if(floatSub(oldChildList.length , newChildList.length) != 0){
					isChange = true;
					//info(2);
					break;
				}
				
				for(var k=0; k<newChildList.length;k++){
					var newChild = newChildList[k];
					if(!newChild.childid){
						isChange = true;
						//info(3);
						break;
					} else {
						for(var m=0; m<oldChildList.length; m++){
							var oldChild = oldChildList[m];
							if(newChild.childid == oldChild.childid){
								if(newChild.alaysisId != oldChild.alaysisId || floatSub(newChild.stoneNum, oldChild.stoneNum) != 0 || floatSub(newChild.unitPrice, oldChild.unitPrice) !=0) {
									isChange = true;
									//info(4);
									break;
								}
							}
						}
						if(isChange){
							break;
						}
					}
				}
				if(isChange){
					break;
				}
			}
		}
		if(isChange){
			break;
		}
	}
	return isChange;
}


function showLineHistory(){
	var billid = jQuery("#billid").val();
	var _iframeId = "LineHistoryIframe";
	var options = {
		title : '交接单行信息历史',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 800,
		height : 400,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/pur/handover.vm?user_action=doSearchLineRec&billid='+billid;
	jQuery.weeboxs.open(url, options);
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}