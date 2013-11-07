package com.jatools.manager.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.BasicPriceTwo;

public interface BasicPriceTwoManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getBasicPriceTwoPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveBasicPriceTwo(BasicPriceTwo stonePrice);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	BasicPriceTwo getBasicPriceTwoById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateBasicPriceTwo(BasicPriceTwo stonePrice);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteBasicPriceTwo(String id);
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceTwo getBasicPriceTwo(BasicPriceTwo sp);
	public BasicPriceTwo getTwoPrice(String value);
}
