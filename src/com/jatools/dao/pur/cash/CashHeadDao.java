package com.jatools.dao.pur.cash;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.cash.CashHead;

public interface CashHeadDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getCashHeadData(Map<String, String> condition);
	
	/**
	 * 保存结算
	 * @param cashHead
	 */
	void saveCashHead(CashHead cashHead);
	/**
	 * 根据id获取结算
	 * @param cashId
	 * @return
	 */
	CashHead getCashHead(String cashId);
	
	/**
	 * 保存或修改结算
	 * @param cashHead
	 */
	void updateCashHead(CashHead cashHead);
	
	/**
	 * 修改结算的状态
	 * @param cashId
	 * @param status
	 * @param userId
	 */
	public void modifyCashHeadStatus(String cashId, String status, String userId);
	/**
	 * 删除结算头表
	 * @param cashId
	 */
	void deleteCashHead(String cashId, String suerId);
	
	/**
	 * 查询货品台账 分页
	 * @param cashId
	 * @param billtype
	 * @param vendrId
	 * @return
	 */
	Pager getCashPagerList(Map<String, String> condition);
	
	/**
	 * 在删除单据时 还原 台账状态为2
	 * @param cashId
	 * @param userId
	 */
	void modifyProdAccountValid(String cashId, String userId);
	
	/**
	 * 根据货品台账id取货品台账
	 * @param prId
	 * @return
	 */
	CashProdAccount getProdAccountById(String prId);
}
