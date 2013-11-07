package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpSession;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.ExpressReceuserManager;

public class ExpressReceuserDwr {
	private ExpressReceuserManager expressReceuserManager;

	public void setExpressReceuserManager(
			ExpressReceuserManager expressReceuserManager) {
		this.expressReceuserManager = expressReceuserManager;
	}
	
	/**
	 * 保存
	 * @param orgId
	 * @param addUserIds
	 * @param deleteUserIds
	 * @return
	 */
	public String saveExpressReceuser(String orgId, String[] addUserIds, String[] deleteReceIds, HttpSession session){
		expressReceuserManager.saveExpressReceuser(orgId, addUserIds, deleteReceIds, CommonUtil.getSessionUserId(session));
		return null;
	}
	
}
