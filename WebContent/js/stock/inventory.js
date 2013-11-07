/**
 * 增加数据行
 * @param data
 */
function addOldMaterActive(result, isCode) {
	var stockId = jQuery("#stockId").val();
	if(!stockId) {
		alert("请先选择盘点仓库");
		return;
	}
	if(result.isSuccess == "true"){
		checkMaterActive(result.data, isCode);
	} else {
		alert(result.msg);
	}
}

function checkMaterActive(data, isCode){
	InventoryDwr.isExistOrnaCode(data['ornaCode'], jQuery("#billid").val(), function(isExist){
		if(isExist) {
			alert("饰品已经盘点过。");
		} else {
			 // 大类
			var itemClassName = data['itemClassName'];
			 // 小类
			ornaClassName = data['ornaClassName'],
			 // 款式
			styleName = printStyle(data['styleId'], data['styleName'], data['bigGraph']),
			 // 分析范围
			alaysisName = data['alaysisName'],
			 // 饰品编码
			ornaCode = data['ornaCode'],
			 // 商品名称
			ornaDsc = data['ornaDsc'],
			 // 计量单位
			saleUnitName = data['saleUnitName'],
			 // 现有量
			nowQty = data['nowQty'],
			 // 总量
			allQty = data['allQty'],
			 // 粒数
			stoneNowNum = data['stoneNowNum'],
			 // 网店金额
			posAmount = data['posAmount'],
			 // 主石重量
			mainWeight = data['mainWeight'],
			 // 配石重量
			partWeight = data['partWeight'],
			groups = data['groups'],
			memo = "";
			if(jQuery("#memo").val()){
				memo = "<input type='text' style='width:60px;' readonly value='" + jQuery("#memo").val() + "'>";
			}
			
			var fieldList = ["", itemClassName, ornaClassName, styleName, alaysisName, ornaCode, ornaDsc, saleUnitName,
			                 nowQty, allQty, stoneNowNum, posAmount, mainWeight, partWeight, groups, memo];
			insertRow("tbl_old_mater", fieldList, true);
			
			var sumCount = jQuery("#sumCount").val();
			if(sumCount) {
				sumCount = floatAdd(sumCount, 1);
			} else {
				sumCount = 1;
			}
			jQuery("#sumCount").val(sumCount);
			// 保存到数据库
			saveData(data, isCode);
			jQuery("#memo").val("");
			changeSeq("tbl_old_mater");
		}
	});
}

function createTdElement(trElement, value) {
	tdEle = document.createElement("TD");
	tdEle.innerHTML = value;
	trElement.appendChild(tdEle);
}


/**
 * 保存
 */
function saveData(data, isCode) {
	var lines = getInventoryLine(data, isCode);
	var inventoryHead = {
		billid		:jQuery("#billid").val(),
		billno		:jQuery("#billno").val(),
		dodate		:jQuery("#dodate").val(),
		orgId		:jQuery("#orgId").val(),
		status		:"1",
		stockId		:jQuery("#stockId").val(),
		isstock		:jQuery("#isstock").val(),
		sumCount	:jQuery("#sumCount").val()
	};
	InventoryDwr.saveOrUpdateInventory(inventoryHead, lines, function(data){
		if(data) {
			if(data['isSuccess'] === "true") {
				jQuery("#billid").val(data['billid']);
				jQuery("#billno").val(data['billno']);
				jQuery("#stockId").attr("disabled", true);
			} else {
				alert(data['msg']);
			}
		} else {
			alert("系统出错，请稍后重试。");
		}
	});
}
/**
 * 取原饰品行表数据
 * @returns {Array}
 */
function getInventoryLine(data, isCode) {
	var lines = [];
	var line = {
			orgId		:data["orgId"],
			ornaCode	:data["ornaCode"],
			itemClassId	:data["itemClassId"],
			ornaClassId	:data["ornaClassId"],
			styleId		:data["styleId"],
			alaysisId	:data["alaysisId"],
			ornaCode	:data["ornaCode"],
			ornaDsc		:data["ornaDsc"],
			ornaBarcode	:data["ornaBarcode"],
			unitId		:data["saleUnitId"],
			nowQty		:data["nowQty"],
			allQty		:data["allQty"],
			grains		:data["stoneNowNum"],
			posMoney	:data["posAmount"],
			mainWeight	:data["mainWeight"],
			isDblLabel	:data["isDblLabel"],
			isMutiPart	:data["isMutiPart"],
			isPsale		:data["isConsign"],
			posCost		:data["realTotalCost"],
			partWeight	:data["partWeight"],
			iscode		:isCode?"1":"0",
			groups		:data["groups"],
			memo		:jQuery("#memo").val()
			};
	lines[0] = line;
	
	return lines;
}

// ======================================================================================================================

/**
 * 关闭单据
 */
function closeBill(){
	var billid = jQuery("#billid").val();
	if(billid) {
		confirm("确认要关闭单据？", function(){
			showLayer(true);
			InventoryDwr.closeBill(billid, function(data){
				showLayer(false);
				if(data){
					if(data['isSuccess'] == "true"){
						window.location = "procInventory.vm";
					} else {
						alert(data['msg']);
					}
				}else {
					alert("关闭失败");
				}
			});
		});
	} else {
		alert("关闭异常");
	}
}

function openBill(){
	var billid = jQuery("#billid").val();
	if(billid) {
		confirm("确认要开启单据？", function(){
			showLayer(true);
			InventoryDwr.openBill(billid, function(data){
				showLayer(false);
				if(data){
					if(data['isSuccess'] == "true"){
						window.location = "procInventory.vm";
					} else {
						alert(data['msg']);
					}
				}else {
					alert("开启失败");
				}
			});
		});
	} else {
		alert("开启异常");
	}
}

function checkOrnaCode(){
	var isstock = jQuery("#isstock").val();
	if(isstock){
		jQuery("#barCode_in").attr("disabled", false);
		jQuery("#ornaCode_in").attr("disabled", false);
	} else {
		jQuery("#barCode_in").attr("disabled", true);
		jQuery("#ornaCode_in").attr("disabled", true);
	}
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}