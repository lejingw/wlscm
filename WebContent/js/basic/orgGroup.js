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
		var orgGroupObj = {
				groupId : jQuery("#groupId").val(),
				orgId:jQuery("#orgId").val(),
				itemClassId:jQuery("#itemClassId").val(),
				ornaClassId:jQuery("#ornaClassId").val(),
				groups:jQuery("#groups").val(),
				memo : jQuery("#memo").val(),
				status:jQuery('#status').val()
			};
		showLayer(true);
		OrgGroupDwr.saveOrUpdateOrgGroup(orgGroupObj, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("保存成功", function(){
						window.location = "orgGroup.vm?user_action=toEdit&groupId="+data.groupId;
					});
				} else {
					alert(data.msg);
				}
			}else{
				alert("保存失败");
			}
		});
	});
}

function copyData(){
	if(jQuery("#copySave").val()){
		confirm("<span style='color:red'>复制的数据尚未保存，确定要重新复制吗?</span>", function(){
			copyData2();
		});
	} else {
		copyData2();
	}
}
function copyData2(){
	jQuery("#groupId").val("");
	jQuery("#groups").val("");
	jQuery("#memo").val("");
	jQuery("#copySave").val(1);
	jQuery("#copyMsg").html("&nbsp;&nbsp;复制成功，请重新选择柜组，进行保存");
}