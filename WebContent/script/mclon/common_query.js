/**
 * 初始化查询div
 * @param btnQueryShowId 列表查询按钮id
 * @param btnQueryCancelId 取消查询按钮id
 */
function initQuery(btnQueryShowId, btnQueryCancelId, divWidth, divHeight){
	var divId = "queryDiv";
	var backgroundDivId = "queryDivBackground";
    var screenwidth = jQuery(window).width();
    var screenheight = jQuery(window).height();
    var mytop = jQuery(document).scrollTop();
	divWidth = divWidth || 260;
	divHeight = divHeight || 150;
    var getPosLeft = screenwidth/2 - divWidth;
    var getPosTop = screenheight/2 - divHeight;
    jQuery("#" + divId).css({"left":getPosLeft,"top":getPosTop});
    jQuery(window).resize(function()
    {
        screenwidth = jQuery(window).width();
        screenheight = jQuery(window).height();
        mytop = jQuery(document).scrollTop();
        getPosLeft = screenwidth/2 - divWidth;
        getPosTop = screenheight/2 - divHeight;
        jQuery("#" + divId).css({"left":getPosLeft,"top":getPosTop+mytop});
    });

    jQuery(window).scroll(function()
    {
        screenwidth = jQuery(window).width();
        screenheight = jQuery(window).height();
        mytop = jQuery(document).scrollTop();
        getPosLeft = screenwidth/2 - divWidth;
        getPosTop = screenheight/2 - divHeight;
        jQuery("#" + divId).css({"left":getPosLeft,"top":getPosTop+mytop});
    });

    jQuery("#" + btnQueryShowId).click(function()
    {
        jQuery("#" + divId).fadeIn("fast");
        jQuery("body").append("<div id='" + backgroundDivId + "'></div>");
        var documentheight = jQuery(document).height();
        jQuery("#" + backgroundDivId).css({"opacity":"0.5","height":documentheight});
        return false;
    });

    jQuery("#" + btnQueryCancelId).click(function()
    {
        jQuery("#" + divId).hide();
        jQuery("#" + backgroundDivId).remove();
        return false;
    });
}

/**
 * 
 * @param btnExportShowId
 * @param exportFunc
 */
function initExportExcel(btnExportShowId, exportFunc){
	jQuery("body").append('<div id="exportDiv"><table class="control"><tr><td>导出Excel</td></tr></table>                                                                 '+                 
			'		<table class="content">                                                            '+                 
			'			<tr>                                                                           '+                 
			'				<td class="body1">选择导出范围</td>                                         '+                       
			'				<td class="export">                                                        '+                 
			'					<select id="_exportCondition" style="width:130px;">                                          '+
			'                   </select>                                                       '+     
			'				</td>                                                                          '+               
			'			</tr>                                                                            '+                 
			'			<tr>                                                                             '+                 
			'				<td class="export2" colspan="2"><input type="button" id="_btnExport" value="导出">'+
			'　　　<input type="button" id="_btnExportClose" value="取消"></td>      '+                                                                   
			'			</tr>                                                                            '+                 
			'	    </table>                                                                         '+
			'</div>                                                                                ');
	
	var totalCount = jQuery("#_totalCount").val();
	if(totalCount && floatSub(totalCount, 0)>0){
		totalCount = parseInt(totalCount);
		var pageCount = 0,
			p_size = 10000;
		if(totalCount%p_size == 0){
			pageCount = totalCount/p_size;
		} else {
			pageCount = Math.floor(totalCount/p_size) + 1;
		}
		removeAllOptions("_exportCondition",false);
		for(var i=0; i<pageCount;i++){
			var value;
			if(i==0){
				value = "1-"+p_size;
			} else {
				value = (i*p_size+1) + "-" + ((i+1)*p_size);
			}
			addOption("_exportCondition", i, value, false);
		}
	}
	
	var divId = "exportDiv";
	var backgroundDivId = "exportDivBackground";
    var screenwidth = jQuery(window).width();
    var screenheight = jQuery(window).height();
    var mytop = jQuery(document).scrollTop();
	divWidth =  320;
	divHeight = 100;
    var getPosLeft = screenwidth/2 - divWidth;
    var getPosTop = screenheight/2 - divHeight;
    jQuery("#" + divId).css({"left":getPosLeft,"top":getPosTop});
    jQuery(window).resize(function()
    {
        screenwidth = jQuery(window).width();
        screenheight = jQuery(window).height();
        mytop = jQuery(document).scrollTop();
        getPosLeft = screenwidth/2 - divWidth;
        getPosTop = screenheight/2 - divHeight;
        jQuery("#" + divId).css({"left":getPosLeft,"top":getPosTop+mytop});
    });

    jQuery(window).scroll(function()
    {
        screenwidth = jQuery(window).width();
        screenheight = jQuery(window).height();
        mytop = jQuery(document).scrollTop();
        getPosLeft = screenwidth/2 - divWidth;
        getPosTop = screenheight/2 - divHeight;
        jQuery("#" + divId).css({"left":getPosLeft,"top":getPosTop+mytop});
    });
    
    jQuery("#_btnExportClose").click(function()
    {	
        jQuery("#" + divId).hide();
        jQuery("#" + backgroundDivId).remove();
        return false;
    });
    
    jQuery("#_btnExport").click(function()
    {	
    	var startOld = jQuery("#start").val(),
    		limitOld = jQuery("#limit").val(),
    		condition = jQuery("#_exportCondition").val();
    	if(!limitOld || floatSub(limitOld, 0)<=0){
    		limitOld = 65535;
    	}
    	var start = 1, limit = 10000;
    	//info("condition:"+condition);
    	if(condition && floatSub(condition, 0) > 0){
	    	start = floatMul(condition, 10000)+1;
		}
    	//info(start);
    	jQuery("#start").val(start);
		jQuery("#limit").val(limit);
		exportFunc();
		jQuery("#start").val(startOld);
		jQuery("#limit").val(limitOld);
		jQuery("#" + divId).hide();
        jQuery("#" + backgroundDivId).remove();
    });
    
    jQuery("#"+btnExportShowId).click(function()
    {
        jQuery("#" + divId).fadeIn("fast");
        jQuery("body").append("<div id='" + backgroundDivId + "'></div>");
        var documentheight = jQuery(document).height();
        jQuery("#" + backgroundDivId).css({"opacity":"0.5","height":documentheight});
        return false;
    });
}

