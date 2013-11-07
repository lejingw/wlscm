package com.jatools.manager.stock.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.OutVendorDao;
import com.jatools.manager.stock.OutVendorManager;

public class OutVendorManagerImpl implements OutVendorManager {

	private OutVendorDao outVendorBillDao;
	
	

	public void setOutVendorBillDao(OutVendorDao outVendorBillDao) {
		this.outVendorBillDao = outVendorBillDao;
	}

	@Override
	public Pager getOutVendorData(Map<String, String> condition) {
		return this.outVendorBillDao.getOutVendorData(condition);
	}

}
