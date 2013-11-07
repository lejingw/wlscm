
function procexitCondition(){
	var vendorId = jQuery("#vendorId").val();
	// info("test|" + vendorId + "|");
	if(!vendorId) {
		alert("请先选择供应商");
		return;
	}
	var _iframeId = "procexitConditionIframe";
	var options = {
		title : '发料单',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 900,
		height : 300,
		okBtnName : '确定',
		//cancelBtnName : '',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			// alert(result);
			if(!result || result.length<1){
				return ;
			}
			insertRows(result);
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath + '/stock/procExitCondtion.vm?orgId='+jQuery("#orgId").val() + "&vendorId=" + jQuery("#vendorId").val();
	jQuery.weeboxs.open(url, options);
}

