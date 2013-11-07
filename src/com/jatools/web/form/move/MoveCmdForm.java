package com.jatools.web.form.move;

import java.util.List;

import com.jatools.vo.move.MoveCmdHead;
import com.jatools.vo.move.MoveCmdLine;
import com.jatools.web.form.BaseForm;

public class MoveCmdForm extends BaseForm {
	
	private MoveCmdHead head;
	private List<MoveCmdLine> lineList;


	public List<MoveCmdLine> getLineList() {
		return lineList;
	}

	public void setLineList(List<MoveCmdLine> lineList) {
		this.lineList = lineList;
	}

	public MoveCmdHead getHead() {
		return head;
	}

	public void setHead(MoveCmdHead head) {
		this.head = head;
	}

}
