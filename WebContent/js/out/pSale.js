var delIds=[];
function addRow(line){
	if(line.isPSale != "1"){
		alert("当前饰品不为代销饰品，不能添加");
		return ;
	}
	var itemClassId=line.itemClassId;
	var ornaClassId=line.ornaClassId;
	var styleId = line.styleId||"";
	var style=printStyle(line.styleId, line.styleName);
	var alaysis=line.alaysisCode||"";
	var ornaCode=line.ornaCode||"";
	var ornaDsc=line.ornaDsc||"";
	var unitName=line.unitName||"";
	var nowQty=line.nowQty||"";
	var allQty=line.allQty||"";
	var grains=line.grains||"";
	var posAmount=line.posAmount||"";
	var mainWeight=line.mainWeight||"";
	var partWeight=line.partWeight||"";
	
	var styleOrnaClass = line.styleOrnaClass||"",
		styleItemClass = line.styleItemClass||"",
		styleMiddleClass = line.styleMiddleClass||"",
		alaysisId = line.alaysisId||"";
	
	var hidden="<input type='hidden' name=\"#lineId\" value=\"\">" +
		"<input type='hidden' name=\"#itemClassId\"  value=\""+itemClassId+"\">" + 
		"<input type='hidden' name=\"#ornaClassId\"  value=\""+ornaClassId+"\">" + 
		"<input type='hidden' name=\"#styleId\"  value=\""+styleId+"\">" + 
		"<input type='hidden' name=\"#styleOrnaClass\"  value=\""+styleOrnaClass+"\">" + 
		"<input type='hidden' name=\"#styleItemClass\"  value=\""+styleItemClass+"\">" + 
		"<input type='hidden' name=\"#styleMiddleClass\"  value=\""+styleMiddleClass+"\">" + 
		"<input type='hidden' name=\"#alaysisId\"  value=\""+alaysisId+"\">" + 
		"<input type='hidden' name=\"#ornaCode\"  value=\""+ornaCode+"\">" + 
		"<input type='hidden' name=\"#ornaBarCode\"  value=\""+line.ornaBarCode+"\">" + 
		"<input type='hidden' name=\"#ornaDsc\"  value=\""+ornaDsc+"\">" + 
		"<input type='hidden' name=\"#unitId\"  value=\""+line.unitId+"\">" + 
		"<input type='hidden' name=\"#nowQty\"  value=\""+(nowQty||0)+"\">" + 
		"<input type='hidden' name=\"#allQty\"  value=\""+(allQty||0)+"\">" + 
		"<input type='hidden' name=\"#grains\"  value=\""+(grains||0)+"\">" + 
		"<input type='hidden' name=\"#mainWeight\"  value=\""+(mainWeight||0)+"\">" + 
		"<input type='hidden' name=\"#partWeight\"  value=\""+(partWeight||0)+"\">" + 
		"<input type='hidden' name=\"#isMutiPart\"  value=\""+(line.isMutiPart||0)+"\">" + 
		"<input type='hidden' name=\"#isDblLabel\"  value=\""+(line.isDblLabel||0)+"\">" + 
		"<input type='hidden' name=\"#posCost\"  value=\""+(line.realTotalCost||0)+"\">" + 
		"<input type='hidden' name=\"#isPSale\"  value=\""+(line.isPSale||0)+"\">" + 
		"<input type='hidden' name=\"#posMoney\"  value=\""+(posAmount||0)+"\">";

	var del=hidden+"<input type='button' class='btn' value='删除' onclick='deleteLine(this)'>";
	insertRow("tbl", [ "",line.itemClassName, line.ornaClassName, style, alaysis, ornaCode, ornaDsc, unitName,
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
function checkVendorSelect(){
	var disabled=$("tbl").rows.length>0;
	document.getElementById("vendorId").disabled=disabled;
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
		sumWeight = floatAdd(sumWeight, $n("#allQty")[i].value||0);
		sumGrains = floatAdd(sumGrains, $n("#grains")[i].value||0);
		sumMoney = floatAdd(sumMoney, $n("#posCost")[i].value||0);
		saleMoney = floatAdd(saleMoney, $n("#posMoney")[i].value||0);
		
		jQuery("#stockId").attr("disabled",true);
	}
	//info(saleMoney);
	//info(new Number(saleMoney*1.17).toFixed(6));
	//info(jQuery("#sumMoney").val());
	jQuery("#sumCount").val(sumCount);
	jQuery("#sumWeight").val(sumWeight.toFixed(6));
	jQuery("#sumGrains").val(sumGrains);
	jQuery("#sumMoney").val(new Number(saleMoney*1.17).toFixed(6));
	jQuery("#sumCost").val(sumMoney.toFixed(6));
	
	
	checkVendorSelect();
}
function deleteLine(obj,id){
	if(id)
		delIds.push(id);
	deleteRow(obj, "tbl");
	resetSum();
}

function saveform(state){
	if($("tbl").rows.length<=0){
		alert("行信息不能为空");
		return;
	}
	if(!Validator.Validate($("frm"),3))
		return false;
	var msg=state==4?"确认审核此单据?":"确定保存?";
	confirm(msg, function(){
		var headObj = {
				headId : jQuery("#headId").val()||"",
				orgId:jQuery("#orgId").val()||"",
				billNo:jQuery("#billNo").val()||"",
				vendorId:jQuery("#vendorId").val()||"",
				saleMoney:jQuery("#saleMoney").val()||"",
				sumCount:jQuery("#sumCount").val()||"",
				sumWeight:jQuery("#sumWeight").val()||"",
				sumGrains:jQuery("#sumGrains").val()||"",
				sumMoney:jQuery("#sumMoney").val()||"",
				sumCost:jQuery("#sumCost").val()||"",
				status:jQuery("#status").val()||"",
				createDate:jQuery("#createDate").val()||"",
				createId:jQuery("#createId").val()||"",
				doDate:jQuery("#doDate").val()||"",
				doType:"1",
				status:state,
				memo : jQuery("#memo").val()||"",
				stockId : jQuery("#stockId").val()
			};
		var lineList = [];
		for(var i=0;i<$("tbl").rows.length;i++){
			lineList.push({
				lineId : $n("#lineId")[i].value,
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
				posCost:$n("#posCost")[i].value,
				isPSale:$n("#isPSale")[i].value,
				posMoney:$n("#posMoney")[i].value
			});
		}
		showLayer(true);
		OutVendorDwr.saveOrUpdatePubSale(headObj, lineList,delIds, function(data){
			showLayer(false);
			if(data){
				alert(data);
			}else{
				alert((state==4?"审批":"保存")+"成功", function(){
					window.location = "outVendor.vm?user_action=toPSaleList";
				});
			}
		});
	});
}

function changeState(headId,status,message){
	if(checkBtn(status))return;
	confirm(message+"?", function(){
		showLayer(true);
		OutVendorDwr.changeHeadStatus(headId,status, function(errMsg){
			showLayer(false);
			if(errMsg){
				alert(errMsg,function(){
					window.location = window.location;
				});
			}else{
				alert("操作成功", function(){
        			window.location = window.location;
				});
			}
		});
	});
}

function checkBtn(status){
	var state=document.getElementById("status").value;
	if(status=="4"&&state!="1"){
		alert("此单据不能审核");
		return true;
	}else if(status=="8"&&state!="5"){
		alert("此单据不能关闭");
		return true;
	}else if(status=="0"&&state!="8"){
		alert("此单据不能作废");
		return true;
	}
}
function show_all(ctl){
	jQuery("#ip_showAll").val(ctl.checked?"1":"0");
	jQuery("#frm").submit();
}


function cashCharge(){
	var billid = jQuery("#headId").val(),
		saleMoney = jQuery("#sumMoney").val();
	if(saleMoney){
		if(!isDecimal(saleMoney)){
			alert("含税金额必须是数字格式");
			return;
		}
		if(floatSub(saleMoney, 0)<0){
			alert("含税金额必须大于等于0的数");
			return;
		}
	}
	if(billid){
		confirm("确定要修改含税金额？", function(){
			showLayer(true);
			OutVendorDwr.cashCharge(billid, saleMoney, function(data){
				showLayer(false);
				if(data){
					if(data.isSuccess == "true"){
						alert("修改成功", function(){
							window.location.reload();
						});
					} else {
						alert(data.msg);
					}
				} else {
					alert("修改失败");
				}
			});
		});
	} else {
		alert("单据不是关闭状态或者单据已经不存在");
	}
}