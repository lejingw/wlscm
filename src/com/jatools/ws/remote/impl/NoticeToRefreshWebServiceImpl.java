package com.jatools.ws.remote.impl;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.jatools.ws.client.NoticeToRefreshRemoteClient;
import com.jatools.ws.remote.NoticeToRefreshWebService;

public class NoticeToRefreshWebServiceImpl implements NoticeToRefreshWebService {
	private String webServiceUrl;

	public void setWebServiceUrl(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	/**
	 * 通知缓存刷新
	 */
	public String noticeToRefreshCahce(String msgKey, String appKey, String lineName) {
		try {
			Service srvcModel = new ObjectServiceFactory().create(NoticeToRefreshRemoteClient.class);
			XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
			NoticeToRefreshRemoteClient srvc = (NoticeToRefreshRemoteClient) factory.create(srvcModel, webServiceUrl);
			int result = srvc.noticeToRefresh(msgKey, appKey, lineName);
			return null;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
