package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.OrnaDesc;

/**
 * @author  ren.ming
 * <br>
 *  饰品名manager
 */
public interface OrnaDescManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getOrnaDescPageData(Map<String, String> condition);


	/**
	 * 根据id获取饰品名
	 * @param ornadescId
	 * @return
	 */
	OrnaDesc getOrnaDesc(String ornadescId);

	/**
	 * 删除饰品名
	 * @param ornadescId
	 */
	void deleteOrnaDesc(String ornadescId);
	
	/**
	 * 保存或修改饰品名
	 * @param ornaDesc
	 */
	public void saveOrUpdateOrnaDesc(OrnaDesc ornaDesc, String userId);

}
