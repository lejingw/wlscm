package com.jatools.manager.sys;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.util.ReviewLog;

public interface BillReviewManager {

	/**
	 * 获取审批单据分页数据
	 * @param condition
	 * @return
	 */
	public Pager getBillReviewPageData(Map<String, String> condition);

	/**
	 * 获取审批记录
	 * @param logid
	 * @return
	 */
	public ReviewLog getReviewLog(String logid);

	/**
	 * 审批单据
	 * @param logid
	 * @param flag
	 */
	public void reviewSuccess(String logid, boolean flag, String userid);
}
