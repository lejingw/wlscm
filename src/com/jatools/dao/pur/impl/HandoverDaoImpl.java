package com.jatools.dao.pur.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.pur.HandoverDao;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverInStock;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.pur.HandoverPriceStone;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HandoverDaoImpl extends BaseDao implements HandoverDao, ReviewActionIntf {
	
	@Override
	public void insertHandoverHead(HandoverHead handoverHead) {
		executeInsert("handoverHead.insert", handoverHead);
	}

	@Override
	public void insertHandoverLine(HandoverLine handoverLine) {
		executeInsert("handoverLine.insert", handoverLine);
	}

	@Override
	public Pager getHandoverHeadData(Map<String, String> condition) {
		condition.put("sourceType", GlobalConstant.BILL_CODE_CAIGOUDAN);
		condition.put("other", null);
		return executeQueryForPager("HandoverHead.getHandoverHeadPageData", "HandoverHead.getHandoverHeadTotalCount", condition);
	}
	@Override
	public Pager getReportHandHandoverHeadData(Map<String, String> condition) {
		return executeQueryForPager("HandoverHead.getReportHandHandoverHeadData", "HandoverHead.getReportHandHandoverHeadTotalCount", condition);
	}
	@Override
	public Pager getReportOutHandoverHeadData(Map<String, String> condition) {
		return executeQueryForPager("HandoverHead.getReportOutHandoverHeadData", "HandoverHead.getReportOutHandoverHeadTotalCount", condition);
	}
	
	public Pager getOtherHandoverHeadData(Map<String, String> condition) {
		condition.put("other", "1");
		condition.put("sourceType", null);
		return executeQueryForPager("HandoverHead.getHandoverHeadPageData", "HandoverHead.getHandoverHeadTotalCount", condition);
	}

	@Override
	public void saveHandoverHead(HandoverHead handoverHead) {
		executeInsert("HandoverHead.saveHandoverHead", handoverHead);
	}

	@Override
	public HandoverHead getHandoverHead(String billid) {
		return (HandoverHead)executeQueryForObject("HandoverHead.getHandoverHead", billid);
	}

    @Override
    public HandoverHead getHandoverHeadByBillno(String billno) {
        return (HandoverHead)executeQueryForObject("HandoverHead.getHandoverHeadByBillno", billno);
    }

    public HandoverHead getHandoverHeadBySrcBillId(String srcBillid, String srcType){
		Map<String , String> params = new HashMap<String, String>();
		params.put("srcBillid", srcBillid);
		params.put("srcType", srcType);
		return (HandoverHead)executeQueryForObject("HandoverHead.getHandoverHeadBySrcBillId", params);
	}

	@Override
	public void updateHandoverHead(HandoverHead handoverHead) {
		executeUpdate("HandoverHead.updateHandoverHead", handoverHead);
	}

	@Override
	public void modifyHandoverHeadStatus(Map params) {
		executeUpdate("HandoverHead.updateHandoverHeadStatus", params);
	}

	public void modifyHandoverHeadStatus(String billid, String status, String userId){
		Map<String , String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("HandoverHead.updateHandoverHeadStatus", params);
	}
	
	public void receiveHandover(String billid, String userId){
		Map<String , String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("HandoverHead.receiveHandoverHead", params);
	}
	
	@Override
	public void deleteHandoverHead(String billid) {
		executeUpdate("HandoverHead.deleteHandoverHead", billid);
	}

	@Override
	public Pager getHandoverLineData(Map<String, String> condition) {
		return executeQueryForPager("HandoverLine.getHandoverLinePageData", "HandoverLine.getHandoverLineTotalCount", condition);
	}

	public List<HandoverLine> getHandoverLineList(String billid) {
		return (List<HandoverLine>)executeQueryForList("HandoverLine.getHandoverLineList", billid);
	}
	@Override
	public void saveHandoverLine(HandoverLine handoverLine) {
		executeInsert("HandoverLine.saveHandoverLine", handoverLine);
	}

	@Override
	public HandoverLine getHandoverLine(String lineid) {
		return (HandoverLine)executeQueryForObject("HandoverLine.getHandoverLine", lineid);
	}

	@Override
	public void updateHandoverLine(HandoverLine handoverLine) {
		executeUpdate("HandoverLine.updateHandoverLine", handoverLine);
	}

	@Override
	public void modifyHandoverLineStatus(Map params) {
		executeUpdate("HandoverLine.updateHandoverLineStatus", params);
	}

	@Override
	public void deleteHandoverLine(String lineid) {
		executeUpdate("HandoverLine.deleteHandoverLine", lineid);
	}
	
	@Override
	public void deleteHandoverLineByBillid(String billid) {
		executeUpdate("HandoverLine.deleteHandoverLineByBillid", billid);
	}

	@Override
	public void reviewSuccess(String billid, String userid) {
		modifyHandoverHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
	}
	
	@Override
	public void reviewFail(String billid, String userid) {
		modifyHandoverHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}

	public String getMaterialTypeByItemClassId(String itemClassId){
		Object obj = executeQueryForObject("handover.getMaterialTypeByItemClassId", itemClassId);
		if(null != obj){
			return (String)obj;
		}
		return null;
	}
	
	public String getCashUnitByItemClassId(String itemClassId, String materialType){
		Map<String, String> params = new HashMap<String, String>();
		params.put("itemClassId", itemClassId);
		params.put("materialType", materialType);
		Object obj = executeQueryForObject("handover.getCashUnitByItemClassId", params);
		if(null != obj){
			return (String)obj;
		}
		return null;
	}
	
	public List<HandoverInStock> getInStockList(String billid){
		return (List<HandoverInStock>)executeQueryForList("handover.getInStockList", billid);
	} 
	
	public List<HandoverInStock> getOtherInStockList(String billid){
		return (List<HandoverInStock>)executeQueryForList("handover.getOtherInStockList", billid);
	}
	
	public List<HandoverInStock> getInStockListByYL(String billid){
		return (List<HandoverInStock>)executeQueryForList("handover.getInStockListByYL", billid);
	}
	
	public List<HandoverInStock> getOtherInStockListByYL(String billid){
		return (List<HandoverInStock>)executeQueryForList("handover.getOtherInStockListByYL", billid);
	}
	
	public void modifyDiff(String billid, String userId, String diffNum, String itemClassId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateId", userId);
		params.put("diffNum", diffNum);
		params.put("itemClassId", itemClassId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("HandoverLine.modifyDiff", params);
	}
	
	public List<SelectorOption> getAnalysisList(){
		return (List<SelectorOption>)executeQueryForList("Handover.getAnalysisList", null);
	}
	
	public Pager getPurchaseList(Map<String, String> condition){
		return executeQueryForPager("Handover.getPurchaseList", "Handover.getPurchaseCount", condition);
	}

	@Override
	public void modifyHandoverReceiveStatus(String billno, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("userId", userId);
		params.put("status", status);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("HandoverLine.modifyHandoverReceiveStatus", params);
	}
	
	public void modifyHandoverReceiveStatusByBillid(String billid, String status, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("userId", userId);
		params.put("status", status);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("HandoverLine.modifyHandoverReceiveStatusByBillid", params);
	}

	@Override
	public void modifyPurchaseStatus(String id, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("userId", userId);
		params.put("status", status);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("Handover.modifyPurchaseStatus", params);
	}

	@Override
	public void modifyPurchaseStatusByHandoverId(String billid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("userId", userId);
		params.put("status", status);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("Handover.modifyPurchaseStatusByHandoverId", params);
	}

	@Override
	public void modifyMaterInvalidByHandoverId(String handoverId) {
		executeUpdate("Handover.modifyMaterInvalidByHandoverId", handoverId);
	}

	@Override
	public int getInivPriceLockCount(String handoverId) {
		Integer result = (Integer)executeQueryForObject("Handover.getInivPriceLockCount", handoverId);
		return result==null?0:result.intValue();
	}

	@Override
	public void insertPriceLockLineFromIniv(String handoverId, String priceLockId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("handoverId", handoverId);
		params.put("priceLockId", priceLockId);
		executeUpdate("Handover.insertPriceLockLineFromIniv", params);
	}

	/**
	 * 装箱单回写快递单号
	 * @param headid 装箱单id
	 * @param expressNo null 表示清除快递单号
	 */
	public void updatePackNoFromMovePack(String headid, String billNo, String expressNo){
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("billNo", billNo);
		params.put("expressNo", expressNo);
		executeUpdate("Handover.updatePackNoFromMovePack", params);
	}

	@Override
	public void updateProdAccountByCalc(String handoverId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("srcBillId", handoverId);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("Handover.updateProdAccountByCalc", params);
	}

	@Override
	public void insertProdAccountByCalc(String handoverId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("srcBillId", handoverId);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("createDate", DateUtil.getCurrentDate18());
		executeUpdate("Handover.insertProdAccountByCalc", params);
	}

	@Override
	public void modifyCalcByHandover(String handoverId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("srcBillId", handoverId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("Handover.modifyCalcByHandover", params);
	}

	@Override
	public void modifyHandoverCashStatus(String billid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("userId", userId);
		params.put("udpateDate", DateUtil.getCurrentDate18());
		executeUpdate("Handover.modifyHandoverCashStatus", params);
	}

	@Override
	public List<MaterIniv> getMaterInivList(String handId) {
		return (List<MaterIniv>)executeQueryForList("Handover.getMaterInivList", handId);
	}

	@Override
	public void modifyMaterInivOldTotalCost(String inivId) {
		executeUpdate("Handover.modifyMaterInivOldTotalCost", inivId);
	}

	@Override
	public void modifyMaterInivNewTotalCost(MaterIniv iniv) {
		executeUpdate("Handover.modifyMaterInivNewTotalCost", iniv);
	}

	@Override
	public List<HandoverPriceStone> selectPriceStonesByHandover(String handoverId) {
		return (List<HandoverPriceStone>)executeQueryForList("Handover.selectPriceStonesByHandover", handoverId);
	}

	@Override
	public List<CashProdAccount> selectProdAccountByHandover(String handoverId) {
		return (List<CashProdAccount>)executeQueryForList("Handover.selectProdAccountByHandover", handoverId);
	}

	@Override
	public List<CashProdAccount> selectProdAccountByHandId(String handoverId) {
		return (List<CashProdAccount>)executeQueryForList("Handover.selectProdAccountByHandId", handoverId);
	}
	
	public void changeHandoverHead(HandoverHead head, String userId){
		head.setUpdateDate(DateUtil.getCurrentDate18());
		head.setUpdateId(userId);
		executeUpdate("Handover.updateHandoverHead", head);
	}
	@Override
	public void changeHandoverLine(HandoverLine line, String userId) {
		line.setUpdateDate(DateUtil.getCurrentDate18());
		line.setUpdateId(userId);
		if(StringUtil.isNotBlank(line.getLineid())){
			executeUpdate("Handover.updateHandoverLine", line);
		} else {
			line.setCreateDate(DateUtil.getCurrentDate18());
			line.setCreateId(userId);
			this.saveHandoverLine(line);
		}
	}

	@Override
	public boolean checkHandoverModify(String billid, String updateDate) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateDate", updateDate);
		Integer res = (Integer)executeQueryForObject("Handover.checkHandoverModify", params);
		if(res != null && res.intValue()>0){
			return true;
		}
		return false;
	}
	
	public void backupHandoverLine(String billid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("updateId", userId);
		executeInsert("Handover.backuphandoverLine", params);
	}

	public void backupHandoverChild(String billid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("updateId", userId);
		executeInsert("Handover.backupHandoverchild", params);
	}
	
	@Override
	public Pager getHandoverLineRecData(Map<String, String> condition) {
		int resCount = this.getHistoryCount(condition.get("billid"));
		if(resCount > 0){
			return executeQueryForPager("HandoverLineRec.getHandoverLineRecPageData", "HandoverLineRec.getHandoverLineRecTotalCount", condition);
		} else {
			return new Pager();
		}
	}

	@Override
	public int getHistoryCount(String billid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		Integer res = (Integer)executeQueryForObject("HandoverLineRec.getHistoryCount", params);
		if(res!=null){
			return res.intValue();
		}
		return 0;
	}
	
	public void subProdAccountUserNumsByHandId(String handId, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("handId", handId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("updateId", userId);
		executeInsert("Handover.subProdAccountUserNumsByHandId", params);
	}
	
	public void insertLineByExit(String exitBillid, String handoverId, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("exitBillid", exitBillid);
		params.put("handoverId", handoverId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("createId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("updateId", userId);
		executeInsert("Handover.insertLineByExit", params);
	}
}
