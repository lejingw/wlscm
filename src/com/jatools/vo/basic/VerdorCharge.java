package com.jatools.vo.basic;

import com.jatools.web.util.BdCommon;

public class VerdorCharge {
	private String chargeId;//CHARGE_ID	INTEGER	N			ID
	private String verdorId;//VERDOR_ID	NUMBER	Y			供应商ID
	private String styleId;//STYLE_ID	NUMBER	Y			款式ID
	private String styleName;
	private String isBigGraph;//ISBIGGRAPH
	private String status;//STATUS	NUMBER	Y			状态
	private String memo;//MEMO	VARCHAR2(1000)	Y			备注
	private String createDate;//CREATE_DATE	VARCHAR2(20)	Y			创建时间
	private String createId;//CREATE_ID	NUMBER	Y			创建人员
	private String updateDate;//UPDATE_DATE	VARCHAR2(20)	Y			修改时间
	private String updateId;//UPDATE_ID	NUMBER	Y			修改人员
	
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getIsBigGraph() {
		return isBigGraph;
	}
	public void setIsBigGraph(String isBigGraph) {
		this.isBigGraph = isBigGraph;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getVerdorId() {
		return verdorId;
	}
	public void setVerdorId(String verdorId) {
		this.verdorId = verdorId;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
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
