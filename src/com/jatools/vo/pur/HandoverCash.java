package com.jatools.vo.pur;

public class HandoverCash {

	private String lineid;//	number	n			lineid
	private String billid;//	number	n			单据id
	private String materialType;//	varchar2(20)	y			原料类型
	private String itemClassId;//	number	y			大类id
	private String itemClassIdOld;
	private String moneys;//	number	y			0否 1是
	private String moneysOld;
	private String status;//	number	y			状态
	private String memo;//	varchar2(1000)	y			备注
	private String createDate;//	varchar2(20)	y			创建时间
	private String createId;//	number	y			创建人员
	private String updateDate;//	varchar2(20)	y			最后修改时间
	private String updateId;//	number	y			最后修改人员
	
	public String getMoneysOld() {
		return moneysOld;
	}
	public void setMoneysOld(String moneysOld) {
		this.moneysOld = moneysOld;
	}
	public String getItemClassIdOld() {
		return itemClassIdOld;
	}
	public void setItemClassIdOld(String itemClassIdOld) {
		this.itemClassIdOld = itemClassIdOld;
	}
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getMoneys() {
		return moneys;
	}
	public void setMoneys(String moneys) {
		this.moneys = moneys;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
