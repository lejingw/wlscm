
function checkField(){
	var payMoney = jQuery("#payMoney").val();
	if(payMoney) {
		if(!isDecimal(payMoney)) {
			alert("付款金额格式不合法。");
			return false;
			if(floadSub(payMoney, 0) <= 0){
				alert("付款金额必须是大于0的数。");
				return false;
			}
		}
	}
	return true;
}

function changeVendor(){
	var vendorId = jQuery("#verdorId").val();
	if(vendorId){
		PayMoneyDwr.getLessMoney(vendorId, function(data){
			jQuery("#lessMoney").val(data);
		});
	}
	
}
//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField()){
		return false;
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
		var payMoney = getPayMoney();
		showLayer(true);
		PayMoneyDwr.saveOrUpdatePayMoney(payMoney, function(msg){
			showLayer(false);
			if(msg) {
				alert(msg);
			} else {
				alert("保存成功", function(){
					window.location = "payMoney.vm";
				});
			}
		});
	});
}


function writeAccount(){
	if(!checkForm()){
		return ;
	}
	confirm("确定要记账？", function(){
		var payMoney = getPayMoney();
		showLayer(true);
		PayMoneyDwr.saveAndAccountPayMoney(payMoney, function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("处理成功", function(){
						window.location = "payMoney.vm?user_action=toEdit&payId=" + data['payId'];
					});
				} else {
					alert(data['msg']);
				}
			} else {
				alert("记账失败");
			}
		});
	});
	
}
function getPayMoney(){
	var payMoney = {
			payId	: jQuery("#payId").val(),
			billNo	: jQuery("#billNo").val(),
			orgId	: jQuery("#orgId").val(),
			voderId	: jQuery("#verdorId").val(),
			billDate: jQuery("#billDate").val(),
			payMoney: jQuery("#payMoney").val(),
			lessMoney: jQuery("#lessMoney").val(),
			status	: "1",
			memo	: jQuery("#memo").val(),
	};
	return payMoney;
}

function cancelPayMoney(){
	confirm("确定要撤销？", function(){
		showLayer(true);
		PayMoneyDwr.cancelPayMoney(jQuery("#payId").val(), function(data){
			showLayer(false);
			if(data){
				if(data['isSuccess'] == "true"){
					alert("处理成功", function(){
						window.location = "payMoney.vm?user_action=toEdit&payId=" + jQuery("#payId").val();
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