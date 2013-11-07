package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.SaleChangeLabel;

/**
 * @author  ren.ming<br>
 */
public interface SaleChangeLabelDao {
	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	Pager getSaleChangeLabelPageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param saleChangeLabel
	 */
	void saveSaleChangeLabel(SaleChangeLabel saleChangeLabel);
	/**
	 * 根据id获取
	 * @param changelabelId
	 * @return
	 */
	SaleChangeLabel getSaleChangeLabel(String changelabelId);
	/**
	 * 保存或修改
	 * @param saleChangeLabel
	 */
	void updateSaleChangeLabel(SaleChangeLabel saleChangeLabel);
	/**
	 * 删除
	 * @param changelabelId
	 */
	void deleteSaleChangeLabel(String changelabelId);

	int isExistsChangeLabel(SaleChangeLabel saleChangeLabel);
}
