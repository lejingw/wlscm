/**
 * 页面加载方法
 */
function setPageValue(){
	jQuery("#ornaCode").val("自动生成");
	jQuery("#ornaBarcode").val("自动生成");
	jQuery("#stateName").val("保存");
	jQuery("#decimalNum").val("2");
	var allowInivType = jQuery("#allowInivType").val();//类型
	//柜组 
	DictDwr.getDictsForSlt("group", function(data){
		addOptions("groups", data, null, null, true, true,"10");
	});
	if(allowInivType=="2"||allowInivType=="1"){
		
		//佩戴对象
		BdCommonDwr.getAllWear(function(data){
			addOptions("wearId", data, null, null, true, true);
		});
		//款式类别
		DictDwr.getDictsForSlt("styletype", function(data){
			addOptions("styleType", data, null, null, true, true);
		});
		//生肖	
		DictDwr.getDictsForSlt("bornimg", function(data){
			addOptions("zodiac", data, null, null, true, true);
		});
		//常用摘要
		BdCommonDwr.getAllSummary( function(data){
			addOptions("summaryId", data, null, null, true, true);
		});
		if(allowInivType=="1"){
			//红绳工费
			DictDwr.getDictsForSlt("stringworkprice", function(data){
				addOptions("stringWorkPrice", data, null, null, true, true );
			});
		}
			
	}
	if(allowInivType=="2"||allowInivType=="3"){
		//形状
		DictDwr.getDictsForSlt("diashape", function(data){
			addOptions("mainShapeId", data, null, null, true, true);
		});
		//颜色
		DictDwr.getDictsForSlt("diacolor", function(data){
			addOptions("colorId", data, null, null, true, true);
		});
		//净度
		DictDwr.getDictsForSlt("diaclean", function(data){
			addOptions("cleanId", data, null, null, true, true);
		});
		//色级
		DictDwr.getDictsForSlt("diacolorgrade", function(data){
			addOptions("mainColorGradeId", data, null, null, true, true);
		});
		//切工
		DictDwr.getDictsForSlt("diacut", function(data){
			addOptions("cutId", data, null, null, true, true);
		});
		//萤光
		DictDwr.getDictsForSlt("diafluor", function(data){
			addOptions("fluorescenceId", data, null, null, true, true);
		});
		//抛光
		DictDwr.getDictsForSlt("diapoli", function(data){
			addOptions("polishineId", data, null, null, true, true);
		});
		//钻石底尖
		DictDwr.getDictsForSlt("diavertex", function(data){
			addOptions("vertexId", data, null, null, true, true);
		});
		//主石腰围
		DictDwr.getDictsForSlt("diawais", function(data){
			addOptions("waistlineId", data, null, null, true, true);
		});
		//钻石修饰度
		DictDwr.getDictsForSlt("diamodi", function(data){
			addOptions("mainModification", data, null, null, true, true);
		});
		//主石对称性
		DictDwr.getDictsForSlt("diasymm", function(data){
			addOptions("symmetryId", data, null, null, true, true);
		});
		
		if(allowInivType=="2"){
			//净度
			DictDwr.getDictsForSlt("diaclean", function(data){
				addOptions("cleanIdXq", data, null, null, true, true);
			});
			//色级
			DictDwr.getDictsForSlt("diacolorgrade", function(data){
				addOptions("colorGradeIdXq", data, null, null, true, true);
			});
		}
	}
}
function getInivCode(){
	PriceHeadDwr.getInivCalcCode("1",function(code){
		if(null==code||""==code){
			jQuery("#btnSave1").attr("disabled", "disabled");
			jQuery("#btnSave2").attr("disabled", "disabled");
			jQuery("#btnSave3").attr("disabled", "disabled");
			alert("当前登录人员没有入库编码");
		}
	});
}
function setButDis(state){
	if(state=="11"){
		jQuery("#btnSave1").attr("disabled", "disabled");
		jQuery("#btnSave2").attr("disabled", "disabled");
		jQuery("#btnSave3").attr("disabled", "disabled");
	}
}
/**
 * 交接单窗口
 */
function showHandover(){
	var allowInivType = jQuery("#allowInivType").val();//类型
	var handoverBillId = jQuery("#handoverBillId").val();
	var options = {
			title : '交接单',
			contentType : 'iframe',
			iframeId : "handoverWindowId",
			width : 900,
			height : 450,
			okBtnName : '确定',
			closeable:true,
			boxid:'winDiv',
			onok : function(box) {
				var result = jQuery("#handoverWindowId")[0].contentWindow.getValues();
				
				if(result==undefined){
					return ;
				}
				changeHandover(result.billid,result.billno,result.verdorId,result.dotype,result.isPsale,result.materialId);
				box.close();
			},
			oncancel : function(box) {
				box.close();
			}
		};
		var url = ctxPath+ "/calc/priceHead.vm?user_action=getHandoerCalcWindow&ininvTypeId="+allowInivType+
					"&handoverBillId="+handoverBillId+"&type=2";
		printWin= jQuery.weeboxs.open(url, options);
}
function countFactoryOrnaCode(v){
	if(v=="1"){
		jQuery("#factoryOrnaCode").removeAttr("disabled");
		jQuery("#factoryOrnaCode").attr("dataType", "Require");
	}else{
		jQuery("#factoryOrnaCode").attr("disabled", "disabled");
		jQuery("#factoryOrnaCode").val("");
		jQuery("#factoryOrnaCode").removeAttr("dataType");
	}
}
/**
 * 改变交接单
 */
function changeHandover(handoverBillId,handoverBillName,venderId,inivFlag,isPsale,materialId){
	var allowInivType = jQuery("#allowInivType").val();//类型
	jQuery("#handoverBillId").val(handoverBillId);
	jQuery("#srcBillNo").val(handoverBillName);
	
	jQuery("#venderId").val(venderId);//供应商
	jQuery("#inivFlag").val(inivFlag);//入库标志
	jQuery("#isConsign").val(isPsale);//是否代销
	countFactoryOrnaCode(isPsale);
	
	
	jQuery("#materialId")[0].checked= (materialId=="1"?true:false);//是否双标签
	
	addOptions("ornaClassId", null, null, null, true, true);
	addOptions("styleItemId", null, null, null, true, true);
	addOptions("styleMiddleId", null, null, null, true, true);
	addOptions("styleOrnaId", null, null, null, true, true);
	addOptions("qualityId", null, null, null, true, true);
	jQuery("#styleName").val("");
	jQuery("#styleId").val("");
	jQuery("#factoryStyleId").val("");
	if(allowInivType!="3")
		addOptions("bracketcolorId", null, null, null, true, true);
	
	changePurchae("");
	
	BdCommonDwr.getCustById(venderId,function(venderName){//交接单下的供应商
		if(venderName!=null)
			jQuery("#venderName").val(venderName);
	});

	PriceHeadDwr.getHandoerItemClassByHeadId(handoverBillId,allowInivType,"","2",function(data){//交接单下的大类
		addOptions("itemClassId", data, null, null, true, true);
	});
	
	if(allowInivType!="1"){
		jQuery("#oldOrnaCode").val("");//原饰品
		/*PriceHeadDwr.getOldOrnaCodeByHeadId(handoverBillId,function(data){//交接单上游饰品
			addOptions("oldOrnaCode", data, null, null, true, true,"$!form.iniv.oldOrnaCode");
		});*/
	}

	jQuery("#nuclearBillId").val("");//核价ID
	jQuery("#nuclearBillName").val("");//核价单号
	jQuery("#calcpriceNo").val("");//核价单号
	jQuery("#partContent").val("");//配件内容
	jQuery("#basicPrice").val("");//基础价
	jQuery("#costPrice").val("");//成本单价
	jQuery("#totalCost").val("");//总成本
	
}
function showPurchaseHeadWind(){
	var handoverBillId = jQuery("#handoverBillId").val();
	if(handoverBillId==""){
		alert("行选择交接单");
		return;
	}
	var options = {
			title : '定做单',
			contentType : 'iframe',
			iframeId : "purchaseWindowId",
			width : 900,
			height : 450,
			okBtnName : '确定',
			closeable:true,
			boxid:'winDiv',
			onok : function(box) {
				var result = jQuery("#purchaseWindowId")[0].contentWindow.getValues();
				
				if(result==undefined||result==""||result==null){
					return ;
				}
				changePurchae(result.billno);
				box.close();
			},
			oncancel : function(box) {
				box.close();
			}
		};
		var url = ctxPath+ "/calc/priceHead.vm?user_action=getPurchaseHeadInivWindow&billid="+handoverBillId+"&billno="+jQuery("#customizeNo").val();
		printWin= jQuery.weeboxs.open(url, options);
}
function changePurchae(no){
	if(no==""){
		jQuery("#customizeNo").val(no);
		jQuery("#isCustom")[0].checked=false;
	}else{
		jQuery("#customizeNo").val(no);
		jQuery("#isCustom")[0].checked=true;
	}
}
function blurCustomizeNo(){
	var handoverBillId = jQuery("#handoverBillId").val();
	var no = jQuery("#customizeNo").val();
	PriceHeadDwr.checkCustomizeNo(no,handoverBillId,function(c){
		if(c){
			jQuery("#isCustom")[0].checked=true;
		}else{
			jQuery("#customizeNo").val("");
			jQuery("#isCustom")[0].checked=false;
		}
	});
	
}
function showNuclearBill(){
	var allowInivType = jQuery("#allowInivType").val();//类型
	var handoverBillId = jQuery("#handoverBillId").val();//交接单
	if(handoverBillId==""){
		alert("请选择交接单");
		return;
	}
	var unitId = jQuery("#unitId").val();
	if("612"!=unitId){
		alert("计量单位必须为件");
		return;
	}
	var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
	var itemClassId = jQuery("#itemClassId").val();
	var ornaClassId = jQuery("#ornaClassId").val();
	var options = {
		title : '核价单',
		contentType : 'iframe',
		iframeId : "NuclearBillWindowId",
		width : 1050,
		height : 450,
		okBtnName : '确定',
		closeable:true,
		boxid:'winnDiv',
		onok : function(box) {
			var result = jQuery("#NuclearBillWindowId")[0].contentWindow.getValues();
			
			if(result==undefined){
				return ;
			}
			findPByid(result.id);
			box.close();
		},onclick :function(box){
			info(box)
		},oncancel : function(box) {
			box.close();
		}
	};
	var url = ctxPath+ "/calc/priceHead.vm?user_action=loadPriceByInivPage&inivType="+allowInivType+
				"&itemClassId="+itemClassId+"&ornaClassId="+ornaClassId+"&handoverBillId="+handoverBillId+
				"&id="+nuclearBillId;
	printWin = jQuery.weeboxs.open(url, options);
	
}
function findPByid(id){
	PriceHeadDwr.getPriceById(id,function(data){
		afterFindPrice(data,"1",jQuery("#styleId").val(),"I");//加载核价数据
	});
}
/**
 * 输入核价单
 */
