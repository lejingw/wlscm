/**
 * 根据id，返回dom对象
 */
function $(id) {
    return document.getElementById(id);
}
/**
 * 根据name，返回dom对象数组
 */
function $n(name){
	return document.getElementsByName(name);
}
/**
 * 应用属性，不存在则添加，存在则覆盖
 */
function apply(obj, obj2){
	if(obj2){
		for(var p in obj2){
			obj[p] = obj2[p];
		}
	}
	return obj;
}
window.info = window.alert;
//window.info = function(){};
window.alert = function(msg, callback){
	/*
	return jQuery.weeboxs.open(""+msg, {
			title:'提示',
			type:'alert',
			boxid:'alertDiv',
			closeable:false,
			onok:function(box){
				if(callback){callback();};
				box.close();
			}
		});
	*/
	jAlert(msg, "提示", callback);
};
window.confirm = function(msg, okfunc, cancelfunc){
	/*
	jQuery.weeboxs.open(""+msg, {
			title:'确认',
			boxid:'confirmDiv',
			closeable:true,
			onok:function(box){
				if(okfunc){okfunc();};
				box.close();
			},
			oncancel:function(box){
				if(cancelfunc){cancelfunc();};
				box.close();
			}
		});
	return false;
	*/
	jConfirm(msg, "确认", function(flag){
		if(flag && okfunc){
			try{okfunc();}catch(e){}
		}else if(!flag && cancelfunc){
			try{cancelfunc();}catch(e){}
		}
	});
};
/**
 * 显示遮罩层
function showLayer(status) {
	switch( status ) {
		case true:
			showLayer(false);
			jQuery("BODY").append('<div id="confirm_loading"></div>');
			jQuery("#confirm_loading").css({
				position: 'absolute',
				zIndex: 99999,
				top: '0px',
				left: '0px',
				width: '100%',
				height: jQuery(document).height(),
				background: '#AAA',
				opacity: .5,
				"background-image": "url('"+ctxPath+"/image/mclon/loading.gif')",
				"background-repeat": "no-repeat",
				"background-position": "center"
			});
		break;
		case false:
			jQuery("#confirm_loading").remove();
		break;
	}
}
 */
/**
 * 覆盖jQuery的click方法
 * 先将按钮失效，后同步执行回调方法，执行完成后，使按钮有效
jQuery.fn.click = function(fn){
	var _this = this;
	return this.bind("click", function(){
		_this.attr("disabled", true);
		//startSync();
		if(fn){try{fn();}catch(e){}}
		//endSync();
		_this.attr("disabled", false);
	});
};
 */
window.confirm2 = function(msg, okBtnName, okfunc, cancelBtnName, cancelfunc){
	jQuery.weeboxs.open(""+msg, {
			title:'确认',
			boxid:'confirmDiv',
			closeable:true,
			okBtnName:cancelBtnName, 
			onok:function(box){
				if(cancelfunc){cancelfunc();};
				box.close();
			},
			oncancel:function(box){
				box.close();
			}
		}).addButton({
    		title : okBtnName,
    		fn : function(box){
    			if(okfunc){okfunc();};
    			box.close();
    		}
    	});
	return false;
};
/**
 * dwr开始同步
 */
function startSync(){
	dwr.engine.setAsync(false);
}
/**
 * dwr结束同步
 */
function endSync(){
	dwr.engine.setAsync(true);
}
/**
 * 链接转向
 */
function goToURL(url) {
  window.location = url;
}
/**
 * 页面重定向
 */
function forward(url) {
//	document.write("<form id='_frm' name='_frm' action='" + url + "' method='post'></form>");
//	document.getElementById("_frm").submit();
	var frm = document.createElement("form");
	frm.id = "_frm";
	frm.name = "_frm";
	frm.action = url;
	frm.method = 'post';
	document.body.appendChild(frm);
//	document.body.insertAdjacentElement("beforeEnd",frm); 
	document.getElementById("_frm").submit();
}
/**
 * 同步执行方法
 * @param func 方法
 * @returns func方法执行后的返回值
function synExe(func, argumentArr){
	var _data = null;
	argumentArr.push(function(){_data = data;});
	startSync();
	func.apply(argumentArr);
	//func(function(data){_data = data;});
	endSync();
	return _data;
}
 */
/**
 * 获取系统参数配置值
 */
function getParameterValue(name){
	var val = null;
	startSync();
	BdCommonDwr.getParameterValue(name, function(data){val = data;});
	endSync();
	return val;
}
/*
#set($cssfiles = ["$StringUtil.getContextPath()/script/mclon/calendar/calendar.css"])
#set($jsfiles = ["$StringUtil.getContextPath()/script/mclon/calendar/calendar.js"])
*/
/**
 * 初始化日期控件
 */
