package com.jatools.web.dwr.pur.cash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.pur.cash.CashHeadManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.cash.CashHead;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.web.util.StringUtil;

public class CashHeadDwr {

	private static Logger logger = Logger.getLogger(CashHeadDwr.class);
	
	private CommonManager commonManager;
	private CashHeadManager cashHeadManager;
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public void setCashHeadManager(CashHeadManager cashHeadManager) {
		this.cashHeadManager = cashHeadManager;
	}

	public String saveOrUpdateCashHead(CashHead cashHead, List<CashLine> lines, String deleteIds, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(cashHead.getCashId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_JISUANDAN);
				cashHead.setBillNo(billNo);
			}
			cashHeadManager.saveOrUpdateCashHead(cashHead, lines, deleteIds, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public Map<String, String> saveAndCheckCashHead(CashHead cashHead, List<CashLine> lines, String deleteIds, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(StringUtil.isEmpty(cashHead.getCashId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_JISUANDAN);
				cashHead.setBillNo(billNo);
			}
			this.cashHeadManager.saveAndCheckCashHead(cashHead, lines, deleteIds, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("cashId", cashHead.getCashId());
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
	
	public String deleteCashHead(String cashId, HttpServletRequest req) {
		try{
			if(StringUtil.isNotEmpty(cashId)){
				this.cashHeadManager.deleteCashHead(cashId, CommonUtil.getSessionUserId(req));
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
	
//	public Map<String, String> checkBill(String cashId, HttpServletRequest req) {
//		Map<String, String> result = new HashMap<String, String>();
//		try {
//			this.cashHeadManager.checkBillHead(cashId, CommonUtil.getSessionUserId(req));
//			result.put("isSuccess", "true");
//		} catch(Exception e) {
//			logger.error(e);
//			result.put("isSuccess", "false");
//			if(StringUtil.isNotBlank(e.getMessage())){
//				result.put("msg", e.getMessage());
//			} else {
//				result.put("msg", "提交审核失败");
//			}
//		}
//		return result;
//	}
	
	public Map<String, String> closeBill(String cashId, List<CashLine> lines, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.cashHeadManager.closeBill(cashId, lines, CommonUtil.getSessionUserId(req));
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
