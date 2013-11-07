package com.jatools.vo.bd;

public class Org {
	private String orgId;
	private String parentId;
	private String orgName;
	private String jmFlag;
	
	private String nocheck;

	public Org() {
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getJmFlag() {
		return jmFlag;
	}

	public void setJmFlag(String jmFlag) {
		this.jmFlag = jmFlag;
	}

	public String getNocheck() {
		return nocheck;
	}

	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}
}
