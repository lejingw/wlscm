package com.jatools.web.dwr.basic;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.BillnoRuleManager;
import com.jatools.vo.basic.BillnoRule;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class BillnoRuleDwr {
	private BillnoRuleManager billnoRuleManager;

	public void setBillnoRuleManager(BillnoRuleManager billnoRuleManager) {
		this.billnoRuleManager = billnoRuleManager;
	}
	/**
	 * 获取单据编码
	 * @param billCode
	 * @return
	 */
	public List<SelectorOption> getBillCodeForSlt(String billCode){
		List<SelectorOption> list = billnoRuleManager.getBillnoCodeForSlt(billCode);
		return list;
	}
	
	/**
	 * 删除单据编码
	 * @param billCode
	 * @return
	 */
	public String deleteBillCode(String billCode){
		billnoRuleManager.deleteBillnoRule(billCode);
		return null;
	}
	/**
	 * 保存单据编码
	 */
	public String saveBillCode(String billCode, String billName, String billnoRule, String finance, String memo, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		BillnoRule rule = new BillnoRule();
		rule.setBillCode(billCode);
		rule.setBillName(billName);
		rule.setBillnoRule(billnoRule);
		rule.setFinance(finance);
		rule.setMemo(memo);
		rule.setUpdateDate(DateUtil.getCurrentDate18());
		rule.setUpdateId(userid);
		if(billnoRuleManager.checkBillCode(billCode)){
			billnoRuleManager.updateBillnoRule(rule);
		}else{
			rule.setCreateDate(DateUtil.getCurrentDate18());
			rule.setCreateId(userid);
			rule.setStatus(DictConstant.BILL_STATUS_SAVE);
			billnoRuleManager.saveBillnoRule(rule);//保存单据
		}
		return null;
	}
}
