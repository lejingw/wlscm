package com.jatools.manager.pur.cash;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.cash.CashPayChange;

public interface CashPayChangeManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getCashPayChangeData(Map<String, String> condition);
	
	/**
	 * 保存资金调整单
	 * @param cashPayChange
	 */
	void saveCashPayChange(CashPayChange cashPayChange);
	/**
	 * 根据id获取资金调整单
	 * @param chaId
	 * @return
	 */
	CashPayChange getCashPayChange(String chaId);
	
	/**
	 * 保存或修改资金调整单
	 * @param cashPayChange
	 */
	void updateCashPayChange(CashPayChange cashPayChange);
	
	/**
	 * 保存或修改
	 * @param cashPayChange
	 * @param userId
	 */
	void saveOrUpdateCashPayChange(CashPayChange cashPayChange, String userId);
	
	/**
	 * 保存并提交审核
	 * @param cashPayChange
	 * @param userId
	 */
	void saveAndCheckCashPayChange(CashPayChange cashPayChange, String userId);
	
	/**
	 * 修改资金调整单的状态
	 * @param chaId
	 * @param status
	 * @param userId
	 */
	public void modifyCashPayChangeStatus(String chaId, String status, String userId);
	/**
	 * 删除资金调整单头表
	 * @param chaId
	 */
	void deleteCashPayChange(String chaId);
	
	/**
	 * 提交审批
	 * @param cashId
	 * @param userId
	 */
	public void checkProdProd(String chaId, String userId);
	
	/**
	 * 关闭单据
	 * @param chaId
	 * @param userId
	 */
	void closeBill(String chaId, String userId);
}
