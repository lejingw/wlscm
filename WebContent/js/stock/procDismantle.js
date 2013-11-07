//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var oLen = $("tbl_old_mater").rows.length;
	if (oLen==0) {
		alert("尚未添加原饰品！！！");
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
		var oldLines = getOldItemList();
		var changeHead = getHeadData();
		var deleteOIds = jQuery("#deleteOldIds").val();
		showLayer(true);
		ProcDismantleDwr.saveOrUpdateDismantle(changeHead, oldLines, deleteOIds, function(msg){
			showLayer(false);
			if(msg) {
				alert(msg);
			} else {
				alert("保存成功", function(){
					window.location = "procDismantle.vm";
				});
			}
		});
	});
}

function getHeadData(){
	var changeHead = {
			billid		:jQuery("#billid").val(),
			billno		:jQuery("#billno").val(),
			dodate		:jQuery("#dodate").val(),
			orgId		:jQuery("#orgId").val(),
			status		:"1",
			dotype		:"2",
			stockId		:jQuery("#stock_id").val(),
			sumCount	:jQuery("#sumCount").val(),
			sumWeight	:jQuery("#sumWeight").val(),
			sumGrains	:jQuery("#sumGrains").val(),
			sumMoney	:jQuery("#sumMoney").val(),
			sumCost		:jQuery("#sumCost").val(),
			memo		:jQuery("#memo").val()
		};
	return changeHead;
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
				partWeight:$n("partWeight")[i].value,
				posCost:$n("posCost")[i].value
			};
		oldItemList[i] = oldItem;
	}
	return oldItemList;
}

function checkBill() {
	if(!checkForm()){
		return ;
	}
	confirm("确定要提交审核？", function(){
		var oldLines = getOldItemList();
		var changeHead = getHeadData();
		var deleteOIds = jQuery("#deleteOldIds").val();
		showLayer(true);
		ProcDismantleDwr.saveAndCheckDismantle(changeHead, oldLines, deleteOIds, function(data){
			showLayer(false);
			if(data){
				alert(data);
			} else {
				alert("提交审核成功！", function(){
					window.location = "procDismantle.vm";
				});
			}
    	});		
	});
}

/**
 * 作废单据
 */
function deleteBill(){
	var url = "procDismantle.vm";
	baseDeleteBill(url);
}

/**
 * 关闭单据
 */
function closeBill(){
	var url = "procDismantle.vm";
	baseCloseBill(url);
}