function initCalendarArr(inputIdArr, config){
	if(!inputIdArr){
		return ;
	}
	for(var i=0;i<inputIdArr.length;i++){
		initCalendar(inputIdArr[i], config);
	}
}
/**
 * 初始化日期控件
 */
function initCalendar(inputId, config){
	return Calendar.setup(apply({
			inputField: inputId,
			dateFormat: "%Y-%m-%d",
			trigger: inputId,
			bottomBar: true,
			showTime:false,
			onSelect: function() {
				this.hide();
			}
		}, config)
	);
}
//cal.selection.set(Calendar.dateToInt(new Date()) + 3);
//jQuery("#date").val(cal.selection.print("%Y-%m-%d"));
/**
 * 初始化标签打印
 * 页面添加js文件引用url
 * "$StringUtil.getContextPath()/script/mclon/ZeroClipboard/ZeroClipboard.js",
 * btnId		打印button的id
 * getUrlFunc	获取打印url，返回false或者null，则不执行打印
 */
function initLabelPrint(btnId, getUrlFunc){
	var win = null;
	if(isIE()){
		jQuery("#" + btnId).click(function(){
			var url = getUrlFunc();
			if(!url){
				return ;
			}
			url += (url.indexOf("?")<0?"?":"&") + "_printflag=1";
			win = showLabelPrint(url);
		});
	}else{
		var clip = new ZeroClipboard.Client();
		clip.setHandCursor(true);
		clip.addEventListener('mouseDown', function(cb) {
			if(jQuery("#" + btnId).attr("disabled")){
				return ;
			}
			var url = getUrlFunc();
			if(!url){
				return ;
			}
			url += (url.indexOf("?")<0?"?":"&") + "_printflag=1";
			cb.setText("");
			cb.setText("mclonprint:/" + url);
			//info("调用IE内核");
			//showLabelPrint(url);
		});
		clip.glue(btnId);
	}
	return win;
}
/**
 * 显示标签打印窗口
 * @param url
 */
function showLabelPrint(url){
	var options = {
		title : '打印标签',
		contentType : 'iframe',
		iframeId : "labelPrintFrm",
		width : 800,
		height : 400,
		showOk : false,
		cancelBtnName : '关闭',
		closeable : true,
		boxid : 'winDiv',
		oncancel : function(box) {
			box.close();
		}
	};
	return jQuery.weeboxs.open(url, options);
}
/**
 * 添加回车键的响应
 * @param inputId 控件id，如编码或条码输入框
 * @param func 点击回车键，回调的方法
 * 在回调方法前，回将控件失去焦点，防止重复点击回车键
 * 回调方法后，控件将重新获取焦点
 */
function setEnterKey(inputId, fn){
	jQuery("#" + inputId).live("keypress",function(e){
		if(e.keyCode != 13)
			return;
//		jQuery("#" + inputId).blur();
		jQuery("#" + inputId).attr("disabled", true);
		startSync();
		if(fn){try{fn();}catch(e){}}
		endSync();
		jQuery("#" + inputId).attr("disabled", false);
		jQuery("#" + inputId).focus();
	});
}
/**
* tbody获取选中的第一个checkbox的index
* 若无选中的则返回-1
*/
function getSelectIndex(chkName){
	var selectIndex = -1;
	jQuery("input[name='" + chkName + "']").each(function(index){
		if(jQuery(this).attr("checked")){
			selectIndex = index;
			return ;
		}
	});
	return selectIndex;
}
/**
* tbody获取选中的checkbox的index
* 若无选中的则返回-1
* 若是多选，则返回index数组
*/
function getSelectIndexs(chkName){
	var valsArr = [];
	jQuery("input[name='" + chkName + "']").each(function(index){
		if(jQuery(this).attr("checked")){
			valsArr.push(index);
		}
	});
	return valsArr;
}
/**
 * @param tblId 一般取tbody的id
 * @param tdHtmlArr td单元格的innerHTML字符串数组,数字长度要与tr中td的个数一致
 * @param insertFirstFlag 是否插入第一行，false添加最后一行
 */
function insertRow(tblId, tdHtmlArr, insertFirstFlag){
	if(!tdHtmlArr || tdHtmlArr.length<1)	return;
	var tr = null;
	if(insertFirstFlag){
		tr = $(tblId).insertRow(0);
	}else{
		tr = document.createElement("TR");
		$(tblId).appendChild(tr);
	}
	for(var i=0;i<tdHtmlArr.length;i++){
		var td = document.createElement("TD");
		td.innerHTML = tdHtmlArr[i];
		tr.appendChild(td);
	}
	var className = "row1";
	if($(tblId).rows.length>1){
		var index = tr.rowIndex + (insertFirstFlag?0:-2);
		if($(tblId).rows[index].className == "row1")
			className = "row2";
		else
			className = "row1";
	}
	jQuery(tr).addClass(className);
	jQuery(tr).hover(function(){
		jQuery(this).toggleClass("rowover");
	});
}
/**
 * 删除列表行
 * @param tblId tbody的id
 * @param obj 一般只指td中的删除input对象
 */
