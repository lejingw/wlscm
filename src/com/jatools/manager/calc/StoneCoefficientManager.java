package com.jatools.manager.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.StoneCoefficient;

public interface StoneCoefficientManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getStoneCoefficientPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveStoneCoefficient(StoneCoefficient stoneCoefficient);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	StoneCoefficient getStoneCoefficientById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateStoneCoefficient(StoneCoefficient stoneCoefficient);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteStoneCoefficient(String id);
	/**
	 * 获取石头系数
	 * @param sc
	 * @return
	 */
	public StoneCoefficient getStoneCoefficient(StoneCoefficient sc);
}
