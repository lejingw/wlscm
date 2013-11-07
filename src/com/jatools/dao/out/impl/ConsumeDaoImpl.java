package com.jatools.dao.out.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.out.ConsumeDao;
import com.jatools.vo.out.Consume;
import com.jatools.vo.out.ConsumeLine;
import com.jatools.web.util.DateUtil;

public class ConsumeDaoImpl extends BaseDao implements ConsumeDao,ReviewActionIntf {
	@SuppressWarnings("rawtypes")
	@Override
	public Pager getConsumePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("Consume.getConsumeAllPageData", "Consume.getConsumeToalCount", condition);
		return pager;
	}

	@Override
	public void saveConsume(Consume Consume) {
		executeInsert("Consume.saveConsume", Consume);
	}

	@Override
	public Consume getConsumeById(String id) {
		return (Consume)executeQueryForObject("Consume.getConsumeById", id);
	}

	@Override
	public void updateConsume(Consume Consume) {
		executeUpdate("Consume.updateConsume", Consume);
	}

	@Override
	public void deleteConsume(String id) {
		executeUpdate("Consume.deleteConsume", id);
	}
	/**
	 * 审批完成
	 * 状态设置为审批完成
	 */
	@Override
	public void reviewSuccess(String billid, String userid) {
		updateConsumStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		updateConsumStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}
	
	public void updateConsumStatus(String billid, String status, String userid) {
		Map<String , String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("Consume.updateConsumeStatus", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsumeLine> getLines(String billid) {
		return (List<ConsumeLine>)executeQueryForList("Consume.getLines", billid);
	}

	@Override
	public void insertLine(ConsumeLine line) {
		executeInsert("Consume.inserLine", line);
	}

	@Override
	public void updateLine(ConsumeLine line) {
		executeUpdate("Consume.updateLine", line);
	}

	@Override
	public void deleteLineById(String lineid) {
		executeUpdate("Consume.deleteLineById", lineid);
	}

	@Override
	public void deleteLineByBillid(String billid) {
		executeUpdate("Consume.deleteLineByBillid", billid);
	}

	public void insertMoneyAccountByConsume(String billid, String userId){
		Map<String , String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("userId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("Consume.insertMoneyAccountByConsume", params);
	}

}
