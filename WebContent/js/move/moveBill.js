function addMoveRow(data){
	var chkHtml = "<input type='checkbox' name='chk'/>"
		+ "<input type='hidden' name='lineid' value=''/>"
		+ "<input type='hidden' name='ornaCode' value='" + data.ornaCode + "'/>"
		+ "<input type='hidden' name='weight' value='" + (data.weight?data.weight:"") + "'/>"
		
	var ornaCodeHtml = data.ornaCode;
	var ornaDscHtml = data.ornaDsc;
	var weightHtml = data.weight;
	var statusHtml = "保存";
	var deleteHtml = "<input type='button' name='deleteBtn' class='btn' value='删除' onclick='deleteMoveRow(this)'/>";
	var htmlArr = [chkHtml, ornaCodeHtml, ornaDscHtml, weightHtml, statusHtml, deleteHtml];
	insertRow('tbl', htmlArr, true);
}
function deleteMoveRow(obj){
	var index = obj.parentNode.parentNode.rowIndex - 1;
	if(!isNull($n("lineid")[index].value)){
		deleteOrnaCodeArr.push($n("ornaCode")[index].value);
	}
	deleteRow(obj, 'tbl');
	calcSum();
}

function getMaterActiveInfo(ornaCode){
	if(!checkOrnaCodeExists(ornaCode))
		return;
	MoveBillDwr.getMaterActiveInfo(ornaCode, jQuery("#outOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		jQuery("#ornaCode_in").val("");
	});
}
function checkOrnaCodeExists(ornaCode){
	for(var i=0;i<$n("ornaCode").length;i++){
		if($n("ornaCode")[i].value == ornaCode){
			alert("第"+(i+1)+"行 编码[" + ornaCode + "]已经存在");
			return false;
		}
	}
	return true;
}
var sumCostLimit = null;
function calcSum(){
	var sumWeight="0";
	for(var i=0;i<$n("ornaCode").length;i++){
		sumWeight = floatAdd(sumWeight, nullToZero($n("weight")[i].value));
	}
	jQuery("#sumCount").val($n("ornaCode").length);//件数合计
	jQuery("#sumWeight").val(sumWeight);//重量合计
}
function nullToZero(val){
	if(!val)
		return "0";
	return val;
}

// -----------------------------------------------------------------------------------------------------------------------------------
/**
 * 显示选择调拨指令窗口
 */
function showSelectMoveCmdWin (){
	var outStockId = jQuery("#outStockId").val();
	var inStockId = jQuery("#inStockId").val();
	var outOrgId = jQuery("#outOrgId").val();
	var inOrgId = jQuery("#inOrgId").val();
	var headid = jQuery("#headid").val();
	if(!inOrgId) {
		alert("请选择调入组织");
		return;
	}
	var _iframeId = "selectMoveCmdIframe";
	var options = {
		title : '调拨指令单',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 900,
		height : 300,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			insertRows(result, box);
//			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/move/moveCmd.vm?user_action=selectMoveCmd&inOrgId='+inOrgId + "&outOrgId=" + outOrgId+ "&outStockId=" + outStockId+ "&inStockId=" + inStockId + "&moveId=" + headid;
	jQuery.weeboxs.open(url, options);
}

function insertRows(res, box) {
	var oldMoveCmdId = jQuery("#moveCmdId").val();
	if(!res) {
		if(oldMoveCmdId.length >0) {
			confirm("确定不选择调拨指令单?", function(){
				clearLines();
				box.close();
			});
		} else {
			box.close();
		}
		jQuery("#moveCmdNo").val("");
		jQuery("#moveCmdId").val("");
		jQuery("#ornaCode_in").attr("disabled", false);
		jQuery("#barCode_in").attr("disabled", false);
	} else {
		box.close();
		jQuery("#ornaCode_in").attr("disabled", true);
		jQuery("#barCode_in").attr("disabled", true);
		var newMoveCmdId = res.moveCmdId,
			moveCmdNo = res.moveCmdNo,
			ornaCodes = res.ornaCodes;
		jQuery("#moveCmdNo").val(moveCmdNo);
		jQuery("#moveCmdId").val(newMoveCmdId);
		if(newMoveCmdId != oldMoveCmdId){
			if(oldMoveCmdId.length >0){
				clearLines();
			}
			addLines(ornaCodes);
		}
	}
}
/**
 * 清空行
 */
function clearLines (){
	while($n("ornaCode").length >0){
		deleteOrnaCodeArr.push($n("ornaCode")[0].value);
		deleteRow($n("ornaCode")[0], 'tbl');
	}
	calcSum();
	disableOutInfo();
}

/**
 * 增加饰品行
 * @param {} ornaCodes
 */
function addLines(ornaCodes) {
	var ornaCodeArr = ornaCodes.split(",");
	for(var i=0; i<ornaCodeArr.length; i++){
		var ornaCode = ornaCodeArr[i];
		getMaterActiveInfo2(ornaCode);
	}
}

/**
 * 获取饰品信息
 * @param {} code
 */
function getMaterActiveInfo2(code){
	if(!checkOrnaCodeExists2(code))
		return;
	MoveBillDwr.getMaterActiveInfo2(code, jQuery("#outOrgId").val(), jQuery("#billType").val(), jQuery("#inOrgId").val(), function(data){
		addMoveRow(data);
		calcSum();
		disableOutInfo();
		$n("deleteBtn")[0].disabled = true;
	});
}
/**
 * 判断饰品编码是否已经存在行中
 * @param {} code
 * @return {Boolean}
 */
function checkOrnaCodeExists2(code){
	for(var i=0;i<$n("ornaCode").length;i++){
		if(ornaFlag && $n("ornaCode")[i].value == code){
			return false;
		}
	}
	return true;
}

function initMoveCmdData(){
	var moveCmdId = jQuery("#moveCmdId").val();
	if(moveCmdId && moveCmdId.length >0){
		jQuery("#ornaCode_in").attr("disabled", true);
		jQuery("#barCode_in").attr("disabled", true);
		
		for(var i=0; i< $n("deleteBtn").length ; i++){
			$n("deleteBtn")[i].disabled = true;
		}
	}
}