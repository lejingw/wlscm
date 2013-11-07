package com.jatools.vo.tag;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class LabelTagApplyPrint implements ReviewBill{

	private String id	;//	id
	private String no	;//	编码
	private String createdate	;//	创建日期
	private String createuserid	;//	创建人id
	private String orgId	;//	创建组织
	private String numTotal	;//	件数合计
	private String receiveNum	;//	已接收件数
	private String cancelNum	;//	取消件数
	private String updateuserid	;//	更新人
	private String updatedate	;//	更新日期
	private String labelReceiveType	;//	标签接收方式
	private String receiveUserid	;//	接收人
	private String state	;//	单据状态
	private String printType	;//	打印类型
	private String srcBillNo	;//	来原单据id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}
	public String getReceiveNum() {
		return receiveNum;
	}
	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}
	public String getCancelNum() {
		return cancelNum;
	}
	public void setCancelNum(String cancelNum) {
		this.cancelNum = cancelNum;
	}
	public String getUpdateuserid() {
		return updateuserid;
	}
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getLabelReceiveType() {
		return labelReceiveType;
	}
	public void setLabelReceiveType(String labelReceiveType) {
		this.labelReceiveType = labelReceiveType;
	}
	public String getReceiveUserid() {
		return receiveUserid;
	}
	public void setReceiveUserid(String receiveUserid) {
		this.receiveUserid = receiveUserid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getSrcBillNo() {
		return srcBillNo;
	}
	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_LABEL_PRINT;
	}
	@Override
	public String getBillid() {
		return this.id;
	}
	@Override
	public String getBillno() {
		return this.no;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/tag/labelTagApplyPrint.vm?user_action=toEdit&id=" + this.id;
	}
	@Override
	public String getBeanName() {
		return "labelTagApplyPrintDao";
	}

}
