package com.jatools.vo.pur.cash;

public class PayMoney {
	private String payId;//	integer	n			
	private String billNo	;//(20)	y			单据编号
	private String billDate	;//(20)	y			单据时间
	private String orgId	;//	y			采购组织
	private String voderId	;//	y			供应商
	private String payMoney	;//	y			付款金额
	private String lessMoney;
	private String status	;//	y			状态
	private String memo	;//(1000)	y			备注
	private String createDate	;//(20)	y			创建时间
	private String createId	;//	y			创建人员
	private String updateDate	;//(20)	y			最后修改时间
	private String updateId	;//	y			最后修改人员

	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getVoderId() {
		return voderId;
	}
	public void setVoderId(String voderId) {
		this.voderId = voderId;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
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
	public String getLessMoney() {
		return lessMoney;
	}
	public void setLessMoney(String lessMoney) {
		this.lessMoney = lessMoney;
	}
	
}
