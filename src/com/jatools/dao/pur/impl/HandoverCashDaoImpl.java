package com.jatools.dao.pur.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.HandoverCashDao;
import com.jatools.vo.pur.HandoverCash;

/**
 * 交接单结算行表Dao 实现类
 * @author ren.ming
 * Created 2011-11-20
 */
public class HandoverCashDaoImpl extends BaseDao implements HandoverCashDao {

	@Override
	public Pager getHandoverCashData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("HandoverCash.getHandoverCashPageData", "HandoverCash.getHandoverCashTotalCount", condition);
		return pager;
	}

	@Override
	public void saveHandoverCash(HandoverCash handoverCash) {
		executeInsert("HandoverCash.saveHandoverCash", handoverCash);

	}

	@Override
	public HandoverCash getHandoverCash(String lineid) {
		HandoverCash head = (HandoverCash)executeQueryForObject("HandoverCash.getHandoverCash", lineid);
		return head;
	}

	@Override
	public void updateHandoverCash(HandoverCash handoverCash) {
		executeUpdate("HandoverCash.updateHandoverCash", handoverCash);
	}

	@Override
	public void deleteHandoverCash(String lineid) {
		executeUpdate("HandoverCash.deleteHandoverCash", lineid);
	}
	
	public void deleteHandoverCashByBillid(String billid) {
		executeUpdate("HandoverCash.deleteHandoverCashByBillid", billid);
	}

	@SuppressWarnings("unchecked")
	public List<HandoverCash> getHandoverCashList(String billid) {
		return (List<HandoverCash>)executeQueryForList("HandoverCash.getHandoverCashList", billid);
	}
}
