package com.jatools.vo.pur.cash;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class CashHead implements ReviewBill{

	private String cashId	;//	n			
	private String billNo	;//(20)	y			单据编号
	private String billDate	;//(20)	y			单据时间
	private String orgId	;//	y			采购组织
	private String voderId	;//	y			供应商
	private String materialType	;//(20)	y			原料类型
	private String status	;//	y			状态
	private String memo	;//(1000)	y			备注
	private String createDate	;//(20)	y			创建时间
	private String createId	;//	y			创建人员
	private String updateDate	;//(20)	y			最后修改时间
	private String updateId	;//	y			最后修改人员
	
	private String dotype;//	number	y			业务类型
	private Double cashNums;//	number	y			结算数量
	
	public String getDotype() {
		return dotype;
	}
	public void setDotype(String dotype) {
		this.dotype = dotype;
	}
	public Double getCashNums() {
		return cashNums;
	}
	public void setCashNums(Double cashNums) {
		this.cashNums = cashNums;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
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
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
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
		return GlobalConstant.BILL_CODE_JISUANDAN;
	}
	@Override
	public String getBillid() {
		return this.cashId;
	}
	@Override
	public String getBillno() {
		return this.billNo;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/pur/cash/cashHead.vm?user_action=toEdit&cashId=" + this.cashId;
	}
	@Override
	public String getBeanName() {
		return "cashHeadDao";
	}
	
}
