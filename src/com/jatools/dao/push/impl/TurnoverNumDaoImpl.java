package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.db2.jcc.a.se;
import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.TurnoverNumDao;
import com.jatools.vo.push.TurnoverNum;
import com.jatools.web.util.DateUtil;

public class TurnoverNumDaoImpl extends BaseDao implements TurnoverNumDao{
	/**
	 * 获取分页数据
	 */
	public Pager getTurnoverNumPageData(Map<String, String> condition){
		return executeQueryForPager("TurnoverNum.getTurnoverNumPageData", "TurnoverNum.getTurnoverNumTotalCount", condition);
	}
	@Override
	public void saveTurnoverNum(TurnoverNum dn) {
		executeInsert("TurnoverNum.saveTurnoverNum", dn);
	}

	@Override
	public void updateTurnoverNum(TurnoverNum dn) {
		executeUpdate("TurnoverNum.updateTurnoverNum", dn);
	}
	
	public List<TurnoverNum> checkTurnoverNumRepeat(TurnoverNum dn) {
		return executeQueryForList("TurnoverNum.checkTurnoverNumRepeat", dn);
	}
	
	/**
	 * 删除
	 * @param billId
	 */
	public void deleteTurnoverNum(List<String> billIdList){
		executeBatchDelete("TurnoverNum.deleteTurnoverNum", billIdList);
	}
	/**
	 * 复制
	 */
	public void copyTurnoverNum(String srcStartDate, String srcEndDate, String targetStartDate, String targetEndDate, String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("srcStartDate", srcStartDate);
		map.put("srcEndDate", srcEndDate);
		map.put("targetStartDate", targetStartDate);
		map.put("targetEndDate", targetEndDate);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("TurnoverNum.copyTurnoverNum", map);
	}
	/**
	 * 复制时先删除原来的数据
	 */
	public void deleteTurnoverNumByDate(String startDate, String endDate, String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		delete("TurnoverNum.deleteTurnoverNumByDate", map);
	}
	public void saveTurnoverNumFromExcel(String seqId, String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("seqId", seqId);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("TurnoverNum.saveTurnoverNumFromExcel", map);
	}
}