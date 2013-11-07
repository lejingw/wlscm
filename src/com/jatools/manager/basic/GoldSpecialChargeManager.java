package com.jatools.manager.basic;

import com.jatools.common.Pager;
import com.jatools.vo.basic.GoldSpecialCharge;

import java.util.Map;

/**
 * @author  ren.ming
 * @Created 2011.11.16
 * <br>
 *  与pos接口数据manager
 */
public interface GoldSpecialChargeManager {
	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getGoldSpecialChargePageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param goldSpecialCharge
	 */
	void saveOrUpdateGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge, String userId);

	/**
	 * 根据id获
	 * @param billid
	 * @return
	 */
	GoldSpecialCharge getGoldSpecialCharge(String billid);

	/**
	 * 删除
	 * @param billids
	 */
	void deleteGoldSpecialCharge(String billids);

    GoldSpecialCharge getGoldSpecialChargeByItemOrna(String itemClassId, String ornaClassId, String weight);
}
