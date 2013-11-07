package com.jatools.ws.client;

public interface NoticeToRefreshRemoteClient {
	/**
	 * 通知缓存刷新
	 */
	public int noticeToRefresh(String msgKey, String appKey, String lineName);
}
