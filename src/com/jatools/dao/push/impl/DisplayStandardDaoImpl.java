package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.DisplayStandardDao;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.DisplayOrgtypeHead;
import com.jatools.vo.push.DisplayOrgtypeLine;
import com.jatools.vo.push.DisplayStandard;

public class DisplayStandardDaoImpl extends BaseDao implements DisplayStandardDao {
	
	public List<Map> getAllDisplayStandard() {
		return executeQueryForList("DisplayStandard.getAllDisplayStandard", null);
	}
	public List<Map> getAllDisplayOrgtype() {
		return executeQueryForList("DisplayStandard.getAllDisplayOrgtype", null);
	}
	/**
	 * 保存
	 */
	public void saveDisplayStandard(DisplayStandard dn){
		executeInsert("DisplayStandard.saveDisplayStandard", dn);
	}
	public void saveDisplayStandard2(DisplayStandard dn){
		executeInsert("DisplayStandard.saveDisplayStandard2", dn);
	}
	public void updateDisplayStandard(DisplayStandard dn) {
		executeUpdate("DisplayStandard.updateDisplayStandard", dn);
	}
	public void updateDisplayStandard2(DisplayStandard dn) {
		executeUpdate("DisplayStandard.updateDisplayStandard2", dn);
	}
	public void deleteDisplayStandard(List<String> billIdList){
		executeBatchDelete("DisplayStandard.deleteDisplayStandard", billIdList);
	}
	public void deleteDisplayStandard2(List<String> billIdList){
		executeBatchDelete("DisplayStandard.deleteDisplayStandard2", billIdList);
	}
	public void deleteDisplayStandardByHeadid(String headid){
		delete("DisplayStandard.deleteDisplayStandardByHeadid", headid);
	}
	public Pager getDisplayOrgTypePageData(Map<String, String> condition){
		return executeQueryForPager(
				"DisplayStandard.getDisplayOrgTypePageData",
				"DisplayStandard.getDisplayOrgTypeTotalCount", condition);
	}
	/**
	 * 获取推式陈列标准组织
	 * @param headid
	 * @return
	 */
	public List<Org> getDisplayOrg(String headid){
		return executeQueryForList("DisplayStandard.getDisplayOrg", headid);
	}
	public List<DisplayOrgtypeLine> getDisplayOrgByHeadid(String headid){
		return executeQueryForList("DisplayStandard.getDisplayOrgByHeadid", headid);
	}
	
	public String saveDisplayOrgtype(DisplayOrgtypeHead head){
		return (String) executeInsert("DisplayStandard.saveDisplayOrgtype", head);
	}
	public void updateDisplayOrgtype(DisplayOrgtypeHead head){
		executeUpdate("DisplayStandard.updateDisplayOrgtype", head);
	}
	public void saveDisplayOrgtypeLine(DisplayOrgtypeLine line){
		executeInsert("DisplayStandard.saveDisplayOrgtypeLine", line);
	}
	public void deleteDisplayOrgtype(String headid){
		delete("DisplayStandard.deleteDisplayOrgtype", headid);
	}
	public void deleteDisplayOrgtypeLine(String lineid){
		delete("DisplayStandard.deleteDisplayOrgtypeLine", lineid);
	}
	public void deleteDisplayOrgtypeLineByHeadid(String headid){
		delete("DisplayStandard.deleteDisplayOrgtypeLineByHeadid", headid);
	}
	public List<String> checkOrgAvail(String headid, String orgId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("orgId", orgId);
		return executeQueryForList("DisplayStandard.checkOrgAvail", map);
	}
	/**
	 * 获取选择款式分页数据
	 */
	public Pager getSelectStylePageData(String headid, String itemClassId, String ornaClassId,
			String analysisId, String styleItemClassId, String start,
			String limit){Map<String, String> condition = new HashMap<String, String>();
		condition.put("start", start);
		condition.put("limit", limit);
		condition.put("headid", headid);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("analysisId", analysisId);
		condition.put("styleItemClassId", styleItemClassId);
		return executeQueryForPager("DisplayStandard.getSelectStylePageData", "DisplayStandard.getSelectStyleTotalCount", condition);
	}
	public List<DisplayStandard> getDisplayStandardList(String headid){
		return executeQueryForList("DisplayStandard.getDisplayStandardList", headid);
	}
	public List<DisplayStandard> getDisplayStandardList2(String headid){
		return executeQueryForList("DisplayStandard.getDisplayStandardList2", headid);
	}
	public List<Map<String, String>> getRegionOrgs(){
		return executeQueryForList("DisplayStandard.getRegionOrgs", null);
	}
}
