/**
 * 显示行表 的 子表
 * @param btn
 */
function showLineChild(btn){
	var rowIndex = btn.parentNode.parentNode.rowIndex;
	beforeShowChidren(rowIndex-2);
	var _iframeId = "lineChildrenIframe";
	var options = {
		title : '原料钻石',
		contentType : 'selector',
		iframeId : _iframeId,
		width : 418,
		height : 200,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		lineRow:rowIndex-2,
		onok : function(box) {
			if(changeChildValues(this.lineRow)){
				box.close();
			}
		},
		oncancel : function(box) {
			box.close();
		}
	};
	jQuery.weeboxs.open(jQuery("#line_children_head"), options);
	changeAnalysCount();
}

function addChildRow(){
	insertChildRow("", "", "", "");
}

function insertChildRow(childid, alaysisId, stoneNum, unitPrice){
	var childLen = $("line_children").rows.length,
		alaysisIdField = '<select name="child_alaysisId" ></select>'+
					'<input type="hidden" name="childid" value="' + childid + '"/>',
		stoneNumField = '<input type="text" name="stoneNum" value="' + stoneNum + '" onblur="checkStoneNum(this)" style="width:70px;"/>',
		unitPriceField = '<input type="text" name="unitPrice" value="' + unitPrice + '"  onblur="checkUnitPrice(this)" style="width:70px;"/>',
		deleteBtn = "<input type='button' value='' class='delrow' onclick='deleteChildRow(this)'/>";
	insertRow("line_children", [alaysisIdField, stoneNumField, unitPriceField, deleteBtn], false);
	HandoverDwr.getAnalysisList(function(data){
		addOptions2("child_alaysisId", childLen,  data, null, null, true, true, alaysisId);
	});
}

function beforeShowChidren(rowIndex){
	$("line_children").innerHTML = "";
	jQuery("#deleteChildIds").val($n("delete_child_ids")[rowIndex].value);
	var childList = $n("child_list")[rowIndex].value;
	if(childList){
		childList = jQuery.parseJSON(childList);
		jQuery.each(childList, function(idx, child){
			insertChildRow(child.childid, child.alaysisId, child.stoneNum, child.unitPrice);
		});
	}
}

function deleteChildRow(btn){
	var rowIndex = btn.parentNode.parentNode.rowIndex;
	confirm("确定要删除", function(){
		var childid = $n("childid")[rowIndex-1].value;
		var deleteChildIds = jQuery("#deleteChildIds").val();
		if(deleteChildIds) {
			jQuery("#deleteChildIds").val(deleteChildIds + ";" + childid);
		} else {
			jQuery("#deleteChildIds").val(childid);
		}
		deleteRow(btn, "line_children");
		changeAnalysCount();
	});
}

function changeChildValues(lineRow){
	var childLen = $("line_children").rows.length,
		childList = [],
		alaysisIds = [],
		isSuccess = true,
		j =0;
	for(var i=0; i< childLen ; i++){
		var alaysisId = $n("child_alaysisId")[i].value,
			stoneNum = $n("stoneNum")[i].value,
			unitPrice = $n("unitPrice")[i].value;
		if(!alaysisId){
			alert("分析范围不允许为空");
			isSuccess = false;
			break;
		}
		if(alaysisIds.contains(alaysisId)){
			alert("分析范围不允许重复出现");
			isSuccess = false;
			break;
		}
		if(!stoneNum || floatSub(stoneNum, 0) <= 0){
			alert("数量必须是大于0的数。");
			isSuccess = false;
			break;
		}
		if(!unitPrice || floatSub(unitPrice, 0) <= 0){
			alert("单价必须是大于0的数。");
			isSuccess = false;
			break;
		}
		alaysisIds.push(alaysisId);
		var child = {
			childid		: $n("childid")[i].value,
			lineid		: $n("lineid")[lineRow].value,
			alaysisId	: alaysisId,
			stoneNum	: stoneNum,
			unitPrice	: unitPrice
		};
		childList[j] = child;
		j++;
	}
	if(childLen <= 0){
		alert("尚未增加数据行。");
		isSuccess = false;
	}
	if(isSuccess){
		$n("child_list")[lineRow].value = JSON.stringify(childList);
		$n("delete_child_ids")[lineRow].value = jQuery("#deleteChildIds").val();
		jQuery("#deleteChildIds").val("");
	}
	return isSuccess;
}


