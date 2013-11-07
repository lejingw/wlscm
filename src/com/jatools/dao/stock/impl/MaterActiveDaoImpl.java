package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterInStock;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.web.util.DateUtil;

public class MaterActiveDaoImpl extends BaseDao implements MaterActiveDao {
	/**
	 * 根据饰品编码获取现有量信息
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String ornaCode){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ornaCode", ornaCode);
		return (MaterActive)executeQueryForObject("MaterActive.getMaterActiveByOrnaCode", map);
	}
	/**
	 * 根据饰品条码获取现有量信息
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByBarCode(String barCode){
		Map<String, String> map = new HashMap<String, String>();
		map.put("barCode", barCode);
		return (MaterActive)executeQueryForObject("MaterActive.getMaterActiveByBarCode", map);
	}

    /**
     * 标签打印单 取饰品信息
     * @param ornaCode
     * @return
     */
    public MaterActive getMaterActiveByOrnaCodeTagPrint(String ornaCode){
        return (MaterActive)executeQueryForObject("MaterActive.getMaterActiveByOrnaCodeTagPrint", ornaCode);
    }
	/**
	 * 根据饰品编码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String ornaCode, String orgId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ornaCode", ornaCode);
		map.put("orgId", orgId);
		return (MaterActive)executeQueryForObject("MaterActive.getMaterActiveByOrnaCode", map);
	}
	/**
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public MaterActive getMaterActiveByBarCode(String barCode, String orgId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("barCode", barCode);
		map.put("orgId", orgId);
		return (MaterActive)executeQueryForObject("MaterActive.getMaterActiveByBarCode", map);
	}
	
	/**
	 * 供拆包混包使用<br>
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @param isPackage 拆包混包标记
	 * @return
	 */
	public PackageMaterActive getPackageMAByBarCode(String barCode, String orgId, String stockId, String isPackage){
		Map<String, String> map = new HashMap<String, String>();
		map.put("barCode", barCode);
		map.put("orgId", orgId);
		map.put("stockId", stockId);
		map.put("package", isPackage);
		return (PackageMaterActive)executeQueryForObject("MaterActive.getPackageMAByBarCode", map);
	}
	
	/**
	 * 供拆包混包使用<br>
	 * 根据饰品编码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @param isPackage 拆包混包标记
	 * @return
	 */
	public PackageMaterActive getPackageMAByOrnaCode(String ornaCode, String orgId, String stockId, String isPackage){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ornaCode", ornaCode);
		map.put("orgId", orgId);
		map.put("stockId", stockId);
		map.put("package", isPackage);
		return (PackageMaterActive)executeQueryForObject("MaterActive.getPackageMAByOrnaCode", map);
	}
	
	/**
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getChangeMAByBarCode(String barCode, String orgId, String stockId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("barCode", barCode);
		map.put("orgId", orgId);
		map.put("stockId", stockId);
		map.put("package", null);
		return (PackageMaterActive)executeQueryForObject("MaterActive.getPackageMAByBarCode", map);
	}
	
	/**
	 * 根据饰品编码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getChangeMAByOrnaCode(String ornaCode, String orgId, String stockId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ornaCode", ornaCode);
		map.put("orgId", orgId);
		map.put("stockId", stockId);
		map.put("package", null);
		return (PackageMaterActive)executeQueryForObject("MaterActive.getPackageMAByOrnaCode", map);
	}
	
	public PackageMaterActive getMaterNoactiveByBarCode(String barCode){
		Map<String, String> map = new HashMap<String, String>();
		map.put("barCode", barCode);
		return (PackageMaterActive)executeQueryForObject("MaterActive.getMaterNoactiveByBarCode", map);
	}

	public PackageMaterActive getMaterNoactiveByOrnaCode(String ornaCode){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ornaCode", ornaCode);
		return (PackageMaterActive)executeQueryForObject("MaterActive.getMaterNoactiveByOrnaCode", map);
	}
	
	
	/**
	 * 标记现有量饰品状态为"保留"901
	 * @param ornaCode
	 * @param billCode
	 * @param billId
	 */
	public void markMaterActiveUsed(String ornaCode, String billCode, String billNo){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("ornaCode", ornaCode);
		condition.put("billCode", billCode);
		condition.put("billNo", billNo);
		executeUpdate("MaterActive.markMaterActiveUsed", condition);
	}
	/**
	 * 标记现有量饰品状态为"有效"900
	 * @param ornaCode
	 */
	public void markMaterActiveValid(String ornaCode){
		executeUpdate("MaterActive.markMaterActiveValid", ornaCode);
	}
	
