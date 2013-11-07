// ------------------------------------LIST ---------------------------------------------




//-------------------------------------EDIT----------------------------------------------

function initData(values){
	BdCommonDwr.getArticleTypeForSlt(function(data){
		addOptions("articleTypeId", data, null, null, true, true, values.articleTypeId);
		changeArticle(values);
	});
	BdCommonDwr.getAllQualityForSlt(function(data){
		addOptions("qualityId", data, null, null, true, true, values.qualityId);
	});
}

function changeArticle(values){
	BdCommonDwr.getItemClassForSlt(values.articleTypeId, function(data){
		addOptions("itemClassId", data, null, null, true, true, values.itemClassId);
		changeItemClass(values);
	});
}

function changeItemClass(values){
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(values.itemClassId, function(data){
		addOptions("ornaClassId", data, null, null, true, true, values.ornaClassId);
	});
}


function changeOrnaDsc(){
	var itemClass = $("itemClassId"),
		ornaClass = $("ornaClassId"),
		desc = "";
	if(itemClass.value && ornaClass.value){
		var itemClassName = itemClass.options[itemClass.selectedIndex].text,
			ornaClassName = ornaClass.options[ornaClass.selectedIndex].text;
		desc = itemClassName + ornaClassName;
	}
	if(desc){
		jQuery("#ornaDsc").val(desc);
	}
}
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
		var ornaDescObj = {
				ornadscId : jQuery("#ornadscId").val(),
				articleTypeId:jQuery("#articleTypeId").val(),
				itemClassId:jQuery("#itemClassId").val(),
				ornaClassId:jQuery("#ornaClassId").val(),
				qualityId:jQuery("#qualityId").val(),
				ornaDsc:jQuery("#ornaDsc").val(),
				memo : jQuery("#memo").val(),
				status:jQuery('#status').val()
			};
		showLayer(true);
		OrnaDescDwr.saveOrUpdateOrnaDesc(ornaDescObj, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("保存成功", function(){
						window.location = "ornaDesc.vm";
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

