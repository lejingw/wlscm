package com.jatools.dao.move.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.StandardDispatchDao;
import com.jatools.vo.move.StandardDispatch;
import com.jatools.vo.move.StandardDispatchOrna;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class StandardDispatchDaoImpl extends BaseDao implements StandardDispatchDao {	
	public Pager getStandardDispatchPageData(Map<String, String> condition){
		return executeQueryForPager("StandardDispatch.getStandardDispatchPageData", "StandardDispatch.getStandardDispatchTotalCount", condition);
	}
	public StandardDispatch getStandardDispatch(String gheadid){
		return (StandardDispatch) executeQueryForObject("StandardDispatch.getStandardDispatch", gheadid);
	}
	public StandardDispatchOrna getStandardDispatchOrnaInfo(String code, boolean ornaFlag){
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("ornaFlag", ornaFlag?"1":"0");
		return (StandardDispatchOrna) executeQueryForObject("StandardDispatch.getStandardDispatchOrnaInfo", map);
	}
	public Pager getMatchDispatchOrgPageData(String ornaCode, Map<String, String> condition){
		StandardDispatchOrna info = getStandardDispatchOrnaInfo(ornaCode, true);
		//condition.put("ornaCode", ornaCode);
		condition.put("itemClassId", info.getItemClassId());
		condition.put("ornaClassId", info.getOrnaClassId());
		condition.put("analysisId", info.getAnalysisId());
		condition.put("styleId", info.getStyleId());
		condition.put("posAmount", info.getPosAmount());
		condition.put("basicPrice", info.getBasicPrice());
		return executeQueryForPager("StandardDispatch.getMatchDispatchOrgPageData", "StandardDispatch.getMatchDispatchOrgTotalCount", condition);
	}
	public String saveDispatchOrnaLog(String gheadid, String ornaCode, String orgId, String dispatchFlag, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("ornaCode", ornaCode);
		map.put("orgId", orgId);
		map.put("dispatchFlag", dispatchFlag);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		return (String) executeInsert("StandardDispatch.saveDispatchOrnaLog", map);
	}
	public Pager getShowDispatchedPageData(String gheadid, Map<String, String> condition){
		condition.put("gheadid", gheadid);
		return executeQueryForPager("StandardDispatch.getShowDispatchedPageData", "StandardDispatch.getShowDispatchedTotalCount", condition);
	}
	public void deleteDispatchOrnaLog(String gheadid, String ornaCode, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("ornaCode", ornaCode);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("StandardDispatch.deleteDispatchOrnaLog", map);
	}
	public void exchangeDispatching(String gheadid, String ornaCode, String orgId, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("ornaCode", ornaCode);
		map.put("orgId", orgId);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("StandardDispatch.exchangeDispatching", map);
	}
	public String saveStandardDispatch(StandardDispatch sd){
		return (String)executeInsert("StandardDispatch.saveStandardDispatch", sd);
	}
	public void deleteStandardDispatch(String gheadid, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("StandardDispatch.deleteStandardDispatch", map);
	}
	public void deleteDispatchOrnaLogByGheadid(String gheadid, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("StandardDispatch.deleteDispatchOrnaLogByGheadid", map);
	}
	public List<String> getCreateMovebillInorgs(String gheadids){
		return executeQueryForList("StandardDispatch.getCreateMovebillInorgs", gheadids);
	}

	public List<String> getOrnaClodeFromMove(String gheadids, String inOrgId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadids", gheadids);
		map.put("inOrgId", inOrgId);
		return executeQueryForList("StandardDispatch.getOrnaClodeFromMove", map);
	}
	
	public void updateDispatchOrnaStatusToMoved(String gheadids, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadids", gheadids);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("StandardDispatch.updateDispatchOrnaStatusToMoved", map);
	}
	public void closeStandardDispatch(String gheadids, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadids", gheadids);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("StandardDispatch.closeStandardDispatch", map);
	}

	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getItemClassForSlt(String headid){
		return executeQueryForList("StandardDispatch.getItemClassForSlt", headid);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		return executeQueryForList("StandardDispatch.getOrnaClassForSlt", map);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		return executeQueryForList("StandardDispatch.getAnalysisForSlt", map);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		return executeQueryForList("StandardDispatch.getStyleItemClassForSlt", map);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		return executeQueryForList("StandardDispatch.getStyleMiddleClassForSlt", map);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		map.put("styleMiddleClassId", styleMiddleClassId);
		return executeQueryForList("StandardDispatch.getStyleOrnaClassForSlt", map);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		map.put("styleMiddleClassId", styleMiddleClassId);
		map.put("styleOrnaClassId", styleOrnaClassId);
		return executeQueryForList("StandardDispatch.getStyleIdForSlt", map);
	}
}
