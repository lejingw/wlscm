function go_to(ao){//跳转
	var h = jQuery("#tab h3");
	var d = jQuery("#tab div");
	for(var i=0;i<h.length;i++) {
		if(ao-1==i) {
			h[i].className+=" up";
			d[i].className+=" block";
		} else {
			h[i].className=" ";
			d[i].className=" ";
		}
	}
}
function getInivCalcCode(){//没有核价编码不能操作
	PriceHeadDwr.getInivCalcCode("2",function(code){
		if(null==code||""==code){
			disabled();
			alert("当前登录人员没有核价编码");
		}
	});
	if(jQuery("#state").val()!="1"&&jQuery("#state").val()!="") disabled();
}
function disabled(){
	var caclType = jQuery("#caclType").val();//核价类型
	
	jQuery("#btnSave1").attr("disabled", "disabled");
	jQuery("#btnSave2").attr("disabled", "disabled");
	jQuery("#btnSave3").attr("disabled", "disabled");
	
	if(caclType=="2"||caclType=="3"){
		jQuery("#btnAddRow1").attr("disabled", "disabled");
		jQuery("#btnAddRow2").attr("disabled", "disabled");
		jQuery("#btnAddRow3").attr("disabled", "disabled");
		if(caclType=="3")
			jQuery("#btnAddRow4").attr("disabled", "disabled");
	}
}
/**
 * 页面加载方法
 */
function setPageValue(){
	jQuery("#wlCode").val("自动生成");
	jQuery("#ornaBarCode").val("自动生成");
	jQuery("#stateName").val("保存");
	var caclType = jQuery("#caclType").val();//核价类型
	
	//所有材质
	BdCommonDwr.getAllQualityForSlt(function(data){
		addOptions("qualityId", data, null, null, true, true);
	});
	//业务类型
	DictDwr.getDictsForSlt("purbiztype", function(data){
		addOptions("inivFlag", data);
	});
	//工费
	if(caclType!="4"){
		SbraTypeDwr.getSbraTypeHeadList("1",function(data){
			if(data==null)
				info("没有维护工费系数");
			else{
				jQuery("#otherCoefficient_h").val(data[0].value.split("_")[1]);
			}
		});		
	}else if(caclType=="4"){
		//形状
		DictDwr.getDictsForSlt("diashape", function(data){
			addOptions("dmShapeId", data, null, null, true, true);
		});
		//颜色
		DictDwr.getDictsForSlt("diacolor", function(data){
			addOptions("colorId",data, null, null, true, true);
		});
	}
	if(caclType=="2"||caclType=="3"){
		//净度
		DictDwr.getDictsForSlt("diaclean", function(data){
			addOptions("cleanIdxq", data, null, null, true, true);
		});
		//色级
		DictDwr.getDictsForSlt("diacolorgrade", function(data){
			addOptions("colorGreadIdxq", data, null, null, true, true);
		});
	}
	if(caclType=="4"||caclType=="3"){
		DictDwr.getDictsForSlt("hlevel", function(data){
			addOptions("hlevel", data, null, null, true, true);
		});
	}
}
/**
 * 交接单窗口
 */
function showHandover(){
	var caclType = jQuery("#caclType").val();//核价类型
	var handoverNo_h = jQuery("#handoverNo_h").val();
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
				changeHandover(result.billid,result.billno,result.verdorId,result.dotype,result.isPsale);
				box.close();
			},
			oncancel : function(box) {
				box.close();
			}
		};
		var url = ctxPath+ "/calc/priceHead.vm?user_action=getHandoerCalcWindow&ininvTypeId="+caclType+
					"&handoverBillId="+handoverNo_h+"&type=1";
		printWin = jQuery.weeboxs.open(url, options);
}
function disableDoType(dotype){
	var caclType = jQuery("#caclType").val();//核价类型
	var id = jQuery("#id").val();
	if(dotype=="1"||dotype=="2"){
		jQuery("#taxCoefficient_h").attr("readOnly", true);
		jQuery("#allWidthoutTaxPrice_h").attr("readOnly", true);
		jQuery("#allWidthTaxPrice_h").attr("readOnly", true);
		jQuery("#mainNumber_h").attr("readOnly", true);
		jQuery("#mainNumber_h").attr("dataType", "");
		jQuery("#mainWeight_h").attr("readOnly", true);
		jQuery("#mainWeight_h").attr("dataType", "");
		jQuery("#secondNumber_h").attr("readOnly", true);
		jQuery("#secondNumber_h").attr("dataType", "");
		jQuery("#secondWeight_h").attr("readOnly", true);
		jQuery("#secondWeight_h").attr("dataType", "");
	}else{
		//jQuery("#taxCoefficient_h").attr("readOnly", false);
		jQuery("#allWidthoutTaxPrice_h").attr("readOnly", false);
		jQuery("#allWidthTaxPrice_h").attr("readOnly", false);
		jQuery("#mainNumber_h").attr("readOnly", false);
		jQuery("#mainNumber_h").attr("dataType", "Integer");
		jQuery("#mainWeight_h").attr("readOnly", false);
		jQuery("#mainWeight_h").attr("dataType", "Double");
		jQuery("#secondNumber_h").attr("readOnly", false);
		//jQuery("#secondNumber_h").attr("dataType", "Integer");
		jQuery("#secondWeight_h").attr("readOnly", false);
		//jQuery("#secondWeight_h").attr("dataType", "Double");
		if(id==""){
			jQuery("#basicPrice_h").val("");
			jQuery("#taxCoefficient_h").val("1.17");
			jQuery("#allWidthoutTaxPrice_h").val("");
			jQuery("#allWidthTaxPrice_h").val("");
		}
	}
	countHeadByStoneLine();
	countHeadBySbraLine();
	countHeadByAcsLine();
	if(caclType=="3")
		countHeadByAccLine();
	countHeadData();
}
function blutMainNumber(){
	checkNum("mainNumber_h");
	checkNum("secondNumber_h");
	checkNum("mainWeight_h");
	var mainNumber_h = jQuery("#mainNumber_h").val();
	var secondNumber_h = jQuery("#secondNumber_h").val();
	var mainWeight_h = jQuery("#mainWeight_h").val();
	if(mainNumber_h!="0"&&mainNumber_h!=""){
		var value = floatDiv(mainWeight_h,mainNumber_h).toFixed(4);
		jQuery("#avgWeight").val(value=="0.00"?"":value);
		loadCoefficient();
	}else{
		jQuery("#avgWeight").val("");
		jQuery("#ncoefficient_h").val("");
		jQuery("#basicPrice_h").val("");
	}
	var v = floatAdd(mainNumber_h,secondNumber_h);
	jQuery("#allNumber_h").val(v=="0"?"":v);
	
}
/**
 * 改变交接单
 */
function changeHandover(billid,billno,verdorId,dotype,isPsale){
	var caclType = jQuery("#caclType").val();//核价类型
	jQuery("#handoverName").val(billno);
	jQuery("#handoverNo_h").val(billid);
	jQuery("#vender_h").val(verdorId);//供应商
	jQuery("#inivFlag").val(dotype);//业务类型0采购 1委外
	jQuery("#isConsignment").val(isPsale);//是否代销
	
	BdCommonDwr.getCustById(verdorId,function(venderName){//交接单下的供应商
		if(venderName!=null)
			jQuery("#venderName_h").val(venderName);
	});
	
	PriceHeadDwr.getHandoerItemClassByHeadId(billid,caclType,"","1",function(data){//交接单下的大类
		addOptions("itemClassId", data, null, null, true, true);
		addOptions("ornaClassId", null, null, null, true, true);
		loadCoefficient();
	});
	cleanLabelData();
	
	if(caclType=="2"||caclType=="3")
		disableDoType(dotype);
}
/**
 * 改变大类
 */
function changeItemClassId(){
	var caclType = jQuery("#caclType").val();//核价类型
	//大类下的小类
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
		addOptions("ornaClassId", data, null, null, true, true);
	});
	loadCoefficient();
	if(caclType=="2"){
		var updateStoneLine = $n("stoneWeight");//计算石头行表市场价和系数数据
		for ( var int = 0; int < updateStoneLine.length; int++) {
			getCoefficeintByItmeClass(updateStoneLine[int]);
			//loadStoneDwr(updateStoneLine[int]);
		}		
	}
}
/**
 * 改变小类
 */
function changeOrnaClassId(){
	var caclType = jQuery("#caclType").val();//核价类型
	var inivFlag = jQuery("#inivFlag").val();
	countLabelType(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val());//加载标签类型
}
/**
 * 成色选择方法
 */
function changeQuality(){
	var caclType = jQuery("#caclType").val();//核价类型
	
	var qualityId= jQuery("#qualityId").val();//成色
	if(qualityId==""){
		jQuery("#sbraColorId_h").val("");
	}else{
		BdCommonDwr.getBracketColorByQualityId(qualityId,function(data){//托架材质下托架颜色
			addOptions("sbraColorId_h", data, null, null, true, true);
		});
	}
	if(caclType!="4")
		loadCoefficient();//查找总系数
	if(caclType=="2"||caclType=="3"){
		  
		var updateSbraLine = $n("sbra_qualityId");//主表托架改变计算托行表架行数据
		for ( var int = 0; int < updateSbraLine.length; int++) {
			countSbraLineData(updateSbraLine[int]);
		}
	}
	countLabelType(jQuery("#itemClassId").val(),jQuery("#ornaClassId").val());//加载标签类型
	
    		
}
/**
 * 素多核价单中材质找系数
 */
