package com.jatools.manager.pur.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.dao.pur.PurGatherDao;
import com.jatools.dao.pur.PurchaseDao;
import com.jatools.dao.push.PushDispatchDao;
import com.jatools.dao.stock.DispatchOrnaDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.pur.PurGatherManager;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PurGatherManagerImpl implements PurGatherManager {
	private PushDispatchDao pushDispatchDao;
	private PurGatherDao purGatherDao;
	private CommonDao commonDao;
	private PurchaseDao purchaseDao;
	private DispatchOrnaDao dispatchOrnaDao;
	
	public void setDispatchOrnaDao(DispatchOrnaDao dispatchOrnaDao) {
		this.dispatchOrnaDao = dispatchOrnaDao;
	}

	public void setPushDispatchDao(PushDispatchDao pushDispatchDao) {
		this.pushDispatchDao = pushDispatchDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setPurGatherDao(PurGatherDao purGatherDao) {
		this.purGatherDao = purGatherDao;
	}

	/**
	 * 获取总部总单列表分页数据
	 */
	public Pager getPurGatherPageData(Map<String, String> condition) {
		return purGatherDao.getPurGatherPageData(condition);
	}
	

	/**
	 * 根据总部总单id获取单据头对象
	 * @param headid
	 * @return
	 */
	public PurGatherHead getPurGatherHead(String headid){
		return purGatherDao.getPurGatherHead(headid);
	}
	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid){
		return purGatherDao.getItemClassForSlt(headid);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId){
		return purGatherDao.getOrnaClassForSlt(headid, itemClassId);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId){
		return purGatherDao.getAnalysisForSlt(headid, itemClassId, ornaClassId);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange){
		return purGatherDao.getStyleItemClassForSlt(headid, itemClassId, ornaClassId, analysisArange);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId){
		return purGatherDao.getStyleMiddleClassForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId){
		return purGatherDao.getStyleOrnaClassForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId, styleMiddleClassId);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		return purGatherDao.getStyleIdForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}

	/**
	 * 获取总部总单行列表分页数据
	 */
	public Pager getPurGatherLinePageData(String headid, Map<String, String> condition){
		return purGatherDao.getPurGatherLinePageData(headid, condition);
	}
	/**
	 * 保存采购单（手工分解）
	 * @param purchaseHead
	 * @param purchaseLineList
	 */
	public void savePurchase(PurchaseHead purchaseHead, List<String> purGatherLineIdList, List<String> numOrderList, String userid){
		String headid = purchaseHead.getHeadid();
		if(StringUtil.isNotEmpty(headid)){
			//手工分解生成采购单时，若存在供应商对应的采购单，则进行更新
			purchaseDao.updatePurchaseHead(purchaseHead);		
		}else{
			purchaseHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_CAIGOUDAN));
			headid = purchaseDao.savePurchaseHead(purchaseHead);
		}
		List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
		for(int i=0;i<purGatherLineIdList.size();i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put("headid", headid);
			map.put("numOrder", numOrderList.get(i));
			map.put("createDate", DateUtil.getCurrentDate18());
			map.put("createId", userid);
			map.put("updateDate", DateUtil.getCurrentDate18());
			map.put("updateId", userid);
			map.put("gatherLineId", purGatherLineIdList.get(i));
			paramList.add(map);
		}
		purchaseDao.savePurchaseLineFromGatherLine(paramList);
	}
	/**
	 * 检查采购总单行，是否存在未生成采购单的记录
	 * @param purGatherHeadId
	 * @return true  存在
	 */
	public boolean checkPurGatherLineAvail(String purGatherHeadId){
		return purGatherDao.checkPurGatherLineAvail(purGatherHeadId);
	}
	/**
	 * 获取自动生成采购单时的，供应商
	 */
	public List<String> getVendorIdAuto(String purGatherHeadId){
		return purGatherDao.getVendorIdAuto(purGatherHeadId);
	}
	/**
	 * 自动生成采购单
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void createPurchaseAuto(String purGatherHeadId, String userid){
		List<String> vendorIdList = purGatherDao.getVendorIdAuto(purGatherHeadId);
		for(String vendorId : vendorIdList){
			//检查采购总单生成的采购单是否已经存在此供应商
			String purchaseHeadId = purGatherDao.checkPurchaseHeadExistsAuto(purGatherHeadId, vendorId);
			if(StringUtil.isEmpty(purchaseHeadId)){
				//保存自动生成的采购单头
				purchaseHeadId = purGatherDao.savePurchaseHeadAuto(purGatherHeadId, vendorId, commonDao.getBillno(GlobalConstant.BILL_CODE_CAIGOUDAN), userid);				
			}
			//保存自动生成的采购单行
			purGatherDao.savePurchaseLineAuto(purGatherHeadId, vendorId, purchaseHeadId, userid);
			//修改自动生成的采购单行的采购数量
			purGatherDao.updatePurchaseLineAuto(purGatherHeadId, vendorId, purchaseHeadId, userid);
		}
	}
	/**
	 * 检查总单是否可以撤销
	 * @param purGatherHeadId
	 */
	public void checkCancelPurGatherAvail(String purGatherHeadId, String userid){
		//有手工分解生成采购单的不能撤销
		int count = purGatherDao.getPurchaseManualCount(purGatherHeadId);
		if(count>0){
			throw new RuntimeException("不能撤销，存在手工分解生成的采购单");
		}
		//有撤销数量的
		count = purGatherDao.getPurchaseCancelNumCount(purGatherHeadId);
		if(count>0){
			throw new RuntimeException("不能撤销，存在被修改采购数量的采购单");
		}
		//有到货记录的不能生成
		count = purGatherDao.getPurchaseEnterCount(purGatherHeadId);
		if(count>0){
			throw new RuntimeException("不能撤销，采购单存在到货、不合格记录");
		}
		//有不是记账状态、有保留的不能撤销
		count = purGatherDao.getPurchaseStatusCount(purGatherHeadId);
		if(count >0){
			throw new RuntimeException("不能撤销，存在不为记账状态或者被保留的记录");
		}
	}
	/**
	 * 撤销采购总单
	 * @param purGatherHeadId
	 */
	public void cancelPurGather(String purGatherHeadId, String userid){
		PurGatherHead gatherHead = purGatherDao.getPurGatherHead(purGatherHeadId);
		//删除减库临时数据
		dispatchOrnaDao.deleteDispatchSubTempData(gatherHead.getGatherHeadId());
		//删除采购单
		purGatherDao.deletePurchaseBillByGatherHeadId(purGatherHeadId, userid);
		//删除采购总单
		purGatherDao.deletePurGatherBill(purGatherHeadId, userid);
		try {
			if(GlobalConstant.BILL_CODE_PUSH_GATHER_BILL.equals(gatherHead.getSrcBillCode())){
				pushDispatchDao.updateHqGatherHeadStatus(gatherHead.getGatherHeadId(), DictConstant.BILL_STATUS_CREATE_GATHER_ORDER, null, userid);
			}else if(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA.equals(gatherHead.getSrcBillCode())){
				//修改总部总单状态
				dispatchOrnaDao.updateHqGatherHeadStatus(gatherHead.getGatherHeadId(), DictConstant.PLAN_BILL_STATUS_GATHERED, null, userid);
			}
		} catch (Exception e) {
			throw new RuntimeException("调用计划接口[改要货汇总单状态失败]失败");
		}
	}
	/**
	 * 根据采购总单id和供应商id，查看是否已经存在采购单，一个供应商只能有一张采购单
	 * @param purGatherHeadId
	 * @param vendorId
	 * @return
	 */
	public PurchaseHead getPurchaseHeadByVendorId(String purGatherHeadId, String vendorId){
		return purGatherDao.getPurchaseHeadByVendorId(purGatherHeadId, vendorId);
	}
}
