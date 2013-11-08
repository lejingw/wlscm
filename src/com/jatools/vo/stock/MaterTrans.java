package com.jatools.vo.stock;

public class MaterTrans {

	private String transId	;  //	n			事务id
	private String orgId	;  //	n			库存组织
	private String stockId	;  //	y			仓库
	private String locator	;  //(20)	y			货位
	private String itemClassId	;  //	y			大类id
	private String ornaClassId	;  //	y			小类id
	private String styleitemclass	;  //	y			款式大类id
	private String stylemiddleclass	;  //	y			款式中类id
	private String styleornaclass	;  //	y			款式小类id
	private String styleId	;  //	y			款式id
	private String alaysisId	;  //	y			分析范围id
	private String ornaCode	;  //(20)	n			商品编码
	private String ornaBarcode	;  //(20)	n			商品条码
	private String ornaDsc	;  //(50)	y			商品名称
	private String unitId	;  //	n			计量单位
	private String nowQty	;  //	y			现有量
	private String allQty	;  //	y			总重量
	private String grains	;  //	y	0		粒数
	private String posCost	;  //	y			暂估成本
	private String posMoney	;  //	y			销售金额
	private String transFlag	;  //	y			事务标志 1入库  -1出库
	private String transSourceBill	;  //(50)	y			
	private String transSourceNo	;  //(50)	y			事务来源单号
	private String transBody	;  //(200)	y			事务处理说明
	private String transSourceType	;  //	y			0采购入库1委外入库
	private String transFinance	;  //(50)	y			核算类型
	private String transDate	;  //(20)	y			事务时间
	private String transCost	;  //	y			事务成本
	private String transMoney	;  //	y			事务金额
	private String transQty	;  //	y			事务数量
	private String isPsale	;  //	y			是否代销
	private String moveOrgId	;  //	y			调拔对应组织
	private String ornaCodeSource	;  //(20)	y			饰品原编码
	private String createDate	;  //(20)	y			创建时间
	private String createId	;  //	y			创建人员
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
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
	public String getTransFlag() {
		return transFlag;
	}
	public void setTransFlag(String transFlag) {
		this.transFlag = transFlag;
	}
	public String getTransSourceBill() {
		return transSourceBill;
	}
	public void setTransSourceBill(String transSourceBill) {
		this.transSourceBill = transSourceBill;
	}
	public String getTransSourceNo() {
		return transSourceNo;
	}
	public void setTransSourceNo(String transSourceNo) {
		this.transSourceNo = transSourceNo;
	}
	public String getTransBody() {
		return transBody;
	}
	public void setTransBody(String transBody) {
		this.transBody = transBody;
	}
	public String getTransSourceType() {
		return transSourceType;
	}
	public void setTransSourceType(String transSourceType) {
		this.transSourceType = transSourceType;
	}
	public String getTransFinance() {
		return transFinance;
	}
	public void setTransFinance(String transFinance) {
		this.transFinance = transFinance;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransCost() {
		return transCost;
	}
	public void setTransCost(String transCost) {
		this.transCost = transCost;
	}
	public String getTransMoney() {
		return transMoney;
	}
	public void setTransMoney(String transMoney) {
		this.transMoney = transMoney;
	}
	public String getTransQty() {
		return transQty;
	}
	public void setTransQty(String transQty) {
		this.transQty = transQty;
	}
	public String getIsPsale() {
		return isPsale;
	}
	public void setIsPsale(String isPsale) {
		this.isPsale = isPsale;
	}
	public String getMoveOrgId() {
		return moveOrgId;
	}
	public void setMoveOrgId(String moveOrgId) {
		this.moveOrgId = moveOrgId;
	}
	public String getOrnaCodeSource() {
		return ornaCodeSource;
	}
	public void setOrnaCodeSource(String ornaCodeSource) {
		this.ornaCodeSource = ornaCodeSource;
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
	
	
}
