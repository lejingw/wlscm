package com.jatools.manager.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.PurchaseCust;
import com.jatools.vo.pur.PurchaseHead;

public interface PurchaseCustManager {

	/**
	 * 获取定做单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurchaseCustPageData(Map<String, String> condition, String jmFlag);
	/**
	 * 获取接收定做单列表
	 * @param condition
	 * @return
	 */
	public Pager getCustReceiveListPageData(Map<String, String> condition, String jmFlag, String userid);

	/**
	 * 获取定做单信息
	 * @param custid
	 * @return
	 */
	public PurchaseCust getPurchaseCust(String custid, String jmFlag);

	/**
	 * 获取定做单供应商
	 * @param custid
	 * @return
	 */
	public List<PurchaseHead> getPurchaseCustVendorList(String custid, String jmFlag);

}
