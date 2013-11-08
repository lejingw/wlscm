package com.jatools.dao.move;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.vo.stock.MaterActive;

public interface MoveBillDao {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pager getMoveBillPageData(Map<String, String> condition, String orgId, String userid);

	/**
	 * 获取现有量表信息
	 * @param ornaCode
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMaterActiveInfo(String ornaCode);
	
	
	
	
	
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
	public String saveMoveBillHead(MoveBillHead moveHead, String userid);
	/**
	 * 修改调拨单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public void updateMoveBillHead(MoveBillHead moveHead, String userid);
	/**
	 * 保存调拨单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void saveMoveBillLine(List<String> newOrnaCodeList, String headid, String billType, String jmFlag, String inOrgId, String userid);
	/**
	 * 删除调拨单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void deleteMoveBillLineByOrnaCode(List<String> deleteOrnaCodeList, String headid, String userid);
	/**
	 * 更新调拨单头合计数据
	 * @param headid
	 * @param userid
	 */
	public void updateMoveBillSumNum(String headid, String userid);
	/**
	 * 删除调拨单行
	 * @param headid
	 */
	public void deleteMoveBillLine(String headid);
	/**
	 * 删除调拨单头
	 * @param headid
	 */
	public void deleteMoveBillHead(String headid);
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
	 * 修改调拨单状态
	 * @param billid
	 * @param userid
	 * @param status
	 */
	public void updateMoveBillStatus(String billid, String userid, String status);
	/**
	 * 接收调拨单
	 * @param headid
	 * @param userid
	 */
	public void receiveMoveBillByPackId(String headid, String jmFlag, String userid);

	/**
	 * 检查是否配置调拨网络
	 * @param outOrgId
	 * @param inOrgId
	 * @return
	 */
	public Long getMoveNetCount(String outOrgId, String inOrgId);
	/**
	 * 移库单、柜组调拨单接收
	 * @param billid
	 * @param userid
	 */
	public void receiveMoveBillHead(String billid, String userid);
	/**
	 * 移库单、柜组调拨单接收
	 * @param billid
	 * @param userid
	 */
	public void receiveMoveBillLine(String billid, String userid);
	/**
	 * 关闭调拨单
	 * @param headid
	 * @param userid
	 */
	public void closeMoveBill(String headid, String userid);

	/**
	 * 来自订单配货的调拨，改配货饰品状态为已到店
	 * @param headid
	 * @param userid
	 */
	public void updateDispatchOrnaStatus(String headid, String userid);
	/**
	 * 获取调拨统计数据
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getMoveBillLineStatForPrint(String headid);
	/**
	 * 获取饰品信息打印标签
	 * @param headid
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String headid, String ornaCode);
	
	void updateNorececount(String headid, String lineid, String userId);

	/**
	 * 生成加盟销售结算单
	 * @param headid
	 */
	public String generateSaleEstimate(String headid, String userid);
	/**
	 * 生成加盟退货结算单
	 * @param headid
	 */
	public String generateReturnEstimate(String headid, String billType, String userid);
	/**
	 * 生成红字加盟销售结算单
	 * @param headid
	 * @param userid
	 */
	public String backRedSaleEstimate(String headid, String userid);

	public int getFreeReturnLeftDays(String ornaCode);
}
