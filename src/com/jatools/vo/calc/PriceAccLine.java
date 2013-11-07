package com.jatools.vo.calc;

public class PriceAccLine {

	private String id; //ID	NUMBER
	private String headId;//HEAD_ID 核价单头表ID
	private String accId;//ACC_ID 辅料ID
	private Double accWithoutTaxCost;//ACC_WITHOUT_TAX_COST	NUMBER 辅料不含税成本
	private Double accTax;//ACC_TAX	NUMBER 辅料税率
	private Double accWithTaxCost;//ACC_WITH_TAX_COST 辅料含税成本
	private Double accMarketPrice;//ACC_MARKET_PRICE 辅料市场价
	private String accCoefficient;//ACC_COEFFICIENT 辅料系数
	private Double priceWithCoefficient;//PRICE_WITH_COEFFICIENT 含系数后价
	private String verificationFlag;//VERIFICATION_FLAG 是否核销
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public Double getAccWithoutTaxCost() {
		return accWithoutTaxCost;
	}
	public void setAccWithoutTaxCost(Double accWithoutTaxCost) {
		this.accWithoutTaxCost = accWithoutTaxCost;
	}
	public Double getAccTax() {
		return accTax;
	}
	public void setAccTax(Double accTax) {
		this.accTax = accTax;
	}
	public Double getAccWithTaxCost() {
		return accWithTaxCost;
	}
	public void setAccWithTaxCost(Double accWithTaxCost) {
		this.accWithTaxCost = accWithTaxCost;
	}
	public Double getAccMarketPrice() {
		return accMarketPrice;
	}
	public void setAccMarketPrice(Double accMarketPrice) {
		this.accMarketPrice = accMarketPrice;
	}
	public String getAccCoefficient() {
		return accCoefficient;
	}
	public void setAccCoefficient(String accCoefficient) {
		this.accCoefficient = accCoefficient;
	}
	public Double getPriceWithCoefficient() {
		return priceWithCoefficient;
	}
	public void setPriceWithCoefficient(Double priceWithCoefficient) {
		this.priceWithCoefficient = priceWithCoefficient;
	}
	public String getVerificationFlag() {
		return verificationFlag;
	}
	public void setVerificationFlag(String verificationFlag) {
		this.verificationFlag = verificationFlag;
	}

	
}
