package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.manager.move.DispatchWayManager;
import com.jatools.vo.move.DispatchWay;

public class DispatchWayDwr {
	private DispatchWayManager dispatchWayManager;

	public void setDispatchWayManager(DispatchWayManager dispatchWayManager) {
		this.dispatchWayManager = dispatchWayManager;
	}

	public String saveOrUpdateDispatchStyle(DispatchWay dn, HttpServletRequest req) {
		String userId = CommonUtil.getSessionUserId(req);
		List<DispatchWay> list = dispatchWayManager.checkDispatchStyleRepeat(dn);
		if (list.size() > 0) {
			return "存在 组织 大类+小类+分析范围+款式 相同的记录";
		}
		dispatchWayManager.saveOrUpdateDispatchStyle(dn, userId);
		return null;
	}

	public String deleteDispatchStyle(List<String> billIdList) {
		dispatchWayManager.deleteDispatchStyle(billIdList);
		return null;
	}
	//---------------------------------

	public String saveOrUpdateDispatchPrice(DispatchWay dn, HttpServletRequest req) {
		String userId = CommonUtil.getSessionUserId(req);
		List<DispatchWay> list = dispatchWayManager.checkDispatchPriceRepeat(dn);
		if (list.size() > 0) {
			return "存在 组织 大类+小类+分析范围+款式 相同的记录";
		}
		dispatchWayManager.saveOrUpdateDispatchPrice(dn, userId);
		return null;
	}

	public String deleteDispatchPrice(List<String> billIdList) {
		dispatchWayManager.deleteDispatchPrice(billIdList);
		return null;
	}
}
