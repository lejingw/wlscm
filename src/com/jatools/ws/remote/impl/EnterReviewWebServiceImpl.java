package com.jatools.ws.remote.impl;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import com.jatools.ws.client.EnterReviewRemoteClient;
import com.jatools.ws.remote.EnterReviewWebService;
import com.jatools.ws.review.vo.ReviewInfo;

public class EnterReviewWebServiceImpl implements EnterReviewWebService{
	private String webServiceUrl;
	
	public void setWebServiceUrl(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	/**
	 * 进入审批
	 * @param reviewBean
	 * @return
	 */
	public String doEnterReview(ReviewInfo reviewBean) {
		String result = null;
		try {
			Service srvcModel = new ObjectServiceFactory().create(EnterReviewRemoteClient.class);
			XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
			EnterReviewRemoteClient srvc = (EnterReviewRemoteClient) factory.create(srvcModel, webServiceUrl);
			result = srvc.doEnterReview(reviewBean);
		} catch (Exception e) {
			result = null;
		}
		System.out.print(result);
		return result;
	}
}
