package com.jatools.vo.calc;

public class PriceHead {
	private String id;//ID 主键
	private String no;//NO 核价单号
	private String orgId;//ORG_ID 创建组织
	private String handoverNo;//HANDOVER 交接单号
	private String handoverName;//
	private String vender;//VENDER 建议供应商
	private String itemClassId;//ITEM_CLASS_ID 大类
	private String ornaClassId;//ORNA_CLASS_ID 小类
	private String qualityId;//QUALITY_ID 托架材质
	private String colorId;//COLOR_ID 颜色
	private String cleanId;//CLEAN_ID 净度
	private String colorGreadId;//COLORGRADE_ID 色级
	private String dmShapeId;//DM_SHAPE_ID 形状
	private String	cutId; //切工id
	private String isNolineCalc;//IS_NOLINE_CALC 是否无行核价
	private String isOldornaIniv;//IS_OLDORNA_INIV 是否旧品入库
	private String feeTax;//FEE_TAX 工费税率
	private Double feeWidthoutTaxUnitPrice;//FEE_WIDTHOUT_TAX_UNIT_PRICE 工费不含税单价
	private Double feeWidthoutTaxPrice;//FEE_WIDTHOUT_TAX_PRICE 工费不含税价
	private Double feeWidthTaxPrice;//FEE_WIDTH_TAX_PRICE 工费含税价
	private Double feeMarketPrice;//FEE_MARKET_PRICE 工费市场价
	private Double selfMadeTax;//SELF_MADE_TAX 自配费用税率
	private Double  selfMadeWidthoutTaxPrice; //自配费用不含税价
	private Double	selfMadeWidthTaxprice; //自配费用含税价
	private Double	selfMadeMarketPrice; //自配费用市场价
	private String	allNumber; //总粒数
	private String	mainNumber; //主石粒数
	private Double	mainWeight; //主石重量
	private Double	mainWidthoutTaxPrice; //主石不含税价
	private Double	mainWidthTaxPrice; //主石含税价
	private Double	mainMarketPrice; //主石市场价
	private String	secondNumber; //配石粒数
	private Double	secondWeight; //配石重量
	private Double	secondWidthoutTaxPrice; //配石不含税价
	private Double	secondWidthTaxPrice; //配石含税价
	private Double	secondMarketPrice; //配石市场价
	private Double	sbraCoefficient; //托架系数
	private Double	sbraWeight; //托架重量
	private Double	sbraWidthoutTaxPrice; //托架不含税价
	private Double	sbraWidthTaxPrice; //托架含税价
	private Double	sbraMarketPrice; //托架市场价
	private Double	acsWidthoutTaxPrice; //配件不含税价
	private Double	acsWidthTaxPrice; //配件含税价
	private Double	acsMarketPrice; //配件市场价
	private Double	ncoefficient; //倍率系数
	private String	styleId; //款式id
	private Double	allWidthoutTaxPrice; //总不含税成本
	private Double	allWidthTaxPrice; //总含税成本
	private Double	allMarketPrice; //总市场价
	private Double	basicPrice; //基础价
	private Double	taxCoefficient; //税率系数
	private Double	allWeight; //饰品总重
	private	String	secondDsc; //配石内容
	private String	state; //单据状态
	private String	updateDate; //时间戳
	private String	createUserId; //制单人
	private String	createDate; //制单日期
	private String	caclType; //核价类型
	private String	factoryFeeType; //工厂公费方式
	private Double	otherCoefficient; //其他系数
	private String	styleItemId; //款式大类
	private String	styleMiddleId; //款式中类
	private String	styleOrnaclassId; //款式小类
	private String	themeCoefficient; //主题系数
	private Double	priceWithCoefficient; //金含系数后价
	private Double	feeWithCoefficient; //工费含系数后价
	private Double	allmoneyWithCoefficient; //总含系数后价
	private Double	mainPriceWithCoefficient; //主石含系数后价
	private Double	secondPriceWithCoefficient; //配石含系数后价
	private Double	acsPriceWithCoefficient; //配件含系数后价
	private Double	sbraPriceWithCoefficient; //托架含系数后价
	private String	factoryStyleId; //工厂款号id
	private Double	lpFee; //拉/喷沙工费
	private Double	mainZsPrice; //主石证书价格
	private Double	secondZsPrice; //配石证书价格
	private Double	mainStoneXqFee; //主石镶嵌工费总额
	private Double	secondStoneXqDee; //配石镶嵌工费总额
	private String	wlCode; //万隆编码
	private String	outGoodsBillNo; //出货单编号
	private Double	accWithoutTax; //辅料不含税价
	private Double	accWithTax; //辅料含税价
	private Double	accMarketPrice; //辅料市场价
	private Double	accPriceWithCoefficient; //辅料含系数后价
	private String	sbraStyleId; //托架款式id
	private String	sbraColorId; //托架颜色
	private String	isDoubleLabel; //是否双标签
	private String  isConsignment;//IS_CONSIGNMENT	CHAR(1)	Y			是否代销
	private String	labelId; //标签名称id
	private String	ornaBarCode; //条码
	private Double	avgWeight; //主石每粒的平均重量
	private String	verificationFlag; //是否核销
	private Double	specialWorkPrice; //特殊工费
	private Double	newGoldPrice; //金价
	private String	stoneCoeff; //石头系数
	private String	actTradeinFee; //实际以旧换新费用
	private String	tradeinFee; //以旧换新费用
	private String	analysisArangeId; //分析范围
	private String  hlevel;//翡翠档级--数据字典
	private String  inivFlag;//INIV_FLAG业务类型
	private String 	basicPriceCoefficient;//基础系数
	private String identId	; //(100)	y			鉴定证书号
	private String hrdCert	; //(100)	y			hrd国际证书
	private String giaCert	; //(100)	y			gia国际证书
	private String igiCert	; //(100)	y			igi国际证书
	private String agsCert	; //(100)	y			ags国际证书
	private String colorGreadIdxq;//COLOR_ID 颜色
	private String cleanIdxq;//CLEAN_ID 净度
	private String updateBasicPrice;
	private String basicPriceOld;
	
	
	
