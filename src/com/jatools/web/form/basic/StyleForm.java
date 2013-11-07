package com.jatools.web.form.basic;

import com.jatools.vo.bd.Style;
import com.jatools.web.form.BaseForm;

public class StyleForm extends BaseForm {
	private Style s;
	private String stylePicUrl;
	
	public String getStylePicUrl() {
		return stylePicUrl;
	}

	public void setStylePicUrl(String stylePicUrl) {
		this.stylePicUrl = stylePicUrl;
	}

	public Style getS() {
		return s;
	}

	public void setS(Style s) {
		this.s = s;
	}
	
}
