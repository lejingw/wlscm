package com.jatools.vo.stock;

public class DispatchOrnaLog {
	private String id;
	private String ornaCode;
	private String gatherHeadId;
	private String gatherLineId;
	private String orderHeadId;
	private String orderLineId;
	private String orgId;
	private String itemClassId;
	private String ornaClassId;
	private String createId;
	private String createDate;
	private String updateId;
	private String updateDate;
	private String status;
	private String oldFlag;// 是否过时分配

	private String ornaDsc;// 饰品名称
	private String unitId;// 计量单位id
	private String styleId;// 款式id
	private String styleName;// 款式名称
	private String bigGraph;
	private String mainWeight;// 主石重
	private String partWeight;// 配石重
	private String colorGradeId;
	private String cleanId;
	private String sizeId;// 尺寸id
	private String sizeName;// 尺寸
	private String basicPrice;// 基础价
	private String posAmount;//网点金额
	private String allQty;// 总重量
	private String nowQty;// 现有量
	private String realTotalCost;// 真实总成本
	private String dispatchFlag;
	private String subtempId;//减库配货临时数据id
	
	private String analysisId;
	private String analysisName;
	private String qualityId;
	private String bracketcolorId;
	private String isPerfectMatch;//是否模糊匹配
	private String matchRuleDsc;//取消匹配规则内容描述
	
	private String newPosMoney;
	private String styleItemClassId;
	private String styleMiddleClassId;
	private String styleOrnaClassId;
	
	private String stockId;
	private String isMaterial;
	private String srcBillCode;
	
	public String getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}

	public String getAnalysisName() {
		return analysisName;
	}

	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}

	public String getQualityId() {
		return qualityId;
	}

	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	
	public String getBracketcolorId() {
		return bracketcolorId;
	}

	public void setBracketcolorId(String bracketcolorId) {
		this.bracketcolorId = bracketcolorId;
	}

	public String getMatchRuleDsc() {
		return matchRuleDsc;
	}

	public void setMatchRuleDsc(String matchRuleDsc) {
		this.matchRuleDsc = matchRuleDsc;
	}
	
	public String getIsPerfectMatch() {
		return isPerfectMatch;
	}

	public void setIsPerfectMatch(String isPerfectMatch) {
		this.isPerfectMatch = isPerfectMatch;
	}

	public String getSubtempId() {
		return subtempId;
	}

	public void setSubtempId(String subtempId) {
		this.subtempId = subtempId;
	}

	public String getStyleId() {
		return styleId;
	}

	public String getSrcBillCode() {
		return srcBillCode;
	}

	public void setSrcBillCode(String srcBillCode) {
		this.srcBillCode = srcBillCode;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

	public String getOrnaDsc() {
		return ornaDsc;
	}

	public void setOrnaDsc(String ornaDsc) {
		this.ornaDsc = ornaDsc;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getMainWeight() {
		return mainWeight;
	}

	public void setMainWeight(String mainWeight) {
		this.mainWeight = mainWeight;
	}

	public String getPartWeight() {
		return partWeight;
	}

	public void setPartWeight(String partWeight) {
		this.partWeight = partWeight;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(String basicPrice) {
		this.basicPrice = basicPrice;
	}

	public String getPosAmount() {
		return posAmount;
	}

	public void setPosAmount(String posAmount) {
		this.posAmount = posAmount;
	}

	public String getNowQty() {
		return nowQty;
	}

	public void setNowQty(String nowQty) {
		this.nowQty = nowQty;
	}

	public String getAllQty() {
		return allQty;
	}

	public void setAllQty(String allQty) {
		this.allQty = allQty;
	}

	public String getRealTotalCost() {
		return realTotalCost;
	}

	public void setRealTotalCost(String realTotalCost) {
		this.realTotalCost = realTotalCost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrnaCode() {
		return ornaCode;
	}

	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}

	public String getGatherHeadId() {
		return gatherHeadId;
	}

	public void setGatherHeadId(String gatherHeadId) {
		this.gatherHeadId = gatherHeadId;
	}

	public String getGatherLineId() {
		return gatherLineId;
	}

	public void setGatherLineId(String gatherLineId) {
		this.gatherLineId = gatherLineId;
	}

	public String getOrderHeadId() {
		return orderHeadId;
	}

	public void setOrderHeadId(String orderHeadId) {
		this.orderHeadId = orderHeadId;
	}

	public String getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(String orderLineId) {
		this.orderLineId = orderLineId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldFlag() {
		return oldFlag;
	}

	public void setOldFlag(String oldFlag) {
		this.oldFlag = oldFlag;
	}

	public String getDispatchFlag() {
		return dispatchFlag;
	}

	public void setDispatchFlag(String dispatchFlag) {
		this.dispatchFlag = dispatchFlag;
	}

	public String getColorGradeId() {
		return colorGradeId;
	}

	public void setColorGradeId(String colorGradeId) {
		this.colorGradeId = colorGradeId;
	}

	public String getCleanId() {
		return cleanId;
	}

	public void setCleanId(String cleanId) {
		this.cleanId = cleanId;
	}

	public String getNewPosMoney() {
		return newPosMoney;
	}

	public void setNewPosMoney(String newPosMoney) {
		this.newPosMoney = newPosMoney;
	}

	public String getStyleItemClassId() {
		return styleItemClassId;
	}

	public void setStyleItemClassId(String styleItemClassId) {
		this.styleItemClassId = styleItemClassId;
	}

	public String getStyleMiddleClassId() {
		return styleMiddleClassId;
	}

	public void setStyleMiddleClassId(String styleMiddleClassId) {
		this.styleMiddleClassId = styleMiddleClassId;
	}

	public String getStyleOrnaClassId() {
		return styleOrnaClassId;
	}

	public void setStyleOrnaClassId(String styleOrnaClassId) {
		this.styleOrnaClassId = styleOrnaClassId;
	}

	public String getBigGraph() {
		return bigGraph;
	}

	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getIsMaterial() {
		return isMaterial;
	}

	public void setIsMaterial(String isMaterial) {
		this.isMaterial = isMaterial;
	}

}
