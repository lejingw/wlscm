package com.jatools.web.form.pur;

import com.jatools.vo.pur.PurGatherHead;
import com.jatools.web.form.BaseForm;

public class PurGatherForm extends BaseForm {

	private PurGatherHead head;

	public void setHead(PurGatherHead head) {
		this.head = head;
	}

	public PurGatherHead getHead() {
		return head;
	}
}
