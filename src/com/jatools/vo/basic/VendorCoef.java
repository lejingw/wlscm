package com.jatools.vo.basic;

public class VendorCoef {
	private String coefId;// COEF_ID	NUMBER(8)	N			
	private String vendorId;//VENDOR_ID	VARCHAR2(12)	N	
	private String coef;//COEF	NUMBER(8)	N	
	private String memo;//MEMO	VARCHAR2(1000)	Y			备注
	private String status;//STATUS	NUMBER	Y			状态
	private String createDate;//CREATE_DATE	VARCHAR2(20)	Y			创建时间
	private String createId;//CREATE_ID	NUMBER(8)	Y			创建人
	private String updateDate;//UPDATE_DATE	VARCHAR2(20)	Y			更新人
	private String updateId;//UPDATE_ID	NUMBER(8)	Y			更新时间
	
	public String getCoefId() {
		return coefId;
	}
	public void setCoefId(String coefId) {
		this.coefId = coefId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getCoef() {
		return coef;
	}
	public void setCoef(String coef) {
		this.coef = coef;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	
}
