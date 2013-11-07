function showCopyData(){
	var _iframeId = "copyDataIframe";
	var options = {
		title : '复制柜组数据',
		contentType : 'selector',
		iframeId : _iframeId,
		width : 450,
		height : 180,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var res = checkInput();
			if(res.isSucc){
				doCopyData(res, box);
			}
		},
		oncancel : function(box) {
			box.close();
		}
	};
	jQuery.weeboxs.open(jQuery("#copyDataDiv"), options);
	initCopyData();
}

function doCopyData(data, box){
	confirm("确定要复制数据？", function(){
		box.close();
		var params = JSON.stringify(data);
		showLayer(true);
		OrgGroupDwr.copyOrgGroupData(params, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("复制成功", function(){
						jQuery("#frm").submit();
					});
				} else {
					alert(data.msg);
				}
			} else {
				alert("复制失败");
			}
		});
	});
}

function checkInput(){
	var type = jQuery("#chk_type").val(),
		result = {type:type};
	if("org" == type){
		var orgId_s = jQuery("#orgId_s").val(),
			orgId_d = jQuery("#orgId_d").val();
		if(!orgId_s || !orgId_d){
			alert("组织不能为空");
			result.isSucc = false;
			return result;
		}
		var orgIdArr = orgId_d.split(","),
			orgIdList = [];
		for(var i=0; i<orgIdArr.length; i++){
			if(orgIdArr[i] != orgId_s){
				orgIdList.push(orgIdArr[i]);
			}
		}
		if(orgIdList.length <=0){
			alert("源组织和目标组织不允许相同");
			result.isSucc = false;
			return result;
		}
		result.source = orgId_s;
		result.dest = "";
		for(var j=0; j<orgIdList.length;j++){
			if(result.dest){
				result.dest = result.dest + "," + orgIdList[j];
			} else {
				result.dest = orgIdList[j];
			}
		}
	} else if("itemClass" == type){
		var itemClassId_s = jQuery("#itemClassId_s").val(),
			itemClassId_d = jQuery("#itemClassId_d").val();
		if(!itemClassId_s || !itemClassId_d){
			alert("大类不能为空");
			result.isSucc = false;
			return result;
		} 
		if(itemClassId_s == itemClassId_d){
			alert("大类不能相等");
			result.isSucc = false;
			return result;
		}
		result.source = itemClassId_s;
		result.dest = itemClassId_d;
	} else {
		var ornaClassId_s = jQuery("#ornaClassId_s").val(),
			ornaClassId_d = jQuery("#ornaClassId_d").val();
		if(!ornaClassId_s || !ornaClassId_d){
			alert("小类不能为空");
			result.isSucc = false;
			return result;
		}
		if(ornaClassId_s == ornaClassId_d){
			alert("小类不能相等");
			result.isSucc = false;
			return result;
		}
		result.source = ornaClassId_s;
		result.dest = ornaClassId_d;
	}
	result.isSucc = true;
	return result;
}



function initCopyData(){
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions("itemClassId_s", data, null, null, true, true);
		addOptions("itemClassId_d", data, null, null, true, true);
	});
	BdCommonDwr.getAllOrnaClassForSlt("", function(data){
		addOptions("ornaClassId_s", data, null, null, true, true);
		addOptions("ornaClassId_d", data, null, null, true, true);
	});
	disabledOrg(false);
	disabledItemClass(true);
	disabledOrnaClass(true);
	jQuery("#chk_type").val("org");
}

function orgClick(){
	checkChange("org");
};
function itemClassClick(){
	checkChange("itemClass");
};
function ornaClassClick(){
	checkChange("ornaClass");
};
function showSourceOrgWin(){
	selectOrg(function(idArr, nameArr){
			jQuery("#orgId_s").val(idArr.join(","));
			jQuery("#orgName_s").val(nameArr.join(","));
		}, null, null, false, jQuery("#orgId_s").val());
}
function showDestOrgWin(){
	selectOrg(function(idArr, nameArr){
			jQuery("#orgId_d").val(idArr.join(","));
			jQuery("#orgName_d").val(nameArr.join(","));
		}, null, null, true, jQuery("#orgId_d").val());
}
function checkChange(type){
	if("org" == type){
		var enable = jQuery("#chk_org").attr("checked");
		disabledOrg(!enable);
		disabledItemClass(enable);
		disabledOrnaClass(enable);
	} else if ("itemClass" == type){
		var enable = jQuery("#chk_itemClass").attr("checked");
		disabledOrg(enable);
		disabledItemClass(!enable);
		disabledOrnaClass(enable);
	} else {
		var enable = jQuery("#chk_ornaClass").attr("checked");
		disabledOrg(enable);
		disabledItemClass(enable);
		disabledOrnaClass(!enable);
	}
	jQuery("#chk_type").val(type);
}

function disabledOrg(enable){
	jQuery("#chk_org").attr("checked", !enable);
	jQuery("#orgName_s").attr("disabled", enable);
	jQuery("#orgName_d").attr("disabled", enable);
}

function disabledItemClass(enable){
	jQuery("#chk_itemClass").attr("checked", !enable);
	jQuery("#itemClassId_s").attr("disabled", enable);
	jQuery("#itemClassId_d").attr("disabled", enable);
}
function disabledOrnaClass(enable){
	jQuery("#chk_ornaClass").attr("checked", !enable);
	jQuery("#ornaClassId_s").attr("disabled", enable);
	jQuery("#ornaClassId_d").attr("disabled", enable);
}