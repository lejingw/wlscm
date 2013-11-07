package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;

public interface MoveReceiveManager {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveReceivePageData(Map<String, String> condition, String billType, String jmFlag, String orgId);
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getReceivedMoveBillLine(String headid);
	/**
	 * 获取调拨单头
	 * @param headid
	 * @return
	 */
	public MoveBillHead getMoveBillHead(String headid);
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMoveLineInfo(String code, boolean ornaFlag, String billType, String jmFlag);
	/**
	 * 接收调拨单行
	 * @param lineid
	 * @param sessionUserId
	 */
	public void receiveMoveLine(String lineid, String inGroupNo, String sessionUserId);
	/**
	 * 删除已接收的记录
	 * @param lineid
	 * @param userid
	 */
	public void deleteMoveReceiveLine(String lineid, String userid);
	/**
	 * 检查是否存在已接收但未入库的调拨单行
	 * @param headid
	 * @return
	 */
	public boolean checkMoveLineStatusAvail(String headid);
	/**
	 * 关闭调拨单
	 * @param headid
	 * @param sessionUserId
	 */
	public void closeMoveBill(String headid, String sessionUserId);
	/**
	 * 将已经接收的饰品入库，并更改状态为已入库11
	 * @param headid
	 * @param userid
	 */
	public void inivMoveLine(String headid, String billType, String jmFlag, String userid);
	/**
	 * 未接收饰品
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getUnreceiveMoveBillLine(String headid);
	/**
	 * 已入库饰品
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getInivOrnaMoveBillLine(String headid);
	/**
	 *  修改调入柜组
	 * @param lineid
	 * @param inGroupNo
	 */
	public void changeInGroupNo(String lineid, String inGroupNo);
	/**
	 * 生成标签申请单
	 * @param headid
	 * @param sessionUserId
	 */
	public void createLabelApply(String headid, String userId);
	public Integer getLabelApplyLineCount(String headid);
	/**
	 * 确认关闭
	 * @param headid
	 * @param userid
	 */
	public void confirmClose(String headid, String userid);
	/**
	 * 继续接收
	 * @param headid
	 * @param userid
	 */
	public void continueReceive(String headid, String userid);
}
