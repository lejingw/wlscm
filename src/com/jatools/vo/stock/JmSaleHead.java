package com.jatools.vo.stock;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class JmSaleHead implements ReviewBill{

	private String billid     ;
	private String billno     ;
	private String dodate     ;
	private String orgId      ;
	private String stockId    ;
	private String sumCount   ;
	private String sumWeight  ;
	private String sumGrains  ;
	private String sumCost    ;
	private String sumMoney   ;
	private String sumJmmoney ;
	private String status     ;
	private String memo       ;
	private String createDate ;
	private String createId   ;
	private String updateDate ;
	private String updateId   ;
	private String checkDate  ;
	private String checkId    ;
	
	public String getDodate() {
		return dodate;
	}

	public void setDodate(String dodate) {
		this.dodate = dodate;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
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

	public String getSumJmmoney() {
		return sumJmmoney;
	}

	public void setSumJmmoney(String sumJmmoney) {
		this.sumJmmoney = sumJmmoney;
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

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_JM_SALE;
	}

	@Override
	public String getBillid() {
		return this.billid;
	}

	@Override
	public String getBillno() {
		return this.billno;
	}

	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/stock/jmSale.vm?user_action=toEdit&billid=" + billid;
	}

	@Override
	public String getBeanName() {
		return "jmSaleDao";
	}

	@Override
	public String getOrgId() {
		return this.orgId;
	}

}
