function addRow(){
	var verdorId = jQuery("#verdorId").val();
	if(!verdorId){
		alert("请先选择供应商");
		return;
	}
	var _iframeId = "searchOutVendorIframe";
	var options = {
		title : '对公销售单',
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
	var url = ctxPath + '/inout/send.vm?user_action=doOutVendor&&verdorId='+verdorId;
	jQuery.weeboxs.open(url, options);
}

function addRowData(result){
	for(var i=0; i<result.length; i++){
		var obj = result[i],
			saleMoney = obj.saleMoney,
			billno = obj.billno;
		if(!isExistOutVendor(billno)){
			var outNoField = billno,
				saleMoneyField = saleMoney,
				billMoneyField = '<input type="text" name="billMoney" value=""  dataType="Require" msg="不能为空" onblur="checkField()"/>',
				deleteField = '<input type="button" value="删除"  style="width:60px;" onclick="deleteLineRow(this)"/>'+
							'<input type="hidden" name="lineid" value="" />'+
							'<input type="hidden" name="outNo" value="'+ billno +'" />'+
							'<input type="hidden" name="saleMoney" value="'+ saleMoney +'" />';
			
			insertRow("tbl",["", outNoField,saleMoneyField, billMoneyField, deleteField],false);
			changeSeq("tbl");
		}
	}
	changeValues();
}

function checkField(){
	var rowLen = $("tbl").rows.length,
		sumMoney = 0;
	for(var i=0; i<rowLen; i++){
		var billMoney = $n("billMoney")[i].value;
		if(billMoney){
			if(!isDecimal(billMoney) || floatSub(billMoney, 0) <=0){
				alert("发票金额必须是大于的数");
				return false;
			}
			sumMoney = floatAdd(sumMoney, billMoney);
		}
	}
	jQuery("#invoiceMoney").val(sumMoney>0?sumMoney:"");
	return true;
}

function deleteLineRow(button){
	confirm("确定要删除？", function(){
		var rowIndex = button.parentNode.parentNode.rowIndex,
			lineid = $n("lineid")[rowIndex-1].value,
			deleteIds = jQuery("#deleteIds").val();
		if(lineid){
			if(deleteIds){
				deleteIds = deleteIds + ";" + lineid;
			} else {
				deleteIds = lineid ;
			}
		}
		deleteRowByIndex(rowIndex-1, "tbl");
		jQuery("#deleteIds").val(deleteIds);
		changeValues();
		changeSeq("tbl");
	});
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
		var billMoney = $n("billMoney")[i].value;
		if(billMoney){
			sumMoney = floatAdd(sumMoney , billMoney);
		}
	}
	jQuery("#invoiceMoney").val(sumMoney);
}

function isExistOutVendor(billno){
	var rowLen = $("tbl").rows.length;
	for(var i=0; i<rowLen;i++){
		if(billno == $n("outNo")[i].value){
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
		alert("尚未添加对公销售单数据！！！");
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
		var sendHead = getHeadData();
		showLayer(true);
		SendDwr.saveOrUpdateSend(sendHead, lines, jQuery("#deleteIds").val(), function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("保存成功", function(){
						window.location = "send.vm?user_action=toEdit&billid="+data['billid'];
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
		var sendHead = getHeadData();
		showLayer(true);
		SendDwr.saveAndCloseSend(sendHead, lines, jQuery("#deleteIds").val(), function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("处理成功", function(){
						window.location = "send.vm?user_action=toEdit&billid="+data['billid'];
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
				outNo	:$n("outNo")[i].value,
				saleMoney :$n("saleMoney")[i].value,
				billMoney:$n("billMoney")[i].value,
				status:'1'
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