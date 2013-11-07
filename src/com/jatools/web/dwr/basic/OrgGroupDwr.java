package com.jatools.web.dwr.basic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.OrgGroupManager;
import com.jatools.vo.basic.OrgGroup;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class OrgGroupDwr {
	private static Logger logger = Logger.getLogger(OrgGroupDwr.class);
	
	private OrgGroupManager orgGroupManager;
	
	/**
	 * 保存或修改POS对接数据
	 * @param orgGroup
	 */
	public Map<String, String> saveOrUpdateOrgGroup(OrgGroup orgGroup, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(StringUtil.isEmpty(orgGroup.getGroupId())){
				orgGroup.setCreateDate(DateUtil.getCurrentDate18());
				orgGroup.setCreateId(CommonUtil.getSessionUserId(req));
				orgGroup.setStatus(DictConstant.BILL_STATUS_SAVE);
			}
			orgGroup.setUpdateDate(DateUtil.getCurrentDate18());
			orgGroup.setUpdateId(CommonUtil.getSessionUserId(req));
			orgGroupManager.saveOrUpdateOrgGroup(orgGroup);
			result.put("isSuccess", "true");
			result.put("groupId", orgGroup.getGroupId());
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "保存出错");
			}
			return result;
		}
	}
	
	public String deleteOrgGroup(String groupId, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(groupId)){
				orgGroupManager.deleteOrgGroup(groupId);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}
	
	
	public Map<String, String> copyOrgGroupData(String params, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		JSONObject json = JSONObject.fromObject(params);
		String type = json.getString("type");
		String source = json.getString("source");
		String dest = json.getString("dest");
		try{
			if(StringUtil.isNotBlank(type) && StringUtil.isNotBlank(source) && StringUtil.isNotBlank(dest)){
				this.orgGroupManager.copyOrgGroup(type, source, dest, CommonUtil.getSessionUserId(req));
				result.put("isSuccess", "true");
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "复制失败");
			}
		} catch(Exception e){
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "复制失败");
			}
			result.put("isSuccess", "false");
		}
		return result;
	}
	public OrgGroupManager getOrgGroupManager() {
		return orgGroupManager;
	}

	public void setOrgGroupManager(OrgGroupManager orgGroupManager) {
		this.orgGroupManager = orgGroupManager;
	}
	
	
	
}
