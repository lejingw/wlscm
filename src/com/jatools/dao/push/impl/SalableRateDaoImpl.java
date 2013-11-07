package com.jatools.dao.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.SalableRateDao;
import com.jatools.vo.push.SalableRate;

public class SalableRateDaoImpl extends BaseDao implements SalableRateDao{
	/**
	 * 获取分页数据
	 */
	public Pager getSalableRatePageData(Map<String, String> condition){
		return executeQueryForPager("SalableRate.getSalableRatePageData", "SalableRate.getSalableRateTotalCount", condition);
	}
	@Override
	public void saveSalableRate(SalableRate dn) {
		executeInsert("SalableRate.saveSalableRate", dn);
	}

	@Override
	public void updateSalableRate(SalableRate dn) {
		executeUpdate("SalableRate.updateSalableRate", dn);
	}
	
	public boolean checkSalableRateExists(SalableRate dn) {
		Integer count = (Integer)executeQueryForObject("SalableRate.checkSalableRateExists", dn);
		return count >0;
	}

	/**
	 * 删除
	 * @param billId
	 */
	public void deleteSalableRate(List<String> billIdList){
		executeBatchDelete("SalableRate.deleteSalableRate", billIdList);
	}
	public SalableRate getSalableRateByRegionId(String regionId){
		return (SalableRate)executeQueryForObject("SalableRate.getSalableRateByRegionId", regionId);
	}
}
