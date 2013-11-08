/**
 * 选择组织
 * @param okfunc 确定调用的方法 function(idArr, nameArr, objArr){}
 * @param checkFunc 确定前做数据有效性检查
 * @param cancelfunc 取消调用的方法 function(idArr, nameArr, objArr){}
 * @param multiFlag 是否多选true多选false单选
 * @param selectedValues 已经选择的ids，一般取idArr.join(",")
 * @param userAction getOrgTree
 */
function selectOrg(okfunc, cancelfunc, checkFunc, multiFlag, selectedValues, userAction, params) {
	var _iframeId = "selectOrgFrm";
	var options = {
		title : '选择组织',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 350,
		//okBtnName : '确定',
		//cancelBtnName : '取消',
		onok : function(box) {
			var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			//alert(results);
			if(checkFunc && !checkFunc(results[0], results[1], results[2])){
				return;
			}
			okfunc(results[0], results[1], results[2]);
			box.close();// 增加事件方法后需手动关闭弹窗
		},
		oncancel : function(box) {
			if(cancelfunc){
				cancelfunc();
			}
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	};
	userAction = userAction || "getOrgTree";
	var url = ctxPath + '/common/SelectOrg.vm?user_action='+userAction+'&multiFlag=' + (multiFlag?true:false) + '&selectedValues=' + selectedValues;
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];
		}
	}
	jQuery.weeboxs.open(url, options);
}
/**
 * 选择款式大类、中类、小类公用弹出框
 * @param okfunc 确定调用的方法 function(idArr, nameArr, objArr){}
 * @param checkFunc 确定前做数据有效性检查
 * @param cancelfunc 取消调用的方法 function(idArr, nameArr, objArr){}
 * @param multiFlag 是否多选true多选false单选
 * @param selectedValues 已经选择的ids，一般取idArr.join(",")
 * @param userAction getAllStyleItemClass
 */
function selectStyleClass(okfunc, cancelfunc, checkFunc, multiFlag, selectedValues, userAction, params) {
	var _iframeId = "selectStyleClassFrm";
	var options = {
		title : '选择',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 350,
		//okBtnName : '确定',
		//cancelBtnName : '取消',
		onok : function(box) {
			var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			//alert(results);
			if(checkFunc && !checkFunc(results[0], results[1], results[2])){
				return;
			}
			okfunc(results[0], results[1], results[2]);
			box.close();// 增加事件方法后需手动关闭弹窗
		},
		oncancel : function(box) {
			if(cancelfunc){
				cancelfunc();
			}
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	};
	userAction = userAction || "getAllStyleItemClass";
	var url = ctxPath + '/common/SelectStyleClass.vm?user_action='+userAction+'&multiFlag=' + (multiFlag?true:false) + '&selectedValues=' + selectedValues;
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];
		}
	}
	jQuery.weeboxs.open(url, options);
}
/**
 * 选择供应商
 * @param okfunc 确定调用的方法 function(idArr, nameArr, objArr){}
 * @param checkFunc 确定前做数据有效性检查
 * @param cancelfunc 取消调用的方法 function(idArr, nameArr, objArr){}
 * @param multiFlag 是否多选true多选false单选
 * @param selectedValues 已经选择的ids，一般取idArr.join(",")
 * @param userAction getAllVendor
 */
function selectVendor(okfunc, cancelfunc, checkFunc, multiFlag, selectedValues, userAction, params) {
	var _iframeId = "selectVendorFrm";
	var options = {
		title : '选择',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 350,
		//okBtnName : '确定',
		//cancelBtnName : '取消',
		onok : function(box) {
			var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			//alert(results);
			if(checkFunc && !checkFunc(results[0], results[1], results[2])){
				return;
			}
			okfunc(results[0], results[1], results[2]);
			box.close();// 增加事件方法后需手动关闭弹窗
		},
		oncancel : function(box) {
			if(cancelfunc){
				cancelfunc();
			}
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	};
	userAction = userAction || "selectVendor";
	var url = ctxPath + '/common/SelectCommon.vm?user_action='+userAction+'&multiFlag=' + (multiFlag?true:false) + '&selectedValues=' + selectedValues;
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];
		}
	}
	jQuery.weeboxs.open(url, options);
}
/**
 * 选择采购员
 * @param okfunc 确定调用的方法 function(idArr, nameArr){}
 * @param checkFunc 确定前做数据有效性检查
 * @param cancelfunc 取消调用的方法 function(idArr, nameArr){}
 * @param multiFlag 是否多选true多选false单选
 * @param selectedValues 已经选择的ids，一般取idArr.join(",")
 * @param userAction getPurchaseEmps
 */
