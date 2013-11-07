package com.jatools.dao.tag.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.tag.LabelTagImportLineDao;
import com.jatools.vo.tag.LabelTagImportLine;
import com.jatools.web.util.DateUtil;

public class LabelTagImportLineDaoImpl extends BaseDao implements
		LabelTagImportLineDao {

	public Pager getLabelTagImportLinePagerData(Map<String, String> condition){
		return executeQueryForPager("LabelTagImportLine.getLabelTagImportLinePageData", "LabelTagImportLine.getLabelTagImportLineTotalCount", condition);
	}
	
	@Override
	public void saveLabelTagImportLine(LabelTagImportLine LabelTagImportLine) {
		this.executeInsert("LabelTagImportLine.saveLabelTagImportLine", LabelTagImportLine);
	}

	@Override
	public LabelTagImportLine getLabelTagImportLine(String lineid) {
		return (LabelTagImportLine)this.executeQueryForObject("LabelTagImportLine.getLabelTagImportLine", lineid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabelTagImportLine> getLabelTagImportLineList(String headid) {
		return (List<LabelTagImportLine>)this.executeQueryForList("LabelTagImportLine.getLabelTagImportLineList", headid);
	}

	@Override
	public void updateLabelTagImportLine(LabelTagImportLine LabelTagImportLine) {
		this.executeUpdate("LabelTagImportLine.updateLabelTagImportLine", LabelTagImportLine);
	}

	@Override
	public void deleteLabelTagImportLine(String lineid) {
		this.executeUpdate("LabelTagImportLine.deleteLabelTagImportLine", lineid);
	}

	@Override
	public void deleteLabelTagImportLineByHeadid(String headid) {
		this.executeUpdate("LabelTagImportLine.deleteLabelTagImportLineByHeadid", headid);
	}

	@SuppressWarnings("unchecked")
	public List<String> getValidOrnaCodes(String ornaCodes){
		return (List<String>)executeQueryForList("LabelTagImportLine.getValidOrnaCode", ornaCodes);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getExistsOrnaCodes(String seqId){
		return (List<String>)executeQueryForList("LabelTagImportLine.getExistsOrnaCode", seqId);
	}

	@Override
	public String getLineTempSeq() {
		return (String) executeQueryForObject("LabeltagImportLine.getLineTempSeq", null);
	}

	@Override
	public void saveTempLine(LabelTagImportLine line) {
		this.executeInsert("LabelTagImportLine.saveTempLine", line);
	}
	
	public void saveTempLineList(List<LabelTagImportLine> lines) {
		this.executeBatchInsert("LabelTagImportLine.saveTempLine", lines);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getInValidOrnaCodes(String seqId){
		return (List<String>)executeQueryForList("LabelTagImportLine.getInValidOrnaCode", seqId);
	}

	@Override
	public void deleteTempLineBySeqId(String seqId) {
		executeUpdate("LabelTagImportLine.deleteTempLineBySeqId", seqId);
	}
	
	public void inertLineFromTemp(String headid, String seqId, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("seqId", seqId);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("LabelTagImportLine.insertLineFromTemp", params);
	}
}
