package com.jatools.dao.tag.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.tag.LabelTagImportHeadDao;
import com.jatools.vo.tag.LabelImportOrgGroupNo;
import com.jatools.vo.tag.LabelTagImportHead;
import com.jatools.web.util.DateUtil;

public class LabelTagImportHeadDaoImpl extends BaseDao implements LabelTagImportHeadDao, ReviewActionIntf {

	@Override
	public Pager getLabelTagImportHeadData(Map<String, String> condition) {
		return executeQueryForPager("LabelTagImportHead.getLabelTagImportHeadPageData", "LabelTagImportHead.getLabelTagImportHeadTotalCount", condition);
	}

	@Override
	public void saveLabelTagImportHead(LabelTagImportHead labelTagImportHead) {
		executeInsert("LabelTagImportHead.saveLabelTagImportHead", labelTagImportHead);
	}

	@Override
	public LabelTagImportHead getLabelTagImportHead(String headid) {
		return (LabelTagImportHead)executeQueryForObject("LabelTagImportHead.getLabelTagImportHead", headid);
	}

	@Override
	public void updateLabelTagImportHead(LabelTagImportHead labelTagImportHead) {
		executeUpdate("LabelTagImportHead.updateLabelTagImportHead", labelTagImportHead);
	}

	public void modifyLabelImportCount(String headId){
		executeUpdate("LabelTagImportHead.modifyLabelImportCount", headId);
	}
	@Override
	public void modifyLabelTagImportHeadStatus(String headid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("status", status);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagImportHead.updateLabelTagImportHeadStatus", params);
	}

	@Override
	public void deleteLabelTagImportHead(String headid) {
		executeUpdate("LabelTagImportHead.deleteLabelTagImportHead", headid);
	}

	@SuppressWarnings("unchecked")
	public List<LabelImportOrgGroupNo> getOrgGroupNo(String headid){
		return (List<LabelImportOrgGroupNo>)executeQueryForList("LabelTagImportHead.getOrgGroupNo", headid);
	}
	
	@Override
	public void reviewSuccess(String headid, String userId) {
		this.modifyLabelTagImportHeadStatus(headid, DictConstant.BILL_STATUS_REVIEWED, userId);
		
	}

	@Override
	public void reviewFail(String headid, String userId) {
		this.modifyLabelTagImportHeadStatus(headid, DictConstant.BILL_STATUS_SAVE, userId);
	}

	public void createPrintHead(LabelImportOrgGroupNo orgGroupNo) {
		executeInsert("LabelTagImportHead.createLabelTagApplyPrint", orgGroupNo);
	}
	
	public void createPrintLine(LabelImportOrgGroupNo orgGroupNo) {
		executeInsert("LabelTagImportHead.createLabelTagPrintLine", orgGroupNo);
	}
	
	public void modifyLabelPrintNumCount(String headid, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", headid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("updateId", userId);
		executeUpdate("LabelTagImportHead.modifyLabelPrintNumCount", params);
	}
    //--------------------------------------------------------------------------------------------
	@Override
	public void insertPrintLines(String importHeadId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", importHeadId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("LabelTagImportHead.insertPrintLine", params);
	}
	
	public void insertPrintHeads(String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("LabelTagImportHead.insertPrinthead", params);
	}
	
	public void updatePrintLineHeadId(){
		executeUpdate("LabelTagImportHead.updatePrintLineHeadId", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<LabelImportOrgGroupNo> getOrgGroupNo2(){
		return (List<LabelImportOrgGroupNo>)executeQueryForList("LabelTagImportHead.getOrgGroupNo2", null);
	}
	
	public void modifyLabelPrintBillNo(LabelImportOrgGroupNo orgGroupNo){
		executeUpdate("LabelTagImportHead.modifyLabelPrintBillNo", orgGroupNo);
	}
}
