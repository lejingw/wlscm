package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveCmdHead;
import com.jatools.vo.move.MoveCmdLine;

public interface MoveCmdManager {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveCmdPageData(Map<String, String> condition, String orgId, String userid);
	
	public Pager getMoveCmdPageDataByMoveBill(Map<String, String> condition);

	/**
	 * 获取现有量表信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveCmdLine getMaterActiveInfo(String code, boolean ornaFlag, String inOrgId);
	/**
	 * 检查饰品状态是否有效
	 * @param ornaCodeList
	 * @return
	 */
	public List<String> checkOrnaStatusAvail(List<String> ornaCodeList);
	/**
	 * 保存或修改调拨单
	 * @param moveHead
	 * @param newOrnaCodeList 添加的行记录
	 * @param deleteOrnaCodeList 删除的行记录
	 * @return
	 */
	public void saveMoveCmd(MoveCmdHead moveHead, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, String userid);
	/**
	 * 删除调拨单
	 * @param netIdList
	 */
	public void deleteMoveCmd(List<String> headidList);
	
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MoveCmdLine> getMoveCmdLine(String headid);
	/**
	 * 获取调拨单头
	 * @param headid
	 * @return
	 */
	public MoveCmdHead getMoveCmdHead(String headid);
	
	/**
	 * 作废
	 * @param headid
	 * @param userId
	 */
	void invalidMoveCmd(String headid, String userId);
}
