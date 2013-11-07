package com.jatools.manager.push;

import com.jatools.common.Pager;
import com.jatools.vo.push.Salable;

import java.util.List;
import java.util.Map;

public interface SalableManager {
	/**
	 * 获取分页数据
	 */
	public Pager getSalablePageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateSalable(Salable dn, String userId);
	/**
	 * 删除
	 */
	public void deleteSalable(List<String> billIdList);

    Salable getSalableById(String id);
}
