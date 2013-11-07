function loadMaterByOrnaCode(){
	var ornaCode = jQuery("#ornaCode_in").val(),
		orgId = jQuery("#orgId").val();
	if(!orgId){
		alert("请选择组织");
		return;
	}
	if(ornaCode){
		LabelTagApplyPrintDwr.getMaterByOrnaCode(ornaCode, orgId, function(data){
			afterLoad(data);
		});
	} else {
		alert("请输入饰品编码");
	}
}

function loadMaterByOrnaBarCode(){
	var ornaBarCode = jQuery("#barCode_in").val(),
		orgId = jQuery("#orgId").val();
	if(!orgId){
		alert("请选择组织");
		return;
	}
	if(ornaBarCode){
		LabelTagApplyPrintDwr.getMaterByOrnaBarCode(ornaBarCode, orgId, function(data){
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
		weight = nullToBlank(mater.allQty),
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
		oldSpecialWorkPrice =nullToBlank(mater.specialWorkPrice),
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
		state = '',
		deleteBtn = '<input type="button" value="删除" style="width:60px;" onclick="deleteLineRow(this, \'tbl\')"/>' + 
					'<input type="hidden" name="lineid" value="" />'+
					'<input type="hidden" name="itemClassId" value="'+mater.itemClassId+'" />'+
					'<input type="hidden" name="ornaClassId" value="'+mater.ornaClassId+'" />'+
					'<input type="hidden" name="ornaCode" value="'+(ornaCode?ornaCode:"")+'" />'+
					'<input type="hidden" name="unitId" value="'+(mater.saleUnitId?mater.saleUnitId:"")+'" />'+
					'<input type="hidden" name="num" value="'+(num?num:"")+'" />'+
					'<input type="hidden" name="weight" value="'+(weight?weight:"")+'" />'+
					'<input type="hidden" name="oldAmount" value="'+(oldAmount?oldAmount:"")+'" />'+
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
					'<input type="hidden" name="state" value="" />';
	var fieldList = ["", ornaCode, itemClassName, ornaClassName, unitName, num, weight, oldOrnaName, newOrnaName, oldBasicPrice, newBasicPrice, 
	                 oldAmount, newAmount, sizeName, newSizeId, updateReason, oldSpecialWorkPrice, newSpecialWorkPrice, 
	                 oldNativeCert, newNativeCert, oldGiaCert, newGiaCert, oldHrdCert, newHrdCert, oldIgiCert, newIgiCert,
	                 oldZodiac, newZodiac, isDblLabel, labelKindFiled, state, deleteBtn];
	insertRow("tbl", fieldList, true);
	setLabelKindValue(0, isDblLabel);
	$n("labelKind")[0].value = labelKind;
	// var row = $("tbl").rows.length-1;
	/*BdCommonDwr.getSizeByItemIdAndOrnaId($n("itemClassId")[0].value, $n("ornaClassId")[0].value, function(data){
		addOptions2("newSizeId", 0, data, null, null, true, true);
	});*/
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
		changeSeq("tbl");
	});
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
	for(var i=0; i<len; i++){
		numTotal = numTotal + 1;
	}
	jQuery("#numTotal").val(numTotal>0?numTotal:"");
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
		LabelTagApplyPrintDwr.saveOrUpdateLabelApply(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				alert(data);
			} else {
				alert("保存成功", function(){
					window.location = "labelTagApplyPrint.vm";
				});
			}
		});
	});
}

function closeBill(){
	if(!checkForm()){
		return ;
	}
	confirm("确定要记账？", function(){
		var head = getHeadData(),
			lines = getLinesData(),
			deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		LabelTagApplyPrintDwr.saveAndMarkLabelApply(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("处理成功", function(){
						window.location = "labelTagApplyPrint.vm?user_action=toEdit&id="+data.id;
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
	var receiveNum = jQuery("#receiveNum").val(),
		cancelNum = jQuery("#cancelNum").val();
	
	var head = {
			no				: jQuery("#billNo").val(),
			orgId			: jQuery("#orgId").val(),
			id				: jQuery("#id").val(),
			numTotal		: jQuery("#numTotal").val(),
			receiveNum		: receiveNum?receiveNum:"0",
			receiveUserid	: jQuery("#receiveUserid").val(),
			cancelNum		: cancelNum?cancelNum:"0",
			printType		: jQuery("#printType").val(),
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
			weight:$n("weight")[i].value,
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
			state:$n("state")[i].value,
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