package com.jatools.vo.stock;

public class MaterIniv {

	private String id	; //(8)	n			主键
	private String no	; //(20)	y			入库单号
	private String allowInivType	; //(1)	y			允许入库类型
	private String stockId	; //(8)	y			仓库id
	private String inivOrg	; //(8)	y			入库组织
	private String inivDirection	; //(1)	y			入库方向
	private String inivDate	; //(19)	y			入库日期
	private String transceiverType	; //(8)	y			收发类别 
	private String stockOrg	; //(8)	y			库存组织
	private String preAllotOrg	; //(8)	y			预分配组织
	private String deptId	; //(8)	y			部门
	private String salesman	; //(8)	y			业务员
	private String notes	; //(500)	y			备注
	private String handoverBillId	; //(8)	y			交接单号
	private String venderId	; //(8)	y			供应商
	private String articleTypeId	; //(8)	y			商品类别
	private String itemClassId	; //(8)	y			大类
	private String ornaClassId	; //(8)	y			小类
	private String analysisArangeId	; //(8)	y			分析范围
	private String styleItemId	; //(8)	y			款式大类
	private String styleMiddleId	; //(8)	y			款式中类
	private String styleId	; //(8)	y			款式
	private String styleName;
	private String unitId	; //(8)	y			计量单位
	private String quantity	; //(24,6)	y			数量
	private String ornaName	; //(50)	y			饰品名称
	private String ornaCode	; //(20)	y			饰品编码
	private String sizeId	; //(8)	y			尺寸
	private String isConsign	; //(1)	y			是否代销 0 否 1是
	private String isCustom	; //(1)	y			是否定做 0 否 1是
	private Double costPrice	; //(24,6)	y			成本单价
	private Double totalCost	; //(24,6)	y			总成本
	private String smallLabelType	; //(10)	y			小标签类型
	private String decimalNum	; //(1)	y			小数点保留位数
	private String trackIame	; //(20)	y			跟踪名称
	private String identId	; //(100)	y			鉴定证书号
	private String hrdCert	; //(100)	y			hrd国际证书
	private String giaCert	; //(100)	y			gia国际证书
	private String igiCert	; //(100)	y			igi国际证书
	private String agsCert	; //(100)	y			ags国际证书
	private String totalNum	; //(16)	y			总粒数
	private String qualityId	; //(8)	y			托架材质id
	private String basicPrice	; //(24,6)	y			基础价
	private String groups	; //(4)	y			柜组
	private String posAmount	; //(24,6)	y			网点金额
	private Double specialWeight	; //(24,6)	y			特殊重量
	private Double weight	; //(24,6)	y			重量
	private String summaryId	; //(8)	y			常用摘要id
	private String nuclearBillId	; //(8)	y			核价单号
	private String mainModification	; //(24,6)	y			主石修适度
	private String mainShapeId	; //(8)	y			主石形状id
	private String mainColorGradeId	; //(8)	y			主石色级id
	private String cleanId	; //(8)	y			钻石净度id
	private String colorId	; //(8)	y			钻石颜色id
	private String cutId	; //(8)	y			切工id
	private String bracketcolorId	; //(8)	y			托架颜色id
	private String wearId	; //(8)	y			佩戴对象
	private String priceAttrGroup	; //(12)	y			价格属性组
	private String qltyId	; //(8)	y			品质等级id
	private String specialWorkPrice	; //(24,6)	y			特殊工费
	private String stringWorkPrice	; //(24,6)	y			红绳工费
	private Double cutWideScale	; //(24,6)	y			切工台宽比
	private Double cutDeepScale	; //(24,6)	y			切工亭深比
	private String symmetryId	; //(8)	y			对称性id
	private String polishineId	; //(8)	y			抛光id
	private String fluorescenceId	; //(8)	y			荧光id
	private String waistlineId	; //(8)	y			腰围id
	private String vertexId	; //(8)	y			底尖id
	private String factoryFee	; //(24,6)	y			工厂工费
	private Double factoryFeePrice	; //(24,6)	y			工厂工费单价
	private Double mainWeight	; //(24,6)	y			主石重量
	private String mainStoneNum	; //(18)	y			主石粒数
	private Double partStoneWeight	; //(24,6)	y			配石重量
	private String partStoneContent	; //(100)	y			配石内容
	private String partStoneNum	; //(18)	y			配石粒数
	private String zodiac	; //(12)	y			生肖
	private Double allQty	; //(24,6)	y			总重量
	private Double avgNum	; //(24,6)	y			平均粒数
	private String isUnder_3fen	; //(1)	y	0		是否3分以下
	private String luckyDsc	; //(20)	y			吉祥语标签
	private String inlayno	; //(10)	y			镶嵌规格 
	private String updatedate	; //(19)	y			时间戳
	private String createuserid	; //(8)	y			制单人
	private String createdate	; //(19)	y			制单日期
	private String factoryStyleId	; //(8)	y			工厂款号id
	private String approvaluserid	; //(8)	y			审批人
	private String approvaldate	; //(19)	y			审批日期
	private String srcBillNo	; //(20)	y			来源单据编号
	private String srcBillId	; //(8)	y			来源单据id
	private String srcBillCode;
	private String state	; //(8)	y			单据状态
	private String consignmentType	; //(1)	y			代销类型
	private String factoryFeeType	; //(24,6)	y			工厂公费方式
	private String isOldOtc	; //(1)	y			是否旧品上柜
	private String partContent	; //(100)	y			配件内容
	private String luckyWord	; //(20)	y			吉祥语
	private String isTearStone	; //(1)	y			是否拆石
	private String styleType	; //(3)	y			款式类别
	private String isMutiPart	; //(1)	y			是否多粒 0 否 1是
	private String styleOrnaId	; //(8)	y			款式小类
	private String issuchinda	; //(1)	y	0		是否高档素金
	private String materialId	; //(8)	y			物料id
	private String themeCoefficient	; //(24,6)	y			主题系数
	private String ncoefficient	; //(24,6)	y			总系数段
	private String slotId	; //(8)	y			货位id
	private String calcpriceNo	; //(20)	y			核价单号
	private String ornaBarcode	; //(20)	y			饰品条码
	private String customizeNo	; //(20)	y			定做单单号
	private String bracketStyleId	; //(8)	y			托架款式id
	private String isDblLabel	; //(1)	y			是否双标签段
	private String tags	; //(1)	y			暂估标志
	private String privateType	; //(1)	y			是否自有料(料属性)0否 1是  2空
	private String lackMaterial	; //(1)	y			是否欠料
	private String lackMaterialNum	; //(8)	y			欠料数量
	private String lackMaterialWeight	; //(24,6)	y			欠料重量
	private String verificationFlag	; //(1)	y			是否核销
	private String inivFlag	; //(1)	y			入库标志 1采购;2委外;3料转换;4退料5料提纯6拆石
	private String oldOrnaCode	; //(20)	y			原饰品编码
	private String handoverName;//
	private String costUnit;
	private String lockFlag;
	private String colorGradeIdXq;
	private String cleanIdXq;
	private String inivCost;
	private String factoryOrnaCode;//FACTORY_ORNA_CODE	VARCHAR2(20)	Y			工厂饰品编码

