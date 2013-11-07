

var getDateUrlArray = function(datas, lp) {
	var dataArray = new Array();
	var str = "";
	var lastType=0;
	var array=new Array();
	if(lp==1||lp==2||lp==3){//普通、物流、价格标签
		for (var i = 0; i < datas.length; i++) {
			if(datas[i].labelType==null||datas[i].labelType==""){
				continue;
			}
			if(lastType!=datas[i].labelType){
				array=new Array();
				array.push(datas[i].labelType);
				array.push("");
				dataArray.push(array);
			}else{
				array=dataArray[dataArray.length-1];
			}
			lastType=datas[i].labelType;
			str=array[1];
			if (!str == "") {
				str += "\r\n";
			}
			str += printLabel(datas[i],lp);
			array[1]=str;
			dataArray[dataArray.length-1]=array;
		}		
	}else if(lp==4){//吉祥语标签
		for (var i = 0; i < datas.length; i++) {
			var zodiac=renZodiac(datas[i].bornImg); //获取对应的生肖名称
			if (!str == "") {
				str += "\r\n";
			}
			str += '"';
			for (var j = 0; j < datas[i].luckDsc.length; j++) {
				str += datas[i].luckDsc.substring(j,j+1);
				if(j!=datas[i].luckDsc.length-1){
					str += "\r\n";
				}
			}
			for (var j = 0; j < zodiac.length; j++) {
				str += zodiac.substring(j,j+1);
				if(j!=zodiac.length-1){
					str += "\r\n";
				}
			}
			str += '"';	
		}
		array=new Array();
		array.push(null);
		array.push(str);
		dataArray.push(array);
	}else if(lp==5){//条码
		for (var i = 0; i < datas.length; i++) {
			str += datas[i].ornaBarCode+",";
			str += datas[i].ornaCode+",\r\n";
		}
		array=new Array();
		array.push(null);
		array.push(str);
		dataArray.push(array);
	}
	return dataArray;
}

var printLabel = function(o,lp) {
	var type = o.labelType;
	return dataFormat(o, type,lp);
}

/**
 * 格式化数据
 * @param {} o
 * @param {} type 小标签类型
 * @param {} lp 打印类型 1：普通标签 2：物流标签 3：价格标签 说明：价格标签不打印条码     物流标签不打印价格
 * @return {}
 */
