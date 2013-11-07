package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.VendorCoefDao;
import com.jatools.vo.basic.VendorCoef;

public class VendorCoefDaoImpl extends BaseDao implements VendorCoefDao {
	@Override
	public Pager getVendorCoefPageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("VendorCoef.getVendorCoefAllPageData", "VendorCoef.getVendorCoefToalCount", condition);
		return pager;
	}

	@Override
	public void saveVendorCoef(VendorCoef VendorCoef) {
		executeInsert("VendorCoef.saveVendorCoef", VendorCoef);
	}

	@Override
	public VendorCoef getVendorCoefById(String id) {
		return (VendorCoef)executeQueryForObject("VendorCoef.getVendorCoefById", id);
	}

	@Override
	public void updateVendorCoef(VendorCoef VendorCoef) {
		executeUpdate("VendorCoef.updateVendorCoef", VendorCoef);
	}

	@Override
	public void deleteVendorCoef(String id) {
		executeUpdate("VendorCoef.deleteVendorCoef", id);
	}

	@Override
	public String getVendorCoefC(Map<String, String> condition) {
		return (String)executeQueryForObject("VendorCoef.getVendorCoefByQualityId", condition);
	}
}
