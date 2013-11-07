package com.jatools.web.dwr.pur.cash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.pur.cash.CashProdProdManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.vo.pur.cash.CashProdProd;
import com.jatools.web.util.StringUtil;

public class ProdProdDwr {

	private static Logger logger = Logger.getLogger(ProdProdDwr.class);
	
	private CommonManager commonManager;
	private CashProdProdManager cashProdProdManager;
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public void setCashProdProdManager(CashProdProdManager cashProdProdManager) {
		this.cashProdProdManager = cashProdProdManager;
	}

	public String saveOrUpdateProdProd(CashProdProd prodProd, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(prodProd.getChaId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_LIAOZHUANLIAO);
				prodProd.setBillNo(billNo);
			}
			cashProdProdManager.saveOrUpdateCashProdProd(prodProd, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public String deleteProdProd(String id, HttpServletRequest req) {
		try{
			if(StringUtil.isNotEmpty(id)){
				this.cashProdProdManager.deleteCashProdProd(id, CommonUtil.getSessionUserId(req));
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
	
	public Map<String, String> checkBill(String chaId, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.cashProdProdManager.checkProdProd(chaId, CommonUtil.getSessionUserId(req));
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
	
	public Map<String, String> saveAndCheckBill(CashProdProd prodProd, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(StringUtil.isEmpty(prodProd.getChaId())){
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_LIAOZHUANLIAO);
				prodProd.setBillNo(billNo);
			}
			this.cashProdProdManager.saveAndCheckCashProdProd(prodProd, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("chaId", prodProd.getChaId());
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
			this.cashProdProdManager.closeBill(chaId, CommonUtil.getSessionUserId(req));
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
