package com.jatools.web.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
/**
 * 小类数据缓存
 * @author wanglj
 *
 */
public class OrnaClassCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(OrnaClassCache.class);
	private static OrnaClassCache ornaClassCache = null;
	private static Map<String, OrnaClass> ornaClassMap = null;
	private static Date refreshTime = null;

	private OrnaClassCache() {
	}

	public synchronized static OrnaClassCache getInstance() {
		if (null == ornaClassCache) {
			init();
		}
		return OrnaClassCache.ornaClassCache;
	}
	public synchronized static void refresh(){
		ornaClassCache = null;
		ornaClassMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == OrnaClassCache.ornaClassMap) {
			logger.debug("初始化小类缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					OrnaClassCache.ornaClassMap = new LinkedHashMap<String, OrnaClass>();
					List<OrnaClass> ornaClassList = bdCommonManager.getAllOrnaClass();
					for (OrnaClass orna : ornaClassList) {
						OrnaClassCache.ornaClassMap.put(orna.getOrnaClassId(), orna);
					}
				}
				OrnaClassCache.ornaClassCache = new OrnaClassCache();
			} catch (Exception e) {
				logger.error("初始化小类缓存数据失败");
				throw new RuntimeException("初始化小类缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != OrnaClassCache.ornaClassCache) {
			OrnaClassCache.ornaClassCache = null;
		}
		if (null != OrnaClassCache.ornaClassMap) {
			OrnaClassCache.ornaClassMap.clear();
		}
	}
	

	/**
	 * 根据小类id获取小类名称
	 * @param ornaClassId
	 * @return
	 */
	public String getOrnaClass(String ornaClassId){
		OrnaClass ornaClass = ornaClassMap.get(ornaClassId);
		if(null != ornaClass){
			return ornaClass.getOrnaClassDesc();
		}
		return null;
	}
	
	public String getOrnaClassNames(String ornaClassIds){
		if(StringUtil.isBlank(ornaClassIds))	return null;
		String[] tmpArr = ornaClassIds.split(",");
		String tmpStr = "$";
		for(String tmp : tmpArr){
			tmpStr += "," + ornaClassMap.get(tmp).getOrnaClassDesc();
		}
		if("$".equals(tmpStr))	return null;
		return tmpStr.substring(2);
	}
	
	public Map<String, String> getAllOrnaClass() {
		Map<String, String> res = new HashMap<String, String>();
		if(OrnaClassCache.ornaClassMap.size() > 0){
			for(String key : OrnaClassCache.ornaClassMap.keySet()){
				res.put(key, OrnaClassCache.ornaClassMap.get(key).getOrnaClassDesc());
			}
		}
		return res;
	}
	
	public String getNameById(String id){
		return getOrnaClass(id);
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