	public String getBasicPriceOld() {
		return basicPriceOld;
	}
	public void setBasicPriceOld(String basicPriceOld) {
		this.basicPriceOld = basicPriceOld;
	}
	public String getUpdateBasicPrice() {
		return updateBasicPrice;
	}
	public void setUpdateBasicPrice(String updateBasicPrice) {
		this.updateBasicPrice = updateBasicPrice;
	}
	public String getIsConsignment() {
		return isConsignment;
	}
	public void setIsConsignment(String isConsignment) {
		this.isConsignment = isConsignment;
	}
	public String getColorGreadIdxq() {
		return colorGreadIdxq;
	}
	public void setColorGreadIdxq(String colorGreadIdxq) {
		this.colorGreadIdxq = colorGreadIdxq;
	}
	public String getCleanIdxq() {
		return cleanIdxq;
	}
	public void setCleanIdxq(String cleanIdxq) {
		this.cleanIdxq = cleanIdxq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getHandoverNo() {
		return handoverNo;
	}
	public void setHandoverNo(String handoverNo) {
		this.handoverNo = handoverNo;
	}
	public String getHandoverName() {
		return handoverName;
	}
	public void setHandoverName(String handoverName) {
		this.handoverName = handoverName;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getOrnaClassId() {
		return ornaClassId;
	}
	public void setOrnaClassId(String ornaClassId) {
		this.ornaClassId = ornaClassId;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
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
	public String getIsNolineCalc() {
		return isNolineCalc;
	}
	public void setIsNolineCalc(String isNolineCalc) {
		this.isNolineCalc = isNolineCalc;
	}
	public String getIsOldornaIniv() {
		return isOldornaIniv;
	}
	public void setIsOldornaIniv(String isOldornaIniv) {
		this.isOldornaIniv = isOldornaIniv;
	}
	public String getFeeTax() {
		return feeTax;
	}
	public void setFeeTax(String feeTax) {
		this.feeTax = feeTax;
	}
	public Double getFeeWidthoutTaxUnitPrice() {
		return feeWidthoutTaxUnitPrice;
	}
	public void setFeeWidthoutTaxUnitPrice(Double feeWidthoutTaxUnitPrice) {
		this.feeWidthoutTaxUnitPrice = feeWidthoutTaxUnitPrice;
	}
	public Double getFeeWidthoutTaxPrice() {
		return feeWidthoutTaxPrice;
	}
	public void setFeeWidthoutTaxPrice(Double feeWidthoutTaxPrice) {
		this.feeWidthoutTaxPrice = feeWidthoutTaxPrice;
	}
	public Double getFeeWidthTaxPrice() {
		return feeWidthTaxPrice;
	}
	public void setFeeWidthTaxPrice(Double feeWidthTaxPrice) {
		this.feeWidthTaxPrice = feeWidthTaxPrice;
	}
	public Double getFeeMarketPrice() {
		return feeMarketPrice;
	}
	public void setFeeMarketPrice(Double feeMarketPrice) {
		this.feeMarketPrice = feeMarketPrice;
	}
	public Double getSelfMadeTax() {
		return selfMadeTax;
	}
	public void setSelfMadeTax(Double selfMadeTax) {
		this.selfMadeTax = selfMadeTax;
	}
	public Double getSelfMadeWidthoutTaxPrice() {
		return selfMadeWidthoutTaxPrice;
	}
	public void setSelfMadeWidthoutTaxPrice(Double selfMadeWidthoutTaxPrice) {
		this.selfMadeWidthoutTaxPrice = selfMadeWidthoutTaxPrice;
	}
	public Double getSelfMadeWidthTaxprice() {
		return selfMadeWidthTaxprice;
	}
	public void setSelfMadeWidthTaxprice(Double selfMadeWidthTaxprice) {
		this.selfMadeWidthTaxprice = selfMadeWidthTaxprice;
	}
	public Double getSelfMadeMarketPrice() {
		return selfMadeMarketPrice;
	}
	public void setSelfMadeMarketPrice(Double selfMadeMarketPrice) {
		this.selfMadeMarketPrice = selfMadeMarketPrice;
	}
	public String getAllNumber() {
		return allNumber;
	}
	public void setAllNumber(String allNumber) {
		this.allNumber = allNumber;
	}
	public String getMainNumber() {
		return mainNumber;
	}
	public void setMainNumber(String mainNumber) {
		this.mainNumber = mainNumber;
	}
	public Double getMainWeight() {
		return mainWeight;
	}
	public void setMainWeight(Double mainWeight) {
		this.mainWeight = mainWeight;
	}
	public Double getMainWidthoutTaxPrice() {
		return mainWidthoutTaxPrice;
	}
	public void setMainWidthoutTaxPrice(Double mainWidthoutTaxPrice) {
		this.mainWidthoutTaxPrice = mainWidthoutTaxPrice;
	}
	public Double getMainWidthTaxPrice() {
		return mainWidthTaxPrice;
	}
	public void setMainWidthTaxPrice(Double mainWidthTaxPrice) {
		this.mainWidthTaxPrice = mainWidthTaxPrice;
	}
	public Double getMainMarketPrice() {
		return mainMarketPrice;
	}
	public void setMainMarketPrice(Double mainMarketPrice) {
		this.mainMarketPrice = mainMarketPrice;
	}
	public String getSecondNumber() {
		return secondNumber;
	}
	public void setSecondNumber(String secondNumber) {
		this.secondNumber = secondNumber;
	}
	public Double getSecondWeight() {
		return secondWeight;
	}
	public void setSecondWeight(Double secondWeight) {
		this.secondWeight = secondWeight;
	}
	public Double getSecondWidthoutTaxPrice() {
		return secondWidthoutTaxPrice;
	}
	public void setSecondWidthoutTaxPrice(Double secondWidthoutTaxPrice) {
		this.secondWidthoutTaxPrice = secondWidthoutTaxPrice;
	}
	public Double getSecondWidthTaxPrice() {
		return secondWidthTaxPrice;
	}
	public void setSecondWidthTaxPrice(Double secondWidthTaxPrice) {
		this.secondWidthTaxPrice = secondWidthTaxPrice;
	}
	public Double getSecondMarketPrice() {
		return secondMarketPrice;
	}
	public void setSecondMarketPrice(Double secondMarketPrice) {
		this.secondMarketPrice = secondMarketPrice;
	}
	public Double getSbraCoefficient() {
		return sbraCoefficient;
	}
	public void setSbraCoefficient(Double sbraCoefficient) {
		this.sbraCoefficient = sbraCoefficient;
	}
	public Double getSbraWeight() {
		return sbraWeight;
	}
	public void setSbraWeight(Double sbraWeight) {
		this.sbraWeight = sbraWeight;
	}
	public Double getSbraWidthoutTaxPrice() {
		return sbraWidthoutTaxPrice;
	}
	public void setSbraWidthoutTaxPrice(Double sbraWidthoutTaxPrice) {
		this.sbraWidthoutTaxPrice = sbraWidthoutTaxPrice;
	}
	public Double getSbraWidthTaxPrice() {
		return sbraWidthTaxPrice;
	}
	public void setSbraWidthTaxPrice(Double sbraWidthTaxPrice) {
		this.sbraWidthTaxPrice = sbraWidthTaxPrice;
	}
	public Double getSbraMarketPrice() {
		return sbraMarketPrice;
	}
	public void setSbraMarketPrice(Double sbraMarketPrice) {
		this.sbraMarketPrice = sbraMarketPrice;
	}
	public Double getAcsWidthoutTaxPrice() {
		return acsWidthoutTaxPrice;
	}
	public void setAcsWidthoutTaxPrice(Double acsWidthoutTaxPrice) {
		this.acsWidthoutTaxPrice = acsWidthoutTaxPrice;
	}
	public Double getAcsWidthTaxPrice() {
		return acsWidthTaxPrice;
	}
	public void setAcsWidthTaxPrice(Double acsWidthTaxPrice) {
		this.acsWidthTaxPrice = acsWidthTaxPrice;
	}
	public Double getAcsMarketPrice() {
		return acsMarketPrice;
	}
	public void setAcsMarketPrice(Double acsMarketPrice) {
		this.acsMarketPrice = acsMarketPrice;
	}
	public Double getNcoefficient() {
		return ncoefficient;
	}
	public void setNcoefficient(Double ncoefficient) {
		this.ncoefficient = ncoefficient;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public Double getAllWidthoutTaxPrice() {
		return allWidthoutTaxPrice;
	}
	public void setAllWidthoutTaxPrice(Double allWidthoutTaxPrice) {
		this.allWidthoutTaxPrice = allWidthoutTaxPrice;
	}
	public Double getAllWidthTaxPrice() {
		return allWidthTaxPrice;
	}
	public void setAllWidthTaxPrice(Double allWidthTaxPrice) {
		this.allWidthTaxPrice = allWidthTaxPrice;
	}
	public Double getAllMarketPrice() {
		return allMarketPrice;
	}
	public void setAllMarketPrice(Double allMarketPrice) {
		this.allMarketPrice = allMarketPrice;
	}
	public Double getBasicPrice() {
		return basicPrice;
	}
	public void setBasicPrice(Double basicPrice) {
		this.basicPrice = basicPrice;
	}
	public Double getTaxCoefficient() {
		return taxCoefficient;
	}
	public void setTaxCoefficient(Double taxCoefficient) {
		this.taxCoefficient = taxCoefficient;
	}
	public Double getAllWeight() {
		return allWeight;
	}
	public void setAllWeight(Double allWeight) {
		this.allWeight = allWeight;
	}
	public String getSecondDsc() {
		return secondDsc;
	}
	public void setSecondDsc(String secondDsc) {
		this.secondDsc = secondDsc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getCaclType() {
		return caclType;
	}
	public void setCaclType(String caclType) {
		this.caclType = caclType;
	}
	public String getFactoryFeeType() {
		return factoryFeeType;
	}
	public void setFactoryFeeType(String factoryFeeType) {
		this.factoryFeeType = factoryFeeType;
	}
	public Double getOtherCoefficient() {
		return otherCoefficient;
	}
	public void setOtherCoefficient(Double otherCoefficient) {
		this.otherCoefficient = otherCoefficient;
	}
	public String getStyleItemId() {
		return styleItemId;
	}
	public void setStyleItemId(String styleItemId) {
		this.styleItemId = styleItemId;
	}
	public String getStyleMiddleId() {
		return styleMiddleId;
	}
	public void setStyleMiddleId(String styleMiddleId) {
		this.styleMiddleId = styleMiddleId;
	}
	public String getStyleOrnaclassId() {
		return styleOrnaclassId;
	}
	public void setStyleOrnaclassId(String styleOrnaclassId) {
		this.styleOrnaclassId = styleOrnaclassId;
	}
	public String getThemeCoefficient() {
		return themeCoefficient;
	}
	public void setThemeCoefficient(String themeCoefficient) {
		this.themeCoefficient = themeCoefficient;
	}
	public Double getPriceWithCoefficient() {
		return priceWithCoefficient;
	}
	public void setPriceWithCoefficient(Double priceWithCoefficient) {
		this.priceWithCoefficient = priceWithCoefficient;
	}
	public Double getFeeWithCoefficient() {
		return feeWithCoefficient;
	}
	public void setFeeWithCoefficient(Double feeWithCoefficient) {
		this.feeWithCoefficient = feeWithCoefficient;
	}
	public Double getAllmoneyWithCoefficient() {
		return allmoneyWithCoefficient;
	}
	public void setAllmoneyWithCoefficient(Double allmoneyWithCoefficient) {
		this.allmoneyWithCoefficient = allmoneyWithCoefficient;
	}
	public Double getMainPriceWithCoefficient() {
		return mainPriceWithCoefficient;
	}
	public void setMainPriceWithCoefficient(Double mainPriceWithCoefficient) {
		this.mainPriceWithCoefficient = mainPriceWithCoefficient;
	}
	public Double getSecondPriceWithCoefficient() {
		return secondPriceWithCoefficient;
	}
	public void setSecondPriceWithCoefficient(Double secondPriceWithCoefficient) {
		this.secondPriceWithCoefficient = secondPriceWithCoefficient;
	}
	public Double getAcsPriceWithCoefficient() {
		return acsPriceWithCoefficient;
	}
	public void setAcsPriceWithCoefficient(Double acsPriceWithCoefficient) {
		this.acsPriceWithCoefficient = acsPriceWithCoefficient;
	}
	public Double getSbraPriceWithCoefficient() {
		return sbraPriceWithCoefficient;
	}
	public void setSbraPriceWithCoefficient(Double sbraPriceWithCoefficient) {
		this.sbraPriceWithCoefficient = sbraPriceWithCoefficient;
	}
	public String getFactoryStyleId() {
		return factoryStyleId;
	}
	public void setFactoryStyleId(String factoryStyleId) {
		this.factoryStyleId = factoryStyleId;
	}
	public Double getLpFee() {
		return lpFee;
	}
	public void setLpFee(Double lpFee) {
		this.lpFee = lpFee;
	}
	public Double getMainZsPrice() {
		return mainZsPrice;
	}
	public void setMainZsPrice(Double mainZsPrice) {
		this.mainZsPrice = mainZsPrice;
	}
	public Double getSecondZsPrice() {
		return secondZsPrice;
	}
	public void setSecondZsPrice(Double secondZsPrice) {
		this.secondZsPrice = secondZsPrice;
	}
	public Double getMainStoneXqFee() {
		return mainStoneXqFee;
	}
	public void setMainStoneXqFee(Double mainStoneXqFee) {
		this.mainStoneXqFee = mainStoneXqFee;
	}
	public Double getSecondStoneXqDee() {
		return secondStoneXqDee;
	}
	public void setSecondStoneXqDee(Double secondStoneXqDee) {
		this.secondStoneXqDee = secondStoneXqDee;
	}
	public String getWlCode() {
		return wlCode;
	}
	public void setWlCode(String wlCode) {
		this.wlCode = wlCode;
	}
	public String getOutGoodsBillNo() {
		return outGoodsBillNo;
	}
	public void setOutGoodsBillNo(String outGoodsBillNo) {
		this.outGoodsBillNo = outGoodsBillNo;
	}
	public Double getAccWithoutTax() {
		return accWithoutTax;
	}
	public void setAccWithoutTax(Double accWithoutTax) {
		this.accWithoutTax = accWithoutTax;
	}
	public Double getAccWithTax() {
		return accWithTax;
	}
	public void setAccWithTax(Double accWithTax) {
		this.accWithTax = accWithTax;
	}
	public Double getAccMarketPrice() {
		return accMarketPrice;
	}
	public void setAccMarketPrice(Double accMarketPrice) {
		this.accMarketPrice = accMarketPrice;
	}
	public Double getAccPriceWithCoefficient() {
		return accPriceWithCoefficient;
	}
	public void setAccPriceWithCoefficient(Double accPriceWithCoefficient) {
		this.accPriceWithCoefficient = accPriceWithCoefficient;
	}
	public String getSbraStyleId() {
		return sbraStyleId;
	}
	public void setSbraStyleId(String sbraStyleId) {
		this.sbraStyleId = sbraStyleId;
	}
	public String getSbraColorId() {
		return sbraColorId;
	}
	public void setSbraColorId(String sbraColorId) {
		this.sbraColorId = sbraColorId;
	}
	public String getIsDoubleLabel() {
		return isDoubleLabel;
	}
	public void setIsDoubleLabel(String isDoubleLabel) {
		this.isDoubleLabel = isDoubleLabel;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getOrnaBarCode() {
		return ornaBarCode;
	}
	public void setOrnaBarCode(String ornaBarCode) {
		this.ornaBarCode = ornaBarCode;
	}
	public Double getAvgWeight() {
		return avgWeight;
	}
	public void setAvgWeight(Double avgWeight) {
		this.avgWeight = avgWeight;
	}
	public String getVerificationFlag() {
		return verificationFlag;
	}
	public void setVerificationFlag(String verificationFlag) {
		this.verificationFlag = verificationFlag;
	}
	public Double getSpecialWorkPrice() {
		return specialWorkPrice;
	}
	public void setSpecialWorkPrice(Double specialWorkPrice) {
		this.specialWorkPrice = specialWorkPrice;
	}
	public Double getNewGoldPrice() {
		return newGoldPrice;
	}
	public void setNewGoldPrice(Double newGoldPrice) {
		this.newGoldPrice = newGoldPrice;
	}
	public String getStoneCoeff() {
		return stoneCoeff;
	}
	public void setStoneCoeff(String stoneCoeff) {
		this.stoneCoeff = stoneCoeff;
	}
	public String getActTradeinFee() {
		return actTradeinFee;
	}
	public void setActTradeinFee(String actTradeinFee) {
		this.actTradeinFee = actTradeinFee;
	}
	public String getTradeinFee() {
		return tradeinFee;
	}
	public void setTradeinFee(String tradeinFee) {
		this.tradeinFee = tradeinFee;
	}
	public String getAnalysisArangeId() {
		return analysisArangeId;
	}
	public void setAnalysisArangeId(String analysisArangeId) {
		this.analysisArangeId = analysisArangeId;
	}
	public String getHlevel() {
		return hlevel;
	}
	public void setHlevel(String hlevel) {
		this.hlevel = hlevel;
	}
	public String getInivFlag() {
		return inivFlag;
	}
	public void setInivFlag(String inivFlag) {
		this.inivFlag = inivFlag;
	}
	public String getBasicPriceCoefficient() {
		return basicPriceCoefficient;
	}
	public void setBasicPriceCoefficient(String basicPriceCoefficient) {
		this.basicPriceCoefficient = basicPriceCoefficient;
	}
	public String getIdentId() {
		return identId;
	}
	public void setIdentId(String identId) {
		this.identId = identId;
	}
	public String getHrdCert() {
		return hrdCert;
	}
	public void setHrdCert(String hrdCert) {
		this.hrdCert = hrdCert;
	}
	public String getGiaCert() {
		return giaCert;
	}
	public void setGiaCert(String giaCert) {
		this.giaCert = giaCert;
	}
	public String getIgiCert() {
		return igiCert;
	}
	public void setIgiCert(String igiCert) {
		this.igiCert = igiCert;
	}
	public String getAgsCert() {
		return agsCert;
	}
	public void setAgsCert(String agsCert) {
		this.agsCert = agsCert;
	}
	
	
}
