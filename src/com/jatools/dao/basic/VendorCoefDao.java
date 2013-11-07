package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.VendorCoef;

public interface VendorCoefDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getVendorCoefPageData(Map<String,String> conditionMap);
	/**
	 * 保存单据
	 */
	void saveVendorCoef(VendorCoef VendorCoef);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	VendorCoef getVendorCoefById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateVendorCoef(VendorCoef VendorCoef);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteVendorCoef(String id);
	/**
	 * 是否有重复
	 * @param qid
	 * @return
	 */
	String getVendorCoefC(Map<String, String> condition);
}
