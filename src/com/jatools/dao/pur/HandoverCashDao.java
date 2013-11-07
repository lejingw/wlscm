package com.jatools.dao.pur;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.pur.HandoverCash;

/**
 * 交接单结算行表Dao
 * @author ren.ming<br>
 * Created 2011-12-12
 */
public interface HandoverCashDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getHandoverCashData(Map<String, String> condition);
	/**
	 * 保存交接单结算行表
	 * @param handoverCash
	 */
	void saveHandoverCash(HandoverCash handoverCash);
	/**
	 * 根据id获取交接单结算行表
	 * @param lineid
	 * @return
	 */
	HandoverCash getHandoverCash(String lineid);
	/**
	 * 保存或修改交接单结算行表
	 * @param handoverCash
	 */
	void updateHandoverCash(HandoverCash handoverCash);
	/**
	 * 删除交接单结算行表
	 * @param lineid
	 */
	void deleteHandoverCash(String lineid);
	
	/**
	 * 删除交接单结算行表
	 * @param billid
	 */
	void deleteHandoverCashByBillid(String billid);
	
	/**
	 * 根据单号获取交接单结算行表
	 * @param billid
	 * @return
	 */
	List<HandoverCash> getHandoverCashList(String billid);
}
