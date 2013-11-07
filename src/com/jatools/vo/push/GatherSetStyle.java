package com.jatools.vo.push;

import com.jatools.vo.BaseVO;
import com.jatools.web.util.StringUtil;

public class GatherSetStyle extends BaseVO {
	private String listid;
	private String lineid;
	private String salableFlag;
	private String styleId;
	private String qualityId;
	private String bracketColorId;
	private String rate;
	private String realRate;
	private String stockNum;
	private String monthNum;
	private String halfYearNum;
	private String halfYearAvgNum;
	private String yearNum;
	private String styleCreateDate;
	private String orderNum;
	
	private String styleName;
	private String picUrl;
	
	private int dispatchNum = 0;
	
	public int getDispatchNum() {
		return dispatchNum;
	}
	public void setDispatchNum(int dispatchNum) {
		this.dispatchNum = dispatchNum;
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
	public String getListid() {
		return listid;
	}
	public void setListid(String listid) {
		this.listid = listid;
	}
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
	}
	public String getSalableFlag() {
		return salableFlag;
	}
	public void setSalableFlag(String salableFlag) {
		this.salableFlag = salableFlag;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
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
	public String getRate() {
		return StringUtil.formatDouble(rate, 3);
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getStockNum() {
		return StringUtil.formatDouble(stockNum, 3);
	}
	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	public String getOrderNum() {
		return StringUtil.formatDouble(orderNum, 3);
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	public String getRealRate() {
		return realRate;
	}
	public void setRealRate(String realRate) {
		this.realRate = realRate;
	}
	public String getHalfYearNum() {
		return halfYearNum;
	}
	public void setHalfYearNum(String halfYearNum) {
		this.halfYearNum = halfYearNum;
	}
	public String getYearNum() {
		return yearNum;
	}
	public void setYearNum(String yearNum) {
		this.yearNum = yearNum;
	}
	public String getStyleCreateDate() {
		return styleCreateDate;
	}
	public void setStyleCreateDate(String styleCreateDate) {
		this.styleCreateDate = styleCreateDate;
	}
	
}
