package com.jatools.manager.pur.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.pur.HandoverCashDao;
import com.jatools.manager.pur.HandoverCashManager;
import com.jatools.vo.pur.HandoverCash;
import com.jatools.web.util.StringUtil;

public class HandoverCashManagerImpl implements HandoverCashManager{
	private HandoverCashDao handoverCashDao;
	
	public void setHandoverCashDao(HandoverCashDao handoverCashDao) {
		this.handoverCashDao = handoverCashDao;
	}

	@Override
	public Pager getHandoverCashData(Map<String, String> condition) {
		return this.handoverCashDao.getHandoverCashData(condition);
	}

	@Override
	public void saveHandoverCash(HandoverCash handoverCash) {
		this.handoverCashDao.saveHandoverCash(handoverCash);
	}

	@Override
	public HandoverCash getHandoverCash(String lineid) {
		return this.handoverCashDao.getHandoverCash(lineid);
	}

	@Override
	public void updateHandoverCash(HandoverCash handoverCash) {
		this.handoverCashDao.updateHandoverCash(handoverCash);
	}

	@Override
	public void deleteHandoverCash(String lineid) {
		this.handoverCashDao.deleteHandoverCash(lineid);
		
	}
	
	public void deleteHandoverCashByBillid(String billid) {
		this.handoverCashDao.deleteHandoverCashByBillid(billid);
		
	}

	@Override
	public void saveOrUpdateHandoverCash(
			HandoverCash HandoverCash) {
		if (StringUtil.isNotBlank(HandoverCash.getLineid())) {
			this.handoverCashDao.updateHandoverCash(HandoverCash);
		} else {
			this.handoverCashDao.saveHandoverCash(HandoverCash);
		}
	}
	
	public List<HandoverCash> getHandoverCashList(String billid){
		return this.handoverCashDao.getHandoverCashList(billid);
	}

}
