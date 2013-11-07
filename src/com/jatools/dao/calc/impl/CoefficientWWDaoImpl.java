package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.CoefficientWWDao;
import com.jatools.vo.calc.CoefficientWW;

public class CoefficientWWDaoImpl extends BaseDao implements CoefficientWWDao{

	@Override
	public Pager getCoefficientWWPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("CoefficientWW.getCoefficientWWAllPageData", "CoefficientWW.getCoefficientWWToalCount", condition);
		return pager;
	}

	@Override
	public void saveCoefficientWW(CoefficientWW coefficient) {
		executeInsert("CoefficientWW.saveCoefficientWW", coefficient);
	}

	@Override
	public CoefficientWW getCoefficientWWById(String id) {
		CoefficientWW c = (CoefficientWW) executeQueryForObject("CoefficientWW.getCoefficientWWById", id);
		return c;
	}

	@Override
	public void updateCoefficientWW(CoefficientWW coefficient) {
		executeUpdate("CoefficientWW.updateCoefficientWW", coefficient);
	}

	@Override
	public void deleteCoefficientWW(String id) {
		executeUpdate("CoefficientWW.deleteCoefficientWW", id);
	}

	@Override
	public CoefficientWW getCoefficientWW(CoefficientWW a) {
		// TODO Auto-generated method stub
		return (CoefficientWW)executeQueryForObject("CoefficientWW.getCoefficientWW", a);
	}
}
