/**
 * 增加数据行
 * @param data
 */
function addPackageOldMaterActive(result) {
	if(result.isSuccess == "true"){
		dealMaterActive(result.data);
	} else {
		alert(result.msg);
	}
}

function dealMaterActive(data){
if(!checkPackageType(data)) return ;
	
	tr = document.createElement("TR");
	
	$("tbl_old_mater").appendChild(tr);
	createTdElement(tr, "");
	 // 大类
	createTdElement(tr, data['itemClassName']);
	 // 小类
	createTdElement(tr, data['ornaClassName']);
	 // 款式
	createTdElement(tr, printStyle(data['styleId'], data['styleName'], data['bigGraph']));
	 // 分析范围
	createTdElement(tr, data['alaysisName']);
	 // 饰品编码
	createTdElement(tr, data['ornaCode']);
	 // 商品名称
	createTdElement(tr, data['ornaDsc']);
	 // 计量单位
	createTdElement(tr, data['saleUnitName']);
	 // 现有量
	createTdElement(tr, data['nowQty']);
	 // 总量
	createTdElement(tr, data['allQty']);
	 // 粒数
	createTdElement(tr, data['stoneNowNum']);
	 // 网店金额
	createTdElement(tr, data['posAmount']);
	 // 主石重量
	createTdElement(tr, data['mainWeight']);
	 // 配石重量
	createTdElement(tr, data['partWeight']);
	
	createTdElement(tr, data['mainShapname']);
	createTdElement(tr, data['colorName']);
	createTdElement(tr, "<input type='checkbox' name='newVal' style='width:10px;' onclick='checkNewVal(this)'/>");
	// 删除按钮
	inner_HTML = "<input type='button' value='删除' style='width:60px;' onclick='deleteMater(this,\""+data['ornaCode']+"\")'/>" + 
				"<input type='hidden' name='itemClassId' value='"+ (data['itemClassId']?data['itemClassId']:"") +"' />" +
				"<input type='hidden' name='ornaClassId' value='"+ (data['ornaClassId']?data['ornaClassId']:"") +"' />" +
				"<input type='hidden' name='styleId' value='"+ (data['styleId']?data['styleId']:"") +"' />" + 
				"<input type='hidden' name='alaysisId' value='"+ (data['alaysisId']?data['alaysisId']:"") +"' />" +
				"<input type='hidden' name='alaysisName' value='"+ (data['alaysisName']?data['alaysisName']:"") +"' />" +
				"<input type='hidden' name='ornaCode' value='"+ (data['ornaCode']?data['ornaCode']:"") +"' />" + 
				"<input type='hidden' name='ornaDsc' value='"+ (data['ornaDsc']?data['ornaDsc']:"") +"' />" + 
				"<input type='hidden' name='ornaBarcode' value='"+ (data['ornaBarcode']?data['ornaBarcode']:"") +"' />" +
				"<input type='hidden' name='saleUnitId' value='"+ (data['saleUnitId']?data['saleUnitId']:"") +"' />" + 
				"<input type='hidden' name='nowQty' value='"+ (data['nowQty']?data['nowQty']:"") +"' />" + 
				"<input type='hidden' name='allQty' value='"+ (data['allQty']?data['allQty']:"") +"' />" + 
				"<input type='hidden' name='stoneNowNum' value='"+ (data['stoneNowNum']?data['stoneNowNum']:"") +"' />" +
				"<input type='hidden' name='posAmount' value='"+ (data['posAmount']?data['posAmount']:"") +"' />" + 
				"<input type='hidden' name='mainWeight' value='"+ (data['mainWeight']?data['mainWeight']:"") +"' />" + 
				"<input type='hidden' name='partWeight' value='"+ (data['partWeight']?data['partWeight']:"") +"' />" +
				"<input type='hidden' name='isMutiPart' value='"+ (data['isMutiPart']?data['isMutiPart']:"") +"' />" +
				"<input type='hidden' name='isDblLabel' value='"+ (data['isDblLabel']?data['isDblLabel']:"") +"' />" +
				"<input type='hidden' name='isConsign' value='"+ (data['isConsign']?data['isConsign']:"") +"' />" +
				"<input type='hidden' name='realTotalCost' value='"+( data['realTotalCost']?data['realTotalCost']:"") +"' />" +
				"<input type='hidden' name='stockId' value='"+ (data['stockId']?data['stockId']:"") +"' />" +
				"<input type='hidden' name='inivCost' value='"+ (data['inivCost']?data['inivCost']:"") +"' />" +
				"<input type='hidden' name='colorId' value='"+ (data['colorId']?data['colorId']:"") +"' />" +
				"<input type='hidden' name='mainShapeId' value='"+ (data['mainShapeId']?data['mainShapeId']:"") +"' />" +
				"<input type='hidden' name='oldLineid' value='' />"  ;
	createTdElement(tr, inner_HTML);
	// 业务类型 禁用控制
	enablePackageType();
	changeValues();
	changeSeq("tbl_old_mater");
}

