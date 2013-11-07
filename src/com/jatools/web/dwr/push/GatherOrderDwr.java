package com.jatools.web.dwr.push;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.manager.push.GatherOrderManager;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.StringUtil;

public class GatherOrderDwr {
//	private GatherSetManager gatherSetManager;
	private GatherOrderManager gatherOrderManager;

//	public void setGatherSetManager(GatherSetManager gatherSetManager) {
//		this.gatherSetManager = gatherSetManager;
//	}

	public void setGatherOrderManager(GatherOrderManager gatherOrderManager) {
		this.gatherOrderManager = gatherOrderManager;
	}

	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid, String setLineId, String salableFlag){
		if(StringUtil.isEmpty(headid) && (StringUtil.isEmpty(setLineId))){
			return null;
		}
		return gatherOrderManager.getItemClassForSlt(headid, setLineId, salableFlag);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId){
		if((StringUtil.isEmpty(headid) && (StringUtil.isEmpty(setLineId))) || StringUtil.isEmpty(itemClassId)){
			return null;
		}
		return gatherOrderManager.getOrnaClassForSlt(headid, setLineId, salableFlag, itemClassId);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId){
		if((StringUtil.isEmpty(headid) && (StringUtil.isEmpty(setLineId))) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId)){
			return null;
		}
		return gatherOrderManager.getAnalysisForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId){
		if((StringUtil.isEmpty(headid) && (StringUtil.isEmpty(setLineId))) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/){
			return null;
		}
		return gatherOrderManager.getStyleItemClassForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId){
		if((StringUtil.isEmpty(headid) && (StringUtil.isEmpty(setLineId))) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/ || StringUtil.isEmpty(styleItemClassId)){
			return null;
		}
		return gatherOrderManager.getStyleMiddleClassForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId, styleItemClassId);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId){
		if((StringUtil.isEmpty(headid) && (StringUtil.isEmpty(setLineId))) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/ || StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId)){
			return null;
		}
		return gatherOrderManager.getStyleOrnaClassForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String setLineId, String salableFlag, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		if((StringUtil.isEmpty(headid) && (StringUtil.isEmpty(setLineId))) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) /*|| StringUtil.isEmpty(analysisId)*/ || StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId) || StringUtil.isEmpty(styleOrnaClassId)){
			return null;
		}
		return gatherOrderManager.getStyleIdForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
	public String saveNewOrderNum(String lineid, String newOrderNum){
		gatherOrderManager.saveNewOrderNum(lineid, newOrderNum);
		return null;
	}
	public String saveNewSize(String lineid, String newSizeId){
		gatherOrderManager.saveNewSize(lineid, newSizeId);
		return null;
	}
	public String cancelGatherOrder(String headid, HttpServletRequest req){
		try {
			String userid = CommonUtil.getSessionUserId(req);
			gatherOrderManager.cancelGatherOrder(headid, userid);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 强制关闭时进行检查
	 * 1、根据最后到货日期+1天作为单据关闭日期，若此时间前强制关闭单据，则给出明确提示。
	 * 2、采购单已被保留，总单也可以强制关闭，但不能自动关闭。
	 * @param headid
	 * @param req
	 * @return
	 */
	public String checkCloseGatherOrder(String headid, HttpServletRequest req){
		try {
			String userid = CommonUtil.getSessionUserId(req);
			gatherOrderManager.checkCloseGatherOrder(headid, userid);
		} catch (Exception e) {
//			return e.getMessage();
		}
		return null;
	}
	/**
	 * 强制关闭
	 * @param headid
	 * @param req
	 * @return
	 */
	public String closeGatherOrder(String headid, HttpServletRequest req){
		try {
			String userid = CommonUtil.getSessionUserId(req);
			gatherOrderManager.closeGatherOrder(headid, userid);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 强制开启
	 * @param headid
	 * @param req
	 * @return
	 */
	public String reopenGatherOrder(String headid, HttpServletRequest req){
		try {
			String userid = CommonUtil.getSessionUserId(req);
			gatherOrderManager.reopenGatherOrder(headid, userid);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
}
