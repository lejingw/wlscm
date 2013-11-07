package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;

public interface MoveBillManager {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveBillPageData(Map<String, String> condition, String billType, String jmFlag, String orgId, String userid);

	/**
	 * 获取现有量表信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMaterActiveInfo(String code, boolean ornaFlag, String orgId, String billType, String jmFlag, String inOrgId);
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
	public void saveMoveBill(MoveBillHead moveHead, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, String userid);
	/**
	 * 删除调拨单
	 * @param netIdList
	 */
	public void deleteMoveBill(List<String> headidList, String userid);
	/**
	 * 生成结算单
	 * @param netIdList
	 */
	public void generateEstimate(List<String> headidList, String billType, String userid);
	/**
	 * 撤销调拨单
	 * @param headidList
	 * @param userid
	 */
	public void cancelMoveBill(List<String> headidList, String userid);
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getMoveBillLine(String headid, String printLabelType);
	/**
	 * 获取调拨单头
	 * @param headid
	 * @return
	 */
	public MoveBillHead getMoveBillHead(String headid);
	/**
	 * 检查是否配置调拨网络
	 * @param outOrgId
	 * @param inOrgId
	 * @return
	 */
	public boolean checkMoveNetAvail(String outOrgId, String inOrgId);

	/**
	 * 移库单、柜组调拨单接收
	 * @param headid
	 * @param userid
	 */
	public void receiveMoveBill(String headid, String userid);
	/**
	 * 获取调拨统计数据
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getMoveBillLineStatForPrint(String headid);
	/**
	 * 打印标签
	 * @param ornaCodeList
	 * @return
	 */
	public List<Tag> getTagInfoForPrintLabels(String headid, String[] ornaCodeList);

//	public String getBillno(String string);
}
