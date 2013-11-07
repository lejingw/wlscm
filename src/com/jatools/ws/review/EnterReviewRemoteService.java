package com.jatools.ws.review;

public interface EnterReviewRemoteService {
	/**
	 * 本地模拟，进入审批
	 */
	public void enterReviewRemote(String agent, String billCode, String billName, String billId, String billNo, String pageUrl);
}
