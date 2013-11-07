package com.jatools.vo.pur.cash;

import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.web.util.StringUtil;

public class CashPayChange implements ReviewBill{

	private String chaId	;//	n			
	private String billNo	;//(20)	y			单据编号
	private String billDate	;//(20)	y			单据时间
	private String orgId	;//	y			采购组织
	private String voderId	;//	y			供应商
	private String dotype	;//	y			0期初数据1调整单据
	private String billType	;//	y			应收-1  应付1
	private Double chaMoney	;//	y			调整金额
	private String status	;//	y			状态
	private String memo	;//(1000)	y			备注
	private String createDate	;//(20)	y			创建时间
	private String createId	;//	y			创建人员
	private String updateDate	;//(20)	y			最后修改时间
	private String updateId	;//	y			最后修改人员
	public String getChaId() {
		return chaId;
	}
	public void setChaId(String chaId) {
		this.chaId = chaId;
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
	public String getDotype() {
		return dotype;
	}
	public void setDotype(String dotype) {
		this.dotype = dotype;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public Double getChaMoney() {
		return chaMoney;
	}
	public void setChaMoney(Double chaMoney) {
		this.chaMoney = chaMoney;
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
		return GlobalConstant.BILL_CODE_TIAOZHENGDAN_ZJ;
	}
	@Override
	public String getBillid() {
		return this.chaId;
	}
	@Override
	public String getBillno() {
		return this.billNo;
	}
	@Override
	public String getPageUrl() {
		return StringUtil.getContextPath() + "/pur/cash/payChange.vm?user_action=toEdit&chaId="+this.chaId;
	}
	@Override
	public String getBeanName() {
		return "cashPayChangeDao";
	}
	
}
