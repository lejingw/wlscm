package com.jatools.dao.move.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.SpecialDiamDao;
import com.jatools.vo.move.SpecialDiam;
import com.jatools.web.util.DateUtil;

public class SpecialDiamDaoImpl extends BaseDao implements SpecialDiamDao {
	/**
	 * 获取分页数据
	 */
	public Pager getSpecialDiamPageData(Map<String, String> condition){
		return executeQueryForPager("SpecialDiam.getSpecialDiamPageData", "SpecialDiam.getSpecialDiamTotalCount", condition);
	}
	public Pager getSpecialDiamOrgPageData(Map<String, String> condition){
		return executeQueryForPager("SpecialDiam.getSpecialDiamOrgPageData", "SpecialDiam.getSpecialDiamOrgTotalCount", condition);
	}
	@Override
	public void saveSpecialDiam(SpecialDiam dn) {
		executeInsert("SpecialDiam.saveSpecialDiam", dn);
	}

	@Override
	public void updateSpecialDiam(SpecialDiam dn) {
		executeUpdate("SpecialDiam.updateSpecialDiam", dn);
	}
	
	public List<SpecialDiam> checkSpecialDiamRepeat(SpecialDiam dn) {
		return executeQueryForList("SpecialDiam.checkSpecialDiamRepeat", dn);
	}

	/**
	 * 删除
	 * @param billId
	 */
	public void deleteSpecialDiam(List<String> billIdList){
		executeBatchDelete("SpecialDiam.deleteSpecialDiam", billIdList);
	}
	@Override
	public void saveSpecialDiamFromExcel(String seqId, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seqId", seqId);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("SpecialDiam.saveSpecialDiamFromExcel", map);
	}
	public boolean isExisteSpecialDiamOrg(String orgId){
		Integer count = (Integer) executeQueryForObject("SpecialDiam.isExisteSpecialDiamOrg", orgId);
		return count>0;
	}
	public void saveSpecialDiamOrg(String orgId, String orgType, String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgId", orgId);
		map.put("orgType", orgType);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("SpecialDiam.saveSpecialDiamOrg", map);
	}
	public void updateSpecialDiamOrg(String orgId, String orgType, String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgId", orgId);
		map.put("orgType", orgType);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("SpecialDiam.updateSpecialDiamOrg", map);
	}
	public void deleteSpecialDiamOrg(String orgId){
		delete("SpecialDiam.deleteSpecialDiamOrg", orgId);
	}
}
