package com.jatools.dao.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.SpecialCharge;

public interface SpecialChargeDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getSpecialChargePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveSpecialCharge(SpecialCharge specialCharge);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	SpecialCharge getSpecialChargeById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateSpecialCharge(SpecialCharge specialCharge);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteSpecialCharge(String id);
	/**
	 * 获取工费
	 * @param sc
	 * @return
	 */
	public String getSpecialCharge(SpecialCharge sc);
}
