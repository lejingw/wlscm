package com.jatools.dao.inout.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.inout.SendHeadDao;
import com.jatools.vo.inout.SendHead;
import com.jatools.web.util.DateUtil;

public class SendHeadDaoImpl extends BaseDao implements SendHeadDao, ReviewActionIntf {

	@Override
	public Pager getSendHeadData(Map<String, String> condition) {
		return executeQueryForPager("SendHead.getSendHeadPageData", "SendHead.getSendHeadTotalCount", condition);
	}

	@Override
	public void saveSendHead(SendHead SendHead) {
		executeInsert("SendHead.saveSendHead", SendHead);
	}

	@Override
	public SendHead getSendHead(String billid) {
		return (SendHead)executeQueryForObject("SendHead.getSendHead", billid);
	}

	@Override
	public void updateSendHead(SendHead SendHead) {
		executeUpdate("SendHead.updateSendHead", SendHead);
	}

	@Override
	public void modifySendHeadStatus(String billid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("SendHead.updateSendHeadStatus", params);
	}

	@Override
	public void deleteSendHead(String billid) {
		executeUpdate("SendHead.deleteSendHead", billid);
	}

	@Override
	public void reviewSuccess(String billid, String userId) {
		this.modifySendHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userId);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		this.modifySendHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public Pager getOutVendorListData(Map<String, String> condition) {
		return executeQueryForPager("SendHead.getOutVendorList", "SendHead.getOutVendorCount", condition);
	}

	@Override
	public void modifyOutVendorIsBll(String billno, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("SendHead.modifyOutVendorIsBill", params);
	}

	@Override
	public void modifyOutVendorIsNotBll(String billid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("SendHead.modifyOutVendorIsNotBill", params);
	}

	public void modifyOutVendorBillByLineid(String lineid, String status, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineid", lineid);
		params.put("status", status);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("SendHead.modifyOutVendorBillByLineid", params);
	}
}
