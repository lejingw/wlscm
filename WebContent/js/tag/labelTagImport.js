

function addLine(){
	var ornaCode = "<input type='text' style='width:120px;' name='ornaCode' dataType='Require' msg='不能为空' />",
		newOrnaName = "<input type='text' style='width:120px;' name='newOrnaName' />",
		newBasicPrice= "<input type='text' style='width:100px;' name='newBasicPrice'  onblur='checkField()'/>",
		newAmount= "<input type='text' style='width:100px;' name='newAmount'  onblur='checkField()'/>",
		newSpecialWorkPrice = "<input type='text' style='width:100px;' name='newSpecialWorkPrice' onblur='checkField()'/>",
		updateReason = "<input type='text' style='width:180px;' name='updateReason'  />",
		groupNo = "<input type='text' style='width:180px;' name='groupNo'  />",
		deleteBtn = "<input type='button' value='删除' style='width:60px;' onclick='deleteLineRow(this, \"tbl\")'/>"+
					"<input type='hidden' name='lineid' />";
	var fieldList = ["", ornaCode, newOrnaName, newBasicPrice, newAmount, newSpecialWorkPrice, updateReason, groupNo, deleteBtn];
	insertRow("tbl",fieldList,true);
	
	changeValues(1);
	changeSeq("tbl");
}

function checkField(){
	var len = $("tbl").rows.length;
	for(var i=0; i<len; i++){
		var newAmount = $n("newAmount")[i].value,
			newSpecialWorkPrice = $n("newSpecialWorkPrice")[i].value,
			newBasicPrice = $n("newBasicPrice")[i].value;
		if(newAmount){
			if(!isDecimal(newAmount)){
				alert("新金额必须是数字格式");
				return false;
			} 
		}
		if(newSpecialWorkPrice){
			if(!isDecimal(newSpecialWorkPrice)){
				alert("新特殊工费必须是数字格式");
				return false;
			}
		}
		if(newBasicPrice){
			if(!isDecimal(newBasicPrice)){
				alert("新基础价必须是数字格式");
				return false;
			}
		}
	}
	return true;
}

function deleteLineRow(button, domId) {
	confirm("确定要删除", function(){
		var ri = button.parentNode.parentNode.rowIndex;
		var lineId = $n("lineid")[ri-1].value;
		if(lineId) {
			var deleteIds = jQuery("#deleteIds").val();
			var idsArray = deleteIds.split(";");
			for(var i=0;i<idsArray.length;i++) {
				if(!idsArray.contains(lineId)) {
					if(deleteIds){
						deleteIds = deleteIds + ";" + lineId;
					}else {
						deleteIds = lineId;
					}
					idsArray.push(lineId);
				}
			}
			jQuery("#deleteIds").val(deleteIds);
		}
		//info(jQuery("#deleteIds").val());
		deleteRow(button, domId);
		changeValues(-1);
		changeSeq("tbl");
	});
}

/**
 * 在新增行和删除行之后要处理的事情
 */
function changeValues(t){
	var counts = jQuery("#counts").val();
	if(!counts){
		counts = 0;
	}
	if(t==1){
		counts = counts + 1;
	}
	if(t==-1){
		counts = counts - 1;
	}
	jQuery("#counts").val(counts);
}

//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var len = $("tbl").rows.length,
		ornaArr = [];
	if (len<=0) {
		alert("尚未添加行信息！！！");
		return false;
	}
	for(var i=0; i<len; i++){
		var orna = $n("ornaCode")[i].value;
		if(!ornaArr.contains(orna)){
			ornaArr.push(orna);
		}
	}
	if(ornaArr.length != len){
		alert("饰品编码不允许相同");
		return false;
	}
	if(!checkField()) return false;
	
	return true;
}

function saveForm(){
	if(!checkForm()){
		return ;
	}
	confirm("确定要保存", function(){
		var head = getHeadData(),
			lines = getLinesData(),
			deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		LabelTagImportDwr.saveOrUpdateLabelImport(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				alert(data);
			} else {
				alert("保存成功", function(){
					window.location = "labelTagImport.vm";
				});
			}
		});
	});
}

