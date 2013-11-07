package com.jatools.web.form.calc;

import com.jatools.vo.calc.BasicPriceOne;
import com.jatools.web.form.BaseForm;

public class BasicPriceOneForm extends BaseForm{
	private BasicPriceOne sp;
	private String priceType;
	
	

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public BasicPriceOne getSp() {
		return sp;
	}

	public void setSp(BasicPriceOne sp) {
		this.sp = sp;
	}
	

}
