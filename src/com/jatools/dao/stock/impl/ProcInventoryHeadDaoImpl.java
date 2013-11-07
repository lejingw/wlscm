package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcInventoryHeadDao;
import com.jatools.vo.stock.ProcInventoryHead;
import com.jatools.web.util.DateUtil;

/**
 * 盘点单头表Dao 实现类
 * @author ren.ming
 * Created 2011-12-1
 */
public class ProcInventoryHeadDaoImpl extends BaseDao implements ProcInventoryHeadDao {

	@Override
	public Pager getProcInventoryHeadData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcInventoryHead.getProcInventoryHeadPageData", "ProcInventoryHead.getProcInventoryHeadTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcInventoryHead(ProcInventoryHead procInventoryHead) {
		executeInsert("ProcInventoryHead.saveProcInventoryHead", procInventoryHead);

	}

	@Override
	public ProcInventoryHead getProcInventoryHead(String billid) {
		ProcInventoryHead head = (ProcInventoryHead)executeQueryForObject("ProcInventoryHead.getProcInventoryHead", billid);
		return head;
	}

	@Override
	public void updateProcInventoryHead(ProcInventoryHead procInventoryHead) {
		executeUpdate("ProcInventoryHead.updateProcInventoryHead", procInventoryHead);
	}

	public void modifyProcInventoryHeadStatus(String billid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		executeUpdate("ProcInventoryHead.updateProcInventoryHeadStatus", params);
	}
	@Override
	public void deleteProcInventoryHead(String billid) {
		executeUpdate("ProcInventoryHead.deleteProcInventoryHead", billid);
	}
	
	public Integer getMainBillCount(String orgId, String stockId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("stockId", stockId);
		params.put("orgId", orgId);
		return (Integer)executeQueryForObject("ProcInventoryHead.getMainBillCount", params);
	}
	
	public void modifyBillIsMain(String billid, String isMain){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("isMain", isMain);
		executeUpdate("ProcInventoryHead.modifyBillIsMain", params);
	}

	@Override
	public void updateInventorySumCount(String billid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcInventoryHead.updateInventorySumCount", params);
	}

	@Override
	public void mergeInventory(String mainBillId, String oldBillId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mainBillId", mainBillId);
		params.put("userId", userId);
		params.put("oldBillId", oldBillId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcInventoryHead.mergeInventory", params);
	}

	@Override
	public boolean asertIsMerged(String billid) {
		Integer res = (Integer)executeQueryForObject("ProcInventory.asertIsMerge", billid);
		if(res != null && res.intValue() >0){
			return true;
		}
		return false;
	}
}
