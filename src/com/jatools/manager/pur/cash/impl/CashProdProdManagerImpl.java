package com.jatools.manager.pur.cash.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.out.CashProdAccountDao;
import com.jatools.dao.pur.cash.CashProdProdDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.pur.cash.CashProdProdManager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.cash.CashProdProd;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class CashProdProdManagerImpl extends BaseManager implements CashProdProdManager {

	private CashProdProdDao cashProdProdDao;
	private CashProdAccountDao cashProdAccountDao;
	
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public void setCashProdProdDao(CashProdProdDao cashProdProdDao) {
		this.cashProdProdDao = cashProdProdDao;
	}

	public void setCashProdAccountDao(CashProdAccountDao cashProdAccountDao) {
		this.cashProdAccountDao = cashProdAccountDao;
	}
	@Override
	public Pager getCashProdProdData(Map<String, String> condition) {
		return this.cashProdProdDao.getCashProdProdData(condition);
	}

	@Override
	public void saveCashProdProd(CashProdProd cashProdProd) {
		this.cashProdProdDao.saveCashProdProd(cashProdProd);
	}

	@Override
	public CashProdProd getCashProdProd(String chaId) {
		return this.cashProdProdDao.getCashProdProd(chaId);
	}

	@Override
	public void updateCashProdProd(CashProdProd cashProdProd) {
		this.cashProdProdDao.updateCashProdProd(cashProdProd);
	}

	@Override
	public void saveOrUpdateCashProdProd(CashProdProd cashProdProd, String userId) {
		this.saveUpdateCheckCashProdProd(cashProdProd, userId, false);
	}
	
	public void saveAndCheckCashProdProd(CashProdProd cashProdProd, String userId) {
		this.saveUpdateCheckCashProdProd(cashProdProd, userId, true);
	}
	
	private void saveUpdateCheckCashProdProd(CashProdProd cashProdProd, String userId, boolean isCheck){
		cashProdProd.setUpdateDate(DateUtil.getCurrentDate18());
		cashProdProd.setUpdateId(userId);
		sumDiff(cashProdProd);
		this.asertProdAccount(cashProdProd.getPrId());
		if(StringUtil.isNotBlank(cashProdProd.getChaId())){
			asertStatus("jat_cash_prod_prod", "cha_id", cashProdProd.getChaId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.cashProdProdDao.updateCashProdProd(cashProdProd);
		} else {
			cashProdProd.setCreateDate(DateUtil.getCurrentDate18());
			cashProdProd.setCreateId(userId);
			this.cashProdProdDao.saveCashProdProd(cashProdProd);
			
			// 修改台账状态为已引用
			this.cashProdAccountDao.modifyProdAccountStatus(cashProdProd.getPrId(), DictConstant.BILL_STATUS_REFERENCE, userId);
		}
		if(isCheck){
			workflowService.enterReview(cashProdProd, userId);
			this.cashProdProdDao.modifyCashProdProdStatus(cashProdProd.getBillid(), DictConstant.BILL_STATUS_REVIEWING, userId);
		}
	}

	private void asertProdAccount(String prId){
		CashProdAccount prod = this.cashProdAccountDao.getCashProdAccount(prId);
		if(prod != null){
			if(StringUtil.isNotBlank(prod.getUserNums()) && Double.valueOf(prod.getUserNums()) > 0){
				throw new RuntimeException("台账正在被其他单据使用中");
			}
		} else {
			throw new RuntimeException("台账不存在");
		}
	}
	private void sumDiff(CashProdProd cashProdProd){
		double weightOld = 0;
		double weightNew = 0;
		double priceOld = 0;
		double priceNew = 0;
		if(null != cashProdProd.getWeightOld()){
			weightOld = cashProdProd.getWeightOld();
		}
		if(null != cashProdProd.getWeightNew()){
			weightNew = cashProdProd.getWeightNew();
		}
		if(null != cashProdProd.getPriceOld()){
			priceOld = cashProdProd.getPriceOld();
		}
		if(null != cashProdProd.getPriceNew()){
			priceNew = cashProdProd.getPriceNew();
		}
		double diff = weightNew*priceNew - weightOld*priceOld;
		cashProdProd.setDiffMoney(diff);
	}
	@Override
	public void modifyCashProdProdStatus(String chaId, String status,
			String userId) {
		this.cashProdProdDao.modifyCashProdProdStatus(chaId, status, userId);
	}

	@Override
	public void deleteCashProdProd(String chaId, String userId) {
		asertStatus("jat_cash_prod_prod", "cha_id", chaId, "status", DictConstant.BILL_STATUS_SAVE);
		this.cashProdProdDao.updateProdAccountStatus(chaId, DictConstant.BILL_STATUS_MARK, userId);
		this.cashProdProdDao.deleteCashProdProd(chaId);
	}

	/**
	 * 提交审批
	 * @param cashId
	 * @param userId
	 */
	public void checkProdProd(String chaId, String userId) {
		asertStatus("jat_cash_prod_prod", "cha_id", chaId, "status", DictConstant.BILL_STATUS_SAVE);
		this.cashProdProdDao.modifyCashProdProdStatus(chaId, DictConstant.BILL_STATUS_REVIEWING, userId);
		CashProdProd prodProd = this.cashProdProdDao.getCashProdProd(chaId);
		//进入审批
		workflowService.enterReview(prodProd, userId);
	}

	@Override
	public void closeBill(String chaId, String userId) {
		asertStatus("jat_cash_prod_prod", "cha_id", chaId, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.cashProdProdDao.modifyCashProdProdStatus(chaId, DictConstant.BILL_STATUS_CLOSED, userId);
		this.cashProdProdDao.updateProdAccountChecked(chaId, userId);
		this.cashProdProdDao.updateProdAccountStatus(chaId, DictConstant.BILL_STATUS_MARK, userId);
		this.cashProdProdDao.insertProdAccountByChaId(chaId, userId);
		this.cashProdProdDao.insertProdAccountByPrid(chaId, userId);
	}
	
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
