package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.BasicPriceJadeDao;
import com.jatools.vo.calc.BasicPriceJade;

public class BasicPriceJadeDaoImpl extends BaseDao implements BasicPriceJadeDao {

	@Override
	public Pager getBasicPriceJadePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("BasicPriceJade.getBasicPriceJadeAllPageData", "BasicPriceJade.getBasicPriceJadeToalCount", condition);
		return pager;
	}

	@Override
	public void saveBasicPriceJade(BasicPriceJade stonePrice) {
		executeInsert("BasicPriceJade.saveBasicPriceJade", stonePrice);
	}

	@Override
	public BasicPriceJade getBasicPriceJadeById(String id) {
		BasicPriceJade c = (BasicPriceJade) executeQueryForObject("BasicPriceJade.getBasicPriceJadeById", id);
		return c;
	}

	@Override
	public void updateBasicPriceJade(BasicPriceJade stonePrice) {
		executeUpdate("BasicPriceJade.updateBasicPriceJade", stonePrice);
	}

	@Override
	public void deleteBasicPriceJade(String id) {
		executeUpdate("BasicPriceJade.deleteBasicPriceJade", id);
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceJade getBasicPriceJade(BasicPriceJade sp){
		return (BasicPriceJade)executeQueryForObject("BasicPriceJade.getBasicPriceJade", sp);
	}
}
