package com.jatools.vo.stock;

import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UserCache;

public class DispatchCondition {
	private String id;
	private String gheadid;
	private String itemClassId;
	private String ornaClassId;
	private String createId;
	private String createDate;
	private String updateId;
	private String updateDate;
	private String status;
	private String srcBillCode;
	

	public String getSrcBillCode() {
		return srcBillCode;
	}

	public void setSrcBillCode(String srcBillCode) {
		this.srcBillCode = srcBillCode;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGheadid() {
		return gheadid;
	}

	public void setGheadid(String gheadid) {
		this.gheadid = gheadid;
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
	public String getItemClassName(){
		return ItemClassCache.getInstance().getNameById(itemClassId);
	}
	public String getOrnaClassName(){
		return OrnaClassCache.getInstance().getNameById(ornaClassId);
	}
	public String getCreateName(){
		return UserCache.getInstance().getNameById(createId);
	}
}
