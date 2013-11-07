function showSelectCashWinSrc(){
	var billType= jQuery("#billType").val(),
		vendorId = jQuery("#verdorId").val(),
		cashId = jQuery("#cashId").val(),
		articleTypeId = getArticleId();
	if(!billType){
		alert("请选择业务类型");
		return;
	}
	if(!vendorId){
		alert("请选择供应商");
		return;
	}
	// info(billType);
	var billTypeSel = $("billType"),
		title = billTypeSel.options[billTypeSel.selectedIndex].text;
	
	var rowLen = $("tbl").rows.length;
	var data = "";
	for(var i=0; i<rowLen;i++){
		var bill_type = $n("bill_type")[i].value;
		if(billType == bill_type){
			var lineId = $n("lineId")[i].value,
				prId = $n("prId")[i].value;
			if(data){
				data = data + ";" + lineId + ":" + prId;
			} else {
				data = lineId + ":" + prId;
			}
		}
	}
	// info(data);
	var url = "cashHead.vm?user_action=selectCash&is_src=1&cashId="+cashId+"&vendorId="+vendorId+"&billType="+billType+"&data="+data+"&articleTypeId="+articleTypeId;
	
	showWindow(title, url);
}

function showSelectCashWinDist(){
	var billType= jQuery("#billType").val(),
		vendorId = jQuery("#verdorId").val(),
		cashId = jQuery("#cashId").val(),
		articleTypeId = getArticleId(),
		title;
	if(!billType){
		alert("请选择业务类型");
		return;
	}
	if(!vendorId){
		alert("请选择供应商");
		return;
	}
	billType = floatSub(0, billType)+"";
	// info(billType);
	if(billType == "1"){
		title = "委外发料";
	} else {
		title = "交接退料";
	}
	var rowLen = $("tbl").rows.length;
	var data = "";
	for(var i=0; i<rowLen;i++){
		var bill_type = $n("bill_type")[i].value;
		if(billType == bill_type){
			var lineId = $n("lineId")[i].value,
				prId = $n("prId")[i].value;
			if(data){
				data = data + ";" + lineId + ":" + prId;
			} else {
				data = lineId + ":" + prId;
			}
		}
	}
	// info(data);
	var url = "cashHead.vm?user_action=selectCash&is_src=0&cashId="+cashId+"&vendorId="+vendorId+"&billType="+billType+"&data="+data+"&articleTypeId="+articleTypeId;
	
	showWindow(title, url);
}

