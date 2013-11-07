package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.VendorCoefDao;
import com.jatools.manager.basic.VendorCoefManager;
import com.jatools.vo.basic.VendorCoef;

public class VendorCoefManagerImpl implements VendorCoefManager {
	
	private VendorCoefDao vendorCoefDao;
	
	
	public VendorCoefDao getVendorCoefDao() {
		return vendorCoefDao;
	}

	public void setVendorCoefDao(VendorCoefDao vendorCoefDao) {
		this.vendorCoefDao = vendorCoefDao;
	}

	@Override
	public Pager getVendorCoefPageData(Map<String, String> conditionMap) {
		return this.vendorCoefDao.getVendorCoefPageData(conditionMap);
	}

	@Override
	public void saveVendorCoef(VendorCoef VendorCoef) {
		this.vendorCoefDao.saveVendorCoef(VendorCoef);
	}

	@Override
	public VendorCoef getVendorCoefById(String id) {
		return this.vendorCoefDao.getVendorCoefById(id);
	}

	@Override
	public void updateVendorCoef(VendorCoef VendorCoef) {
		this.vendorCoefDao.updateVendorCoef(VendorCoef);
	}

	@Override
	public void deleteVendorCoef(String id) {
		this.vendorCoefDao.deleteVendorCoef(id);
	}

	@Override
	public String getVendorCoefC(Map<String, String> condition) {
		return this.vendorCoefDao.getVendorCoefC(condition);
	}
	
}