function changeValues() {
	// ====================== 原重量 和 原粒数==============
	var rowLen = $("tbl_old_mater").rows.length,
		oWeight = 0,
		oGrains = 0;
	for(var i=0; i<rowLen; i++) {
		var allQty = $n("nowQty")[i].value;
		if(allQty){
			oWeight = floatAdd(oWeight ,allQty);
		}
		var stoneNowNum = $n("stoneNowNum")[i].value;
		if(stoneNowNum){
			oGrains = floatAdd(oGrains, stoneNowNum);
		}
	}
	$("oldWeight").value = oWeight>0?oWeight+"":"";
	$("oldGrains").value = oGrains>0?oGrains+"":"";
	if(rowLen > 0){
		jQuery("#stock_id").attr("disabled", true);
	} else {
		jQuery("#stock_id").attr("disabled", false);
	}
	if(rowLen <= 0){
		while($("tbl").rows.length >0){
			deleteRowByIndex(0, "tbl");
		}
	}
}

/**
 * 从原饰品行表中删除 一行
 * @param button
 * @param ornaCode
 */
function deleteMater(button, ornaCode) {
	confirm("确定要删除", function(){
		// deleteOldIds 增加值
		var ri = button.parentNode.parentNode.rowIndex;
		var oldLineid = $n("oldLineid")[ri-1].value;
		if(oldLineid) {
			var deleteOldIds = $("deleteOldIds").value;
			var oldIdsArray = deleteOldIds.split(";");
			var isExist = false;
			for(var i=0;i<oldIdsArray.length;i++) {
				if(oldIdsArray[i] == oldLineid) {
					// 已经存在
					isExist = true;
				}
			}
			if(!isExist) {
				if(deleteOldIds) {
					deleteOldIds = deleteOldIds + ";" + oldLineid + ":" + ornaCode;				
				} else {
					deleteOldIds = oldLineid + ":" + ornaCode;
				}
			}
			$("deleteOldIds").value = deleteOldIds;
		}
		
		// 删除行
		deleteRow(button,"tbl_old_mater");
		// 业务类型 禁用控制
		enablePackageType();
		changeValues();
		changeSeq("tbl_old_mater");
	});
}

function deleteNewMater(button, domId) {
	confirm("确定要删除", function(){
		// deleteNewIds 增加值
		var ri = button.parentNode.parentNode.rowIndex;
		var newLineId = $n("newLineId")[ri-1].value;
		if(newLineId) {
			var deleteNewIds = $("deleteNewIds").value;
			var newIdsArray = deleteNewIds.split(";");
			var isExist = false;
			for(var i=0;i<newIdsArray.length;i++) {
				if(newIdsArray[i] == newLineId) {
					// 已经存在
					isExist = true;
				}
			}
			if(!isExist) {
				if(deleteNewIds) {
					deleteNewIds = deleteNewIds + ";" + newLineId;
				} else {
					deleteNewIds = newLineId;
				}
			}
			$("deleteNewIds").value = deleteNewIds;
		}
		
		deleteRow(button, domId);
		changeSeq("tbl");
	});
	
}

