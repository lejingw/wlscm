package com.jatools.vo.basic;

public class StoneMainCoefficient {

	private String id;//	number(8)	n			id
	private String itemClassId;//	number	y			大类
	private Double weightStr;//	number	y			重量始
	private Double weightEnd;//	number	y			重量止
	private String coefficient;//	number	y			系数
	
	/**
	 * 状态
	 */
	private String status;// number,状态
	/**
	 * 备注
	 */
	private String memo;// varchar2(1000),备注
	/**
	 * 创建时间
	 */
	private String createDate;// varchar2(20),创建时间
	/**
	 * 创建人
	 */
	private String createId;// number,创建人
	/**
	 * 最后修改时间
	 */
	private String updateDate;// varchar2(20),最后修改时间
	/**
	 * 最后修改时间
	 */
	private String updateId;// number 最后修改时间
	
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
	
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getWeightStr() {
		return weightStr;
	}
	public void setWeightStr(Double weightStr) {
		this.weightStr = weightStr;
	}
	public Double getWeightEnd() {
		return weightEnd;
	}
	public void setWeightEnd(Double weightEnd) {
		this.weightEnd = weightEnd;
	}
	public String getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}
	
}
