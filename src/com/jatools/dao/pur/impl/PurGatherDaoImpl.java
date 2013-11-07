package com.jatools.dao.pur.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.PurGatherDao;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.util.DateUtil;

public class PurGatherDaoImpl extends BaseDao implements PurGatherDao{
	/**
	 * 获取减库生成的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData(String gheadid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		return executeQueryForList("PurGather.getSubPurGatherLineData", map);
	}
	/**
	 * 获取不减库时生成的采购总单行数据
	 */
	public List<PurGatherLine> getUnsubPurGatherLineData(String gheadid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		return executeQueryForList("PurGather.getUnsubPurGatherLineData", map);
	}
	/**
	 * 根据总部总单id获取生成采购总单的数据
	 * @param gheadid
	 * @return
	 */
	public PurGatherHead getPurGatherHeadByGatherHeadId(String gheadid){
		return (PurGatherHead) executeQueryForObject("PurGather.getPurGatherHeadByGatherHeadId", gheadid);
	}

	/**
	 * 保存采购总单头数据
	 * @param purGatherHead
	 * @return
	 */
	public String savePurGatherHead(PurGatherHead purGatherHead){
		return (String) executeInsert("PurGather.savePurGatherHead", purGatherHead);
	}

	/**
	 * 保存采购总单行数据
	 * @param list
	 */
	public void savePurGatherLine(List<PurGatherLine> list){
		executeBatchInsert("PurGather.savePurGatherLine", list);
	}
	
	
	/**
	 * 获取总部总单列表分页数据
	 */
	public Pager getPurGatherPageData(Map<String, String> condition){
		return executeQueryForPager("PurGather.getPurGatherPageData", "PurGather.getPurGatherTotalCount", condition);
	}
	/**
	 * 根据总部总单id获取单据头对象
	 * @param headid
	 * @return
	 */
	public PurGatherHead getPurGatherHead(String headid){
		return (PurGatherHead) executeQueryForObject("PurGather.getPurGatherHead", headid);
	}
	
