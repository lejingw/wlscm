package com.jatools.manager.inout.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.inout.SendLineDao;
import com.jatools.manager.inout.SendLineManager;
import com.jatools.vo.inout.SendLine;

public class SendLineManagerImpl implements SendLineManager {

	private SendLineDao sendLineDao;
	
	public SendLineDao getSendLineDao() {
		return sendLineDao;
	}

	public void setSendLineDao(SendLineDao sendLineDao) {
		this.sendLineDao = sendLineDao;
	}

	@Override
	public Pager getSendLineData(Map<String, String> condition) {
		return this.sendLineDao.getSendLineData(condition);
	}

	@Override
	public void saveSendLine(SendLine SendLine) {
		this.sendLineDao.saveSendLine(SendLine);
	}

	@Override
	public SendLine getSendLine(String lineid) {
		return this.sendLineDao.getSendLine(lineid);
	}

	@Override
	public List<SendLine> getSendLineList(String billid) {
		return this.sendLineDao.getSendLineList(billid);
	}

	@Override
	public void updateSendLine(SendLine SendLine) {
		this.sendLineDao.updateSendLine(SendLine);
	}

	@Override
	public void modifySendLineStatus(String lineid, String status, String userId) {
		this.sendLineDao.modifySendLineStatus(lineid, status, userId);
	}

	@Override
	public void deleteSendLine(String lineid) {
		this.sendLineDao.deleteSendLine(lineid);
	}

	@Override
	public void deleteSendLineByBillid(String billid) {
		this.sendLineDao.deleteSendLineByBillid(billid);
	}

}
