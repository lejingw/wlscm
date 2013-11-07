package com.jatools.web.form.basic;

import com.jatools.vo.basic.Charge;
import com.jatools.vo.basic.FashionGoldPrice;
import com.jatools.web.form.BaseForm;


public class FashionGoldPriceForm extends BaseForm {
	private FashionGoldPrice goldPrice;

    public FashionGoldPrice getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(FashionGoldPrice goldPrice) {
        this.goldPrice = goldPrice;
    }
}
