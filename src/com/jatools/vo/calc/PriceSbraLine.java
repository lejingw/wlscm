package com.jatools.vo.calc;

public class PriceSbraLine {
	private String id;//ID 主键
	private String headId;//HEAD_ID	NUMBER(8) 主表id
	private Double sbraWeight;//SBRA_WEIGHT	NUMBER(24,6) 托架重量
	private Double sbraWithoutTaxPrice;//SBRA_WITHOUT_TAX_PRICE	NUMBER(24,6) 托架不含税单价
	private Double sbraWithoutTaxmoney;//SBRA_WITHOUT_TAX_MONEY	NUMBER(24,6) 托架不含税金额
	private Double sbraTax;//SBRA_TAX	NUMBER(24,6)	Y			税率
	private Double sbraWithTaxMoney;//SBRA_WITH_TAX_MONEY	NUMBER(24,6) 托架含税金额
	private Double sbraMarketPrice;//SBRA_MARKET_PRICE	NUMBER(24,6) 托架市场价单价
	private Double sbraMarketAllMoney;//SBRA_MARKET_ALLMONEY 托架市场价总金额
	private String isInlayHalfProduct;//IS_INLAY_HALF_PRODUCT 是否镶嵌半成品
	private String halfProductPrice;//HALF_PRODUCT_PRICE 半成品成本
	private String updateDate;//UPDATEDATE	CHAR(19) 时间戳
	private String createUserId;//CREATEUSERID	NUMBER(8) 制单人
	private String createDate;//CREATEDATE 制单日期
	private Double sbraCoefficient;//SBRA_COEFFICIENT 托架系数
	private Double priceWithCoefficient;//PRICE_WITH_COEFFICIENT 含系数后价
	private String styleId;//STYLE_ID 款式ID
	private String qualityId;//QUALITY_ID 托架材质
	private String materCode;//MATER_CODE 原料编码
	private String privateType;//PRIVATE_TYPE 是否自有料
	private Double goldLostPercent;//GOLD_LOST_PERCENT 金耗率
	private Double goldLostWeight;//GOLD_LOST_WEIGHT 金耗重
	private Double goldAllweight;//GOLD_ALL_WEIGHT 含耗重
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public Double getSbraWeight() {
		return sbraWeight;
	}
	public void setSbraWeight(Double sbraWeight) {
		this.sbraWeight = sbraWeight;
	}
	public Double getSbraWithoutTaxPrice() {
		return sbraWithoutTaxPrice;
	}
	public void setSbraWithoutTaxPrice(Double sbraWithoutTaxPrice) {
		this.sbraWithoutTaxPrice = sbraWithoutTaxPrice;
	}
	public Double getSbraWithoutTaxmoney() {
		return sbraWithoutTaxmoney;
	}
	public void setSbraWithoutTaxmoney(Double sbraWithoutTaxmoney) {
		this.sbraWithoutTaxmoney = sbraWithoutTaxmoney;
	}
	public Double getSbraTax() {
		return sbraTax;
	}
	public void setSbraTax(Double sbraTax) {
		this.sbraTax = sbraTax;
	}
	public Double getSbraWithTaxMoney() {
		return sbraWithTaxMoney;
	}
	public void setSbraWithTaxMoney(Double sbraWithTaxMoney) {
		this.sbraWithTaxMoney = sbraWithTaxMoney;
	}
	public Double getSbraMarketPrice() {
		return sbraMarketPrice;
	}
	public void setSbraMarketPrice(Double sbraMarketPrice) {
		this.sbraMarketPrice = sbraMarketPrice;
	}
	public Double getSbraMarketAllMoney() {
		return sbraMarketAllMoney;
	}
	public void setSbraMarketAllMoney(Double sbraMarketAllMoney) {
		this.sbraMarketAllMoney = sbraMarketAllMoney;
	}
	public String getIsInlayHalfProduct() {
		return isInlayHalfProduct;
	}
	public void setIsInlayHalfProduct(String isInlayHalfProduct) {
		this.isInlayHalfProduct = isInlayHalfProduct;
	}
	public String getHalfProductPrice() {
		return halfProductPrice;
	}
	public void setHalfProductPrice(String halfProductPrice) {
		this.halfProductPrice = halfProductPrice;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Double getSbraCoefficient() {
		return sbraCoefficient;
	}
	public void setSbraCoefficient(Double sbraCoefficient) {
		this.sbraCoefficient = sbraCoefficient;
	}
	public Double getPriceWithCoefficient() {
		return priceWithCoefficient;
	}
	public void setPriceWithCoefficient(Double priceWithCoefficient) {
		this.priceWithCoefficient = priceWithCoefficient;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getMaterCode() {
		return materCode;
	}
	public void setMaterCode(String materCode) {
		this.materCode = materCode;
	}
	public String getPrivateType() {
		return privateType;
	}
	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}
	public Double getGoldLostPercent() {
		return goldLostPercent;
	}
	public void setGoldLostPercent(Double goldLostPercent) {
		this.goldLostPercent = goldLostPercent;
	}
	public Double getGoldLostWeight() {
		return goldLostWeight;
	}
	public void setGoldLostWeight(Double goldLostWeight) {
		this.goldLostWeight = goldLostWeight;
	}
	public Double getGoldAllweight() {
		return goldAllweight;
	}
	public void setGoldAllweight(Double goldAllweight) {
		this.goldAllweight = goldAllweight;
	}
	
	
}
