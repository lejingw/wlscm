function editBill(){
	var idxs = getSelectIndexs('chk');
	if(idxs.length != 1){
		alert("请选择要修改的一条记录");
		return ;
	}
	showEidtDialog(idxs[0]);
}
function showEidtDialog(index){
	jQuery("#headid").val($n("chk")[index].value);
	jQuery("#orgtypeName").val($n("hd_orgtypeName")[index].value);
	DisplayStandardDwr.getDisplayStandardList(jQuery("#headid").val(), function(datas){
//		info(datas.length +"---"+jQuery("#headid").val());
		removeAllRows('tbl2');
		for(var i=0;i<datas.length;i++){
			addSalableStyleRow(datas[i]);
		}
	});
	showDisplayStandard();
}
function addBill(){
	jQuery("#headid").val("");
	jQuery("#orgtypeName").val("");
	showDisplayStandard();
}
function showDisplayStandard(){
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions("itemClassId", data, null, null, true, true);
	});
	removeAllOptions("ornaClassId", true);
	removeAllOptions("analysisId", true);
	removeAllOptions("styleItemClassId", true);
	jQuery("#orgtypeDiv").dialog({
			title:'畅销款陈列标准设置',
			width:900, 
			height:500,
			modal:true,
			zIndex:1,
			close:function(){
				if(refreshFlag){
            		window.location = "displayStandard.vm";
            	}else{
                	jQuery('#orgtypeDiv').dialog('close');
            	}
			}
		});
}
/**
 * 改变大类
 */
function changeOrnaClassId(){
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
		addOptions("ornaClassId", data, null, null, true, true);
		changeOrnaClass();
	});
}
/**
 *　改变小类
 */
function changeOrnaClass(){
	//获取分析范围
	BdCommonDwr.getAnalysisForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), function(data){
		addOptions("analysisId", data, null, null, true, true);
	});
	//获取款式大类
	BdCommonDwr.getStyleItemClassForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), function(data){
		addOptions("styleItemClassId", data, null, null, true, true);
	});
}
function selectStyle(){
	if(!jQuery("#itemClassId").val() || !jQuery("#ornaClassId").val()
			 || !jQuery("#analysisId").val()  || !jQuery("#styleItemClassId").val()){
		alert("请先选择大类、小类、分析范围、款式大类");
		return ;
	}
	jQuery("#selectStyleDiv").dialog({
			width : 700, 
			height : 300,
			modal:true,
			zIndex : 2,
			open: function(event, ui) {
				loadSelectStyleLines(1, 5);
			},
			beforeClose: function(event, ui) {
				return true;
			}
		});
	jQuery("#selectStyleDiv").dialog("option", "buttons", [{
			text:"确定",
			click:function(){
				var data = getSelectStyleRowData();
				for(var i=0,j=data.length;i<j;i++){
					addSalableStyleRow(data[i]);
				}
				jQuery(this).dialog("close");
			}
		},{
			text:"取消",
			click:function(){jQuery(this).dialog("close");}
		}
	]);
}
function getSelectStyleRowData(){
	var data = [];
	var selectChkArr = $n("selectStyleChk");
	for(var i=0, j=selectChkArr.length;i<j;i++){
		if(!selectChkArr[i].checked)
			continue ;
		if(isExistsStyle($n("selectStyleId")[i].value))
			continue ;
		data.push({
			lineid				: "",
			itemClassId			: jQuery("#itemClassId").val(),
			itemClassName		: jQuery("#itemClassId").find('option:selected').text(),
			ornaClassId			: jQuery("#ornaClassId").val(),
			ornaClassName		: jQuery("#ornaClassId").find('option:selected').text(),
			analysisId			: jQuery("#analysisId").val(),
			analysisName		: jQuery("#analysisId").find('option:selected').text(),
			styleItemClassId	: jQuery("#styleItemClassId").val(),
			styleItemClassName	: jQuery("#styleItemClassId").find('option:selected').text(),
			
			styleMiddleClassId	: $n("selectStyleMiddleClassId")[i].value,
			styleMiddleClassName: $n("selectStyleMiddleClassName")[i].value,
			styleOrnaClassId	: $n("selectStyleOrnaClassId")[i].value,
			styleOrnaClassName	: $n("selectStyleOrnaClassName")[i].value,
			styleId				: $n("selectStyleId")[i].value,
			styleName			: $n("selectStyleName")[i].value,
			bigGraph			: $n("selectBigGraph")[i].value,
			minCount			: "0",
			maxCount			: "0"
		});
	}
	return data;
}
/**
 * 加载款式行
 */
