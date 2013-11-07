package com.jatools.vo.stock;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.StringUtil;

public class DispatchStatistics {
	private String orgId;
	private String itemClassId;
	private String ornaClassId;
	private String analysisId;
	private String analysisName;
	private String colorGradeGradeId;
	private String cleanGradeId;
	private String sumCount;
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
	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}
	public String getGradeName(){
		return DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, StringUtil.trimToEmpty(colorGradeGradeId))
				+DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, StringUtil.trimToEmpty(cleanGradeId));
	}
}
