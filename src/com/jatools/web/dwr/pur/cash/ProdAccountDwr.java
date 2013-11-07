package com.jatools.web.dwr.pur.cash;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jatools.manager.out.CashProdAccountManager;
import com.jatools.manager.pur.cash.CashHeadManager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.util.SelectorOption;

public class ProdAccountDwr {

	private CashProdAccountManager cashProdAccountManager;
	private CashHeadManager cashHeadManager;
	public void setCashHeadManager(CashHeadManager cashHeadManager) {
		this.cashHeadManager = cashHeadManager;
	}
	public void setCashProdAccountManager(CashProdAccountManager cashProdAccountManager) {
		this.cashProdAccountManager = cashProdAccountManager;
	}

	/**
	 * 取货品台账中供应商列表
	 * @return
	 */
	public List<SelectorOption> getVendorList(){
		return this.cashProdAccountManager.getVendorList();
	}
	
	/**
	 * 根据供应商 查询货品台账中 商品类别
	 * @param vendorId
	 * @return
	 */
	public List<SelectorOption> getArticleList(String vendorId){
		return this.cashProdAccountManager.getArticleList(vendorId);
	}
	
	/**
	 * 根据商品类别 查询货品台账中 大类
	 * @param articleId
	 * @return
	 */
	public List<SelectorOption> getItemClassList(String articleId){
		return this.cashProdAccountManager.getItemClassList(articleId);
	}
	
	/**
	 * 查询货品台账中 原料类型
	 * @param articleId
	 * @return
	 */
	public List<SelectorOption> getMaterialTypeList(){
		return this.cashProdAccountManager.getMaterialTypeList();
	}
	
	public boolean checkProdAccount(String prId, HttpServletRequest req){
		CashProdAccount prodAccount = this.cashHeadManager.getProdAccountById(prId);
		if(prodAccount != null){
			return true;
		}
		return false;
	}
}
