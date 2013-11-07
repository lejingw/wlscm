package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.BasicPriceTwoDao;
import com.jatools.manager.calc.SbraPriceManager;
import com.jatools.manager.calc.BasicPriceTwoManager;
import com.jatools.vo.calc.BasicPriceTwo;

public class BasicPriceTwoManagerImpl implements BasicPriceTwoManager {

	private BasicPriceTwoDao basicPriceTwoDao;
	
	public void setBasicPriceTwoDao(BasicPriceTwoDao basicPriceTwoDao) {
		this.basicPriceTwoDao = basicPriceTwoDao;
	}

	@Override
	public Pager getBasicPriceTwoPageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return basicPriceTwoDao.getBasicPriceTwoPageData(condition);
	}

	@Override
	public void saveBasicPriceTwo(BasicPriceTwo basicPriceTwo) {
		// TODO Auto-generated method stub
		basicPriceTwoDao.saveBasicPriceTwo(basicPriceTwo);
	}

	@Override
	public BasicPriceTwo getBasicPriceTwoById(String id) {
		// TODO Auto-generated method stub
		return basicPriceTwoDao.getBasicPriceTwoById(id);
	}

	@Override
	public void updateBasicPriceTwo(BasicPriceTwo basicPriceTwo) {
		// TODO Auto-generated method stub
		basicPriceTwoDao.updateBasicPriceTwo(basicPriceTwo);
	}

	@Override
	public void deleteBasicPriceTwo(String id) {
		// TODO Auto-generated method stub
		basicPriceTwoDao.deleteBasicPriceTwo(id);
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceTwo getBasicPriceTwo(BasicPriceTwo sp){
		return basicPriceTwoDao.getBasicPriceTwo(sp);
	}

	@Override
	public BasicPriceTwo getTwoPrice(String value) {
		return basicPriceTwoDao.getTwoPrice(value);
	}

}
