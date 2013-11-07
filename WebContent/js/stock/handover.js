
function addRow(){
	var rowLen = $("tbl").rows.length;
	var itemClass = "<select name='itemClassId' dataType='Require' onchange='checkitemClass(this)' value='' msg='不能为空'></select>"+
					'<input type="button" name="itemBtn" class="analyz" style="display:none;" onclick="showLineChild(this)"/>';
	var unitNo = "<select name='unitNo' dataType='Require' value='' msg='不能为空'>" +
				"</select>";
	
	var hangNum = "<input type='text' name='hangNum' style='width:50px;' value='' onblur='checkField()' dataType='Require' msg='不能为空'>" ;
	var hangPrice = "<input type='text' name='hangPrice' style='width:50px;' value='' onblur='checkField()' dataType='Require'  msg='不能为空'>" ;
	var yesNum = "<input type='text' name='yesNum' readonly value='' style='width:50px;'>" ;
	var yesMoney = "<input type='text' name='yesMoney' readonly value='' style='width:50px;'>" ;
	var deleteField = "<input type='button' value='删除' style='width:60px;' onclick='deleteHandoverRow(this,\"tbl\")'/>" +
					"<input type='hidden' name='lineid' readonly value=''>"+
					"<input type='hidden' name='olditemClassId' readonly value=''>"+
					'<input type="hidden" name="child_list" value=""/>'+
					"<input type='hidden' name='oldUnitNo' readonly value=''>"+
					'<input type="hidden" name="oldHangNum" value="" />'+
					'<input type="hidden" name="delete_child_ids" value=""/>';
					
	insertRow("tbl",["", itemClass,unitNo, hangNum, hangPrice, yesNum, yesMoney, deleteField],false);
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions2("itemClassId", rowLen,  data, null, null, true, true);
	});
	DictDwr.getDictsForSlt('purunit', function(data){
		addOptions2("unitNo", rowLen,  data, null, null, true, true);
	});
	changeSeq("tbl");
}


function checkField(){
	var len = $("tbl").rows.length;
	for(var i=0; i<len ;i++){
		var hangNum = $n("hangNum")[i].value,
			hangPrice = $n("hangPrice")[i].value;
		if(hangNum){
			if(!isDecimal(hangNum)){
				alert("交接数量必须是数字");
				return false;
			}
			if(floatSub(hangNum, 0) <=0){
				alert("交接数量必须大于0");
				return false;
			}
		}
		if(hangPrice){
			if(!isDecimal(hangPrice)){
				alert("单价必须是数字");
				$n("hangPrice")[row].value = "";
				return false;
			}
			if(floatSub(hangPrice, 0) <=0){
				alert("单价必须大于0");
				return false;
			}
		}
		if(hangNum && hangPrice) {
			var result = floatMul(hangNum, hangPrice);
			$n("yesNum")[i].value=hangNum;
			$n("yesMoney")[i].value=result;
		}
	}
	// 修改总数量和总金额
	changeValues();
	return true;
}

/**
 * 用于重新计算 总数量和总金额
 */
function changeValues(){
	var len = $("tbl").rows.length;
	// alert(len);
	var sunNum = 0;
	var sumMoney = 0;
	for(var i=0; i< len; i++) {
		if($n("hangNum")[i].value){
			// alert("hangNum:" + $n("hangNum")[i].value);
			sunNum = floatAdd(sunNum, $n("hangNum")[i].value);
			if($n("hangPrice")[i].value) {
				var mon = floatMul($n("hangNum")[i].value, $n("hangPrice")[i].value);
				// alert("mon:" + mon);
				sumMoney = floatAdd(sumMoney, mon);
			}
		}
	}
	jQuery("#sumNum").val(sunNum);
	jQuery("#sumMoney").val(sumMoney);
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


//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var oLen = $("tbl").rows.length;
	if (oLen==0) {
		alert("尚未添加交接标签！！！");
		return false;
	}
	if(!checkField()) return;
	var itemClassIds = [];
	for(var i=0; i<oLen; i++){
		var itemClassId = $n("itemClassId")[i].value;
		if(itemClassIds.contains(itemClassId)){
			alert("不能有重复的大类");
			return false;
		} else {
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
					for(var j=0; j<childList.length; j++){
						if(!childList[j].unitPrice || childList[j].unitPrice.length <= 0){
							alert("原料分析范围单价必须大于0的数");
							return false;
						}
					}
				}
			}
			itemClassIds.push(itemClassId);
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
		var handoverHead = getHeadData();
		var deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		OtherHandoverDwr.saveOrUpdateHandover(handoverHead, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("保存成功", function(){
						var isMini = jQuery("#isMini").val();
						if(isMini=="true"){
							var srcBillid = jQuery("#sourceId").val(),
								billCode = jQuery("#sourceType").val();
							window.location = "handover.vm?user_action=miniHandover&billid=" + srcBillid + "&billCode=" + billCode;
						} else{
							window.location = "handover.vm?user_action=toEdit&billid=" + data['billid'];
						}
					});
				} else {
					alert(data['msg']);
				}
			}else {
				alert("保存失败");
			}
		});
	});
}

