package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.StoneMainCoefficient;

public interface StoneMainCoefficientDao {

	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getStoneMainCoefficientPageData(Map<String, String> condition);
	/**
	 * 保存钻石石头系数关系
	 * @param StoneMainCoefficient
	 */
	void saveStoneMainCoefficient(StoneMainCoefficient StoneMainCoefficient);
	/**
	 * 根据id获取钻石石头系数关系
	 * @param StoneMainCoefficientId
	 * @return
	 */
	StoneMainCoefficient getStoneMainCoefficient(String StoneMainCoefficientId);
	/**
	 * 保存或修改钻石石头系数关系
	 * @param StoneMainCoefficient
	 */
	void updateStoneMainCoefficient(StoneMainCoefficient StoneMainCoefficient);
	/**
	 * 删除钻石石头系数关系
	 * @param StoneMainCoefficientId
	 */
	void deleteStoneMainCoefficient(String StoneMainCoefficientId);
	
	/**
	 * 判断起始金额是否存在
	 * @param weightStr
	 * @return
	 */
	int checkWeightStr(StoneMainCoefficient StoneMainCoefficient);
	/**
	 * 检测终止金额是否存在
	 * @param weightEnd
	 * @return
	 */
	int checkWeightEnd(StoneMainCoefficient StoneMainCoefficient);
}
