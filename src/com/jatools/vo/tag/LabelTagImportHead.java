package com.jatools.vo.tag;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class LabelTagImportHead implements ReviewBill{

	private String headid      ;
	private String billno      ;
	private String orgId      ;
	private String printType  ;
	private String memo	    ;
	private String createid	;
	private String createdate  ;
	private String updateid    ;
	private String updatedate	;
	private String status	    ;
	private String counts;
	
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
		this.createid = createid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdateid() {
		return updateid;
	}
	public void setUpdateid(String updateid) {
		this.updateid = updateid;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_LABEL_IMPORT;
	}
	@Override
	public String getBillid() {
		return this.headid;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/tag/labelTagImport.vm?user_action=toEdit&headid=" + this.headid;
	}
	@Override
	public String getBeanName() {
		return "labelTagImportHeadDao";
	}
	
	
}
