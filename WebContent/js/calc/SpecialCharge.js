//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField()) return false;
	return true;
}
//保存
function saveform(){
	if(!checkForm()){
		return ;
	}
	confirm("确定保存?", function(){
		var obj = {
				chargeId : jQuery("#chargeId").val(),
				weightStr:jQuery("#weightStr").val(),
				weightEnd:jQuery("#weightEnd").val(),
				weightCheck:jQuery("#weightCheck").val(),
				chargeUnit:jQuery("#chargeUnit").val(),
				chargeFloat:jQuery("#chargeFloat").val(),
				chargeMoney:jQuery("#chargeMoney").val(),
				memo : jQuery("#memo").val(),
				status :'1'
			};
		showLayer(true);
		SpecialChargeDwr.saveOrUpdateSpecialCharge(obj, function(data){
			showLayer(false);
			var m = data.split("_");
			if(m[0]=="2"){
				alert(m[1]);
			}else{
				alert(m[1], function(){
					window.location = "specialCharge.vm";
				});
			}
		});
	});
}

function checkField(){
	var weightStart = jQuery("#weightStr").val(),
		weightEnd = jQuery("#weightEnd").val(),
		weightCheck = jQuery("#weightCheck").val(),
		chargeUnit = jQuery("#chargeUnit").val(),
		chargeFloat = jQuery("#chargeFloat").val();
	if(weightStart){
		if(!isDecimal(weightStart)){jQuery("#chargeMoney").val("");
			alert("起始重量必须是数字");
			return false;
		}
		if(floatSub(weightStart, 0) < 0){jQuery("#chargeMoney").val("");
			alert("起始重量必须是大于等于0 ");
			return false;
		}
	}
	if(weightEnd){
		if(!isDecimal(weightEnd)){jQuery("#chargeMoney").val("");
			alert("截止重量必须是数字");
			return false;
		}
		if(weightStart){
			if(floatSub(weightEnd, weightStart) <= 0){jQuery("#chargeMoney").val("");
				alert("截止重量必须大于起始重量");
				return false;
			}
		}
	}
	if(weightCheck){
		if(!isDecimal(weightCheck)){jQuery("#chargeMoney").val("");
			alert("核算重量必须是数字");
			return false;
		}
		if(floatSub(weightCheck, 0) <= 0){jQuery("#chargeMoney").val("");
			alert("核算重量必须是大于0");
			return false;
		}
	}
	if(chargeUnit){
		if(!isDecimal(chargeUnit)){jQuery("#chargeMoney").val("");
			alert("工费单价必须是数字");
			return false;
		}
		if(floatSub(chargeUnit, 0) <= 0){jQuery("#chargeMoney").val("");
			alert("工费单价必须是大于0");
			return false;
		}
	}
	if(chargeFloat){
		if(!isDecimal(chargeFloat)){jQuery("#chargeMoney").val("");
			alert("浮动值必须是数字");
			return false;
		}
		if(floatSub(chargeFloat, 0) <= 0){jQuery("#chargeMoney").val("");
			alert("浮动值必须是大于0");
			return false;
		}
	}
	if(weightCheck!=""&&chargeUnit!=""&&chargeFloat!="")
		jQuery("#chargeMoney").val(floatAdd(floatMul(weightCheck,chargeUnit),chargeFloat));
	else
		jQuery("#chargeMoney").val("");
	return true;
}