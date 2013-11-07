package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.StonePriceDao;
import com.jatools.vo.calc.StonePrice;

public class StonePriceDaoImpl extends BaseDao implements StonePriceDao {

	@Override
	public Pager getStonePricePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("StonePrice.getStonePriceAllPageData", "StonePrice.getStonePriceToalCount", condition);
		return pager;
	}

	@Override
	public void saveStonePrice(StonePrice stonePrice) {
		executeInsert("StonePrice.saveStonePrice", stonePrice);
	}

	@Override
	public StonePrice getStonePriceById(String id) {
		StonePrice c = (StonePrice) executeQueryForObject("StonePrice.getStonePriceById", id);
		return c;
	}

	@Override
	public void updateStonePrice(StonePrice stonePrice) {
		executeUpdate("StonePrice.updateStonePrice", stonePrice);
	}

	@Override
	public void deleteStonePrice(String id) {
		executeUpdate("StonePrice.deleteStonePrice", id);
	}

	public boolean existStonePrice(StonePrice sp){
		Integer res = (Integer) executeQueryForObject("StonePrice.existStonePrice", sp);
        if(res != null && res.intValue() >0) {
            return true;
        }
        return false;
	}

    /**
     * 获取主配石市场价
     * @param sp
     * @return
     */
    public StonePrice  getStonePrice(StonePrice sp){
       return (StonePrice) executeQueryForObject("StonePrice.getStonePrice", sp);
    }
}
