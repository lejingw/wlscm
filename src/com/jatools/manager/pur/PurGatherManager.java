package com.jatools.manager.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLine;
import com.jatools.vo.util.SelectorOption;

public interface PurGatherManager {

	/**
	 * 获取总部总单列表分页数据
	 */
	public Pager getPurGatherPageData(Map<String, String> condition);

	/**
	 * 根据总部总单id获取单据头对象
	 * @param headid
	 * @return
	 */
	public PurGatherHead getPurGatherHead(String headid);
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

	/**
	 * 获取总部总单行列表分页数据
	 */
	public Pager getPurGatherLinePageData(String headid, Map<String, String> condition);
	/**
	 * 保存采购单（手工分解）
	 * @param purchaseHead
	 * @param purchaseLineList
	 */
	public void savePurchase(PurchaseHead purchaseHead, List<String> purGatherLineIdList, List<String> numOrderList, String userid);

	/**
	 * 检查采购总单行，是否存在未生成采购单的记录
	 * @param purGatherHeadId
	 * @return true  存在
	 */
	public boolean checkPurGatherLineAvail(String purGatherHeadId);

	/**
	 * 获取自动生成采购单时的，供应商
	 */
	public List<String> getVendorIdAuto(String purGatherHeadId);
	/**
	 * 自动生成采购单
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void createPurchaseAuto(String purGatherHeadId, String userid);
	/**
	 * 检查总单是否可以撤销
	 * @param purGatherHeadId
	 */
	public void checkCancelPurGatherAvail(String purGatherHeadId, String userid);
	/**
	 * 撤销采购总单
	 * @param purGatherHeadId
	 */
	public void cancelPurGather(String purGatherHeadId, String userid);
	/**
	 * 根据采购总单id和供应商id，查看是否已经存在采购单，一个供应商只能有一张采购单
	 * @param purGatherHeadId
	 * @param vendorId
	 * @return
	 */
	public PurchaseHead getPurchaseHeadByVendorId(String purGatherHeadId, String vendorId);
}
