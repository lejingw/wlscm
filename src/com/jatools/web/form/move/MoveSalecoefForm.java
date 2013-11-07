package com.jatools.web.form.move;

import java.util.List;

import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;
import com.jatools.web.form.BaseForm;

public class MoveSalecoefForm extends BaseForm {
	private MoveSalecoefHead head;
	private List<MoveSalecoefLine> list;

	public MoveSalecoefHead getHead() {
		return head;
	}

	public void setHead(MoveSalecoefHead head) {
		this.head = head;
	}

	public List<MoveSalecoefLine> getList() {
		return list;
	}

	public void setList(List<MoveSalecoefLine> list) {
		this.list = list;
	}

}
