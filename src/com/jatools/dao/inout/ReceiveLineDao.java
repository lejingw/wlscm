package com.jatools.dao.inout;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.inout.ReceiveLine;

public interface ReceiveLineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getReceiveLineData(Map<String, String> condition);
	
	/**
	 * 保存收票单据行表
	 * @param receiveLine
	 */
	void saveReceiveLine(ReceiveLine receiveLine);
	/**
	 * 根据id获取收票单据行表
	 * @param lineid
	 * @return
	 */
	ReceiveLine getReceiveLine(String lineid);
	
	/**
	 * 通过头表id取行表数据
	 * @param billid
	 * @return
	 */
	List<ReceiveLine> getReceiveLineList(String billid);
	
	/**
	 * 保存或修改收票单据行表
	 * @param receiveLine
	 */
	void updateReceiveLine(ReceiveLine receiveLine);
	
	/**
	 * 修改收票单据行表的状态
	 * @param lineid
	 * @param status
	 * @param userId
	 */
	public void modifyReceiveLineStatus(String lineid, String status, String userId);
	/**
	 * 删除收票单据行表
	 * @param lineid
	 */
	void deleteReceiveLine(String lineid);
	
	/**
	 * 删除头表对应得行表数据
	 * @param billid
	 */
	void deleteReceiveLineByBillid(String billid);
	
	/**
	 * 根据lineid 修改交接单 收票状态
	 * @param lineid
	 * @param status
	 * @param userId
	 */
	void modifyHandoverBillStatus(String lineid, String status, String userId);
}
