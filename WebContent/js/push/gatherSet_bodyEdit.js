/**
 * 显示添加类别窗口
 */
function addNumberRow(){
	jQuery("#addItemDiv").dialog({
    		width : 450,
    		height : 180,
			modal:true,
			open : function(event, ui) {
                var isLoveStyle = jQuery("#isLoveStyle").val();
				GatherSetDwr.getItemClassForSlt(jQuery("#headid").val(), function(data){
					addOptions('itemClassId', data, null, null, true, true);
				});
				removeAllOptions('ornaClassId',true);
				removeAllOptions('analysisId',true);
				removeAllOptions('styleItemClassId',true);
                if("1" == isLoveStyle) {
                    jQuery("#analysisId").attr("disabled", true);
                    jQuery("#analysisId").removeAttr("dataType");
                }
			},
			close:function(){
				if(refreshFlag){
					window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
            	}else{
                	jQuery('#addItemDiv').dialog('close');
            	}
			}
		});
}
function changeItemClassId(){
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
		addOptions('ornaClassId', data, null, null, true, true);
		changeOrnaClassId();
	});
}
function changeOrnaClassId(){
    var isLoveStyle = jQuery("#isLoveStyle").val();
	BdCommonDwr.getAnalysisForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), function(data){
		addOptions('analysisId', data, null, null, true, true);
	});
	BdCommonDwr.getPushStyleItemClassForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), isLoveStyle, function(data){
        addOptions('styleItemClassId', data, null, null, true, true);
	});
}
/**
 * 添加类别
 */
function saveGatherSetLine(){
	if(!Validator.Validate($("addItemFrm"),3))
		return;
	if(!isNumber(jQuery("#orderNum").val()) || parseFloat(jQuery("#orderNum").val())<=0){
		alert("下单数量必须为正整数类型");
		return ;
	}
	confirm("确定保存", function(){
		showLayer(true);
		GatherSetDwr.saveGatherSetLine({
			headid : jQuery("#headid").val(),
			itemClassId : jQuery("#itemClassId").val(),
			ornaClassId : jQuery("#ornaClassId").val(),
			analysisId : jQuery("#analysisId").val(),
			styleItemClassId : jQuery("#styleItemClassId").val(),
			orderNum : jQuery("#orderNum").val()
		}, function(data){
    		showLayer(false);
    		alert(data?data:"保存成功", function(){
    			if(!data){
    				window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
    			}
    		});
    	});
	});
}
var refreshFlag = false;
/**
 * 保存并复制类别
 */
function saveGatherSetLine2(){
	if(!Validator.Validate($("addItemFrm"),3))
		return;
	if(!isNumber(jQuery("#orderNum").val()) || parseFloat(jQuery("#orderNum").val())<=0){
		alert("下单数量必须为正整数类型");
		return ;
	}
	confirm("确定保存并复制", function(){
		showLayer(true);
		GatherSetDwr.saveGatherSetLine({
			headid : jQuery("#headid").val(),
			itemClassId : jQuery("#itemClassId").val(),
			ornaClassId : jQuery("#ornaClassId").val(),
			analysisId : jQuery("#analysisId").val(),
			styleItemClassId : jQuery("#styleItemClassId").val(),
			orderNum : jQuery("#orderNum").val()
		}, function(data){
    		showLayer(false);
    		alert(data?data:"保存成功", function(){
    			refreshFlag = true;
//    			if(!data){
//    				window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
//    			}
    		});
    	});
	});
}
/**
 * 删除类别
 */
function deleteGatherSetLine(lineid){
	confirm("确定删除", function(){
		showLayer(true);
		GatherSetDwr.deleteGatherSetLine(lineid, function(data){
    		showLayer(false);
    		alert(data?data:"删除成功", function(){
    			if(!data){
    				window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
    			}
    		});
    	});
	});
}
/**
 * 显示修改下单数量窗口
 */
function showOrderNumWin(obj, lineid) {
	jQuery("#orderNumDiv").dialog({
		width : 300,
		height : 150,
		modal : true,
		zIndex : 1,
		open : function(event, ui) {
			jQuery("#editLineId").val(lineid);
			var orderNum = jQuery("#divLineOrderNum" + lineid).html();
			jQuery("#newOrderNum").val(orderNum);
		}
	});
}
/**
 * 修改行上的下单数量
 */
function updateLineOrderNum() {
	if(!isNumber(jQuery("#newOrderNum").val()) || parseFloat(jQuery("#newOrderNum").val())<=0){
		alert("下单数量必须为正整数类型");
		return ;
	}
	confirm("确定保存", function(){
		showLayer(true);
		GatherSetDwr.updateLineOrderNum(jQuery("#editLineId").val(), jQuery("#newOrderNum").val(), function(data){
    		showLayer(false);
    		alert("1" == data[0]?"保存成功<br>"+data[1]:data[1], function(){
    			window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
    		});
    	});
	});
}
function removeAllRows(tblId){
	for(var i=$(tblId).rows.length-1;i>=0;i--){
		$(tblId).deleteRow(i);
	}
}
/**
 * 显示配置页面
 */
