package com.jatools.manager.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.GatherOrderDao;
import com.jatools.dao.push.GatherSetDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.GatherOrderManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.GatherOrderHead;
import com.jatools.vo.push.GatherOrderLine;
import com.jatools.vo.util.SelectorOption;
import com.opensymphony.oscache.util.StringUtil;

public class GatherOrderManagerImpl extends BaseManager implements
		GatherOrderManager {
	private CommonDao commonDao;
	private GatherSetDao gatherSetDao;
	private GatherOrderDao gatherOrderDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setGatherSetDao(GatherSetDao gatherSetDao) {
		this.gatherSetDao = gatherSetDao;
	}

	public void setGatherOrderDao(GatherOrderDao gatherOrderDao) {
		this.gatherOrderDao = gatherOrderDao;
	}

	public CommonDao getCommonDao() {
		return this.commonDao;
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
		return gatherOrderDao.getItemClassForSlt(headid, setLineId, salableFlag);
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
		return gatherOrderDao.getOrnaClassForSlt(headid, setLineId, salableFlag, itemClassId);
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
		return gatherOrderDao.getAnalysisForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId);
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
		return gatherOrderDao.getStyleItemClassForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId);
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
		return gatherOrderDao.getStyleMiddleClassForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId, styleItemClassId);
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
		return gatherOrderDao.getStyleOrnaClassForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId);
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
		return gatherOrderDao.getStyleIdForSlt(headid, setLineId, salableFlag, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
	/**
	 * 从配货记录表中获取手工减库产生的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public Pager getGatherOrderLinePageData(String headid, String setLineId, String salableFlag, Map<String, String> condition){
		return gatherOrderDao.getGatherOrderLinePageData(headid, setLineId, salableFlag, condition);
	}
	public Pager getGatherOrderPageData(Map<String, String> condition){
		return gatherOrderDao.getGatherOrderPageData(condition);
	}
	public void saveNewOrderNum(String lineid, String newOrderNum){
		GatherOrderLine line = gatherOrderDao.getGatherOrderLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_CREATE_GATHER_ORDER);
		gatherOrderDao.updateGatherSetLineOrderNum(lineid, newOrderNum);
	}
	public void saveNewSize(String lineid, String newSizeId){
		GatherOrderLine line = gatherOrderDao.getGatherOrderLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_CREATE_GATHER_ORDER);
		boolean flag = gatherOrderDao.checkSizeExists(lineid, newSizeId);
		if(flag){
			throw new RuntimeException("新尺寸已经存在");
		}
		gatherOrderDao.updateGatherSetLineSizeId(lineid, newSizeId);
	}
	/**
	 * 撤销总单
	 */
	public void cancelGatherOrder(String headid, String userid){
		asertStatus("jat_pl_gather_set_head", "headid", headid, "status", DictConstant.BILL_STATUS_CREATE_GATHER_ORDER);
		if(gatherOrderDao.checkHasPushDispatch(headid)){
			throw new RuntimeException("该总单已经配货，不能撤销");
		}
		gatherOrderDao.deleteGatherOrderLineByHeadid(headid);
		gatherSetDao.updateGatherSetHeadStatus(headid, DictConstant.BILL_STATUS_SAVE);
	}
	public GatherOrderHead getGatherOrderHead(String headid){
		return gatherOrderDao.getGatherOrderHead(headid);
	}
	/**
	 * 根据推式总单id获取区域下的组织
	 * @param gatherOrderHeadId
	 * @return
	 */
	public List<Org> getOrgByGatherOrder(String gatherOrderHeadId){
		return gatherOrderDao.getOrgByGatherOrder(gatherOrderHeadId);
	}
	/**
	 * 强制关闭检查
	 * @param headid
	 * @param userid
	 */
	public void checkCloseGatherOrder(String headid, String userid){
		throw new RuntimeException("xxx，不能强制关闭");
	}
	/**
	 * 强制关闭
	 * @param headid
	 * @param req
	 * @return
	 */
	public void closeGatherOrder(String headid, String userid){
		gatherOrderDao.closeGatherOrder(headid, userid);
	}
	/**
	 * 强制开启
	 * @param headid
	 * @param req
	 * @return
	 */
	public void reopenGatherOrder(String headid, String userid){
		gatherOrderDao.reopenGatherOrder(headid, userid);
	}
}
