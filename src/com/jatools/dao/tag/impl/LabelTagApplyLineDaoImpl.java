package com.jatools.dao.tag.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.tag.LabelTagApplyLineDao;
import com.jatools.vo.tag.LabelTagApplyLine;
import com.jatools.web.util.DateUtil;

public class LabelTagApplyLineDaoImpl extends BaseDao implements LabelTagApplyLineDao {

	@Override
	public Pager getLabelTagApplyLineData(Map<String, String> condition) {
		return executeQueryForPager("LabelTagApplyLine.getLabelTagApplyLinePageData", "LabelTagApplyLine.getLabelTagApplyLineTotalCount", condition);
	}

	@Override
	public void saveLabelTagApplyLine(LabelTagApplyLine labelTagApplyLine) {
		executeInsert("LabelTagApplyLine.saveLabelTagApplyLine", labelTagApplyLine);
	}

	@Override
	public LabelTagApplyLine getLabelTagApplyLine(String id) {
		return (LabelTagApplyLine)executeQueryForObject("LabelTagApplyLine.getLabelTagApplyLine", id);
	}

	@Override
	public void updateLabelTagApplyLine(LabelTagApplyLine labelTagApplyLine) {
		executeUpdate("LabelTagApplyLine.updateLabelTagApplyLine", labelTagApplyLine);
	}

	@Override
	public void modifyLabelTagApplyLineStatus(String id, String status, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("LabelTagApplyLine.updateLabelTagApplyLineStatus", params);
	}

	@Override
	public void deleteLabelTagApplyLine(String id) {
		executeUpdate("LabelTagApplyLine.deleteLabelTagApplyLine", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabelTagApplyLine> getLabelTagApplyLineList(String headid) {
		return (List<LabelTagApplyLine>)executeQueryForList("LabelTagApplyLine.getLabelTagApplyLineListByHeadid", headid);
	}

	@Override
	public void deleteLabelTagApplyLineByHeadid(String headid) {
		executeUpdate("LabelTagApplyLine.deleteLabelTagApplyLineByHeadid", headid);
	}

}
