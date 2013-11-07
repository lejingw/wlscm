package com.jatools.manager.pur.cash.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.pur.cash.PayMoneyDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.pur.cash.PayMoneyManager;
import com.jatools.vo.pur.cash.PayMoney;
import com.jatools.web.util.StringUtil;

public class PayMoneyManagerImpl extends BaseManager implements PayMoneyManager {

	private PayMoneyDao payMoneyDao;
	
	public void setPayMoneyDao(PayMoneyDao payMoneyDao) {
		this.payMoneyDao = payMoneyDao;
	}

	@Override
	public Pager getPayMoneyData(Map<String, String> condition) {
		return this.payMoneyDao.getPayMoneyData(condition);
	}

	@Override
	public void savePayMoney(PayMoney payMoney) {
		this.payMoneyDao.savePayMoney(payMoney);
	}

	@Override
	public PayMoney getPayMoney(String payId) {
		return this.payMoneyDao.getPayMoney(payId);
	}

	@Override
	public void updatePayMoney(PayMoney payMoney) {
		this.payMoneyDao.updatePayMoney(payMoney);
	}

	@Override
	public void modifyPayMoneyStatus(String payId, String status, String userId) {
		this.payMoneyDao.modifyPayMoneyStatus(payId, status, userId);
	}

	@Override
	public void deletePayMoney(String payId) {
		asertStatus("jat_cash_pay_money", "pay_id", payId, "status", DictConstant.BILL_STATUS_SAVE);
		this.payMoneyDao.deletePayMoney(payId);
	}

	public void saveOrUpdatePayMoney(PayMoney payMoney, String userId) {
		if(StringUtil.isNotBlank(payMoney.getPayId())){
			asertStatus("jat_cash_pay_money", "pay_id", payMoney.getPayId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.payMoneyDao.updatePayMoney(payMoney);
		} else{
			this.payMoneyDao.savePayMoney(payMoney);
		}
	}
	
	public void saveAndAccountPayMoney(PayMoney payMoney, String userId) {
		payMoney.setStatus(DictConstant.BILL_STATUS_CLOSED);
		if(StringUtil.isNotBlank(payMoney.getPayId())){
			asertStatus("jat_cash_pay_money", "pay_id", payMoney.getPayId(), "status", DictConstant.BILL_STATUS_SAVE);
			this.payMoneyDao.updatePayMoney(payMoney);
		} else{
			this.payMoneyDao.savePayMoney(payMoney);
		}
		this.payMoneyDao.insertMoneyAccountFromPayMoney(payMoney.getPayId(), userId);
	}
	
	public Double getLessMoney(String vendorId){
		return this.payMoneyDao.getLessMoney(vendorId);
	}
	
	public void insertMoneyAccountFromPayMoney(String payId, String userId){
		this.payMoneyDao.insertMoneyAccountFromPayMoney(payId, userId);
		this.payMoneyDao.modifyPayMoneyStatus(payId, DictConstant.BILL_STATUS_CLOSED, userId);
	}

	public void cancelMoneyAccountFromPayMoney(String payId, String userId){
		asertStatus("jat_cash_pay_money", "pay_id", payId, "status", DictConstant.BILL_STATUS_CLOSED);
		this.payMoneyDao.cancelMoneyAccountFromPayMoney(payId, userId);
		this.payMoneyDao.modifyPayMoneyStatus(payId, DictConstant.BILL_STATUS_SAVE, userId);
	}
	
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
