package com.jatools.common;

import java.util.List;

public class Pager<T> {
	private int start = Integer.parseInt(Global.PAGE_DEFAULT_START);
	private int limit = Integer.parseInt(Global.PAGE_DEFAULT_LIMIT);
	private int totalCount = 0;

	private List<T> pageData;

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
