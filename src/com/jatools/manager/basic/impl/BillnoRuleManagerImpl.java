package com.jatools.manager.basic.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.BillnoRuleDao;
import com.jatools.manager.basic.BillnoRuleManager;
import com.jatools.vo.basic.BillnoRule;
import com.jatools.vo.util.SelectorOption;

public class BillnoRuleManagerImpl implements BillnoRuleManager {
	private BillnoRuleDao billnoRuleDao;

	public void setBillnoRuleDao(BillnoRuleDao billnoRuleDao) {
		this.billnoRuleDao = billnoRuleDao;
	}
	/**
	 * 获取分页数据
	 * 
	 * @param condition
	 * @return
	 */
	public Pager getBillnoRulePageData(Map<String, String> condition){
		return billnoRuleDao.getBillnoRulePageData(condition);
	}
	/**
	 * 获取单据编码
	 * @param billCode
	 * @return
	 */
	public List<SelectorOption> getBillnoCodeForSlt(String billCode){
		return billnoRuleDao.getBillnoCodeForSlt(billCode);
	}
	/**
	 * 检查单据编号是否已经存在
	 * @param billCode
	 * @return
	 */
	public boolean checkBillCode(String billCode){
		return billnoRuleDao.checkBillCode(billCode);
	}
	/**
	 * 保存单据
	 * @param billnoRule
	 */
	public void saveBillnoRule(BillnoRule billnoRule){
		billnoRuleDao.saveBillnoRule(billnoRule);
	}
	/**
	 * 根据id获取单号编码规则信息记录
	 * @param billnoRuleId
	 * @return
	 */
	public BillnoRule getBillnoRule(String billnoRuleId){
		return billnoRuleDao.getBillnoRule(billnoRuleId);
	}
	/**
	 * 保存或修改单据
	 * @param billnoRule
	 */
	public void updateBillnoRule(BillnoRule billnoRule){
		billnoRuleDao.updateBillnoRule(billnoRule);
	}
	/**
	 * 删除单据
	 * @param billCode
	 */
	public void deleteBillnoRule(String billCode){
		billnoRuleDao.deleteBillnoRule(billCode);
	}
}
