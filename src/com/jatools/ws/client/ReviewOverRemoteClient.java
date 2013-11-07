package com.jatools.ws.client;

public interface ReviewOverRemoteClient {

	/**
	 * 审批结束
	 * @param billCode 单据编码
	 * @param billId 单据id
	 * @param successFlag true审批通过 false审批不通过
	 */
	public void reviewAction(String billCode, String billId, boolean successFlag, String userid);
	/**
	 * webservice审批结束
	 * @param reviewResult 审批结果1成功、2继续、3失败
	 */
	public String reviewCallBack(String moduleCode, Long billId, Long reviewResult, Long reviewUserId, String reviewDate);
}
