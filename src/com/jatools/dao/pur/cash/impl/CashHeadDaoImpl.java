package com.jatools.dao.pur.cash.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.cash.CashHeadDao;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.cash.CashHead;
import com.jatools.web.util.DateUtil;

public class CashHeadDaoImpl extends BaseDao implements CashHeadDao, ReviewActionIntf {

	@Override
	public Pager getCashHeadData(Map<String, String> condition) {
		return executeQueryForPager("CashHead.getCashHeadPageData", "CashHead.getCashHeadTotalCount", condition);
	}

	@Override
	public void saveCashHead(CashHead cashHead) {
		executeInsert("CashHead.saveCashHead", cashHead);
	}

	@Override
	public CashHead getCashHead(String cashId) {
		return (CashHead)executeQueryForObject("CashHead.getCashHead", cashId);
	}

	@Override
	public void updateCashHead(CashHead cashHead) {
		executeUpdate("CashHead.updateCashHead", cashHead);
	}

	@Override
	public void modifyCashHeadStatus(String cashId, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cashId", cashId);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashHead.updateCashHeadStatus", params);
	}

	@Override
	public void deleteCashHead(String cashId, String userId) {
		this.subProdAccountUserNums(cashId, userId);
		executeUpdate("CashHead.deleteCashHead", cashId);
	}

	public Pager getCashPagerList(Map<String, String> condition){
		return executeQueryForPager("CashHead.selectCashList", "CashHead.selectCashCount", condition);
	}

	@Override
	public void reviewSuccess(String cashId, String userId) {
		this.modifyCashHeadStatus(cashId, DictConstant.BILL_STATUS_REVIEWED, userId);
	}

	@Override
	public void reviewFail(String cashId, String userid) {
		this.modifyCashHeadStatus(cashId, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public void modifyProdAccountValid(String cashId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cashId", cashId);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashHead.modifyProdAccountValid", params);
	}

	@Override
	public CashProdAccount getProdAccountById(String prId) {
		return (CashProdAccount)executeQueryForObject("CashHead.getProdAccountById", prId);
	}
	
	private void subProdAccountUserNums(String billid, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashHead.subProdAccountUserNums", params);
	}
}
