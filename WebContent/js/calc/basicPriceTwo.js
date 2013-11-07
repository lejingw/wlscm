/**
 * 保存
 * @returns {Boolean}
 */
function saveBasicPriceTwo(){
	if(!isDecimal(jQuery("#basicprice").val())){
		alert('系数必须为数字');
		return false;
	}
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {billid:jQuery("#billid").val(),
    						basicprice:jQuery("#basicprice").val()
    		};
    		BasicPriceTwoDwr.saveOrUpdateBasicPriceTwo(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "basicPriceTwo.vm";
    				});
    			}
    		});
    	});
	}
	
}