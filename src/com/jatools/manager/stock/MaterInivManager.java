package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.stock.MaterIniv;

public interface MaterInivManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getMaterInivPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	String saveMaterIniv(MaterIniv materIniv);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	MaterIniv getMaterInivById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateMaterIniv(MaterIniv materIniv);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteMaterIniv(String id);
	/**
	 * 获取单品
	 * @param sc
	 * @return
	 */
	public MaterIniv getMaterIniv(MaterIniv a);
	/**
	 * 保存修改单品
	 * @param mater
	 * @return
	 */
	String saveOrUpdateMater(MaterIniv mater);
	/**
	 * 取交接单行表中的成本单价
	 * @param handoverBillId
	 * @param itemClassId
	 * @return
	 */
	String getHangPriceByIniv(String handoverBillId,String itemClassId );
	/**
	 * 标签打印
	 * @param ids
	 * @return
	 */
	List<Tag> printLabels(String[] ids);


    Double getFashionGoldPrice(String itemClassId);
}
