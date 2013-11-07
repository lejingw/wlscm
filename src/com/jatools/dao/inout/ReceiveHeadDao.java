package com.jatools.dao.inout;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.inout.ReceiveHead;

public interface ReceiveHeadDao {

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
	void deleteReceiveHead(String billid);
	
	/**
	 * 获取交接单列表
	 * @param condition
	 * @return
	 */
	Pager getReceiveHandoverList(Map<String, String> condition);
}