	/**
	 * 获取总部总单行列表分页数据
	 */
	public Pager getPurGatherLinePageData(String headid, Map<String, String> condition){
		condition.put("headid", headid);
		return executeQueryForPager("PurGather.getPurGatherLinePageData", "PurGather.getPurGatherLineTotalCount", condition);
	}

	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectorOption> getItemClassForSlt(String headid){
		return executeQueryForList("PurGather.getItemClassForSlt", headid);
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
		return executeQueryForList("PurGather.getOrnaClassForSlt", map);
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
		return executeQueryForList("PurGather.getAnalysisForSlt", map);
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
		return executeQueryForList("PurGather.getStyleItemClassForSlt", map);
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
		return executeQueryForList("PurGather.getStyleMiddleClassForSlt", map);
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
		return executeQueryForList("PurGather.getStyleOrnaClassForSlt", map);
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
		return executeQueryForList("PurGather.getStyleIdForSlt", map);
	}
	/**
	 * 从配货记录表中获取手工减库产生的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData2(String gheadid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		return executeQueryForList("PurGather.getSubPurGatherLineData2", map);
	}

	/**
	 * 检查采购总单行，是否存在未生成采购单的记录
	 * @param purGatherHeadId
	 * @return true  存在
	 */
	public boolean checkPurGatherLineAvail(String purGatherHeadId){
		Long count = (Long) executeQueryForObject("PurGather.checkPurGatherLineAvail", purGatherHeadId);
		if(count >0)
			return true;
		return false;
	}
	/**
	 * 获取自动生成采购单时的，供应商
	 */
	public List<String> getVendorIdAuto(String purGatherHeadId){
		return executeQueryForList("PurGather.getVendorIdAuto", purGatherHeadId);
	}
	/**
	 * 检查采购总单生成的采购单是否已经存在此供应商
	 * @param purGatherHeadId
	 * @param vendorId
	 * @return
	 */
	public String checkPurchaseHeadExistsAuto(String purGatherHeadId, String vendorId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("purGatherHeadId", purGatherHeadId);
		map.put("vendorId", vendorId);
		return (String)executeQueryForObject("PurGather.checkPurchaseHeadExistsAuto", map);
	}
	/**
	 * 保存自动生成的采购单头
	 * @param purGatherHeadId
	 * @param vendorId
	 * @param userid
	 * @return
	 */
	public String savePurchaseHeadAuto(String purGatherHeadId, String vendorId, String billno,String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("purGatherHeadId", purGatherHeadId);
		map.put("vendorId", vendorId);
		map.put("billno", billno);
		map.put("dodate", DateUtil.getCurrentDate10());
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		return (String) executeInsert("PurGather.savePurchaseHeadAuto", map);
	}
	/**
	 * 保存自动生成的采购单行
	 * @param purGatherHeadId
	 * @param vendorId
	 * @param purchaseHeadId
	 * @param userid
	 */
	public void savePurchaseLineAuto(String purGatherHeadId, String vendorId, String purchaseHeadId, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("purGatherHeadId", purGatherHeadId);
		map.put("vendorId", vendorId);
		map.put("purchaseHeadId", purchaseHeadId);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("PurGather.savePurchaseLineAuto", map);
	}
	/**
	 * 修改自动生成的采购单行的采购数量
	 * @param purGatherHeadId
	 * @param vendorId
	 * @param purchaseHeadId
	 * @param userid
	 */
	public void updatePurchaseLineAuto(String purGatherHeadId, String vendorId, String purchaseHeadId, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("purGatherHeadId", purGatherHeadId);
		map.put("vendorId", vendorId);
		map.put("purchaseHeadId", purchaseHeadId);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("PurGather.updatePurchaseLineAuto", map);
	}
	/**
	 * 撤销时，获取有手工分解生成采购单数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseManualCount(String purGatherHeadId){
		return (Integer)executeQueryForObject("PurGather.getPurchaseManualCount", purGatherHeadId);
	}
	/**
	 * 撤销时，获取有撤销数量的记录数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseCancelNumCount(String purGatherHeadId){
		return (Integer)executeQueryForObject("PurGather.getPurchaseCancelNumCount", purGatherHeadId);
	}
	/**
	 * 撤销时，获取有到货记录的数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseEnterCount(String purGatherHeadId){
		return (Integer)executeQueryForObject("PurGather.getPurchaseEnterCount", purGatherHeadId);
	}
	/**
	 * 撤销时，获取有不是记账状态、有保留的数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseStatusCount(String purGatherHeadId){
		return (Integer)executeQueryForObject("PurGather.getPurchaseStatusCount", purGatherHeadId);
	}
	/**
	 * 撤销总单时，删除采购单
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void deletePurchaseBillByGatherHeadId(String purGatherHeadId, String userid){
		delete("PurGather.deletePurchaseHeadByGatherHeadId", purGatherHeadId);
		delete("PurGather.deletePurchaseLineByGatherHeadId", purGatherHeadId);
	}
	/**
	 * 撤销总单时，删除采购总单
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void deletePurGatherBill(String purGatherHeadId, String userid){
		delete("PurGather.deletePurGatherHead", purGatherHeadId);
		delete("PurGather.deletePurGatherLine", purGatherHeadId);
	}
	/**
	 * 根据采购总单id和供应商id，查看是否已经存在采购单，一个供应商只能有一张采购单
	 * @param purGatherHeadId
	 * @param vendorId
	 * @return
	 */
	public PurchaseHead getPurchaseHeadByVendorId(String purGatherHeadId, String vendorId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("purGatherHeadId", purGatherHeadId);
		condition.put("vendorId", vendorId);
		return (PurchaseHead) executeQueryForObject("PurGather.getPurchaseHeadByVendorId", condition);
	}
}
