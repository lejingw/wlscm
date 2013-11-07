package com.jatools.dao.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.CoefficientWW;

public interface CoefficientWWDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getCoefficientWWPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveCoefficientWW(CoefficientWW coefficient);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	CoefficientWW getCoefficientWWById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateCoefficientWW(CoefficientWW coefficient);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteCoefficientWW(String id);
	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	public CoefficientWW getCoefficientWW(CoefficientWW a);
}