function checkPackageType(materData) {
	var chLen = $("tbl_old_mater").rows.length;
	var packageType = jQuery("#dotype").val();
	// info("业务类型：" + packageType);
	if (!packageType) {
		alert("请先选择业务类型");
		jQuery("#dotype").removeAttr("disabled");
		return false;
	}
	if(jQuery("#dotype").val() == "0"){
		if(chLen > 0){
			alert("拆包只支持一种原饰品");
			return false;
		}
	}
	if(jQuery("#dotype").val() == "2"){
		if(chLen > 0){
			alert("原料分割目前只支持一种原饰品");
			return false;
		}
	}
	if (checkOldMaterExist(materData['ornaCode'])) {
		return false;
	}
	
	if(jQuery("#dotype").val() == "1") {
		return checkPackage(materData);
	}
	
	return true;
}

/**
 * 判断商品编码是否已经存在列表中
 * @param ornaCode
 * @returns {Boolean}
 */
function checkOldMaterExist(ornaCode) {
	var rowLen = $("tbl_old_mater").rows.length;
	if (rowLen >0) {
		for (var i=0; i< rowLen;i++) {
			if($n("ornaCode")[i].value == ornaCode) {
				alert("饰品已经存在！！！");
				return true;
			}
		}
	}
	return false;
}

/**
 * 判断混包<br>
 * 所有原饰品的大类 小类 以及分析范围必须一致否则不允许混包
 */
function checkPackage(materData) {
	var rowLen = $("tbl_old_mater").rows.length;
	if (rowLen > 0) {
		var itemClassId = $n("itemClassId")[0].value;
		var ornaClassId = $n("ornaClassId")[0].value;
		var alaysisId = $n("alaysisId")[0].value;
		if(materData['itemClassId'] == itemClassId && materData['ornaClassId'] == ornaClassId && materData['alaysisId']==alaysisId) {
			return true;
		} else {
			alert("不相同的大类、小类和分析范围的饰品不允许混包");
			return false;
		}
	} else {
		return true;
	}
}

function enablePackageType() {
	var chLen = $("tbl_old_mater").rows.length;
	if (chLen>0) {
		jQuery("#dotype").attr("disabled", true);
	} else {
		jQuery("#dotype").removeAttr("disabled");
	}
}

function createTdElement(trElement, value) {
	tdEle = document.createElement("TD");
	tdEle.innerHTML = value;
	trElement.appendChild(tdEle);
}

/**
 * 增加行 新饰品
 */
function addRow(){
	var chLen = $("tbl_old_mater").rows.length;
	if(chLen <= 0) {
		alert("请先扫面或者输入原饰品的编码");
		return ;
	}
	var ptype = jQuery("#dotype").val() ;
	if(!ptype){
		alert("请选择业务类型！！！");
		return ;
	}
	if(ptype =="1") {
		if(chLen < 2) {
			alert("混包时至少2种原饰品！！！");
			return;
		}
		if($("tbl").rows.length >0) {
			alert("混包只允许混合成一种新饰品！！！");
			return;
		}
	}
	var dotype = jQuery("#dotype").val(),
		field2 = "";
	if(dotype == "2"){
		field2 = "<input type='hidden' name='newStoneNum' disabled />";
	}else {
		field2 = "<input type='text' name='newStoneNum' dataType='Require' onblur='checkField()' msg='不能为空'/><font color='red'>*</font>";
	}
	var field1 = "<input type='hidden' name='newLineId' />" +
			"<input type='hidden' name='newBarCode' />" +
			"<input type='hidden' name='newInivCost' />"+
			"<input type='text' name='newCode' value='自动生成' readonly />";
	var field3 = "<input type='text' name='new_Weight' dataType='Require' onblur='checkField()' msg='不能为空'/><font color='red'>*</font>";
	var field4 = "<input type='hidden' name='newAlaysisId' />" +
			"<input type='text' name='newAlaysisName' dataType='Require' readonly msg='不能为空'/><font color='red'>*</font>";
	var field5 = "<input type='button' value='删除' style='width:60px;' onclick='deleteNewMater(this,\"tbl\")'/>";
	
	insertRow("tbl",["", field1,field2,field3,field4, "", field5],false);
	changeSeq("tbl");
}


