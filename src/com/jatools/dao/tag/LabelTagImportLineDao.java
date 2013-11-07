package com.jatools.dao.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.tag.LabelTagImportLine;

public interface LabelTagImportLineDao {

	Pager getLabelTagImportLinePagerData(Map<String, String> condition);
	/**
	 * 保存
	 * @param LabelTagImportLine
	 */
	void saveLabelTagImportLine(LabelTagImportLine LabelTagImportLine);
	
	/**
	 * 根据id获取
	 * @param lineid
	 * @return
	 */
	LabelTagImportLine getLabelTagImportLine(String lineid);
	
	
	/**
	 * 根据id获取
	 * @param headid
	 * @return
	 */
	List<LabelTagImportLine> getLabelTagImportLineList(String headid);
	
	
	/**
	 * 保存或修改
	 * @param LabelTagImportLine
	 */
	void updateLabelTagImportLine(LabelTagImportLine LabelTagImportLine);
	
	
	
	/**
	 * 删除
	 * @param lineid
	 */
	void deleteLabelTagImportLine(String lineid);
	
	/**
	 * 删除
	 * @param headid
	 */
	void deleteLabelTagImportLineByHeadid(String headid);
	
	
	List<String> getValidOrnaCodes(String ornaCodes);
	
	List<String> getExistsOrnaCodes(String seqId);
	
	
	// -------------------------------------------------
	String getLineTempSeq();
	
	void saveTempLine(LabelTagImportLine line);
	void saveTempLineList(List<LabelTagImportLine> lines);
	List<String> getInValidOrnaCodes(String seqId);
	
	void deleteTempLineBySeqId(String seqId);
	
	void inertLineFromTemp(String headid, String seqId, String userId);
}