function deleteRow(obj, tblId){
	var rowIndex = obj.parentNode.parentNode.rowIndex;
	deleteRowByIndex(rowIndex - 1, tblId);
}
/**
 * 删除列表行
 * @param tblId tbody的id
 * @param index
 */
function deleteRowByIndex(index, tblId){
	$(tblId).deleteRow(index);
}
/**
 * 删除下拉框所有内容
* sltId select下拉框的id或对象
* showEmptyOptionFlag true 显示'--请选择--"选项 <可选 默认为false>
 */
function removeAllOptions(sltId,showEmptyOptionFlag){
	var slt = ((typeof sltId == 'string')?$(sltId):sltId);
	var length = slt.options.length;
	for(var i=length-1;i>=0;i--){
		slt.options.remove(i);
	}
	if(showEmptyOptionFlag){
		var op = new Option("--请选择--","");
	    slt.options.add(op);
	}
}
/**
 * 给下拉框添加一个选项
 * @param sltId select下拉框的id或对象
 * @param key 添加选项的键
 * @param value 添加选项的值
 */
function addOption(sltId, key, value, selectedFlag){
	var slt = ((typeof sltId == 'string')?$(sltId):sltId);
	var op = new Option(value, key);
	if(selectedFlag)
		op.selected = true;
    slt.options.add(op);
}
/**
 * 添加下拉框内容
 * @param sltId select下拉框的id或对象
 * @param data select下拉框的数据，为数组格式
 * @param keyName 默认取 value
 * @param valueName 默认取 text
 * @param clearAll 是否清除原有数据，true清除
 * @param showEmptyOptionFlag 当clearAll为true时有效，显示提示选项"--请选择--"
 * @param selectedValue 选中的值（单选）
 */
function addOptions(sltId, data, keyName, valueName, clearAll, showEmptyOptionFlag, selectedValue){
	if(clearAll){
		removeAllOptions(sltId,showEmptyOptionFlag);
	}
	if(!data || data.length<1)	return ;
	keyName = keyName || "value";
	valueName = valueName || "text";
	for(var i=0;i<data.length;i++){
		addOption(sltId, data[i][keyName], data[i][valueName]);
	}
	if(selectedValue){
		selectOption(sltId, selectedValue);
	}
}
/**
 * 添加下拉框内容
 * @param sltName select下拉框的name
 * @param index name为sltName对应控件数组的下标
 * @param data select下拉框的数据，为数组格式
 * @param keyName 默认取 value
 * @param valueName 默认取 text
 * @param clearAll 是否清除原有数据，true清除
 * @param showEmptyOptionFlag 当clearAll为true时有效，显示提示选项"--请选择--"
 * @param selectedValue 选中的值（单选）
 */
function addOptions2(sltName, index, data, keyName, valueName, clearAll, showEmptyOptionFlag, selectedValue){
	var slt = $n(sltName)[index];
	if(!slt)
		return ;
	addOptions(slt, data, keyName, valueName, clearAll, showEmptyOptionFlag, selectedValue);
}
/**
 * 选择下拉的值
 * @param sltId select下拉框的id或对象
 * @param val 选中的值（单选）
 */
function selectOption(sltId, val){
	var slt = ((typeof sltId == 'string')?$(sltId):sltId);
	var length = slt.options.length;
	for(var i=0;i<length;i++){
		if(slt.options[i].value == val){
			slt.options[i].selected = true;
		}
	}
}
/**
 * 选择下拉的值
 * @param sltId select下拉框的id或对象
 * @param val 选中的值（单选）
 */
function selectOptionByText(sltId, txt){
	var slt = ((typeof sltId == 'string')?$(sltId):sltId);
	var length = slt.options.length;
	for(var i=0;i<length;i++){
		if(slt.options[i].text == txt){
			slt.options[i].selected = true;
		}
	}
}
/**
 * 选中或取消列表全选复选框
 * @param chkallId 列表头全选checkbox的id
 * @param chkName 列表选中checkbox的name
 */
function checkAll(chkallId, chkName, multiSelectFlag) {
	if(multiSelectFlag){
		jQuery("#"+chkallId).change(function(){
			var obj = jQuery(this);
			jQuery("input[name='" + chkName + "']").each(function(){
				jQuery(this).attr("checked", obj.attr("checked"));
			});
		});
		jQuery("input[name='" + chkName + "']").each(function(){
			jQuery(this).bind("click", function(event){
				event.stopPropagation();
			});
		});
	}else{
		jQuery("input[name='" + chkName + "']").each(function(index){
			jQuery(this).bind("click", function(event){
				jQuery("input[name='" + chkName + "']").each(function(index2){
					if(index != index2){
						jQuery(this).attr("checked", false);
					}
				});
				event.stopPropagation();
			});
		});
		jQuery("#"+chkallId).attr("disabled",true);
	}
}
/**
 * 为列表添加换行样式
 * @param tblId tbody的id
 */
