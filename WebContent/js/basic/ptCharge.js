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
	var weightStr = jQuery("#weightStr").val(),
		weightEnd = jQuery("#weightEnd").val(),
		chargeUnit = jQuery("#chargeUnit").val();
	if(weightStr){
		if(!isDecimal(weightStr)){
			alert("起始金额必须是数字格式");
			return false;
		}
		if(floatSub(weightStr, 0) < 0){
			alert("起始金额不能小于0");
			return false;
		}
	}
	if(weightEnd){
		if(!isDecimal(weightEnd)){
			alert("截止金额必须是数字格式");
			return false;
		}
		if(weightStr){
			if(floatSub(weightEnd, weightStr) <= 0){
				alert("截止金额必须大于起始金额");
				return false;
			}
		}
	}
	if(chargeUnit){
		if(!isDecimal(chargeUnit)){
			alert("工费标准必须是数字格式");
			return false;
		}
		if(floatSub(chargeUnit, 0) <= 0){
			alert("工费标准必须大于0");
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
	var str = jQuery("#weightStr").val(),
		end = jQuery("#weightEnd").val(),
		id = jQuery("#chargeId").val();
	showLayer(true);
	PtChargeDwr.checkWeightStr(id, str, function(isExistStr){
		showLayer(false);
		if(!isExistStr){
			showLayer(true);
			PtChargeDwr.checkWeightEnd(id, end, function(isExistEnd){
				showLayer(false);
				if(!isExistEnd){
					confirm("确定保存?", function(){
						var chargeObj = {
								chargeId : id,
								weightStr:str,
								weightEnd:end,
								chargeUnit: jQuery("#chargeUnit").val(),
								memo : jQuery("#memo").val(),
								status:jQuery('#status').val()
							};
						showLayer(true);
						PtChargeDwr.saveOrUpdatePtCharge(chargeObj, function(data){
							showLayer(false);
							if(data){
								alert(data);
							}else{
								alert("保存成功", function(){
									window.location = "ptCharge.vm";
								});
							}
						});
					});
				} else {
					alert("金额止 已经存在");
				}
			});
		} else {
			alert("金额始 已经存在");
		}
	});
}