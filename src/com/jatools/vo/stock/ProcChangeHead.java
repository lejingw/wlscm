package com.jatools.vo.stock;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

/**
 * 形态转换 头表
 * @author ren.ming
 * Created 2011-11-28
 */
public class ProcChangeHead implements ReviewBill{

	private String billid			;//		单据id
	private String billno			;//		单据号
	private String dodate			;//		业务时间
	private String orgId			;//		库存组织
	private String stockId		;//			仓库
	private String dotype			;//		业务类型 0 形态转换1 料提纯 2拆石
	private String invalid			;//		作废标志
	private String sumCount		;//			总件数
	private String sumWeight	;//				总重量
	private String sumGrains	;//				总粒数
	private String sumCost		;//			总成本
	private String sumMoney		;//			总金额
	private String status			;//		状态
	private String isCheck		;//			财务复核标志
	private String isFlag			;//		核算接口标志
	private String memo				;//	备注
	private String createDate	;//				创建时间
	private String createId		;//			创建人员
	private String updateDate	;//				最后修改时间
	private String updateId		;//			最后修改人员
	private String checkDate	;//				审核时间
	private String checkId		;//			审核人员
	private String cancelDate	;//				撤单时间
	private String cancelId		;//			撤单人员
	private String isPack		;//是否装箱
	private String packNo		;//装箱单号
	
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
	public String getDotype() {
		return dotype;
	}
	public void setDotype(String dotype) {
		this.dotype = dotype;
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
	
	public String getIsPack() {
		return isPack;
	}
	public void setIsPack(String isPack) {
		this.isPack = isPack;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	@Override
	public String getBillCode() {
		if("0".equals(this.dotype)){
			return GlobalConstant.BILL_CODE_XINGTAI;
		} else if("1".equals(this.dotype)){
			return GlobalConstant.BILL_CODE_TICHUN;
		} else if("2".equals(this.dotype)){
			return GlobalConstant.BILL_CODE_CHAISHI;
		}
		return null;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/stock/procChange.vm?user_action=toEdit&billid=" + billid;
	}
	@Override
	public String getBeanName() {
		return "procChangeHeadDao";
	}
	
}
