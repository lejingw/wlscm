function loadLineByOrnaCode(){
	var ornaCode = jQuery("#ornaCode_in").val(),
		labelId = jQuery("#id").val();
	if(!checkReceiveType()) return;
	if(ornaCode){
		LabelTagApplyPrintDwr.getLineByOrnaCode(labelId, ornaCode, function(data){
			afterLoad(data);
		});
	} else {
		alert("请输入饰品编码");
	}
}

function loadLineByOrnaBarCode(){
	var ornaBarCode = jQuery("#barCode_in").val(),
		labelId = jQuery("#id").val();
	if(!checkReceiveType()) return;
	if(ornaBarCode){
		LabelTagApplyPrintDwr.getLineByOrnaBarCode(labelId, ornaBarCode, function(data){
			afterLoad(data);
		});
	} else {
		alert("请输入扫描饰品");
	}
}

function afterLoad(data){
	if(data){
		if(data['isSuccess'] == "true"){
			var line = data['line'];
			if(checkExistLine(line['id'])) return;
			addMaterRow(line);
		} else {
			alert(data['msg']);
		}
	} else {
		alert("获取饰品信息错误");
	}
	jQuery("#ornaCode_in").val("");
	jQuery("#ornaBarCode_in").val("");
}

function addMaterRow(line){
	var itemClassName = nullToBlank(line.itemClassName),
		ornaClassName = nullToBlank(line.ornaClassName),
		ornaCode = nullToBlank(line.ornaCode),
		unitName = nullToBlank(line.unitName),
		num = nullToBlank(line.num),
		weight = nullToBlank(line.weight),
		oldAmount = nullToBlank(line.oldAmount),
		newAmount = nullToBlank(line.newAmount),
		oldOrnaName = nullToBlank(line.oldOrnaName),
		newOrnaName = nullToBlank(line.newOrnaName),
		oldNativeCert = nullToBlank(line.oldNativeCert),
		newNativeCert = nullToBlank(line.newNativeCert),
		oldGiaCert = nullToBlank(line.oldGiaCert),
		newGiaCert = nullToBlank(line.newGiaCert),
		oldHrdCert = nullToBlank(line.oldHrdCert),
		newHrdCert = nullToBlank(line.newHrdCert),
		oldIgiCert = nullToBlank(line.oldIgiCert),
		newIgiCert = nullToBlank(line.newIgiCert),
		oldZodiac = nullToBlank(line.oldZodiac),
		newZodiac = nullToBlank(line.newZodiac),
		oldSpecialWorkPrice = nullToBlank(line.oldSpecialWorkPrice),
		newSpecialWorkPrice = nullToBlank(line.newSpecialWorkPrice),
		oldBasicPrice = nullToBlank(line.oldBasicPrice),
		newBasicPrice = nullToBlank(line.newBasicPrice),
		isDblLabel = nullToBlank(line.isDblLabel),
		updateReason = nullToBlank(line.updateReason),
		state = nullToBlank(line.statusName),
		sizeName = nullToBlank(line.oldSizeName),
		labelKind = nullToBlank(line.labelKind),
		newSizeId = nullToBlank(line.newSizeName),
		new_size_id = nullToBlank(line.newSize),
		labelKindFiled = '<select type="text" name="labelKind" disabled /></select>';
		deleteBtn = '<input type="button" value="删除" style="width:60px;" onclick="deleteLineRow(this, \'tbl\')"/>' + 
					'<input type="hidden" name="itemClassId" value="'+line.itemClassId+'" />'+
					'<input type="hidden" name="ornaClassId" value="'+line.ornaClassId+'" />'+
					'<input type="hidden" name="new_size_id" value="'+new_size_id+'" />'+
					'<input type="hidden" name="labelKind_1" value="'+labelKind+'" />'+
					'<input type="hidden" name="isDblLabel" value="'+(isDblLabel?isDblLabel:"")+'" />'+
					'<input type="hidden" name="lineid" value="'+line.id+'" />';
	var fieldList = ["", ornaCode, itemClassName, ornaClassName, unitName, num, weight, oldOrnaName, newOrnaName, oldBasicPrice, newBasicPrice, 
	                 oldAmount, newAmount, sizeName, newSizeId, updateReason, oldSpecialWorkPrice, newSpecialWorkPrice, 
	                 oldNativeCert, newNativeCert, oldGiaCert, newGiaCert, oldHrdCert, newHrdCert, oldIgiCert, newIgiCert,
	                 oldZodiac, newZodiac, isDblLabel, labelKindFiled, state, deleteBtn];
	insertRow("tbl", fieldList, true);
	setLabelKindValue(0, isDblLabel);
	$n("labelKind")[0].value = labelKind;
	//var row = $("tbl").rows.length-1;
	//setNewSizeValue(0);
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
function deleteLineRow(button, domId) {
	confirm("确定要删除", function(){
		deleteRow(button, domId);
		changeSeq("tbl");
	});
}

function checkReceiveType(){
	if(!jQuery("#labelReceiveType").val()){
		alert("请选择接收方式");
		return false;
	}
	return true;
}
function checkExistLine(lineid){
	var len = $("tbl").rows.length;
	for(var i=0; i<len;i++){
		if(lineid){
			if(lineid == $n("lineid")[i].value){
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
		numTotal = jQuery("#numTotal").val(),
		labelReceiveType = jQuery("#labelReceiveType").val(),
		num = 0,
		num2 = 0;
	if(len > 0){
		jQuery("#labelReceiveType").attr("disabled", true);
	}else {
		jQuery("#labelReceiveType").attr("disabled", false);
	}
	for(var i=0; i<len; i++){
		num = num + 1;
	}
	num2 = floatSub(numTotal, num);
	if(labelReceiveType == "1"){
		jQuery("#cancelNum").val(num>0?num:"0");
		jQuery("#receiveNum").val(num2);
	} else {
		jQuery("#receiveNum").val(num>0?num:"0");
		jQuery("#cancelNum").val(num2);
	}
}


//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	return true;
}

function receiveBill(){
	if(!checkForm()){
		return ;
	}
	confirm("确定要接收?", function(){
		beforeReceive();
		var head = getHeadData(),
			lines = getLinesData();
		showLayer(true);
		LabelTagApplyPrintDwr.receiveLabelApplyPrint(head, lines, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("接收成功", function(){
						window.location = "labelTagApplyReceive.vm?user_action=toEdit&id="+data.id;
					});
				}else {
					alert(data.msg);
				}
			} else {
				alert("接收失败");
			}
		});
	});
}

