package com.jatools.web.form.util;

import java.util.List;
import java.util.Map;

import com.jatools.web.form.BaseForm;

public class CommonForm extends BaseForm{
	private String styleId;
	private String styleBigPicPath;
	private String styleSmallPicPath;
    private String styleObliquePicPath;
    private String styleTopviewPicPath;
	private List<Map<String, String>> reviewLogList;
	
	public String getStyleBigPicPath() {
		return styleBigPicPath;
	}
	public void setStyleBigPicPath(String styleBigPicPath) {
		this.styleBigPicPath = styleBigPicPath;
	}
	public String getStyleSmallPicPath() {
		return styleSmallPicPath;
	}
	public void setStyleSmallPicPath(String styleSmallPicPath) {
		this.styleSmallPicPath = styleSmallPicPath;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public void setReviewLogList(List<Map<String, String>> reviewLogList) {
		this.reviewLogList = reviewLogList;
	}
	public List<Map<String, String>> getReviewLogList() {
		return reviewLogList;
	}

    public String getStyleObliquePicPath() {
        return styleObliquePicPath;
    }

    public void setStyleObliquePicPath(String styleObliquePicPath) {
        this.styleObliquePicPath = styleObliquePicPath;
    }

    public String getStyleTopviewPicPath() {
        return styleTopviewPicPath;
    }

    public void setStyleTopviewPicPath(String styleTopviewPicPath) {
        this.styleTopviewPicPath = styleTopviewPicPath;
    }
}
