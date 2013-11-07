package com.jatools.manager.pur.cash;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.cash.CashHead;
import com.jatools.vo.pur.cash.CashLine;

public interface CashHeadManager {

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
	 * 保存或修改
	 * @param cashHead
	 * @param userId
	 */
	void saveOrUpdateCashHead(CashHead cashHead, List<CashLine> lines, String deleteIds,  String userId);
	
	void saveAndCheckCashHead(CashHead cashHead, List<CashLine> lines, String deleteIds,  String userId);
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
	void deleteCashHead(String cashId, String userId);
	
	/**
	 * 查询货品台账 分页
	 * @return
	 */
	Pager getCashPagerList(Map<String, String> condition);
	
	/**
	 * 提交审批
	 * @param cashId
	 * @param userId
	 *//*
	public void checkBillHead(String cashId, String userId);*/
	
	/**
	 * 关闭单据 1、修改台账的核销数量和未核销数量 2、如果台账的未核销数量为0则改成 已核销完成状态 3、修改台账的状态为2 4修改单据状态为关闭
	 * @param cashId
	 * @param lines
	 * @param userId
	 */
	void closeBill(String cashId, List<CashLine> lines, String userId);
	
	
	/**
	 * 根据货品台账id取货品台账
	 * @param prId
	 * @return
	 */
	CashProdAccount getProdAccountById(String prId);
}
