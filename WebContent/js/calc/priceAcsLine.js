//统计配件行表数据写入头表
function countHeadByAcsLine(){
	var inivFlag = jQuery("#inivFlag").val();
	if(inivFlag==""||inivFlag=="0")
		return;	
	var acsLineId = $n("acsLineId");
	var acsWidthoutTaxPrice_h = 0;//配件不含税价
	var acsWidthTaxPrice_h = 0;//配件含税价
	var acsMarketPrice_h = 0;//配件市场价
	var acsPriceWithCoefficient_h = 0;//配件含系数后价
	for ( var i = 0; i < acsLineId.length; i++) {
		//acsWidthoutTaxPrice_h+=Number($n("acsWithoutTaxMoney")[i].value);
		acsWidthoutTaxPrice_h=floatAdd($n("acsWithoutTaxMoney")[i].value,acsWidthoutTaxPrice_h);
		//acsWidthTaxPrice_h+=Number($n("acsWithTaxMoney")[i].value);
		acsWidthTaxPrice_h=floatAdd($n("acsWithTaxMoney")[i].value,acsWidthTaxPrice_h);
		//acsMarketPrice_h+=Number($n("acsMarketAllMoney")[i].value);
		acsMarketPrice_h=floatAdd($n("acsMarketAllMoney")[i].value,acsMarketPrice_h);
		//acsPriceWithCoefficient_h+=Number($n("acs_priceWithCoefficient")[i].value);
		acsPriceWithCoefficient_h=floatAdd($n("acs_priceWithCoefficient")[i].value,acsPriceWithCoefficient_h);
	}
	jQuery("#acsWidthoutTaxPrice_h").val(acsWidthoutTaxPrice_h);//配件不含税价
	jQuery("#acsWidthTaxPrice_h").val(acsWidthTaxPrice_h);//配件含税价
	jQuery("#acsMarketPrice_h").val(acsMarketPrice_h);//配件市场价
	jQuery("#acsPriceWithCoefficient_h").val(acsPriceWithCoefficient_h);//配件含系数后价
	
	countHeadData();//统计头表
}
//自有料为是 必输入M编码
function changeAcsM(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	if($n("acsPprivateType")[index].value=="1"){
		$n("acsCode")[index].readOnly = false;
	}else{
		$n("acsCode")[index].value = "";
		$n("acsCode")[index].readOnly = true;
	}
}
//配件类型修改后加载成色信息
function loadQuality(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	var acsType = $n("acsType")[index].value.split("_");
	$n("otherCoefficient")[index].value=acsType[1];//其它 系数
	//加载成色
	showLayer(true);
	SbraTypeDwr.getSbraTypeLineSelectList(acsType[0],function(data){
		showLayer(false);
		addOptions2("acs_qualityId", index, data, null, null, true, true);
	});
}
//计算配件行表数据
function countAcsLineData(obj){
	var index = obj.parentNode.parentNode.rowIndex-1;
	if(!isNumber(index+""))
		index = obj.parentNode.rowIndex-1;
	checkVal("acsWeight",index);
	checkVal("acsQuantity",index);
	checkVal("acsWithoutTaxPrice",index);
	//load
	$n("acsWithoutTaxMoney")[index].value = Number(floatMul($n("acsWithoutTaxPrice")[index].value,$n("acsQuantity")[index].value)).toFixed(2);
	//$n("acsWithoutTaxMoney")[index].value = ($n("acsWithoutTaxPrice")[index].value*$n("acsQuantity")[index].value).toFixed(2);
	$n("acsWithTaxMoney")[index].value = (1*$n("acsWithoutTaxMoney")[index].value).toFixed(2);
	$n("acsMarketAllMoney")[index].value=$n("acsWithTaxMoney")[index].value;
	//$n("acs_priceWithCoefficient")[index].value=($n("otherCoefficient")[index].value*$n("acsMarketAllMoney")[index].value).toFixed(2);
	$n("acs_priceWithCoefficient")[index].value=Number(floatMul($n("otherCoefficient")[index].value,$n("acsMarketAllMoney")[index].value)).toFixed(2);
	changeLpFee();//工费计算方式为件时 要取托架重量算工费数据
	countHeadByAcsLine();//
}
//添加配件行表
function addAcsRow(){
	var inivFlag = jQuery("#inivFlag").val();
	var dataType = inivFlag=="1"||inivFlag=="2"?"dataType='Require' msg='不能为空'":"";
	go_to(4);
	//成色
	var field1 = "<input type='hidden' name='acsLineId' />" +
				"<select name='acs_qualityId' onchange='' style='width:100px;' "+dataType+" ></select>";
	//是否自有料
	var field0 = "<select name='acsPprivateType' onchange='changeAcsM(this)' style='width:70px;'><option value='1'>是</option><option value='0'>否</option></select>";
	//M配件编码
	var field2 = "<input type='text' name='acsCode' style='width:110px;' />";
	//配件类型
	var field3 = "<select name='acsType' onchange='loadQuality(this)' "+dataType+" style='width:100px;' ></select>";
	//重量
	var field4 = "<input type='text' name='acsWeight' style='width:50px;' "+dataType+"  onblur='countAcsLineData(this)' />";
	//数量
	var field5 = "<input type='text' name='acsQuantity' style='width:50px;' value='1' "+dataType+" onblur='countAcsLineData(this)'  />";
	//配件不含税单价
	var field6 = "<input type='text' name='acsWithoutTaxPrice' "+dataType+" style='width:80px;' onblur='countAcsLineData(this)'  />";
	//配件不含税金额
	var field7 = "<input type='text' name='acsWithoutTaxMoney' "+dataType+" style='width:80px;' readonly />";
	//税率
	var field8 = "<input type='text' name='acsTax' readonly style='width:40px;' value='1'/>";
	// 配件市场总金额 配件含税金额
	var field9 = "<input type='hidden' name='acsMarketAllMoney' style='width:80px;' readonly />" +
			"<input type='text' name='acsWithTaxMoney' "+dataType+" style='width:80px;' readonly />";
	//其它系数
	var field11 = "<input type='text' name='otherCoefficient' "+dataType+" style='width:70px;' readonly />";
	//系数后价
	var field12 = "<input type='text' name='acs_priceWithCoefficient' "+dataType+" style='width:70px;' readonly />";
	
	var fieldd = "<input type='button' value='删除' style='width:60px;' onclick='beforeDeleteRow(this,\"tb3\",3)'>";
	insertRow("tb3",[field1,field0,field2,field3,field4,field5,field6,field7,field8,field9,field11,field12,fieldd],false);
	
	//加载配件类型
	SbraTypeDwr.getSbraTypeHeadList("2",function(data){
		addOptions2("acsType", $("tb3").rows.length - 1, data, null, null, true, true);
	});
}

