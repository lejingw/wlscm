package com.jatools.manager.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.tag.LabelTagImportHead;
import com.jatools.vo.tag.LabelTagImportLine;

public interface LabelTagImportManager {

	Pager getLabelTagImportHeadData(Map<String, String> condition);
	Pager getLabelTagImportLineData(Map<String, String> condition);
	
	void saveOrUpdateLabelTagImportHead(LabelTagImportHead labelTagImportHead, List<LabelTagImportLine> lines, String deleteIds, String userId);
	
	void saveAndCheckLabelTagImportHead(LabelTagImportHead labelTagImportHead, List<LabelTagImportLine> lines, String deleteIds, String userId);
	
	void saveOrUpdateLabelTagImportHead(LabelTagImportHead labelTagImportHead, String seqId, String userId);
	
	LabelTagImportHead getLabelTagImportHead(String headid);
	
	List<LabelTagImportLine> getLabelTagImportLineList(String headid);
	
	void deleteLabelTagImportHead(String headid);
	
	void closeLabelTagImport(String headid, String userId);
	
	List<String> getValidOrnaCodes(String ornaCodes);
	
	List<String> getExistsOrnaCodes(String seqId);
	
	
	// ---------------------------------------------------------------------------------
	String getLineTempSeq();
	
	String insertLinesTemp(List<LabelTagImportLine> lines);
	
	List<String> getInValidOrnaCodes(String seqId);
	
	void deleteTempLineBySeqId(String seqId);
}
