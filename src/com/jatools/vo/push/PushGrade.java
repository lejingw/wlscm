package com.jatools.vo.push;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.StringUtil;

public class PushGrade {
	private String billId;
	private String itemClassId;
	private String ornaClassId;
	private String analysisId;
	private String colorGradeId;
	private String cleanId;
	private String colorGradeGradeId;
	private String cleanGradeId;
	private String rate;
	private String createId;
	private String createDate;
	private String updateId;
	private String updateDate;
	private String status;
	
	private String analysisName;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
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

	public String getRate() {
		return StringUtil.formatDouble(rate, 3);
	}

	public void setRate(String rate) {
		this.rate = rate;
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

	public String getAnalysisName() {
		return analysisName;
	}

	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}
	public String getGradeName(){
		return DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, StringUtil.trimToEmpty(colorGradeGradeId))
				+DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, StringUtil.trimToEmpty(cleanGradeId));
	}
}
