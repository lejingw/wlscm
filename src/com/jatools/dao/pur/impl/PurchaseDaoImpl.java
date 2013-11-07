package com.jatools.dao.pur.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.PurchaseDao;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.pur.PurchaseEnter;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class PurchaseDaoImpl extends BaseDao implements PurchaseDao{
	/**
	 * 获取要生成采购单的供应商id列表
	 * @param gheadid
	 * @return
	 */
	public List<String> getVendorIdList(String gheadid){
		return executeQueryForList("Purchase.getVendorIdList", gheadid);
	}
	/**
	 * 保存采购单头表
	 * @param purchaseHead
	 */
	public String savePurchaseHead(PurchaseHead purchaseHead){
		return (String)executeInsert("Purchase.savePurchaseHead", purchaseHead);
	}
	/**
	 * 修改采购单头表
	 * 手工分解生成采购单时，若存在供应商对应的采购单，则进行更新
	 * @param purchaseHead
	 */
	public void updatePurchaseHead(PurchaseHead purchaseHead){
		executeUpdate("Purchase.updatePurchaseHead", purchaseHead);
	}
	/**
	 * 保存采购单头表
	 * @param purchaseHeadList
	 */
	public void savePurchaseHeadList(List<PurchaseHead> purchaseHeadList){
		executeBatchInsert("Purchase.savePurchaseHead", purchaseHeadList);
	}

	/**
	 * 根据采购总单行生成采购单行
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void savePurchaseLineFromGatherLine(List<Map<String, String>> purchaseLineList){
		executeBatchInsert("Purchase.savePurchaseLine", purchaseLineList);
	}
	/**
	 * 减库或不减库生成采购单行
	 */
	public void savePurchaseLineFromSub(String purGatherHeadId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("purGatherHeadId", purGatherHeadId);
		condition.put("createId", userid);
		condition.put("updateId", userid);
		condition.put("createDate", DateUtil.getCurrentDate18());
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("Purchase.savePurchaseLineFromSub", condition);
	}
	
	
	/**
	 * 获取采购单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurchasePageData(Map<String, String> condition){
		return executeQueryForPager("Purchase.getPurchasePageData", "Purchase.getPurchaseTotalCount", condition);
	}

	/**
	 * 获取采购单行分页数据
	 * @param headid
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseLinePageData(String headid, Map<String, String> condition){
		condition.put("headid", headid);
		return executeQueryForPager("Purchase.getPurchaseLinePageData", "Purchase.getPurchaseLineTotalCount", condition);
	}
	
	/**
	 * 获取采购单头数据
	 * @param headid
	 * @return
	 */
	public PurchaseHead getPurchaseHead(String headid){
		return (PurchaseHead) executeQueryForObject("Purchase.getPurchaseHead", headid);
	}
	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid){
		return executeQueryForList("Purchase.getItemClassForSlt", headid);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		return executeQueryForList("Purchase.getOrnaClassForSlt", map);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		return executeQueryForList("Purchase.getAnalysisForSlt", map);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		return executeQueryForList("Purchase.getStyleItemClassForSlt", map);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		return executeQueryForList("Purchase.getStyleMiddleClassForSlt", map);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		map.put("styleMiddleClassId", styleMiddleClassId);
		return executeQueryForList("Purchase.getStyleOrnaClassForSlt", map);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleItemClassId", styleItemClassId);
		map.put("styleMiddleClassId", styleMiddleClassId);
		map.put("styleOrnaClassId", styleOrnaClassId);
		return executeQueryForList("Purchase.getStyleIdForSlt", map);
	}
	/**
	 * 根据采购单行id，获取数据
	 * @param line
	 * @return
	 */
	public PurchaseLine getPurchaseLineById(String lineid){
		return (PurchaseLine) executeQueryForObject("Purchase.getPurchaseLineById", lineid);
	}
	/**
	 * 获取到货数量登记、不合格数量登记分页数据
	 * @param lineid
	 * @param enterType
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseLineEntryByTypePageData(String lineid, String enterType, Map<String, String> condition){
		condition.put("lineid", lineid);
		condition.put("enterType", enterType);
		return executeQueryForPager("Purchase.getPurchaseLineEntryByTypePageData", "Purchase.getPurchaseLineEntryByTypeTotalCount", condition);
	}
	/**
	 * 删除到货、不合格记录
	 * @param enterid
	 */
	public void deletePurchaseEnter(String enterid){
		int cnt = delete("Purchase.deletePurchaseEnter", enterid);
		if(cnt <1)
			throw new RuntimeException("删除失败");
	}
	/**
	 * 保存到货、不合格记录
	 * @param enterid
	 * @return
	 */
	public void savePurchaseEnter(PurchaseEnter enter){
		executeInsert("Purchase.savePurchaseEnter", enter);
	}
	/**
	 * 撤回，修改采购数量
	 */
	public void updatePurchaseLineNumOrder(String[] lineidArr, String[] numCancelArr){
		List<Map<String, String>> conditionList = new ArrayList<Map<String,String>>();
		for(int i=0;i<lineidArr.length;i++){
			Map<String, String> condition = new HashMap<String, String>();
			condition.put("numCancel", numCancelArr[i]);
			condition.put("lineid", lineidArr[i]);
			conditionList.add(condition);
		}
		executeBatchUpdate("Purchase.updatePurchaseLineNumOrder", conditionList);
	}
	/**
	 * 保存采购员
	 * @param headid
	 * @param purEmpids
	 */
	public void updatePurchaseEmpids(String headid, String purEmpids, String bizType, String memo){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("purEmpids", purEmpids);
		condition.put("bizType", bizType);
		condition.put("memo", memo);
		executeUpdate("Purchase.updatePurchaseEmpids", condition);
	}
	/**
	 * 根据款式获取主供应商
	 * @param styleId
	 * @return
	 */
	public String getVendorIdByStyleId(String styleId){
		return (String) executeQueryForObject("Purchase.getVendorIdByStyleId", styleId);
	}
	/**
	 * 根据定做单生成采购单行
	 * @param headid
	 * @param userid
	 */
	public void savePurchaseLineFromCust(String custid, String jmFlag, String headid, String userid, String numOrder, String styleId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("custid", custid);
		condition.put("jmFlag", jmFlag);
		condition.put("headid", headid);
		condition.put("numOrder", numOrder);
		condition.put("styleId", styleId);
		condition.put("createId", userid);
		condition.put("createDate", DateUtil.getCurrentDate18());
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("Purchase.savePurchaseLineFromCust", condition);
	}

	/**
	 * 改变采购单头保留状态
	 * @param operId
	 * @param checked
	 */
	public void updatePurchaseHeadUsedFlag(String headid, boolean checked){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("checked", checked?"1":"0");
		executeUpdate("Purchase.updatePurchaseHeadUsedFlag", condition);
	}

	/**
	 * 改变采购单行保留状态
	 */
	public void updatePurchaseLineUsedFlag(boolean headFlag, String operId, boolean checked){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headFlag", headFlag?"1":"0");
		condition.put("operId", operId);
		condition.put("checked", checked?"1":"0");
		executeUpdate("Purchase.updatePurchaseLineUsedFlag", condition);
	}
	/**
	 * 改总部状态为已到店
	 * @param gheadid
	 * @param userid
	 */
	public void updateHqOrnaStatus(String gheadid, String userid, String srcBillCode){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		condition.put("srcBillCode", srcBillCode);
		executeUpdate("DispatchOrna.updateHqOrnaStatus", condition);
	}
	/**
	 * 获取采购单信息导出excel
	 * @param headid
	 * @return
	 */
	public List<Map> getPurchaseLineForExcel(String headid){
		return (List<Map>)executeQueryForList("Purchase.getPurchaseLineForExcel", headid);
	}
}