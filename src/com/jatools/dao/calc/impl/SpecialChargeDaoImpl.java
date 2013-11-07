package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.SpecialChargeDao;
import com.jatools.vo.calc.SpecialCharge;

public class SpecialChargeDaoImpl extends BaseDao implements SpecialChargeDao{
	@Override
	public Pager getSpecialChargePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("SpecialCharge.getSpecialChargeAllPageData", "SpecialCharge.getSpecialChargeToalCount", condition);
		return pager;
	}

	@Override
	public void saveSpecialCharge(SpecialCharge specialCharge) {
		executeInsert("SpecialCharge.saveSpecialCharge", specialCharge);
	}

	@Override
	public SpecialCharge getSpecialChargeById(String id) {
		SpecialCharge c = (SpecialCharge) executeQueryForObject("SpecialCharge.getSpecialChargeById", id);
		return c;
	}

	@Override
	public void updateSpecialCharge(SpecialCharge specialCharge) {
		executeUpdate("SpecialCharge.updateSpecialCharge", specialCharge);
	}

	@Override
	public void deleteSpecialCharge(String id) {
		executeUpdate("SpecialCharge.deleteSpecialCharge", id);
	}

	@Override
	public String getSpecialCharge(SpecialCharge sc) {
		return (String)executeQueryForObject("SpecialCharge.getSpecialCharge", sc);
	}
}
