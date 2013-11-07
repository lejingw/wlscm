package com.jatools.web.form.out;

import java.util.List;

import com.jatools.vo.out.Consume;
import com.jatools.vo.out.ConsumeLine;
import com.jatools.web.form.BaseForm;

public class ConsumForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Consume consume;
	private List<ConsumeLine> lines;
	public Consume getConsume() {
		return consume;
	}
	public void setConsume(Consume consume) {
		this.consume = consume;
	}
	public List<ConsumeLine> getLines() {
		return lines;
	}
	public void setLines(List<ConsumeLine> lines) {
		this.lines = lines;
	}
	
}
