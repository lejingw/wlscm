package com.jatools.manager.inout;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.inout.ReceiveHead;
import com.jatools.vo.inout.ReceiveLine;

public interface ReceiveHeadManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getReceiveHeadData(Map<String, String> condition);
	
	/**
	 * 保存收票单据
	 * @param receiveHead
	 */
	void saveReceiveHead(ReceiveHead receiveHead);
	
	/**
	 * 保存或修改收票单据
	 * @param receiveHead
	 * @param lines
	 */
	void saveOrUpdateReceiveHead(ReceiveHead receiveHead, List<ReceiveLine> lines, String deleteIds, String userId);
	
	/**
	 * 保存并关闭单据
	 * @param receiveHead
	 * @param lines
	 * @param deleteIds
	 * @param userId
	 */
	void saveAndCloseReceiveHead(ReceiveHead receiveHead, List<ReceiveLine> lines, String deleteIds, String userId);
	
	/**
	 * 根据id获取收票单据
	 * @param billid
	 * @return
	 */
	ReceiveHead getReceiveHead(String billid);
	
	/**
	 * 保存或修改收票单据
	 * @param receiveHead
	 */
	void updateReceiveHead(ReceiveHead receiveHead);
	
	/**
	 * 修改收票单据的状态
	 * @param billid
	 * @param status
	 * @param userId
	 */
	public void modifyReceiveHeadStatus(String billid, String status, String userId);
	/**
	 * 删除收票单据头表
	 * @param billid
	 */
	void deleteReceiveHead(String billid, String userId);
	
	/**
	 * 关闭单据
	 * @param billid
	 * @param userId
	 */
	void closeReceiveBill(String billid, String userId );
	
	/**
	 * 获取交接单列表
	 * @param condition
	 * @return
	 */
	Pager getReceiveHandoverList(Map<String, String> condition);
	
	/**
	 * 撤销收票
	 * @param billid
	 * @param userId
	 */
	void reverseReceive(String billid, String userId);
}
