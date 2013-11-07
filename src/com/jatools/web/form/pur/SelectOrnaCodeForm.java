package com.jatools.web.form.pur;

import com.jatools.web.form.common.SelectorForm;

public class SelectOrnaCodeForm extends SelectorForm {

	private String headId;
	private String ornaCode;
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	
	public SelectOrnaCodeForm(boolean multiFlag, String selectedValues) {
		super(multiFlag, selectedValues);
	}
}
