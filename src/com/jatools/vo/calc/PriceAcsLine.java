package com.jatools.vo.calc;

public class PriceAcsLine {
	private String id;//ID 主键
	private String headId;//HEAD_ID 主表id
	private String acsType;//ACS_TYPE 配件类型
	private Double acsWeight;//ACS_WEIGHT 重量
	private Double acsQuantity;//ACS_QUANTITY 数量
	private Double acsWithoutTaxPrice;//ACS_WITHOUT_TAX_PRICE 配件不含税单价
	private Double acsWithoutTaxMoney;//ACS_WITHOUT_TAX_MONEY 配件不含税金额
	private Double acsTax;//ACS_TAX 税率
	private Double acsWithTaxMoney;//ACS_WITH_TAX_MONEY 配件含税金额
	private Double acsMarketAllMoney;//ACS_MARKET_ALLMONEY 配件市场总金额
	private String updateDate;//UPDATEDATE 时间戳
	private String createUserId;//CREATEUSERID 制单人
	private String createDate;//CREATEDATE 制单日期
	private String otherCoefficient;//OTHER_COEFFICIENT 其它系数
	private Double priceWithCoefficient;//PRICE_WITH_COEFFICIENT 系数后价
	private String styleId;//STYLE_ID 款式ID
	private String qualityId;//QUALITY_ID 成色
	private String acsCode;//ACS_CODE 配件编码
	private String privateType;//PRIVATE_TYPE 是否自有料
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
	public String getAcsType() {
		return acsType;
	}
	public void setAcsType(String acsType) {
		this.acsType = acsType;
	}
	public Double getAcsWeight() {
		return acsWeight;
	}
	public void setAcsWeight(Double acsWeight) {
		this.acsWeight = acsWeight;
	}
	public Double getAcsQuantity() {
		return acsQuantity;
	}
	public void setAcsQuantity(Double acsQuantity) {
		this.acsQuantity = acsQuantity;
	}
	public Double getAcsWithoutTaxPrice() {
		return acsWithoutTaxPrice;
	}
	public void setAcsWithoutTaxPrice(Double acsWithoutTaxPrice) {
		this.acsWithoutTaxPrice = acsWithoutTaxPrice;
	}
	public Double getAcsWithoutTaxMoney() {
		return acsWithoutTaxMoney;
	}
	public void setAcsWithoutTaxMoney(Double acsWithoutTaxMoney) {
		this.acsWithoutTaxMoney = acsWithoutTaxMoney;
	}
	public Double getAcsTax() {
		return acsTax;
	}
	public void setAcsTax(Double acsTax) {
		this.acsTax = acsTax;
	}
	public Double getAcsWithTaxMoney() {
		return acsWithTaxMoney;
	}
	public void setAcsWithTaxMoney(Double acsWithTaxMoney) {
		this.acsWithTaxMoney = acsWithTaxMoney;
	}
	public Double getAcsMarketAllMoney() {
		return acsMarketAllMoney;
	}
	public void setAcsMarketAllMoney(Double acsMarketAllMoney) {
		this.acsMarketAllMoney = acsMarketAllMoney;
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
	public String getOtherCoefficient() {
		return otherCoefficient;
	}
	public void setOtherCoefficient(String otherCoefficient) {
		this.otherCoefficient = otherCoefficient;
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
	public String getAcsCode() {
		return acsCode;
	}
	public void setAcsCode(String acsCode) {
		this.acsCode = acsCode;
	}
	public String getPrivateType() {
		return privateType;
	}
	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}
	
	
}
