package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcInventoryDiffheadDao;
import com.jatools.vo.stock.ProcInventoryDiffhead;
import com.jatools.web.util.DateUtil;

/**
 * 盘点差异单头表Dao 实现类
 * @author ren.ming
 * Created 2011-12-1
 */
public class ProcInventoryDiffheadDaoImpl extends BaseDao implements ProcInventoryDiffheadDao {

	@Override
	public Pager getProcInventoryDiffheadData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcInventoryDiffhead.getProcInventoryDiffheadPageData", "ProcInventoryDiffhead.getProcInventoryDiffheadTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcInventoryDiffhead(ProcInventoryDiffhead procInventoryDiffhead) {
		executeInsert("ProcInventoryDiffhead.saveProcInventoryDiffhead", procInventoryDiffhead);

	}

	@Override
	public ProcInventoryDiffhead getProcInventoryDiffhead(String billid) {
		ProcInventoryDiffhead head = (ProcInventoryDiffhead)executeQueryForObject("ProcInventoryDiffhead.getProcInventoryDiffhead", billid);
		return head;
	}

	@Override
	public void updateProcInventoryDiffhead(ProcInventoryDiffhead procInventoryDiffhead) {
		executeUpdate("ProcInventoryDiffhead.updateProcInventoryDiffhead", procInventoryDiffhead);
	}
	
	
	public void modifyProcInventoryDiffheadStatus(String billid, String status, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		executeUpdate("ProcInventoryDiffhead.updateProcInventoryDiffheadStatus", params);
	}

	@Override
	public void deleteProcInventoryDiffhead(String billid) {
		executeUpdate("ProcInventoryDiffhead.deleteProcInventoryDiffhead", billid);
	}

	@Override
	public void insertProcInventoryDiffhead(String srcBillId, String billid, String billno, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("srcBillId", srcBillId);
		params.put("billno", billno);
		params.put("userId", userId);
		params.put("dodate", DateUtil.getCurrentDate10());
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("createId", userId);
		executeUpdate("ProcInventoryDiffhead.insertProcInventoryDiffhead", params);
	}

	@Override
	public void updateDiffHeadInfo(String billid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcInventoryDiffhead.updateDiffheadInfo", params);
	}
	
	public String getBillid(){
		return (String)executeQueryForObject("ProcInventoryDiffhead.getBillid", null);
	}
}
