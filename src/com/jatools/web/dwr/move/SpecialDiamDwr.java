package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.manager.move.SpecialDiamManager;
import com.jatools.vo.move.SpecialDiam;
import com.jatools.web.util.StringUtil;

public class SpecialDiamDwr {
	private SpecialDiamManager specialDiamManager;

	public void setSpecialDiamManager(SpecialDiamManager specialDiamManager) {
		this.specialDiamManager = specialDiamManager;
	}

	public String saveOrUpdateSpecialDiam(SpecialDiam dn, HttpServletRequest req) {
		String userId = CommonUtil.getSessionUserId(req);
		List<SpecialDiam> list = specialDiamManager.checkSpecialDiamRepeat(dn);
		if (list.size() > 0) {
			return "存在 大类+小类+分析范围 相同的记录";
		}
		specialDiamManager.saveOrUpdateSpecialDiam(dn, userId);
		return null;
	}

	public String deleteSpecialDiam(List<String> billIdList) {
		specialDiamManager.deleteSpecialDiam(billIdList);
		return null;
	}
	public String changeSpecialDiamOrgType(String orgId, String orgType, HttpServletRequest req) {
		String userId = CommonUtil.getSessionUserId(req);
		if(StringUtil.isNotEmpty(orgType)){
			specialDiamManager.saveOrUpdateSpecialDiamOrg(orgId, orgType, userId);
		}else{
			specialDiamManager.deleteSpecialDiamOrg(orgId);
		}
		return null;
	}
	public String deleteSpecialDiamOrgs(List<String> orgIdList){
		if(null != orgIdList && orgIdList.size()>0){
			for(String orgId : orgIdList){
				specialDiamManager.deleteSpecialDiamOrg(orgId);
			}
		}
		return null;
	}
}
