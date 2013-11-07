package com.jatools.dao.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.MaterIniv;

public interface MaterInivDao {

	/**
	 * 计算交接单对应的入库单的饰品总和
	 * @param params
	 * @return
	 */
	public int getSumMaters(String srcBillId, String srcBillCode);
	
	/**
	 * 通过 交接单的id 修改入库单状态
	 * @param status 状态吗
	 * @param userId 修改人员id
	 * @param srcBillId 交接单id
	 */
	void updateBillStatusBySourceId(String fromStatus, String toStatus, String userId, String srcBillId);
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
	 * 取交接单行表中的成本单价
	 * @param handoverBillId
	 * @param itemClassId
	 * @return
	 */
	String getHangPriceByIniv(String handoverBillId,String itemClassId );
	/**
	 * 入库修改核价单状态
	 * @param state
	 */
	void updatePriceStateByIniv(String state,String id);

    Double getFashionGoldPrice(String itemClassId);
}
