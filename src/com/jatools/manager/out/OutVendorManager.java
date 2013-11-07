package com.jatools.manager.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutVendorHead;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterNoActive;

public interface OutVendorManager {
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
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public OutOrna getOrnaInfo(String code, boolean ornaFlag);
	/**
	 * 保存对公销售
	 * @param head
	 * @param lines
	 * @param delIds
	 */
	void saveOrUpdatePubSale(OutVendorHead head, List<OutVendorLine> lines,
			String[] delIds);
	/**
	 * 获取头信息
	 * @param headId
	 * @return
	 */
	OutVendorHead getVendorHead(String headId);
	OutVendorHead getVendorHeadByBillno(String billno);
	/**
	 * 获取行信息
	 * @param headId
	 * @return
	 */
	List<OutVendorLine> getVendorLines(String headId);
	/**
	 * 删除单据
	 * @param headId
	 */
	void deleteVendor(String headId);
	/**
	 * 关闭单据
	 */
	void changeHeadStatus(OutVendorHead head);
	/**
	 * 根据发料类型查大类
	 */
	String getItemClassByMaterial(String materialType);

	List<PurchaseHead> getPurchaseByVendorId(String vendorId);

	List<MaterNoActive> getPrintLineByNoMater(String id);
	List<MaterActive> getPrintLineByMater(String id);
	
	
	void cashCharge(String billid, String saleMoney, String userId);
}
