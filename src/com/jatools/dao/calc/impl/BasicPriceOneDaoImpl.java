package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.BasicPriceOneDao;
import com.jatools.vo.calc.BasicPriceOne;

public class BasicPriceOneDaoImpl extends BaseDao implements BasicPriceOneDao {

	@Override
	public Pager getBasicPriceOnePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("BasicPriceOne.getBasicPriceOneAllPageData", "BasicPriceOne.getBasicPriceOneToalCount", condition);
		return pager;
	}

	@Override
	public void saveBasicPriceOne(BasicPriceOne stonePrice) {
		executeInsert("BasicPriceOne.saveBasicPriceOne", stonePrice);
	}

	@Override
	public BasicPriceOne getBasicPriceOneById(String id) {
		BasicPriceOne c = (BasicPriceOne) executeQueryForObject("BasicPriceOne.getBasicPriceOneById", id);
		return c;
	}

	@Override
	public void updateBasicPriceOne(BasicPriceOne stonePrice) {
		executeUpdate("BasicPriceOne.updateBasicPriceOne", stonePrice);
	}

	@Override
	public void deleteBasicPriceOne(String id) {
		executeUpdate("BasicPriceOne.deleteBasicPriceOne", id);
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceOne getBasicPriceOne(BasicPriceOne sp){
		return (BasicPriceOne)executeQueryForObject("BasicPriceOne.getBasicPriceOne", sp);
	}
}
