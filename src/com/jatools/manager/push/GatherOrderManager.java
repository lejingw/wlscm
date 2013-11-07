package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.GatherOrderHead;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.util.SelectorOption;

public interface GatherOrderManager {

	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid, String setLineId, String salableFlag);
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId);
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId);
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisArange);
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId);
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId);
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId);

	/**
	 * 获取总部总单行列表分页数据
	 */
	public Pager getGatherOrderLinePageData(String headid, String setLineId, String salableFlag, Map<String, String> condition);
	
	public Pager getGatherOrderPageData(Map<String, String> condition);
	public void saveNewOrderNum(String lineid, String newOrderNum);
	public void saveNewSize(String lineid, String newSizeId);
	public void cancelGatherOrder(String headid, String userid);
	public GatherOrderHead getGatherOrderHead(String headid);
	/**
	 * 根据推式总单id获取区域下的组织
	 * @param gatherOrderHeadId
	 * @return
	 */
	public List<Org> getOrgByGatherOrder(String gatherOrderHeadId);
	/**
	 * 强制关闭检查
	 * @param headid
	 * @param userid
	 */
	public void checkCloseGatherOrder(String headid, String userid);
	/**
	 * 强制关闭
	 * @param headid
	 * @param req
	 * @return
	 */
	public void closeGatherOrder(String headid, String userid);
	/**
	 * 强制开启
	 * @param headid
	 * @param req
	 * @return
	 */
	public void reopenGatherOrder(String headid, String userid);
	
}
