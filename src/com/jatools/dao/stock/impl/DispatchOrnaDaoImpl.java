package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.common.constant.StockConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.DispatchOrnaDao;
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
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class DispatchOrnaDaoImpl extends BaseDao implements DispatchOrnaDao {
	/**
	 * 订单配货，总部总单列表
	 * @param condition
	 * @return
	 */
	public Pager getGatherHeadPageData(Map<String, String> condition){
		return executeQueryForPager("DispatchOrna.getGatherHeadPageData", "DispatchOrna.getGatherHeadTotalCount", condition);
	}
	
	/**
	 * 获取配货参数
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	public List<DispatchCondition> getDispatchCondition(String gheadid, String srcBillCode){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("srcBillCode", srcBillCode);
		return executeQueryForList("DispatchOrna.getDispatchCondition", condition);
	}
	

	/**
	 * 订单配货页面，获取总部总单部分数据
	 * @param gheadid
	 * @return
	 */
	public GatherHead getDispatchGatherHead(String gheadid){
		return (GatherHead) executeQueryForObject("DispatchOrna.getDispatchGatherHead", gheadid);
	}
	

	/**
	 * 根据id，获取配货参数
	 * @param conditionId
	 * @return
	 */
	public DispatchCondition getDispatchConditionById(String conditionId){
		return (DispatchCondition) executeQueryForObject("DispatchOrna.getDispatchConditionById", conditionId);
	}
	

	/**
	 * 获取总部总单行中的所有大类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineItemClassForSlt(String gheadid){
		return executeQueryForList("DispatchOrna.getGatherLineItemClassForSlt", gheadid);
	}
	
	/**
	 * 根据大类
	 * 获取总部总单行中的所有小类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineOrnaClassForSlt(String gheadid, String itemClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("itemClassId", itemClassId);
		return executeQueryForList("DispatchOrna.getGatherLineOrnaClassForSlt", condition);
	}

	/**
	 * 检查配货参数是否已经存在
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public boolean checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId, String srcBillCode){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("srcBillCode", srcBillCode);
		Integer count = (Integer)executeQueryForObject("DispatchOrna.checkDispatchCondition", condition);
		if(count>0)
			return false;
		return true;
	}
	/**
	 * 创建配货参数
	 * @param disCondition
	 */
	public String saveDispatchCondition(DispatchCondition disCondition){
		return (String)executeInsert("DispatchOrna.saveDispatchCondition", disCondition);
	}
	
	/**
	 * 从减库临时数据中获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getSubtempDispatchOrnaInfo(String conditionId, String code, boolean ornaFlag, String srcBillCode){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("conditionId", conditionId);
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		condition.put("srcBillCode", srcBillCode);
		return (DispatchOrnaInfo)executeQueryForObject("DispatchOrna.getSubtempDispatchOrnaInfo", condition);
	}
	/**
	 * 获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getDispatchOrnaInfo(String code, boolean ornaFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		return (DispatchOrnaInfo)executeQueryForObject("DispatchOrna.getDispatchOrnaInfo", condition);
	}
	
	/**
	 * 获取配货记录信息
	 * @param ornaCode
	 * @return
	 */
	public DispatchOrnaLog getDispatchOrnaLogById(String ornaLogId){
		return (DispatchOrnaLog)executeQueryForObject("DispatchOrna.getDispatchOrnaLogById", ornaLogId);
	}
	/**
	 * 获取配货记录信息
	 * @param ornaCode
	 * @return
	 */
	public DispatchOrnaLog getDispatchOrnaLogByOrnaCode(String ornaCode, String srcBillCode){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ornaCode", ornaCode);
		map.put("srcBillCode", srcBillCode);
		return (DispatchOrnaLog)executeQueryForObject("DispatchOrna.getDispatchOrnaLogByOrnaCode", map);
	}
	/**
	 * 获取配货参数下的所有分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchOrg(String conditionId){
		return executeQueryForList("DispatchOrna.getDispatchOrg", conditionId);
	}
	/**
	 * 生成配货临时数据
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @param userid
	 */
	public void createDispatchTempData(String gheadid, String itemClassId, String ornaClassId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("userid", userid);
		executeInsert("DispatchOrna.createDispatchTempData", condition);
	}
	/**
	 * 根据sql获取匹配的临时数据
	 * @param sql
	 */
	public List<DispatchTemp> getMatchDispatchTemp(String sql){
		return executeQueryForList("DispatchOrna.getMatchDispatchTemp", sql);
	}
	/**
	 * 保存配货记录
	 * @param dispatchOrnaLog
	 */
	public String saveDispatchOrnaLog(DispatchOrnaLog dispatchOrnaLog){
		return (String)executeInsert("DispatchOrna.saveDispatchOrnaLog", dispatchOrnaLog);
	}
	/**
	 * 根据配货参数id,获取正配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchingPageData(Map<String, String> condition){
		return executeQueryForPager("DispatchOrna.getDispatchingPageData", "DispatchOrna.getDispatchingTotalCount", condition);
	}
	/**
	 * 根据总部总单id，获取门店已配货分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchedPageData(Map<String, String> condition){
		return executeQueryForPager("DispatchOrna.getDispatchedPageData", "DispatchOrna.getDispatchedTotalCount", condition);
	}
	/**
	 * 根据总部总单id，获取对应所有的门店
	 * @param gheadid 总部总单id
	 * @return
	 */
	public List<Org> getPurchaseGatherOrg(String gheadid){
		return executeQueryForList("DispatchOrna.getPurchaseGatherOrg", gheadid);
	}
	/**
	 * 已配货，获取大类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @return
	 */
	public List<ItemClass> getPurchaseGatherItemClass(String gheadid, String orgId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("orgId", orgId);
		return executeQueryForList("DispatchOrna.getPurchaseGatherItemClass", condition);
	}
	/**
	 * 已配货，获取小类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @param itemClassId 大类id
	 * @return
	 */
	public List<OrnaClass> getPurchaseGatherOrnaClass(String gheadid, String orgId, String itemClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("orgId", orgId);
		condition.put("itemClassId", itemClassId);
		return executeQueryForList("DispatchOrna.getPurchaseGatherOrnaClass", condition);
	}

	/**
	 * 根据总部总单id，获取总部总单行分页数据
	 * @param conditionId
	 * @param orgId
	 * @param showUnfullOnly
	 * @return
	 */
	public Pager getHqlinePageData(Map<String, String> condition){
		return executeQueryForPager("DispatchOrna.getHqlinePageData", "DispatchOrna.getHqlineTotalCount", condition);
	}
	/**
	 * 总部总单，获取所有大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getHqlineItemClass(String gheadid){
		return executeQueryForList("DispatchOrna.getHqlineItemClass", gheadid);
	}
	/**
	 * 总部总单，根据大类获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getHqlineOrnaClass(String gheadid, String itemClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("itemClassId", itemClassId);
		return executeQueryForList("DispatchOrna.getHqlineOrnaClass", condition);
	}
	/**
	 * 获取正分配饰品信息
	 * @param condition
	 * @return
	 */
	public Pager getShowDispatchingPageData(Map<String, String> condition){
		return executeQueryForPager("DispatchOrna.getShowDispatchingPageData", "DispatchOrna.getShowDispatchingTotalCount", condition);
	}

	/**
	 * 获取已分配饰品信息
	 * @param condition
	 * @return
	 */
	public Pager getShowDispatchedPageData(Map<String, String> condition){
		return executeQueryForPager("DispatchOrna.getShowDispatchedPageData", "DispatchOrna.getShowDispatchedTotalCount", condition);
	}
	/**
	 * 根据配货记录id，逻辑删除
	 * @param ornaLogId
	 */
	public void deleteDispatchOrnaLog(String ornaLogId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("id", ornaLogId);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("DispatchOrna.deleteDispatchOrnaLog", condition);
	}
	/**
	 * 删除已配记录时，调整总部总单行汇总的已配数量段
	 * @param ornaLogId
	public void updateGatherLineDispatchNum(String gatherLineId){
		executeUpdate("DispatchOrna.updateGatherLineDispatchNum", gatherLineId);
	}
	 */
	/**
	 * 获取正配数量
	 */
	public int getDispatchingNum(String orderLineId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("orderLineId", orderLineId);
		return (Integer)executeQueryForObject("DispatchOrna.getShowDispatchingTotalCount", condition);
	}
	/**
	 * 获取已配数量
	 */
	public int getDispatchedNum(String useOrderLineIdFlag, String lineId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("useOrderLineIdFlag", useOrderLineIdFlag);
		condition.put("lineId", lineId);
		return (Integer)executeQueryForObject("DispatchOrna.getShowDispatchedTotalCount", condition);
	}
	/**
	 * 将保存状态的饰品记录改为记账状态
	 * @param conditionId
	 * @param userid
	 */
	public void markDispatchOrnaLog(String conditionId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		condition.put("conditionId", conditionId);
		executeUpdate("DispatchOrna.markDispatchOrnaLog", condition);
	}
	/**
	 * 根据配货参数id更新总部总单行的已分配数量
	public void updateGatherLineDispatchNumByConditionId(String conditionId){
		executeUpdate("DispatchOrna.updateGatherLineDispatchNumByConditionId", conditionId);
	}
	 */
	/**
	 * 根据配货参数id，删除临时数据
	 * @param conditionId
	 */
	public void deleteDispatchTempData(String conditionId){
		executeUpdate("DispatchOrna.deleteDispatchTempData", conditionId);
	}
	/**
	 * 删除配货参数
	 * @param conditionId
	 */
	public void deleteDispatchCondition(String conditionId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		condition.put("conditionId", conditionId);
		executeUpdate("DispatchOrna.deleteDispatchCondition", condition);
	}
	

	/**
	 *  正配货变更
	 * @param ornaLogId
	 * @param newOrderLineId
	 * @param userid
	 */
	public void exchangeDispatching(String ornaLogId, String newOrderLineId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("ornaLogId", ornaLogId);
		condition.put("newOrderLineId", newOrderLineId);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("DispatchOrna.exchangeDispatching", condition);
	}
	
	/**
	 * 生成减库配货临时数据
	 * @param gheadid
	 * @param userid
	 */
	public void createDispatchSubtemp(String gheadid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("createId", userid);
		condition.put("createDate", DateUtil.getCurrentDate18());
		condition.put("dispatchBeyondDays", ParameterCache.getInstance().getValue(ParameterConstant.PUR_DISPATCH_SUB_BEYONDDAYS));
        condition.put("priceRate", BdCommon.getDispatchPriceRate());
		executeInsert("DispatchOrna.createDispatchSubtemp", condition);
	}
	/**
	 * 更新匹配减库记录临时表中的配货记录id段
	 * @param subtempId
	 * @param ornaLogId
	 */
	public void updateSubtempOrna(String subtempId, String ornaLogId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("subtempId", subtempId);
		condition.put("ornaLogId", ornaLogId);
		executeInsert("DispatchOrna.updateSubtempOrna", condition);
	}
	/**
	 * 根据总部汇总单头id，获取采购总单头id，若未生成采购单，则返回null
	 * @param gheadid
	 * @return
	 */
	public String getPurGatherHeadId(String gheadid){
		return (String) executeQueryForObject("DispatchOrna.getPurGatherHeadId", gheadid);
	}
	/**
	 * 更新更新人和更新时间
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void updateUpdateIdAndDate(String purGatherHeadId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("purGatherHeadId", purGatherHeadId);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("DispatchOrna.updateUpdateIdAndDate", condition);
	}
	/**
	 * 更新已有的采购总单行的采购数量
	 * @param purGatherHeadId
	 */
	public void updatePurGatherNumOrderFMX(String purGatherHeadId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("purGatherHeadId", purGatherHeadId);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		condition.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		executeUpdate("DispatchOrna.updatePurGatherNumOrderFMX", condition);
	}

	/**
	 * 创建新的采购总单行(非明细中新添加的行记录)
	 * @param gheadid
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void savePurGatherLineFMX(String gheadid, String purGatherHeadId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("purGatherHeadId", purGatherHeadId);
		condition.put("createId", userid);
		condition.put("createDate", DateUtil.getCurrentDate18());
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		condition.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		executeInsert("DispatchOrna.savePurGatherLineFMX", condition);
	}
	/**
	 * 获取新的供应商(非明细中新添加的行记录)
	 * @param purGatherHeadId
	 */
	public List<String> getVendorIdListFMX(String purGatherHeadId){
		return executeQueryForList("DispatchOrna.getVendorIdListFMX", purGatherHeadId);
	}
	/**
	 * 更新已有的采购单行的采购数量和当前增量(非明细中新添加的行记录)
	 * @param purGatherHeadId
	 */
	public void updatePurchaseLineNumOrderFMX(String purGatherHeadId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("purGatherHeadId", purGatherHeadId);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		condition.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		executeUpdate("DispatchOrna.updatePurchaseLineNumOrderFMX", condition);
	}
	/**
	 * 创建新的采购单行(非明细中新添加的行记录)
	 * @param purGatherHeadId
	 * @param userid
	 */
	public void savePurchaseLineFMX(String purGatherHeadId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("purGatherHeadId", purGatherHeadId);
		condition.put("createId", userid);
		condition.put("createDate", DateUtil.getCurrentDate18());
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("DispatchOrna.savePurchaseLineFMX", condition);
	}
	/**
	 * 生成调拨单获取大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getMoveItemClass(String gheadid){
		return executeQueryForList("DispatchOrna.getMoveItemClass", gheadid);
	}
	/**
	 * 生成调拨单获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getMoveOrnaClass(String gheadid){
		return executeQueryForList("DispatchOrna.getMoveOrnaClass", gheadid);
	}
	/**
	 * 获取调拨单分配店
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveOrgForSlt(String gheadid){
		return executeQueryForList("DispatchOrna.getMoveOrgForSlt", gheadid);
	}
	/**
	 * 获取调拨单配货员
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveDispatchUserForSlt(String gheadid){
		return executeQueryForList("DispatchOrna.getMoveDispatchUserForSlt", gheadid);
	}
	/**
	 * 未生成调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getUnmovebill(String gheadid){
		return executeQueryForList("DispatchOrna.getUnmovebill", gheadid);
	}
	/**
	 * 调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getMovebill(String gheadid){
		return executeQueryForList("DispatchOrna.getMovebill", gheadid);
	}
	/**
	 * 获取真实总成本
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @return
	 */
	public List<DispatchRealCost> getDispatchRealCost(Map<String, String> condition){
		return executeQueryForList("DispatchOrna.getDispatchRealCost", condition);
	}
	/**
	 * 撤销总单时，删除减库临时数据
	 * @param purGatherHeadId
	 */
	public void deleteDispatchSubTempData(String purGatherHeadId){
		delete("DispatchOrna.deleteDispatchSubTempData", purGatherHeadId);
	}
	/**
	 * 修改计划要货总部汇总状态
	 * @param gheadid
	 * @param planstatus
	 * @param userid
	 */
	public void updateHqGatherHeadStatus(String gheadid, String planstatus, String dotype, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("dotype", dotype);
		condition.put("planstatus", planstatus);
		condition.put("userid", userid);
		//调用存储过程
		executeInsert("DispatchOrna.updateHqGatherHeadStatus", condition);
	}

	/**
	 * 删除配货参数id对应的订单配货日志统计表
	 * @param conditionId
	 */
	public void deleteDispatchLogStat(String conditionId){
		delete("DispatchOrna.deleteDispatchLogStat", conditionId);
	}

	/**
	 * 统计配货参数id对应的订单配货日志统计表
	 * @param conditionId
	 * @param userid
	 */
	public void createDispatchLogStat(String conditionId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("conditionId", conditionId);
		condition.put("statUserId", userid);
		condition.put("statDate", DateUtil.getCurrentDate18());
		executeInsert("DispatchOrna.createDispatchLogStat", condition);
	}

	/**
	 * 根据配货记录id更新订单配货日志统计表
	 * @param ornaLogId
	 * @param userid
	 */
	public void updateDispatchLogStatByOrnaLogId(String ornaLogId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("ornaLogId", ornaLogId);
		condition.put("statUserId", userid);
		condition.put("statDate", DateUtil.getCurrentDate18());
		executeInsert("DispatchOrna.updateDispatchLogStatByOrnaLogId", condition);
	}

	/**
	 * 更新配货记录标志为手工减库配货
	 * @param gheadid
	 * @param userid
	 */
	public void updateDispatchOrnaDispatchFlagToManualSub(String gheadid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		condition.put("dispatchFlag", StockConstant.DISPATCH_FLAG_MANUAL_SUB);
		executeInsert("DispatchOrna.updateDispatchOrnaDispatchFlagToManualSub", condition);
	}
	/**
	 * 获取要货汇总单单号
	 * @param gheadid
	 * @return
	 */
	public String getGatherHeadBillno(String gheadid){
		return (String) executeQueryForObject("DispatchOrna.getGatherHeadBillno", gheadid);
	}
	/**
	 * 获取要生成调拨单的饰品编码
	 * @param gheadid
	 * @param inOrgId
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @param dispatchUserIds
	 * @return
	 */
	public List<String> getOrnaClodeFromMove(String gheadid, String orgId, String itemClassIds, String ornaClassIds, String dispatchUserIds){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("orgId", orgId);
		condition.put("itemClassIds", itemClassIds);
		condition.put("ornaClassIds", ornaClassIds);
		condition.put("dispatchUserIds", dispatchUserIds);
		return executeQueryForList("DispatchOrna.getOrnaCodeFromMove", condition);
	}

	/**
	 * 状态改为已调拨3
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @param dispatchUserIds
	 * @param userid
	 */
	public void updateDispatchOrnaStatusToMoved(String gheadid, String orgIds, String itemClassIds, String ornaClassIds, String dispatchUserIds, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("orgIds", orgIds);
		condition.put("itemClassIds", itemClassIds);
		condition.put("ornaClassIds", ornaClassIds);
		condition.put("dispatchUserIds", dispatchUserIds);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("DispatchOrna.updateDispatchOrnaStatusToMoved", condition);
	}
	/**
	 * 获取减库饰品
	 * @param condition
	 * @return
	 */
	public Pager getDispatchSubTempOrnaPageData(Map<String, String> condition){
		return executeQueryForPager("DispatchOrna.getDispatchSubTempOrnaPageData", "DispatchOrna.getDispatchSubTempOrnaTotalCount", condition);
	}
	/**
	 * 总部总单 匹配率
	 * @return
	 */
	public String getHqlineDispatchRate(String gheadid, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("showUnfullOnly", showUnfullOnly);
		condition.put("showUnperfectMatch", showUnperfectMatch);
		return (String) executeQueryForObject("DispatchOrna.getHqlineDispatchRate", condition);
	}
	/**
	 * 已配货 匹配率
	 * @return
	 */
	public String getDispatchedDispatchRate(String gheadid, String orgId, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("orgId", orgId);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("showUnfullOnly", showUnfullOnly);
		condition.put("showUnperfectMatch", showUnperfectMatch);
		return (String) executeQueryForObject("DispatchOrna.getDispatchedDispatchRate", condition);
	}
	/**
	 * 获取要货总单状态
	 */
	public String getHeadquarterStatus(String gheadid){
		return (String) executeQueryForObject("DispatchOrna.getHeadquarterStatus", gheadid);
	}
	/**
	 * 导出总部总单行
	 * @return
	 */
	public List<Map> getHqlineForExcel(Map<String, String> condition){
		return executeQueryForList("DispatchOrna.getHqlineForExcel", condition);
	}
}