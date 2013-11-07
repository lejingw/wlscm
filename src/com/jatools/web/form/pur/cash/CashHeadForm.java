package com.jatools.web.form.pur.cash;

import java.util.List;

import com.jatools.vo.pur.cash.CashHead;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.web.form.BaseForm;

public class CashHeadForm extends BaseForm {

	private CashHead cashHead;
	private List<CashLine> lines;
	public CashHead getCashHead() {
		return cashHead;
	}
	public void setCashHead(CashHead cashHead) {
		this.cashHead = cashHead;
	}
	public List<CashLine> getLines() {
		return lines;
	}
	public void setLines(List<CashLine> lines) {
		this.lines = lines;
	}
	
	
}
