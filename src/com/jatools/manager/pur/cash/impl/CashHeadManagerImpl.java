package com.jatools.manager.pur.cash.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.out.CashProdAccountDao;
import com.jatools.dao.pur.cash.CashHeadDao;
import com.jatools.dao.pur.cash.CashLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.pur.cash.CashHeadManager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.cash.CashHead;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class CashHeadManagerImpl extends BaseManager implements CashHeadManager {

	private CashHeadDao cashHeadDao;
	private CashLineDao cashLineDao;
	private CashProdAccountDao cashProdAccountDao;
	
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	
	public void setCashProdAccountDao(CashProdAccountDao cashProdAccountDao) {
		this.cashProdAccountDao = cashProdAccountDao;
	}
	public void setCashHeadDao(CashHeadDao cashHeadDao) {
		this.cashHeadDao = cashHeadDao;
	}
	public void setCashLineDao(CashLineDao cashLineDao) {
		this.cashLineDao = cashLineDao;
	}

	@Override
	public Pager getCashHeadData(Map<String, String> condition) {
		return this.cashHeadDao.getCashHeadData(condition);
	}

	@Override
	public void saveCashHead(CashHead cashHead) {
		this.cashHeadDao.saveCashHead(cashHead);
	}

	@Override
	public CashHead getCashHead(String cashId) {
		return this.cashHeadDao.getCashHead(cashId);
	}

	@Override
	public void updateCashHead(CashHead cashHead) {
		this.cashHeadDao.updateCashHead(cashHead);
	}

	@Override
	public void saveOrUpdateCashHead(CashHead cashHead, List<CashLine> lines, String deleteIds,  String userId) {
		this.saveUpdateCheckCashHead(cashHead, lines, deleteIds, userId, false);
	}
	
	
	
	public void saveAndCheckCashHead(CashHead cashHead, List<CashLine> lines, String deleteIds,  String userId) {
		this.saveUpdateCheckCashHead(cashHead, lines, deleteIds, userId, true);
	}
	
	private void saveUpdateCheckCashHead(CashHead cashHead, List<CashLine> lines, String deleteIds,  String userId, boolean isCheck){
		cashHead.setUpdateDate(DateUtil.getCurrentDate18());
		cashHead.setUpdateId(userId);
		if(StringUtil.isNotBlank(cashHead.getCashId())){
			asertStatus("jat_cash_head", "cash_id", cashHead.getCashId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.cashHeadDao.updateCashHead(cashHead);
		} else {
			cashHead.setCreateDate(DateUtil.getCurrentDate18());
			cashHead.setCreateId(userId);
			this.cashHeadDao.saveCashHead(cashHead);
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineId : idArray){
					if(StringUtil.isNotBlank(lineId)){
						//CashLine line = this.cashLineDao.getCashLine(lineId);
						this.cashLineDao.deleteCashLine(lineId, userId);
						//this.cashProdAccountDao.modifyProdAccountStatus(line.getPrId(), DictConstant.BILL_STATUS_MARK, userId);
					}
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(CashLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				// 判断台账实际可用的数量是否满足要求
				this.asertProdAccount(line);
				
				if(StringUtil.isNotBlank(line.getLineId())){
					this.cashLineDao.updateCashLine(line);
				} else {
					line.setCashId(cashHead.getCashId());
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					this.cashLineDao.saveCashLine(line);
					// 修改 货品台账状态
					//this.cashProdAccountDao.modifyProdAccountStatus(line.getPrId(), DictConstant.BILL_STATUS_REFERENCE, userId);
				}
			}
		}
		if(isCheck){
			workflowService.enterReview(cashHead, userId);
			this.cashHeadDao.modifyCashHeadStatus(cashHead.getCashId(), DictConstant.BILL_STATUS_REVIEWING, userId);
		}
	}
	
	private void asertProdAccount(CashLine line){
		CashProdAccount prod = this.cashHeadDao.getProdAccountById(line.getPrId());
		if(prod != null){
			double userNums = 0;
			double noNums = 0;
			double curNums = 0;
			double oldCurNums = 0;
			if(StringUtil.isNotBlank(prod.getUserNums())){
				userNums = Double.valueOf(prod.getUserNums());
			}
			if(StringUtil.isNotBlank(prod.getNoNums())){
				noNums = Double.valueOf(prod.getNoNums());
			}
			if(line.getCurNums() != null){
				userNums = line.getCurNums();
			}
			if(line.getOldCurNums() != null){
				oldCurNums = line.getOldCurNums();
			}
			if(StringUtil.isNotBlank(line.getLineId())){
				if(curNums - oldCurNums !=0){
					if(noNums - userNums + oldCurNums - curNums <0){
						throw new RuntimeException("台账实际可核销数量不足");
					}
				}
			} else {
				if(noNums - userNums - curNums <0){
					throw new RuntimeException("台账实际可核销数量不足");
				}
			}
		} else {
			throw new RuntimeException("台账实际可核销数量为0或者台账不存在");
		}
	}
	
/*	*//**
	 * 提交审批
	 * @param cashId
	 * @param userId
	 *//*
	public void checkBillHead(String cashId, String userId) {
		asertStatus("jat_cash_head", "cash_id", cashId, "status", DictConstant.BILL_STATUS_SAVE);
		this.cashHeadDao.modifyCashHeadStatus(cashId, DictConstant.BILL_STATUS_REVIEWING, userId);
		CashHead head = this.cashHeadDao.getCashHead(cashId);
		//进入审批
		workflowService.enterReview(head, userId);
	}*/
	
	public void closeBill(String cashId, List<CashLine> lines, String userId){
		asertStatus("jat_cash_head", "cash_id", cashId, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.cashHeadDao.modifyCashHeadStatus(cashId, DictConstant.BILL_STATUS_CLOSED, userId);
		if(CollectionUtils.isNotEmpty(lines)){
			for(CashLine line : lines) {
				this.cashLineDao.modifyProdAccount(line.getLineId(), userId);
				this.cashLineDao.modifyProdAccountChecked(line.getLineId(), userId);
				//this.cashProdAccountDao.modifyProdAccountStatus(line.getPrId(), DictConstant.BILL_STATUS_MARK, userId);
			}
		}
	}
	
	@Override
	public void modifyCashHeadStatus(String cashId, String status, String userId) {
		this.cashHeadDao.modifyCashHeadStatus(cashId, status, userId);
	}

	@Override
	public void deleteCashHead(String cashId, String userId) {
		asertStatus("jat_cash_head", "cash_id", cashId, "status", DictConstant.BILL_STATUS_SAVE);
		//this.cashHeadDao.modifyProdAccountValid(cashId, userId);
		this.cashHeadDao.deleteCashHead(cashId, userId);
		this.cashLineDao.deleteCashLineByCashId(cashId);
	}

	@Override
	public Pager getCashPagerList(Map<String, String> condition) {
		return this.cashHeadDao.getCashPagerList(condition);
	}

	@Override
	public CashProdAccount getProdAccountById(String prId) {
		return this.cashHeadDao.getProdAccountById(prId);
	}
	
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
