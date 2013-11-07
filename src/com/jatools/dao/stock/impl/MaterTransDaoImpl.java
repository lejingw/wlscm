package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.MaterTransDao;
import com.jatools.vo.stock.MaterTrans;
import com.jatools.web.util.DateUtil;

public class MaterTransDaoImpl extends BaseDao implements MaterTransDao {

	@Override
	public void insertInStock(MaterTrans matertrans) {
		matertrans.setTransFlag("1");
		executeInsert("MaterTrans.insertStock", matertrans);
	}

	@Override
	public void insertOutStock(MaterTrans matertrans) {
		matertrans.setTransFlag("-1");
		executeInsert("MaterTrans.insertStock", matertrans);
	}

	@Override
	public void insertFromMaterIniv(String srcBillId, String srcBillCode, String userId, String transSrcBillCode, String transSrcBillNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("srcBillId", srcBillId);
		params.put("srcBillCode", srcBillCode);
		params.put("createId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("transDate", DateUtil.getCurrentDate10());
		params.put("transSrcBillCode", transSrcBillCode);
		params.put("transSrcBillNo", transSrcBillNo);
		executeInsert("MaterTrans.insertFromMaterIniv", params);
	}

	@Override
	public void insertFromChangeLine(String srcBillId, String srcBillCode, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("srcBillId", srcBillId);
		params.put("srcBillCode", srcBillCode);
		params.put("createId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("transDate", DateUtil.getCurrentDate10());
		executeInsert("MaterTrans.insertFromChangeLine", params);
	}

	/**
	 * 写事物处理表
	 * @param billCode
	 * @param headid
	 * @param userid
	 * @param outFlag
	 */
	public void insertFromMoveBill(String billCode, String headid, String userid, boolean outFlag){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billCode", billCode);
		params.put("headid", headid);
		params.put("userid", userid);
		params.put("transDate", DateUtil.getCurrentDate10());
		params.put("date", DateUtil.getCurrentDate18());
		params.put("outFlag", outFlag?"1":"0");
		executeInsert("MaterTrans.insertFromMoveBill", params);
	}
}