package com.jatools.web.form.move;

import java.util.List;

import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.form.BaseForm;

public class MoveNetForm extends BaseForm {
	private String orgid;
	private String outflag;
	private List<SelectorOption> list;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOutflag() {
		return outflag;
	}

	public void setOutflag(String outflag) {
		this.outflag = outflag;
	}

	public List<SelectorOption> getList() {
		return list;
	}

	public void setList(List<SelectorOption> list) {
		this.list = list;
	}
}
