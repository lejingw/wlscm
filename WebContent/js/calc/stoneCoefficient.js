function saveStoneCoefficient(){
	
	if(!isDecimal(jQuery("#coefficient").val())){
		alert('系数必须为数字');
		return false;
	}
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {id:jQuery("#id").val(),
    					   itemClassId:jQuery("#itemClassId").val(),
    					   styleStandard:jQuery("#styleStandard").val(),
    					   coefficient:jQuery("#coefficient").val(),
    					   mainShapeId:jQuery("#mainShapeId").val(),
    					   colorId:jQuery("#colorId").val(),
    					   colorGreadId:jQuery("#colorGreadId").val(),
    					   cleanId:jQuery("#cleanId").val()
    		};
    		StoneCoefficientDwr.saveOrUpdateStoneCoefficient(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "stoneCoefficient.vm";
    				});
    			}
    		});
    	});
	}
	
}