package com.jatools.web.cache;

import java.util.Date;

import com.jatools.web.util.DateUtil;

public class StyleMiddleClassCache implements CacheSingletonIntf {

	private static StyleMiddleClassCache cache = null;

	private StyleMiddleClassCache() {
	}

	public synchronized static StyleMiddleClassCache getInstance() {
		if (null == cache) {
			cache = new StyleMiddleClassCache();
		}
		return cache;
	}
	/**
	 * 根据款式大类ids获取款式大类名称，用逗号隔开
	 * @param styleMiddleClassIds
	 * @return
	 */
	public String getStyleMiddleClassDescByIds(String styleMiddleClassIds){
		return StyleClassCache.getInstance().getStyleMiddleClassDescByIds(styleMiddleClassIds);
	}

	@Override
	public String getNameById(String id) {
		return StyleClassCache.getInstance().getStyleMiddleClassDescByIds(id);
	}
	public String getRefreshTime() {
		return StyleClassCache.getInstance().getRefreshTime();
	}
}
