//统计石头行表数据写入头表
function countHeadByStoneLine(){
	var inivFlag = jQuery("#inivFlag").val();
	if(inivFlag==""||inivFlag=="0")
		return;
	var stoneId = $n("stoneId");
	var mainNumber_h = 0;//主石粒数
	var mainWeight_h = 0;//主石重量
	var mainWidthoutTaxPrice_h = 0;//主石不含税价
	var mainWidthTaxPrice_h = 0;//主石含税价
	var mainMarketPrice_h = 0;//主石市场价
	var mainPriceWithCoefficient_h = 0;//主石含系数后价
	
	var secondNumber_h = 0;//配石粒数
	var secondWeight_h = 0;//配石重量
	var secondWidthoutTaxPrice_h = 0;//配石不含税价
	var secondWidthTaxPrice_h = 0;//配石含税价
	var secondMarketPrice_h = 0;//配石市场价
	var secondPriceWithCoefficient_h = 0;//配石含系数后价
	
	for ( var i = 0; i < stoneId.length; i++) {
		if($n("stoneType")[i].value=='1'){
			//mainNumber_h+=Number($n("stoneNums")[i].value);
			mainNumber_h=floatAdd($n("stoneNums")[i].value,mainNumber_h);
			//mainWeight_h+=Number($n("stoneWeight")[i].value);
			mainWeight_h=floatAdd($n("stoneWeight")[i].value,mainWeight_h);
			//mainWidthoutTaxPrice_h+=Number($n("withoutTaxAllMoney")[i].value);
			mainWidthoutTaxPrice_h=floatAdd($n("withoutTaxAllMoney")[i].value,mainWidthoutTaxPrice_h)
			//mainWidthTaxPrice_h+=Number($n("withTaxAllMoney")[i].value);
			mainWidthTaxPrice_h=floatAdd($n("withTaxAllMoney")[i].value,mainWidthTaxPrice_h);
			//mainMarketPrice_h+=Number($n("marketAllMoney")[i].value);
			mainMarketPrice_h=floatAdd($n("marketAllMoney")[i].value,mainMarketPrice_h);
			//mainPriceWithCoefficient_h+=Number($n("stone_priceWithCoefficient")[i].value);
			mainPriceWithCoefficient_h=floatAdd($n("stone_priceWithCoefficient")[i].value,mainPriceWithCoefficient_h);
		}else if($n("stoneType")[i].value=='2'){
			//secondNumber_h+=Number($n("stoneNums")[i].value);
			secondNumber_h=floatAdd($n("stoneNums")[i].value,secondNumber_h);
			//secondWeight_h+=Number($n("stoneWeight")[i].value);
			secondWeight_h=floatAdd($n("stoneWeight")[i].value,secondWeight_h);
			//secondWidthoutTaxPrice_h+=Number($n("withoutTaxAllMoney")[i].value);
			secondWidthoutTaxPrice_h=floatAdd($n("withoutTaxAllMoney")[i].value,secondWidthoutTaxPrice_h);
			//secondWidthTaxPrice_h+=Number($n("withTaxAllMoney")[i].value);
			secondWidthTaxPrice_h=floatAdd($n("withTaxAllMoney")[i].value,secondWidthTaxPrice_h);
			//secondMarketPrice_h+=Number($n("marketAllMoney")[i].value);
			secondMarketPrice_h=floatAdd($n("marketAllMoney")[i].value,secondMarketPrice_h);
			//secondPriceWithCoefficient_h+=Number($n("stone_priceWithCoefficient")[i].value);
			secondPriceWithCoefficient_h=floatAdd($n("stone_priceWithCoefficient")[i].value,secondPriceWithCoefficient_h);
		}
	}
	
	jQuery("#mainNumber_h").val(mainNumber_h);//主石粒数
	jQuery("#mainWeight_h").val(mainWeight_h);//主石重量
	if(mainNumber_h!="0"){
		jQuery("#avgWeight").val(floatDiv(mainWeight_h,mainNumber_h).toFixed(4));
	}else{
		jQuery("#avgWeight").val("");
	}
	jQuery("#mainWidthoutTaxPrice_h").val(Number(mainWidthoutTaxPrice_h).toFixed(2));//主石不含税价
	jQuery("#mainWidthTaxPrice_h").val(Number(mainWidthTaxPrice_h).toFixed(2));//主石含税价
	jQuery("#mainMarketPrice_h").val(Number(mainMarketPrice_h).toFixed(2));//主石市场价
	jQuery("#mainPriceWithCoefficient_h").val(Number(mainPriceWithCoefficient_h).toFixed(2));//主石含系数后价
	
	jQuery("#secondNumber_h").val(secondNumber_h);//配石粒数
	jQuery("#secondWeight_h").val(secondWeight_h);//配石重量
	jQuery("#secondWidthoutTaxPrice_h").val(Number(secondWidthoutTaxPrice_h).toFixed(2));//配石不含税价
	jQuery("#secondWidthTaxPrice_h").val(Number(secondWidthTaxPrice_h).toFixed(2));//配石含税价
	jQuery("#secondMarketPrice_h").val(Number(secondMarketPrice_h).toFixed(2));//配石市场价
	jQuery("#secondPriceWithCoefficient_h").val(Number(secondPriceWithCoefficient_h).toFixed(2));//配石含系数后价
	
	countHeadData();//统计头表
}