function showWindow(title, url){
	var _iframeId = "cashHeadShowWinIframe";
	var options = {
		title : '选择核销单据(' + title + ")",
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 720,
		height : 400,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			
			if(!result || result.data.length<1){
				//alert("请先选择台账记录");
				return ;
			}
			insertRows(result);
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	
	jQuery.weeboxs.open(url, options);
}
/**
 * 取商品类别
 * @returns {String}
 */
function getArticleId(){
	var rowLen  = $("tbl").rows.length,
		articleId = "";
	for(var i=0;i<rowLen;i++){
		articleId = $n("articleId")[i].value;
		if(articleId){
			break;
		}
	}
	return articleId;
}

function insertRows(result){
	
	if(result.is_src == "1"){
		var idx = isExistSrcBill();
		if(idx >=0){// 已经存在源单据
			// 确认是否删除
			confirm("确认要替换源单据？", function(){
				var  lineId = $n("lineId")[idx].value;
				if(lineId){
					// 数据库删除
					$("tbl").deleteRow(idx);
				} else {
					info("lineId:"+lineId);
					info(idx);
					$("tbl").deleteRow(idx);
				}
				addRow(result);
				changeValues();
			}, function(){
				
			});
		} else {
			// 直接插入行
			addRow(result);
		}
	} else {
		addRow(result);
	}
	changeValues();
}

function addRow(result){
	var data = result.data,
		is_src = result.is_src,
		billType = result.billType;
	for(var i=0; i<data.length; i++){
		var lineId = data[i].lineId,
			billno = data[i].bill_no,
			prId = data[i].prId,
			articleTypeId = data[i].articleTypeId,
			articleTypeName = data[i].articleTypeName,
			noNums = data[i].noNums?data[i].noNums:"0",
			userNums = data[i].userNums?data[i].userNums:"0",
			oldCurNums = data[i].oldCurNums?data[i].oldCurNums:"0",
			itemClassId = data[i].itemClassId,
			itemClassName = data[i].itemClassName;
		noNums = floatSub(noNums, userNums);
		if(lineId){
			noNums = floatAdd(noNums, oldCurNums);
		}
		var typeField = "";
		if(billType == "1"){
			typeField = "委外发料";
		} else {
			typeField = "交接单";
		}
		var curNumsField = '<input type="text" name="curNums" value="" onblur="checkField()" dataType="Require" msg="不能为空"/>',
			memoField = '<input type="text" name="memo" value=""/>',
			deleteField = '<input type="button" value="删除" style="width:60px;" onclick="deleteCashRow(this, \'tbl\')"/>'+
						'<input type="hidden" name="lineId" value="'+lineId+'" />'+
						'<input type="hidden" name="prId" value="'+prId+'" />'+
						'<input type="hidden" name="bill_type" value="'+billType+'" />'+
						'<input type="hidden" name="lessNums" value="'+noNums+'" />'+
						'<input type="hidden" name="itemClassId" value="'+itemClassId+'" />'+
						'<input type="hidden" name="oldCurNums" value="'+oldCurNums+'" />'+
						'<input type="hidden" name="articleId" value="'+articleTypeId+'" />';
		if(is_src == "1"){
			insertRow("tbl",["", typeField,billno, articleTypeName, itemClassName, noNums, curNumsField, memoField, deleteField],true);
			deleteLineIdFromDeleteIds(lineId);
		} else {
			if(isExistCashBill(prId) == -1){
				insertRow("tbl",["", typeField,billno, articleTypeName, itemClassName, noNums,curNumsField, memoField, deleteField],false);
				deleteLineIdFromDeleteIds(lineId);
			}
		}
		changeSeq("tbl");
	}
}
/**
 * 在增加行 时，如果lineid不为空，并且lineid在deleteIds中的话，需要把他从已删除列表中T掉
 * @param lineId
 */
function deleteLineIdFromDeleteIds(lineId){
	var deleteIds = $("deleteIds").value,
		idsArray = deleteIds.split(";"),
		deleteNewIds = "";
	if(lineId && deleteIds){
		for(var i=0; i<idsArray.length;i++){
			if(!idsArray.contains(lineId)){
				if(deleteNewIds) {
					deleteNewIds = deleteNewIds + ";" + idsArray[i];
				} else {
					deleteNewIds = idsArray[i];
				}
			}
		}
		$("deleteIds").value = deleteNewIds;
	}
}
/**
 * 删除行
 * @param obj
 * @param domId
 */
function deleteCashRow(obj, domId){
	confirm("确定要删除", function(){
		var ri = obj.parentNode.parentNode.rowIndex;
		var lineId = $n("lineId")[ri-1].value;
		if(lineId) {
			var deleteIds = $("deleteIds").value;
			var idsArray = deleteIds.split(";");
			if(!idsArray.contains(lineId)){
				if(deleteIds) {
					deleteIds = deleteIds + ";" + lineId;
				} else {
					deleteIds = lineId;
				}
			}
			$("deleteIds").value = deleteIds;
		}
		deleteRow(obj, domId);
		changeValues();
		changeSeq("tbl");
	});
}
/**
 * 判断是否存在目标单据
 * @param prId
 * @returns {Number}
 */
function isExistCashBill(prId){
	var idx = -1;
	var rowLen  = $("tbl").rows.length;
	for (var i=0; i<rowLen; i++){
		if(prId == $n("prId")[i].value){
			idx = i;
			break;
		}
	}
	console.info(prId);
	console.info(idx);
	return idx;
}
/**
 * 判断是否存在源单据
 * @returns {Number}
 */
function isExistSrcBill(){
	var idx = -1;
	var rowLen  = $("tbl").rows.length;
	var billType = jQuery("#billType").val();
	for (var i=0; i<rowLen; i++){
		if(billType == $n("bill_type")[i].value){
			idx = i;
			break;
		}
	}
	return idx;
}

function changeValues(){
	var rowLen = $("tbl").rows.length;
	if(rowLen <=0){
		jQuery("#billType").attr("disabled", false);
		jQuery("#vendorName").attr("disabled", false);
	} else {
		jQuery("#billType").attr("disabled", true);
		jQuery("#vendorName").attr("disabled", true);
	}
}

function checkField(){
	var rwLen = $("tbl").rows.length,
		dotype = jQuery("#billType").val();
	for(var i=0; i<rwLen; i++){
		var lessNums = $n("lessNums")[i].value,
			curNums = $n("curNums")[i].value,
			billType = $n("bill_type")[i].value;
		if(curNums){
			if(!isDecimal(curNums)){
				alert("本次结算数量 不合法");
				return false;
			} else {
				if(floatSub(lessNums, curNums) <0){
					alert("本次结算数量 不能大于 需核销数量");
					return false;
				}
			}
			if(billType == dotype){
				jQuery("#cashNums").val(curNums);
			}
		}
	}
	return true;
}

function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField())return false;
	var rowLen = $("tbl").rows.length,
		typeNum = [],
		num = 0,
		numed = 0;
	if(rowLen <=0){
		alert("尚未添加需要核销的单据");
		return false;
	} else {
		for(var i=0; i<rowLen; i++){
			var billType = $n("bill_type")[i].value,
				curNums = $n("curNums")[i].value;
			if(!typeNum.contains(billType)){
				typeNum.push(billType);
			}
			if(billType == "-1"){
				num = floatAdd(num, curNums);
			} else {
				numed = floatAdd(numed, curNums);
			}
		}
		if(typeNum.length != 2){
			alert("尚未添加 源单据 或者 目标单据");
			return false;
		}
		if(num != numed){
			alert("核销数量总和 与 被核销数量总和必须一致");
			return false;
		}
	}
	return true;
}

