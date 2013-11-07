package com.jatools.dao.inout.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.inout.SendLineDao;
import com.jatools.vo.inout.SendLine;
import com.jatools.web.util.DateUtil;

public class SendLineDaoImpl extends BaseDao implements SendLineDao {

	@Override
	public Pager getSendLineData(Map<String, String> condition) {
		return executeQueryForPager("SendLine.getSendLinePageData", "SendLine.getSendLineTotalCount", condition);
	}

	@Override
	public void saveSendLine(SendLine SendLine) {
		executeInsert("SendLine.saveSendLine", SendLine);
	}

	@Override
	public SendLine getSendLine(String lineid) {
		return (SendLine)executeQueryForObject("SendLine.getSendLine", lineid);
	}

	@Override
	public void updateSendLine(SendLine SendLine) {
		executeUpdate("SendLine.updateSendLine", SendLine);
	}

	@Override
	public void modifySendLineStatus(String lineid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineid", lineid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("SendLine.updateSendLineStatus", params);
	}

	@Override
	public void deleteSendLine(String lineid) {
		executeUpdate("SendLine.deleteSendLine", lineid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SendLine> getSendLineList(String billid) {
		return (List<SendLine>)executeQueryForList("SendLine.getSendLineListByBillid", billid);
	}

	@Override
	public void deleteSendLineByBillid(String billid) {
		executeUpdate("SendLine.deleteSendLineByBillid", billid);
	}

}
