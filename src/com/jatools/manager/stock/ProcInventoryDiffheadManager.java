package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcInventoryDiffhead;
import com.jatools.vo.stock.ProcInventoryDiffline;

/**
 * @author  ren.ming<br>
 * Created 2011.12.1
 * <br>
 *  盘点差异单头表manager
 */
public interface ProcInventoryDiffheadManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcInventoryDiffheadData(Map<String, String> condition);

	/**
	 * 保存盘点差异单头表
	 * @param ProcInventoryDiffhead
	 */
	void saveProcInventoryDiffhead(ProcInventoryDiffhead ProcInventoryDiffhead);

	/**
	 * 根据id获取盘点差异单头表
	 * @param billid
	 * @return
	 */
	ProcInventoryDiffhead getProcInventoryDiffhead(String billid);

	/**
	 * 保存或修改盘点差异单头表
	 * @param ProcInventoryDiffhead
	 */
	void updateProcInventoryDiffhead(ProcInventoryDiffhead ProcInventoryDiffhead);

	/**
	 * 删除盘点差异单头表
	 * @param billid
	 */
	void deleteProcInventoryDiffhead(String billid);
	
	/**
	 * 保存或修改盘点差异单头表
	 * @param ProcInventoryDiffhead
	 */
	public void saveOrUpdateProcInventoryDiffhead(ProcInventoryDiffhead ProcInventoryDiffhead, List<ProcInventoryDiffline> lines, String ids[]);
	
	/**
	 * 关闭单据 成功结束单据<br>
	 * @param billid 单据编号
	 * @param userId 当前处理人
	 */
	public void closeBill(String billid, String userId);
	
	
	/**
	 * 生成差异单
	 * @param billid 盘点单据id
	 * @param userId 用户id
	 */
	public void createDiffBill(String billid, String userId);
	
	

}
