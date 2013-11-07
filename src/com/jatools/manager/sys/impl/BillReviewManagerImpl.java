package com.jatools.manager.sys.impl;

import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.dao.sys.BillReviewDao;
import com.jatools.manager.sys.BillReviewManager;
import com.jatools.vo.util.ReviewLog;
import com.jatools.ws.remote.WorkflowService;

public class BillReviewManagerImpl implements BillReviewManager {
	private BillReviewDao billReviewDao;
	private WorkflowService workflowService;

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setBillReviewDao(BillReviewDao billReviewDao) {
		this.billReviewDao = billReviewDao;
	}
	
	/**
	 * 获取审批单据分页数据
	 * @param condition
	 * @return
	 */
	public Pager getBillReviewPageData(Map<String, String> condition){
		return billReviewDao.getBillReviewPageData(condition);
	}
	/**
	 * 获取审批记录
	 * @param logid
	 * @return
	 */
	public ReviewLog getReviewLog(String logid){
		return billReviewDao.getReviewLog(logid);
	}
	/**
	 * 审批单据
	 * @param logid
	 * @param flag
	 */
	public void reviewSuccess(String logid, boolean flag, String userid){
		ReviewLog reviewLog = getReviewLog(logid);
		workflowService.reviewAction(reviewLog.getBillCode(), reviewLog.getBillId(), flag, userid);
	}
}
