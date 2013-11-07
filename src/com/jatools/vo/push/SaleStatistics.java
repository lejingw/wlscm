package com.jatools.vo.push;

import com.jatools.vo.BaseVO;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.util.StringUtil;

public class SaleStatistics extends BaseVO {
//	private String itemClassId;
//	private String ornaClassId;
//	private String analysisId;
//	private String styleItemClassId;
	private String styleMiddleClassId;
	private String styleOrnaClassId;
	private String styleId;
	private String stockNum;
	private String yearNum;
	private String halfYearNum;
	private String halfYearAvgNum;
	private String monthNum;

	private String styleName;
	private String styleCreateDate;
	private String picUrl;

	private String styleRate;

    public String getStyleRate() {
        return styleRate;
    }

    public void setStyleRate(String styleRate) {
        this.styleRate = styleRate;
    }
//	public String getItemClassId() {
//		return itemClassId;
//	}
//
//	public void setItemClassId(String itemClassId) {
//		this.itemClassId = itemClassId;
//	}
//
//	public String getOrnaClassId() {
//		return ornaClassId;
//	}
//
//	public void setOrnaClassId(String ornaClassId) {
//		this.ornaClassId = ornaClassId;
//	}
//
//	public String getAnalysisId() {
//		return analysisId;
//	}
//
//	public void setAnalysisId(String analysisId) {
//		this.analysisId = analysisId;
//	}
//
//	public String getStyleItemClassId() {
//		return styleItemClassId;
//	}
//
//	public void setStyleItemClassId(String styleItemClassId) {
//		this.styleItemClassId = styleItemClassId;
//	}

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

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getStyleMiddleClassName() {
		return StyleMiddleClassCache.getInstance().getNameById(
				styleMiddleClassId);
	}

	public String getStyleOrnaClassName() {
		return StyleOrnaClassCache.getInstance().getNameById(styleOrnaClassId);
	}

	public String getYearNum() {
		return StringUtil.formatDouble(yearNum, 3);
	}

	public void setYearNum(String yearNum) {
		this.yearNum = yearNum;
	}

	public String getHalfYearNum() {
		return StringUtil.formatDouble(halfYearNum, 3);
	}

	public void setHalfYearNum(String halfYearNum) {
		this.halfYearNum = halfYearNum;
	}

	public String getHalfYearAvgNum() {
		return StringUtil.formatDouble(halfYearAvgNum, 3);
	}

	public void setHalfYearAvgNum(String halfYearAvgNum) {
		this.halfYearAvgNum = halfYearAvgNum;
	}

	public String getMonthNum() {
		return StringUtil.formatDouble(monthNum, 3);
	}

	public void setMonthNum(String monthNum) {
		this.monthNum = monthNum;
	}

	public String getStyleCreateDate() {
		return styleCreateDate;
	}

	public void setStyleCreateDate(String styleCreateDate) {
		this.styleCreateDate = styleCreateDate;
	}

}
