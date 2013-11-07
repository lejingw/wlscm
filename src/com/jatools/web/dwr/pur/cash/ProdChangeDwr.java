package com.jatools.web.dwr.pur.cash;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.pur.cash.CashProdChangeManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.cash.CashProdChange;
import com.jatools.web.util.StringUtil;

public class ProdChangeDwr {

	private static Logger logger = Logger.getLogger(ProdChangeDwr.class);
	
	private CommonManager commonManager;
	private CashProdChangeManager cashProdChangeManager;
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public void setCashProdChangeManager(CashProdChangeManager cashProdChangeManager) {
		this.cashProdChangeManager = cashProdChangeManager;
	}

	public String saveOrUpdateProdChange(CashProdChange prodChange, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(prodChange.getChaId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_TIAOZHENGDAN_HP);
				prodChange.setBillNo(billNo);
			}
			cashProdChangeManager.saveOrUpdateCashProdChange(prodChange, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public String deleteProdChange(String prodChangeId, HttpServletRequest req) {
		try{
			if(StringUtil.isNotEmpty(prodChangeId)){
				this.cashProdChangeManager.deleteCashProdChange(prodChangeId);
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
	
	public Map<String, String> saveAndCheckBill(CashProdChange prodChange, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(StringUtil.isEmpty(prodChange.getChaId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_TIAOZHENGDAN_HP);
				prodChange.setBillNo(billNo);
			}
			this.cashProdChangeManager.saveAndCheckCashProdChange(prodChange, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("chaId", prodChange.getChaId());
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
			this.cashProdChangeManager.checkProdProd(chaId, CommonUtil.getSessionUserId(req));
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
	
	public Map<String, String> closeBill(String chaId, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.cashProdChangeManager.closeBill(chaId, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "关闭失败");
			}
		}
		return result;
	}
	
}
