package com.jatools.manager.pur.impl;

import java.util.List;

import com.jatools.dao.pur.HandoverChildDao;
import com.jatools.manager.pur.HandoverChildManager;
import com.jatools.vo.pur.HandoverChild;

public class HandoverChildManagerImpl implements HandoverChildManager {

	private HandoverChildDao handoverChildDao;
	
	public void setHandoverChildDao(HandoverChildDao handoverChildDao) {
		this.handoverChildDao = handoverChildDao;
	}

	@Override
	public List<HandoverChild> getHandoverChildList(String lineid) {
		return this.handoverChildDao.getHandoverChildList(lineid);
	}

	@Override
	public void saveHandoverChild(HandoverChild handoverChild) {
		this.handoverChildDao.saveHandoverChild(handoverChild);
	}

	@Override
	public HandoverChild getHandoverChild(String childid) {
		return this.handoverChildDao.getHandoverChild(childid);
	}

	@Override
	public void updateHandoverChild(HandoverChild handoverChild) {
		this.handoverChildDao.updateHandoverChild(handoverChild);
	}

	@Override
	public void deleteHandoverChild(String childid) {
		this.handoverChildDao.deleteHandoverChild(childid);
	}

	@Override
	public void deleteHandoverChildByLineid(String lineid) {
		this.handoverChildDao.deleteHandoverChildByLineid(lineid);
	}

	@Override
	public void insertChildByExit(String exitBillid, String handoverId,
			String userId) {
		this.handoverChildDao.insertChildByExit(exitBillid, handoverId, userId);
	}
}
