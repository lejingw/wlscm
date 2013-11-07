package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.ProcInventoryDiffhead;
import com.jatools.vo.stock.ProcInventoryDiffline;
import com.jatools.web.form.BaseForm;


public class ProcInventoryDiffHeadForm extends BaseForm {
	private ProcInventoryDiffhead procInventoryDiffhead;
	
	private List<ProcInventoryDiffline> lines;
	
	
	public ProcInventoryDiffhead getProcInventoryDiffhead() {
		return procInventoryDiffhead;
	}

	public void setProcInventoryDiffhead(ProcInventoryDiffhead procInventoryDiffhead) {
		this.procInventoryDiffhead = procInventoryDiffhead;
	}

	public List<ProcInventoryDiffline> getLines() {
		return lines;
	}

	public void setLines(List<ProcInventoryDiffline> lines) {
		this.lines = lines;
	}
}
