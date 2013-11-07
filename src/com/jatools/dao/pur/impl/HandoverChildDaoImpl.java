package com.jatools.dao.pur.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.HandoverChildDao;
import com.jatools.vo.pur.HandoverChild;
import com.jatools.web.util.DateUtil;
/**
 * 
 * @author ren.ming
 * 
 */
@SuppressWarnings("unchecked")
public class HandoverChildDaoImpl extends BaseDao implements HandoverChildDao {
	
	@Override
	public List<HandoverChild> getHandoverChildList(String lineid) {
		return (List<HandoverChild>)executeQueryForList("HandoverChild.getHandoverChildList", lineid);
	}

	@Override
	public void saveHandoverChild(HandoverChild handoverChild) {
		executeInsert("HandoverChild.saveHandoverChild", handoverChild);
	}

	@Override
	public HandoverChild getHandoverChild(String childid) {
		return (HandoverChild)executeQueryForObject("HandoverChild.getHandoverChild", childid);
	}

	@Override
	public void updateHandoverChild(HandoverChild handoverChild) {
		executeUpdate("HandoverChild.updateHandoverChild", handoverChild);
	}

	@Override
	public void deleteHandoverChild(String childid) {
		executeUpdate("HandoverChild.deleteHandoverChild", childid);
	}

	@Override
	public void deleteHandoverChildByLineid(String lineid) {
		executeUpdate("HandoverChild.deleteHandoverChildByLineid", lineid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getHandoverChildHistoryData(Map<String, String> condition) {
		int resCount = this.getHistoryCount(condition.get("lineid"));
		if(resCount > 0){
			return this.executeQueryForPager("HandoverChild.getHandoverChildHistoryData", "HandoverChild.getHandoverChildHistoryCount", condition);
		} else {
			return new Pager();
		}
	}

	@Override
	public int getHistoryCount(String lineid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineid", lineid);
		Integer res = (Integer)executeQueryForObject("HandoverChild.getHistoryCount", params);
		if(res!=null){
			return res.intValue();
		}
		return 0;
	}

	public void insertChildByExit(String exitBillid, String handoverId, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("exitBillid", exitBillid);
		params.put("handoverId", handoverId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("createId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("updateId", userId);
		executeInsert("HandoverChild.insertChildByExit", params);
	}
}
