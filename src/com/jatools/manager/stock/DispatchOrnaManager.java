package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchRealCost;
import com.jatools.vo.stock.DispatchStatistics;
import com.jatools.vo.stock.DispatchTemp;
import com.jatools.vo.stock.GatherHead;
import com.jatools.vo.util.SelectorOption;

public interface DispatchOrnaManager {
	/**
	 * 订单配货，总部总单列表
	 * @param conditon
	 * @return
	 */
	public Pager getGatherHeadPageData(Map<String, String> conditon);

	/**
	 * 获取配货参数
	 * @param gheadid
	 * @return
	 */
	public List<DispatchCondition> getDispatchCondition(String gheadid);

	/**
	 * 订单配货页面，获取总部总单部分数据
	 * @param gheadid
	 * @return
	 */
	public GatherHead getDispatchGatherHead(String gheadid);

	/**
	 * 根据id，获取配货参数
	 * @param conditionId
	 * @return
	 */
	public DispatchCondition getDispatchConditionById(String conditionId);
	
	/**
	 * 获取总部总单行中的所有大类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineItemClassForSlt(String gheadid);
	
	/**
	 * 根据大类
	 * 获取总部总单行中的所有小类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineOrnaClassForSlt(String gheadid, String itemClassId);
	/**
	 * 检查配货参数是否已经存在
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public boolean checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId);

	/**
	 * 创建配货参数
	 * @param disCondition
	 */
	public String saveDispatchCondition(String gheadid, String itemClassId, String ornaClassId, String userid);
	/**
	 * 从减库临时数据中获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getSubtempDispatchOrnaInfo(String conditionId, String code, boolean ornaFlag);
	/**
	 * 获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getDispatchOrnaInfo(String code, boolean ornaFlag);
	/**
	 * 获取配货记录信息
	 * @param ornaCode
	 * @return
	 */
	public DispatchOrnaLog getDispatchOrnaLogById(String ornaLogId);
	/**
	 * 获取配货记录信息
	 * @param ornaCode
	 * @return
	 */
	public DispatchOrnaLog getDispatchOrnaLogByOrnaCode(String ornaCode);
	/**
	 * 获取配货参数下的所有分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchOrg(String conditionId);

	/**
	 * 根据饰品属性进行配货
	 */
	public List<DispatchTemp> getMatchDispatchTempList(DispatchOrnaInfo info, boolean styleFlag, String styleFlag2, boolean colorGradeFlag, boolean cleanFlag, String inOrgId, String conditionId);

	/**
	 * 根据sql获取匹配的临时数据
	 */
	public DispatchTemp getMatchDispatchTemp(DispatchOrnaInfo info, boolean styleFlag, String styleFlag2, boolean colorGradeFlag, boolean cleanFlag, String inOrgId, String conditionId);

	/**
	 * 保存配货记录
	 * @param dispatchOrnaLog
	 */
	public String saveDispatchOrnaLog(DispatchOrnaLog dispatchOrnaLog);

	/**
	 * 根据配货参数id,获取正配货临时分页数据
	 * @param conditionId
	 * @param orgId
	 * @param showUnfullOnly
	 * @return
	 */
	public Pager getDispatchingPageData(Map<String, String> condition);
	/**
	 * 根据总部总单id，获取门店已配货分页数据
	 * @param conditionId
	 * @param orgId
	 * @param showUnfullOnly
	 * @return
	 */
	public Pager getDispatchedPageData(Map<String, String> condition);

	/**
	 * 根据总部总单id，获取对应所有的门店
	 * @param gheadid
	 * @return
	 */
	public List<Org> getPurchaseGatherOrg(String gheadid);


