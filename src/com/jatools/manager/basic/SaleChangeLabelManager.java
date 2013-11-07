package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.SaleChangeLabel;

/**
 * @author  ren.ming
 */
public interface SaleChangeLabelManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getSaleChangeLabelPageData(Map<String, String> condition);


	/**
	 * 根据id获取
	 * @param changelabelId
	 * @return
	 */
	SaleChangeLabel getSaleChangeLabel(String changelabelId);

	/**
	 * 删除
	 * @param changelabelId
	 */
	void deleteSaleChangeLabel(String changelabelId);
	
	/**
	 * 保存或修改
	 * @param saleChangeLabel
	 */
	public void saveOrUpdateSaleChangeLabel(SaleChangeLabel saleChangeLabel);

}
