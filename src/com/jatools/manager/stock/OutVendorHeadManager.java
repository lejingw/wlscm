package com.jatools.manager.stock;

import java.util.Map;

import com.jatools.common.Pager;

public interface OutVendorHeadManager {

	
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getOutVendorHeadData(Map<String, String> condition);
}
