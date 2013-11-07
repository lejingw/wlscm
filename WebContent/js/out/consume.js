
function initFun(){
	jQuery("#btnAdd").click(addLine);
	jQuery("#btnSave").click(saveConsume);
	jQuery("#btnCheck").click(checkConsume);
	jQuery("#btnClose").click(closeConsume);
	jQuery("#btnToList").click(function(){
		window.location = "consum.vm";
	});
	
	DictDwr.getDictsForSlt("maintaintype", function(data){
		for(var i=0; i<rowLen; i++){
			addOptions2("dotype", i,  data, null, null, true, true, $n("dotype_old")[i].value);
		}
	});
	BdCommonDwr.getAllItemClassForSlt(function(data){
		for(var k=0; k<rowLen; k++) {
			addOptions2("itemClassId", k,  data, null, null, true, true, $n("itemClassId_old")[k].value);
			changeOraClass($n("itemClassId")[k], true);
		}
	});
	var rowLen = $("tbl").rows.length;
	for(var j=0; j<rowLen; j++){
		checkDotype(null, j);
	}
}

function changeOraClass(itemClassObj, useOldVal){
	var idx = itemClassObj.parentNode.parentNode.rowIndex-1;
	var ornaClassId = useOldVal?$n("ornaClassId_old")[idx].value:"";
	var qualityId = useOldVal?$n("qualityId_old")[idx].value:"";
	BdCommonDwr.getOrnaClassByItemClassIdForSlt($n("itemClassId")[idx].value, function(data){
		addOptions2("ornaClassId", idx,  data, null, null, true, true, ornaClassId);		
	});
	BdCommonDwr.getQualityByItemClassId($n("itemClassId")[idx].value, function(data){
		addOptions2("qualityId", idx,  data, null, null, true, true, qualityId);		
	});
}

function addLine(){
	var dotype 		= '<select name="dotype" onchange="checkDotype(this)" style="width:70px" dataType="Require" msg="不能为空"></select>',
		itemClassId = '<select name="itemClassId" onchange="changeOraClass(this, false)" style="width:70px" dataType="Require" msg="不能为空"></select>',
		ornaClassId = '<select name="ornaClassId" style="width:70px" ></select>',
		qualityId 	= '<select name="qualityId" style="width:90px" dataType="Require" msg="不能为空"></select>',
		allQty 	= '<input name="allQty" style="width:70px" />',
		mainWeight 	= '<input name="mainWeight" style="width:70px"  />',
		partWeight 	= '<input name="partWeight" style="width:70px"  />',
		addNums 	= '<input name="addNums" style="width:70px" />',
		addWeight 	= '<input name="addWeight" style="width:70px" />',
		addMoney 	= '<input name="addMoney" style="width:70px"  />',
		addGoldWeight 	= '<input name="addGoldWeight" style="width:70px"/>',
		addGoldMoney 	= '<input name="addGoldMoney" style="width:70px"  />',
		repairCharge 	= '<input name="repairCharge" style="width:70px" dataType="Require" msg="不能为空" />',
		repairMoney 	= '<input name="repairMoney" onblur="changeValues()" style="width:70px" dataType="Require" msg="不能为空" />',
		// 删除按钮
		inner_HTML = "<input type='button' value='删除' style='width:60px;' onclick='deleteLine(this,\"\")'/>" +
					"<input type='hidden' name='lineid' value='' />"+
					"<input type='hidden' name='dotype_old' value='' />"+
					"<input type='hidden' name='itemClassId_old' value='' />"+
					"<input type='hidden' name='itemClassId_old' value='' />"+
					"<input type='hidden' name='qualityId_old' value='' />";
	var fieldList = ["", dotype, itemClassId, ornaClassId, qualityId, allQty, mainWeight, partWeight, addNums, addWeight, 
						addMoney, addGoldWeight, addGoldMoney, repairCharge, repairMoney,  inner_HTML ];
	insertRow("tbl", fieldList, true);
	DictDwr.getDictsForSlt("maintaintype", function(data){
		addOptions2("dotype", 0,  data, null, null, true, true, null);
	});
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions2("itemClassId", 0,  data, null, null, true, true, null);
	});
	changeValues();
	changeSeq("tbl");
}

function changeValues() {
	var rowLen = $("tbl").rows.length;
	var repairMoneySum = 0;
	for(var i=0; i<rowLen; i++) {
		var repairMoney = $n("repairMoney")[i].value;
		if(repairMoney){
			repairMoneySum = floatAdd(repairMoneySum , repairMoney);
		}
	}
	$("amount").value = repairMoneySum>0?repairMoneySum+"":"0";
}

