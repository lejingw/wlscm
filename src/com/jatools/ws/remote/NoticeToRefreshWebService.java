package com.jatools.ws.remote;

public interface NoticeToRefreshWebService {
	/**
	 * 通知缓存刷新
	 */
	public String noticeToRefreshCahce(String msgKey, String appKey, String lineName);
}
