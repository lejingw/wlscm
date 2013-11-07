function saveSbraPrice(){
	
	if(!isDecimal(jQuery("#marketPrice").val())){
		alert('托架市场价必须为数字');
		return false;
	}
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {id:jQuery("#id").val(),
    					   qualityId:jQuery("#qualityId").val(),
    					   marketPrice:jQuery("#marketPrice").val()
    		};
    		SbraPriceDwr.saveOrUpdateSbraPrice(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "sbraPrice.vm";
    				});
    			}
    		});
    	});
	}
	
}