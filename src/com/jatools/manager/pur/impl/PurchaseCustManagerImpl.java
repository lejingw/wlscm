package com.jatools.manager.pur.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.pur.PurchaseCustDao;
import com.jatools.manager.pur.PurchaseCustManager;
import com.jatools.vo.pur.PurchaseCust;
import com.jatools.vo.pur.PurchaseHead;

public class PurchaseCustManagerImpl implements PurchaseCustManager {
	private PurchaseCustDao purchaseCustDao;

	public void setPurchaseCustDao(PurchaseCustDao purchaseCustDao) {
		this.purchaseCustDao = purchaseCustDao;
	}

	/**
	 * 获取定做单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseCustPageData(Map<String, String> condition, String jmFlag){
		return purchaseCustDao.getPurchaseCustPageData(condition, jmFlag);
	}
	/**
	 * 获取接收定做单列表
	 * @param condition
	 * @return
	 */
	public Pager getCustReceiveListPageData(Map<String, String> condition, String jmFlag, String userid){
		return purchaseCustDao.getCustReceiveListPageData(condition, jmFlag, userid);
	}

	/**
	 * 获取定做单信息
	 * @param custid
	 * @return
	 */
	public PurchaseCust getPurchaseCust(String custid, String jmFlag){
		return purchaseCustDao.getPurchaseCust(custid, jmFlag);
	}
	/**
	 * 获取定做单供应商
	 * @param custid
	 * @return
	 */
	public List<PurchaseHead> getPurchaseCustVendorList(String custid, String jmFlag){
		return purchaseCustDao.getPurchaseCustVendorList(custid, jmFlag);
	}
}