function checkDotype(obj, idx){
	var row = obj?obj.parentNode.parentNode.rowIndex-1:idx;
	var dotype = obj?obj.value:$n("dotype_old")[row].value;
	if(dotype == "1"){
		if(isSaved){
			jQuery($n("ornaClassId")[row]).attr("disabled", false);
		}
		jQuery($n("mainWeight")[row]).attr("disabled", false);
		jQuery($n("partWeight")[row]).attr("disabled", false);
		jQuery($n("addNums")[row]).attr("disabled", false);
		jQuery($n("addWeight")[row]).attr("disabled", false);
		jQuery($n("addMoney")[row]).attr("disabled", false);
		jQuery($n("addGoldWeight")[row]).attr("disabled", false);
		jQuery($n("addGoldMoney")[row]).attr("disabled", false);
	} else if(dotype == "2") {
		$n("ornaClassId")[row].value = "";
		$n("mainWeight")[row].value = "";
		$n("partWeight")[row].value = "";
		$n("addNums")[row].value = "";
		$n("addWeight")[row].value = "";
		$n("addMoney")[row].value = "";
		$n("addGoldWeight")[row].value = "";
		$n("addGoldMoney")[row].value = "";
		jQuery($n("ornaClassId")[row]).attr("disabled", true);
		jQuery($n("mainWeight")[row]).attr("disabled", true);
		jQuery($n("partWeight")[row]).attr("disabled", true);
		jQuery($n("addNums")[row]).attr("disabled", true);
		jQuery($n("addWeight")[row]).attr("disabled", true);
		jQuery($n("addMoney")[row]).attr("disabled", true);
		jQuery($n("addGoldWeight")[row]).attr("disabled", true);
		jQuery($n("addGoldMoney")[row]).attr("disabled", true);
	}
}

function deleteLine(button, lineid) {
	confirm("确定要删除?", function(){
		if(lineid) {
			var deleteIds = $("deleteIds").value;
			var oldIdsArray = deleteIds.split(";");
			var isExist = false;
			for(var i=0;i<oldIdsArray.length;i++) {
				if(oldIdsArray[i] == lineid) {
					isExist = true;
				}
			}
			if(!isExist) {
				if(deleteIds) {
					deleteIds = deleteIds + ";" + lineid;				
				} else {
					deleteIds = lineid;
				}
			}
			$("deleteIds").value = deleteIds;
		}
		deleteRow(button,"tbl");
		changeValues();
		changeSeq("tbl");
	});
}

function checkField(){
	var len = $("tbl").rows.length;
	for(var i=0; i<len; i++){
		var allQty = $n("allQty")[i].value;
		var mainWeight = $n("mainWeight")[i].value;
		var partWeight = $n("partWeight")[i].value;
		var addNums = $n("addNums")[i].value;
		var addWeight = $n("addWeight")[i].value;
		var addMoney = $n("addMoney")[i].value;
		var addGoldWeight = $n("addGoldWeight")[i].value;
		var addGoldMoney = $n("addGoldMoney")[i].value;
		var repairCharge = $n("repairCharge")[i].value;
		var repairMoney = $n("repairMoney")[i].value;
		if(allQty.length > 0 ){
			if(!isDecimal(allQty) || floatSub(allQty, 0) < 0){
				alert("第" + (i+1) + "行，数量/重量必须是大于等于0的数");
				return false;
			}
		}
		if(mainWeight.length > 0){
			if(!isDecimal(mainWeight) || floatSub(mainWeight, 0) < 0){
				alert("第" + (i+1) + "行，主石重量必须是大于等于0的数");
				return false;
			}
		}
		if(partWeight.length > 0){
			if(!isDecimal(partWeight) || floatSub(partWeight, 0) < 0){
				alert("第" + (i+1) + "行，配石重量必须是大于等于0的数");
				return false;
			}
		}
		if(addNums.length > 0){
			if(!isDecimal(addNums) || floatSub(addNums, 0) < 0){
				alert("第" + (i+1) + "行，补石粒数必须是大于等于0的数");
				return false;
			}
		}
		if(addWeight.length > 0){
			if(!isDecimal(addWeight) || floatSub(addWeight, 0) < 0){
				alert("第" + (i+1) + "行，补石重量必须是大于等于0的数");
				return false;
			}
		}
		if(addMoney.length > 0){
			if(!isDecimal(addMoney) || floatSub(addMoney, 0) < 0){
				alert("第" + (i+1) + "行，补石金额必须是大于等于0的数");
				return false;
			}
		}
		if(addGoldWeight.length > 0){
			if(!isDecimal(addGoldWeight) || floatSub(addGoldWeight, 0) < 0){
				alert("第" + (i+1) + "行，加金重量必须是大于等于0的数");
				return false;
			}
		}
		if(addGoldMoney.length > 0){
			if(!isDecimal(addGoldMoney) || floatSub(addGoldMoney, 0) < 0){
				alert("第" + (i+1) + "行，加金金额必须是大于等于0的数");
				return false;
			}
		}
		if(repairCharge.length > 0){
			if(!isDecimal(repairCharge) || floatSub(repairCharge, 0) <= 0){
				alert("第" + (i+1) + "行，维修工费(加工费单价)必须是大于0的数");
				return false;
			}
		}
		if(repairMoney.length > 0){
			if(!isDecimal(repairMoney) || floatSub(repairMoney, 0) < 0){
				alert("第" + (i+1) + "行，维修金额(加工费)必须是大于0的数");
				return false;
			}
		}
	}
	return true;
}


