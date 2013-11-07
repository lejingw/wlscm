package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.StandardDispatch;
import com.jatools.vo.move.StandardDispatchOrna;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.util.SelectorOption;

public interface StandardDispatchManager {

	Pager getStandardDispatchPageData(Map<String, String> condition);

	List<DispatchCondition> getDispatchCondition(String gheadid);

	DispatchCondition getDispatchConditionById(String conditionId);

	StandardDispatch getStandardDispatch(String gheadid);

	boolean checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId);

	String[] saveDispatchCondition(String gheadid, String itemClassId, String ornaClassId, String userid);

	void releaseLock(String conditionId, String userid);
	
	StandardDispatchOrna getStandardDispatchOrnaInfo(String code, boolean ornaFlag);

	DispatchOrnaLog getDispatchOrnaLogByOrnaCode(String ornaCode);

	Pager getMatchDispatchOrgPageData(String ornaCode, Map<String, String> condition);

	void saveDispatchOrnaLog(String gheadid, String ornaCode, String orgId, String dispatchFlag, String userid);

	Pager getShowDispatchedPageData(String gheadid, Map<String, String> condition);

	void deleteDispatchOrnaLog(String ornaLogId, String userid);

	void cancelDispatch(String gheadid, String ornaCode, String userid);

	void exchangeDispatching(String gheadid, String ornaCode, String orgId, String userid);

	void checkStandardDispatchDelete(String gheadid);

	void deleteStandardDispatch(List<String> gheadidList, String userid);

	List<MoveBillHead> createMoveBill(List<String> gheadidList, String outOrgId, String userid);
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
