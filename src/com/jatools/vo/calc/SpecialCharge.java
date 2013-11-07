package com.jatools.vo.calc;

public class SpecialCharge {
	private String chargeId;//CHARGE_ID	INTEGER	N
	private String weightStr;//WEIGHT_STR	NUMBER	Y			重量始
	private String weightEnd;//WEIGHT_END	NUMBER	Y			重量止
	private String weightCheck;//WEIGHT_CHECK	NUMBER	Y			核算重量
	private String chargeUnit;//CHARGE_UNIT	NUMBER	Y			工费单价
	private String chargeFloat;//CHARGE_FLOAT	NUMBER	Y			浮动值
	private String chargeMoney;//CHARGE_MONEY	NUMBER	Y			特殊工费
	private String status;//STATUS	NUMBER	Y			状态
	private String createDate;
	private String createId;
	private String updateDate;
	private String updateId;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
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
	public String getWeightCheck() {
		return weightCheck;
	}
	public void setWeightCheck(String weightCheck) {
		this.weightCheck = weightCheck;
	}
	public String getChargeUnit() {
		return chargeUnit;
	}
	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}
	public String getChargeFloat() {
		return chargeFloat;
	}
	public void setChargeFloat(String chargeFloat) {
		this.chargeFloat = chargeFloat;
	}
	public String getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(String chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
