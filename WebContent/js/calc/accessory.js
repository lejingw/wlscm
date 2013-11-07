function saveAccessory(){
	
//	if(!isDecimal(jQuery("#startWeight").val())){
//		alert('启始重量必须为数字');
//		return false;
//	}else if(!isDecimal(jQuery("#endWeight").val())){
//		alert('截止重量必须为数字');
//		return false;
//	}else if(parseFloat(jQuery("#startWeight").val())>=jQuery("#endWeight").val()){
//		alert('截止重量必须大于启始重量');
//		return false;
//	}else 
	if(!isDecimal(jQuery("#coefficient").val())){
		alert('系数必须为数字');
		return false;
	}
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {id:jQuery("#id").val(),
    					   accessoryId:jQuery("#accessoryId").val(),
    					   coefficient:jQuery("#coefficient").val(),
    					   styleStandard:jQuery("#styleStandard").val(),
    					   startWeight:jQuery("#startWeight").val(),
    					   endWeight:jQuery("#endWeight").val()
    		};
    		AccessoryDwr.saveOrUpdateAccessory(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "accessory.vm";
    				});
    			}
    		});
    	});
	}
	
}