package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.OrgGroupDao;
import com.jatools.manager.basic.OrgGroupManager;
import com.jatools.vo.basic.OrgGroup;
import com.jatools.web.util.StringUtil;

/**
 * @author  ren.ming
 * @Created 2011.11.16
 * <br>
 *  与pos接口数据manager实现类
 */
public class OrgGroupManagerImpl implements OrgGroupManager {
	private OrgGroupDao orgGroupDao;

	public void setOrgGroupDao(OrgGroupDao orgGroupDao) {
		this.orgGroupDao = orgGroupDao;
	}


	public Pager getOrgGroupPageData(Map<String, String> condition){
		return orgGroupDao.getOrgGroupPageData(condition);
	}
	
	
	public void saveOrgGroup(OrgGroup orgGroup){
		orgGroupDao.saveOrgGroup(orgGroup);
	}
	
	public OrgGroup getOrgGroup(String groupId){
		return orgGroupDao.getOrgGroup(groupId);
	}

	
	public void updateOrgGroup(OrgGroup orgGroup){
		orgGroupDao.updateOrgGroup(orgGroup);
	}
	/**
	 * groupids 
	 */
	public void deleteOrgGroup(String groupids){
		if(StringUtil.isNotBlank(groupids)){
			String groupidArr[] = groupids.split(";");
			for(String groupid : groupidArr) {
				if(StringUtil.isNotBlank(groupid)){
					orgGroupDao.deleteOrgGroup(groupid);
				}
			}
		}
	}
	
	
	public void saveOrUpdateOrgGroup(OrgGroup orgGroup){
		int count = this.orgGroupDao.selectOrgGroupCount(orgGroup);
		if(count >0){
			throw new RuntimeException("保存失败，组织-大类-小类 已经存在柜组");
		}
		String groupId = orgGroup.getGroupId();
		if(StringUtil.isEmpty(groupId)){
			orgGroupDao.saveOrgGroup(orgGroup);
		}else{
			orgGroupDao.updateOrgGroup(orgGroup);
		}
	}

	@Override
	public void copyOrgGroup(String type, String source, String dest, String userId) {
		this.orgGroupDao.copyOrgGroup(type, source, dest, userId);
	}
}
