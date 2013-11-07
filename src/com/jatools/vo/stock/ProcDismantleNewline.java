package com.jatools.vo.stock;

/**
 * 拆石 新行表
 * @author ren.ming
 * Created 2011-11-28
 */
public class ProcDismantleNewline {

	private String lineid	; //	n			lineid
	private String billid	; //	n			单据id
	private String materialType	; //(20)	y			原料类型
	private String itemClassId	; //	y			大类id
	private String ornaClassId	; //	y			小类id
	private String styleitemclass	; //	y			款式大类id
	private String stylemiddleclass	; //	y			款式中类id
	private String styleornaclass	; //	y			款式小类id
	private String styleId	; //	y			款式id
	private String alaysisId	; //	y			分析范围id
	private String shapeId	; //	y			形状
	private String colorId	; //	y			颜色
	private String cleanId	; //	y			净度
	private String colorGradeId	; //	y			色级
	private String identCert	; //(20)	y			鉴定证书号
	private String hrdCert	; //(20)	y			hrd国际证书
	private String giaCert	; //(20)	y			gia国际证书
	private String igiCert	; //(20)	y			igi国际证书
	private String agsCert	; //(20)	y			ags国际证书号
	private String mainWeight	; //	y			主石重量
	private String partWeight	; //	y			配石重量
	private String isMutiPart	; //	y			是否多粒
	private String isDblLabel	; //	y			是否双标签段
	private String ornaCode	; //(20)	n			商品编码
	private String ornaBarcode	; //(20)	n			商品条码
	private String ornaDsc	; //(50)	y			商品名称
	private String unitId	; //	n			计量单位
	private String nowQty	; //	y			现有量
	private String allQty	; //	y			总重量
	private String grains	; //	y	0		粒数
	private String posCost	; //	y			暂估成本
	private String posMoney	; //	y			销售金额
	private String isPsale	; //	y			是否代销
	private String status	; //	y			状态
	private String memo	; //(1000)	y			备注
	private String createDate	; //(20)	y			创建时间
	private String createId	; //	y			创建人员
	private String updateDate	; //(20)	y			最后修改时间
	private String updateId	; //	y			最后修改人员
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
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
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
	public String getShapeId() {
		return shapeId;
	}
	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
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
	public String getColorGradeId() {
		return colorGradeId;
	}
	public void setColorGradeId(String colorGradeId) {
		this.colorGradeId = colorGradeId;
	}
	public String getIdentCert() {
		return identCert;
	}
	public void setIdentCert(String identCert) {
		this.identCert = identCert;
	}
	public String getHrdCert() {
		return hrdCert;
	}
	public void setHrdCert(String hrdCert) {
		this.hrdCert = hrdCert;
	}
	public String getGiaCert() {
		return giaCert;
	}
	public void setGiaCert(String giaCert) {
		this.giaCert = giaCert;
	}
	public String getIgiCert() {
		return igiCert;
	}
	public void setIgiCert(String igiCert) {
		this.igiCert = igiCert;
	}
	public String getAgsCert() {
		return agsCert;
	}
	public void setAgsCert(String agsCert) {
		this.agsCert = agsCert;
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
	
}
