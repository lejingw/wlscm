package com.jatools.web.form.basic;

import java.util.List;

import com.jatools.vo.basic.OrgItem;
import com.jatools.web.form.BaseForm;

public class OrgItemForm extends BaseForm {
	private OrgItem oi;
	private List<String> items;
	
	
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public OrgItem getOi() {
		return oi;
	}

	public void setOi(OrgItem oi) {
		this.oi = oi;
	}
	
	
}