function selectEmp(okfunc, cancelfunc, checkFunc, multiFlag, selectedValues, userAction, params) {
	var _iframeId = "puchaseEmpFrm";
	var options = {
			title : '选择人员',
			contentType : 'iframe',
			iframeId : _iframeId,
			width : 800,
			height : 350,
			onok : function(box) {
    			var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
    			//alert(results);
    			if(checkFunc && !checkFunc(results[0], results[1])){
    				return;
    			}
    			okfunc(results[0], results[1]);
    			box.close();// 增加事件方法后需手动关闭弹窗
			},
			oncancel : function(box) {
				if(cancelfunc){
					cancelfunc();
				}
				box.close();// 增加事件方法后需手动关闭弹窗
			}
	};
	var url =ctxPath + '/common/SelectEmp.vm?user_action=' + userAction + '&multiFlag=' + (multiFlag?true:false) + '&selectedValues=' + selectedValues;
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];			
		}
	}
//	info(url);
	jQuery.weeboxs.open(url, options);
}
/**
 * 选择款式
 * @param okfunc
 * @param cancelfunc
 * @param checkfunc
 * @param multiFlag
 * @param selectedValues
 * @param userAction
 * @param params
 */
function selectStyle(okfunc, cancelfunc, checkfunc, multiFlag, selectedValues, userAction, params){
	var _iframeId = "selectStyleFrm";
	var options = {
			title : '选择款式',
			contentType : 'iframe',
			iframeId : _iframeId,
			width : 800,
			height : 480,
			onok : function(box) {
				var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
				//alert(results);
				if(checkFunc && !checkFunc(results[0], results[1])){
					return;
				}
				okfunc(results[0], results[1]);
				box.close();// 增加事件方法后需手动关闭弹窗
			},
			oncancel : function(box) {
				if(cancelfunc){
					cancelfunc();
				}
				box.close();// 增加事件方法后需手动关闭弹窗
			}
	};
	userAction = userAction || "doPerform";
	var url =ctxPath + '/common/SelectStyle.vm?user_action=' + userAction + '&multiFlag=' + (multiFlag?true:false) + '&selectedValues=' + selectedValues;
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];
		}
	}
	//info(url);
	return jQuery.weeboxs.open(url, options);
}


/**
 * 查看审批流程
 * @param billCode 单据编号
 * @param billId 单据id
 */
function viewReviewLog(billCode, billId){
	var _iframeId = "viewReviewLogFrm";
	var options = {
			title : '查看审批流程',
			contentType : 'iframe',
			iframeId : _iframeId,
			width : 800,
			height : 300,
			cancelBtnName:'关闭',
			showOk : false,
			oncancel : function(box) {
				box.close();
			}
	};
	billCode = billCode || '';
	billId = billId || '';
	var url =ctxPath + '/util/common.vm?user_action=viewReviewLog&billCode=' + billCode + "&billId=" + billId;
	//info(url);
	jQuery.weeboxs.open(url, options);
}
/**
 * 查看款式图片
 * @param styleId
 */
function viewStyle(styleId){
	var _iframeId = "viewStylePicFrm";
	var options = {
			title : '款式图片',
			contentType : 'iframe',
			iframeId : _iframeId,
			width : 650,
			height : 450,
			cancelBtnName:'关闭',
			showOk : false,
			closeable : true,
			oncancel : function(box) {
				box.close();
			}
	};
	var url =ctxPath + '/util/common.vm?user_action=viewStylePic&styleId='+styleId;
	//info(url);
	jQuery.weeboxs.open(url, options);
}



