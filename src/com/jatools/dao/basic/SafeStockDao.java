package com.jatools.dao.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.SafeStock;

public interface SafeStockDao {

	public Pager getSafeStockPageData(Map<String, String> condition);

	public SafeStock getSafeStock(String safeId);

	public Integer getSafeStockCount(String safeId, String itemClassId, String ornaClassId, String analysisId, String styleId);

	public void updateSafeStock(SafeStock stock);

	public void saveSafeStock(SafeStock stock);

	public void deleteSafeStock(String safeId);
	
	public List<SafeStock> queryBatchCreateSafeStock(Map<String, String> condition);

	public void deleteBatchSafeStock(List<SafeStock> stockList);

	public void saveBatchSafeStock(List<SafeStock> stockList);
}
