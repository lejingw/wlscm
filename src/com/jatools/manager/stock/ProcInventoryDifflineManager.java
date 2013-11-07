package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcInventoryDiffline;

/**
 * @author  ren.ming<br>
 * Created 2011.12.1
 * <br>
 * 盘点差异单原行表manager
 */
public interface ProcInventoryDifflineManager {
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
	 * 保存或修改盘点差异单原行表
	 * @param ProcInventoryDiffline
	 */
	public void saveOrUpdateProcInventoryDiffline(ProcInventoryDiffline ProcInventoryDiffline);
	
	/**
	 * 根据单号获取盘点差异单原行表
	 * @param billid
	 * @return
	 */
	public List<ProcInventoryDiffline> getProcInventoryDifflineList(String billid);

	/**
	 * 查询未盘点饰品数量
	 * @param orgId 组织
	 * @param stockId 仓库
	 * @return
	 */
	public int getNoStockCount(String billid, String orgId, String stockId);
}
