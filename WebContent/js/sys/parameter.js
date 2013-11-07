//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if (!checkField()) {
		return false;
	}
	return true;
}


function checkField(){
	var param_name = jQuery("#param_name").val(),
		name = jQuery("#name").val();
	if(param_name != name){
		alert("键值不允许修改");
		return false;
	}
	return true;
}

//保存
function saveform(){
	if(!checkForm()){
		return ;
	}
	var param = {
			name:jQuery("#name").val(),
			value:jQuery("#value").val(),
			memo:jQuery("#memo").val(),
			status:"1"
	};
	ParameterDwr.updateParameter(param, function(data){
		if(data){
			alert(data);
		} else {
			alert("保存成功", function(){
				window.location = "parameter.vm";
			});
		}
	});
	
}