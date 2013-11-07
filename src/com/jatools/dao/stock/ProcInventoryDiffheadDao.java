package com.jatools.dao.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcInventoryDiffhead;

/**
 * 盘点差异单头表Dao
 * @author ren.ming<br>
 * Created 2011-12-1
 */
public interface ProcInventoryDiffheadDao {

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
	 * 修改头表的状态
	 * @param billid
	 * @param status
	 * @param userId
	 */
	public void modifyProcInventoryDiffheadStatus(String billid, String status, String userId);
	/**
	 * 删除盘点差异单头表
	 * @param billid
	 */
	void deleteProcInventoryDiffhead(String billid);
	
	/**
	 * 根据盘点单创建差异单头信息
	 * @param srcBillId 盘点单id
	 * @param billno 差异单单据编码
	 * @param userId
	 */
	void insertProcInventoryDiffhead(String srcBillId, String billid, String billno, String userId);
	
	/**
	 * 修改差异单头的统计数据
	 * @param billid
	 * @param userId
	 */
	void updateDiffHeadInfo(String billid, String userId);
	
	String getBillid();
}
