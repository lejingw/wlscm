package com.jatools.manager.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutBackHead;
import com.jatools.vo.out.OutBackLine;

public interface OutBackManager {
	/**
	 * 获取对公销售,代销退货 ,委外退料头分 页信息
	 * @param condition
	 */
	Pager getBackHeadPage(Map<String, String> condition);
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public OutOrna getOrnaInfo(String code, boolean ornaFlag);
	/**
	 * 保存归还单信息
	 * @param head
	 * @param lines
	 * @param delIds
	 */
	void saveOrUpdateOutBack(OutBackHead head, List<OutBackLine> lines,
			String[] delIds);
	/**
	 * 获取头信息
	 * @param headId
	 * @return
	 */
	OutBackHead getBackHead(String headId);
	/**
	 * 获取头信息
	 * @param ornaCode TODO
	 * @param ornaFlag TODO
	 * @param headId
	 * @return
	 */
	OutBackLine getBackLine(String headId, String ornaCode, boolean ornaFlag);
	/**
	 * 获取行信息
	 * @param headId
	 * @return
	 */
	List<OutBackLine> getBackLines(String headId);
	/**
	 * 删除单据
	 * @param headId
	 */
	void deleteBack(String headId);
	/**
	 * 关闭单据
	 */
	void changeHeadStatus(OutBackHead head);
	/**
	 *  执行还回操作
	 * @param ids
	 * @param head
	 */
	void backLines(String ids,OutBackHead head);
}
