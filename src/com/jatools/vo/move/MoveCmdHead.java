package com.jatools.vo.move;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class MoveCmdHead implements ReviewBill {

	private String headid;
	private String billno;
	private String dodate;
	private String outOrgId;
	private String outStockId;
	private String inOrgId;
	private String inStockId;
	private String sumCount;
	private String sumWeight;
	private String sumGrains;
	private String sumCost;
	private String sumMoney;
	private String status;
	private String memo;
	private String createDate;
	private String createId;
	private String updateDate;
	private String updateId;
	public String getHeadid() {
		return headid;
	}
	public void setHeadid(String headid) {
		this.headid = headid;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getDodate() {
		return dodate;
	}
	public void setDodate(String dodate) {
		this.dodate = dodate;
	}
	public String getOutOrgId() {
		return outOrgId;
	}
	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}
	public String getOutStockId() {
		return outStockId;
	}
	public void setOutStockId(String outStockId) {
		this.outStockId = outStockId;
	}
	public String getInOrgId() {
		return inOrgId;
	}
	public void setInOrgId(String inOrgId) {
		this.inOrgId = inOrgId;
	}
	public String getInStockId() {
		return inStockId;
	}
	public void setInStockId(String inStockId) {
		this.inStockId = inStockId;
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
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_MOVE_CMD;
	}
	@Override
	public String getBillid() {
		return this.headid;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT  + "/move/moveCmd.vm?user_action=toEdit&headid=" + headid;
	}
	@Override
	public String getBeanName() {
		return "moveCmdDao";
	}
	@Override
	public String getOrgId() {
		return this.inOrgId;
	}
	
	private String ornaCodes;
	
	public String getOrnaCodes() {
		return ornaCodes;
	}
	public void setOrnaCodes(String ornaCodes) {
		this.ornaCodes = ornaCodes;
	}
	
}
