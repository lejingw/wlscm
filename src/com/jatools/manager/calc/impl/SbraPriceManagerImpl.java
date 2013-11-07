package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.SbraPriceDao;
import com.jatools.manager.calc.SbraPriceManager;
import com.jatools.vo.calc.SbraPrice;

public class SbraPriceManagerImpl implements SbraPriceManager {

	private SbraPriceDao sbraPriceDao;
	
	public void setSbraPriceDao(SbraPriceDao sbraPriceDao) {
		this.sbraPriceDao = sbraPriceDao;
	}

	@Override
	public Pager getSbraPricePageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return sbraPriceDao.getSbraPricePageData(condition);
	}

	@Override
	public void saveSbraPrice(SbraPrice sbraPrice) {
		// TODO Auto-generated method stub
		sbraPriceDao.saveSbraPrice(sbraPrice);
	}

	@Override
	public SbraPrice getSbraPriceById(String id) {
		// TODO Auto-generated method stub
		return sbraPriceDao.getSbraPriceById(id);
	}

	@Override
	public void updateSbraPrice(SbraPrice sbraPrice) {
		// TODO Auto-generated method stub
		sbraPriceDao.updateSbraPrice(sbraPrice);
	}

	@Override
	public void deleteSbraPrice(String id) {
		// TODO Auto-generated method stub
		sbraPriceDao.deleteSbraPrice(id);
	}

	@Override
	public SbraPrice getSbraPriceByQualityId(String qid) {
		// TODO Auto-generated method stub
		return sbraPriceDao.getSbraPriceByQualityId(qid);
	}

}
