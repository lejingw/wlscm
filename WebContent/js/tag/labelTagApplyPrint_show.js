/**
 * 选择标签打印时，过滤行表数据
 */
function onFilterLines(){
	jQuery("#frm").submit();
}

/**
 * 排序规则
 */
function orderByRule(){
	setOrderValue();
	var _iframeId = "orderbyruleIframe";
	var options = {
		title : '排序规则',
		contentType : 'selector',
		iframeId : _iframeId,
		width : 418,
		height : 200,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			getOrderStr();
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	jQuery.weeboxs.open(jQuery("#line_order_by_rule"), options);
}

function setOrderValue(){
	var orderBy = jQuery("#orderBy").val();
	if(orderBy){
		if(orderBy.indexOf("ASC")>=0){
			jQuery("#orderASC").attr("checked", true);
			jQuery("#orderDESC").attr("checked", false);
		} else {
			jQuery("#orderASC").attr("checked", false);
			jQuery("#orderDESC").attr("checked", true);
		}
		if(orderBy.indexOf("item_class_id")>=0){
			jQuery("#orderItemClass").attr("checked", true);
		} else {
			jQuery("#orderItemClass").attr("checked", false);
		}
		if(orderBy.indexOf("orna_class_id")>=0){
			jQuery("#orderOrnaClass").attr("checked", true);
		} else {
			jQuery("#orderOrnaClass").attr("checked", false);
		}
		if(orderBy.indexOf("orna_code")>=0){
			jQuery("#orderOrnaCode").attr("checked", true);
		} else {
			jQuery("#orderOrnaCode").attr("checked", false);
		}
		if(orderBy.indexOf("num")>=0){
			jQuery("#orderNum").attr("checked", true);
		} else {
			jQuery("#orderNum").attr("checked", false);
		}
		if(orderBy.indexOf("old_amount")>=0){
			jQuery("#orderOldMoney").attr("checked", true);
		} else {
			jQuery("#orderOldMoney").attr("checked", false);
		}
		if(orderBy.indexOf("new_amount")>=0){
			jQuery("#orderNewMoney").attr("checked", true);
		} else {
			jQuery("#orderNewMoney").attr("checked", false);
		}
		if(orderBy.indexOf("weight")>=0){
			jQuery("#orderWeight").attr("checked", true);
		} else {
			jQuery("#orderWeight").attr("checked", false);
		}
	} else {
		jQuery("#orderASC").attr("checked", true);
		jQuery("#orderDESC").attr("checked", false);
	}
}

function getOrderStr(){
	var orderASC = jQuery("#orderASC").attr("checked"),
		orderDESC = jQuery("#orderDESC").attr("checked"),
		orderItemClass = jQuery("#orderItemClass").attr("checked"),
		orderOrnaClass = jQuery("#orderOrnaClass").attr("checked"),
		orderOrnaCode = jQuery("#orderOrnaCode").attr("checked"),
		orderNum = jQuery("#orderNum").attr("checked"),
		orderOldMoney = jQuery("#orderOldMoney").attr("checked"),
		orderNewMoney = jQuery("#orderNewMoney").attr("checked"),
		orderWeight = jQuery("#orderWeight").attr("checked");
	var resStr = [], order = "";
	resStr.push("order by");
	if(orderASC){ order = " ASC "; }
	if(orderDESC){order = " DESC ";}
	if(orderItemClass){
		resStr.push('a.item_class_id'+order);
	}
	if(orderOrnaClass){
		resStr.push('a.orna_class_id'+order);
	}
	if(orderOrnaCode){
		resStr.push('a.orna_code'+order);
	}
	if(orderNum){
		resStr.push('a.num'+order);
	}
	if(orderOldMoney){
		resStr.push('a.old_amount'+order);
	}
	if(orderNewMoney){
		resStr.push('a.new_amount'+order);
	}
	if(orderWeight){
		resStr.push('a.weight'+order);
	}
	if(resStr.length >1){
		var len = resStr.length,
			result = "";
		for(var i=0; i<len;i++){
			if(i == len-1 || i==0){
				result = result + " " + resStr[i];
			} else {
				result = result + " " + resStr[i] + ", ";
			}
		}
		// info(result);
		jQuery("#orderBy").val(result);
	} else {
		jQuery("#orderBy").val("");
	}
	jQuery("#frm").submit();
}

function radiofn(chId){
	var checked = jQuery("#"+chId).attr("checked");
	if(chId == "orderASC"){
		if(checked){
			jQuery("#orderDESC").attr("checked", false);
		} else {
			jQuery("#orderDESC").attr("checked", true);
		}
	} else {
		if(checked){
			jQuery("#orderASC").attr("checked", false);
		} else {
			jQuery("#orderASC").attr("checked", true);
		}
	}
}

function setLabelKindValue(idx, isDblLabel){
	if(isDblLabel == "0"){
		var op = new Option("普通标签", "100");
		$n("labelKind")[idx].options.add(op);
	} else {
		var op = new Option("物流标签", "010");
		var op1 = new Option("价格标签", "001");
		var op2 = new Option("物流价格标签", "011");
		$n("labelKind")[idx].options.add(op);
		$n("labelKind")[idx].options.add(op1);
		$n("labelKind")[idx].options.add(op2);
	}
}
function changeSeq(tblId){
	var len = $(tblId).rows.length;
	for(var i=0; i<len; i++){
		$(tblId).rows[i].cells[0].innerHTML = len-i;
	}
}