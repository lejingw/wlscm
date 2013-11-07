package com.jatools.vo.pur.cash;

import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.web.util.StringUtil;

public class CashProdChange implements ReviewBill{
	private String chaId	;//	n			
	private String billNo	;//(20)	y			单据编号
	private String billDate	;//(20)	y			单据时间
	private String orgId	;//	y			采购组织
	private String voderId	;//	y			供应商
	private String dotype	;//	y			0期初数据1调整单据
	private String materialType	;//(20)	y			原料类型
	private String billType	;//	y			交接退料-1  委外发料1
	private String itemClassId	;//	y			大类id
	private String itemClassName;
	private String cashUnit;
	private Double chaNums	;//	y			调整数量
	private String status	;//	y			状态
	private String memo	;//(1000)	y			备注
	private String createDate	;//(20)	y			创建时间
	private String createId	;//	y			创建人员
	private String updateDate	;//(20)	y			最后修改时间
	private String updateId	;//	y			最后修改人员
	
	private String articleTypeDsc;
	
	private String ornaCode;
	
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getArticleTypeDsc() {
		return articleTypeDsc;
	}
	public void setArticleTypeDsc(String articleTypeDsc) {
		this.articleTypeDsc = articleTypeDsc;
	}
	public String getCashUnit() {
		return cashUnit;
	}
	public void setCashUnit(String cashUnit) {
		this.cashUnit = cashUnit;
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
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
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public Double getChaNums() {
		return chaNums;
	}
	public void setChaNums(Double chaNums) {
		this.chaNums = chaNums;
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
		return GlobalConstant.BILL_CODE_TIAOZHENGDAN_HP;
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
		return StringUtil.getContextPath() + "/pur/cash/prodChange.vm?user_action=toEdit&chaId="+this.chaId;
	}
	@Override
	public String getBeanName() {
		return "cashProdChangeDao";
	}
	
}
