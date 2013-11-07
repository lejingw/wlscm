package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcInventoryDiffline;

/**
 * 盘点差异单原行表Dao
 * @author ren.ming<br>
 * Created 2011-12-1
 */
public interface ProcInventoryDifflineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcInventoryDifflineData(Map<String, String> condition);
	/**
	 * 保存盘点差异单原行表
	 * @param ProcInventoryDiffline
	 */
	void saveProcInventoryDiffline(ProcInventoryDiffline ProcInventoryDiffline);
	/**
	 * 根据id获取盘点差异单原行表
	 * @param lineid
	 * @return
	 */
	ProcInventoryDiffline getProcInventoryDiffline(String lineid);
	/**
	 * 保存或修改盘点差异单原行表
	 * @param ProcInventoryDiffline
	 */
	void updateProcInventoryDiffline(ProcInventoryDiffline ProcInventoryDiffline);
	/**
	 * 删除盘点差异单原行表
	 * @param lineid
	 */
	void deleteProcInventoryDiffline(String lineid);
	
	/**
	 * 删除盘点差异单原行表
	 * @param billid
	 */
	void deleteProcInventoryDifflineByBillid(String billid);
	
	/**
	 * 根据单号获取盘点差异单原行表
	 * @param billid
	 * @return
	 */
	List<ProcInventoryDiffline> getProcInventoryDifflineList(String billid);
	
	/**
	 * 查询未盘点饰品数量
	 * @param params
	 * @return
	 */
	public int getNoStockCount(String billid, String orgId, String stockId);
	
	/**
	 * 插入盘点差异单行表
	 * @param params
	 */
	public void insertDiffLines(Map<String, String> params);
	
	/**
	 * 插入盘点差异单盘盈行数据
	 * @param inventId
	 * @param diffId
	 * @param userId
	 */
	public void insertDiffSignLines(String inventId, String diffId, String userId);
	
	public void insertDiffLinesByInvent(String srcBillid, String billid, String userId);
}
