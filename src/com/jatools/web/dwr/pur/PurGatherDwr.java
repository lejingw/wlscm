package com.jatools.web.dwr.pur;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.manager.pur.PurGatherManager;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLineSession;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PurGatherDwr {
	private static Logger logger = Logger.getLogger(PurGatherDwr.class);
	private PurGatherManager purGatherManager;

	public void setPurGatherManager(PurGatherManager purGatherManager) {
		this.purGatherManager = purGatherManager;
	}
	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid){
		if(StringUtil.isEmpty(headid)){
			return null;
		}
		return purGatherManager.getItemClassForSlt(headid);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId)){
			return null;
		}
		return purGatherManager.getOrnaClassForSlt(headid, itemClassId);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId)){
			return null;
		}
		return purGatherManager.getAnalysisForSlt(headid, itemClassId, ornaClassId);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/){
			return null;
		}
		return purGatherManager.getStyleItemClassForSlt(headid, itemClassId, ornaClassId, analysisId);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/ || StringUtil.isEmpty(styleItemClassId)){
			return null;
		}
		return purGatherManager.getStyleMiddleClassForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/ || StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId)){
			return null;
		}
		return purGatherManager.getStyleOrnaClassForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/ || StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId) || StringUtil.isEmpty(styleOrnaClassId)){
			return null;
		}
		return purGatherManager.getStyleIdForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
	
	/**
	 * 保存采购单（手工分解）
	 * @param purchaseHead
	 * @param purGatherHeadId
	 * @return
	 */
	public String savePurchase(String purGatherHeadId, PurchaseHead purchaseHead, HttpSession session){
		Object obj = session.getAttribute(PurConstant.NUM_CURRENT_DISPATCH_SESSION_KEY);
		if(null == obj || !(obj instanceof List)){
			return "不能获取采购单分解数量";
		}
		List<PurchaseLineSession> list = (List<PurchaseLineSession>) obj;
		List<String> purGatherLineIdList = new ArrayList<String>();
		List<String> numOrderList = new ArrayList<String>();
		for(PurchaseLineSession pls : list){
			if(Integer.parseInt(pls.getNumCurrentDispatch())>0){
				purGatherLineIdList.add(pls.getGlineid());
				numOrderList.add(pls.getNumCurrentDispatch());
			}
		}
		if(numOrderList.size()<1){
			return "不能获取采购单分解数量";
		}
		try {
			String userid = CommonUtil.getSessionUserId(session);
			PurchaseHead purchaseHead2 = setPurchaseHeadData(purGatherHeadId, purchaseHead, userid);
			purGatherManager.savePurchase(purchaseHead2, purGatherLineIdList, numOrderList, userid);
			session.removeAttribute(PurConstant.NUM_CURRENT_DISPATCH_SESSION_KEY);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	private PurchaseHead setPurchaseHeadData(String purGatherHeadId, PurchaseHead purchaseHead, String userid) {
		//根据采购总单id和供应商id，查看是否已经存在采购单，一个供应商只能有一张采购单
		PurchaseHead existsHead = purGatherManager.getPurchaseHeadByVendorId(purGatherHeadId, purchaseHead.getVendorId());
		if(null == existsHead){
			PurGatherHead purGatherHead = purGatherManager.getPurGatherHead(purGatherHeadId);
			purchaseHead.setSrcBillCode(GlobalConstant.BILL_CODE_CAIGOUZONGDAN);
			purchaseHead.setSrcBillId(purGatherHeadId);
			purchaseHead.setSrcBillNo(purGatherHead.getBillno());
			purchaseHead.setBillType(PurConstant.ORDER_KIND_FEIMINGXI.equals(purGatherHead.getOrderKind())?PurConstant.BILL_TYPE_FEIMINGXI:PurConstant.BILL_TYPE_MINGXI);
			purchaseHead.setOrgId(purGatherHead.getOrgId());
			purchaseHead.setOrderCalenderId(purGatherHead.getOrderCalenderId());
			purchaseHead.setOrderEndDate(purGatherHead.getOrderEndDate());
			purchaseHead.setCreateId(userid);
			purchaseHead.setCreateDate(DateUtil.getCurrentDate18());
			existsHead = purchaseHead;
		}else{
			existsHead.setDodate(purchaseHead.getDodate());
			existsHead.setPurEmpids(purchaseHead.getPurEmpids());
			existsHead.setBizType(purchaseHead.getBizType());
			existsHead.setMemo(purchaseHead.getMemo());
		}
		existsHead.setUpdateId(userid);
		existsHead.setUpdateDate(DateUtil.getCurrentDate18());
		existsHead.setManualFlag(PurConstant.PURCHASE_HEAD_MANUAL_YES);//手工生成
		existsHead.setStatus(DictConstant.BILL_STATUS_MARK);
		return existsHead;
	}
	/**
	 * 判断登录用户是否为采购员
	 * @param session
	 * @return
	 */
	public boolean isSessionUserPurEmp(HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		List<Dict> purchaseEmpList = DictCache.getInstance().getDicts("purchaseEmp");
		for(Dict dict : purchaseEmpList){
			if(dict.getItemKey().equals(userid)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 将当前分解数量放入session
	 * @param glineid
	 * @param numDispatch
	 * @param session
	 */
	public void addNumDispatchToSession(String glineid, String numDispatch, HttpSession session){
		Object obj = session.getAttribute(PurConstant.NUM_CURRENT_DISPATCH_SESSION_KEY);
		List<PurchaseLineSession> list = null;
		if(null != obj && obj instanceof List){
			list = (List<PurchaseLineSession>) obj;
			boolean flag = true;
			for(PurchaseLineSession pls : list){
				if(pls.getGlineid().equals(glineid)){
					pls.setNumCurrentDispatch(numDispatch);
					flag = false;
					break;
				}
			}
			if(flag){
				list.add(new PurchaseLineSession(glineid, numDispatch));
			}
		}else{
			list = new ArrayList<PurchaseLineSession>();
			list.add(new PurchaseLineSession(glineid, numDispatch));
		}
		session.setAttribute(PurConstant.NUM_CURRENT_DISPATCH_SESSION_KEY, list);
	}
	/**
	 * 自动生成采购单
	 * @param purGatherHeadId
	 * @return
	 */
	public String createPurchaseAuto(String purGatherHeadId, HttpSession session){
		//检查采购总单行，是否存在未生成采购单的记录
		boolean flag = purGatherManager.checkPurGatherLineAvail(purGatherHeadId);
		if(!flag){
			return "采购总单，不存在未生成采购单的记录";
		}
		//获取自动生成采购单时的，供应商
		List<String> vendorIdList = purGatherManager.getVendorIdAuto(purGatherHeadId);
		//检查未生成采购单的记录对应的款式是否存在默认供应商
		if(vendorIdList.size()<1){
			return "采购总单，未生成采购单的记录对应的款式没有默认供应商存在";
		}
		purGatherManager.createPurchaseAuto(purGatherHeadId, CommonUtil.getSessionUserId(session));
		return null;
	}
	
	/**
	 * 撤销采购总单
	 * @param purGatherHeadId
	 * @param session
	 * @return
	 */
	public String cancelPurGather(String purGatherHeadId, HttpSession session){
		try {
			String userid = CommonUtil.getSessionUserId(session);
			purGatherManager.checkCancelPurGatherAvail(purGatherHeadId, userid);
			purGatherManager.cancelPurGather(purGatherHeadId, userid);
			return null;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
