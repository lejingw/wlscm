package com.jatools.dao.basic.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.OrgGroupDao;
import com.jatools.vo.basic.OrgGroup;
import com.jatools.web.util.DateUtil;
/**
 * @author  ren.ming
 * @Created 2011.11.16
 * <br>
 *  与pos接口数据dao实现类
 */
public class OrgGroupDaoImpl extends BaseDao implements OrgGroupDao{
	
	public Pager getOrgGroupPageData(Map<String, String> condition){
		Pager pager = executeQueryForPager("OrgGroup.getOrgGroupPageData", "OrgGroup.getOrgGroupTotalCount", condition);
		return pager;
	}
	
	
	public void saveOrgGroup(OrgGroup orgGroup){
		executeInsert("OrgGroup.saveOrgGroup", orgGroup);
	}
	
	
	public OrgGroup getOrgGroup(String groupId){
		OrgGroup rule = (OrgGroup) executeQueryForObject("OrgGroup.getOrgGroup", groupId);
		return rule;
	}
	
	
	public void updateOrgGroup(OrgGroup orgGroup){
		executeUpdate("OrgGroup.updateOrgGroup", orgGroup);
	}
	
	
	public void deleteOrgGroup(String groupId){
		executeUpdate("OrgGroup.deleteOrgGroup", groupId);
	}


	@Override
	public int selectOrgGroupCount(OrgGroup orgGroup) {
		Integer res = (Integer)executeQueryForObject("OrgGroup.selectOrgGroupCount", orgGroup);
		if(res != null){
			return res.intValue();
		}
		return 0;
	}


	@Override
	public void copyOrgGroup(String type, String source, String dest, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("source", source);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("createId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("updateId", userId);
		if(OrgGroupDao.ORG.equals(type)) {
			String orgIds[] = dest.split(",");
			for(String orgId : orgIds){
				params.put("dest", orgId);
				this.executeInsert("OrgGroup.CopyOrgGroup", params);
			}
		} else {
			params.put("dest", dest);
			this.executeInsert("OrgGroup.CopyOrgGroup", params);
		}
	}
}