function showItemsRateWin(obj, lineid, salableNum, unsalableNum, realSalableOrderNum){
	jQuery("#configDiv").dialog({
			width:900, 
			height:500,
			modal:true,
			zIndex:1,
			open: function(event, ui) {
				jQuery("#configLineId").val(lineid);
				setSalableNumHtml(salableNum, unsalableNum, realSalableOrderNum);
				loadGatherStyleLine();
				loadGatherGradeLine();
				loadGatherSizeLine();
			},
			close:function(){
				window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
			}
		});
}
var qualitySelectHtml = null;
var uniqueQualityId = "";
var saleDeleteIdArr = null;
var unsaleDeleteIdArr = null;
function loadGatherStyleLine(){
	removeAllRows('tbl1');
	removeAllRows('tbl2');
	var lineid = jQuery("#configLineId").val();
	qualitySelectHtml = "<option value=''>--请选择--</option>";
	saleDeleteIdArr = [];
	unsaleDeleteIdArr = [];
	// 获取材质
	GatherSetDwr.getQualitySelectOption(lineid, function(data){
		for(var i=0,j=data.length;i<j;i++){
			//j = 1;
			if(j == 1){
				uniqueQualityId = data[i].value;
				qualitySelectHtml = "<option value='" + data[i].value + "' >" + data[i].text + "</option>";				
			}else{		
				qualitySelectHtml += "<option value='" + data[i].value + "' >" + data[i].text + "</option>";				
			}
		}
		GatherSetDwr.getGatherSetStyle(lineid, function(data2){
			for(var i=0,j=data2.length;i<j;i++){
				if(data2[i].salableFlag == "1"){
					addSalableStyleRow(data2[i]);
				}else{
					addUnsalableStyleRow(data2[i]);
				}
			}
		});
	});
}
function loadGatherSaleStyleLine(){
	removeAllRows('tbl1');
	saleDeleteIdArr = [];
	GatherSetDwr.getGatherSetStyle(jQuery("#configLineId").val(), function(data){
		for(var i=0,j=data.length;i<j;i++){
			if(data[i].salableFlag == "1"){
				addSalableStyleRow(data[i]);
			}
		}
	});
}
function loadGatherUnsaleStyleLine(){
	removeAllRows('tbl2');
	unsaleDeleteIdArr = [];
	GatherSetDwr.getGatherSetStyle(jQuery("#configLineId").val(), function(data){
		for(var i=0,j=data.length;i<j;i++){
			if(data[i].salableFlag != "1"){
				addUnsalableStyleRow(data[i]);
			}
		}
	});
}
function addSalableStyleRow(data){
	var htmlArr = [];
	htmlArr.push("<input type='hidden' name='saleListid' value='"+data.listid+"'>" + ($("tbl1").rows.length+1));
	htmlArr.push("<input type='hidden' name='saleStyleId' value='"+data.styleId+"'>" + (data.picUrl?"<a href='javascript:viewStyle("+data.styleId+")'>" + data.styleName + "</a>":data.styleName));//款式
	htmlArr.push("<select style='width:90px;' name='saleQualityId' onchange='changeSaleQuality(this, true)'>"+qualitySelectHtml+"</select>");//材质
	htmlArr.push("<select style='width:60px;' name='saleBracketColorId'><option value=''>--请选择--</option></select>");//材质颜色
	htmlArr.push("<input type='text' name='saleRate' value='"+data.rate+"' onchange='checkSaleRate(this, true)' style='width:60px;'>");//比例
	htmlArr.push("<input type='hidden' name='saleStockNum' value='"+data.stockNum+"'>" + data.stockNum);//库存
	htmlArr.push("<input type='hidden' name='saleMonthNum' value='"+data.monthNum+"'>" + data.monthNum);//上月销售
	htmlArr.push("<input type='hidden' name='saleHalfYearNum' value='"+data.halfYearNum+"'>" + data.halfYearNum);//上半年销售
	htmlArr.push("<input type='hidden' name='saleHalfYearAvgNum' value='"+data.halfYearAvgNum+"'><input type='hidden' name='saleYearNum' value='"+data.yearNum+"'>" + data.yearNum);//上一年销售
	htmlArr.push("<input type='hidden' name='saleStyleCreateDate' value='"+data.styleCreateDate+"'>" + data.styleCreateDate);//创建时间
	htmlArr.push("<input type='hidden' name='saleOrderNum' value='"+data.orderNum+"'>" + data.orderNum);//数量
	htmlArr.push("<input type='button' class='btn' value='删除' onclick='deleteSaleRow(this, true)'>");
	insertRow("tbl1", htmlArr, false);

	selectOption($n("saleQualityId")[$("tbl1").rows.length-1], data.qualityId);
	var slt = $n("saleBracketColorId")[$("tbl1").rows.length-1];
	BdCommonDwr.getBracketColorByQualityId(data.qualityId,function(d){//托架材质下托架颜色
		addOptions(slt, d, null, null, true, true, data.bracketColorId);
		if(!data.bracketColorId && d && 1 == d.length){
			selectOption(slt, d[0].value);
		}
	});
    statisticsSalableSum();
}
/**
 * 统计畅销款 比例合计 和 数量合计
 */
function statisticsSalableSum(){
    var tbl1Len = $("tbl1").rows.length, orderNumSum = 0, styleRateSum =0;
    for(var i=0; i<tbl1Len; i++) {
        orderNumSum = floatAdd($n("saleOrderNum")[i].value, orderNumSum);
        styleRateSum = floatAdd($n("saleRate")[i].value, styleRateSum);
    }
    $("realSalableOrderNumSumHtml").value = orderNumSum;
    $("realSalableStyleRateSumHtml").value = styleRateSum;
}
/**
 * 统计非畅销款 比例合计 和 数量合计
 */
