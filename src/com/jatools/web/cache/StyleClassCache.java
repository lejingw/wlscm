package com.jatools.web.cache;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.StyleItemClass;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class StyleClassCache {
	private static Logger logger = Logger.getLogger(OrnaClassCache.class);
	private static StyleClassCache styleClassCache = null;
	private static Map<String, String> styleItemClassMap = null;//款式大类
	private static Map<String, String> styleMiddleClassMap = null;//款式中类
	private static Map<String, String> styleOrnaClassMap = null;//款式小类
	private static Date refreshTime = null;

	private StyleClassCache() {
	}

	public synchronized static StyleClassCache getInstance() {
		if (null == StyleClassCache.styleClassCache) {
			init();
		}
		return StyleClassCache.styleClassCache;
	}

	public synchronized static void refresh(){
		styleClassCache = null;
		styleItemClassMap = null;
		styleMiddleClassMap = null;
		styleOrnaClassMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == StyleClassCache.styleClassCache) {
			logger.debug("初始化小类缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					StyleClassCache.styleItemClassMap = new LinkedHashMap<String, String>();
					List<StyleItemClass> styleItemClassList = bdCommonManager.getAllStyleItemClass();
					for (StyleItemClass item : styleItemClassList) {
						StyleClassCache.styleItemClassMap.put(item.getItemClassId(), item.getItemClassDesc());
					}
					StyleClassCache.styleMiddleClassMap = new LinkedHashMap<String, String>();
					List<StyleMiddleClass> styleMiddleClassList = bdCommonManager.getAllStyleMiddleClass();
					for (StyleMiddleClass item : styleMiddleClassList) {
						StyleClassCache.styleMiddleClassMap.put(item.getMiddleClassId(), item.getMiddleClassDesc());
					}
					StyleClassCache.styleOrnaClassMap = new LinkedHashMap<String, String>();
					List<StyleOrnaClass> styleOrnaClassList = bdCommonManager.getAllStyleOrnaClass();
					for (StyleOrnaClass item : styleOrnaClassList) {
						StyleClassCache.styleOrnaClassMap.put(item.getOrnaClassId(), item.getOrnaClassDesc());
					}
				}
				StyleClassCache.styleClassCache = new StyleClassCache();
			} catch (Exception e) {
				logger.error("初始化小类缓存数据失败");
				throw new RuntimeException("初始化小类缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != StyleClassCache.styleClassCache) {
			StyleClassCache.styleClassCache = null;
		}
		if (null != StyleClassCache.styleItemClassMap) {
			StyleClassCache.styleItemClassMap.clear();
		}
		if (null != StyleClassCache.styleMiddleClassMap) {
			StyleClassCache.styleMiddleClassMap.clear();
		}
		if (null != StyleClassCache.styleOrnaClassMap) {
			StyleClassCache.styleOrnaClassMap.clear();
		}
	}
	/**
	 * 获取所有款式大类
	 */
	public Map<String, String> getAllStyleItemClass() {
		return styleItemClassMap;
	}
	
	public Map<String, String> getAllStyleMiddleClass() {
		return styleMiddleClassMap;
	}
	
	public Map<String, String> getAllStyleOrnaClass() {
		return styleOrnaClassMap;
	}
	
	/**
	 * 根据款式大类ids获取款式大类名称，用逗号隔开
	 * @param styleItemClassIds
	 * @return
	 */
	public String getStyleItemClassDescByIds(String styleItemClassIds){
		if(StringUtil.isBlank(styleItemClassIds))	return null;
		String[] tmpArr = styleItemClassIds.split(",");
		String tmpStr = "$";
		for(String tmp : tmpArr){
			tmpStr += "," + styleItemClassMap.get(tmp);
		}
		if("$".equals(tmpStr))	return null;
		return tmpStr.substring(2);
	}
	/**
	 * 根据款式中类ids获取款式中类名称，用逗号隔开
	 * @param styleItemClassIds
	 * @return
	 */
	public String getStyleMiddleClassDescByIds(String styleMiddleClassIds){
		if(StringUtil.isBlank(styleMiddleClassIds))	return null;
		String[] tmpArr = styleMiddleClassIds.split(",");
		String tmpStr = "$";
		for(String tmp : tmpArr){
			tmpStr += "," + styleMiddleClassMap.get(tmp);
		}
		if("$".equals(tmpStr))	return null;
		return tmpStr.substring(2);
	}
	/**
	 * 根据款式小类ids获取款式小类名称，用逗号隔开
	 * @param styleItemClassIds
	 * @return
	 */
	public String getStyleOrnaClassDescByIds(String styleOrnaClassIds){
		if(StringUtil.isBlank(styleOrnaClassIds))	return null;
		String[] tmpArr = styleOrnaClassIds.split(",");
		String tmpStr = "$";
		for(String tmp : tmpArr){
			tmpStr += "," + styleOrnaClassMap.get(tmp);
		}
		if("$".equals(tmpStr))	return null;
		return tmpStr.substring(2);
	}
	
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