function getSbraCoefficientByQualityId(){
	checkNum("sbraWeight_h");

	var qualityId= jQuery("#qualityId").val();//成色
	var itemClassId = jQuery("#itemClassId").val();
	if(qualityId==""||itemClassId==""){
		jQuery("#sbraCoefficient_h").val("");
		jQuery("#priceWithCoefficient_h").val("");
		jQuery("#basicPrice_h").val("");
		return;
	}
	showLayer(true);
	SbraCoefficientDwr.getSbraCoefficientByQualityId(itemClassId,qualityId,jQuery("#sbraWeight_h").val(),function(data){
		showLayer(false);
		if(data==null||data.coefficient==null){
			jQuery("#sbraCoefficient_h").val("");
			jQuery("#priceWithCoefficient_h").val("");
			jQuery("#basicPrice_h").val("");
		}else{
			jQuery("#sbraCoefficient_h").val(data.coefficient);
			jQuery("#priceWithCoefficient_h").val(//金税系数后价
					(Number(data.coefficient)*Number(jQuery("#sbraWidthTaxPrice_h").val())).toFixed(2)
			);
			countGoldPrice();
		}
	});
}
/**
 * 计算素金金价信息
 */
function countGoldPrice(){
	checkNum("sbraMarketPrice_h");

	var sbraMarketPrice_h = jQuery("#sbraMarketPrice_h").val();
	jQuery("#sbraWidthoutTaxPrice_h").val(//金不含税价
		(Number(sbraMarketPrice_h)*Number(jQuery("#sbraWeight_h").val())).toFixed(2)
	);
	jQuery("#sbraWidthTaxPrice_h").val(//金含税价
			(1.17*Number(jQuery("#sbraWidthoutTaxPrice_h").val())).toFixed(2)
	);
	jQuery("#priceWithCoefficient_h").val(//金税系数后价
			(Number(jQuery("#sbraCoefficient_h").val())*Number(jQuery("#sbraWidthTaxPrice_h").val())).toFixed(2)
	);
	changeLpFee();
}
/**
 * 计算金价
 */
function countGold(){
	if(jQuery("#itemClassId option:selected").text()=="镶嵌千足金"){
		var qualityId= jQuery("#qualityId").val();//成色
		var ornaClassId = jQuery("#ornaClassId").val();
		var itemClassId = jQuery("#itemClassId").val();
		if(ornaClassId==""||itemClassId==""){
			jQuery("#newGoldPrice_h").val("");
			return;
		}	
		showLayer(true);
		BdCommonDwr.getGoldByCalc(itemClassId,ornaClassId,qualityId, function(s){
			showLayer(false);
			if(s!=null)
				jQuery("#newGoldPrice_h").val(s);	//金价
			else
				jQuery("#newGoldPrice_h").val("");
		});
	}
}
/**
 * 查找总系数
 */
function loadCoefficient(){
	checkNum("allWeight_h");

	var caclType = jQuery("#caclType").val();//核价类型
	var itemClassId = jQuery("#itemClassId").val();//大类
	var qualityId= jQuery("#qualityId").val();//成色
	var avgWeight = "";
	if(caclType=="1")//查找总系数，素金按饰品总重、钻石按平均分数、其它珠宝按含税总成本。
		avgWeight = jQuery("#allWeight_h").val();
	else if(caclType=="3")
		avgWeight = jQuery("#allWidthTaxPrice_h").val();
	else
		avgWeight = jQuery("#avgWeight").val();
	if(itemClassId==""||qualityId==""||avgWeight==""){
		jQuery("#ncoefficient_h").val("");
		jQuery("#basicPrice_h").val("");
		return ;
	}
	var hlevel = "-1";
	if(caclType=="3"||caclType=="4"){
		if("172"==itemClassId||"159"==itemClassId)//三翡翠
			hlevel = jQuery("#hlevel").val();
	}
	var inivFlag = jQuery("#inivFlag").val();
	if(inivFlag=="1"||inivFlag=="2"){//委外
		var CoefficientWW = {
				   itemClassId:itemClassId,
				   qualityId:qualityId,
				   startWeight:avgWeight,
				   styleStandard:hlevel};

		showLayer(true);
		CoefficientWWDwr.getCoefficientWW(CoefficientWW,jQuery("#allWeight_h").val(),
			jQuery("#avgWeight").val(),jQuery("#allWidthTaxPrice_h").val(),function(data){
			showLayer(false);
			if(data==null||data.coefficient==null){
				jQuery("#ncoefficient_h").val("");
				jQuery("#basicPrice_h").val("");
			}else{
				jQuery("#ncoefficient_h").val(data.coefficient);
				var inivFlag = jQuery("#inivFlag").val();
				var countValue = 0;
				if(("1"==inivFlag||"2"==inivFlag)&&(caclType=="2"||caclType=="3"))
					countValue = jQuery("#allmoneyWithCoefficient_h").val();					
				else	
					countValue = jQuery("#allWidthTaxPrice_h").val();
				checkBasicPrice(floatMul(countValue,data.coefficient).toFixed(2));
			}
		});
	}else{
		var Coefficient = {
				   itemClassId:itemClassId,
				   qualityId:qualityId,
				   startWeight:avgWeight,
				   styleStandard:hlevel};

		showLayer(true);
		CoefficientDwr.getCoefficient(Coefficient,jQuery("#allWeight_h").val(),
			jQuery("#avgWeight").val(),jQuery("#allWidthTaxPrice_h").val(),function(data){
			showLayer(false);
			if(data==null||data.coefficient==null){
				jQuery("#ncoefficient_h").val("");
				jQuery("#basicPrice_h").val("");
			}else{
				jQuery("#ncoefficient_h").val(data.coefficient);
				var inivFlag = jQuery("#inivFlag").val();
				var countValue = 0;
				if(("1"==inivFlag||"2"==inivFlag)&&(caclType=="2"||caclType=="3"))
					countValue = jQuery("#allmoneyWithCoefficient_h").val();					
				else	
					countValue = jQuery("#allWidthTaxPrice_h").val();
				checkBasicPrice(floatMul(countValue,data.coefficient).toFixed(2));
			}
		});
	}
}
/**
 * 加载标签类型
 * @param i
 * @param o
 */
function countLabelType(i,o){
	if(i==""||o==""){
		addOptions("labelId_h", null, null, null, true, true,"");
		jQuery("#isDoubleLabel_h")[0].checked=false;
		return;
	}
	var qualityId = jQuery("#qualityId").val();
	showLayer(true);
	BdCommonDwr.getNewLabelTypeByCalc(i,o,"612",qualityId,"0", function(data){
		showLayer(false);
		if(data==null){
			addOptions("labelId_h", null, null, null, true, true,"");
			jQuery("#isDoubleLabel_h")[0].checked=false;
		}else{
			if(data.length==1){
				var iss = data[0].value.split("_");
				jQuery("#isDoubleLabel_h")[0].checked= iss[1]=="1"?true:false;
				addOptions("labelId_h", data, null, null, true, true,data[0].value);
			}else{
				addOptions("labelId_h", data, null, null, true, true);
			}
		}
	});
}
function changeLabel(){
	var label = jQuery("#labelId_h").val();
	if(label=="")
		jQuery("#isDoubleLabel_h")[0].checked=false;
	else{
		var iss = label.split("_");
		jQuery("#isDoubleLabel_h")[0].checked= iss[1]=="1"?true:false;		
	}
}
/**
 * 删除行表，重新统计行表数据
 * @param obj
 * @param t
 * @param index
 */
function beforeDeleteRow(obj,t,index){
	confirm("确定要删除", function(){
		var rowIndex = obj.parentNode.parentNode.rowIndex;
		if(index==1){
			var lineid = $n("stoneId")[rowIndex-1].value;
			var delIds = jQuery("#deleteStoneLineIds").val(); 
			if(delIds) {
				jQuery("#deleteStoneLineIds").val(delIds + ";" + lineid);
			} else {
				jQuery("#deleteStoneLineIds").val(lineid);
			}
		}else if(index==2){
			var lineid = $n("sbraLineId")[rowIndex-1].value;
			var delIds = jQuery("#deleteSbraLineIds").val();
			if(delIds) {
				jQuery("#deleteSbraLineIds").val(delIds + ";" + lineid);
			} else {
				jQuery("#deleteSbraLineIds").val(lineid);
			}
		}else if(index==3){
			var lineid = $n("acsLineId")[rowIndex-1].value;
			var delIds = jQuery("#deleteAcsLineIds").val();
			if(delIds) {
				jQuery("#deleteAcsLineIds").val(delIds + ";" + lineid);
			} else {
				jQuery("#deleteAcsLineIds").val(lineid);
			}
		}else if(index==4){
			var lineid = $n("accLineId")[rowIndex-1].value;
			var delIds = jQuery("#deleteAccLineIds").val();
			if(delIds) {
				jQuery("#deleteAccLineIds").val(delIds + ";" + lineid);
			} else {
				jQuery("#deleteAccLineIds").val(lineid);
			}
		}
		deleteRow(obj, t);
		if(index==1)
			countHeadByStoneLine();
		else if(index==2)
			countHeadBySbraLine();
		else if(index==3)
			countHeadByAcsLine();
		else if(index==4)
			countHeadByAccLine();
	});
}
/**
 * 工费计算方法
 */
