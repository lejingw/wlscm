package com.jatools.dao.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.util.SelectorOption;

public interface PurGatherDao {
	/**
	 * 获取减库生成的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData(String gheadid);
	/**
	 * 获取不减库时生成的采购总单行数据
	 */
	public List<PurGatherLine> getUnsubPurGatherLineData(String gheadid);
	/**
	 * 根据总部总单id获取生成采购总单的数据
	 * @param gheadid
	 * @return
	 */
	public PurGatherHead getPurGatherHeadByGatherHeadId(String gheadid);
	/**
	 * 保存采购总单头数据
	 * @param purGatherHead
	 * @return
	 */
	public String savePurGatherHead(PurGatherHead purGatherHead);
	/**
	 * 保存采购总单行数据
	 * @param list
	 */
	public void savePurGatherLine(List<PurGatherLine> list);
	
	
	/**
	 * 获取总部总单列表分页数据
	 */
	Pager getPurGatherPageData(Map<String, String> condition);
	/**
	 * 根据总部总单id获取单据头对象
	 * @param headid
	 * @return
	 */
	PurGatherHead getPurGatherHead(String headid);

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
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId);
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId);
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId);
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId);
	/**
	 * 获取总部总单行列表分页数据
	 */
	public Pager getPurGatherLinePageData(String headid, Map<String, String> condition);
	/**
	 * 从配货记录表中获取手工减库产生的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData2(String gheadid);
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
	 * 检查采购总单生成的采购单是否已经存在此供应商
	 * @param purGatherHeadId
	 * @param vendorId
	 * @return
	 */
	public String checkPurchaseHeadExistsAuto(String purGatherHeadId, String vendorId);
	/**
	 * 保存自动生成的采购单头
	 * @param purGatherHeadId
	 * @param vendorId
	 * @param userid
	 * @return
	 */
	public String savePurchaseHeadAuto(String purGatherHeadId, String vendorId, String billno, String userid);
	/**
	 * 保存自动生成的采购单行
	 * @param purGatherHeadId
	 * @param vendorId
	 * @param purchaseHeadId
	 * @param userid
	 */
	public void savePurchaseLineAuto(String purGatherHeadId, String vendorId, String purchaseHeadId, String userid);
	/**
	 * 修改自动生成的采购单行的采购数量
	 * @param purGatherHeadId
	 * @param vendorId
	 * @param purchaseHeadId
	 * @param userid
	 */
	public void updatePurchaseLineAuto(String purGatherHeadId, String vendorId, String purchaseHeadId, String userid);
	/**
	 * 撤销时，获取有手工分解生成采购单数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseManualCount(String purGatherHeadId);
	/**
	 * 撤销时，获取有撤销数量的记录数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseCancelNumCount(String purGatherHeadId);
	/**
	 * 撤销时，获取有到货记录的数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseEnterCount(String purGatherHeadId);
	/**
	 * 撤销时，获取有不是记账状态、有保留的数量
	 * @param purGatherHeadId
	 * @return
	 */
	public int getPurchaseStatusCount(String purGatherHeadId);
	/**
	 * 撤销总单时，删除采购单
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void deletePurchaseBillByGatherHeadId(String purGatherHeadId, String userid);
	/**
	 * 撤销总单时，删除采购总单
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void deletePurGatherBill(String purGatherHeadId, String userid);
	/**
	 * 根据采购总单id和供应商id，查看是否已经存在采购单，一个供应商只能有一张采购单
	 * @param purGatherHeadId
	 * @param vendorId
	 * @return
	 */
	public PurchaseHead getPurchaseHeadByVendorId(String purGatherHeadId, String vendorId);
}
