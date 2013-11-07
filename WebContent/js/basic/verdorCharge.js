function saveVerdorCharge(){
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){
    		var headObj = {chargeId:jQuery("#chargeId").val(),
    						verdorId:jQuery("#verdorId").val(),
    						styleId:jQuery("#sid").val(),
    						status:jQuery("#status").val(),
    						memo:jQuery("#memo").val()
    		};
    		VerdorChargeDwr.saveOrUpdateVerdorCharge(headObj, function(data){
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "verdorCharge.vm";
    				});
    			}
    		});
    	});
	}
	
}
function exportListToExcel(){
	jQuery("#user_action").val("exportExcel");
	jQuery("#myForm").submit();
	//user_action改回之前的值，否则之后的任何提交如果不重新设置user_action，则都将是导出excel，比如上下翻页
	jQuery("#user_action").val("");
}
function deleteVerdorCharge(){
	var index = getSelectIndexs('chk');
	if(index.length!=1){
		alert("请选择一行");
		return;
	}
	var state = $n("status")[index[0]].value;
	var id = $n("chk")[index[0]].value;
	if("1"!=state){
		alert("单据状态不为保存");
		return;
	}
	confirm("确定删除?", function(){
		window.location = "verdorCharge.vm?user_action=deleteVerdorCharge&id=" + id;
	});
}