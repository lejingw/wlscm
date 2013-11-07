package com.jatools.vo.stock;

public class PushDispatchTemp {
	private String orgId;//分配店
	private String dispatchedNum;//已配数
	private String dispatchingNum;//正配数
	private String matchDegee;//契合度
	private String numDiff;//缺货差异
	private String gradeDiff;//品质差异
	
	
	private String gheadid;
	private String glineid;
	private String orderEndDate;
	public String getGheadid() {
		return gheadid;
	}
	public void setGheadid(String gheadid) {
		this.gheadid = gheadid;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrderEndDate() {
		return orderEndDate;
	}
	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	public String getDispatchedNum() {
		return dispatchedNum;
	}
	public void setDispatchedNum(String dispatchedNum) {
		this.dispatchedNum = dispatchedNum;
	}
	public String getDispatchingNum() {
		return dispatchingNum;
	}
	public void setDispatchingNum(String dispatchingNum) {
		this.dispatchingNum = dispatchingNum;
	}
	public String getMatchDegee() {
		return matchDegee;
	}
	public void setMatchDegee(String matchDegee) {
		this.matchDegee = matchDegee;
	}
	public String getNumDiff() {
		return numDiff;
	}
	public void setNumDiff(String numDiff) {
		this.numDiff = numDiff;
	}
	public String getGradeDiff() {
		return gradeDiff;
	}
	public void setGradeDiff(String gradeDiff) {
		this.gradeDiff = gradeDiff;
	}

    public String getGlineid() {
        return glineid;
    }

    public void setGlineid(String glineid) {
        this.glineid = glineid;
    }
}
