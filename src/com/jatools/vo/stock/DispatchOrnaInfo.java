package com.jatools.vo.stock;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.DictCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UnitCache;

public class DispatchOrnaInfo {
	private String ornaCode;// 饰品编码
	private String ornaDsc;// 饰品名称
	private String unitId;// 计量单位id
	private String styleId;// 款式id
	private String styleName;// 款式名称
	private String bigGraph;
	private String styleItemClassId;
	private String styleMiddleClassId;
	private String styleOrnaClassId;
	private String colorGradeId;// 色级
	private String cleanId;// 净度
	private String colorGradeGradeId;// 色级品质
	private String cleanGradeId;// 净度品质
	private String mainWeight;// 主石重
	private String partWeight;// 配石重
	private String sizeId;// 尺寸id
	private String sizeName;// 尺寸
	private String basicPrice;// 基础价
	private String posAmount;//网点金额
	private String analyzeUnitName;// 分析单位
	private String stockId;//仓库

	private String itemClassId;// 大类id
	private String ornaClassId;// 小类id
	private String qualityId;// 商品材质id
	private String bracketcolorId;
	private String analyseId;// 分析范围id
	private String analyseName;
	private String status;// 状态
	
	private String nowQty;//现有量
	private String allQty;//总重量
	private String realTotalCost;//真实总成本

	private String unitName;
	private String colorGradeName;
	private String cleanName;
	private String colorGradeGradeName;
	private String cleanGradeName;

	private String dispatchModel;// 1已配货、2配货成功、3配货未成功
	private String orgId;
	private String orgName;

	private String subtempId;
	private String materialFlag;//现有量是否备货
	
	private String ornaLogId;//配货记录id
	private String orderLineId;//门店总单行id
	private String gatherHeadId;//总部总单id

	public void setNameByCache() {
		unitName = UnitCache.getInstance().getUnitName(unitId);
		colorGradeName = DictCache.getInstance().getValue(DictConstant.DIA_COLORGRADE, colorGradeId);
		cleanName = DictCache.getInstance().getValue(DictConstant.DIA_CLEAN, cleanId);
		colorGradeGradeName = DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, colorGradeGradeId);
		cleanGradeName = DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, cleanGradeId);
		orgName = OrgCache.getInstance().getOrgName(orgId);
	}

	public String getAnalyzeUnitName() {
		return analyzeUnitName;
	}

	public void setAnalyzeUnitName(String analyzeUnitName) {
		this.analyzeUnitName = analyzeUnitName;
	}

	public String getSubtempId() {
		return subtempId;
	}

	public void setSubtempId(String subtempId) {
		this.subtempId = subtempId;
	}

	public String getOrnaCode() {
		return ornaCode;
	}

	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
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

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
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

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
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

	public String getDispatchModel() {
		return dispatchModel;
	}

	public void setDispatchModel(String dispatchModel) {
		this.dispatchModel = dispatchModel;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getColorGradeGradeId() {
		return colorGradeGradeId;
	}

	public void setColorGradeGradeId(String colorGradeGradeId) {
		this.colorGradeGradeId = colorGradeGradeId;
	}

	public String getCleanGradeId() {
		return cleanGradeId;
	}

	public void setCleanGradeId(String cleanGradeId) {
		this.cleanGradeId = cleanGradeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getColorGradeName() {
		return colorGradeName;
	}

	public void setColorGradeName(String colorGradeName) {
		this.colorGradeName = colorGradeName;
	}

	public String getCleanName() {
		return cleanName;
	}

	public void setCleanName(String cleanName) {
		this.cleanName = cleanName;
	}

	public String getColorGradeGradeName() {
		return colorGradeGradeName;
	}

	public void setColorGradeGradeName(String colorGradeGradeName) {
		this.colorGradeGradeName = colorGradeGradeName;
	}

	public String getCleanGradeName() {
		return cleanGradeName;
	}

	public void setCleanGradeName(String cleanGradeName) {
		this.cleanGradeName = cleanGradeName;
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

	public String getBracketcolorId() {
		return bracketcolorId;
	}

	public void setBracketcolorId(String bracketcolorId) {
		this.bracketcolorId = bracketcolorId;
	}

	public String getAnalyseId() {
		return analyseId;
	}

	public void setAnalyseId(String analyseId) {
		this.analyseId = analyseId;
	}

	public String getAnalyseName() {
		return analyseName;
	}

	public void setAnalyseName(String analyseName) {
		this.analyseName = analyseName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getMaterialFlag() {
		return materialFlag;
	}

	public void setMaterialFlag(String materialFlag) {
		this.materialFlag = materialFlag;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
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

	public String getOrnaLogId() {
		return ornaLogId;
	}

	public void setOrnaLogId(String ornaLogId) {
		this.ornaLogId = ornaLogId;
	}

	public String getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(String orderLineId) {
		this.orderLineId = orderLineId;
	}

	public String getBigGraph() {
		return bigGraph;
	}

	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}


	public String getGatherHeadId() {
		return gatherHeadId;
	}

	public void setGatherHeadId(String gatherHeadId) {
		this.gatherHeadId = gatherHeadId;
	}
}
