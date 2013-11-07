package com.jatools.dao.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.PurchaseEnter;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLine;
import com.jatools.vo.util.SelectorOption;

public interface PurchaseDao {
	/**
	 * 获取要生成采购单的供应商id列表
	 * @param gheadid
	 * @return
	 */
	public List<String> getVendorIdList(String gheadid);
	
	
	/**
	 * 获取采购单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurchasePageData(Map<String, String> condition);
	/**
	 * 获取采购单行分页数据
	 * @param headid
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseLinePageData(String headid, Map<String, String> condition);
	/**
	 * 获取采购单头数据
	 * @param headid
	 * @return
	 */
	public PurchaseHead getPurchaseHead(String headid);
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
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange);
	/**
	 * 获取款式中类
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId);
	/**
	 * 获取款式小类
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId);
	/**
	 * 获取款式
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId);
	/**
	 * 根据采购单行id，获取数据
	 * @param line
	 * @return
	 */
	public PurchaseLine getPurchaseLineById(String lineid);
	/**
	 * 获取到货数量登记、不合格数量登记分页数据
	 * @param lineid
	 * @param enterType
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseLineEntryByTypePageData(String lineid, String enterType, Map<String, String> condition);
	/**
	 * 删除到货、不合格记录
	 * @param enterid
	 */
	public void deletePurchaseEnter(String enterid);
	/**
	 * 保存到货、不合格记录
	 * @param enterid
	 * @return
	 */
	public void savePurchaseEnter(PurchaseEnter enter);
	/**
	 * 撤回，修改采购数量
	 */
	public void updatePurchaseLineNumOrder(String[] lineidArr, String[] numCancelArr);
	/**
	 * 保存采购员
	 * @param headid
	 * @param purEmpids
	 */
	public void updatePurchaseEmpids(String headid, String purEmpids, String bizType, String memo);
	/**
	 * 保存采购单头表
	 * @param purchaseHead
	 */
	public String savePurchaseHead(PurchaseHead purchaseHead);
	/**
	 * 修改采购单头表
	 * 手工分解生成采购单时，若存在供应商对应的采购单，则进行更新
	 * @param purchaseHead
	 */
	public void updatePurchaseHead(PurchaseHead purchaseHead);
	/**
	 * 保存采购单头表
	 * @param purchaseHeadList
	 */
	public void savePurchaseHeadList(List<PurchaseHead> purchaseHeadList);

	/**
	 * 根据采购总单行生成采购单行
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void savePurchaseLineFromGatherLine(List<Map<String, String>> purchaseLineList);
	/**
	 * 保存采购单行表
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void savePurchaseLineFromSub(String purGatherHeadId, String userid);

	/**
	 * 根据款式获取主供应商
	 * @param styleId
	 * @return
	 */
	public String getVendorIdByStyleId(String styleId);
	
	/**
	 * 根据定做单生成采购单行
	 * @param custid 定做单id
	 * @param headid 采购单头id
	 * @param userid
	 */
	public void savePurchaseLineFromCust(String custid, String jmFlag, String headid, String userid, String numOrder, String styleId);
	/**
	 * 改变采购单头保留状态
	 * @param operId
	 * @param checked
	 */
	public void updatePurchaseHeadUsedFlag(String headid, boolean checked);

	/**
	 * 改变采购单行保留状态
	 */
	public void updatePurchaseLineUsedFlag(boolean headFlag, String operId, boolean checked);
	/**
	 * 改总部状态为已到店
	 * @param gheadid
	 * @param userid
	 */
	public void updateHqOrnaStatus(String gheadid, String userid, String srcBillCode);
	/**
	 * 获取采购单信息导出excel
	 * @param headid
	 * @return
	 */
	public List<Map> getPurchaseLineForExcel(String headid);
}
