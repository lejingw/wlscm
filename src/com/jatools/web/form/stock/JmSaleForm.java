package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.JmSaleHead;
import com.jatools.vo.stock.JmSaleLine;
import com.jatools.web.form.BaseForm;


public class JmSaleForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private JmSaleHead jmSaleHead;

	private List<JmSaleLine> lines;


	public JmSaleHead getJmSaleHead() {
		return jmSaleHead;
	}

	public void setJmSaleHead(JmSaleHead JmSaleHead) {
		this.jmSaleHead = JmSaleHead;
	}

	

	public List<JmSaleLine> getLines() {
		return lines;
	}

	public void setLines(List<JmSaleLine> lines) {
		this.lines = lines;
	}

}
