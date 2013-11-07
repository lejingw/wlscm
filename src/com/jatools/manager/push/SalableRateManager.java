package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.SalableRate;

public interface SalableRateManager {
	/**
	 * 获取分页数据
	 */
	public Pager getSalableRatePageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateSalableRate(SalableRate dn, String userId);
	/**
	 * 删除
	 */
	public void deleteSalableRate(List<String> billIdList);
}
