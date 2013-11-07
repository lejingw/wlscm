package com.jatools.manager.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.Coefficient;

public interface CoefficientManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getCoefficientPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveCoefficient(Coefficient coefficient);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	Coefficient getCoefficientById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateCoefficient(Coefficient coefficient);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteCoefficient(String id);
	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	public Coefficient getCoefficient(Coefficient a);
}