function statisticsUnSalableSum(){
    var tbl1Len = $("tbl2").rows.length, orderNumSum = 0, styleRateSum =0;
    for(var i=0; i<tbl1Len; i++) {
        orderNumSum = floatAdd($n("unsaleOrderNum")[i].value, orderNumSum);
        styleRateSum = floatAdd($n("unsaleRate")[i].value, styleRateSum);
    }
    $("realUnSalableOrderNumSumHtml").value = orderNumSum;
    $("realUnSalableStyleRateSumHtml").value = styleRateSum;
}

function addUnsalableStyleRow(data){
	var htmlArr = [];
	htmlArr.push("<input type='hidden' name='unsaleListid' value='"+data.listid+"'>" + ($("tbl2").rows.length+1));
	htmlArr.push("<input type='hidden' name='unsaleStyleId' value='"+data.styleId+"'>" + (data.picUrl?"<a href='javascript:viewStyle("+data.styleId+")'>" + data.styleName + "</a>":data.styleName));//款式
	htmlArr.push("<select style='width:90px;' name='unsaleQualityId' onchange='changeSaleQuality(this, false)'>"+qualitySelectHtml+"</select>");//材质
	htmlArr.push("<select style='width:60px;' name='unsaleBracketColorId'><option value=''>--请选择--</option></select>");//材质颜色
	htmlArr.push("<input type='text' name='unsaleRate' value='"+data.rate+"' onchange='checkSaleRate(this, false)' style='width:60px;'>");//比例
	htmlArr.push("<input type='hidden' name='unsaleStockNum' value='"+data.stockNum+"'>" + data.stockNum);//库存
	htmlArr.push("<input type='hidden' name='unsaleMonthNum' value='"+data.monthNum+"'>" + data.monthNum);//上月销售
	htmlArr.push("<input type='hidden' name='unsaleHalfYearNum' value='"+data.halfYearNum+"'>" + data.halfYearNum);//上半年销售
	htmlArr.push("<input type='hidden' name='unsaleHalfYearAvgNum' value='"+data.halfYearAvgNum+"'><input type='hidden' name='unsaleYearNum' value='"+data.yearNum+"'>" + data.yearNum);//上一年销售
	htmlArr.push("<input type='hidden' name='unsaleStyleCreateDate' value='"+data.styleCreateDate+"'>" + data.styleCreateDate);//创建时间
	htmlArr.push("<input type='hidden' name='unsaleOrderNum' value='"+data.orderNum+"'>" + data.orderNum);//数量
	htmlArr.push("<input type='button' class='btn' value='删除' onclick='deleteSaleRow(this, false)'>");
	insertRow("tbl2", htmlArr, false);
	
	selectOption($n("unsaleQualityId")[$("tbl2").rows.length-1], data.qualityId);
	var slt = $n("unsaleBracketColorId")[$("tbl2").rows.length-1];
	BdCommonDwr.getBracketColorByQualityId(data.qualityId,function(d){//托架材质下托架颜色
		addOptions(slt, d, null, null, true, true, data.bracketColorId);
		if(!data.bracketColorId && d && 1 == d.length){
			selectOption(slt, d[0].value);
		}
	});
    statisticsUnSalableSum();
}
/**
 * 改变材质，获取材质颜色
 */
function changeSaleQuality(obj, salable){
	var index = obj.parentNode.parentNode.rowIndex-1;
	BdCommonDwr.getBracketColorByQualityId(obj.value,function(data){//托架材质下托架颜色
		if(salable){
			addOptions2("saleBracketColorId", index, data, null, null, true, true, (data&&1==data.length?data[0].value:""));
		}else{
			addOptions2("unsaleBracketColorId", index, data, null, null, true, true, (data&&1==data.length?data[0].value:""));
		}
	});
}
/**
 * 检查比例
 */
function checkSaleRate(obj, salable){
    if(salable) {
        statisticsSalableSum();
    } else {
        statisticsUnSalableSum();
    }
	var index = obj.parentNode.parentNode.rowIndex - 1;
	var itemArr = null;
	if(salable){
		itemArr = $n("saleRate");
	}else{
		itemArr = $n("unsaleRate");
	}
	return checkSaleRate2(index, itemArr);
}
/**
 * 检查比例
 */
function checkSaleRate2(index, itemArr){
	var item = itemArr[index];
	if(!isDecimal(item.value)){
		alert("第"+(index+1)+"行 比例必须为数字类型");
		return false;
	}
	var tmp = parseFloat(item.value);
	if(tmp<0 || tmp>100){//可以等于0
		alert("第"+(index+1)+"行 比例必须在0至100之间");
		return false;
	}
	var sum = "0";
	for(var i=0,j=itemArr.length;i<j;i++){
		if(!isNull(itemArr[i].value)){
			sum = floatAdd(sum, itemArr[i].value);
		}
	}
	if(parseFloat(sum)>100){
		alert("列表比例之和已经超过100%");
		return false;
	}
	return true;
}
/**
 * 删除行
 */
