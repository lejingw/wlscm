package com.jatools.dao.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.BasicPriceOne;

public interface BasicPriceOneDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getBasicPriceOnePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveBasicPriceOne(BasicPriceOne stonePrice);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	BasicPriceOne getBasicPriceOneById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateBasicPriceOne(BasicPriceOne stonePrice);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteBasicPriceOne(String id);
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceOne getBasicPriceOne(BasicPriceOne sp);
}
