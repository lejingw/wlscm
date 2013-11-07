package com.jatools.dao.move;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.move.MoveCmdHead;
import com.jatools.vo.move.MoveCmdLine;
import com.jatools.vo.stock.MaterActive;
@SuppressWarnings("rawtypes")
public interface MoveCmdDao {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	
	public Pager getMoveCmdPageData(Map<String, String> condition, String orgId, String userid);

	Pager getMoveCmdPageDataByMoveBill(Map<String, String> condition);
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
	 * 保存调拨单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public String saveMoveCmdHead(MoveCmdHead moveHead, String userid);
	/**
	 * 修改调拨单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public void updateMoveCmdHead(MoveCmdHead moveHead, String userid);
	/**
	 * 保存调拨单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void saveMoveCmdLine(List<String> newOrnaCodeList, String headid, String inOrgId, String userid);
	/**
	 * 删除调拨单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void deleteMoveCmdLineByOrnaCode(List<String> deleteOrnaCodeList, String headid, String userid);
	/**
	 * 更新调拨单头合计数据
	 * @param headid
	 * @param userid
	 */
	public void updateMoveCmdSumNum(String headid, String userid);
	/**
	 * 删除调拨单行
	 * @param headid
	 */
	public void deleteMoveCmdLine(String headid);
	/**
	 * 删除调拨单头
	 * @param headid
	 */
	public void deleteMoveCmdHead(String headid);
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
	 * 修改调拨单状态
	 * @param billid
	 * @param userid
	 * @param status
	 */
	public void updateMoveCmdStatus(String headid, String userid, String status);
	
	/**
	 * 关闭调拨单
	 * @param headid
	 * @param userid
	 */
	public void closeMoveCmd(String headid, String userid);

	/**
	 * 获取饰品信息打印标签
	 * @param headid
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String headid, String ornaCode);
	
	/**
	 * 查询已经不在调出组织下的饰品编码
	 * @param headid
	 * @return
	 */
	String getInvalidOrgOrnaCode(String headid);
	
	/**
	 * 查询非有效状态饰品编码
	 * @param headid
	 * @return
	 */
	String getInvalidStateOrnaCode(String headid);
	
	void modifyMaterValidByMoveCmd(String headid);
}
