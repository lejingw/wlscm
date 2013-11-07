package com.jatools.dao.tag.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.tag.LabelTagApplyPrintLineDao;
import com.jatools.vo.tag.LabelTagApplyPrintLine;
import com.jatools.web.util.DateUtil;

public class LabelTagApplyPrintLineDaoImpl extends BaseDao implements LabelTagApplyPrintLineDao {

	@Override
	public Pager getLabelTagApplyPrintLineData(Map<String, String> condition) {
		return executeQueryForPager("LabelTagApplyPrintLine.getLabelTagApplyPrintLinePageData", "LabelTagApplyPrintLine.getLabelTagApplyPrintLineTotalCount", condition);
	}

	@Override
	public void saveLabelTagApplyPrintLine(LabelTagApplyPrintLine LabelTagApplyPrintLine) {
		executeInsert("LabelTagApplyPrintLine.saveLabelTagApplyPrintLine", LabelTagApplyPrintLine);
	}

	@Override
	public LabelTagApplyPrintLine getLabelTagApplyPrintLine(String id) {
		return (LabelTagApplyPrintLine)executeQueryForObject("LabelTagApplyPrintLine.getLabelTagApplyPrintLine", id);
	}

	@Override
	public void updateLabelTagApplyPrintLine(LabelTagApplyPrintLine LabelTagApplyPrintLine) {
		executeUpdate("LabelTagApplyPrintLine.updateLabelTagApplyPrintLine", LabelTagApplyPrintLine);
	}

	@Override
	public void modifyPrintLineStatus(String id, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrintLine.updateLabelTagApplyPrintLineStatus", params);
	}

	@Override
	public void modifyPrintLineStatusByHeadid(String headid, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrintLine.updateLabelTagApplyPrintLineStatusByHeadid", params);
	}
	
	@Override
	public void deleteLabelTagApplyPrintLine(String id) {
		executeUpdate("LabelTagApplyPrintLine.deleteLabelTagApplyPrintLine", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabelTagApplyPrintLine> getLabelTagApplyPrintLineList(String headid) {
		return (List<LabelTagApplyPrintLine>)executeQueryForList("LabelTagApplyPrintLine.getLabelTagApplyPrintLineListByHeadid", headid);
	}
	
	@SuppressWarnings("unchecked")
	public List<LabelTagApplyPrintLine> getLabelTagApplyPrintLineList(Map<String, String> condition) {
		return (List<LabelTagApplyPrintLine>)executeQueryForList("LabelTagApplyPrintLine.getLabelTagApplyPrintLineList", condition);
	}

	@Override
	public void deleteLabelTagApplyPrintLineByHeadid(String headid) {
		executeUpdate("LabelTagApplyPrintLine.deleteLabelTagApplyPrintLineByHeadid", headid);
	}

	@Override
	public LabelTagApplyPrintLine getPrintLine(String labelId, String ornaCode, String ornaBarCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ornaCode", ornaCode);
		params.put("ornaBarCode", ornaBarCode);
		params.put("labelId", labelId);
		return (LabelTagApplyPrintLine)executeQueryForObject("LabelTagApplyPrintLine.getPrintLine", params);
	}

	@Override
	public void updateState(String headid, String recType, String inOrNotIn, List<String> lineids) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("headid", headid);
		params.put("recType", recType);
		params.put("inOrNotIn", inOrNotIn);
		params.put("lineids", lineids);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrintLine.updateState", params);
	}
	
	public void updateState(String headid, String state) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("headid", headid);
		params.put("state", state);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrintLine.updateState2", params);
	}

	@Override
	public void udpateStateToCancel(String headId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("headId", headId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyPrintLine.updateStateToCancel", params);
	}

	@Override
	public String selectOrnaCodeNotInMater(String headId) {
		return (String)executeQueryForObject("LabeltagPrintLine.selectOrnaCodeNotInmater", headId);
	}

	@Override
	public String selectOrnaCodeNotInOrg(String headId) {
		return (String)executeQueryForObject("LabeltagPrintLine.selectOrnaCodeNotInOrg", headId);
	}

	@Override
	public void changeInvalidMater(String headid, String ornaCodes,
			String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("headid", headid);
		params.put("ornaCodes", ornaCodes);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelPrintLine.changeInvalidMater", params);
	}

}
