package com.jatools.dao.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.tag.LabelTagApplyPrint;

public interface LabelTagApplyPrintDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getLabelTagApplyPrintData(Map<String, String> condition);
	
	/**
	 * 保存标签打印
	 * @param LabelTagApplyPrint
	 */
	void saveLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint);
	/**
	 * 根据id获取标签打印
	 * @param id
	 * @return
	 */
	LabelTagApplyPrint getLabelTagApplyPrint(String id);
	
	/**
	 * 保存或修改标签打印
	 * @param LabelTagApplyPrint
	 */
	void updateLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint);
	
	/**
	 * 修改标签打印的状态
	 * @param id
	 * @param status
	 * @param userId
	 */
	public void modifyLabelTagApplyPrintStatus(String id, String status, String userId);
	
	public void modifyLabelTagApplyPrintStatus2(String id, String status, String userId);
	/**
	 * 删除标签打印头表
	 * @param id
	 */
	void deleteLabelTagApplyPrint(String id);
	
	
	/**
	 * 取打印标签数据
	 * @param headid
	 * @return
	 */
	List<Tag> getPrintData (String headid, String labelType, String lineids, String orderBy);
	
	/**
	 * 查询 标签申请单 行表数量
	 * @param orgId
	 * @return
	 */
	int getApplyCountByOrgId(String orgId);
	
	/**
	 * 门店创建打印单 行表
	 * @param headid 头表id
	 * @param orgId
	 */
	void insertLines(String headid, String orgId);
	
	/**
	 * 修改标签申请单 状态为已合并，同时把打印单id写入申请单中
	 * @param printId
	 * @param orgId
	 */
	void updateLabelApply(String printId, String orgId);
	
	/**
	 * 修改标签申请单行表 状态为已合并
	 * @param printId
	 * @param orgId
	 */
	void updateLabelApplyLines(String printId, String orgId);
	
	void updateLabelApplyStatusByPrintId(String printId);
	
	void updateLabelApplyLineStatusByPrintId(String printId);
	
	/**
	 * 修改现有量信息
	 * @param headid
	 */
	void modifyMater(String headid);
	
	void modifyMater2(String headid);
	
	void modifyMaterAnalysis(String headid);

	void modifyMaterAnalysis2(String headid);

	/**
	 * 初始化接收单据  把接收人清空， 接收数量和取消数量 置0
	 * @param printId
	 * @param userId
	 */
	void initReceiveBill(String printId, String userId);
	
	/**
	 * 修改标签申请单行表状态
	 * @param headId
	 */
	void updateApplyLineStatus(String headId);
	
	void modifyLabelPrintNum(String headid, String userId);
	
	/**
	 * 批量关闭打印单
	 * @param billids
	 * @param userId
	 */
	void closeLabelPrints(String billids, String userId);
	
	/**
	 * 批量取消打印单行
	 * @param billids
	 * @param userId
	 */
	void cancelPrintLines(String billids, String userId);
	
	/**
	 * 撤销数据
	 * @param headid
	 */
	void revocatMater(String headid);
}
