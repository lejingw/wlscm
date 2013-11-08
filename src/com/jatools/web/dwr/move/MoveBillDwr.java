package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.manager.move.MoveBillManager;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.util.DateUtil;

public class MoveBillDwr {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(MoveBillDwr.class);
	private MoveBillManager moveBillManager;

	public void setMoveBillManager(MoveBillManager moveBillManager) {
		this.moveBillManager = moveBillManager;
	}
	
	/**
	 * 获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public MoveBillLine getMaterActiveInfo(String ornaCode, String orgId){
		try {
			MoveBillLine line = moveBillManager.getMaterActiveInfo(ornaCode, orgId);
			return line;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
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
		List<String> tmpList = moveBillManager.checkOrnaStatusAvail(ornaCodeList);
		if(null!=tmpList && tmpList.size()>0)
			return tmpList;
		return null;
	}
	/**
	 * 保存或修改调拨单
	 * @param moveHead
	 * @param newOrnaCodeList 添加的行记录
	 * @param deleteOrnaCodeList 删除的行记录
	 * @return
	 */
	public String saveMoveBill(MoveBillHead moveHead, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, HttpSession session){
		try {
			moveBillManager.saveMoveBill(moveHead, newOrnaCodeList, deleteOrnaCodeList, CommonUtil.getSessionUserId(session));
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public MoveBillLine getMaterActiveInfo2(String code, String orgId, String billType, String inOrgId){
//		MoveBillLine line = moveBillManager.getMaterActiveInfo(code, true, orgId, billType, null, inOrgId);
//		if(null == line){
//			throw new RuntimeException("不能获取饰品信息");
//		}
//		if(!orgId.equals(line.getOrgId())){
//			throw new RuntimeException("饰品所在网点不为当前选择的调出组织");
//		}
//		line.setCurrentDate(DateUtil.getCurrentDate10());
//		line.setNames();
//		return line;
//	}
	public String getSumCostLimit(){
		String sumCostLimit = ParameterCache.getInstance().getValue(ParameterConstant.MOVE_BILL_SUMCOST_LIMIT);
		return sumCostLimit;
	}
	/**
	 * 删除调拨单
	 * @param headid
	 * @return
	 */
	public String deleteMoveBill(List<String> headidList, HttpServletRequest req){
		try{
			moveBillManager.deleteMoveBill(headidList, CommonUtil.getSessionUserId(req));
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 生成加盟结算单
	 * @param headidList
	 * @param session
	 * @return
	 */
	public String generateEstimate(List<String> headidList, String billType, HttpSession session){
		try{
			String userid = CommonUtil.getSessionUserId(session);
			moveBillManager.generateEstimate(headidList, billType, userid);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 撤销加盟调拨单
	 * @param headidList
	 * @param session
	 * @return
	 */
	public String cancelMoveBill(List<String> headidList, HttpSession session){
		try{
			String userid = CommonUtil.getSessionUserId(session);
			moveBillManager.cancelMoveBill(headidList, userid);
			return null;
		}catch(Exception e){
			return e.getMessage();
		}
	}
	/**
	 * 检查是否配置调拨网络
	 * @param outOrgId
	 * @param inOrgId
	 * @return
	 */
	public boolean checkMoveNetAvail(String outOrgId, String inOrgId){
		return moveBillManager.checkMoveNetAvail(outOrgId, inOrgId);
	}
	/**
	 * 移库单、柜组调拨单接收
	 * @param billid
	 * @param session
	 * @return
	 */
	public String receiveMoveBill(String billid, HttpSession session){
		try {
			moveBillManager.receiveMoveBill(billid, CommonUtil.getSessionUserId(session));
		} catch (Exception e) {
			return e.getMessage();
		}	
		return null;
	}
	
	public String test(){
		try {
			Thread.sleep(500*1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		for(int i=0;i<20;i++){
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					System.out.println(moveBillManager.getBillno("TB"));
//					System.out.println(commonManager.getBillno("TB"));
//				}
//			}).start();
//		}
		if(true){
			throw new RuntimeException("xxxxxx打发啊打发");
		}
		return null;
	}
}