function deleteSaleRow(obj, salable){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(salable){
		saleDeleteIdArr.push($n("saleListid")[index].value);
		deleteRow(obj, "tbl1");
        statisticsSalableSum();
	}else{
		unsaleDeleteIdArr.push($n("unsaleListid")[index].value);
		deleteRow(obj, "tbl2");
        statisticsUnSalableSum();
	}
}
function selectStyle(salableFlag){
	jQuery("#selectStyleDiv").dialog({
			width:900, 
			height:450,
			modal:true,
			zIndex : 2,
			open: function(event, ui) {
				loadSelectStyleLines(salableFlag, 1, 10);
			},
			beforeClose: function(event, ui) {
				return true;
			}
		});
	jQuery("#selectStyleDiv").dialog("option", "buttons", [{
			text:"确定",
			click:function(){
				var data = getSelectStyleRowData(salableFlag);
				var existStyleNameArr = [];
				for(var i=0,j=data.length;i<j;i++){
					if(isExistsStyle(!salableFlag, data[i].styleId)){
						existStyleNameArr.push(data[i].styleName);
						continue;
					}
					if(salableFlag){
						addSalableStyleRow(data[i]);
					}else{
						addUnsalableStyleRow(data[i]);
					}
				}
				if(existStyleNameArr.length>0){
					alert((salableFlag?"非畅销款":"畅销款")+"中 已经存在款式<br>"+existStyleNameArr.join(","));
				}
				jQuery(this).dialog("close");
			}
		},{
			text:"取消",
			click:function(){jQuery(this).dialog("close");}
		}
	]);
}
/**
 * 保存
 * @param salable
 */
function saveStyle(salable){
	var addLineArr = [];
	var updateLineArr = [];
	for(var i=0, j=$(salable?"tbl1":"tbl2").rows.length;i<j;i++){
		if(!checkStyleLine(i, salable))
			return ;
		if(isNull($n(salable?"saleListid":"unsaleListid")[i].value)){
			addLineArr.push(getStyleData(i,salable));
		}else{
			updateLineArr.push(getStyleData(i,salable));
		}
	}
	confirm("确定保存?", function(){
		showLayer(true);
		GatherSetDwr.saveGatherSetStyle(jQuery("#configLineId").val(), salable, addLineArr, updateLineArr, salable?saleDeleteIdArr:unsaleDeleteIdArr, function(data){
    		showLayer(false);
    		alert(data[0], function(){
				if(data.length==4){
					setSalableNumHtml(data[1], data[2], data[3]);
				}
				loadGatherSaleStyleLine();
				loadGatherUnsaleStyleLine();
    		});
		});
	});
}
function setSalableNumHtml(salableNum, unsalableNum, realSalableOrderNum){
	if(salableNum && unsalableNum){
		jQuery("#salableNumHtml").val(salableNum);
		jQuery("#unsalableNumHtml").val(unsalableNum);
	}else{
		jQuery("#salableNumHtml").val("");
		jQuery("#unsalableNumHtml").val("");
	}
	if(realSalableOrderNum){
		jQuery("#realSalableOrderNumHtml").val(realSalableOrderNum);
	}else{		
		jQuery("#realSalableOrderNumHtml").val("");
	}
}
function getStyleData(index, salable){
	return {
		listid :		$n(salable?"saleListid":"unsaleListid")[index].value,
		styleId :		$n(salable?"saleStyleId":"unsaleStyleId")[index].value,
		qualityId :		$n(salable?"saleQualityId":"unsaleQualityId")[index].value,
		bracketColorId :$n(salable?"saleBracketColorId":"unsaleBracketColorId")[index].value,
		rate :			$n(salable?"saleRate":"unsaleRate")[index].value,
		stockNum :		$n(salable?"saleStockNum":"unsaleStockNum")[index].value,
		monthNum :		$n(salable?"saleMonthNum":"unsaleMonthNum")[index].value,
		halfYearNum :	$n(salable?"saleHalfYearNum":"unsaleHalfYearNum")[index].value,
		halfYearAvgNum :$n(salable?"saleHalfYearAvgNum":"unsaleHalfYearAvgNum")[index].value,
		yearNum :		$n(salable?"saleYearNum":"unsaleYearNum")[index].value,
		styleCreateDate:$n(salable?"saleStyleCreateDate":"unsaleStyleCreateDate")[index].value
	};
}
function checkStyleLine(index, salable){
	if(!checkSaleRate2(index, salable?$n("saleRate"):$n("unsaleRate"))){
		return false;
	}
	if(parseInt((salable?$n("saleRate"):$n("unsaleRate"))[index].value)==0){
		return true;
	}
	if(isNull($n(salable?"saleQualityId":"unsaleQualityId")[index].value)){
		alert("第"+(index+1)+"行 材质不能为空");
		return false;
	}
	if(isNull($n(salable?"saleBracketColorId":"unsaleBracketColorId")[index].value)){
		alert("第"+(index+1)+"行 材质颜色不能为空");
		return false;
	}
	return true;
}
/**
 * 加载款式行
 */
function loadSelectStyleLines(salable, start, limit){
	var lineid = jQuery("#configLineId").val();
	jQuery("#selectStyleChkAll").attr("checked", false);
	removeAllRows('selectStyleTbl');
	GatherSetDwr.getSelectStylePageData(salable, lineid, start, limit, function(pager){
		var pageData = pager.pageData;
		var totalCount = pager.totalCount;
		for(var i=0,j=pageData.length;i<j;i++){
			addSelectStyleRow(salable, pageData[i]);
		}
		createPagingToolbar2(function(s, l){
			loadSelectStyleLines(salable, s, l);
		}, pager.start, pager.limit, totalCount);
	});
}
/**
 * 添加选择款式行
 * @param salable
 * @param data
 */
