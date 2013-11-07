package com.jatools.dao.calc.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.BasicPriceTwoDao;
import com.jatools.vo.calc.BasicPriceTwo;

public class BasicPriceTwoDaoImpl extends BaseDao implements BasicPriceTwoDao {

	@Override
	public Pager getBasicPriceTwoPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("BasicPriceTwo.getBasicPriceTwoAllPageData", "BasicPriceTwo.getBasicPriceTwoToalCount", condition);
		return pager;
	}

	@Override
	public void saveBasicPriceTwo(BasicPriceTwo stonePrice) {
		executeInsert("BasicPriceTwo.saveBasicPriceTwo", stonePrice);
	}

	@Override
	public BasicPriceTwo getBasicPriceTwoById(String id) {
		BasicPriceTwo c = (BasicPriceTwo) executeQueryForObject("BasicPriceTwo.getBasicPriceTwoById", id);
		return c;
	}

	@Override
	public void updateBasicPriceTwo(BasicPriceTwo stonePrice) {
		executeUpdate("BasicPriceTwo.updateBasicPriceTwo", stonePrice);
	}

	@Override
	public void deleteBasicPriceTwo(String id) {
		executeUpdate("BasicPriceTwo.deleteBasicPriceTwo", id);
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceTwo getBasicPriceTwo(BasicPriceTwo sp){
		return (BasicPriceTwo)executeQueryForObject("BasicPriceTwo.getBasicPriceTwo", sp);
	}

	@Override
	public BasicPriceTwo getTwoPrice(String value) {
		List<BasicPriceTwo> list = executeQueryForList("BasicPriceTwo.getTwoPrice", value);
		
		if(list==null||list.size()==0)
			return null;
		else 
			return list.get(0);
	}
}
