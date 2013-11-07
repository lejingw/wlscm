package com.jatools.vo.calc;

public class PriceStoneLine {
	private String id;//ID 主键
	private String headId;//HEAD_ID 主表id
	private String isMainStone;//IS_MAIN_STONE 是否主石
	private String luodanCode;//LUODAN_CODE 裸石编码
	private String stoneNums;//STONE_NUMS 粒数
	private Double stoneWeight;//STONE_WEIGHT 石头重量
	private Double withotTaxPrice;//WITHOUT_TAX_PRICE 不含税单价
	private Double withoutTaxAllMoney;//WITHOUT_TAX_ALLMONEYDouble额
	private Double stoneTax;//STONE_TAX税率
	private Double withTaxAllMoney;//WITH_TAX_ALLMONEY 含税总金额
	private Double marketPrice;//MARKET_PRICE 市场价单价
	private Double marketAllMoney;//MARKET_ALLMONEY 市场价总金额
	private String updateDate;//UPDATEDATE 时间戳
	private String createUserId;//CREATEUSERID 制单人
	private String createDate;//CREATEDATE 制单日期
	private String stoneType;//STONE_TYPE 石头类型
	private String stoneSpec;//STONE_SPEC 石头规格
	private Double stoneCoefficient;//STONE_COEFFICIENT 石头系数
	private Double priceWithCoefficient;//PRICE_WITH_COEFFICIENT 系数后价
	private String colorId;//COLOR_ID 颜色
	private String cleanId;//CLEAN_ID 净度
	private String colorGreadId;//GRADE_ID 色级
	private String dmShapeId;//DM_SHAPE_ID 形状
	private String cutId;//CUT_ID 切工ID
	private String isMainInfo;//IS_MAIN_INFO 是否主信息
	private String stoneThemeCoefficient;//STONE_THEME_COEFFICIENT 主配石主题系数
	private String feeType;//FEE_TYPE 计费方式
	private Double certificatePrice;//CERTIFICATE_PRICE 证书价格
	private String factoryNuleCode;//FACTORY_NULE_CODE 工厂裸石编码
	private String privateType;//PRIVATE_TYPE 是否自有料(料属性)0否 1是  2空
	private String stoneItemClassId;
	
	public String getStoneItemClassId() {
		return stoneItemClassId;
	}
	public void setStoneItemClassId(String stoneItemClassId) {
		this.stoneItemClassId = stoneItemClassId;
	}
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
	public String getIsMainStone() {
		return isMainStone;
	}
	public void setIsMainStone(String isMainStone) {
		this.isMainStone = isMainStone;
	}
	public String getLuodanCode() {
		return luodanCode;
	}
	public void setLuodanCode(String luodanCode) {
		this.luodanCode = luodanCode;
	}
	public String getStoneNums() {
		return stoneNums;
	}
	public void setStoneNums(String stoneNums) {
		this.stoneNums = stoneNums;
	}
	public Double getStoneWeight() {
		return stoneWeight;
	}
	public void setStoneWeight(Double stoneWeight) {
		this.stoneWeight = stoneWeight;
	}
	public Double getWithotTaxPrice() {
		return withotTaxPrice;
	}
	public void setWithotTaxPrice(Double withotTaxPrice) {
		this.withotTaxPrice = withotTaxPrice;
	}
	public Double getWithoutTaxAllMoney() {
		return withoutTaxAllMoney;
	}
	public void setWithoutTaxAllMoney(Double withoutTaxAllMoney) {
		this.withoutTaxAllMoney = withoutTaxAllMoney;
	}
	public Double getStoneTax() {
		return stoneTax;
	}
	public void setStoneTax(Double stoneTax) {
		this.stoneTax = stoneTax;
	}
	public Double getWithTaxAllMoney() {
		return withTaxAllMoney;
	}
	public void setWithTaxAllMoney(Double withTaxAllMoney) {
		this.withTaxAllMoney = withTaxAllMoney;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getMarketAllMoney() {
		return marketAllMoney;
	}
	public void setMarketAllMoney(Double marketAllMoney) {
		this.marketAllMoney = marketAllMoney;
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
	public String getStoneType() {
		return stoneType;
	}
	public void setStoneType(String stoneType) {
		this.stoneType = stoneType;
	}
	public String getStoneSpec() {
		return stoneSpec;
	}
	public void setStoneSpec(String stoneSpec) {
		this.stoneSpec = stoneSpec;
	}
	public Double getStoneCoefficient() {
		return stoneCoefficient;
	}
	public void setStoneCoefficient(Double stoneCoefficient) {
		this.stoneCoefficient = stoneCoefficient;
	}
	public Double getPriceWithCoefficient() {
		return priceWithCoefficient;
	}
	public void setPriceWithCoefficient(Double priceWithCoefficient) {
		this.priceWithCoefficient = priceWithCoefficient;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getCleanId() {
		return cleanId;
	}
	public void setCleanId(String cleanId) {
		this.cleanId = cleanId;
	}
	public String getColorGreadId() {
		return colorGreadId;
	}
	public void setColorGreadId(String colorGreadId) {
		this.colorGreadId = colorGreadId;
	}
	public String getDmShapeId() {
		return dmShapeId;
	}
	public void setDmShapeId(String dmShapeId) {
		this.dmShapeId = dmShapeId;
	}
	public String getCutId() {
		return cutId;
	}
	public void setCutId(String cutId) {
		this.cutId = cutId;
	}
	public String getIsMainInfo() {
		return isMainInfo;
	}
	public void setIsMainInfo(String isMainInfo) {
		this.isMainInfo = isMainInfo;
	}
	public String getStoneThemeCoefficient() {
		return stoneThemeCoefficient;
	}
	public void setStoneThemeCoefficient(String stoneThemeCoefficient) {
		this.stoneThemeCoefficient = stoneThemeCoefficient;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Double getCertificatePrice() {
		return certificatePrice;
	}
	public void setCertificatePrice(Double certificatePrice) {
		this.certificatePrice = certificatePrice;
	}
	public String getFactoryNuleCode() {
		return factoryNuleCode;
	}
	public void setFactoryNuleCode(String factoryNuleCode) {
		this.factoryNuleCode = factoryNuleCode;
	}
	public String getPrivateType() {
		return privateType;
	}
	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}
	
	
	private String oldOrnaCode ;// 原饰品编码
	private String oldStoneNum;// 原核价数量

	public String getOldOrnaCode() {
		return oldOrnaCode;
	}
	public void setOldOrnaCode(String oldOrnaCode) {
		this.oldOrnaCode = oldOrnaCode;
	}
	public String getOldStoneNum() {
		return oldStoneNum;
	}
	public void setOldStoneNum(String oldStoneNum) {
		this.oldStoneNum = oldStoneNum;
	}
	
}
