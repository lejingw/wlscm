package com.jatools.vo.push;

import com.jatools.vo.BaseVO;
import com.jatools.web.cache.StyleItemClassCache;

public class GatherSetLine extends BaseVO {
	private String lineid;
	private String headid;
	private String itemClassId;
	private String ornaClassId;
	private String analysisId;
	private String styleItemClassId;
	private String orderNum;
	private String salableNum;
	private String unsalableNum;
	private String realSalableOrderNum;
	private String finishFlag;
	
	private String analysisName;
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
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
	public String getStyleItemClassId() {
		return styleItemClassId;
	}
	public String getStyleItemClassName() {
		return StyleItemClassCache.getInstance().getNameById(styleItemClassId);
	}
	public void setStyleItemClassId(String styleItemClassId) {
		this.styleItemClassId = styleItemClassId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getAnalysisName() {
		return analysisName;
	}
	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}
	public String getHeadid() {
		return headid;
	}
	public void setHeadid(String headid) {
		this.headid = headid;
	}
	public String getSalableNum() {
		return salableNum;
	}
	public void setSalableNum(String salableNum) {
		this.salableNum = salableNum;
	}
	public String getUnsalableNum() {
		return unsalableNum;
	}
	public void setUnsalableNum(String unsalableNum) {
		this.unsalableNum = unsalableNum;
	}
	public void setRealSalableOrderNum(String realSalableOrderNum) {
		this.realSalableOrderNum = realSalableOrderNum;
	}
	public String getRealSalableOrderNum() {
		return realSalableOrderNum;
	}
	public String getFinishFlag() {
		return finishFlag;
	}
	public void setFinishFlag(String finishFlag) {
		this.finishFlag = finishFlag;
	}
}
