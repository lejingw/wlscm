//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField()) return false;
	return true;
}
//保存
function saveform(){
	if(!checkForm()){
		return ;
	}
	confirm("确定保存?", function(){
		var coefficientObj = {
				id : jQuery("#id").val(),
				itemClassId:jQuery("#itemClassId").val(),
				weightStr:jQuery("#weightStr").val(),
				weightEnd:jQuery("#weightEnd").val(),
				coefficient:jQuery("#coefficient").val(),
				memo : jQuery("#memo").val(),
				status :'1'
			};
		showLayer(true);
		StoneMainCoefficientDwr.saveOrUpdate(coefficientObj, function(data){
			showLayer(false);
			if(data){
				alert(data);
			}else{
				alert("保存成功", function(){
					window.location = "stoneMainCoefficient.vm";
				});
			}
		});
	});
}

function checkField(){
	var weightStart = jQuery("#weightStr").val(),
		weightEnd = jQuery("#weightEnd").val(),
		coefficient = jQuery("#coefficient").val();
	if(weightStart){
		if(!isDecimal(weightStart)){
			alert("起始重量必须是数字");
			return false;
		}
		if(floatSub(weightStart, 0) < 0){
			alert("起始重量必须是大于等于0 的数字");
			return false;
		}
	}
	if(weightEnd){
		if(!isDecimal(weightEnd)){
			alert("截止重量必须是数字");
			return false;
		}
		if(weightStart){
			if(floatSub(weightEnd, weightStart) <= 0){
				alert("截止重量必须大于起始重量");
				return false;
			}
		}
	}
	if(coefficient){
		if(!isDecimal(coefficient)){
			alert("系数必须是数字");
			return false;
		}
		if(floatSub(coefficient, 0) <= 0){
			alert("系数必须是大于的数字");
			return false;
		}
	}
	return true;
}