package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.CoefficientDao;
import com.jatools.manager.calc.CoefficientManager;
import com.jatools.vo.calc.Coefficient;

public class CoefficientManagerImpl implements CoefficientManager {

	private CoefficientDao coefficientDao;
	
	
	public void setCoefficientDao(CoefficientDao coefficientDao) {
		this.coefficientDao = coefficientDao;
	}

	@Override
	public Pager getCoefficientPageData(Map<String, String> condition) {
		return coefficientDao.getCoefficientPageData(condition);
	}

	@Override
	public void saveCoefficient(Coefficient coefficient) {
		coefficientDao.saveCoefficient(coefficient);
	}

	@Override
	public Coefficient getCoefficientById(String id) {
		return coefficientDao.getCoefficientById(id);
	}

	@Override
	public void updateCoefficient(Coefficient coefficient) {
		coefficientDao.updateCoefficient(coefficient);
	}

	@Override
	public void deleteCoefficient(String id) {
		coefficientDao.deleteCoefficient(id);
	}

	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	@Override
	public Coefficient getCoefficient(Coefficient a){
		return coefficientDao.getCoefficient(a);
	}
}
