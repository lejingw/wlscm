package com.jatools.manager.out.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.out.CashProdAccountDao;
import com.jatools.manager.out.CashProdAccountManager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.util.SelectorOption;

public class CashProdAccountManagerImpl implements CashProdAccountManager {

	private CashProdAccountDao cashProdAccountDao;
	
	public void setCashProdAccountDao(CashProdAccountDao cashProdAccountDao) {
		this.cashProdAccountDao = cashProdAccountDao;
	}

	@Override
	public Pager getCashProdAccountData(Map<String, String> condition) {
		return this.cashProdAccountDao.getCashProdAccountData(condition);
	}

	@Override
	public void saveCashProdAccount(CashProdAccount CashProdAccount) {
		this.cashProdAccountDao.saveCashProdAccount(CashProdAccount);
	}

	@Override
	public void updateCashProdAccountUserNums(CashProdAccount CashProdAccount) {
		this.cashProdAccountDao.updateCashProdAccountUserNums(CashProdAccount);
	}
	
	@Override
	public CashProdAccount getCashProdAccount(String prId) {
		return this.cashProdAccountDao.getCashProdAccount(prId);
	}

	@Override
	public void updateCashProdAccount(CashProdAccount CashProdAccount) {
		this.cashProdAccountDao.updateCashProdAccount(CashProdAccount);
	}

	@Override
	public void deleteCashProdAccount(String prId) {
		this.cashProdAccountDao.deleteCashProdAccount(prId);
	}

	@Override
	public List<CashProdAccount> getCashListByPrid(String prId) {
		return this.cashProdAccountDao.getCashListByPrid(prId);
	}

	@Override
	public List<SelectorOption> getVendorList() {
		return this.cashProdAccountDao.getVendorList();
	}

	@Override
	public List<SelectorOption> getArticleList(String vendorId) {
		return this.cashProdAccountDao.getArticleList(vendorId);
	}

	@Override
	public List<SelectorOption> getItemClassList(String articleId) {
		return this.cashProdAccountDao.getItemClassList(articleId);
	}

	@Override
	public List<SelectorOption> getMaterialTypeList() {
		return this.cashProdAccountDao.getMaterialTypeList();
	}

	@Override
	public void deleteAccountUserByPriceLineId(String priceLineId) {
		this.cashProdAccountDao.deleteAccountUserByPriceLineId(priceLineId);
	}

	@Override
	public void deleteAccountUserByPriceHeadId(String priceHeadId) {
		this.cashProdAccountDao.deleteAccountUserByPriceHeadId(priceHeadId);
	}

	@Override
	public void subAccountUserNumByPriceLineId(String priceLineId) {
		this.cashProdAccountDao.subAccountUserNumByPriceLineId(priceLineId);
	}
	
	@Override
	public void addAccountUserNumByPriceLineId(String priceLineId) {
		this.cashProdAccountDao.addAccountUserNumByPriceLineId(priceLineId);
	}

	@Override
	public void subAccountUserNumByPriceHeadId(String priceHeadId) {
		this.cashProdAccountDao.subAccountUserNumByPriceHeadId(priceHeadId);
	}

}
