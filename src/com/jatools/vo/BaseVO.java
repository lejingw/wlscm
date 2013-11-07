package com.jatools.vo;

import com.jatools.web.cache.UserCache;

public class BaseVO {
	private String createId;
	private String createDate;
	private String updateId;
	private String updateDate;
	private String status;

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateName() {
		return UserCache.getInstance().getNameById(this.createId);
	}

	public String getUpdateName() {
		return UserCache.getInstance().getNameById(this.updateId);
	}
}
