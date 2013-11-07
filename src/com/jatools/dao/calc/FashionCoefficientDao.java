package com.jatools.dao.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.FashionCoefficient;

public interface FashionCoefficientDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getFashionCoefficientPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveFashionCoefficient(FashionCoefficient fashionCoefficient);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	FashionCoefficient getFashionCoefficientById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateFashionCoefficient(FashionCoefficient fashionCoefficient);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteFashionCoefficient(String id);
	/**
	 * 材质找系数
	 * @param qid
	 * @return
	 */
	FashionCoefficient getFashionCoefficientItemClassId(Map<String, String> condition);
}
