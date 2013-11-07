//增行
function addRow(){
	var field1 = "<td><select name='qualityId'></select></td>";
	var field2 = "<input type='button' value='删除' style='width:60px;' onclick='deleteRow(this,\"tbl\")'>";
	insertRow("tbl",[field1,field2],false);
	BdCommonDwr.getAllQualityForSlt(function(data){
		addOptions2("qualityId", $("tbl").rows.length - 1, data, null, null, true, true);
	});
}

function checkField(obj){
	if(obj.value){
		if(!isDecimal(obj.value)){
			alert("请输入正确格式的小数");
			return false;
		}
		return true;
	}
	return false;
}

//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	for(var i=0;i<$("tbl").rows.length;i++){
		if(!isDecimal($n("qualityId")[i].value)){
			alert('第'+(1+i)+'行 ,托架材质不能为空。');
			return false;
		}
	}
	return true;
}
//保存
function saveform(){
	if(!checkForm()){
		return ;
	}
	confirm("确定保存?", function(){
		var headObj = {
				id : jQuery("#id").val(),
				sbraType:jQuery("#sbraType").val(),
				coefficient:jQuery("#coefficient").val(),
				isFee:jQuery("#isFee")[0].checked==false?'0':'1',
				archiveFlag:jQuery("#archiveFlag")[0].checked==false?'0':'1'
			};
		var lineList = [];
		for(var i=0;i<$("tbl").rows.length;i++){
			lineList.push({
				qualityId : $n("qualityId")[i].value
			});
		}
		SbraTypeDwr.saveOrUpdateSbraType(headObj, lineList, function(data){
			if(data){
				alert(data);
			}else{
				alert("保存成功", function(){
					window.location = "sbraType.vm";
				});
			}
		});
	});
}