package com.jatools.ws.client;

public interface ViewReviewLogRemoteClient {
	/**
	 * 本地模拟，获取审批记录
	 */
	public String getReviewLog(String billCode, String billId);
	/**
	 * webservice
	 */
	public String getReviewLogPageData(Long billId, String modelCode);
}
