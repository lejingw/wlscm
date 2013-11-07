package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.CoefficientDao;
import com.jatools.vo.calc.Coefficient;

public class CoefficientDaoImpl extends BaseDao implements CoefficientDao {

	
	@Override
	public Pager getCoefficientPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("Coefficient.getCoefficientAllPageData", "Coefficient.getCoefficientToalCount", condition);
		return pager;
	}

	@Override
	public void saveCoefficient(Coefficient coefficient) {
		executeInsert("Coefficient.saveCoefficient", coefficient);
	}

	@Override
	public Coefficient getCoefficientById(String id) {
		Coefficient c = (Coefficient) executeQueryForObject("Coefficient.getCoefficientById", id);
		return c;
	}

	@Override
	public void updateCoefficient(Coefficient coefficient) {
		executeUpdate("Coefficient.updateCoefficient", coefficient);
	}

	@Override
	public void deleteCoefficient(String id) {
		executeUpdate("Coefficient.deleteCoefficient", id);
	}

	@Override
	public Coefficient getCoefficient(Coefficient a) {
		// TODO Auto-generated method stub
		return (Coefficient)executeQueryForObject("Coefficient.getCoefficient", a);
	}

}
