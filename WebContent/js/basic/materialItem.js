//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	return true;
}
//保存
function saveform(){
	if(!checkForm()){
		return ;
	}
	confirm("确定保存?", function(){
		var materialItemObj = {
				materialItemId : jQuery("#materialItemId").val(),
				materialType:jQuery("#materialType").val(),
				itemClassId:jQuery("#itemClassId").val(),
				purUnit:jQuery("#purUnit").val(),
				memo : jQuery("#memo").val(),
				status :'1'
			};
		showLayer(true);
		MaterialItemDwr.saveOrUpdateMItem(materialItemObj, function(data){
			showLayer(false);
			if(data){
				alert(data);
			}else{
				alert("保存成功", function(){
					window.location = "materialItem.vm";
				});
			}
		});
	});
}