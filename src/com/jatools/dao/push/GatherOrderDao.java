package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.GatherOrderHead;
import com.jatools.vo.push.GatherOrderLine;
import com.jatools.vo.util.SelectorOption;

public interface GatherOrderDao {

	/**
	 * 获取类别下实际需要的畅销款下单数量
	 * @param lineid
	 * @return
	 */
	public String getRealSalableOrderNum(String lineid);
	public void saveGatherOrderLineByItem(List<Map<String, String>> map);
	public void deleteGatherOrderLineBySetLineId(String setLineId);
	public void deleteGatherOrderLineByHeadid(String headid);

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
	public void updateGatherSetLineOrderNum(String lineid, String newOrderNum);
	public boolean checkSizeExists(String lineid, String newSizeId);
	public void updateGatherSetLineSizeId(String lineid, String newSizeId);
	public GatherOrderLine getGatherOrderLine(String lineid);
	public GatherOrderHead getGatherOrderHead(String gheadid);

	/**
	 * 根据推式总单id获取区域下的组织
	 * @param gatherOrderHeadId
	 * @return
	 */
	public List<Org> getOrgByGatherOrder(String gatherOrderHeadId);
	public boolean checkHasPushDispatch(String headid);

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

    void createOrderLineByHeadid(String headid, String userId);
}
