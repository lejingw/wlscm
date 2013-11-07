package com.jatools.manager.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.HandoverCash;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverInStock;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.util.SelectorOption;

@SuppressWarnings("rawtypes")
public interface HandoverManager {

	/**
	 * 获取分页数据 正常交接单
	 * @param condition
	 * @return
	 */
	Pager getHandoverHeadData(Map<String, String> condition);
	/**
	 * 获取报表分页数据 正常交接单(交接单未结算数量)
	 * @param condition
	 * @return
	 */
	Pager getReportHandHandoverHeadData(Map<String, String> condition);
	/**
	 * 获取报表分页数据 正常交接单(交接单未收票数量)
	 * @param condition
	 * @return
	 */
	Pager getReportOutHandoverHeadData(Map<String, String> condition);
	
	/**
	 * 其他交接单
	 * @param condition
	 * @return
	 */
	Pager getOtherHandoverHeadData(Map<String, String> condition);
	/**
	 * 交接单头表
	 * @param handoverHead
	 */
	void saveHandoverHead(HandoverHead handoverHead);
	/**
	 * 根据id获取交接单头表
	 * @param billid
	 * @return
	 */
	HandoverHead getHandoverHead(String billid);
	HandoverHead getHandoverHeadByBillno(String billno);

	HandoverHead getHandoverHeadBySrcBillId(String srcBillid, String srcType);
	/**
	 * 保存或修改交接单头表
	 * @param handoverHead
	 */
	void updateHandoverHead(HandoverHead handoverHead);
	
	/**
	 * 修改头表的状态
	 * @param params
	 */
	public void modifyHandoverHeadStatus(Map params);
	/**
	 * 删除交接单头表
	 * @param billid
	 */
	void deleteHandoverHead(String billid, String userId);
	
	/**
	 * 保存或修改
	 * @param handoverHead
	 * @param lines
	 * @param ids
	 */
	
	void saveOrUpdateOtherHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, String ids);
	
	void saveAndCheckOtherHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, String ids);
	
	void saveOrUpdateHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, List<HandoverCash> cashLines,  String ids[]);
	
	void saveAndCheckHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, List<HandoverCash> cashLines,  String ids[]);
	
	/**
	 * 结算
	 * @param cashs
	 * @param ids
	 * @param userId
	 */
	public void saveOrUpdateCash(HandoverHead head, List<HandoverCash> cashs, String ids, String userId);
	/**
	 * 修改不合格数量/重量
	 * @param head
	 * @param lines
	 */
	void modifyNoNum(HandoverHead head , List<HandoverLine> lines, String userId);
	/**
	 * 审核单据
	 * @param billid
	 * @param userId
	 */
	void checkBillhead(String billid, String userId);
	
	/**
	 * 关闭其他交接单
	 * @param billid
	 * @param userId
	 */
	boolean closeOtherBillHead(String billid, String userId, String orgId);
	
	/**
	 * 关闭单据
	 * @param billid
	 * @param userId
	 * @return
	 */
	boolean closeBillHead(String billid, String userId);
	/**
	 * 接收单据
	 * @param billid
	 * @param lines 交接行表数据
	 * @param userId
	 */
	void receiveBillHead(String billid, List<HandoverLine> lines,  String userId);
	
	/**
	 * 不接收单据
	 * @param billid
	 * @param userId
	 */
	void receiveNoBillHead(String billid, String userId);
	
	/**
	 * 采购入库
	 * @param head
	 * @param userId
	 */
	void materInStock(HandoverHead head, String userId, String orgId);
	
	/**
	 * 入库统计
	 * @param billid 交接单id
	 * @return
	 */
	List<HandoverInStock> getInStockList(String billid); 
	
	
	/**
	 * 修改单据状态
	 * @param billid
	 * @param status
	 * @param userId
	 */
	public void modifyHandoverHeadStatus(String billid, String status, String userId);
	// =====================================================================================================
	// ------------------------------------------行表--------------------------------------------------------
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getHandoverLineData(Map<String, String> condition);
	
	/**
	 * 根据头表billid查行表数据
	 * @param billid
	 * @return
	 */
	List<HandoverLine> getHandoverLineList(String billid);
	
	/**
	 * 交接单行表
	 * @param handoverLine
	 */
	void saveHandoverLine(HandoverLine handoverLine);
	/**
	 * 根据id获取交接单行表
	 * @param lineid
	 * @return
	 */
	HandoverLine getHandoverLine(String lineid);
	/**
	 * 保存或修改交接单行表
	 * @param handoverLine
	 */
	void updateHandoverLine(HandoverLine handoverLine);
	
	/**
	 * 修改行表的状态
	 * @param params
	 */
	public void modifyHandoverLineStatus(Map params);
	
	/**
	 * 删除交接单行表
	 * @param lineid
	 */
	void deleteHandoverLine(String lineid);
	
	/**
	 * 获取原料钻石大类对应的 分析范围
	 * @return
	 */
	List<SelectorOption> getAnalysisList();
	
	/**
	 * 业务类型不为空 未关闭的采购单
	 * @return
	 */
	Pager getPurchaseList(Map<String, String> condition);
	
	
	/**
	 * 其他交接单 入库统计
	 * @param billid
	 * @return
	 */
	List<HandoverInStock> getOtherInStockList(String billid); 
	
	List<HandoverInStock> getOtherInStockListByYL(String billid);
	List<HandoverInStock> getInStockListByYL(String billid);
	
	//void changeHandoverLine(HandoverHead head, List<HandoverLine> lines, String userId);
	
	public Map<String, String> changeHandoverLine(HandoverHead head, List<HandoverLine> lines, String deleteLineIds, String userId);
	
	
	Pager getHandoverLineRecData(Map<String, String> condition);
	
	Pager getHandoverChildRecData(Map<String, String> condition);
	
	/**
	 * 通过退料单 插入交接单行信息
	 * @param exitBillid
	 * @param handoverId
	 * @param userId
	 */
	void insertLineByExit(String exitBillid, String handoverId, String userId);
}
