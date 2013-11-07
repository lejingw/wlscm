function saveStonePrice(){
	
	if(!isDecimal(jQuery("#startWeight").val())){
		alert('启始重量必须为数字');
		return false;
	}else if(!isDecimal(jQuery("#endWeight").val())){
		alert('截止重量必须为数字');
		return false;
	}else if(parseFloat(jQuery("#startWeight").val())>=jQuery("#endWeight").val()){
		alert('截止重量必须大于启始重量');
		return false;
	}else if(!isDecimal(jQuery("#marketPrice").val())){
		alert('市场价必须为数字');
		return false;
	}
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {id:jQuery("#id").val(),
    					   itemClassId:jQuery("#itemClassId").val(),
    					   styleStandard:jQuery("#styleStandard").val(),
    					   marketPrice:jQuery("#marketPrice").val(),
    					   priceType:jQuery("#priceType").val(),
    					   stoneType:jQuery("#stoneType").val(),
    					   startWeight:jQuery("#startWeight").val(),
    					   endWeight:jQuery("#endWeight").val(),
    					   
    					   shapeId:jQuery("#shapeId").val(),
    					   cutId:jQuery("#cutId").val(),
    					   cleanId:jQuery("#cleanId").val(),
    					   colorId:jQuery("#colorId").val(),
    					   colorGradeId:jQuery("#colorGradeId").val()};
    		StonePriceDwr.saveOrUpdateStonePrice(headObj, function(data){
                if(data) {
                    alert(data);
                } else {
                    alert("保存成功", function(){
                        window.location = "stonePrice.vm?priceType="+jQuery("#priceType").val();
                    });
                }
    		});
    	});
	}
}