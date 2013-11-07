package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcPackageOldlineDao;
import com.jatools.vo.stock.ProcPackageOldline;
import com.jatools.web.util.DateUtil;

/**
 * 拆包单原行表Dao 实现类
 * @author ren.ming<br>
 * Created 2011-11-20
 */
public class ProcPackageOldlineDaoImpl extends BaseDao implements ProcPackageOldlineDao {

	@Override
	public Pager getProcPackageOldlineData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcPackageOldline.getProcPackageOldlinePageData", "ProcPackageOldline.getProcPackageOldlineTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcPackageOldline(ProcPackageOldline procPackageOldline) {
		executeInsert("ProcPackageOldline.saveProcPackageOldline", procPackageOldline);
	}

	@Override
	public ProcPackageOldline getProcPackageOldline(String lineid) {
		ProcPackageOldline head = (ProcPackageOldline)executeQueryForObject("ProcPackageOldline.getProcPackageOldline", lineid);
		return head;
	}

	@Override
	public void updateProcPackageOldline(ProcPackageOldline procPackageOldline) {
		executeUpdate("ProcPackageOldline.updateProcPackageOldline", procPackageOldline);
	}

	@Override
	public void deleteProcPackageOldline(String lineid) {
		executeUpdate("ProcPackageOldline.deleteProcPackageOldline", lineid);
	}
	
	public void deleteProcPackageOldlineByBillid(String billid) {
		executeUpdate("ProcPackageOldline.deleteProcPackageOldlineByBillid", billid);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcPackageOldline> getProcPackageOldlineList(String billid) {
		return (List<ProcPackageOldline>)executeQueryForList("ProcPackageOldline.getProcPackageOldlineList", billid);
	}
	
	public void outStockInsertTrans(String lineid, String transSourceBill, String transFinance, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineid", lineid);
		params.put("transSourceBill", transSourceBill);
		params.put("transFinance", transFinance);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		executeInsert("ProcPackageOldline.outStockInsertTrans", params);
	}
}
