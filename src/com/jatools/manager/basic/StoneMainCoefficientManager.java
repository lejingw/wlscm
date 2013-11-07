package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.StoneMainCoefficient;

public interface StoneMainCoefficientManager {

	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getStoneMainCoefficientPageData(Map<String, String> condition);
	/**
	 * 保存或修改钻石石头系数
	 * @param stoneMainCoefficient
	 */
	void saveOrUpdateStoneMainCoefficient(StoneMainCoefficient stoneMainCoefficient, String userId);
	/**
	 * 根据id获取钻石石头系数
	 * @param id
	 * @return
	 */
	StoneMainCoefficient getStoneMainCoefficient(String id);
	
	/**
	 * 删除钻石石头系数
	 * @param id
	 */
	void deleteStoneMainCoefficient(String id);
}