function checkHandover() {
	if(!checkForm()){
		return ;
	}
	confirm("确定要提交审批？", function(){
		var lines = getItemList();
		var handoverHead = getHeadData();
		var deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		OtherHandoverDwr.saveAndCheckHandover(handoverHead, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("提交审批成功", function(){
						var isMini = jQuery("#isMini").val();
						if(isMini=="true"){
							var srcBillid = jQuery("#sourceId").val(),
								billCode = jQuery("#sourceType").val();
							window.location = "handover.vm?user_action=miniHandover&billid=" + srcBillid + "&billCode=" + billCode;
						} else{
							window.location = "handover.vm?user_action=toEdit&billid=" + data['billid'];
						}
					});
				} else {
					alert(data['msg']);
				}
			}else {
				alert("提交审批失败");
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
			sourceType	:jQuery("#sourceType").val(),
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
			memo		:jQuery("#memo").val()
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
				handMoney:$n("yesMoney")[i].value,
				isIniv:'0',
				status:'1',
				children :childList,
				deleteChildIds:$n("delete_child_ids")[i].value
			};
		oldItemList[i] = oldItem;
	}
	return oldItemList;
}

/**
 * 审核单据
 */
function checkBill() {
	confirm("确定要审核？", function(){
		var billid = jQuery("#billid").val();
		showLayer(true);
		OtherHandoverDwr.checkBill(billid, function(data){
			showLayer(false);
    		if(data['isSuccess'] == "true") {
    			alert("审核成功！", function(){
    				window.location = "handover.vm?user_action=toEdit&billid=" + billid;
				});
    		} else {
    			alert(data['msg']);
    		}
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
		OtherHandoverDwr.closeBill(billid, function(data){
			showLayer(false);
    		if(data['isSuccess'] == "true") {
    			alert("关闭成功！", function(){
    				window.location = "handover.vm?user_action=toEdit&billid=" + billid;
				});
    		} else {
    			alert(data['msg']);
    		}
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
		height : 300,
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
	var url = ctxPath + '/stock/handover.vm?user_action=instockList&billid='+jQuery("#billid").val();
	jQuery.weeboxs.open(url, options);
}
function checkitemClass(itemClass){
	var rowIdx = itemClass.parentNode.parentNode.rowIndex-1;
	if(itemClass.value == '156'){
		$n("itemBtn")[rowIdx].style.display = "";
	} else {
		$n("itemBtn")[rowIdx].style.display = "none";
	}
}

//=============================================================================================================
//--------------------------------------- 孙表- 行表的子表处理--------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------
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
	var st = sourceType||'',
		isDisabled = false;
	if(st == "TL"){
		isDisabled = true;
	}
	var childLen = $("line_children").rows.length,
		alaysisIdField = '<select name="child_alaysisId" ' + (isDisabled?'disabled':'') + '></select>'+
					'<input type="hidden" name="childid" value="' + childid + '"/>',
		stoneNumField = '<input type="text" name="stoneNum" value="' + stoneNum + '" onblur="checkStoneNum(this)" style="width:70px;" ' + (isDisabled?'readOnly':'') + '/>',
		unitPriceField = '<input type="text" name="unitPrice" value="' + unitPrice + '" onblur="checkUnitPrice(this)" style="width:70px;"/>',
		deleteBtn = "<input type='button' value='' class='delrow'  " + (isDisabled?"":"onclick='deleteChildRow(this)'") + "/>";
	insertRow("line_children", [alaysisIdField, stoneNumField, unitPriceField, deleteBtn], false);
	OtherHandoverDwr.getAnalysisList(function(data){
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


function editLine(){
	var billid = jQuery("#billid").val();
	var _iframeId = "EditLineIframe";
	var options = {
		title : '其他交接单行表',
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
				box.close();
				saveLine(result);
			}
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/stock/handover.vm?user_action=toEditLine&billid='+billid;
	jQuery.weeboxs.open(url, options);
}



function saveLine(result){
	var len = $("tbl").rows.length,
		isChange = false,
		lines = result.lines;

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
			OtherHandoverDwr.changeHandoverLine(handHead, handLines, deleteLineIds, function(data){
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
			hangPrice = $n("hangPrice")[i].value,
			children = $n("child_list")[i].value;
		for(var j=0;j<lines.length;j++){
			var line = lines[j];
			if(lineid == line.lineid){
				if(itemClassId != line.itemClassId || floatSub(hangNum, line.hangNum) !=0 
						|| unitNo != line.unitNo || floatSub(hangPrice, line.hangPrice) !=0 ){
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
		title : '其他交接单行信息历史',
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
	var url = ctxPath + '/stock/handover.vm?user_action=doSearchLineRec&billid='+billid;
	jQuery.weeboxs.open(url, options);
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}


function showSourceBill(){
	var sourceType = jQuery("#sourceType").val(),
		sourceId = jQuery("#sourceId").val();
	if(sourceType && sourceId){
		var url = ctxPath, title = "来源单据";
		if(sourceType === "CF"){
			url = url + '/stock/procPackage.vm?user_action=doEdit&billid='+sourceId;
			title = title + "(拆包混包)";
		} else if(sourceType === "TL") {
			url = url + '/stock/procExit.vm?user_action=toEdit&billid='+sourceId;
			title = title + "(退料)";
		} else if(sourceType === "XT"){
			url = url + '/stock/procChange.vm?user_action=toEdit&billid='+sourceId;
			title = title + "(形态转换)";
		} else if(sourceType === "CS"){
			url = url + '/stock/procDismantle.vm?user_action=toEdit&billid='+sourceId;
			title = title + "(拆石)";
		} else if(sourceType === "LT"){
			url = url + '/stock/procPurify.vm?user_action=toEdit&billid='+sourceId;
			title = title + "(料提纯)";
		} else {
			return;
		}
		
		var _iframeId = "ShowSourceBillIframe",
			width = document.body.clientWidth;
		var options = {
			title : title,
			contentType : 'iframe',
			iframeId : _iframeId,
			width : width,
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
		jQuery.weeboxs.open(url, options);
	}
}