function checkStoneNum(obj){
	var stoneNum = obj.value;
	var isSucc = true;
	if(stoneNum){
		if(!isDecimal(stoneNum)){
			alert("数量格式不合法");
			isSucc = false;
		} else {
			if(floatSub(stoneNum, 0) <= 0){
				alert("数量必须是大于0的数。");
				isSucc = false;
			}
		}
	}
	if(!isSucc){
		obj.value = "";
	}
	changeAnalysCount();
}

function changeAnalysCount(){
	var len = $("line_children").rows.length,
	count = 0;
	for(var i=0; i<len; i++){
		var num = $n("stoneNum")[i].value;
		if(num && isDecimal(num) && floatSub(num, 0) >0){
			count = floatAdd(count, num);
		}
	}
	jQuery("#analysCount").val(count);
}

function checkUnitPrice(obj){
	var unitPrice = obj.value;
	var isSucc = true;
	if(unitPrice){
		if(!isDecimal(unitPrice)){
			alert("单价格式不合法");
			isSucc = false;
		} else {
			if(floatSub(unitPrice, 0) <= 0){
				alert("单价必须是大于0的数。");
				isSucc = false;
			}
		}
	}
	if(!isSucc){
		obj.value = "";
	}
}


function checkForm(){
	var oLen = $("tbl").rows.length,
		itemClassArr = [];
	for(var i=0; i<oLen;i++){
		var itemClassId = $n("itemClassId")[i].value,
			unitNo = $n("unitNo")[i].value;
		if(!itemClassId){
			alert("第["+(i+1)+"]行，大类不能为空");
			return false;
		}
		if(!unitNo){
			alert("第["+(i+1)+"]行，计量单位不能为空");
			return false;
		}
		if(itemClassArr.contains(itemClassId)){
			alert("行中不允许有重复的大类");
			return false;
		} else {
			itemClassArr.push(itemClassId);
		}
		
		if(itemClassId == "156"){
			if(!$n("child_list")[i].value){
				alert("原料钻石 尚未增加分析范围行表。");
				return false;
			}
			var childList = $n("child_list")[i].value;
			if(childList){
				childList = jQuery.parseJSON(childList);
				var childCount = 0,
					hangNum =  $n("hangNum")[i].value;
				jQuery.each(childList, function(idx, child){
					childCount = floatAdd(childCount , child.stoneNum);
				});
				if(childCount != hangNum){
					alert("原料钻石分析范围 数量之和["+childCount+"] 必须等于交接数量["+hangNum+"]");
					return false;
				}
			}
		}
	}
	return true;
}

JSON.stringify = JSON.stringify || function (obj) {
    var t = typeof (obj);
    if (t != "object" || obj === null) {
        // simple data type
        if (t == "string") obj = '"'+obj+'"';
        return String(obj);
    }
    else {
        // recurse array or object
        var n, v, json = [], arr = (obj && obj.constructor == Array);
        for (n in obj) {
            v = obj[n]; t = typeof(v);
            if (t == "string") v = '"'+v+'"';
            else if (t == "object" && v !== null) v = JSON.stringify(v);
            json.push((arr ? "" : '"' + n + '":') + String(v));
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    }
};

//  ---------------------------------- 行的处理--------------------------------------
function addRow(){
	var rowLen = $("tbl").rows.length;
	var itemClass = "<select name='itemClassId' dataType='Require' value='' onchange='checkitemClass(this)'  msg='不能为空' ></select>" +
					'<input type="button" name="itemBtn" class="analyz" style="display:none;" onclick="showLineChild(this)"/>';
	var unitNo = "<select name='unitNo' dataType='Require' value='' msg='不能为空'></select>";
	
	var hangNum = "<input type='text' name='hangNum' style='width:50px;' value='' onblur='checkField()' dataType='Require' msg='不能为空'>" ;
	var goldPrice = "<input type='text' name='goldPrice' style='width:60px;' value='' onblur='checkField()' dataType='Require' msg='不能为空'>" ;
	var hangPrice = "<input type='text' name='hangPrice' style='width:60px;' value='' onblur='checkField()' dataType='Require'  msg='不能为空'>" ;
	var handCharge = "<input type='text' name='handCharge'  style='width:60px;' value='' onblur='checkField()' dataType='Require'  msg='不能为空'>";
	var handMoney = "<input type='text' name='handMoney'  style='width:60px;' value='' disabled dataType='Require'  msg='不能为空'>";
	var noNumLast = "<input type='text' name='noNumLast'  style='width:50px;' value='' disabled ' >";
	var noNumNow = "<input type='text' name='noNumNow'  style='width:50px;' value='' disabled >";
	var yesNum = "<input type='text' name='yesNum' value='' disabled style='width:50px;' >" ;
	var yesMoney = "<input type='text' name='yesMoney' disabled value='' style='width:60px;'>" ;
	var diffNum = "<input type='text' name='diffNum' disabled style='width:50px;' value='' >";
	var diffMoney = "<input type='text' name='diffMoney' disabled style='width:60px;' value='' >";
	var deleteField = "<input type='button' value='' class='delrow' onclick='deleteHandoverRow(this,\"tbl\")'/>" +
					"<input type='hidden' name='lineid' readonly value=''>" + 
					"<input type='hidden' name='oldItemClassId' readonly value=''>" +
					"<input type='hidden' name='noNumLastOld' readonly value=''>" +
					"<input type='hidden' name='noNumNowOld' readonly value=''>" +
					"<input type='hidden' name='oldUnitNo' readonly value=''>" +
					'<input type="hidden" name="oldHangNum" value="" />'+
					'<input type="hidden" name="child_list" value=""/>'+
					'<input type="hidden" name="delete_child_ids" value=""/>';
					
	insertRow("tbl",[deleteField, itemClass,unitNo, hangNum, goldPrice,hangPrice, 
	                 handCharge, handMoney, noNumLast, noNumNow, yesNum, yesMoney,  
	                 diffNum, diffMoney],false);
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions2("itemClassId", rowLen,  data, null, null, true, true);
	});
	DictDwr.getDictsForSlt('purunit', function(data){
		addOptions2("unitNo", rowLen,  data, null, null, true, true);
	});
}

