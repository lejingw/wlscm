package com.jatools.ws.remote;



public interface ViewReviewLogWebService {
	/**
	 * 获取审批记录
	 * @param billId
	 * @param moduleCode
	 * @return
	 */
	public String getReviewLogPageData(Long billId, String moduleCode);
}