function checkField(){
	var rowLen = $("tbl").rows.length,
		nWeight = 0,
		nGrains = 0;
	for(var i=0; i< rowLen; i++){
		var newStoneNum = $n("newStoneNum")[i].value,
			newWeight = $n("new_Weight")[i].value;
		if(newStoneNum){
			if(isInteger(newStoneNum)){
				if(floatSub(newStoneNum, 0) <=0){
					alert("粒数必须是大于0的数");
					return false;
				}
			} else {
				alert("粒数必须是整数");
				return false;
			}
			nGrains = floatAdd(nGrains , newStoneNum);
		}
		
		if(newWeight){
			if(isDecimal(newWeight)){
				if(floatSub(newWeight, 0) <=0){
					alert("重量必须是大于0 的数");
					return false;
				}
			} else {
				alert("重量必须是数字");
				return false;
			}
			var dotype = jQuery("#dotype").val();
			if(dotype == "2"){//  原料分割 重新计算 分析范围
				changeAlaysis(i, newWeight);
			} else if (dotype == "0"){// 拆包 
				changeUnpackingAlaysis(i);
			} else {
				$n("newAlaysisId")[i].value = $n("alaysisId")[0].value;
				$n("newAlaysisName")[i].value = $n("alaysisName")[0].value;
			}
			nWeight = floatAdd(nWeight , newWeight);
		}
	}
	$("newWeight").value = nWeight>0?nWeight+"":"";
	$("newGrains").value = nGrains>0?nGrains+"":"";
	return true;
}

function changeAlaysis(idx, newWeight){
	var oldLen = $("tbl_old_mater").rows.length,
		oldAllPrice = 0, // 总价格
		oldAllWeight = 0,// 总重量
		mainWeightAll = 0,// 主石总重量
		basePrice = 0;
	for(var i=0; i< oldLen; i++) {
		var posAmount = $n("posAmount")[i].value,
			allQty = $n("nowQty")[i].value,
			mainWeight = $n("mainWeight")[i].value;
		if(posAmount){
			oldAllPrice = floatAdd(oldAllPrice ,posAmount);					
		}
		if(allQty) {
			oldAllWeight = floatAdd(oldAllWeight , allQty);
		}
		if(mainWeight){
			mainWeightAll = floatAdd(mainWeightAll , mainWeight);
		}
	}
	if(oldAllWeight>0 && oldAllPrice >0){// 计算销价
		basePrice = floatMul(floatDiv(newWeight, oldAllWeight), oldAllPrice);
	}
	var analysisObj = {
			allQty		:oldAllWeight,
			mainWeight 	: mainWeightAll,
			basePrice	:basePrice,
			itemClassId	:$n("itemClassId")[0].value,
			ornaClassId	:$n("ornaClassId")[0].value
		};
	PackageDwr.getAnalysis(analysisObj, function(data){
		if(data) {
			$n("newAlaysisId")[idx].value = data['analysisId'];
			$n("newAlaysisName")[idx].value = data['analysisDesc'];
		} else {
			alert("取不到分析范围");
			$n("newAlaysisId")[idx].value = "";
			$n("newAlaysisName")[idx].value = "";
		}
	});
}

