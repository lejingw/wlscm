package com.jatools.vo.push;

import com.jatools.web.util.StringUtil;

public class PushSize {
	private String billId;
	private String itemClassId;
	private String ornaClassId;
	private String styleItemClassId;
	private String startSizeId;
	private String endSizeId;
	private String sizeId;
	private String rate;
	private String createId;
	private String createDate;
	private String updateId;
	private String updateDate;
	private String status;
	
	private String startSizeName;
	private String endSizeName;
	private String sizeName;
	private String startSizeNum;
	private String endSizeNum;
	
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
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
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
	public String getRate() {
		return StringUtil.formatDouble(rate, 3);
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getStartSizeNum() {
		return startSizeNum;
	}
	public void setStartSizeNum(String startSizeNum) {
		this.startSizeNum = startSizeNum;
	}
	public String getEndSizeNum() {
		return endSizeNum;
	}
	public void setEndSizeNum(String endSizeNum) {
		this.endSizeNum = endSizeNum;
	}
	public String getStyleItemClassId() {
		return styleItemClassId;
	}
	public void setStyleItemClassId(String styleItemClassId) {
		this.styleItemClassId = styleItemClassId;
	}
}
