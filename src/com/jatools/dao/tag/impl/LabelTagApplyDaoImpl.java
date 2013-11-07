package com.jatools.dao.tag.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.tag.LabelTagApplyDao;
import com.jatools.vo.tag.LabelReasonInfo;
import com.jatools.vo.tag.LabelTagApply;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class LabelTagApplyDaoImpl extends BaseDao implements LabelTagApplyDao, ReviewActionIntf {

	@Override
	public Pager getLabelTagApplyData(Map<String, String> condition) {
		return executeQueryForPager("LabelTagApply.getLabelTagApplyPageData", "LabelTagApply.getLabelTagApplyTotalCount", condition);
	}

	@Override
	public void saveLabelTagApply(LabelTagApply labelTagApply) {
		executeInsert("LabelTagApply.saveLabelTagApply", labelTagApply);
	}

	@Override
	public LabelTagApply getLabelTagApply(String id) {
		return (LabelTagApply)executeQueryForObject("LabelTagApply.getLabelTagApply", id);
	}

	@Override
	public void updateLabelTagApply(LabelTagApply labelTagApply) {
		executeUpdate("LabelTagApply.updateLabelTagApply", labelTagApply);
	}

	@Override
	public void modifyLabelTagApplyStatus(String id, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApply.updateLabelTagApplyStatus", params);
	}
	
	public void modifyLabelTagApplyStatusByPrintId(String id, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("printId", id);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApply.updateLabelTagApplyStatusByPrintId", params);
	}

	@Override
	public void deleteLabelTagApply(String id) {
		executeUpdate("LabelTagApply.deleteLabelTagApply", id);
	}

	@Override
	public void reviewSuccess(String id, String userId) {
		this.modifyLabelTagApplyStatus(id, DictConstant.BILL_STATUS_REVIEWED, userId);
	}

	@Override
	public void reviewFail(String id, String userid) {
		this.modifyLabelTagApplyStatus(id, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectorOption> getApplyReason() {
		return (List<SelectorOption>)executeQueryForList("LabelTagApply.getApplyReason", null);
	}

	@Override
	public LabelReasonInfo getApplyReasoninfo(String labelId) {
		return (LabelReasonInfo)executeQueryForObject("LabelTagApply.getApplyReasoninfo", labelId);
	}
}
