package com.jatools.dao.basic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.PriceLockDao;
import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.basic.PriceLockLine;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.web.util.DateUtil;

public class PriceLockDaoImpl extends BaseDao implements PriceLockDao, ReviewActionIntf {

	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	public Pager getPriceLockPageData(Map<String, String> condition, String orgId) {
		condition.put("orgId", orgId);
		return executeQueryForPager("PriceLock.getPriceLockPageData", "PriceLock.getPriceLockTotalCount", condition);
	}

	/**
	 * 保存
	 */
	public String savePriceLockHead(PriceLockHead head, String userid){
		head.setCreateId(userid);
		head.setCreateDate(DateUtil.getCurrentDate18());
		head.setUpdateId(userid);
		head.setUpdateDate(DateUtil.getCurrentDate18());
		return (String)executeInsert("PriceLock.savePriceLockHead", head);
	}

	/**
	 * 保存
	 */
	public void savePriceLockLine(List<String> ornaCodeList, String headid, String userid){
		if(null != ornaCodeList && ornaCodeList.size()>0){
			List<Map<String, String>> params = new ArrayList<Map<String, String>>();
			for(String ornaCode: ornaCodeList){
				Map<String, String> map = new HashMap<String, String>();
				map.put("ornaCode", ornaCode);
				map.put("headid", headid);
//				map.put("date", DateUtil.getCurrentDate18());
//				map.put("userid", userid);
				params.add(map);
			}
			executeBatchInsert("PriceLock.savePriceLockLine", params);
		}
	}

	/**
	 * 更新
	 */
	public void updatePriceLockHead(PriceLockHead head, String userid){
		head.setUpdateId(userid);
		head.setUpdateDate(DateUtil.getCurrentDate18());
		executeUpdate("PriceLock.updatePriceLockHead", head);
	}

	/**
	 * 删除记录
	 * 
	 * @param lockId
	 */
	public void deletePriceLockHead(String headid) {
		delete("PriceLock.deletePriceLockHead", headid);
	}

	/**
	 * 删除记录
	 * 
	 * @param headid
	 */
	public void deletePriceLockLineByHeadid(String headid) {
		delete("PriceLock.deletePriceLockLineByHeadid", headid);
	}

	/**
	 * 删除记录
	 * 
	 * @param headid
	 */
	public void deletePriceLockLineByOrnaCode(List<String> ornaCodeList, String headid){
		if(null != ornaCodeList && ornaCodeList.size()>0){
			List<Map<String, String>> params = new ArrayList<Map<String,String>>();
			for(String ornaCode : ornaCodeList){
				Map<String, String> map = new HashMap<String, String>();
				map.put("ornaCode", ornaCode);
				map.put("headid", headid);
				params.add(map);
			}
			executeBatchDelete("PriceLock.deletePriceLockLineByOrnaCode", params);
		}
	}

	/**
	 * 根据价格锁定head
	 */
	public PriceLockHead getPriceLockHead(String headid) {
		return (PriceLockHead) executeQueryForObject("PriceLock.getPriceLockHead", headid);
	}

	/**
	 * 根据价格锁定line
	 */
	public List<PriceLockLine> getPriceLockLine(String headid) {
		return executeQueryForList("PriceLock.getPriceLockLine", headid);
	}
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public PriceLockLine getMaterActiveInfo(String code, boolean ornaFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		return (PriceLockLine)executeQueryForObject("PriceLock.getMaterActiveInfo", condition);
	}
	/**
	 * 获取价格锁定数据
	 */
	public Pager queryPriceLockPageData(Map<String, String> condition){
		return executeQueryForPager("PriceLock.queryPriceLockPageData", "PriceLock.queryPriceLockTotalCount", condition);
	}

	private void updatePriceLockHeadStatus(String headid, String status, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", status);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("PriceLock.updatePriceLockHeadStatus", condition);
	}
	
	@Override
	public void reviewSuccess(String billid, String userid) {
		updatePriceLockHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
		PriceLockHead head = getPriceLockHead(billid);
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("lockFlag", head.getLockFlag());
		condition.put("headid", billid);
		executeUpdate("MaterActive.updateLockStatusFromPriceLock", condition);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		updatePriceLockHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}
}
