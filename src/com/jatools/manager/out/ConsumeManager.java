package com.jatools.manager.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.Consume;
import com.jatools.vo.out.ConsumeLine;

public interface ConsumeManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Pager getConsumePageData(Map<String,String> conditionMap);
	
	/**
	 * 保存或修改
	 */
	void saveOrUpdateConsume(Consume Consume, List<ConsumeLine> lines, String deleteIds, String userId);
	
	/**
	 * 保存并审批
	 * @param Consume
	 * @param lines
	 * @param deleteIds
	 * @param userId
	 */
	void saveAndCheckConsume(Consume Consume, List<ConsumeLine> lines, String deleteIds, String userId);
	
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	Consume getConsumeById(String id);
	
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteConsume(String id);

	/**
	 * 查询行数据
	 * @param billid
	 * @return
	 */
	List<ConsumeLine> getLines(String billid);
	
	void closeConsume(String billid, String userId);
}
