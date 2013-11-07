
function checkField(){
	var chaMoney = jQuery("#chaMoney").val();
	if(chaMoney){
		if(!isDecimal(chaMoney) || floatSub(chaMoney, 0) <=0){
			alert("金额必须是大于0的数");
			return false;
		}
	}
	return true;
}

function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField())return false;
	
	return true;
}

function saveForm(){
	if(checkForm()){
		confirm("确定要保存？", function(){
			var prodData = getProdData();
			showLayer(true);
			PayChangeDwr.saveOrUpdatePayChange(prodData, function(msg){
				showLayer(false);
				if(msg){
					alert(msg);
				} else {
					alert("保存成功", function(){
						window.location = "payChange.vm";
					});
				}
			});
		});
	}
}

function getProdData(){
	var prodData = {
			chaId:jQuery("#chaId").val(),
			billNo:jQuery("#billNo").val(),
			orgId:jQuery("#orgId").val(),
			voderId:jQuery("#verdorId").val(),
			billDate:jQuery("#billDate").val(),
			dotype:jQuery("#dotype").val(),
			billType:jQuery("#billType").val(),
			chaMoney:jQuery("#chaMoney").val(),
			memo:jQuery("#memo").val(),
			status:"1"
	};
	return prodData;
}


/**
 * 审核单据
 */
function checkBill() {
	if(checkForm()){
		confirm("确定要提交审核？", function(){
			var prodData = getProdData();
			showLayer(true);
			PayChangeDwr.saveAndCheckBill(prodData, function(data){
				showLayer(false);
				redirectBill(data['chaId'], data, "单据提交成功");
	    	});	
		});
	}
}
/**
 * 记账单据
 */
function closeBill() {
	confirm("确定要写入台账？", function(){
		var chaId = jQuery("#chaId").val();
		showLayer(true);
		PayChangeDwr.closeBill(chaId, function(data){
			showLayer(false);
			redirectBill(chaId, data, "结算成功");
    	});	
	});
}

/**
 * 跳转url
 * @param billid
 * @param data
 * @param msg
 */
function redirectBill(chaId, data, msg){
	if(data['isSuccess'] == "true") {
		alert(msg, function(){
			window.location = "payChange.vm?user_action=toEdit&chaId=" + chaId;
		});
	} else {
		alert(data['msg']);
	}
}