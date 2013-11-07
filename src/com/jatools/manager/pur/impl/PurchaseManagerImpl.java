package com.jatools.manager.pur.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.dao.pur.PurchaseCustDao;
import com.jatools.dao.pur.PurchaseDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.pur.PurchaseManager;
import com.jatools.vo.pur.PurchaseCust;
import com.jatools.vo.pur.PurchaseEnter;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class PurchaseManagerImpl implements PurchaseManager {
	private PurchaseDao purchaseDao;
	private PurchaseCustDao purchaseCustDao;
	private CommonDao commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public void setPurchaseCustDao(PurchaseCustDao purchaseCustDao) {
		this.purchaseCustDao = purchaseCustDao;
	}

	/**
	 * 获取采购单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurchasePageData(Map<String, String> condition){
		return purchaseDao.getPurchasePageData(condition);
	}

	/**
	 * 获取采购单行分页数据
	 * @param headid
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseLinePageData(String headid, Map<String, String> condition){
			return purchaseDao.getPurchaseLinePageData(headid, condition);
	}

	/**
	 * 获取采购单头数据
	 * @param headid
	 * @return
	 */
	public PurchaseHead getPurchaseHead(String headid){
		return purchaseDao.getPurchaseHead(headid);
	}
	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid){
		return purchaseDao.getItemClassForSlt(headid);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId){
		return purchaseDao.getOrnaClassForSlt(headid, itemClassId);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId){
		return purchaseDao.getAnalysisForSlt(headid, itemClassId, ornaClassId);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange){
		return purchaseDao.getStyleItemClassForSlt(headid, itemClassId, ornaClassId, analysisArange);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId){
		return purchaseDao.getStyleMiddleClassForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId){
		return purchaseDao.getStyleOrnaClassForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId, styleMiddleClassId);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		return purchaseDao.getStyleIdForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
	/**
	 * 根据采购单行id，获取数据
	 * @param line
	 * @return
	 */
	public PurchaseLine getPurchaseLineById(String lineid){
		return purchaseDao.getPurchaseLineById(lineid);
	}

	/**
	 * 获取到货数量登记、不合格数量登记分页数据
	 * @param lineid
	 * @param enterType
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseLineEntryByTypePageData(String lineid, String enterType, Map<String, String> condition){
		return purchaseDao.getPurchaseLineEntryByTypePageData(lineid, enterType, condition);
	}
	/**
	 * 删除到货、不合格记录
	 * @param enterid
	 */
	public void deletePurchaseEnter(String enterid){
		purchaseDao.deletePurchaseEnter(enterid);
	}
	/**
	 * 保存到货、不合格记录
	 * @param enterid
	 * @return
	 */
	public void savePurchaseEnter(PurchaseEnter enter){
		purchaseDao.savePurchaseEnter(enter);
	}
	/**
	 * 撤回，修改采购数量
	 */
	public void markPurchaseBill(PurchaseHead purchaseHead, String[] lineidArr, String[] numCancelArr){
		//保存采购员
		purchaseDao.updatePurchaseEmpids(purchaseHead.getHeadid(), purchaseHead.getPurEmpids(), purchaseHead.getBizType(), purchaseHead.getMemo());
		if(null != lineidArr && lineidArr.length>0){
			purchaseDao.updatePurchaseLineNumOrder(lineidArr, numCancelArr);
		}
	}
	/**
	 * 根据定做单生成采购单
	 * @param custid
	 * @return
	 */
	public void createPurchaseBillFromCust(String custid, String jmFlag, List<PurchaseHead> purchaseHeadList, String userid){
		PurchaseCust purchaseCust = purchaseCustDao.getPurchaseCust(custid, jmFlag);
		for(PurchaseHead head : purchaseHeadList){
			head.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_CAIGOUDAN));
			if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
				head.setSrcBillCode(GlobalConstant.BILL_CODE_CUMTOMIZE_BILL);
			}else{
				head.setSrcBillCode(GlobalConstant.BILL_CODE_DINGZUODAN);
			}
			head.setSrcBillId(custid);
			head.setBillType(PurConstant.BILL_TYPE_DINGZUO);
//			head.setBizType(PurConstant.BIZ_TYPE_CG);//定做生成的采购单都是采购类型的,非委外
//			head.setDodate(DateUtil.getCurrentDate10());
			head.setOrgId(purchaseCust.getOrgId());
//			head.setVendorId(vendorId);
			head.setOrderCalenderId(null);
			//要求到货日期作为采购结束时间
			if(null != purchaseCust.getRequestDate()){
				head.setOrderEndDate(purchaseCust.getRequestDate().trim());
			}
//			head.setMemo(null);
			head.setCreateId(userid);
			head.setCreateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(userid);
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setStatus(DictConstant.BILL_STATUS_MARK);
			//保存采购单头表
			String headid = purchaseDao.savePurchaseHead(head);
			//保存采购单行表
			purchaseDao.savePurchaseLineFromCust(custid, jmFlag, headid, userid, head.getNumOrder(), head.getStyleId());
		}
		//更新定做单状态,已生成采购单
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			purchaseCustDao.updateJmPurchaseCustStatus(custid, DictConstant.BILL_STATUS_CREATE_PURCHASE, null, userid);
		}else{
			purchaseCustDao.updatePurchaseCustStatus(custid, DictConstant.PLAN_BILL_STATUS_PURCHASED, null, userid);
		}
	}
	/**
	 * 采购岗接收、拒绝定做单
	 * @param custid
	 * @param passFlag
	 * @param reviewMemo
	 * @param userid
	 */
	public void reviewCustBill(String custid, String jmFlag, boolean passFlag, String reviewMemo, String userid){
		//更新定做单状态
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			String status = passFlag?DictConstant.BILL_STATUS_PUR_REVIEWED:DictConstant.BILL_STATUS_SAVE;
			purchaseCustDao.updateJmPurchaseCustStatus(custid, status, reviewMemo, userid);
		}else{
			String status = passFlag?DictConstant.PLAN_BILL_STATUS_PUR_REVIEWED:DictConstant.PLAN_BILL_STATUS_SAVE;
			purchaseCustDao.updatePurchaseCustStatus(custid, status, reviewMemo, userid);
		}
	}
	/**
	 * 改变采购单头保留状态
	 * @param operId
	 * @param checked
	 */
	public void updatePurchaseHeadUsedFlag(String headid, boolean checked){
		purchaseDao.updatePurchaseHeadUsedFlag(headid, checked);
	}

	/**
	 * 改变采购单行保留状态
	 */
	public void updatePurchaseLineUsedFlag(boolean headFlag, String operId, boolean checked){
		purchaseDao.updatePurchaseLineUsedFlag(headFlag, operId, checked);
	}
	/**
	 * 获取采购单信息导出excel
	 * @param headid
	 * @return
	 */
	public List<Map> getPurchaseLineForExcel(String headid){
		return purchaseDao.getPurchaseLineForExcel(headid);
	}
}
