package com.jatools.web.dwr.pur.cash;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.pur.cash.PayMoneyManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.cash.PayMoney;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PayMoneyDwr {

	private static Logger logger = Logger.getLogger(PayMoneyDwr.class);
	
	private CommonManager commonManager;
	private PayMoneyManager payMoneyManager;
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public void setPayMoneyManager(PayMoneyManager payMoneyManager) {
		this.payMoneyManager = payMoneyManager;
	}

	public String saveOrUpdatePayMoney(PayMoney payMoney, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(payMoney.getPayId())){
				payMoney.setCreateDate(DateUtil.getCurrentDate18());
				payMoney.setCreateId(CommonUtil.getSessionUserId(req));
				payMoney.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_FUKUANDAN);
				payMoney.setBillNo(billNo);
			}
			payMoney.setUpdateDate(DateUtil.getCurrentDate18());
			payMoney.setUpdateId(CommonUtil.getSessionUserId(req));
			payMoneyManager.saveOrUpdatePayMoney(payMoney, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public Map<String, String> saveAndAccountPayMoney(PayMoney payMoney, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			if(StringUtil.isEmpty(payMoney.getPayId())){
				payMoney.setCreateDate(DateUtil.getCurrentDate18());
				payMoney.setCreateId(CommonUtil.getSessionUserId(req));
				payMoney.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_FUKUANDAN);
				payMoney.setBillNo(billNo);
			}
			payMoney.setUpdateDate(DateUtil.getCurrentDate18());
			payMoney.setUpdateId(CommonUtil.getSessionUserId(req));
			payMoneyManager.saveAndAccountPayMoney(payMoney, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("payId", payMoney.getPayId());
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "fasle");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "记账失败");
			}
			return result;
		}
	}
	
	public String deletePayMoney(String payMoneyId, HttpServletRequest req) {
		try{
			if(StringUtil.isNotEmpty(payMoneyId)){
				this.payMoneyManager.deletePayMoney(payMoneyId);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "删除失败";
		}
	}
	
	public Map<String, String> writeAccount(String payId, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.payMoneyManager.insertMoneyAccountFromPayMoney(payId, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e){
			logger.error(e);
			result.put("isSuccess", "fasle");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "记账失败");
			}
		}
		return result;
	}
	
	public Map<String, String> cancelPayMoney(String payId, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.payMoneyManager.cancelMoneyAccountFromPayMoney(payId, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e){
			logger.error(e);
			result.put("isSuccess", "fasle");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "撤销失败");
			}
		}
		return result;
	}
	
	/**
	 * 取欠供应商的当前金额
	 * @param vendorId
	 * @param req
	 * @return
	 */
	public Double getLessMoney(String vendorId, HttpServletRequest req){
		return this.payMoneyManager.getLessMoney(vendorId);
	}
}
