package com.jatools.web.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.sys.DictManager;
import com.jatools.vo.sys.Dict;
import com.jatools.web.util.DateUtil;
/**
 * 数据字典缓存
 * @author wanglj
 *
 */
public class DictCache {
	private static Logger logger = Logger.getLogger(DictCache.class);
	private static DictCache dictCache = null;
	private static Map<String, List<Dict>> cacheDictMap = null;
	private static Date refreshTime = null;
	
	private DictCache(){}
	
	public static DictCache getInstance(){
		if(null == dictCache){
			init();
		}
		return DictCache.dictCache;
	}
	
	public synchronized static void refresh(){
		dictCache = null;
		cacheDictMap = null;
		init();
	}
	
	private synchronized static void init(){
		if(null == DictCache.dictCache){
			logger.debug("初始化数据字典缓存数据...");
			try {
				DictManager dictManager = (DictManager) Global.springContext.getBean("dictManager");
				if(null != dictManager){
					DictCache.cacheDictMap = new HashMap<String, List<Dict>>();
					List<Dict> dictItemList = dictManager.getAllDictItem();
					for(Dict item : dictItemList){
						if(DictCache.cacheDictMap.containsKey(item.getEntryCode())){
							List<Dict> items = DictCache.cacheDictMap.get(item.getEntryCode());
							items.add(item);
						}else{
							List<Dict> items = new ArrayList<Dict>();
							items.add(item);
							DictCache.cacheDictMap.put(item.getEntryCode(), items);
						}
					}
				}
				dictCache = new DictCache();
			} catch (Exception e) {
				logger.error("初始化数据字典缓存数据失败");
				throw new RuntimeException("初始化数据字典缓存数据失败");
			}
		}
		refreshTime = new Date();
	}
	
	public synchronized void destroy(){
		DictCache.dictCache = null;
		if(null != DictCache.cacheDictMap){
			DictCache.cacheDictMap.clear();
		}
	}
	
	/**
	 * 获取所有数据字典数据
	 * @return
	 */
	public Map<String, List<Dict>> getAllDict(){
		return DictCache.cacheDictMap;
	}

	/**
	 * 根据数据字典名称获取数据字典项
	 */
	public List<Dict> getDicts(String code){
		List<Dict> list = DictCache.cacheDictMap.get(code);
		return list;
	}

	/**
	 * 获取数据字典值,若不能找到则返回key
	 * @param code
	 * @param key
	 * @return
	 */
	public String getValue(String code, String key){
		List<Dict> list = DictCache.cacheDictMap.get(code);
		if(null != list && !list.isEmpty()){
			Iterator<Dict> iter = list.iterator();
			while(iter.hasNext()){
				Dict dict = iter.next();
				if(dict.getItemKey().equals(key)){
					return dict.getItemValue();
				}
			}
		}
		return key;
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
