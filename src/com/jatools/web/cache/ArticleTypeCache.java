package com.jatools.web.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.ArticleType;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;
/**
 * 数据字典缓存
 * @author wanglj
 *
 */
public class ArticleTypeCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(ArticleTypeCache.class);
	private static ArticleTypeCache aiticleTypeCache = null;
	private static Map<String, String> articleTypeMap = null;
	private static Date refreshTime = null;
	
	private ArticleTypeCache(){}
	
	public static ArticleTypeCache getInstance(){
		if(null == aiticleTypeCache){
			init();
		}
		return ArticleTypeCache.aiticleTypeCache;
	}
	
	public synchronized static void refresh(){
		aiticleTypeCache = null;
		articleTypeMap = null;
		init();
	}
	
	private synchronized static void init(){
		if(null == ArticleTypeCache.aiticleTypeCache){
			logger.debug("初始化商品类别缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if(null != bdCommonManager){
					ArticleTypeCache.articleTypeMap = new HashMap<String, String>();
					List<ArticleType> aiticleTypeItemList = bdCommonManager.getAllArticleType();
					for(ArticleType item : aiticleTypeItemList){
						ArticleTypeCache.articleTypeMap.put(item.getArticleTypeId(), item.getArticleTypeDesc());
					}
				}
				aiticleTypeCache = new ArticleTypeCache();
			} catch (Exception e) {
				logger.error("初始化商品类别缓存数据失败");
				throw new RuntimeException("初始化商品类别缓存数据失败");
			}
		}
		refreshTime = new Date();
	}
	
	public synchronized void destroy(){
		ArticleTypeCache.aiticleTypeCache = null;
		if(null != ArticleTypeCache.articleTypeMap){
			ArticleTypeCache.articleTypeMap.clear();
		}
	}
	
	/**
	 * 获取所有商品类别数据
	 * @return
	 */
	public Map<String, String> getAllArticleType(){
		return ArticleTypeCache.articleTypeMap;
	}

	/**
	 * 根据商品类别名称获取商品类别项
	 */
	public String getArticleTypeName(String articleTypeIds){
		if(StringUtil.isEmpty(articleTypeIds))	return null;
		String[] tmpArr = articleTypeIds.split(",");
		String tmpStr = "$";
		for(String tmp : tmpArr){
			tmpStr += "," + articleTypeMap.get(tmp);
		}
		if("$".equals(tmpStr))	return null;
		return tmpStr.substring(2);
	}
	public String getNameById(String id){
		return getArticleTypeName(id);
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