function changeLpFee(){
	checkNum("feeWidthoutTaxUnitPrice_h");
	checkNum("lpFee_h");

	var caclType = jQuery("#caclType").val();//核价类型
	
	var factoryFeeType_h = jQuery("#factoryFeeType_h").val();//工费计算方式 
	if(factoryFeeType_h==""){
		jQuery("#feeWidthoutTaxPrice_h").val("");
		jQuery("#feeWidthTaxPrice_h").val("");
		jQuery("#feeWithCoefficient_h").val("");
		jQuery("#feeMarketPrice_h").val("");
		return;
	}
	//托架重量：件为1、克为值
	var sbraWeight_h = factoryFeeType_h=="1"?1:jQuery("#sbraWeight_h").val();
	if(sbraWeight_h==null){
		return;
	}
	if(caclType=="2"||caclType=="3"){
		//工费不含税：工费计算方式为件，工费*（托架重量）。工费计算方式为克，工费*1）+拉/喷沙工费。
		jQuery("#feeWidthoutTaxPrice_h").val((Number(jQuery("#feeWidthoutTaxUnitPrice_h").val()*sbraWeight_h)+
											Number(jQuery("#lpFee_h").val())).toFixed(2));
		//工费含税：工费不含税*工费税率。
		jQuery("#feeWidthTaxPrice_h").val((jQuery("#feeWidthoutTaxPrice_h").val()*1.17).toFixed(2));
		
		//工费含系数后价：工费系数*工费含税。
		jQuery("#feeWithCoefficient_h").val((jQuery("#otherCoefficient_h").val()*jQuery("#feeWidthTaxPrice_h").val()).toFixed(2));
		
		//工费市场价：工费含税价。
		jQuery("#feeMarketPrice_h").val(jQuery("#feeWidthTaxPrice_h").val());
		
	}else if(caclType=="1"){
		//工费不含税：工费计算方式为件，工费*（托架重量）。工费计算方式为克，工费*1）+拉/喷沙工费。
		jQuery("#feeWidthoutTaxPrice_h").val((Number(jQuery("#feeWidthoutTaxUnitPrice_h").val()*sbraWeight_h)).toFixed(2));
		//工费含税：工费不含税*工费税率。
		jQuery("#feeWidthTaxPrice_h").val((jQuery("#feeWidthoutTaxPrice_h").val()*1.17).toFixed(2));
		//工费含系数后价：工费系数*工费含税。
		jQuery("#feeWithCoefficient_h").val((jQuery("#otherCoefficient_h").val()*jQuery("#feeWidthTaxPrice_h").val()).toFixed(2));
		
		//getSbraCoefficientByQualityId();//素多核价单中材质找系数
	}
	countHeadData();
}
/**
 * 取分析范围
 * @param i
 * @param o
 * @param aw
 * @param bp
 * @param mw
 */
function getAnalysisArange(i,o,aw,bp,mw){
	var analysisArangeId_h = jQuery("#analysisArangeId_h").val();
	var analysisArangeName_h = jQuery("#analysisArangeName_h").val();
	
	if(i==null||o==null){
		jQuery("#analysisArangeId_h").val("");
		jQuery("#analysisArangeName_h").val("");
		return ;
	}
	PriceHeadDwr.getAnalysisArange(i,o,aw,bp,mw,function(a){
		if(a!=null){
			jQuery("#analysisArangeId_h").val(a.analysisId);
			jQuery("#analysisArangeName_h").val(a.analysisDesc);
		}else{
			alert("分析范围没有维护")
			jQuery("#analysisArangeId_h").val("");
			jQuery("#analysisArangeName_h").val("");
		}
	});
}
function blurAllWidthoutTaxPrice_h(){
	if(checkNum("allWidthoutTaxPrice_h"))
		return;
	var allWidthoutTaxPrice_h = jQuery("#allWidthoutTaxPrice_h").val();
	var taxCoefficient_h = jQuery("#taxCoefficient_h").val();
	var value = floatMul(allWidthoutTaxPrice_h,taxCoefficient_h).toFixed(2);
	jQuery("#allWidthTaxPrice_h").val(value=="0.00"?"":value);
	
	var caclType = jQuery("#caclType").val();
	if(caclType!="4")
		loadCoefficient();//查找总系数
}
function blurAllWidthTaxPrice_h(){
	if(checkNum("allWidthTaxPrice_h"))
		return;
	var allWidthTaxPrice_h = jQuery("#allWidthTaxPrice_h").val();
	var taxCoefficient_h = jQuery("#taxCoefficient_h").val();
	var value = 0;
	if(taxCoefficient_h==""||taxCoefficient_h=="0")
		value = "";
	else
		value = floatDiv(allWidthTaxPrice_h,taxCoefficient_h).toFixed(2);
	jQuery("#allWidthoutTaxPrice_h").val(value=="0.00"?"":value);
	
	var caclType = jQuery("#caclType").val();
	if(caclType!="4")
		loadCoefficient();//查找总系数
}
/**
 * 国检证书控件石头市场价
 */
function changeIdentId(){
	var updateStoneLine = $n("stoneWeight");//计算石头行表市场价和系数数据
	for ( var int = 0; int < updateStoneLine.length; int++) {
		loadStoneDwr(updateStoneLine[int]);
	}
}
/**
 * 计算素金金价
 */
function countGoldPrice_G(type){
	var selfMadeTax = jQuery("#selfMadeTax")[0].checked;
	var sbraMarketPrice_h = jQuery("#sbraMarketPrice_h").val();
	var sbraWidthoutTaxPrice_h = jQuery("#sbraWidthoutTaxPrice_h").val();
	var allWeight_h = jQuery("#allWeight_h").val();
	
	if("1"==type){
		if(checkNum("sbraMarketPrice_h")){
			jQuery("#sbraWidthoutTaxPrice_h").val("");
			jQuery("#sbraWidthTaxPrice_h").val("");
		}else
			jQuery("#sbraWidthoutTaxPrice_h").val(sbraMarketPrice_h);
	}else{
		if(checkNum("sbraWidthoutTaxPrice_h")){
			jQuery("#sbraMarketPrice_h").val("");
			jQuery("#sbraWidthTaxPrice_h").val("");
		}else
			jQuery("#sbraMarketPrice_h").val(sbraWidthoutTaxPrice_h);
	}
	jQuery("#sbraWidthTaxPrice_h").val(floatMul(jQuery("#sbraWidthoutTaxPrice_h").val()
			,allWeight_h).toFixed(2));
	if(selfMadeTax)
		return;
	countAll_G();
}
/**
 * 计算素金工费
 */
function changeLpFee_G(type){
	var selfMadeTax = jQuery("#selfMadeTax")[0].checked;
	var allWeight_h = jQuery("#allWeight_h").val();
	var feeWidthoutTaxUnitPrice_h = jQuery("#feeWidthoutTaxUnitPrice_h").val();
	var feeWidthoutTaxPrice_h = jQuery("#feeWidthoutTaxPrice_h").val();
	
	var factoryFeeType_h = jQuery("#factoryFeeType_h").val();//工费计算方式 
	var num = factoryFeeType_h=="1"?1:allWeight_h;//托架重量：件为1、克为值
		
	if("1"==type){
		if(checkNum("feeWidthoutTaxUnitPrice_h")){
			jQuery("#feeWidthoutTaxUnitPrice_h").val("");
			jQuery("#feeWidthoutTaxPrice_h").val("");
		}else
			jQuery("#feeWidthoutTaxPrice_h").val((Number(feeWidthoutTaxUnitPrice_h)*1.17).toFixed(2));
	}else{
		if(checkNum("feeWidthoutTaxPrice_h")){
			jQuery("#feeWidthoutTaxUnitPrice_h").val("");
			jQuery("#feeWidthoutTaxPrice_h").val("");
		}else
			jQuery("#feeWidthoutTaxUnitPrice_h").val(floatDiv(feeWidthoutTaxPrice_h,1.17).toFixed(2));
	}
	jQuery("#feeWidthTaxPrice_h").val(
			floatMul(jQuery("#feeWidthoutTaxPrice_h").val(),num).toFixed(2));
	if(selfMadeTax)
		return;
	countAll_G();
}
function countAllWeight_G(){
	checkNum("allWeight_h");
	var selfMadeTax = jQuery("#selfMadeTax")[0].checked;
	countGoldPrice_G(1);
	changeLpFee_G(1);
	if(!selfMadeTax)
		countAll_G();
	loadCoefficient();
}
function changeAll_G(type){
	var allWidthoutTaxPrice_h = jQuery("#allWidthoutTaxPrice_h").val();
	var allWidthTaxPrice_h = jQuery("#allWidthTaxPrice_h").val();
	
	if("1"==type){
		if(checkNum("allWidthoutTaxPrice_h")){
			jQuery("#allWidthoutTaxPrice_h").val("");
			jQuery("#allWidthTaxPrice_h").val("");
		}else
			jQuery("#allWidthTaxPrice_h").val((Number(allWidthoutTaxPrice_h)*1.17).toFixed(2));
	}else{
		if(checkNum("allWidthTaxPrice_h")){
			jQuery("#allWidthTaxPrice_h").val("");
			jQuery("#allWidthoutTaxPrice_h").val("");
		}else
			jQuery("#allWidthoutTaxPrice_h").val(floatDiv(allWidthTaxPrice_h,1.17).toFixed(2));
	}
	loadCoefficient();
}
function changeSelfMadeTax(type){
	var allWidthoutTaxPrice_h = jQuery("#allWidthoutTaxPrice_h").val();
	var allWidthTaxPrice_h = jQuery("#allWidthTaxPrice_h").val();
	
	var selfMadeTax = jQuery("#selfMadeTax")[0].checked;
	jQuery("#allWidthoutTaxPrice_h").attr("readOnly", !selfMadeTax);
	jQuery("#allWidthTaxPrice_h").attr("readOnly", !selfMadeTax);
	
	if(selfMadeTax){
		jQuery("#allWidthoutTaxPrice_value").val(allWidthoutTaxPrice_h);
		jQuery("#allWidthTaxPrice_value").val(allWidthTaxPrice_h);
	}else{
		jQuery("#allWidthoutTaxPrice_h").val(jQuery("#allWidthoutTaxPrice_value").val());
		jQuery("#allWidthTaxPrice_h").val(jQuery("#allWidthTaxPrice_value").val());
		if(type=="1")
			countAllWeight_G();
	}
}
function countAll_G(){
	//总含税成本 
	jQuery("#allWidthTaxPrice_h").val(
			floatAdd(jQuery("#feeWidthTaxPrice_h").val(),
			jQuery("#sbraWidthTaxPrice_h").val()).toFixed(2));
	//总不含税成本
	jQuery("#allWidthoutTaxPrice_h").val(
			floatDiv(jQuery("#allWidthTaxPrice_h").val(),1.17).toFixed(2));

	loadCoefficient();
}

