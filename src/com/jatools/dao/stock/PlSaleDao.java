package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.PlSaleHead;
import com.jatools.vo.stock.PlSaleLine;

public interface PlSaleDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Pager getPlSaleData(Map<String, String> condition);
	/**
	 * 保存
	 * @param PlSale
	 */
	void savePlSaleHead(PlSaleHead PlSaleHead);
	/**
	 * 根据id获取
	 * @param billid
	 * @return
	 */
	PlSaleHead getPlSaleHead(String billid);
	/**
	 * 保存或修改
	 * @param PlSale
	 */
	void updatePlSaleHead(PlSaleHead PlSaleHead);
	
	/**
	 * 修改头表的状态
	 * @param params
	 */
	public void modifyPlSaleHeadStatus(String billid, String status, String userId);
	
	/**
	 * 删除
	 * @param billid
	 */
	void deletePlSaleHead(String billid);

	List<PlSaleLine> getLines(String billid);
	
	void insertLine(PlSaleLine line);
	
	void updateLine(PlSaleLine line);
	
	void deleteLineById(String lineid);
	
	void deleteLineByBillid(String billid);

    boolean isExistsOrnaCode(String billid, String ornaCode);

    boolean isExistsInOtherBill(String billid, String ornaCode);
}
