package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.DisplayOrgtypeHead;
import com.jatools.vo.push.DisplayOrgtypeLine;
import com.jatools.vo.push.DisplayStandard;

public interface DisplayStandardDao {
	public List<Map> getAllDisplayStandard();
	public List<Map> getAllDisplayOrgtype();
	/**
	 * 保存
	 * @param dn
	 */
	public void saveDisplayStandard(DisplayStandard dn);
	public void saveDisplayStandard2(DisplayStandard dn);
	/**
	 * 修改
	 * @param dn
	 */
	public void updateDisplayStandard(DisplayStandard dn);
	public void updateDisplayStandard2(DisplayStandard dn);
	public void deleteDisplayStandard(List<String> billIdList);
	public void deleteDisplayStandard2(List<String> billIdList);
	public void deleteDisplayStandardByHeadid(String headid);
	public Pager getDisplayOrgTypePageData(Map<String, String> condition);
	/**
	 * 获取推式陈列标准组织
	 * @param headid
	 * @return
	 */
	public List<Org> getDisplayOrg(String headid);
	public List<DisplayOrgtypeLine> getDisplayOrgByHeadid(String headid);
	
	public String saveDisplayOrgtype(DisplayOrgtypeHead head);
	public void updateDisplayOrgtype(DisplayOrgtypeHead head);
	public void saveDisplayOrgtypeLine(DisplayOrgtypeLine line);
	public void deleteDisplayOrgtype(String headid);
	public void deleteDisplayOrgtypeLine(String lineid);
	public void deleteDisplayOrgtypeLineByHeadid(String headid);
	public List<String> checkOrgAvail(String headid, String orgId);

	/**
	 * 获取选择款式分页数据
	 */
	public Pager getSelectStylePageData(String headid, String itemClassId, String ornaClassId,
			String analysisId, String styleItemClassId, String start,
			String limit);
	/**
	 * 畅销款
	 * @param headid
	 * @return
	 */
	public List<DisplayStandard> getDisplayStandardList(String headid);
	/**
	 * 非畅销款
	 * @param head
	 * @return
	 */
	public List<DisplayStandard> getDisplayStandardList2(String head);
	public List<Map<String, String>> getRegionOrgs();
}
