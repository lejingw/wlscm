package com.jatools.dao.move.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.DispatchWayDao;
import com.jatools.vo.move.DispatchWay;
import com.jatools.web.util.DateUtil;

public class DispatchWayDaoImpl extends BaseDao implements DispatchWayDao {


	public Pager getDispatchStylePageData(Map<String, String> condition){
		return executeQueryForPager("DispatchWay.getDispatchStylePageData", "DispatchWay.getDispatchStyleTotalCount", condition);
	}

	public Pager getDispatchPricePageData(Map<String, String> condition){
		return executeQueryForPager("DispatchWay.getDispatchPricePageData", "DispatchWay.getDispatchPriceTotalCount", condition);
	}
	@Override
	public void saveDispatchStyle(DispatchWay dn) {
		executeInsert("DispatchWay.saveDispatchStyle", dn);
	}

	@Override
	public void updateDispatchStyle(DispatchWay dn) {
		executeUpdate("DispatchWay.updateDispatchStyle", dn);
	}
	
	public List<DispatchWay> checkDispatchStyleRepeat(DispatchWay dn) {
		return executeQueryForList("DispatchWay.checkDispatchStyleRepeat", dn);
	}

	/**
	 * 删除
	 */
	public void deleteDispatchStyle(List<String> billIdList){
		executeBatchDelete("DispatchWay.deleteDispatchStyle", billIdList);
	}
	@Override
	public void saveDispatchStyleFromExcel(String seqId, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seqId", seqId);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("DispatchWay.saveDispatchStyleFromExcel", map);
	}
	//-------------------------------------

	@Override
	public void saveDispatchPrice(DispatchWay dn) {
		executeInsert("DispatchWay.saveDispatchPrice", dn);
	}

	@Override
	public void updateDispatchPrice(DispatchWay dn) {
		executeUpdate("DispatchWay.updateDispatchPrice", dn);
	}
	
	public List<DispatchWay> checkDispatchPriceRepeat(DispatchWay dn) {
		return executeQueryForList("DispatchWay.checkDispatchPriceRepeat", dn);
	}

	/**
	 * 删除
	 */
	public void deleteDispatchPrice(List<String> billIdList){
		executeBatchDelete("DispatchWay.deleteDispatchPrice", billIdList);
	}
	@Override
	public void saveDispatchPriceFromExcel(String seqId, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seqId", seqId);
		map.put("userId", userId);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("DispatchWay.saveDispatchPriceFromExcel", map);
	}

    public Pager getDispatchStyleReportPageData(Map<String, String> condition){
        return executeQueryForPager("DispatchWay.getDispatchStyleReportPageData", "DispatchWay.getDispatchStyleReportTotalCount", condition);
    }

    public Pager getDispatchPriceReportPageData(Map<String, String> condition){
        return executeQueryForPager("DispatchWay.getDispatchPriceReportPageData", "DispatchWay.getDispatchPriceReportTotalCount", condition);
    }
}
