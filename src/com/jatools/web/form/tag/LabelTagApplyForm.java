package com.jatools.web.form.tag;

import java.util.List;

import com.jatools.vo.tag.LabelTagApply;
import com.jatools.vo.tag.LabelTagApplyLine;
import com.jatools.web.form.BaseForm;

public class LabelTagApplyForm extends BaseForm {

	private LabelTagApply labelTagApply;
	private List<LabelTagApplyLine> lines;
	public LabelTagApply getLabelTagApply() {
		return labelTagApply;
	}
	public void setLabelTagApply(LabelTagApply labelTagApply) {
		this.labelTagApply = labelTagApply;
	}
	public List<LabelTagApplyLine> getLines() {
		return lines;
	}
	public void setLines(List<LabelTagApplyLine> lines) {
		this.lines = lines;
	}
	
}
