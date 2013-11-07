package com.jatools.web.dwr.basic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.OrgItemManager;
import com.jatools.vo.basic.OrgItem;
import com.jatools.web.util.DateUtil;

public class OrgItemDwr {
	private static Logger logger = Logger.getLogger(OrgItemDwr.class);
	private	OrgItemManager orgItemManager;
	
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		OrgItemDwr.logger = logger;
	}
	public OrgItemManager getOrgItemManager() {
		return orgItemManager;
	}
	public void setOrgItemManager(OrgItemManager orgItemManager) {
		this.orgItemManager = orgItemManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateOrgItem(OrgItem head ,String itemClassIds,HttpServletRequest req){
		try {
			String is[] = itemClassIds.split("_");
			if(null==head.getOrgItemId()||"".equals(head.getOrgItemId())){
				for(int i = 0; i < is.length; i++){
					Map<String, String> condition = new HashMap<String, String>();
					condition.put("orgId", head.getOrgId());
					//condition.put("id", head.getOrgItemId());	
					String result = orgItemManager.getOrgItemByOrgId(condition);
					if(!"0".equals(result)){
						return "0_组织已维护大类";
					}
				}
			}
			orgItemManager.deleteOrgItemByOrgId(head.getOrgId());
			for (int i = 0; i < is.length; i++) {
				head.setItemClassId(is[i]);
				head.setOrgItemId(null);
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				head.setStatus(DictConstant.BILL_STATUS_SAVE);
				head.setUpdateDate(DateUtil.getCurrentDate18());
				head.setUpdateId(CommonUtil.getSessionUserId(req));
				orgItemManager.saveOrgItem(head);
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "0_保存出错";
		}
	}
}
//condition.put("orgId", head.getOrgId());
//condition.put("id", head.getOrgItemId());	
//String result = orgItemManager.getOrgItemByOrgId(condition);
//if(!"0".equals(result)){
//	return "0_组织已维护大类";
//}
//if(StringUtil.isEmpty(head.getOrgItemId())){
//	head.setCreateDate(DateUtil.getCurrentDate18());
//	head.setCreateId(CommonUtil.getSessionUserId(req));
//	head.setStatus(DictConstant.BILL_STATUS_SAVE);
//	orgItemManager.saveOrgItem(head);
//}else{
//	OrgItem old = orgItemManager.getOrgItemById(head.getOrgItemId());
//	if(old!=null){
//		head.setUpdateDate(DateUtil.getCurrentDate18());
//		head.setUpdateId(CommonUtil.getSessionUserId(req));
//		head.setCreateDate(old.getCreateDate());
//		head.setCreateId(old.getCreateId());
//		head.setStatus(DictConstant.BILL_STATUS_SAVE);
//		orgItemManager.updateOrgItem(head);
//	}
//}