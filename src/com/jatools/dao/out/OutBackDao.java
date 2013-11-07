package com.jatools.dao.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutBackHead;
import com.jatools.vo.out.OutBackLine;


public interface OutBackDao {
	/**
	 * 获取供出单
	 * @param condition
	 */
	Pager getBackHeadPage(Map<String, String> condition);
	/**
	 * 根据饰品编码或条纹获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	OutOrna getOrna(String code, boolean ornaFlag);
	/**
	 * 删除行
	 * @param delIds
	 */
	void deleteLines(String delIds);
	/**
	 * 更新头信息
	 * @param head
	 */
	void updateHead(OutBackHead head);
	/**
	 * 插入头信息
	 * @param head
	 * @return
	 */
	String insertHead(OutBackHead head);
	/**
	 * 更新行信息
	 * @param line
	 */
	void updateLine(OutBackLine line);
	/**
	 * 插入行信息
	 * @param line
	 */
	void insertLine(OutBackLine line);
	 /**
	  * 根据头id获取行信息
	  * @param headId
	  * @return
	  */
	List<OutBackLine> getBackLines(String headId);
	/**
	 * 获取头信息
	 * @param headId
	 * @return
	 */
	OutBackHead getBackHead(String headId);
	/**
	 * 获取行信息
	 * @param headId
	 * @return
	 */
	OutBackLine getBackLine(String headId, String ornaCode, boolean ornaFlag);
	/**
	 * 删除头信息
	 * @param headId
	 */
	void deleteHead(String headId);
	/**
	 * 根据头ID删除所有行
	 * @param headId
	 */
	void deleteLinesByHeadId(String headId);
	/**
	 * 改变状态
	 * @param head
	 */
	void changeHeadStatus(OutBackHead head);
	/**
	 * 写事务记录表
	 * @param line
	 */
	void saveMaterTrans(OutBackLine line);
	/**
	 * 执行还回操作：更改行表信息
	 * @param ids
	 * @param backDate TODO
	 * @param backBody TODO
	 */
	void backLines(String ids, String backDate, String backBody);
	
}