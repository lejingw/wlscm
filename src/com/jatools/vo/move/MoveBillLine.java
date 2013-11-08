package com.jatools.vo.move;

import com.jatools.web.util.BdCommon;

public class MoveBillLine {
	private String lineid;
	private String headid;
	private String ornaCode;
	private String ornaDsc;
	private String orgId;
	private Double weight;
	private String status;
	
	
	
	private String barCode;
	private String itemClassId;
	private String ornaClassId;
	private String styleItemClassId;
	private String styleMiddleClassId;
	private String styleOrnaClassId;
	private String styleId;
	private String analysisId;
	private String unitId;
	private Double allWeight;
	private Double nowQty;
	private String grains;
	private Double mainWeight;
	private Double partWeight;
	private String isMutiPart;
	private String isDblLabel;
	private Double posCost;
	private Double posAmount;
	private String isPsale;
	private String outGroupNo;
	private String inGroupNo;// 接收时写入
	private String receDate;
	private String receEmpId;
	private Double newPosCost;
	private String newPosMoney;
	private Double salepriceCoef;
	private String memo;
	private String createDate;
	private String createId;
	private String updateDate;
	private String updateId;

	private String styleName;
	private String bigGraph;
	private String analysisName;
	private String printLabel;

	private String stockId;

	private String itemClassName;
	private String ornaClassName;
	private String unitName;
	
	private String sumCount;
	private String sumWeight;
	private String sumCost;
	
	private String toshopdateAfter48;//到店时间延后两天（48小时）
	private String currentDate;//当前时间
	
	private String freeReturnFlag;//是否免费换货
	private String freeLeftDays;//免费换货剩余天数

	public void setNames() {
		this.itemClassName = BdCommon.getItemClassDesc(itemClassId);
		this.ornaClassName = BdCommon.getOrnaClassDesc(ornaClassId);
		this.unitName = BdCommon.getUnitName(unitId);
	}

	public String getSumCount() {
		return sumCount;
	}

	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}

	public String getSumWeight() {
		return sumWeight;
	}

	public void setSumWeight(String sumWeight) {
		this.sumWeight = sumWeight;
	}

	public String getSumCost() {
		return sumCost;
	}

	public void setSumCost(String sumCost) {
		this.sumCost = sumCost;
	}

	public String getItemClassName() {
		return itemClassName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}

	public String getOrnaClassName() {
		return ornaClassName;
	}

	public void setOrnaClassName(String ornaClassName) {
		this.ornaClassName = ornaClassName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getLineid() {
		return lineid;
	}

	public void setLineid(String lineid) {
		this.lineid = lineid;
	}

	public String getHeadid() {
		return headid;
	}

	public void setHeadid(String headid) {
		this.headid = headid;
	}

	public String getOrnaCode() {
		return ornaCode;
	}

	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getOrnaDsc() {
		return ornaDsc;
	}

	public void setOrnaDsc(String ornaDsc) {
		this.ornaDsc = ornaDsc;
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

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Double getNowQty() {
		return nowQty;
	}

	public void setNowQty(Double nowQty) {
		this.nowQty = nowQty;
	}

	public Double getAllWeight() {
		return allWeight;
	}

	public void setAllWeight(Double allWeight) {
		this.allWeight = allWeight;
	}

	public String getGrains() {
		return grains;
	}

	public void setGrains(String grains) {
		this.grains = grains;
	}

	public Double getMainWeight() {
		return mainWeight;
	}

	public void setMainWeight(Double mainWeight) {
		this.mainWeight = mainWeight;
	}

	public Double getPartWeight() {
		return partWeight;
	}

	public void setPartWeight(Double partWeight) {
		this.partWeight = partWeight;
	}

	public String getIsMutiPart() {
		return isMutiPart;
	}

	public void setIsMutiPart(String isMutiPart) {
		this.isMutiPart = isMutiPart;
	}

	public String getIsDblLabel() {
		return isDblLabel;
	}

	public void setIsDblLabel(String isDblLabel) {
		this.isDblLabel = isDblLabel;
	}

	public Double getPosCost() {
		return posCost;
	}

	public void setPosCost(Double posCost) {
		this.posCost = posCost;
	}

	public Double getPosAmount() {
		return posAmount;
	}

	public void setPosAmount(Double posAmount) {
		this.posAmount = posAmount;
	}

	public String getIsPsale() {
		return isPsale;
	}

	public void setIsPsale(String isPsale) {
		this.isPsale = isPsale;
	}

	public String getOutGroupNo() {
		return outGroupNo;
	}

	public void setOutGroupNo(String outGroupNo) {
		this.outGroupNo = outGroupNo;
	}

	public String getInGroupNo() {
		return inGroupNo;
	}

	public void setInGroupNo(String inGroupNo) {
		this.inGroupNo = inGroupNo;
	}

	public String getReceDate() {
		return receDate;
	}

	public void setReceDate(String receDate) {
		this.receDate = receDate;
	}

	public String getReceEmpId() {
		return receEmpId;
	}

	public void setReceEmpId(String receEmpId) {
		this.receEmpId = receEmpId;
	}

	public Double getNewPosCost() {
		return newPosCost;
	}

	public void setNewPosCost(Double newPosCost) {
		this.newPosCost = newPosCost;
	}

	public String getNewPosMoney() {
		return newPosMoney;
	}

	public void setNewPosMoney(String newPosMoney) {
		this.newPosMoney = newPosMoney;
	}

	public Double getSalepriceCoef() {
		return salepriceCoef;
	}

	public void setSalepriceCoef(Double salepriceCoef) {
		this.salepriceCoef = salepriceCoef;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getBigGraph() {
		return bigGraph;
	}

	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}

	public String getAnalysisName() {
		return analysisName;
	}

	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getPrintLabel() {
		return printLabel;
	}

	public void setPrintLabel(String printLabel) {
		this.printLabel = printLabel;
	}

	public String getToshopdateAfter48() {
		return toshopdateAfter48;
	}

	public void setToshopdateAfter48(String toshopdateAfter48) {
		this.toshopdateAfter48 = toshopdateAfter48;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getFreeReturnFlag() {
		return freeReturnFlag;
	}

	public void setFreeReturnFlag(String freeReturnFlag) {
		this.freeReturnFlag = freeReturnFlag;
	}

	public String getFreeLeftDays() {
		return freeLeftDays;
	}

	public void setFreeLeftDays(String freeLeftDays) {
		this.freeLeftDays = freeLeftDays;
	}
}
