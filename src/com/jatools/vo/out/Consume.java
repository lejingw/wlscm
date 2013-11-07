package com.jatools.vo.out;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class Consume implements ReviewBill {
	private String billid;// 
	private String billno;// 
	private String orgId;//
	private String vendorId;// 	供应商
	private Double amount;// 
	private String status;// 状态
	private String memo  ;// 备注
	private String createDate;// 创建时间
	private String createId;// 创建人员
	private String updateDate;// 最后修改时间
	private String updateId;// 最后修改人员
	private String dodate;
	
	public String getDodate() {
		return dodate;
	}
	public void setDodate(String dodate) {
		this.dodate = dodate;
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
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorid) {
		this.vendorId = vendorid;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
		return GlobalConstant.BILL_CODE_CONSUM;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT  + "/out/consum.vm?user_action=toEditConsum&billid=" + this.billid;
	}
	@Override
	public String getBeanName() {
		return "consumeDao";
	}
	@Override
	public String getOrgId() {
		return this.orgId;
	}
	
	
}
