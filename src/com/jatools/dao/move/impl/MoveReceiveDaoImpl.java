package com.jatools.dao.move.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MoveReceiveDao;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class MoveReceiveDaoImpl extends BaseDao implements MoveReceiveDao {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveReceivePageData(Map<String, String> condition, String billType, String jmFlag, String orgId){
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("orgId", orgId);
		return executeQueryForPager("MoveReceive.getMoveReceivePageData", "MoveReceive.getMoveReceiveTotalCount", condition);
	}
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getReceivedMoveBillLine(String headid){
		return executeQueryForList("MoveReceive.getReceivedMoveBillLine", headid);
	}
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMoveLineInfo(String code, boolean ornaFlag, String billType, String jmFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		return (MoveBillLine) executeQueryForObject("MoveReceive.getMoveLineInfo", condition);
	}
	/**
	 * 接收调拨单行
	 * @param lineid
	 * @param userid
	 */
	public void receiveMoveLine(String lineid, String inGroupNo, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("lineid", lineid);
		condition.put("status", DictConstant.BILL_STATUS_RECEIVED);
		condition.put("group", inGroupNo);
		condition.put("receEmpId", userid);
		condition.put("receDate", DateUtil.getCurrentDate18());
		executeUpdate("MoveReceive.receiveMoveLine", condition);
	}
	/**
	 * 删除已接收的记录
	 * @param lineid
	 * @param userid
	 */
	public void deleteMoveReceiveLine(String lineid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("lineid", lineid);
		condition.put("status", DictConstant.BILL_STATUS_SAVE);
		condition.put("group", null);
		condition.put("receEmpId", null);
		condition.put("receDate", null);
		executeUpdate("MoveReceive.receiveMoveLine", condition);
	}
	/**
	 * 获取已接收的调拨单行数
	 * @param headid
	 * @return
	 */
	public Long getReceivedMoveLineCount(String headid){
		return (Long) executeQueryForObject("MoveReceive.getReceivedMoveLineCount", headid);
	}
	/**
	 * 作废调拨单行
	 * @param headid
	 * @param userid
	 */
	public void discardMoveLine(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		//将保存状态的调拨单行作废
		executeUpdate("MoveReceive.discardMoveLine", condition);
	}
	/**
	 * 将已经接收的饰品入库
	 * @param headid
	 * @param userid
	 */
	public void inivMoveLine(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		//将已接收状态的调拨单行改为已入库
		executeUpdate("MoveReceive.inivMoveLine", condition);
	}
	/**
	 * 未接收饰品
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getUnreceiveMoveBillLine(String headid){
		return executeQueryForList("MoveReceive.getUnreceiveMoveBillLine", headid);
	}
	/**
	 * 已入库饰品
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getInivOrnaMoveBillLine(String headid){
		return executeQueryForList("MoveReceive.getInivOrnaMoveBillLine", headid);
	}
	/**
	 *  修改调入柜组
	 * @param lineid
	 * @param inGroupNo
	 */
	public void changeInGroupNo(String lineid, String inGroupNo){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("lineid", lineid);
		condition.put("inGroupNo", inGroupNo);
		executeUpdate("MoveReceive.changeInGroupNo", condition);
	}
	/**
	 * 生成标签申请单头
	 * @param headid
	 * @param userId
	 * @return
	 */
	public String createLabelApplyHead(String headid, String userId, String applyBillNo){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userId", userId);
		condition.put("date", DateUtil.getCurrentDate18());
		condition.put("changeLabelReason", "38");
		condition.put("applyBillNo", applyBillNo);
		return (String) executeInsert("MoveReceive.createLabelApplyHead", condition);
	}
	/**
	 * 生成标签申请单行
	 * @param headid
	 * @param applyHeadId
	 * @param userId
	 */
	public void createLabelApplyLine(String headid, String applyHeadId, String userId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("applyHeadId", applyHeadId);
		condition.put("userId", userId);
		condition.put("date", DateUtil.getCurrentDate18());
		executeInsert("MoveReceive.createLabelApplyLine", condition);
	}
	/**
	 * 更新标志
	 * @param headid
	 * @param userId
	 */
	public void updateLabelApplyFlag(String headid, String userId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userId", userId);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveReceive.updateLabelApplyFlag", condition);
	}
	public Integer getLabelApplyLineCount(String headid){
		return (Integer) executeQueryForObject("MoveReceive.getLabelApplyLineCount", headid);
	}
	/**
	 * 获取未入库是饰品数量
	 * @param headid
	 * @return
	 */
	public Integer getUnInivCount(String headid){
		return (Integer) executeQueryForObject("MoveReceive.getUnInivCount", headid);
	}

    public boolean isJmMove(String headid) {
        Integer res = (Integer)this.executeQueryForObject("MoveReceive.isJmMove", headid);
        if(res != null && res.intValue() > 0) {
            return true;
        }
        return false;
    }
}
