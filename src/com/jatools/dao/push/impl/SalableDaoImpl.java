package com.jatools.dao.push.impl;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.SalableDao;
import com.jatools.vo.push.Salable;

import java.util.List;
import java.util.Map;

public class SalableDaoImpl extends BaseDao implements SalableDao{
	/**
	 * 获取分页数据
	 */
	public Pager getSalablePageData(Map<String, String> condition){
		return executeQueryForPager("Salable.getSalablePageData", "Salable.getSalableTotalCount", condition);
	}

	@Override
	public void saveSalable(Salable dn) {
		executeInsert("Salable.saveSalable", dn);
	}

	@Override
	public void updateSalable(Salable dn) {
		executeUpdate("Salable.updateSalable", dn);
	}
	
	public boolean checkSalableExists(Salable dn) {
		Integer count = (Integer)executeQueryForObject("Salable.checkSalableExists", dn);
		return count >0;
	}

	/**
	 * 删除
	 * @param billIdList
	 */
	public void deleteSalable(List<String> billIdList){
		executeBatchDelete("Salable.deleteSalable", billIdList);
	}

	public Salable getSalableById(String id){
		return (Salable)executeQueryForObject("Salable.getSalableById", id);
	}
}
