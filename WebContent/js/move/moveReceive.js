function addMoveRow(info){
	var chkHtml = "<input type='checkbox' name='chk'/>"
		+ "<input type='hidden' name='lineid' value='" + info.lineid + "'/>"
		+ "<input type='hidden' name='ornaCode' value='" + info.ornaCode + "'/>"
		+ "<input type='hidden' name='barCode' value='" + info.barCode + "'/>"
		+ "<input type='hidden' name='allWeight' value='" + (info.allWeight?info.allWeight:"") + "'/>"
		+ "<input type='hidden' name='posCost' value='" + (info.posCost?info.posCost:"") + "'/>"
		+ "<input type='hidden' name='posAmount' value='" + (info.posAmount?info.posAmount:"") + "'/>"
		+ "<input type='hidden' name='grains' value='" + (info.grains?info.grains:"") + "'/>";
	var ornaCodeHtml = info.ornaCode;
	var ornaDscHtml = info.ornaDsc;
//	var inGroupNoHtml = info.inGroupNo;
	var inGroupNoHtml = "<select name='inGroupNo' style='width:60px;' onchange='changeInGroupNo(this)'></select>";
	var itemClassHtml = info.itemClassName;
	var ornaClassHtml = info.ornaClassName;
	var analysisNameHtml = info.analysisName;
	var styleNameHtml = printStyle(info.styleId, info.styleName, info.bigGraph);
	var unitHtml = info.unitName;
	var allWeightHtml = info.allWeight;
	var nowQtyHtml = info.nowQty;
	var grainsHtml = info.grains;
	var posAmountHtml = info.posAmount;
	var mainWeight = info.mainWeight;
	var partWeight = info.partWeight;
	var statusHtml = "已接收";
	var deleteHtml = "<input type='button' class='btn' value='删除' onclick='deleteMoveRow(this)'/>";
	var htmlArr = [chkHtml, ornaCodeHtml, ornaDscHtml, inGroupNoHtml, itemClassHtml, ornaClassHtml, analysisNameHtml, styleNameHtml, unitHtml, 
	               allWeightHtml, nowQtyHtml, grainsHtml, posAmountHtml, mainWeight, partWeight, statusHtml, deleteHtml];
	insertRow('tbl', htmlArr, true);
	startSync();
	DictDwr.getDictsForSlt("group", function(data){
		addOptions2("inGroupNo", 0, data, null, null, true, true, info.inGroupNo);
	});
	endSync();
}
function changeInGroupNo(obj){
	var rowIndex = obj.parentNode.parentNode.rowIndex - 1;
	var lineid = $n("lineid")[rowIndex].value;
	var inGroupNo = $n("inGroupNo")[rowIndex].value;
	showLayer(true);
	MoveReceiveDwr.changeInGroupNo(lineid, inGroupNo, function(data){
		showLayer(false);
		alert(data?data:"修改调入柜组成功");
	});
}
function deleteMoveRow(obj){
	confirm("确定删除?", function(){		
		var index = obj.parentNode.parentNode.rowIndex - 1;
		MoveReceiveDwr.deleteMoveReceiveLine($n("lineid")[index].value, function(data){			
			deleteRow(obj, 'tbl');
			calcSum(false);
		});
	});
}
/**
 * 根据饰品编码获取饰品信息
 */
function getMoveLineByOrnaCode(){
	getMoveLineInfo(jQuery("#ornaCode_in").val(), true);
}
/**
 * 根据饰品条码获取饰品信息
 */
function getMoveLineByBarCode(){
	getMoveLineInfo(jQuery("#barCode_in").val(), false);
}
function getMoveLineInfo(code, ornaFlag){
	if(!checkOrnaCodeExists(code, ornaFlag))
		return;
	MoveReceiveDwr.getMoveLineInfo(jQuery("#headid").val(), code, ornaFlag, jQuery("#billType").val(), jQuery("#jmFlag").val(), function(data){
		addMoveRow(data);
		calcSum(true);
		if(ornaFlag){
			jQuery("#ornaCode_in").val("");
		}else{
			jQuery("#barCode_in").val("");
		}
	});
}
function checkOrnaCodeExists(code, ornaFlag){
	for(var i=0;i<$n("ornaCode").length;i++){
		if(ornaFlag && $n("ornaCode")[i].value == code){
			alert("第"+(i+1)+"行 饰品编码[" + code + "]已经存在");
			return false;
		}
		if(!ornaFlag && $n("barCode")[i].value == code){
			alert("第"+(i+1)+"行 饰品条码[" + code + "]已经存在");
			return false;
		}
	}
	return true;
}
function calcSum(addFlag){
	jQuery("#receCount").val(parseInt(jQuery("#receCount").val()) + (addFlag?1:-1));//已接收件数
	jQuery("#noreceCount").val(parseInt(jQuery("#noreceCount").val()) + (addFlag?-1:1));//未接收件数
	changeButtons();
}
function nullToZero(val){
	if(!val)
		return "0";
	return val;
}