package com.jatools.dao.basic;

import com.jatools.common.Pager;
import com.jatools.vo.basic.Charge;
import com.jatools.vo.basic.FashionGoldPrice;

import java.util.Map;

public interface FashionGoldPriceDao {

	Pager getFashionGoldPricePageData(Map<String, String> condition);

	void saveFashionGoldPrice(FashionGoldPrice goldPrice);

	FashionGoldPrice getFashionGoldPrice(String id);

	void updateFashionGoldPrice(FashionGoldPrice goldPrice);

	void deleteFashionGoldPrice(String id);

    boolean isExistsFashionGoldPrice(FashionGoldPrice goldPrice);
}
