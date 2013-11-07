//统计托架行表数据写入头表
function countHeadBySbraLine(){
	var inivFlag = jQuery("#inivFlag").val();
	if(inivFlag==""||inivFlag=="0")
		return;	
	var sbraLineId = $n("sbraLineId");
	var sbraWeight_h = 0;//托架重量
	var sbraWidthoutTaxPrice_h = 0;//托架不含税价
	var sbraWidthTaxPrice_h = 0;//托架含税价
	var sbraMarketPrice_h = 0;//托架市场价
	var sbraPriceWithCoefficient_h = 0;//托架含系数后价
	for ( var i = 0; i < sbraLineId.length; i++) {
		//sbraWeight_h+=Number($n("sbraWeight")[i].value);
		sbraWeight_h=floatAdd($n("sbraWeight")[i].value,sbraWeight_h);
		//sbraWidthoutTaxPrice_h+=Number($n("sbraWithoutTaxmoney")[i].value);
		sbraWidthoutTaxPrice_h=floatAdd($n("sbraWithoutTaxmoney")[i].value,sbraWidthoutTaxPrice_h);
		//sbraWidthTaxPrice_h+=Number($n("sbraWithTaxMoney")[i].value);
		sbraWidthTaxPrice_h=floatAdd($n("sbraWithTaxMoney")[i].value,sbraWidthTaxPrice_h);
		//sbraMarketPrice_h+=Number($n("sbraMarketAllMoney")[i].value);
		sbraMarketPrice_h=floatAdd($n("sbraMarketAllMoney")[i].value,sbraMarketPrice_h);
		//sbraPriceWithCoefficient_h+=Number($n("sbra_priceWithCoefficient")[i].value);
		sbraPriceWithCoefficient_h=floatAdd($n("sbra_priceWithCoefficient")[i].value,sbraPriceWithCoefficient_h);
	}
	jQuery("#sbraWeight_h").val(Number(sbraWeight_h).toFixed(2));//托架重量
	jQuery("#sbraWidthoutTaxPrice_h").val(Number(sbraWidthoutTaxPrice_h).toFixed(2));//托架不含税价
	jQuery("#sbraWidthTaxPrice_h").val(Number(sbraWidthTaxPrice_h).toFixed(2));//托架含税价
	jQuery("#sbraMarketPrice_h").val(Number(sbraMarketPrice_h).toFixed(2));//托架市场价
	jQuery("#sbraPriceWithCoefficient_h").val(Number(sbraPriceWithCoefficient_h).toFixed(2));//托架含系数后价
	
	countHeadData();//统计头表
}

