package com.jatools.web.form.inout;

import java.util.List;

import com.jatools.vo.inout.ReceiveHead;
import com.jatools.vo.inout.ReceiveLine;
import com.jatools.web.form.BaseForm;

public class ReceiveForm extends BaseForm {

	private ReceiveHead receiveHead;
	private List<ReceiveLine> lines;
	public ReceiveHead getReceiveHead() {
		return receiveHead;
	}
	public void setReceiveHead(ReceiveHead receiveHead) {
		this.receiveHead = receiveHead;
	}
	public List<ReceiveLine> getLines() {
		return lines;
	}
	public void setLines(List<ReceiveLine> lines) {
		this.lines = lines;
	}
	
}
