package com.jatools.vo.calc;

public class BasicPriceJade {
	private String billid;
	private String itemclassid;
	private Double startprice;
	private Double endprice;
	private Double bigprice;
	private Double smallprice;
	
	private String createdate;
	private String createuserid;
	private String updatedate;
	private String updateuserid;
	private String status;
	private String decimalnum;//DECIMAL_NUM;
	
	
	public Double getSmallprice() {
		return smallprice;
	}
	public void setSmallprice(Double smallprice) {
		this.smallprice = smallprice;
	}
	public String getDecimalnum() {
		return decimalnum;
	}
	public void setDecimalnum(String decimalnum) {
		this.decimalnum = decimalnum;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getItemclassid() {
		return itemclassid;
	}
	public void setItemclassid(String itemclassid) {
		this.itemclassid = itemclassid;
	}
	public Double getStartprice() {
		return startprice;
	}
	public void setStartprice(Double startprice) {
		this.startprice = startprice;
	}
	public Double getEndprice() {
		return endprice;
	}
	public void setEndprice(Double endprice) {
		this.endprice = endprice;
	}
	public Double getBigprice() {
		return bigprice;
	}
	public void setBigprice(Double bigprice) {
		this.bigprice = bigprice;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdateuserid() {
		return updateuserid;
	}
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
