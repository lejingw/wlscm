package com.jatools.web.dwr.basic;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.SafeStockManager;
import com.jatools.vo.basic.SafeStock;

public class SafeStockDwr {
	private SafeStockManager safeStockManager;

	public void setSafeStockManager(SafeStockManager safeStockManager) {
		this.safeStockManager = safeStockManager;
	}
	
	public String saveSafeStock(SafeStock stock, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		if(!safeStockManager.checkSafeStock(stock.getSafeId(), stock.getItemClassId(), stock.getOrnaClassId(), stock.getAnalysisId(), stock.getStyleId())){
			return "当前选择的大类、小类、分析范围、款式有相同的记录已经存在";
		}
		safeStockManager.saveOrUpdateSafeStock(stock, userid);
		return null;
	}
	public String saveBatchSafeStock(List<SafeStock> stockList, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		if(null != stockList && stockList.size()>0){
			safeStockManager.saveBatchSafeStock(stockList, userid);
		}
		return null;
	}
	
	public String deleteSafeStock(String safeIds){
		safeStockManager.deleteSafeStock(safeIds);
		return null;
	}
}
