package com.jatools.vo.pur;

public class PurchaseLineSession {
	private String glineid;
	private String numCurrentDispatch;
	
	public String getGlineid() {
		return glineid;
	}
	public void setGlineid(String glineid) {
		this.glineid = glineid;
	}
	public String getNumCurrentDispatch() {
		return numCurrentDispatch;
	}
	public void setNumCurrentDispatch(String numCurrentDispatch) {
		this.numCurrentDispatch = numCurrentDispatch;
	}
	
	public PurchaseLineSession(String glineid, String numCurrentDispatch) {
		super();
		this.glineid = glineid;
		this.numCurrentDispatch = numCurrentDispatch;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((glineid == null) ? 0 : glineid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseLineSession other = (PurchaseLineSession) obj;
		if (glineid == null) {
			if (other.glineid != null)
				return false;
		} else if (!glineid.equals(other.glineid))
			return false;
		return true;
	}
}
