package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.StoneCoefficientDao;
import com.jatools.manager.calc.StoneCoefficientManager;
import com.jatools.vo.calc.StoneCoefficient;

public class StoneCoefficientManagerImpl implements StoneCoefficientManager {

	private StoneCoefficientDao stoneCoefficientDao;
	
	
	public void setStoneCoefficientDao(StoneCoefficientDao stoneCoefficientDao) {
		this.stoneCoefficientDao = stoneCoefficientDao;
	}

	@Override
	public Pager getStoneCoefficientPageData(Map<String, String> condition) {
		return stoneCoefficientDao.getStoneCoefficientPageData(condition);
	}

	@Override
	public void saveStoneCoefficient(StoneCoefficient stoneCoefficient) {
		stoneCoefficientDao.saveStoneCoefficient(stoneCoefficient);
	}

	@Override
	public StoneCoefficient getStoneCoefficientById(String id) {
		return stoneCoefficientDao.getStoneCoefficientById(id);
	}

	@Override
	public void updateStoneCoefficient(StoneCoefficient stoneCoefficient) {
		stoneCoefficientDao.updateStoneCoefficient(stoneCoefficient);
	}

	@Override
	public void deleteStoneCoefficient(String id) {
		stoneCoefficientDao.deleteStoneCoefficient(id);
	}

	@Override
	public StoneCoefficient getStoneCoefficient(StoneCoefficient sc) {
		return stoneCoefficientDao.getStoneCoefficient(sc);
	}

}
