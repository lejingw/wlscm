package com.jatools.web.form.basic;

import java.util.List;

import com.jatools.vo.basic.SafeStock;
import com.jatools.web.form.BaseForm;

public class SafeStockForm extends BaseForm {
	private SafeStock safeStock;
	private List<SafeStock>list;

	public SafeStock getSafeStock() {
		return safeStock;
	}

	public void setSafeStock(SafeStock safeStock) {
		this.safeStock = safeStock;
	}

	public List<SafeStock> getList() {
		return list;
	}

	public void setList(List<SafeStock> list) {
		this.list = list;
	}
	
}
