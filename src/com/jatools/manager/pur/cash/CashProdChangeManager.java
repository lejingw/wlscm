package com.jatools.manager.pur.cash;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.cash.CashProdChange;

public interface CashProdChangeManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getCashProdChangeData(Map<String, String> condition);
	
	/**
	 * 保存货品调整单
	 * @param cashProdChange
	 */
	void saveCashProdChange(CashProdChange cashProdChange);
	/**
	 * 根据id获取货品调整单
	 * @param chaId
	 * @return
	 */
	CashProdChange getCashProdChange(String chaId);

    CashProdChange getCashProdChangeByBillno(String billno);
	/**
	 * 保存或修改货品调整单
	 * @param cashProdChange
	 */
	void updateCashProdChange(CashProdChange cashProdChange);
	
	
	/**
	 * 保存或修改
	 * @param cashProdChange
	 * @param userId
	 */
	void saveOrUpdateCashProdChange(CashProdChange cashProdChange, String userId);
	
	void saveAndCheckCashProdChange(CashProdChange cashProdChange, String userId);
	
	/**
	 * 修改货品调整单的状态
	 * @param chaId
	 * @param status
	 * @param userId
	 */
	public void modifyCashProdChangeStatus(String chaId, String status, String userId);
	/**
	 * 删除货品调整单头表
	 * @param chaId
	 */
	void deleteCashProdChange(String chaId);
	
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
