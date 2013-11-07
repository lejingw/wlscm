package com.jatools.manager.calc;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraCoefficient;

public interface SbraCoefficientManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getSbraCoefficientPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveSbraCoefficient(SbraCoefficient SbraCoefficient);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	SbraCoefficient getSbraCoefficientById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateSbraCoefficient(SbraCoefficient SbraCoefficient);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteSbraCoefficient(String id);
	/**
	 * 材质找系数
	 * @param qid
	 * @return
	 */
	SbraCoefficient getSbraCoefficientByQualityId(Map<String, String> condition);
	/**
	 * 大类下的材质 
	 * @param itemClassId
	 * @return
	 */
	List<Quality> getQualityByItemClassId(String itemClassId);
}
