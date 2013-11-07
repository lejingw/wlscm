package com.jatools.manager.out.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.out.CashMoneyAccountDao;
import com.jatools.manager.out.CashMoneyAccountManager;
import com.jatools.vo.out.CashMoneyAccount;

public class CashMoneyAccountManagerImpl implements CashMoneyAccountManager{

	private CashMoneyAccountDao cashMoneyAccountDao;
	
	public void setCashMoneyAccountDao(CashMoneyAccountDao cashMoneyAccountDao) {
		this.cashMoneyAccountDao = cashMoneyAccountDao;
	}

	@Override
	public Pager getCashMoneyAccountData(Map<String, String> condition) {
		return this.cashMoneyAccountDao.getCashMoneyAccountData(condition);
	}

	@Override
	public void saveCashMoneyAccount(CashMoneyAccount cashMoneyAccount) {
		this.cashMoneyAccountDao.saveCashMoneyAccount(cashMoneyAccount);
	}

	@Override
	public CashMoneyAccount getCashMoneyAccount(String mrId) {
		return this.cashMoneyAccountDao.getCashMoneyAccount(mrId);
	}

	@Override
	public void updateCashMoneyAccount(CashMoneyAccount cashMoneyAccount) {
		this.cashMoneyAccountDao.updateCashMoneyAccount(cashMoneyAccount);
	}

	@Override
	public void deleteCashMoneyAccount(String mrId) {
		this.cashMoneyAccountDao.deleteCashMoneyAccount(mrId);
	}

	
}
