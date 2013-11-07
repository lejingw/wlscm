package com.jatools.manager.pur.cash.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.pur.cash.CashPayChangeDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.pur.cash.CashPayChangeManager;
import com.jatools.vo.pur.cash.CashPayChange;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class CashPayChangeManagerImpl extends BaseManager implements CashPayChangeManager {

	private CashPayChangeDao cashPayChangeDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public void setCashPayChangeDao(CashPayChangeDao cashPayChangeDao) {
		this.cashPayChangeDao = cashPayChangeDao;
	}

	@Override
	public Pager getCashPayChangeData(Map<String, String> condition) {
		return this.cashPayChangeDao.getCashPayChangeData(condition);
	}

	@Override
	public void saveCashPayChange(CashPayChange cashPayChange) {
		this.cashPayChangeDao.saveCashPayChange(cashPayChange);
	}

	@Override
	public CashPayChange getCashPayChange(String chaId) {
		return this.cashPayChangeDao.getCashPayChange(chaId);
	}

	@Override
	public void updateCashPayChange(CashPayChange cashPayChange) {
		this.cashPayChangeDao.updateCashPayChange(cashPayChange);
	}

	@Override
	public void saveOrUpdateCashPayChange(CashPayChange cashPayChange,
			String userId) {
		cashPayChange.setUpdateDate(DateUtil.getCurrentDate18());
		cashPayChange.setUpdateId(userId);
		if(StringUtil.isNotBlank(cashPayChange.getChaId())){
			asertStatus("jat_cash_pay_change", "cha_id", cashPayChange.getChaId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.cashPayChangeDao.updateCashPayChange(cashPayChange);
		} else {
			cashPayChange.setCreateDate(DateUtil.getCurrentDate18());
			cashPayChange.setCreateId(userId);
			this.cashPayChangeDao.saveCashPayChange(cashPayChange);
		}

	}

	public void saveAndCheckCashPayChange(CashPayChange cashPayChange, String userId) {
		cashPayChange.setUpdateDate(DateUtil.getCurrentDate18());
		cashPayChange.setUpdateId(userId);
		cashPayChange.setStatus(DictConstant.BILL_STATUS_REVIEWING);
		if(StringUtil.isNotBlank(cashPayChange.getChaId())){
			asertStatus("jat_cash_pay_change", "cha_id", cashPayChange.getChaId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.cashPayChangeDao.updateCashPayChange(cashPayChange);
		} else {
			cashPayChange.setCreateDate(DateUtil.getCurrentDate18());
			cashPayChange.setCreateId(userId);
			this.cashPayChangeDao.saveCashPayChange(cashPayChange);
		}

		workflowService.enterReview(cashPayChange, userId);
	}
	
	@Override
	public void modifyCashPayChangeStatus(String chaId, String status,
			String userId) {
		this.cashPayChangeDao.modifyCashPayChangeStatus(chaId, status, userId);
	}

	@Override
	public void deleteCashPayChange(String chaId) {
		asertStatus("jat_cash_pay_change", "cha_id", chaId, "status", DictConstant.BILL_STATUS_SAVE);
		this.cashPayChangeDao.deleteCashPayChange(chaId);
	}

	/**
	 * 提交审批
	 * @param cashId
	 * @param userId
	 */
	public void checkProdProd(String chaId, String userId) {
		asertStatus("jat_cash_pay_change", "cha_id", chaId, "status", DictConstant.BILL_STATUS_SAVE);
		this.cashPayChangeDao.modifyCashPayChangeStatus(chaId, DictConstant.BILL_STATUS_REVIEWING, userId);
		CashPayChange payChange = this.cashPayChangeDao.getCashPayChange(chaId);
		//进入审批
		workflowService.enterReview(payChange, userId);
	}

	@Override
	public void closeBill(String chaId, String userId) {
		asertStatus("jat_cash_pay_change", "cha_id", chaId, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.cashPayChangeDao.modifyCashPayChangeStatus(chaId, DictConstant.BILL_STATUS_CLOSED, userId);
		this.cashPayChangeDao.insertPayAccountByChaId(chaId, userId);
	}
	
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