function changeUnpackingAlaysis(idx, newWeight){
	var oldLen = $("tbl_old_mater").rows.length,
		oldAllPrice = 0, // 总价格
		oldAllWeight = 0,// 总重量
		basePrice = 0;
	for(var i=0; i< oldLen; i++) {
		var posAmount = $n("posAmount")[i].value,
			allQty = $n("nowQty")[i].value;
		if(posAmount){
			oldAllPrice = floatAdd(oldAllPrice ,posAmount);					
		}
		if(allQty) {
			oldAllWeight = floatAdd(oldAllWeight , allQty);
		}
	}
	if(oldAllWeight>0 && oldAllPrice >0){// 计算销价
		basePrice = floatMul(floatDiv(newWeight, oldAllWeight), oldAllPrice);
	}
	var grain = $n("newStoneNum")[idx].value,
		newWeight = $n("new_Weight")[idx].value;
	if(grain && newWeight){
		var avg = floatDiv(newWeight, grain);
		// info(avg);
		var analysisObj = {
				allQty		:oldAllWeight,
				mainWeight 	: avg,
				basePrice	:basePrice,
				itemClassId	:$n("itemClassId")[0].value,
				ornaClassId	:$n("ornaClassId")[0].value
			};
		PackageDwr.getAnalysis(analysisObj, function(data){
			var oldAnalysId =  $n("alaysisId")[0].value;
			if(data) {
				if(oldAnalysId == data['analysisId']){
					$n("newAlaysisId")[idx].value = data['analysisId'];
					$n("newAlaysisName")[idx].value = data['analysisDesc'];
				} else {
					// 判断当前分析范围是否是原分析范围的上下档
					PackageDwr.checkAnalysis(oldAnalysId, data['analysisId'], function(res){
						if(res){
							if(res == "true"){
								$n("newAlaysisId")[idx].value = data['analysisId'];
								$n("newAlaysisName")[idx].value = data['analysisDesc'];
							} else {
								alert("重量/粒数的平均分数不在有效范围内");
								$n("newAlaysisId")[idx].value = "";
								$n("newAlaysisName")[idx].value = "";
							}
						} else {
							alert("无法取得分析范围");
							$n("newAlaysisId")[idx].value = "";
							$n("newAlaysisName")[idx].value = "";
						}
					});
				}
			} else {
				alert("取不到分析范围");
				$n("newAlaysisId")[idx].value = "";
				$n("newAlaysisName")[idx].value = "";
			}
		});
	}
} 



//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField()) return false;
	var oLen = $("tbl_old_mater").rows.length,
		nLen = $("tbl").rows.length,
		ptype = jQuery("#dotype").val();
	if (oLen==0) {
		alert("尚未添加原饰品！！！");
		return false;
	}
	if (nLen==0) {
		alert("尚未添加新饰品！！！");
		return false;
	}
	if(ptype == "0" || ptype == "2") { // 拆包/原料分割
		if(nLen <= 1) {
			alert("拆包/原料分割必须是多个新饰品！！！");
			return false;
		}
		if(oLen != 1) {
			alert("拆包/原料分割只能一种原饰品！！！");
			return false;
		}
	}
	if (ptype == "1") { // 混包
		if(nLen != 1) {
			alert("混包只能混成一种新饰品！！！");
			return false;
		}
		if(oLen <= 1) {
			alert("混包时必须多种原饰品！！！");
			return false;
		}
		if(!checkColorAndMainShap()) return false;
	}
	if(ptype != "2"){
	//  原粒数和新粒数的误差判别
		if(floatSub(jQuery("#oldGrains").val() , jQuery("#newGrains").val()) != 0){
			alert("新粒数合计必须和原粒数合计保持一直。");
			return false;
		}
	}
	return true;
}

