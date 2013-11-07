package com.jatools.manager.pur.cash;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.cash.CashProdProd;

public interface CashProdProdManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getCashProdProdData(Map<String, String> condition);
	
	/**
	 * 保存料转料单
	 * @param cashProdProd
	 */
	void saveCashProdProd(CashProdProd cashProdProd);
	/**
	 * 根据id获取料转料单
	 * @param chaId
	 * @return
	 */
	CashProdProd getCashProdProd(String chaId);
	
	/**
	 * 保存或修改料转料单
	 * @param cashProdProd
	 */
	void updateCashProdProd(CashProdProd cashProdProd);
	
	/**
	 * 保存或修改
	 * @param cashProdProd
	 * @param userId
	 */
	void saveOrUpdateCashProdProd(CashProdProd cashProdProd, String userId);
	
	void saveAndCheckCashProdProd(CashProdProd cashProdProd, String userId);
	/**
	 * 修改料转料单的状态
	 * @param chaId
	 * @param status
	 * @param userId
	 */
	public void modifyCashProdProdStatus(String chaId, String status, String userId);
	/**
	 * 删除料转料单头表
	 * @param chaId
	 */
	void deleteCashProdProd(String chaId, String userId);
	
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
