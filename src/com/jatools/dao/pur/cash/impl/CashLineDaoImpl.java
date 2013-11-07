package com.jatools.dao.pur.cash.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.cash.CashLineDao;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.web.util.DateUtil;

public class CashLineDaoImpl extends BaseDao implements CashLineDao {

	@Override
	public Pager getCashLineData(Map<String, String> condition) {
		return executeQueryForPager("CashLine.getCashLinePageData", "CashLine.getCashLineTotalCount", condition);
	}

	@Override
	public void saveCashLine(CashLine CashLine) {
		executeInsert("CashLine.saveCashLine", CashLine);
		this.addProdAccountUserNums(CashLine.getLineId(), CashLine.getUpdateId());
	}

	@Override
	public CashLine getCashLine(String lineId) {
		return (CashLine)executeQueryForObject("CashLine.getCashLine", lineId);
	}

	@Override
	public void updateCashLine(CashLine CashLine) {
		this.subProdAccountUserNums(CashLine.getLineId(), CashLine.getUpdateId());
		executeUpdate("CashLine.updateCashLine", CashLine);
		this.addProdAccountUserNums(CashLine.getLineId(), CashLine.getUpdateId());
	}

	@Override
	public void modifyCashLineStatus(String lineId, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineId);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashLine.updateCashLineStatus", params);
	}

	@Override
	public void deleteCashLine(String lineId, String userId) {
		// 先修改台账 userNums
		this.subProdAccountUserNums(lineId, userId);
		// 删除行
		executeUpdate("CashLine.deleteCashLine", lineId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashLine> getCashLineList(String cashId) {
		return (List<CashLine>)executeQueryForList("CashLine.getCashLineListByCashId", cashId);
	}

	@Override
	public void deleteCashLineByCashId(String cashId) {
		executeUpdate("CashLine.deleteCashLineByCashId", cashId);
	}

	@Override
	public void modifyProdAccount(String lineId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineId);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashLine.modifyProdAccount", params);
	}

	@Override
	public void modifyProdAccountChecked(String lineId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineId);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashLine.modifyProdAccountChecked", params);
	}
	
	private void subProdAccountUserNums(String lineid, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashLine.subProdAccountUserNums", params);
	}
	
	private void addProdAccountUserNums(String lineid, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashLine.addProdAccountUserNums", params);
	}
}
