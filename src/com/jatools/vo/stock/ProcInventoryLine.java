package com.jatools.vo.stock;

/**
 * 盘点单行表
 * @author ren.ming
 * Created 2011-12-5
 */
public class ProcInventoryLine {

	private String lineid	;//	n			lineid
	private String billid	;//	n			单据id
	private String oldBillid	;//	y			
	private String rowId	;//	y			
	private String itemClassId	;//	y			大类id
	private String ornaClassId	;//	y			小类id
	private String styleitemclass	;//	y			款式大类id
	private String stylemiddleclass	;//	y			款式中类id
	private String styleornaclass	;//	y			款式小类id
	private String styleId	;//	y			款式id
	private String alaysisId	;//	y			分析范围id
	private String ornaCode	;//(20)	n			商品编码
	private String ornaBarcode	;//(20)	n			商品条码
	private String ornaDsc	;//(50)	y			商品名称
	private String unitId	;//	n			计量单位
	private String nowQty	;//	y			现有量
	private String allQty	;//	y			总重量
	private String grains	;//	y	0		粒数
	private String mainWeight	;//	y			主石重量
	private String partWeight	;//	y			配石重量
	private String isMutiPart	;//	y			是否多粒
	private String isDblLabel	;//	y			是否双标签段
	private String posCost	;//	y			暂估成本
	private String posMoney	;//	y			销售金额
	private String isPsale	;//	y			是否代销
	private String groups	;//(4)	y			柜组
	private String iscode	;//	y			0否1是
	private String status	;//	y			状态
	private String memo	;//(1000)	y			备注
	private String createDate	;//(20)	y			创建时间
	private String createId	;//	y			创建人员
	private String updateDate	;//(20)	y			最后修改时间
	private String updateId	;//	y			最后修改人员
	
	private String unitName;
	private String alaysisName;
	private String styleName;
	private String ornaClassName;
	private String itemClassName;
	
	private String orgId;
	private String bigGraph;
	
	public String getBigGraph() {
		return bigGraph;
	}
	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
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
	public String getOrnaClassName() {
		return ornaClassName;
	}
	public void setOrnaClassName(String ornaClassName) {
		this.ornaClassName = ornaClassName;
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
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
	public String getOldBillid() {
		return oldBillid;
	}
	public void setOldBillid(String oldBillid) {
		this.oldBillid = oldBillid;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
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
	public String getNowQty() {
		return nowQty;
	}
	public void setNowQty(String nowQty) {
		this.nowQty = nowQty;
	}
	public String getAllQty() {
		return allQty;
	}
	public void setAllQty(String allQty) {
		this.allQty = allQty;
	}
	public String getGrains() {
		return grains;
	}
	public void setGrains(String grains) {
		this.grains = grains;
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
	public String getPosCost() {
		return posCost;
	}
	public void setPosCost(String posCost) {
		this.posCost = posCost;
	}
	public String getPosMoney() {
		return posMoney;
	}
	public void setPosMoney(String posMoney) {
		this.posMoney = posMoney;
	}
	public String getIsPsale() {
		return isPsale;
	}
	public void setIsPsale(String isPsale) {
		this.isPsale = isPsale;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getIscode() {
		return iscode;
	}
	public void setIscode(String iscode) {
		this.iscode = iscode;
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
	
}
