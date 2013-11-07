package com.jatools.vo.bd;

import com.jatools.vo.stock.MaterIniv;

public class Tag {
	private String labelType;//标签类型
	private String ornaCode;//0 标签编码
	private String ornaBarCode;//1 条码
	private String ornaName;//2 饰品名称
	private Double posAmount;//3 价格
	private Double mainWeight;//4 主石重量
	private Double partStoneWeight;//5 配石重量
	private Double allQty;//6 饰品总重
	private Double specialWeight;//7 特殊重量
	private String sizeName;//8 饰品尺寸
	private String quality;//9 成色（托架材质）
	private String mainShape;//11 主石形状 
	private String partStoneContent;//12 配石内容
	private String cleanName;//13 净度
	private String color;//14 颜色
	private String factoryStyle;//15 工商款号
	private String venderCode;//16 供应商编码;2010-07-08修改 由编码改为供应商代码
	private Double stringWorkPrice;//17 红绳工费
	private String specialWorkPrice;//18 特殊工费
	private String itemClassName;//大类
	private String isMutiPart;//19 是否多粒
	private String grian;//粒数
	
	private String ident;//鉴定证书号
	private String HRDCert;//HRD国际证书
	private String GIACert;//GIA国际证书
	private String IGICert;//IGI国际证书
	private String AGSCert;//AGS国际证书
	
	//----------此处只有吉祥语标签需要附值
	private String luckDsc;//吉祥语标签  
	private String bornImg;//生肖
	
	private String errorMsg;//记录错误信息	（不赋值）
	
	
	private String certificate;//合格证	（不赋值）
	private String colorGrade;//色级
	private String analysisArange;
	
	private String styleName;
	private String themeName; //主题名称
	
	private String make;        //厂名	（不赋值）
	private String factorySite;  //厂址	（不赋值）
	private String placeOrigin;   //产地	（不赋值）
	private String custServicePhone; //客服电话	（不赋值）
	
	private String ornaClassName;
	private String styleItemCode;//款式大类编码 12-05-07
	
	public Tag(){}
	public Tag(String ornaCode, String ornaBarCode, String ornaName,
			Double posAmount, Double mainWeight, Double partStoneWeight,
			Double allQty, Double specialWeight, String sizeName,
			String quality, String mainShape, String partStoneContent,
			String cleanName, String color, String factoryStyle,
			String venderCode, Double stringWorkPrice, String specialWorkPrice,
			String itemClassName, String isMutiPart, String ident, String HRDCert,
			String GIACert, String IGICert, String AGSCert, String grian,String certificate,
			String colorGrade,String make,String factorySite,String placeOrigin, String custServicePhone,String themeName,
			String styleItemCode) {
		this.ornaCode = ornaCode;
		this.ornaBarCode = ornaBarCode;
		this.ornaName = ornaName;
		this.posAmount = posAmount;
		this.mainWeight = mainWeight;
		this.partStoneWeight = partStoneWeight;
		this.allQty = allQty;
		this.specialWeight = specialWeight;
		this.sizeName = sizeName;
		this.quality = quality;
		this.mainShape = mainShape;
		this.partStoneContent = partStoneContent;
		this.cleanName = cleanName;
		this.color = color;
		this.factoryStyle = factoryStyle;
		this.venderCode = venderCode;
		this.stringWorkPrice = stringWorkPrice;
		this.specialWorkPrice = specialWorkPrice;
		this.itemClassName = itemClassName;
		this.isMutiPart = isMutiPart;
		this.ident = ident;
		this.HRDCert = HRDCert;
		this.GIACert = GIACert;
		this.IGICert = IGICert;
		this.AGSCert = AGSCert;
		this.grian = grian;
		this.certificate=certificate;
		this.colorGrade = colorGrade;
		this.make=make;
		this.factorySite=factorySite;
		this.placeOrigin=placeOrigin;
		this.custServicePhone=custServicePhone;
		this.themeName=themeName;
		this.styleItemCode=styleItemCode;
	}

