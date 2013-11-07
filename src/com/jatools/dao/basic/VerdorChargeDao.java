package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.VerdorCharge;

public interface VerdorChargeDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getVerdorChargePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveVerdorCharge(VerdorCharge VerdorCharge);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	VerdorCharge getVerdorChargeById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateVerdorCharge(VerdorCharge VerdorCharge);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteVerdorCharge(String id);
	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	public VerdorCharge getVerdorCharge(VerdorCharge a);
}
