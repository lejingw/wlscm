package com.jatools.dao.inout.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.inout.ReceiveLineDao;
import com.jatools.vo.inout.ReceiveLine;
import com.jatools.web.util.DateUtil;

public class ReceiveLineDaoImpl extends BaseDao implements ReceiveLineDao {

	@Override
	public Pager getReceiveLineData(Map<String, String> condition) {
		return executeQueryForPager("ReceiveLine.getReceiveLinePageData", "ReceiveLine.getReceiveLineTotalCount", condition);
	}

	@Override
	public void saveReceiveLine(ReceiveLine receiveLine) {
		executeInsert("ReceiveLine.saveReceiveLine", receiveLine);
	}

	@Override
	public ReceiveLine getReceiveLine(String lineid) {
		return (ReceiveLine)executeQueryForObject("ReceiveLine.getReceiveLine", lineid);
	}

	@Override
	public void updateReceiveLine(ReceiveLine receiveLine) {
		executeUpdate("ReceiveLine.updateReceiveLine", receiveLine);
	}

	@Override
	public void modifyReceiveLineStatus(String lineid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineid", lineid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ReceiveLine.updateReceiveLineStatus", params);
	}

	@Override
	public void deleteReceiveLine(String lineid) {
		executeUpdate("ReceiveLine.deleteReceiveLine", lineid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveLine> getReceiveLineList(String billid) {
		return (List<ReceiveLine>)executeQueryForList("ReceiveLine.getReceiveLineListByBillid", billid);
	}

	@Override
	public void deleteReceiveLineByBillid(String billid) {
		executeUpdate("ReceiveLine.deleteReceiveLineByBillid", billid);
	}

	@Override
	public void modifyHandoverBillStatus(String lineid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineid", lineid);
		params.put("status", status);
		params.put("updateId", userId);
		executeUpdate("ReceiveLine.modifyHandoverBillStatus", params);
	}

}
