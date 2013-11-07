package com.jatools.ws.review.vo;

public class ReviewInfo {
	private Long reviewBillId;
	private String reviewBillNo;
	private String moduleCode;
	private String callBackUrl;
	private String htmlUrl;
	private Long userId;
	private Long financeOrgId;
	private String typeControlValue;
	private Object fieldValue;
	private Object operMethodValues;

	public Long getReviewBillId() {
		return reviewBillId;
	}

	public void setReviewBillId(Long reviewBillId) {
		this.reviewBillId = reviewBillId;
	}

	public String getReviewBillNo() {
		return reviewBillNo;
	}

	public void setReviewBillNo(String reviewBillNo) {
		this.reviewBillNo = reviewBillNo;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFinanceOrgId() {
		return financeOrgId;
	}

	public void setFinanceOrgId(Long financeOrgId) {
		this.financeOrgId = financeOrgId;
	}

	public String getTypeControlValue() {
		return typeControlValue;
	}

	public void setTypeControlValue(String typeControlValue) {
		this.typeControlValue = typeControlValue;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Object getOperMethodValues() {
		return operMethodValues;
	}

	public void setOperMethodValues(Object operMethodValues) {
		this.operMethodValues = operMethodValues;
	}

}
