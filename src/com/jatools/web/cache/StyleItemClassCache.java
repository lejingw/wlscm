package com.jatools.web.cache;

import java.util.Date;

import com.jatools.web.util.DateUtil;

public class StyleItemClassCache implements CacheSingletonIntf {

	private static StyleItemClassCache cache = null;

	private StyleItemClassCache() {
	}

	public synchronized static StyleItemClassCache getInstance() {
		if (null == cache) {
			cache = new StyleItemClassCache();
		}
		return cache;
	}
	/**
	 * 根据款式大类ids获取款式大类名称，用逗号隔开
	 * @param styleItemClassIds
	 * @return
	 */
	public String getStyleItemClassDescByIds(String styleItemClassIds){
		return StyleClassCache.getInstance().getStyleItemClassDescByIds(styleItemClassIds);
	}

	@Override
	public String getNameById(String id) {
		return StyleClassCache.getInstance().getStyleItemClassDescByIds(id);
	}
	public String getRefreshTime() {
		return StyleClassCache.getInstance().getRefreshTime();
	}
}
