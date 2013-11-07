//初始化 JS遮罩层 
function initStartWait() {
	// 全屏
	if (jQuery("#wait_fullbg") == null || jQuery("#wait_fullbg")[0] == null) {
		var wait_fullbg = jQuery('<div id="wait_fullbg"></div>');
		jQuery(wait_fullbg).appendTo("body").css({
			"background-color" : "Gray",
			"display" : "none",
			"z-index" : "3",
			"position" : "absolute",
			"left" : "0px",
			"top" : "0px",
			"filter" : "Alpha(Opacity = 30)",
			"-moz-opacity" : "0.4",
			"opacity" : "0.4"
		});
		var wait_frame = jQuery('<iframe name="wait_frame" id="wait_frame" frameborder="0" scrolling="no"></iframe>');
		jQuery(wait_frame).appendTo("body").css({
			"background-color" : "Gray",
			"display" : "none",
			"z-index" : "2",
			"position" : "absolute",
			"left" : "0px",
			"top" : "0px",
			"filter" : "Alpha(Opacity = 30)",
			"-moz-opacity" : "0.4",
			"opacity" : "0.4"
		});
	}
	// 居中 dialog
	if (jQuery("#wait_dialog") == null || jQuery("#wait_dialog")[0] == null) {
		var wait_dialog = jQuery('<div id="wait_dialog"></div>');
		jQuery(wait_dialog).appendTo("body").css({
			"background" : "white",
			"display" : "none",
			"z-index" : "5",
			"position" : "absolute",
			"width" : "260px",
			"height" : "66px"
		});
//		// 关闭
//		if (jQuery("#wait_dialog_close") == null || jQuery("#wait_dialog_close")[0] == null) {
//			var wait_dialog_close = jQuery('<div id="wait_dialog_close"><a href="#" onclick="endWait();">关闭</a></div>');
//			jQuery(wait_dialog_close).appendTo(jQuery(wait_dialog)).css({
//				"text-align" : " right",
//				"padding-right" : "2px"
//			});
//		}
		// 提示
		if (jQuery("#wait_dialog_content") == null
				|| jQuery("#wait_dialog_content")[0] == null) {
			var wait_dialog_content = jQuery('<div id="wait_dialog_content"></div>');
			jQuery(wait_dialog_content).appendTo(jQuery(wait_dialog)).css({
				"text-align" : "center"
			});
		}
		// 滚动条图片
		if (jQuery("#wait_dialog_img") == null || jQuery("#wait_dialog_img")[0] == null) {
			var _startWait_img = '<img src="' + ctxPath + '/image/mclon/loading.gif"/>';
			var wait_dialog_img = jQuery('<div id="wait_dialog_img">' + _startWait_img + '</div>');
			jQuery(wait_dialog_img).appendTo(jQuery(wait_dialog)).css({
				"text-align" : "center"
			});
		}
	}
}
// 显示灰色JS遮罩层
function startWait(wait_msg) {
	initStartWait();
	var _startWait_msg = '正在提交数据，请稍后...';
	if (wait_msg != null && wait_msg != '') {
		_startWait_msg = wait_msg;
	}
	var bH = getClientH();
	var bW = getClientW();
	jQuery('#wait_frame').css({
		width : bW - 3,
		height : bH - 5,
		display : "block"
	});
	jQuery("#wait_fullbg").css({
		width : bW,
		height : bH,
		display : "block"
	});
	var tbT = getAbsoluteTopOfDialog() + "px";
	var tbL = getAbsoluteLeftOfDialog() + "px";
	jQuery("#wait_dialog").css({
		top : tbT,
		left : tbL,
		display : "block"
	});
	jQuery("#wait_dialog_content").html(_startWait_msg);
	jQuery(window).scroll(function() {
		resetWait()
	});
	jQuery(window).resize(function() {
		resetWait()
	});
}
// 窗口 高度
function getClientH() {
	var winHeight = 0;
	if (window.innerHeight) {
		winHeight = window.innerHeight;
	} else if ((document.body) && (document.body.clientHeight)) {
		winHeight = document.body.clientHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		winHeight = document.documentElement.clientHeight;
	}
	return winHeight;
} // 窗口 宽度
function getClientW() {
	var winWidth = 0;
	if (window.innerWidth) {
		winWidth = window.innerWidth;
	} else if ((document.body) && (document.body.clientWidth)) {
		winWidth = document.body.clientWidth;
	} else if (document.documentElement && document.documentElement.clientWidth) {
		winWidth = document.documentElement.clientWidth;
	}
	return winWidth;
} // 滚动条 距左边的距离
function getScrollLeft() {
	var scrollLeft = 0;
	if ((document.body) && (document.body.scrollLeft)) {
		scrollLeft = document.body.scrollLeft;
	} else if (document.documentElement && document.documentElement.scrollLeft) {
		scrollLeft = document.documentElement.scrollLeft;
	}
	return scrollLeft;
} // 滚动条 距顶部的距离
function getScrollTop() {
	var scrollTop = 0;
	if ((document.body) && (document.body.scrollTop)) {
		scrollTop = document.body.scrollTop;
	} else if (document.documentElement && document.documentElement.scrollTop) {
		scrollTop = document.documentElement.scrollTop;
	}
	return scrollTop;
}
// dialog 距屏幕的左边距
function getAbsoluteLeftOfDialog() {
	var sl = getScrollLeft();
	var cw = getClientW();
	var objW = jQuery("#wait_dialog").width();
	var objL = Number(sl) + (Number(cw) - Number(objW)) / 2;
	return objL;
}
// dialog 距屏幕的上边距
function getAbsoluteTopOfDialog() {
	var st = getScrollTop();
	var ch = getClientH();
	var objH = jQuery("#wait_dialog").height();
	var objT = Number(st) + (Number(ch) - Number(objH)) / 2;
	return objT;
}
// 重置 遮罩层位置
function resetWait() {
	var fullbg_display = jQuery("#wait_fullbg").css("display");
	if (fullbg_display == "block") {
		var bH2 = getClientH();
		var bW2 = getClientW();
		jQuery("#wait_frame").css({
			width : bW2 - 3,
			height : bH2 - 5
		});
		jQuery("#wait_fullbg").css({
			width : bW2,
			height : bH2
		});
		var tbT = getAbsoluteTopOfDialog() + "px";
		var tbL = getAbsoluteLeftOfDialog() + "px";
		jQuery("#wait_dialog").css({
			top : tbT,
			left : tbL
		});
	}
}
// 关闭 dialog 和 JS遮罩层
function endWait() {
	jQuery("#wait_dialog").css("display", "none");
	jQuery("#wait_fullbg").css("display", "none");
	jQuery("#wait_frame").css("display", "none");
}
/**
 * 显示遮罩层
 */
function showLayer(showFlag, wait_msg) {
	if(showFlag){
		startWait(wait_msg);
	}else{
		endWait();
	}
};