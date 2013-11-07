package com.jatools.manager.pur.cash.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.pur.cash.CashProdChangeDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.pur.cash.CashProdChangeManager;
import com.jatools.vo.pur.cash.CashProdChange;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class CashProdChangeManagerImpl extends BaseManager implements CashProdChangeManager {

	private CashProdChangeDao cashProdChangeDao;
	
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public void setCashProdChangeDao(CashProdChangeDao cashProdChangeDao) {
		this.cashProdChangeDao = cashProdChangeDao;
	}

	@Override
	public Pager getCashProdChangeData(Map<String, String> condition) {
		return this.cashProdChangeDao.getCashProdChangeData(condition);
	}

	@Override
	public void saveCashProdChange(CashProdChange cashProdChange) {
		this.cashProdChangeDao.saveCashProdChange(cashProdChange);
	}

	@Override
	public CashProdChange getCashProdChange(String chaId) {
		return this.cashProdChangeDao.getCashProdChange(chaId);
	}
	@Override
	public CashProdChange getCashProdChangeByBillno(String billno) {
		return this.cashProdChangeDao.getCashProdChangeByBillno(billno);
	}

	@Override
	public void updateCashProdChange(CashProdChange cashProdChange) {
		this.cashProdChangeDao.updateCashProdChange(cashProdChange);
	}

	@Override
	public void saveOrUpdateCashProdChange(CashProdChange cashProdChange,
			String userId) {
		cashProdChange.setUpdateDate(DateUtil.getCurrentDate18());
		cashProdChange.setUpdateId(userId);
		if(StringUtil.isNotBlank(cashProdChange.getChaId())){
			asertStatus("jat_cash_prod_change", "cha_id", cashProdChange.getChaId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.cashProdChangeDao.updateCashProdChange(cashProdChange);
		} else {
			cashProdChange.setCreateDate(DateUtil.getCurrentDate18());
			cashProdChange.setCreateId(userId);
			this.cashProdChangeDao.saveCashProdChange(cashProdChange);
		}
	}

	@Override
	public void saveAndCheckCashProdChange(CashProdChange cashProdChange, String userId) {
		cashProdChange.setUpdateDate(DateUtil.getCurrentDate18());
		cashProdChange.setUpdateId(userId);
		cashProdChange.setStatus(DictConstant.BILL_STATUS_REVIEWING);
		if(StringUtil.isNotBlank(cashProdChange.getChaId())){
			asertStatus("jat_cash_prod_change", "cha_id", cashProdChange.getChaId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.cashProdChangeDao.updateCashProdChange(cashProdChange);
		} else {
			cashProdChange.setCreateDate(DateUtil.getCurrentDate18());
			cashProdChange.setCreateId(userId);
			this.cashProdChangeDao.saveCashProdChange(cashProdChange);
		}
		workflowService.enterReview(cashProdChange, userId);
	}
	
	@Override
	public void modifyCashProdChangeStatus(String chaId, String status,
			String userId) {
		this.cashProdChangeDao.modifyCashProdChangeStatus(chaId, status, userId);
	}

	@Override
	public void deleteCashProdChange(String chaId) {
		asertStatus("jat_cash_prod_change", "cha_id", chaId, "status", DictConstant.BILL_STATUS_SAVE);
		this.cashProdChangeDao.deleteCashProdChange(chaId);
	}

	
	/**
	 * 提交审批
	 * @param cashId
	 * @param userId
	 */
	public void checkProdProd(String chaId, String userId) {
		asertStatus("jat_cash_prod_change", "cha_id", chaId, "status", DictConstant.BILL_STATUS_SAVE);
		this.cashProdChangeDao.modifyCashProdChangeStatus(chaId, DictConstant.BILL_STATUS_REVIEWING, userId);
		CashProdChange prodChange = this.cashProdChangeDao.getCashProdChange(chaId);
		//进入审批
		workflowService.enterReview(prodChange, userId);
	}

	@Override
	public void closeBill(String chaId, String userId) {
		asertStatus("jat_cash_prod_change", "cha_id", chaId, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.cashProdChangeDao.modifyCashProdChangeStatus(chaId, DictConstant.BILL_STATUS_CLOSED, userId);
		this.cashProdChangeDao.insertProdAccountbyChaId(chaId, userId);
		/*if(true){
			throw new RuntimeException("test");
		}*/
	}
	
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
