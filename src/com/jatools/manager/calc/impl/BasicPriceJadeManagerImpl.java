package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.BasicPriceJadeDao;
import com.jatools.manager.calc.SbraPriceManager;
import com.jatools.manager.calc.BasicPriceJadeManager;
import com.jatools.vo.calc.BasicPriceJade;

public class BasicPriceJadeManagerImpl implements BasicPriceJadeManager {

	private BasicPriceJadeDao basicPriceJadeDao;
	
	public void setBasicPriceJadeDao(BasicPriceJadeDao basicPriceJadeDao) {
		this.basicPriceJadeDao = basicPriceJadeDao;
	}

	@Override
	public Pager getBasicPriceJadePageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return basicPriceJadeDao.getBasicPriceJadePageData(condition);
	}

	@Override
	public void saveBasicPriceJade(BasicPriceJade basicPriceJade) {
		// TODO Auto-generated method stub
		basicPriceJadeDao.saveBasicPriceJade(basicPriceJade);
	}

	@Override
	public BasicPriceJade getBasicPriceJadeById(String id) {
		// TODO Auto-generated method stub
		return basicPriceJadeDao.getBasicPriceJadeById(id);
	}

	@Override
	public void updateBasicPriceJade(BasicPriceJade basicPriceJade) {
		// TODO Auto-generated method stub
		basicPriceJadeDao.updateBasicPriceJade(basicPriceJade);
	}

	@Override
	public void deleteBasicPriceJade(String id) {
		// TODO Auto-generated method stub
		basicPriceJadeDao.deleteBasicPriceJade(id);
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceJade getBasicPriceJade(BasicPriceJade sp){
		return basicPriceJadeDao.getBasicPriceJade(sp);
	}

}
