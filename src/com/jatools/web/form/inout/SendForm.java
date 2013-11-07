package com.jatools.web.form.inout;

import java.util.List;

import com.jatools.vo.inout.SendHead;
import com.jatools.vo.inout.SendLine;
import com.jatools.web.form.BaseForm;

public class SendForm extends BaseForm {

	private SendHead sendHead;
	private List<SendLine> lines;
	public SendHead getSendHead() {
		return sendHead;
	}
	public void setSendHead(SendHead sendHead) {
		this.sendHead = sendHead;
	}
	public List<SendLine> getLines() {
		return lines;
	}
	public void setLines(List<SendLine> lines) {
		this.lines = lines;
	}
	
	
}
