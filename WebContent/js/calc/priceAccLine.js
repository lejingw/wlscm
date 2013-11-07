//统计辅料行表数据写入头表
function countHeadByAccLine(){
	var inivFlag = jQuery("#inivFlag").val();
	if(inivFlag==""||inivFlag=="0")
		return;	
	var accLineId = $n("accLineId");
	var accMarketPrice_h = 0;
	var accPriceWithCoefficient_h = 0;
	var accWithoutTax_h = 0;
	var accWithTax_h = 0;
	for ( var i = 0; i < accLineId.length; i++) {
		//accMarketPrice_h+=Number($n("accMarketPrice")[i].value);
		accMarketPrice_h=floatAdd($n("accMarketPrice")[i].value,accMarketPrice_h);
		//accPriceWithCoefficient_h+=Number($n("acc_priceWithCoefficient")[i].value);
		accPriceWithCoefficient_h=floatAdd($n("acc_priceWithCoefficient")[i].value,accPriceWithCoefficient_h);
		//accWithoutTax_h+=Number($n("accWithoutTaxCost")[i].value);
		accWithoutTax_h=floatAdd($n("accWithoutTaxCost")[i].value,accWithoutTax_h);
		//accWithTax_h+=Number($n("accWithTaxCost")[i].value);
		accWithTax_h=floatAdd($n("accWithTaxCost")[i].value,accWithTax_h);
	}
	jQuery("#accMarketPrice_h").val(accMarketPrice_h);//辅料市场价
	jQuery("#accPriceWithCoefficient_h").val(accPriceWithCoefficient_h);//辅料含系数后价
	jQuery("#accWithoutTax_h").val(accWithoutTax_h);//辅料不含税价
	jQuery("#accWithTax_h").val(accWithTax_h);//辅料含税价
	
	countHeadData();//统计头表
	
}
//修改辅料行表数据触发事件 
function countAccLineData(obj,type){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	
	var accId = $n("accId")[index].value;
	if($n("accTax")[index].value=="0"){
		$n("accWithTaxCost")[index].value="";
		$n("accWithoutTaxCost")[index].value="";
		$n("accMarketPrice")[index].value="";
		$n("acc_priceWithCoefficient")[index].value="";
	}
	if("1"==type){
		checkVal("accWithoutTaxCost",index);
		$n("accWithTaxCost")[index].value=Number(floatMul($n("accWithoutTaxCost")[index].value,$n("accTax")[index].value)).toFixed(2);
		//$n("accWithTaxCost")[index].value=($n("accWithoutTaxCost")[index].value*$n("accTax")[index].value).toFixed(2);
	}else{
		checkVal("accWithTaxCost",index);
		$n("accWithoutTaxCost")[index].value=Number(floatDiv($n("accWithTaxCost")[index].value,$n("accTax")[index].value)).toFixed(2);
		//$n("accWithoutTaxCost")[index].value=floatDiv($n("accWithTaxCost")[index].value,$n("accTax")[index].value).toFixed(2);
	}
	$n("accMarketPrice")[index].value=$n("accWithTaxCost")[index].value;
	if(accId!=""){//通过辅料ID打辅料系数
		dwr.engine.setAsync(false);//设置同步
		showLayer(true);
		PriceHeadDwr.getAccessoryById(accId,function(acc){
			showLayer(false);
			if(acc!=null){
				$n("accCoefficient")[index].value=acc.coefficient;
				//$n("acc_priceWithCoefficient")[index].value=(acc.coefficient*$n("accMarketPrice")[index].value).toFixed(2);
				$n("acc_priceWithCoefficient")[index].value=Number(floatMul(acc.coefficient,$n("accMarketPrice")[index].value)).toFixed(2);
			}else{
				$n("accCoefficient")[index].value="";
				$n("acc_priceWithCoefficient")[index].value="";
			}
		});
		dwr.engine.setAsync(true);//取消同步
	}else{
		$n("accCoefficient")[index].value="";
		$n("acc_priceWithCoefficient")[index].value="";
	}
	countHeadByAccLine();//统计行表;
}
//添加辅料行表
function addAccRow(){go_to(5);
	var inivFlag = jQuery("#inivFlag").val();
	var dataType = inivFlag=="1"||inivFlag=="2"?"dataType='Require' msg='不能为空'":"";
	//辅料名称
	var field1 = "<input type='hidden' name='accLineId' />" +
			"<select name='accId' onchange='countAccLineData(this,1)' "+dataType+" style='width:90px;' ></select>";
	//辅料不含税成
	var field2 = "<input type='text' "+dataType+" name='accWithoutTaxCost' style='width:80px;'  onblur='countAccLineData(this,1)' />";
	//辅料名称
	var field3 = "<input type='text' name='accTax' value='1' "+dataType+" onchange='countAccLineData(this,1)' style='width:80px;'  />";
	//辅料市场价 辅料含税成本
	var field4 = "<input type='hidden' name='accMarketPrice' readonly style='width:80px;'/>" +
			"<input type='text' "+dataType+" name='accWithTaxCost' style='width:80px;' style='width:80px;'  onblur='countAccLineData(this,2)'/>";
	//辅料系数
	var field6 = "<input type='text' "+dataType+" name='accCoefficient' readonly style='width:80px;'/>";
	//辅料含系数后价
	var field7 = "<input type='text' "+dataType+" name='acc_priceWithCoefficient' readonly style='width:80px;'/>";
	var fieldd = "<input type='button' value='删除' style='width:60px;' onclick='beforeDeleteRow(this,\"tb4\",4)'>";
	insertRow("tb4",[field1,field2,field3,field4,field6,field7,fieldd],false);
	
	DictDwr.getDictsForSlt("accessories", function(data){
		addOptions2("accId", $("tb4").rows.length - 1, data, null, null, true, true);
	});
}
function keyup(v){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
}