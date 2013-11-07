package com.jatools.dao.stock.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.OutVendorHeadDao;
import com.jatools.vo.stock.OutVendorHead;

public class OutVendorHeadDaoImpl extends BaseDao implements OutVendorHeadDao {


	@Override
	public Pager getOutVendorHeadData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("CashProdAccount.getPageData", "CashProdAccount.getTotalCount", condition);
		return pager;
	}
	
	public void insert(OutVendorHead vendorHead) {
		executeInsert("CashProdAccount.insert", vendorHead);
	}
			
}
