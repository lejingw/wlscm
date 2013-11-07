package com.jatools.vo.out;

import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UnitCache;

public class OutOrna {
	private String ornaCode;// 饰品编码
	private String ornaBarCode;// 饰品编码
	private String ornaDsc;// 饰品名称
	private String unitId;// 计量单位id
	private String styleId;// 款式id
	private String styleName;// 款式名称
	private String mainWeight;// 主石重
	private String partWeight;// 配石重
	private String styleItemClass;
	private String styleMiddleClass;
	private String styleOrnaClass;
	private String itemClassId;// 大类id
	private String itemClassName;// 大类id
	private String ornaClassId;// 小类id
	private String ornaClassName;// 小类id
	private String alaysisId;// 分析范围id
	private String alaysisCode;// 分析范围id
	private String status;// 状态
	private String colorId;
	private String cleanId;
	private Double nowQty;//现有量
	private Double allQty;//总重量
	private Double grains;//粒数
	private Double realTotalCost;//真实总成本

	private String unitName;
	private Integer isPSale;

	private Integer isMutiPart;//是否多粒
	private Integer isDblLabel;//是否双标签段
	private String orgId;
	private String posAmount;//网点金额
	private String vendorId;//供应商id
	
	private String stockId;
	
	private String bigGraph;
	
	public String getBigGraph() {
		return bigGraph;
	}

	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getOrnaCode() {
		return ornaCode;
	}

	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}

	public String getOrnaDsc() {
		return ornaDsc;
	}

	public void setOrnaDsc(String ornaDsc) {
		this.ornaDsc = ornaDsc;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getMainWeight() {
		return mainWeight;
	}

	public void setMainWeight(String mainWeight) {
		this.mainWeight = mainWeight;
	}

	public String getPartWeight() {
		return partWeight;
	}

	public void setPartWeight(String partWeight) {
		this.partWeight = partWeight;
	}

	public String getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}

	public String getOrnaClassId() {
		return ornaClassId;
	}

	public void setOrnaClassId(String ornaClassId) {
		this.ornaClassId = ornaClassId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getUnitName() {
		return UnitCache.getInstance().getUnitName(unitId);
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getPosAmount() {
		return posAmount;
	}

	public void setPosAmount(String posAmount) {
		this.posAmount = posAmount;
	}


	public String getOrnaBarCode() {
		return ornaBarCode;
	}

	public void setOrnaBarCode(String ornaBarCode) {
		this.ornaBarCode = ornaBarCode;
	}


	public String getItemClassName() {
		return ItemClassCache.getInstance().getItemClassDesc(itemClassId);
	}

	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}

	public String getOrnaClassName() {
		return OrnaClassCache.getInstance().getOrnaClass(ornaClassId);
	}

	public void setOrnaClassName(String ornaClassName) {
		this.ornaClassName = ornaClassName;
	}

	public String getAlaysisId() {
		return alaysisId;
	}

	public void setAlaysisId(String alaysisId) {
		this.alaysisId = alaysisId;
	}

	public Double getNowQty() {
		return nowQty;
	}

	public void setNowQty(Double nowQty) {
		this.nowQty = nowQty;
	}

	public Double getAllQty() {
		return allQty;
	}

	public void setAllQty(Double allQty) {
		this.allQty = allQty;
	}

	public Double getGrains() {
		return grains;
	}

	public void setGrains(Double grains) {
		this.grains = grains;
	}

	public Double getRealTotalCost() {
		return realTotalCost;
	}

	public void setRealTotalCost(Double realTotalCost) {
		this.realTotalCost = realTotalCost;
	}

	public Integer getIsPSale() {
		return isPSale;
	}

	public void setIsPSale(Integer isPSale) {
		this.isPSale = isPSale;
	}

	public Integer getIsMutiPart() {
		return isMutiPart;
	}

	public void setIsMutiPart(Integer isMutiPart) {
		this.isMutiPart = isMutiPart;
	}

	public Integer getIsDblLabel() {
		return isDblLabel;
	}

	public void setIsDblLabel(Integer isDblLabel) {
		this.isDblLabel = isDblLabel;
	}

	public String getAlaysisCode() {
		return alaysisCode;
	}

	public void setAlaysisCode(String alaysisCode) {
		this.alaysisCode = alaysisCode;
	}

	public String getStyleItemClass() {
		return styleItemClass;
	}

	public void setStyleItemClass(String styleItemClass) {
		this.styleItemClass = styleItemClass;
	}

	public String getStyleMiddleClass() {
		return styleMiddleClass;
	}

	public void setStyleMiddleClass(String styleMiddleClass) {
		this.styleMiddleClass = styleMiddleClass;
	}

	public String getStyleOrnaClass() {
		return styleOrnaClass;
	}

	public void setStyleOrnaClass(String styleOrnaClass) {
		this.styleOrnaClass = styleOrnaClass;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getCleanId() {
		return cleanId;
	}

	public void setCleanId(String cleanId) {
		this.cleanId = cleanId;
	}


}
