/**
 * 保存
 * @returns {Boolean}
 */
function saveBasicPriceJade(){
	if(!isDecimal(jQuery("#startprice").val())){
		alert('启始价格必须为数字');
		return false;
	}else if(!isDecimal(jQuery("#endprice").val())){
		alert('截止价格必须为数字');
		return false;
	}else if(!isDecimal(jQuery("#smallprice").val())){
		alert('小价格必须为数字');
		return false;
	}else if(!isDecimal(jQuery("#bigprice").val())){
		alert('大价格必须为数字');
		return false;
	}else if(parseFloat(jQuery("#startprice").val())>=jQuery("#endprice").val()){
		alert('截止价格必须大于启始价格');
		return false;
	}else if(parseFloat(jQuery("#smallprice").val())>=jQuery("#bigprice").val()){
		alert('大价格必须大于小价格');
		return false;
	}
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {billid:jQuery("#billid").val(),
    					   itemclassid:jQuery("#itemclassid").val(),
    					   startprice:jQuery("#startprice").val(),
    					   endprice:jQuery("#endprice").val(),
    					   smallprice:jQuery("#smallprice").val(),
    					   decimalnum:jQuery("#decimalnum").val(),
    					   bigprice:jQuery("#bigprice").val()
    		};
    		BasicPriceJadeDwr.saveOrUpdateBasicPriceJade(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "basicPriceJade.vm";
    				});
    			}
    		});
    	});
	}
	
}