package com.jatools.manager.basic;

import com.jatools.common.Pager;
import com.jatools.vo.basic.FashionGoldPrice;

import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-11-12
 * Time: 下午5:29
 */
public interface FashionGoldPriceManager {
    Pager getFashionGoldPricePageData(Map<String, String> condition);

    void saveOrUpdateFashionGoldPrice(FashionGoldPrice goldPrice, String userId);

    FashionGoldPrice getFashionGoldPrice(String id);

    void deleteFashionGoldPrice(String id);
}