	/**
	 * 把饰品从现有量表移入非现有量
	 */
	public void moveMaterActive2NoActive(String ornaCode){
		executeInsert("MaterActive.copyMaterActive2NoActive", ornaCode);
		executeUpdate("MaterActive.deleteMaterActive", ornaCode);
	}
	
	public PackageMaterActive getMaterActiveFromAllByOrnaCode(String ornaCode) {
		return (PackageMaterActive)executeQueryForObject("MaterActive.getMaterActiveFromAllByOrnaCode", ornaCode);
	}
	
	public void packageInStock(MaterInStock inStock) {
		executeInsert("MaterActive.packageInStock", inStock);
	}
	
	public Pager getMaterActivePagerData(Map<String, String> params) {
		return executeQueryForPager("MaterActive.getMaterActivePageData", "MaterActive.getMaterActiveTotalCount", params);
	}
	
	
	
	public void addMaterMaterial(String ornaCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isMaterial", "1");
		params.put("ornaCode", ornaCode);
		executeUpdate("MaterActive.modifyMaterial", params);
	}
	
	public void deleteMaterMaterial(String ornaCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isMaterial", "0");
		params.put("ornaCode", ornaCode);
		executeUpdate("MaterActive.modifyMaterial", params);
	}
	
	
	public String getStockCount(String orgId, String stockId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("stockId", stockId);
		params.put("orgId", orgId);
		return (String)executeQueryForObject("MaterActive.getStockCount", params);
	}
	
	
	
