package com.jatools.dao.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.tag.LabelReasonInfo;
import com.jatools.vo.tag.LabelTagApply;
import com.jatools.vo.util.SelectorOption;

public interface LabelTagApplyDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getLabelTagApplyData(Map<String, String> condition);
	
	/**
	 * 保存标签申请
	 * @param labelTagApply
	 */
	void saveLabelTagApply(LabelTagApply labelTagApply);
	/**
	 * 根据id获取标签申请
	 * @param id
	 * @return
	 */
	LabelTagApply getLabelTagApply(String id);
	
	/**
	 * 保存或修改标签申请
	 * @param labelTagApply
	 */
	void updateLabelTagApply(LabelTagApply labelTagApply);
	
	/**
	 * 修改标签申请的状态
	 * @param id
	 * @param status
	 * @param userId
	 */
	public void modifyLabelTagApplyStatus(String id, String status, String userId);
	
	public void modifyLabelTagApplyStatusByPrintId(String id, String status, String userId);
	/**
	 * 删除标签申请头表
	 * @param id
	 */
	void deleteLabelTagApply(String id);
	
	/**
	 * 取换标签原因列表
	 * @return
	 */
	List<SelectorOption> getApplyReason();
	
	LabelReasonInfo getApplyReasoninfo(String labelId);
}
