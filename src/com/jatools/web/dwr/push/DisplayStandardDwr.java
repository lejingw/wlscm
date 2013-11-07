package com.jatools.web.dwr.push;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.push.DisplayStandardManager;
import com.jatools.vo.push.DisplayOrgtypeHead;
import com.jatools.vo.push.DisplayOrgtypeLine;
import com.jatools.vo.push.DisplayStandard;

public class DisplayStandardDwr {
	private DisplayStandardManager displayStandardManager;

	public void setDisplayStandardManager(
			DisplayStandardManager displayStandardManager) {
		this.displayStandardManager = displayStandardManager;
	}

	public String saveOrUpdateDisplayStandard(String headid,
			List<DisplayStandard> addList, List<DisplayStandard> updateList,
			List<String> deleteIdList, HttpServletRequest req) {
		String userId = CommonUtil.getSessionUserId(req);
		displayStandardManager.saveOrUpdateDisplayStandard(headid, addList,
				updateList, deleteIdList, userId);
		return null;
	}
	public String saveOrUpdateDisplayStandard2(String headid,
			List<DisplayStandard> addList, List<DisplayStandard> updateList,
			List<String> deleteIdList, HttpServletRequest req) {
		String userId = CommonUtil.getSessionUserId(req);
		displayStandardManager.saveOrUpdateDisplayStandard2(headid, addList,
				updateList, deleteIdList, userId);
		return null;
	}
	
	public String deleteDisplayStandardList2(String lineid){
		displayStandardManager.deleteDisplayStandardList2(lineid);
		return null;
	}

	public List<DisplayOrgtypeLine> getDisplayOrgByHeadid(String headid) {
		return displayStandardManager.getDisplayOrgByHeadid(headid);
	}

	public String saveOrUpdateDisplayOrgtype(DisplayOrgtypeHead head,
			List<DisplayOrgtypeLine> addLineList, List<String> deleteIdList,
			HttpServletRequest req) {
		String userId = CommonUtil.getSessionUserId(req);
		displayStandardManager.saveOrUpdateDisplayOrgtype(head, addLineList,
				deleteIdList, userId);
		return null;
	}

	/**
	 * 获取选择款式分页数据
	 */
	public Pager getSelectStylePageData(String headid, String itemClassId,
			String ornaClassId, String analysisId, String styleItemClassId,
			String start, String limit) {
		return displayStandardManager.getSelectStylePageData(headid,
				itemClassId, ornaClassId, analysisId, styleItemClassId, start,
				limit);
	}
	/**
	 * 畅销款
	 * @param headid
	 * @return
	 */
	public List<DisplayStandard> getDisplayStandardList(String headid){
		return displayStandardManager.getDisplayStandardList(headid);
	}
	/**
	 * 非畅销款
	 * @param headid
	 * @return
	 */
	public List<DisplayStandard> getDisplayStandardList2(String headid){
		return displayStandardManager.getDisplayStandardList2(headid);
	}
	/**
	 * 删除组织类别
	 */
	public String deleteDisplayOrgtype(List<String> billIdList){
		displayStandardManager.deleteDisplayOrgtype(billIdList);
		return null;
	}
}