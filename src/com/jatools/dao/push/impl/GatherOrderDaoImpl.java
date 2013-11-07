package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.GatherOrderDao;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.GatherOrderHead;
import com.jatools.vo.push.GatherOrderLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class GatherOrderDaoImpl extends BaseDao implements GatherOrderDao{

	/**
	 * 获取类别下实际需要的畅销款下单数量
	 * @param lineid
	 * @return
	 */
	public String getRealSalableOrderNum(String lineid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("date", DateUtil.getCurrentDate10());
		map.put("lineid", lineid);
		return (String) executeQueryForObject("GatherOrder.getRealSalableOrderNum", map);
	}

	public void saveGatherOrderLineByItem(List<Map<String, String>> map){
		executeBatchInsert("GatherOrder.saveGatherOrderLineByItem", map);
	}
	public void deleteGatherOrderLineBySetLineId(String lineid){
		delete("GatherOrder.deleteGatherOrderLineBySetLineId", lineid);
	}
	public void deleteGatherOrderLineByHeadid(String headid){
		delete("GatherOrder.deleteGatherOrderLineByHeadid", headid);
	}


	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getItemClassForSlt(String headid, String setLineId, String salableFlag){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("setLineId", setLineId);
		map.put("salableFlag", salableFlag);
		return executeQueryForList("GatherOrder.getItemClassForSlt", map);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getOrnaClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("setLineId", setLineId);
		map.put("salableFlag", salableFlag);
		map.put("itemClassId", itemClassId);
		return executeQueryForList("GatherOrder.getOrnaClassForSlt", map);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getAnalysisForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("setLineId", setLineId);
		map.put("salableFlag", salableFlag);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		return executeQueryForList("GatherOrder.getAnalysisForSlt", map);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("setLineId", setLineId);
		map.put("salableFlag", salableFlag);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		return executeQueryForList("GatherOrder.getStyleItemClassForSlt", map);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("setLineId", setLineId);
		map.put("salableFlag", salableFlag);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		return executeQueryForList("GatherOrder.getStyleMiddleClassForSlt", map);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("setLineId", setLineId);
		map.put("salableFlag", salableFlag);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		map.put("styleMiddleClassId", styleMiddleClassId);
		return executeQueryForList("GatherOrder.getStyleOrnaClassForSlt", map);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getStyleIdForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("setLineId", setLineId);
		map.put("salableFlag", salableFlag);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		map.put("styleMiddleClassId", styleMiddleClassId);
		map.put("styleOrnaClassId", styleOrnaClassId);
		return executeQueryForList("GatherOrder.getStyleIdForSlt", map);
	}
	/**
	 * 从配货记录表中获取手工减库产生的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public Pager getGatherOrderLinePageData(String headid, String setLineId, String salableFlag, Map<String, String> condition){
		if(StringUtil.isNotEmpty(headid)){
			condition.put("headid", headid);
		}else{
			condition.put("setLineId", setLineId);
			condition.put("salableFlag", salableFlag);
		}
		return executeQueryForPager("GatherOrder.getGatherOrderLinePageData", "GatherOrder.getGatherOrderLineTotalCount", condition);
	}
	public Pager getGatherOrderPageData(Map<String, String> condition){
		return executeQueryForPager("GatherOrder.getGatherOrderPageData",
				"GatherOrder.getGatherOrderTotalCount", condition);
	}
	public void updateGatherSetLineOrderNum(String lineid, String newOrderNum){
		Map<String, String> map = new HashMap<String, String>();
		map.put("lineid", lineid);
		map.put("newOrderNum", newOrderNum);
		executeUpdate("GatherOrder.updateGatherSetLineOrderNum", map);
	}
	public boolean checkSizeExists(String lineid, String newSizeId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("lineid", lineid);
		map.put("newSizeId", newSizeId);
		Integer count = (Integer) executeQueryForObject("GatherOrder.checkSizeExists", map);
		return count >0;
	}
	public void updateGatherSetLineSizeId(String lineid, String newSizeId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("lineid", lineid);
		map.put("newSizeId", newSizeId);
		executeUpdate("GatherOrder.updateGatherSetLineSizeId", map);
	}
	public GatherOrderLine getGatherOrderLine(String lineid){
		return (GatherOrderLine) executeQueryForObject("GatherOrder.getGatherOrderLine", lineid);
	}
	public GatherOrderHead getGatherOrderHead(String headid){
		return (GatherOrderHead) executeQueryForObject("GatherOrder.getGatherOrderHead", headid);
	}
	/**
	 * 根据推式总单id获取区域下的组织
	 * @param gatherOrderHeadId
	 * @return
	 */
	public List<Org> getOrgByGatherOrder(String gatherOrderHeadId){
		return executeQueryForList("GatherOrder.getOrgByGatherOrder", gatherOrderHeadId);
	}
	public boolean checkHasPushDispatch(String headid){
		Integer count = (Integer) executeQueryForObject("GatherOrder.checkHasPushDispatch", headid);
		return count > 0;
	}
	/**
	 * 强制关闭
	 * @param headid
	 * @param req
	 * @return
	 */
	public void closeGatherOrder(String headid, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("GatherOrder.closeGatherOrder", map);
	}
	/**
	 * 强制开启
	 * @param headid
	 * @param req
	 * @return
	 */
	public void reopenGatherOrder(String headid, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeUpdate("GatherOrder.reopenGatherOrder", map);
	}

    @Override
    public void createOrderLineByHeadid(String headid, String userId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("headid", headid);
        map.put("createId", userId);
        map.put("createDate", DateUtil.getCurrentDate18());
        this.executeInsert("GatherOrder.createOrderLineByHeadid", map);
    }
}
