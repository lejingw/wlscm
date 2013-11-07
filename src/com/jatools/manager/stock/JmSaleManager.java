package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.JmSaleHead;
import com.jatools.vo.stock.JmSaleLine;

public interface JmSaleManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Pager getJmSaleData(Map<String, String> condition);
	
	void saveOrUpdateJmSaleHead(JmSaleHead JmSaleHead, List<JmSaleLine> lines, String deleteIds, String userId);
	
	void saveAndCheckJmSaleHead(JmSaleHead JmSaleHead, List<JmSaleLine> lines, String deleteIds, String userId);
	
	JmSaleHead getJmSaleHead(String billid);
	
	void deleteJmSaleHead(String billid);
	
	List<JmSaleLine> getLines(String billid);
	
	void closeJmSale(String billid, String userId);
}
