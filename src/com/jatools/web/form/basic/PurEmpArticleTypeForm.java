package com.jatools.web.form.basic;

import java.util.List;

import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.form.BaseForm;

public class PurEmpArticleTypeForm extends BaseForm {
	private MoveSalecoefHead head;
	private String purEmpId;
	private String empFlag;
	private List<SelectorOption> list;

	public String getPurEmpId() {
		return purEmpId;
	}

	public void setPurEmpId(String purEmpId) {
		this.purEmpId = purEmpId;
	}

	public String getEmpFlag() {
		return empFlag;
	}

	public void setEmpFlag(String empFlag) {
		this.empFlag = empFlag;
	}

	public List<SelectorOption> getList() {
		return list;
	}

	public void setList(List<SelectorOption> list) {
		this.list = list;
	}

	public MoveSalecoefHead getHead() {
		return head;
	}

	public void setHead(MoveSalecoefHead head) {
		this.head = head;
	}
}
