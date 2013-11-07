package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchRealCost;
import com.jatools.vo.stock.DispatchTemp;
import com.jatools.vo.stock.GatherHead;
import com.jatools.vo.util.SelectorOption;

public interface DispatchOrnaDao {
	/**
	 * 订单配货，总部总单列表
	 * @param conditon
	 * @return
	 */
	public Pager getGatherHeadPageData(Map<String, String> conditon);
	
	/**
	 * 获取配货参数
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	public List<DispatchCondition> getDispatchCondition(String gheadid, String srcBillCode);

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
	public boolean checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId, String srcBillCode);
	/**
	 * 创建配货参数
	 * @param disCondition
	 */
	public String saveDispatchCondition(DispatchCondition disCondition);
	/**
	 * 从减库临时数据中获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getSubtempDispatchOrnaInfo(String conditionId, String code, boolean ornaFlag, String srcBillCode);
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
	public DispatchOrnaLog getDispatchOrnaLogByOrnaCode(String ornaCode, String srcBillCode);
	/**
	 * 获取配货参数下的所有分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchOrg(String conditionId);

	/**
	 * 生成配货临时数据
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @param userid
	 */
	public void createDispatchTempData(String gheadid, String itemClassId, String ornaClassId, String userid);
	/**
	 * 根据sql获取匹配的临时数据
	 * @param sql
	 */
	public List<DispatchTemp> getMatchDispatchTemp(String sql);
	/**
	 * 保存配货记录
	 * @param dispatchOrnaLog
	 */
	public String saveDispatchOrnaLog(DispatchOrnaLog dispatchOrnaLog);
	/**
	 * 根据配货参数id,获取正配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchingPageData(Map<String, String> condition);
	/**
	 * 根据总部总单id，获取门店已配货分页数据
	 * @param condition
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
	 * 删除已配记录时，调整总部总单行汇总的已配数量段
	 * @param ornaLogId
	public void updateGatherLineDispatchNum(String gatherLineId);
	 */
	/**
	 * 获取正配数量
	 */
	public int getDispatchingNum(String orderLineId);
	/**
	 * 获取已配数量
	 */
	public int getDispatchedNum(String useOrderLineIdFlag, String lineId);
	/**
	 * 将保存状态的饰品记录改为记账状态
	 * @param conditionId
	 * @param userid
	 */
	public void markDispatchOrnaLog(String conditionId, String userid);
	/**
	 * 根据配货参数id更新总部总单行的已分配数量
	public void updateGatherLineDispatchNumByConditionId(String conditionId);
	 */
	/**
	 * 根据配货参数id，删除临时数据
	 * @param conditionId
	 */
	public void deleteDispatchTempData(String conditionId);
	/**
	 * 删除配货参数
	 * @param conditionId
	 */
	public void deleteDispatchCondition(String conditionId, String userid);

	/**
	 *  正配货变更
	 * @param ornaLogId
	 * @param newOrderLineId
	 * @param userid
	 */
	public void exchangeDispatching(String ornaLogId, String newOrderLineId, String userid);

	/**
	 * 生成减库配货临时数据
	 * @param gheadid
	 * @param userid
	 */
	public void createDispatchSubtemp(String gheadid, String userid);
	/**
	 * 更新匹配减库记录临时表中的配货记录id段
	 * @param subtempId
	 * @param ornaLogId
	 */
	public void updateSubtempOrna(String subtempId, String ornaLogId);
	/**
	 * 根据总部汇总单头id，获取采购总单头id，若未生成采购单，则返回null
	 * @param gheadid
	 * @return
	 */
	public String getPurGatherHeadId(String gheadid);

	/**
	 * 更新更新人和更新时间
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void updateUpdateIdAndDate(String purGatherHeadId, String userid);

	/**
	 * 更新已有的采购总单行的采购数量
	 * @param purGatherHeadId
	 */
	public void updatePurGatherNumOrderFMX(String purGatherHeadId, String userid);

	/**
	 * 创建新的采购总单行(非明细中新添加的行记录)
	 * @param gheadid
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void savePurGatherLineFMX(String gheadid, String purGatherHeadId, String userid);

	/**
	 * 获取新的供应商(非明细中新添加的行记录)
	 * @param purGatherHeadId
	 */
	public List<String> getVendorIdListFMX(String purGatherHeadId);

	/**
	 * 更新已有的采购单行的采购数量和当前增量(非明细中新添加的行记录)
	 * @param purGatherHeadId
	 */
	public void updatePurchaseLineNumOrderFMX(String purGatherHeadId, String userid);

	/**
	 * 创建新的采购单行(非明细中新添加的行记录)
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void savePurchaseLineFMX(String purGatherHeadId, String userid);
	/**
	 * 生成调拨单获取大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getMoveItemClass(String gheadid);
	/**
	 * 生成调拨单获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getMoveOrnaClass(String gheadid);
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
	public List<DispatchRealCost> getDispatchRealCost(Map<String, String> condition);
	/**
	 * 撤销总单时，删除减库临时数据
	 * @param purGatherHeadId
	 */
	public void deleteDispatchSubTempData(String purGatherHeadId);
	/**
	 * 修改计划要货总部汇总状态
	 * @param gheadid
	 * @param planstatus
	 * @param dotype
	 * @param userid
	 */
	public void updateHqGatherHeadStatus(String gheadid, String planstatus, String dotype, String userid);

	/**
	 * 删除配货参数id对应的订单配货日志统计表
	 * @param conditionId
	 */
	public void deleteDispatchLogStat(String conditionId);

	/**
	 * 统计配货参数id对应的订单配货日志统计表
	 * @param conditionId
	 * @param userid
	 */
	public void createDispatchLogStat(String conditionId, String userid);

	/**
	 * 根据配货记录id更新订单配货日志统计表
	 * @param ornaLogId
	 * @param userid
	 */
	public void updateDispatchLogStatByOrnaLogId(String ornaLogId, String userid);

	/**
	 * 更新配货记录标志为手工减库配货
	 * @param gheadid
	 * @param userid
	 */
	public void updateDispatchOrnaDispatchFlagToManualSub(String gheadid, String userid);
	/**
	 * 获取要货汇总单单号
	 * @param gheadid
	 * @return
	 */
	public String getGatherHeadBillno(String gheadid);
	/**
	 * 获取要生成调拨单的饰品编码
	 * @param gheadid
	 * @param inOrgId
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @param dispatchUserIds
	 * @return
	 */
	public List<String> getOrnaClodeFromMove(String gheadid, String inOrgId, String itemClassIds, String ornaClassIds, String dispatchUserIds);

	/**
	 * 状态改为已调拨3
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @param dispatchUserIds
	 * @param userid
	 */
	public void updateDispatchOrnaStatusToMoved(String gheadid, String orgIds, String itemClassIds, String ornaClassIds, String dispatchUserIds, String userid);
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

	/**
	 * 获取要货总单状态
	 */
	public String getHeadquarterStatus(String gheadid);
	/**
	 * 导出总部总单行
	 * @return
	 */
	public List<Map> getHqlineForExcel(Map<String, String> condition);
}
