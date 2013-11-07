package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.UnsaleOrna;

public interface UnsaleOrnaManager {

	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getUnsaleOrnaPageData(Map<String, String> condition);
	
	/**
	 * 保存
	 * @param unsaleOrna
	 */
	void saveOrUpdateUnsaleOrna(UnsaleOrna unsaleOrna, String userId);
	
	/**
	 * 根据id获取
	 * @param unsaleId
	 * @return
	 */
	UnsaleOrna getUnsaleOrna(String unsaleId);
	
	/**
	 * 删除
	 * @param unsaleIds
	 */
	void deleteUnsaleOrna(String unsaleIds);
}
