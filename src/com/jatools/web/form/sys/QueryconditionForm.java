package com.jatools.web.form.sys;

import java.util.List;

import com.jatools.vo.sys.QueryConditionHead;
import com.jatools.vo.sys.QueryConditionLine;
import com.jatools.web.form.BaseForm;

public class QueryconditionForm extends BaseForm {
	private QueryConditionHead head;
	private List<QueryConditionLine> lineList;

	public QueryConditionHead getHead() {
		return head;
	}

	public void setHead(QueryConditionHead head) {
		this.head = head;
	}

	public List<QueryConditionLine> getLineList() {
		return lineList;
	}

	public void setLineList(List<QueryConditionLine> lineList) {
		this.lineList = lineList;
	}
}
