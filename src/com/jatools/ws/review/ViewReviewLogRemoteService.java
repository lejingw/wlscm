package com.jatools.ws.review;


public interface ViewReviewLogRemoteService {
	/**
	 * 本地模拟，获取审批记录
	 */
	public String getReviewLog(String billCode, String billId);
}
