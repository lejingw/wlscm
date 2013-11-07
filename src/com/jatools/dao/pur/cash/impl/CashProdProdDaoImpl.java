package com.jatools.dao.pur.cash.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.cash.CashProdProdDao;
import com.jatools.vo.pur.cash.CashProdProd;
import com.jatools.web.util.DateUtil;

public class CashProdProdDaoImpl extends BaseDao implements CashProdProdDao, ReviewActionIntf {

	@Override
	public Pager getCashProdProdData(Map<String, String> condition) {
		return executeQueryForPager("CashProdProd.getCashProdProdPageData", "CashProdProd.getCashProdProdTotalCount", condition);
	}

	@Override
	public void saveCashProdProd(CashProdProd cashProdProd) {
		executeInsert("CashProdProd.saveCashProdProd", cashProdProd);
	}

	@Override
	public CashProdProd getCashProdProd(String chaId) {
		return (CashProdProd)executeQueryForObject("CashProdProd.getCashProdProd", chaId);
	}

	@Override
	public void updateCashProdProd(CashProdProd cashProdProd) {
		executeUpdate("CashProdProd.updateCashProdProd", cashProdProd);
	}

	@Override
	public void modifyCashProdProdStatus(String chaId, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdProd.updateCashProdProdStatus", params);
	}

	@Override
	public void deleteCashProdProd(String chaId) {
		executeUpdate("CashProdProd.deleteCashProdProd", chaId);
	}

	@Override
	public void reviewSuccess(String chaId, String userid) {
		this.modifyCashProdProdStatus(chaId, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String chaId, String userid) {
		this.modifyCashProdProdStatus(chaId, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public void insertProdAccountByPrid(String chaId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdProd.insertProdAccountByPrid", params);
	}

	@Override
	public void insertProdAccountByChaId(String chaId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdProd.insertProdAccountbyChaId", params);
	}
	
	public void updateProdAccountChecked(String chaId, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdProd.updateProdAccountChecked", params);
	}
	
	public void updateProdAccountStatus(String chaId, String status, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("chaId", chaId);
		params.put("userId", userId);
		params.put("status", status);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdProd.updateProdAccountStatus", params);
	}
}
