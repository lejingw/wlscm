package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.MoveReceiveManager;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.vo.sys.Dict;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.DictUtil;
import com.opensymphony.oscache.util.StringUtil;

public class MoveReceiveDwr {
	private static Logger logger = Logger.getLogger(MoveReceiveDwr.class);
	private MoveReceiveManager moveReceiveManager;

	public void setMoveReceiveManager(MoveReceiveManager moveReceiveManager) {
		this.moveReceiveManager = moveReceiveManager;
	}
	
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @param headid
	 * @return
	 */
	public MoveBillLine getMoveLineInfo(String headid, String code, boolean ornaFlag, String billType, String jmFlag, HttpSession session){
		MoveBillLine line = moveReceiveManager.getMoveLineInfo(code, ornaFlag, billType, jmFlag);
		if(null == line){
			throw new RuntimeException("不能获取饰品信息");
		}
		if(!headid.equals(line.getHeadid())){
			throw new RuntimeException("饰品不在当前接收单据,属于"+DictUtil.getValue(DictConstant.INVCODE, line.getStockId())+"仓库");
		}
		if(DictConstant.BILL_STATUS_RECEIVED.equals(line.getStatus())){
			throw new RuntimeException("该饰品已经接收");
		}
		if(!DictConstant.BILL_STATUS_SAVE.equals(line.getStatus())){
			throw new RuntimeException("只有保存状态的调拨饰品行记录才能接收");
		}
		if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(billType)){
			String orgId = CommonUtil.getSessionOrgId(session);
			boolean hqOrgFlag = false;
			List<Dict> hqorgList = DictCache.getInstance().getDicts(DictConstant.HQ_ORGS);
			for(Dict dict : hqorgList){
				if(dict.getItemValue().equals(orgId)){
					hqOrgFlag = true;
					break;
				}
			}
//			if(!hqOrgFlag && StringUtil.isEmpty(line.getInGroupNo())){
//				throw new RuntimeException("不能根据组织["+BdCommon.getOrgName(line.getOrgId())+"]、大类["+BdCommon.getItemClassDesc(line.getItemClassId())+"]、小类["+BdCommon.getOrnaClassDesc(line.getOrnaClassId())+"]获取调入柜组");
//			}
			if(hqOrgFlag){
				line.setInGroupNo("10");
			}
		}
		moveReceiveManager.receiveMoveLine(line.getLineid(), line.getInGroupNo(), CommonUtil.getSessionUserId(session));
		line.setNames();
		return line;
	}
	/**
	 * 删除已接收的记录
	 * @param lineid
	 * @param session
	 * @return
	 */
	public String deleteMoveReceiveLine(String lineid, HttpSession session){
		if(StringUtil.isEmpty(lineid)){
			return "不能获取参数";
		}
		moveReceiveManager.deleteMoveReceiveLine(lineid, CommonUtil.getSessionUserId(session));
		return null;
	}
	private byte[] lock1 = new byte[0];
	/**
	 * 将已经接收的饰品入库，并更改状态为已入库11
	 * @param headid
	 * @param session
	 * @return
	 */
	public String inivMoveLine(String headid, String billType, String jmFlag, HttpSession session){
		if(StringUtil.isEmpty(headid)){
			return "不能获取参数";
		}
		synchronized (lock1) {			
			moveReceiveManager.inivMoveLine(headid, billType, jmFlag, CommonUtil.getSessionUserId(session));
		}
		return null;
	}
	/**
	 * 关闭单据
	 * @param headid
	 * @return
	 */
	public String closeMoveBill(String headid, HttpSession session){
		if(StringUtil.isEmpty(headid)){
			return "不能获取参数";
		}
		boolean flag = moveReceiveManager.checkMoveLineStatusAvail(headid);//检查是否存在已接收但未入库的调拨单行
		if(!flag){
			return "存在已接收但未入库的调拨单行";
		}
		moveReceiveManager.closeMoveBill(headid, CommonUtil.getSessionUserId(session));
		return null;
	}
	/**
	 * 修改调入柜组
	 * @param lineid
	 * @param inGroupNo
	 * @return
	 */
	public String changeInGroupNo(String lineid, String inGroupNo){
		moveReceiveManager.changeInGroupNo(lineid, inGroupNo);
		return null;
	}
	/**
	 * 生成标签申请单
	 * @param headid
	 * @param session
	 * @return
	 */
	public String createLabelApply(String headid, HttpSession session){
		if(StringUtil.isEmpty(headid)){
			return "不能获取参数";
		}
		Integer count = moveReceiveManager.getLabelApplyLineCount(headid);
		if(count<1){
			return "没有符合条件的记录，不能生成标签申请单<br>计量单位为件，且网点金额有变动";
		}
		moveReceiveManager.createLabelApply(headid, CommonUtil.getSessionUserId(session));
		return null;
	}
	/**
	 * 确认关闭
	 * @param headid
	 * @param session
	 * @return
	 */
	public String confirmClose(String headid, HttpSession session){
		if(StringUtil.isEmpty(headid)){
			return "不能获取参数";
		}
		String userid = CommonUtil.getSessionUserId(session);
		String orgid = CommonUtil.getSessionOrgId(session);
		MoveBillHead head = moveReceiveManager.getMoveBillHead(headid);
		if(!head.getOutOrgId().equals(orgid)){
			return "只有当前登入组织等于调出组织时，才可以进行操作";
		}
		try {
			moveReceiveManager.confirmClose(headid, userid);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 继续接收
	 * @param headid
	 * @param userid
	 */
	public String continueReceive(String headid, HttpSession session){
		if(StringUtil.isEmpty(headid)){
			return "不能获取参数";
		}
		String userid = CommonUtil.getSessionUserId(session);
		String orgid = CommonUtil.getSessionOrgId(session);
		MoveBillHead head = moveReceiveManager.getMoveBillHead(headid);
		if(!head.getOutOrgId().equals(orgid)){
			return "只有当前登入组织等于调出组织时，才可以进行操作";
		}
		try {
			moveReceiveManager.continueReceive(headid, userid);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
}