function addSelectStyleRow(salable, data){
	var htmlArr = [];
	var checkFlag = isExistsStyle(salable, data.styleId);
	htmlArr.push("<input type='checkbox' name='selectStyleChk'" + (checkFlag?" checked":"") + ">"+
			"<input type='hidden' name='selectStyleId' value='"+data.styleId+"'>"+
			"<input type='hidden' name='selectStyleName' value='"+data.styleName+"'>"+
			"<input type='hidden' name='selectPicUrl' value='"+data.picUrl+"'>"+
			"<input type='hidden' name='selectStyleRate' value='"+data.styleRate+"'>"+
			"<input type='hidden' name='selectStockNum' value='"+data.stockNum+"'>"+
			"<input type='hidden' name='selectYearNum' value='"+data.yearNum+"'>"+
			"<input type='hidden' name='selectHalfYearNum' value='"+data.halfYearNum+"'>"+
			"<input type='hidden' name='selectHalfYearAvgNum' value='"+data.halfYearAvgNum+"'>"+
			"<input type='hidden' name='selectMonthNum' value='"+data.monthNum+"'>"+
			"<input type='hidden' name='selectStyleCreateDate' value='"+data.styleCreateDate+"'>");
	htmlArr.push(data.styleMiddleClassName);
	htmlArr.push(data.styleOrnaClassName);
	htmlArr.push(data.picUrl?"<a href='javascript:viewStyle("+data.styleId+")'>" + data.styleName + "</a>":data.styleName);//款式
	htmlArr.push(data.styleRate);// 款式比例
	htmlArr.push(data.styleCreateDate);//创建时间
	htmlArr.push(data.stockNum);//库存
	htmlArr.push(data.monthNum);//上月销售
	htmlArr.push(data.halfYearNum);//半年销售
	htmlArr.push(data.yearNum);//年销售
	insertRow("selectStyleTbl", htmlArr);
}
function getSelectStyleRowData(salable){
    var salaleQualityId = "86", salableBracketColorId="49";/*
    if(salable){
        salaleQualityId = "86", salableBracketColorId="49";
    }*/
	var data = [];
	var selectChkArr = $n("selectStyleChk");
	for(var i=0, j=selectChkArr.length;i<j;i++){
		if(!selectChkArr[i].checked)
			continue ;
		if(isExistsStyle(salable, $n("selectStyleId")[i].value))
			continue ;
		data.push({
			listid		: "",
			styleId		: $n("selectStyleId")[i].value,
			styleName	: $n("selectStyleName")[i].value,
			picUrl		: $n("selectPicUrl")[i].value,
			qualityId	: uniqueQualityId||salaleQualityId,
			bracketColorId : salableBracketColorId,
			rate		: $n("selectStyleRate")[i].value,
			stockNum	: $n("selectStockNum")[i].value,
			monthNum	: $n("selectMonthNum")[i].value,
			halfYearNum	: $n("selectHalfYearNum")[i].value,
			halfYearAvgNum	: $n("selectHalfYearAvgNum")[i].value,
			yearNum	: $n("selectYearNum")[i].value,
			styleCreateDate	: $n("selectStyleCreateDate")[i].value,
			orderNum	: "0"
		});
	}
	return data;
}
/**
 * 判断款式是否已经存在
 * @param salable
 * @param styleId
 */
function isExistsStyle(salable, styleId){
	var itemArr = null;
	if(salable){
		itemArr = $n("saleStyleId");
	}else{
		itemArr = $n("unsaleStyleId");
	}
	for(var i=0,j=itemArr.length;i<j;i++){
		if(styleId == itemArr[i].value){
			return true;
		}
	}
	return false;
}
//----------------------------------------------------------------------------------------------------品质
var gradeDeleteIdArr = null;
function loadGatherGradeLine(){
	removeAllRows('tbl3');
	gradeDeleteIdArr = [];
	GatherSetDwr.getGatherSetGrade(jQuery("#configLineId").val(), function(data){
		for(var i=0,j=data.length;i<j;i++){
			addGatherGradeRow(data[i]);
		}
	});
}
function addGatherGradeRow(data){
	var htmlArr = [];
	htmlArr.push("<input type='hidden' name='gradeListid' value='"+data.listid+"'>" + ($("tbl3").rows.length+1));
	htmlArr.push("<input type='hidden' name='gradeColorGradeId' value='"+data.colorGradeId+"'>" + data.colorGradeName);//色级
	htmlArr.push("<input type='hidden' name='gradeCleanId' value='"+data.cleanId+"'>" + data.cleanName);//净度
	htmlArr.push("<input type='hidden' name='gradeColorGradeGradeId' value='"+data.colorGradeGradeId+"'><input type='hidden' name='gradeCleanGradeId' value='"+data.cleanGradeId+"'>" + data.gradeName);//品质止
	htmlArr.push("<input type='text' name='gradeRate' value='"+data.rate+"' onchange='checkgradeRate(this)' style='width:60px;'>");//比例
	htmlArr.push("<input type='hidden' name='gradeOrderNum' value='"+data.orderNum+"'>" + data.orderNum);//数量
	htmlArr.push("<input type='button' class='btn' value='删除' onclick='deleteGradeRow(this)'>");
	insertRow("tbl3", htmlArr, false);
}
/**
 * 删除行
 */
