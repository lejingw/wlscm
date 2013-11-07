package com.jatools.dao.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.StonePrice;

public interface StonePriceDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getStonePricePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveStonePrice(StonePrice stonePrice);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	StonePrice getStonePriceById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateStonePrice(StonePrice stonePrice);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteStonePrice(String id);
	/**
	 * @param sp
	 * @return
	 */
	public boolean existStonePrice(StonePrice sp);

    /**
     * 获取主配石市场价
     * @param sp
     * @return
     */
    public StonePrice  getStonePrice(StonePrice sp);
}
