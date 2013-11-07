package com.jatools.web.form.basic;

import java.util.List;

import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.basic.PriceLockLine;
import com.jatools.web.form.BaseForm;

public class PriceLockForm extends BaseForm {
	private String orgId;
	private PriceLockHead head;
	private List<PriceLockLine> list;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<PriceLockLine> getList() {
		return list;
	}

	public void setList(List<PriceLockLine> list) {
		this.list = list;
	}

	public PriceLockHead getHead() {
		return head;
	}

	public void setHead(PriceLockHead head) {
		this.head = head;
	}

}
