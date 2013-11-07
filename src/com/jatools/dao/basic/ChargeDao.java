package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.Charge;

/**
 * @author  ren.ming
 * @Created 2011.11.16
 * <br>
 *  工费核算标准 dao
 */
public interface ChargeDao {
	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getChargePageData(Map<String, String> condition);
	/**
	 * 保存工费核算标准
	 * @param Charge
	 */
	void saveCharge(Charge charge);
	/**
	 * 根据id获取工费核算标准
	 * @param chargeId
	 * @return
	 */
	Charge getCharge(String chargeId);
	/**
	 * 保存或修改工费核算标准
	 * @param Charge
	 */
	void updateCharge(Charge charge);
	/**
	 * 删除工费核算标准
	 * @param chargeId
	 */
	void deleteCharge(String chargeId);

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
