package com.jatools.dao.inout.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.inout.ReceiveHeadDao;
import com.jatools.vo.inout.ReceiveHead;
import com.jatools.web.util.DateUtil;

public class ReceiveHeadDaoImpl extends BaseDao implements ReceiveHeadDao, ReviewActionIntf {

	@Override
	public Pager getReceiveHeadData(Map<String, String> condition) {
		return executeQueryForPager("ReceiveHead.getReceiveHeadPageData", "ReceiveHead.getReceiveHeadTotalCount", condition);
	}

	@Override
	public void saveReceiveHead(ReceiveHead receiveHead) {
		executeInsert("ReceiveHead.saveReceiveHead", receiveHead);
	}

	@Override
	public ReceiveHead getReceiveHead(String billid) {
		return (ReceiveHead)executeQueryForObject("ReceiveHead.getReceiveHead", billid);
	}

	@Override
	public void updateReceiveHead(ReceiveHead receiveHead) {
		executeUpdate("ReceiveHead.updateReceiveHead", receiveHead);
	}

	@Override
	public void modifyReceiveHeadStatus(String billid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ReceiveHead.updateReceiveHeadStatus", params);
	}

	@Override
	public void deleteReceiveHead(String billid) {
		executeUpdate("ReceiveHead.deleteReceiveHead", billid);
	}

	@Override
	public void reviewSuccess(String billid, String userId) {
		this.modifyReceiveHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userId);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		this.modifyReceiveHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public Pager getReceiveHandoverList(Map<String, String> condition) {
		return executeQueryForPager("ReceiveHead.searchHandoverList", "ReceiveHead.searchHandoverCount", condition);
	}

}
