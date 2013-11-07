
/**
 * 检查是否可以配货
 */
function checkDispatch(){
	if("-1" == jQuery("#conditionId").val()){
		alert("请先生成配货参数");
		return false;
	}
	return true;
}
/**
 * 根据饰品编码获取饰品信息
 */
function getMaterActiveByOrnaCode(){
	if(!checkDispatch())
		return ;
	setDispatchOrnaInfo({});
	dispatchOrna(jQuery("#ornaCode_in").val(), true);
}
/**
 * 根据饰品条码获取饰品信息
 */
function getMaterActiveByBarCode(){
	if(!checkDispatch())
		return ;
	setDispatchOrnaInfo({});
	dispatchOrna(jQuery("#barCode_in").val(), false);
}
/**
 * 配货
 * @param code
 * @param ornaFlag
 */
function dispatchOrna(code, ornaFlag){
//	var conditionId = jQuery("#conditionId").val();
	showLayer(true);
	StandardDispatchDwr.dispatchOrna(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), code, ornaFlag, {
		callback:function(data){
			showLayer(false);
			setDispatchOrnaInfo(data);
			showDispatchingChangeFrame(data.ornaCode);
			jQuery("#ornaCode_in").val("");
			jQuery("#barCode_in").val("");
		},
		errorHandler:function(msg){
			showLayer(false);
			alert(msg);
			showDispatchingChangeFrame("");
		}
	});
}
function showDispatchingChangeFrame(ornaCode){
	var url ="standardDispatch.vm?user_action=dispatching&gheadid="+jQuery("#gheadid").val()+"&ornaCode=" + ornaCode;
	jQuery("#dispatchingChangeFrame").attr("src", url);
}
/**
 * 显示配货饰品信息
 */
function setDispatchOrnaInfo(ornaInfo){
	//分析范围	款式大类	款式中类	款式小类	款式	色级	净度	品质	尺寸	商品材质	托架颜色	计量单位
	$("tbl1").rows[0].cells[0].innerHTML = toEmpty(ornaInfo.analysisName);
	$("tbl1").rows[0].cells[1].innerHTML = toEmpty(ornaInfo.styleItemClassName);
	$("tbl1").rows[0].cells[2].innerHTML = toEmpty(ornaInfo.styleMiddleClassName);
	$("tbl1").rows[0].cells[3].innerHTML = toEmpty(ornaInfo.styleOrnaClassName);
	$("tbl1").rows[0].cells[4].innerHTML = printStyle(toEmpty(ornaInfo.styleId), toEmpty(ornaInfo.styleName), toEmpty(ornaInfo.bigGraph));
	$("tbl1").rows[0].cells[5].innerHTML = toEmpty(ornaInfo.colorGradeName);
	$("tbl1").rows[0].cells[6].innerHTML = toEmpty(ornaInfo.cleanName);
	$("tbl1").rows[0].cells[7].innerHTML = toEmpty(ornaInfo.colorGradeGradeName) +""+ toEmpty(ornaInfo.cleanGradeName);
	$("tbl1").rows[0].cells[8].innerHTML = toEmpty(ornaInfo.sizeName);
	$("tbl1").rows[0].cells[9].innerHTML = toEmpty(ornaInfo.qualityName);
	$("tbl1").rows[0].cells[10].innerHTML = toEmpty(ornaInfo.bracketcolorName);
	$("tbl1").rows[0].cells[11].innerHTML= toEmpty(ornaInfo.unitName);
}
function toEmpty(val){
	if(!val)	return "";
	return val;
}