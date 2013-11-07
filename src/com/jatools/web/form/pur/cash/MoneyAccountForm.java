package com.jatools.web.form.pur.cash;

import java.util.List;

import com.jatools.vo.out.CashProdAccount;
import com.jatools.web.form.BaseForm;

public class MoneyAccountForm extends BaseForm {

	private List<CashProdAccount> cashList;

	public List<CashProdAccount> getCashList() {
		return cashList;
	}

	public void setCashList(List<CashProdAccount> cashList) {
		this.cashList = cashList;
	}
	
	
}
