package com.jatools.vo.stock;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

/**
 * 退料头表对象
 * @author ren.ming
 * Created 2011-11-28
 */
public class ProcExitHead implements ReviewBill{

	private String billid	; //	n			单据id
	private String billno	; //(50)	n			单据号
	private String dodate	; //(20)	n			业务时间
	private String orgId	; //	n			组织
	private String vendorId ; // 供应商
	private String stockId	; //	y			仓库
	private String invalid	; //	y	0		作废标志
	private String sumCount	; //	y			总件数
	private String sumWeight	; //	y			总重量
	private String sumGrains	; //	y			总粒数
	private String sumCost	; //	y			总成本
	private String sumMoney	; //	y			总金额
	private String status	; //	y	0		状态
	private String isCheck	; //	y	0		财务复核标志
	private String isFlag	; //	y	0		核算接口标志
	private String memo	; //(1000)	y			备注
	private String createDate	; //(20)	y			创建时间
	private String createId	; //	y			创建人员
	private String updateDate	; //(20)	y			最后修改时间
	private String updateId	; //	y			最后修改人员
	private String checkDate	; //(20)	y			审核时间
	private String checkId	; //	y			审核人员
	private String cancelDate	; //(20)	y			撤单时间
	private String cancelId	; //	y			撤单人员
	
	private String charge;
	
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
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
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_TUILIAO;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/stock/procExit.vm?user_action=toEdit&billid=" + billid;
	}
	@Override
	public String getBeanName() {
		return "procExitHeadDao";
	}
	
}
