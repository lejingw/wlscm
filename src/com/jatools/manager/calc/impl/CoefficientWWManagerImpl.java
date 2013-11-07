package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.CoefficientWWDao;
import com.jatools.manager.calc.CoefficientWWManager;
import com.jatools.vo.calc.CoefficientWW;

public class CoefficientWWManagerImpl implements CoefficientWWManager{

	private CoefficientWWDao coefficientWWDao;
	
	
	public void setCoefficientWWDao(CoefficientWWDao coefficientWWDao) {
		this.coefficientWWDao = coefficientWWDao;
	}

	@Override
	public Pager getCoefficientWWPageData(Map<String, String> condition) {
		return coefficientWWDao.getCoefficientWWPageData(condition);
	}

	@Override
	public void saveCoefficientWW(CoefficientWW coefficient) {
		coefficientWWDao.saveCoefficientWW(coefficient);
	}

	@Override
	public CoefficientWW getCoefficientWWById(String id) {
		return coefficientWWDao.getCoefficientWWById(id);
	}

	@Override
	public void updateCoefficientWW(CoefficientWW coefficient) {
		coefficientWWDao.updateCoefficientWW(coefficient);
	}

	@Override
	public void deleteCoefficientWW(String id) {
		coefficientWWDao.deleteCoefficientWW(id);
	}

	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	@Override
	public CoefficientWW getCoefficientWW(CoefficientWW a){
		return coefficientWWDao.getCoefficientWW(a);
	}
}
