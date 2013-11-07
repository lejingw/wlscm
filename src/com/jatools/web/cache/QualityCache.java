package com.jatools.web.cache;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.Quality;
import com.jatools.web.util.DateUtil;

public class QualityCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(QualityCache.class);
	private static QualityCache qualityCache = null;
	private static Map<String, String> qualityMap = null;
	private static Date refreshTime = null;

	private QualityCache() {
	}

	public synchronized static QualityCache getInstance() {
		if (null == QualityCache.qualityCache) {
			init();
		}
		return QualityCache.qualityCache;
	}

	public synchronized static void refresh(){
		qualityCache = null;
		qualityMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == QualityCache.qualityCache) {
			logger.debug("初始化商品材质缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					QualityCache.qualityMap = new LinkedHashMap<String, String>();
					List<Quality> qualityList = bdCommonManager.getAllQuality();
					for (Quality item : qualityList) {
						QualityCache.qualityMap.put(item.getQualityId(), item.getQualityName());
					}
				}
				QualityCache.qualityCache = new QualityCache();
			} catch (Exception e) {
				logger.error("初始化商品材质缓存数据失败");
				throw new RuntimeException("初始化商品材质缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != QualityCache.qualityCache) {
			QualityCache.qualityCache = null;
		}
		if (null != QualityCache.qualityMap) {
			QualityCache.qualityMap.clear();
		}
	}
	/**
	 * 获取所有商品材质
	 */
	public Map<String, String> getAllQuality() {
		return qualityMap;
	}
	
	/**
	 * 根据商品材质ids获取商品材质名称，用逗号隔开
	 * @param qualityIds
	 * @return
	 */
	public String getQualityName(String qualityId){
		if(null == qualityId)	return null;
		return qualityMap.get(qualityId);
	}
	public String getNameById(String id){
		return getQualityName(id);
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
