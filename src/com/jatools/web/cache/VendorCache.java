package com.jatools.web.cache;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.jatools.common.Global;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.basic.Vendor;
import com.jatools.web.util.DateUtil;
/**
 * 供应商数据缓存
 * @author wanglj
 *
 */
public class VendorCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(VendorCache.class);
	private static VendorCache vendorCache = null;
	private static Map<String, Vendor> vendorMap = null;
	private static Date refreshTime = null;

	private VendorCache() {
	}

	public synchronized static VendorCache getInstance() {
		if (null == vendorCache) {
			init();
		}
		return vendorCache;
	}
	public synchronized static void refresh(){
		vendorCache = null;
		vendorMap = null;
		init();
	}
	private synchronized static void init() {
		if (null == vendorMap) {
			logger.debug("初始化供应商缓存数据...");
			try {
				BdCommonManager bdCommonManager = (BdCommonManager) Global.springContext.getBean("bdCommonManager");
				if (null != bdCommonManager) {
					vendorMap = new LinkedHashMap<String, Vendor>();
					List<Vendor> VendorList = bdCommonManager.getAllVendor();
					for (Vendor vendor : VendorList) {
						vendorMap.put(vendor.getVendorId(), vendor);
					}
				}
				vendorCache = new VendorCache();
			} catch (Exception e) {
				logger.error("初始化供应商缓存数据失败");
				throw new RuntimeException("初始化供应商缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != vendorCache) {
			vendorCache = null;
		}
		if (null != vendorMap) {
			vendorMap.clear();
		}
	}
	
	/**
	 * 根据供应商id获取供应商名称
	 * @param vendorId
	 * @return
	 */
	public String getVendorName(String vendorId){
		Vendor Vendor = vendorMap.get(vendorId);
		if(null != Vendor){
			return Vendor.getVendorName();
		}
		return null;
	}
	
	/**
	 * 根据供应商id获取供应商代码
	 * @param vendorId
	 * @return
	 */
	public String getVendorSupplierCode(String vendorId){
		Vendor Vendor = vendorMap.get(vendorId);
		if(null != Vendor){
			return Vendor.getSupplierCode();
		}
		return null;
	}

	public String getNameById(String id){
		return getVendorName(id);
	}
	
	public Map<String, Vendor> getAllVendor(){
		return VendorCache.vendorMap;
	}
	
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
