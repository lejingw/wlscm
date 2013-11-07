package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.MaterialItem;

public interface MaterialItemDao {

	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getMaterialItemPageData(Map<String, String> condition);
	/**
	 * 保存原料大类关系
	 * @param materialItem
	 */
	void saveMaterialItem(MaterialItem materialItem);
	/**
	 * 根据id获取原料大类关系
	 * @param materialItemId
	 * @return
	 */
	MaterialItem getMaterialItem(String materialItemId);
	/**
	 * 保存或修改原料大类关系
	 * @param MaterialItem
	 */
	void updateMaterialItem(MaterialItem MaterialItem);
	/**
	 * 删除原料大类关系
	 * @param materialItemId
	 */
	void deleteMaterialItem(String materialItemId);
	
	/**
	 * 判断是否有重复的数据存在
	 * @param MaterialItem
	 * @return
	 */
	boolean isExistsmaterial(MaterialItem MaterialItem);
}
