package com.jatools.web.form.calc;

import java.util.List;

import com.jatools.vo.calc.SbraTypeHead;
import com.jatools.vo.calc.SbraTypeLine;
import com.jatools.web.form.BaseForm;

public class SbraTypeForm extends BaseForm{
	private SbraTypeHead head;
	private List<SbraTypeLine> line;
	public SbraTypeHead getHead() {
		return head;
	}
	public void setHead(SbraTypeHead head) {
		this.head = head;
	}
	public List<SbraTypeLine> getLine() {
		return line;
	}
	public void setLine(List<SbraTypeLine> line) {
		this.line = line;
	}
	
	
}
