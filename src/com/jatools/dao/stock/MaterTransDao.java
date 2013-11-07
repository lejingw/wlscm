package com.jatools.dao.stock;

import com.jatools.vo.stock.MaterTrans;

public interface MaterTransDao {

	/**
	 * 入库事务
	 * @param matertrans
	 */
	public void insertInStock(MaterTrans matertrans);
	
	/**
	 * 出库事务
	 * @param matertrans
	 */
	public void insertOutStock(MaterTrans matertrans);
	
	/**
	 * 从入库单 插入入库事务
	 * @param srcBillId 入库单中对应的交接单id
	 * @param srcBillCode 入库单对应的交接单类型
	 * @param userId 创建人id
	 * @param transSrcBillCode 事务来源单据类型
	 * @param transSrcBillNo 事务来源单据编码
	 */
	void insertFromMaterIniv(String srcBillId, String srcBillCode, String userId, String transSrcBillCode, String transSrcBillNo);
	
	/**
	 * 从形态转换|料提纯|拆石  进行出库 事务插入
	 * @param srcBillId  形态转换|料提纯|拆石 头表id
	 * @param srcBillCode 形态转换|料提纯|拆石 头表类型
	 * @param userId 创建人员 id
	 */
	void insertFromChangeLine(String srcBillId, String srcBillCode, String userId);

	/**
	 * 写事物处理表
	 * @param billCode
	 * @param headid
	 * @param userid
	 * @param outFlag
	 */
	public void insertFromMoveBill(String billCode, String headid, String userid, boolean outFlag);
}
