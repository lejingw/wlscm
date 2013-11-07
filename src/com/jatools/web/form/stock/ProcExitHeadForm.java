package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.ProcExitHead;
import com.jatools.vo.stock.ProcExitLine;
import com.jatools.web.form.BaseForm;


public class ProcExitHeadForm extends BaseForm {
	private ProcExitHead procExitHead;
	/**
	 * 退料列表
	 */
	private List<ProcExitLine> exitLines;
	
	public ProcExitHead getProcExitHead() {
		return procExitHead;
	}

	public void setProcExitHead(ProcExitHead ProcExitHead) {
		this.procExitHead = ProcExitHead;
	}

	

	public List<ProcExitLine> getExitLines() {
		return exitLines;
	}

	public void setExitLines(List<ProcExitLine> exitLines) {
		this.exitLines = exitLines;
	}

}
