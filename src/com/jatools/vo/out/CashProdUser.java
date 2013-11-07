package com.jatools.vo.out;

public class CashProdUser {
	private String Id;//ID	INTEGER	N			流水号
	private String prId;//PR_ID	INTEGER	Y			台帐ID
	private String ornaNo;//ORNA_NO	VARCHAR2(20)	Y			饰品编码
	private String userNums;//USER_NUMS	INTEGER	Y			使用量
	private String sourceType;//SOURCE_TYPE	VARCHAR2(20)	Y			来源类型
	private String sourceId;//SOURCE_ID	INTEGER	Y			来源ID
	private String state;//STATE	INTEGER	Y			状态
	private String createDate;//CREATE_DATE	VARCHAR2(20)	Y			创建时间
	private String createId;//CREATE_ID	INTEGER	Y			创建人员
	private String updateDate;//UPDATE_DATE	VARCHAR2(20)	Y			最后修改时间
	private String updateId;//UPDATE_ID	INTEGER	Y			最后修改人员
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getOrnaNo() {
		return ornaNo;
	}
	public void setOrnaNo(String ornaNo) {
		this.ornaNo = ornaNo;
	}
	public String getUserNums() {
		return userNums;
	}
	public void setUserNums(String userNums) {
		this.userNums = userNums;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
