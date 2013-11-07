package com.jatools.manager.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.SafeStock;

public interface SafeStockManager {

	public Pager getSafeStockPageData(Map<String, String> condition);

	public SafeStock getSafeStock(String safeId);

	public boolean checkSafeStock(String safeId, String itemClassId, String ornaClassId, String analysisId, String styleId);

	public void saveOrUpdateSafeStock(SafeStock stock, String userid);

	public void deleteSafeStock(String safeId);

	public List<SafeStock> queryBatchCreateSafeStock(Map<String, String> condition);

	public void saveBatchSafeStock(List<SafeStock> stockList, String userid);
}
