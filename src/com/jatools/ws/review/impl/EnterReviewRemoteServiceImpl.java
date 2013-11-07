package com.jatools.ws.review.impl;

import org.apache.log4j.Logger;
import com.jatools.ws.review.EnterReviewRemoteService;

public class EnterReviewRemoteServiceImpl implements EnterReviewRemoteService {
	private Logger logger = Logger.getLogger(EnterReviewRemoteServiceImpl.class);
	/**
	 * 本地模拟，进入审批
	 */
	public void enterReviewRemote(String agent, String billCode, String billName, String billId, String billNo, String pageUrl) {
		logger.debug("------------------本地模拟，进入审批---------------------");
	}
}
