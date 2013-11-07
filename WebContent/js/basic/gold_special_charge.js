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
	var weightStart = jQuery("#weightStart").val(),
		weightEnd = jQuery("#weightEnd").val(),
        workPrice = jQuery("#workPrice").val();
	if(weightStart){
		if(!isDecimal(weightStart)){
			alert("起始重量必须是数字格式");
			return false;
		}
		if(floatSub(weightStart, 0) < 0){
			alert("起始重量不能小于0");
			return false;
		}
	}
	if(weightEnd){
		if(!isDecimal(weightEnd)){
			alert("截止重量必须是数字格式");
			return false;
		}
		if(weightStart){
			if(floatSub(weightEnd, weightStart) <= 0){
				alert("截止重量必须大于起始重量");
				return false;
			}
		}
	}
	if(workPrice){
		if(!isDecimal(workPrice)){
			alert("工费单价必须是数字格式");
			return false;
		}
		if(floatSub(workPrice, 0) <= 0){
			alert("工费单价必须大于0");
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
	var charge = {
        itemClassId : jQuery("#itemClassId").val(),
        ornaClassId : jQuery("#ornaClassId").val(),
        weightStart : jQuery("#weightStart").val(),
        weightEnd : jQuery("#weightEnd").val(),
        workPrice : jQuery("#workPrice").val(),
        workType : jQuery("#workType").val(),
        billid : jQuery("#billid").val(),
        status:"1"
    };
    confirm("确定要保存？", function(){
        showLayer(true);
        GoldSpecialChargeDwr.saveOrUpdateGoldSpecialCharge(charge, function(data){
            showLayer(false);
            if(data){
                alert(data);
            }else{
                alert("保存成功", function(){
                    window.location = "goldSpecialCharge.vm";
                });
            }
        });
    });
}