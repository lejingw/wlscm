package com.jatools.web.form.stock;

import com.jatools.vo.stock.MaterIniv;
import com.jatools.web.form.BaseForm;

public class MaterInivForm extends BaseForm {
	private MaterIniv iniv;
	private String inivType;
	private String printData;
	private String pt;
	private String params;

	
	public String getInivType() {
		return inivType;
	}

	public void setInivType(String inivType) {
		this.inivType = inivType;
	}

	public MaterIniv getIniv() {
		return iniv;
	}

	public void setIniv(MaterIniv iniv) {
		this.iniv = iniv;
	}

	public String getPrintData() {
		return printData;
	}

	public void setPrintData(String printData) {
		this.printData = printData;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	
	
}
