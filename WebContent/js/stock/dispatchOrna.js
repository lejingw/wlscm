/**
 * 记账
 */
function markBill(){
	if("-1" == jQuery("#conditionId").val()){
		alert("无法完成记账，请先生成配货参数");
		return false;
	}
	confirm("确定记账?", function(){
		showLayer(true);
		DispatchOrnaDwr.markBill(jQuery("#conditionId").val(), function(data){
			showLayer(false);
			alert(data?data:"记账成功", function(){
				window.location = ctxPath + "/stock/dispatchOrna.vm";
			});
		});
	});
}
/**
 * 查看减库饰品
 */
function viewSubOrna(){
	var options = {
		title : '查看减库饰品',
		contentType : 'iframe',
		iframeId : "subOrnaFrm",
		width : 1000,
		height : 400,
		showOk : false,
		cancelBtnName : '关闭',
		closeable : true,
		boxid : 'winDiv',
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/stock/dispatchOrna.vm?user_action=viewSubOrna&gheadid=' + jQuery("#gheadid").val();
	jQuery.weeboxs.open(url, options);
}
/**
 * 改变大类
 */
function changeItemClass1(){
	DispatchOrnaDwr.getGatherLineOrnaClassForSlt(jQuery("#gheadid").val(), jQuery("#itemClassId1").val(), function(data){
			addOptions("ornaClassId1", data, null, null, true, true);
		});
}
/**
* 生成配货参数
*/
function saveDispatchCondition(){
	if(!jQuery("#itemClassId1").val() || !jQuery("#ornaClassId1").val()){
		alert("请先选择大类、小类");
		return ;
	}
	showLayer(true);
	DispatchOrnaDwr.checkDispatchCondition(jQuery("#gheadid").val(), jQuery("#itemClassId1").val(), jQuery("#ornaClassId1").val(), function(data){
		showLayer(false);
		if(!data){
			showLayer(true);
			DispatchOrnaDwr.saveDispatchCondition(jQuery("#gheadid").val(), jQuery("#itemClassId1").val(), jQuery("#ornaClassId1").val(), function(data2){
				showLayer(false);
				alert("生成配货参数成功", function(){
					window.location = "dispatchOrna.vm?user_action=dispatch&gheadid=" + jQuery("#gheadid").val()
						+ "&gstatus=" + jQuery("#gstatus").val() + "&conditionId=" + data2;
				});
			});
		}else{
			alert(data);
		}
	});
}
/**
 * 选择分配店
 */
function selectDispatchOrg(){
	if("-1" == jQuery("#conditionId").val()){
		alert("请先生成配货参数");
		return ;
	}
	var _iframeId = "selectDispatchOrg";
	var options = {
		title : '选择分配店',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 300,
		okBtnName : '确定',
		//cancelBtnName : '',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			if(!result || result.length<1){
				alert("请先选择分配店");
				return ;
			}
			jQuery("#inOrgId").val(result[0].orgId);
			jQuery("#inOrgName").val(result[0].orgName);
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/stock/dispatchOrna.vm?user_action=selectDispatchOrg&conditionId=' + jQuery("#conditionId").val() + "&inOrgId=" + jQuery("#inOrgId").val();
	jQuery.weeboxs.open(url, options).addButton({
		title : '自动分配',
		fn : function(box){
			jQuery("#inOrgId").val("-1");
			jQuery("#inOrgName").val("自动分配");
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	});
}
/**
 * 检查是否可以配货
 * @returns {Boolean}
 */
var HeadquarterStatus = null;
function checkDispatch(){
	if(null == HeadquarterStatus){
		startSync();
		DispatchOrnaDwr.getHeadquarterStatus(jQuery("#gheadid").val(), function(data){
			HeadquarterStatus = data;
		});
		endSync();
	}
	if(HeadquarterStatus == 8){
		alert("总部总单已经关闭不能配货");
		return false;
	}
	if("-1" == jQuery("#conditionId").val()){
		alert("请先生成配货参数");
		return false;
	}
	if(!jQuery("#inOrgId").val()){
		alert("请先选择分配店");
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
	go_to(1);
	setDispatchOrnaInfo({});
	dispatchOrna(jQuery("#ornaCode").val(), true);
}
/**
 * 根据饰品条码获取饰品信息
 */
function getMaterActiveByBarCode(){
	if(!checkDispatch())
		return ;
	go_to(1);
	setDispatchOrnaInfo({});
	dispatchOrna(jQuery("#barCode").val(), false);
}
/**
 * 配货
 * @param code
 * @param ornaFlag
 */
function dispatchOrna(code, ornaFlag){
	var conditionId = jQuery("#conditionId").val();
	var inOrgId = jQuery("#inOrgId").val();
	var styleFalg = jQuery("#styleFalg").attr("checked");
	var styleFalg2 = jQuery("#styleFalg2").val();
	var colorGradeFlag = jQuery("#colorGradeFlag").attr("checked");
	var cleanFlag = jQuery("#cleanFlag").attr("checked");
	showLayer(true);
	DispatchOrnaDwr.dispatchOrna(conditionId, code, ornaFlag, inOrgId, styleFalg, styleFalg2, colorGradeFlag, cleanFlag, function(data){
		showLayer(false);
		setDispatchOrnaInfo(data);
		var displayHtml = "";
		if(data.dispatchModel == '3'){
			displayHtml = "饰品配货<font size='6' color=red>未成功</font>";
		}else if(data.dispatchModel == '2'){
			displayHtml = "饰品配货<font size='6' color=blue>成功</font>,已经配给<font size='6'>" + data.orgName + "</font>";
		}else if(data.dispatchModel == '1'){
			displayHtml = "饰品配货<font size='6' color=red>未成功</font>,已经配给<font size='6'>" + data.orgName + "</font>";
			if(data.gatherHeadId != jQuery("#gheadid").val()){
				displayHtml += ",该饰品在其他总单中配过，不能变更";
			}
		}
		if(data.status == "3"){
			displayHtml += ",且已经调拨，不能变更";
		}
		$("dispatchTip").innerHTML = displayHtml;
		showDispatchingChangeFrame(data.dispatchModel, data.gatherHeadId, data.status, data.orderLineId, data.ornaLogId);
		jQuery("#ornaCode").val("");
		jQuery("#barCode").val("");
	});
}
function showDispatchingChangeFrame(oldDispatchModel, oldGatherHeadId, oldStatus, orderLineId, ornaLogId){
	//在其他总单中不能变更
	//已调拨不能变更
	if(!jQuery("#showDispatchingChangeChk").attr("checked") || isNull(ornaLogId) || isNull(orderLineId) || ("1" == oldDispatchModel && oldGatherHeadId != jQuery("#gheadid").val()) || (oldStatus == "3")){
		jQuery("#dispatchingChangeFrame").attr("src", "about:blank");
		jQuery("#dispatchingChangeFrame").hide();
		return ;
	}
	
	var styleFalg = jQuery("#styleFalg").attr("checked");
	var colorGradeFlag = jQuery("#colorGradeFlag").attr("checked");
	var cleanFlag = jQuery("#cleanFlag").attr("checked");
	var url =ctxPath + "/stock/dispatchOrna.vm?user_action=changeDispatching&conditionId=" + jQuery("#conditionId").val()
		+ "&orderLineId=" + orderLineId + "&ornaLogId=" + ornaLogId
		+ "&styleFalg=" + (styleFalg?1:0) + "&styleFalg2=" + jQuery("#styleFalg2").val() + "&colorGradeFlag=" + (colorGradeFlag?1:0) + "&cleanFlag=" + (cleanFlag?1:0)
		+ "&openflag=1";
	
	jQuery("#dispatchingChangeFrame").attr("src", url);
	jQuery("#dispatchingChangeFrame").show();
}
/**
 * 显示配货饰品信息
 */
function setDispatchOrnaInfo(ornaInfo){
	//info($("tbl1").rows.length);
	$("tbl1").rows[0].cells[0].innerHTML = toEmpty(ornaInfo.ornaCode);
	$("tbl1").rows[0].cells[1].innerHTML = toEmpty(ornaInfo.ornaDsc);
	$("tbl1").rows[0].cells[2].innerHTML = toEmpty(ornaInfo.unitName);
	$("tbl1").rows[0].cells[3].innerHTML = printStyle(toEmpty(ornaInfo.styleId), toEmpty(ornaInfo.styleName), toEmpty(ornaInfo.bigGraph));
	$("tbl1").rows[0].cells[4].innerHTML = toEmpty(ornaInfo.colorGradeGradeName) +""+ toEmpty(ornaInfo.cleanGradeName);
	$("tbl1").rows[0].cells[5].innerHTML = toEmpty(ornaInfo.allQty);
	$("tbl1").rows[0].cells[6].innerHTML = toEmpty(ornaInfo.mainWeight);
	$("tbl1").rows[0].cells[7].innerHTML = toEmpty(ornaInfo.partWeight);
	$("tbl1").rows[0].cells[8].innerHTML = toEmpty(ornaInfo.sizeName);
	$("tbl1").rows[0].cells[9].innerHTML = toEmpty(ornaInfo.basicPrice);
}
function toEmpty(val){
	if(!val)	return "";
	return val;
}

var changeDispatchingBox = null;

/**
 * 变更正配数据
 */
function changeDispatching(obj, orderLineId, ornaLogId){
	var _iframeId = "changeDispatching";
	var options = {
			title : '变更分配饰品',
			contentType : 'iframe',
			iframeId : _iframeId,
			width : 1000,
			height : 350,
			okBtnName : '关闭',
			//cancelBtnName : '',
			showCancel : false,
			closeable:true,
			boxid:'winDiv',
			onok : function(box) {
				//	var result = jQuery("#" + _iframeId)[0].contentWindow.needRefresh();
				//	if(!selectFunc(result))
				//		return ;
				box.close();
			}
	};
	var styleFalg = jQuery("#styleFalg").attr("checked");
	var colorGradeFlag = jQuery("#colorGradeFlag").attr("checked");
	var cleanFlag = jQuery("#cleanFlag").attr("checked");
	var url =ctxPath + "/stock/dispatchOrna.vm?user_action=changeDispatching&conditionId=" + jQuery("#conditionId").val()
		+ "&orderLineId=" + orderLineId + "&ornaLogId=" + ornaLogId
		+ "&styleFalg=" + (styleFalg?1:0) + "&styleFalg2=" + jQuery("#styleFalg2").val() + "&colorGradeFlag=" + (colorGradeFlag?1:0) + "&cleanFlag=" + (cleanFlag?1:0);
	changeDispatchingBox = jQuery.weeboxs.open(url, options);
}
function closeChangeDispatchingBox(newOrderLineId, oldOrderLineId){
	changeDispatchingBox.close();
	if(newOrderLineId || oldOrderLineId){
		jQuery("#dispatchingFrame")[0].contentWindow.refreshShowDispatchingWin(newOrderLineId, oldOrderLineId);
	}
}
/**
 * 改变款式匹配条件
 */
function changeStyleFlag(){
	if(jQuery("#styleFalg").attr("checked")){
		jQuery("#styleFalg2").hide();
	}else{		
		jQuery("#styleFalg2").show();
	}
}