package com.jatools.web.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
/**
 * 大类数据缓存
 * @author wanglj
 *
 */
public class ItemClassCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(ItemClassCache.class);
	private static ItemClassCache itemClassCache = null;
	private static Map<String, ItemClass> itemClassMap = null;
	private static Date refreshTime = null;

	private ItemClassCache() {
	}

	public synchronized static ItemClassCache getInstance() {
		if (null == itemClassCache) {
			init();
		}
		return ItemClassCache.itemClassCache;
	}

	public synchronized static void refresh(){
		itemClassCache = null;
		itemClassMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == ItemClassCache.itemClassCache) {
			logger.debug("初始化大类缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					ItemClassCache.itemClassMap = new LinkedHashMap<String, ItemClass>();
					List<ItemClass> list = bdCommonManager.getAllItemClass();
					for(ItemClass itemClass : list){
						ItemClassCache.itemClassMap.put(itemClass.getItemClassId(), itemClass);
					}
				}
				ItemClassCache.itemClassCache = new ItemClassCache();
			} catch (Exception e) {
				logger.error("初始化大类缓存数据失败");
				throw new RuntimeException("初始化大类缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != ItemClassCache.itemClassCache) {
			ItemClassCache.itemClassCache = null;
		}
		if(null != ItemClassCache.itemClassMap){
			ItemClassCache.itemClassMap.clear();
		}
	}
	/**
	 * 根据大类id获取大类名称
	 * @param itemClassId
	 * @return
	 */
	public String getItemClassDesc(String itemClassId){
		ItemClass itemClass = ItemClassCache.itemClassMap.get(itemClassId);
		if(null != itemClass){
			return itemClass.getItemClassDesc();
		}
		return null;
	}
	
	public String getItemClassNames(String itemClassIds){
		if(StringUtil.isBlank(itemClassIds))	return null;
		String[] tmpArr = itemClassIds.split(",");
		String tmpStr = "$";
		for(String tmp : tmpArr){
			tmpStr += "," + itemClassMap.get(tmp).getItemClassDesc();
		}
		if("$".equals(tmpStr))	return null;
		return tmpStr.substring(2);
	}
	
	/**
	 * 根据大类id获取商品类别
	 * @param itemClassId
	 * @return
	 */
	public String getArticleTypeDesc(String itemClassId){
		ItemClass itemClass = ItemClassCache.itemClassMap.get(itemClassId);
		if(null != itemClass){
			return itemClass.getArticleTypeId();
		}
		return null;
	}

	/**
	 * 获取所有大类数据
	 * @return
	 */
	public Map<String, ItemClass> getAllItemClass() {
		return ItemClassCache.itemClassMap;
	}
	
	public Map<String, String> getAllItemClass2() {
		Map<String, String> res = new HashMap<String, String>();
		if(ItemClassCache.itemClassMap.size() > 0){
			for(String key : ItemClassCache.itemClassMap.keySet()){
				res.put(key, ItemClassCache.itemClassMap.get(key).getItemClassDesc());
			}
		}
		return res;
	}
	
	public String getNameById(String id){
		return getItemClassDesc(id);
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