	public PackageMaterActive getPackageMA(String ornaCode, String barCode, String orgId, String stockId, String state) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ornaCode", ornaCode);
		params.put("barCode", barCode);
		params.put("orgId", orgId);
		params.put("stockId", stockId);
		params.put("state", state);
		return (PackageMaterActive)executeQueryForObject("MaterActive.getPackageMA", params);
	}
	/**
	 * 供盘点使用的取现有量数据 通过 饰品编码
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @param isMaterial
	 * @return
	 */
	public PackageMaterActive getMAByOrnaCOde(String ornaCode, String orgId, String stockId) {
		return this.getPackageMA(ornaCode, null, null, null, null);
	}
	/**
	 * 供盘点使用的取现有量数据
	 * @param barCode
	 * @param orgId
	 * @param stockId
	 * @param isMaterial
	 * @return
	 */
	public PackageMaterActive getMAByBarCode(String barCode, String orgId, String stockId) {
		return this.getPackageMA(null, barCode, null, null, null);
	}
	
	
	public void inStockFormIniv(String srcBillId, String srcBillCode){
		Map<String, String> params = new HashMap<String, String>();
		params.put("srcBillId", srcBillId);
		params.put("srcBillCode", srcBillCode);
		executeInsert("MaterActive.inStockFormIniv", params);
	}
	
	@Override
	public void moveActive2NoActiveForChange(String billid) {
		executeInsert("MaterActive.moveActive2NoActiveForChange", billid);
		executeUpdate("MaterActive.deleteActive2NoActiveForChange", billid);
	}
	
	/**
	 * 调拨单入库,改现有量饰品组织、柜组、网点金额段
	 * @param headid
	 * @param userid
	 */
	public void updateOrgGroupFromMoveBill(String headid, String userid){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("headid", headid);
		conditioin.put("userid", userid);
		conditioin.put("toshopdate", DateUtil.getCurrentDate18());
		executeInsert("MaterActive.updateOrgGroupFromMoveBill", conditioin);
	}

    /**
     * 调拨单入库,改现有量饰品组织、柜组、网点金额段
     * @param headid
     * @param userid
     * @param isJmMove 是否是加盟门店之间的调拨
     */
    public void updateOrgGroupFromMoveBill(String headid, String userid, boolean isJmMove){
        Map<String, String> conditioin = new HashMap<String, String>();
        conditioin.put("headid", headid);
        conditioin.put("userid", userid);
        conditioin.put("isJmMove", isJmMove?"1":"0");
        conditioin.put("toshopdate", DateUtil.getCurrentDate18());
        executeInsert("MaterActive.updateOrgGroupFromMoveBill", conditioin);
    }
	/**
	 * 改现有量状态，将废弃的行记录还原为有效
	 * @param headid
	 * @param userid
	 */
	public void updateStatusFromMoveBill(String headid, String userid){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("headid", headid);
		conditioin.put("userid", userid);
		executeUpdate("MaterActive.updateStatusFromMoveBill", conditioin);
	}

	/**
	 * 移库调拨单接收
	 * @param stockId
	 * @param headid
	 * @param userid
	 */
	public void updateStockFromMoveBill(String stockId, String headid, String userid){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("stockId", stockId);
		conditioin.put("headid", headid);
		executeUpdate("MaterActive.updateStockFromMoveBill", conditioin);
	}
	/**
	 * 柜组调拨单接收
	 * @param groups
	 * @param headid
	 * @param userid
	 */
	public void updateGroupsFromMoveBill(String groups, String headid, String userid){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("groups", groups);
		conditioin.put("headid", headid);
		executeUpdate("MaterActive.updateGroupsFromMoveBill", conditioin);
	}
	/**
	 * 当改总部状态为已到店时，修改饰品状态为有效
	 * @param gheadid
	 * @param userid
	 */
	public int updateHqOrnaStatus(String gheadid, String userid, String srcBillCode){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("gheadid", gheadid);
		conditioin.put("userid", userid);
		conditioin.put("date", DateUtil.getCurrentDate18());
		conditioin.put("srcBillCode", srcBillCode);
		return executeUpdate("MaterActive.updateHqOrnaStatus", conditioin);
	}
	
	@Override
	public MaterActive getMaterActiveForLabelByOrnaCode(String ornaCode) {
		return this.getMaterActiveForLabel(ornaCode, null);
	}
	@Override
	public MaterActive getMaterActiveForLabelByornaBarcode(String ornaBarcode) {
		return this.getMaterActiveForLabel(null, ornaBarcode);
	}
	/**
	 * 生成调拨单修改现拥有量表中的引用单据编码和单据编号段
	 * @param moveHeadIds
	 */
	public void updateBillCodeFromDispatch(String moveHeadIds){
		executeUpdate("MaterActive.updateBillCodeFromDispatch", moveHeadIds);
	}
	
	private MaterActive getMaterActiveForLabel(String ornaCode, String ornaBarcode){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("ornaCode", ornaCode);
		conditioin.put("ornaBarcode", ornaBarcode);
		return (MaterActive)executeQueryForObject("MaterActive.getMaterActiveForLabel", conditioin);
	}
	
	public void updateConsignByPlSale(String plSaleId, boolean isConsign){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("billid", plSaleId);
		conditioin.put("isConsign", isConsign?"0":"1");
		conditioin.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("MaterActive.updateConsignByPlSale", conditioin);// 修改现有量代销状态
		executeUpdate("MaterActive.updateNoActiveConsignByPlSale", conditioin);// 修改非现有量代销状态
		executeUpdate("MaterActive.updateSaleListConsignByPlSale", conditioin);
	}
	
	public void updatePosMoneyByJmSale(String jmSaleId){
		Map<String, String> conditioin = new HashMap<String, String>();
		conditioin.put("billid", jmSaleId);
		conditioin.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("MaterActive.updatePosMoneyByJmSale", conditioin);
	}
	/**
	 * 标准配货删除单据
	 * @param gheadid
	 */
	public void markMaterActiveValidByStandardDispatch(String gheadid){
		executeUpdate("MaterActive.markMaterActiveValidByStandardDispatch", gheadid);
	}

    @Override
    public void markMaterActiveUsedByStandardDistatch(String ornaCode, String billCode, String headid) {
        this.markMaterActiveUsedByDistatch(ornaCode, billCode, headid, "1");
    }

    @Override
    public void markMaterActiveUsedByBillDistatch(String ornaCode, String billCode, String headid) {
        this.markMaterActiveUsedByDistatch(ornaCode, billCode, headid, "2");
    }

    @Override
    public void markMaterActiveUsedByPushDistatch(String ornaCode, String billCode, String headid) {
        this.markMaterActiveUsedByDistatch(ornaCode, billCode, headid, "3");
    }

    private void markMaterActiveUsedByDistatch(String ornaCode, String billCode, String headid, String type) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ornaCode", ornaCode);
        params.put("billCode", billCode);
        params.put("headid", headid);
        params.put("type", type);
        this.executeUpdate("MaterActive.markMaterActiveUsedByDistatch", params);
    }
}
