package com.jatools.vo.stock;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.DictCache;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.cache.UnitCache;

public class PlSaleLine {

	private String lineid          ;
	private String billid          ;
	private String itemClassId     ;
	private String ornaClassId     ;
	private String styleitemclass  ;
	private String stylemiddleclass;
	private String styleornaclass  ;
	private String styleId         ;
	private String alaysisId       ;
	private String ornaCode        ;
	private String ornaBarcode     ;
	private String ornaDsc         ;
	private String unitId          ;
	private Double nowQty          ;
	private Double allQty          ;
	private String grains          ;
	private Double mainWeight      ;
	private Double partWeight      ;
	private String isMutiPart      ;
	private String isDblLabel      ;
	private Double posCost         ;
	private Double posMoney        ;
	private String status          ;
	private String memo            ;
	private String createDate      ;
	private String createId        ;
	private String updateDate      ;
	private String updateId        ;
	
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
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
	public String getStyleitemclass() {
		return styleitemclass;
	}
	public void setStyleitemclass(String styleitemclass) {
		this.styleitemclass = styleitemclass;
	}
	public String getStylemiddleclass() {
		return stylemiddleclass;
	}
	public void setStylemiddleclass(String stylemiddleclass) {
		this.stylemiddleclass = stylemiddleclass;
	}
	public String getStyleornaclass() {
		return styleornaclass;
	}
	public void setStyleornaclass(String styleornaclass) {
		this.styleornaclass = styleornaclass;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getAlaysisId() {
		return alaysisId;
	}
	public void setAlaysisId(String alaysisId) {
		this.alaysisId = alaysisId;
	}
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getOrnaBarcode() {
		return ornaBarcode;
	}
	public void setOrnaBarcode(String ornaBarcode) {
		this.ornaBarcode = ornaBarcode;
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
	public String getGrains() {
		return grains;
	}
	public void setGrains(String grains) {
		this.grains = grains;
	}
	public Double getMainWeight() {
		return mainWeight;
	}
	public void setMainWeight(Double mainWeight) {
		this.mainWeight = mainWeight;
	}
	public Double getPartWeight() {
		return partWeight;
	}
	public void setPartWeight(Double partWeight) {
		this.partWeight = partWeight;
	}
	public String getIsMutiPart() {
		return isMutiPart;
	}
	public void setIsMutiPart(String isMutiPart) {
		this.isMutiPart = isMutiPart;
	}
	public String getIsDblLabel() {
		return isDblLabel;
	}
	public void setIsDblLabel(String isDblLabel) {
		this.isDblLabel = isDblLabel;
	}
	public Double getPosCost() {
		return posCost;
	}
	public void setPosCost(Double posCost) {
		this.posCost = posCost;
	}
	public Double getPosMoney() {
		return posMoney;
	}
	public void setPosMoney(Double posMoney) {
		this.posMoney = posMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	public String getItemClassName(){
		return ItemClassCache.getInstance().getItemClassDesc(itemClassId);
	}
	public String getOrnaClassName(){
		return OrnaClassCache.getInstance().getOrnaClass(ornaClassId);
	}
	public String getUnitName(){
		return UnitCache.getInstance().getNameById(unitId);
	}
	public String getIsMutiPartName(){
		return DictCache.getInstance().getValue(DictConstant.YES_OR_NO, this.isMutiPart);
	}
	public String getIsDblLabelName(){
		return DictCache.getInstance().getValue(DictConstant.YES_OR_NO, this.isDblLabel);
	}
	
	private String styleName;
	private String bigGraph;
	private String alaysisName;
	
	public String getAlaysisName() {
		return alaysisName;
	}
	public void setAlaysisName(String alaysisName) {
		this.alaysisName = alaysisName;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getBigGraph() {
		return bigGraph;
	}
	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}
	
	public String getStyleitemclassName() {
		return StyleItemClassCache.getInstance().getNameById(styleitemclass);
	}
	public String getStylemiddleclassName() {
		return StyleMiddleClassCache.getInstance().getNameById(stylemiddleclass);
	}
	public String getStyleornaclassName() {
		return StyleOrnaClassCache.getInstance().getNameById(styleornaclass);
	}

    private String orgId;
    private String outFlag;

    public String getOutFlag() {
        return outFlag;
    }

    public void setOutFlag(String outFlag) {
        this.outFlag = outFlag;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
