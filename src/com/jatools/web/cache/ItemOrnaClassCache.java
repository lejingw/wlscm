package com.jatools.web.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.ItemOrnaClass;
import com.jatools.web.util.DateUtil;

/**
 * 大类小类关系数据缓存
 * @author wanglj
 *
 */
public class ItemOrnaClassCache {
	private static Logger logger = Logger.getLogger(ItemOrnaClassCache.class);
	private static ItemOrnaClassCache itemOrnaClassCache = null;
	private static Map<String, List<ItemOrnaClass>> itemOrnaClassmap = null;
	private static Date refreshTime = null;

	private ItemOrnaClassCache() {
	}

	public synchronized static ItemOrnaClassCache getInstance() {
		if (null == itemOrnaClassCache) {
			init();
		}
		return ItemOrnaClassCache.itemOrnaClassCache;
	}

	public synchronized static void refresh(){
		itemOrnaClassCache = null;
		itemOrnaClassmap = null;
		init();
	}
	private synchronized static void init() {
		if (null == ItemOrnaClassCache.itemOrnaClassCache) {
			logger.debug("初始化大类小类关系缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					ItemOrnaClassCache.itemOrnaClassmap = new HashMap<String, List<ItemOrnaClass>>();
					List<ItemOrnaClass> itemOrnaClassList = bdCommonManager.getAllItemOrnaClass();
					for (ItemOrnaClass itemOrnaClass : itemOrnaClassList) {
						if(ItemOrnaClassCache.itemOrnaClassmap.containsKey(itemOrnaClass.getItemClassId())){
							List<ItemOrnaClass> items = ItemOrnaClassCache.itemOrnaClassmap.get(itemOrnaClass.getItemClassId());
							items.add(itemOrnaClass);
						}else{
							List<ItemOrnaClass> items = new ArrayList<ItemOrnaClass>();
							items.add(itemOrnaClass);
							ItemOrnaClassCache.itemOrnaClassmap.put(itemOrnaClass.getItemClassId(), items);
						}
					}
				}
				itemOrnaClassCache = new ItemOrnaClassCache();
			} catch (Exception e) {
				logger.error("初始化大类小类关系缓存数据失败");
				throw new RuntimeException("初始化大类小类关系缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != ItemOrnaClassCache.itemOrnaClassCache) {
			ItemOrnaClassCache.itemOrnaClassCache = null;
		}
		if (null != ItemOrnaClassCache.itemOrnaClassmap) {
			ItemOrnaClassCache.itemOrnaClassmap.clear();
		}
	}
	/**
	 * 根据大类id获取对应小类数据
	 * @param itemClassId
	 * @return
	 */
	public List<ItemOrnaClass> getOrnaClassOrnaByItemClassId(String itemClassId){
		return ItemOrnaClassCache.itemOrnaClassmap.get(itemClassId);
	}
	
	public List<ItemOrnaClass> getAllOrnaClassOrna(){
		Set<String> keys = ItemOrnaClassCache.itemOrnaClassmap.keySet();
		List<ItemOrnaClass> result = new ArrayList<ItemOrnaClass>();
		List<String> ids = new ArrayList<String>();
		for(String key : keys){
			List<ItemOrnaClass> ornaList = ItemOrnaClassCache.itemOrnaClassmap.get(key);
			for(ItemOrnaClass orna : ornaList){
				if(!ids.contains(orna.getOrnaClassId())){
					result.add(orna);
					ids.add(orna.getOrnaClassId());
				}
			}
		}
		return result;
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
