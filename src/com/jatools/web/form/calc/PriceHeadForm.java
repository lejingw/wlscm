package com.jatools.web.form.calc;

import java.util.List;

import com.jatools.vo.calc.PriceAccLine;
import com.jatools.vo.calc.PriceAcsLine;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.calc.PriceSbraLine;
import com.jatools.vo.calc.PriceStoneLine;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.web.form.BaseForm;

public class PriceHeadForm extends BaseForm {
	private PriceHead head;
	private List<PriceAccLine> accLine;
	private List<PriceAcsLine> acsLine;
	private List<PriceSbraLine> sbraLine;
	private List<PriceStoneLine> stoneLine;
	private List<PurchaseHead> pur;
	private List<CashProdAccount> cashLine;
	private String caclType;
	
	
	public List<CashProdAccount> getCashLine() {
		return cashLine;
	}
	public void setCashLine(List<CashProdAccount> cashLine) {
		this.cashLine = cashLine;
	}
	public List<PurchaseHead> getPur() {
		return pur;
	}
	public void setPur(List<PurchaseHead> pur) {
		this.pur = pur;
	}
	public PriceHead getHead() {
		return head;
	}
	public void setHead(PriceHead head) {
		this.head = head;
	}
	public List<PriceAccLine> getAccLine() {
		return accLine;
	}
	public void setAccLine(List<PriceAccLine> accLine) {
		this.accLine = accLine;
	}
	public List<PriceAcsLine> getAcsLine() {
		return acsLine;
	}
	public void setAcsLine(List<PriceAcsLine> acsLine) {
		this.acsLine = acsLine;
	}
	public List<PriceSbraLine> getSbraLine() {
		return sbraLine;
	}
	public void setSbraLine(List<PriceSbraLine> sbraLine) {
		this.sbraLine = sbraLine;
	}
	public List<PriceStoneLine> getStoneLine() {
		return stoneLine;
	}
	public void setStoneLine(List<PriceStoneLine> stoneLine) {
		this.stoneLine = stoneLine;
	}
	public String getCaclType() {
		return caclType;
	}
	public void setCaclType(String caclType) {
		this.caclType = caclType;
	}
	
	
}
