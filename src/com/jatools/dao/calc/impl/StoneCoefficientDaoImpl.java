package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.StoneCoefficientDao;
import com.jatools.vo.calc.Coefficient;
import com.jatools.vo.calc.StoneCoefficient;

public class StoneCoefficientDaoImpl extends BaseDao implements StoneCoefficientDao {

	@Override
	public Pager getStoneCoefficientPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("StoneCoefficient.getStoneCoefficientAllPageData", "StoneCoefficient.getStoneCoefficientToalCount", condition);
		return pager;
	}

	@Override
	public void saveStoneCoefficient(StoneCoefficient stoneCoefficient) {
		executeInsert("StoneCoefficient.saveStoneCoefficient", stoneCoefficient);
	}

	@Override
	public StoneCoefficient getStoneCoefficientById(String id) {
		StoneCoefficient c = (StoneCoefficient) executeQueryForObject("StoneCoefficient.getStoneCoefficientById", id);
		return c;
	}

	@Override
	public void updateStoneCoefficient(StoneCoefficient stoneCoefficient) {
		executeUpdate("StoneCoefficient.updateStoneCoefficient", stoneCoefficient);
	}

	@Override
	public void deleteStoneCoefficient(String id) {
		executeUpdate("StoneCoefficient.deleteStoneCoefficient", id);
	}

	@Override
	public StoneCoefficient getStoneCoefficient(StoneCoefficient sc) {
		return (StoneCoefficient)executeQueryForObject("StoneCoefficient.getStoneCoefficient", sc);
	}
}
