package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.DispatchCondition;
import com.jatools.web.form.BaseForm;

public class DispatchConditonForm extends BaseForm {
	private String gheadid;
	private String gstatus;
	private List<DispatchCondition> list = null;
	
	public List<DispatchCondition> getList() {
		return list;
	}
	public void setList(List<DispatchCondition> list) {
		this.list = list;
	}
	public String getGheadid() {
		return gheadid;
	}
	public void setGheadid(String gheadid) {
		this.gheadid = gheadid;
	}
	public String getGstatus() {
		return gstatus;
	}
	public void setGstatus(String gstatus) {
		this.gstatus = gstatus;
	}
	
}
