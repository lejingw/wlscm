package com.jatools.manager.pur;

import java.util.List;

import com.jatools.vo.pur.HandoverChild;

public interface HandoverChildManager {

	/**
	 * 根据行表lineid查行表数据
	 * @param lineid
	 * @return
	 */
	List<HandoverChild> getHandoverChildList(String lineid);
	
	/**
	 * 交接单行表孙表
	 * @param handoverChild
	 */
	void saveHandoverChild(HandoverChild handoverChild);
	/**
	 * 根据id获取交接单行表孙表
	 * @param childid
	 * @return
	 */
	HandoverChild getHandoverChild(String childid);
	/**
	 * 保存或修改交接单行表孙表
	 * @param handoverChild
	 */
	void updateHandoverChild(HandoverChild handoverChild);
	
	/**
	 * 删除交接单行表孙表
	 * @param childid
	 */
	void deleteHandoverChild(String childid);
	
	
	void deleteHandoverChildByLineid(String lineid);
	
	/**
	 * 通过退料单 插入交接单孙表
	 * @param exitBillid
	 * @param handoverId
	 * @param userId
	 */
	void insertChildByExit(String exitBillid, String handoverId, String userId);
}
