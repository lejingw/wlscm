package com.jatools.manager.basic.impl;

import com.jatools.common.Pager;
import com.jatools.dao.basic.FashionGoldPriceDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.basic.FashionGoldPriceManager;
import com.jatools.vo.basic.FashionGoldPrice;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-11-12
 * Time: 下午5:30
 */
public class FashionGoldPriceManagerImpl extends BaseManager implements FashionGoldPriceManager {
    @Override
    public CommonDao getCommonDao() {
        return this.commonDao;
    }

    @Override
    public Pager getFashionGoldPricePageData(Map<String, String> condition) {
        return this.fashionGoldPriceDao.getFashionGoldPricePageData(condition);
    }

    @Override
    public void saveOrUpdateFashionGoldPrice(FashionGoldPrice goldPrice, String userId) {
        boolean  isExists = this.fashionGoldPriceDao.isExistsFashionGoldPrice(goldPrice);
        if(isExists) {
            throw new RuntimeException("大类已经存在");
        }
        goldPrice.setUpdateDate(DateUtil.getCurrentDate18());
        goldPrice.setUpdateId(userId);
        if(StringUtil.isNotBlank(goldPrice.getId())) {
            this.fashionGoldPriceDao.updateFashionGoldPrice(goldPrice);
        } else {
            goldPrice.setCreateDate(DateUtil.getCurrentDate18());
            goldPrice.setCreateId(userId);
            this.fashionGoldPriceDao.saveFashionGoldPrice(goldPrice);
        }
    }

    @Override
    public FashionGoldPrice getFashionGoldPrice(String id) {
        return this.fashionGoldPriceDao.getFashionGoldPrice(id);
    }

    @Override
    public void deleteFashionGoldPrice(String ids) {
        if(StringUtil.isNotBlank(ids)){
            String idArr[] = ids.split(";");
            for(String id : idArr) {
                if(StringUtil.isNotBlank(id)) {
                    this.fashionGoldPriceDao.deleteFashionGoldPrice(id);
                }
            }
        }
    }

    private CommonDao commonDao;
    private FashionGoldPriceDao fashionGoldPriceDao;

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public void setFashionGoldPriceDao(FashionGoldPriceDao fashionGoldPriceDao) {
        this.fashionGoldPriceDao = fashionGoldPriceDao;
    }
}