function checkExport(){
	var startRow = jQuery("#_startRow").val(),
		endRow = jQuery("#_endRow").val();
	if(startRow){
		if(isDecimal(startRow)){
			if(floatSub(startRow, 0) <=0){
				alert("起始行必须大于0");
				return false;
			}
		} else {
			alert("起始行只能是数字");
			return false;
		}
	}else {
		alert("请输入起始行");
		return false;
	}
	
	if(endRow){
		if(isDecimal(endRow)){
			if(floatSub(endRow, 0) <=0){
				alert("截止行必须大于0");
				return false;
			}
		} else {
			alert("截止行只能是数字");
			return false;
		}
	}else {
		alert("请输入截止行");
		return false;
	}
	
	if(floatSub(endRow, startRow) <=0){
		alert("截止行 必须 大于 起始行");
		return false;
	}
	return true;
}

function exportChange(){
	var exportType = $n("_exportType")[0].checked;
	if(exportType){
		jQuery("#_startRow").attr("disabled", false);
		jQuery("#_endRow").attr("disabled", false);
	} else {
		jQuery("#_startRow").attr("disabled", true);
		jQuery("#_endRow").attr("disabled", true);
	}
}

//-------------------------------------通用查询条件-----------------------------------------------------
function initQuery2(queryCode, frmId, btnId){
	jQuery("#" + btnId).click(function(){
		showQueryWin();
	});
	_q_query_code = queryCode;
	_q_frm_id = frmId;
	

	var sqlField = document.createElement("input");
	sqlField.type="hidden";
	sqlField.id = _q_sql_input_id;
	sqlField.name = _q_sql_input_id;
	$(_q_frm_id).appendChild(sqlField);
	QueryConditionDwr.getQuerySql(_q_query_code, function(data){
		//info(data);
		if(data){			
			var obj = eval("(" + data + ")");
			if(obj.query_data){
				_q_query_data = obj.query_data;
			}
			if(obj.sql){
				$(_q_sql_input_id).value = obj.sql;
			}
		}
	});
}
var _q_query_code = null;
var _q_frm_id = null;
var _q_tbl_id = "_q_tbl_id";
var _q_sql_input_id = "_q_sql";
var _q_query_data = null;
function showQueryWin(){
	if(!$("_boxcontent")){
		var divElmt = document.createElement("div");
		divElmt.style.display = "none";
		divElmt.id = "_boxcontent";
		divElmt.innerHTML = "<table class='control'><tr><td>"
							+"<input type='button' class='btn' value='增行' onclick='_q_addRow()'>"
							+"<input type='button' class='btn' value='清空' onclick='_q_clear_content()'>"
							+"</td></tr></table>"
							+"<table class='list'>"
							+"	<thead>"
							+"		<tr>"
							+"			<th>关系</th>"
							+"			<th>括号</th>"
							+"			<th>字段</th>"
							+"			<th>运算符</th>"
							+"			<th>内容</th>"
							+"			<th>括号</th>"
							+"			<th></th>"
							+"		</tr>"
							+"	</thead>"
							+"	<tbody id='"+_q_tbl_id+"'>"
							+"</tbody>";
		document.body.appendChild(divElmt);
		altRowCSS(_q_tbl_id);
	}
	jQuery.weeboxs.open('#_boxcontent', {
		title:'查询',
		boxid:'_boxQuery',
		closeable:true,
		modal:false,
		okBtnName:"查询",
		width:700,
		height:200,
		onok:function(box){
			_saveQueryCondition(function(){				
				jQuery("#"+_q_frm_id).submit();
				box.close();
			});
		},
		oncancel:function(box){
			box.close();
		}
	});
	if(_q_query_data && _q_query_data.length>0){
		for(var i=0;i<_q_query_data.length;i++){
			_q_addRow(_q_query_data[i]);
		}
	}
}
function _q_clear_content(){
	for(var i=$(_q_tbl_id).rows.length-1;i>=0;i--){
		$(_q_tbl_id).deleteRow(i);
	}
	//_q_addRow();
}
function _q_addRow(obj){
	var f1 = "<select name='_q_relation' style='width:60px;'><option value='and'>并且</option><option value='or'>或者</option></select>";//关系
	var f2 = "<select name='_q_leftQuot' style='width:60px;'><option value=''></option><option value='('>(</option><option value='(('>((</option><option value='((('>(((</option><option value='(((('>((((</option></select>";//左括号
	var f3 = "<input type='hidden' name='_q_inputType'><select name='_q_fieldName' onchange='_q_changeFieldName(this)'><option value=''>--请选择--</option></select>";//字段名
	var f4 = "<select name='_q_operation' onchange='_q_changeOperation(this)' style='width:80px;'><option value=''>--请选择--</option></select>";//运算符
	var f5 = "<input type='text' name='_q_content_1' style='width:90px;'/><select name='_q_content_2' style='width:90px;display:none;'><option value=''>--请选择--</option></select>";//内容
	var f6 = "<select name='_q_rightQuot' style='width:60px;'><option value=''></option><option value=')'>)</option><option value='))'>))</option><option value=')))'>)))</option><option value='))))'>))))</option></select>";//右括号
	var f7 = "<input type='button' value='删除' style='width:60px;' class='btn' onclick='_q_deleteRow(this,\""+_q_tbl_id+"\")'>";//删除
	insertRow(_q_tbl_id,[f1,f2,f3,f4,f5,f6,f7],false);
	var index = $(_q_tbl_id).rows.length - 1;
	if(0 == index){$n("_q_relation")[0].disabled = true;}
	QueryConditionDwr.getQueryFieldName(_q_query_code, function(data){
		addOptions2("_q_fieldName", index, data, null, null, true, true, obj?obj.conditionId:null);
		_q_changeFieldName($n("_q_fieldName")[index], obj);
	});
	if(obj){
		selectOption($n("_q_relation")[index], obj.relation);
		selectOption($n("_q_leftQuot")[index], obj.leftQuot);
		selectOption($n("_q_rightQuot")[index], obj.rightQuot);
	}
}
function _q_deleteRow(obj, tblId){
	deleteRow(obj, tblId);
	var index = $(_q_tbl_id).rows.length;
	if(index>0){$n("_q_relation")[0].disabled = true;}
}
/**
 * 改变查询字段
 * @param obj
 */