function checkColorAndMainShap(){
	var len = $("tbl_old_mater").rows.length,
		colorArr = [],
		mainShapArr = [];
	for(var i=0; i<len; i++){
		var colorId = $n("colorId")[i].value,
			mainShapId = $n("mainShapeId")[i].value;
		if(!colorArr.contains(colorId)){
			colorArr.push(colorId);
		}
		if(!mainShapArr.contains(mainShapId)){
			mainShapArr.push(mainShapId);
		}
	}
	if(colorArr.length >1 ){
		alert("不同颜色的饰品不允许混包");
		return false;
	}
	if(mainShapArr.length > 1){
		alert("不同形状的饰品不允许混包");
		return false;
	}
	return true;
}

function checkNewVal(obj){
	var idx = obj.parentNode.parentNode.rowIndex,
		len = $("tbl_old_mater").rows.length;
	if(jQuery(obj).attr("checked")){
		for(var i=0; i<len; i++){
			if(idx-1 != i){
				jQuery($n("newVal")[i]).attr("checked", false);
			}
		}
	}
}

/**
 * 保存表单
 */
function saveform() {
	if(!checkForm()){
		return ;
	}
	showLayer(true);
	PackageDwr.checkWeightValid(jQuery("#oldWeight").val(), jQuery("#newWeight").val(), function(data){
		showLayer(false);
		if(data) {
			alert(data);
		} else {
			submitForm();
		}
	});
}

function submitForm(){
	confirm("确定要保存？", function(){
		var oldLines = getOldItemList();
		//info(oldLines[0].ornaCode);
		var newLines = getNewLinesList();
		var packageHead = {
			billid		:jQuery("#billid").val(),
			billno		:jQuery("#billno").val(),
			dodate		:jQuery("#dodate").val(),
			orgId		:jQuery("#orgId").val(),
			status		:"1",
			stockId		:jQuery("#stock_id").val(),
			oldWeight	:jQuery("#oldWeight").val(),
			oldGrains	:jQuery("#oldGrains").val(),
			newWeight	:jQuery("#newWeight").val(),
			newGrains	:jQuery("#newGrains").val(),
			dotype		:jQuery("#dotype").val(),
			memo		:jQuery("#memo").val()
		};
		var deleteOIds = [jQuery("#deleteOldIds").val(), jQuery("#deleteNewIds").val()];
		showLayer(true);
		PackageDwr.saveOrUpdatePackage(packageHead, oldLines, newLines, deleteOIds, function(msg){
			showLayer(false);
			if(msg) {
				alert(msg);
			} else {
				alert("保存成功", function(){
					window.location = "procPackage.vm";
				});
			}
		});
	});
}
/**
 * 取原饰品行表数据
 * @returns {Array}
 */
function getOldItemList() {
	var oldRows = $("tbl_old_mater").rows.length;
	var oldItemList = [];
	for (var i=0; i<oldRows; i++) {
		var oldItem = {
				ornaCode:$n("ornaCode")[i].value,
				lineid	:$n("oldLineid")[i].value,
				itemClassId:$n("itemClassId")[i].value,
				ornaClassId:$n("ornaClassId")[i].value,
				styleId:$n("styleId")[i].value,
				alaysisId:$n("alaysisId")[i].value,
				ornaCode:$n("ornaCode")[i].value,
				ornaDsc:$n("ornaDsc")[i].value,
				ornaBarcode:$n("ornaBarcode")[i].value,
				unitId:$n("saleUnitId")[i].value,
				nowQty:$n("nowQty")[i].value,
				allQty:$n("allQty")[i].value,
				grains:$n("stoneNowNum")[i].value,
				posMoney:$n("posAmount")[i].value,
				mainWeight:$n("mainWeight")[i].value,
				isDblLabel:$n("isDblLabel")[i].value,
				isMutiPart:$n("isMutiPart")[i].value,
				isPsale:$n("isConsign")[i].value,
				posCost:$n("realTotalCost")[i].value,
				inivCost:$n("inivCost")[i].value,
				partWeight:$n("partWeight")[i].value,
				colorId	: $n("colorId")[i].value,
				mainShapeId:$n("mainShapeId")[i].value,
				newVal:jQuery($n("newVal")[i]).attr("checked")?"1":"0"
				};
		oldItemList[i] = oldItem;
	}
	return oldItemList;
}
/**
 * 取新饰品行表数据
 */