function changeStoneM(obj){//自有料为是 必输入M编码
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	var inivFlag = jQuery("#inivFlag").val();
	if($n("stonePrivateType")[index].value=="1"){
		if(inivFlag=="1"||inivFlag=="2"){
			jQuery($n("luodanCode")[index]).attr("dataType","Require");
			$n("luodanCode")[index].disabled = false;
			jQuery($n("luodanCode")[index]).attr("msg","不能为空");	

			jQuery($n("factoryNuleCode")[index]).attr("dataType"," ");
			$n("factoryNuleCode")[index].disabled = true;
			jQuery($n("factoryNuleCode")[index]).attr("msg","");
		}
		$n("feeType")[index].disabled = true;
		$n("withotTaxPrice")[index].readOnly = true;
	}else{
		if(inivFlag=="1"||inivFlag=="2"){
			jQuery($n("luodanCode")[index]).attr("dataType"," ");
			$n("luodanCode")[index].disabled = true;
			jQuery($n("luodanCode")[index]).attr("msg","");	
			
			jQuery($n("factoryNuleCode")[index]).attr("dataType","Require");
			$n("factoryNuleCode")[index].disabled = false;
			jQuery($n("factoryNuleCode")[index]).attr("msg","不能为空");		
		}
		$n("feeType")[index].disabled = false;
		$n("withotTaxPrice")[index].readOnly = false;
	}
	$n("luodanCode")[index].value = "";
	$n("factoryNuleCode")[index].value = "";
	$n("withotTaxPrice")[index].value= "";//采购价
	$n("marketPrice")[index].value="";//市场价单价			
	$n("marketAllMoney")[index].value="";//市场价总金额		
	$n("stone_priceWithCoefficient")[index].value="";//含系数后价
	$n("withoutTaxAllMoney")[index].value="";	//不含税总金额
	$n("withTaxAllMoney")[index].value="";	//含税总金额
}
//加载石头系数和石头市场价
function loadStoneDwr(obj,type){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	checkVal("stoneNums",index);
	checkVal("stoneWeight",index);
	checkVal("withotTaxPrice",index);
	
	var stonePrivateType=$n("stonePrivateType")[index].value;//是否自有料
	var luodanCode = $n("luodanCode")[index].value;//裸石编码

	dwr.engine.setAsync(false);//设置同步
	
	getCoefficeintByItmeClass(obj);
	if(stonePrivateType=="1"){//是自有料 由裸石带出
		if(luodanCode==""){
			cleanStoneLine(obj);
			return;
		}else{
			checkStoneMaterNo(luodanCode,obj);
			getMaterNoActiveByOrnaCode(obj,type);
		}
	}else{
		getPriceNoType("",obj);
	}

	$n("stone_priceWithCoefficient")[index].value=(floatMul($n("stoneCoefficient")[index].value,
			$n("marketAllMoney")[index].value).toFixed(2));//含系数后价
	
	dwr.engine.setAsync(true);//取消同步
	countHeadByStoneLine();
}
function loadCoefficientByLineItemClass(obj){
	dwr.engine.setAsync(false);//设置同步
	getCoefficeintByItmeClass(obj);
	dwr.engine.setAsync(true);//取消同步
	countHeadByStoneLine();
}
/**
 * 不是自有料到市场价基础表取
 * @param obj
 */
