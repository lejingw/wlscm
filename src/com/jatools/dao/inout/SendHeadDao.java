package com.jatools.dao.inout;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.inout.SendHead;

public interface SendHeadDao {

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
	void saveSendHead(SendHead sendHead);
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
	void updateSendHead(SendHead sendHead);
	
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
	void deleteSendHead(String billid);
	
	
	/**
	 * 查询对公销售单
	 * @param condition
	 * @return
	 */
	Pager getOutVendorListData(Map<String, String> condition);
	
	/**
	 * 修改对公销售单 为已出票状态
	 * @param billno 对公销售单号
	 * @param userId
	 */
	void modifyOutVendorIsBll(String billno, String userId);
	
	/**
	 * 修改对公销售单 为未出票状态
	 * @param billid 出票单 id
	 * @param userId
	 */
	void modifyOutVendorIsNotBll(String billid, String userId);
	
	/**
	 * 根据lineid修改对公销售单 出票状态
	 * @param lineid
	 * @param status
	 * @param userId
	 */
	void modifyOutVendorBillByLineid(String lineid, String status, String userId);
}
