package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.vo.stock.StockTransDO;

/**
 * 拆包单新行表Dao
 * @author ren.ming<br>
 * Created 2011-11-20
 */
public interface ProcPackageNewlineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcPackageNewlineData(Map<String, String> condition);
	/**
	 * 保存拆包单新行表
	 * @param procPackageNewline
	 */
	void saveProcPackageNewline(ProcPackageNewline procPackageNewline);
	/**
	 * 根据id获取拆包单新行表
	 * @param lineid
	 * @return
	 */
	ProcPackageNewline getProcPackageNewline(String lineid);
	/**
	 * 保存或修改拆包单新行表
	 * @param procPackageNewline
	 */
	void updateProcPackageNewline(ProcPackageNewline procPackageNewline);
	/**
	 * 删除拆包单新行表
	 * @param lineid
	 */
	void deleteProcPackageNewline(String lineid);
	
	/**
	 * 删除拆包单新行表
	 * @param billid
	 */
	void deleteProcPackageNewlineByBillid(String billid);
	
	/**
	 * 根据单号获取拆包单新行表
	 * @param billid
	 * @return
	 */
	List<ProcPackageNewline> getProcPackageNewlineList(String billid);
	
	/**
	 * 拆包混包插入入库事务
	 * @param stockTransDO
	 */
	void inStockInsertTrans(StockTransDO stockTransDO);
	
	/**
	 * 拆包混包 从现有量表插入
	 * @param iniv
	 */
	void insertInivFromMater(MaterIniv iniv);
}
