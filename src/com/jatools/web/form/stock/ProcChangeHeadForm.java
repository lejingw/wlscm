package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.vo.stock.ProcChangeLine;
import com.jatools.web.form.BaseForm;


public class ProcChangeHeadForm extends BaseForm {
	private ProcChangeHead procChangeHead;

	private List<ProcChangeLine> oldLines;


	public ProcChangeHead getProcChangeHead() {
		return procChangeHead;
	}

	public void setProcChangeHead(ProcChangeHead procChangeHead) {
		this.procChangeHead = procChangeHead;
	}

	

	public List<ProcChangeLine> getOldLines() {
		return oldLines;
	}

	public void setOldLines(List<ProcChangeLine> oldLines) {
		this.oldLines = oldLines;
	}

}
