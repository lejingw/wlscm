function addRow(){
	var verdorId = jQuery("#verdorId").val();
	if(!verdorId){
		alert("请先选择供应商");
		return;
	}
	var _iframeId = "searchHandoverIframe";
	var options = {
		title : '交接单',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 800,
		height : 350,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			if(!result || result.length<1){
				return ;
			}
			addRowData(result);
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/inout/receive.vm?user_action=doHandover&&verdorId='+verdorId;
	jQuery.weeboxs.open(url, options);
}

function addRowData(result){
	for(var i=0; i<result.length; i++){
		var lines = jQuery.parseJSON(result[i].lines),
			billno = result[i].billno;
		if(!isExistHandover(billno) && lines && lines.length >0){
			for(var j=0; j<lines.length;j++){
				var obj = lines[j];
				var handNoField = obj.billno,
					itemClassIdField = obj.itemClassDesc,
					handUnitField = "<select name='unitNo' dataType='Require' value='' msg='不能为空'></select>",
					noNum = obj.noNum,
					handNum = obj.handNum,
					handNum2 = floatSub(handNum, noNum),
					handNumField = '<input type="text" name="handNum" value="'+ handNum2 +'" style="width:120px;" onblur="checkField()"/>',
					priceField = '<input type="text" name="price" value="" readonly class="none" style="width:120px;"/>',
					moneysField = '<input type="text" name="moneys" value="" readonly class="none" style="width:120px;"/>',
					taxField = '<input type="text" name="tax" value="0.17" dataType="Require" msg="不能为空" onblur="checkField()" style="width:80px;"/>',
					taxMoneyField = '<input type="text" name="taxMoney" value="" readonly class="none" style="width:120px;"/>',
					allMoneyField = '<input type="text" name="allMoney" value="" dataType="Require" msg="不能为空" style="width:120px;" onblur="checkField()"/>',
					memoField = '<input type="text" name="lineMemo" value="" />',
					deleteField = '<input type="button" value="删除"  style="width:60px;" onclick="deleteLineRow(this)"/>'+
								'<input type="hidden" name="lineid" value="" />'+
								'<input type="hidden" name="itemClassId" value="'+ obj.itemClassId +'" />'+
								'<input type="hidden" name="handUnit" value="'+ obj.unitId +'" />'+
								'<input type="hidden" name="handNo" value="'+ obj.billno +'" />';
				insertRow("tbl",["", handNoField,itemClassIdField, handUnitField, handNumField, priceField,
				                 moneysField, taxField, taxMoneyField, allMoneyField, memoField, deleteField],true);
				changeSeq("tbl");
			}
		}
	}
	setHandUnit();
	changeValues();
}

function setHandUnit(){
	DictDwr.getDictsForSlt('purunit', function(data){
		var len = $("tbl").rows.length;
		for(var i=0; i<len; i++){
			addOptions2("unitNo", i,  data, null, null, true, true, $n("handUnit")[i].value);
		}
	});
}
function checkField(){
	var rowLen = $("tbl").rows.length,
		sumMoney = 0;
	for(var i=0; i<rowLen; i++){
		var tax = $n("tax")[i].value,
			allMoney = $n("allMoney")[i].value,
			handNum = $n("handNum")[i].value;
		if(!handNum){
			alert("交接数量不能为空");
			return false;
		} else {
			if(floatSub(handNum, 0) <=0){
				alert("交接数量必须大于0");
				return false;
			}
		}
		if(tax){
			if(!isDecimal(tax) || floatSub(tax, 0) <=0 || floatSub(tax, 1) >=0){
				alert("税率必须是(0~1)之间的数，不包括0,1");
				return false;
			}
		}
		if(allMoney){
			if(!isDecimal(allMoney) || floatSub(allMoney, 0) <=0){
				alert("价税合计必须是大于的数");
				return false;
			}
			sumMoney = floatAdd(sumMoney, allMoney);
		}
		if(tax && allMoney){
            var moneys = floatDiv2(allMoney, floatAdd(1, tax), 2);
            $n("moneys")[i].value = moneys;
            $n("taxMoney")[i].value = floatSub(allMoney, moneys);
			$n("price")[i].value = floatDiv2(moneys, handNum, 2);
		}
	}
	jQuery("#invoiceMoney").val(sumMoney>0?sumMoney:"");
	return true;
}

function deleteLineRow(button){
	var rowIndex = button.parentNode.parentNode.rowIndex,
		billno = $n("handNo")[rowIndex-1].value,
		rowLen = $("tbl").rows.length,
		deleteIds = jQuery("#deleteIds").val();
	for(var i=rowLen-1; i>=0; i--){
		var handNo = $n("handNo")[i].value,
			lineid = $n("lineid")[i].value;
		if(handNo == billno){
			deleteRowByIndex(i, "tbl");
			if(lineid){
				if(deleteIds){
					deleteIds = deleteIds + ";" + lineid;
				} else {
					deleteIds = lineid ;
				}
			}
		}
	}
	jQuery("#deleteIds").val(deleteIds);
	changeValues();
	changeSeq("tbl");
}

function changeValues(){
	var len = $("tbl").rows.length,
		sumMoney = 0;
	if(len <=0){
		jQuery("#vendorName").attr("disabled", false);
	} else {
		jQuery("#vendorName").attr("disabled", true);
	}
	for(var i=0; i<len;i++){
		var allMoney = $n("allMoney")[i].value;
		if(allMoney){
			sumMoney = floatAdd(sumMoney , allMoney);
		}
	}
	jQuery("#invoiceMoney").val(sumMoney);
}

function isExistHandover(billno){
	var rowLen = $("tbl").rows.length;
	for(var i=0; i<rowLen;i++){
		if(billno == $n("handNo")[i].value){
			return true;
		}
	}
	return false;
}


//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var oLen = $("tbl").rows.length;
	if (oLen==0) {
		alert("尚未添加交接单数据！！！");
		return false;
	}
	if(!checkField()) return false;
	return true;
}

