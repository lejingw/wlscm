package com.jatools.dao.pur.cash.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.cash.CashPayChangeDao;
import com.jatools.vo.pur.cash.CashPayChange;
import com.jatools.web.util.DateUtil;

public class CashPayChangeDaoImpl extends BaseDao implements CashPayChangeDao , ReviewActionIntf{

	@Override
	public Pager getCashPayChangeData(Map<String, String> condition) {
		return executeQueryForPager("CashPayChange.getCashPayChangePageData", "CashPayChange.getCashPayChangeTotalCount", condition);
	}

	@Override
	public void saveCashPayChange(CashPayChange cashPayChange) {
		executeInsert("CashPayChange.saveCashPayChange", cashPayChange);
	}

	@Override
	public CashPayChange getCashPayChange(String chaId) {
		return (CashPayChange)executeQueryForObject("CashPayChange.getCashPayChange", chaId);
	}

	@Override
	public void updateCashPayChange(CashPayChange cashPayChange) {
		executeUpdate("CashPayChange.updateCashPayChange", cashPayChange);
	}

	@Override
	public void modifyCashPayChangeStatus(String chaId, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashPayChange.updateCashPayChangeStatus", params);
	}

	@Override
	public void deleteCashPayChange(String chaId) {
		executeUpdate("CashPayChange.deleteCashPayChange", chaId);
	}

	@Override
	public void reviewSuccess(String chaId, String userid) {
		this.modifyCashPayChangeStatus(chaId, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String chaId, String userid) {
		this.modifyCashPayChangeStatus(chaId, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public void insertPayAccountByChaId(String chaId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashPayChange.insertPayAccountByChaId", params);
	}
	
}
