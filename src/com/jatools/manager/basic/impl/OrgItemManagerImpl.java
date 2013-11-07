package com.jatools.manager.basic.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.OrgItemDao;
import com.jatools.manager.basic.OrgItemManager;
import com.jatools.vo.basic.OrgItem;

public class OrgItemManagerImpl implements OrgItemManager {
	
	private OrgItemDao orgItemDao;
	
	public void setOrgItemDao(OrgItemDao orgItemDao) {
		this.orgItemDao = orgItemDao;
	}

	@Override
	public Pager getOrgItemPageData(Map<String, String> condition) {
		return orgItemDao.getOrgItemPageData(condition);
	}

	@Override
	public void saveOrgItem(OrgItem orgItem) {
		orgItemDao.saveOrgItem(orgItem);
	}

	@Override
	public OrgItem getOrgItemById(String orgItemId) {
		return orgItemDao.getOrgItemById(orgItemId);
	}

	@Override
	public void updateOrgItem(OrgItem orgItem) {
		orgItemDao.updateOrgItem(orgItem);
	}

	@Override
	public void deleteOrgItem(String orgItemId) {
		orgItemDao.deleteOrgItem(orgItemId);
	}

	@Override
	public List<OrgItem> getOrgItemList() {
		return null;
	}

	@Override
	public String getOrgItemByOrgId(Map<String, String> condition) {
		return orgItemDao.getOrgItemByOrgId(condition);
	}

	@Override
	public void deleteOrgItemByOrgId(String orgId) {
		orgItemDao.deleteOrgItemByOrgId(orgId);
	}

	@Override
	public List<String> getItemByOrgId(String orgId) {
		return orgItemDao.getItemByOrgId(orgId);
	}

}
