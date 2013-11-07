package com.jatools.dao.pur.cash;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.cash.CashProdProd;

public interface CashProdProdDao {

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
	void deleteCashProdProd(String chaId);
	
	/**
	 * 根据已有的货品台账单据 插入一条一样 但是已经核销的单据
	 * @param chaId
	 * @param userId
	 */
	void insertProdAccountByPrid(String chaId, String userId);
	
	/**
	 * 根据转料单id 生成一笔 台账
	 * @param chaId
	 * @param userId
	 * @param materialType
	 * @param itemClassId
	 * @param noNums
	 * @param memo
	 */
	void insertProdAccountByChaId(String chaId, String userId);
	
	/**
	 * 根据 转料单id 修改台账状态为已核销
	 * @param chaId
	 * @param userId
	 */
	void updateProdAccountChecked(String chaId, String userId);
	
	/**
	 * 根据 转料单id 修改台账状态
	 * @param chaId
	 * @param userId
	 */
	void updateProdAccountStatus(String chaId, String status, String userId);
}