/**
 * 统计头表数据
 */
function countHeadData(){
	var inivFlag = jQuery("#inivFlag").val();
	var  feeWidthoutTaxPrice_h = jQuery("#feeWidthoutTaxPrice_h").val()==""?"0":jQuery("#feeWidthoutTaxPrice_h").val();
	var  feeWidthTaxPrice_h = jQuery("#feeWidthTaxPrice_h").val()==""?"0":jQuery("#feeWidthTaxPrice_h").val();
	
	var caclType = jQuery("#caclType").val();//核价类型
	if(caclType=="3"||caclType=="2"){
		if(inivFlag==""||inivFlag=="0")
			return;
		if(caclType=="2")
			loadCoefficient();
		//总不含税成本
		jQuery("#allWidthoutTaxPrice_h").val((Number(jQuery("#mainWidthoutTaxPrice_h").val())+
											Number(jQuery("#secondWidthoutTaxPrice_h").val())+
											Number(jQuery("#sbraWidthoutTaxPrice_h").val())+
											Number(jQuery("#acsWidthoutTaxPrice_h").val())+
											Number(feeWidthoutTaxPrice_h)).toFixed(2));
		//总含税成本 
		jQuery("#allWidthTaxPrice_h").val((Number(jQuery("#mainWidthTaxPrice_h").val())+
										Number(jQuery("#secondWidthTaxPrice_h").val())+
										Number(jQuery("#sbraWidthTaxPrice_h").val())+
										Number(jQuery("#acsWidthTaxPrice_h").val())+
										Number(feeWidthTaxPrice_h)).toFixed(2));
		//总税率
		if(jQuery("#allWidthoutTaxPrice_h").val()==""||jQuery("#allWidthoutTaxPrice_h").val()=="0.00"){
			jQuery("#taxCoefficient_h").val("0");
		}else{
			jQuery("#taxCoefficient_h").val((jQuery("#allWidthTaxPrice_h").val()/
					jQuery("#allWidthoutTaxPrice_h").val()).toFixed(2));
		}
		//总粒数 
		jQuery("#allNumber_h").val((Number(jQuery("#mainNumber_h").val())+
										Number(jQuery("#secondNumber_h").val())).toFixed(2));	
		//总市场价
		jQuery("#allMarketPrice_h").val((Number(jQuery("#mainMarketPrice_h").val())+
										Number(jQuery("#secondMarketPrice_h").val())+
										Number(jQuery("#sbraMarketPrice_h").val())+
										Number(jQuery("#acsMarketPrice_h").val())+
										Number(jQuery("#feeMarketPrice_h").val())).toFixed(2));
		//总含系数后价
		jQuery("#allmoneyWithCoefficient_h").val((Number(jQuery("#mainPriceWithCoefficient_h").val())+
										Number(jQuery("#secondPriceWithCoefficient_h").val())+
										Number(jQuery("#sbraPriceWithCoefficient_h").val())+
										Number(jQuery("#acsPriceWithCoefficient_h").val())+
										Number(jQuery("#feeWithCoefficient_h").val())).toFixed(2));
		//基础价
		checkBasicPrice(floatMul(jQuery("#allmoneyWithCoefficient_h").val(),jQuery("#ncoefficient_h").val()));
	
		if(caclType=="3"){//其它珠宝单有(辅料)
			jQuery("#allWidthoutTaxPrice_h").val(
					floatAdd(jQuery("#allWidthoutTaxPrice_h").val(),Number(jQuery("#accWithoutTax_h").val())).toFixed(2)
			);
			jQuery("#allWidthTaxPrice_h").val(
					floatAdd(jQuery("#allWidthTaxPrice_h").val(),Number(jQuery("#accWithTax_h").val())).toFixed(2)
			);
			loadCoefficient();
			jQuery("#allMarketPrice_h").val(
					floatAdd(jQuery("#allMarketPrice_h").val(),Number(jQuery("#accMarketPrice_h").val())).toFixed(2)
			);
			jQuery("#allmoneyWithCoefficient_h").val(
					floatAdd(jQuery("#allmoneyWithCoefficient_h").val(),Number(jQuery("#accPriceWithCoefficient_h").val())).toFixed(2)
			);
			//基础价
			checkBasicPrice(floatMul(jQuery("#allmoneyWithCoefficient_h").val(),jQuery("#ncoefficient_h").val()));
		}
		
	}
	if(caclType=="1"){
		//总不含税成本
		jQuery("#allWidthoutTaxPrice_h").val((
				Number(jQuery("#sbraWidthoutTaxPrice_h").val())+
				Number(feeWidthoutTaxPrice_h)).toFixed(2));
		//总含税成本 
		jQuery("#allWidthTaxPrice_h").val((
				Number(jQuery("#sbraWidthTaxPrice_h").val())+
				Number(feeWidthTaxPrice_h)).toFixed(2));
		//总含系数后价
		jQuery("#allmoneyWithCoefficient_h").val((
				Number(jQuery("#priceWithCoefficient_h").val())+
				Number(jQuery("#feeWithCoefficient_h").val())).toFixed(2));
		//基础价
		checkBasicPrice(floatMul(jQuery("#allmoneyWithCoefficient_h").val(),jQuery("#ncoefficient_h").val()));
	}
}
function checkBasicPrice(v){
	var bp = Number(v).toFixed(0)%10;
	var v = Number(v).toFixed(0);
	if(v=="NaN"||v=="0")
		v="";
	else if(bp=="4")
		v = floatAdd(Number(v),1);
	jQuery("#basicPrice_h").val(v);
}
function checkNum(str){
	var value = jQuery("#"+str+"").val();
	if(value=="")
		return false;
	else if(value=="0"){
		if(str!="feeWidthoutTaxUnitPrice_h"){
			jQuery("#"+str+"").val("");
			return false;			
		}return true;
	}else if(Number(value)<0||Number(value)==NaN){
		jQuery("#"+str+"").val("");
		return false;
	}
	if(!isDecimal(Number(value))){
		alert("请输入正确的数据");
		jQuery("#"+str+"").val("");
		return true;
	}return false;
}
function checkVal(str,i){
	var value = $n(""+str+"")[i].value;
	if(value=="")
		return false;
	else if(value=="0"){
		$n(""+str+"")[i].value="";
		return false;
	}else if(Number(value)<0){
		$n(""+str+"")[i].value="";
		return false;
	}
	if(!isDecimal(Number(value))){
		alert("请输入正确的数据");
		$n(""+str+"")[i].value="";
		return true;
	}return false;
}
function changeUpdatePrice(){
	var basicPrice_h = jQuery("#basicPrice_h").val();
	var updateBasicPrice = jQuery("#updateBasicPrice")[0].checked;
	jQuery("#basicPrice_h").attr("readOnly", !updateBasicPrice);
	if(updateBasicPrice){
		jQuery("#basicPrice_value").val(basicPrice_h);
	}else{
		jQuery("#basicPrice_h").val(jQuery("#basicPrice_value").val());
	}
}
/**
 * 检查表单有效性
 */
function checkForm(v){
	if(!Validator.Validate($("frm"),3))
		return false;
	return true;
}
function returnNull(v){
	if(v=="")
		return null;
	else
		return v;
}
function returnAllWeight(){
	var mainWeight_h = jQuery("#mainWeight_h").val();
	var secondWeight_h = jQuery("#secondWeight_h").val();
	var sbraWeight_h = jQuery("#sbraWeight_h").val();
	var allWeight_h = jQuery("#allWeight_h").val();
	
	var value = floatAdd((floatAdd(mainWeight_h,secondWeight_h)/5),sbraWeight_h);
	
	if(allWeight_h<value){
		alert("饰品总重必须大于等于（主石重量+配石重量）除以5加金重量！");
		return true;
	}else
		return false;
	
}

var FC_ITEM_CLASS_ID = "173";//原料翡翠
var FC_ALL_QYT = "0";//原料翡翠重量
var FC_RETURN = false;
function checkStoneAllQty(){
	var caclType = jQuery("#caclType").val();//核价类型
	var venderId = jQuery("#vender_h").val();
	var headId = jQuery("#id").val();//头表ID
	if(caclType == "3"){
		FC_RETURN = false;
		var fc_price = 0;
		BdCommonDwr.getParameterValue("cacl_fc_price",function(v){
			fc_price = v;
		});
		for(var i=0;i<$("tb1").rows.length;i++){
			PriceHeadDwr.getMaterNoActiveByOrnaCode($n("luodanCode")[i].value,venderId,headId,function(mater){
				if(mater!=null&&Number(mater.allQty)>=0.3&&Number(mater.stoneNowNum)==1){//大于30分的重量,12.08.14修改 （饰品现有量为1）
					var stoneWeight = $n("stoneWeight")[i].value;
					var pnum = floatMul(fc_price,mater.allQty);
					if(Number(floatAdd(mater.allQty,pnum))<Number(stoneWeight)||
					   Number(floatSub(mater.allQty,pnum))>Number(stoneWeight)){
					   alert("第"+(i+1)+"石头行,原料翡翠饰品重量必须在["+Number(floatAdd(mater.allQty,pnum))+"]-["+Number(floatSub(mater.allQty,pnum))+"]范围");
					   FC_RETURN = true;
					}
				}
			});
		}
	}
}

