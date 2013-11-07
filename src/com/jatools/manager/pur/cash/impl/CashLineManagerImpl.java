package com.jatools.manager.pur.cash.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.pur.cash.CashLineDao;
import com.jatools.manager.pur.cash.CashLineManager;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class CashLineManagerImpl implements CashLineManager {

	private CashLineDao cashLineDao;
	
	public void setCashLineDao(CashLineDao cashLineDao) {
		this.cashLineDao = cashLineDao;
	}

	@Override
	public Pager getCashLineData(Map<String, String> condition) {
		return this.cashLineDao.getCashLineData(condition);
	}

	@Override
	public void saveCashLine(CashLine CashLine) {
		this.cashLineDao.saveCashLine(CashLine);
	}

	@Override
	public CashLine getCashLine(String lineId) {
		return this.getCashLine(lineId);
	}

	@Override
	public List<CashLine> getCashLineList(String cashId) {
		return this.cashLineDao.getCashLineList(cashId);
	}

	@Override
	public void updateCashLine(CashLine CashLine) {
		this.cashLineDao.updateCashLine(CashLine);
	}

	@Override
	public void saveOrUpdateCashLine(CashLine CashLine, String userId) {
		CashLine.setUpdateDate(DateUtil.getCurrentDate18());
		CashLine.setUpdateId(userId);
		if(StringUtil.isNotBlank(CashLine.getLineId())){
			this.cashLineDao.updateCashLine(CashLine);
		} else {
			CashLine.setCreateDate(DateUtil.getCurrentDate18());
			CashLine.setCreateId(userId);
			this.cashLineDao.saveCashLine(CashLine);
		}
	}

	@Override
	public void modifyCashLineStatus(String lineId, String status, String userId) {
		this.cashLineDao.modifyCashLineStatus(lineId, status, userId);
	}


	@Override
	public void deleteCashLineByCashId(String cashId) {
		this.cashLineDao.deleteCashLineByCashId(cashId);
	}

}
