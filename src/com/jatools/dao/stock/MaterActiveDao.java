package com.jatools.dao.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterInStock;
import com.jatools.vo.stock.PackageMaterActive;

public interface MaterActiveDao {
	/**
	 * 根据饰品编码获取现有量信息
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String ornaCode);
	/**
	 * 根据饰品条码获取现有量信息
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByBarCode(String barCode);

    /**
     * 标签打印单 取饰品信息
     * @param ornaCode
     * @return
     */
    public MaterActive getMaterActiveByOrnaCodeTagPrint(String ornaCode);

	/**
	 * 根据饰品编码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String ornaCode, String orgId);
	/**
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public MaterActive getMaterActiveByBarCode(String barCode, String orgId);
	
	
	/**
	 * 供拆包混包使用<br>
	 * 根据饰品编码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @param isPackage 拆包混包标记
	 * @return
	 */
	public PackageMaterActive getPackageMAByOrnaCode(String ornaCode, String orgId, String stockId, String isPackage);
	/**
	 * 供拆包混包使用<br>
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @param isPackage 拆包混包标记
	 * @return
	 */
	public PackageMaterActive getPackageMAByBarCode(String barCode, String orgId, String stockId, String isPackage);
	
	/**
	 * 根据饰品编码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getChangeMAByOrnaCode(String ornaCode, String orgId, String stockId);
	/**
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getChangeMAByBarCode(String barCode, String orgId, String stockId);
	
	/**
	 * 从非现有量库查询饰品
	 * @param barCode
	 * @return
	 */
	public PackageMaterActive getMaterNoactiveByBarCode(String barCode);
	/**
	 * 从非现有量库查询饰品
	 * @param ornaCode
	 * @return
	 */
	public PackageMaterActive getMaterNoactiveByOrnaCode(String ornaCode);
	
	/**
	 * 标记现有量饰品状态为"保留"901
	 * @param ornaCode
	 * @param billCode
	 * @param billId
	 */
	public void markMaterActiveUsed(String ornaCode, String billCode, String billNo);
	/**
	 * 标记现有量饰品状态为"有效"900
	 * @param ornaCode
	 */
	public void markMaterActiveValid(String ornaCode);
	
	/**
	 * 把饰品从现有量表移入非现有量
	 */
	public void moveMaterActive2NoActive(String ornaCode);
	
	/**
	 * 根据饰品编码从现有量和非现有量表中查询 饰品信息
	 * @param ornaCode
	 * @return
	 */
	public PackageMaterActive getMaterActiveFromAllByOrnaCode(String ornaCode);
	
	/**
	 * 拆混包 入库
	 * @param inStock
	 */
	public void packageInStock(MaterInStock inStock);
	
	/**
	 * 获取分页数据
	 * @param params
	 * @return
	 */
	public Pager getMaterActivePagerData(Map<String, String> params);
	
	/**
	 * 打标志
	 * @param ornaCode
	 */
	public void addMaterMaterial(String ornaCode);
	
	/**
	 * 清标志
	 * @param ornaCode
	 */
	public void deleteMaterMaterial(String ornaCode);
	
	/**
	 * 查询现有量库中饰品数量
	 * @param stockId 仓库id
	 * @param orgId 组织id
	 * @return
	 */
	public String getStockCount(String orgId, String stockId);
	
	
	public PackageMaterActive getPackageMA(String ornaCode, String barCode, String orgId, String stockId, String state);
	/**
	 * 供盘点使用的取现有量数据 通过 饰品编码
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @param state
	 * @return
	 */
	public PackageMaterActive getMAByOrnaCOde(String ornaCode, String orgId, String stockId);
	/**
	 * 供盘点使用的取现有量数据
	 * @param barCode
	 * @param orgId
	 * @param stockId
	 * @param state
	 * @return
	 */
	public PackageMaterActive getMAByBarCode(String barCode, String orgId, String stockId);
	
	/**
	 * 从入库单入库
	 * @param srcBillId
	 * @param srcBillCode
	 */
	void inStockFormIniv(String srcBillId, String srcBillCode);
	
	/**
	 * 根据 形态转换|料提纯|拆石 行表 把现有量数据 移到非现有量表中 
	 * @param billid 头表id
	 */
	void moveActive2NoActiveForChange(String billid);
	
	
	/**
	 * 
	 * @param headid
	 * @param userid
	 */
	public void updateOrgGroupFromMoveBill(String headid, String userid);
	/**
	 * 改现有量状态，将废弃的行记录还原为有效
	 * @param headid
	 * @param userid
	 */
	public void updateStatusFromMoveBill(String headid, String userid);

	/**
	 * 移库调拨单接收
	 * @param stockId
	 * @param headid
	 * @param userid
	 */
	public void updateStockFromMoveBill(String stockId, String headid, String userid);
	/**
	 * 柜组调拨单接收
	 * @param groups
	 * @param headid
	 * @param userid
	 */
	public void updateGroupsFromMoveBill(String groups, String headid, String userid);
	/**
	 * 当改总部状态为已到店时，修改饰品状态为有效
	 * @param gheadid
	 * @param userid
	 */
	public int updateHqOrnaStatus(String gheadid, String userid, String srcBillCode);
	
	/**
	 * 由饰品编码取饰品信息
	 * @param ornaCode
	 * @return
	 */
	MaterActive getMaterActiveForLabelByOrnaCode(String ornaCode);
	
	/**
	 * 跟据条码取饰品信息
	 * @param ornaBarcode
	 * @return
	 */
	MaterActive getMaterActiveForLabelByornaBarcode(String ornaBarcode);
	/**
	 * 生成调拨单修改现拥有量表中的引用单据编码和单据编号段
	 * @param moveHeadIds
	 */
	void updateBillCodeFromDispatch(String moveHeadIds);
	
	/**
	 * 修改饰品代销状态为非代销
	 * @param plSaleId
	 */
	void updateConsignByPlSale(String plSaleId, boolean isConsign);
	
	/**
	 * 修改饰品销价
	 * @param jmSaleId
	 */
	void updatePosMoneyByJmSale(String jmSaleId);
	/**
	 * 标准配货删除单据
	 * @param gheadid
	 */
	public void markMaterActiveValidByStandardDispatch(String gheadid);


    void updateOrgGroupFromMoveBill(String headid, String userid, boolean isJmMove);

    /**
     * 标准配货修改饰品保留状态 901
     * @param ornaCode
     * @param billCode
     * @param headid
     */
   void markMaterActiveUsedByStandardDistatch(String ornaCode, String billCode, String headid);

    /**
     * 订单配货修改饰品保留状态 901
     * @param ornaCode
     * @param billCode
     * @param headid
     */
    void markMaterActiveUsedByBillDistatch(String ornaCode, String billCode, String headid);

    /**
     * 推式配货修改饰品保留状态 901
     * @param ornaCode
     * @param billCode
     * @param headid
     */
    void markMaterActiveUsedByPushDistatch(String ornaCode, String billCode, String headid);
}
