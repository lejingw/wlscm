function loadQ(){
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemclassid").val(), function(data){
		addOptions("ornaclassid", data, null, null, true, true,"");
	});	
}

/**
 * 保存
 * @returns {Boolean}
 */
function saveBasicPriceOne(){
	if(!isDecimal(jQuery("#startweight").val())){
		alert('启始重量必须为数字');
		return false;
	}else if(!isDecimal(jQuery("#endweight").val())){
		alert('截止重量必须为数字');
		return false;
	}else if(parseFloat(jQuery("#startweight").val())>=jQuery("#endweight").val()){
		alert('主石重量始必须大于主石重量止');
		return false;
	}
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {billid:jQuery("#billid").val(),
    					   itemclassid:jQuery("#itemclassid").val(),
    					   ornaclassid:jQuery("#ornaclassid").val(),
    					   startweight:jQuery("#startweight").val(),
    					   endweight:jQuery("#endweight").val()
    		};
    		BasicPriceOneDwr.saveOrUpdateBasicPriceOne(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "basicPriceOne.vm";
    				});
    			}
    		});
    	});
	}
	
}