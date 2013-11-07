function mysubmit(){
	
}
function changeCheckBox(checkBox) {
	if(checkBox.checked) {
		jQuery("#noCodeCondition").css("display", "none");
		jQuery("#codeCondition").css("display", "");
		jQuery("#isCode").val("1");
		// 清楚列表数据
		removeDataList();
	} else {
		jQuery("#noCodeCondition").css("display", "");
		jQuery("#codeCondition").css("display", "none");
		jQuery("#isCode").val("0");
		jQuery("#frm").submit();
	}
}

function removeDataList(){
	var len = $("tbl").rows.length;
	for(var i=len-1; i>=0; i--){
		deleteRowByIndex(i, "tbl");
	}
}

function onButtons(){
	jQuery("#itemClassId").change(function(){
		var value = {};
		changeItemClass(value);
	});
	jQuery("#ornaClassId").change(function(){
		changeOrnaClass({});
	});
	jQuery("#styleItemClass").change(function(){
		changeStyleItemClass({});
	});
	jQuery("#styleMiddleClass").change(function(){
		changeStyleMiddleClass({});
	});
	jQuery("#styleOrnaClass").change(function(){
		changeStyleOrnaClass({});
	});
	
	jQuery("#btnSearch").click(function(){
		jQuery("#frm").submit();
	});
	jQuery("#btnReset").click(function(){
		jQuery("#itemClassId").val("");
		jQuery("#ornaClassId").val("");
		jQuery("#analysisId").val("");
		jQuery("#styleItemClass").val("");
		jQuery("#styleMiddleClass").val("");
		jQuery("#styleOrnaClass").val("");
		jQuery("#styleId").val("");
		jQuery("#isMaterial").val("");
	});
	jQuery("#btnAddTip").click(addTip);
	jQuery("#btnDelTip").click(deleteTip);
	setEnterKey("ornaCode", loadMaterByOrnaCode);
	setEnterKey("ornaBarCode", loadMaterByBarCode);
}

function changeItemClass(value){
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
		addOptions("ornaClassId", data, null, null, true, true, value.ornaClassId);
		changeOrnaClass(value);
	});
}

function changeOrnaClass(value){
	//获取分析范围
	BdCommonDwr.getAnalysisForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), function(data){
		addOptions("analysisId", data, null, null, true, true, value.analysisId);
	});
	//获取款式大类
	BdCommonDwr.getStyleItemClassForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), function(data){
		addOptions("styleItemClass", data, null, null, true, true, value.styleItemClass);
		changeStyleItemClass(value);
	});
}

/**
 *　改变款式大类
 */
function changeStyleItemClass(value){
	//获取款式中类
	BdCommonDwr.getStyleMiddleClassForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), jQuery("#styleItemClass").val(), function(data){
		addOptions("styleMiddleClass", data, null, null, true, true, value.styleMiddleClass);
		changeStyleMiddleClass(value);
	});
}

/**
 *　改变款式中类
 */
function changeStyleMiddleClass(value){
	//获取款式小类
	BdCommonDwr.getStyleOrnaClassForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), jQuery("#styleItemClass").val(), jQuery("#styleMiddleClass").val(), function(data){
		addOptions("styleOrnaClass", data, null, null, true, true, value.styleOrnaClass);
		changeStyleOrnaClass(value);
	});
}

/**
 *　改变款式小类
 */
function changeStyleOrnaClass(value){
	//获取款式
	BdCommonDwr.getStyleForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), jQuery("#styleItemClass").val(), jQuery("#styleMiddleClass").val(), jQuery("#styleOrnaClass").val(), function(data){
		addOptions("styleId", data, null, null, true, true, value.styleId);
	});
}

	
function addTip() {
	var ornaCodes = getSelectOrnaCodes();
	if(ornaCodes) {
		confirm("确定要加标志？", function(){
			showLayer(true);
			CargoManagerDwr.addMaterTip(ornaCodes, function(){
    			//jQuery("#frm2").submit();
				addTipAfter( true);
				showLayer(false);
    		});
		});
	} else {
		alert("尚未选择要处理的饰品");
	}
}

