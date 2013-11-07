package com.jatools.vo.out;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class OutBackHead implements ReviewBill {
	private String headId;
	private String billNo;
	private String doDate;
	private String orgId;
	private String stockId;
	private String userId;
	private String backDate;
	private String backBody;
	private String backSum;
	private String invalid;
	private String sumCount;
	private String sumWeight;
	private String sumGrains;
	private String sumCost;
	private String sumMoney;
	private String status;
	private String isCheck;
	private String isFlag;
	private String memo;
	private String createDate;
	private String createId;
	private String updateDate;
	private String updateId;
	private String checkDate;
	private String checkId;
	private String cancelDate;
	private String cancelId;
	private String outOrgId;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDoDate() {
		return doDate;
	}

	public void setDoDate(String doDate) {
		this.doDate = doDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public String getBackBody() {
		return backBody;
	}

	public void setBackBody(String backBody) {
		this.backBody = backBody;
	}

	public String getBackSum() {
		return backSum;
	}

	public void setBackSum(String backSum) {
		this.backSum = backSum;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}

	public String getSumCount() {
		return sumCount;
	}

	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}

	public String getSumWeight() {
		return sumWeight;
	}

	public void setSumWeight(String sumWeight) {
		this.sumWeight = sumWeight;
	}

	public String getSumGrains() {
		return sumGrains;
	}

	public void setSumGrains(String sumGrains) {
		this.sumGrains = sumGrains;
	}

	public String getSumCost() {
		return sumCost;
	}

	public void setSumCost(String sumCost) {
		this.sumCost = sumCost;
	}

	public String getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
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

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelId() {
		return cancelId;
	}

	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}

	public String getHeadId() {
		return headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}

	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_JIECHUGUIHUAN;
	}

	@Override
	public String getBillid() {
		return headId;
	}

	@Override
	public String getBillno() {
		return billNo;
	}

	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/out/outBack.vm?user_action=toEditOutBack&headid="
				+ headId;
	}

	@Override
	public String getBeanName() {
		return "outBackDao";
	}

	public String getOutOrgId() {
		return outOrgId;
	}

	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}
}