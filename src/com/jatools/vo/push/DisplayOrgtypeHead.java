package com.jatools.vo.push;

import com.jatools.vo.BaseVO;

public class DisplayOrgtypeHead extends BaseVO {
	private String headid;
	private String orgtypeName;
	private String memo;
	
	public String getHeadid() {
		return headid;
	}
	public void setHeadid(String headid) {
		this.headid = headid;
	}
	public String getOrgtypeName() {
		return orgtypeName;
	}
	public void setOrgtypeName(String orgtypeName) {
		this.orgtypeName = orgtypeName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
