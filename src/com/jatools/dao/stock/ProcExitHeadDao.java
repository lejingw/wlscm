package com.jatools.dao.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.stock.ProcExitHead;

/**
 * 退料单头表Dao
 * @author ren.ming<br>
 * Created 2011-11-28
 */
public interface ProcExitHeadDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcExitHeadData(Map<String, String> condition);
	Pager getReportProcExitHeadData(Map<String, String> condition);
	/**
	 * 保存退料单头表
	 * @param procExitHead
	 */
	void saveProcExitHead(ProcExitHead procExitHead);
	/**
	 * 根据id获取退料单头表
	 * @param billid
	 * @return
	 */
	ProcExitHead getProcExitHead(String billid);

	ProcExitHead getProcExitHeadByBillno(String billno);
	/**
	 * 保存或修改拆包混包单头表
	 * @param procExitHead
	 */
	void updateProcExitHead(ProcExitHead procExitHead);
	
	/**
	 * 修改头表的状态
	 * @param params
	 */
	@SuppressWarnings("rawtypes")
	public void modifyProcExitHeadStatus(Map params);
	
	public void modifyProcExitHeadStatus(String billid, String status, String userId);
	/**
	 * 删除退料单头表
	 * @param billid
	 */
	void deleteProcExitHead(String billid);
	
	/**
	 * 根据退料单插入 货品台账
	 * @param billid
	 * @param userId
	 */
	void insertProdccountByExit(String billid, String userId);
	
	/**
	 * 修改台账状态为引用
	 * @param billno
	 * @param ornaCode
	 */
	void modifyProdAccountStatusInValid(String billno, String ornaCode, String userId);
	
	/**
	 * 修改台账为记账状态
	 * @param lineid
	 * @param userId
	 */
	void modifyProdAccountStatusValid(String lineid, String userId);
	
	void saveCashMoneyAccount(String billid, double money, double billType, String userId);
	
	CashProdAccount getProdAccountByOut(String billno, String ornaCode);
	
	void subProdAccountUserNums(String billid, String userId);
}
