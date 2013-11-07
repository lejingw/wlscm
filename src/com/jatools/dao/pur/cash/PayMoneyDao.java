package com.jatools.dao.pur.cash;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.cash.PayMoney;

public interface PayMoneyDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getPayMoneyData(Map<String, String> condition);
	
	/**
	 * 保存付款单
	 * @param payMoney
	 */
	void savePayMoney(PayMoney payMoney);
	/**
	 * 根据id获取付款单
	 * @param payId
	 * @return
	 */
	PayMoney getPayMoney(String payId);
	
	/**
	 * 保存或修改付款单
	 * @param payMoney
	 */
	void updatePayMoney(PayMoney payMoney);
	
	/**
	 * 修改付款单的状态
	 * @param payId
	 * @param status
	 * @param userId
	 */
	public void modifyPayMoneyStatus(String payId, String status, String userId);
	/**
	 * 删除交接单头表
	 * @param payId
	 */
	void deletePayMoney(String payId);
	/**
	 * 取欠供应商的当前金额
	 * @param vendorId
	 * @return
	 */
    Double getLessMoney(String vendorId);
	
	/**
	 * 写入资金台账
	 * @param payId
	 * @param userId
	 */
	void insertMoneyAccountFromPayMoney(String payId, String userId);
	
	/**
	 * 撤销
	 * @param payId
	 * @param userId
	 */
	void cancelMoneyAccountFromPayMoney(String payId, String userId);
}
