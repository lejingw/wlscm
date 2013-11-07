package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcPackageOldline;

/**
 * 拆包单原行表Dao
 * @author ren.ming<br>
 * Created 2011-11-20
 */
public interface ProcPackageOldlineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcPackageOldlineData(Map<String, String> condition);
	/**
	 * 保存拆包单原行表
	 * @param procPackageOldline
	 */
	void saveProcPackageOldline(ProcPackageOldline procPackageOldline);
	/**
	 * 根据id获取拆包单原行表
	 * @param lineid
	 * @return
	 */
	ProcPackageOldline getProcPackageOldline(String lineid);
	/**
	 * 保存或修改拆包单原行表
	 * @param procPackageOldline
	 */
	void updateProcPackageOldline(ProcPackageOldline procPackageOldline);
	/**
	 * 删除拆包单原行表
	 * @param lineid
	 */
	void deleteProcPackageOldline(String lineid);
	
	/**
	 * 删除拆包单原行表
	 * @param billid
	 */
	void deleteProcPackageOldlineByBillid(String billid);
	
	/**
	 * 根据单号获取拆包单原行表
	 * @param billid
	 * @return
	 */
	List<ProcPackageOldline> getProcPackageOldlineList(String billid);
	
	/**
	 * 拆包混包 出库写事务
	 * @param lineid
	 * @param transSourceBill
	 * @param transFinance
	 * @param userId
	 */
	void outStockInsertTrans(String lineid, String transSourceBill, String transFinance, String userId);
}
