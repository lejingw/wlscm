package com.jatools.dao.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.Consume;
import com.jatools.vo.out.ConsumeLine;

public interface ConsumeDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Pager getConsumePageData(Map<String,String> conditionMap);
	/**
	 * 保存单据
	 */
	void saveConsume(Consume Consume);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	Consume getConsumeById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateConsume(Consume Consume);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteConsume(String id);

	void updateConsumStatus(String billid, String status, String userid);
	
	List<ConsumeLine> getLines(String billid);
	
	void insertLine(ConsumeLine line);
	
	void updateLine(ConsumeLine line);
	
	void deleteLineById(String lineid);
	
	void deleteLineByBillid(String billid);
	
	void insertMoneyAccountByConsume(String billid, String userId);
}
