function loadMaterByOrnaCode(){
	var ornaCode = jQuery("#ornaCode_in").val();
	if(!checkChangelabelId())return;
	if(ornaCode){
		LabelTagApplyDwr.getMaterByOrnaCode(ornaCode, jQuery("#orgId").val(), function(data){
			afterLoad(data);
		});
	} else {
		alert("请输入饰品编码");
	}
}

function loadMaterByOrnaBarCode(){
	var ornaBarCode = jQuery("#barCode_in").val();
	if(!checkChangelabelId())return;
	if(ornaBarCode){
		LabelTagApplyDwr.getMaterByOrnaBarCode(ornaBarCode, jQuery("#orgId").val(), function(data){
			afterLoad(data);
		});
	} else {
		alert("请输入饰品条码");
	}
}

function afterLoad(data){
	if(data){
		if(data['isSuccess'] == "true"){
			var mater = data['mater'];
			if(checkExistMater(mater['ornaCode'])) return;
			addMaterRow(mater);
		} else {
			alert(data['msg']);
		}
	} else {
		alert("获取饰品信息错误");
	}
	jQuery("#ornaCode_in").val("");
	jQuery("#barCode_in").val("");
}

function addMaterRow(mater){
	var itemClassName = nullToBlank(mater.itemClassName),
		ornaClassName = nullToBlank(mater.ornaClassName),
		ornaCode = nullToBlank(mater.ornaCode),
		unitName = nullToBlank(mater.unitName),
		num = nullToBlank(mater.nowQty),
		weighr = nullToBlank(mater.allQty),
		oldAmount = nullToBlank(mater.posAmount),
		newAmount = '<input type="text" name="newAmount" value="" class="fieldCls" onblur="checkField()"/>',
		oldOrnaName = nullToBlank(mater.ornaDsc),
		newOrnaName = '<input type="text" name="newOrnaName" value="" />',
		oldNativeCert = nullToBlank(mater.identId),
		newNativeCert = '<input type="text" name="newNativeCert" value="" class="fieldCls"/>',
		oldGiaCert = nullToBlank(mater.giaCert),
		newGiaCert = '<input type="text" name="newGiaCert" value="" class="fieldCls"/>',
		oldHrdCert = nullToBlank(mater.hrdCert),
		newHrdCert = '<input type="text" name="newHrdCert" value="" class="fieldCls"/>',
		oldIgiCert = nullToBlank(mater.igiCert),
		newIgiCert = '<input type="text" name="newIgiCert" value="" class="fieldCls"/>',
		oldZodiac = nullToBlank(mater.zodiac),
		newZodiac = '<select type="text" name="newZodiac" value="" /></select>',
		oldSpecialWorkPrice = nullToBlank(mater.specialWorkPrice),
		newSpecialWorkPrice = '<input type="text" name="newSpecialWorkPrice" value="" class="fieldCls" onblur="checkField()"/>',
		oldBasicPrice = nullToBlank(mater.basicPrice),
		newBasicPrice = '<input type="text" name="newBasicPrice" value="" class="fieldCls" onblur="checkField()"/>',
		isDblLabel = nullToBlank(mater.isDblLabel),
		labelKind = jQuery("#labelKind").val(),
		labelKindFiled = '<select type="text" name="labelKind" /></select>';
		sizeId = nullToBlank(mater.sizeId),
		sizeName = nullToBlank(mater.sizeName),
		newSizeId = '<input type="button" name="newSizeBtn" style="width:50px;" value="" onclick="showNewSizeSelect(this)"/>',
		updateReason = '<input type="text" name="updateReason" value="" />',
		state = '保存',
		deleteBtn = '<input type="button" value="删除" style="width:60px;" onclick="deleteLineRow(this, \'tbl\')"/>' + 
					'<input type="hidden" name="lineid" value="" />'+
					'<input type="hidden" name="itemClassId" value="'+mater.itemClassId+'" />'+
					'<input type="hidden" name="ornaClassId" value="'+mater.ornaClassId+'" />'+
					'<input type="hidden" name="ornaCode" value="'+(ornaCode?ornaCode:"")+'" />'+
					'<input type="hidden" name="unitId" value="'+(mater.saleUnitId?mater.saleUnitId:"")+'" />'+
					'<input type="hidden" name="num" value="'+(num?num:"")+'" />'+
					'<input type="hidden" name="weighr" value="'+(weighr?weighr:"")+'" />'+
					'<input type="hidden" name="oldAmount" value="'+oldAmount+'" />'+
					'<input type="hidden" name="oldOrnaName" value="'+(oldOrnaName?oldOrnaName:"")+'" />'+
					'<input type="hidden" name="oldNativeCert" value="'+(oldNativeCert?oldNativeCert:"")+'" />'+
					'<input type="hidden" name="oldGiaCert" value="'+(oldGiaCert?oldGiaCert:"")+'" />'+
					'<input type="hidden" name="oldHrdCert" value="'+(oldHrdCert?oldHrdCert:"")+'" />'+
					'<input type="hidden" name="oldIgiCert" value="'+(oldIgiCert?oldIgiCert:"")+'" />'+
					'<input type="hidden" name="oldZodiac" value="'+(oldZodiac?oldZodiac:"")+'" />'+
					'<input type="hidden" name="oldSpecialWorkPrice" value="'+(oldSpecialWorkPrice?oldSpecialWorkPrice:"")+'" />'+
					'<input type="hidden" name="oldBasicPrice" value="'+(oldBasicPrice?oldBasicPrice:"")+'" />'+
					'<input type="hidden" name="isDblLabel" value="'+(isDblLabel?isDblLabel:"")+'" />'+
					'<input type="hidden" name="labelKind_1" value="'+(labelKind?labelKind:"")+'" />'+
					'<input type="hidden" name="oldSizeId" value="' + sizeId + '" />'+
					'<input type="hidden" name="new_size_id" value="" />'+
					'<input type="hidden" name="newZodiac_1" value="" />'+
					'<input type="hidden" name="state" value="1" />';
	var fieldList = ["", ornaCode, itemClassName, ornaClassName, unitName, num, weighr, oldOrnaName, newOrnaName, oldBasicPrice, newBasicPrice, 
	                 oldAmount, newAmount, oldSpecialWorkPrice, newSpecialWorkPrice, sizeName, newSizeId, updateReason,
	                 oldNativeCert, newNativeCert, oldGiaCert, newGiaCert, oldHrdCert, newHrdCert, oldIgiCert, newIgiCert,
	                 oldZodiac, newZodiac, isDblLabel, labelKindFiled, state, deleteBtn];
	insertRow("tbl",fieldList,true);
	setLabelKindValue(0, isDblLabel);
	$n("labelKind")[0].value = labelKind;
	// var row = $("tbl").rows.length-1;

	DictDwr.getDictsForSlt("bornimg", function(data){
		addOptions2("newZodiac", 0, data, null, null, true, true);
	});
	changeValues();
	changeSeq("tbl");
}