function deleteGradeRow(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	gradeDeleteIdArr.push($n("gradeListid")[index].value);
	deleteRow(obj, "tbl3");
}
/**
 * 检查比例
 */
function checkgradeRate(obj){
	var index = obj.parentNode.parentNode.rowIndex - 1;
	var itemArr = $n("gradeRate");
	return checkGradeRate2(index, itemArr);
}
/**
 * 检查比例
 */
function checkGradeRate2(index, itemArr){
	var item = itemArr[index];
	if(!isDecimal(item.value)){
		alert("第"+(index+1)+"行 比例必须为数字类型");
		return false;
	}
	var tmp = parseFloat(item.value);
	if(tmp<0 || tmp>100){
		alert("第"+(index+1)+"行 比例必须在0至100之间");
		return false;
	}
	var sum = "0";
	for(var i=0,j=itemArr.length;i<j;i++){
		if(!isNull(itemArr[i].value)){
			sum = floatAdd(sum, itemArr[i].value);
		}
	}
	if(parseFloat(sum)>100){
		alert("列表比例之和已经超过100%");
		return false;
	}
	return true;
}
/**
 * 保存
 */
function saveGrade(){
	var addLineArr = [];
	var updateLineArr = [];
	for(var i=0, j=$("tbl3").rows.length;i<j;i++){
		if(!checkGradeLine(i))
			return ;
		if(isNull($n("gradeListid")[i].value)){
			addLineArr.push(getGradeData(i));
		}else{
			updateLineArr.push(getGradeData(i));
		}
	}
	confirm("确定保存?", function(){
		showLayer(true);
		GatherSetDwr.saveGatherSetGrade(jQuery("#configLineId").val(), addLineArr, updateLineArr, gradeDeleteIdArr, function(data){
			showLayer(false);
			alert(data?data:"保存成功", function(){
				if(!data){
					loadGatherGradeLine();
				}
			});
		});
	});
}
function getGradeData(index){
	return {
		listid :			$n("gradeListid")[index].value,
		colorGradeId :		$n("gradeColorGradeId")[index].value,
		cleanId :			$n("gradeCleanId")[index].value,
		colorGradeGradeId :	$n("gradeColorGradeGradeId")[index].value,
		cleanGradeId :		$n("gradeCleanGradeId")[index].value,
		rate :				$n("gradeRate")[index].value
	};
}
/**
 * 显示选择品质窗口
 */
function selectGrade(){
	jQuery("#selectGradeDiv").dialog({
		width:800, 
		height:400,
		modal:true,
		zIndex : 2,
		open: function(event, ui) {
			loadSelectGradeLines();
		}
	});
	jQuery("#selectGradeDiv").dialog("option", "buttons", [{
		text:"确定",
		click:function(){
			var data = getSelectGradeRowData();
			for(var i=0,j=data.length;i<j;i++){					
				addGatherGradeRow(data[i]);
			}
			jQuery(this).dialog("close");
		}
	},{
		text:"取消",
		click:function(){jQuery(this).dialog("close");}
	}
	]);
}
function getSelectGradeRowData(){
	var data = [];
	var selectChkArr = $n("selectGradeChk");
	for(var i=0, j=selectChkArr.length;i<j;i++){
		if(!selectChkArr[i].checked)
			continue ;
		if(isExistsGrade($n("selectColorGradeId")[i].value, $n("selectCleanId")[i].value))
			continue ;
		data.push({
			listid				: "",
			colorGradeId		: $n("selectColorGradeId")[i].value,
			cleanId 			: $n("selectCleanId")[i].value,
			colorGradeGradeId	: $n("selectColorGradeGradeId")[i].value,
			cleanGradeId		: $n("selectCleanGradeId")[i].value,
			gradeName			: $n("selectGradeName")[i].value,
			colorGradeName		: $n("selectColorGradeName")[i].value,
			cleanName			: $n("selectCleanName")[i].value,
			rate				: $n("selectGradeRate")[i].value,
			orderNum			: ""
		});
	}
	return data;
}
/**
 * 判断品质是否已经存在
 */
function isExistsGrade(colorGradeId, cleanId){
//	info(colorGradeId + "--" + cleanId);
	for(var i=0,j=$n("gradeColorGradeId").length;i<j;i++){
		if(colorGradeId == $n("gradeColorGradeId")[i].value && cleanId == $n("gradeCleanId")[i].value){
			return true;
		}
	}
	return false;
}
function loadSelectGradeLines(){
	removeAllRows('selectGradeTbl');
	GatherSetDwr.getSelectGradeData(jQuery("#configLineId").val(), function(data){
		for(var i=0,j=data.length;i<j;i++){
			addSelectGradeRow(data[i]);
		}
	});
}
/**
 * 添加选择品质行
 * @param data
 */
