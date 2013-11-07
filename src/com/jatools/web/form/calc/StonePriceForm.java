package com.jatools.web.form.calc;

import com.jatools.vo.calc.StonePrice;
import com.jatools.web.form.BaseForm;

public class StonePriceForm extends BaseForm{
	private StonePrice sp;
	private String priceType;
	
	

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public StonePrice getSp() {
		return sp;
	}

	public void setSp(StonePrice sp) {
		this.sp = sp;
	}
	

}
