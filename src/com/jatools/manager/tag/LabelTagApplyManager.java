package com.jatools.manager.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.tag.LabelReasonInfo;
import com.jatools.vo.tag.LabelTagApply;
import com.jatools.vo.tag.LabelTagApplyLine;
import com.jatools.vo.util.SelectorOption;

public interface LabelTagApplyManager {
	
	/**
	 * 取单条头表数据
	 * @param id
	 * @return
	 */
	LabelTagApply getLabelTagApply(String id);
	
	/**
	 * 取单条行表数据
	 * @param lineid
	 * @return
	 */
	LabelTagApplyLine getLabelTagApplyLine(String lineid);
	
	/**
	 * 根据头id取行表数据
	 * @param headid
	 * @return
	 */
	List<LabelTagApplyLine> getLabelTagApplyLines(String headid);
	
	/**
	 * 头表分页数据
	 * @param condition
	 * @return
	 */
	Pager getLabelTagApplyData(Map<String, String> condition);
	
	
	/**
	 * 保存或修改
	 * @param labelTagApply
	 * @param lines
	 * @param userId
	 */
	void saveOrUpdateLabelTagApply(LabelTagApply labelTagApply, List<LabelTagApplyLine> lines , String deleteIds,  String userId);
	
	/**
	 * 保存并提交审核
	 * @param labelTagApply
	 * @param lines
	 * @param userId
	 */
	void saveAndCheckLabelTagApply(LabelTagApply labelTagApply, List<LabelTagApplyLine> lines , String deleteIds,  String userId);
	
	/**
	 * 关闭单据
	 * @param labelTagApply
	 */
	void closeLabelTagApply(LabelTagApply labelTagApply, String userId);
	
	
	/**
	 * 删除单据
	 * @param headid
	 */
	void deleteLabelTagApply(String headid);
	
	/**
	 * 取换标签原因列表
	 * @return
	 */
	List<SelectorOption> getApplyReason();
	
	LabelReasonInfo getApplyReasoninfo(String labelId);
}
