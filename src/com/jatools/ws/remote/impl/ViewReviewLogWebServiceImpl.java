package com.jatools.ws.remote.impl;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.jatools.ws.client.ViewReviewLogRemoteClient;
import com.jatools.ws.remote.ViewReviewLogWebService;

public class ViewReviewLogWebServiceImpl implements ViewReviewLogWebService {
	private String webServiceUrl;

	public void setWebServiceUrl(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	/**
	 * 获取审批记录
	 * 
	 * @param billId
	 * @param moduleCode
	 * @return
	 */
	public String getReviewLogPageData(Long billId, String moduleCode) {
		String result = null;
		try {
			Service srvcModel = new ObjectServiceFactory().create(ViewReviewLogRemoteClient.class);
			XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
			ViewReviewLogRemoteClient srvc = (ViewReviewLogRemoteClient) factory.create(srvcModel, webServiceUrl);
			result = srvc.getReviewLogPageData(billId, moduleCode);
		} catch (Exception e) {
			result = null;
		}
		 System.out.print(result);
		return result;
	}

}
