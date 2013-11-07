package com.jatools.manager.calc.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.SbraCoefficientDao;
import com.jatools.manager.calc.SbraCoefficientManager;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraCoefficient;

public class SbraCoefficientManagerImpl implements SbraCoefficientManager{

	private SbraCoefficientDao sbraCoefficientDao; 
	
	public void setSbraCoefficientDao(SbraCoefficientDao sbraCoefficientDao) {
		this.sbraCoefficientDao = sbraCoefficientDao;
	}

	@Override
	public Pager getSbraCoefficientPageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return sbraCoefficientDao.getSbraCoefficientPageData(condition);
	}

	@Override
	public void saveSbraCoefficient(SbraCoefficient SbraCoefficient) {
		// TODO Auto-generated method stub
		sbraCoefficientDao.saveSbraCoefficient(SbraCoefficient);
	}

	@Override
	public SbraCoefficient getSbraCoefficientById(String id) {
		// TODO Auto-generated method stub
		return sbraCoefficientDao.getSbraCoefficientById(id);
	}

	@Override
	public void updateSbraCoefficient(SbraCoefficient SbraCoefficient) {
		// TODO Auto-generated method stub
		sbraCoefficientDao.updateSbraCoefficient(SbraCoefficient);
	}

	@Override
	public void deleteSbraCoefficient(String id) {
		// TODO Auto-generated method stub
		sbraCoefficientDao.deleteSbraCoefficient(id);
	}

	@Override
	public SbraCoefficient getSbraCoefficientByQualityId(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return sbraCoefficientDao.getSbraCoefficientByQualityId(condition);
	}

	@Override
	public List<Quality> getQualityByItemClassId(String itemClassId) {
		// TODO Auto-generated method stub
		return sbraCoefficientDao.getQualityByItemClassId(itemClassId);
	}

}
