package com.jatools.web.form.tag;

import java.util.List;

import com.jatools.vo.tag.LabelTagImportHead;
import com.jatools.vo.tag.LabelTagImportLine;
import com.jatools.web.form.BaseForm;

public class LabelTagImportForm extends BaseForm {

	private static final long serialVersionUID = 1L;
	private LabelTagImportHead importHead;
	private List<LabelTagImportLine> lines;
	public LabelTagImportHead getImportHead() {
		return importHead;
	}
	public void setImportHead(LabelTagImportHead importHead) {
		this.importHead = importHead;
	}
	public List<LabelTagImportLine> getLines() {
		return lines;
	}
	public void setLines(List<LabelTagImportLine> lines) {
		this.lines = lines;
	}
	
	
}
