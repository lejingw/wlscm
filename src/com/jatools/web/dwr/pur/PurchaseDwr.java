package com.jatools.web.dwr.pur;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.pur.PurchaseManager;
import com.jatools.vo.pur.PurchaseEnter;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.pur.PurchaseLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PurchaseDwr {
	private static Logger logger = Logger.getLogger(PurchaseDwr.class);
	private PurchaseManager purchaseManager;

	public PurchaseManager getPurchaseManager() {
		return purchaseManager;
	}

	public void setPurchaseManager(PurchaseManager purchaseManager) {
		this.purchaseManager = purchaseManager;
	}

	/**
	 * 获取大类
	 * 
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid) {
		if (StringUtil.isEmpty(headid)) {
			return null;
		}
		return purchaseManager.getItemClassForSlt(headid);
	}

	/**
	 * 获取小类
	 * 
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId) {
		if (StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId)) {
			return null;
		}
		return purchaseManager.getOrnaClassForSlt(headid, itemClassId);
	}

	/**
	 * 获取分析范围
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId) {
		if (StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId)) {
			return null;
		}
		return purchaseManager.getAnalysisForSlt(headid, itemClassId, ornaClassId);
	}

	/**
	 * 获取款式大类
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId) {
		if (StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/) {
			return null;
		}
		return purchaseManager.getStyleItemClassForSlt(headid, itemClassId, ornaClassId, analysisId);
	}
	/**
	 * 获取款式中类
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId) {
		if (StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/||StringUtil.isEmpty(styleItemClassId)) {
			return null;
		}
		return purchaseManager.getStyleMiddleClassForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId);
	}
	/**
	 * 获取款式小类
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId) {
		if (StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/||StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId)) {
			return null;
		}
		return purchaseManager.getStyleOrnaClassForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId);
	}

	/**
	 * 获取款式
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId) {
		if (StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/ || StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId) || StringUtil.isEmpty(styleOrnaClassId)) {
			return null;
		}
		return purchaseManager.getStyleIdForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
	
	/**
	 * 更新采购单行中的到货数量、不合格数量、合格数量
	 * @param lineid
	 * @return
	 */
	public String[] getNumArriveCheck(String lineid){
		PurchaseLine line = purchaseManager.getPurchaseLineById(lineid);
		String[] tmp = new String[4];
		tmp[0] = line.getNumOrder();
		tmp[1] = line.getNumArrive();
		tmp[2] = line.getNumCheck();
		tmp[3] = line.getNumUncheck();
		return tmp;
	}
	/**
	 * 删除到货、不合格记录
	 * @param enterid
	 * @return
	 */
	public String deletePurchaseEnter(String enterid){
		try {
			purchaseManager.deletePurchaseEnter(enterid);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	/**
	 * 保存到货、不合格记录
	 * @param enterid
	 * @return
	 */
	public String savePurchaseEnter(String headid, PurchaseEnter enter, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		enter.setCreateDate(DateUtil.getCurrentDate18());
		enter.setCreateId(userid);
		enter.setStatus(DictConstant.BILL_STATUS_SAVE);
		PurchaseHead head = purchaseManager.getPurchaseHead(headid);
		enter.setOutdateFlag(DateUtil.isBefore(head.getOrderEndDate(), DateUtil.getCurrentDate10())?"1":"0");
		purchaseManager.savePurchaseEnter(enter);
		return null;
	}
	/**
	 * 检查到货数量有效性，是否可以删除到货数量
	 * @param enterid
	 * @return
	 */
	public String checkDeleteNumArrive(String lineid, int num){
		PurchaseLine line = purchaseManager.getPurchaseLineById(lineid);
		int numArrive = Integer.parseInt(line.getNumArrive());
		int numUncheck = Integer.parseInt(line.getNumUncheck());
		if(numArrive - num < numUncheck){
			return "删除后，到货数量小于不合格数量，请先修改不合格数量";
		}
		return null;
	}
	/**
	 * 检查到货数量有效性，是否可以保存不合格数量
	 * @param enterid
	 * @return
	 */
	public String checkSaveNumUncheck(String lineid, int num){
		PurchaseLine line = purchaseManager.getPurchaseLineById(lineid);
		int numArrive = Integer.parseInt(line.getNumArrive());
		int numUncheck = Integer.parseInt(line.getNumUncheck());
		if(numArrive < numUncheck + num){
			return "保存后，不合格数量大于到货数量，请先修改到货数量";
		}
		return null;
	}
	
	/**
	 * 撤回，修改采购数量
	 * @purchaseHead 包含purEmpids采购员、bizType业务类型、memo说明
	 * @param lineidArr
	 * @param numCancelArr
	 * @return
	 */
	public String markPurchaseBill(PurchaseHead purchaseHead, String[] lineidArr, String[] numCancelArr){
		purchaseManager.markPurchaseBill(purchaseHead, lineidArr, numCancelArr);
		return null;
	}
	
	/**
	 * 根据定做单生成采购单
	 * @param custid
	 * @return
	 */
	public String createPurchaseBillFromCust(String custid, String jmFlag, List<PurchaseHead> purchaseHeadList, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		purchaseManager.createPurchaseBillFromCust(custid, jmFlag, purchaseHeadList, userid);
		return null;
	}
	/**
	 * 采购岗接收、拒绝定做单
	 * @param custid
	 * @param flag
	 * @param reviewMemo
	 * @param session
	 * @return
	 */
	public String reviewCustBill(String custid, String jmFlag, String flag, String reviewMemo, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		purchaseManager.reviewCustBill(custid, jmFlag, DictConstant.YES_OR_NO_YES.equals(flag), reviewMemo, userid);
		return null;
	}
	/**
	 * 改变保留状态
	 * @param headFlag
	 * @param operId
	 * @param checked
	 * @return
	 */
	public String changeUsedFlag(boolean headFlag, String operId, boolean checked){
		if(headFlag){
			purchaseManager.updatePurchaseHeadUsedFlag(operId, checked);
			purchaseManager.updatePurchaseLineUsedFlag(true, operId, checked);
		}else{
			purchaseManager.updatePurchaseLineUsedFlag(false, operId, checked);
		}
		return null;
	}
}