function getPriceHead(){
	var caclType = jQuery("#caclType").val();//核价类型
	var head = null;
	var actTradeinFee="";
	var tradeinFee="";
	var numberStore = 0;
	var basicPriceOld=jQuery("#basicPriceOld").val();
	var inivFlag = jQuery("#inivFlag").val();
	
	if(caclType=="2"&&"157"==jQuery("#itemClassId").val()){//行有一个是三分以下的钻石核价单 算以旧换新费用
		var istfxm = false;
		if(Number(jQuery("#avgWeight").val())<0.03&&inivFlag!="1"&&inivFlag!="2"){
			istfxm = true;
			numberStore = jQuery("#allNumber_h").val();
		}else{
			var stoneWeightLine = $n("stoneWeight");
			for ( var int = 0; int < stoneWeightLine.length; int++) {
				var avgline = floatDiv($n("stoneWeight")[int].value,$n("stoneNums")[int].value);
				if(Number(avgline)<0.03){
					istfxm = true;
					numberStore = floatAdd(numberStore,$n("stoneNums")[int].value);
				}
			}
		}
		if(istfxm){
			startSync();
			var tables_price = 0;
			var tradein_fee_maximum = 0;
			BdCommonDwr.getParameterValue("tables_price",function(v){
				tables_price = v;
			});
			BdCommonDwr.getParameterValue("tradein_fee_maximum",function(v){
				tradein_fee_maximum = v;
			});
			actTradeinFee=floatMul(tables_price,numberStore);
			var jcb = floatMul(floatMul(jQuery("#allmoneyWithCoefficient_h").val()==""?jQuery("#allWidthTaxPrice_h").val():jQuery("#allmoneyWithCoefficient_h").val()
					,jQuery("#ncoefficient_h").val()),tradein_fee_maximum);
			if(Number(jcb)>Number(actTradeinFee))
				tradeinFee=actTradeinFee;
			else
					tradeinFee=jcb;
			if(!jQuery("#updateBasicPrice")[0].checked){
				checkBasicPrice(floatAdd((floatMul(jQuery("#allmoneyWithCoefficient_h").val()==""?jQuery("#allWidthTaxPrice_h").val():jQuery("#allmoneyWithCoefficient_h").val()
						,jQuery("#ncoefficient_h").val())),tradeinFee));				
			}
			endSync();			
		}
	}
	//算原基础价2021-04-11
	startSync();
	var isprice=-1;
	DictDwr.getDictsForSlt("basicpriceone", function(data){
		if(jQuery("#itemClassId").val()==data[0].value||
				jQuery("#itemClassId").val()==data[1].value)
			isprice=1;
	});
	DictDwr.getDictsForSlt("basicpricetwo", function(data){
		if(jQuery("#itemClassId").val()==data[0].value||
			jQuery("#itemClassId").val()==data[1].value||
			jQuery("#itemClassId").val()==data[2].value)
			isprice=2;
	});
	if(isprice==1){
		var one ={
			itemclassid:jQuery("#itemClassId").val(),
			ornaclassid:jQuery("#ornaClassId").val(),
			startweight:jQuery("#mainWeight_h").val()	
		};
		BasicPriceOneDwr.getBasicPriceOne(one,function(onedata){
			if(onedata!=null){
				BasicPriceTwoDwr.getTwoPrice(jQuery("#basicPrice_h").val(),function(twodata){
					if(twodata!=null){
						basicPriceOld=jQuery("#basicPrice_h").val();
						jQuery("#basicPriceOld").val(jQuery("#basicPrice_h").val());
						jQuery("#basicPrice_h").val(twodata.basicprice);
					}
				});
			}
		});
	}else if(isprice==2){
		var jade ={
				itemclassid:jQuery("#itemClassId").val(),
				startprice:jQuery("#basicPrice_h").val()	
		};
		BasicPriceJadeDwr.getBasicPriceJade(jade,function(jadedata){
			if(jadedata!=null){// 100 188 200
				var jv1 = Math.abs(floatSub(jQuery("#basicPrice_h").val(),jadedata.bigprice));
				var jv2 = Math.abs(floatSub(jQuery("#basicPrice_h").val(),jadedata.smallprice));
				
				var vp = Math.abs(jQuery("#basicPrice_h").val())+"";
				var twoprice=0;
				jQuery("#basicPriceOld").val(jQuery("#basicPrice_h").val());
				basicPriceOld=jQuery("#basicPrice_h").val();
				if(jv1>jv2){
					twoprice=jadedata.smallprice;
					jQuery("#basicPrice_h").val(
							floatSub(twoprice,vp.substr(floatSub(vp.length,jadedata.decimalnum),vp.length)));
				}else {
					twoprice=jadedata.bigprice;
					jQuery("#basicPrice_h").val(
							floatAdd(twoprice,vp.substr(floatSub(vp.length,jadedata.decimalnum),vp.length)));
				}
			}
		});
	}
	endSync();	
	if(caclType=="2"||caclType=="3")
		head = {
			id:returnNull(jQuery("#id").val()),//ID 主键
			no:returnNull(jQuery("#no").val()),//NO 核价单号
			orgId:returnNull(jQuery("#orgId").val()),//ORG_ID 创建组织
			handoverNo:returnNull(jQuery("#handoverNo_h").val()),//HANDOVER 交接单号
			handoverName:jQuery("#handoverName").val(),
			vender:returnNull(jQuery("#vender_h").val()),//VENDER 建议供应商
			itemClassId:returnNull(jQuery("#itemClassId").val()),//ITEM_CLASS_ID 大类
			ornaClassId:returnNull(jQuery("#ornaClassId").val()),//ORNA_CLASS_ID 小类
			qualityId:returnNull(jQuery("#qualityId").val()),//QUALITY_ID 托架材质
			colorId:returnNull(jQuery("#colorId_h").val()),//COLOR_ID 颜色
			cleanId:returnNull(jQuery("#cleanId_h").val()),//CLEAN_ID 净度
			colorGreadId:returnNull(jQuery("#colorGreadId_h").val()),//COLORGRADE_ID 色级
			dmShapeId:returnNull(jQuery("#dmShapeId_h").val()),//DM_SHAPE_ID 形状
			cutId:returnNull(jQuery("#cutId_h").val()), //切工id
			//isOldornaIniv:jQuery("#isOldornaIniv_h")[0].checked==false?'0':'1',//IS_OLDORNA_INIV 是否旧品入库
			feeTax:returnNull(jQuery("#feeTax_h").val()),//FEE_TAX 工费税率
			feeWidthoutTaxUnitPrice:returnNull(jQuery("#feeWidthoutTaxUnitPrice_h").val()),//FEE_WIDTHOUT_TAX_UNIT_PRICE 工费不含税单价
			feeWidthoutTaxPrice:returnNull(jQuery("#feeWidthoutTaxPrice_h").val()),//FEE_WIDTHOUT_TAX_PRICE 工费不含税价
			feeWidthTaxPrice:returnNull(jQuery("#feeWidthTaxPrice_h").val()),//FEE_WIDTH_TAX_PRICE 工费含税价
			feeMarketPrice:returnNull(jQuery("#feeMarketPrice_h").val()),//FEE_MARKET_PRICE 工费市场价
			selfMadeTax:returnNull(jQuery("#selfMadeTax_h").val()),//SELF_MADE_TAX 自配费用税率
			selfMadeWidthoutTaxPrice:returnNull(jQuery("#selfMadeTax_h").val()), //自配费用不含税价
			selfMadeWidthTaxprice:returnNull(jQuery("#selfMadeWidthTaxprice_h").val()), //自配费用含税价
			selfMadeMarketPrice:returnNull(jQuery("#selfMadeWidthTaxprice_h").val()), //自配费用市场价
			allNumber:returnNull(jQuery("#allNumber_h").val()), //总粒数
			avgWeight:returnNull(jQuery("#avgWeight").val()), //平均分数
			mainNumber:returnNull(jQuery("#mainNumber_h").val()), //主石粒数
			mainWeight:returnNull(jQuery("#mainWeight_h").val()), //主石重量
			mainWidthoutTaxPrice:returnNull(jQuery("#mainWidthoutTaxPrice_h").val()), //主石不含税价
			mainWidthTaxPrice:returnNull(jQuery("#mainWidthTaxPrice_h").val()), //主石含税价
			mainMarketPrice:returnNull(jQuery("#mainMarketPrice_h").val()), //主石市场价
			secondNumber:returnNull(jQuery("#secondNumber_h").val()), //配石粒数
			secondWeight:returnNull(jQuery("#secondWeight_h").val()), //配石重量
			secondWidthoutTaxPrice:returnNull(jQuery("#secondWidthoutTaxPrice_h").val()), //配石不含税价
			secondWidthTaxPrice:returnNull(jQuery("#secondWidthTaxPrice_h").val()), //配石含税价
			secondMarketPrice:returnNull(jQuery("#secondMarketPrice_h").val()), //配石市场价
			sbraCoefficient:returnNull(jQuery("#sbraCoefficient_h").val()), //托架系数
			sbraWeight:returnNull(jQuery("#sbraWeight_h").val()), //托架重量
			sbraWidthoutTaxPrice:returnNull(jQuery("#sbraWidthoutTaxPrice_h").val()), //托架不含税价
			sbraWidthTaxPrice:returnNull(jQuery("#sbraWidthTaxPrice_h").val()), //托架含税价
			sbraMarketPrice:returnNull(jQuery("#sbraMarketPrice_h").val()), //托架市场价
			acsWidthoutTaxPrice:returnNull(jQuery("#acsWidthoutTaxPrice_h").val()), //配件不含税价
			acsWidthTaxPrice:returnNull(jQuery("#acsWidthTaxPrice_h").val()), //配件含税价
			acsMarketPrice:returnNull(jQuery("#acsMarketPrice_h").val()), //配件市场价
			ncoefficient:returnNull(jQuery("#ncoefficient_h").val()), //倍率系数
			allWidthoutTaxPrice:returnNull(jQuery("#allWidthoutTaxPrice_h").val()), //总不含税成本
			allWidthTaxPrice:returnNull(jQuery("#allWidthTaxPrice_h").val()), //总含税成本
			allMarketPrice:returnNull(jQuery("#allMarketPrice_h").val()), //总市场价
			basicPrice:returnNull(jQuery("#basicPrice_h").val()), //基础价
			taxCoefficient:returnNull(jQuery("#taxCoefficient_h").val()), //税率系数
			allWeight:returnNull(jQuery("#allWeight_h").val()), //饰品总重
			secondDsc:returnNull(jQuery("#secondDsc_h").val()), //配石内容
			state:returnNull(jQuery("#state").val()), //单据状态
			updateDate:returnNull(jQuery("#updateDate").val()), //时间戳
			createUserId:returnNull(jQuery("#createUserId").val()), //制单人
			createDate:returnNull(jQuery("#createDate").val()), //制单日期
			caclType:returnNull(jQuery("#caclType").val()), //核价类型
			factoryFeeType:returnNull(jQuery("#factoryFeeType_h").val()), //工厂公费方式
			otherCoefficient:returnNull(jQuery("#otherCoefficient_h").val()), //其他系数
			priceWithCoefficient:returnNull(jQuery("#priceWithCoefficient_h").val()), //金含系数后价
			feeWithCoefficient:returnNull(jQuery("#feeWithCoefficient_h").val()), //工费含系数后价
			allmoneyWithCoefficient:returnNull(jQuery("#allmoneyWithCoefficient_h").val()), //总含系数后价
			mainPriceWithCoefficient:returnNull(jQuery("#mainPriceWithCoefficient_h").val()), //主石含系数后价
			secondPriceWithCoefficient:returnNull(jQuery("#secondPriceWithCoefficient_h").val()), //配石含系数后价
			acsPriceWithCoefficient:returnNull(jQuery("#acsPriceWithCoefficient_h").val()), //配件含系数后价
			sbraPriceWithCoefficient:returnNull(jQuery("#sbraPriceWithCoefficient_h").val()), //托架含系数后价
			lpFee:returnNull(jQuery("#lpFee_h").val()), //拉/喷沙工费
			mainStoneXqFee:returnNull(jQuery("#mainStoneXqFee_h").val()), //主石镶嵌工费总额
			secondStoneXqDee:returnNull(jQuery("#mainStoneXqFee_h").val()), //配石镶嵌工费总额
			wlCode:returnNull(jQuery("#wlCode").val()), //万隆编码
			outGoodsBillNo:returnNull(jQuery("#outGoodsBillNo_h").val()), //出货单编号
			accWithoutTax:returnNull(jQuery("#accWithoutTax_h").val()), //辅料不含税价
			accWithTax:returnNull(jQuery("#accWithTax_h").val()), //辅料含税价
			accMarketPrice:returnNull(jQuery("#accMarketPrice_h").val()), //辅料市场价
			accPriceWithCoefficient:returnNull(jQuery("#accPriceWithCoefficient_h").val()), //辅料含系数后价
			sbraColorId:returnNull(jQuery("#sbraColorId_h").val()), //托架颜色
			isDoubleLabel:jQuery("#isDoubleLabel_h")[0].checked==false?'0':'1', //是否双标签
			labelId:returnNull(jQuery("#labelId_h option:selected").text()=="--请选择--"?"":jQuery("#labelId_h  option:selected").text()), //标签名称id
			ornaBarCode:returnNull(jQuery("#ornaBarCode").val()), //条码
			//avgWeight:returnNull(jQuery("#_h").val()), //主石每粒的平均重量
			//verificationFlag:returnNull(jQuery("#_h").val()), //是否核销
			newGoldPrice:returnNull(jQuery("#newGoldPrice_h").val()), //金价
			stoneCoeff:returnNull(jQuery("#stoneCoeff_h").val()), //石头系数
			//actTradeinFee:returnNull(jQuery("#actTradeinFee_h").val()), //实际以旧换新费用
			//tradeinFee:returnNull(jQuery("#tradeinFee_h").val()), //以旧换新费用
			analysisArangeId:returnNull(jQuery("#analysisArangeId_h").val()), //分析范围
			hlevel:returnNull(jQuery("#hlevel").val()),
			isConsignment:returnNull(jQuery("#isConsignment").val()),
			inivFlag:returnNull(jQuery("#inivFlag").val()),
			basicPriceCoefficient:returnNull(jQuery("#basicPriceCoefficient").val()),
            identId          :jQuery("#identId").val(), //(100)	y			鉴定证书号                                       
			hrdCert          :jQuery("#hrdCert").val(), //(100)	y			hrd国际证书                                      
			giaCert          :jQuery("#giaCert").val(), //(100)	y			gia国际证书                                      
			igiCert          :jQuery("#igiCert").val(), //(100)	y			igi国际证书                                      
			agsCert          :jQuery("#agsCert").val(), //(100)	y			ags国际证书                                      
			colorGreadIdxq          :jQuery("#colorGreadIdxq").val(), //(100)	y			igi国际证书                                      
			cleanIdxq          :jQuery("#cleanIdxq").val(), //(100)	y			ags国际证书
			updateBasicPrice:jQuery("#updateBasicPrice")[0].checked==false?'0':'1',
			actTradeinFee:actTradeinFee,
			tradeinFee:tradeinFee,
			basicPriceOld:basicPriceOld
		};
	else if(caclType=="1")
		head = {
			id:returnNull(jQuery("#id").val()),//ID 主键
			no:returnNull(jQuery("#no").val()),//NO 核价单号
			orgId:returnNull(jQuery("#orgId").val()),//ORG_ID 创建组织
			handoverNo:returnNull(jQuery("#handoverNo_h").val()),//HANDOVER 交接单号
			handoverName:jQuery("#handoverName").val(),
			vender:returnNull(jQuery("#vender_h").val()),//VENDER 建议供应商
			itemClassId:returnNull(jQuery("#itemClassId").val()),//ITEM_CLASS_ID 大类
			ornaClassId:returnNull(jQuery("#ornaClassId").val()),//ORNA_CLASS_ID 小类
			qualityId:returnNull(jQuery("#qualityId").val()),//QUALITY_ID 托架材质
			colorId:returnNull(jQuery("#colorId_h").val()),//COLOR_ID 颜色
			feeTax:returnNull(jQuery("#feeTax_h").val()),//FEE_TAX 工费税率
			feeWidthoutTaxUnitPrice:returnNull(jQuery("#feeWidthoutTaxUnitPrice_h").val()),//FEE_WIDTHOUT_TAX_UNIT_PRICE 工费不含税单价
			feeWidthoutTaxPrice:returnNull(jQuery("#feeWidthoutTaxPrice_h").val()),//FEE_WIDTHOUT_TAX_PRICE 工费不含税价
			feeWidthTaxPrice:returnNull(jQuery("#feeWidthTaxPrice_h").val()),//FEE_WIDTH_TAX_PRICE 工费含税价
			sbraWeight:returnNull(jQuery("#sbraWeight_h").val()), //托架重量
			sbraWidthoutTaxPrice:returnNull(jQuery("#sbraWidthoutTaxPrice_h").val()), //托架不含税价
			sbraWidthTaxPrice:returnNull(jQuery("#sbraWidthTaxPrice_h").val()), //托架含税价
			sbraMarketPrice:returnNull(jQuery("#sbraMarketPrice_h").val()), //托架市场价
			ncoefficient:returnNull(jQuery("#ncoefficient_h").val()), //总系数
			allWidthoutTaxPrice:returnNull(jQuery("#allWidthoutTaxPrice_h").val()), //总不含税成本
			allWidthTaxPrice:returnNull(jQuery("#allWidthTaxPrice_h").val()), //总含税成本
			basicPrice:returnNull(jQuery("#basicPrice_h").val()), //基础价
			allWeight:returnNull(jQuery("#allWeight_h").val()), //饰品总重
			secondDsc:returnNull(jQuery("#secondDsc_h").val()), //配石内容
			state:returnNull(jQuery("#state").val()), //单据状态
			updateDate:returnNull(jQuery("#updateDate").val()), //时间戳
			createUserId:returnNull(jQuery("#createUserId").val()), //制单人
			createDate:returnNull(jQuery("#createDate").val()), //制单日期
			caclType:returnNull(jQuery("#caclType").val()), //核价类型
			factoryFeeType:returnNull(jQuery("#factoryFeeType_h").val()), //工厂公费方式
			wlCode:returnNull(jQuery("#wlCode").val()), //万隆编码
			sbraColorId:returnNull(jQuery("#sbraColorId_h").val()), //托架颜色
			isDoubleLabel:jQuery("#isDoubleLabel_h")[0].checked==false?'0':'1', //是否双标签
			labelId:returnNull(jQuery("#labelId_h option:selected").text()=="--请选择--"?"":jQuery("#labelId_h  option:selected").text()), //标签名称id
			ornaBarCode:returnNull(jQuery("#ornaBarCode").val()), //条码
			analysisArangeId:returnNull(jQuery("#analysisArangeId_h").val()), //分析范围
			hlevel:returnNull(jQuery("#hlevel").val()),
			inivFlag:returnNull(jQuery("#inivFlag").val()),
			isConsignment:returnNull(jQuery("#isConsignment").val()),
			updateBasicPrice:jQuery("#updateBasicPrice")[0].checked==false?'0':'1',
			selfMadeTax:jQuery("#selfMadeTax")[0].checked==false?'0':'1',
			basicPriceOld:basicPriceOld
		};
	else if(caclType=="4")
		head = {
			id:returnNull(jQuery("#id").val()),//ID 主键
			no:returnNull(jQuery("#no").val()),//NO 核价单号
			orgId:returnNull(jQuery("#orgId").val()),//ORG_ID 创建组织
			handoverNo:returnNull(jQuery("#handoverNo_h").val()),//HANDOVER 交接单号
			handoverName:jQuery("#handoverName").val(),
			vender:returnNull(jQuery("#vender_h").val()),//VENDER 建议供应商
			itemClassId:returnNull(jQuery("#itemClassId").val()),//ITEM_CLASS_ID 大类
			ornaClassId:returnNull(jQuery("#ornaClassId").val()),//ORNA_CLASS_ID 小类
			qualityId:returnNull(jQuery("#qualityId").val()),//QUALITY_ID 托架材质
			colorId:returnNull(jQuery("#colorId_h").val()),//COLOR_ID 颜色
			taxCoefficient:returnNull(jQuery("#taxCoefficient_h").val()),//FEE_TAX 税率
			ncoefficient:returnNull(jQuery("#ncoefficient_h").val()), //总系数
			allWidthoutTaxPrice:returnNull(jQuery("#allWidthoutTaxPrice_h").val()), //总不含税成本
			allWidthTaxPrice:returnNull(jQuery("#allWidthTaxPrice_h").val()), //总含税成本
			sbraWeight:returnNull(jQuery("#sbraWeight_h").val()), //托架重量
			basicPrice:returnNull(jQuery("#basicPrice_h").val()), //基础价
			allWeight:returnNull(jQuery("#allWeight_h").val()), //饰品总重
			secondDsc:returnNull(jQuery("#secondDsc_h").val()), //配石内容
			state:returnNull(jQuery("#state").val()), //单据状态
			updateDate:returnNull(jQuery("#updateDate").val()), //时间戳
			createUserId:returnNull(jQuery("#createUserId").val()), //制单人
			createDate:returnNull(jQuery("#createDate").val()), //制单日期
			caclType:returnNull(jQuery("#caclType").val()), //核价类型
			allNumber:returnNull(jQuery("#allNumber_h").val()), //总粒数
			mainNumber:returnNull(jQuery("#mainNumber_h").val()), //主石粒数
			mainWeight:returnNull(jQuery("#mainWeight_h").val()), //主石重量
			secondNumber:returnNull(jQuery("#secondNumber_h").val()), //配石粒数
			secondWeight:returnNull(jQuery("#secondWeight_h").val()), //配石重量
			wlCode:returnNull(jQuery("#wlCode").val()), //万隆编码
			outGoodsBillNo:returnNull(jQuery("#outGoodsBillNo_h").val()), //出货单编号
			sbraColorId:returnNull(jQuery("#sbraColorId_h").val()), //托架颜色
			isDoubleLabel:jQuery("#isDoubleLabel_h")[0].checked==false?'0':'1', //是否双标签
			labelId:returnNull(jQuery("#labelId_h option:selected").text()=="--请选择--"?"":jQuery("#labelId_h  option:selected").text()), //标签名称id
			ornaBarCode:returnNull(jQuery("#ornaBarCode").val()), //条码
			dmShapeId:returnNull(jQuery("#dmShapeId").val()), //形状
			colorId:returnNull(jQuery("#colorId").val()), //颜色
			analysisArangeId:returnNull(jQuery("#analysisArangeId_h").val()), //分析范围
			hlevel:returnNull(jQuery("#hlevel").val()),
			inivFlag:returnNull(jQuery("#inivFlag").val()),
			isConsignment:returnNull(jQuery("#isConsignment").val()),
			basicPriceCoefficient:returnNull(jQuery("#basicPriceCoefficient").val()),
			basicPriceOld:basicPriceOld
		};
	return head;
}
function getStoneLine(){
	var caclType = jQuery("#caclType").val();
	var stoneLine=[];
	if(caclType=="3"||caclType=="2"){
		
		for(var i=0;i<$("tb1").rows.length;i++){
			var itemclassIdv = "";
			if(caclType=="3")
				itemclassIdv= returnNull($n("stoneItemClassId")[i].value); 
				
			stoneLine.push({
				 id :returnNull($n("stoneId")[i].value),//ID 主键
				 headId :jQuery("#id").val(),//HEAD_ID 主表id
				 luodanCode :returnNull($n("luodanCode")[i].value),//LUODAN_CODE 裸石编码
				 factoryNuleCode :returnNull($n("factoryNuleCode")[i].value),// 工厂编码
				 stoneNums :returnNull($n("stoneNums")[i].value),//STONE_NUMS 粒数
				 stoneWeight :returnNull($n("stoneWeight")[i].value),//STONE_WEIGHT 石头重量
				 withotTaxPrice :returnNull($n("withotTaxPrice")[i].value),//WITHOUT_TAX_PRICE 不含税单价
				 withoutTaxAllMoney :returnNull($n("withoutTaxAllMoney")[i].value),//WITHOUT_TAX_ALLMONEY 不含税总金额
				 stoneTax :returnNull($n("stoneTax")[i].value),//STONE_TAX税率
				 withTaxAllMoney :returnNull($n("withTaxAllMoney")[i].value),//WITH_TAX_ALLMONEY 含税总金额
				 marketPrice :returnNull($n("marketPrice")[i].value),//MARKET_PRICE 市场价单价
				 marketAllMoney :returnNull($n("marketAllMoney")[i].value),//MARKET_ALLMONEY 市场价总金额
				 stoneType :returnNull($n("stoneType")[i].value),//STONE_TYPE 石头类型
				 stoneCoefficient :returnNull($n("stoneCoefficient")[i].value),//STONE_COEFFICIENT 石头系数
				 priceWithCoefficient :returnNull($n("stone_priceWithCoefficient")[i].value),//PRICE_WITH_COEFFICIENT 系数后价
				 colorId :returnNull($n("colorId")[i].value),//COLOR_ID 颜色
				 cleanId :returnNull($n("cleanId")[i].value),//CLEAN_ID 净度
				 colorGreadId :returnNull($n("colorGreadId")[i].value),//GRADE_ID 色级
				 dmShapeId :returnNull($n("dmShapeId")[i].value),//DM_SHAPE_ID 形状
				 cutId :returnNull($n("cutId")[i].value),//CUT_ID 切工ID
				 feeType :returnNull($n("feeType")[i].value),//FEE_TYPE 计费方式
				 privateType :returnNull($n("stonePrivateType")[i].value),//PRIVATE_TYPE 是否自有料(料属性)0否 1是  2空
				 stoneItemClassId :itemclassIdv,
				 oldStoneNum :returnNull($n("oldStoneNum")[i].value),
				 oldOrnaCode :returnNull($n("oldOrnaCode")[i].value)
			});
		}
	}
	return stoneLine;
}
function getSbraLine(){
	var caclType = jQuery("#caclType").val();
	var sbraLine = [];
	if(caclType=="3"||caclType=="2")
		for(var i=0;i<$("tb2").rows.length;i++){
			sbraLine.push({
				id:returnNull($n("sbraLineId")[i].value),//ID 主键
				headId:jQuery("#id").val(),//HEAD_ID	NUMBER(8) 主表id
				sbraWeight:returnNull($n("sbraWeight")[i].value),//SBRA_WEIGHT	NUMBER(24,6) 托架重量
				sbraWithoutTaxPrice:returnNull($n("sbraWithoutTaxPrice")[i].value),//SBRA_WITHOUT_TAX_PRICE	NUMBER(24,6) 托架不含税单价
				sbraWithoutTaxmoney:returnNull($n("sbraWithoutTaxmoney")[i].value),//SBRA_WITHOUT_TAX_MONEY	NUMBER(24,6) 托架不含税金额
				//sbraTax:returnNull($n("sbraTax")[i].value),//SBRA_TAX	NUMBER(24,6)	Y			税率
				sbraWithTaxMoney:returnNull($n("sbraWithTaxMoney")[i].value),//SBRA_WITH_TAX_MONEY	NUMBER(24,6) 托架含税金额
				sbraMarketPrice:returnNull($n("sbraMarketPrice")[i].value),//SBRA_MARKET_PRICE	NUMBER(24,6) 托架市场价单价
				sbraMarketAllMoney:returnNull($n("sbraMarketAllMoney")[i].value),//SBRA_MARKET_ALLMONEY 托架市场价总金额
				sbraCoefficient:returnNull($n("sbraCoefficient")[i].value),//SBRA_COEFFICIENT 托架系数
				priceWithCoefficient:returnNull($n("sbra_priceWithCoefficient")[i].value),//PRICE_WITH_COEFFICIENT 含系数后价
				qualityId:returnNull($n("sbra_qualityId")[i].value),//QUALITY_ID 托架材质
				privateType:returnNull($n("sbraPrivateType")[i].value),//PRIVATE_TYPE 是否自有料
				goldLostPercent:returnNull($n("goldLostPercent")[i].value),//GOLD_LOST_PERCENT 金耗率
				goldLostWeight:returnNull($n("goldLostWeight")[i].value),//GOLD_LOST_WEIGHT 金耗重
				goldAllweight:returnNull($n("goldAllweight")[i].value)//GOLD_ALL_WEIGHT 含耗重
			});
		}
	return sbraLine;
}
function getAcsLine(){
	var caclType = jQuery("#caclType").val();
	var acsLine = [];
	if(caclType=="3"||caclType=="2")
		for(var i=0;i<$("tb3").rows.length;i++){
			acsLine.push({
				id:returnNull($n("acsLineId")[i].value),//ID 主键
				headId:jQuery("#id").val(),//HEAD_ID 主表id
				acsType:returnNull($n("acsType")[i].value.split("_")[0]),//ACS_TYPE 配件类型
				acsWeight:returnNull($n("acsWeight")[i].value),//ACS_WEIGHT 重量
				acsQuantity:returnNull($n("acsQuantity")[i].value),//ACS_QUANTITY 数量
				acsWithoutTaxPrice:returnNull($n("acsWithoutTaxPrice")[i].value),//ACS_WITHOUT_TAX_PRICE 配件不含税单价
				acsWithoutTaxMoney:returnNull($n("acsWithoutTaxMoney")[i].value),//ACS_WITHOUT_TAX_MONEY 配件不含税金额
				acsWithTaxMoney:returnNull($n("acsWithTaxMoney")[i].value),//ACS_WITH_TAX_MONEY 配件含税金额
				acsMarketAllMoney:returnNull($n("acsMarketAllMoney")[i].value),//ACS_MARKET_ALLMONEY 配件市场总金额
				otherCoefficient:returnNull($n("otherCoefficient")[i].value),//OTHER_COEFFICIENT 其它系数
				priceWithCoefficient:returnNull($n("acs_priceWithCoefficient")[i].value),//PRICE_WITH_COEFFICIENT 系数后价
				qualityId:returnNull($n("acs_qualityId")[i].value),//QUALITY_ID 成色
				acsCode:returnNull($n("acsCode")[i].value),//ACS_CODE 配件编码
				privateType:returnNull($n("acsPprivateType")[i].value)//PRIVATE_TYPE 是否自有料
			});
		}
	return acsLine;
}
function getAccLine(){
	var caclType = jQuery("#caclType").val();
	var accLine = [];
	if(caclType=="3")
		for(var i=0;i<$("tb4").rows.length;i++){
			accLine.push({
				id:returnNull($n("accLineId")[i].value), //ID	NUMBER
				headId:jQuery("#id").val(),//HEAD_ID 核价单头表ID
				accId:returnNull($n("accId")[i].value),//ACC_ID 辅料ID
				accWithoutTaxCost:returnNull($n("accWithoutTaxCost")[i].value),//ACC_WITHOUT_TAX_COST	NUMBER 辅料不含税成本
				accTax:returnNull($n("accTax")[i].value),//ACC_TAX	NUMBER 辅料税率
				accWithTaxCost:returnNull($n("accWithTaxCost")[i].value),//ACC_WITH_TAX_COST 辅料含税成本
				accMarketPrice:returnNull($n("accMarketPrice")[i].value),//ACC_MARKET_PRICE 辅料市场价
				accCoefficient:returnNull($n("accCoefficient")[i].value),//ACC_COEFFICIENT 辅料系数
				priceWithCoefficient:returnNull($n("acc_priceWithCoefficient")[i].value)//PRICE_WITH_COEFFICIENT 含系数后价
			});
		}
	return accLine;
}
function cleanLabelData(){
	var stoneLine = $n("stoneId");

	for ( var i = (stoneLine.length-1); i >=0 ; i--) {
		$("tb1").deleteRow(i);
	}
	var sbraLineId = $n("sbraLineId");
	for ( var i = (sbraLineId.length-1); i >=0 ; i--) {
		$("tb2").deleteRow(i);
	}
	var acsLineId = $n("acsLineId");
	for ( var i = (acsLineId.length-1); i >=0 ; i--) {
		$("tb3").deleteRow(i);
	}
	var accLineId = $n("accLineId");
	for ( var i = (accLineId.length-1); i >=0 ; i--) {
		$("tb4").deleteRow(i);
	}

	var caclType = jQuery("#caclType").val();//核价类型
	var head = null;
	jQuery("#id").val("");
	jQuery("#no").val("");
	jQuery("#wlCode").val("自动生成");//, //万隆编码
	jQuery("#ornaBarCode").val("自动生成");//, //条码
	jQuery("#allWeight_h").val(""); // 饰品总重
	jQuery("#analysisArangeId_h").val(""); //分析范围
	jQuery("#analysisArangeName_h").val("");
	jQuery("#basicPrice_value").val("");
	jQuery("#allWidthoutTaxPrice_value").val("");
	jQuery("#allWidthTaxPrice_value").val("");
	jQuery("#allWidthoutTaxPrice_h").val(""); // 总不含税成本
	jQuery("#allWidthTaxPrice_h").val(""); // 总含税成本
	jQuery("#basicPrice_h").val(""); // 基础价
	if(caclType=="1"){
		countAllWeight_G();
	}else{
		jQuery("#feeWidthoutTaxUnitPrice_h").val("");//FEE_WIDTHOUT_TAX_UNIT_PRICE 工费不含税单价
		jQuery("#feeWidthoutTaxPrice_h").val("");//FEE_WIDTHOUT_TAX_PRICE 工费不含税价
		jQuery("#feeWidthTaxPrice_h").val("");//FEE_WIDTH_TAX_PRICE 工费含税价
		jQuery("#feeMarketPrice_h").val("");//FEE_MARKET_PRICE 工费市场价
		jQuery("#selfMadeTax_h").val("");//SELF_MADE_TAX 自配费用税率
		jQuery("#selfMadeTax_h").val(""); //自配费用不含税价
		jQuery("#selfMadeWidthTaxprice_h").val(""); //自配费用含税价
		jQuery("#selfMadeWidthTaxprice_h").val(""); //自配费用市场价
		jQuery("#allNumber_h").val(""); //总粒数
		jQuery("#mainNumber_h").val(""); //主石粒数
		jQuery("#mainWeight_h").val(""); //主石重量
		jQuery("#mainWidthoutTaxPrice_h").val(""); //主石不含税价
		jQuery("#mainWidthTaxPrice_h").val(""); //主石含税价
		jQuery("#mainMarketPrice_h").val(""); //主石市场价
		jQuery("#secondNumber_h").val(""); //配石粒数
		jQuery("#secondWeight_h").val(""); //配石重量
		jQuery("#secondWidthoutTaxPrice_h").val(""); //配石不含税价
		jQuery("#secondWidthTaxPrice_h").val(""); //配石含税价
		jQuery("#secondMarketPrice_h").val(""); //配石市场价
	    jQuery("#sbraCoefficient_h").val(""); //托架系数
	    jQuery("#sbraWeight_h").val(""); //托架重量
		jQuery("#sbraWidthoutTaxPrice_h").val(""); // 托架不含税价
		jQuery("#sbraWidthTaxPrice_h").val(""); // 托架含税价
		jQuery("#sbraMarketPrice_h").val(""); // 托架市场价
		jQuery("#acsWidthoutTaxPrice_h").val(""); // 配件不含税价
		jQuery("#acsWidthTaxPrice_h").val(""); // 配件含税价
		jQuery("#acsMarketPrice_h").val(""); // 配件市场价
		jQuery("#ncoefficient_h").val(""); // 倍率系数
		jQuery("#allMarketPrice_h").val(""); // 总市场价
		//jQuery("#taxCoefficient_h").val(""); // 税率系数
		jQuery("#updateDate").val(""); // 时间戳
		jQuery("#createUserId").val(""); // 制单人
		jQuery("#createDate").val(""); // 制单日期
		jQuery("#factoryFeeType_h").val(""); // 工厂公费方式
		//jQuery("#otherCoefficient_h").val(""); // 其他系数
		jQuery("#priceWithCoefficient_h").val(""); // 金含系数后价
		jQuery("#feeWithCoefficient_h").val(""); // 工费含系数后价
		jQuery("#allmoneyWithCoefficient_h").val(""); // 总含系数后价
		jQuery("#mainPriceWithCoefficient_h").val(""); // 主石含系数后价
		jQuery("#secondPriceWithCoefficient_h").val(""); // 配石含系数后价
		jQuery("#acsPriceWithCoefficient_h").val(""); // 配件含系数后价
		jQuery("#sbraPriceWithCoefficient_h").val(""); // 托架含系数后价
		jQuery("#lpFee_h").val(""); // 拉/喷沙工费
		jQuery("#mainStoneXqFee_h").val(""); // 主石镶嵌工费总额
		jQuery("#mainStoneXqFee_h").val(""); // 配石镶嵌工费总额
		jQuery("#outGoodsBillNo_h").val(""); //出货单编号
		jQuery("#accWithoutTax_h").val(""); //辅料不含税价
		jQuery("#accWithTax_h").val(""); //辅料含税价
		jQuery("#accMarketPrice_h").val(""); //辅料市场价
		jQuery("#accPriceWithCoefficient_h").val(""); //辅料含系数后价
		jQuery("#newGoldPrice_h").val(""); //金价
		jQuery("#stoneCoeff_h").val(""); //石头系数
		jQuery("#actTradeinFee").val(""); //金价
		jQuery("#tradeinFee").val(""); //石头系数
	}
}
/**
 * 保存
 */
