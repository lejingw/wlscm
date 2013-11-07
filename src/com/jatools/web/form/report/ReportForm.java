package com.jatools.web.form.report;

import java.util.List;
import java.util.Map;

import com.jatools.web.form.BaseForm;

public class ReportForm extends BaseForm {
	private String reportType;
	private List<Map> list;
	private List<Map> listSum;
	private List<Map> listTotal;
	private String start;
	private String limit;
	private String totalCount;
	private Map<String, String> condition;

	
	public List<Map> getListTotal() {
		return listTotal;
	}

	public void setListTotal(List<Map> listTotal) {
		this.listTotal = listTotal;
	}

	
	public List<Map> getListSum() {
		return listSum;
	}

	public void setListSum(List<Map> listSum) {
		this.listSum = listSum;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public Map<String, String> getCondition() {
		return condition;
	}
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public void setCondition(Map<String, String> condition) {
		this.condition = condition;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<Map> getList() {
		return list;
	}

	public void setList(List<Map> list) {
		this.list = list;
	}
}