function _q_changeFieldName(obj, setObj){
	var index = obj.parentNode.parentNode.rowIndex - 1;
	if(isNull(obj.value)){
		_changeInputContent(index, "1");
		_enableInputContent(index, true);
		$n("_q_content_1")[index].value = "";
		$n("_q_content_2")[index].value = "";
		removeAllOptions($n("_q_operation")[index], true);
		return;
	}
	QueryConditionDwr.getQueryFieldInfo(obj.value, function(dataStr){
		//info(dataStr);
		var data = eval("("+dataStr+")");
		addOptions2("_q_operation", index, data.operation, null, null, true, true, setObj?setObj.operIndex:null);
		_q_changeOperation($n("_q_operation")[index]);
		$n("_q_inputType")[index].value = data.inputType;
		_changeInputContent(index, data.inputType);
		if("2" == data.inputType || "3" == data.inputType || "5" == data.inputType){
			addOptions2("_q_content_2", index, data.inputContent, null, null, true, true, setObj?setObj.content:null);
		}else if("1" == data.inputType && setObj && setObj.content){
			$n("_q_content_1")[index].value = setObj.content;
		}
//		if("4" == data.inputType){
//		}
	});
}
/**
 * 改变运算符
 * @param obj
 */
function _q_changeOperation(obj){
	var index = obj.parentNode.parentNode.rowIndex - 1;
	if(isNull(obj.value)){
		_enableInputContent(index, true);
	}else{
		var tmp = obj.value.split("_");
		_enableInputContent(index, "1" != tmp[1]);
	}
}
function _changeInputContent(index, inputType){
	$n("_q_content_1")[index].style.display = ("1" == inputType?"block":"none");
	$n("_q_content_2")[index].style.display = ("1" == inputType?"none":"block");
}
function _enableInputContent(index, enableFlag){
	$n("_q_content_1")[index].disabled = !enableFlag;
	$n("_q_content_2")[index].disabled = !enableFlag;
}
/**
 * 检查查询条件合法性
 */
