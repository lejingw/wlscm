package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcExitHeadDao;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.stock.ProcExitHead;
import com.jatools.web.util.DateUtil;

/**
 * 退料单头表Dao 实现类
 * @author ren.ming
 * Created 2011-11-28
 */
public class ProcExitHeadDaoImpl extends BaseDao implements ProcExitHeadDao, ReviewActionIntf {

	@Override
	public Pager getProcExitHeadData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcExitHead.getProcExitHeadPageData", "ProcExitHead.getProcExitHeadTotalCount", condition);
		return pager;
	}
	@Override
	public Pager getReportProcExitHeadData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcExitHead.getReportProcExitHeadData", "ProcExitHead.getReportProcExitHeadTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcExitHead(ProcExitHead procExitHead) {
		executeInsert("ProcExitHead.saveProcExitHead", procExitHead);

	}

	@Override
	public ProcExitHead getProcExitHead(String billid) {
		ProcExitHead head = (ProcExitHead)executeQueryForObject("ProcExitHead.getProcExitHead", billid);
		return head;
	}
	@Override
	public ProcExitHead getProcExitHeadByBillno(String billno) {
		ProcExitHead head = (ProcExitHead)executeQueryForObject("ProcExitHead.getProcExitHeadByBillno", billno);
		return head;
	}

	@Override
	public void updateProcExitHead(ProcExitHead procExitHead) {
		executeUpdate("ProcExitHead.updateProcExitHead", procExitHead);
	}
	@SuppressWarnings("rawtypes")
	public void modifyProcExitHeadStatus(Map params) {
		executeUpdate("ProcExitHead.updateProcExitHeadStatus", params);
	}
	
	public void modifyProcExitHeadStatus(String billid, String status, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		executeUpdate("ProcExitHead.updateProcExitHeadStatus", params);
	}
	
	@Override
	public void deleteProcExitHead(String billid) {
		executeUpdate("ProcExitHead.deleteProcExitHead", billid);
	}

	@Override
	public void reviewSuccess(String billid, String userid) {
		modifyProcExitHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		modifyProcExitHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public void insertProdccountByExit(String billid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("proExitHead.insertProdccountByExit", params);
	}

	@Override
	public void modifyProdAccountStatusInValid(String billno, String ornaCode, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("ornaCode", ornaCode);
		params.put("userId", userId);
		params.put("status", DictConstant.BILL_STATUS_REFERENCE);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("proExitHead.modifyProdAccountStatus", params);
	}

	@Override
	public void modifyProdAccountStatusValid(String lineid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("proExitHead.modifyProdAccountStatusValid", params);
	}

	@Override
	public void saveCashMoneyAccount(String billid, double money, double billType, String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billid", billid);
		params.put("money", money);
		params.put("billType", billType);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("ProcExitHead.cashCharge", params);
	}

	
	public CashProdAccount getProdAccountByOut(String billno, String ornaCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("ornaCode", ornaCode);
		return (CashProdAccount)executeQueryForObject("ProcExitHead.getProdAccountByOut", params);
	}
	
	public void subProdAccountUserNums(String billid, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcExitHead.subProdAccountUserNums", params);
	}
}
