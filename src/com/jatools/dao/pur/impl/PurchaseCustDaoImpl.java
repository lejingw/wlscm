package com.jatools.dao.pur.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.PurchaseCustDao;
import com.jatools.vo.pur.PurchaseCust;
import com.jatools.vo.pur.PurchaseHead;

public class PurchaseCustDaoImpl extends BaseDao implements PurchaseCustDao {
	/**
	 * 获取定做单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseCustPageData(Map<String, String> condition, String jmFlag){
		condition.put("jmFlag", jmFlag);
		return executeQueryForPager("PurchaseCust.getPurchaseCustPageData", "PurchaseCust.getPurchaseCustTotalCount", condition);
	}
	/**
	 * 获取接收定做单列表
	 * @param condition
	 * @return
	 */
	public Pager getCustReceiveListPageData(Map<String, String> condition, String jmFlag, String userid){
		condition.put("jmFlag", jmFlag);
		condition.put("userid", userid);
		return executeQueryForPager("PurchaseCust.getCustReceiveListPageData", "PurchaseCust.getCustReceiveListTotalCount", condition);
	}
	/**
	 * 获取定做单信息
	 * @param custid
	 * @return
	 */
	public PurchaseCust getPurchaseCust(String custid, String jmFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("jmFlag", jmFlag);
		condition.put("custid", custid);
		return (PurchaseCust) executeQueryForObject("PurchaseCust.getPurchaseCust", condition);
	}
	/**
	 * 获取定做单供应商
	 * @param custid
	 * @return
	 */
	public List<PurchaseHead> getPurchaseCustVendorList(String custid, String jmFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("jmFlag", jmFlag);
		condition.put("custid", custid);
		List<PurchaseHead> list =  executeQueryForList("PurchaseCust.getPurchaseCustVendorList", condition);
		return list;
	}
	/**
	 * 更新定做单状态
	 * @param custid
	 * @param userid
	 */
	public void updatePurchaseCustStatus(String custid, String status, String memo, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("custid", custid);
		condition.put("status", status);//planstate
		condition.put("memo", memo);
		condition.put("userid", userid);
		executeInsert("PurchaseCust.updatePurchaseCustStatus", condition);
	}
	/**
	 * 更新加盟定做单状态
	 * @param custid
	 * @param userid
	 */
	public void updateJmPurchaseCustStatus(String custid, String status, String memo, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("custid", custid);
		condition.put("status", status);
		condition.put("memo", memo);
		condition.put("userid", userid);
		executeInsert("PurchaseCust.updateJmPurchaseCustStatus", condition);
	}
}
