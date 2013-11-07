package com.jatools.vo.tag;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class LabelTagApply implements ReviewBill{

	private String id	;//	tagApplyBillId
	private String no	;//	tagApplyBillCode
	private String createdate	;//	创建日期
	private String createuserid	;//	创建人id
	private String orgId	;//	创建组织
	private String tagPrintBillId	;//	标签打印单据号
	private String numTotal	;//	件数合计
	private String changelabelId	;//	换标签原因
	private String state	;//	单据状态
	private String updatedate	;//	updatedate
	
	private String printNo;
	
	private String changelabelName;
	
	public String getChangelabelName() {
		return changelabelName;
	}
	public void setChangelabelName(String changelabelName) {
		this.changelabelName = changelabelName;
	}
	public String getPrintNo() {
		return printNo;
	}
	public void setPrintNo(String printNo) {
		this.printNo = printNo;
	}
	
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
	public String getTagPrintBillId() {
		return tagPrintBillId;
	}
	public void setTagPrintBillId(String tagPrintBillId) {
		this.tagPrintBillId = tagPrintBillId;
	}
	public String getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}
	public String getChangelabelId() {
		return changelabelId;
	}
	public void setChangelabelId(String changelabelId) {
		this.changelabelId = changelabelId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_LABEL_APPLY;
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
		return Global.CONTEXT + "/tag/labelTagApply.vm?user_action=toEdit&id=" + this.id;
	}
	@Override
	public String getBeanName() {
		return "labelTagApplyDao";
	}


}