function nullToBlank(str){
	if(!str){
		return "";
	}
	return str;
}

function setLabelKindValue(idx, isDblLabel){
	if(isDblLabel == "0"){
		var op = new Option("普通标签", "100");
		$n("labelKind")[idx].options.add(op);
	} else {
		var op = new Option("物流标签", "010");
		var op1 = new Option("价格标签", "001");
		var op2 = new Option("物流价格标签", "011");
		$n("labelKind")[idx].options.add(op);
		$n("labelKind")[idx].options.add(op1);
		$n("labelKind")[idx].options.add(op2);
	}
}
function checkField(){
	var len = $("tbl").rows.length;
	for(var i=0; i<len; i++){
		var newAmount = $n("newAmount")[i].value,
			newSpecialWorkPrice = $n("newSpecialWorkPrice")[i].value,
			newBasicPrice = $n("newBasicPrice")[i].value,
            labelKind = $n("labelKind")[i].value;
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
        if(!labelKind) {
            alert("标签种类不能为空");
            return false;
        }
	}
	return true;
}

function deleteLineRow(button, domId) {
	confirm("确定要删除", function(){
		var ri = button.parentNode.parentNode.rowIndex;
		var lineId = $n("lineid")[ri-1].value;
		if(lineId) {
			var deleteIds = $("deleteIds").value;
			var idsArray = deleteIds.split(";");
			for(var i=0;i<idsArray.length;i++) {
				if(!idsArray.contains(lineId)) {
					if(deleteIds){
						deleteIds = deleteIds + ";" + lineId;
					}else {
						deleteIds = lineId;
					}
				}
			}
			$("deleteIds").value = deleteIds;
		}
		deleteRow(button, domId);
		changeValues();
		changeSeq("tbl");
	});
}

