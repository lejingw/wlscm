package com.jatools.dao.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.tag.LabelImportOrgGroupNo;
import com.jatools.vo.tag.LabelTagImportHead;

public interface LabelTagImportHeadDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getLabelTagImportHeadData(Map<String, String> condition);
	
	/**
	 * 保存
	 * @param labelTagImportHead
	 */
	void saveLabelTagImportHead(LabelTagImportHead labelTagImportHead);
	/**
	 * 根据id获取
	 * @param headid
	 * @return
	 */
	LabelTagImportHead getLabelTagImportHead(String headid);
	
	/**
	 * 保存或修改
	 * @param labelTagImportHead
	 */
	void updateLabelTagImportHead(LabelTagImportHead labelTagImportHead);
	
	void modifyLabelImportCount(String headid);
	/**
	 * 修改状态
	 * @param headid
	 * @param status
	 * @param userId
	 */
	public void modifyLabelTagImportHeadStatus(String headid, String status, String userId);
	
	/**
	 * 删除
	 * @param headid
	 */
	void deleteLabelTagImportHead(String headid);
	
	
	List<LabelImportOrgGroupNo> getOrgGroupNo(String headid);
	
	void createPrintHead(LabelImportOrgGroupNo orgGroupNo);
	
	void createPrintLine(LabelImportOrgGroupNo orgGroupNo);
	
	void modifyLabelPrintNumCount(String headid, String userId);
	
	
	// --------------------------------------------------------------------------------------------
	void insertPrintLines(String importHeadId);
	
	void insertPrintHeads(String userId);
	
	void updatePrintLineHeadId();
	List<LabelImportOrgGroupNo> getOrgGroupNo2();
	
	void modifyLabelPrintBillNo(LabelImportOrgGroupNo orgGroupNo);
}
