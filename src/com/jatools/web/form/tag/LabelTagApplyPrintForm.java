package com.jatools.web.form.tag;

import java.util.List;

import com.jatools.vo.tag.LabelTagApplyPrint;
import com.jatools.vo.tag.LabelTagApplyPrintLine;
import com.jatools.web.form.BaseForm;

public class LabelTagApplyPrintForm extends BaseForm {

	private LabelTagApplyPrint labelTagApplyPrint;
	private List<LabelTagApplyPrintLine> lines;
	public LabelTagApplyPrint getLabelTagApplyPrint() {
		return labelTagApplyPrint;
	}
	public void setLabelTagApplyPrint(LabelTagApplyPrint labelTagApplyPrint) {
		this.labelTagApplyPrint = labelTagApplyPrint;
	}
	public List<LabelTagApplyPrintLine> getLines() {
		return lines;
	}
	public void setLines(List<LabelTagApplyPrintLine> lines) {
		this.lines = lines;
	}
	
}
