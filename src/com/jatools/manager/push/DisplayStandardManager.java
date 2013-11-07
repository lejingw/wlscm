package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.DisplayOrgtypeHead;
import com.jatools.vo.push.DisplayOrgtypeLine;
import com.jatools.vo.push.DisplayStandard;

public interface DisplayStandardManager {
	public List<Map> getAllDisplayStandard();
	public List<Map> getAllDisplayOrgtype();

	/**
	 * 保存或修改
	 */
	public void saveOrUpdateDisplayStandard(String headid, List<DisplayStandard> addList,
			List<DisplayStandard> updateList, List<String> deleteIdList,
			String userId);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateDisplayStandard2(String headid, List<DisplayStandard> addList,
			List<DisplayStandard> updateList, List<String> deleteIdList,
			String userId);

	public void deleteDisplayStandardList2(String lineid);
	public Pager getDisplayOrgTypePageData(Map<String, String> condition);

	/**
	 * 获取推式陈列标准组织
	 * 
	 * @param headid
	 * @return
	 */
	public List<Org> getDisplayOrg(String headid);

	public List<DisplayOrgtypeLine> getDisplayOrgByHeadid(String headid);

	public void saveOrUpdateDisplayOrgtype(DisplayOrgtypeHead head,
			List<DisplayOrgtypeLine> addLineList, List<String> deleteIdList,
			String userId);

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
	 * @param headid
	 * @return
	 */
	public List<DisplayStandard> getDisplayStandardList2(String headid);

	public List<Map<String, String>> getRegionOrgs();
	/**
	 * 删除组织类别
	 * @param billIdList
	 */
	public void deleteDisplayOrgtype(List<String> billIdList);
}
