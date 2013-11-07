package com.jatools.web.cache;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.Unit;
import com.jatools.web.util.DateUtil;

public class UnitCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(UnitCache.class);
	private static UnitCache unitCache = null;
	private static Map<String, String> unitMap = null;
	private static Date refreshTime = null;

	private UnitCache() {
	}

	public synchronized static UnitCache getInstance() {
		if (null == UnitCache.unitCache) {
			init();
		}
		return UnitCache.unitCache;
	}

	public synchronized static void refresh(){
		unitCache = null;
		unitMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == UnitCache.unitCache) {
			logger.debug("初始化计量单位缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					UnitCache.unitMap = new LinkedHashMap<String, String>();
					List<Unit> unitList = bdCommonManager.getAllUnit();
					for (Unit item : unitList) {
						UnitCache.unitMap.put(item.getUnitId(), item.getUnitName());
					}
				}
				UnitCache.unitCache = new UnitCache();
			} catch (Exception e) {
				logger.error("初始化计量单位缓存数据失败");
				throw new RuntimeException("初始化计量单位缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != UnitCache.unitCache) {
			UnitCache.unitCache = null;
		}
		if (null != UnitCache.unitMap) {
			UnitCache.unitMap.clear();
		}
	}
	/**
	 * 获取所有计量单位
	 */
	public Map<String, String> getAllUnit() {
		return unitMap;
	}
	
	/**
	 * 根据计量单位ids获取计量单位名称
	 * @param unitId
	 * @return
	 */
	public String getUnitName(String unitId){
		if(null == unitId)	return null;
		return unitMap.get(unitId);
	}
	public String getNameById(String id){
		return getUnitName(id);
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
