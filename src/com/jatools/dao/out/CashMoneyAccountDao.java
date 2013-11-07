package com.jatools.dao.out;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashMoneyAccount;

public interface CashMoneyAccountDao {

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
	
	/**
	 * 查询交接单 结算的 资金台账 对于交接单 资金台账最多一条
	 * @param billno
	 * @param dotype
	 * @return
	 */
	int selectCashMoneyAccountCount(String billno, String dotype);
	
}
