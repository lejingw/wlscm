package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.StandardDispatchManager;
import com.jatools.vo.move.StandardDispatch;
import com.jatools.vo.move.StandardDispatchOrna;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.StringUtil;

public class StandardDispatchDwr {
	private StandardDispatchManager standardDispatchManager;
	private byte[] lock = new byte[0];

	public void setStandardDispatchManager(StandardDispatchManager standardDispatchManager) {
		this.standardDispatchManager = standardDispatchManager;
	}
	/**
	 * 检查配货参数是否已经存在
	 */
	public String checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId){
		if("-1".equals(gheadid))
			return null;
		StandardDispatch standardDispatch = standardDispatchManager.getStandardDispatch(gheadid);
		if(DictConstant.PLAN_BILL_STATUS_SAVE.equals(standardDispatch.getStatus())){
			boolean flag = standardDispatchManager.checkDispatchCondition(gheadid, itemClassId, ornaClassId);
			if(!flag){
				return "该配货参数已经被使用";
			}
		}else{
			if(DictConstant.BILL_STATUS_MOVED.equals(standardDispatch.getStatus())){
				return "总部总单已经关闭不能生成配货参数";
			}
			return "只有保存状态的单据才可以生成配货参数";
		}
		return null;
	}
	/**
	 * 创建配货参数
	 */
	public String[] saveDispatchCondition(String gheadid, String itemClassId, String ornaClassId, HttpSession session){
		try {
			String userid = CommonUtil.getSessionUserId(session);
			//创建配货参数，并生成配货临时数据
			return standardDispatchManager.saveDispatchCondition(gheadid, itemClassId, ornaClassId, userid);
		} catch (Exception e) {
			throw new RuntimeException("创建配货参数出错");
		}
	}
	/**
	 * 释放配货参数
	 */
	public String releaseLock(String conditionId, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		standardDispatchManager.releaseLock(conditionId, userid);
		return null;
	}
	

	/**
	 * 配货
	 * @param code
	 * @param ornaFlag
	 */
	public StandardDispatchOrna dispatchOrna(String itemClassId, String ornaClassId, String code, boolean ornaFlag, HttpSession session){
		//到现有量表中查找
		StandardDispatchOrna info = standardDispatchManager.getStandardDispatchOrnaInfo(code, ornaFlag);
		if(null == info){
			throw new RuntimeException("不能获取饰品信息");
		}
		if(!itemClassId.equals(info.getItemClassId()) || !ornaClassId.equals(info.getOrnaClassId())){
			throw new RuntimeException("大类、小类不符合当前配货参数");
		}
		String currentOrgId = CommonUtil.getSessionOrgId(session);
		if(!currentOrgId.equals(info.getOrgId())){
			throw new RuntimeException("饰品所在网点不为当前用户的登录组织");
		}
		checkMaterActiveStatus(info);
		return info;
	}
	/**
	 * 检查饰品是否可以配货
	 * @param materActive
	 */
	private void checkMaterActiveStatus(StandardDispatchOrna info){
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_USED.equals(info.getStatus())){
			throw new RuntimeException("该饰品为保留状态，不能配货");
		}
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_ONWAY.equals(info.getStatus())){
			throw new RuntimeException("该饰品为在途状态，不能配货");
		}
		if(!DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(info.getStatus())){
			throw new RuntimeException("该饰品不为有效状态，不能配货");
		}
		if(DictConstant.YES_OR_NO_YES.equals(info.getMaterialFlag())){
			throw new RuntimeException("该饰品为备货，不能配货");
		}
		if(!DictConstant.INVCODE_ORNA.equals(info.getStockId())){
			throw new RuntimeException("该饰品仓库不为饰品库，不能配货");
		}
	}
	/**
	 * 删除配货记录
	 * @param logId
	 * @return
	 */
	public String deleteDispatchLog(String ornaLogId, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		standardDispatchManager.deleteDispatchOrnaLog(ornaLogId, userid);
		return null;
	}
	public String deleteStandardDispatch(List<String> gheadidList, HttpSession session){
		if(gheadidList.size()<1){
			return null;
		}
		for(String gheadid:gheadidList){
			standardDispatchManager.checkStandardDispatchDelete(gheadid);
		}
		String userid = CommonUtil.getSessionUserId(session);
		standardDispatchManager.deleteStandardDispatch(gheadidList, userid);
		return null;
	}
	public String createMoveBill(List<String> gheadidList, HttpSession session){
		if(gheadidList.size()<1){
			return null;
		}
		for(String gheadid:gheadidList){
			standardDispatchManager.checkStandardDispatchDelete(gheadid);
		}
		String userid = CommonUtil.getSessionUserId(session);
		String orgid = CommonUtil.getSessionOrgId(session);
		standardDispatchManager.createMoveBill(gheadidList, orgid, userid);
		return null;
	}
	public String cancelDispatch(String gheadid, String ornaCode, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		standardDispatchManager.cancelDispatch(gheadid, ornaCode, userid);
		return null;
	}
	public String exchangeDispatching(String gheadid, String ornaCode, String orgId, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		standardDispatchManager.exchangeDispatching(gheadid, ornaCode, orgId, userid);
		return null;
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
		return standardDispatchManager.getItemClassForSlt(headid);
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
		return standardDispatchManager.getOrnaClassForSlt(headid, itemClassId);
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
		return standardDispatchManager.getAnalysisForSlt(headid, itemClassId, ornaClassId);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) || StringUtil.isEmpty(analysisId)){
			return null;
		}
		return standardDispatchManager.getStyleItemClassForSlt(headid, itemClassId, ornaClassId, analysisId);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) || StringUtil.isEmpty(analysisId) || StringUtil.isEmpty(styleItemClassId)){
			return null;
		}
		return standardDispatchManager.getStyleMiddleClassForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) || StringUtil.isEmpty(analysisId) || StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId)){
			return null;
		}
		return standardDispatchManager.getStyleOrnaClassForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		if(StringUtil.isEmpty(headid) || StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) || StringUtil.isEmpty(analysisId) || StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId) || StringUtil.isEmpty(styleOrnaClassId)){
			return null;
		}
		return standardDispatchManager.getStyleIdForSlt(headid, itemClassId, ornaClassId, analysisId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
}
