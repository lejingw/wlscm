package com.jatools.dao.calc.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.SbraCoefficientDao;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraCoefficient;

public class SbraCoefficientDaoImpl extends BaseDao implements SbraCoefficientDao {

	@Override
	public Pager getSbraCoefficientPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("SbraCoefficient.getSbraCoefficientAllPageData", "SbraCoefficient.getSbraCoefficientToalCount", condition);
		return pager;
	}

	@Override
	public void saveSbraCoefficient(SbraCoefficient SbraCoefficient) {
		executeInsert("SbraCoefficient.saveSbraCoefficient", SbraCoefficient);
	}

	@Override
	public SbraCoefficient getSbraCoefficientById(String id) {
		// TODO Auto-generated method stub
		return (SbraCoefficient)executeQueryForObject("SbraCoefficient.getSbraCoefficientById", id);
	}

	@Override
	public void updateSbraCoefficient(SbraCoefficient sbraCoefficient) {
		// TODO Auto-generated method stub
		executeUpdate("SbraCoefficient.updateSbraCoefficient", sbraCoefficient);
	}

	@Override
	public void deleteSbraCoefficient(String id) {
		executeUpdate("SbraCoefficient.deleteSbraCoefficient", id);
	}

	@Override
	public SbraCoefficient getSbraCoefficientByQualityId(Map<String, String> condition) {
		return (SbraCoefficient)executeQueryForObject("SbraCoefficient.getSbraCoefficientByQualityId", condition);
	}

	@Override
	public List<Quality> getQualityByItemClassId(String itemClassId) {
		return executeQueryForList("SbraCoefficient.getQualityByItemClassId", itemClassId);
	}
	
}