function enterCalcPriceNo(){
	var calcpriceNo = jQuery("#calcpriceNo").val();
	var handoverBillId = jQuery("#handoverBillId").val();//交接单
	if(handoverBillId==""){
		alert("请选择交接单");
		jQuery("#calcpriceNo").val("");
		return;
	}
	var unitId = jQuery("#unitId").val();
	if("612"!=unitId){
		alert("计量单位必须为件");
		jQuery("#calcpriceNo").val("");
		return;
	}
	PriceHeadDwr.getPriceByNo(calcpriceNo,function(data){
		if(data!=null){
			if(data.handoverNo!=handoverBillId){
				alert("核价单不在该交接单下");
				jQuery("#calcpriceNo").val("");
				return;
			}
			afterFindPrice(data,"1",jQuery("#styleId").val(),"I");//加载核价数据
		}else{
			jQuery("#calcpriceNo").val("");
			jQuery("#nuclearBillId").val("");//核价ID
			jQuery("#nuclearBillName").val("");//核价单号
			alert("没有找到符合的核价单");
		}
	});
}
/**
 * 展现核价数据到页面
 * @param data 数据
 * @param cleanType 是否要清除款式
 * @param styleValue 款式值
 * @param updateType 是否修改进来
 */
var HJweight="";
var HJXQ=true;
var dmainWeight="";
var dsecondWeight="";
function afterFindPrice(data,cleanType,styleValue,updateType){
	var allowInivType = jQuery("#allowInivType").val();//类型
	jQuery("#nuclearBillId").val(data.id);//核价ID
	jQuery("#nuclearBillName").val(data.no);//核价单号
	jQuery("#ornaCode").val(data.wlCode);
	jQuery("#ornaBarcode").val(data.ornaBarCode);
	jQuery("#calcpriceNo").val(data.no);
	HJweight = data.allWeight;
	var decimalNum = jQuery("#decimalNum").val();
	var vender = jQuery("#venderId").val();//供应商

	jQuery("#itemClassId").val(data.itemClassId);
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(data.itemClassId, function(orna){//大类下的小类
		addOptions("ornaClassId", orna, null, null, true, true,data.ornaClassId);
	});
	BdCommonDwr.getArticleTypeDescByItemId(data.itemClassId,function(articleTypeId){//大类下的商品类型
		jQuery("#articleTypeId").val(articleTypeId);
		BdCommonDwr.getArticleTypeDesc(articleTypeId, function(v){
			jQuery("#articleTypeName").val(v);
		});
	});
	
	jQuery("#analysisArangeId").val(data.analysisArangeId);//分析范围
	BdCommonDwr.getAnalysisName(data.analysisArangeId, function(a){//分析范围
		jQuery("#analysisArangeName").val(a);
	});
	jQuery("#partContent").val(data.secondDsc);//配件内容
	jQuery("#basicPrice").val(data.basicPrice);//基础价
	jQuery("#posAmount").val(data.basicPrice);//网点金额
	jQuery("#costPrice").val(Number(data.allWidthTaxPrice).toFixed(decimalNum));//成本单价 （总含税成本）
	jQuery("#totalCost").val(Number(data.allWidthTaxPrice).toFixed(decimalNum));//总成本 （总含税成本）
	jQuery("#isDblLabel")[0].checked= (data.isDoubleLabel=="1"?true:false);//是否双标签
	
	BdCommonDwr.getAllQualityForSlt( function(qdata){//所有材质
		addOptions("qualityId", qdata, null, null, true, true,data.qualityId);
		countLabelType(data.itemClassId,data.ornaClassId,data.labelId+"_"+data.isDoubleLabel);//加载标签类型
	});
	BdCommonDwr.getBracketColorByQualityId(data.qualityId,function(c){//托架材质下托架颜色
		if(allowInivType!="3")
			addOptions("bracketcolorId", c, null, null, true, true,data.sbraColorId);
	});
	countOrnadsc(data.itemClassId,data.ornaClassId,data.qualityId);//加载饰品名
	loadSize(data.itemClassId,data.ornaClassId,jQuery("#sizeId").val());//加载尺寸
	countGtoup(data.itemClassId,data.ornaClassId,jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
	
	disabledByNuclearBill();

	if(updateType=="I"){
		jQuery("#identId").val(data.identId);
		if("2"==allowInivType){
			jQuery("#hrdCert").val(data.hrdCert);
			jQuery("#giaCert").val(data.giaCert);
			jQuery("#igiCert").val(data.igiCert);
			jQuery("#agsCert").val(data.agsCert);                            
			jQuery("#mainStoneNum").val(data.mainNumber);//主石粒数
			jQuery("#partStoneNum").val(data.secondNumber);//配石粒数
			jQuery("#totalNum").val(data.allNumber);//总粒数
			jQuery("#mainWeight").val(setStoneWeight(data.mainWeight,decimalNum,"1"));//主石重量
			jQuery("#partStoneWeight").val(setStoneWeight(data.secondWeight,decimalNum,"1"));//配石重量
			dmainWeight=data.mainWeight;
			dsecondWeight = data.secondWeight;
			
			PriceHeadDwr.getMainStoneLineByHeadId(data.id,function(stoneLine){//石头行
				if(stoneLine!=null)
					PriceHeadDwr.getMaterNoActiveByOrnaCode(stoneLine.luodanCode,data.vender,"",function(mater){
						if(mater!=null){
							var identId = false;
							if((mater.hrdCert!=""&&mater.hrdCert!=null)||(mater.giaCert!=""&&mater.giaCert!=null)||(mater.igiCert!=""&&mater.igiCert!=null))
								identId = true;
							if(identId){
								jQuery("#cleanId").val(stoneLine.cleanId);//净度
								jQuery("#mainColorGradeId").val(stoneLine.colorGreadId);//色级
								HJXQ = false;
								var identValue1 = (mater.hrdCert!=""&&mater.hrdCert!=null)?mater.hrdCert:"";
								var identValue2 = (mater.giaCert!=""&&mater.giaCert!=null)?mater.giaCert:"";
								var identValue3 = (mater.igiCert!=""&&mater.igiCert!=null)?mater.igiCert:"";
								jQuery("#hrdCert").val(identValue1);
								jQuery("#giaCert").val(identValue2);
								jQuery("#igiCert").val(identValue3);
							}else{
								HJXQ = true;
							}
							jQuery("#mainShapeId").val(stoneLine.dmShapeId);//形状
							jQuery("#colorId").val(stoneLine.colorId);//颜色
							jQuery("#cutId").val(stoneLine.cutId);//切工
						}
					});
			});
			
			
			countCostPrice();//计算成本单价
		}
	}else{
		if("2"==allowInivType){
			PriceHeadDwr.getMainStoneLineByHeadId(data.id,function(stoneLine){//石头行
				if(stoneLine!=null)
					PriceHeadDwr.getMaterNoActiveByOrnaCode(stoneLine.luodanCode,data.vender,"",function(mater){
						if(mater!=null){
							var identId = false;
							if((mater.hrdCert!=""&&mater.hrdCert!=null)||(mater.giaCert!=""&&mater.giaCert!=null)||(mater.igiCert!=""&&mater.igiCert!=null))
								identId = true;
							if(identId){
								HJXQ = false;
							}else{
								HJXQ = true;
							}
						}
					});
			});
			dmainWeight=data.mainWeight;
			dsecondWeight = data.secondWeight;
		}
	}
}
function changeCleanIdXq(){
	if(HJXQ)
		jQuery("#cleanId").val(jQuery("#cleanIdXq").val());//净度
}
function changeColorGradeIdXq(){
	if(HJXQ)
		jQuery("#mainColorGradeId").val(jQuery("#colorGradeIdXq").val());//色级
}
/**
 * 核价后disabled控件
 */
function disabledByNuclearBill(){
	jQuery("#smallLabelType").attr("disabled", "disabled");//标签类型
	jQuery("#partContent").attr("disabled", "disabled");//配件内容
	jQuery("#basicPrice").attr("disabled", "disabled");//基础价
	jQuery("#posAmount").attr("disabled", "disabled");//网点金额
	jQuery("#qualityId").attr("disabled", "disabled");//材质
	
	jQuery("#bracketcolorId").attr("disabled", "disabled");//材质颜色
//	jQuery("#ornaName").attr("disabled", "disabled");//饰品名
	jQuery("#itemClassId").attr("disabled", "disabled");//大类
	jQuery("#ornaClassId").attr("disabled", "disabled");//小类
//	jQuery("#styleItemId").attr("disabled", "disabled");//款式大类
//	jQuery("#styleMiddleId").attr("disabled", "disabled");//款式中类
//	jQuery("#styleOrnaId").attr("disabled", "disabled");//款式小类
	jQuery("#articleTypeId").attr("disabled", "disabled");//商品类别
	jQuery("#articleTypeName").attr("disabled", "disabled");//商品类别名
	jQuery("#qualityId").attr("disabled", "disabled");//材质
	jQuery("#costPrice").attr("disabled", "disabled");//成本单价
	jQuery("#isDblLabel").attr("disabled", "disabled");//是否双标签
	
	//jQuery("#priceAttrGroup").removeAttr("disabled"); 
	
}
/**
 * 改变大类
 */
function changeItemClassId(){
	var allowInivType = jQuery("#allowInivType").val();//类型

	BdCommonDwr.getArticleTypeDescByItemId(jQuery("#itemClassId").val(),function(articleTypeId){//大类下的商品类型
		jQuery("#articleTypeId").val(articleTypeId);
		BdCommonDwr.getArticleTypeDesc(articleTypeId, function(v){
			jQuery("#articleTypeName").val(v);
		});
	});
	//大类下的小类
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
		addOptions("ornaClassId", data, null, null, true, true);
		addOptions("styleItemId", null, null, null, true, true);
		addOptions("styleMiddleId", null, null, null, true, true);
		addOptions("styleOrnaId", null, null, null, true, true);
		var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
		if(allowInivType=="3"){
			addOptions("qualityId", null, null, null, true, true);
			jQuery("#unitId").val("");
			jQuery("#analysisArangeId").val("");//分析范围
			jQuery("#analysisArangeName").val("");
			countStronCostPrice();
		}else{
			if(nuclearBillId==""||nuclearBillId==null){
				addOptions("qualityId", null, null, null, true, true);
				addOptions("bracketcolorId", null, null, null, true, true);
			}
		}
	});
	jQuery("#styleName").val("");
	jQuery("#styleId").val("");
	jQuery("#factoryStyleId").val("");
	countCostPrice();//计算成本单价
	beforeCountNewGoldPrice(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val());//特殊工费

}

/**
 * 改变小类
 */
function changeOrnaClassId(){
	var allowInivType = jQuery("#allowInivType").val();//类型
	//loadStyle(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),jQuery("#venderId").val(),jQuery("#handoverBillId").val(),"0","");//加载款式
	loadSize(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),"");//加载尺寸
	
	countLabelType(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),"");//加载标签类型
	countOrnadsc(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),jQuery("#qualityId").val());//加载饰品名
	countGtoup(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
	
	BdCommonDwr.getStyleItemClassForSlt(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(), function(data){//大类小类下的款式大类
		addOptions("styleItemId", data, null, null, true, true);
		addOptions("styleMiddleId", null, null, null, true, true);
		addOptions("styleOrnaId", null, null, null, true, true);
		var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
		if(allowInivType=="3"){
			addOptions("qualityId", null, null, null, true, true);
			jQuery("#unitId").val("");
			jQuery("#analysisArangeId").val("");//分析范围
			jQuery("#analysisArangeName").val("");
			countStronCostPrice();
		}else{
			if(nuclearBillId==""||nuclearBillId==null){
				addOptions("qualityId", null, null, null, true, true);
				addOptions("bracketcolorId", null, null, null, true, true);
			}
		}
	});
	jQuery("#styleName").val("");
	jQuery("#styleId").val("");
	jQuery("#factoryStyleId").val("");
	getAnalysis();
}
/**
 * 款式大类改变
 */
