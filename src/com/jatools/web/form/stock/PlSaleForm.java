package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.PlSaleHead;
import com.jatools.vo.stock.PlSaleLine;
import com.jatools.web.form.BaseForm;


public class PlSaleForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private PlSaleHead PlSaleHead;

	private List<PlSaleLine> lines;


	public PlSaleHead getPlSaleHead() {
		return PlSaleHead;
	}

	public void setPlSaleHead(PlSaleHead PlSaleHead) {
		this.PlSaleHead = PlSaleHead;
	}

	

	public List<PlSaleLine> getLines() {
		return lines;
	}

	public void setLines(List<PlSaleLine> lines) {
		this.lines = lines;
	}

}