function selectClass(okfunc, cancelfunc, checkFunc, multiFlag, selectedValues, userAction, params) {
	var _iframeId = "selectClassFrm";
	var options = {
		title : '选择',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 350,
		//okBtnName : '确定',
		//cancelBtnName : '取消',
		onok : function(box) {
			var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			//alert(results);
			if(checkFunc && !checkFunc(results[0], results[1], results[2])){
				return;
			}
			okfunc(results[0], results[1], results[2]);
			box.close();// 增加事件方法后需手动关闭弹窗
		},
		oncancel : function(box) {
			if(cancelfunc){
				cancelfunc();
			}
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	};
	userAction = userAction || "getAllItemClass";
	var url = ctxPath + '/common/SelectStyleClass.vm?user_action='+userAction+'&multiFlag=' + (multiFlag?true:false) + '&selectedValues=' + selectedValues;
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];
		}
	}
	jQuery.weeboxs.open(url, options);
}
/**
 * 选择商品类别
 * @param okfunc 确定调用的方法 function(idArr, nameArr, objArr){}
 * @param checkFunc 确定前做数据有效性检查
 * @param cancelfunc 取消调用的方法 function(idArr, nameArr, objArr){}
 * @param multiFlag 是否多选true多选false单选
 * @param selectedValues 已经选择的ids，一般取idArr.join(",")
 * @param userAction getAllVendor
 */
function selectArticleType(okfunc, cancelfunc, checkFunc, multiFlag, selectedValues, userAction, params) {
	var _iframeId = "selectArticleTypeFrm";
	var options = {
		title : '选择',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 350,
		//okBtnName : '确定',
		//cancelBtnName : '取消',
		onok : function(box) {
			var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			//alert(results);
			if(checkFunc && !checkFunc(results[0], results[1], results[2])){
				return;
			}
			okfunc(results[0], results[1], results[2]);
			box.close();// 增加事件方法后需手动关闭弹窗
		},
		oncancel : function(box) {
			if(cancelfunc){
				cancelfunc();
			}
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	};
	userAction = userAction || "selectArticleType";
	var url = ctxPath + '/common/SelectCommon.vm?user_action='+userAction+'&multiFlag=' + (multiFlag?true:false) + '&selectedValues=' + selectedValues;
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];
		}
	}
	jQuery.weeboxs.open(url, options);
}


var srcUrlMap = new Map();
srcUrlMap.put("DP", "/stock/dispatchOrna.vm?user_action=dispatch");
srcUrlMap.put("PU", "/push/pushDispatch.vm?user_action=dispatch");
srcUrlMap.put("MC", "/move/moveCmd.vm?user_action=toEdit");
srcUrlMap.put("JJ", "/pur/handover.vm?user_action=toEdit");
srcUrlMap.put("WW", "/out/outVendor.vm?user_action=toEditEntrustout");
srcUrlMap.put("HJ", "/calc/priceHead.vm?user_action=toEditPriceHead");
srcUrlMap.put("TL", "/stock/procExit.vm?user_action=toEdit");
srcUrlMap.put("FT", "/pur/cash/prodChange.vm?user_action=toEdit");

function viewSrcBillWin(srcBillId, srcBillNo, srcBillCode){
    if(!(srcBillId || srcBillNo) || !srcBillCode) return;
    var options = {
        title : '来源单据',
        contentType : 'iframe',
        iframeId : "purchaseWindowId",
        width : 800,
        height : 400,
        okBtnName : '确定',
        closeable:true,
        boxid:'winDiv',
        onok : function(box) {box.close();},
        oncancel : function(box) {box.close();}
    };
    var url = srcUrlMap.get(srcBillCode);
    url = ctxPath+ url + "&gheadid="+srcBillId+"&headid=" + srcBillId + "&billid=" + srcBillId + "&billno=" + srcBillNo + "&conditionId=-1"  + "&reviewflag=1";
    jQuery.weeboxs.open(url, options);
}