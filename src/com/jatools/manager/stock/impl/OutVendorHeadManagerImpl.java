package com.jatools.manager.stock.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.OutVendorHeadDao;
import com.jatools.manager.stock.OutVendorHeadManager;

public class OutVendorHeadManagerImpl implements OutVendorHeadManager {

	private OutVendorHeadDao outVendorHeadDao;
	
	

	public void setOutVendorHeadDao(OutVendorHeadDao outVendorHeadDao) {
		this.outVendorHeadDao = outVendorHeadDao;
	}

	@Override
	public Pager getOutVendorHeadData(Map<String, String> condition) {
		return this.outVendorHeadDao.getOutVendorHeadData(condition);
	}

}
