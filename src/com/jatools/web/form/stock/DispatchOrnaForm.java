package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.bd.Org;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchRealCost;
import com.jatools.vo.stock.DispatchStatistics;
import com.jatools.vo.stock.GatherHead;
import com.jatools.web.form.BaseForm;

public class DispatchOrnaForm extends BaseForm {
	private String gheadid;
	private String gstatus;
	private String conditionId;
	private DispatchCondition disCondition;
	private List<Org> dispatchOrgList;
	private String inOrgId;
	private List<DispatchMoveBill> moveBillList;
	private List<DispatchRealCost> realCostList;
	private List<DispatchStatistics> statisticsList;

	public List<DispatchRealCost> getRealCostList() {
		return realCostList;
	}

	public void setRealCostList(List<DispatchRealCost> realCostList) {
		this.realCostList = realCostList;
	}

	public List<DispatchStatistics> getStatisticsList() {
		return statisticsList;
	}

	public void setStatisticsList(List<DispatchStatistics> statisticsList) {
		this.statisticsList = statisticsList;
	}

	public String getInOrgId() {
		return inOrgId;
	}

	public void setInOrgId(String inOrgId) {
		this.inOrgId = inOrgId;
	}

	public List<Org> getDispatchOrgList() {
		return dispatchOrgList;
	}

	public void setDispatchOrgList(List<Org> dispatchOrgList) {
		this.dispatchOrgList = dispatchOrgList;
	}

	public DispatchCondition getDisCondition() {
		return disCondition;
	}

	public void setDisCondition(DispatchCondition disCondition) {
		this.disCondition = disCondition;
	}

	private GatherHead ghead;

	public GatherHead getGhead() {
		return ghead;
	}

	public void setGhead(GatherHead ghead) {
		this.ghead = ghead;
	}

	public String getGheadid() {
		return gheadid;
	}

	public void setGheadid(String gheadid) {
		this.gheadid = gheadid;
	}

	public String getGstatus() {
		return gstatus;
	}

	public void setGstatus(String gstatus) {
		this.gstatus = gstatus;
	}

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public void setMoveBillList(List<DispatchMoveBill> list) {
		moveBillList = list;
	}

	public List<DispatchMoveBill> getMoveBillList() {
		return moveBillList;
	}
}
