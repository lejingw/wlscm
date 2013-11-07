package com.jatools.manager.inout;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.inout.SendHead;
import com.jatools.vo.inout.SendLine;

public interface SendHeadManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getSendHeadData(Map<String, String> condition);
	
	/**
	 * 保存出票单据
	 * @param sendHead
	 */
	//void saveSendHead(SendHead sendHead);
	
	
	/**
	 * 保存或修改出票单据
	 * @param sendHead
	 */
	public void saveorUpdateSendHead(SendHead sendHead, List<SendLine> lines, String deleteIds, String userId);
	
	void saveAndCloseSendHead(SendHead sendHead, List<SendLine> lines, String deleteIds, String userId);
	/**
	 * 根据id获取出票单据
	 * @param billid
	 * @return
	 */
	SendHead getSendHead(String billid);
	
	/**
	 * 保存或修改出票单据
	 * @param sendHead
	 */
	//void updateSendHead(SendHead sendHead);
	
	/**
	 * 修改出票单据的状态
	 * @param billid
	 * @param status
	 * @param userId
	 */
	public void modifySendHeadStatus(String billid, String status, String userId);
	/**
	 * 删除出票单据头表
	 * @param billid
	 */
	void deleteSendHead(String billid, String userId);
	
	/**
	 * 关闭单据
	 * @param billid
	 * @param userId
	 */
	void closeSendHead(String billid, String userId);
	
	
	/**
	 * 查询对公销售单
	 * @param condition
	 * @return
	 */
	Pager getOutVendorListData(Map<String, String> condition);
}
