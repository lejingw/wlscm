package com.jatools.web.dwr.push;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jatools.common.CommonUtil;
import com.jatools.manager.push.DistributeNumManager;
import com.jatools.vo.push.DistributeNum;

public class DistributeNumDwr {
	private DistributeNumManager distributeNumManager;
	
	public void setDistributeNumManager(DistributeNumManager distributeNumManager) {
		this.distributeNumManager = distributeNumManager;
	}

	public String saveOrUpdateDistributeNum(DistributeNum dn, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		if(distributeNumManager.checkDistributeNumRepeat(dn)>0){
			return "组织+大类+小类+分析范围+款式大类 已经存在";
		}
		distributeNumManager.saveOrUpdateDistributeNum(dn, userId);
		return null;
	}
	
	public String deleteDistributeNum(List<String> billIdList){
		distributeNumManager.deleteDistributeNum(billIdList);
		return null;
	}
	/**
	 * 复制
	 */
	public String copyDistributeNum(String srcOrgId, String targetOrgIds, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		distributeNumManager.copyDistributeNum(srcOrgId, targetOrgIds, userId);
		return null;
	}
}
