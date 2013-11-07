package com.jatools.dao.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutVendorHead;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterNoActive;


public interface OutVendorDao {
	/**
	 * 获取对公销售,代销退货 ,委外退料头分 页信息
	 * @param condition
	 */
	Pager getVendorHeadPage(Map<String, String> condition);
	/**
	 * 获取委外发料未结算数量列表
	 * @param condition
	 */
	Pager getReportSaleVendorHeadPage(Map<String, String> condition);
	/**
	 * 获取对公销售单未出票数量列表
	 * @param condition
	 */
	Pager getReportOutVendorHeadPage(Map<String, String> condition);
	/**
	 * 获取委外发料未结算数量列表
	 * @param condition
	 */
	Pager getReportSendVendorHeadPage(Map<String, String> condition);
	/**
	 * 根据饰品编码或条纹获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	OutOrna getOrna(String code, boolean ornaFlag);
	/**
	 * 删除行
	 * @param delIds
	 */
	void deleteLines(String delIds);
	/**
	 * 更新头信息
	 * @param head
	 */
	void updateHead(OutVendorHead head);
	/**
	 * 插入头信息
	 * @param head
	 * @return
	 */
	String insertHead(OutVendorHead head);
	/**
	 * 更新行信息
	 * @param line
	 */
	void updateLine(OutVendorLine line);
	/**
	 * 插入行信息
	 * @param line
	 */
	void insertLine(OutVendorLine line);
	 /**
	  * 根据头id获取行信息
	  * @param headId
	  * @return
	  */
	List<OutVendorLine> getVendorLines(String headId);
	/**
	 * 获取头信息
	 * @param headId
	 * @return
	 */
	OutVendorHead getVendorHead(String headId);
	/**
	 * 删除头信息
	 * @param headId
	 */
	void deleteHead(String headId);
	/**
	 * 根据头ID删除所有行
	 * @param headId
	 */
	void deleteLinesByHeadId(String headId);
	/**
	 * 改变状态
	 * @param head
	 */
	void changeHeadStatus(OutVendorHead head);
	/**
	 * 改变饰品现有量状态
	 * @param code
	 * @param noActive是否是非现有量，true:非->现在有量,false:现在有量->非现在有量
	 */
	void moveOrna(String code,boolean noActive);
	/**
	 * 删除现有量
	 * @param code
	 */
	void deleteOrna(String code);
	/**
	 * 删除非现有量
	 * @param code
	 */
	void deleteOrnaNo(String code);
	void saveMaterTrans(OutVendorLine line);
	void saveCashMoneyAccount(OutVendorHead head);
	void saveCashProdAccount(OutVendorHead head, OutVendorLine line);
	void changeMaterStatus(String ornaCode, boolean noActive,String billType,String billNo);
	String getFinaceType(String billType);
	String getItemClassByMaterial(String materialType);
	void changeProdChecked(String billNo);
	String getLossMoney(String venderId,String payMoney);
	String getUnitFromMaterial(String materialType,String itemClass);
	List<PurchaseHead> getPurchaseByVendorId(String vendorId);
	List<MaterNoActive> getPrintLineByNoMater(String id);
	List<MaterActive> getPrintLineByMater(String id);
	
	
	void cashCharge(OutVendorHead head);



    OutVendorHead getVendorHeadByBillno(String billno);
}