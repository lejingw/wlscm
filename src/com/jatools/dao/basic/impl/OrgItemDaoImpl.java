package com.jatools.dao.basic.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.OrgItemDao;
import com.jatools.vo.basic.OrgItem;

public class OrgItemDaoImpl extends BaseDao implements OrgItemDao {

	@Override
	public Pager getOrgItemPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("OrgItem.getOrgItemPageData", "OrgItem.getOrgItemTotalCount", condition);
		return pager;
	}

	@Override
	public void saveOrgItem(OrgItem orgItem) {
		executeInsert("OrgItem.saveOrgItem", orgItem);
	}

	@Override
	public OrgItem getOrgItemById(String orgItemId) {
		OrgItem oi = (OrgItem) executeQueryForObject("OrgItem.getOrgItemById", orgItemId);
		return oi;
	}

	@Override
	public void updateOrgItem(OrgItem orgItem) {
		executeUpdate("OrgItem.updateOrgItem", orgItem);
	}

	@Override
	public void deleteOrgItem(String orgItemId) {
		executeUpdate("OrgItem.deleteOrgItemLJ", orgItemId);
	}

	@Override
	public List<OrgItem> getOrgItemList() {
		return null;
	}

	@Override
	public String getOrgItemByOrgId(Map<String, String> condition) {
		return (String) executeQueryForObject("OrgItem.getOrgItemByOrgId", condition);
	}

	@Override
	public void deleteOrgItemByOrgId(String orgId) {
		executeUpdate("OrgItem.deleteOrgItemByOrgId", orgId);
	}

	@Override
	public List<String> getItemByOrgId(String orgId) {
		return executeQueryForList("OrgItem.getItemByOrgId", orgId);
	}

}
