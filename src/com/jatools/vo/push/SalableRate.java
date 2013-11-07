package com.jatools.vo.push;

import com.jatools.web.util.StringUtil;

public class SalableRate {
	private String regionId;
	private String saleDisRate;
	private String saleTurnRate;
	private String unsaleDisRate;
	private String unsaleTurnRate;
	private String createId;
	private String createDate;
	private String updateId;
	private String updateDate;
	private String status;
	private String regionName;
	
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getSaleDisRate() {
		return StringUtil.formatDouble(saleDisRate, 3);
	}

	public void setSaleDisRate(String saleDisRate) {
		this.saleDisRate = saleDisRate;
	}

	public String getSaleTurnRate() {
		return StringUtil.formatDouble(saleTurnRate, 3);
	}

	public void setSaleTurnRate(String saleTurnRate) {
		this.saleTurnRate = saleTurnRate;
	}

	public String getUnsaleDisRate() {
		return StringUtil.formatDouble(unsaleDisRate, 3);
	}

	public void setUnsaleDisRate(String unsaleDisRate) {
		this.unsaleDisRate = unsaleDisRate;
	}

	public String getUnsaleTurnRate() {
		return StringUtil.formatDouble(unsaleTurnRate, 3);
	}

	public void setUnsaleTurnRate(String unsaleTurnRate) {
		this.unsaleTurnRate = unsaleTurnRate;
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

}
