// 手工输入饰品编码出发的方法
function loadOldItemByOrnaCode(){
	var stockId = jQuery("#stock_id").val();
	if(stockId){
		var ornaCode = jQuery("#ornaCode_in").val();
		if(ornaCode){
			BaseChangeDwr.getMaterActiveByOrnaCode(ornaCode, jQuery("#orgId").val(), stockId, function(result){
				if (result) {
					if(result.isSuccess == "true"){
						addChangeOldMaterActive(result.data);
					} else {
						alert(result.msg);
					}
		    	} else {
		    		alert("饰品不存在");
		    	}
			});
			jQuery("#ornaCode_in").val("");
		}
	} else {
		alert("请先选择饰品仓库");
	}
	
}
// 扫描枪 扫描饰品出发的方法
function loadOldItemByBarCode(){
	var stockId = jQuery("#stock_id").val();
	if(stockId){
		var barCode = jQuery("#barCode_in").val();
		if(barCode){
			BaseChangeDwr.getMaterActiveByBarCode(barCode, jQuery("#orgId").val(), stockId, function(result){
				if (result) {
					if(result.isSuccess == "true"){
						addChangeOldMaterActive(result.data);
					} else {
						alert(result.msg);
					}
		    	} else {
		    		alert("饰品不存在");
		    	}
			});
			jQuery("#barCode_in").val("");
		}
	} else {
		alert("请先选择饰品仓库");
	}
}

function baseDeleteBill(url){
	var billid = jQuery("#billid").val();
	confirm("确定要作废?", function(){
		showLayer(true);
		BaseChangeDwr.deleteChangeHead(billid, function(data) {
			showLayer(false);
			if(data) {
				alert(data);
			} else {
				alert("作废成功！", function(){
					window.location = url;
				});
			}
		});
	});
}


/**
 * 关闭单据
 */
function baseCloseBill(url){
	var billid = jQuery("#billid").val();
	if(billid) {
		confirm("确认要关闭单据？", function(){
			showLayer(true);
			BaseChangeDwr.closeChangeHead(billid, function(msg){
				showLayer(false);
				if(msg) {
					alert(msg);
				} else {
					alert("单据关闭成功", function(){
						window.location = url;
					});
				}
			});
		});
	} else {
		alert("关闭异常");
	}
}

/**
 * 增加数据行
 * @param data
 */
function addChangeOldMaterActive(data) {
	if(!checkChangeType(data)) return ;
	var isConsign = data.isConsign;
	//alert(isConsign);
	if(isConsign && (isConsign == "1" || isConsign == 1)){
		confirm("<div style='color:red;'>饰品是代销饰品，确定要继续吗？</div>", function(){
			addLine(data);
		});
	} else {
		addLine(data);
	}
}

function addLine(data){
	var itemClassName = data['itemClassName'],
		 // 小类
		ornaClassName =  data['ornaClassName'],
		 // 款式
		styleName = printStyle(data['styleId'], data['styleName'], data['bigGraph']);
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
		realTotalCost = data['realTotalCost'],
		 // 主石重量
		mainWeight = data['mainWeight'],
		 // 配石重量
		partWeight = data['partWeight'],
		// 删除按钮
		inner_HTML = "<input type='button' value='删除' style='width:60px;' onclick='deleteMater(this,\""+data['ornaCode']+"\")'/>" +
					//"<input type='hidden' name='oldRowId' value='"+ rowNum +"' />" +
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
					"<input type='hidden' name='allQty' value='"+ (data['allQty']?data['allQty']:'') +"' />" + 
					"<input type='hidden' name='stoneNowNum' value='"+ (data['stoneNowNum']?data['stoneNowNum']:"") +"' />" +
					"<input type='hidden' name='posAmount' value='"+ (data['posAmount']?data['posAmount']:"") +"' />" + 
					"<input type='hidden' name='mainWeight' value='"+ (data['mainWeight']?data['mainWeight']:"") +"' />" + 
					"<input type='hidden' name='partWeight' value='"+ (data['partWeight']?data['partWeight']:"") +"' />" +
					"<input type='hidden' name='isMutiPart' value='"+ (data['isMutiPart']?data['isMutiPart']:"") +"' />" +
					"<input type='hidden' name='isDblLabel' value='"+ (data['isDblLabel']?data['isDblLabel']:"") +"' />" +
					"<input type='hidden' name='isConsign' value='"+ (data['isConsign']?data['isConsign']:"") +"' />" +
					"<input type='hidden' name='stockId' value='"+ (data['stockId']?data['stockId']:"") +"' />" +
					"<input type='hidden' name='posCost' value='"+ (data['realTotalCost']?data['realTotalCost']:"") +"' />" +
					"<input type='hidden' name='oldLineid' value='' />"  ;
	var fieldList = ["", itemClassName, ornaClassName, styleName, alaysisName, ornaCode, ornaDsc, saleUnitName, nowQty, allQty, stoneNowNum, 
	                 posAmount, realTotalCost, mainWeight,partWeight, inner_HTML ];
	insertRow("tbl_old_mater", fieldList, true);
	
	changeValues();
	changeSeq("tbl_old_mater");
}

function createTdElement(trElement, value) {
	tdEle = document.createElement("TD");
	tdEle.innerHTML = value;
	trElement.appendChild(tdEle);
}

function changeValues() {
	// ====================== 原重量 和 原粒数==============
	var rowLen = $("tbl_old_mater").rows.length;
	var oWeight = 0;
	var oGrains = 0;
	var oMoney = 0;
	var count = 0;
	var posCost = 0;
	for(var i=0; i<rowLen; i++) {
		var allQty = $n("allQty")[i].value;
		if(allQty){
			oWeight = floatAdd(oWeight , allQty);
		}
		var stoneNowNum = $n("stoneNowNum")[i].value;
		if(stoneNowNum) {
			oGrains = floatAdd(oGrains , stoneNowNum);
		}
		var posMoney = $n("posAmount")[i].value;
		if(posMoney){
			oMoney = floatAdd(oMoney , posMoney);
		}
		var cost = $n("posCost")[i].value;
		if(cost){
			posCost = floatAdd(posCost , cost);
		}
		count++;
	}
	$("sumWeight").value = oWeight>0?oWeight+"":"";
	$("sumGrains").value = oGrains>0?oGrains+"":"";
	$("sumMoney").value = oMoney>0?oMoney+"":"";
	$("sumCount").value = count;
	$("sumCost").value = posCost;
	if(rowLen > 0){
		jQuery("#stock_id").attr("disabled", true);
	} else {
		jQuery("#stock_id").attr("disabled", false);
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

		changeValues();
		changeSeq("tbl_old_mater");
	});
}

function checkChangeType(materData) {
	
	if (checkOldMaterExist(materData['ornaCode'])) {
		return false;
	}
	//return checkPackage(materData);
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


function showHandover(){
	var _iframeId = "procChangeMiniIframe";
	var options = {
		title : '其他交接单',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 900,
		height : 500,
		okBtnName : '关闭',
		//cancelBtnName : '',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/stock/handover.vm?user_action=miniHandover&billid='+jQuery("#billid").val() + "&billCode=" + jQuery("#billCode").val();
	jQuery.weeboxs.open(url, options);
}

function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}