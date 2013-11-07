package com.jatools.dao.calc;

import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.calc.SbraPrice;

public interface SbraPriceDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getSbraPricePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveSbraPrice(SbraPrice sbraPrice);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	SbraPrice getSbraPriceById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateSbraPrice(SbraPrice sbraPrice);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteSbraPrice(String id);
	/**
	 * 材质下的市场价
	 * @param qid
	 * @return
	 */
	public SbraPrice getSbraPriceByQualityId(String qid);
}
