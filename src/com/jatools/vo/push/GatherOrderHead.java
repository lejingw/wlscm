package com.jatools.vo.push;


public class GatherOrderHead extends GatherSetHead {
	private String purchaseFlag;
	private String dispatchFlag;
	private String ordertype;
	private String dotype;
	public String getPurchaseFlag() {
		return purchaseFlag;
	}
	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}
	public String getDispatchFlag() {
		return dispatchFlag;
	}
	public void setDispatchFlag(String dispatchFlag) {
		this.dispatchFlag = dispatchFlag;
	}
	public String getDotype() {
		return dotype;
	}
	public void setDotype(String dotype) {
		this.dotype = dotype;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	
}
