package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.PtCharge;

public interface PtChargeManager {
	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getPtChargePageData(Map<String, String> condition);
	/**
	 * 保存工费核算标准
	 * @param PtCharge
	 */
	void savePtCharge(PtCharge charge);
	/**
	 * 根据id获取工费核算标准
	 * @param chargeId
	 * @return
	 */
	PtCharge getPtCharge(String chargeId);
	/**
	 * 保存或修改工费核算标准
	 * @param PtCharge
	 */
	void updatePtCharge(PtCharge charge);
	/**
	 * 删除工费核算标准
	 * @param chargeId
	 */
	void deletePtCharge(String chargeId);
	
	/**
	 * 保存或修改工费核算标准
	 * @param charge
	 */
	public void saveOrUpdatePtCharge(PtCharge charge);

	/**
	 * 判断起始金额是否存在
	 * @param weightStr
	 * @return
	 */
	int checkWeightStr(String id, String weightStr);
	/**
	 * 检测终止金额是否存在
	 * @param weightEnd
	 * @return
	 */
	int checkWeightEnd(String id, String weightEnd);
}