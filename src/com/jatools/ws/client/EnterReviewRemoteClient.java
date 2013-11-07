package com.jatools.ws.client;

import com.jatools.ws.review.vo.ReviewInfo;

public interface EnterReviewRemoteClient {
	/**
	 * 本地模拟，进入审批
	 */
	public void enterReviewRemote(String agent, String billCode, String billName, String billId, String billNo, String pageUrl);
	/**
	 * webservice
	 */
	public String doEnterReview(ReviewInfo reviewBean);
}
