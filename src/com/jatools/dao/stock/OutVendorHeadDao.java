package com.jatools.dao.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.OutVendorHead;

public interface OutVendorHeadDao {
	
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getOutVendorHeadData(Map<String, String> condition);
	/**
	 * 创建台账记录
	 * @param vendorHead
	 */
	public void insert(OutVendorHead vendorHead);
}
