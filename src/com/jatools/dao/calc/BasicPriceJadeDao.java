package com.jatools.dao.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.BasicPriceJade;

public interface BasicPriceJadeDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getBasicPriceJadePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveBasicPriceJade(BasicPriceJade stonePrice);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	BasicPriceJade getBasicPriceJadeById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateBasicPriceJade(BasicPriceJade stonePrice);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteBasicPriceJade(String id);
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceJade getBasicPriceJade(BasicPriceJade sp);
}
