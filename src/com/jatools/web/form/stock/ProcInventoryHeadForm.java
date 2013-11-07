package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.ProcInventoryHead;
import com.jatools.vo.stock.ProcInventoryLine;
import com.jatools.web.form.BaseForm;


public class ProcInventoryHeadForm extends BaseForm {
	private ProcInventoryHead procInventoryHead;
	
	private List<ProcInventoryLine> lines;
	
	
	public ProcInventoryHead getProcInventoryHead() {
		return procInventoryHead;
	}

	public void setProcInventoryHead(ProcInventoryHead ProcInventoryHead) {
		this.procInventoryHead = ProcInventoryHead;
	}

	public List<ProcInventoryLine> getLines() {
		return lines;
	}

	public void setLines(List<ProcInventoryLine> lines) {
		this.lines = lines;
	}
}
