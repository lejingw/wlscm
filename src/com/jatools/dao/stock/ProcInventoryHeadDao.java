package com.jatools.dao.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcInventoryHead;

/**
 * 盘点单头表Dao
 * @author ren.ming<br>
 * Created 2011-12-1
 */
public interface ProcInventoryHeadDao {

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
	 * 修改头表的状态
	 * @param billid
	 * @param status
	 * @param userId
	 */
	public void modifyProcInventoryHeadStatus(String billid, String status, String userId);
	/**
	 * 删除盘点单头表
	 * @param billid
	 */
	void deleteProcInventoryHead(String billid);
	
	/**
	 * 取得仓库 的主单数量
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public Integer getMainBillCount(String orgId, String stockId);
	
	/**
	 * 设置单据是否为主单
	 * @param billid
	 * @param isMain
	 */
	public void modifyBillIsMain(String billid, String isMain);
	
	/**
	 * 重新计算盘点单盘点件数
	 * @param billid
	 * @param userId
	 */
	void updateInventorySumCount(String billid, String userId);
	
	/**
	 * 合并盘点单
	 * @param mainBillId
	 * @param oldBillIds
	 * @param userId
	 */
	void mergeInventory(String mainBillId, String oldBillIds, String userId);
	
	/**
	 * 判断是否已经合并到其他单据
	 * @param billid
	 * @return
	 */
	boolean asertIsMerged(String billid);
}
