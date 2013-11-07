package com.jatools.vo.out;

public class CashProdAccount {
	private String prId					;// 
	private String billType			;// 		单据类型
	private String billNo				;// 	单据编号
	private String billDate			;// 		单据时间
	private String orgId					;// 采购组织
	private String dotype					;// 交接退料-1  委外发料1
	private String voderId				;// 	供应商
	private String materialType	;// 				原料类型
	private String itemClassId	;// 				大类id
	private String cashUnit			;// 		结算单位
	private String billNums			;// 		单据数量
	private String cashNums			;// 		已结算数量
	private String noNums				;// 	未结算数量
	private String isChecked			;// 		是否核销完成
	private String status					;// 状态
	private String memo					  ;// 备注
	private String createDate		;// 			创建时间
	private String createId			;// 		创建人员
	private String updateDate		;// 			最后修改时间
	private String updateId			;// 		最后修改人员
	private String materialTypeName;
	private String articleTypeDsc;// 商品类别
	
	private String userNums;
	private String oldCurNums;
	
	private Double calcNums;
	private String calcHeadId;
	
	private String lineId;// 供结算使用
	private String itemClassName;
	private String articleTypeId;
	private String articleTypeName;
	
	private String srcBillNo;// 来源单据编号
	private String srcBillType;// 来源单据类型
	private String cashBillNo;// 结算单据编号
	private String cashDate;// 结算时间
	
	public Double getCalcNums() {
		return calcNums;
	}
	public void setCalcNums(Double calcNums) {
		this.calcNums = calcNums;
	}
	public String getCalcHeadId() {
		return calcHeadId;
	}
	public void setCalcHeadId(String calcHeadId) {
		this.calcHeadId = calcHeadId;
	}
	public String getOldCurNums() {
		return oldCurNums;
	}
	public void setOldCurNums(String oldCurNums) {
		this.oldCurNums = oldCurNums;
	}
	public String getUserNums() {
		return userNums;
	}
	public void setUserNums(String userNums) {
		this.userNums = userNums;
	}
	public String getArticleTypeDsc() {
		return articleTypeDsc;
	}
	public void setArticleTypeDsc(String articleTypeDsc) {
		this.articleTypeDsc = articleTypeDsc;
	}
	public String getSrcBillNo() {
		return srcBillNo;
	}
	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	public String getSrcBillType() {
		return srcBillType;
	}
	public void setSrcBillType(String srcBillType) {
		this.srcBillType = srcBillType;
	}
	public String getCashBillNo() {
		return cashBillNo;
	}
	public void setCashBillNo(String cashBillNo) {
		this.cashBillNo = cashBillNo;
	}
	public String getCashDate() {
		return cashDate;
	}
	public void setCashDate(String cashDate) {
		this.cashDate = cashDate;
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
	public String getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(String articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
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
	public String getDotype() {
		return dotype;
	}
	public void setDotype(String dotype) {
		this.dotype = dotype;
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
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getCashUnit() {
		return cashUnit;
	}
	public void setCashUnit(String cashUnit) {
		this.cashUnit = cashUnit;
	}
	public String getBillNums() {
		return billNums;
	}
	public void setBillNums(String billNums) {
		this.billNums = billNums;
	}
	public String getCashNums() {
		return cashNums;
	}
	public void setCashNums(String cashNums) {
		this.cashNums = cashNums;
	}
	public String getNoNums() {
		return noNums;
	}
	public void setNoNums(String noNums) {
		this.noNums = noNums;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
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
}
