package com.jatools.vo.inout;

public class ReceiveHandover {
	
	private String billid;
	private String billno;
	private String dodate;
	private String verdorId;
	private String status;// 交接单状态
	private String srcBillNo;
	private String srcBillType;
	
	private String sumNum;
	private String sumMoney;
	private String sumCharge;
	private String lines;
	
	public String getSumNum() {
		return sumNum;
	}
	public void setSumNum(String sumNum) {
		this.sumNum = sumNum;
	}
	public String getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getSumCharge() {
		return sumCharge;
	}
	public void setSumCharge(String sumCharge) {
		this.sumCharge = sumCharge;
	}
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
	public String getDodate() {
		return dodate;
	}
	public void setDodate(String dodate) {
		this.dodate = dodate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getSrcBillNo() {
		return srcBillNo;
	}
	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	public String getSrcBillType() {
		return srcBillType;
	}
	public void setSrcBillType(String srcBillType) {
		this.srcBillType = srcBillType;
	}
	
	public String getVerdorId() {
		return verdorId;
	}
	public void setVerdorId(String verdorId) {
		this.verdorId = verdorId;
	}
	
}