function deleteHandoverRow(button, domId) {
	confirm("确定要删除", function(){
		var rowIndex = button.parentNode.parentNode.rowIndex;
		var lineid = $n("lineid")[rowIndex-2].value;
		var delIds = jQuery("#deleteLineIds").val();
		if(delIds) {
			jQuery("#deleteLineIds").val(delIds + ";" + lineid);
		} else {
			jQuery("#deleteLineIds").val(lineid);
		}
		$(domId).deleteRow(rowIndex-2);
	});
}

function checkitemClass(itemClass){
	var rowIdx = itemClass.parentNode.parentNode.rowIndex-2;
	if(itemClass.value == '156'){
		$n("itemBtn")[rowIdx].style.display = "";
	} else {
		$n("itemBtn")[rowIdx].style.display = "none";
	}
}

function addRow2(){
	var rowLen = $("tbl").rows.length;
	var itemClass = "<select name='itemClassId' dataType='Require' onchange='checkitemClass(this)' value='' msg='不能为空'></select>"+
					'<input type="button" name="itemBtn" class="analyz" style="display:none;" onclick="showLineChild(this)"/>';
	var unitNo = "<select name='unitNo' dataType='Require' value='' msg='不能为空'>" +
				"</select>";
	
	var hangNum = "<input type='text' name='hangNum' style='width:50px;' value='' onblur='checkField()' dataType='Require' msg='不能为空'>" ;
	var hangPrice = "<input type='text' name='hangPrice' style='width:60px;' value='' onblur='checkField()' dataType='Require'  msg='不能为空'>" ;
	var yesNum = "<input type='text' name='yesNum' readonly value='' style='width:50px;'>" ;
	var yesMoney = "<input type='text' name='yesMoney' readonly value='' style='width:60px;'>" ;
	var deleteField = "<input type='button' value='' class='delrow' onclick='deleteHandoverRow(this,\"tbl\")'/>" +
					"<input type='hidden' name='lineid' readonly value=''>"+
					"<input type='hidden' name='oldItemClassId' readonly value=''>"+
					'<input type="hidden" name="child_list" value=""/>'+
					"<input type='hidden' name='oldUnitNo' readonly value=''>"+
					'<input type="hidden" name="oldHangNum" value="" />'+
					"<input type='hidden' name='handCharge' value='0' />"+
					"<input type='hidden' name='goldPrice' value='0' />"+
					"<input type='hidden' name='handMoney' value='0' />"+
					'<input type="hidden" name="delete_child_ids" value=""/>';
					
	insertRow("tbl",[deleteField, itemClass,unitNo, hangNum, hangPrice, yesNum, yesMoney],false);
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions2("itemClassId", rowLen,  data, null, null, true, true);
	});
	DictDwr.getDictsForSlt('purunit', function(data){
		addOptions2("unitNo", rowLen,  data, null, null, true, true);
	});
}