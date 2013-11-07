package com.jatools.web.form.move;

import java.util.List;

import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.web.form.BaseForm;

public class MoveBillForm extends BaseForm {
	private String billType;
	private String jmFlag;
	private MoveBillHead head;
	private String printLabelType;
	private List<MoveBillLine> lineList;
	private String printData;

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getJmFlag() {
		return jmFlag;
	}

	public void setJmFlag(String jmFlag) {
		this.jmFlag = jmFlag;
	}

	public List<MoveBillLine> getLineList() {
		return lineList;
	}

	public void setLineList(List<MoveBillLine> lineList) {
		this.lineList = lineList;
	}

	public MoveBillHead getHead() {
		return head;
	}

	public void setHead(MoveBillHead head) {
		this.head = head;
	}

	public String getPrintLabelType() {
		return printLabelType;
	}

	public void setPrintLabelType(String printLabelType) {
		this.printLabelType = printLabelType;
	}

	public String getPrintData() {
		return printData;
	}

	public void setPrintData(String printData) {
		this.printData = printData;
	}
}
