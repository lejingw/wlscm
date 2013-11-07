package com.jatools.web.dwr.pur.cash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.pur.cash.CashPayChangeManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.vo.pur.cash.CashPayChange;
import com.jatools.web.util.StringUtil;

public class PayChangeDwr {

	private static Logger logger = Logger.getLogger(PayChangeDwr.class);
	
	private CommonManager commonManager;
	private CashPayChangeManager cashPayChangeManager;
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public void setCashPayChangeManager(CashPayChangeManager cashPayChangeManager) {
		this.cashPayChangeManager = cashPayChangeManager;
	}

	public String saveOrUpdatePayChange(CashPayChange payChange, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(payChange.getChaId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_TIAOZHENGDAN_ZJ);
				payChange.setBillNo(billNo);
			}
			cashPayChangeManager.saveOrUpdateCashPayChange(payChange, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	public String deletePayChange(String payChangeId, HttpServletRequest req) {
		try{
			if(StringUtil.isNotEmpty(payChangeId)){
				this.cashPayChangeManager.deleteCashPayChange(payChangeId);
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
	public Map<String, String> saveAndCheckBill(CashPayChange payChange, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(StringUtil.isEmpty(payChange.getChaId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_TIAOZHENGDAN_ZJ);
				payChange.setBillNo(billNo);
			}
			this.cashPayChangeManager.saveAndCheckCashPayChange(payChange, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("chaId", payChange.getChaId());
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "提交审核失败");
			}
		}
		return result;
	}
	
	public Map<String, String> checkBill(String chaId, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.cashPayChangeManager.checkProdProd(chaId, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "提交审核失败");
			}
		}
		return result;
	}
	
	public Map<String, String> closeBill(String chaId, List<CashLine> lines, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.cashPayChangeManager.closeBill(chaId, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "核算失败");
			}
		}
		return result;
	}
	
}
