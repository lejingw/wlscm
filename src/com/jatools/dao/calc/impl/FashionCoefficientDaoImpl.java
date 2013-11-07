package com.jatools.dao.calc.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.FashionCoefficientDao;
import com.jatools.dao.calc.StoneCoefficientDao;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.Coefficient;
import com.jatools.vo.calc.FashionCoefficient;

public class FashionCoefficientDaoImpl extends BaseDao implements FashionCoefficientDao {

	@Override
	public Pager getFashionCoefficientPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("FashionCoefficient.getFashionCoefficientAllPageData", "FashionCoefficient.getFashionCoefficientToalCount", condition);
		return pager;
	}

	@Override
	public void saveFashionCoefficient(FashionCoefficient fashionCoefficient) {
		executeInsert("FashionCoefficient.saveFashionCoefficient", fashionCoefficient);
	}

	@Override
	public FashionCoefficient getFashionCoefficientById(String id) {
		return (FashionCoefficient)executeQueryForObject("FashionCoefficient.getFashionCoefficientById", id);
	}

	@Override
	public void updateFashionCoefficient(FashionCoefficient fashionCoefficient) {
		executeUpdate("FashionCoefficient.updateFashionCoefficient", fashionCoefficient);
	}

	@Override
	public void deleteFashionCoefficient(String id) {
		executeUpdate("FashionCoefficient.deleteFashionCoefficient", id);
	}

	@Override
	public FashionCoefficient getFashionCoefficientItemClassId(Map<String, String> condition) {
		return (FashionCoefficient)executeQueryForObject("FashionCoefficient.getFashionCoefficientByItemClassId", condition);
	}
}
