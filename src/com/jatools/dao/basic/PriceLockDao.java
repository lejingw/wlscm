package com.jatools.dao.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.basic.PriceLockLine;

public interface PriceLockDao {

	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getPriceLockPageData(Map<String, String> condition, String orgId);

	/**
	 * 保存
	 * 
	 * @param orgId
	 * @param addUserIds
	 */
	String savePriceLockHead(PriceLockHead head, String userid);

	/**
	 * 保存
	 */
	void savePriceLockLine(List<String> ornaCodeList, String headid, String userid);

	/**
	 * 更新
	 * 
	 * @param orgId
	 * @param addUserIds
	 */
	void updatePriceLockHead(PriceLockHead head, String userid);

	/**
	 * 删除记录
	 * 
	 * @param lockId
	 */
	void deletePriceLockHead(String headid);

	/**
	 * 删除记录
	 * 
	 * @param headid
	 */
	void deletePriceLockLineByHeadid(String headid);

	/**
	 * 删除记录
	 */
	void deletePriceLockLineByOrnaCode(List<String> ornaCodeList, String headid);

	/**
	 * 根据价格锁定head
	 */
	public PriceLockHead getPriceLockHead(String headid);

	/**
	 * 根据价格锁定line
	 */
	public List<PriceLockLine> getPriceLockLine(String headid);
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public PriceLockLine getMaterActiveInfo(String code, boolean ornaFlag);
	/**
	 * 获取价格锁定数据
	 */
	public Pager queryPriceLockPageData(Map<String, String> condition);
}
