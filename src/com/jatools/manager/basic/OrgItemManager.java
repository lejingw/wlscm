package com.jatools.manager.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.OrgItem;

public interface OrgItemManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getOrgItemPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveOrgItem(OrgItem orgItem);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	OrgItem getOrgItemById(String orgItemId);
	String getOrgItemByOrgId(Map<String,String> condition);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateOrgItem(OrgItem orgItem);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteOrgItem(String orgItemId);
	void deleteOrgItemByOrgId(String orgId);
	/**
	 * 采购组织大类所有列表
	 * @return
	 */
	List<OrgItem> getOrgItemList();
	List<String> getItemByOrgId(String orgId);
	
}
