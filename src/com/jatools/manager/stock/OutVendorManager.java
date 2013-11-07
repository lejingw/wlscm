package com.jatools.manager.stock;

import java.util.Map;

import com.jatools.common.Pager;

public interface OutVendorManager {

	
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getOutVendorData(Map<String, String> condition);
}
