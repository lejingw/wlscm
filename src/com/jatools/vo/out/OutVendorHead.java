package com.jatools.vo.out;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.web.util.StringUtil;

public class OutVendorHead implements ReviewBill {
	private String headId;
	private String billNo;
	private String doDate;//业务时间
	private String orgId;
	private String stockId;
	private String vendorId;//供应商
	private String doType;//类型，0对公销售  1代销退货  2委外退料
	private String materialType;//发料类型 来源于数据字典
	private String itemClassId;//发料大类
	private String saleMoney;//销售金额
	private String invalid;//作废标志
	private String sumCount;//总件数
	private String sumWeight;
	private String sumGrains;//总粒数
	private String sumCost;//总成本
	private String sumMoney;
	private String status;//状态
	private String isCheck;//财务复核标志
	private String isFlag;//核算接口标志
	private String memo;//备注
	private String createDate;//创建时间
	private String createId;//创建人
	private String updateDate;
	private String updateId;
	private String checkDate;//审核时间
	private String checkId;
	private String cancelDate;//撤单时间
	private String cancelId;
	private String billType;
	private String lossMoney;
	private String purBillNo;//PUR_BILLNO; 采购单号
	public String getPurBillNo() {
		return purBillNo;
	}
	public void setPurBillNo(String purBillNo) {
		this.purBillNo = purBillNo;
	}
	public String getLossMoney() {
		return lossMoney;
	}
	public void setLossMoney(String lossMoney) {
		this.lossMoney = lossMoney;
	}
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String billId) {
		this.headId = billId;
	}
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
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(String saleMoney) {
		this.saleMoney = saleMoney;
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
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
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
	public String getBillCode() {
		if (StringUtil.equals("0", doType)) {
			return GlobalConstant.BILL_CODE_DIUGONG;
		} else if (StringUtil.equals("1", doType)) {
			return GlobalConstant.BILL_CODE_DAIXIAO;
		} else if (StringUtil.equals("2", doType)) {
			return GlobalConstant.BILL_CODE_WEIWAI;
		} 
		return null;
	}
	@Override
	public String getPageUrl() {
		if (StringUtil.equals("0", doType)) {
			return Global.CONTEXT  + "/out/outVendor.vm?user_action=toEditPubsale&headid=" + headId;
		} else if (StringUtil.equals("1", doType)) {
			return Global.CONTEXT  + "/out/outVendor.vm?user_action=toEditPsale&headid=" + headId;
		} else if (StringUtil.equals("2", doType)) {
			return Global.CONTEXT  + "/out/outVendor.vm?user_action=toEditEntrustout&headid=" + headId;
		}
		return null;
	}
	@Override
	public String getBeanName() {
		return "outVendorDao";
	}
	
	private String isBill;
	public String getIsBill() {
		return isBill;
	}
	public void setIsBill(String isBill) {
		this.isBill = isBill;
	}
	
}
