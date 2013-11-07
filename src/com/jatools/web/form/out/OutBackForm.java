package com.jatools.web.form.out;

import java.util.List;

import com.jatools.vo.out.OutBackHead;
import com.jatools.vo.out.OutBackLine;
import com.jatools.web.form.BaseForm;

public class OutBackForm extends BaseForm {
	private OutBackHead head=new OutBackHead();
	private List<OutBackLine> list;
	private String showAll;

	public String getShowAll() {
		return showAll;
	}

	public void setShowAll(String showAll) {
		this.showAll = showAll;
	}

	public OutBackHead getHead() {
		return head;
	}

	public void setHead(OutBackHead head) {
		this.head = head;
	}

	public List<OutBackLine> getList() {
		return list;
	}

	public void setList(List<OutBackLine> list) {
		this.list = list;
	}

}
