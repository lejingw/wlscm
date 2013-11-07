package com.jatools.web.form.sys;

import java.util.List;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;
import com.jatools.web.form.BaseForm;

public class DictForm extends BaseForm {
	private String action;
	private String entryType;
	private DictEntry entry;
	private List<DictItem> list;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public DictEntry getEntry() {
		return entry;
	}

	public void setEntry(DictEntry entry) {
		this.entry = entry;
	}

	public List<DictItem> getList() {
		return list;
	}

	public void setList(List<DictItem> list) {
		this.list = list;
	}
}
