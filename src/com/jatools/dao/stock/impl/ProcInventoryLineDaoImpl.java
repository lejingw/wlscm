package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcInventoryLineDao;
import com.jatools.vo.stock.ProcInventoryLine;

/**
 * 盘点单原行表Dao 实现类
 * @author ren.ming<br>
 * Created 2011-12-1
 */
public class ProcInventoryLineDaoImpl extends BaseDao implements ProcInventoryLineDao {

	@Override
	public Pager getProcInventoryLineData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcInventoryLine.getProcInventoryLinePageData", "ProcInventoryLine.getProcInventoryLineTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcInventoryLine(ProcInventoryLine procInventoryLine) {
		executeInsert("ProcInventoryLine.saveProcInventoryLine", procInventoryLine);
	}

	@Override
	public ProcInventoryLine getProcInventoryLine(String lineid) {
		ProcInventoryLine head = (ProcInventoryLine)executeQueryForObject("ProcInventoryLine.getProcInventoryLine", lineid);
		return head;
	}

	@Override
	public void updateProcInventoryLine(ProcInventoryLine procInventoryLine) {
		executeUpdate("ProcInventoryLine.updateProcInventoryLine", procInventoryLine);
	}

	@Override
	public void deleteProcInventoryLine(String lineid) {
		executeUpdate("ProcInventoryLine.deleteProcInventoryLine", lineid);
	}
	
	public void deleteProcInventoryLineByBillid(String billid) {
		executeUpdate("ProcInventoryLine.deleteProcInventoryLineByBillid", billid);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcInventoryLine> getProcInventoryLineList(String billid) {
		return (List<ProcInventoryLine>)executeQueryForList("ProcInventoryLine.getProcInventoryLineList", billid);
	}
	
	public boolean isExistOrnaCode(String ornaCode, String billid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ornaCode", ornaCode);
		params.put("billid", billid);
		Integer count = (Integer)executeQueryForObject("ProcInventoryLine.getCountByBillid", params);
		if(count != null && count.intValue() >0) {
			return true;
		}
		return false;
	}
}
