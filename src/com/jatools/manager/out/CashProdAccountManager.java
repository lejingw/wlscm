package com.jatools.manager.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.util.SelectorOption;

public interface CashProdAccountManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getCashProdAccountData(Map<String, String> condition);
	
	
	/**
	 * 保存货品台账
	 * @param CashProdAccount
	 */
	void saveCashProdAccount(CashProdAccount CashProdAccount);
	void updateCashProdAccountUserNums(CashProdAccount CashProdAccount);
	
	/**
	 * 根据id获取货品台账
	 * @param prId
	 * @return
	 */
	CashProdAccount getCashProdAccount(String prId);
	
	
	/**
	 * 保存或修改货品台账
	 * @param CashProdAccount
	 */
	void updateCashProdAccount(CashProdAccount CashProdAccount);
	
	
	/**
	 * 删除货品台账
	 * @param prId
	 */
	void deleteCashProdAccount(String prId);
	
	/**
	 * 查询单据 被那些单据核销过
	 * @param prId
	 * @return
	 */
	List<CashProdAccount> getCashListByPrid(String prId);
	
	
	/**
	 * 取货品台账中供应商列表
	 * @return
	 */
	List<SelectorOption> getVendorList();
	
	/**
	 * 根据供应商 查询货品台账中 商品类别
	 * @param vendorId
	 * @return
	 */
	List<SelectorOption> getArticleList(String vendorId);
	
	/**
	 * 根据商品类别 查询货品台账中 大类
	 * @param articleId
	 * @return
	 */
	List<SelectorOption> getItemClassList(String articleId);
	
	
	List<SelectorOption> getMaterialTypeList();
	
	
	/**
	 * 通过核价单行 删除台账使用量表数据 
	 * @param priceLineId
	 */
	void deleteAccountUserByPriceLineId(String priceLineId);
	
	/**
	 * 通过核价单头 删除台账使用量表数据 
	 * @param priceHeadId
	 */
	void deleteAccountUserByPriceHeadId(String priceHeadId);
	
	/**
	 * 通过核价单行 减台账使用量段值
	 * @param priceLineId
	 */
	void subAccountUserNumByPriceLineId(String priceLineId);
	
	/**
	 * 通过核价单行 增加台账使用量段值
	 * @param priceLineId
	 */
	void addAccountUserNumByPriceLineId(String priceLineId);
	
	/**
	 * 通过核价单头 减台账使用量段值
	 * @param priceHeadId
	 */
	void subAccountUserNumByPriceHeadId(String priceHeadId);
}
