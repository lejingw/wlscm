package com.jatools.dao.basic.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.BillnoRuleDao;
import com.jatools.vo.basic.BillnoRule;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.StringUtil;

public class BillnoRuleDaoImpl extends BaseDao implements BillnoRuleDao{
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	public Pager getBillnoRulePageData(Map<String, String> condition){
		Pager pager = executeQueryForPager("BillnoRule.getBillnoRulePageData", "BillnoRule.getBillnoRuleTotalCount", condition);
		return pager;
	}
	/**
	 * 获取单据编码
	 * @param billCode
	 * @return
	 */
	public List<SelectorOption> getBillnoCodeForSlt(String billCode){
		if(StringUtil.isEmpty(billCode))
			billCode = null;
		return executeQueryForList("BillnoRule.getBillnoCodeForSlt", billCode);
	}
	/**
	 * 检查单据编号是否已经存在
	 * @param billCode
	 * @return
	 */
	public boolean checkBillCode(String billCode){
		int count = (Integer) executeQueryForObject("BillnoRule.checkBillCode", billCode);
		if(count<1)return false;
		return true;
	}
	/**
	 * 保存单据
	 * @param billnoRule
	 */
	public void saveBillnoRule(BillnoRule billnoRule){
		executeInsert("BillnoRule.saveBillnoRule", billnoRule);
	}
	/**
	 * 根据id获取单号编码规则信息记录
	 * @param billnoRuleId
	 * @return
	 */
	public BillnoRule getBillnoRule(String billCode){
		BillnoRule rule = (BillnoRule) executeQueryForObject("BillnoRule.getBillnoRule", billCode);
		return rule;
	}
	/**
	 * 保存或修改单据
	 * @param billnoRule
	 */
	public void updateBillnoRule(BillnoRule billnoRule){
		executeUpdate("BillnoRule.updateBillnoRule", billnoRule);
	}
	/**
	 * 删除单据
	 * @param billCode
	 */
	public void deleteBillnoRule(String billCode){
		executeUpdate("BillnoRule.deleteBillnoRule", billCode);
	}
}