package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.UnsaleOrna;

public interface UnsaleOrnaDao {

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
	void saveUnsaleOrna(UnsaleOrna unsaleOrna);
	
	/**
	 * 根据id获取
	 * @param unsaleId
	 * @return
	 */
	UnsaleOrna getUnsaleOrna(String unsaleId);
	
	/**
	 * 保存或修改
	 * @param unsaleOrna
	 */
	void updateUnsaleOrna(UnsaleOrna unsaleOrna);
	
	/**
	 * 删除
	 * @param unsaleId
	 */
	void deleteUnsaleOrna(String unsaleId);

	/**
	 * 判断是否存在
	 * @param 
	 * @return
	 */
	int isExists(UnsaleOrna unsaleOrna);
	
}
