package com.jatools.manager.basic.impl;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.basic.PriceLockDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.basic.PriceLockManager;
import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.basic.PriceLockLine;
import com.jatools.ws.remote.WorkflowService;
import com.opensymphony.oscache.util.StringUtil;

public class PriceLockManagerImpl extends BaseManager implements PriceLockManager {
	private PriceLockDao priceLockDao;
	private CommonDao commonDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setPriceLockDao(PriceLockDao priceLockDao) {
		this.priceLockDao = priceLockDao;
	}

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPriceLockPageData(Map<String, String> condition, String orgId){
		return priceLockDao.getPriceLockPageData(condition, orgId);
	}

	/**
	 * 删除记录
	 * @param headid
	 */
	public void deletePriceLock(String headid){
		asertStatus("jat_basic_price_lock_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
		priceLockDao.deletePriceLockHead(headid);
		priceLockDao.deletePriceLockLineByHeadid(headid);
	}
	
	public PriceLockHead getPriceLockHead(String headid){
		return priceLockDao.getPriceLockHead(headid);
	}

	public List<PriceLockLine> getPriceLockLine(String headid){
		return priceLockDao.getPriceLockLine(headid);
	}
	
	/**
	 * 保存
	 * @param orgId
	 * @param addUserIds
	 * @param deleteReceIds
	 */
	public void savePriceLock(PriceLockHead head, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, String userid){
		String headid = head.getHeadid();
		if(StringUtil.isEmpty(headid)){
			head.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_PRICE_LOCK));
			headid = priceLockDao.savePriceLockHead(head, userid);
		}else{
			asertStatus("jat_basic_price_lock_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
			priceLockDao.updatePriceLockHead(head, userid);
			priceLockDao.deletePriceLockLineByOrnaCode(deleteOrnaCodeList, headid);
		}
		priceLockDao.savePriceLockLine(newOrnaCodeList, headid, userid);
		if(DictConstant.BILL_STATUS_REVIEWING.equals(head.getStatus())){
			workflowService.enterReview(head, userid);
		}
	}
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public PriceLockLine getMaterActiveInfo(String code, boolean ornaFlag){
		return priceLockDao.getMaterActiveInfo(code, ornaFlag);
	}
	/**
	 * 获取价格锁定数据
	 */
	public Pager queryPriceLockPageData(Map<String, String> condition){
		return priceLockDao.queryPriceLockPageData(condition);
	}
}