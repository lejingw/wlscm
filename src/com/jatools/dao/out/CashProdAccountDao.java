package com.jatools.dao.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.util.SelectorOption;

public interface CashProdAccountDao {

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
	
	void updateCashProdAccountUserNums(CashProdAccount CashProdAccount);
	
	/**
	 * 删除货品台账
	 * @param prId
	 */
	void deleteCashProdAccount(String prId);
	/**
	 * 修改 台账 状态为 已销账
	 * @param billno
	 * @param itemClassId
	 * @param dotype 交接退料-1  委外发料1
	 * @param materialType 原料类型
	 */
	void modifyCashProdAccountChecked(String billno, String itemClassId, String userId, String dotype, String materialType);
	/**
	 * 供 查询 本次不合格是否已经写入台账
	 * @param billno
	 * @param itemClassId
	 * @param dotype 交接退料-1  委外发料1
	 * @param materialType 原料类型
	 * @return
	 */
	int selectCashProdAccountCount(String billno, String itemClassId, String dotype, String materialType);
	
	/**
	 * 修改台账状态
	 * @param prId
	 * @param status
	 * @param userId
	 */
	void modifyProdAccountStatus(String prId, String status, String userId);
	
	/**
	 * 取台账记录
	 * @param billno 来源单据号
	 * @param ornaCode 饰品编码
	 * @param isChecked 是否已经核销完成
	 * @return
	 */
	CashProdAccount getCashProdAccount(String billno, String ornaCode, String isChecked);
	
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
