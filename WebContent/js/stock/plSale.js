// 手工输入饰品编码出发的方法
function loadOldItemByOrnaCode(){
	var stockId = jQuery("#stock_id").val();
	if(stockId){
		var vendorId = jQuery("#vendorId").val();
		if(!vendorId){
			alert("请选择供应商");
			return;
		}
		var ornaCode = jQuery("#ornaCode_in").val();
		if(ornaCode){
			PlSaleDwr.getMaterActiveByOrnaCode(ornaCode, jQuery("#orgId").val(), stockId, vendorId, function(result){
				if (result) {
					if(result.isSuccess == "true"){
                        checkOrnaCodeExists(result.data);
						//addLine(result.data);
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
		var vendorId = jQuery("#vendorId").val();
		if(!vendorId){
			alert("请选择供应商");
			return;
		}
		var barCode = jQuery("#barCode_in").val();
		if(barCode){
			PlSaleDwr.getMaterActiveByBarCode(barCode, jQuery("#orgId").val(), stockId, vendorId, function(result){
				if (result) {
					if(result.isSuccess == "true"){
                        checkOrnaCodeExists(result.data);
						//addLine(result.data);
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

/**
 * 判断商品编码是否已经存在列表中
 * @param ornaCode
 * @returns {Boolean}
 */
function checkOldMaterExist(ornaCode) {
	var rowLen = $("tbl").rows.length;
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

function checkOrnaCodeExists(data) {
    PlSaleDwr.isExistsOrnaCode(jQuery("#billid").val(), data.ornaCode, function(msg) {
        if(msg) {
            alert(msg);
        } else {
            addLine(data);
        }
    });
}

function addLine(data){
	if(checkOldMaterExist(data.ornaCode)) return ;
	var ornaCode 		= checNull(data.ornaCode),
		ornaDsc 		= checNull(data.ornaDsc),
		itemClassName 	= checNull(data.itemClassName),
		ornaClassName 	= checNull(data.ornaClassName),
		styleitemclass 	= checNull(data.styleitemclassName),
		stylemiddleclass= checNull(data.stylemiddleclassName),
		styleornaclass 	= checNull(data.styleornaclassName),
		styleName 		= printStyle(checNull(data.styleId), checNull(data.styleName), checNull(data.bigGraph)),
		alaysisName 	= checNull(data.alaysisName),
		unitName 		= checNull(data.saleUnitName),
		nowQty 			= checNull(data.nowQty),
		allQty 			= checNull(data.allQty),
		grains 			= checNull(data.stoneNowNum),
		mainWeight 		= checNull(data.mainWeight),
		partWeight 		= checNull(data.partWeight),
		isMutiPart 		= checNull(data.isMutiPart)=="1"?"是":"否",
		isDblLabel 		= checNull(data.isDblLabel)=="1"?"是":"否",
		posMoney 		= checNull(data.posAmount),
		orgId 		    = checNull(data.orgId),
		orgName 		= checNull(data.orgName),
		outFlag 		= checNull(data.outFlag),
        outFlagName 	= checNull(data.outFlagName),

		// 删除按钮
		inner_HTML = "<input type='button' value='删除' style='width:60px;' onclick='deleteLine(this,\"\")'/>" +
					"<input type='hidden' name='ornaCode' value='"+ checNull(data.ornaCode) +"' />" +
					"<input type='hidden' name='ornaBarcode' value='"+ checNull(data.ornaBarcode) +"' />" +
					"<input type='hidden' name='ornaDsc' value='"+ checNull(data.ornaDsc) +"' />" +
					"<input type='hidden' name='itemClassId' value='"+ checNull(data.itemClassId) +"' />" +
					"<input type='hidden' name='ornaClassId' value='"+ checNull(data.ornaClassId) +"' />" +
					"<input type='hidden' name='styleitemclass' value='"+ checNull(data.styleitemclass) +"' />" +
					"<input type='hidden' name='stylemiddleclass' value='"+ checNull(data.stylemiddleclass) +"' />" +
					"<input type='hidden' name='styleornaclass' value='"+ checNull(data.styleornaclass) +"' />" +
					"<input type='hidden' name='styleId' value='"+ checNull(data.styleId) +"' />" +
					"<input type='hidden' name='alaysisId' value='"+ checNull(data.alaysisId) +"' />" +
					"<input type='hidden' name='unitId' value='"+ checNull(data.saleUnitId) +"' />" +
					"<input type='hidden' name='nowQty' value='"+ checNull(data.nowQty) +"' />" +
					"<input type='hidden' name='allQty' value='"+ checNull(data.allQty) +"' />" +
					"<input type='hidden' name='grains' value='"+ checNull(data.stoneNowNum) +"' />" +
					"<input type='hidden' name='mainWeight' value='"+ checNull(data.mainWeight) +"' />" +
					"<input type='hidden' name='partWeight' value='"+ checNull(data.partWeight) +"' />" +
					"<input type='hidden' name='isMutiPart' value='"+ checNull(data.isMutiPart) +"' />" +
					"<input type='hidden' name='isDblLabel' value='"+ checNull(data.isDblLabel) +"' />" +
					"<input type='hidden' name='posMoney' value='"+ checNull(data.posAmount) +"' />" +
					"<input type='hidden' name='line_orgId' value='"+ checNull(data.orgId) +"' />" +
					"<input type='hidden' name='outFlag' value='"+ checNull(data.outFlag) +"' />" +
					"<input type='hidden' name='lineid' value='' />";
	var fieldList = ["", orgName, ornaCode, ornaDsc, itemClassName, ornaClassName, styleitemclass, stylemiddleclass, styleornaclass, styleName, alaysisName,
						unitName, nowQty, allQty, grains, mainWeight, partWeight, isMutiPart, isDblLabel, posMoney, outFlagName, inner_HTML ];
	insertRow("tbl", fieldList, true);
	
	changeValues();
	changeSeq("tbl");
}

function changeValues() {
	// ====================== 原重量 和 原粒数==============
	var rowLen = $("tbl").rows.length;
	var oWeight = 0;
	var oGrains = 0;
	var oMoney = 0;
	var count = 0;
	for(var i=0; i<rowLen; i++) {
		var allQty = $n("allQty")[i].value;
		if(allQty){
			oWeight = floatAdd(oWeight , allQty);
		}
		var grains = $n("grains")[i].value;
		if(grains) {
			oGrains = floatAdd(oGrains , grains);
		}
		var posMoney = $n("posMoney")[i].value;
		if(posMoney){
			oMoney = floatAdd(oMoney , posMoney);
		}
		count++;
	}
	$("sumWeight").value = oWeight>0?oWeight+"":"0";
	$("sumGrains").value = oGrains>0?oGrains+"":"0";
	$("sumMoney").value = oMoney>0?oMoney+"":"0";
	$("sumCount").value = count;
	if(rowLen > 0){
		jQuery("#stock_id").attr("disabled", true);
		jQuery("#vendorName").attr("disabled", true);
	} else {
		jQuery("#stock_id").attr("disabled", false);
		jQuery("#vendorName").attr("disabled", true);
	}
}


function checNull(val){
	return val?val:"";
}
function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
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
		enableCheck();
		changeValues();
		changeSeq("tbl");
	});
}

function enableCheck() {
	var chLen = $("tbl").rows.length;
	if (chLen>0) {
		jQuery("#vendorName").attr("disabled", true);
		jQuery("#stock_id").attr("disabled", true);
	} else {
		jQuery("#vendorName").attr("disabled", false);
		jQuery("#stock_id").attr("disabled", false);
	}
}

function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var oLen = $("tbl").rows.length;
	if (oLen==0) {
		alert("尚未添加饰品！！！");
		return false;
	}
	return true;
}

/**
 * 保存表单
 */
function saveform() {
	if(!checkForm()){
		return ;
	}
	confirm("确定要保存？", function(){
		var lines = getItemList();
		var head = getHeadData();
		var deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		PlSaleDwr.saveOrUpdatePlSale(head, lines, deleteIds, function(msg){
			showLayer(false);
			if(msg) {
				alert(msg);
			} else {
				alert("保存成功", function(){
					window.location = "plSale.vm";
				});
			}
		});
	});
}

function checkBill(){
	if(!checkForm()){
		return ;
	}
	confirm("确认要审核？", function(){
		var lines = getItemList();
		var head = getHeadData();
		var deleteIds = jQuery("#deleteIds").val();
		showLayer(true);
		PlSaleDwr.saveAndCheckPlSale(head, lines, deleteIds, function(data){
			showLayer(false);
			if(data) {
				if(data['isSuccess'] == "true"){
					alert("审批成功", function(){
						window.location = "plSale.vm?user_action=toEdit&billid=" + data['billid'];
					});
				} else {
					alert(data['msg']);
				}
			} else {
				alert("审核失败");
			}
		});
	});
}

/**
 * 取头表数据
 */
function getHeadData(){
	var head = {
			billid		:jQuery("#billid").val(),
			billno		:jQuery("#billno").val(),
			dodate		:jQuery("#dodate").val(),
			orgId		:jQuery("#orgId").val(),
			status		:"1",
			stockId		:jQuery("#stock_id").val(),
			sumCount	:jQuery("#sumCount").val(),
			sumWeight	:jQuery("#sumWeight").val(),
			sumGrains	:jQuery("#sumGrains").val(),
			sumCost 	:jQuery("#sumCost").val(),
			sumMoney	:jQuery("#sumMoney").val(),
			sumJmmoney	:jQuery("#sumJmmoney").val(),
			vendorId	:jQuery("#vendorId").val(),
			memo		:jQuery("#memo").val()
		};
	return head;
}

/**
 * 取行表数据
 * @returns {Array}
 */
function getItemList() {
	var oldRows = $("tbl").rows.length;
	var oldItemList = [];
	for (var i=0; i<oldRows; i++) {
		var oldItem = {
				ornaCode		:$n("ornaCode")[i].value,
				ornaBarcode		:$n("ornaBarcode")[i].value,
				ornaDsc			:$n("ornaDsc")[i].value,
				itemClassId		:$n("itemClassId")[i].value,
				ornaClassId		:$n("ornaClassId")[i].value,
				styleitemclass	:$n("styleitemclass")[i].value,
				stylemiddleclass:$n("stylemiddleclass")[i].value,
				styleornaclass	:$n("styleornaclass")[i].value,
				styleId			:$n("styleId")[i].value,
				alaysisId		:$n("alaysisId")[i].value,
				unitId			:$n("unitId")[i].value,
				nowQty			:$n("nowQty")[i].value,
				allQty			:$n("allQty")[i].value,
				grains			:$n("grains")[i].value,
				mainWeight		:$n("mainWeight")[i].value,
				partWeight		:$n("partWeight")[i].value,
				isMutiPart		:$n("isMutiPart")[i].value,
				isDblLabel		:$n("isDblLabel")[i].value,
				posMoney		:$n("posMoney")[i].value,
				lineid			:$n("lineid")[i].value,
				orgId			:$n("line_orgId")[i].value,
				outFlag			:$n("outFlag")[i].value
			};
		oldItemList[i] = oldItem;
	}
	return oldItemList;
}

/**
 * 关闭单据
 */
function closeBill(){
	var billid = jQuery("#billid").val();
	if(billid) {
		confirm("确认要关闭单据？", function(){
			showLayer(true);
			PlSaleDwr.closePlSale(billid, function(msg){
				showLayer(false);
				if(msg) {
					alert(msg);
				} else {
					alert("单据关闭成功", function(){
						window.location = "plSale.vm";
					});
				}
			});
		});
	} else {
		alert("关闭异常");
	}
}

function destroyBill(){
    var billid = jQuery("#billid").val();
    if(billid) {
        confirm("确认要撤销？", function(){
            showLayer(true);
            PlSaleDwr.destroyPlSale(billid, function(msg){
                showLayer(false);
                if(msg) {
                    alert(msg);
                } else {
                    alert("撤销成功", function(){
                        window.location = "plSale.vm";
                    });
                }
            });
        });
    } else {
        alert("撤销异常");
    }
}