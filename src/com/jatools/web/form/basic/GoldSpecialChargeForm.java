package com.jatools.web.form.basic;

import com.jatools.vo.basic.Charge;
import com.jatools.vo.basic.GoldSpecialCharge;
import com.jatools.web.form.BaseForm;


public class GoldSpecialChargeForm extends BaseForm {
	private GoldSpecialCharge goldSpecialCharge;

    public GoldSpecialCharge getGoldSpecialCharge() {
        return goldSpecialCharge;
    }

    public void setGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge) {
        this.goldSpecialCharge = goldSpecialCharge;
    }
}
