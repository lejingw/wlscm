package com.jatools.vo.basic;

import com.jatools.common.Global;
import com.jatools.common.intf.ReviewBill;

public class LabelType implements ReviewBill {
	private String labelTypeId;// LABEL_TYPE_ID NUMBER(8) N
	private String labelTypeName;// LABEL_TYPE_NAME VARCHAR2(12) N 标签类型
	private String itemClassId;// ITEM_CLASS_ID NUMBER(8) N 大类
	private String ornaClassId;
	private String unitId;// UNIT_ID NUMBER(8) N 计量单位
	private String bracketFlag;// BRACKET_FLAG CHAR(1) N '1' 是否镶嵌
	private String dblLabelFlag;// DBL_LABEL_FLAG CHAR(1) N 是否双标签段
	private String memo;// MEMO VARCHAR2(1000) Y 备注
	private String createDate;// CREATE_DATE VARCHAR2(20) Y 创建时间
	private String createId;// CREATE_ID NUMBER(8) Y 创建人
	private String updateDate;// UPDATE_DATE VARCHAR2(20) Y 更新人
	private String updateId;// UPDATE_ID NUMBER(8) Y 更新时间
	private String archiveflag;
	private String status;// STATUS NUMBER Y 是否封存
	private String labelTypeNameOld;// LABEL_TYPE_NAME VARCHAR2(12) N 标签类型
	private String dblLabelFlagOld;// DBL_LABEL_FLAG CHAR(1) N 是否双标签段
	private String privateType;//PRIVATE_TYPE	CHAR(1)	Y			是否高工艺0否 1是 

	public String getPrivateType() {
		return privateType;
	}

	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}

	public String getLabelTypeId() {
		return labelTypeId;
	}

	public void setLabelTypeId(String labelTypeId) {
		this.labelTypeId = labelTypeId;
	}

	public String getLabelTypeName() {
		return labelTypeName;
	}

	public void setLabelTypeName(String labelTypeName) {
		this.labelTypeName = labelTypeName;
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

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getBracketFlag() {
		return bracketFlag;
	}

	public void setBracketFlag(String bracketFlag) {
		this.bracketFlag = bracketFlag;
	}

	public String getDblLabelFlag() {
		return dblLabelFlag;
	}

	public void setDblLabelFlag(String dblLabelFlag) {
		this.dblLabelFlag = dblLabelFlag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getArchiveflag() {
		return archiveflag;
	}

	public void setArchiveflag(String archiveflag) {
		this.archiveflag = archiveflag;
	}

	public String getLabelTypeNameOld() {
		return labelTypeNameOld;
	}

	public void setLabelTypeNameOld(String labelTypeNameOld) {
		this.labelTypeNameOld = labelTypeNameOld;
	}

	public String getDblLabelFlagOld() {
		return dblLabelFlagOld;
	}

	public void setDblLabelFlagOld(String dblLabelFlagOld) {
		this.dblLabelFlagOld = dblLabelFlagOld;
	}

	@Override
	public String getBillCode() {
		return "LU";
	}

	@Override
	public String getBillid() {
		return this.labelTypeId;
	}

	@Override
	public String getBillno() {
		return this.itemClassId+this.ornaClassId+this.unitId+this.bracketFlag;
	}

	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/basic/labelType.vm?user_action=toEditLabelType&id=" + labelTypeId;
	}

	@Override
	public String getBeanName() {
		return "labelTypeDao";
	}

	@Override
	public String getOrgId() {
		return orgId;
	}
	private String orgId;

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
