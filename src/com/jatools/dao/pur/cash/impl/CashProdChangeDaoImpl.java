package com.jatools.dao.pur.cash.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.cash.CashProdChangeDao;
import com.jatools.vo.pur.cash.CashProdChange;
import com.jatools.web.util.DateUtil;

public class CashProdChangeDaoImpl extends BaseDao implements CashProdChangeDao, ReviewActionIntf {

	@Override
	public Pager getCashProdChangeData(Map<String, String> condition) {
		return executeQueryForPager("CashProdChange.getCashProdChangePageData", "CashProdChange.getCashProdChangeTotalCount", condition);
	}

	@Override
	public void saveCashProdChange(CashProdChange cashProdChange) {
		executeInsert("CashProdChange.saveCashProdChange", cashProdChange);
	}

	@Override
	public CashProdChange getCashProdChange(String chaId) {
		return (CashProdChange)executeQueryForObject("CashProdChange.getCashProdChange", chaId);
	}

	@Override
	public CashProdChange getCashProdChangeByBillno(String billno) {
		return (CashProdChange)executeQueryForObject("CashProdChange.getCashProdChangeByBillno", billno);
	}

	@Override
	public void updateCashProdChange(CashProdChange cashProdChange) {
		executeUpdate("CashProdChange.updateCashProdChange", cashProdChange);
	}

	@Override
	public void modifyCashProdChangeStatus(String chaId, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdChange.updateCashProdChangeStatus", params);
	}

	@Override
	public void deleteCashProdChange(String chaId) {
		executeUpdate("CashProdChange.deleteCashProdChange", chaId);
	}

	@Override
	public void reviewSuccess(String chaId, String userid) {
		this.modifyCashProdChangeStatus(chaId, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String chaId, String userid) {
		this.modifyCashProdChangeStatus(chaId, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public void insertProdAccountbyChaId(String chaId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("updateId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdChange.insertProdAccountbyChaId", params);
	}
}
