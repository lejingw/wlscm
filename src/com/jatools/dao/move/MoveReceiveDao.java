package com.jatools.dao.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveBillLine;

public interface MoveReceiveDao {

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
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMoveLineInfo(String code, boolean ornaFlag, String billType, String jmFlag);
	/**
	 * 接收调拨单行
	 * @param lineid
	 * @param userId
	 */
	public void receiveMoveLine(String lineid, String inGroupNo, String userId);
	/**
	 * 删除已接收的记录
	 * @param lineid
	 * @param userid
	 */
	public void deleteMoveReceiveLine(String lineid, String userid);
	/**
	 * 获取已接收的调拨单行数
	 * @param headid
	 * @return
	 */
	public Long getReceivedMoveLineCount(String headid);
	/**
	 * 废弃调拨单行
	 * @param headid
	 * @param userid
	 */
	public void discardMoveLine(String headid, String userid);
	/**
	 * 将已经接收的饰品入库
	 * @param headid
	 * @param userid
	 */
	public void inivMoveLine(String headid, String userid);
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
	 * 生成标签申请单头
	 * @param headid
	 * @param userId
	 * @return
	 */
	public String createLabelApplyHead(String headid, String userId, String applyBillNo);
	/**
	 * 生成标签申请单行
	 * @param headid
	 * @param applyHeadId
	 * @param userId
	 */
	public void createLabelApplyLine(String headid, String applyHeadId, String userId);
	/**
	 * 更新标志
	 * @param headid
	 * @param userId
	 */
	public void updateLabelApplyFlag(String headid, String userId);
	public Integer getLabelApplyLineCount(String headid);
	/**
	 * 获取未入库是饰品数量
	 * @param headid
	 * @return
	 */
	public Integer getUnInivCount(String headid);

    /**
     * 是否是加盟门店之间的调拨
     * @param headid
     * @return
     */
    boolean isJmMove(String headid);
}
