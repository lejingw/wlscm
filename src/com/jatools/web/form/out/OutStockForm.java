package com.jatools.web.form.out;

import java.util.List;

import com.jatools.vo.out.OutStockHead;
import com.jatools.vo.out.OutStockLine;
import com.jatools.web.form.BaseForm;

public class OutStockForm extends BaseForm {
	private OutStockHead head=new OutStockHead();
	private List<OutStockLine> list;

	public OutStockHead getHead() {
		return head;
	}

	public void setHead(OutStockHead head) {
		this.head = head;
	}

	public List<OutStockLine> getList() {
		return list;
	}

	public void setList(List<OutStockLine> list) {
		this.list = list;
	}

}
