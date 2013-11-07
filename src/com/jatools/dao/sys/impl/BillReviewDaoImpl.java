package com.jatools.dao.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.sys.BillReviewDao;
import com.jatools.vo.util.ReviewLog;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class BillReviewDaoImpl extends BaseDao implements BillReviewDao{
	/**
	 * 保存审批记录
	 * @param wlscmAgent
	 * @param billCode
	 * @param billName
	 * @param billId
	 * @param billNo
	 * @param pageUrl
	 */
	public void saveReviewLog(ReviewLog reviewLog){
		executeInsert("BillReview.saveReviewLog", reviewLog);
	}

	/**
	 * 获取审批单据分页数据
	 * @param condition
	 * @return
	 */
	public Pager getBillReviewPageData(Map<String, String> condition){
		return executeQueryForPager("BillReview.getBillReviewPageData", "BillReview.getBillReviewTotalCount", condition);
	}
	/**
	 * 获取审批记录
	 * @param logid
	 * @return
	 */
	public ReviewLog getReviewLog(String logid){
		return (ReviewLog)executeQueryForObject("BillReview.getReviewLog", logid);
	}
	/**
	 * 审批单据
	 * @param logid
	 * @param flag
	 */
	public void reviewSuccess(String logid, boolean successFlag, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("logid", logid);
		condition.put("finishFlag", successFlag?"1":"2");
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("BillReview.reviewSuccess", condition);
	}
	/**
	 * 根据单据编码和单据id，获取当前正在审批的记录
	 * @param billCode
	 * @param billId
	 * @return
	 */
	public List<ReviewLog> getReviewLogByBillCodeAndId(String billCode, String billId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billCode", billCode);
		condition.put("billId", billId);
		return executeQueryForList("BillReview.getReviewLogByBillCodeAndId", condition);
	}
	/**
	 * 根据单据编码获取模块编码
	 * @param billCode
	 * @return
	 */
	public String getModelCodeByBillCode(String billCode){
		return (String) executeQueryForObject("BillReview.getModelCodeByBillCode", billCode);
	}
	/**
	 * 根据模块编码获取单据编码
	 * @param modelCode
	 * @return
	 */
	public String getBillCodeByModelCode(String modelCode){
		return  (String) executeQueryForObject("BillReview.getBillCodeByModelCode", modelCode);
	}
	/**
	 * 获取审批记录
	 * @param billCode
	 * @param billId
	 * @return
	 */
	public List<Map<String, String>> getReviewLog2(String billCode, String billId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billCode", billCode);
		condition.put("billId", billId);
		return executeQueryForList("BillReview.getReviewLog2", condition);
	}
}