function getNewLinesList() {
	var newRows = $("tbl").rows.length;
	var newItemList = [];
	for (var i=0; i<newRows; i++) {
		var newItem = {
				lineid		:$n("newLineId")[i].value,
				ornaCode	:$n("newCode")[i].value,
				ornaBarcode	:$n("newBarCode")[i].value,
				grains		:$n("newStoneNum")[i].value,
				allQty		:$n("new_Weight")[i].value,
				inivCost	:$n("newInivCost")[i].value,
				analysisArangeId	:$n("newAlaysisId")[i].value
			};
		newItemList[i] = newItem;
	}
	return newItemList;
}

function getNeedChecked(){
	var newRows = $("tbl").rows.length,
		dotype = jQuery("#dotype").val();
	var analysIds = [];
	for (var i=0; i<newRows; i++) {
		var analysisArangeId = $n("newAlaysisId")[i].value;
		if(!analysIds.contains(analysisArangeId)){
			analysIds.push(analysisArangeId);
		}
	}
	if(dotype == "0" && analysIds.length > 1){
		return true;
	}
	return false;
}

function saveAndClose(){
	if(!checkForm()){
		return ;
	}
	showLayer(true);
	PackageDwr.checkWeightValid(jQuery("#oldWeight").val(), jQuery("#newWeight").val(), function(data){
		showLayer(false);
		if(data) {
			alert(data);
		} else {
			submitCloseForm();
		}
	});
}

function submitCloseForm(){
	var status = jQuery("#status").val(),
		needChecked = getNeedChecked();
	
	if(status == "1" && needChecked){
		var msg = "<font style='color:red;'>";
		msg = msg + "新饰品分析范围和原饰品不一致，需要提交审批流程";
		confirm(msg + "</font><br><br>确认要继续吗？", function(){
			confirmCloseBill( "1");
		});
	} else {
		confirm("确认要入库？", function(){
			confirmCloseBill( "");
		});
	}
}


function confirmCloseBill( needChecked){
	var oldLines = getOldItemList();
	var newLines = getNewLinesList();
	var packageHead = {
		billid		:jQuery("#billid").val(),
		billno		:jQuery("#billno").val(),
		dodate		:jQuery("#dodate").val(),
		orgId		:jQuery("#orgId").val(),
		status		:"1",
		stockId		:jQuery("#stock_id").val(),
		oldWeight	:jQuery("#oldWeight").val(),
		oldGrains	:jQuery("#oldGrains").val(),
		newWeight	:jQuery("#newWeight").val(),
		newGrains	:jQuery("#newGrains").val(),
		dotype		:jQuery("#dotype").val(),
		memo		:jQuery("#memo").val()
	};
	var deleteOIds = [jQuery("#deleteOldIds").val(), jQuery("#deleteNewIds").val()];
	showLayer(true);
	PackageDwr.saveAndClosePackage(packageHead, oldLines, newLines, deleteOIds, needChecked, function(data){
		showLayer(false);
		if(data) {
			if(data.isSuccess=="true"){
				var msg = "处理成功";
				if(needChecked == "1"){
					msg = "提交审核成功";
				}
				alert(msg, function(){
					window.location = "procPackage.vm";
				});
			} else {
				alert(data.msg);
			}
		} else {
			alert("处理失败");
		}
	});
}


function printLabel(){
	var billid = jQuery("#billid").val();
	if(billid){
		var url = ctxPath + '/stock/procPackage.vm?user_action=toShowPrint&billid='+billid;
		//showLabelPrint(url);
		return  url;
	}
	return;
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}