function checkChangelabelId(){
	var clId = jQuery("#changelabelId").val();
	if(clId){
		return true;
	}else {
		alert("请先选择换标签原因");
		return false;
	}
}

function checkExistMater(ornaCode){
	var len = $("tbl").rows.length;
	for(var i=0; i<len;i++){
		if(ornaCode){
			if(ornaCode == $n("ornaCode")[i].value){
				alert("饰品已经存在列表中");
				return true;
			}
		}
	}
	return false;
}
/**
 * 在新增行和删除行之后要处理的事情
 */
function changeValues(){
	var len = $("tbl").rows.length,
		numTotal = 0;
	if(len >0){
		jQuery("#changelabelId").attr("disabled", true);
	} else {
		jQuery("#changelabelId").attr("disabled", false);
	}
	for(var i=0; i<len; i++){
		numTotal = numTotal + 1;
	}
	jQuery("#numTotal").val(numTotal>0?numTotal:"0");
	changeLineLKindStatus();
	changeWeightStatus();
}

/**
 * 根据换标签原因修改 行表对应的列是否允许为空
 */
function changeLineLKindStatus(){
	var len = $("tbl").rows.length,
		isincreaseamount = jQuery("#isincreaseamount").val(),
		isupdatecauses = jQuery("#isupdatecauses").val(),
		isbasicPrice = jQuery("#isbasicPrice").val(),
		specialWorkPrice = jQuery("#specialWorkPrice").val();
	for(var i=0; i<len; i++){
		if(isincreaseamount != "1"){
			jQuery($n("newAmount")[i]).attr("dataType", "Require");
			jQuery($n("newAmount")[i]).attr("msg", "不允许为空");
		} else {
			jQuery($n("newAmount")[i]).removeAttr("dataType");
		}
		if(isupdatecauses != "1"){
			jQuery($n("updateReason")[i]).attr("dataType", "Require");
			jQuery($n("updateReason")[i]).attr("msg", "不允许为空");
		}else {
			jQuery($n("updateReason")[i]).removeAttr("dataType");
		}
		if(isbasicPrice != "1"){
			jQuery($n("newBasicPrice")[i]).attr("dataType", "Require");
			jQuery($n("newBasicPrice")[i]).attr("msg", "不允许为空");
		}else {
			jQuery($n("newBasicPrice")[i]).removeAttr("dataType");
		}
		if(specialWorkPrice != "1"){
			jQuery($n("newSpecialWorkPrice")[i]).attr("dataType", "Require");
			jQuery($n("newSpecialWorkPrice")[i]).attr("msg", "不允许为空");
		}else {
			jQuery($n("newSpecialWorkPrice")[i]).removeAttr("dataType");
		}
	}
}

function changeWeightStatus(){
	var len = $("tbl").rows.length;
	
	for(var i=0; i<len; i++){
		unitId = $n("unitId")[i].value;
		// info(unitId);
		if(unitId == 609 || unitId == "609"){
			jQuery($n("newAmount")[i]).removeAttr("dataType");
			jQuery($n("newAmount")[i]).attr("disabled", true);
			jQuery($n("newBasicPrice")[i]).removeAttr("dataType");
			jQuery($n("newBasicPrice")[i]).attr("disabled", true);
		}
	}
}

