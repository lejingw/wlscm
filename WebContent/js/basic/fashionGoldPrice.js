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
	var goldPrice = jQuery("#goldPrice").val();
	if(goldPrice){
		if(!isDecimal(goldPrice)){
			alert("固定金价必须是数字格式");
			return false;
		}
		if(floatSub(goldPrice, 0) <= 0){
			alert("固定金价不能必须大于0");
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
        var obj = {
                id :  jQuery("#id").val(),
                itemClassId : jQuery("#itemClassId").val(),
                goldPrice : jQuery("#goldPrice").val(),
                memo : jQuery("#memo").val(),
                status:'1'
        };
        showLayer(true);
        FashionGoldPriceDwr.saveOrUpdateGoldPrice(obj, function(data){
            showLayer(false);
            if(data){
                alert(data);
            }else{
                alert("保存成功", function(){
                    window.location = "fashionGoldPrice.vm";
                });
            }
        });
    });
}