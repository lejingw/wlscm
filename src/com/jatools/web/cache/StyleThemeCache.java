package com.jatools.web.cache;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class StyleThemeCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(StyleThemeCache.class);
	private static StyleThemeCache styleThemeCache = null;
	private static Map<String, String> styleThemeMap = null;
	private static Date refreshTime = null;

	private StyleThemeCache() {
	}

	public synchronized static StyleThemeCache getInstance() {
		if (null == StyleThemeCache.styleThemeCache) {
			init();
		}
		return StyleThemeCache.styleThemeCache;
	}

	public synchronized static void refresh(){
		styleThemeCache = null;
		styleThemeMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == StyleThemeCache.styleThemeCache) {
			logger.debug("初始化主题款缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					StyleThemeCache.styleThemeMap = new LinkedHashMap<String, String>();
					List<SelectorOption> styleThemeList = bdCommonManager.getAllStyleTheme();
					for (SelectorOption item : styleThemeList) {
						StyleThemeCache.styleThemeMap.put(item.getValue(), item.getText());
					}
				}
				StyleThemeCache.styleThemeCache = new StyleThemeCache();
			} catch (Exception e) {
				logger.error("初始化主题款缓存数据失败");
				throw new RuntimeException("初始化主题款缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != StyleThemeCache.styleThemeCache) {
			StyleThemeCache.styleThemeCache = null;
		}
		if (null != StyleThemeCache.styleThemeMap) {
			StyleThemeCache.styleThemeMap.clear();
		}
	}
	/**
	 * 获取所有主题款
	 */
	public Map<String, String> getAllStyleTheme() {
		return styleThemeMap;
	}
	
	/**
	 * 根据主题款ids获取主题款名称
	 * @param styleThemeIds
	 * @return
	 */
	public String getStyleThemeName(String styleThemeId){
		if(null == styleThemeId)	return null;
		return styleThemeMap.get(styleThemeId);
	}
	
	public String getStyleThemeNames(String styleThemeIds){
		if(null == styleThemeIds)	return null;
		String[] tmpArr = styleThemeIds.split(",");
		String tmpStr = "$";
		for(String tmp : tmpArr){
			if(StringUtil.isNotBlank(styleThemeMap.get(tmp))){
				tmpStr += "," + styleThemeMap.get(tmp);
			}
		}
		if("$".equals(tmpStr))	return null;
		return tmpStr.substring(2);
	}
	
	public String getNameById(String id){
		return getStyleThemeName(id);
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
