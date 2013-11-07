package com.jatools.vo.stock;

/**
 * 退料行表 对象
 * @author ren.ming
 * Created 2011-11-28
 */
public class ProcExitLine {

	private String lineid    ;//nlineid
	private String billid    ;//n单据id
	private String billno    ;//(20)n发料单编号
	private String billdate    ;//(20)y发料单日期
	private String ornaNo    ;//(20)y商品编码
	private String materialType    ;//(50)y发料类型 来源于数据字典
	private String materialTypeName;
	private String itemClassId    ;//y大类id
	private String itemClassName;
	private String alaysisId    ;//y分析范围id
	private String alaysisName;
	private String unitId    ;//n计量单位
	private String unitName;
	private String mainColorGradeId    ;//y色级
	private String mainColorGradeName;
	private String cleanId    ;//y净度
	private String cleanName;
	private String cashUnit    ;//(20)y结算单位
	private String cashUnitName;
	private String billNums    ;//y单据数量
	private String oldNums    ;//y可退料数量
	private String exitNums    ;//y0退料数量
	private String isDiff    ;//(20)n商品条码
	private String status    ;//y状态
	private String memo    ;//(1000)y备注
	private String createDate    ;//(20)y创建时间
	private String createId    ;//y创建人员
	private String updateDate    ;//(20)y最后修改时间
	private String updateId    ;//y最后修改人员
	private String costPrice;
	
	private String oldExitNums    ;
	
	public String getOldExitNums() {
		return oldExitNums;
	}
	public void setOldExitNums(String oldExitNums) {
		this.oldExitNums = oldExitNums;
	}
	public String getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
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
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getIsDiff() {
		return isDiff;
	}
	public void setIsDiff(String isDiff) {
		this.isDiff = isDiff;
	}
	public String getCashUnit() {
		return cashUnit;
	}
	public void setCashUnit(String cashUnit) {
		this.cashUnit = cashUnit;
	}
	public String getOldNums() {
		return oldNums;
	}
	public void setOldNums(String oldNums) {
		this.oldNums = oldNums;
	}
	public String getExitNums() {
		return exitNums;
	}
	public void setExitNums(String exitNums) {
		this.exitNums = exitNums;
	}
	public String getOrnaNo() {
		return ornaNo;
	}
	public void setOrnaNo(String ornaNo) {
		this.ornaNo = ornaNo;
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
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
	public String getAlaysisId() {
		return alaysisId;
	}
	public void setAlaysisId(String alaysisId) {
		this.alaysisId = alaysisId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getMainColorGradeId() {
		return mainColorGradeId;
	}
	public void setMainColorGradeId(String mainColorGradeId) {
		this.mainColorGradeId = mainColorGradeId;
	}
	public String getCleanId() {
		return cleanId;
	}
	public void setCleanId(String cleanId) {
		this.cleanId = cleanId;
	}
	public String getBillNums() {
		return billNums;
	}
	public void setBillNums(String billNums) {
		this.billNums = billNums;
	}
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
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
	public String getMainColorGradeName() {
		return mainColorGradeName;
	}
	public void setMainColorGradeName(String mainColorGradeName) {
		this.mainColorGradeName = mainColorGradeName;
	}
	public String getCleanName() {
		return cleanName;
	}
	public void setCleanName(String cleanName) {
		this.cleanName = cleanName;
	}
	public String getCashUnitName() {
		return cashUnitName;
	}
	public void setCashUnitName(String cashUnitName) {
		this.cashUnitName = cashUnitName;
	}
	
}