function addSelectGradeRow(data){
	var htmlArr = [];
	var checkFlag = isExistsGrade(data.colorGradeId, data.cleanId);
	htmlArr.push("<input type='checkbox' name='selectGradeChk'" + (checkFlag?" checked":"") + ">"+
			"<input type='hidden' name='selectColorGradeId' value='"+data.colorGradeId+"'>"+
			"<input type='hidden' name='selectCleanId' value='"+data.cleanId+"'>"+
			"<input type='hidden' name='selectColorGradeGradeId' value='"+(data.colorGradeGradeId?data.colorGradeGradeId:"")+"'>"+
			"<input type='hidden' name='selectCleanGradeId' value='"+(data.cleanGradeId?data.cleanGradeId:"")+"'>"+
			"<input type='hidden' name='selectColorGradeName' value='"+data.colorGradeName+"'>"+
			"<input type='hidden' name='selectCleanName' value='"+data.cleanName+"'>"+
			"<input type='hidden' name='selectGradeName' value='"+data.gradeName+"'>"+
			"<input type='hidden' name='selectGradeRate' value='"+data.rate+"'>");
	htmlArr.push(data.colorGradeName);
	htmlArr.push(data.cleanName);
	htmlArr.push(data.gradeName);
	htmlArr.push(data.rate);
	insertRow("selectGradeTbl", htmlArr);
}
function checkGradeLine(index){
	if(!checkGradeRate2(index, $n("gradeRate"))){
		return false;
	}
	return true;
}
//----------------------------------------------------------------------------------------------------尺寸
var sizeDeleteIdArr = null;
function loadGatherSizeLine(){
	removeAllRows('tbl4');
	sizeDeleteIdArr = [];
	GatherSetDwr.getGatherSetSize(jQuery("#configLineId").val(), function(data){
		for(var i=0,j=data.length;i<j;i++){
			addGatherSizeRow(data[i]);
		}
	});
}
function addGatherSizeRow(data){
	var htmlArr = [];
	htmlArr.push("<input type='hidden' name='sizeListid' value='"+data.listid+"'>" + ($("tbl4").rows.length+1));
	htmlArr.push("<input type='hidden' name='sizeSizeId' value='"+data.sizeId+"'>" + data.sizeName);//尺寸名称
	htmlArr.push("<input type='hidden' name='sizeStartSizeId' value='"+data.startSizeId+"'>" + data.startSizeName);//尺寸起
	htmlArr.push("<input type='hidden' name='sizeEndSizeId' value='"+data.endSizeId+"'>" + data.endSizeName);//尺寸止
	htmlArr.push("<input type='text' name='sizeRate' value='"+data.rate+"' onchange='checksizeRate(this)' style='width:60px;'>");//比例
	htmlArr.push("<input type='hidden' name='sizeOrderNum' value='"+data.orderNum+"'>" + data.orderNum);//数量
	htmlArr.push("<input type='button' class='btn' value='删除' onclick='deleteSizeRow(this)'>");
	insertRow("tbl4", htmlArr, false);
}
/**
 * 删除行
 */
function deleteSizeRow(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	sizeDeleteIdArr.push($n("sizeListid")[index].value);
	deleteRow(obj, "tbl4");
}
/**
 * 检查比例
 */
function checksizeRate(obj){
	var index = obj.parentNode.parentNode.rowIndex - 1;
	var itemArr = $n("sizeRate");
	return checkSizeRate2(index, itemArr);
}
/**
 * 检查比例
 */
function checkSizeRate2(index, itemArr){
	var item = itemArr[index];
	if(!isDecimal(item.value)){
		alert("第"+(index+1)+"行 比例必须为数字类型");
		return false;
	}
	var tmp = parseFloat(item.value);
	if(tmp<0 || tmp>100){
		alert("第"+(index+1)+"行 比例必须在0至100之间");
		return false;
	}
	var sum = "0";
	for(var i=0,j=itemArr.length;i<j;i++){
		if(!isNull(itemArr[i].value)){
			sum = floatAdd(sum, itemArr[i].value);
		}
	}
	if(parseFloat(sum)>100){
		alert("列表比例之和已经超过100%");
		return false;
	}
	return true;
}
/**
 * 保存
 */
function saveSize(){
	var addLineArr = [];
	var updateLineArr = [];
	for(var i=0, j=$("tbl4").rows.length;i<j;i++){
		if(!checkSizeLine(i))
			return ;
		if(isNull($n("sizeListid")[i].value)){
			addLineArr.push(getSizeData(i));
		}else{
			updateLineArr.push(getSizeData(i));
		}
	}
	confirm("确定保存?", function(){
		showLayer(true);
		GatherSetDwr.saveGatherSetSize(jQuery("#configLineId").val(), addLineArr, updateLineArr, sizeDeleteIdArr, function(data){
    		showLayer(false);
    		alert(data?data:"保存成功", function(){
    			if(!data){
    				loadGatherSizeLine();
    			}
    		});
		});
	});
}
function getSizeData(index){
	return {
		listid :		$n("sizeListid")[index].value,
		sizeId :		$n("sizeSizeId")[index].value,
		startSizeId :	$n("sizeStartSizeId")[index].value,
		endSizeId :		$n("sizeEndSizeId")[index].value,
		rate :			$n("sizeRate")[index].value
	};
}
/**
 * 显示选择尺寸窗口
 */
