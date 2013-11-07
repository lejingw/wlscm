package com.jatools.web.form.pur;

import java.util.List;

import com.jatools.vo.pur.PurchaseCust;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.web.form.BaseForm;

public class PurchaseCustForm extends BaseForm {
	private PurchaseCust cust;
	private List<PurchaseHead> vendorList;
	private String selectedStyleId;
	private String stylePicPath;
	private String jmFlag;
	

	public String getSelectedStyleId() {
		return selectedStyleId;
	}

	public void setSelectedStyleId(String selectedStyleId) {
		this.selectedStyleId = selectedStyleId;
	}

	public List<PurchaseHead> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<PurchaseHead> vendorList) {
		this.vendorList = vendorList;
	}

	public PurchaseCust getCust() {
		return cust;
	}

	public void setCust(PurchaseCust cust) {
		this.cust = cust;
	}

	public String getStylePicPath() {
		return stylePicPath;
	}

	public void setStylePicPath(String stylePicPath) {
		this.stylePicPath = stylePicPath;
	}

	public String getJmFlag() {
		return jmFlag;
	}

	public void setJmFlag(String jmFlag) {
		this.jmFlag = jmFlag;
	}

}
