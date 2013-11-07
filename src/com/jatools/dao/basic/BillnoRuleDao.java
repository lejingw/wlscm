package com.jatools.dao.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.BillnoRule;
import com.jatools.vo.util.SelectorOption;

public interface BillnoRuleDao {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getBillnoRulePageData(Map<String, String> condition);
	
	/**
	 * 获取单据编码
	 * @param billCode
	 * @return
	 */
	List<SelectorOption> getBillnoCodeForSlt(String billCode);
	/**
	 * 检查单据编号是否已经存在
	 * @param billCode
	 * @return
	 */
	boolean checkBillCode(String billCode);
	/**
	 * 保存单据
	 * @param billnoRule
	 */
	void saveBillnoRule(BillnoRule billnoRule);
	/**
	 * 根据id获取单号编码规则信息记录
	 * @param billnoRuleId
	 * @return
	 */
	BillnoRule getBillnoRule(String billCode);
	/**
	 * 保存或修改单据
	 * @param billnoRule
	 */
	void updateBillnoRule(BillnoRule billnoRule);
	/**
	 * 删除单据
	 * @param billCode
	 */
	void deleteBillnoRule(String billCode);
}
