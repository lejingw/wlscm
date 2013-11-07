package com.jatools.web.form.push;

import java.util.List;

import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.web.form.BaseForm;

public class GatherSetForm extends BaseForm {
	private GatherSetHead head;
	private List<GatherSetLine> lineList;
	private String backUrl;
	
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public GatherSetHead getHead() {
		return head;
	}

	public void setHead(GatherSetHead head) {
		this.head = head;
	}

	public List<GatherSetLine> getLineList() {
		return lineList;
	}

	public void setLineList(List<GatherSetLine> lineList) {
		this.lineList = lineList;
	}

}
