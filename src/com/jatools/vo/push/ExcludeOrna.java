package com.jatools.vo.push;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.StringUtil;

public class ExcludeOrna {
	private String ornaCode;
	private String barCode;
	private String ornaDsc;
	private String state;
	private String orgId;
	private String itemClassId;
	private String ornaClassId;
	private String analysisId;
	private String analysisName;
	private String styleId;
	private String styleName;
	private String colorGradeId;
	private String cleanId;
	private String colorGradeGradeId;
	private String cleanGradeId;
	private String sizeId;
	private String sizeName;
	private String qualityId;
	private String bracketColorId;
	private String unitId;
	private String createId;
	private String createDate;
	private String status;
	
	private String orgName;
	private String stateName;

	private String styleItemClassId;
	private String styleMiddleClassId;
	private String styleOrnaClassId;
	private String bigGraph;
	private String nowQty;
	private String allQty;
	private String stoneTotalNum;
	private String posAmount;
	private String mainWeight;
	private String partWeight;

	public String getGradeName(){
		return DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, StringUtil.trimToEmpty(colorGradeGradeId))
				+DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, StringUtil.trimToEmpty(cleanGradeId));
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getBracketColorId() {
		return bracketColorId;
	}
	public void setBracketColorId(String bracketColorId) {
		this.bracketColorId = bracketColorId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
	public String getStoneTotalNum() {
		return stoneTotalNum;
	}
	public void setStoneTotalNum(String stoneTotalNum) {
		this.stoneTotalNum = stoneTotalNum;
	}
	public String getPosAmount() {
		return posAmount;
	}
	public void setPosAmount(String posAmount) {
		this.posAmount = posAmount;
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
}