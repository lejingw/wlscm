package com.jatools.dao.stock.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcChangeLineDao;
import com.jatools.vo.stock.ProcChangeLine;

/**
 * 形态转换单原行表Dao 实现类
 * @author ren.ming<br>
 * Created 2011-11-28
 */
public class ProcChangeLineDaoImpl extends BaseDao implements ProcChangeLineDao {

	@Override
	public Pager getProcChangeLineData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcChangeLine.getProcChangeLinePageData", "ProcChangeLine.getProcChangeLineTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcChangeLine(ProcChangeLine ProcChangeLine) {
		executeInsert("ProcChangeLine.saveProcChangeLine", ProcChangeLine);
	}

	@Override
	public ProcChangeLine getProcChangeLine(String lineid) {
		ProcChangeLine head = (ProcChangeLine)executeQueryForObject("ProcChangeLine.getProcChangeLine", lineid);
		return head;
	}

	@Override
	public void updateProcChangeLine(ProcChangeLine ProcChangeLine) {
		executeUpdate("ProcChangeLine.updateProcChangeLine", ProcChangeLine);
	}

	@Override
	public void deleteProcChangeLine(String lineid) {
		executeUpdate("ProcChangeLine.deleteProcChangeLine", lineid);
	}
	
	public void deleteProcChangeLineByBillid(String billid) {
		executeUpdate("ProcChangeLine.deleteProcChangeLineByBillid", billid);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcChangeLine> getProcChangeLineList(String billid) {
		return (List<ProcChangeLine>)executeQueryForList("ProcChangeLine.getProcChangeLineList", billid);
	}
}
