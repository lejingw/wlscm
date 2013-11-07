package com.jatools.dao.basic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.SafeStockDao;
import com.jatools.vo.basic.SafeStock;

public class SafeStockDaoImpl extends BaseDao implements SafeStockDao {

	@Override
	public Pager getSafeStockPageData(Map<String, String> condition) {
		return executeQueryForPager("SafeStock.getSafeStockPageData", "SafeStock.getSafeStockTotalCount", condition);
	}

	public SafeStock getSafeStock(String safeId){
		return (SafeStock) executeQueryForObject("SafeStock.getSafeStock", safeId);
	}
	public Integer getSafeStockCount(String safeId, String itemClassId, String ornaClassId, String analysisId, String styleId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("safeId", safeId);
		map.put("itemClassId", itemClassId);
		map.put("ornaClassId", ornaClassId);
		map.put("analysisId", analysisId);
		map.put("styleId", styleId);
		return (Integer) executeQueryForObject("SafeStock.getSafeStockCount", map);
	}

	public void updateSafeStock(SafeStock stock){
		executeUpdate("SafeStock.updateSafeStock", stock);
	}

	public void saveSafeStock(SafeStock stock){
		executeInsert("SafeStock.saveSafeStock", stock);
	}

	public void deleteSafeStock(String safeId){
		delete("SafeStock.deleteSafeStock", safeId);
	}
	
	public List<SafeStock> queryBatchCreateSafeStock(Map<String, String> condition){
		return executeQueryForList("SafeStock.queryBatchCreateSafeStock", condition);
	}

	public void deleteBatchSafeStock(List<SafeStock> stockList){
		executeBatchDelete("SafeStock.deleteBatchSafeStock", stockList);
	}

	public void saveBatchSafeStock(List<SafeStock> stockList){
		executeBatchInsert("SafeStock.saveSafeStock", stockList);
	}
}