    private Double fixedGoldPrice;

    public Double getFixedGoldPrice() {
        return fixedGoldPrice;
    }

    public void setFixedGoldPrice(Double fixedGoldPrice) {
        this.fixedGoldPrice = fixedGoldPrice;
    }

    public String getFactoryOrnaCode() {
		return factoryOrnaCode;
	}
	public void setFactoryOrnaCode(String factoryOrnaCode) {
		this.factoryOrnaCode = factoryOrnaCode;
	}
	public String getInivCost() {
		return inivCost;
	}
	public void setInivCost(String inivCost) {
		this.inivCost = inivCost;
	}
	public String getColorGradeIdXq() {
		return colorGradeIdXq;
	}
	public void setColorGradeIdXq(String colorGradeIdXq) {
		this.colorGradeIdXq = colorGradeIdXq;
	}
	public String getCleanIdXq() {
		return cleanIdXq;
	}
	public void setCleanIdXq(String cleanIdXq) {
		this.cleanIdXq = cleanIdXq;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getSrcBillCode() {
		return srcBillCode;
	}
	public void setSrcBillCode(String srcBillCode) {
		this.srcBillCode = srcBillCode;
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
	public String getAllowInivType() {
		return allowInivType;
	}
	public void setAllowInivType(String allowInivType) {
		this.allowInivType = allowInivType;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getInivOrg() {
		return inivOrg;
	}
	public void setInivOrg(String inivOrg) {
		this.inivOrg = inivOrg;
	}
	public String getInivDirection() {
		return inivDirection;
	}
	public void setInivDirection(String inivDirection) {
		this.inivDirection = inivDirection;
	}
	public String getInivDate() {
		return inivDate;
	}
	public void setInivDate(String inivDate) {
		this.inivDate = inivDate;
	}
	public String getTransceiverType() {
		return transceiverType;
	}
	public void setTransceiverType(String transceiverType) {
		this.transceiverType = transceiverType;
	}
	public String getStockOrg() {
		return stockOrg;
	}
	public void setStockOrg(String stockOrg) {
		this.stockOrg = stockOrg;
	}
	public String getPreAllotOrg() {
		return preAllotOrg;
	}
	public void setPreAllotOrg(String preAllotOrg) {
		this.preAllotOrg = preAllotOrg;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getHandoverBillId() {
		return handoverBillId;
	}
	public void setHandoverBillId(String handoverBillId) {
		this.handoverBillId = handoverBillId;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public String getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(String articleTypeId) {
		this.articleTypeId = articleTypeId;
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
	public String getAnalysisArangeId() {
		return analysisArangeId;
	}
	public void setAnalysisArangeId(String analysisArangeId) {
		this.analysisArangeId = analysisArangeId;
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
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOrnaName() {
		return ornaName;
	}
	public void setOrnaName(String ornaName) {
		this.ornaName = ornaName;
	}
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getIsConsign() {
		return isConsign;
	}
	public void setIsConsign(String isConsign) {
		this.isConsign = isConsign;
	}
	public String getIsCustom() {
		return isCustom;
	}
	public void setIsCustom(String isCustom) {
		this.isCustom = isCustom;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public String getSmallLabelType() {
		return smallLabelType;
	}
	public void setSmallLabelType(String smallLabelType) {
		this.smallLabelType = smallLabelType;
	}
	public String getDecimalNum() {
		return decimalNum;
	}
	public void setDecimalNum(String decimalNum) {
		this.decimalNum = decimalNum;
	}
	public String getTrackIame() {
		return trackIame;
	}
	public void setTrackIame(String trackIame) {
		this.trackIame = trackIame;
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
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getBasicPrice() {
		return basicPrice;
	}
	public void setBasicPrice(String basicPrice) {
		this.basicPrice = basicPrice;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getPosAmount() {
		return posAmount;
	}
	public void setPosAmount(String posAmount) {
		this.posAmount = posAmount;
	}
	public Double getSpecialWeight() {
		return specialWeight;
	}
	public void setSpecialWeight(Double specialWeight) {
		this.specialWeight = specialWeight;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	public String getNuclearBillId() {
		return nuclearBillId;
	}
	public void setNuclearBillId(String nuclearBillId) {
		this.nuclearBillId = nuclearBillId;
	}
	public String getMainModification() {
		return mainModification;
	}
	public void setMainModification(String mainModification) {
		this.mainModification = mainModification;
	}
	public String getMainShapeId() {
		return mainShapeId;
	}
	public void setMainShapeId(String mainShapeId) {
		this.mainShapeId = mainShapeId;
	}
	public String getMainColorGradeId() {
		return mainColorGradeId;
	}
	public void setMainColorGradeId(String mainColorGradeId) {
		this.mainColorGradeId = mainColorGradeId;
	}
	public String getCleanId() {
		return cleanId;
	}
	public void setCleanId(String cleanId) {
		this.cleanId = cleanId;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getCutId() {
		return cutId;
	}
	public void setCutId(String cutId) {
		this.cutId = cutId;
	}
	public String getBracketcolorId() {
		return bracketcolorId;
	}
	public void setBracketcolorId(String bracketcolorId) {
		this.bracketcolorId = bracketcolorId;
	}
	public String getWearId() {
		return wearId;
	}
	public void setWearId(String wearId) {
		this.wearId = wearId;
	}
	public String getPriceAttrGroup() {
		return priceAttrGroup;
	}
	public void setPriceAttrGroup(String priceAttrGroup) {
		this.priceAttrGroup = priceAttrGroup;
	}
	public String getQltyId() {
		return qltyId;
	}
	public void setQltyId(String qltyId) {
		this.qltyId = qltyId;
	}
	public String getSpecialWorkPrice() {
		return specialWorkPrice;
	}
	public void setSpecialWorkPrice(String specialWorkPrice) {
		this.specialWorkPrice = specialWorkPrice;
	}
	public String getStringWorkPrice() {
		return stringWorkPrice;
	}
	public void setStringWorkPrice(String stringWorkPrice) {
		this.stringWorkPrice = stringWorkPrice;
	}
	public Double getCutWideScale() {
		return cutWideScale;
	}
	public void setCutWideScale(Double cutWideScale) {
		this.cutWideScale = cutWideScale;
	}
	public Double getCutDeepScale() {
		return cutDeepScale;
	}
	public void setCutDeepScale(Double cutDeepScale) {
		this.cutDeepScale = cutDeepScale;
	}
	public String getSymmetryId() {
		return symmetryId;
	}
	public void setSymmetryId(String symmetryId) {
		this.symmetryId = symmetryId;
	}
	public String getPolishineId() {
		return polishineId;
	}
	public void setPolishineId(String polishineId) {
		this.polishineId = polishineId;
	}
	public String getFluorescenceId() {
		return fluorescenceId;
	}
	public void setFluorescenceId(String fluorescenceId) {
		this.fluorescenceId = fluorescenceId;
	}
	public String getWaistlineId() {
		return waistlineId;
	}
	public void setWaistlineId(String waistlineId) {
		this.waistlineId = waistlineId;
	}
	public String getVertexId() {
		return vertexId;
	}
	public void setVertexId(String vertexId) {
		this.vertexId = vertexId;
	}
	public String getFactoryFee() {
		return factoryFee;
	}
	public void setFactoryFee(String factoryFee) {
		this.factoryFee = factoryFee;
	}
	public Double getFactoryFeePrice() {
		return factoryFeePrice;
	}
	public void setFactoryFeePrice(Double factoryFeePrice) {
		this.factoryFeePrice = factoryFeePrice;
	}
	public Double getMainWeight() {
		return mainWeight;
	}
	public void setMainWeight(Double mainWeight) {
		this.mainWeight = mainWeight;
	}
	public String getMainStoneNum() {
		return mainStoneNum;
	}
	public void setMainStoneNum(String mainStoneNum) {
		this.mainStoneNum = mainStoneNum;
	}
	public Double getPartStoneWeight() {
		return partStoneWeight;
	}
	public void setPartStoneWeight(Double partStoneWeight) {
		this.partStoneWeight = partStoneWeight;
	}
	public String getPartStoneContent() {
		return partStoneContent;
	}
	public void setPartStoneContent(String partStoneContent) {
		this.partStoneContent = partStoneContent;
	}
	public String getPartStoneNum() {
		return partStoneNum;
	}
	public void setPartStoneNum(String partStoneNum) {
		this.partStoneNum = partStoneNum;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public Double getAllQty() {
		return allQty;
	}
	public void setAllQty(Double allQty) {
		this.allQty = allQty;
	}
	public Double getAvgNum() {
		return avgNum;
	}
	public void setAvgNum(Double avgNum) {
		this.avgNum = avgNum;
	}
	public String getIsUnder_3fen() {
		return isUnder_3fen;
	}
	public void setIsUnder_3fen(String isUnder_3fen) {
		this.isUnder_3fen = isUnder_3fen;
	}
	public String getLuckyDsc() {
		return luckyDsc;
	}
	public void setLuckyDsc(String luckyDsc) {
		this.luckyDsc = luckyDsc;
	}
	public String getInlayno() {
		return inlayno;
	}
	public void setInlayno(String inlayno) {
		this.inlayno = inlayno;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getFactoryStyleId() {
		return factoryStyleId;
	}
	public void setFactoryStyleId(String factoryStyleId) {
		this.factoryStyleId = factoryStyleId;
	}
	public String getApprovaluserid() {
		return approvaluserid;
	}
	public void setApprovaluserid(String approvaluserid) {
		this.approvaluserid = approvaluserid;
	}
	public String getApprovaldate() {
		return approvaldate;
	}
	public void setApprovaldate(String approvaldate) {
		this.approvaldate = approvaldate;
	}
	public String getSrcBillNo() {
		return srcBillNo;
	}
	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	public String getSrcBillId() {
		return srcBillId;
	}
	public void setSrcBillId(String srcBillId) {
		this.srcBillId = srcBillId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getConsignmentType() {
		return consignmentType;
	}
	public void setConsignmentType(String consignmentType) {
		this.consignmentType = consignmentType;
	}
	public String getFactoryFeeType() {
		return factoryFeeType;
	}
	public void setFactoryFeeType(String factoryFeeType) {
		this.factoryFeeType = factoryFeeType;
	}
	public String getIsOldOtc() {
		return isOldOtc;
	}
	public void setIsOldOtc(String isOldOtc) {
		this.isOldOtc = isOldOtc;
	}
	public String getPartContent() {
		return partContent;
	}
	public void setPartContent(String partContent) {
		this.partContent = partContent;
	}
	public String getLuckyWord() {
		return luckyWord;
	}
	public void setLuckyWord(String luckyWord) {
		this.luckyWord = luckyWord;
	}
	public String getIsTearStone() {
		return isTearStone;
	}
	public void setIsTearStone(String isTearStone) {
		this.isTearStone = isTearStone;
	}
	public String getStyleType() {
		return styleType;
	}
	public void setStyleType(String styleType) {
		this.styleType = styleType;
	}
	public String getIsMutiPart() {
		return isMutiPart;
	}
	public void setIsMutiPart(String isMutiPart) {
		this.isMutiPart = isMutiPart;
	}
	public String getStyleOrnaId() {
		return styleOrnaId;
	}
	public void setStyleOrnaId(String styleOrnaId) {
		this.styleOrnaId = styleOrnaId;
	}
	public String getIssuchinda() {
		return issuchinda;
	}
	public void setIssuchinda(String issuchinda) {
		this.issuchinda = issuchinda;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getThemeCoefficient() {
		return themeCoefficient;
	}
	public void setThemeCoefficient(String themeCoefficient) {
		this.themeCoefficient = themeCoefficient;
	}
	public String getNcoefficient() {
		return ncoefficient;
	}
	public void setNcoefficient(String ncoefficient) {
		this.ncoefficient = ncoefficient;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public String getCalcpriceNo() {
		return calcpriceNo;
	}
	public void setCalcpriceNo(String calcpriceNo) {
		this.calcpriceNo = calcpriceNo;
	}
	public String getOrnaBarcode() {
		return ornaBarcode;
	}
	public void setOrnaBarcode(String ornaBarcode) {
		this.ornaBarcode = ornaBarcode;
	}
	public String getCustomizeNo() {
		return customizeNo;
	}
	public void setCustomizeNo(String customizeNo) {
		this.customizeNo = customizeNo;
	}
	public String getBracketStyleId() {
		return bracketStyleId;
	}
	public void setBracketStyleId(String bracketStyleId) {
		this.bracketStyleId = bracketStyleId;
	}
	public String getIsDblLabel() {
		return isDblLabel;
	}
	public void setIsDblLabel(String isDblLabel) {
		this.isDblLabel = isDblLabel;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getPrivateType() {
		return privateType;
	}
	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}
	public String getLackMaterial() {
		return lackMaterial;
	}
	public void setLackMaterial(String lackMaterial) {
		this.lackMaterial = lackMaterial;
	}
	public String getLackMaterialNum() {
		return lackMaterialNum;
	}
	public void setLackMaterialNum(String lackMaterialNum) {
		this.lackMaterialNum = lackMaterialNum;
	}
	public String getLackMaterialWeight() {
		return lackMaterialWeight;
	}
	public void setLackMaterialWeight(String lackMaterialWeight) {
		this.lackMaterialWeight = lackMaterialWeight;
	}
	public String getVerificationFlag() {
		return verificationFlag;
	}
	public void setVerificationFlag(String verificationFlag) {
		this.verificationFlag = verificationFlag;
	}
	public String getInivFlag() {
		return inivFlag;
	}
	public void setInivFlag(String inivFlag) {
		this.inivFlag = inivFlag;
	}
	public String getOldOrnaCode() {
		return oldOrnaCode;
	}
	public void setOldOrnaCode(String oldOrnaCode) {
		this.oldOrnaCode = oldOrnaCode;
	}
	public String getHandoverName() {
		return handoverName;
	}
	public void setHandoverName(String handoverName) {
		this.handoverName = handoverName;
	}
	public String getCostUnit() {
		return costUnit;
	}
	public void setCostUnit(String costUnit) {
		this.costUnit = costUnit;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	
}

