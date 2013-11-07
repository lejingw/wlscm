package com.jatools.vo.basic;

/**
 * 工费核算标准
 * @author ren.ming
 *
 */
public class Charge {
	/**
	 * ID
	 */
	private String chargeId;// integer not null,
	/**
	 * 工费始 重量始
	 */
	private String weightStr;// number,
	/**
	 * 工费止 重量止 
	 */
	private String weightEnd;// number,
	/**
	 * 工费值
	 */
	private String chargeVal;// number,
	/**
	 * 状态
	 */
	private String status;// number,
	/**
	 * 备注
	 */
	private String memo;// varchar2(1000),
	private String createDate;// varchar2(20),
	private String createId;// number,
	private String updateDate;// varchar2(20),
	private String updateId;// number
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	
	public String getChargeVal() {
		return chargeVal;
	}
	public void setChargeVal(String chargeVal) {
		this.chargeVal = chargeVal;
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
	public String getWeightStr() {
		return weightStr;
	}
	public void setWeightStr(String weightStr) {
		this.weightStr = weightStr;
	}
	public String getWeightEnd() {
		return weightEnd;
	}
	public void setWeightEnd(String weightEnd) {
		this.weightEnd = weightEnd;
	}
	
}
