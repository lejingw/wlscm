package com.jatools.manager.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.pur.PurchaseEnter;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLine;
import com.jatools.vo.util.SelectorOption;

public interface PurchaseManager {

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
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange);
	/**
	 * 获取款式中类
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId);
	/**
	 * 获取款式中小类
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId);
	/**
	 * 获取款式
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
	 * @purchaseHead 包含purEmpids采购员、bizType业务类型、memo说明
	 */
	public void markPurchaseBill(PurchaseHead purchaseHead, String[] lineidArr, String[] numCancelArr);
	/**
	 * 根据定做单生成采购单
	 * @param custid
	 * @return
	 */
	public void createPurchaseBillFromCust(String custid, String jmFlag, List<PurchaseHead> purchaseHeadList, String userid);
	/**
	 * 采购岗接收、拒绝定做单
	 * @param custid
	 * @param passFlag
	 * @param reviewMemo
	 * @param userid
	 */
	public void reviewCustBill(String custid, String jmFlag, boolean passFlag, String reviewMemo, String userid);
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
	 * 获取采购单信息导出excel
	 * @param headid
	 * @return
	 */
	public List<Map> getPurchaseLineForExcel(String headid);
}
