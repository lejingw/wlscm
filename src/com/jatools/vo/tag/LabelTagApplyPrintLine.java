package com.jatools.vo.tag;

public class LabelTagApplyPrintLine {

	private String id	;//	lineId
	private String headId	;//	标签打印单id
	private String ornaCode	;//	饰品编码
	private String ornaName	;//	饰品名称
	private String unitId	;//	计量单位
	private String num	;//	数量
	private String weight	;//	重量
	private String oldAmount	;//	原金额
	private String newAmount	;//	新金额
	private String oidSize	;//	原尺寸
	private String newSize	;//	新尺寸
	private String oldOrnaName	;//	原饰品名称
	private String newOrnaName	;//	新饰品名称
	private String oldNativeCert	;//	原国鉴证书
	private String newNativeCert	;//	新国鉴证书
	private String oldGiaCert	;//	原gia证书
	private String newGiaCert	;//	新gia证书
	private String oldHrdCert	;//	原hrd证书
	private String newHrdCert	;//	新hrd证书
	private String oldIgiCert	;//	原igi证书
	private String newIgiCert	;//	新igi证书
	private String oldZodiac	;//	原生肖
	private String newZodiac	;//	现生肖
	private String updateReason	;//	更新原因
	private String state	;//	行状态
	private String updatedate	;//	updatedate
	private String isDblLabel	;//	是否双标签段
	private String labelKind	;//	标签种类 111 每一位对应一种标签  (普通标签，物流标签，价格标签)
	private String oldBasicPrice	;//	原基础价
	private String newBasicPrice	;//	新基础价
	private String itemClassId	;//	大类
	private String ornaClassId	;//	小类
	private String oldSpecialWorkPrice	;//	原特殊工费
	private String newSpecialWorkPrice	;//	新特殊工费
	
	private String oldSizeName;
	private String newSizeName;
	private String receDate;
	
	public String getReceDate() {
		return receDate;
	}
	public void setReceDate(String receDate) {
		this.receDate = receDate;
	}
	public String getNewSizeName() {
		return newSizeName;
	}
	public void setNewSizeName(String newSizeName) {
		this.newSizeName = newSizeName;
	}
	public String getOldSizeName() {
		return oldSizeName;
	}
	public void setOldSizeName(String oldSizeName) {
		this.oldSizeName = oldSizeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getOrnaName() {
		return ornaName;
	}
	public void setOrnaName(String ornaName) {
		this.ornaName = ornaName;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getOldAmount() {
		return oldAmount;
	}
	public void setOldAmount(String oldAmount) {
		this.oldAmount = oldAmount;
	}
	public String getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(String newAmount) {
		this.newAmount = newAmount;
	}
	public String getOidSize() {
		return oidSize;
	}
	public void setOidSize(String oidSize) {
		this.oidSize = oidSize;
	}
	public String getNewSize() {
		return newSize;
	}
	public void setNewSize(String newSize) {
		this.newSize = newSize;
	}
	public String getOldOrnaName() {
		return oldOrnaName;
	}
	public void setOldOrnaName(String oldOrnaName) {
		this.oldOrnaName = oldOrnaName;
	}
	public String getNewOrnaName() {
		return newOrnaName;
	}
	public void setNewOrnaName(String newOrnaName) {
		this.newOrnaName = newOrnaName;
	}
	public String getOldNativeCert() {
		return oldNativeCert;
	}
	public void setOldNativeCert(String oldNativeCert) {
		this.oldNativeCert = oldNativeCert;
	}
	public String getNewNativeCert() {
		return newNativeCert;
	}
	public void setNewNativeCert(String newNativeCert) {
		this.newNativeCert = newNativeCert;
	}
	public String getOldGiaCert() {
		return oldGiaCert;
	}
	public void setOldGiaCert(String oldGiaCert) {
		this.oldGiaCert = oldGiaCert;
	}
	public String getNewGiaCert() {
		return newGiaCert;
	}
	public void setNewGiaCert(String newGiaCert) {
		this.newGiaCert = newGiaCert;
	}
	public String getOldHrdCert() {
		return oldHrdCert;
	}
	public void setOldHrdCert(String oldHrdCert) {
		this.oldHrdCert = oldHrdCert;
	}
	public String getNewHrdCert() {
		return newHrdCert;
	}
	public void setNewHrdCert(String newHrdCert) {
		this.newHrdCert = newHrdCert;
	}
	public String getOldIgiCert() {
		return oldIgiCert;
	}
	public void setOldIgiCert(String oldIgiCert) {
		this.oldIgiCert = oldIgiCert;
	}
	public String getNewIgiCert() {
		return newIgiCert;
	}
	public void setNewIgiCert(String newIgiCert) {
		this.newIgiCert = newIgiCert;
	}
	public String getOldZodiac() {
		return oldZodiac;
	}
	public void setOldZodiac(String oldZodiac) {
		this.oldZodiac = oldZodiac;
	}
	public String getNewZodiac() {
		return newZodiac;
	}
	public void setNewZodiac(String newZodiac) {
		this.newZodiac = newZodiac;
	}
	public String getUpdateReason() {
		return updateReason;
	}
	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getIsDblLabel() {
		return isDblLabel;
	}
	public void setIsDblLabel(String isDblLabel) {
		this.isDblLabel = isDblLabel;
	}
	public String getLabelKind() {
		return labelKind;
	}
	public void setLabelKind(String labelKind) {
		this.labelKind = labelKind;
	}
	public String getOldBasicPrice() {
		return oldBasicPrice;
	}
	public void setOldBasicPrice(String oldBasicPrice) {
		this.oldBasicPrice = oldBasicPrice;
	}
	public String getNewBasicPrice() {
		return newBasicPrice;
	}
	public void setNewBasicPrice(String newBasicPrice) {
		this.newBasicPrice = newBasicPrice;
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
	public String getOldSpecialWorkPrice() {
		return oldSpecialWorkPrice;
	}
	public void setOldSpecialWorkPrice(String oldSpecialWorkPrice) {
		this.oldSpecialWorkPrice = oldSpecialWorkPrice;
	}
	public String getNewSpecialWorkPrice() {
		return newSpecialWorkPrice;
	}
	public void setNewSpecialWorkPrice(String newSpecialWorkPrice) {
		this.newSpecialWorkPrice = newSpecialWorkPrice;
	}
	private String itemClassName;
	private String ornaClassName;
	private String unitName;
	private String statusName;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
