package com.jatools.manager.tag;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.tag.LabelTagApplyPrint;
import com.jatools.vo.tag.LabelTagApplyPrintLine;

public interface LabelTagApplyPrintManager {
	/**
	 * 取单条头表数据
	 * @param id
	 * @return
	 */
	LabelTagApplyPrint getLabelTagApplyPrint(String id);
	
	/**
	 * 取单条行表数据
	 * @param lineid
	 * @return
	 */
	LabelTagApplyPrintLine getLabelTagApplyPrintLine(String lineid);
	
	/**
	 * 根据头id取行表数据
	 * @param headid
	 * @return
	 */
	List<LabelTagApplyPrintLine> getLabelTagApplyPrintLines(String headid);
	
	List<LabelTagApplyPrintLine> getLabelTagApplyPrintLines(Map<String, String> condition);
	
	/**
	 * 头表分页数据
	 * @param condition
	 * @return
	 */
	Pager getLabelTagApplyPrintData(Map<String, String> condition);
	
	
	Pager getLabelTagApplyPrintLineData(Map<String, String> condition);
	
	/**
	 * 保存或修改
	 * @param LabelTagApplyPrint
	 * @param lines
	 * @param userId
	 */
	void saveOrUpdateLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint, List<LabelTagApplyPrintLine> lines , String deleteIds,  String userId);
	
	/**
	 * @param LabelTagApplyPrint
	 * @param lines
	 * @param userId
	 */
	void saveAndMarkLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint, List<LabelTagApplyPrintLine> lines , String deleteIds,  String userId);
	
	/**
	 * 确认接收单据
	 * @param LabelTagApplyPrint
	 * @param lines
	 * @param userId
	 */
	void receiveLabelApplyPrint(LabelTagApplyPrint LabelTagApplyPrint, List<LabelTagApplyPrintLine> lines,  String userId);
	
	/**
	 * 记账 把单据状态改成 已合并
	 * @param LabelTagApplyPrint
	 */
	void remarkLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint, String userId);
	
	
	/**
	 * 删除单据
	 * @param headid
	 */
	void deleteLabelTagApplyPrint(String headid);
	
	/**
	 * 取打印标签数据
	 * @param headid
	 * @param labelType 标签类型
	 * @return
	 */
	List<Tag> getPrintData (String headid, String labelType, String lineids, String orderBy);
	
	/**
	 * 门店创建 打印单
	 * @param orgIds 组织列表以','分割
	 * @param useId
	 * @return 返回 头表id
	 */
	public Map<String, String> createLabelPrint(String orgIds, String useId);
	
	void updateLabelApplyStatus(String printId, String userId);
	
	
	LabelTagApplyPrintLine getPrintLine(String labelId, String ornaCode, String ornaBarCode);
	
	void initLabelApplyPrint(String headid, String userId);
	
	
	void closeLabelPrint(String headid, String ornaNotInMater, String ornaNotInOrg, String userId);
	Map<String, String> checkPrintOrnaCode(String headid);
	
	void updateMaterData(String billids, String userId);
	
	void cancelPrintBill(String billids, String userId);
	
	void revocatMater(String headid, String userId);
}