function altRowCSS(tblId){
	jQuery("#"+tblId+" tr").filter(":odd").each(function(){
		jQuery(this).addClass("row2");
	}).end().filter(":even").each(function(){
		jQuery(this).addClass("row1");
	}).end().hover(function(){
		jQuery(this).toggleClass("rowover");
	});
}
/**
 * 设置table间隔行以及鼠标划过样式
 * 处理checkbox的单选、多选，以及tr点击与checkbox的勾选、取消的关联
 * @param tblId 一般取tbody的id
 * @param chkallId 头部全部选择checkbox的id
 * @param chkName 列表行checkbox的name
 * @param multiSelectFlag 单选还是多选
 * @param rowSelectFun 勾选或者取消勾选时的回调方法
 * 			单选时,function(checked, index)	checked当前行是否选中，index当前行下标
 * 			多选时,function(curChecked, curIndex, checkedIndexArr)	curChecked当前行是否选中，curIndex当前行下标，checkedIndexArr选中行下标数字
 */
function checkTable(tblId, chkallId, chkName, multiSelectFlag, rowSelectFun, dblClickFun){
	//设置样式
	altRowCSS(tblId);
	//多选
	if(multiSelectFlag){
		jQuery("#"+chkallId).change(function(){
			var obj = jQuery(this);
			jQuery("input[name='" + chkName + "']").each(function(){
				jQuery(this).attr("checked", obj.attr("checked"));
			});
			if(rowSelectFun){
				rowSelectFun(jQuery(this).attr("checked"), -1);
			}
		});
		jQuery("input[name='" + chkName + "']").each(function(index){
			jQuery(this).bind("click", function(event){
				event.stopPropagation();
				if(rowSelectFun){
					rowSelectFun(jQuery(this).attr("checked"), index, getSelectIndexs(chkName));
				}
			});
		});
	}else{
		jQuery("input[name='" + chkName + "']").each(function(index){
			jQuery(this).bind("click", function(event){
				jQuery("input[name='" + chkName + "']").each(function(index2){
					if(index != index2){
						jQuery(this).attr("checked", false);
					}
				});
				event.stopPropagation();
				if(rowSelectFun){
					rowSelectFun(jQuery(this).attr("checked"), index);
				}
			});
		});
		jQuery("#"+chkallId).attr("disabled",true);
	}
	
	jQuery("#"+tblId+" tr").each(function(index){
		jQuery(this).bind("click", function(event){
			if(!jQuery("input[name='"+chkName+"']")[index])	return ;
			var currentChk = jQuery(jQuery("input[name='"+chkName+"']")[index]);
			if(currentChk.attr("disabled"))	return;
			currentChk.attr("checked", !currentChk.attr("checked"));
			if(currentChk.attr("checked") && !multiSelectFlag){
				jQuery("input[name='" + chkName + "']").each(function(index2){
					if(index != index2){
						jQuery(this).attr("checked", false);
					}
				});
			}
			event.stopPropagation();
			if(rowSelectFun){
				if(multiSelectFlag){
					rowSelectFun(currentChk.attr("checked"), index, getSelectIndexs(chkName));					
				}else{
					rowSelectFun(currentChk.attr("checked"), index);
				}
			}
		});
	});
	
	if(dblClickFun){
		jQuery("#"+tblId+" tr").each(function(index){
			jQuery(this).bind("dblclick", function(event){
				var currentChk = jQuery("input[name='"+chkName+"']")[index];
				if(currentChk){
                    jQuery(currentChk).attr("checked", true);
                }
				dblClickFun(index);
			});
		});
	}
}
/**
 * 创建列表分页工具条
 * @param formId form表单的id
 * @param start 开始页数通常取$!form.pager.start
 * @param limit 每页显示条数通常取$!form.pager.limit
 * @param totalCount 总条数通常取$!form.pager.totalCount
 */