function getPriceNoType(itemClassId,obj){
	var caclType = jQuery("#caclType").val();//核价类型
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	var stonePrivateType=$n("stonePrivateType")[index].value;//是否自有料
	var feeType = $n("feeType")[index].value;
	var num = feeType=='2'?$n("stoneWeight")[index].value:$n("stoneNums")[index].value;
	
	$n("withoutTaxAllMoney")[index].value=floatMul($n("withotTaxPrice")[index].value,num).toFixed(2);	//不含税总金额
	$n("withTaxAllMoney")[index].value=floatMul($n("withoutTaxAllMoney")[index].value,1).toFixed(2);	//含税总金额
	var StonePrice = {//主配石市场价
			   itemClassId:itemClassId,
			   priceType:1,
			   stoneType:$n("stoneType")[index].value,
			   startWeight:floatDiv($n("stoneWeight")[index].value,$n("stoneNums")[index].value).toFixed(3),
			   shapeId:$n("dmShapeId")[index].value,
			   cutId:$n("cutId")[index].value,
			   cleanId:$n("cleanId")[index].value,
			   colorId:$n("colorId")[index].value,
			   colorGradeId:$n("colorGreadId")[index].value};
	if(stonePrivateType=="1"){//是自有料 由裸石带出
		if(StonePrice.itemClassId==""||StonePrice.mainShapeId==""||
				StonePrice.colorId==""||StonePrice.cleanId==""||StonePrice.colorGreadId==""||
				StonePrice.stoneType==""||StonePrice.cutId==""||StonePrice.startWeight==""||
				$n("stoneNums")[index].value==""||$n("stoneWeight")[index].value==""){
			$n("marketPrice")[index].value="";//市场价单价			
			$n("marketAllMoney")[index].value="";//市场价总金额		
			$n("stone_priceWithCoefficient")[index].value="";//含系数后价
			return;
		}
		//获取主配石市场价
		showLayer(true);
		StonePriceDwr.getStonePrice(StonePrice,function(data){
			showLayer(false);
			if(null==data){	
				$n("marketPrice")[index].value="";//市场价单价			
				$n("marketAllMoney")[index].value="";//市场价总金额		
				$n("stone_priceWithCoefficient")[index].value="";//含系数后价
				alert("石头行表:第"+(index+1)+"行没有维护市场价");
			}else{
				if(caclType == "2"){
					$n("marketPrice")[index].value=data.marketPrice;//市场价单价
					$n("marketAllMoney")[index].value=(data.marketPrice*num).toFixed(2);//市场价总金额	
				}else{
					$n("marketAllMoney")[index].value=$n("withTaxAllMoney")[index].value;//市场价总金额
					$n("marketPrice")[index].value=floatDiv($n("marketAllMoney")[index].value,num).toFixed(2);//市场价单价
				}
				
				$n("stone_priceWithCoefficient")[index].value=(
						floatMul($n("stoneCoefficient")[index].value,
						$n("marketAllMoney")[index].value).toFixed(2));//含系数后价
			}
		});
	}else{
		if(StonePrice.startWeight==""||$n("withTaxAllMoney")[index].value==""||$n("stoneNums")[index].value==""){			
			$n("marketAllMoney")[index].value="";//市场价总金额		
			$n("stone_priceWithCoefficient")[index].value="";//含系数后价
			return;
		}
		$n("marketAllMoney")[index].value=$n("withTaxAllMoney")[index].value;//市场价总金额	
		$n("marketPrice")[index].value=floatDiv($n("marketAllMoney")[index].value,num).toFixed(2);//市场价单价
		$n("stone_priceWithCoefficient")[index].value=(
				floatMul($n("stoneCoefficient")[index].value,
				$n("marketAllMoney")[index].value).toFixed(2));//含系数后价
	}
}
function getCoefficeintByItmeClass(obj){
	//取石系数 ,钻石核价单取（钻石核价石头系数）；其它珠宝核价单取（宝石系数）
	var caclType = jQuery("#caclType").val();//核价类型
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	var itemClassId = "";
	if(caclType=="2")
		itemClassId = jQuery("#itemClassId").val();
	else{
		itemClassId = $n("stoneItemClassId")[index].value;
	}
	var stoneWeight = $n("stoneWeight")[index].value;
	var stoneNums=$n("stoneNums")[index].value;
	if(itemClassId==""||stoneWeight==""||stoneNums==""){
		$n("stoneCoefficient")[index].value="";//石头系数
		$n("stone_priceWithCoefficient")[index].value="";//含系数后价
	}else{
		showLayer(true);
		stoneWeight=floatDiv(stoneWeight,stoneNums).toFixed(6);//3.15修改拿下平均分数取
		PriceHeadDwr.getCalcStoneMainCoefficient(itemClassId,stoneWeight,function(snum){//大小类、重量获取钻石核价石头系数
			showLayer(false); 
			if(snum==null||snum==""){
				$n("stoneCoefficient")[index].value="";//石头系数
				$n("stone_priceWithCoefficient")[index].value="";//含系数后价
				alert("钻石石头行表:第"+(index+1)+"行没有维护钻石核价石头系数");
			}else{
				$n("stoneCoefficient")[index].value = snum;
				$n("stone_priceWithCoefficient")[index].value=(
						floatMul($n("stoneCoefficient")[index].value,
						$n("marketAllMoney")[index].value).toFixed(2));//含系数后价
			}
		});
	}
}


