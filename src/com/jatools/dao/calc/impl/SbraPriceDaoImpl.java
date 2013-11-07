package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.SbraPriceDao;
import com.jatools.vo.calc.Coefficient;
import com.jatools.vo.calc.SbraPrice;

public class SbraPriceDaoImpl extends BaseDao implements SbraPriceDao {

	@Override
	public Pager getSbraPricePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("SbraPrice.getSbraPriceAllPageData", "SbraPrice.getSbraPriceToalCount", condition);
		return pager;
	}

	@Override
	public void saveSbraPrice(SbraPrice sbraPrice) {
		executeInsert("SbraPrice.saveSbraPrice", sbraPrice);
	}

	@Override
	public SbraPrice getSbraPriceById(String id) {
		SbraPrice c = (SbraPrice) executeQueryForObject("SbraPrice.getSbraPriceById", id);
		return c;
	}

	@Override
	public void updateSbraPrice(SbraPrice sbraPrice) {
		executeUpdate("SbraPrice.updateSbraPrice", sbraPrice);
	}

	@Override
	public void deleteSbraPrice(String id) {
		executeUpdate("SbraPrice.deleteSbraPrice", id);
	}

	@Override
	public SbraPrice getSbraPriceByQualityId(String qid) {
		// TODO Auto-generated method stub
		return (SbraPrice)executeQueryForObject("SbraPrice.getSbraPriceByQualityId", qid);
	}

}