	public String getOrnaCode() {
		if("原料钻石".equals(this.itemClassName)||"原料钻石".equals(this.ornaName)){
			return renD0Code(this.ornaCode, 3);
		}
		return ornaCode;
	}

	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}

	public String getOrnaBarCode() {
		return ornaBarCode;
	}

	public void setOrnaBarCode(String ornaBarCode) {
		this.ornaBarCode = ornaBarCode;
	}

	public String getOrnaName() {
		return ornaName;
	}

	public void setOrnaName(String ornaName) {
		this.ornaName = ornaName;
	}

	public Double getPosAmount() {
		return posAmount;
	}

	public void setPosAmount(Double posAmount) {
		this.posAmount = posAmount;
	}

	public Double getMainWeight() {
		return mainWeight;
	}

	public void setMainWeight(Double mainWeight) {
		this.mainWeight = mainWeight;
	}

	public Double getPartStoneWeight() {
		return partStoneWeight;
	}

	public void setPartStoneWeight(Double partStoneWeight) {
		this.partStoneWeight = partStoneWeight;
	}

	public Double getAllQty() {
		return allQty;
	}

	public void setAllQty(Double allQty) {
		this.allQty = allQty;
	}

	public Double getSpecialWeight() {
		return specialWeight;
	}

	public void setSpecialWeight(Double specialWeight) {
		this.specialWeight = specialWeight;
	}


	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}


	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getIdentNo() {
		return (isEmpty(this.getIdent()) ? "" : this.getIdent()) + 
			(isEmpty(this.getHRDCert()) ? "" : "HRD:" + this.getHRDCert()) + 
			(isEmpty(this.getGIACert()) ? "" : "GIA:" + this.getGIACert()) + 
			(isEmpty(this.getIGICert()) ? "" : "IGI:" + this.getIGICert()) + 
			(isEmpty(this.getAGSCert()) ? "" : this.getAGSCert());
	}

	public String getHrdGiaNo() {
		return (isEmpty(this.getHRDCert()) ? "" : "HRD:" + this.getHRDCert()) + 
			(isEmpty(this.getGIACert()) ? "" : "GIA:" + this.getGIACert());
	}

	public String getIdentIgiAgsNo() {
		return (isEmpty(this.getIdent()) ? "" : this.getIdent()) +
			(isEmpty(this.getIGICert()) ? "" : "IGI:" + this.getIGICert()) + 
			(isEmpty(this.getAGSCert()) ? "" : this.getAGSCert());
	}


	public String getMainShape() {
		return mainShape;
	}

	public void setMainShape(String mainShape) {
		this.mainShape = mainShape;
	}

	public String getPartStoneContent() {
		return partStoneContent;
	}

	public void setPartStoneContent(String partStoneContent) {
		this.partStoneContent = partStoneContent;
	}


	public String getCleanName() {
		return cleanName;
	}

	public void setCleanName(String cleanName) {
		this.cleanName = cleanName;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFactoryStyle() {
		return factoryStyle;
	}

	public void setFactoryStyle(String factoryStyle) {
		this.factoryStyle = factoryStyle;
	}

	public String getVenderCode() {
		return venderCode;
	}

	public void setVenderCode(String venderCode) {
		this.venderCode = venderCode;
	}

	public Double getStringWorkPrice() {
		return stringWorkPrice;
	}

	public void setStringWorkPrice(Double stringWorkPrice) {
		this.stringWorkPrice = stringWorkPrice;
	}

	public String getSpecialWorkPrice() {
		return specialWorkPrice;
	}

	public void setSpecialWorkPrice(String specialWorkPrice) {
		this.specialWorkPrice = specialWorkPrice;
	}

	public String getItemClassName() {
		return itemClassName;
	}

	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}

	public String getIsMutiPart() {
		return isMutiPart;
	}

	public void setIsMutiPart(String isMutiPart) {
		this.isMutiPart = isMutiPart;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getHRDCert() {
		return HRDCert;
	}

	public void setHRDCert(String HRDCert) {
		this.HRDCert = HRDCert;
	}

	public String getGIACert() {
		return GIACert;
	}

	public void setGIACert(String GIACert) {
		this.GIACert = GIACert;
	}

	public String getIGICert() {
		return IGICert;
	}

	public void setIGICert(String IGICert) {
		this.IGICert = IGICert;
	}

	public String getAGSCert() {
		return AGSCert;
	}

	public void setAGSCert(String AGSCert) {
		this.AGSCert = AGSCert;
	}

	public String getLuckDsc() {
		return luckDsc;
	}

	public void setLuckDsc(String luckDsc) {
		this.luckDsc = luckDsc;
	}

	public String getBornImg() {
		return bornImg;
	}

	public void setBornImg(String bornImg) {
		this.bornImg = bornImg;
	}

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getGrian() {
		return grian;
	}

	public void setGrian(String grian) {
		this.grian = grian;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getColorGrade() {
		return colorGrade;
	}

	public void setColorGrade(String colorGrade) {
		this.colorGrade = colorGrade;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getFactorySite() {
		return factorySite;
	}

	public void setFactorySite(String factorySite) {
		this.factorySite = factorySite;
	}

	public String getPlaceOrigin() {
		return placeOrigin;
	}

	public void setPlaceOrigin(String placeOrigin) {
		this.placeOrigin = placeOrigin;
	}

	public String getCustServicePhone() {
		return custServicePhone;
	}

	public void setCustServicePhone(String custServicePhone) {
		this.custServicePhone = custServicePhone;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getAnalysisArange() {
		return analysisArange;
	}

	public void setAnalysisArange(String analysisArange) {
		this.analysisArange = analysisArange;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getOrnaClassName() {
		return ornaClassName;
	}

	public void setOrnaClassName(String ornaClassName) {
		this.ornaClassName = ornaClassName;
	}	
	
	public static String renD0Code(String _a,int bit){
		String tmp="";
		if(bit<=0)bit=3;
		while(true){
			bit=Math.min(bit,_a.length());
			tmp+=_a.substring(0,bit);
			_a=_a.substring(bit);
			if(_a.length()>0){
				tmp+="-";
			}else{
				break;
			}
		}
		return tmp;
		
	}
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s.trim()))
			return true;
		return false;
	}
	public String getStyleItemCode() {
		return styleItemCode;
	}
	public void setStyleItemCode(String styleItemCode) {
		this.styleItemCode = styleItemCode;
	}
	
}