function saveForm(){
	if(checkForm()){
		confirm("确定要保存？", function(){
			var head = getHeadData(),
				lines = getLineData(),
				deleteIds = jQuery("#deleteIds").val();
			showLayer(true);
			CashHeadDwr.saveOrUpdateCashHead(head, lines, deleteIds, function(msg){
				showLayer(false);
				if(msg){
					alert(msg);
				} else {
					alert("保存成功", function(){
						window.location = "cashHead.vm";
					});
				}
			});
		});
	}
}

function getHeadData(){
	var head = {
		cashId:jQuery("#cashId").val(),
		billNo:jQuery("#billNo").val(),
		voderId:jQuery("#verdorId").val(),
		orgId:jQuery("#orgId").val(),
		billDate:jQuery("#billDate").val(),
		billType:jQuery("#billType").val(),
		dotype:jQuery("#billType").val(),
		cashNums:jQuery("#cashNums").val(),
		status:"1"
	};
	return head;
}

function getLineData(){
	var dataList = [],
		rowLen = $("tbl").rows.length;
	for(var i=0; i<rowLen; i++){
		var obj = {
				cashId:jQuery("#cashId").val(),
				lineId:$n("lineId")[i].value,
				prId:$n("prId")[i].value,
				billType:$n("bill_type")[i].value,
				lessNums:$n("lessNums")[i].value,
				curNums:$n("curNums")[i].value,
				memo:$n("memo")[i].value,
				status:"1",
				itemClassId:$n("itemClassId")[i].value,
				oldCurNums:$n("oldCurNums")[i].value,
				articleId:$n("articleId")[i].value
		};
		dataList.push(obj);
	}
	return dataList;
}

/**
 * 审核单据
 */
function checkBill() {
	if(checkForm()){
		confirm("确定要提交审核？", function(){
			var head = getHeadData(),
				lines = getLineData(),
				deleteIds = jQuery("#deleteIds").val();
			showLayer(true);
			CashHeadDwr.saveAndCheckCashHead(head, lines, deleteIds, function(data){
				showLayer(false);
				redirectBill(data['cashId'], data, "单据提交成功");
			});
		});
	}
}

/**
 * 核算单据
 */
function closeBill() {
	confirm("确定要核算单据？", function(){
		var cashId = jQuery("#cashId").val(),
			lines = getLineData();
		showLayer(true);
		CashHeadDwr.closeBill(cashId, lines, function(data){
			showLayer(false);
			redirectBill(cashId, data, "结算成功");
    	});	
	});
}

/**
 * 跳转url
 * @param billid
 * @param data
 * @param msg
 */
function redirectBill(cashId, data, msg){
	if(data['isSuccess'] == "true") {
		alert(msg, function(){
			window.location = "cashHead.vm?user_action=toEdit&cashId=" + cashId;
		});
	} else {
		alert(data['msg']);
	}
}

function writeAccount(){}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}