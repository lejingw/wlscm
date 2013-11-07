package com.jatools.vo.stock;

/**
 * 拆包单原行表
 * @author ren.ming
 * Created 2011-11-20
 */
public class ProcPackageOldline {
	/**
	 * lineid
	 */
	private String lineid;//	number	n			lineid
	/**
	 * 单据id
	 */
	private String billid;//	number	n			单据id
	
	/**
	 * 大类id
	 */
	private String itemClassId;//	number	y			大类id
	private String itemClassName;
	/**
	 * 小类id
	 */
	private String ornaClassId;//	number	y			小类id
	private String ornaClassName;
	/**
	 * 款式大类id
	 */
	private String styleitemclass;//	number	y			款式大类id
	/**
	 * 款式中类id
	 */
	private String stylemiddleclass;//	number	y			款式中类id
	/**
	 * 款式小类id
	 */
	private String styleornaclass;//	number	y			款式小类id
	/**
	 * 款式id
	 */
	private String styleId;//	number	y			款式id
	private String styleName;
	/**
	 * 分析范围id
	 */
	private String alaysisId;//	number	y			分析范围id
	private String alaysisName;
	/**
	 * 商品编码
	 */
	private String ornaCode;//	varchar2(20)	n			商品编码
	/**
	 * 商品条码
	 */
	private String ornaBarcode;//	varchar2(20)	n			商品条码
	/**
	 * 商品名称
	 */
	private String ornaDsc;//	varchar2(50)	y			商品名称
	/**
	 * 计量单位
	 */
	private String unitId;//	number	n			计量单位
	private String unitName;
	/**
	 * 现有量
	 */
	private String nowQty;//	number	y			现有量
	/**
	 * 总重量
	 */
	private String allQty;//	number	y			总重量
	/**
	 * 粒数
	 */
	private String grains;//	number	y	0		粒数
	/**
	 * 主石重量
	 */
	private String mainWeight;//	number	y			主石重量
	/**
	 * 配石重量
	 */
	private String partWeight;//	number	y			配石重量
	/**
	 * 是否多粒
	 */
	private String isMutiPart;//	number	y			是否多粒
	/**
	 * 是否双标签段
	 */
	private String isDblLabel;//	number	y			是否双标签段
	/**
	 * 暂估成本
	 */
	private String posCost;//	number	y			暂估成本
	/**
	 * 销售金额
	 */
	private String posMoney;//	number	y			销售金额
	/**
	 * 是否代销
	 */
	private String isPsale;//	number	y			是否代销
	/**
	 * 状态
	 */
	private String status;//	number	y			状态
	/**
	 * 备注
	 */
	private String memo;//	varchar2(1000)	y			备注
	/**
	 * 创建时间
	 */
	private String createDate;//	varchar2(20)	y			创建时间
	/**
	 * 创建人员
	 */
	private String createId;//	number	y			创建人员
	/**
	 * 最后修改时间
	 */
	private String updateDate;//	varchar2(20)	y			最后修改时间
	/**
	 * 最后修改人员
	 */
	private String updateId;//	number	y			最后修改人员
	
	private String inivCost;
	
	private String newVal;
	private String colorId;
	private String colorName;
	private String mainShapeId;
	private String mainShapeName;
	
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getMainShapeName() {
		return mainShapeName;
	}
	public void setMainShapeName(String mainShapeName) {
		this.mainShapeName = mainShapeName;
	}
	public String getNewVal() {
		return newVal;
	}
	public void setNewVal(String newVal) {
		this.newVal = newVal;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getMainShapeId() {
		return mainShapeId;
	}
	public void setMainShapeId(String mainShapeId) {
		this.mainShapeId = mainShapeId;
	}
	public String getInivCost() {
		return inivCost;
	}
	public void setInivCost(String inivCost) {
		this.inivCost = inivCost;
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
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
	public String getOrnaClassName() {
		return ornaClassName;
	}
	public void setOrnaClassName(String ornaClassName) {
		this.ornaClassName = ornaClassName;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getAlaysisName() {
		return alaysisName;
	}
	public void setAlaysisName(String alaysisName) {
		this.alaysisName = alaysisName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	private String bigGraph;

	public String getBigGraph() {
		return bigGraph;
	}
	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}
	
}
