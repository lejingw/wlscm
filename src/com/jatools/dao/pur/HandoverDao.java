package com.jatools.dao.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverInStock;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.pur.HandoverPriceStone;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.util.SelectorOption;
@SuppressWarnings("rawtypes")
public interface HandoverDao {

	/**
	 * 获取分页数据
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
	
	public void modifyHandoverHeadStatus(String billid, String status, String userId);
	
	/**
	 * 接收单据
	 * @param billid
	 * @param userId
	 */
	public void receiveHandover(String billid, String userId);
	
	/**
	 * 删除交接单头表
	 * @param billid
	 */
	void deleteHandoverHead(String billid);
	
	
	/**
	 * 插入交接单头表
	 * @param handoverHead
	 */
	public void insertHandoverHead(HandoverHead handoverHead);
	
	/**
	 * 入库统计
	 * @param billid
	 * @return
	 */
	List<HandoverInStock> getInStockList(String billid); 
	
	/**
	 * 其他交接单 入库统计
	 * @param billid
	 * @return
	 */
	List<HandoverInStock> getOtherInStockList(String billid); 
	
	
	/**
	 * 查询 原料钻石 入库统计
	 */
	List<HandoverInStock> getInStockListByYL(String billid);
	
	
	/**
	 * 查询 原料钻石 入库统计 其他入库单
	 */
	List<HandoverInStock> getOtherInStockListByYL(String billid);
	
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
	
	void deleteHandoverLineByBillid(String billid);
	/**
	 * 插入交接单行表
	 * @param handoverLine
	 */
	public void insertHandoverLine(HandoverLine handoverLine);
	
	/**
	 * 根据大类id 取 原料类型
	 * @param itemClassId
	 * @return
	 */
	String getMaterialTypeByItemClassId(String itemClassId);
	
	/**
	 * 根据大类取结算单位
	 * @param itemClassId
	 * @return
	 */
	String getCashUnitByItemClassId(String itemClassId, String materialType);
	
	/**
	 * 修改 磅差
	 * @param billid
	 * @param userId
	 */
	void modifyDiff(String billid, String userId, String diffNum, String itemClassId);
	
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
	 * 根据交接单号 修改 交接单 是否收票状态
	 * @param billno
	 * @param userId
	 */
	void modifyHandoverReceiveStatus(String billno, String status, String userId);
	
	/**
	 * 根据交接单号 修改 交接单 是否收票状态
	 * @param billid
	 * @param userId
	 */
	void modifyHandoverReceiveStatusByBillid(String billid, String status, String userId);
	
	/**
	 * 修改采购单状态
	 * @param id
	 * @param status
	 * @param userId
	 */
	void modifyPurchaseStatus(String id, String status, String userId);
	
	/**
	 * 根据交接单id修改采购单状态
	 * @param billid
	 * @param status
	 * @param userId
	 */
	void modifyPurchaseStatusByHandoverId(String billid, String status, String userId);
	
	/**
	 * 修改现有量为已引用 
	 * @param handoverId
	 */
	void modifyMaterInvalidByHandoverId(String handoverId);
	
	/**
	 * 查询入库单价格锁定的数量
	 * @param handoverId
	 * @return
	 */
	int getInivPriceLockCount(String handoverId);
	
	/**
	 * 根据入库单 插入 价格锁定单行表数据
	 * @param handoverId
	 * @param priceLockId
	 */
	void insertPriceLockLineFromIniv(String handoverId, String priceLockId);
	/**
	 * 装箱单回写快递单号
	 * @param headid 装箱单id
	 * @param expressNo null 表示清除快递单号
	 */
	void updatePackNoFromMovePack(String headid, String billNo, String expressNo);
	
	/**
	 * 根据核价单 修改货品台账
	 * @param handoverId
	 * @param userId
	 */
	void updateProdAccountByCalc(String handoverId, String userId);
	/**
	 * 根据核价单插入货品台账
	 * @param handoverId
	 * @param userId
	 */
	void insertProdAccountByCalc(String handoverId, String userId);
	
	/**
	 * 根据交接单修改 核价单行表核价状态
	 * @param handoverId
	 * @param userId
	 */
	void modifyCalcByHandover(String handoverId, String userId);
	
	/**
	 * 修改交接单的结算状态
	 * @param billid
	 * @param userId
	 */
	void modifyHandoverCashStatus(String billid, String userId);
	
	/**
	 * 取入库单 根据 交接单id
	 * @param handId
	 * @return
	 */
	List<MaterIniv> getMaterInivList(String handId);
	
	/**
	 * 修改入库单成本之前 把成本保存下来
	 * @param inivId
	 */
	void modifyMaterInivOldTotalCost(String inivId);
	
	/**
	 * 修改入库单成本
	 * @param iniv
	 */
	void modifyMaterInivNewTotalCost(MaterIniv iniv);
	
	
	/**
	 * 查询满足条件的核价单的数量
	 * @param handoverId
	 * @return
	 */
	List<HandoverPriceStone> selectPriceStonesByHandover(String handoverId);
	
	/**
	 * 查询满足条件的货品台账
	 * @param handoverId
	 * @return
	 */
	List<CashProdAccount> selectProdAccountByHandover(String handoverId);
	
	List<CashProdAccount> selectProdAccountByHandId(String handoverId);
	
	public void changeHandoverHead(HandoverHead head, String userId);
	
	void changeHandoverLine(HandoverLine line, String userId);
	
	boolean checkHandoverModify(String billid, String updateDate);
	
	void backupHandoverLine(String billid, String userId);
	
	void backupHandoverChild(String billid, String userId);
	 
	
	Pager getHandoverLineRecData(Map<String, String> condition);
	
	int getHistoryCount(String billid);
	
	void subProdAccountUserNumsByHandId(String handId, String userId);
	
	/**
	 * 通过退料单 插入交接单行信息
	 * @param exitBillid
	 * @param handoverId
	 * @param userId
	 */
	void insertLineByExit(String exitBillid, String handoverId, String userId);
}