/**
 * 保存表单
 */
function saveForm() {
	if(!checkForm()){
		return ;
	}
	confirm("确定要保存？", function(){
		var lines = getItemList();
		var receiveHead = getHeadData();
		showLayer(true);
		ReceiveDwr.saveOrUpdateReceive(receiveHead, lines, jQuery("#deleteIds").val(), function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("保存成功", function(){
						window.location = "receive.vm?user_action=toEdit&billid="+data['billid'];
					});
				} else {
					alert(data['msg']);
				}
			} else {
				alert("保存失败");
			}
		});
	});
}

/**
 * 保存表单
 */
function closeBill() {
	if(!checkForm()){
		return ;
	}
	confirm("确定要记账？", function(){
		var lines = getItemList();
		var receiveHead = getHeadData();
		showLayer(true);
		ReceiveDwr.saveAndCloseReceive(receiveHead, lines, jQuery("#deleteIds").val(), function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("处理成功", function(){
						window.location = "receive.vm?user_action=toEdit&billid="+data['billid'];
					});
				} else {
					alert(data['msg']);
				}
			} else {
				alert("处理失败");
			}
		});
	});
}

/**
 * 撤销单据
 */
function reverseBill(){
	confirm("确定要撤销？", function(){
		var billid = jQuery("#billid").val();
		showLayer(true);
		ReceiveDwr.reverseReceive(billid, function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("处理成功", function(){
						window.location.reload();
					});
				} else {
					alert(data['msg']);
				}
			} else {
				alert("撤销失败");
			}
		});
	});
}

function getHeadData(){
	var head = {
		billid		:jQuery("#billid").val(),
		billno		:jQuery("#billno").val(),
		orgId		:jQuery("#orgId").val(),
		vendorId	:jQuery("#verdorId").val(),
		status		:"1",
		dodate		:jQuery("#dodate").val(),
		invoiceNo	:jQuery("#invoiceNo").val(),
		invoiceDate	:jQuery("#invoiceDate").val(),
		invoiceMoney:jQuery("#invoiceMoney").val(),
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
	var itemList = [];
	for (var i=0; i<oldRows; i++) {
		var item = {
				billid	:jQuery("#billid").val(),
				lineid	:$n("lineid")[i].value,
				itemClassId:$n("itemClassId")[i].value,
				handUnit :$n("unitNo")[i].value,
				handNum:$n("handNum")[i].value,
				handNo:$n("handNo")[i].value,
				price:$n("price")[i].value,
				moneys:$n("moneys")[i].value,
				status:'1',
				tax:$n("tax")[i].value,
				taxMoney:$n("taxMoney")[i].value,
				allMoney:$n("allMoney")[i].value,
				memo:$n("lineMemo")[i].value
			};
		itemList.push(item);
	}
	return itemList;
}
function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}