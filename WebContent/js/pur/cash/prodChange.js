
function checkField(){
	var chaNums = jQuery("#chaNums").val();
	if(chaNums){
		if(!isDecimal(chaNums) || floatSub(chaNums, 0) <=0){
			alert("重量必须是大于0的数");
			return false;
		}
	}
	return true;
}

function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField())return false;
	
	return true;
}

function saveForm(){
	if(checkForm()){
		confirm("确定要保存？", function(){
			var prodData = getProdData();
			showLayer(true);
			ProdChangeDwr.saveOrUpdateProdChange(prodData, function(msg){
				showLayer(false);
				if(msg){
					alert(msg);
				} else {
					alert("保存成功", function(){
						window.location = "prodChange.vm";
					});
				}
			});
		});
	}
}

function getProdData(){
	var prodData = {
			chaId:jQuery("#chaId").val(),
			billNo:jQuery("#billNo").val(),
			orgId:jQuery("#orgId").val(),
			voderId:jQuery("#verdorId").val(),
			billDate:jQuery("#billDate").val(),
			materialType:jQuery("#materialType").val(),
			itemClassId:jQuery("#itemClassId").val(),
			dotype:jQuery("#dotype").val(),
			billType:jQuery("#billType").val(),
			chaNums:jQuery("#chaNums").val(),
			memo:jQuery("#memo").val(),
			cashUnit:jQuery("#cashUnit").val(),
			ornaCode:jQuery("#ornaCode").val(),
			status:"1"
	};
	return prodData;
}


/**
 * 审核单据
 */
function checkBill() {
	if(checkForm()){
		confirm("确定要提交审核？", function(){
			var prodData = getProdData();
			showLayer(true);
			ProdChangeDwr.saveAndCheckBill(prodData, function(data){
				showLayer(false);
				redirectBill(data['chaId'], data, "单据提交成功");
	    	});	
		});
	}
}
/**
 * 核算单据
 */
function closeBill() {
	confirm("确定要写入货品台账？", function(){
		var chaId = jQuery("#chaId").val();
		showLayer(true);
		ProdChangeDwr.closeBill(chaId, function(data){
			showLayer(false);
			redirectBill(chaId, data, "台账写入成功");
    	});	
	});
}

/**
 * 跳转url
 * @param billid
 * @param data
 * @param msg
 */
function redirectBill(chaId, data, msg){
	if(data['isSuccess'] == "true") {
		alert(msg, function(){
			window.location = "prodChange.vm?user_action=toEdit&chaId=" + chaId;
		});
	} else {
		alert(data['msg']);
	}
}