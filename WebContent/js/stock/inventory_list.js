
function toEditPage(obj, billid){
	window.location = "procInventory.vm?user_action=toEdit&billid=" + billid;
}
function toShowPage(billid){
	window.location = "procInventory.vm?user_action=toShow&billid=" + billid;
}
function deleteInventory(obj, billid){
	var rowidx = obj.parentNode.parentNode.rowIndex;
	var status = $n("status_list")[rowidx-1].value;
	if(status && status == "1"){
    	confirm("确定删除?", function(){
    		window.location = "procInventory.vm?user_action=delete&billid=" + billid;
    	});
	} else {
		alert("不允许删除");
	}
}

function changeIsmain(chbox, billid, orgId, stockId) {
	showLayer(true);
	InventoryDwr.changeIsmain(billid, orgId, stockId,  chbox.checked, function(data){
		showLayer(false);
		if(data['isSuccess'] === "false") {
			alert(data['msg']);
			chbox.checked = !chbox.checked;
		} else {
			var rowLen = chbox.parentNode.parentNode.rowIndex;
			changeStock(rowLen-1);
		}
	});
}


/**
 * 生成差异
 */
function createDiffBill(billid, billno) {
	showLayer(true);
	InventoryDwr.createDiffBill(billid, function(data){
		showLayer(false);
		if(data['isSuccess'] == "true") {
			window.location = "procInventory.vm?user_action=showDiffBill&sourceNo="+billno;
		} else {
			alert(data['msg']);
		}
	});
} 

function showDiffBill(billno) {
	window.location = "procInventory.vm?user_action=showDiffBill&sourceNo="+billno;
}


function showAllBill(obj) {
	if(obj.checked){
		jQuery("#status").val("all");
	} else {
		jQuery("#status").val("");
	}
	jQuery("#frm").submit();
}

function setValues(){
	var rowLen = $("tbl").rows.length;
	for(var i=0; i<rowLen; i++){
		changeStock(i);
	}
}

function changeStock(idx){
	var ismain = $n("ismain")[idx].checked,
	billid = $n("billid")[idx].value,
	billno = $n("billno_list")[idx].value,
	stockName = $n("stockName_list")[idx].value,
	status = $n("status_list")[idx].value;
	if(ismain && status != "8" && curOrgId == $n("status_orgId")[idx].value){
		$n("showDiff")[idx].innerHTML = "<a   href='javascript:createDiffBill(\"" + billid + "\", \"" + billno + "\")'>" + stockName + "</a>";
	} else {
		$n("showDiff")[idx].innerHTML = stockName;
	}
}
function reset(){
	jQuery("#billno").val("");
	jQuery("#startDate").val("");
	jQuery("#endDate").val("");
	jQuery("#stockId").val("");
	jQuery("#status").val("");
	jQuery("#createName").val("");
	jQuery("#createId").val("");
}


/**
 * 合并单据
 */
function mergeBills() {
	var rowLen = $("tbl").rows.length;
	var billids = "";
	var mainBillCount = 0;
	var isAllSaveStatus = true;
	var stockIdList = [],
		rows = 0;
	for(var i=0; i< rowLen; i++) {
		var orgId = $n("status_orgId")[i].value;
		var checked = $n("chk")[i].checked;
		var stockId = $n("stockId_list")[i].value;
		if(checked) {
			if(orgId != curOrgId){
				alert("请选择当前组织下的单据进行合并");
				return ;
			}
			var ismain = "0";
			if($n("ismain")[i].checked) {
				mainBillCount = mainBillCount + 1;
				ismain = "1";
			}
			if(billids) {
				billids = billids + ";" + $n("billid")[i].value + ":" + ismain;
			} else {
				billids = $n("billid")[i].value + ":" + ismain;
			}
			if($n("status_list")[i].value != "1") {
				isAllSaveStatus = false;
				break;
			}
			if(!stockIdList.contains(stockId)){
				stockIdList.push(stockId);
			}
			rows++;
		}
	}
	if(!isAllSaveStatus) {
		alert("只允许合并状态为 ‘保存’的单据");
		return;
	}
	if(stockIdList.length >1){
		alert("不同子库的盘点单，不允许合并");
		return;
	}
	if(rows <=1){
		alert("请选择多张单据进行合并");
		return ;
	}
	if(billids) {
		if(mainBillCount ==0) {
    		alert("单据合并时  必须包括一张主单。");
    	} else if(mainBillCount > 1){
    		alert("合并单据，只能包括一张主单。");
    	} else {
    		confirm("确定要合并吗？", function(){
    			showLayer(true);
    			InventoryDwr.mergeBills(billids, function(data){
    				showLayer(false);
    				if(data['isSuccess'] == "true") {
    					alert("合并成功", function(){
    						window.location = "procInventory.vm?";
    					});
    				} else {
    					alert(data['msg']);
    				}
    			});
    		});
    	}
	} else {
		alert("尚未选择要合并的单据");
	}
}

function showSelectUserWin(){
	selectEmp(function(idArr, nameArr){
			jQuery("#createId").val(idArr);
			jQuery("#createName").val(nameArr);
		}, 
		null, null, false, jQuery("#createId").val(), "getEmpByOrgId", {orgId:jQuery("#orgId").val()});
}