function createPagingToolbar(formId, start, limit, totalCount){
	document.write("<table style='width:100%;'><tr><td style='BACKGROUND-COLOR: #D2E0F1;height:20px;' valign='center'>");
	//document.write("<table border='0' width='100%' height='100%' style='BACKGROUND-COLOR: #D2E0F1;'><tr><td>");
	document.write("<input type='hidden' id='start' name='start' value=''/>");
	document.write("<input type='hidden' id='limit' name='limit' value=''/>");
	document.write("<input type='hidden' id='_totalCount' value=''/>");
	document.write("<input type='image' id='_btnFirst'   src='" + ctxPath + "/css/mclon/img/page-first.gif' style='vertical-align:text-bottom;' title='第一页'>&nbsp;");
	document.write("<input type='image' id='_btnPre'     src='" + ctxPath + "/css/mclon/img/page-prev.gif'  style='vertical-align:text-bottom;' title='上一页'>&nbsp;");
	document.write("当前第<input type='text' id='_pageindex' style='width:25px;height:12px;text-align:center;' value=''>页&nbsp;");
	document.write("共<input type='text' id='_totalPageCount' style='width:40px;height:12px;text-align:center;background-color:#EBEBEB;' value='' readonly>页&nbsp;");
	document.write("<input type='image' id='_btnNext'    src='" + ctxPath + "/css/mclon/img/page-next.gif'  style='vertical-align:text-bottom;' title='下一页'>&nbsp;");
	document.write("<input type='image' id='_btnLast'    src='" + ctxPath + "/css/mclon/img/page-last.gif'  style='vertical-align:text-bottom;' title='最后页'>&nbsp;");
	document.write("<input type='image' id='_btnRefresh' src='" + ctxPath + "/css/mclon/img/refresh.gif'    style='vertical-align:text-bottom;' title='刷新'>&nbsp;");
	
	document.write("每页<input type='text' id='_pageCount' style='width:25px;height:12px;text-align:center;' value=''>条&nbsp;");
	document.write("显示第 "+start+" 条到 "+(start + limit - 1)+" 条记录，一共 "+totalCount+" 条");
	document.write("</td></tr></table>");
	
	jQuery("#start").val(start);
	jQuery("#limit").val(limit);
	jQuery("#_pageindex").val(Math.ceil(start / limit));
	jQuery("#_totalPageCount").val(Math.ceil(totalCount / limit));
	jQuery("#_pageCount").val(limit);
	jQuery("#_totalCount").val(totalCount);
	
	if(parseInt(jQuery("#_pageindex").val())<=1){
		jQuery("#_btnPre").attr('src', "" + ctxPath + "/css/mclon/img/page-prev-disabled.gif");
		jQuery("#_btnFirst").attr("src", "" + ctxPath + "/css/mclon/img/page-first-disabled.gif");
		
		jQuery("#_btnPre").attr('disabled', true);
		jQuery("#_btnFirst").attr("disabled", true);
	}
	if(parseInt(jQuery("#_pageindex").val())>=parseInt(jQuery("#_totalPageCount").val())){
		jQuery("#_btnNext").attr('src', ctxPath + "/css/mclon/img/page-next-disabled.gif");
		jQuery("#_btnLast").attr("src", ctxPath + "/css/mclon/img/page-last-disabled.gif");
		
		jQuery("#_btnNext").attr('disabled', true);
		jQuery("#_btnLast").attr("disabled", true);
	}
	/**
	* 改变页数
	*/
	jQuery("#_pageindex").change(function(){
		if(isNumeric(jQuery("#_pageindex").val())){
	       	jQuery("#start").val((parseInt(jQuery("#_pageindex").val()) - 1) * parseInt(jQuery("#_pageCount").val()) + 1);
			jQuery("#"+formId).submit();
		}else{
			jQuery("#_pageindex").val("");
		}
		
	});
	/**
	* 改变每页显示条数
	*/
	jQuery("#_pageCount").change(function(){
		if(isNumeric(jQuery("#_pageCount").val())){
	    	jQuery("#start").val("1");
	    	jQuery("#limit").val(jQuery("#_pageCount").val());
	    	jQuery("#"+formId).submit();
		}else{
			jQuery("#_pageCount").val("");
		}
	});
	jQuery("#_totalPageCount").focus(function(){
		jQuery(this).blur();
	});
	jQuery("#_btnFirst").click(function(){
		jQuery("#start").val("1");
		jQuery("#"+formId).submit();
	});
	jQuery("#_btnPre").click(function(){
		if(parseInt(jQuery("#start").val()) - parseInt(jQuery("#limit").val()) - 1 >= 0){
			//jQuery("#_pageindex").val(parseInt(jQuery("#_pageindex").val()) - 1);
	    	jQuery("#start").val(parseInt(jQuery("#start").val()) - parseInt(jQuery("#limit").val()));
	    	jQuery("#"+formId).submit();
		}
	});
	jQuery("#_btnLast").click(function(){
		jQuery("#start").val((Math.ceil(totalCount / limit)-1)*parseInt(jQuery("#_pageCount").val()) + 1);
		jQuery("#"+formId).submit();
	});
	jQuery("#_btnNext").click(function(){
		if(parseInt(jQuery("#start").val()) + parseInt(jQuery("#limit").val()) - 1 <= totalCount){
	    	//jQuery("#_pageindex").val(parseInt(jQuery("#_pageindex").val()) + 1);
	    	jQuery("#start").val(parseInt(jQuery("#start").val()) + parseInt(jQuery("#limit").val()));
	    	jQuery("#"+formId).submit();
		}
	});
	jQuery("#_btnRefresh").click(function(){
    	jQuery("#"+formId).submit();
	});
}

/**
 * 检查输入字符串是否符合正整数格式
 */
