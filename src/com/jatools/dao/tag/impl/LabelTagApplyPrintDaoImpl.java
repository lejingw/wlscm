package com.jatools.dao.tag.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.tag.LabelTagApplyPrintDao;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.tag.LabelTagApplyPrint;
import com.jatools.web.util.DateUtil;

public class LabelTagApplyPrintDaoImpl extends BaseDao implements LabelTagApplyPrintDao, ReviewActionIntf {

	@Override
	public Pager getLabelTagApplyPrintData(Map<String, String> condition) {
		return executeQueryForPager("LabelTagApplyPrint.getLabelTagApplyPrintPageData", "LabelTagApplyPrint.getLabelTagApplyPrintTotalCount", condition);
	}

	@Override
	public void saveLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint) {
		executeInsert("LabelTagApplyPrint.saveLabelTagApplyPrint", LabelTagApplyPrint);
	}

	@Override
	public LabelTagApplyPrint getLabelTagApplyPrint(String id) {
		return (LabelTagApplyPrint)executeQueryForObject("LabelTagApplyPrint.getLabelTagApplyPrint", id);
	}

	@Override
	public void updateLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint) {
		executeUpdate("LabelTagApplyPrint.updateLabelTagApplyPrint", LabelTagApplyPrint);
	}

	@Override
	public void modifyLabelTagApplyPrintStatus(String id, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.updateLabelTagApplyPrintStatus", params);
	}
	/**
	 * 批量更新现有量数据时， 修改打印单状态，接收数量和取消数量以及接收方式
	 * @param id
	 * @param status
	 * @param userId
	 */
	public void modifyLabelTagApplyPrintStatus2(String id, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.updateLabelTagApplyPrintStatus2", params);
	}

	@Override
	public void deleteLabelTagApplyPrint(String id) {
		executeUpdate("LabelTagApplyPrint.deleteLabelTagApplyPrint", id);
	}

	@Override
	public void reviewSuccess(String id, String userId) {
		this.modifyLabelTagApplyPrintStatus(id, DictConstant.BILL_STATUS_REVIEWED, userId);
	}

	@Override
	public void reviewFail(String id, String userid) {
		this.modifyLabelTagApplyPrintStatus(id, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getPrintData(String headid, String labelType, String lineids, String orderBy) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("labelType", labelType);
		params.put("lineids", lineids);
		params.put("orderBy", orderBy);
		return (List<Tag>)executeQueryForList("LabelTagApplyPrint.getPrintData", params);
	}

	@Override
	public int getApplyCountByOrgId(String orgId) {
		Integer result = (Integer)executeQueryForObject("LabelTagApplyPrint.getApplyCountByOrgId", orgId);
		return result != null?result.intValue():0;
	}

	@Override
	public void insertLines(String headid, String orgId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("orgId", orgId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.insertLines", params);
	}

	@Override
	public void updateLabelApply(String printId, String orgId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("printId", printId);
		params.put("orgId", orgId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.updateLabelApply", params);
	}

	@Override
	public void updateLabelApplyLines(String printId, String orgId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("printId", printId);
		params.put("orgId", orgId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.updateLabelApplyLines", params);
	}

	@Override
	public void updateLabelApplyStatusByPrintId(String printId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("printId", printId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.updateLabelApplyStatusByPrintId", params);
	}

	@Override
	public void updateLabelApplyLineStatusByPrintId(String printId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("printId", printId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.updateLabelApplyLineStatusByPrintId", params);
	}

	@Override
	public void modifyMater(String headid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.modifyMater", params);
	}

	public void modifyMater2(String headid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.modifyMater2", params);
	}
	
	@Override
	public void initReceiveBill(String printId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("printId", printId);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.initReceiveBill", params);
	}

	@Override
	public void updateApplyLineStatus(String headId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headId", headId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.updateApplyLineStatus", params);
	}
	
	public void modifyMaterAnalysis(String headid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.modifyMaterAnalysis", params);
	}
	public void modifyMaterAnalysis2(String headid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.modifyMaterAnalysis2", params);
	}

	@Override
	public void modifyLabelPrintNum(String headid, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelPrint.modifyLabelPrintNum", params);
	}

	@Override
	public void closeLabelPrints(String billids, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billids", billids);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelPrint.closeLabelPrints", params);
	}

	@Override
	public void cancelPrintLines(String billids, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billids", billids);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelPrint.cancelPrintLines", params);
	}
	
	public void revocatMater(String headid){
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrint.revocatMater", params);
	}
}
