package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.SpecialChargeDao;
import com.jatools.manager.calc.SpecialChargeManager;
import com.jatools.vo.calc.SpecialCharge;

public class SpecialChargeManagerImpl implements SpecialChargeManager{
	private SpecialChargeDao specialChargeDao;
	
	public void setSpecialChargeDao(SpecialChargeDao specialChargeDao) {
		this.specialChargeDao = specialChargeDao;
	}

	@Override
	public Pager getSpecialChargePageData(Map<String, String> condition) {
		return specialChargeDao.getSpecialChargePageData(condition);
	}

	@Override
	public void saveSpecialCharge(SpecialCharge specialCharge) {
		specialChargeDao.saveSpecialCharge(specialCharge);
	}

	@Override
	public SpecialCharge getSpecialChargeById(String id) {
		return specialChargeDao.getSpecialChargeById(id);
	}

	@Override
	public void updateSpecialCharge(SpecialCharge specialCharge) {
		specialChargeDao.updateSpecialCharge(specialCharge);
	}

	@Override
	public void deleteSpecialCharge(String id) {
		specialChargeDao.deleteSpecialCharge(id);
	}

	@Override
	public String getSpecialCharge(SpecialCharge sc) {
		return specialChargeDao.getSpecialCharge(sc);
	}
}
