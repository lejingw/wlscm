package com.jatools.vo.push;

import com.jatools.web.util.StringUtil;

public class GatherSetSize {
	private String listid;
	private String lineid;
	private String sizeId;
	private String startSizeId;
	private String endSizeId;
	private String rate;
	private String realRate;
	private String orderNum;
	private String stockNum;
	private String createId;
	private String createDate;
	private String status;
	
	private String sizeName;
	private String startSizeName;
	private String endSizeName;

	private int dispatchNum = 0;
	public int getDispatchNum() {
		return dispatchNum;
	}
	public void setDispatchNum(int dispatchNum) {
		this.dispatchNum = dispatchNum;
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
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getStartSizeId() {
		return startSizeId;
	}
	public void setStartSizeId(String startSizeId) {
		this.startSizeId = startSizeId;
	}
	public String getEndSizeId() {
		return endSizeId;
	}
	public void setEndSizeId(String endSizeId) {
		this.endSizeId = endSizeId;
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
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getStartSizeName() {
		return startSizeName;
	}
	public void setStartSizeName(String startSizeName) {
		this.startSizeName = startSizeName;
	}
	public String getEndSizeName() {
		return endSizeName;
	}
	public void setEndSizeName(String endSizeName) {
		this.endSizeName = endSizeName;
	}
	public String getRealRate() {
		return realRate;
	}
	public void setRealRate(String realRate) {
		this.realRate = realRate;
	}
	
}
