package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.JmSaleDao;
import com.jatools.vo.stock.JmSaleHead;
import com.jatools.vo.stock.JmSaleLine;
import com.jatools.web.util.DateUtil;

public class JmSaleDaoImpl extends BaseDao implements JmSaleDao, ReviewActionIntf {

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getJmSaleData(Map<String, String> condition) {
		return executeQueryForPager("JmSale.getJmSalePageData", "JmSale.getJmSaleTotalCount", condition);
	}

	@Override
	public void saveJmSaleHead(JmSaleHead JmSaleHead) {
		this.executeInsert("JmSale.saveJmSale", JmSaleHead);
	}

	@Override
	public JmSaleHead getJmSaleHead(String billid) {
		return (JmSaleHead)executeQueryForObject("JmSale.getJmSale", billid);
	}

	@Override
	public void updateJmSaleHead(JmSaleHead JmSaleHead) {
		this.executeUpdate("JmSale.updateJmSale", JmSaleHead);
	}

	@Override
	public void modifyJmSaleHeadStatus(String billid, String status,
			String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("JmSale.updateJmSaleStatus", params);
	}

	@Override
	public void deleteJmSaleHead(String billid) {
		executeUpdate("JmSale.deleteJmSale", billid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JmSaleLine> getLines(String billid) {
		return executeQueryForList("JmSale.getLines", billid);
	}

	@Override
	public void insertLine(JmSaleLine line) {
		this.executeInsert("JmSale.insertLine", line);
	}

	@Override
	public void updateLine(JmSaleLine line) {
		this.executeUpdate("JmSale.updateLine", line);
	}

	@Override
	public void deleteLineById(String lineid) {
		executeUpdate("JmSale.deleteLineById", lineid);
	}

	@Override
	public void deleteLineByBillid(String billid) {
		executeUpdate("JmSale.deleteLineByBillid", billid);
	}

	@Override
	public void reviewSuccess(String billid, String userid) {
		this.modifyJmSaleHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userid);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		this.modifyJmSaleHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}

}
