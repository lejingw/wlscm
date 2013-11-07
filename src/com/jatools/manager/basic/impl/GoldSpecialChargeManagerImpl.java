package com.jatools.manager.basic.impl;

import com.jatools.common.Pager;
import com.jatools.dao.basic.GoldSpecialChargeDao;
import com.jatools.manager.basic.GoldSpecialChargeManager;
import com.jatools.vo.basic.GoldSpecialCharge;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

import java.util.Map;

/**
 * @author  ren.ming
 * @Created 2011.11.16
 * <br>
 *  与pos接口数据manager实现类
 */
public class GoldSpecialChargeManagerImpl implements GoldSpecialChargeManager {
    @Override
    public Pager getGoldSpecialChargePageData(Map<String, String> condition) {
        return this.goldSpecialChargeDao.getGoldSpecialChargePageData(condition);
    }

    @Override
    public void saveOrUpdateGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge, String userId) {
        boolean  isExists = this.goldSpecialChargeDao.checkWeightExists(goldSpecialCharge);
        if(isExists) {
            throw   new RuntimeException("重量不允许交叉");
        }
        goldSpecialCharge.setUpdateDate(DateUtil.getCurrentDate18());
        goldSpecialCharge.setUpdateId(userId);
        if(StringUtil.isBlank(goldSpecialCharge.getBillid())) {
            goldSpecialCharge.setCreateDate(DateUtil.getCurrentDate18());
            goldSpecialCharge.setCreateId(userId);
            this.goldSpecialChargeDao.saveGoldSpecialCharge(goldSpecialCharge);
        } else {
            this.goldSpecialChargeDao.updateGoldSpecialCharge(goldSpecialCharge);
        }
    }

    @Override
    public GoldSpecialCharge getGoldSpecialCharge(String billid) {
        return this.goldSpecialChargeDao.getGoldSpecialCharge(billid);
    }

    @Override
    public void deleteGoldSpecialCharge(String billids) {
        if(StringUtil.isNotBlank(billids)) {
            String billidArr[] = billids.split(";");
            for(String billid : billidArr) {
                if(StringUtil.isNotBlank(billid)) {
                    this.goldSpecialChargeDao.deleteGoldSpecialCharge(billid);
                }
            }
        }
    }

    @Override
    public GoldSpecialCharge getGoldSpecialChargeByItemOrna(String itemClassId, String ornaClassId, String weight) {
        return this.goldSpecialChargeDao.getGoldSpecialChargeByItemOrna(itemClassId, ornaClassId, weight);
    }

    private GoldSpecialChargeDao goldSpecialChargeDao;

    public void setGoldSpecialChargeDao(GoldSpecialChargeDao goldSpecialChargeDao) {
        this.goldSpecialChargeDao = goldSpecialChargeDao;
    }
}
