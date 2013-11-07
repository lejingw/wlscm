package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.DistributeNumDao;
import com.jatools.vo.push.DistributeNum;
import com.jatools.web.util.DateUtil;

public class DistributeNumDaoImpl extends BaseDao implements DistributeNumDao{
	/**
	 * 获取分页数据
	 */
	public Pager getDistributeNumPageData(Map<String, String> condition){
		return executeQueryForPager("DistributeNum.getDistributeNumPageData", "DistributeNum.getDistributeNumTotalCount", condition);
	}
	@Override
	public void saveDistributeNum(DistributeNum dn) {
		executeInsert("DistributeNum.saveDistributeNum", dn);
	}

	@Override
	public void updateDistributeNum(DistributeNum dn) {
		executeUpdate("DistributeNum.updateDistributeNum", dn);
	}
	
	public Integer checkDistributeNumRepeat(DistributeNum dn) {
		return (Integer) executeQueryForObject("DistributeNum.checkDistributeNumRepeat", dn);
	}

	/**
	 * 删除
	 * @param billId
	 */
	public void deleteDistributeNum(List<String> billIdList){
		executeBatchDelete("DistributeNum.deleteDistributeNum", billIdList);
	}
	/**
	 * 复制
	 */
	public void copyDistributeNum(String srcOrgId, String targetOrgIds, 	String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("srcOrgId", srcOrgId);
		map.put("targetOrgIds", targetOrgIds);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("DistributeNum.copyDistributeNum", map);
	}
	/**
	 * 复制时先删除原来的数据
	 */
	public void deleteDistributeNumByOrgs(String targetOrgIds, String userId){
		delete("DistributeNum.deleteDistributeNumByOrgs", targetOrgIds);
	}
	/**
	 * 导入excel
	 */
	public void saveDistributeNumFromExcel(String seqId, String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("seqId", seqId);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("DistributeNum.saveDistributeNumFromExcel", map);
	}
}
