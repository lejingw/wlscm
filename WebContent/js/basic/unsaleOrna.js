//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if (!checkField()) {
		return false;
	}
	return true;
}


function checkField(){
	var moneyStr = jQuery("#moneyStr").val(),
		moneyEnd = jQuery("#moneyEnd").val(),
		flowCycle = jQuery("#flowCycle").val(),
		unfashCycle = jQuery("#unfashCycle").val(),
		unfashUncycle = jQuery("#unfashUncycle").val();
	if(moneyStr){
		if(!isDecimal(moneyStr)){
			alert("起始金额必须是数字");
			return false;
		}
		if(floatSub(moneyStr, 0) < 0){
			alert("起始金额不能小于0");
			return false;
		}
	}
	if(moneyEnd){
		if(!isDecimal(moneyEnd)){
			alert("截止金额必须是数字格式");
			return false;
		}
		if(moneyStr){
			if(floatSub(moneyEnd, moneyStr) <= 0){
				alert("截止金额必须大于起始金额");
				return false;
			}
		}
	}
	if(flowCycle){
		if(!isInteger(flowCycle)){
			alert("流转周期必须是整数");
			return false;
		}
		if(floatSub(flowCycle, 0) <= 0){
			alert("流转周期必须大于0");
			return false;
		}
	}
	if(unfashCycle){
		if(!isInteger(unfashCycle)){
			alert("淘汰款式周期必须是整数");
			return false;
		}
		if(floatSub(unfashCycle, 0) < 0){
			alert("流转周期必须大于等于0");
			return false;
		}
	}
	if(unfashUncycle){
		if(!isInteger(unfashUncycle)){
			alert("非淘汰款式周期必须是整数");
			return false;
		}
		if(floatSub(unfashUncycle, 0) < 0){
			alert("流转周期必须大于等于0");
			return false;
		}
	}
	return true;
}

//保存
function saveform(){
	if(!checkForm()){
		return ;
	}
	confirm("确定保存?", function(){
		var data = getData();
		showLayer(true);
		UnsaleOrnaDwr.saveOrUpdateUnsaleOrna(data, function(data){
			showLayer(false);
			if(data){
				alert(data);
			}else{
				alert("保存成功", function(){
					window.location = "unsaleOrna.vm";
				});
			}
		});
	});
}

function getData(){
	var data = {
		unsaleId	: jQuery("#unsaleId").val(),
		itemClassId	: jQuery("#itemClassId").val(),
		moneyStr	: jQuery("#moneyStr").val(),
		moneyEnd	: jQuery("#moneyEnd").val(),
		flowCycle	: jQuery("#flowCycle").val(),
		unfashCycle	: jQuery("#unfashCycle").val(),
		unfashUncycle: jQuery("#unfashUncycle").val(),
		memo		: jQuery("#memo").val(),
		status		: "1"
	};
	return data;
}