//增行
function addRow(){
	var field1 = "<input type='text' name='mainwtStr' onblur='checkField(this)'>";
	var field2 = "<input type='text' name='mainwtEnd' onblur='checkField(this)'>";
	var field3 = "<input type='text' name='moveCoef' onblur='checkField(this)' value='1.0'>";
	var field4 = "<input type='button' value='删除' style='width:60px;' onclick='deleteRow(this,\"tbl\")'>";
	insertRow("tbl",[field1,field2,field3,field4],false);
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
		if(!isDecimal($n("mainwtStr")[i].value)){
			alert('第'+(1+i)+'行 起始主石重必须为数字');
			return false;
		}
		if(!isDecimal($n("mainwtEnd")[i].value)){
			alert('第'+(1+i)+'行 截止主石重必须为数字');
			return false;
		}
		if(!isDecimal($n("moveCoef")[i].value)){
			alert('第'+(1+i)+'行 调拨系数必须为数字');
			return false;
		}
		if(parseFloat($n("mainwtStr")[i].value) >= parseFloat($n("mainwtEnd")[i].value)){
			alert('第'+(1+i)+'行 起始主石重只能小于截止主石重');
			return false;
		}
		if(!checkLineRepeat(i)){
			return false;
		}
	}
	return true;
}
function checkLineRepeat(index){
	for(var i=0;i<$("tbl").rows.length;i++){
		if(i == index)
			continue;
		if(parseFloat($n("mainwtStr")[index].value) >= parseFloat($n("mainwtEnd")[i].value)
				|| parseFloat($n("mainwtEnd")[index].value) <= parseFloat($n("mainwtStr")[i].value)){
			continue;
		}else{
			alert('第'+(1+index)+'行 和 第' + (1+i) + '行 主石重区间范围有重叠');
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
	var result = null;
	startSync();
	MoveSalecoefDwr.checkRepeat(jQuery("#inOrgId").val(), jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), jQuery("#styleItemIds").val(), jQuery("#headid").val(), function(data){result = data;});
	endSync();
	if(!result){
		alert("调入组织、大类、小类相同的记录已经存在");
		return ;
	}
	confirm("确定保存?", function(){
		var headObj = {
				headid : jQuery("#headid").val(),
				inOrgId:jQuery("#inOrgId").val(),
				itemClassId:jQuery("#itemClassId").val(),
				ornaClassId:jQuery("#ornaClassId").val(),
				styleItemIds:jQuery("#styleItemIds").val(),
				memo : jQuery("#memo").val()
			};
		var lineList = [];
		for(var i=0;i<$("tbl").rows.length;i++){
			lineList.push({
				mainwtStr : $n("mainwtStr")[i].value,
				mainwtEnd : $n("mainwtEnd")[i].value,
				moveCoef : $n("moveCoef")[i].value
			});
		}
		showLayer(true);
		MoveSalecoefDwr.saveOrUpdateMoveSalecoef(headObj, lineList, function(data){
			showLayer(false);
			if(data){
				alert(data);
			}else{
				alert("保存成功", function(){
					window.location = "moveSalecoef.vm";
				});
			}
		});
	});
}