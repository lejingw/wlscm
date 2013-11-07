/**
 * 选择配货参数
 */
function selectOrg(gheadid, gstatus, selectFunc, newfunc) {
	var _iframeId = "selectDispatchCondition";
	var options = {
		title : '选择配货参数',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 300,
		okBtnName : '选择参数进入',
		//cancelBtnName : '',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			if(!selectFunc(result))
				return ;
			box.close();// 增加事件方法后需手动关闭弹窗
		},
		oncancel : function(box) {
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	};
	var url = ctxPath + '/stock/dispatchOrna.vm?user_action=condition&gheadid=' + gheadid + "&gstatus=" + gstatus;
	jQuery.weeboxs.open(url, options).addButton({
		title : '创建新的分配',
		fn : function(box){
			if(!newfunc())
				return ;
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	});
}