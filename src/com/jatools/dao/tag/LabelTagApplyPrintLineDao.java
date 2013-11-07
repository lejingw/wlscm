package com.jatools.dao.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.tag.LabelTagApplyPrintLine;

public interface LabelTagApplyPrintLineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getLabelTagApplyPrintLineData(Map<String, String> condition);
	
	/**
	 * 保存标签打印行表
	 * @param LabelTagApplyPrintLine
	 */
	void saveLabelTagApplyPrintLine(LabelTagApplyPrintLine LabelTagApplyPrintLine);
	/**
	 * 根据id获取标签打印行表
	 * @param id
	 * @return
	 */
	LabelTagApplyPrintLine getLabelTagApplyPrintLine(String id);
	
	/**
	 * 通过头表id取行表数据
	 * @param id
	 * @return
	 */
	List<LabelTagApplyPrintLine> getLabelTagApplyPrintLineList(String id);
	
	List<LabelTagApplyPrintLine> getLabelTagApplyPrintLineList(Map<String, String> condition);
	
	/**
	 * 保存或修改标签打印行表
	 * @param LabelTagApplyPrintLine
	 */
	void updateLabelTagApplyPrintLine(LabelTagApplyPrintLine LabelTagApplyPrintLine);
	
	/**
	 * 修改标签打印行表的状态
	 * @param id
	 * @param status
	 * @param userId
	 */
	public void modifyPrintLineStatus(String id, String status, String userId);
	
	/**
	 * 
	 * @param headid
	 * @param status
	 * @param userId
	 */
	public void modifyPrintLineStatusByHeadid(String headid, String status, String userId);
	
	/**
	 * 删除标签打印行表
	 * @param id
	 */
	void deleteLabelTagApplyPrintLine(String id);
	
	/**
	 * 删除头表对应得行表数据
	 * @param headid
	 */
	void deleteLabelTagApplyPrintLineByHeadid(String headid);
	
	LabelTagApplyPrintLine getPrintLine(String labelId, String ornaCode, String ornaBarCode);
	
	/**
	 * 修改打印单 行表状态
	 * @param recType 接收中 | 已接收
	 * @param inOrNotIn 只能是 0|1
	 * @param lineids
	 */
	void updateState(String headid, String recType, String inOrNotIn, List<String> lineids);
	
	void updateState(String headid, String state);
	
	/**
	 * 修改未接收的行状态为已取消
	 * @param headId
	 */
	void udpateStateToCancel(String headId);
	
	
	String selectOrnaCodeNotInMater(String headId);
	
	String selectOrnaCodeNotInOrg(String headId);
	
	void changeInvalidMater(String headid, String ornaCodes, String userId);
}
