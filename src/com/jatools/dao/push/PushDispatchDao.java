package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchStatistics;
import com.jatools.vo.stock.PushDispatchTemp;
import com.jatools.vo.util.SelectorOption;

public interface PushDispatchDao {

	/**
	 * 生成减库配货临时数据
	 * @param gheadid
	 * @param userid
	 */
	public void createDispatchSubtemp(String gheadid, String userid);

	/**
	 * 获取减库生成的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData(String gheadid);
	/**
	 * 修改计划要货总部汇总状态
	 * @param gheadid
	 * @param status
	 * @param userid
	 */
	public void updateHqGatherHeadStatus(String gheadid, String status, String dotype, String userid);
	/**
	 * 不减库生成采购总单时，获取采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getUnsubPurGatherLineData(String gheadid);

	/**
	 * 更新配货记录标志为手工减库配货
	 * @param gheadid
	 * @param userid
	 */
	public void updateDispatchOrnaDispatchFlagToManualSub(String gheadid, String userid);

	/**
	 * 从配货记录表中获取手工减库产生的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData2(String gheadid);

	/**
	 * 获取要货总单状态
	 */
	public String getHeadquarterStatus(String gheadid);
	/**
	 * 获取总部总单行中的所有大类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineItemClassForSlt(String gheadid);
	/**
	 * 根据大类 获取总部总单行中的所有小类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineOrnaClassForSlt(String gheadid, String itemClassId);

	/**
	 * 获取正配货分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchingOrg(String conditionId);
	/**
	 * 获取配货参数下的所有分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchOrg(String conditionId);
	/**
	 * 根据配货参数id,获取正配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchingPageData(Map<String, String> condition);
	/**
	 * 获取已配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchedPageData(Map<String, String> condition);

	/**
	 * 根据总部总单id，获取对应所有的门店
	 * @param gheadid
	 * @return
	 */
	public List<Org> getDispatchedOrg(String gheadid);

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
	 * @param condition
	 * @return
	 */
	public Pager getHqlinePageData(Map<String, String> condition);
	public Pager getHqlinePageData2(Map<String, String> condition);
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
	 * 导出总部总单行
	 * @return
	 */
	public List<Map> getHqlineForExcel(Map<String, String> condition);
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
	 * 根据sql获取匹配的临时数据
	 * @param sql
	 */
	public List<PushDispatchTemp> getMatchDispatchTemp(String sql);
	public List<PushDispatchTemp> getMatchDispatchTemp(Map<String, String> condition);
	/**
	 * 获取统计数据
	 */
	public List<DispatchStatistics> getDispatchStatistics(String gheadid,
			String dispatchingFlag);

	public Map<String, String> getPushDispatchData(Map<String,String> map);
	/**
	 * 正配货变更
	 * @param ornaLogId
	 * @param newOrderLineId
	 * @param userid
	 */
	public void exchangeDispatching(String ornaLogId, String newOrderLineId,
			String userid);

    Pager getShowDispatchedPageData(Map<String, String> condition);
}