function isNumber(s) {
    var s1 = s + "";
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s1.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}

/**
 * 检查输入对象的值是否符合整数格式
 */
function isInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}

/**
 * 检查输入字符串是否是带小数的数字格式,可以是负数
 */
function isDecimal(str) {
	if (isInteger(str))
		return true;
//	var re = /^[-]{0,1}(\d+)[\.]{0,1}(\d+)$/;
	var reg = /^((-?((0\.[0-9]+)|([1-9][0-9]*\.[0-9]+)|([1-9]+[0-9]*)))|0)$/;
	if (reg.test(str)) {
	//	if (RegExp.$1 == 0 && RegExp.$2 == 0)
	//		return false;
		return true;
	} else {
		return false;
	}
}

/**
 * 浮点数加法运算
 */
function floatAdd(arg1, arg2) {
	arg1 = "" + arg1;
	arg2 = "" + arg2;
	var r1 = 0, r2 = 0;
	if( arg1.split(".").length>1){
		r1 = arg1.split(".")[1].length;
	}
	if(arg2.split(".").length>1){
		r2 = arg2.split(".")[1].length;
	}
	var m = Math.pow(10, Math.max(r1, r2));
	//return Number(((arg1 * m + arg2 * m) / m).toFixed(Math.max(r1, r2)));
	return (floatMul(arg1,m) + floatMul(arg2,m)) / m;
}
/**
 * 浮点数减法运算
 */
function floatSub(arg1, arg2) {
	arg1 = "" + arg1;
	arg2 = "" + arg2;
	var r1 = 0, r2 = 0;
	if(arg1.split(".").length>1){
		r1 = arg1.split(".")[1].length;
	}
	if(arg2.split(".").length>1){
		r2 = arg2.split(".")[1].length;
	}
	var m = Math.pow(10, Math.max(r1, r2));
	//return Number(((arg1 * m - arg2 * m) / m).toFixed(Math.max(r1, r2)));
	return (floatMul(arg1,m) - floatMul(arg2,m)) / m;
}
/**
 * 浮点数乘法运算
 */
function floatMul(arg1, arg2) {
	arg1 = "" + arg1;
	arg2 = "" + arg2;
	var m = 0;
	if(arg1.split(".").length>1){
		m = arg1.split(".")[1].length;
	}
	if(arg2.split(".").length>1){
		m += arg2.split(".")[1].length;
	}
	return Number(arg1.replace(".", "")) * Number(arg2.replace(".", "")) / Math.pow(10, m);
}
/**
 * 浮点数除法运算
 */
function floatDiv(arg1, arg2) {
	arg1 = "" + arg1;
	arg2 = "" + arg2;
	var r1 = 0, r2 = 0;
	if(arg1.split(".").length>1){
		r1 = arg1.split(".")[1].length;
	}
	if(arg2.split(".").length>1){
		r2 = arg2.split(".")[1].length;
	}
	return floatMul(Number(arg1.replace(".", "")) / Number(arg2.replace(".", "")), Math.pow(10, r2 - r1));
}
/**
 * 检查单据页面是否审批
 */
function checkReview(){
	var params = parseURLParam();
	if(params['reviewflag'] == '1'){
		jQuery(".control").hide();
		//按钮失效
		jQuery("input[type='button']").each(function(){
			jQuery(this).attr("disabled", true);
		});
		//文本框只读
		jQuery("input[type='text']").each(function(){
			jQuery(this).attr("readonly", true);
		});
		jQuery("input[type='checkbox']").each(function(){
			jQuery(this).attr("disabled", true);
		});
		//下拉框失效
		jQuery("select").each(function(){
			jQuery(this).attr("disabled", true);
		});
	}
}
/**
 * 根据页面连接获取参数信息对象
 */
function parseURLParam() {
	var href = decodeURI(window.location.href);
	var ret = {};
	var mark = href.indexOf('?');
	
	var subUrl = href.substring(mark + 1);
	var urlArray = subUrl.split('&');
	for (var i = 0; i < urlArray.length; i++) {
		if (urlArray[i]) {
			var param = urlArray[i].split('=');
	    	ret[param[0]] = param[1];
		}
	}
	return ret;
}
/**
 * 取通过URL传过来的参数 (格式如 ?Param1=Value1&Param2=Value2)
function getUrlParams() {
    var urlParams = new Object() ;
    var aParams = document.location.search.substr(1).split('&') ;
    for (var i = 0; i < aParams.length; i++) {
        var aParam = aParams[i].split('=') ;
        urlParams[aParam[0]] = aParam[1];
    }
    return urlParams;
}
 */
jQuery.fn.clearForm = function(){
	return this.each(function() {
            var type = this.type, tag = this.tagName.toLowerCase();
            if (tag == 'form')
              return jQuery(':input',this).clearForm();
            if (type == 'text' || type == 'password' || tag == 'textarea')
              this.value = '';
            else if (type == 'checkbox' || type == 'radio')
              this.checked = false;
            else if (tag == 'select')
              this.selectedIndex = 0;
      });
};
/**
 * 判断当前浏览器是否为IE
 */
