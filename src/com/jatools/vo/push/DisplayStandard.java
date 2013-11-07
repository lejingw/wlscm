package com.jatools.vo.push;

import com.jatools.vo.BaseVO;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;

public class DisplayStandard extends BaseVO {
	private String lineid;
	private String headid;
	private String itemClassId;
	private String ornaClassId;
	private String analysisId;
	private String analysisName;
	private String styleItemClassId;
	private String styleMiddleClassId;
	private String styleOrnaClassId;
	private String styleId;
	private String styleName;
	private String bigGraph;
	private String minCount;
	private String maxCount;
	private String memo;

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


	public String getItemClassName() {
		return ItemClassCache.getInstance().getNameById(itemClassId);
	}
	public String getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}

	public String getOrnaClassName() {
		return OrnaClassCache.getInstance().getNameById(ornaClassId);
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

	public String getStyleItemClassName() {
		return StyleItemClassCache.getInstance().getNameById(styleItemClassId);
	}
	public String getStyleItemClassId() {
		return styleItemClassId;
	}

	public void setStyleItemClassId(String styleItemClassId) {
		this.styleItemClassId = styleItemClassId;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getMinCount() {
		return minCount;
	}

	public void setMinCount(String minCount) {
		this.minCount = minCount;
	}

	public String getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStyleMiddleClassId() {
		return styleMiddleClassId;
	}
	public String getStyleMiddleClassName() {
		return StyleMiddleClassCache.getInstance().getNameById(styleMiddleClassId);
	}

	public void setStyleMiddleClassId(String styleMiddleClassId) {
		this.styleMiddleClassId = styleMiddleClassId;
	}

	public String getStyleOrnaClassId() {
		return styleOrnaClassId;
	}
	public String getStyleOrnaClassName() {
		return StyleOrnaClassCache.getInstance().getNameById(styleOrnaClassId);
	}

	public void setStyleOrnaClassId(String styleOrnaClassId) {
		this.styleOrnaClassId = styleOrnaClassId;
	}

	public String getAnalysisName() {
		return analysisName;
	}

	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
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
}
