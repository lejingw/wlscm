package com.jatools.web.dwr.move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.manager.move.MoveCmdManager;
import com.jatools.vo.move.MoveCmdHead;
import com.jatools.vo.move.MoveCmdLine;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.util.StringUtil;

public class MoveCmdDwr {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(MoveCmdDwr.class);
	private MoveCmdManager moveCmdManager;

	public void setMoveCmdManager(MoveCmdManager MoveCmdManager) {
		this.moveCmdManager = MoveCmdManager;
	}
	
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public MoveCmdLine getMaterActiveInfo(String code, boolean ornaFlag, String orgId, String inOrgId){
		MoveCmdLine line = moveCmdManager.getMaterActiveInfo(code, ornaFlag, inOrgId);
		if(null == line){
			throw new RuntimeException("不能获取饰品信息");
		}
		if(!orgId.equals(line.getOrgId())){
			throw new RuntimeException("饰品所在网点不为当前选择的调出组织");
		}
		checkMaterActiveStatus(line);
		line.setNames();
		return line;
	}
	/**
	 * 检查饰品是否可以调拨
	 * @param materActive
	 */
	private void checkMaterActiveStatus(MoveCmdLine info){
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_USED.equals(info.getStatus())){
			throw new RuntimeException("该饰品为保留状态，不能调拨");
		}
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_ONWAY.equals(info.getStatus())){
			throw new RuntimeException("该饰品为在途状态，不能调拨");
		}
		if(!DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(info.getStatus())){
			throw new RuntimeException("该饰品不为有效状态，不能调拨");
		}
	}
	/**
	 * 检查饰品状态是否有效
	 * @param ornaCodeList
	 * @return
	 */
	public List<String> checkOrnaStatusAvail(List<String> ornaCodeList){
		if(null == ornaCodeList || ornaCodeList.size()<1)
			return null;
		List<String> tmpList = moveCmdManager.checkOrnaStatusAvail(ornaCodeList);
		if(null!=tmpList && tmpList.size()>0)
			return tmpList;
		return null;
	}
	public String getSumCostLimit(){
		String sumCostLimit = ParameterCache.getInstance().getValue(ParameterConstant.MOVE_BILL_SUMCOST_LIMIT);
		return sumCostLimit;
	}
	
	/**
	 * 保存或修改调拨单
	 * @param moveHead
	 * @param newOrnaCodeList 添加的行记录
	 * @param deleteOrnaCodeList 删除的行记录
	 * @return
	 */
	public String saveMoveCmd(MoveCmdHead moveHead, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, double sumCost, HttpSession session){
		double sumCostLimit = Double.parseDouble(ParameterCache.getInstance().getValue(ParameterConstant.MOVE_BILL_SUMCOST_LIMIT));
		if(sumCostLimit<sumCost){
			return "调拨单总成本已经超上限[" + sumCostLimit + "]";
		}
		try {
			moveCmdManager.saveMoveCmd(moveHead, newOrnaCodeList, deleteOrnaCodeList, CommonUtil.getSessionUserId(session));
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 删除调拨单
	 * @param headid
	 * @return
	 */
	public String deleteMoveCmd(List<String> headidList){
		try{
			moveCmdManager.deleteMoveCmd(headidList);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	
	public Map<String, String> invalidMoveCmd(String headid, HttpServletRequest req) {
		Map<String, String> res = new HashMap<String, String>();
		try {
			this.moveCmdManager.invalidMoveCmd(headid, CommonUtil.getSessionUserId(req));
			res.put("isSuccess", "true");
		} catch (Exception e) {
			e.printStackTrace();
			res.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				res.put("msg", e.getMessage());
			} else {
				res.put("msg", "处理失败");
			}
		}
		return res;
	}
}
