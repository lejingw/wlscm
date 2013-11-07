package com.jatools.dao.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.StandardDispatch;
import com.jatools.vo.move.StandardDispatchOrna;
import com.jatools.vo.util.SelectorOption;


public interface StandardDispatchDao {
	
	Pager getStandardDispatchPageData(Map<String, String> condition);

	StandardDispatch getStandardDispatch(String gheadid);

	StandardDispatchOrna getStandardDispatchOrnaInfo(String code, boolean ornaFlag);

	Pager getMatchDispatchOrgPageData(String ornaCode, Map<String, String> condition);

	String saveDispatchOrnaLog(String gheadid, String ornaCode, String orgId, String dispatchFlag, String userid);

	Pager getShowDispatchedPageData(String gheadid, Map<String, String> condition);

	void deleteDispatchOrnaLog(String gheadid, String ornaCode, String userid);

	void exchangeDispatching(String gheadid, String ornaCode, String orgId, String userid);
	
	String saveStandardDispatch(StandardDispatch sd);

	void deleteStandardDispatch(String gheadid, String userid);
	
	void deleteDispatchOrnaLogByGheadid(String gheadid, String userid);

	List<String> getCreateMovebillInorgs(String gheadids);

	List<String> getOrnaClodeFromMove(String gheadids, String inOrgId);
	
	void updateDispatchOrnaStatusToMoved(String gheadids, String userid);

	void closeStandardDispatch(String gheadids, String userid);
	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid);
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId);
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId);
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange);
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId);
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId);
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId);

}
