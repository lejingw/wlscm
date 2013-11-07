package com.jatools.manager.out;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashMoneyAccount;

public interface CashMoneyAccountManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getCashMoneyAccountData(Map<String, String> condition);
	
	
	/**
	 * 保存资金台账
	 * @param cashMoneyAccount
	 */
	void saveCashMoneyAccount(CashMoneyAccount cashMoneyAccount);
	
	/**
	 * 根据id获取资金台账
	 * @param mrId
	 * @return
	 */
	CashMoneyAccount getCashMoneyAccount(String mrId);
	
	
	/**
	 * 保存或修改资金台账
	 * @param cashMoneyAccount
	 */
	void updateCashMoneyAccount(CashMoneyAccount cashMoneyAccount);
	
	
	/**
	 * 删除资金台账
	 * @param mrId
	 */
	void deleteCashMoneyAccount(String mrId);
}