	/**
	 * 已配货，获取大类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @return
	 */
	public List<ItemClass> getPurchaseGatherItemClass(String gheadid, String orgId);
	/**
	 * 已配货，获取小类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @param itemClassId 大类id
	 * @return
	 */
	public List<OrnaClass> getPurchaseGatherOrnaClass(String gheadid, String orgId, String itemClassId);
	/**
	 * 根据总部总单id，获取总部总单行分页数据
	 * @param conditionId
	 * @param orgId
	 * @param showUnfullOnly
	 * @return
	 */
	public Pager getHqlinePageData(Map<String, String> condition);
	/**
	 * 总部总单，获取所有大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getHqlineItemClass(String gheadid);
	/**
	 * 总部总单，根据大类获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getHqlineOrnaClass(String gheadid, String itemClassId);

	/**
	 * 获取正分配饰品信息
	 * @param condition
	 * @return
	 */
	public Pager getShowDispatchingPageData(Map<String, String> condition);

	/**
	 * 获取已分配饰品信息
	 * @param condition
	 * @return
	 */
	public Pager getShowDispatchedPageData(Map<String, String> condition);

	/**
	 * 根据配货记录id，逻辑删除
	 * @param ornaLogId
	 */
	public void deleteDispatchOrnaLog(String ornaLogId, String userid);
	/**
	 * 获取正配数量
	 */
	public int getDispatchingNum(String orderLineId);
	/**
	 * 获取已配数量
	 */
	public int getDispatchedNum(String useOrderLineIdFlag, String lineId);
	/**
	 * 记账
	 * @param gheadid
	 * @param conditionId
	 */
	public void markBill(String gheadid, String conditionId);

	/**
	 * 正配货变更
	 * @param ornaLogId
	 * @param newOrderLineId
	 * @param userid
	 */
	public void exchangeDispatching(String ornaLogId, String newOrderLineId, String userid);
	/**
	 * 生成调拨单获取大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getMoveItemClass(String gheadid);
	/**
	 * 生成调拨单获取小类(明细要货单)
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getMoveOrnaClass(String gheadid);
	/**
	 * 减库生成采购单(明细)
	 * @param gheadid
	 * @param userid
	 */
	public void subPurchase(String gheadid, String userid);
	/**
	 * 手工减库生成采购单(明细)
	 * @param gheadid
	 * @param userid
	 */
	public void subPurchase2(String gheadid, String userid);

	/**
	 * 不减库生成采购单（明细和非明细均可）
	 * @param gheadid
	 * @param userid
	 */
	public void unsubPurchase(String gheadid, String userid);
	/**
	 * 获取调拨单分配店
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveOrgForSlt(String gheadid);
	/**
	 * 获取调拨单配货员
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveDispatchUserForSlt(String gheadid);
	/**
	 * 未生成调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getUnmovebill(String gheadid);
	/**
	 * 调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getMovebill(String gheadid);
	/**
	 * 获取真实总成本
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @return
	 */
	public List<DispatchRealCost> getDispatchRealCost(String gheadid, String orgIds, String itemClassIds, String ornaClassIds, String srcBillCode);

	/**
	 * 生成调拨单
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @param dispatchUserIds
	 * @param userid
	 */
	public List<MoveBillHead> createMoveBill(String gheadid, String orgIds, String itemClassIds, String ornaClassIds, String dispatchUserIds, String outOrgId, String userid);
	/**
	 * 改总部状态为已到店
	 * @param gheadid
	 * @param userid
	 */
	public int updateHqOrnaStatus(String gheadid, String userid);
	/**
	 * 获取减库饰品
	 * @param condition
	 * @return
	 */
	public Pager getDispatchSubTempOrnaPageData(Map<String, String> condition);
	/**
	 * 总部总单 匹配率
	 * @return
	 */
	public String getHqlineDispatchRate(String gheadid, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch);
	/**
	 * 已配货 匹配率
	 * @return
	 */
	public String getDispatchedDispatchRate(String gheadid, String orgId, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch);

	public String getHeadquarterStatus(String gheadid);
	/**
	 * 导出总部总单行
	 * @return
	 */
	public List<Map> getHqlineForExcel(Map<String, String> condition);
}
