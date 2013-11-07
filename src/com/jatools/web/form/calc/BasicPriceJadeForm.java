package com.jatools.web.form.calc;

import com.jatools.vo.calc.BasicPriceJade;
import com.jatools.web.form.BaseForm;

public class BasicPriceJadeForm extends BaseForm{
	private BasicPriceJade sp;
	private String priceType;
	
	

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public BasicPriceJade getSp() {
		return sp;
	}

	public void setSp(BasicPriceJade sp) {
		this.sp = sp;
	}
	

}
