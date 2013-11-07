package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.JmSaleHead;
import com.jatools.vo.stock.JmSaleLine;

public interface JmSaleDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Pager getJmSaleData(Map<String, String> condition);
	/**
	 * 保存
	 * @param JmSale
	 */
	void saveJmSaleHead(JmSaleHead JmSaleHead);
	/**
	 * 根据id获取
	 * @param billid
	 * @return
	 */
	JmSaleHead getJmSaleHead(String billid);
	/**
	 * 保存或修改
	 * @param JmSale
	 */
	void updateJmSaleHead(JmSaleHead JmSaleHead);
	
	/**
	 * 修改头表的状态
	 * @param params
	 */
	public void modifyJmSaleHeadStatus(String billid, String status, String userId);
	
	/**
	 * 删除
	 * @param billid
	 */
	void deleteJmSaleHead(String billid);

	List<JmSaleLine> getLines(String billid);
	
	void insertLine(JmSaleLine line);
	
	void updateLine(JmSaleLine line);
	
	void deleteLineById(String lineid);
	
	void deleteLineByBillid(String billid);
}
