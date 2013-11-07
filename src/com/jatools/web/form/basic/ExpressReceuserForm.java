package com.jatools.web.form.basic;

import java.util.List;

import com.jatools.vo.basic.ExpressReceuser;
import com.jatools.web.form.BaseForm;

public class ExpressReceuserForm extends BaseForm {
	private String orgId;
	
	private List<ExpressReceuser> list;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<ExpressReceuser> getList() {
		return list;
	}

	public void setList(List<ExpressReceuser> list) {
		this.list = list;
	}
	
}
