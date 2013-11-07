package com.jatools.web.cache;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.bd.Org;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
/**
 * 组织数据缓存
 * @author wanglj
 *
 */
public class OrgCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(OrgCache.class);
	private static OrgCache orgCache = null;
	private static Map<String, Org> financeOrgMap = null;
	private static Map<String, Org> executeOrgMap = null;
	private static Date refreshTime = null;

	private OrgCache() {
	}

	public synchronized static OrgCache getInstance() {
		if (null == OrgCache.orgCache) {
			init();
		}
		return OrgCache.orgCache;
	}

	public synchronized static void refresh(){
		orgCache = null;
		financeOrgMap = null;
		executeOrgMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == OrgCache.orgCache) {
			logger.debug("初始化组织缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					logger.debug("初始化财务组织缓存数据...");
					OrgCache.financeOrgMap = new LinkedHashMap<String, Org>();
					List<Org> financeOrgList = bdCommonManager.getOrgTreeByType(GlobalConstant.ORG_TYPE_FINANCE);
					for (Org org : financeOrgList) {
						OrgCache.financeOrgMap.put(org.getOrgId(), org);
					}

					logger.debug("初始化行政组织缓存数据...");
					OrgCache.executeOrgMap = new LinkedHashMap<String, Org>();
					List<Org> executeOrgList = bdCommonManager.getOrgTreeByType(GlobalConstant.ORG_TYPE_EXECUTE);
					for (Org org : executeOrgList) {
						OrgCache.executeOrgMap.put(org.getOrgId(), org);
					}
				}
				orgCache = new OrgCache();
			} catch (Exception e) {
				logger.error("初始化组织缓存数据失败");
				throw new RuntimeException("初始化组织缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != OrgCache.orgCache) {
			OrgCache.orgCache = null;
		}
		if (null != OrgCache.financeOrgMap) {
			OrgCache.financeOrgMap.clear();
		}
		if (null != OrgCache.executeOrgMap) {
			OrgCache.executeOrgMap.clear();
		}
	}

	/**
	 * 获取财务组织树数据
	 * @return
	 */
	public Map<String, Org> getFinanceOrgTree(){
		return OrgCache.financeOrgMap;
	}
	/**
	 * 获取行政组织树数据
	 * @return
	 */
	public Map<String, Org> getExecuteOrgTree(){
		return OrgCache.executeOrgMap;
	}

	/**
	 * 根据组织id获取组织名称，不区分财务、行政组织
	 * @param orgId
	 * @return
	 */
	public String getOrgName(String orgId) {
		if(StringUtil.isEmpty(orgId)){
			return null;
		}
		Org org = financeOrgMap.get(orgId);
		if(null != org){
			return org.getOrgName();
		}
		org = executeOrgMap.get(orgId);
		if(null != org){
			return org.getOrgName();
		}
		return null;
	}

	/**
	 * 根据组织id获取组织名称，不区分财务、行政组织
	 * @param orgId
	 * @return
	 */
	public Org getOrgById(String orgId) {
		if(StringUtil.isEmpty(orgId)){
			return null;
		}
		Org org = financeOrgMap.get(orgId);
		if(null != org){
			return org;
		}
		return executeOrgMap.get(orgId);
	}
	public String getNameById(String id){
		return getOrgName(id);
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