function isIE(){
	return navigator.userAgent.indexOf("MSIE")>0;
}
/**
 * 为 Array 类增加一个 max 方法
 */
Array.prototype.max = function(){
    var i, max = this[0];
    for (i = 1; i < this.length; i++){
        if (max < this[i])
            max = this[i];
    }
    return max;
};
/**
 * 为字符串增加trim方法
 */
String.prototype.trim = function(){
    // 用正则表达式将前后空格用空字符串替代。
    return this.replace(/(^\s*)|(\s*$)/g, "");
};
/**
 * 为 Array 类增加一个 contains方法
 */
Array.prototype.contains = function(str) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] === str)
            return true;
    }
    return false;
};
/**
 * 执行正则表达式
 */
function executeExp(re, s){
    return re.test(s);
}
/**
 * 判断是否为空
 * null，undefined，空字符串""，空格字符串" "，都返回false
 */
function isNull(str){
	if(!str){
		return true;
	}
	if(!str.trim()){
		return true;
	}
	return false;
}
/**
 * 判断是否是字母、数字或者为空
 */
function isAlphaNumeric(strValue){
    // 只能是 A-Z a-z 0-9 之间的字母数字 或者为空
    return executeExp(/^\w*$/gi, strValue);
}
/**
 * 判断是否是正确的日期，格式为2003-12-12
 */
function isDate(strValue){
    if (isEmpty(strValue)) return true;
    if (!executeExp(/^\d{4}-[01]?\d-[0-3]?\d$/g, strValue)) return false;
    var arr = strValue.split("-");
    var year = arr[0];
    var month = arr[1];
    var day = arr[2];
    // 1 <= 月份 <= 12，1 <= 日期 <= 31
    if (!( ( 1 <= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ))
        return false;
    // 润年检查
    if (!( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ))
        return false;
    // 7月以前的双月每月不超过30天
    if (( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ))
        return false;
    // 8月以后的单月每月不超过30天
    if (( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ))
        return false;
    // 2月最多29天
    if (( month == 2) && ( day >= 30 ))
        return false;
    return true;
}
/**
 * 判断是否是正确的Email
 */
function isEmail(strValue){
    if (isEmpty(strValue)) return true;
    var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    return executeExp(pattern, strValue);
}
/**
 * 判断字符串是否为空
 */
function isEmpty(strValue){
    if (strValue == null || strValue == "")
        return true;
    else
        return false;
}
/**
 * 判断是否为数字
 */
function isNumeric(strValue){
    return executeExp(/^\d*$/g, strValue);
}
/**
 * 判断是否为浮点数（不带正负号）
 */
function isNumberFloat(strValue){
    if (isEmpty(strValue)) return true;
    return executeExp(/^\d+(\.\d+)?$/, strValue);
    //return (!isNaN(parseFloat(strValue))) ? true : false;
}
/**
 * 判断是否是货币
 */
function isMoney(strValue){
    if (isEmpty(strValue)) return true;
    return executeExp(/^[+-]?\d+(,\d{3})*(\.\d+)?$/g, strValue);
}
/**
 * 判断是否为手机号码
 */
function isMobile(strValue){
    if (isEmpty(strValue)) return false;
    return executeExp(/^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|154|155|156|157|158|159|180|181|182|183|184|185|186|187|188|189)\d{8}$/, strValue);
}
/**
 * 判断是否为电话
 */
function isPhone(strValue){
    if (isEmpty(strValue)) return true;
    return executeExp(/(^\(\d{3,5}\)\d{6,8}(-\d{2,8})?$)|(^\d+-\d+$)|(^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|154|155|156|157|158|159|180|181|182|183|184|185|186|187|188|189)\d{8}$)/g, strValue);
}
/**
 * 判断是否为邮政编码
 */
function isPostalCode(strValue){
	if (isEmpty(strValue)) return true;
    return executeExp(/(^$)|(^\d{6}$)/gi, strValue);
}
/**
 * 判断是否为合法的URL
 */
function isURL(strValue){
    if (isEmpty(strValue)) return true;
    var pattern = /^(http|https|ftp):\/\/(\w+\.)+[a-z]{2,3}(\/\w+)*(\/\w+\.\w+)*(\?\w+=\w*(&\w+=\w*)*)*/gi;
    return executeExp(pattern, strValue);
}
/**
 * 刷新当前窗口
 */
function refresh() {
	document.location.reload();
}

/**
 * 把json对象转成json字符串
 */
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
function printStyle(styleId, styleName, bigGraph){
	if(isNull(bigGraph)){
		return styleName;
	}
	return "<a href='javascript:viewStyle("+styleId+")'>" + styleName + "</a>";
}

