package com.jatools.dao.basic.impl;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.GoldSpecialChargeDao;
import com.jatools.vo.basic.GoldSpecialCharge;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-11-1
 * Time: 下午3:44
 */
public class GoldSpecialChargeDaoImpl extends BaseDao implements GoldSpecialChargeDao {
    @Override
    public Pager getGoldSpecialChargePageData(Map<String, String> condition) {
        return this.executeQueryForPager("GoldSpecialCharge.getGoldSpecialChargePageData", "GoldSpecialCharge.getGoldSpecialChargeTotalCount", condition);
    }

    @Override
    public void saveGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge) {
        this.executeInsert("GoldSpecialCharge.saveGoldSpecialCharge", goldSpecialCharge);
    }

    @Override
    public GoldSpecialCharge getGoldSpecialCharge(String billid) {
        return (GoldSpecialCharge)this.executeQueryForObject("GoldSpecialCharge.getGoldSpecialCharge", billid);
    }

    @Override
    public void updateGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge) {
        this.executeUpdate("GoldSpecialCharge.updateGoldSpecialCharge", goldSpecialCharge);
    }

    @Override
    public void deleteGoldSpecialCharge(String id) {
        this.delete("GoldSpecialCharge.deleteGoldSpecialCharge", id);
    }

    @Override
    public boolean checkWeightExists(GoldSpecialCharge goldSpecialCharge) {
        Integer res = (Integer)this.executeQueryForObject("GoldSpecialCharge.checkWeightExists", goldSpecialCharge);
        if(res != null && res.intValue() > 0) {
            return true;
        }
        return false;
    }

    public GoldSpecialCharge getGoldSpecialChargeByItemOrna(String itemClassId, String ornaClassId, String weight) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("itemClassId", itemClassId);
        params.put("ornaClassId", ornaClassId);
        params.put("weight", weight);
        return (GoldSpecialCharge)this.executeQueryForObject("GoldSpecialCharge.getGoldSpecialChargeByItemOrna", params);
    }
}
