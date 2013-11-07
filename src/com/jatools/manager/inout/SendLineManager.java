package com.jatools.manager.inout;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.inout.SendLine;

public interface SendLineManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getSendLineData(Map<String, String> condition);
	
	/**
	 * 保存出票单据行表
	 * @param SendLine
	 */
	void saveSendLine(SendLine SendLine);
	/**
	 * 根据id获取出票单据行表
	 * @param lineid
	 * @return
	 */
	SendLine getSendLine(String lineid);
	
	/**
	 * 通过头表id取行表数据
	 * @param billid
	 * @return
	 */
	List<SendLine> getSendLineList(String billid);
	
	/**
	 * 保存或修改出票单据行表
	 * @param SendLine
	 */
	void updateSendLine(SendLine SendLine);
	
	/**
	 * 修改出票单据行表的状态
	 * @param lineid
	 * @param status
	 * @param userId
	 */
	public void modifySendLineStatus(String lineid, String status, String userId);
	/**
	 * 删除出票单据行表
	 * @param lineid
	 */
	void deleteSendLine(String lineid);
	
	/**
	 * 删除头表对应得行表数据
	 * @param billid
	 */
	void deleteSendLineByBillid(String billid);
}