function resetWinSize(){
	var dw = jQuery(document).width();
	var dw2 = jQuery(".list").width();
	var dh2 = jQuery(".list").height();
	var dh = jQuery(document).height();
	var dh3 = jQuery(".control").height();
	var newHeight = dh-dh3-27;
	if(dw2<dw){
		if(dh2 > newHeight){
			dw = dw - 18;
		}
		jQuery(".list").css("width", dw+"px");
	}
	//info(dh+"-"+dh2);
	//info(dh3);
	jQuery(".scroll").css("height", newHeight+"px");
	
}

function toComplete(){
	info('待完成');
}

/**
 * 四舍五入 默认2位小数
 * @param {} decimal
 * @param {} num 默认2
 * @return {}
 */
function roundDecimal(decimal, num) {
    num = num||2;
    decimal = decimal||0;
    return floatDiv(Math.round(decimal*Math.pow(10, num)), Math.pow(10, num));
}

/**
 * 浮点数除法运算
 */
function floatDiv2(arg1, arg2, n) {
    arg1 = "" + arg1;
    arg2 = "" + arg2;
    var r1 = 0, r2 = 0;
    if(arg1.split(".").length>1){
        r1 = arg1.split(".")[1].length;
    }
    if(arg2.split(".").length>1){
        r2 = arg2.split(".")[1].length;
    }
    var res = floatMul(Number(arg1.replace(".", "")) / Number(arg2.replace(".", "")), Math.pow(10, r2 - r1));
    if(n && isNumber(n)) {
        return Math.round(res*Math.pow(10, n))/Math.pow(10, n);
    }else{
        return res;
    }
}

/**
 函数：把字符串转换为日期对象
 参数：yyyy-mm-dd或dd/mm/yyyy形式的字符串
 返回：Date对象
 注：IE下不支持直接实例化日期对象，如new Date("2011-04-06")
 */
Date.prototype.convertDate = function (date) {
    var flag = true;
    var dateArray = date.split("-");
    if (dateArray.length != 3) {
        dateArray = date.split("/");
        if (dateArray.length != 3) {
            return null;
        }
        flag = false;
    }
    var newDate = new Date();
    if (flag) {
        // month从0开始
        newDate.setFullYear(dateArray[0], dateArray[1] - 1, dateArray[2]);
    } else {
        newDate.setFullYear(dateArray[2], dateArray[1] - 1, dateArray[0]);
    }
    newDate.setHours(0, 0, 0);
    return newDate;
};

/**
 函数：计算两个日期之间的差值
 参数：date是日期对象
 flag：ms-毫秒，s-秒，m-分，h-小时，d-天，M-月，y-年
 返回：当前日期和date两个日期相差的毫秒/秒/分/小时/天
 */
Date.prototype.dateDiff = function (date, flag) {
    var msCount;
    var diff = this.getTime() - date.getTime();
    switch (flag) {
        case "ms":
            msCount = 1;
            break;
        case "s":
            msCount = 1000;
            break;
        case "m":
            msCount = 60 * 1000;
            break;
        case "h":
            msCount = 60 * 60 * 1000;
            break;
        case "d":
            msCount = 24 * 60 * 60 * 1000;
            break;
    }
    return Math.floor(diff / msCount);
};

/**
 * 计算2个时间相差天数
 * @param date1 时间字符串 “2012-10-10”
 * @param date2 时间字符串 “2012-10-10”
 */
function diffDate(date1, date2) {
    var dateObj1 = new Date().convertDate(date1);
    var dateObj2 = new Date().convertDate(date2);
    return dateObj1.dateDiff(dateObj2, 'd');
}


/**
 * 自定義map
 */
function Map(){
    this.size=0;
    this.keys=new Array();
    this.values=new Array();
    this.get = function(key) {
        for (var i = 0; i < this.size; i++) {
            if (key == this.keys[i]) {
                return this.values[i];
            }
        }
    };
    this.getValueByIndex = function(index) {
        if (index > 0 && index < this.size) {
            return this.values[index];
        } else {
            return null;
        }
    };
    this.getKeyByIndex = function(index) {
        if (index > 0 && index < this.size) {
            return this.keys[index];
        } else {
            return null;
        }
    };
    this.put = function(key, value) {
        for (var i = 0; i < this.size; i++) {
            if (key == this.keys[i]) {
                alert("key is repeat");
                return null;
            }
        }
        this.keys[this.size] = key;
        this.values[this.size] = value;
        this.size++;
    };
    this.remove = function(key) {
        for (var i = 0; i < this.size; i++) {
            if (key == this.keys[i]) {
                for (var j = i; j < this.size; j++) {
                    this.keys[j] = this.keys[j + 1];
                    this.values[j] = this.values[j + 1];
                }
                break;
            }
        }
        this.size--;
    };
    this.removeAll = function() {
        this.keys = new Array();
        this.values = new Array();
    };
}
