package com.jatools.dao.out.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.out.CashProdAccountDao;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class CashProdAccountDaoImpl extends BaseDao implements CashProdAccountDao {

	@Override
	public void saveCashProdAccount(CashProdAccount cashProdAccount) {
		executeInsert("CashProdAccount.saveCashProdAccount", cashProdAccount);
	}

	@Override
	public CashProdAccount getCashProdAccount(String prId) {
		return (CashProdAccount)executeQueryForObject("CashProdAccount.getCashProdAccount", prId);
	}


	@Override
	public void updateCashProdAccount(CashProdAccount cashProdAccount) {
		executeUpdate("CashProdAccount.updateCashProdAccount", cashProdAccount);
	}@Override
	public void updateCashProdAccountUserNums(CashProdAccount cashProdAccount) {
		executeUpdate("CashProdAccount.updateCashProdAccountUserNums", cashProdAccount);
	}

	@Override
	public void deleteCashProdAccount(String prId) {
		executeUpdate("CashProdAccount.deleteCashProdAccount", prId);
	}

	@Override
	public Pager getCashProdAccountData(Map<String, String> condition) {
		return executeQueryForPager("CashProdAccount.getCashProdAccountPageData", "CashProdAccount.getCashProdAccountTotalCount", condition);
	}

	public void modifyCashProdAccountChecked(String billno, String itemClassId, String userId, String dotype, String materialType){
		Map<String , String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("itemClassId", itemClassId);
		params.put("userId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		params.put("dotype", dotype);
		params.put("materialType", materialType);
		executeUpdate("CashProdAccount.modifyCashProdAccountChecked", params);
	}
	
	public int selectCashProdAccountCount(String billno, String itemClassId, String dotype, String materialType){
		Map<String , String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("itemClassId", itemClassId);
		params.put("dotype", dotype);
		params.put("materialType", materialType);
		Object res = executeQueryForObject("CashProdAccount.selectCashProdAccountCount", params);
		if(res != null){
			return (Integer)res;
		}
		return 0;
	}
	
	public void modifyProdAccountStatus(String prId, String status, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("prId", prId);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("CashProdAccount.modifyProdAccountStatus", params);
	}

	@Override
	public CashProdAccount getCashProdAccount(String billno, String ornaCode, String isChecked) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billno", billno);
		params.put("ornaCode", ornaCode);
		params.put("isChecked", isChecked);
		return (CashProdAccount)executeQueryForObject("CashProdAccount.searchCashProdAccount", params);
	}

	@Override
	public List<CashProdAccount> getCashListByPrid(String prId) {
		return (List<CashProdAccount>)executeQueryForList("CashProdAccount.getCashListByPrid", prId);
	}

	@Override
	public List<SelectorOption> getVendorList() {
		return (List<SelectorOption>)executeQueryForList("CashProdAccount.getVendorList", null);
	}

	@Override
	public List<SelectorOption> getArticleList(String vendorId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("voderId", vendorId);
		return (List<SelectorOption>)executeQueryForList("CashProdAccount.getArticleList", params);
	}

	@Override
	public List<SelectorOption> getItemClassList(String articleId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("articleId", articleId);
		return (List<SelectorOption>)executeQueryForList("CashProdAccount.getItemClassList", params);
	}
	
	public List<SelectorOption> getMaterialTypeList() {
		return (List<SelectorOption>)executeQueryForList("CashProdAccount.getMaterialTypeList", null);
	}

	@Override
	public void deleteAccountUserByPriceLineId(String priceLineId) {
		executeUpdate("CashProdAccount.deleteAccountUserByPriceLineId", priceLineId);
	}

	@Override
	public void deleteAccountUserByPriceHeadId(String priceHeadId) {
		executeUpdate("CashProdAccount.deleteAccountUserByPriceHeadId", priceHeadId);
	}

	@Override
	public void subAccountUserNumByPriceLineId(String priceLineId) {
		executeUpdate("CashProdAccount.subAccountUserNumByPriceLineId", priceLineId);
	}
	
	@Override
	public void addAccountUserNumByPriceLineId(String priceLineId) {
		executeUpdate("CashProdAccount.addAccountUserNumByPriceLineId", priceLineId);
	}

	@Override
	public void subAccountUserNumByPriceHeadId(String priceHeadId) {
		executeUpdate("CashProdAccount.subAccountUserNumByPriceHeadId", priceHeadId);
	}
}
