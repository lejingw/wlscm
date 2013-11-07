package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.common.constant.StockConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.PushDispatchDao;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchStatistics;
import com.jatools.vo.stock.PushDispatchTemp;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.util.DateUtil;

public class PushDispatchDaoImpl extends BaseDao implements PushDispatchDao {

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
		executeInsert("PushDispatch.createDispatchSubtemp", condition);
	}
	/**
	 * 获取减库生成的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData(String gheadid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		return executeQueryForList("PushDispatch.getSubPurGatherLineData", map);
	}
	/**
	 * 修改总部汇总状态
	 * @param gheadid
	 * @param status
	 * @param userid
	 */
	public void updateHqGatherHeadStatus(String gheadid, String status, String dotype, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("dotype", dotype);
		condition.put("status", status);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("PushDispatch.updateHqGatherHeadStatus", condition);
	}
	/**
	 * 不减库生成采购总单时，获取采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getUnsubPurGatherLineData(String gheadid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		return executeQueryForList("PushDispatch.getUnsubPurGatherLineData", map);
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
		executeInsert("PushDispatch.updateDispatchOrnaDispatchFlagToManualSub", condition);
	}

	/**
	 * 从配货记录表中获取手工减库产生的采购总单行数据
	 * @param gheadid
	 * @return
	 */
	public List<PurGatherLine> getSubPurGatherLineData2(String gheadid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("enableOrderStart", ParameterCache.getInstance().getValue(ParameterConstant.ENABLE_PURCHASE_NUM_LIMIT));
		return executeQueryForList("PushDispatch.getSubPurGatherLineData2", map);
	}
	/**
	 * 获取要货总单状态
	 */
	public String getHeadquarterStatus(String gheadid){
		return (String) executeQueryForObject("PushDispatch.getHeadquarterStatus", gheadid);
	}
	/**
	 * 获取总部总单行中的所有大类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineItemClassForSlt(String gheadid){
		return executeQueryForList("PushDispatch.getGatherLineItemClassForSlt", gheadid);
	}
	
	/**
	 * 根据大类 获取总部总单行中的所有小类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineOrnaClassForSlt(String gheadid, String itemClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("itemClassId", itemClassId);
		return executeQueryForList("PushDispatch.getGatherLineOrnaClassForSlt", condition);
	}
	/**
	 * 获取正配货分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchingOrg(String conditionId){
		return executeQueryForList("PushDispatch.getDispatchingOrg", conditionId);
	}
	/**
	 * 获取配货参数下的所有分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchOrg(String conditionId){
		return executeQueryForList("PushDispatch.getDispatchOrg", conditionId);
	}
	/**
	 * 根据配货参数id,获取正配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchingPageData(Map<String, String> condition){
		return executeQueryForPager("PushDispatch.getDispatchingPageData", "PushDispatch.getDispatchingTotalCount", condition);
	}
	/**
	 * 获取已配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchedPageData(Map<String, String> condition){
		return executeQueryForPager("PushDispatch.getDispatchedPageData", "PushDispatch.getDispatchedTotalCount", condition);
	}

	/**
	 * 根据总部总单id，获取对应所有的门店
	 * @param gheadid 总部总单id
	 * @return
	 */
	public List<Org> getDispatchedOrg(String gheadid){
		return executeQueryForList("PushDispatch.getDispatchedOrg", gheadid);
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
		return executeQueryForList("PushDispatch.getPurchaseGatherItemClass", condition);
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
		return executeQueryForList("PushDispatch.getPurchaseGatherOrnaClass", condition);
	}

	/**
	 * 根据总部总单id，获取总部总单行分页数据
	 * @param condition
	 * @return
	 */
	public Pager getHqlinePageData(Map<String, String> condition){
		return executeQueryForPager("PushDispatch.getHqlinePageData", "PushDispatch.getHqlineTotalCount", condition);
	}

	public Pager getHqlinePageData2(Map<String, String> condition){
		return executeQueryForPager("PushDispatch.getHqlinePageData2", "PushDispatch.getHqlineTotalCount2", condition);
	}
	/**
	 * 总部总单，获取所有大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getHqlineItemClass(String gheadid){
		return executeQueryForList("PushDispatch.getHqlineItemClass", gheadid);
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
		return executeQueryForList("PushDispatch.getHqlineOrnaClass", condition);
	}
	/**
	 * 导出总部总单行
	 * @return
	 */
	public List<Map> getHqlineForExcel(Map<String, String> condition){
		return executeQueryForList("PushDispatch.getHqlineForExcel", condition);
	}
	/**
	 * 生成调拨单获取大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getMoveItemClass(String gheadid){
		return executeQueryForList("PushDispatch.getMoveItemClass", gheadid);
	}
	/**
	 * 生成调拨单获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getMoveOrnaClass(String gheadid){
		return executeQueryForList("PushDispatch.getMoveOrnaClass", gheadid);
	}
	/**
	 * 获取调拨单分配店
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveOrgForSlt(String gheadid){
		return executeQueryForList("PushDispatch.getMoveOrgForSlt", gheadid);
	}
	/**
	 * 获取调拨单配货员
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveDispatchUserForSlt(String gheadid){
		return executeQueryForList("PushDispatch.getMoveDispatchUserForSlt", gheadid);
	}
	/**
	 * 未生成调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getUnmovebill(String gheadid){
		return executeQueryForList("PushDispatch.getUnmovebill", gheadid);
	}
	/**
	 * 调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getMovebill(String gheadid){
		return executeQueryForList("PushDispatch.getMovebill", gheadid);
	}
	/**
	 * 获取要货汇总单单号
	 * @param gheadid
	 * @return
	 */
	public String getGatherHeadBillno(String gheadid){
		return (String) executeQueryForObject("PushDispatch.getGatherHeadBillno", gheadid);
	}
	/**
	 * 获取要生成调拨单的饰品编码
	 * @param gheadid
	 * @param orgId
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
	 * 根据sql获取匹配的临时数据
	 * @param sql
	 */
	public List<PushDispatchTemp> getMatchDispatchTemp(String sql){
		return executeQueryForList("PushDispatch.getMatchDispatchTemp", sql);
	}/**
	 * 获取统计数据
	 */
	public List<DispatchStatistics> getDispatchStatistics(String gheadid,
			String dispatchingFlag){
		Map<String, String> map = new HashMap<String, String>();
		map.put("gheadid", gheadid);
		map.put("dispatchingFlag", dispatchingFlag);
		return executeQueryForList("PushDispatch.getDispatchStatistics", map);
	}

	public Map<String, String> getPushDispatchData(Map<String, String> data){
		return (Map<String, String>) executeQueryForObject("PushDispatch.getPushDispatchData", data);
	}
	/**
	 *  正配货变更
	 */
	public void exchangeDispatching(String ornaLogId, String newOrgId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("ornaLogId", ornaLogId);
		condition.put("newOrgId", newOrgId);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("PushDispatch.exchangeDispatching", condition);
	}

    @Override
    public Pager getShowDispatchedPageData(Map<String, String> condition) {
        return this.executeQueryForPager("PushDispatch.getShowDispatchedPageData", "PushDispatch.getShowDispatchedTotalCount", condition);
    }

    public List<PushDispatchTemp> getMatchDispatchTemp(Map<String, String> condition){
        return (List<PushDispatchTemp>) executeQueryForList("PushDispatch.getDispatchOrgList", condition);
    }
}
