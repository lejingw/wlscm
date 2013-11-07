package com.jatools.vo.basic;

public class MaterialItem {

	private String materialItemId	;//	
	private String materialType	;//	发料类型 来源于数据字典
	private String itemClassId	;//	大类id
	private String purUnit	;//	结核单位
	/**
	 * 状态
	 */
	private String status;// number,状态
	/**
	 * 备注
	 */
	private String memo;// varchar2(1000),备注
	/**
	 * 创建时间
	 */
	private String createDate;// varchar2(20),创建时间
	/**
	 * 创建人
	 */
	private String createId;// number,创建人
	/**
	 * 最后修改时间
	 */
	private String updateDate;// varchar2(20),最后修改时间
	/**
	 * 最后修改时间
	 */
	private String updateId;// number 最后修改时间
	
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
	public String getMaterialItemId() {
		return materialItemId;
	}
	public void setMaterialItemId(String materialItemId) {
		this.materialItemId = materialItemId;
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
	public String getPurUnit() {
		return purUnit;
	}
	public void setPurUnit(String purUnit) {
		this.purUnit = purUnit;
	}

}
