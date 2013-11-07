package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.FashionCoefficientDao;
import com.jatools.manager.calc.FashionCoefficientManager;
import com.jatools.vo.calc.FashionCoefficient;

public class FashionCoefficientManagerImpl implements FashionCoefficientManager {

	private FashionCoefficientDao fashionCoefficientDao;
	
	
	public FashionCoefficientDao getFashionCoefficientDao() {
		return fashionCoefficientDao;
	}

	public void setFashionCoefficientDao(FashionCoefficientDao fashionCoefficientDao) {
		this.fashionCoefficientDao = fashionCoefficientDao;
	}

	@Override
	public Pager getFashionCoefficientPageData(Map<String, String> condition) {
		return this.fashionCoefficientDao.getFashionCoefficientPageData(condition);
	}

	@Override
	public void saveFashionCoefficient(FashionCoefficient fashionCoefficient) {
		fashionCoefficientDao.saveFashionCoefficient(fashionCoefficient);
	}

	@Override
	public FashionCoefficient getFashionCoefficientById(String id) {
		return this.fashionCoefficientDao.getFashionCoefficientById(id);
	}

	@Override
	public void updateFashionCoefficient(FashionCoefficient fashionCoefficient) {
		this.fashionCoefficientDao.updateFashionCoefficient(fashionCoefficient);
	}

	@Override
	public void deleteFashionCoefficient(String id) {
		this.fashionCoefficientDao.deleteFashionCoefficient(id);
	}

	@Override
	public FashionCoefficient getFashionCoefficientItemClassId(
			Map<String, String> condition) {
		return this.fashionCoefficientDao.getFashionCoefficientItemClassId(condition);
	}

}