function checkBill(){
	if(!checkForm()){
		return ;
	}
	confirm("确定要提交审核", function(){
		var head = getHeadData(),
			lines = getLinesData(),
			deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		LabelTagImportDwr.saveAndCheckLabelImport(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("提交审核成功", function(){
						window.location = "labelTagImport.vm?user_action=toEdit&headid="+data.headid;
					});
				} else {
					alert(data.msg);
				}
			} else {
				alert("提交审核失败");
			}
		});
	});
}


function closeBill(){
	confirm("确定要生成打印单?", function(){
		var headid = jQuery("#headid").val();
		showLayer(true);
		LabelTagImportDwr.closeLabelImport(headid, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("处理成功", function(){
						window.location = "labelTagImport.vm?user_action=toEdit&headid="+headid;
					});
				} else {
					alert(data.msg);
				}
			} else {
				alert("处理失败");
			}
		});
	});
}

function getHeadData(){
	var head = {
			billno			: jQuery("#billno").val(),
			orgId			: jQuery("#orgId").val(),
			headid			: jQuery("#headid").val(),
			numTotal		: jQuery("#numTotal").val(),
			memo			: jQuery("#memo").val(),
			status			: "1"
	};
	return head;
}

function getLinesData(){
	var lines = [],
		len = $("tbl").rows.length;
	for(var i=0; i<len ; i++){
		var obj = {
			lineid			:$n("lineid")[i].value,
			headid			:jQuery("#headid").val(),
			newAmount		:$n("newAmount")[i].value,
			newOrnaName		:$n("newOrnaName")[i].value,
			newSpecialWorkPrice:$n("newSpecialWorkPrice")[i].value,
			newBasicPrice	:$n("newBasicPrice")[i].value,
			updateReason	:$n("updateReason")[i].value,
			ornaCode		:$n("ornaCode")[i].value,
			groupNo			:$n("groupNo")[i].value
		};
		lines.push(obj);
	}
	return lines;
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}



function showImportWin(){
	jQuery("#excel_file").val("");
	var _iframeId = "importWinIframe";
	var options = {
		title : '导入标签打印单',
		contentType : 'selector',
		iframeId : _iframeId,
		width : 400,
		height : 60,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var fileName = jQuery("#excel_file").val(),
				len = fileName.length,
				fix = "";
			if(len > 4){
				fix = fileName.substring(len-4, len);
				//info(fix);
			}
			if(fix !== ".xls"){
				alert("请选择excel2003格式的文件");
				return "";
			}
			ajaxFileUpload(box);
		},
		oncancel : function(box) {
			box.close();
		}
	};
	jQuery.weeboxs.open(jQuery("#import_excel_div"), options);
}

function ajaxFileUpload(box) {
	confirm("确定要上传？", function(){
		showLayer(true);
		box.hide();
		var url = ctxPath + '/tag/labelTagImport.vm?user_action=importExcel';
		jQuery.ajaxFileUpload({
			url : url, //需要链接到服务器地址
			secureuri : false,
			fileElementId : 'excel_file', //文件选择框的id属性
			dataType : 'json', //服务器返回的格式，可以是json
			success : function (data, status) //相当于java中try语句块的用法
			{
				showLayer(false);
				//info(data.isSuccess);
				//info(data.headid);
				if(data.isSuccess === "true"){
					var ornaDBs = data.ornaDBs;
					var msg = "上传成功";
					if(ornaDBs && ornaDBs.length >0){
						msg = msg + "\n重复的饰品编码【"+ornaDBs+"】";
					}
					alert(msg, function(){
						
						box.close();
						window.location = ctxPath + "/tag/labelTagImport.vm?user_action=toEdit&headid=" + data.headid;
					});
				} else {
					alert(data.msg);
					box.show();
				}
			},
			error : function (data, status, e) //相当于java中catch语句块的用法
			{
				showLayer(false);
				alert("上传失败");
				box.show();
			}
		});
	});
}