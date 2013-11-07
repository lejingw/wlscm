package com.jatools.web.form.calc;

import com.jatools.vo.calc.BasicPriceTwo;
import com.jatools.web.form.BaseForm;

public class BasicPriceTwoForm extends BaseForm{
	private BasicPriceTwo sp;
	private String priceType;
	
	

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public BasicPriceTwo getSp() {
		return sp;
	}

	public void setSp(BasicPriceTwo sp) {
		this.sp = sp;
	}
	

}