function changeStyleItem(){
	var allowInivType = jQuery("#allowInivType").val();//类型
	BdCommonDwr.getStyleMiddleClassForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), jQuery("#styleItemId").val(), function(data){//款式大类的款式中类
		addOptions("styleMiddleId", data, null, null, true, true);
		addOptions("styleOrnaId", null, null, null, true, true);
		var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
		if(allowInivType=="3"){
			addOptions("qualityId", null, null, null, true, true);
			jQuery("#unitId").val("");
			jQuery("#analysisArangeId").val("");//分析范围
			jQuery("#analysisArangeName").val("");
			countStronCostPrice();
		}else{
			if(nuclearBillId==""||nuclearBillId==null){
				addOptions("qualityId", null, null, null, true, true);
				addOptions("bracketcolorId", null, null, null, true, true);
			}
		}
	});jQuery("#styleName").val("");
	jQuery("#styleId").val("");
	jQuery("#factoryStyleId").val("");

}
/**
 * 款式中类改变
 */
function changeStyleMiddle(){
	var allowInivType = jQuery("#allowInivType").val();//类型
	BdCommonDwr.getStyleOrnaClassForSlt(jQuery("#itemClassId").val(), jQuery("#ornaClassId").val(), jQuery("#styleItemId").val(), jQuery("#styleMiddleId").val(), function(data){//款式中类的款式小类
		addOptions("styleOrnaId", data, null, null, true, true);
		var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
		if(allowInivType=="3"){
			addOptions("qualityId", null, null, null, true, true);
			jQuery("#unitId").val("");
			jQuery("#analysisArangeId").val("");//分析范围
			jQuery("#analysisArangeName").val("");
			countStronCostPrice();
		}else{
			if(nuclearBillId==""||nuclearBillId==null){
				addOptions("qualityId", null, null, null, true, true);
				addOptions("bracketcolorId", null, null, null, true, true);
			}
		}
	});jQuery("#styleName").val("");
	jQuery("#styleId").val("");
	jQuery("#factoryStyleId").val("");

}
/**
 * 款式小类改变
 */
function changeStyleOrna(){
	jQuery("#styleName").val("");
	jQuery("#styleId").val("");
	jQuery("#factoryStyleId").val("");
	var allowInivType = jQuery("#allowInivType").val();//类型
	var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
	if(allowInivType=="3"){
		addOptions("qualityId", null, null, null, true, true);
		jQuery("#unitId").val("");
		jQuery("#analysisArangeId").val("");//分析范围
		jQuery("#analysisArangeName").val("");
		countStronCostPrice();
	}else{
		if(nuclearBillId==""||nuclearBillId==null){
			addOptions("qualityId", null, null, null, true, true);
			addOptions("bracketcolorId", null, null, null, true, true);
		}
	}
}
/**
 * 输入工厂款号
 */
function loadFactoryStyle(){
	var itemClassId = jQuery("#itemClassId").val();
	var ornaClassId = jQuery("#ornaClassId").val();
	var custId = jQuery("#venderId").val();
	var toStyle = jQuery("#factoryStyleId").val();
	var styleId = jQuery("#styleId").val();
	
	if(toStyle=="")	return;
	//else if(styleId!="") return;
	if(itemClassId==""||ornaClassId==""||venderId==""){
		jQuery("#factoryStyleId").val("");
		jQuery("#styleId").val("");
		jQuery("#styleName").val("");
		alert("大小类、供应商不能为空");
	}else{
		PriceHeadDwr.getStyleByToStyle(itemClassId,ornaClassId,custId,toStyle,function(s){
			if(s!=null)
				countStyle(s,"1");
			else{
				jQuery("#factoryStyleId").val("");
				jQuery("#styleId").val("");
				jQuery("#styleName").val("");
				alert("大小类、供应商、工厂款号没有维护款式");
			}
		});
	}
	
}
/**
 * 加载款式相关数据
 * @param style
 * @param vender
 */
function loadStyleData(style,vender,type){
	BdCommonDwr.getStyleByIdAndVendor(style,vender, function(s){
		countStyle(s,type);
		beforeCountNewGoldPrice(s.itemclassid,s.ornaclassid);//处理特殊工费
	});
}
/**
 * 
 * @param s 款式数据
 * @param type 1款式选择 2核价选择 3工厂款号选择
 */
function countStyle(s,type){
	var allowInivType = jQuery("#allowInivType").val();//类型
	var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
	jQuery("#articleTypeId").val(s.articletypeid);
	BdCommonDwr.getArticleTypeDesc(s.articletypeid, function(v){
		jQuery("#articleTypeName").val(v);
	});
	jQuery("#styleId").val(s.styleId);
	jQuery("#styleName").val(s.styleName);
	jQuery("#itemClassId").val(s.itemclassid);
	jQuery("#factoryStyleId").val(s.note==null?"":s.note);//款号
	jQuery("#summaryId").val(s.summaryid);//常用摘要
	
	
	//大类下的小类
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(s.itemclassid, function(data){
		addOptions("ornaClassId", data, null, null, true, true,s.ornaclassid);
		getAnalysis();//分析范围
	});
	//大类小类下的款式大类
	BdCommonDwr.getStyleItemClassForSlt(s.itemclassid,s.ornaclassid, function(data){
		addOptions("styleItemId", data, null, null, true, true,s.styleitemclass);
	});
	//款式大类的款式中类
	BdCommonDwr.getStyleMiddleClassForSlt(s.itemclassid, s.ornaclassid, s.styleitemclass, function(data){
		addOptions("styleMiddleId", data, null, null, true, true,s.stylemiddleclass);
	});
	//款式中类的款式小类
	BdCommonDwr.getStyleOrnaClassForSlt(s.itemclassid, s.ornaclassid, s.styleitemclass, s.stylemiddleclass, function(data){
		addOptions("styleOrnaId", data, null, null, true, true,s.styleornaclass);
	});
	if(allowInivType=="3"){
		jQuery("#unitId").val(s.saleanalyst);//款式下的销售计量单位
		disLock(s.saleanalyst);
		
		BdCommonDwr.getUnitName(s.saleanalyst,function(v){
			jQuery("#unitName").val(v);
		});
		BdCommonDwr.getQualityByStyleId(s.styleId, function(data){//款式下的材质
			addOptions("qualityId", data, null, null, true, true);
		});
		countLabelType(s.itemclassid,s.ornaclassid,"");//加载标签类型
	}else {
		if(nuclearBillId==""||nuclearBillId==null){
			BdCommonDwr.getQualityByStyleId(s.styleId, function(data){//款式下的材质
				addOptions("qualityId", data, null, null, true, true);
				if(allowInivType!="3")
					addOptions("bracketcolorId", null, null, null, true, true);
			});
			countLabelType(s.itemclassid,s.ornaclassid,"");//加载标签类型
			loadSize(s.itemclassid,s.ornaclassid,s.iscm);//加载尺寸
			countGtoup(s.itemclassid,s.ornaclassid,jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
			countCostPrice();//计算成本单价
		}
	}
	countOrnadsc(s.itemclassid,s.ornaclassid,jQuery("#qualityId").val());//加载饰品名
	qtyDquantity(); 
}

function showStyleWindow(){
	var styleId = jQuery("#styleId").val();
	var handoverBillId = jQuery("#handoverBillId").val();
	var allowInivType = jQuery("#allowInivType").val();//类型
	var itemClassId = jQuery("#itemClassId").val();//大类
	var ornaClassId = jQuery("#ornaClassId").val();//小类
	var styleItemId = jQuery("#styleItemId").val();//款式大类
	var styleMiddleId = jQuery("#styleMiddleId").val();//款式中类
	var styleOrnaId = jQuery("#styleOrnaId").val();//款式小类
	var articleTypeId = jQuery("#articleTypeId").val();
	var vender = jQuery("#venderId").val();
	
	printWin = selectStyle(function(id,name){}, null, null, false, styleId, "loadInivStyle", 
			{handoverBillId:handoverBillId,allowInivType:allowInivType,itemClassId:itemClassId,
			ornaClassId:ornaClassId,styleItemId:styleItemId,styleMiddleId:styleMiddleId,
			styleOrnaId:styleOrnaId,vender:vender,articleTypeId:articleTypeId});
}

/**
 * 成色选择方法
 */
function changeQuality(){
	var allowInivType = jQuery("#allowInivType").val();//类型
	var styleId = jQuery("#styleId").val();//款式
	var qualityId= jQuery("#qualityId").val();//成色

	if(allowInivType!="3"){
		if(qualityId==""){
			addOptions("bracketcolorId", null, null, null, true, true,"");
		}else{
			BdCommonDwr.getColorByQualityId(styleId,qualityId,function(data){//托架材质下托架颜色
				addOptions("bracketcolorId", data, null, null, true, true);
			});
		}
	}
	countLabelType(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),"");//加载标签类型
	countOrnadsc(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),qualityId);//加载饰品名
}
/**
 * 尺寸s
 * @param i
 * @param o
 * @param iscm
 */
function loadSize(i,o,iscm){
	BdCommonDwr.getSizeByItemIdAndOrnaId(i,o,function(data){
		addOptions("sizeId", data, null, null, true, true,iscm);
	});
}
function getWeightComp(){
	this.setValueByJs=function(_weight){
		jQuery("#allQty")[0].value=_weight;
		setOrnaWeight();
	}
	return this;
}
/**
 * 重量修改方法
 */
function setOrnaWeight(){
	if(checkNum("allQty"))
		return;
	var allowInivType = jQuery("#allowInivType").val();//类型
	qtyDquantity(); 
	if(allowInivType=="1") {
		changeFactoryFeePrice();
    }
	//countCostPrice();
	
	if(allowInivType=="3"){
		countTotalNum(); 
	}
	getAnalysis();
	var allQty = jQuery("#allQty").val();
	var specialWeight = jQuery("#specialWeight").val();
	if(Number(specialWeight)<=Number(allQty)){
		jQuery("#specialWeight").val("");
	}
}
function blurSpecialWeight(){
	if(checkNum("specialWeight"))
		return;
	var allQty = jQuery("#allQty").val();
	var specialWeight = jQuery("#specialWeight").val();
	if(allQty==""){
		jQuery("#specialWeight").val("");
		alert("输入重量");
	}
	if(specialWeight!=""&&Number(specialWeight)<=Number(allQty)){
		jQuery("#specialWeight").val("");
		alert("特殊重量要大于重量");
	}
}
/**
 * 修改计量单位方法
 */
function changeUnit(){
	qtyDquantity(); 
	countCostPrice(); 
	countGtoup(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
	beforeCountNewGoldPrice(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val());//特殊工费
	countLabelType(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),jQuery("#unitId").val());//加载标签类型
	getAnalysis();
	
	jQuery("#nuclearBillId").val("");//核价ID
	jQuery("#nuclearBillName").val("");//核价单号
	jQuery("#calcpriceNo").val("");//核价单号
	jQuery("#partContent").val("");//配件内容
	jQuery("#basicPrice").val("");//基础价
	jQuery("#posAmount").val("");//基础价
	
	disLock(jQuery("#unitId").val());
}
function disLock(u){
	var lockFlag = jQuery("#lockFlag");
	if("612"==u){	
		lockFlag.removeAttr("disabled");
	}else{
		lockFlag.attr("disabled", "disabled");
		jQuery("#lockFlag")[0].checked= false;
	}
}
function blurBasicPrice(){
	checkNum("basicPrice");
	var bp = Number(jQuery("#basicPrice").val()).toFixed(0)%10;
	var v = Number(jQuery("#basicPrice").val()).toFixed(0);
	if(bp=="4")
		v = floatAdd(Number(jQuery("#basicPrice").val()).toFixed(0),1);
	jQuery("#basicPrice").val(v=="0"?"":v);
	jQuery("#posAmount").val(Number(jQuery("#basicPrice").val()));
	getAnalysis();
}
/**
 * 控制数量
 */
