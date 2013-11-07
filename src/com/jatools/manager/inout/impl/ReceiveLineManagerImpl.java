package com.jatools.manager.inout.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.inout.ReceiveLineDao;
import com.jatools.manager.inout.ReceiveLineManager;
import com.jatools.vo.inout.ReceiveLine;

public class ReceiveLineManagerImpl implements ReceiveLineManager {

	private ReceiveLineDao receiveLineDao;
	

	public void setReceiveLineDao(ReceiveLineDao receiveLineDao) {
		this.receiveLineDao = receiveLineDao;
	}

	@Override
	public Pager getReceiveLineData(Map<String, String> condition) {
		return this.receiveLineDao.getReceiveLineData(condition);
	}

	@Override
	public void saveReceiveLine(ReceiveLine receiveLine) {
		this.receiveLineDao.saveReceiveLine(receiveLine);
	}

	@Override
	public ReceiveLine getReceiveLine(String lineid) {
		return this.receiveLineDao.getReceiveLine(lineid);
	}

	@Override
	public List<ReceiveLine> getReceiveLineList(String billid) {
		return this.receiveLineDao.getReceiveLineList(billid);
	}

	@Override
	public void updateReceiveLine(ReceiveLine receiveLine) {
		this.receiveLineDao.updateReceiveLine(receiveLine);
	}

	@Override
	public void modifyReceiveLineStatus(String lineid, String status,
			String userId) {
		this.receiveLineDao.modifyReceiveLineStatus(lineid, status, userId);
	}

	@Override
	public void deleteReceiveLine(String lineid) {
		this.receiveLineDao.deleteReceiveLine(lineid);
	}

	@Override
	public void deleteReceiveLineByBillid(String billid) {
		this.receiveLineDao.deleteReceiveLineByBillid(billid);
	}

}
