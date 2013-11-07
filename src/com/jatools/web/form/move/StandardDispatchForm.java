package com.jatools.web.form.move;

import java.util.List;

import com.jatools.vo.move.StandardDispatch;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.web.form.BaseForm;

public class StandardDispatchForm extends BaseForm {
	private String gheadid;
	private StandardDispatch ghead;
	private String conditionId;
	private DispatchCondition disCondition;
	private List<DispatchCondition> list;
	private String dispatchModel;//1已配货、2配货成功、3配货未成功
	private String ornaCode;//饰品编码
	private String dispatchOrgId;//分配组织
	
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getDispatchOrgId() {
		return dispatchOrgId;
	}
	public void setDispatchOrgId(String dispatchOrgId) {
		this.dispatchOrgId = dispatchOrgId;
	}
	public String getDispatchModel() {
		return dispatchModel;
	}
	public void setDispatchModel(String dispatchModel) {
		this.dispatchModel = dispatchModel;
	}
	public StandardDispatch getGhead() {
		return ghead;
	}
	public void setGhead(StandardDispatch ghead) {
		this.ghead = ghead;
	}
	public List<DispatchCondition> getList() {
		return list;
	}
	public void setList(List<DispatchCondition> list) {
		this.list = list;
	}
	public String getGheadid() {
		return gheadid;
	}
	public void setGheadid(String gheadid) {
		this.gheadid = gheadid;
	}
	public String getConditionId() {
		return conditionId;
	}
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	public DispatchCondition getDisCondition() {
		return disCondition;
	}
	public void setDisCondition(DispatchCondition disCondition) {
		this.disCondition = disCondition;
	}
	
	
}
