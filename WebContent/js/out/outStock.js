var delIds=[];
function addRow(line){
	var itemClassId=line.itemClassId;
	var ornaClassId=line.ornaClassId;
	var styleId=line.styleName;
	var alaysisId=line.alaysisCode||"";
	var ornaCode=line.ornaCode||"";
	var ornaDsc=line.ornaDsc||"";
	var unitName=line.unitName||"";
	var nowQty=line.nowQty||"0";
	var allQty=line.allQty||"0";
	var grains=line.grains||"0";
	var isPSale=line.isPSale||"0";
	var posAmount=line.posAmount||"0";
	var mainWeight=line.mainWeight||"0";
	var partWeight=line.partWeight||"0";
	var hidden="<input type='hidden' name=\"#lineid\" value=\"\">" +
		"<input type='hidden' name=\"#itemClassId\"  value=\""+itemClassId+"\">" + 
		"<input type='hidden' name=\"#ornaClassId\"  value=\""+ornaClassId+"\">" + 
		"<input type='hidden' name=\"#styleId\"  value=\""+line.styleId+"\">" + 
		"<input type='hidden' name=\"#styleOrnaClass\"  value=\""+line.styleOrnaClass+"\">" + 
		"<input type='hidden' name=\"#styleItemClass\"  value=\""+line.styleItemClass+"\">" + 
		"<input type='hidden' name=\"#styleMiddleClass\"  value=\""+line.styleMiddleClass+"\">" +
		"<input type='hidden' name=\"#alaysisId\"  value=\""+line.alaysisId+"\">" + 
		"<input type='hidden' name=\"#ornaCode\"  value=\""+ornaCode+"\">" + 
		"<input type='hidden' name=\"#ornaBarCode\"  value=\""+line.ornaBarCode+"\">" + 
		"<input type='hidden' name=\"#ornaDsc\"  value=\""+ornaDsc+"\">" + 
		"<input type='hidden' name=\"#unitId\"  value=\""+line.unitId+"\">" + 
		"<input type='hidden' name=\"#nowQty\"  value=\""+nowQty+"\">" + 
		"<input type='hidden' name=\"#allQty\"  value=\""+allQty+"\">" + 
		"<input type='hidden' name=\"#grains\"  value=\""+grains+"\">" + 
		"<input type='hidden' name=\"#mainWeight\"  value=\""+mainWeight+"\">" + 
		"<input type='hidden' name=\"#partWeight\"  value=\""+partWeight+"\">" + 
		"<input type='hidden' name=\"#isMutiPart\"  value=\""+(line.isMutiPart||0)+"\">" + 
		"<input type='hidden' name=\"#isDblLabel\"  value=\""+(line.isDblLabel||0)+"\">" + 
		"<input type='hidden' name=\"#isPsale\"  value=\""+isPSale+"\">" +
		"<input type='hidden' name=\"#posCost\"  value=\""+(line.realTotalCost||0)+"\">" + 
		"<input type='hidden' name=\"#posMoney\"  value=\""+posAmount+"\">";

	var del=hidden+"<input type='button' class='btn' value='删除' onclick='deleteLine(this)'>";
	insertRow("tbl", [ "",line.itemClassName, line.ornaClassName, "<a href='javascript:viewStyle(\""+line.styleId+"\")'>"+styleId+"</a>", alaysisId, ornaCode, ornaDsc, unitName,
	                   nowQty, allQty, grains, posAmount, mainWeight, partWeight, del ],
	                   true);
	 resetSum();
	 changeSeq("tbl");
}
function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}
function checkOrnaCode(code,barFlag){
	for(var i=0;i<$("tbl").rows.length;i++){
		var _code=barFlag?$n("#ornaBarCode")[i].value:$n("#ornaCode")[i].value;
		if(code==_code)
			return true;
	}
	return false;
}
function resetSum(){
	var sumCount=sumWeight=sumGrains=sumMoney=saleMoney=0;
	jQuery("#stockId").attr("disabled",false);
	for(var i=0;i<$("tbl").rows.length;i++){
		sumCount+=1;
		sumWeight=floatAdd(sumWeight, $n("#allQty")[i].value||0);
		sumGrains=floatAdd(sumGrains, $n("#grains")[i].value||0);
		sumMoney=floatAdd(sumMoney, $n("#posCost")[i].value||0);
		saleMoney=floatAdd(saleMoney, $n("#posMoney")[i].value||0);
		jQuery("#stockId").attr("disabled",true);
	}
	jQuery("#sumCount").val(sumCount);
	jQuery("#sumWeight").val(sumWeight.toFixed(2));
	jQuery("#sumGrains").val(sumGrains);
	jQuery("#sumMoney").val(saleMoney.toFixed(2));
	jQuery("#sumCost").val(sumMoney.toFixed(2));
}
function deleteLine(obj,id){
	if(id)
		delIds.push(id);
	deleteRow(obj, "tbl");
	resetSum();
}
function getHeadObj(){
	var headObj = {
			billid : jQuery("#billid").val()||"",
			orgId:jQuery("#orgId").val()||"",
			billno:jQuery("#billno").val()||"",
			vendorId:jQuery("#vendorId").val()||"",
			saleMoney:jQuery("#saleMoney").val()||"",
			sumCount:jQuery("#sumCount").val()||"",
			sumWeight:jQuery("#sumWeight").val()||"",
			sumGrains:jQuery("#sumGrains").val()||"",
			sumMoney:jQuery("#sumMoney").val()||"",
			sumCost:jQuery("#sumCost").val()||"",
			status:jQuery("#status").val()||"",
			dodate:jQuery("#dodate").val()||"",
			createId:jQuery("#createId").val()||"",
			memo : jQuery("#memo").val()||"",
			stockId : jQuery("#stockId").val()
		};
	return headObj;
}
function getLines(){
	var lineList = [];
	for(var i=0;i<$("tbl").rows.length;i++){
		lineList.push({
			lineid : $n("#lineid")[i].value,
			itemClassId:$n("#itemClassId")[i].value,
			ornaClassId:$n("#ornaClassId")[i].value,
			styleItemClass:$n("#styleItemClass")[i].value,
			styleMiddleClass:$n("#styleMiddleClass")[i].value,
			styleOrnaClass:$n("#styleOrnaClass")[i].value,
			styleId:$n("#styleId")[i].value,
			alaysisId:$n("#alaysisId")[i].value,
			ornaCode:$n("#ornaCode")[i].value,
			ornaBarCode:$n("#ornaBarCode")[i].value,
			ornaDsc:$n("#ornaDsc")[i].value,
			unitId:$n("#unitId")[i].value,
			nowQty:$n("#nowQty")[i].value,
			allQty:$n("#allQty")[i].value,
			grains:$n("#grains")[i].value,
			mainWeight:$n("#mainWeight")[i].value,
			partWeight:$n("#partWeight")[i].value,
			isMutiPart:$n("#isMutiPart")[i].value,
			isDblLabel:$n("#isDblLabel")[i].value,
			isPsale:$n("#isPsale")[i].value,
			posCost:$n("#posCost")[i].value,
			posMoney:$n("#posMoney")[i].value
		});
	}
	return lineList;
}

function changeState(billId,type,message){
	confirm(message+"?", function(){
		showLayer(true);
		OutStockDwr.changeHeadStatus(billId,type, function(data){
			showLayer(false);
			if(data){
				alert(data);
			}else{
				alert("操作成功", function(){
					window.location = window.location;
				});
			}
		});
	});
}

function saveform(dotype,location,saveType){
	if($("tbl").rows.length<=0){
		alert("行信息不能为空");
		return;
	}
	var mesg="save"==saveType?'确定保存此单据':'确定审核此单据';
	if(!Validator.Validate($("frm"),3))
		return false;
	confirm(mesg+"?", function(){
			var headObj=getHeadObj();
			var lineList=getLines();
			showLayer(true);
			OutStockDwr.saveOrUpdateOutStock(dotype,saveType,headObj, lineList,delIds, function(data){
				showLayer(false);
				if(data){
					alert(data);
				}else{
					alert((saveType=='audit'?"审批":"保存")+"成功", function(){
						window.location = location+"OutStock.vm?user_action=toList";
					});
				}
			});
		});
}