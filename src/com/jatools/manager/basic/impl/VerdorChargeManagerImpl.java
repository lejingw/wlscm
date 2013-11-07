package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.VerdorChargeDao;
import com.jatools.manager.basic.VerdorChargeManager;
import com.jatools.vo.basic.VerdorCharge;

public class VerdorChargeManagerImpl implements VerdorChargeManager{

	private VerdorChargeDao verdorChargeDao;
	
	
	public VerdorChargeDao getVerdorChargeDao() {
		return verdorChargeDao;
	}

	public void setVerdorChargeDao(VerdorChargeDao verdorChargeDao) {
		this.verdorChargeDao = verdorChargeDao;
	}

	@Override
	public Pager getVerdorChargePageData(Map<String, String> condition) {
		return verdorChargeDao.getVerdorChargePageData(condition);
	}

	@Override
	public void saveVerdorCharge(VerdorCharge verdorCharge) {
		verdorChargeDao.saveVerdorCharge(verdorCharge);
	}

	@Override
	public VerdorCharge getVerdorChargeById(String id) {
		return verdorChargeDao.getVerdorChargeById(id);
	}

	@Override
	public void updateVerdorCharge(VerdorCharge verdorCharge) {
		verdorChargeDao.updateVerdorCharge(verdorCharge);
	}

	@Override
	public void deleteVerdorCharge(String id) {
		verdorChargeDao.deleteVerdorCharge(id);
	}

	@Override
	public VerdorCharge getVerdorCharge(VerdorCharge a) {
		return verdorChargeDao.getVerdorCharge(a);
	}
}
