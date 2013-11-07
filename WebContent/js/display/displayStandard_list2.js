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
	BdCommonDwr.getAllItemClassForSlt(function(data){
		addOptions("itemClassId", data, null, null, true, true);
	});
    DisplayStandardDwr.getDisplayStandardList2(jQuery("#headid").val(), function(datas){
        removeAllRows('tbl2');
        for(var i=0;i<datas.length;i++){
            addSalableStyleRow(datas[i]);
        }
    });
	showDisplayStandard();
}
function showDisplayStandard(){
	jQuery("#orgtypeDiv").dialog({
			title:'非畅销款陈列标准设置',
			width:800, 
			height:500,
			modal:true,
			zIndex:1,
			close:function(){
                jQuery('#orgtypeDiv').dialog('close');
			}
		});
}
/**
 * 改变大类
 */
function changeItemClassId(){
    BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
        addOptions("ornaClassId", data, null, null, true, true);
        changeOrnaClassId();
    });
}
/**
 *　改变小类
 */
function changeOrnaClassId(){
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
   var data ={
            lineid				: "",
            itemClassId			: jQuery("#itemClassId").val(),
            itemClassName		: jQuery("#itemClassId").find('option:selected').text(),
            ornaClassId			: jQuery("#ornaClassId").val(),
            ornaClassName		: jQuery("#ornaClassId").find('option:selected').text(),
            analysisId			: jQuery("#analysisId").val(),
            analysisName		: jQuery("#analysisId").find('option:selected').text(),
            styleItemClassId	: jQuery("#styleItemClassId").val(),
            styleItemClassName	: jQuery("#styleItemClassId").find('option:selected').text(),
            minCount			: "0",
            maxCount			: "0"
    };
    if(isExists(data)) return ;
    addSalableStyleRow(data);
    resetIndex();
}

function isExists(data) {
    var len = $('tbl2').rows.length;
    for(var i=0; i<len; i++) {
        var itemId = $n("itemClassId")[i].value,
            ornaId = $n("ornaClassId")[i].value,
            analyId = $n("analysisId")[i].value,
            styleItemId = $n("styleItemClassId")[i].value;
        if(itemId == data.itemClassId && ornaId == data.ornaClassId && analyId == data.analysisId && styleItemId == data.styleItemClassId) {
            alert("选择的大类、小类、分析范围、款式大类已经存在");
            return true;
        }
    }
    return false;
}
/**
 * 改变大类
 */
/*function changeItemClassId(){
//	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
//		addOptions("ornaClassId", data, null, null, true, true);
//		changeOrnaClass();
//	});
	DisplayStandardDwr.getDisplayStandardList2(jQuery("#headid").val(), jQuery("#itemClassId").val(), function(datas){
		removeAllRows('tbl2');
		for(var i=0;i<datas.length;i++){
			addSalableStyleRow(datas[i]);
		}
		resetIndex();
	});
}*/
function removeAllRows(tblId){
	for(var i=$(tblId).rows.length-1;i>=0;i--){
		$(tblId).deleteRow(i);
	}
}
function addSalableStyleRow(data){
	var htmlArr = [];
	htmlArr.push("");
	htmlArr.push("<input type='hidden' name='lineid' value='" + nullToEmpty(data.lineid) + "'><input type='hidden' name='itemClassId' value='"+data.itemClassId+"'>" + data.itemClassName);
	htmlArr.push("<input type='hidden' name='ornaClassId' value='"+data.ornaClassId+"'>" + data.ornaClassName);
	htmlArr.push("<input type='hidden' name='analysisId' value='"+data.analysisId+"'>" + data.analysisName);
	htmlArr.push("<input type='hidden' name='styleItemClassId' value='"+data.styleItemClassId+"'>" + data.styleItemClassName);
	htmlArr.push("<input type='hidden' name='pre_minCount' value='"+nullToEmpty(data.minCount)+"'><input type='text' name='minCount' style='width:45px;' value='"+nullToEmpty(data.minCount)+"'>");//最小量
	htmlArr.push("<input type='hidden' name='pre_maxCount' value='"+nullToEmpty(data.maxCount)+"'><input type='text' name='maxCount' style='width:45px;' value='"+nullToEmpty(data.maxCount)+"'>");//最大量
	htmlArr.push("<input type='button' class='btn' value='删除' onclick='deleteStyleRow(this)'>");
	insertRow("tbl2", htmlArr, false);
}
function nullToEmpty(str){
	return !str?"":str;
}
function resetIndex(){
	var len = $('tbl2').rows.length;
	for(var i=0;i<len;i++){
		$('tbl2').rows[i].cells[0].innerHTML = len - i;
	}
}
/**
 * 删除行
 */
function deleteStyleRow(obj){
		var index = obj.parentNode.parentNode.rowIndex-1;
		if(!isNull($n("lineid")[index].value)){
			confirm("确定删除（将直接删除数据）?", function(){
				DisplayStandardDwr.deleteDisplayStandardList2($n("lineid")[index].value, function(){				
					deleteRow(obj, "tbl2");
					resetIndex();
				});
			});
		}else{
			deleteRow(obj, "tbl2");
			resetIndex();
		}
}
/**
 * 保存
 */
function saveDisplayStandard(){
	var modifyRowIndexArr = [];
	var len = $('tbl2').rows.length;
	for(var i=0;i<len;i++){
		if(($n("pre_minCount")[i].value != $n("minCount")[i].value) || ($n("pre_maxCount")[i].value != $n("maxCount")[i].value)){
			modifyRowIndexArr.push(i);
		}
		if(isNull($n("minCount")[i].value) && isNull($n("maxCount")[i].value)){
			continue;
		}
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
		var deleteArr = [];
		var len = modifyRowIndexArr.length;
		for(var idx=0;idx<len;idx++){
			var i = modifyRowIndexArr[idx];
			if(isNull($n("lineid")[i].value)){
				addArr.push({
							lineid : $n("lineid")[i].value,
							itemClassId : $n("itemClassId")[i].value,
							ornaClassId : $n("ornaClassId")[i].value,
                            analysisId : $n("analysisId")[i].value,
                            styleItemClassId : $n("styleItemClassId")[i].value,
							minCount : $n("minCount")[i].value,
							maxCount : $n("maxCount")[i].value
						});
			}else if(isNull($n("minCount")[i].value) && isNull($n("maxCount")[i].value)){
				deleteArr.push($n("lineid")[i].value);
			}else if(($n("pre_minCount")[i].value != $n("minCount")[i].value) || ($n("pre_maxCount")[i].value != $n("maxCount")[i].value)){
				updateArr.push({
					lineid:$n("lineid")[i].value,
					minCount:$n("minCount")[i].value,
					maxCount:$n("maxCount")[i].value
				});
			}
		}
    	DisplayStandardDwr.saveOrUpdateDisplayStandard2(jQuery("#headid").val(), addArr, updateArr, deleteArr, function(data){
    		showLayer(false);
    		alert(data?data:"保存成功", function(){
    			if(!data){
                	window.location = "displayStandard.vm?user_action=list2";
    			}
    		});
    	});
	});
}