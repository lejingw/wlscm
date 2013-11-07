package com.jatools.web.cache;

public class StyleOrnaClassCache implements CacheSingletonIntf {

	private static StyleOrnaClassCache cache = null;

	private StyleOrnaClassCache() {
	}

	public synchronized static StyleOrnaClassCache getInstance() {
		if (null == cache) {
			cache = new StyleOrnaClassCache();
		}
		return cache;
	}
	/**
	 * 根据款式大类ids获取款式大类名称，用逗号隔开
	 * @param styleOrnaClassIds
	 * @return
	 */
	public String getStyleOrnaClassDescByIds(String styleOrnaClassIds){
		return StyleClassCache.getInstance().getStyleOrnaClassDescByIds(styleOrnaClassIds);
	}

	@Override
	public String getNameById(String id) {
		return StyleClassCache.getInstance().getStyleOrnaClassDescByIds(id);
	}
	public String getRefreshTime() {
		return StyleClassCache.getInstance().getRefreshTime();
	}
}