function save(type){
	var caclType = jQuery("#caclType").val();
	if(!checkForm(caclType)){ 
		return null;
	}else if(caclType=="2"||caclType=="3"){
		if(checkStoneType())
			return null;
	}else if(caclType=="4"){
		if(returnAllWeight())
			return null;
	}
	if(caclType == "3"){
		startSync();
			checkStoneAllQty();
		endSync();
		
		if(FC_RETURN){
			return null;
		}	
	}

	var head = getPriceHead();
	var stoneLine = getStoneLine();
	var sbraLine = getSbraLine();
	var acsLine = getAcsLine();
	var accLine = getAccLine();
	var deleteStoneLineIds = jQuery("#deleteStoneLineIds").val();
	var deleteSbraLineIds = jQuery("#deleteSbraLineIds").val();
	var deleteAcsLineIds = jQuery("#deleteAcsLineIds").val();
	var deleteAccLineIds = jQuery("#deleteAccLineIds").val();
	if("3" == type){
		startSync();
	}
	var url = null;
	showLayer(true);
	jQuery("#showMasage").text("处理中....");
	PriceHeadDwr.saveOrUpdatePrice(head,stoneLine,sbraLine,acsLine,accLine,deleteStoneLineIds,deleteSbraLineIds,deleteAcsLineIds,deleteAccLineIds, function(data){
		showLayer(false);
		var str = data.split("_");
		if(str[0]=="1"){
			if(type=="1")
				window.location = "priceHead.vm";
			else if(type=="2"||type=="3"){
				jQuery("#showMasage").text(str[3]+"保存复制完成");
				cleanLabelData();
				
				if(type=="3"){
					var pt = jQuery("#isDoubleLabel_h")[0].checked?"2":"1";
					var params="-"+pt;
					if(str[2]){
						url =  ctxPath + '/calc/priceHead.vm?user_action=printLabels&billid='+str[2]+"&pt="+pt+"&params="+params + "&isauto=1";
					}
				}
			}	
		}else{
			alert(str[1]);
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