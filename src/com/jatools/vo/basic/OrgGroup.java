package com.jatools.vo.basic;

public class OrgGroup {
	/**
	 * ID
	 */
	private String groupId;// integer not null, ID
	/**
	 * 组织
	 */
	private String orgId;// number not null,组织
	/**
	 * 大类
	 */
	private String itemClassId;// number,大类
	/**
	 * 小类
	 */
	private String ornaClassId;// number,小类
	/**
	 * 柜组
	 */
	private String groups;// number,柜组
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
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