function qtyDquantity(){
	var unitId = jQuery("#unitId").val();
	var allQty = jQuery("#allQty").val();
	
	var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
	var allowInivType = jQuery("#allowInivType").val();//类型
	
	if("612"==unitId){
		jQuery("#quantity").val("1");
		if(allowInivType=="1"&&nuclearBillId==""){
			jQuery("#basicPrice").removeAttr("readonly");
		}else{
			jQuery("#basicPrice").attr("readonly", true);//基础价
		}
		jQuery("#basicPrice").attr("dataType", "Require");
	}else{
		jQuery("#quantity").val(allQty);
		jQuery("#basicPrice").attr("dataType", "");
		jQuery("#basicPrice").attr("readonly", true);//基础价
	}
}
/**
 * 修改总粒数方法
 */
function countTotalNum(){
	if(checkNum("totalNum"))
		return;
	if(!isNumber(jQuery("#totalNum").val())||jQuery("#totalNum").val()=="0"){
		jQuery("#totalNum").val("");
		return;
	}
	var decimalNum = jQuery("#decimalNum").val();
	if(jQuery("#totalNum").val()==""){
		jQuery("#avgNum").val("0");
		return;
	}
	var allQty = jQuery("#allQty").val();
	var totalNum = jQuery("#totalNum").val();
	if(totalNum=="0")
		jQuery("#avgNum").val("0");
	else{
		jQuery("#avgNum").val(setStoneWeight(floatDiv(allQty,totalNum),decimalNum,"2"));
	}
	getAnalysis();
}
/**
 * 
 * 工厂工费处理
 */
function changeFactoryFeePrice(){
	if(checkNum("factoryFeePrice"))
		return;
	var factoryFeeType = jQuery("#factoryFeeType").val();
	if(factoryFeeType==""){
		jQuery("#factoryFee").val("");
		jQuery("#factoryFeePrice").val("");
	}
		
	var factoryFeePrice = jQuery("#factoryFeePrice").val();
	var allQty = jQuery("#allQty").val();
	if("1"==factoryFeeType){
		jQuery("#factoryFee").val(factoryFeePrice);
	}else{
		jQuery("#factoryFee").val(floatMul(factoryFeePrice,allQty).toFixed(2));
	}
	countCostPrice(); 
	beforeCountNewGoldPrice(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val());
}
/*var verdor_charge_price = 0;//特殊工费单价
var verdor_charge_fare = 0;//特殊工费加价
BdCommonDwr.getParameterValue("verdor_charge_price",function(v){
	verdor_charge_price = v;
});
BdCommonDwr.getParameterValue("verdor_charge_fare",function(v){
	verdor_charge_fare = v;
});*/

// =========================================================================
// --------------------------  素金入库特殊工费处理 开始 ---------------------------
// =========================================================================
/**
 * 重新计算特殊工费 by renming 2012-11-12
 */
function reCalcSpecialWorkPrice(){
    var styleId = jQuery("#styleId").val(), // 款式
          verdorId = jQuery("#venderId").val(),
          itemClassId = jQuery("#itemClassId").val(),
          ornaClassId = jQuery("#ornaClassId").val(),
          factoryFeeType = jQuery("#factoryFeeType").val(), // 工厂工费方式 (克|件)
          factoryFeePrice = jQuery("#factoryFeePrice").val(),//工费单价
          allQty = jQuery("#allQty").val(), //  饰品重量
          unitId = jQuery("#unitId").val(),
          allowInivType = jQuery("#allowInivType").val(),
          factoryFee = jQuery("#factoryFee").val();

    BdCommonDwr.getStyleByIdAndVendor(styleId, verdorId, function(style){//款式对象
        var newFactoryFeePrice = factoryFeeType=="2"?factoryFeePrice:floatDiv(factoryFeePrice, allQty);// 特殊工费单价
        /**
         * 为是，在入库时给是否高工艺赋值为是，价格属性组为J
         */
        if(style.isSpecialCharge=="1"){
            jQuery("#privateType")[0].checked = true;//是否高工艺
            var sltData = [{text:"J", value:"J"}];
            addOptions("priceAttrGroup", sltData, null, null, true, true, "J");
            jQuery("#priceAttrGroup").attr("disabled", "disabled");
            if(itemClassId == "146") {// 千足金
                qzjSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId, factoryFee, newFactoryFeePrice, "1");
            } else if(itemClassId == "152") {// 铂金
                ptSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId, factoryFee, newFactoryFeePrice, "1");
            } else {
                jQuery("#specialWorkPrice").val(specialWorkPrice);
                reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
            }
        } else if(style.isSpecialCharge == "0") {
            jQuery("#privateType")[0].checked = false;//是否高工艺
            var sltData = [{text:"J", value:"J"}];
            addOptions("priceAttrGroup", sltData, null, null, true, true, "J");
            jQuery("#priceAttrGroup").attr("disabled", "disabled");
            dealGoldSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId);
        } else {
           /* jQuery("#priceAttrGroup").removeAttr("disabled");
            var priceAttrGroup = jQuery("#priceAttrGroup").val();
            BdCommonDwr.getGoldByIniv(itemClassId, ornaClassId, unitId, allowInivType, function(data){
                if(data && data.length==1){
                    addOptions("priceAttrGroup", data, null, null, true, true,data[0].value);
                }else{
                    addOptions("priceAttrGroup", data, null, null, true, true, priceAttrGroup);
                }

            });*/
            jQuery("#privateType")[0].checked = false;//是否高工艺
            if(itemClassId == "146") {// 千足金
                qzjSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId, factoryFee, newFactoryFeePrice, "2");
            } else if(itemClassId == "152") {// 铂金
                ptSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId, factoryFee, newFactoryFeePrice, "2");
            } else {
                jQuery("#specialWorkPrice").val(specialWorkPrice);
                reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
            }
        }
    });
};

/**
 * 素金小克重 计算工费
 * @param itemClassId
 * @param ornaClassId
 * @param allQty
 * @param factoryFeeType
 */
function dealGoldSpecialWorkPrice (itemClassId, ornaClassId, allQty, styleId){
    GoldSpecialChargeDwr.getGoldSpecialCharge(itemClassId, ornaClassId, allQty, function(data){
        if(data) {
            var sltData = [{text:"J", value:"J"}];
            addOptions("priceAttrGroup", sltData, null, null, true, true, "J");
            jQuery("#priceAttrGroup").attr("disabled", "disabled");
            var specialWorkPrice = floatMul(data.workPrice, allQty);
            specialWorkPrice = clSpecialWorkPrice(specialWorkPrice);
            jQuery("#specialWorkPrice").val(specialWorkPrice);
            if(data.workType == "1") {
                reloadLabelType(itemClassId, ornaClassId, true);//加载价格属性组
            } else {
                reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
            }
        } else {
            jQuery("#specialWorkPrice").val("");
            var sltData = [{text:"M", value:"M"}];
            addOptions("priceAttrGroup", sltData, null, null, true, true, "M");
            jQuery("#priceAttrGroup").attr("disabled", "disabled");
            var vendorId = jQuery("#venderId").val();
            PriceHeadDwr.getVerdorChargeNum(vendorId, styleId, function(data){
                if(data && Number(data) > 0){
                    var sltData = [{text:"MP", value:"MP"}];
                    addOptions("priceAttrGroup", sltData, null, null, true, true, "MP");
                    jQuery("#priceAttrGroup").attr("disabled", "disabled");
                } else {
                    if(jQuery("#qualityId").val() == "79"){
                        var pData = [{"value":"ZJ", "text":"ZJ"}];
                        addOptions("priceAttrGroup", pData, null, null, true, true, "ZJ");
                        jQuery("#priceAttrGroup").attr("disabled", "disabled");
                    }
                }
                reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
            });
        }
    });
}
/**
 * 重新设置标签类型
 */
function reloadLabelType(itemClassId, ornaClassId, privateType){
    var unitId = jQuery("#unitId").val();
    var qualityId = jQuery("#qualityId").val();
    var newPrivateType = privateType==true?"1":"0";
    BdCommonDwr.getNewLabelTypeByCalc(itemClassId, ornaClassId, unitId, qualityId, newPrivateType, function(data){
        if(data==null){
            addOptions("smallLabelType", null, null, null, true, true, "");
            jQuery("#isDblLabel")[0].checked=false;
        }else{
            if(data.length==1){
                var iss = data[0].value.split("_");
                jQuery("#isDblLabel")[0].checked= iss[1]=="1"?true:false;
                addOptions("smallLabelType", data, null, null, true, true, data[0].value);
            }else{
                addOptions("smallLabelType", data, null, null, true, true, "");
            }
        }
    });
}

/**
 * 千足金计算特殊工费
 * @param itemClassId
 * @param ornaClassId
 * @param allQty
 * @param factoryFee 工厂工费
 * @param factoryFeePrice 工厂工费单价
 * @param flag 标志 1:取不到值，使用最小值 2:取不到值 返回空值
 */
function qzjSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId, factoryFee, factoryFeePrice, flag){

    BdCommonDwr.getChargeByNum(factoryFeePrice, flag, function(newSpecialWorkPrice){
        if(newSpecialWorkPrice!=null){
            if(flag == "2") {
                jQuery("#privateType")[0].checked = true;
            }
            var sltData = [{text:"J", value:"J"}];
            addOptions("priceAttrGroup", sltData, null, null, true, true, "J");
            jQuery("#priceAttrGroup").attr("disabled", "disabled");
            var specialWorkPrice = floatAdd(floatMul(newSpecialWorkPrice, allQty), factoryFee);
            specialWorkPrice = clSpecialWorkPrice(specialWorkPrice);
            jQuery("#specialWorkPrice").val(specialWorkPrice);
            reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
        }else{
            if(flag == "1") {
                jQuery("#specialWorkPrice").val("");
                reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
                alert("尚未维护特殊工费");
            } else {
                dealGoldSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId);
            }
        }
    });
}

/**
 * 铂金计算特殊工费
 * @param itemClassId
 * @param ornaClassId
 * @param allQty
 * @param factoryFee 工厂工费
 * @param factoryFeePrice 工厂工费单价
 * @param flag 标志 1:取不到值，使用最小值 2:取不到值 返回空值
 */
function ptSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId, factoryFee, factoryFeePrice, flag){
    BdCommonDwr.getPtChargeByNum(factoryFeePrice, flag, function(newSpecialWorkPrice){
        if(newSpecialWorkPrice!=null){
            var sltData = [{text:"J", value:"J"}];
            addOptions("priceAttrGroup", sltData, null, null, true, true, "J");
            jQuery("#priceAttrGroup").attr("disabled", "disabled");
            var specialWorkPrice = floatAdd(floatMul(newSpecialWorkPrice, allQty), factoryFee);
            specialWorkPrice = clSpecialWorkPrice(specialWorkPrice);
            jQuery("#specialWorkPrice").val(specialWorkPrice);
            reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
        }else{
            if(flag == "1") {
                jQuery("#specialWorkPrice").val("");
                reloadLabelType(itemClassId, ornaClassId, jQuery("#privateType")[0].checked);//加载价格属性组
                alert("尚未维护铂金特殊工费");
            } else {
                dealGoldSpecialWorkPrice(itemClassId, ornaClassId, allQty, styleId);
            }
        }
    });
}