function selectSize(){
	jQuery("#selectSizeDiv").dialog({
			width:800, 
			height:400,
			modal:true,
			zIndex : 2,
			open: function(event, ui) {
				loadSelectSizeLines();
			}
		});
	jQuery("#selectSizeDiv").dialog("option", "buttons", [{
			text:"确定",
			click:function(){
				var data = getSelectSizeRowData();
				for(var i=0,j=data.length;i<j;i++){					
					addGatherSizeRow(data[i]);
				}
				jQuery(this).dialog("close");
			}
		},{
			text:"取消",
			click:function(){jQuery(this).dialog("close");}
		}
	]);
}
function getSelectSizeRowData(){
	var data = [];
	var selectChkArr = $n("selectSizeChk");
	for(var i=0, j=selectChkArr.length;i<j;i++){
		if(!selectChkArr[i].checked)
			continue ;
		if(isExistsSize($n("selectSizeId")[i].value, $n("selectStartSizeId")[i].value, $n("selectEndSizeId")[i].value))
			continue ;
		data.push({
			listid : "",
			sizeId			: $n("selectSizeId")[i].value,
			sizeName 		: $n("selectSizeName")[i].value,
			startSizeId		: $n("selectStartSizeId")[i].value,
			startSizeName	: $n("selectStartSizeName")[i].value,
			endSizeId		: $n("selectEndSizeId")[i].value,
			endSizeName		: $n("selectEndSizeName")[i].value,
			rate			: $n("selectSizeRate")[i].value,
			orderNum : ""
		});
	}
	return data;
}
/**
 * 判断尺寸是否已经存在
 */
function isExistsSize(styleId, startSizeId, endSizeId){
	for(var i=0,j=$n("sizeSizeId").length;i<j;i++){
		if(styleId == $n("sizeSizeId")[i].value && startSizeId == $n("sizeStartSizeId")[i].value && endSizeId == $n("sizeEndSizeId")[i].value){
			return true;
		}
	}
	return false;
}
function loadSelectSizeLines(){
	removeAllRows('selectSizeTbl');
	GatherSetDwr.getSelectSizeData(jQuery("#configLineId").val(), function(data){
		for(var i=0,j=data.length;i<j;i++){
			addSelectSizeRow(data[i]);
		}
	});
}
/**
 * 添加选择尺寸行
 * @param data
 */
function addSelectSizeRow(data){
	var htmlArr = [];
	var checkFlag = isExistsSize(data.sizeId, data.startSizeId, data.endSizeId);
	htmlArr.push("<input type='checkbox' name='selectSizeChk'" + (checkFlag?" checked":"") + ">"+
			"<input type='hidden' name='selectSizeId' value='"+data.sizeId+"'>"+
			"<input type='hidden' name='selectSizeName' value='"+data.sizeName+"'>"+
			"<input type='hidden' name='selectStartSizeId' value='"+data.startSizeId+"'>"+
			"<input type='hidden' name='selectStartSizeName' value='"+data.startSizeName+"'>"+
			"<input type='hidden' name='selectEndSizeId' value='"+data.endSizeId+"'>"+
			"<input type='hidden' name='selectEndSizeName' value='"+data.endSizeName+"'>"+
			"<input type='hidden' name='selectSizeRate' value='"+data.rate+"'>");
	htmlArr.push(data.sizeName);
	htmlArr.push(data.startSizeName);
	htmlArr.push(data.endSizeName);
	htmlArr.push(data.rate);
	insertRow("selectSizeTbl", htmlArr);
}
function checkSizeLine(index){
	if(!checkSizeRate2(index, $n("sizeRate"))){
		return false;
	}
	return true;
}
//--------------------------------------------------------------------------------------------生成明细
function generateDetail(){
	confirm("确定生成明细?", function(){
		showLayer(true);
		GatherSetDwr.generateDetail(jQuery("#configLineId").val(), function(data){
			showLayer(false);
			alert(data?data:"生成明细成功");
		});
	});
}
/**
 * 生成明细
 * @param salableFlag
 */
function viewDispatchDetail(salableFlag){
	jQuery("#viewDetailsDiv").dialog({
			width:1000, 
			height:600,
			modal:true,
			zIndex : 2,
			open: function(event, ui) {
				jQuery("#dispatchingChangeFrame").attr('src', "gatherOrder.vm?user_action=view&setLineId="+jQuery("#configLineId").val()+"&salableFlag="+(salableFlag?"1":"0"));
			}
		});
}
/**
 * 生成总单
 */
function createGatherOrder(){
	confirm("确定生成总单?", function(){
		showLayer(true);
		GatherSetDwr.createGatherOrder(jQuery("#headid").val(), function(data){
			showLayer(false);
			alert(data?data:"生成总单成功", function(){
				if(!data){
					window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
				}
			});
		});
	});
}
/**
 * 修改畅销款铺货比例、畅销款周转比例
 */
function updateSalableRate(){
	if(!isDecimal(jQuery("#saleDisRate").val()) || parseFloat(jQuery("#saleDisRate").val())<0  || parseFloat(jQuery("#saleDisRate").val())>100){
		alert("请输入畅销款铺货比例,且必须为0至100之间");
		return ;
	}
	if(!isDecimal(jQuery("#saleTurnRate").val()) || parseFloat(jQuery("#saleTurnRate").val())<0  || parseFloat(jQuery("#saleTurnRate").val())>100){
		alert("请输入畅销款周转比例,且必须为0至100之间");
		return ;
	}
	confirm("确定保存?", function(){
    	GatherSetDwr.updateSalableRate(jQuery("#headid").val(), jQuery("#saleDisRate").val(), jQuery("#saleTurnRate").val(), function(data){
    		alert("1"==data[0]?"保存成功<br>"+data[1]:data[1], function(){
    			if("1"==data[0]){
    				window.location = "gatherSet.vm?user_action=bodyEdit&billId=" + jQuery("#headid").val();
    			}
    		});
    	});
	});
}