package com.jatools.vo.basic;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.util.DictUtil;
import com.jatools.web.util.StringUtil;

public class PriceLockLine {
	private String lineid;
	private String headid;
	private String ornaCode;
	private String barCode;
	private String ornaDsc;
	private String itemClassId;
	private String ornaClassId;
	private String styleId;
	private String basicPrice;
	private String posMoney;
	private String allWeight;
	private String mainWeight;
	private String partWeight;
	private String colorGradeId;
	private String cleanId;
	private String status;
	
	private String styleName;
	private String bigGraph;
	private String itemClassDsc;
	private String ornaClassDsc;
	private String colorGradeName;
	private String cleanName;
	
	private String orgId;
	private String lockFlag;
	
	private String unitId;
	
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getItemClassDsc() {
		return itemClassDsc;
	}

	public void setItemClassDsc(String itemClassDsc) {
		this.itemClassDsc = itemClassDsc;
	}

	public String getOrnaClassDsc() {
		return ornaClassDsc;
	}

	public void setOrnaClassDsc(String ornaClassDsc) {
		this.ornaClassDsc = ornaClassDsc;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLineid() {
		return lineid;
	}

	public void setLineid(String lineid) {
		this.lineid = lineid;
	}

	public String getHeadid() {
		return headid;
	}

	public void setHeadid(String headid) {
		this.headid = headid;
	}

	public String getOrnaCode() {
		return ornaCode;
	}

	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getOrnaDsc() {
		return ornaDsc;
	}

	public void setOrnaDsc(String ornaDsc) {
		this.ornaDsc = ornaDsc;
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

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(String basicPrice) {
		this.basicPrice = basicPrice;
	}

	public String getPosMoney() {
		return posMoney;
	}

	public void setPosMoney(String posMoney) {
		this.posMoney = posMoney;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getAllWeight() {
		return allWeight;
	}

	public void setAllWeight(String allWeight) {
		this.allWeight = allWeight;
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

	public String getColorGradeId() {
		return colorGradeId;
	}

	public void setColorGradeId(String colorGradeId) {
		this.colorGradeId = colorGradeId;
	}

	public String getCleanId() {
		return cleanId;
	}

	public void setCleanId(String cleanId) {
		this.cleanId = cleanId;
	}

	public String getColorGradeName() {
		return colorGradeName;
	}

	public void setColorGradeName(String colorGradeName) {
		this.colorGradeName = colorGradeName;
	}

	public String getCleanName() {
		return cleanName;
	}

	public void setCleanName(String cleanName) {
		this.cleanName = cleanName;
	}

	public String getBigGraph() {
		return bigGraph;
	}

	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}

	public void setNames() {
		this.itemClassDsc = ItemClassCache.getInstance().getItemClassDesc(itemClassId);
		this.ornaClassDsc = OrnaClassCache.getInstance().getOrnaClass(ornaClassId);
		this.colorGradeName = DictUtil.getValue(DictConstant.DIA_COLORGRADE, this.colorGradeId);
		this.cleanName = DictUtil.getValue(DictConstant.DIA_CLEAN, this.cleanId);
		this.allWeight = StringUtil.formatDouble(allWeight, 3);
		this.mainWeight = StringUtil.formatDouble(mainWeight, 3);
		this.partWeight = StringUtil.formatDouble(partWeight, 3);
	}

}
