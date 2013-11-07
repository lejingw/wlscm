package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.SalableRate;

public interface SalableRateDao {
	/**
	 * 获取分页数据
	 */
	public Pager getSalableRatePageData(Map<String, String> condition);

	/**
	 * 保存
	 * 
	 * @param dn
	 */
	public void saveSalableRate(SalableRate dn);

	/**
	 * 修改
	 * 
	 * @param dn
	 */
	public void updateSalableRate(SalableRate dn);

	/**
	 * 检查记录是否存在
	 * 
	 * @param dn
	 */
	public boolean checkSalableRateExists(SalableRate dn);

	/**
	 * 删除
	 * 
	 * @param billIdList
	 */
	public void deleteSalableRate(List<String> billIdList);

	public SalableRate getSalableRateByRegionId(String regionId);
}
