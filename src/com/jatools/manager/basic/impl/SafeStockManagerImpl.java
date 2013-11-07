package com.jatools.manager.basic.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.basic.SafeStockDao;
import com.jatools.manager.basic.SafeStockManager;
import com.jatools.vo.basic.SafeStock;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class SafeStockManagerImpl implements SafeStockManager {
	private SafeStockDao safeStockDao;

	public void setSafeStockDao(SafeStockDao safeStockDao) {
		this.safeStockDao = safeStockDao;
	}

	@Override
	public Pager getSafeStockPageData(Map<String, String> condition) {
		return safeStockDao.getSafeStockPageData(condition);
	}
	public SafeStock getSafeStock(String safeId){
		return safeStockDao.getSafeStock(safeId);
	}


	public boolean checkSafeStock(String safeId, String itemClassId, String ornaClassId, String analysisId, String styleId){
		Integer count = safeStockDao.getSafeStockCount(safeId, itemClassId, ornaClassId, analysisId, styleId);
		return count<1;
	}

	public void saveOrUpdateSafeStock(SafeStock stock, String userid){
		stock.setUpdateDate(DateUtil.getCurrentDate18());
		stock.setUpdateId(userid);
		if(StringUtil.isNotEmpty(stock.getSafeId())){			
			safeStockDao.updateSafeStock(stock);
		}else{
			stock.setCreateDate(DateUtil.getCurrentDate18());
			stock.setCreateId(userid);
			stock.setStatus(DictConstant.BILL_STATUS_SAVE);
			safeStockDao.saveSafeStock(stock);
		}
		return ;
	}
	
	public void deleteSafeStock(String safeIds){
		if(StringUtils.isNotEmpty(safeIds)){
			for(String safeId:safeIds.split(",")){
				if(StringUtils.isNotEmpty(safeId)){
					safeStockDao.deleteSafeStock(safeId);
				}
			}
		}
	}
	public List<SafeStock> queryBatchCreateSafeStock(Map<String, String> condition){
		return safeStockDao.queryBatchCreateSafeStock(condition);
	}
	public void saveBatchSafeStock(List<SafeStock> stockList, String userid){
		for(SafeStock stock : stockList){
			stock.setMemo("批量生成");
			stock.setUpdateDate(DateUtil.getCurrentDate18());
			stock.setUpdateId(userid);
			stock.setCreateDate(DateUtil.getCurrentDate18());
			stock.setCreateId(userid);
			stock.setStatus(DictConstant.BILL_STATUS_SAVE);
		}
		safeStockDao.deleteBatchSafeStock(stockList);
		safeStockDao.saveBatchSafeStock(stockList);
		return ;
	}
}
