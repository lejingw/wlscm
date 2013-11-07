package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.ExcludeOrnaDao;
import com.jatools.vo.push.ExcludeOrna;

public class ExcludeOrnaDaoImpl extends BaseDao implements ExcludeOrnaDao{
	/**
	 * 获取分页数据
	 */
	public Pager getExcludeOrnaPageData(Map<String, String> condition){
		return executeQueryForPager("ExcludeOrna.getExcludeOrnaPageData", "ExcludeOrna.getExcludeOrnaTotalCount", condition);
	}
	@Override
	public void saveExcludeOrna(ExcludeOrna dn) {
		executeInsert("ExcludeOrna.saveExcludeOrna", dn);
	}
	
	public Integer checkExcludeOrnaRepeat(String ornaCode) {
		return (Integer) executeQueryForObject("ExcludeOrna.checkExcludeOrnaRepeat", ornaCode);
	}

	/**
	 * 删除
	 * @param billId
	 */
	public void deleteExcludeOrna(List<String> billIdList){
		executeBatchDelete("ExcludeOrna.deleteExcludeOrna", billIdList);
	}
	/**
	 * 根据编码获取现有量信息
	 */
	public ExcludeOrna getMaterActiveByCode(String code, boolean ornaFlag){
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("ornaFlag", ornaFlag?"1":"0");
		return (ExcludeOrna) executeQueryForObject("ExcludeOrna.getMaterActiveByCode", map);
	}
	public Pager queryExcludeOrnaPageData(Map<String, String> condition){
		return executeQueryForPager("ExcludeOrna.queryExcludeOrnaPageData", "ExcludeOrna.queryExcludeOrnaTotalCount", condition);
	}
}
