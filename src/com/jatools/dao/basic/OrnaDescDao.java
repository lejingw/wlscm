package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.OrnaDesc;

/**
 * @author  ren.ming<br>
 * <br>
 *  饰品名管理dao
 */
public interface OrnaDescDao {
	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getOrnaDescPageData(Map<String, String> condition);
	/**
	 * 保存饰品名
	 * @param ornaDesc
	 */
	void saveOrnaDesc(OrnaDesc ornaDesc);
	/**
	 * 根据id获取饰品名
	 * @param ornadscId
	 * @return
	 */
	OrnaDesc getOrnaDesc(String ornadscId);
	/**
	 * 保存或修改饰品名
	 * @param ornaDesc
	 */
	void updateOrnaDesc(OrnaDesc ornaDesc);
	
	/**
	 * 删除饰品名
	 * @param ornadscId
	 */
	void deleteOrnaDesc(String ornadscId);

	
	int selectOrnaDescCount(OrnaDesc ornaDesc);
}