function addTipAfter(isAdd){
	var idxs = getSelectIndexs("chk");
	//info(idxs);
	for(var i =0; i<idxs.length;i++){
		$n("chk")[idxs[i]].checked = false;
		var flag = "<img src='"+ctxPath+"/image/mclon/flag.png' />";
		if(!isAdd) flag = "";
		$("tbl").rows[idxs[i]].cells[1].innerHTML = flag;
	}
}

function deleteTip() {
	var ornaCodes = getSelectOrnaCodes();
	if(ornaCodes) {
		confirm("确定要清标志？", function(){
			showLayer(true);
			CargoManagerDwr.deleteMaterTip(ornaCodes, function(){
    			//jQuery("#frm2").submit();
				addTipAfter( false);
    			showLayer(false);
    		});	
		});
	} else {
		alert("尚未选择要处理的饰品");
	}
}

function getSelectOrnaCodes(){
	var rowLen = $("tbl").rows.length;
	var ornaCodes = "";
	for(var i=0;i<rowLen ;i++) {
		var isCh = $n("chk")[i].checked;
		if(isCh) {
			var ornaCode = $n("ornaCode_list")[i].value;
			if(ornaCodes){
				ornaCodes = ornaCodes + ";" + ornaCode;
			} else {
				ornaCodes = ornaCode;
			}
		}
	}
	return ornaCodes;
}

//手工输入饰品编码出发的方法
function loadMaterByOrnaCode(){
	var ornaCode = jQuery("#ornaCode").val();
	if(ornaCode){
		CargoManagerDwr.getMaterByOrnaCode(ornaCode, function(res){
			loadData(res);
		});
	} else {
		alert("请输入饰品编码");
	}
	
}
// 扫描枪 扫描饰品出发的方法
function loadMaterByBarCode(){
	var barCode = jQuery("#ornaBarCode").val();
	if(barCode){
		CargoManagerDwr.getMaterByBarCode(barCode, function(res){
			loadData(res);
		});
	} else {
		alert("请输入饰品条码");
	}
}

function loadData(result){
	if(result){
		if(result.isSuccess == "true"){
			var mater = result.data;
			addMater(mater);
		} else {
			alert(result.msg);
		}
	} else {
		alert("系统异常，请稍后重试");
	}
}

function checkExistMater(mater){
	var ornaCode = mater.ornaCode,
		len = $("tbl").rows.length;
	for(var i=0; i<len; i++){
		if(ornaCode == $n("ornaCode_list")[i].value){
			alert("饰品已经存在列表中");
			return true;
		}
	}
	return false;
}
function addMater(mater){
	if(checkExistMater(mater)) return;
	var ornaCode = mater.ornaCode,
		isMaterial = mater.isMaterial,
		materialtext = "";
	if(isMaterial == "1" || isMaterial == 1){
		materialtext = "<img src='"+ctxPath+"/image/mclon/flag.png' />"	;
	}
	var chbox = "<input type='checkbox' name='chk'/>",
		itemClassName = mater.itemClassName  + '<input type="hidden" name="ornaCode_list" value="'+ornaCode+'"/>',
		ornaClassName = mater.ornaClassName,
		styleName = "<a href='javascript:viewStyle(\""+mater.styleId+"\")'>"+mater.styleName+"</a>",
		alaysisName = mater.alaysisName,
		//ornaCode = mater.ornaCode,
		ornaDsc = mater.ornaDsc,
		unitName = mater.saleUnitName,
		nowQty = mater.nowQty,
		allQty = mater.allQty,
		stoneNowNum = mater.stoneNowNum,
		posAmount = mater.posAmount,
		mainWeight = mater.mainWeight,
		partWeight = mater.partWeight;
	insertRow("tbl",[chbox,materialtext, itemClassName, ornaClassName,styleName, 
	                 alaysisName, ornaCode, ornaDsc, unitName, nowQty, allQty,  
	                 stoneNowNum, posAmount, mainWeight, partWeight], true);
	//var len = $("tbl").rows.length;
	//$n("ornaCode_list")[len-1].parentNode.align = "center";
	jQuery("#ornaCode").val("");
	jQuery("#ornaBarCode").val("");
}
