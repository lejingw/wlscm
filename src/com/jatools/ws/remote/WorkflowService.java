package com.jatools.ws.remote;

import java.util.List;
import java.util.Map;

import com.jatools.common.intf.ReviewBill;

public interface WorkflowService {
	/**
	 * 进入审批
	 * @param reviewBill 审批单据
	 * @param pageUrl 单据页面url
	 */
	public void enterReview(ReviewBill reviewBill, String userid);

	/**
	 * 审批结束
	 * @param billCode
	 * @param billId
	 * @param successFlag true审批通过 false审批不通过
	 */
	public void reviewAction(String billCode, String billId, boolean successFlag, String userid);
	
	/**
	 * 获取审批记录
	 * @param billCode
	 * @param billId
	 */
	public List<Map<String, String>> getReviewLog(String billCode, String billId);

	/**
	 * 刷新加盟系统缓存
	 */
	public void refreshJmCache(String msgKey);
}
