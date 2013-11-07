package com.jatools.vo.push;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.StringUtil;

public class GatherSetGrade {
	private String listid;
	private String lineid;
	private String colorGradeId;
	private String cleanId;
	private String colorGradeGradeId;
	private String cleanGradeId;
	private String rate;
	private String realNum;
	private String realRate;
	private String orderNum;
	private String stockNum;
	private String createId;
	private String createDate;
	private String status;

	private int dispatchNum = 0;

	public int getDispatchNum() {
		return dispatchNum;
	}

	public void setDispatchNum(int dispatchNum) {
		this.dispatchNum = dispatchNum;
	}

	public String getColorGradeName() {
		return DictCache.getInstance().getValue(DictConstant.DIA_COLORGRADE,
				colorGradeId);
	}

	public String getCleanName() {
		return DictCache.getInstance()
				.getValue(DictConstant.DIA_CLEAN, cleanId);
	}

	public String getGradeName() {
		return DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL,
				StringUtil.trimToEmpty(colorGradeGradeId))
				+ DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL,
						StringUtil.trimToEmpty(cleanGradeId));
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

	public String getRealNum() {
		return realNum;
	}

	public void setRealNum(String realNum) {
		this.realNum = realNum;
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

	public String getStockNum() {
		return stockNum;
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

	public String getRealRate() {
		return realRate;
	}

	public void setRealRate(String realRate) {
		this.realRate = realRate;
	}

}
