package com.jatools.vo.util;

public class SelectorOption {
	private String value;
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SelectorOption(String value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public SelectorOption() {
		super();
	}

}