/**
 * 查找非现有量
 */
function getMaterNoActiveByOrnaCode(obj,type){
	var index = obj.parentNode.parentNode.rowIndex-1;
	var inivFlag = jQuery("#inivFlag").val();
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	var luodanCode = $n("luodanCode")[index].value;
	var venderId = jQuery("#vender_h").val();
	var headId = jQuery("#id").val();//头表ID
	if($n("stonePrivateType")[index].value=="1"){
		if(luodanCode=="")
			return;
		else if(venderId==""){
			alert("先选择交接单");
			$n("luodanCode")[index].value="";
			return;
		}
		showLayer(true);
		PriceHeadDwr.getMaterNoActiveByOrnaCode(luodanCode,venderId,$n("stoneId")[index].value,function(mater){
			showLayer(false);
			if(mater==null){
				if(inivFlag=="2"){
					loadMaterInivByCode(obj,type,luodanCode,venderId);
				}else{
					alert("编码不存在");
					cleanStoneLine(obj);
					$n("luodanCode")[index].value="";					
				}
			}else{
				 if(mater.nowQty==null){
					alert("该裸石没有写货品台帐记录");
					cleanStoneLine(obj);
					$n("luodanCode")[index].value="";
					return;
				}else if(mater.supplierId!=venderId){
					alert("供应商与头表不符");
					cleanStoneLine(obj);
					$n("luodanCode")[index].value="";
					return;
				}else if(mater.nowQty=="0"||mater.nowQty==null){
					alert("裸石粒数已全部核完");
					cleanStoneLine(obj);
					$n("luodanCode")[index].value="";
					return;
				}else if(Number(mater.nowQty)<$n("stoneNums")[index].value){
					alert("钻石石头行表:第"+(index+1)+"行粒数不能大于货品台帐记录的未结算数量");
					cleanStoneLine(obj);
					$n("stoneNums")[index].value="";
					return;
				}
				setMaterData(mater,obj,type);
			}
		});
	}
}
function loadMaterInivByCode(obj,type,luodanCode,venderId){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	
	showLayer(true);
	PriceHeadDwr.getMaterActiveByOrnaCode(luodanCode,venderId,$n("stoneId")[index].value,function(){
		showLayer(false);
		if(materA==null){
			alert("编码不存在");
			cleanStoneLine(obj);
			$n("luodanCode")[index].value="";
		}else{
			if(materA.nowQty==null){
				alert("该裸石没有写货品台帐记录");
				cleanStoneLine(obj);
				$n("luodanCode")[index].value="";
				return;
			}else if(materA.supplierId!=venderId){
				alert("供应商与头表不符");
				cleanStoneLine(obj);
				$n("luodanCode")[index].value="";
				return;
			}else if(materA.nowQty=="0"){
				alert("裸石粒数已全部核完");
				cleanStoneLine(obj);
				$n("luodanCode")[index].value="";
				return;
			}else if(Number(materA.nowQty)<$n("stoneNums")[index].value){
				alert("钻石石头行表:第"+(index+1)+"行粒数不能大于货品台帐记录的未结算数量");
				cleanStoneLine(obj);
				$n("stoneNums")[index].value="";
				return;
			}
			setMaterData(materA,obj,type);
		}
	});
}
function cleanStoneLine(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	$n("dmShapeId")[index].value= ""; 
	$n("withoutTaxAllMoney")[index].value="";	//不含税总金额
	$n("withTaxAllMoney")[index].value="";	//含税总金额
	$n("colorId")[index].value= ""; 
	$n("cleanId")[index].value= ""; 
	$n("colorGreadId")[index].value= ""; 
	$n("cutId")[index].value= ""; 
	$n("withotTaxPrice")[index].value="";//
	$n("marketPrice")[index].value="";//市场价单价	
	$n("withotTaxPrice")[index].value= "";//采购价		
	$n("marketAllMoney")[index].value="";//市场价总金额		
	$n("stone_priceWithCoefficient")[index].value="";//含系数后价
}

