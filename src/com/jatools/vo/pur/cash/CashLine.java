package com.jatools.vo.pur.cash;

public class CashLine {
	private String lineId	;//	n			
	private String cashId	;//	y			单据id
	private String prId	;//	y			台帐id
	private String billType	;//	y			交接退料-1  委外发料1
	private Double lessNums	;//	y			须结算数量
	private Double curNums	;//	y			本次结算数量
	private String status	;//	y			状态
	private String memo	;//(1000)	y			备注
	private String createDate	;//(20)	y			创建时间
	private String createId	;//	y			创建人员
	private String updateDate	;//(20)	y			最后修改时间
	private String updateId	;//	y			最后修改人员
	
	private String billNo;
	private String itemClassId;
	private String articleId;
	private Double oldCurNums;
	
	public Double getOldCurNums() {
		return oldCurNums;
	}
	public void setOldCurNums(Double oldCurNums) {
		this.oldCurNums = oldCurNums;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
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
	public Double getLessNums() {
		return lessNums;
	}
	public void setLessNums(Double lessNums) {
		this.lessNums = lessNums;
	}
	public Double getCurNums() {
		return curNums;
	}
	public void setCurNums(Double curNums) {
		this.curNums = curNums;
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