function _checkQuery(){
	var leftQuotArr = [], rightQuotArr = [];
	for(var i=0;i<$(_q_tbl_id).rows.length;i++){
		if(isNull($n("_q_fieldName")[i].value)){
			alert("第"+(i+1)+"行 请选择查询字段");
			return false;
		}
		if(isNull($n("_q_operation")[i].value)){
			alert("第"+(i+1)+"行 请选择运算符");
			return false;
		}
		var operation = $n("_q_operation")[i].value;
		var queryVal = _q_getContent($n("_q_inputType")[i].value, operation, i);
		if("1" != operation.split("_")[1]){
			if(isNull(queryVal)){
				alert("第"+(i+1)+"行 请填写查询内容");
				return false;
			}
		}
		//1字符 2整数 3小数 4渲染 5日期str 6日期date
		var fieldType = operation.split("_")[2];
		if("2" == fieldType && !isInteger(queryVal)){
			alert("第"+(i+1)+"行 查询内容必须为整数类型");
			return false;
		}
		if("3" == fieldType && !isDecimal(queryVal)){
			alert("第"+(i+1)+"行 查询内容必须为数字类型");
			return false;
		}
		if(("5" == fieldType || "6" == fieldType) && !isDate(queryVal)){
			alert("第"+(i+1)+"行 查询内容必须为日期类型[yyyy-mm-dd] 并且必须是有效日期");
			return false;
		}
		if(!isNull($n("_q_leftQuot")[i].value)){
			leftQuotArr.push($n("_q_leftQuot")[i].value);
		}
		if(!isNull($n("_q_rightQuot")[i].value)){
			rightQuotArr.push($n("_q_rightQuot")[i].value);
		}
	}
	if(leftQuotArr.length != rightQuotArr.length){
		alert("左右括号未对齐");
		return false;
	}
	for(var i=0;i<leftQuotArr.length;i++){
		if(leftQuotArr[i].length != rightQuotArr[rightQuotArr.length-1-i].length){
			alert("左右括号未对齐");
			return false;
		}
	}
	return true;
}
function _getQueryCondition(){
	var arr = [];
	for(var i=0;i<$(_q_tbl_id).rows.length;i++){
		if(!isNull($n("_q_fieldName")[i].value) && !isNull($n("_q_operation")[i].value)){
			if(("1"==$n("_q_operation")[i].value.split("_")[1]) || ((("1" == $n("_q_inputType")[i].value) && !isNull($n("_q_content_1")[i].value))
				|| (("1" != $n("_q_inputType")[i].value) && !isNull($n("_q_content_2")[i].value)))){				
				arr.push({
					relation : $n("_q_relation")[i].value,
					leftQuot : $n("_q_leftQuot")[i].value,
					conditionId : $n("_q_fieldName")[i].value,
					operIndex : $n("_q_operation")[i].value,
					content : _q_getContent($n("_q_inputType")[i].value, $n("_q_operation")[i].value, i),
					rightQuot : $n("_q_rightQuot")[i].value,
					sort : i
				});
			}
		}
	}
	return arr;
}
function _saveQueryCondition(callbackfunc){
	if(!_checkQuery())	return;
	QueryConditionDwr.saveQueryToTemp(_q_query_code, _getQueryCondition(), function(data){
		if(!data){
			if(callbackfunc){
				callbackfunc();
			}
		}else{
			alert(data);
		}
	});
}
function _q_getContent(inputType, operation, index){
	if("1" == operation.split("_")[1]){
		return null;
	}
	if("1" == inputType){
		return $n("_q_content_1")[index].value;
	}else{
		return $n("_q_content_2")[index].value;
	}
}
//--------------------------------------------------------------------------------------------------