function setMaterData(mater,obj,type){
	var caclType = jQuery("#caclType").val();//核价类型
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	if("1"==type){
		$n("dmShapeId")[index].value= mater.mainShapeId; 
		$n("colorId")[index].value= mater.colorId; 
		$n("cleanId")[index].value= mater.cleanId; 
		$n("colorGreadId")[index].value= mater.mainColorGradeId; 
		$n("cutId")[index].value= mater.cutId; 		
	}
	
	mater.costUnit=mater.costUnit=="3"?"1":mater.costUnit;
	$n("feeType")[index].value=mater.costUnit;
	var num = mater.costUnit=='2'?$n("stoneWeight")[index].value:$n("stoneNums")[index].value;
	if(mater.costUnit=="2"){//裸石计费方式为克拉时
		$n("withotTaxPrice")[index].value= floatDiv(mater.realTotalCost,mater.allQty).toFixed(2);
	}else{
		$n("withotTaxPrice")[index].value= floatDiv(mater.realTotalCost,mater.stoneNowNum).toFixed(2);
	}
	$n("withoutTaxAllMoney")[index].value = floatMul(//不含税总金额
			$n("withotTaxPrice")[index].value,num).toFixed(2);
	$n("withTaxAllMoney")[index].value= floatMul(//含税总金额
			$n("withoutTaxAllMoney")[index].value,1).toFixed(2);
	if(mater.itemClassId=="156"){//原料钻石 
		var identId = false;
		if((mater.hrdCert!=""&&mater.hrdCert!=null)||(mater.giaCert!=""&&mater.giaCert!=null)||(mater.igiCert!=""&&mater.igiCert!=null))
			identId = true;
		if($n("stoneWeight")[index].value>=1){
			 countMPMP(mater,obj);
		}else if($n("stoneWeight")[index].value>=0.3&&identId){
			 countMPMP(mater,obj);
		}else{
			getPriceNoType(mater.itemClassId,obj);
		}
	}else{
		countMPMP(mater,obj);
	}
	
}
function countMPMP(mater,obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	var num = mater.costUnit=='2'?$n("stoneWeight")[index].value:$n("stoneNums")[index].value;
	if(mater.itemClassId=="156"){//原料钻石算原成本 
		var dj = 0;
		if(mater.costUnit=="2"){//裸石计费方式为克拉时
			dj= floatDiv(mater.inivCost,mater.allQty).toFixed(2);
		}else{
			dj= floatDiv(mater.inivCost,mater.stoneNowNum).toFixed(2);
		}
		$n("marketAllMoney")[index].value= floatMul(dj,num).toFixed(2);//市场价总金额
	}else{
		$n("marketAllMoney")[index].value= $n("withTaxAllMoney")[index].value;//市场价总金额
	}
	$n("marketPrice")[index].value= floatDiv(//市场价单价
			$n("marketAllMoney")[index].value,num).toFixed(2);
}

