package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcExitLineDao;
import com.jatools.vo.stock.ProcExitLine;
import com.jatools.web.util.DateUtil;

/**
 * 退料单原行表Dao 实现类
 * @author ren.ming<br>
 * Created 2011-11-28
 */
public class ProcExitLineDaoImpl extends BaseDao implements ProcExitLineDao {

	@Override
	public Pager getProcExitLineData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcExitLine.getProcExitLinePageData", "ProcExitLine.getProcExitLineTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcExitLine(ProcExitLine ProcExitLine) {
		executeInsert("ProcExitLine.saveProcExitLine", ProcExitLine);
		this.addProdAccountUserNums(ProcExitLine.getLineid(), ProcExitLine.getUpdateId());
	}

	@Override
	public ProcExitLine getProcExitLine(String lineid) {
		ProcExitLine head = (ProcExitLine)executeQueryForObject("ProcExitLine.getProcExitLine", lineid);
		return head;
	}

	@Override
	public void updateProcExitLine(ProcExitLine ProcExitLine) {
		this.subProdAccountUserNums(ProcExitLine.getLineid(), ProcExitLine.getUpdateId());
		executeUpdate("ProcExitLine.updateProcExitLine", ProcExitLine);
		this.addProdAccountUserNums(ProcExitLine.getLineid(), ProcExitLine.getUpdateId());
	}

	@Override
	public void deleteProcExitLine(String lineid, String userId) {
		this.subProdAccountUserNums(lineid, userId);
		executeUpdate("ProcExitLine.deleteProcExitLine", lineid);
	}
	
	public void deleteProcExitLineByBillid(String billid) {
		executeUpdate("ProcExitLine.deleteProcExitLineByBillid", billid);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcExitLine> getProcExitLineList(String billid) {
		return (List<ProcExitLine>)executeQueryForList("ProcExitLine.getProcExitLineList", billid);
	}
	
	public void subProdAccountUserNums(String lineid, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcExitLine.subProdAccountUserNums", params);
	}
	
	private void addProdAccountUserNums(String lineid, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", lineid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcExitLine.addProdAccountUserNums", params);
	}
}