function beforeReceive(){
	var labelReceiveType = jQuery("#labelReceiveType").val(),
		numTotal = jQuery("#numTotal").val(),
		len = $("tbl").rows.length;
	if(len == 0){
		if(labelReceiveType == "1"){
			jQuery("#receiveNum").val(numTotal);
			jQuery("#cancelNum").val("0");
		} else {
			jQuery("#receiveNum").val("0");
			jQuery("#cancelNum").val(numTotal);
		}
	}
}

function initBill(){
	confirm("确定要初始化?", function(){
		var headid = jQuery("#id").val();
		showLayer(true);
		LabelTagApplyPrintDwr.initLabelApplyPrint(headid, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("接收成功", function(){
						window.location = "labelTagApplyReceive.vm?user_action=toEdit&id="+headid;
					});
				}else {
					alert(data.msg);
				}
			} else {
				alert("接收失败");
			}
		});
	});
}

function closeBill(){
	confirm("确定要关闭？", function(){
		var headid = jQuery("#id").val();
		showLayer(true);
		LabelTagApplyPrintDwr.checkPrintOrnaCode(headid, function(res){
			showLayer(false);
			if(res){
				if(res.isSuccess =="true"){
					if(res.not_in_mater || res.not_in_org){
						var msg = "<font style='color:red;'>";
						if(res.not_in_mater){
							msg = msg + "【" +revertOrnaCode(res.not_in_mater) + "】编码不在库存<br>"; 
						}
						if(res.not_in_org) {
							msg = msg + "【" + revertOrnaCode(res.not_in_org) + "】编码组织不符";
						}
						confirm(msg + "</font><br>确定要继续关闭单据？", function(){
							closeLabelPrint(headid, res);
						});
					} else {
						closeLabelPrint(headid, null);
					}
				} else {
					alert(res.msg);
				}
			} else {
				alert("关闭失败");
			}
		});
	});
}

function revertOrnaCode(ornaCodes){
	var ornaArr = ornaCodes.split(","),
		newOrnas = "";
	for(var i=0; i<ornaArr.length; i++){
		if(i%2==0 && newOrnas){
			newOrnas = newOrnas + "<br>";
		}
		if(newOrnas){
			newOrnas = newOrnas + "," + ornaArr[i];
		} else {
			newOrnas = ornaArr[i];
		}
        if(i > 20) {
            break;
        }
	}
	return newOrnas;
}

function closeLabelPrint(headid, res){
	var ornaNotInmater = "",
		ornaNotInOrg = "";
	if(res){
		ornaNotInmater = res.not_in_mater,
		ornaNotInOrg = res.not_in_org;
	}
	showLayer(true);
	LabelTagApplyPrintDwr.closeLabelPrint(headid, ornaNotInmater, ornaNotInOrg, function(data){
		showLayer(false);
		if(data){
			if(data.isSuccess == "true"){
				alert("处理成功", function(){
					window.location = "labelTagApplyReceive.vm";
				});
			} else {
				alert(data.msg);
			}
		} else {
			alert("处理失败");
		}
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
			state			: "14",
			labelReceiveType:jQuery("#labelReceiveType").val()
	};
	return head;
}

function getLinesData(){
	var lines = [],
		len = $("tbl").rows.length;
	for(var i=0; i<len ; i++){
		var obj = {
			id:$n("lineid")[i].value
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