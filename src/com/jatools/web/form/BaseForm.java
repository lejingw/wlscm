package com.jatools.web.form;

import java.io.Serializable;
import java.util.Map;

import com.jatools.common.Pager;

/**
 * 表单对象基类
 */
public class BaseForm implements Serializable {
	// 信息提示
	protected String message;

	// 成功标识
	protected boolean successfulFlag;

	// 分页数据对象
	protected Pager pager;

	//页面查询条件存放对象
	private Map<String, String> condition;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccessfulFlag() {
		return successfulFlag;
	}

	public void setSuccessfulFlag(boolean successfulFlag) {
		this.successfulFlag = successfulFlag;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Map<String, String> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, String> condition) {
		this.condition = condition;
	}

}
