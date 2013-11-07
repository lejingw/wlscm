package com.jatools.manager.util.impl;

import java.util.List;
import java.util.Map;

import com.jatools.dao.util.CommonDao;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.Org;
import com.jatools.ws.remote.WorkflowService;

public class CommonManagerImpl implements CommonManager {
	private CommonDao commonDao;
	private WorkflowService workflowService;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	/**
	 * 获取单据编号
	 */
	public synchronized String getBillno(String billCode){
		return commonDao.getBillno(billCode);
	}
	/**
	 * 根据单据编码获取对应财务关系
	 */
	public String getBillFinance(String billCode){
		return commonDao.getBillFinance(billCode);
	}
	
	/**
	 * 获取分析范围
	 * @param itemClassId 大类id
	 * @param ornaClassId 小类id
	 * @param allQty 总重量
	 * @param basicPrice 销价
	 * @param mainWeight 主石重量
	 * @return
	 */
	public Analysis getAnalysis(String itemClassId, String ornaClassId, String allQty, String basicPrice, String mainWeight){
		return commonDao.getAnalysis(itemClassId, ornaClassId, allQty, basicPrice, mainWeight);
	}
	/**
	 * 生成饰品编码
	 * @return
	 */
	public String getOrnaCode(String inivCode, String itemClassId, String ornaClassId){
		return commonDao.getOrnaCode(inivCode, itemClassId, ornaClassId);
	}
	/**
	 * 生成裸石饰品编码
	 * @return
	 */
	public synchronized String getOrnaCodeD0(Double avgFenShu){
		return commonDao.getOrnaCodeD0(avgFenShu);
	}
	/**
	 * 生成饰品条码
	 * @return
	 */
	public String getBarCode(String itemClassId, String ornaClassId){
		return commonDao.getBarCode(itemClassId, ornaClassId);
	}
	/**
	 * 获取审批记录
	 * @param billCode
	 * @param billId
	 */
	public List<Map<String, String>> getReviewLog(String billCode, String billId){
		return workflowService.getReviewLog(billCode, billId);
	}
	
	@Override
	public List<Org> getCurrentUserOrgList(String userId) {
		return this.commonDao.getCurrentUserOrgList(userId);
	}

	public List<Org> getCurrentUserJmOrgList(String userId) {
		return this.commonDao.getCurrentUserJmOrgList(userId);
	}
}