function checkStoneMaterNo(no,obj){
	var inivFlag = jQuery("#inivFlag").val();
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	if(inivFlag==""||inivFlag=="0")
		return ;

	var caclType = jQuery("#caclType").val();//核价类型
	if(caclType=="3"||caclType=="2"){
		var stoneId = $n("stoneId");
		for ( var i = 0; i < stoneId.length; i++) {
			if($n("luodanCode")[i].value==no&&index!=i){
				$n("luodanCode")[index].value="";
				alert("裸石编码已经存在");
			}
		}
	}
}
function checkStoneType(){
	var inivFlag = jQuery("#inivFlag").val();
	if(inivFlag==""||inivFlag=="0")
		return false;
	var caclType = jQuery("#caclType").val();//核价类型
	if(caclType=="2"){
		var stoneId = $n("stoneId");
		var count = 0;
		for ( var i = 0; i < stoneId.length; i++) {
			if($n("stoneType")[i].value=='1'){
				count ++;
			}
		}
		if(count>=1){
			return false;
		}
		alert("石头行表没有主石");
		return true;
	}
	return false;
}
//添加石头行表
function addStoneRow(){
	var inivFlag = jQuery("#inivFlag").val();
	var caclType = jQuery("#caclType").val();//核价类型
	var dataType1 = inivFlag=="1"||inivFlag=="2"?"dataType='Require' msg='不能为空'":"";
	var dataType2 = inivFlag=="1"||inivFlag=="2"?"dataType='Integer' msg='不能为空,必须整数'":"";
	var dataType = inivFlag=="1"||inivFlag=="2"?"dataType='Double' msg='不能为空,且为数字类型'":"";
	go_to(2);
	//计费方式
	var field1 = "<input type='hidden' name='stoneId' />" +
			"<select name='feeType' disabled onchange='loadStoneDwr(this,2)'>" +
			"<option value='1'>件</option>" +
			"<option value='2'>克拉</option></select>";
	//是否自有料
	var field2 = "<select name='stonePrivateType' onchange='changeStoneM(this)' style='width:90px;'><option value='1'>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
			"<option value='0'>否</option></select>";
	//M裸石编码
	var field12 = "<input type='text' name='luodanCode' onchange='loadStoneDwr(this,1)' style='width:130px;' "+dataType1+"/>";
	//工厂编码
	var field50 = "<input type='text' name='factoryNuleCode' style='width:130px;' disabled />";
	//石头类型 
	var field4 = "<select name='stoneType' onchange='loadStoneDwr(this,2)' style='width:80px;' "+dataType1+"></select>";
	//形状
	var field5 = "<select name='dmShapeId' onchange='loadStoneDwr(this,2)' style='width:80px;' ></select>";
	//颜色
	var field6 = "<select name='colorId' onchange='loadStoneDwr(this,2)' style='width:80px;'></select>";
	//净度
	var field7 = "<select name='cleanId' onchange='loadStoneDwr(this,2)' style='width:80px;'></select>";
	//色级
	var field8 = "<select name='colorGreadId' onchange='loadStoneDwr(this,2)' style='width:80px;'></select>";
	//切工
	var field9 = "<select name='cutId' onchange='loadStoneDwr(this,2)' style='width:80px;'></select>";
	//粒数
	var field10 = "<input type='text' name='stoneNums' onblur='loadStoneDwr(this,2)' style='width:80px;' "+dataType2+" />";
	//石头重量
	var field11 = "<input type='text' name='stoneWeight' onblur='loadStoneDwr(this,2)' style='width:70px;'  "+dataType+"/>";
	//不含税单价
	var field14 = "<input type='text' name='withotTaxPrice' "+dataType+" readonly onchange='loadStoneDwr(this,2)' style='width:90px;'  />";
	//不含税总金额
	var field16 = "<input type='text' name='withoutTaxAllMoney' "+dataType+" readonly style='width:110px;' />";
	//税率
	var field23 = "<input type='text' name='stoneTax' readonly style='width:40px;' value='1'/>";
	//含税总金额
	var field17 = "<input type='text' name='withTaxAllMoney' "+dataType+" readonly style='width:80px;' />";
	//市场价单价
	var field19 = "<input type='text' name='marketPrice' "+dataType+" readonly  style='width:80px;'/>";
	//市场价总金额
	var field20 = "<input type='text' name='marketAllMoney' "+dataType+" readonly style='width:100px;'/>";
	//石头系数
	var field21 = "<input type='text' name='stoneCoefficient' "+dataType+" readonly style='width:80px;'/>";
	//含系数后价
	var field22 = "<input type='text' name='stone_priceWithCoefficient' "+dataType+" readonly style='width:80px;'/>";
	
	var fieldd = "<input type='button' value='删除' style='width:60px;' onclick='beforeDeleteRow(this,\"tb1\",1)'>"+
				'<input type="hidden" name="oldOrnaCode" value=""/>'+
				'<input type="hidden" name="oldStoneNum" value=""/>';
	
	var field24 = "<select name='stoneItemClassId' onchange='loadCoefficientByLineItemClass(this)' "+dataType+" style='width:80px;'></select>";
	if(caclType=="2")
		insertRow("tb1",[field1,field2,field12,field50,field4,field5,field6,field7,field8,field9,field10,field11,field14,field16,field23,field17,field19,field20,field21,field22,fieldd],false);
	else{
		insertRow("tb1",[field1,field2,field12,field50,field24,field4,field5,field6,field7,field8,field9,field10,field11,field14,field16,field23,field17,field19,field20,field21,field22,fieldd],false);
		BdCommonDwr.getAllItemClassForSlt(function(data){
			addOptions2("stoneItemClassId", $("tb1").rows.length - 1,data, null, null, true, true );
		});
	}
		
	//形状
	DictDwr.getDictsForSlt("diashape", function(data){
		addOptions2("dmShapeId", $("tb1").rows.length - 1, data, null, null, true, true);
	});
	//颜色
	DictDwr.getDictsForSlt("diacolor", function(data){
		addOptions2("colorId", $("tb1").rows.length - 1, data, null, null, true, true);
	});
	//净度
	DictDwr.getDictsForSlt("diaclean", function(data){
		addOptions2("cleanId", $("tb1").rows.length - 1, data, null, null, true, true);
	});
	//色级
	DictDwr.getDictsForSlt("diacolorgrade", function(data){
		addOptions2("colorGreadId", $("tb1").rows.length - 1, data, null, null, true, true);
	});
	//切工
	DictDwr.getDictsForSlt("diacut", function(data){
		addOptions2("cutId", $("tb1").rows.length - 1, data, null, null, true, true);
	});
	//石头类型
	DictDwr.getDictsForSlt("stonetype", function(data){
		addOptions2("stoneType", $("tb1").rows.length - 1, data, null, null, true, true,"1");
	});
}