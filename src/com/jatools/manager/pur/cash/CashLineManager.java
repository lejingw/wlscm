package com.jatools.manager.pur.cash;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.cash.CashLine;

public interface CashLineManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getCashLineData(Map<String, String> condition);
	
	/**
	 * 保存结算行表
	 * @param CashLine
	 */
	void saveCashLine(CashLine CashLine);
	/**
	 * 根据id获取结算行表
	 * @param lineId
	 * @return
	 */
	CashLine getCashLine(String lineId);
	
	/**
	 * 通过头表id取行表数据
	 * @param cashId
	 * @return
	 */
	List<CashLine> getCashLineList(String cashId);
	
	/**
	 * 保存或修改结算行表
	 * @param CashLine
	 */
	void updateCashLine(CashLine CashLine);
	
	/**
	 * 保存或修改
	 * @param CashLine
	 * @param userId
	 */
	void saveOrUpdateCashLine(CashLine CashLine, String userId);
	
	/**
	 * 修改结算行表的状态
	 * @param lineId
	 * @param status
	 * @param userId
	 */
	public void modifyCashLineStatus(String lineId, String status, String userId);
	
	/**
	 * 删除头表对应得行表数据
	 * @param cashId
	 */
	void deleteCashLineByCashId(String cashId);
}