function changeSbraM(obj){//自有料为是 必输入M编码
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	if($n("sbraPrivateType")[index].value=="1"){
		$n("materCode")[index].readOnly = false;
	}else{
		$n("materCode")[index].value = "";
		$n("materCode")[index].readOnly = true;
	}
}
//计算托架行表数据
function countSbraLineData(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	checkVal("goldLostPercent",index);
	checkVal("sbraWeight",index);
	checkVal("sbraWithoutTaxPrice",index);
	
	//金耗重：托架重量*损耗率/100。
	$n("goldLostWeight")[index].value=Number(floatDiv(floatMul($n("sbraWeight")[index].value,$n("goldLostPercent")[index].value),100)).toFixed(2);
	///$n("goldLostWeight")[index].value=Number($n("sbraWeight")[index].value/100*$n("goldLostPercent")[index].value).toFixed(2);
	//含耗重：金耗加下托架重量
	$n("goldAllweight")[index].value=Number(floatAdd($n("goldLostWeight")[index].value,$n("sbraWeight")[index].value)).toFixed(2);
	//$n("goldAllweight")[index].value=Number(Number($n("goldLostWeight")[index].value)+Number($n("sbraWeight")[index].value)).toFixed(2);
	//托架不含税金额：含耗重*托架不含税单价。
	$n("sbraWithoutTaxmoney")[index].value=Number(floatMul($n("goldAllweight")[index].value,$n("sbraWithoutTaxPrice")[index].value)).toFixed(2);
	//$n("sbraWithoutTaxmoney")[index].value=($n("goldAllweight")[index].value*$n("sbraWithoutTaxPrice")[index].value).toFixed(2);
	//托架含税金额:等于托架不含税金额
	$n("sbraWithTaxMoney")[index].value=$n("sbraWithoutTaxmoney")[index].value;
	
	var qualityId = jQuery("#qualityId").val();
	var qualityName = jQuery("#qualityId option:selected").text();
	$n("sbra_qualityId")[index].value = qualityId;
	$n("sbra_qualityName")[index].value = qualityName;
	
	var sbra_qualityId = qualityId;
	var itemClassId = jQuery("#itemClassId").val();
	
	dwr.engine.setAsync(false);//设置同步
	if(sbra_qualityId==""){
		$n("sbraMarketPrice")[index].value="";
	}else{
		//材质找市场价
		showLayer(true);
		SbraPriceDwr.getSbraPriceByQualityId(sbra_qualityId,function(data){
			showLayer(false);
			if(data==null){
				$n("sbraMarketPrice")[index].value="";
			}else{
				$n("sbraMarketPrice")[index].value = data.marketPrice;
			}
		});
	}
	
	if(sbra_qualityId==""||$n("sbraWeight")[index].value==""){
		$n("sbraMarketAllMoney")[index].value="";
		$n("sbra_priceWithCoefficient")[index].value="";
		$n("sbraCoefficient")[index].value="";
	}else{
		//材质找系数
		showLayer(true);
		SbraCoefficientDwr.getSbraCoefficientByQualityId(itemClassId,sbra_qualityId,$n("sbraWeight")[index].value,function(data){
			showLayer(false);
			if(data==null||data.coefficient==null){
				$n("sbraMarketAllMoney")[index].value="";
				$n("sbra_priceWithCoefficient")[index].value="";
				$n("sbraCoefficient")[index].value="";
				info("当前材质下托架重量范围内没有维护系数。");
			}else{
				$n("sbraCoefficient")[index].value = data.coefficient;
				//托架市场价总金额：含耗重*托架市场价单价
				$n("sbraMarketAllMoney")[index].value = Number(floatMul($n("sbraMarketPrice")[index].value,$n("goldAllweight")[index].value)).toFixed(2);
				//$n("sbraMarketAllMoney")[index].value = ($n("sbraMarketPrice")[index].value*$n("goldAllweight")[index].value).toFixed(2);
				//含系数后价：托架市场价总金额*托架系数。
				$n("sbra_priceWithCoefficient")[index].value=Number(floatMul($n("sbraCoefficient")[index].value,$n("sbraMarketAllMoney")[index].value)).toFixed(2);
				//$n("sbra_priceWithCoefficient")[index].value=($n("sbraCoefficient")[index].value*$n("sbraMarketAllMoney")[index].value).toFixed(2);
			}
			
		});
	}
	dwr.engine.setAsync(true);//取消同步
	countHeadBySbraLine();
}
//添加托架行表
function addSbraRow(){
	var inivFlag = jQuery("#inivFlag").val();
	var dataType = inivFlag=="1"||inivFlag=="2"?"dataType='Require' msg='不能为空'":"";
	go_to(3);
	var qualityId = jQuery("#qualityId").val();
	var qualityName = jQuery("#qualityId option:selected").text();
	//金耗重
	var field1 = "<input type='hidden' name='sbraLineId' />" +
				"<input type='hidden' name='sbra_qualityId' value='"+qualityId+"' />"+
				"<input type='text' name='goldLostWeight' "+dataType+" style='width:60px;' readonly />" +
				"<input type='hidden' name='sbraWithoutTaxmoney' style='width:90px;' readonly />";
	//含耗重
	var field2 = "<input type='text' name='goldAllweight' "+dataType+" style='width:60px;' readonly />";
	//损耗率
	var field3 = "<input type='text' name='goldLostPercent' "+dataType+" style='width:60px;' onblur='countSbraLineData(this)'  />";
	//是否自有料
	var field4 = "<select name='sbraPrivateType' style='width:90px;'><option value='1'>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
			"<option value='0'>否</option></select>";
	//托架材质
	var field5 = "<input type='text' name='sbra_qualityName' "+dataType+" style='width:100px;' value='"+qualityName+"' readonly />";
	//托架重量
	var field7 = "<input type='text' name='sbraWeight' "+dataType+" style='width:70px;' onblur='countSbraLineData(this)'  />";
	//托架不含税单价
	var field8 = "<input type='text' name='sbraWithoutTaxPrice' "+dataType+" style='width:90px;' onblur='countSbraLineData(this)'  />";
	//托架不含税金额
	//托架含税金额
	var field11 = "<input type='text' name='sbraWithTaxMoney' "+dataType+" style='width:90px;' readonly />";
	//托架市场价单价
	var field12 = "<input type='text' name='sbraMarketPrice' "+dataType+" style='width:70px;' readonly />";
	//托架市场价总金额
	var field13 = "<input type='text' name='sbraMarketAllMoney' "+dataType+" style='width:90px;' readonly />";
	//托架系数
	var field14 = "<input type='text' name='sbraCoefficient' "+dataType+" style='width:60px;' readonly />";
	//含系数后价
	var field15 = "<input type='text' name='sbra_priceWithCoefficient' "+dataType+" style='width:70px;' readonly />";
	var fieldd = "<input type='button' value='删除' style='width:60px;' onclick='beforeDeleteRow(this,\"tb2\",2)'>";
	
	insertRow("tb2",[field1,field2,field3,field4,field5,field7,field8,field11,field12,field13,field14,field15,fieldd],false);
	
}