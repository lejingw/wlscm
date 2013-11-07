package com.jatools.dao.basic.impl;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.FashionGoldPriceDao;
import com.jatools.vo.basic.FashionGoldPrice;

import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-11-12
 * Time: 下午5:15
 */
public class FashionGoldPriceDaoImpl extends BaseDao implements FashionGoldPriceDao {
    @Override
    public Pager getFashionGoldPricePageData(Map<String, String> condition) {
        return executeQueryForPager("FashionGoldPrice.getFashionGoldPricePageData", "FashionGoldPrice.getFashionGoldPriceTotalCount", condition);
    }

    @Override
    public void saveFashionGoldPrice(FashionGoldPrice goldPrice) {
        executeInsert("FashionGoldPrice.saveFashionGoldPrice", goldPrice);
    }

    @Override
    public FashionGoldPrice getFashionGoldPrice(String id) {
        return (FashionGoldPrice)executeQueryForObject("FashionGoldPrice.getFashionGoldPrice", id);
    }

    @Override
    public void updateFashionGoldPrice(FashionGoldPrice goldPrice) {
        executeUpdate("FashionGoldPrice.updateFashionGoldPrice", goldPrice);
    }

    @Override
    public void deleteFashionGoldPrice(String id) {
        delete("FashionGoldPrice.deleteFashionGoldPrice", id);
    }

    @Override
    public boolean isExistsFashionGoldPrice(FashionGoldPrice goldPrice) {
        Integer res = (Integer)executeQueryForObject("FashionGoldPrice.checkWeightStr", goldPrice);
        if(res != null && res.intValue() > 0) {
            return true;
        }
        return false;
    }
}
