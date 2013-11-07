package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcExitHead;
import com.jatools.vo.stock.ProcExitLine;

/**
 * @author  ren.ming<br>
 * Created 2011.11.28
 * <br>
 *  退料单头表manager
 */
public interface ProcExitHeadManager {
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
	 * 保存或修改退料单头表
	 * @param procExitHead
	 */
	void updateProcExitHead(ProcExitHead procExitHead);

	/**
	 * 删除退料单头表
	 * @param billid
	 */
	void deleteProcExitHead(String billid, String userId);
	
	/**
	 * 保存或修改退料单头表
	 * @param ProcExitHead
	 */
	public void saveOrUpdateProcExitHead(ProcExitHead ProcExitHead, List<ProcExitLine> exitLines, String ids, String userId);
	
	
	public void saveAndCheckProcExitHead(ProcExitHead ProcExitHead, List<ProcExitLine> exitLines, String ids, String userId);
	/**
	 * 关闭单据 成功结束单据<br>
	 * @param billid 单据编号
	 * @param userId 当前处理人
	 */
	public void closeExitHead(String billid, String userId);
	
	/**
	 * 提交审核单据
	 * @param billid
	 * @param userId
	 */
	public void checkBillHead(String billid, String userId);

	
	void cashCharge(String billid, String charge, String userId);
}
