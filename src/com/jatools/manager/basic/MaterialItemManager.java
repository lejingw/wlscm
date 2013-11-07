package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.MaterialItem;

public interface MaterialItemManager {

	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getMaterialItemPageData(Map<String, String> condition);
	/**
	 * 保存或修改原料大类关系
	 * @param materialItem
	 */
	void saveOrUpdateMaterialItem(MaterialItem materialItem, String userId);
	/**
	 * 根据id获取原料大类关系
	 * @param materialItemId
	 * @return
	 */
	MaterialItem getMaterialItem(String materialItemId);
	
	/**
	 * 删除原料大类关系
	 * @param materialItemId
	 */
	void deleteMaterialItem(String materialItemId);
}
