package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcInventoryDifflineDao;
import com.jatools.vo.stock.ProcInventoryDiffline;
import com.jatools.web.util.DateUtil;

/**
 * 盘点差异单原行表Dao 实现类
 * @author ren.ming<br>
 * Created 2011-12-1
 */
public class ProcInventoryDifflineDaoImpl extends BaseDao implements ProcInventoryDifflineDao {

	@Override
	public Pager getProcInventoryDifflineData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcInventoryDiffline.getProcInventoryDifflinePageData", "ProcInventoryDiffline.getProcInventoryDifflineTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcInventoryDiffline(ProcInventoryDiffline procInventoryDiffline) {
		executeInsert("ProcInventoryDiffline.saveProcInventoryDiffline", procInventoryDiffline);
	}

	@Override
	public ProcInventoryDiffline getProcInventoryDiffline(String lineid) {
		ProcInventoryDiffline head = (ProcInventoryDiffline)executeQueryForObject("ProcInventoryDiffline.getProcInventoryDiffline", lineid);
		return head;
	}

	@Override
	public void updateProcInventoryDiffline(ProcInventoryDiffline procInventoryDiffline) {
		executeUpdate("ProcInventoryDiffline.updateProcInventoryDiffline", procInventoryDiffline);
	}

	@Override
	public void deleteProcInventoryDiffline(String lineid) {
		executeUpdate("ProcInventoryDiffline.deleteProcInventoryDiffline", lineid);
	}
	
	public void deleteProcInventoryDifflineByBillid(String billid) {
		executeUpdate("ProcInventoryDiffline.deleteProcInventoryDifflineByBillid", billid);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcInventoryDiffline> getProcInventoryDifflineList(String billid) {
		return (List<ProcInventoryDiffline>)executeQueryForList("ProcInventoryDiffline.getProcInventoryDifflineList", billid);
	}
	
	public int getNoStockCount(String billid, String orgId, String stockId) {
		Map<String , String > params = new HashMap<String, String>();
		params.put("stockId", stockId);
		params.put("orgId", orgId);
		params.put("billid", billid);
		return (Integer)executeQueryForObject("ProcInventoryDiffline.getNoStockCount", params);
	}
	
	public void insertDiffLines(Map<String, String> params) {
		executeInsert("ProcInventoryDiffline.insertDiffLines", params);
	}
	
	
	public void insertDiffSignLines(String inventId, String diffId, String userId) {
		Map<String , String > params = new HashMap<String, String>();
		params.put("billid", inventId);
		params.put("diffId", diffId);
		params.put("createId", userId);
		params.put("updateId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("ProcInventoryDiffline.insertDiffSignLines", params);
	}

	@Override
	public void insertDiffLinesByInvent(String srcBillid, String billid, String userId) {
		Map<String , String > params = new HashMap<String, String>();
		params.put("srcBillid", srcBillid);
		params.put("billid", billid);
		params.put("createId", userId);
		params.put("updateId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("ProcInventoryDiffline.insertDiffLinesByInvent", params);
	}
}
