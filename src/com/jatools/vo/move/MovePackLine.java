package com.jatools.vo.move;

import com.jatools.web.util.BdCommon;

public class MovePackLine {
	private String lineid;
	private String headid;

	private String billId;
	private String billNo;
	private String billUserId;
	private String billCount;
	private String billCost;
	private String createId;
	private String createDate;
	private String status;

	private String outOrgId;
	private String inOrgId;
	private String billUserName;
	private String isPack;//交接单是否装箱
	
	private String moveBillType;
	private String estimateNo;

	public String getMoveBillType() {
		return moveBillType;
	}

	public void setMoveBillType(String moveBillType) {
		this.moveBillType = moveBillType;
	}

	public void setNames() {
		billUserName = BdCommon.getUserName(billUserId);
	}

	public String getIsPack() {
		return isPack;
	}

	public void setIsPack(String isPack) {
		this.isPack = isPack;
	}

	public String getBillUserName() {
		return billUserName;
	}

	public void setBillUserName(String billUserName) {
		this.billUserName = billUserName;
	}

	public String getLineid() {
		return lineid;
	}

	public void setLineid(String lineid) {
		this.lineid = lineid;
	}

	public String getHeadid() {
		return headid;
	}

	public void setHeadid(String headid) {
		this.headid = headid;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillUserId() {
		return billUserId;
	}

	public void setBillUserId(String billUserId) {
		this.billUserId = billUserId;
	}

	public String getBillCount() {
		return billCount;
	}

	public void setBillCount(String billCount) {
		this.billCount = billCount;
	}

	public String getBillCost() {
		return billCost;
	}

	public void setBillCost(String billCost) {
		this.billCost = billCost;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOutOrgId() {
		return outOrgId;
	}

	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}

	public String getInOrgId() {
		return inOrgId;
	}

	public void setInOrgId(String inOrgId) {
		this.inOrgId = inOrgId;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

}
