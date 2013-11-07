package com.jatools.vo.pur;

public class HandoverChild {

	private String childid;//		n			lineid
	private String lineid;//		n			单据id
	private String alaysisId;//		y			分析范围id
	private String stoneNum;//		y			粒数
	private String createDate;//	(20)	y			创建时间
	private String createId;//		y			创建人员
	private String updateDate;//	(20)	y			最后修改时间
	private String updateId;//		y			最后修改人员
	private String unitPrice;// 单价
	
	private String alaysisName;
	
	public String getAlaysisName() {
		return alaysisName;
	}
	public void setAlaysisName(String alaysisName) {
		this.alaysisName = alaysisName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getChildid() {
		return childid;
	}
	public void setChildid(String childid) {
		this.childid = childid;
	}
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
	}
	public String getAlaysisId() {
		return alaysisId;
	}
	public void setAlaysisId(String alaysisId) {
		this.alaysisId = alaysisId;
	}
	public String getStoneNum() {
		return stoneNum;
	}
	public void setStoneNum(String stoneNum) {
		this.stoneNum = stoneNum;
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
