package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcInventoryHead;
import com.jatools.vo.stock.ProcInventoryLine;

/**
 * @author  ren.ming<br>
 * Created 2011.12.1
 * <br>
 *  盘点单头表manager
 */
public interface ProcInventoryHeadManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcInventoryHeadData(Map<String, String> condition);

	/**
	 * 保存盘点单头表
	 * @param procInventoryHead
	 */
	void saveProcInventoryHead(ProcInventoryHead procInventoryHead);

	/**
	 * 根据id获取盘点单头表
	 * @param billid
	 * @return
	 */
	ProcInventoryHead getProcInventoryHead(String billid);

	/**
	 * 保存或修改盘点单头表
	 * @param procInventoryHead
	 */
	void updateProcInventoryHead(ProcInventoryHead procInventoryHead);

	/**
	 * 删除盘点单头表
	 * @param billid
	 */
	void deleteProcInventoryHead(String billid);
	
	/**
	 * 保存或修改盘点单头表
	 * @param ProcInventoryHead
	 */
	public void saveOrUpdateProcInventoryHead(ProcInventoryHead ProcInventoryHead, List<ProcInventoryLine> lines);
	
	/**
	 * 关闭单据 成功结束单据<br>
	 * @param billid 单据编号
	 * @param userId 当前处理人
	 */
	public void closeInventoryHead(String billid, String userId);
	
	public void openInventoryHead(String billid, String userId);
	/**
	 * 修改盘点主单的状态
	 * @param billid 单据id
	 * @param orgId 组织id
	 * @param stockId 仓库id
	 * @param ismain 是否主单
	 * @return
	 */
	public boolean changeIsmain(String billid, String orgId, String stockId, boolean ismain) ;
	
	/**
	 * 合并盘点单
	 * @param billids
	 * @param userId
	 */
	public boolean mergeBills(String billids, String userId) ;

}
