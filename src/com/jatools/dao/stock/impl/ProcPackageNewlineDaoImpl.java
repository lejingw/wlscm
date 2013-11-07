package com.jatools.dao.stock.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcPackageNewlineDao;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.vo.stock.StockTransDO;

/**
 * 拆包单新行表Dao 实现类
 * @author ren.ming
 * Created 2011-11-20
 */
public class ProcPackageNewlineDaoImpl extends BaseDao implements ProcPackageNewlineDao {

	@Override
	public Pager getProcPackageNewlineData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcPackageNewline.getProcPackageNewlinePageData", "ProcPackageNewline.getProcPackageNewlineTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcPackageNewline(ProcPackageNewline procPackageNewline) {
		executeInsert("ProcPackageNewline.saveProcPackageNewline", procPackageNewline);

	}

	@Override
	public ProcPackageNewline getProcPackageNewline(String lineid) {
		ProcPackageNewline head = (ProcPackageNewline)executeQueryForObject("ProcPackageNewline.getProcPackageNewline", lineid);
		return head;
	}

	@Override
	public void updateProcPackageNewline(ProcPackageNewline procPackageNewline) {
		executeUpdate("ProcPackageNewline.updateProcPackageNewline", procPackageNewline);
	}

	@Override
	public void deleteProcPackageNewline(String lineid) {
		executeUpdate("ProcPackageNewline.deleteProcPackageNewline", lineid);
	}
	
	public void deleteProcPackageNewlineByBillid(String billid) {
		executeUpdate("ProcPackageNewline.deleteProcPackageNewlineByBillid", billid);
	}

	@SuppressWarnings("unchecked")
	public List<ProcPackageNewline> getProcPackageNewlineList(String billid) {
		return (List<ProcPackageNewline>)executeQueryForList("ProcPackageNewline.getProcPackageNewlineList", billid);
	}
	
	public void inStockInsertTrans(StockTransDO stockTransDO){
		executeInsert("ProcPackageNewline.instockInsertTrans", stockTransDO);
	}

	@Override
	public void insertInivFromMater(MaterIniv iniv) {
		executeInsert("ProcPackageNewline.insertInivFromMater", iniv);
	}
}
