package com.jatools.vo.push;

import com.jatools.vo.BaseVO;
import com.jatools.web.cache.StyleItemClassCache;

public class WearSize extends BaseVO {
	private String billId;
	private String itemClassId;
	private String ornaClassId;
	private String styleItemClassId;
	private String wearId;
	private String wearName;
	private String sizeId;
	private String sizeName;
	private String startSizeId;
	private String startSizeName;
	private String endSizeId;
	private String endSizeName;
	private String rate;
	private String startSizeNum;
	private String endSizeNum;
	
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
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getWearId() {
		return wearId;
	}
	public void setWearId(String wearId) {
		this.wearId = wearId;
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
	public String getStartSizeId() {
		return startSizeId;
	}
	public void setStartSizeId(String startSizeId) {
		this.startSizeId = startSizeId;
	}
	public String getStartSizeName() {
		return startSizeName;
	}
	public void setStartSizeName(String startSizeName) {
		this.startSizeName = startSizeName;
	}
	public String getEndSizeId() {
		return endSizeId;
	}
	public void setEndSizeId(String endSizeId) {
		this.endSizeId = endSizeId;
	}
	public String getEndSizeName() {
		return endSizeName;
	}
	public void setEndSizeName(String endSizeName) {
		this.endSizeName = endSizeName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
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
	public String getWearName() {
		return wearName;
	}
	public void setWearName(String wearName) {
		this.wearName = wearName;
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
	
}