// =========================================================================
// --------------------------  素金入库特殊工费处理 结束 ---------------------------
// =========================================================================
/**
 * 处理特殊工费
 */
function beforeCountNewGoldPrice(itemClassId, ornaClassId){
	var allowInivType = jQuery("#allowInivType").val();//类型
	var unitId = jQuery("#unitId").val();

	var verdorId = jQuery("#venderId").val();

	if(allowInivType=="1"&&unitId=="609"&&itemClassId!=""){//为素金入库 并为克
		DictDwr.getDictsForSlt("factoryitemclassid", function(data){//大类在配置项中
			var isitem = false;
			for ( var int = 0; int < data.length; int++) {
				if(data[int].value==itemClassId){
					isitem = true;
					break;
				}
			}
			if(isitem){
				var factoryFeeType = jQuery("#factoryFeeType").val(); // 工厂工费方式 (克|件)
				var factoryFeePrice = jQuery("#factoryFeePrice").val();//工费单价
				var allQty = jQuery("#allQty").val(); //  饰品重量
				var style = jQuery("#styleId").val(); // 款式
				var vender = jQuery("#venderId").val(); // 供应商
				if(style==""||vender==""||allQty==""||factoryFeeType==""){
					jQuery("#specialWorkPrice").val("");
                    jQuery("#privateType")[0].checked = false;//是否高工艺
					countGtoup(itemClassId, ornaClassId, jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
					return;
				}else{
                    /**
                     * 大类为千足金时并且小类为金条或摆件不作处理
                     */
					if(itemClassId=="146" && (ornaClassId=="59"||ornaClassId=="65")){//是千足金
						//小类为摆件或金条， 不做处理
							jQuery("#specialWorkPrice").val("");
                            jQuery("#privateType")[0].checked = false;//是否高工艺
							countGtoup(itemClassId, ornaClassId, jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
					} else {
                        reCalcSpecialWorkPrice(); // 重新计算特殊工费
                    }
				}
			}else{
				jQuery("#specialWorkPrice").val("");
                jQuery("#privateType")[0].checked = false;//是否高工艺
				countGtoup(itemClassId, ornaClassId, jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
			}
		});
	}else{
		jQuery("#specialWorkPrice").val("");
        jQuery("#privateType")[0].checked = false;//是否高工艺
		countGtoup(itemClassId, ornaClassId, jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
	}
}

function resetWorkPrice(val){
    var allQty = jQuery("#allQty").val();
    if(jQuery("#privateType").attr("checked")) {
        if(floatDiv(val, allQty) > wpLimit) {
            val = floatMul(allQty, wpLimit);
        }
    }
    return val;
}

function clSpecialWorkPrice(tsgf){
    tsgf = resetWorkPrice(tsgf);
	var v = Number(tsgf).toFixed(0);
	var bp = Number(tsgf).toFixed(0)%10;
	if(bp=="4")
		v = floatAdd(Number(tsgf).toFixed(0),1);
//	if(bp==5||bp==0)
//		return v=="0"?"":v;
//	else if(bp<5)
//		v = floatAdd(Number(tsgf).toFixed(0),5-bp);
//	else 
//		v = floatAdd(Number(tsgf).toFixed(0),10-bp);
	return v=="0"?"":v;
}

/**
 * 素金计算特殊工费
 * @param value
 * @param i
 * @param o
 * @param type 1：没有在范围内取最小范围的单价
 *                         2：没有在范围内特殊工费为空
 */
function countSJNewGoldPrice(value,i,o,type){
	var allQty = jQuery("#allQty").val();
	var factoryFee = jQuery("#factoryFee").val();
	BdCommonDwr.getChargeByNum(value,type, function(s){
		if(s!=null){
			var tsgf = floatAdd(floatMul(s,allQty),factoryFee);
            jQuery("#privateType")[0].checked = true;//是否高工艺
            tsgf = clSpecialWorkPrice(tsgf);
			/*var v = Number(tsgf).toFixed(0);
			var bp = Number(tsgf).toFixed(0)%10;
			if(bp==5||bp==0)
				jQuery("#specialWorkPrice").val(v=="0"?"":v);
			else if(bp<5)
				v = floatAdd(Number(tsgf).toFixed(0),5-bp);
			else 
				v = floatAdd(Number(tsgf).toFixed(0),10-bp);*/
			jQuery("#specialWorkPrice").val(tsgf);
		}else{
			jQuery("#specialWorkPrice").val("");
			jQuery("#privateType")[0].checked = false;//是否高工艺
		}
		countGtoup(i,o,jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
	});
}
/**
 * PT计算特殊工费
 * @param value
 * @param i
 * @param o
 * @param type 1：没有在范围内取最小范围的单价
 *                         2：没有在范围内特殊工费为空
 */
function countPTNewGoldPrice(value,i,o,type){
	var allQty = jQuery("#allQty").val();
	var factoryFee = jQuery("#factoryFee").val();
	BdCommonDwr.getPtChargeByNum(value,type, function(s){
		if(s!=null){
			var tsgf = floatAdd(floatMul(s,allQty),factoryFee);
            jQuery("#privateType")[0].checked = true;//是否高工艺
            tsgf = clSpecialWorkPrice(tsgf);
			/*var v = Number(tsgf).toFixed(0);
			var bp = Number(tsgf).toFixed(0)%10;*/
			/*if(bp==5||bp==0)
				jQuery("#specialWorkPrice").val(v=="0"?"":v);
			else if(bp<5)
				v = floatAdd(Number(tsgf).toFixed(0),5-bp);
			else 
				v = floatAdd(Number(tsgf).toFixed(0),10-bp);*/
			jQuery("#specialWorkPrice").val(tsgf);
		}else{
			jQuery("#specialWorkPrice").val("");
			jQuery("#privateType")[0].checked = false;//是否高工艺
		}
		countGtoup(i,o,jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
	});
}
/**
 * 加载价格属性组 （素金和钻石入库）
 * @param i
 * @param o
 * @param ftype
 * @param gvalue
 */
function countGtoup(i,o,ftype,gvalue){
	var allowInivType = jQuery("#allowInivType").val();//类型
	var unitId = jQuery("#unitId").val();
	if(allowInivType=="3") // 裸石入库
		return;
	var factoryFeeType = ftype;
	var specialWorkPrice = jQuery("#specialWorkPrice").val();
	var allQty = jQuery("#allQty").val();
	var style = jQuery("#styleId").val();
	var vender = jQuery("#venderId").val();
	if(style==""||vender==""||allQty==""||factoryFeeType==""||specialWorkPrice==""){
		unitId = jQuery("#unitId").val();
		loadGtoup(i,o,unitId,gvalue);
	}else{
		unitId = "612";
		loadGtoup(i,o,unitId,gvalue);
	}
	
	
}
function loadGtoup(i,o,unitId,value){
	var allowInivType = jQuery("#allowInivType").val();//类型
	if("612"==unitId){
		jQuery("#priceAttrGroup").attr("disabled", "disabled");
	}else{
		jQuery("#priceAttrGroup").removeAttr("disabled"); 
	}
	BdCommonDwr.getGoldByIniv(i,o,unitId,allowInivType, function(data){
		if(data==null){
			addOptions("priceAttrGroup", null, null, null, true, true,"");
		}else{
			if(data.length==1)
				addOptions("priceAttrGroup", data, null, null, true, true,data[0].value);
			else
				addOptions("priceAttrGroup", data, null, null, true, true,value);
		}
	countLabelType(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),"");//加载标签类型
	});
}

/**
 * 加载标签类型
 * @param i
 * @param o
 */
function countLabelType(i,o,v){
	var unitId = jQuery("#unitId").val();
	var qualityId = jQuery("#qualityId").val();
	var privateType = jQuery("#privateType")[0].checked==true?"1":"0";
	if(i==""||o==""){
		addOptions("smallLabelType", null, null, null, true, true,"");
		jQuery("#isDblLabel")[0].checked=false;
		return;
	}
	BdCommonDwr.getNewLabelTypeByCalc(i,o,unitId,qualityId,privateType, function(data){
		if(data==null){
			addOptions("smallLabelType", null, null, null, true, true,"");
			jQuery("#isDblLabel")[0].checked=false;
		}else{
			if(data.length==1){
				var iss = data[0].value.split("_");
				jQuery("#isDblLabel")[0].checked= iss[1]=="1"?true:false;
				addOptions("smallLabelType", data, null, null, true, true,data[0].value);
			}else{
				addOptions("smallLabelType", data, null, null, true, true,v);
			}
		}
	});
}
function changeLabel(){
	var label = jQuery("#smallLabelType").val();
	if(label=="")
		jQuery("#isDblLabel")[0].checked=false;
	else{
		var iss = label.split("_");
		jQuery("#isDblLabel")[0].checked= iss[1]=="1"?true:false;		
	}
}
/**
 * 查找饰品名
 * @param i
 * @param o
 * @param q
 */
function countOrnadsc(i,o,q){
	if(i==""||o=="")
		return;
	BdCommonDwr.getNewOrnadscByItemIdAndOrnaId(i,o,q, function(data){
		if(data!=null)
			jQuery("#ornaName").val(returnOrnaName(data.ornaDsc));
		else
			jQuery("#ornaName").val("");
	});
}
function changeCostPric(){
	
	var allowInivType = jQuery("#allowInivType").val();
	var unitId = jQuery("#unitId").val();
	var v = jQuery("#costPrice").val();
	var decimalNum = jQuery("#decimalNum").val();
	var nuclearBillId = jQuery("#nuclearBillId").val();//核价单	
	
	if(allowInivType!="1")
		return;
	if(checkNum("costPrice")||jQuery("#itemClassId").val()==""||jQuery("#allQty").val()==""){
		if(""==nuclearBillId){
			jQuery("#totalCost").val("");
			jQuery("#costPrice").val("");			
		}
		if(""==nuclearBillId&&"612"==unitId){
			jQuery("#basicPrice").val("");
			jQuery("#posAmount").val("");
		}
		return;
	}
	
	var allQty = jQuery("#allQty").val()==""?0:jQuery("#allQty").val();
	var factoryFee = jQuery("#factoryFee").val()==""?0:jQuery("#factoryFee").val();
	var num = floatMul(v,allQty);
	
	if("609"==unitId){
		jQuery("#totalCost").val(floatAdd(num,factoryFee).toFixed(decimalNum));
	}else if(""==nuclearBillId&&"612"==unitId){
		jQuery("#totalCost").val(floatAdd(num,factoryFee).toFixed(decimalNum));
		//素多入库、按件、没有核价单、大类为时尚千足金时：用总成本到时尚千足金系数表取系数，系数*总成本=基础价
		if("188"==jQuery("#itemClassId").val()){
            BdCommonDwr.getCoefficientBySS(jQuery("#itemClassId").val(), floatAdd(num,factoryFee), function(ss){
                if(ss==null){
                    alert("时尚千足金下没有维护系数");
                    jQuery("#basicPrice").val("");
                    jQuery("#posAmount").val("");
                    return;
                } else {
                    var status = jQuery("#state").val();
                    if(status !="11") {
                        // 爱尚金固定金价
                        MaterInivDwr.getFashionGoldPrice(jQuery("#itemClassId").val(), function(goldPrice){
                            if(goldPrice){
                                var newNum = floatAdd(floatMul(goldPrice, allQty), factoryFee);
                                jQuery("#fixedGoldPrice").val(goldPrice);
                                jQuery("#basicPrice").val(floatMul(newNum, ss));//含税的
                                jQuery("#posAmount").val(jQuery("#basicPrice").val());
                                blurBasicPrice();
                            } else {
                                alert("爱尚金固定金价没维护");
                                jQuery("#basicPrice").val("");
                                jQuery("#posAmount").val("");
                                jQuery("#fixedGoldPrice").val("");
                            }
                        });
                    }
                }
            });
		}else{
			var Coefficient = {
					   itemClassId:jQuery("#itemClassId").val(),
					   qualityId:jQuery("#qualityId").val(),
					   startWeight:allQty};
					   
			CoefficientDwr.getCoefficient(Coefficient,jQuery("#allQty").val(),
				jQuery("#avgNum").val(),jQuery("#totalCost").val(),function(data){
				if(data==null||data.coefficient==null){
					alert("大类材质下没有维护总系数");
					jQuery("#basicPrice").val("");
					jQuery("#posAmount").val("");
					return;
				}
				jQuery("#basicPrice").val(floatMul(floatAdd(num,factoryFee),data.coefficient));
				jQuery("#posAmount").val(jQuery("#basicPrice").val());
				blurBasicPrice();
			});
		}
	}
}
/**
 * 计算成本单价
 */
function countCostPrice(){
	var allowInivType = jQuery("#allowInivType").val();
	var unitId = jQuery("#unitId").val();
	var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
	var handoverBillId= jQuery("#handoverBillId").val();//交接单
	var decimalNum = jQuery("#decimalNum").val();
	
	if(allowInivType=="2"||allowInivType=="3")
		return;
	if(jQuery("#costPrice").val()==""){
		MaterInivDwr.getHangPriceByIniv(handoverBillId,jQuery("#itemClassId").val(),function(v){
			if(v==null){
				if(""==nuclearBillId){
					jQuery("#costPrice").val("");
					jQuery("#totalCost").val("");
				}
				if(""==nuclearBillId&&"612"==unitId){
					jQuery("#basicPrice").val("");
					jQuery("#posAmount").val("");
				}
			}else{
				v = Number(v).toFixed(decimalNum);
				if(""==nuclearBillId){
					jQuery("#costPrice").val(v);
				}
				changeCostPric();
			}
		});
	}else{
		changeCostPric();
	}
}
//裸石计算成本单价
function countStronCostPrice(){
	var allowInivType = jQuery("#allowInivType").val();
	if(allowInivType!="3")
		return;
	var costPrice = jQuery("#costPrice").val();
	if(checkNum("costPrice")||costPrice=="")
		return;
	var one = 0;
	var two = 0;
	var costUnit = jQuery("#costUnit").val();
	var handoverBillId = jQuery("#handoverBillId").val();//交接单
	var itemClassId = jQuery("#itemClassId").val();
	var analysisArangeId = jQuery("#analysisArangeId").val();
	var allQty = jQuery("#allQty").val();
	var decimalNum = jQuery("#decimalNum").val();	
	var totalNum = jQuery("#totalNum").val();
	if("156"!=itemClassId){
		if(costUnit=="1"){
			jQuery("#totalCost").val(floatMul(costPrice,totalNum).toFixed(decimalNum));
		}else if(costUnit=="2"){
			jQuery("#totalCost").val(floatMul(costPrice,allQty).toFixed(decimalNum));
		}else if(costUnit=="3"){
			jQuery("#totalCost").val(Number(costPrice).toFixed(decimalNum));
		}else{
			jQuery("#totalCost").val("");
		}
		return false;
	}else{
		if(handoverBillId==""||itemClassId==""||analysisArangeId==""||allQty==""||totalNum==""||costPrice==""){
			jQuery("#costPrice").val("");
			jQuery("#totalCost").val("");
			return true;
		}
		showLayer(true);
		startSync();
		BdCommonDwr.getParameterValue("diniv_unitcost_one",function(v){
			one = v;
		});
		BdCommonDwr.getParameterValue("diniv_unitcost_two",function(v){
			two = v;
		});
		PriceHeadDwr.getUnitPrice(handoverBillId,itemClassId,analysisArangeId,function(v){
			if(v==null){
				jQuery("#costPrice").val("");
				jQuery("#totalCost").val("");
				alert("参数控制[<成本单价>]:<交接单原料钻石行表该分析范围没有维护原料价格>");
				return true;
			}
			var num1 = floatMul(v,one);
			var num2 = floatMul(v,two);
			if(Number(costPrice)>=floatSub(v,num1)&&Number(costPrice)<=floatAdd(v,num1)){
				if(costUnit=="1"){
					jQuery("#totalCost").val(floatMul(costPrice,totalNum).toFixed(decimalNum));
				}else if(costUnit=="2"){
					jQuery("#totalCost").val(floatMul(costPrice,allQty).toFixed(decimalNum));
				}else if(costUnit=="3"){
					jQuery("#totalCost").val(Number(costPrice).toFixed(decimalNum));
				}else{
					jQuery("#totalCost").val("");
				}
				return false;
			}else if(Number(costPrice)>=floatSub(v,num2)&&Number(costPrice)<=floatAdd(v,num2)){
				if(costUnit=="1"){
					jQuery("#totalCost").val(floatMul(costPrice,totalNum).toFixed(decimalNum));
				}else if(costUnit=="2"){
					jQuery("#totalCost").val(floatMul(costPrice,allQty).toFixed(decimalNum));
				}else if(costUnit=="3"){
					jQuery("#totalCost").val(Number(costPrice).toFixed(decimalNum));
				}else{
					jQuery("#totalCost").val("");
				}
				alert("参数控制[<成本单价>]:<在配置误差范围内>");
				return false;
			}else{
				jQuery("#costPrice").val("");
				jQuery("#totalCost").val("");
				alert("参数控制[<成本单价>]:<不在配置误差范围内>");//参数控制[<参数名称>]:<原提示信息>
				return true;
			}
		});
		endSync();
		showLayer(false);
		return false;
	}
}
function changeCostUnit(){
	var costUnit = jQuery("#costUnit").val();
	var costPrice = jQuery("#costPrice").val();
	var allQty = jQuery("#allQty").val();
	var totalNum = jQuery("#totalNum").val();
	var decimalNum = jQuery("#decimalNum").val();	
	if(costPrice==""||allQty=="")
		return;
	if(costUnit=="1"){
		jQuery("#totalCost").val(floatMul(costPrice,totalNum).toFixed(decimalNum));
	}else if(costUnit=="2"){
		jQuery("#totalCost").val(floatMul(costPrice,allQty).toFixed(decimalNum));
	}else if(costUnit=="3"){
		jQuery("#totalCost").val(Number(costPrice).toFixed(decimalNum));
	}else{
		jQuery("#totalCost").val("");
	}
}
function changeNum(){
	var decimalNum = jQuery("#decimalNum").val();
	var allowInivType = jQuery("#allowInivType").val();//类型
	if(jQuery("#totalCost").val()!="")
		jQuery("#totalCost").val(Number(jQuery("#totalCost").val()).toFixed(decimalNum));
	if(jQuery("#costPrice").val()!="")
		jQuery("#costPrice").val(Number(jQuery("#costPrice").val()).toFixed(decimalNum));
	if(allowInivType=="3"){
		countTotalNum(); 
	}
//	else if(allowInivType=="2"){
//		jQuery("#mainWeight").val(setStoneWeight(dmainWeight,decimalNum));//主石重量
//		jQuery("#partStoneWeight").val(setStoneWeight(dsecondWeight,decimalNum));//配石重量
//		
//	}
}
/**
 * 查询分析范围
 */
function getAnalysis(){
	var allowInivType = jQuery("#allowInivType").val();
	var nuclearBillId = jQuery("#nuclearBillId").val();//核价单
	
	var allQty = jQuery("#allQty").val();
	var mainWeight = 0;
	var basicPrice = 0;
	if(allowInivType=="1"){
		basicPrice = jQuery("#basicPrice").val();
	}else if(allowInivType=="2"){
		basicPrice = jQuery("#basicPrice").val();
		mainWeight = jQuery("#mainWeight").val();
	}else if(allowInivType=="3"){
		mainWeight = jQuery("#allQty").val();
		if(jQuery("#itemClassId").val()=="156"){
			mainWeight=jQuery("#avgNum").val()==""||jQuery("#avgNum").val()==null?"0":jQuery("#avgNum").val();
		}
		if(mainWeight==0){
			jQuery("#analysisArangeId").val("");//分析范围
			jQuery("#analysisArangeName").val("");
			return;
		}
	}
	
	//if(nuclearBillId ==""||nuclearBillId==null||nuclearBillId==undefined){
		PriceHeadDwr.getAnalysis(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val(),
				allQty, basicPrice, mainWeight, function(a){
			if(a==null){
				jQuery("#analysisArangeId").val("");//分析范围
				jQuery("#analysisArangeName").val("");
			}else{
				jQuery("#analysisArangeId").val(a.analysisId);//分析范围
				jQuery("#analysisArangeName").val(a.analysisDesc);
			}
			countStronCostPrice();
		});
		
	//}
}
function checkNum(str){
	var value = jQuery("#"+str+"").val();
	if(value=="")
		return false;
	else if(Number(value)==0||value=="0"){
		jQuery("#"+str+"").val("");
		return false;
	}else if(Number(value)<0){
		jQuery("#"+str+"").val("");
		return false;
	}
	if(!isDecimal(value)){
		alert("请输入正确的数据");
		jQuery("#"+str+"").val("");
		return true;
	}return false;
}
/**
 * 检查表单有效性
 */
function checkForm(v){
	startSync();
	beforeCountNewGoldPrice(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val());
	getAnalysis();
	countCostPrice();
	endSync();
	if(!Validator.Validate($("frm"),3))
		return false;
	if(v!="3"&&"188"==jQuery("#itemClassId").val()){
		if("609"==jQuery("#unitId").val()){
			alert("大类为时尚千足金时，计量必须为件");
			return false;
		}
        var costPrice = jQuery("#costPrice").val(),
            fixedGoldPrice = jQuery("#fixedGoldPrice").val();
        if(!fixedGoldPrice) {
            alert("爱尚金固定金价没维护");
            return false;
        } else {
            if(Math.abs(costPrice-fixedGoldPrice) >fixed_gold_price_range) {
                alert("成本单价与爱尚金固定金价差异值超出范围");
                return false;
            }
        }
	}
	return true;
}
function returnNull(v){
	if(v==""||v==null)
		return null;
	else
		return v;
}
function returnOrnaName(v){
	var ornaName = v;
	var zodiac = jQuery("#zodiac").val();
	if(jQuery("#allowInivType").val()!="3"&&zodiac!=""&&zodiac!=null){
		if((ornaName+"").indexOf("N")!=-1)
			ornaName = (ornaName+"").replace("N","");
		if((ornaName+"").indexOf("("+zodiac)!=-1){
			ornaName=(ornaName+"").replace((ornaName+"").substring((ornaName+"").indexOf("("+zodiac),(ornaName+"").indexOf("("+zodiac)+3), "");
		}
		ornaName = ornaName+"("+zodiac+")";
	}else if(jQuery("#allowInivType").val()!="3"&&(zodiac==""||zodiac==null)){
		if((ornaName+"").indexOf("N")!=-1)
			ornaName = (ornaName+"").replace("N","");
		if((ornaName+"").indexOf("("+zodiac)!=-1){
			ornaName=(ornaName+"").replace((ornaName+"").substring((ornaName+"").indexOf("("+zodiac),(ornaName+"").indexOf("("+zodiac)+3), "");
		}
	}
	
	if("388"==jQuery("#summaryId").val()||"384"==jQuery("#summaryId").val()){
		if((ornaName+"").indexOf("N")!=-1)
			ornaName = (ornaName+"").replace("N","");
		ornaName = ornaName + "N";
	}else{
		if((ornaName+"").indexOf("N")!=-1)
			ornaName = (ornaName+"").replace("N","");
	}
	return ornaName;
}
function setStoneWeight(value,l,type){//保留两位，逢9进1
	if(value==""||value==null)
		return value;
	var xs = (value+"").indexOf(".");
	if(xs==-1)
		return value;
	var v1 = (value+"").substring(0, xs);
	var v2 = (value+"").substring(xs+1, (value+"").length);
	var l = type=="2"?l:Number(value)<0.01?"3":"2";
	
	if((v2+"").length>=l){
		var n1 = (v2+"").substring(l, floatAdd(l,1));
		var n2 = "0."+(v2+"").substring(0, l);
		if(n1=="9"){
			var vn = 0;
			if(l==1)
				vn = floatAdd(n2, 0.1);
			else if(l==2)
				vn = floatAdd(n2, 0.01);
			else if(l==3)
				vn = floatAdd(n2, 0.001);
			return Number(floatAdd(v1,vn)).toFixed(l);
		}else{
			return Number(floatAdd(v1,n2)).toFixed(l);
		}
	}else{
		return value;
	}
}
function getMaterInivHead(){
	var ornaName = returnOrnaName(jQuery("#ornaName").val());
	var decimalNum = jQuery("#decimalNum").val();
	var inivCost = "";
	if(jQuery("#itemClassId").val()=="156"){//原料钻石算原成本 
		startSync();
		var iniv_cost_coefficient = 0;
		PriceHeadDwr.getVendorCoefficient(jQuery("#venderId").val(),function(v){
			iniv_cost_coefficient = v;
		});
//		BdCommonDwr.getParameterValue("iniv_cost_coefficient",function(v){
//			iniv_cost_coefficient = v;
//		});
		inivCost = floatMul(jQuery("#totalCost").val(), iniv_cost_coefficient).toFixed(decimalNum);
		endSync();
	}
	return {
		id               :returnNull(jQuery("#id").val()), //(8)	n			主键                                                   
		no               :returnNull(jQuery("#no").val()), //(20)	y			入库单号                                               
		allowInivType    :returnNull(jQuery("#allowInivType").val()), //(1)	y			允许入库类型                                 
		stockId          :returnNull(jQuery("#stockId").val()), //(8)	y			仓库id                                             
		inivOrg          :returnNull(jQuery("#inivOrg").val()), //(8)	y			入库组织                                           
		inivDirection    :returnNull(jQuery("#inivDirection").val()), //(1)	y			入库方向                                     
		inivDate         :returnNull(jQuery("#inivDate").val()), //(19)	y			入库日期                                                  
		stockOrg         :returnNull(jQuery("#stockOrg").val()), //(8)	y			库存组织                                         
		preAllotOrg      :returnNull(jQuery("#preAllotOrg").val()), //(8)	y			预分配组织                                     
		deptId           :returnNull(jQuery("#deptId").val()), //(8)	y			部门                                               
		salesman         :returnNull(jQuery("#salesman").val()), //(8)	y			业务员                                           
		notes            :returnNull(jQuery("#notes").val()), //(500)	y			备注                                               
		handoverBillId   :returnNull(jQuery("#handoverBillId").val()), //(8)	y			交接单号                                   
		venderId         :returnNull(jQuery("#venderId").val()), //(8)	y			供应商                                           
		articleTypeId    :returnNull(jQuery("#articleTypeId").val()), //(8)	y			商品类别                                     
		itemClassId      :returnNull(jQuery("#itemClassId").val()), //(8)	y			大类                                           
		ornaClassId      :returnNull(jQuery("#ornaClassId").val()), //(8)	y			小类                                           
		analysisArangeId :returnNull(jQuery("#analysisArangeId").val()), //(8)	y			分析范围                                 
		styleItemId      :returnNull(jQuery("#styleItemId").val()), //(8)	y			款式大类                                       
		styleMiddleId    :returnNull(jQuery("#styleMiddleId").val()), //(8)	y			款式中类                                     
		styleId          :returnNull(jQuery("#styleId").val()), //(8)	y			款式                                               
		unitId           :returnNull(jQuery("#unitId").val()), //(8)	y			计量单位                                           
		quantity         :returnNull(jQuery("#quantity").val()), //(24,6)	y			数量                                           
		ornaName         :returnNull(ornaName), //(50)	y			饰品名称                                         
		ornaCode         :returnNull(jQuery("#ornaCode").val()), //(20)	y			饰品编码                                         
		sizeId           :returnNull(jQuery("#sizeId").val()), //(8)	y			尺寸                                     
		isCustom         :returnNull(jQuery("#isCustom")[0]==null?null:jQuery("#isCustom")[0].checked==false?'0':'1'), //(1)	y			是否定做 0 否 1是                                
		costPrice        :returnNull(jQuery("#costPrice").val()), //(24,6)	y			成本单价                                     
		totalCost        :returnNull(jQuery("#totalCost").val()), //(24,6)	y			总成本                                       
		smallLabelType   :returnNull(jQuery("#smallLabelType  option:selected").text()=="--请选择--"?"":jQuery("#smallLabelType  option:selected").text()), //(10)	y			小标签类型                                 
		decimalNum       :returnNull(jQuery("#decimalNum").val()), //(1)	y			小数点保留位数                                 
		trackIame        :returnNull(jQuery("#trackIame").val()), //(20)	y			跟踪名称                                       
		identId          :returnNull(jQuery("#identId").val()), //(100)	y			鉴定证书号                                       
		hrdCert          :returnNull(jQuery("#hrdCert").val()), //(100)	y			hrd国际证书                                      
		giaCert          :returnNull(jQuery("#giaCert").val()), //(100)	y			gia国际证书                                      
		igiCert          :returnNull(jQuery("#igiCert").val()), //(100)	y			igi国际证书                                      
		agsCert          :returnNull(jQuery("#agsCert").val()), //(100)	y			ags国际证书                                      
		totalNum         :returnNull(jQuery("#totalNum").val()), //(16)	y			总粒数                                           
		qualityId        :returnNull(jQuery("#qualityId").val()), //(8)	y			托架材质id                                       
		basicPrice       :returnNull(jQuery("#basicPrice").val()), //(24,6)	y			基础价                                       
		groups           :returnNull(jQuery("#groups").val()), //(4)	y			柜组                                               
		posAmount        :returnNull(jQuery("#posAmount").val()), //(24,6)	y			网点金额                                     
		specialWeight    :returnNull(jQuery("#specialWeight").val()), //(24,6)	y			特殊重量                                 
		weight           :returnNull(jQuery("#weight").val()), //(24,6)	y			重量                                             
		summaryId        :returnNull(jQuery("#summaryId").val()), //(8)	y			常用摘要id                                       
		nuclearBillId    :returnNull(jQuery("#nuclearBillId").val()), //(8)	y			核价单号                                     
		mainModification :returnNull(jQuery("#mainModification").val()), //(24,6)	y			主石修适度                             
		mainShapeId      :returnNull(jQuery("#mainShapeId").val()), //(8)	y			主石形状id                                     
		mainColorGradeId :returnNull(jQuery("#mainColorGradeId").val()), //(8)	y			主石色级id                               
		cleanId          :returnNull(jQuery("#cleanId").val()), //(8)	y			钻石净度id                                         
		colorId          :returnNull(jQuery("#colorId").val()), //(8)	y			钻石颜色id                                         
		cutId            :returnNull(jQuery("#cutId").val()), //(8)	y			切工id                                               
		bracketcolorId   :returnNull(jQuery("#bracketcolorId").val()), //(8)	y			托架颜色id                                 
		wearId           :returnNull(jQuery("#wearId").val()), //(8)	y			佩戴对象                                           
		priceAttrGroup   :returnNull(jQuery("#priceAttrGroup").val()), //(12)	y			价格属性组                                 
		qltyId           :returnNull(jQuery("#qltyId").val()), //(8)	y			品质等级id                                         
		specialWorkPrice :returnNull(jQuery("#specialWorkPrice").val()), //(24,6)	y			特殊工费                               
		stringWorkPrice  :returnNull(jQuery("#stringWorkPrice").val()), //(24,6)	y			红绳工费                               
		cutWideScale     :returnNull(jQuery("#cutWideScale").val()), //(24,6)	y			切工台宽比                                 
		cutDeepScale     :returnNull(jQuery("#cutDeepScale").val()), //(24,6)	y			切工亭深比                                 
		symmetryId       :returnNull(jQuery("#symmetryId").val()), //(8)	y			对称性id                                       
		polishineId      :returnNull(jQuery("#polishineId").val()), //(8)	y			抛光id                                         
		fluorescenceId   :returnNull(jQuery("#fluorescenceId").val()), //(8)	y			荧光id                                     
		waistlineId      :returnNull(jQuery("#waistlineId").val()), //(8)	y			腰围id                                         
		vertexId         :returnNull(jQuery("#vertexId").val()), //(8)	y			底尖id                                           
		factoryFee       :returnNull(jQuery("#factoryFee").val()), //(24,6)	y			工厂工费                                     
		factoryFeePrice  :returnNull(jQuery("#factoryFeePrice").val()), //(24,6)	y			工厂工费单价                           
		mainWeight       :returnNull(jQuery("#mainWeight").val()), //(24,6)	y			主石重量                                     
		mainStoneNum     :returnNull(jQuery("#mainStoneNum").val()), //(18)	y			主石粒数                                     
		partStoneWeight  :returnNull(jQuery("#partStoneWeight").val()), //(24,6)	y			配石重量                               
		partStoneContent :returnNull(jQuery("#partStoneContent").val()), //(100)	y			配石内容                               
		partStoneNum     :returnNull(jQuery("#partStoneNum").val()), //(18)	y			配石粒数                                     
		zodiac           :returnNull(jQuery("#zodiac").val()), //(12)	y			生肖                                               
		allQty           :returnNull(jQuery("#allQty").val()), //(24,6)	y			总重量                                           
		avgNum           :returnNull(jQuery("#avgNum").val()), //(24,6)	y			平均粒数                                         
		isUnder_3fen     :returnNull(jQuery("#isUnder_3fen")[0]==null?null:jQuery("#isUnder_3fen")[0].checked==false?'0':'1'), //(1)	y	0		是否3分以下                                  
		luckyDsc         :returnNull(jQuery("#luckyDsc").val()), //(20)	y			吉祥语标签                                       
		inlayno          :returnNull(jQuery("#inlayno").val()), //(10)	y			镶嵌规格                                         
		updatedate       :returnNull(jQuery("#updatedate").val()), //(19)	y			时间戳                                         
		createuserid     :returnNull(jQuery("#createuserid").val()), //(8)	y			制单人                                       
		createdate       :returnNull(jQuery("#createdate").val()), //(19)	y			制单日期                                       
		factoryStyleId   :returnNull(jQuery("#factoryStyleId").val()), //(8)	y			工厂款号id                                 
		approvaluserid   :returnNull(jQuery("#approvaluserid").val()), //(8)	y			审批人                                     
		approvaldate     :returnNull(jQuery("#approvaldate").val()), //(19)	y			审批日期                                     
		srcBillNo        :returnNull(jQuery("#srcBillNo").val()), //(20)	y			来源单据编号                                   
		srcBillId        :returnNull(jQuery("#srcBillId").val()), //(8)	y			来源单据id                                       
		state            :returnNull(jQuery("#state").val()), //(8)	y			单据状态                                             
		consignmentType  :returnNull(jQuery("#consignmentType").val()), //(1)	y			代销类型                                   
		factoryFeeType   :returnNull(jQuery("#factoryFeeType").val()), //(24,6)	y			工厂公费方式                             
		partContent      :returnNull(jQuery("#partContent").val()), //(100)	y			配件内容                                     
		luckyWord        :returnNull(jQuery("#luckyWord").val()), //(20)	y			吉祥语                                         
		isTearStone      :returnNull(jQuery("#isTearStone")[0]==null?null:jQuery("#isTearStone")[0].checked==false?'0':'1'), //(1)	y			是否拆石                                       
		styleType        :returnNull(jQuery("#styleType").val()), //(3)	y			款式类别                                         
		isMutiPart       :returnNull(jQuery("#isMutiPart")[0]==null?null:jQuery("#isMutiPart")[0].checked==false?'0':'1'), //(1)	y			是否多粒 0 否 1是                              
		styleOrnaId      :returnNull(jQuery("#styleOrnaId").val()), //(8)	y			款式小类                                       
		calcpriceNo      :returnNull(jQuery("#calcpriceNo").val()), //(20)	y			核价单号                                     
		ornaBarcode      :returnNull(jQuery("#ornaBarcode").val()), //(20)	y			饰品条码                                     
		customizeNo      :returnNull(jQuery("#customizeNo").val()), //(20)	y			定做单单号                                   
		isDblLabel       :returnNull(jQuery("#isDblLabel")[0]==null?null:jQuery("#isDblLabel")[0].checked==false?'0':'1'), //(1)	y			是否双标签段
		inivFlag         :jQuery("#inivFlag").val(), //(1)	y			入库标志 1采购;2委外;3料转换;4退料5料提纯6拆石   
		oldOrnaCode      :returnNull(jQuery("#oldOrnaCode").val()), //(20)	y			原饰品编码                                   
		srcBillNo     :returnNull(jQuery("#srcBillNo").val()),//					交接单名
		isConsign     :returnNull(jQuery("#isConsign").val()),//					是否代销
		srcBillCode     :returnNull(jQuery("#srcBillCode").val()),
		costUnit     :returnNull(jQuery("#costUnit").val()),
		materialId     :returnNull(jQuery("#materialId")[0]==null?null:jQuery("#materialId")[0].checked==false?'0':'1'), //(1)	y			是否原料
		lockFlag			:jQuery("#lockFlag")[0].checked==false?'0':'1', //价格锁定 
		colorGradeIdXq     :returnNull(jQuery("#colorGradeIdXq").val()),
		cleanIdXq     :returnNull(jQuery("#cleanIdXq").val()),
		factoryOrnaCode     :returnNull(jQuery("#factoryOrnaCode").val()),//12.07.09修改
		inivCost     :inivCost,
		privateType :returnNull(jQuery("#privateType")[0]==null?null:jQuery("#privateType")[0].checked==false?'0':'1'), //(1)	y			是否高工艺
        fixedGoldPrice     :returnNull(jQuery("#fixedGoldPrice").val())
	};
}
function resetContent(){
	var allowInivType = jQuery("#allowInivType").val();

	jQuery("#weight").val("");
	jQuery("#quantity").val("");
	jQuery("#allQty").val("");
	jQuery("#ornaBarcode").val("");
	jQuery("#avgNum").val("");
	jQuery("#analysisArangeId").val("");
	jQuery("#analysisArangeName").val("");
	jQuery("#ornaCode").val("");
	jQuery("#id").val("");
	jQuery("#no").val("");
	jQuery("#specialWeight").val("");
	jQuery("#totalCost").val("");
	jQuery("#basicPrice").val("");
	jQuery("#posAmount").val("");
	jQuery("#factoryOrnaCode").val("");
	if(allowInivType=="2")
		jQuery("#costPrice").val("");
	if(jQuery("#nuclearBillName").val()!=""&&allowInivType!="3"){
		jQuery("#costPrice").val("");
		jQuery("#totalCost").val("");
		jQuery("#nuclearBillName").val("");
		jQuery("#calcpriceNo").val("");
		jQuery("#nuclearBillId").val("");
		jQuery("#basicPrice").val("");
		jQuery("#posAmount").val("");
        
		jQuery("#mainWeight").val(""); //(24,6)	y			主石重量                                     
		jQuery("#mainStoneNum").val(""); //(18)	y			主石粒数                                     
		jQuery("#partStoneWeight").val(""); //(24,6)	 	配石重量                                  
		jQuery("#partStoneNum").val(""); //(18)	y			配石粒数   
		jQuery("#totalNum").val("");
		
		//countCostPrice(jQuery("#itemClassId").val());
		jQuery("#smallLabelType").removeAttr("disabled");//标签类型
		jQuery("#partContent").removeAttr("disabled");//配件内容
		jQuery("#basicPrice").removeAttr("disabled");//基础价
		jQuery("#posAmount").removeAttr("disabled");//网点金额
		jQuery("#qualityId").removeAttr("disabled");//材质
		
		jQuery("#bracketcolorId").removeAttr("disabled");//材质颜色
		jQuery("#ornaName").removeAttr("disabled");//饰品名
		jQuery("#itemClassId").removeAttr("disabled");//大类
		jQuery("#ornaClassId").removeAttr("disabled");//小类
		jQuery("#articleTypeId").removeAttr("disabled");//商品类别
		jQuery("#articleTypeName").removeAttr("disabled");//商品类别名
		jQuery("#qualityId").removeAttr("disabled");//材质
		jQuery("#costPrice").removeAttr("disabled");//成本单价
		jQuery("#isDblLabel").removeAttr("disabled");//是否双标签
	}
}
/**
 * 保存
 */
function save(type){
    //changeFactoryFeePrice();
	var allowInivType = jQuery("#allowInivType").val();
	if(!checkForm(allowInivType)){
		return null;
	}
	var nb = jQuery("#nuclearBillName").val();//核价单号
	if(nb!=""&&nb!=undefined){
		startSync();
		var iniv_calc_weight = 0;
		BdCommonDwr.getParameterValue("iniv_calc_weight",function(v){
			iniv_calc_weight = v;
		});
		var allQty = Number(jQuery("#allQty").val());
		if(allQty>Number(floatAdd(HJweight, iniv_calc_weight))||
				allQty<Number(floatSub(HJweight, iniv_calc_weight))){
			alert("入库重量必须在系数配置范围内");
			return null;
		}
		endSync();
	}
	
	var	head = getMaterInivHead();
	jQuery("#showMasage").text("处理中....");
	showLayer(true);
	var url = null;
	if("3" == type){
		startSync();
	}
	MaterInivDwr.saveOrUpdateMater(head,function(data){
		showLayer(false);
		var str = data.split("_");
		if("1" != str[0]){
			jQuery("#showMasage").text(str[1]);
			return null;
		}
		if("1" == type){
			window.location = "materIniv.vm";
			return null;
		} else{
			jQuery("#showMasage").text(str[3]+"已保存");
			resetContent();
			if("3" == type){
				var pt = jQuery("#isDblLabel")[0].checked?"2":"1";
				url = ctxPath + '/stock/materIniv.vm?user_action=printLabels&billid=' + str[2] + "&pt=" + pt + "&params=-" + pt + "&isauto=1";
			}
		}
	});
	if("3" == type){
		endSync();
	}
	return url;
}
var printWin = null;
function closePrintWin(){
	if(printWin!=null)
		printWin.close();
}
function countInivCost(costUnit,inivCost){
	var allowInivType = jQuery("#allowInivType").val();
	var allQty = jQuery("#allQty").val();
	var totalNum = jQuery("#totalNum").val();
	var decimalNum = jQuery("#decimalNum").val();
	if(allowInivType=="1"){
		jQuery("#inivCost").val(Number(floatDiv(inivCost,allQty)).toFixed(decimalNum));
	}else{
		if(inivCost!=""){
			if(costUnit=="2"){
				jQuery("#inivCost").val(Number(floatDiv(inivCost,allQty)).toFixed(decimalNum));
			}else{
				jQuery("#inivCost").val(Number(floatDiv(inivCost,totalNum)).toFixed(decimalNum));
			}
		}
	}
}

function selectOldOrnaCode(okfunc, cancelfunc, multiFlag, params) {
	var checkFunc = null;
	var _iframeId = "selectOldOrnaCodeFrm";
	var options = {
		title : '选择',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 500,
		height : 350,
		onok : function(box) {
			var results = jQuery("#" + _iframeId)[0].contentWindow.getValues();
			if(checkFunc && !checkFunc(results[0], results[1], results[2])){
				return;
			}
			okfunc(results[0], results[1], results[2]);
			box.close();// 增加事件方法后需手动关闭弹窗
		},
		oncancel : function(box) {
			if(cancelfunc){
				cancelfunc();
			}
			box.close();// 增加事件方法后需手动关闭弹窗
		}
	};
	var url = ctxPath + '/stock/materIniv.vm?user_action=selectOldOrnaCode&multiFlag=' + (multiFlag?true:false) + '&selectedValues=false';
	if(params){
		for(var p in params){
			url += "&" + p + "=" + params[p];
		}
	}
	//info(url);
	jQuery.weeboxs.open(url, options);
}

function showSelectOrnaCodeWin(){
	selectOldOrnaCode(function(idArr, nameArr){
		jQuery("#oldOrnaCode").val(nameArr.join(','));
	} ,null, false, {"headId":jQuery("#handoverBillId").val()});
}


function resetLabelTypeByItemOrnaWeight() {
    var itemClassId = jQuery("#itemClassId").val(),
          ornaClassId = jQuery("#ornaClassId").val(),
          weight = jQuery("#allQty").val();
    if(itemClassId && ornaClassId && weight && (isDecimal(weight) || isNumber(weight))) {
        //startSync();
        GoldSpecialChargeDwr.getGoldSpecialCharge(itemClassId, ornaClassId, weight, function(data){
            if(data) {
                jQuery("#factoryFeePrice").val(data.workPrice);
                jQuery("#factoryFee").val(floatMul(data.workPrice, weight));
                jQuery("#privateType")[0].checked = false;//是否高工艺
                countGtoup(itemClassId,jQuery("#ornaClassId").val(),jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
                var workType = data.workType;
                var sltData = [], smallLabelType;
                if(workType == "1") { // 特殊工费
                    sltData.push({text:"1", value:"1"});
                    smallLabelType = "1";
                } else {
                    sltData.push({text:"8", value:"8"});
                    smallLabelType = "8";
                }
                addOptions("smallLabelType", sltData, null, null, true, true, smallLabelType);
                jQuery("#smallLabelType").val(smallLabelType);
                var specialWorkPrice = clSpecialWorkPrice(floatMul(weight, data.workPrice));
                jQuery("#specialWorkPrice").val(specialWorkPrice);
            } else {
                jQuery("#specialWorkPrice").val("");
                jQuery("#privateType")[0].checked = false;//是否高工艺
                countGtoup(itemClassId,jQuery("#ornaClassId").val(),jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
            }
        });
        //endSync();
    } else {
        jQuery("#specialWorkPrice").val("");
        jQuery("#privateType")[0].checked = false;//是否高工艺
        countGtoup(itemClassId,jQuery("#ornaClassId").val(),jQuery("#factoryFeeType").val(),jQuery("#priceAttrGroup").val());//加载价格属性组
    }
}
