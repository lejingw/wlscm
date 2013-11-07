package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.BasicPriceOneDao;
import com.jatools.manager.calc.SbraPriceManager;
import com.jatools.manager.calc.BasicPriceOneManager;
import com.jatools.vo.calc.BasicPriceOne;

public class BasicPriceOneManagerImpl implements BasicPriceOneManager {

	private BasicPriceOneDao basicPriceOneDao;
	
	public void setBasicPriceOneDao(BasicPriceOneDao basicPriceOneDao) {
		this.basicPriceOneDao = basicPriceOneDao;
	}

	@Override
	public Pager getBasicPriceOnePageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return basicPriceOneDao.getBasicPriceOnePageData(condition);
	}

	@Override
	public void saveBasicPriceOne(BasicPriceOne basicPriceOne) {
		// TODO Auto-generated method stub
		basicPriceOneDao.saveBasicPriceOne(basicPriceOne);
	}

	@Override
	public BasicPriceOne getBasicPriceOneById(String id) {
		// TODO Auto-generated method stub
		return basicPriceOneDao.getBasicPriceOneById(id);
	}

	@Override
	public void updateBasicPriceOne(BasicPriceOne basicPriceOne) {
		// TODO Auto-generated method stub
		basicPriceOneDao.updateBasicPriceOne(basicPriceOne);
	}

	@Override
	public void deleteBasicPriceOne(String id) {
		// TODO Auto-generated method stub
		basicPriceOneDao.deleteBasicPriceOne(id);
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceOne getBasicPriceOne(BasicPriceOne sp){
		return basicPriceOneDao.getBasicPriceOne(sp);
	}

}
