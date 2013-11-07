function showSelectCashWin(){
	var verdorId = jQuery("#verdorId").val(),
		_iframeId = "prodProdShowWinIframe";
	if(!verdorId){
		alert("请选择供应商");
		return ;
	}
	var options = {
		title : "选择转换单据",
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 720,
		height : 400,
		okBtnName : '确定',
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			
			if(!result){
				return ;
			}
			setOldProdValue(result);
			box.close();
		},
		oncancel : function(box) {
			box.close();
		}
	};
	var url = "prodProd.vm?user_action=showWin&verdorId="+verdorId;
	jQuery.weeboxs.open(url, options);
}


function setOldProdValue(result){
	jQuery("#prId").val(result.prId);
	jQuery("#materialTypeOld").val(result.materialType);
	jQuery("#materialTypeNameOld").val(result.materialTypeName);
	jQuery("#itemClassIdOld").val(result.itemClassId);
	jQuery("#itemClassNameOld").val(result.itemClassName);
	jQuery("#weightOld").val(result.noNums);
}

function cleanOldProd(){
	jQuery("#prId").val("");
	jQuery("#materialTypeOld").val("");
	jQuery("#materialTypeNameOld").val("");
	jQuery("#itemClassIdOld").val("");
	jQuery("#itemClassNameOld").val("");
	jQuery("#weightOld").val("");
	jQuery("#priceOld").val("");
}

function checkField(){
	var weightOld = jQuery("#weightOld").val(),
		priceOld = jQuery("#priceOld").val(),
		weightNew = jQuery("#weightNew").val(),
		priceNew = jQuery("#priceNew").val();
	if(weightOld){
		if(floatSub(weightOld, 0) <=0){
			alert("请选择重量大于0 的单据");
			return false;
		}
	}
	if(priceOld){
		if(!isDecimal(priceOld) || floatSub(priceOld, 0) <=0){
			alert("原价格必须是大于0的数");
			return false;
		}
	}
	if(weightNew){
		if(!isDecimal(weightNew) || floatSub(weightNew, 0) <=0){
			alert("新重量必须是大于0的数");
			return false;
		}
	}
	if(priceNew){
		if(!isDecimal(priceNew) || floatSub(priceNew, 0) <=0){
			alert("新价格必须是大于0的数");
			return false;
		}
	}
	return true;
}

/**
 * 计算料转料 之前和之后的价值是否相等
 * @returns {Boolean}
 */
function sumMoney(){
	var weightOld = jQuery("#weightOld").val(),
		priceOld = jQuery("#priceOld").val(),
		weightNew = jQuery("#weightNew").val(),
		priceNew = jQuery("#priceNew").val();
	if(floatMul(weightOld, priceOld) != floatMul(weightNew, priceNew)){
		alert("料转料 总价值必须相等");
		return false;
	}
	return true;
}

function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	if(!checkField())return false;
	if(!sumMoney()) return false;
	return true;
}

function saveForm(){
	if(checkForm()){
		confirm("确定要保存？", function(){
			var prodData = getProdData();
			showLayer(true);
			ProdProdDwr.saveOrUpdateProdProd(prodData, function(msg){
				showLayer(false);
				if(msg){
					alert(msg);
				} else {
					alert("保存成功", function(){
						window.location = "prodProd.vm";
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
			prId:jQuery("#prId").val(),
			materialTypeOld:jQuery("#materialTypeOld").val(),
			itemClassIdOld:jQuery("#itemClassIdOld").val(),
			weightOld:jQuery("#weightOld").val(),
			priceOld:jQuery("#priceOld").val(),
			materialTypeNew:jQuery("#materialTypeNew").val(),
			itemClassIdNew:jQuery("#itemClassIdNew").val(),
			weightNew:jQuery("#weightNew").val(),
			priceNew:jQuery("#priceNew").val(),
			memo:jQuery("#memo").val(),
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
			ProdProdDwr.saveAndCheckBill(prodData, function(data){
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
	confirm("确定要核算单据？", function(){
		var chaId = jQuery("#chaId").val();
		showLayer(true);
		ProdProdDwr.closeBill(chaId, function(data){
			showLayer(false);
			redirectBill(chaId, data, "结算成功");
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
			window.location = "prodProd.vm?user_action=toEdit&chaId=" + chaId;
		});
	} else {
		alert(data['msg']);
	}
}