function loadSelectStyleLines(start, limit){
	var headid = jQuery("#headid").val();
	jQuery("#selectStyleChkAll").attr("checked", false);
	removeAllRows('selectStyleTbl');
	DisplayStandardDwr.getSelectStylePageData(headid, jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(),
			jQuery("#analysisId").val(), jQuery("#styleItemClassId").val(), start, limit, function(pager){
		var pageData = pager.pageData;
		var totalCount = pager.totalCount;
		for(var i=0,j=pageData.length;i<j;i++){
			addSelectStyleRow(pageData[i]);
		}
		createPagingToolbar2(function(s, l){
			loadSelectStyleLines(s, l);
		}, pager.start, pager.limit, totalCount);
	});
}
function removeAllRows(tblId){
	for(var i=$(tblId).rows.length-1;i>=0;i--){
		$(tblId).deleteRow(i);
	}
}
/**
 * 添加选择款式行
 * @param data
 */
function addSelectStyleRow(data){
	var htmlArr = [];
	var checkFlag = isExistsStyle(data.styleId);
	htmlArr.push("<input type='checkbox' name='selectStyleChk'" + (checkFlag?" checked":"") + ">"+
			"<input type='hidden' name='selectStyleMiddleClassId' value='"+data.styleMiddleClassId+"'>"+
			"<input type='hidden' name='selectStyleMiddleClassName' value='"+data.styleMiddleClassName+"'>"+
			"<input type='hidden' name='selectStyleOrnaClassId' value='"+data.styleOrnaClassId+"'>"+
			"<input type='hidden' name='selectStyleOrnaClassName' value='"+data.styleOrnaClassName+"'>"+
			"<input type='hidden' name='selectStyleId' value='"+data.styleId+"'>"+
			"<input type='hidden' name='selectStyleName' value='"+data.styleName+"'>"+
			"<input type='hidden' name='selectBigGraph' value='"+data.bigGraph+"'>"+
			"<input type='hidden' name='selectStockNum' value='"+data.stockNum+"'>"+
			"<input type='hidden' name='selectYearNum' value='"+data.yearNum+"'>"+
			"<input type='hidden' name='selectHalfYearNum' value='"+data.halfYearNum+"'>"+
			"<input type='hidden' name='selectHalfYearAvgNum' value='"+data.halfYearAvgNum+"'>"+
			"<input type='hidden' name='selectMonthNum' value='"+data.monthNum+"'>");
	htmlArr.push(data.styleMiddleClassName);
	htmlArr.push(data.styleOrnaClassName);
	htmlArr.push(data.bigGraph?"<a href='javascript:viewStyle("+data.styleId+")'>" + data.styleName + "</a>":data.styleName);//款式
	htmlArr.push(data.yearNum);//年销售
	htmlArr.push(data.halfYearNum);//半年销售
	htmlArr.push(data.monthNum);//上月销售
	insertRow("selectStyleTbl", htmlArr);
}
/**
 * 判断款式是否已经存在
 * @param styleId
 */
