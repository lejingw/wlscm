package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.StonePriceDao;
import com.jatools.manager.calc.SbraPriceManager;
import com.jatools.manager.calc.StonePriceManager;
import com.jatools.vo.calc.StonePrice;

public class StonePriceManagerImpl implements StonePriceManager {

	private StonePriceDao stonePriceDao;
	
	public void setStonePriceDao(StonePriceDao stonePriceDao) {
		this.stonePriceDao = stonePriceDao;
	}

	@Override
	public Pager getStonePricePageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return stonePriceDao.getStonePricePageData(condition);
	}

	@Override
	public void saveStonePrice(StonePrice stonePrice) {
		// TODO Auto-generated method stub
		stonePriceDao.saveStonePrice(stonePrice);
	}

	@Override
	public StonePrice getStonePriceById(String id) {
		// TODO Auto-generated method stub
		return stonePriceDao.getStonePriceById(id);
	}

	@Override
	public void updateStonePrice(StonePrice stonePrice) {
		// TODO Auto-generated method stub
		stonePriceDao.updateStonePrice(stonePrice);
	}

	@Override
	public void deleteStonePrice(String id) {
		// TODO Auto-generated method stub
		stonePriceDao.deleteStonePrice(id);
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public boolean existStonePrice(StonePrice sp){
		return stonePriceDao.existStonePrice(sp);
	}

    /**
     * 获取主配石市场价
     * @param sp
     * @return
     */
    public StonePrice getStonePrice(StonePrice sp){
        return stonePriceDao.getStonePrice(sp);
    }
}
