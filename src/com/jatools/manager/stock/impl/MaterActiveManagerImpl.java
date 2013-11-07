package com.jatools.manager.stock.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.StockConstant;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.PackageMaterActive;

public class MaterActiveManagerImpl implements MaterActiveManager {
	private MaterActiveDao materActiveDao;

	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}

	/**
	 * 根据饰品编码获取现有量信息
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String ornaCode){
		return materActiveDao.getMaterActiveByOrnaCode(ornaCode);
	}
	/**
	 * 根据饰品条码获取现有量信息
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByBarCode(String barCode){
		return materActiveDao.getMaterActiveByBarCode(barCode);
	}

    /**
     * 标签打印单 取饰品信息
     * @param ornaCode
     * @return
     */
    public MaterActive getMaterActiveByOrnaCodeTagPrint(String ornaCode) {
        return this.materActiveDao.getMaterActiveByOrnaCodeTagPrint(ornaCode);
    }
	/**
	 * 根据饰品编码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String ornaCode, String orgId){
		return materActiveDao.getMaterActiveByOrnaCode(ornaCode, orgId);
	}
	/**
	 * 根据饰品条码和组织获取饰品信息
	 * @param ornaCode
	 * @param orgId
	 * @return
	 */
	public MaterActive getMaterActiveByBarCode(String barCode, String orgId){
		return materActiveDao.getMaterActiveByBarCode(barCode, orgId);
	}
	
	public PackageMaterActive getPackageMAByOrnaCode(String ornaCode, String orgId, String stockId, String isPackage){
		return this.materActiveDao.getPackageMAByOrnaCode(ornaCode, orgId, stockId, isPackage);
	}
	
	public PackageMaterActive getPackageMAByBarCode(String barCode, String orgId, String stockId, String isPackage) {
		return this.materActiveDao.getPackageMAByBarCode(barCode, orgId, stockId, isPackage);
	}
	
	public PackageMaterActive getCargoMAByOrnaCode(String ornaCode, String orgId){
		return this.materActiveDao.getPackageMAByOrnaCode(ornaCode, orgId, StockConstant.STOCK_NEW_ID, null);
	}
	
	public PackageMaterActive getCargoMAByBarCode(String barCode, String orgId){
		return this.materActiveDao.getPackageMAByBarCode(barCode, orgId, StockConstant.STOCK_NEW_ID, null);
	}
	
	public PackageMaterActive getChangeMAByOrnaCode(String ornaCode, String orgId, String stockId){
		return this.materActiveDao.getChangeMAByOrnaCode(ornaCode, orgId, stockId);
	}
	
	public PackageMaterActive getChangeMAByBarCode(String barCode, String orgId, String stockId) {
		return this.materActiveDao.getChangeMAByBarCode(barCode, orgId, stockId);
	}

	
	public PackageMaterActive getMaterNoactiveByBarCode(String barCode){
		return this.materActiveDao.getMaterNoactiveByBarCode(barCode);
	}

	public PackageMaterActive getMaterNoactiveByOrnaCode(String ornaCode){
		return this.materActiveDao.getMaterNoactiveByOrnaCode(ornaCode);
	}
	
	public Pager getMaterActivePagerData(Map<String, String> params) {
		return this.materActiveDao.getMaterActivePagerData(params);
	}
	
	
	/**
	 * 打标志
	 * @param ornaCode
	 */
	public void addMaterMaterial(String ornaCode){
		this.materActiveDao.addMaterMaterial(ornaCode);
	}
	
	/**
	 * 清标志
	 * @param ornaCode
	 */
	public void deleteMaterMaterial(String ornaCode) {
		this.materActiveDao.deleteMaterMaterial(ornaCode);
	}
	
	
	
	public String getStockCount(String orgId, String stockId) {
		return this.materActiveDao.getStockCount(orgId, stockId);
	}
	
	/**
	 * 供盘点使用的取现有量数据 通过 饰品编码
	 * @param ornaCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getMAByOrnaCOde(String ornaCode, String orgId, String stockId) {
		return this.materActiveDao.getMAByOrnaCOde(ornaCode, orgId, stockId);
	}
	/**
	 * 供盘点使用的取现有量数据
	 * @param barCode
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PackageMaterActive getMAByBarCode(String barCode, String orgId, String stockId) {
		
		return this.materActiveDao.getMAByBarCode(barCode, orgId, stockId);
	}
}
