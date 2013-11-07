package com.jatools.dao.push;

import com.jatools.common.Pager;
import com.jatools.vo.push.Salable;

import java.util.List;
import java.util.Map;

public interface SalableDao {
	/**
	 * 获取分页数据
	 */
	public Pager getSalablePageData(Map<String, String> condition);

	/**
	 * 保存
	 * 
	 * @param dn
	 */
	public void saveSalable(Salable dn);

	/**
	 * 修改
	 * 
	 * @param dn
	 */
	public void updateSalable(Salable dn);

	/**
	 * 检查记录是否存在
	 * 
	 * @param dn
	 */
	public boolean checkSalableExists(Salable dn);

	/**
	 * 删除
	 * 
	 * @param billIdList
	 */
	public void deleteSalable(List<String> billIdList);


    Salable getSalableById(String id);
}
