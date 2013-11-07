package com.jatools.manager.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.PackageMaterActive;

public interface MaterActiveManager {
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
	 * @return
	 */
	public PackageMaterActive getPackageMAByOrnaCode(String ornaCode, String orgId, String stockId, String isPackage);
	/**
	 * 供拆包混包使用<br>
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getPackageMAByBarCode(String barCode, String orgId, String stockId, String isPackage);
	
	/**
	 * 原料货位维护<br>
	 * 根据饰品编码和组织获取饰品信息 查询的是饰品库
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public PackageMaterActive getCargoMAByOrnaCode(String ornaCode, String orgId);
	/**
	 * 原料货位维护<br>
	 * 根据饰品条码和组织获取饰品信息 查询的是饰品库
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getCargoMAByBarCode(String barCode, String orgId);
	
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
}
