package com.jatools.dao.pur.cash.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.cash.PayMoneyDao;
import com.jatools.vo.pur.cash.PayMoney;
import com.jatools.web.util.DateUtil;

public class PayMoneyDaoImpl extends BaseDao implements PayMoneyDao {

	@Override
	public Pager getPayMoneyData(Map<String, String> condition) {
		return executeQueryForPager("PayMoney.getPayMoneyPageData", "PayMoney.getPayMoneyTotalCount", condition);
	}

	@Override
	public void savePayMoney(PayMoney payMoney) {
		executeInsert("PayMoney.savePayMoney", payMoney);
	}

	@Override
	public PayMoney getPayMoney(String payId) {
		return (PayMoney)executeQueryForObject("PayMoney.getPayMoney", payId);
	}

	@Override
	public void updatePayMoney(PayMoney payMoney) {
		executeUpdate("PayMoney.updatePayMoney", payMoney);
	}

	@Override
	public void modifyPayMoneyStatus(String payId, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("payId", payId);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("PayMoney.updatePayMoneyStatus", params);
	}

	@Override
	public void deletePayMoney(String payId) {
		executeUpdate("PayMoney.deletePayMoney", payId);
	}

	public Double getLessMoney(String vendorId) {
		Double res = (Double)executeQueryForObject("PayMoney.getLessMoney", vendorId);
        if(res == null || res.doubleValue() <= 0) return 0d;
		return res.doubleValue();
	}
	
	public void insertMoneyAccountFromPayMoney(String payId, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("payId", payId);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("PayMoney.insertMoneyAccountFromPayMoney", params);
	}
	
	public void cancelMoneyAccountFromPayMoney(String payId, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("payId", payId);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("PayMoney.cancelMoneyAccountFromPayMoney", params);
	}
}
