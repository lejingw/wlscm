package com.jatools.web.form.move;

import java.util.List;

import com.jatools.vo.move.MovePackHead;
import com.jatools.vo.move.MovePackLine;
import com.jatools.web.form.BaseForm;

public class MovePackForm extends BaseForm {
	private String billType;
	private String jmFlag;
	private MovePackHead head;
	private List<MovePackLine> lineList;

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public List<MovePackLine> getLineList() {
		return lineList;
	}

	public void setLineList(List<MovePackLine> lineList) {
		this.lineList = lineList;
	}

	public MovePackHead getHead() {
		return head;
	}

	public void setHead(MovePackHead head) {
		this.head = head;
	}

	public String getJmFlag() {
		return jmFlag;
	}

	public void setJmFlag(String jmFlag) {
		this.jmFlag = jmFlag;
	}
}