//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var len = $("tbl").rows.length;
	if (len<=0) {
		alert("尚未添加饰品！！！");
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
		LabelTagApplyDwr.saveOrUpdateLabelApply(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				alert(data);
			} else {
				alert("保存成功", function(){
					window.location = "labelTagApply.vm";
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
		LabelTagApplyDwr.saveAndCheckLabelApply(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("提交审核成功", function(){
						window.location = "labelTagApply.vm?user_action=toEdit&id="+data.id;
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


function getHeadData(){
	var head = {
			no				: jQuery("#billNo").val(),
			orgId			: jQuery("#orgId").val(),
			id				: jQuery("#id").val(),
			numTotal		: jQuery("#numTotal").val(),
			changelabelId	: jQuery("#changelabelId").val(),
			memo			: jQuery("#memo").val(),
			state			: "1"
	};
	return head;
}

function getLinesData(){
	var lines = [],
		len = $("tbl").rows.length;
	for(var i=0; i<len ; i++){
		var obj = {
			id:$n("lineid")[i].value,
			headId:jQuery("#id").val(),
			newAmount:$n("newAmount")[i].value,
			ornaName:$n("oldOrnaName")[i].value,
			newOrnaName:$n("newOrnaName")[i].value,
			newNativeCert:$n("newNativeCert")[i].value,
			newGiaCert:$n("newGiaCert")[i].value,
			newHrdCert:$n("newHrdCert")[i].value,
			newIgiCert:$n("newIgiCert")[i].value,
			newZodiac:$n("newZodiac")[i].value,
			newSpecialWorkPrice:$n("newSpecialWorkPrice")[i].value,
			newBasicPrice:$n("newBasicPrice")[i].value,
			updateReason:$n("updateReason")[i].value,
			itemClassId:$n("itemClassId")[i].value,
			ornaClassId:$n("ornaClassId")[i].value,
			ornaCode:$n("ornaCode")[i].value,
			unitId:$n("unitId")[i].value,
			num:$n("num")[i].value,
			weighr:$n("weighr")[i].value,
			oldAmount:$n("oldAmount")[i].value,
			oldOrnaName:$n("oldOrnaName")[i].value,
			oldNativeCert:$n("oldNativeCert")[i].value,
			oldGiaCert:$n("oldGiaCert")[i].value,
			oldHrdCert:$n("oldHrdCert")[i].value,
			oldIgiCert:$n("oldIgiCert")[i].value,
			oldZodiac:$n("oldZodiac")[i].value,
			oldSpecialWorkPrice:$n("oldSpecialWorkPrice")[i].value,
			oldBasicPrice:$n("oldBasicPrice")[i].value,
			isDblLabel:$n("isDblLabel")[i].value,
			labelKind:$n("labelKind")[i].value,
			state:"1",
			oidSize:$n("oldSizeId")[i].value,
			newSize:$n("new_size_id")[i].value
		};
		lines.push(obj);
	}
	return lines;
}

function showNewSizeSelect(btn){
	var rowIndex = btn.parentNode.parentNode.rowIndex;
	beforeShowNewSize(rowIndex-1);
	var _iframeId = "newSizeIframe";
	var options = {
		title : '新尺寸',
		contentType : 'selector',
		iframeId : _iframeId,
		width : 218,
		height : 50,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		lineRow:rowIndex-1,
		onok : function(box) {
			var newSize = jQuery("#selectNewSize").val(),
				newText = "";
			if(newSize){
				var selectNewSize = $("selectNewSize");
				newText = selectNewSize.options[selectNewSize.selectedIndex].text;
			}
			$n("new_size_id")[this.lineRow].value = newSize;
			$n("newSizeBtn")[this.lineRow].value = newText;
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	jQuery.weeboxs.open(jQuery("#selectNewSizeDiv"), options);
}

function beforeShowNewSize(row){
	BdCommonDwr.getSizeByItemIdAndOrnaId($n("itemClassId")[row].value, $n("ornaClassId")[row].value, function(data){
		addOptions("selectNewSize", data, null, null, true, true, $n("new_size_id")[row].value);
	});
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}