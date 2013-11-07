package com.jatools.dao.out.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.out.CashMoneyAccountDao;
import com.jatools.vo.out.CashMoneyAccount;

public class CashMoneyAccountDaoImpl extends BaseDao implements CashMoneyAccountDao {

	

	@Override
	public void saveCashMoneyAccount(CashMoneyAccount cashMoneyAccount) {
		executeInsert("CashMoneyAccount.saveCashMoneyAccount", cashMoneyAccount);
	}

	@Override
	public CashMoneyAccount getCashMoneyAccount(String mrId) {
		return (CashMoneyAccount)executeQueryForObject("CashMoneyAccount.getCashMoneyAccount", mrId);
	}


	@Override
	public void updateCashMoneyAccount(CashMoneyAccount cashMoneyAccount) {
		executeUpdate("CashMoneyAccount.updateCashMoneyAccount", cashMoneyAccount);
	}

	@Override
	public void deleteCashMoneyAccount(String mrId) {
		executeUpdate("CashMoneyAccount.deleteCashMoneyAccount", mrId);
	}

	@Override
	public Pager getCashMoneyAccountData(Map<String, String> condition) {
		return executeQueryForPager("CashMoneyAccount.getCashMoneyAccountPageData", "CashMoneyAccount.getCashMoneyAccountTotalCount", condition);
	}

	public int selectCashMoneyAccountCount(String billno, String dotype){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("dotype", dotype);
		Object obj = executeQueryForObject("CashMoneyAccount.selectCashMoneyAccountCount", params);
		return null != obj?(Integer)obj:0;
	}
}
