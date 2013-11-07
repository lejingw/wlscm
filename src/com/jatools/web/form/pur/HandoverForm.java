package com.jatools.web.form.pur;

import java.util.List;

import com.jatools.vo.pur.HandoverCash;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverInStock;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.web.form.BaseForm;

public class HandoverForm extends BaseForm {

	private HandoverHead handoverHead;
	
	private List<HandoverLine> lines ;
	private List<HandoverCash> cashLines;
	private List<HandoverInStock> instockList;
	
	private boolean mini = false;
	
	public boolean getMini() {
		return mini;
	}

	public void setMini(boolean mini) {
		this.mini = mini;
	}

	public HandoverHead getHandoverHead() {
		return handoverHead;
	}

	public void setHandoverHead(HandoverHead handoverHead) {
		this.handoverHead = handoverHead;
	}

	public List<HandoverInStock> getInstockList() {
		return instockList;
	}

	public void setInstockList(List<HandoverInStock> instockList) {
		this.instockList = instockList;
	}

	public List<HandoverLine> getLines() {
		return lines;
	}

	public void setLines(List<HandoverLine> lines) {
		this.lines = lines;
	}

	public List<HandoverCash> getCashLines() {
		return cashLines;
	}

	public void setCashLines(List<HandoverCash> cashLines) {
		this.cashLines = cashLines;
	}

}
