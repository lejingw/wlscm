package com.jatools.web.form.move;

import java.util.List;

import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.web.form.BaseForm;

public class MoveReceiveForm extends BaseForm {
	private String billType;
	private String jmFlag;
	private MoveBillHead head;
	private List<MoveBillLine> lineList;

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
}
