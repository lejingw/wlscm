package com.jatools.dao.basic;

import com.jatools.common.Pager;
import com.jatools.vo.basic.GoldSpecialCharge;

import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-11-1
 * Time: 下午3:31
 */
public interface GoldSpecialChargeDao {
    /**
     * 获取分页数据
     *
     * @param condition
     * @return
     */
    Pager getGoldSpecialChargePageData(Map<String, String> condition);
    /**
     * 保存
     * @param goldSpecialCharge
     */
    void saveGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge);
    /**
     * 根据id获取
     * @param billid
     * @return
     */
    GoldSpecialCharge getGoldSpecialCharge(String billid);
    /**
     * 保存或修改
     * @param goldSpecialCharge
     */
    void updateGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge);
    /**
     * 删除
     * @param id
     */
    void deleteGoldSpecialCharge(String id);

    /**
     * 判断重量是否交叉
     * @param goldSpecialCharge
     * @return
     */
    boolean checkWeightExists(GoldSpecialCharge goldSpecialCharge);

    GoldSpecialCharge getGoldSpecialChargeByItemOrna(String itemClassId, String ornaClassId, String weight);
}
