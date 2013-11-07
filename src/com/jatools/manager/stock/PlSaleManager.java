package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.PlSaleHead;
import com.jatools.vo.stock.PlSaleLine;

public interface PlSaleManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Pager getPlSaleData(Map<String, String> condition);
	
	void saveOrUpdatePlSaleHead(PlSaleHead PlSaleHead, List<PlSaleLine> lines, String deleteIds, String userId);
	
	void saveAndCheckPlSaleHead(PlSaleHead PlSaleHead, List<PlSaleLine> lines, String deleteIds, String userId);
	
	PlSaleHead getPlSaleHead(String billid);
	
	void deletePlSaleHead(String billid);
	
	List<PlSaleLine> getLines(String billid);
	
	void closePlSale(String billid, String userId);
	void destroyPlSale(String billid, String userId);


    boolean isExistsOrnaCode(String billid, String ornaCode);

    boolean isExistsInOtherBill(String billid, String ornaCode);
}
