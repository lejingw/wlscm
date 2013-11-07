package com.jatools.dao.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.tag.LabelTagApplyLine;

public interface LabelTagApplyLineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getLabelTagApplyLineData(Map<String, String> condition);
	
	/**
	 * 保存标签申请行表
	 * @param labelTagApplyLine
	 */
	void saveLabelTagApplyLine(LabelTagApplyLine labelTagApplyLine);
	/**
	 * 根据id获取标签申请行表
	 * @param id
	 * @return
	 */
	LabelTagApplyLine getLabelTagApplyLine(String id);
	
	/**
	 * 通过头表id取行表数据
	 * @param id
	 * @return
	 */
	List<LabelTagApplyLine> getLabelTagApplyLineList(String id);
	
	/**
	 * 保存或修改标签申请行表
	 * @param labelTagApplyLine
	 */
	void updateLabelTagApplyLine(LabelTagApplyLine labelTagApplyLine);
	
	/**
	 * 修改标签申请行表的状态
	 * @param id
	 * @param status
	 * @param userId
	 */
	public void modifyLabelTagApplyLineStatus(String id, String status, String userId);
	/**
	 * 删除标签申请行表
	 * @param id
	 */
	void deleteLabelTagApplyLine(String id);
	
	/**
	 * 删除头表对应得行表数据
	 * @param headid
	 */
	void deleteLabelTagApplyLineByHeadid(String headid);
	
}
