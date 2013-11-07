package com.jatools.dao.sys;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.util.ReviewLog;

public interface BillReviewDao {
	/**
	 * 保存审批记录
	 * @param wlscmAgent
	 * @param billCode
	 * @param billName
	 * @param billId
	 * @param billNo
	 * @param pageUrl
	 */
	public void saveReviewLog(ReviewLog reviewLog);

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
	public void reviewSuccess(String logid, boolean successFlag, String userid);

	/**
	 * 根据单据编码和单据id，获取当前正在审批的记录
	 * @param billCode
	 * @param billId
	 * @return
	 */
	public List<ReviewLog> getReviewLogByBillCodeAndId(String billCode, String billId);
	/**
	 * 根据单据编码获取模块编码
	 * @param billCode
	 * @return
	 */
	public String getModelCodeByBillCode(String billCode);
	/**
	 * 根据模块编码获取单据编码
	 * @param modelCode
	 * @return
	 */
	public String getBillCodeByModelCode(String modelCode);
	/**
	 * 获取审批记录
	 * @param billCode
	 * @param billId
	 * @return
	 */
	public List<Map<String, String>> getReviewLog2(String billCode, String billId);
}
