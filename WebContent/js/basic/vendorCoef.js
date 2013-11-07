/**
 * 保存
 * @returns {Boolean}
 */
function saveVendorCoef(){
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {coefId:jQuery("#coefId").val(),
					   	   coef:jQuery("#coef").val(),
					   	   vendorId:jQuery("#vendorId").val(),
    					   status:"1",
    					   memo:jQuery("#memo").val()
    		};
    		VendorCoefDwr.saveOrUpdateVendorCoef(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "vendorCoef.vm";
    				});
    			}
    		});
    	});
	}
	
}