function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var oLen = $("tbl").rows.length;
	if (oLen==0) {
		alert("尚未添加行数据！！！");
		return false;
	}
	if(!checkField()) return false;
	return true;
}

function saveConsume(){
	if(!checkForm()){
		return ;
	}
	confirm("确定要保存？", function(){
		var lines = getLines();
		var head = getHead();
		var deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		ConsumeDwr.saveOrUpdateConsume(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data) {
				if(data.isSuccess == "true"){
					alert("保存成功", function(){
						window.location = "consum.vm";
					});
				} else {
					alert(data.msg);
				}
			} else {
				alert("保存失败");
			}
		});
	});
}


function checkConsume(){
	if(!checkForm()){
		return ;
	}
	confirm("确定要审批？", function(){
		var lines = getLines();
		var head = getHead();
		var deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		ConsumeDwr.saveAndCheckConsume(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data) {
				if(data.isSuccess == "true"){
					alert("审批成功", function(){
						window.location = "consum.vm?user_action=toEditConsum&billid=" + data.billid;
					});
				} else {
					alert(data.msg);
				}
			} else {
				alert("审批失败");
			}
		});
	});
}

function closeConsume(){
	var billid = jQuery("#billid").val();
	if(billid) {
		confirm("确认要关闭单据？", function(){
			showLayer(true);
			ConsumeDwr.closeConsume(billid, function(msg){
				showLayer(false);
				if(msg) {
					alert(msg);
				} else {
					alert("单据关闭成功", function(){
						window.location = "consum.vm";
					});
				}
			});
		});
	} else {
		alert("关闭异常");
	}
}


function getHead(){
	var headObj = {
			billid               :returnNull(jQuery("#billid").val()),			                  
			billno               :returnNull(jQuery("#billno").val()),             
			orgId                :returnNull(jQuery("#orgId").val()),    
			dodate                :returnNull(jQuery("#dodate").val()),    
			vendorId             :returnNull(jQuery("#vendorId").val()),
			amount			     :returnNull(jQuery("#amount").val()),
			memo                 :returnNull(jQuery("#memo").val())                                      
	};
	return headObj;
}
function returnNull(v){
	if(v==""||v==null)
		return null;
	else
		return v;
}

function getLines() {
	var oldRows = $("tbl").rows.length;
	var oldItemList = [];
	for (var i=0; i<oldRows; i++) {
		var oldItem = {
				dotype			:$n("dotype")[i].value,
				itemClassId		:$n("itemClassId")[i].value,
				ornaClassId		:$n("ornaClassId")[i].value,
				qualityId		:$n("qualityId")[i].value,
				allQty			:$n("allQty")[i].value,
				mainWeight		:$n("mainWeight")[i].value,
				partWeight		:$n("partWeight")[i].value,
				addWeight		:$n("addWeight")[i].value,
				addMoney		:$n("addMoney")[i].value,
				addNums			:$n("addNums")[i].value,
				addGoldWeight	:$n("addGoldWeight")[i].value,
				addGoldMoney	:$n("addGoldMoney")[i].value,
				repairCharge	:$n("repairCharge")[i].value,
				repairMoney		:$n("repairMoney")[i].value,
				lineid			:$n("lineid")[i].value
			};
		oldItemList[i] = oldItem;
	}
	return oldItemList;
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}