function isExistsStyle(styleId){
    var analysisId = jQuery("#analysisId").val();
	var itemArr = $n("styleId");
	for(var i=0,j=itemArr.length;i<j;i++){
		if(styleId == itemArr[i].value && analysisId == $n("analysisId")[i].value){
			return true;
		}
	}
	return false;
}
function addSalableStyleRow(data){
	var htmlArr = [];
	htmlArr.push("");
	htmlArr.push("<input type='hidden' name='lineid' value='" + data.lineid + "'><input type='hidden' name='itemClassId' value='"+data.itemClassId+"'>" + data.itemClassName);
	htmlArr.push("<input type='hidden' name='ornaClassId' value='"+data.ornaClassId+"'>" + data.ornaClassName);
	htmlArr.push("<input type='hidden' name='analysisId' value='"+data.analysisId+"'>" + data.analysisName);
	htmlArr.push("<input type='hidden' name='styleItemClassId' value='"+data.styleItemClassId+"'>" + data.styleItemClassName);
	
	htmlArr.push("<input type='hidden' name='styleMiddleClassId' value='"+data.styleMiddleClassId+"'>" + data.styleMiddleClassName);//款式中类
	htmlArr.push("<input type='hidden' name='styleOrnaClassId' value='"+data.styleOrnaClassId+"'>" + data.styleOrnaClassName);//款式小类
	htmlArr.push("<input type='hidden' name='styleId' value='"+data.styleId+"'>" + (data.bigGraph?"<a href='javascript:viewStyle("+data.styleId+")'>" + data.styleName + "</a>":data.styleName));//款式
	htmlArr.push("<input type='hidden' name='pre_minCount' value='"+data.minCount+"'><input type='text' name='minCount' style='width:45px;' value='"+data.minCount+"'>");//最小量
	htmlArr.push("<input type='hidden' name='pre_maxCount' value='"+data.maxCount+"'><input type='text' name='maxCount' style='width:45px;' value='"+data.maxCount+"'>");//最大量
	htmlArr.push("<input type='button' class='btn' value='删除' onclick='deleteStyleRow(this)'>");
	insertRow("tbl2", htmlArr, false);
	resetIndex();
}
function resetIndex(){
	var len = $('tbl2').rows.length;
	for(var i=0;i<len;i++){
		$('tbl2').rows[i].cells[0].innerHTML = len - i;
	}
}
var deleteIdArr = [];
/**
 * 删除行
 */
function deleteStyleRow(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNull($n("lineid")[index].value)){
		deleteIdArr.push($n("lineid")[index].value);
	}
	deleteRow(obj, "tbl2");
	resetIndex();
}
/**
 * 保存
 */
function saveDisplayStandard(){
	var len = $('tbl2').rows.length;
	for(var i=0;i<len;i++){
		if(!isNumber($n("minCount")[i].value) || !isNumber($n("maxCount")[i].value)){
			alert("第"+(i+1)+"行 最小量、最大量不能为空，且必须为数字类型");
			return false;
		}
		if(parseInt($n("minCount")[i].value) > parseInt($n("maxCount")[i].value)){
			alert("第"+(i+1)+"行 最小量不能大于最大量");
			return false;
		}
	}
	confirm("确定保存?", function(){
		showLayer(true);
		var addArr = [];
		var updateArr = [];
		var len = $('tbl2').rows.length;
		for(var i=0;i<len;i++){
			if(isNull($n("lineid")[i].value)){
				addArr.push({
							lineid : $n("lineid")[i].value,
							itemClassId : $n("itemClassId")[i].value,
							ornaClassId : $n("ornaClassId")[i].value,
							analysisId : $n("analysisId")[i].value,
							styleItemClassId : $n("styleItemClassId")[i].value,
							styleMiddleClassId : $n("styleMiddleClassId")[i].value,
							styleOrnaClassId : $n("styleOrnaClassId")[i].value,
							styleId : $n("styleId")[i].value,
							minCount : $n("minCount")[i].value,
							maxCount : $n("maxCount")[i].value
						});
			}else if(($n("pre_minCount")[i].value != $n("minCount")[i].value) || ($n("pre_maxCount")[i].value != $n("maxCount")[i].value)){
				updateArr.push({
					lineid:$n("lineid")[i].value,
					minCount:$n("minCount")[i].value,
					maxCount:$n("maxCount")[i].value
				});
			}
		}
    	DisplayStandardDwr.saveOrUpdateDisplayStandard(jQuery("#headid").val(), addArr, updateArr, deleteIdArr, function(data){
    		showLayer(false);
    		alert(data?data:"保存成功", function(){
    			if(!data){
                	window.location = "displayStandard.vm";
    			}
    		});
    	});
	});
}