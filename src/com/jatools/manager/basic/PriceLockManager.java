package com.jatools.manager.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.basic.PriceLockLine;

public interface PriceLockManager {

	/**
	 * 获取分页数据
	 */
	public Pager getPriceLockPageData(Map<String, String> condition, String orgId);

	/**
	 * 删除记录
	 */
	void deletePriceLock(String headid);

	public PriceLockHead getPriceLockHead(String headid);

	public List<PriceLockLine> getPriceLockLine(String headid);

	/**
	 * 保存
	 */
	public void savePriceLock(PriceLockHead head, List<String> addLineList, List<String> deleteLineList, String userid);
	/**
	 * 获取饰品信息
	 */
	public PriceLockLine getMaterActiveInfo(String code, boolean ornaFlag);
	/**
	 * 获取价格锁定数据
	 */
	public Pager queryPriceLockPageData(Map<String, String> condition);

}