var dataFormat = function(o, type,lp) {
	var datastr = "";
	if(lp==3){//价格标签不打印条码
		datastr += ",";
	}else{
		datastr += o.ornaBarCode + ",";		
	}
	datastr += o.ornaCode + ",";
	datastr += o.ornaName + ",";
	if(lp==2||type == 1||type == 5){//物流标签不打印价格
		datastr += ",";
	}else{
		datastr += formatPrice(o.posAmount) + ",";
	}
	if (type == 1) {
		datastr += (formatWeight(o.allQty) == "" ? "" : formatWeight(o.allQty)+ "g")+ ",";
	} else if (type == 2 || type == 3 || type == 4 || type == 6) {
		datastr += (formatWeight(o.allQty) == "" ? "" : "总"+ formatWeight(o.allQty) + "g")+ ",";
	} else if (type == 5) {
		datastr += (formatWeight(o.allQty) == "" ? "" : formatWeight(o.allQty)+ "ct")+ ",";
	}
	datastr += (formatWeight(o.mainWeight) == "" ? "" : "主"+ formatWeight(o.mainWeight) + "ct")+ ",";
	datastr += (formatWeight(o.partStoneWeight) == "" ? "" : "配"+ formatWeight(o.partStoneWeight) + "ct")+ ",";
	datastr += formartString(o.partStoneContent) + ",";
	datastr += (formatWeight(o.specialWeight) == ""? "": formatWeight(o.specialWeight) + "g")+ ",";
	datastr += formatDL(o.isMutiPart) + ",";
	datastr += formatDiam(o.colorGrade) + ",";
	datastr += formatDiam(o.cleanName) + ",";
	datastr += formartString(o.venderCode) + ",";
	//当大类等于时尚千足金（ID=188）时，在三号标签中反面鉴定证书后增加打印商品款式中的主题名称
	if(type==3&&o.ident.indexOf("$")>-1){
		var a = o.ident.substring(0,o.ident.indexOf("$"));
		var b = o.ident.substring(o.ident.indexOf("$")+1,o.ident.length);
		datastr += formartString(a+"     " + b) + ",";
	}else{
		datastr += formartString(o.ident) + ",";
	}
	datastr += formartString(o.GIACert) + ",";
	datastr += formartString(o.HRDCert) + ",";
	datastr += formartString(o.IGICert) + ",";
	datastr += formartString(o.AGSCert) + ",";
	datastr += formartString(o.sizeName) + ",";
	datastr += formartString(o.styleName) + ",";
	datastr += formartString(o.analysisArange) + ",";
	datastr += (formatPrice(o.specialWorkPrice) == "" ? "" : "特殊工费：￥"+ formatPrice(o.specialWorkPrice))+ ",";
	datastr += (formatPrice(o.stringWorkPrice) == "" ? "" : "红绳工费：￥"+ formatPrice(o.stringWorkPrice))+ ",";
	datastr += formartString(o.quality) + ",";
	datastr += formartString(o.themeName) + ",";
	datastr += formatItem(o.itemClassName,o.ornaClassName) + ",";
	datastr += formartString(o.luckDsc) + ",";
	datastr += formartString(o.bornImg) + ",";
	datastr += formartString(o.factoryStyle) + ",";
	datastr += formartString(o.grian) + ",";
	datastr += formartString(o.color) + ",";
	datastr += formartString(o.mainShape) + ",";
	datastr += formartString(o.make) + ",";
	datastr += formartString(o.factorySite) + ",";
	datastr += formartString(o.placeOrigin) + ",";
	datastr += formartString(o.custServicePhone) + ",";
	if(lp==2||type == 1||type == 5||o.posAmount==''){//物流标签/1、5号标签不打印价格
		datastr += ",";
	}else{
		datastr += "￥,"
	}
	return datastr;

}
/**
 * 格式化重量
 * @param {} v
 * @return {String}
 */
var formatWeight = function(v) {
	if (v == "" || v == 0 || !v)
		return "";
	if (isNumber(v)) {
		return Number(v);
	}
	return v;
}
/**
 * 格式化价格
 * @param {} v
 * @return {String}
 */
var formatPrice = function(v) {
	if (v == "" || v == 0 || !v)
		return "";
	if (isNumber(v)) {
		return Number(v).toFixed(2);
	}
	return v;
}
/**
 * 格式化是否多粒
 * @param {} v
 * @return {String}
 */
var formatDL = function(v) {
	if (v && (v == "是" || (v + "") == 'true' || v == "DL")) {
		return "DL";
	}
	return "";
}
/**
 * 格式化大类
 * @param {} v
 * @return {}
 */
var formatItem = function(v,t) {
	if ((v == '时尚千足金' || v == '千足金' || v == '镶嵌千足金')&&(t!='金条'&&t!='金砖')) {
		v = '"焊点、配件金含量≥900‰"';
	} else if ((v == 'Pt950' || v == 'Pt空托' || v == 'Pt900')&&(t!='金条'&&t!='金砖')) {
		v = '"焊点、配件铂含量≥900‰"';
	} else
		v = "";
	return v;
}

var formartString = function(v){
	if(v!=null&&v!=""){
		v='"'+v+'"';
	}
	return v;
}
/**
 * 净度、色级空白不打印
 * @param {} v
 * @return {}
 */
var formatDiam=function(v){
	if(v =="空白")
		v="";
	return v;
}

/**
 * 是否数值
 * @param {} s
 * @return {Boolean}
 */
function isNumber(s) {
	var regu = "^[0-9]+[\.]?[0-9]{0,9}$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 获取标签模板
 * @param {} lt 标签类型 1-6号
 * @param {} printType 打印类型 1：普通标签 2：物流标签 3：价格标签 4: 吉祥语标签 5:条码
 */
function getFormatBtw(lt,printType){
	if(lt){
		return "label" + lt + ".btw";
	}
	if(printType==4){
		return "luckDsc.btw";
	}else if(printType==5){
		return "barcode.btw";
	}
	return null;
}