package com.jatools.web.form.common;

import java.util.List;
import java.util.Map;

import com.jatools.common.CommonUtil;

public class SelectorForm {
	private boolean multiFlag = false;
	private String selectedValues;
	@SuppressWarnings("rawtypes")
	private List dataList;
	@SuppressWarnings("rawtypes")
	private Map dataMap;
	private String type;
	private String handoverBillId;
	private String allowInivType;
	private String iniv;
	private String itemClassId ;
	private String ornaClassId ;
	private String styleItemId ;
	private String styleMiddleId ;
	private String styleOrnaId ;
	private String vender;
	private String orgId;
	
	
	public String getVender() {
		return vender;
	}

	public void setVender(String vender) {
		this.vender = vender;
	}

	public String getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}

	public String getOrnaClassId() {
		return ornaClassId;
	}

	public void setOrnaClassId(String ornaClassId) {
		this.ornaClassId = ornaClassId;
	}

	public String getStyleItemId() {
		return styleItemId;
	}

	public void setStyleItemId(String styleItemId) {
		this.styleItemId = styleItemId;
	}

	public String getStyleMiddleId() {
		return styleMiddleId;
	}

	public void setStyleMiddleId(String styleMiddleId) {
		this.styleMiddleId = styleMiddleId;
	}

	public String getStyleOrnaId() {
		return styleOrnaId;
	}

	public void setStyleOrnaId(String styleOrnaId) {
		this.styleOrnaId = styleOrnaId;
	}

	public String getIniv() {
		return iniv;
	}

	public void setIniv(String iniv) {
		this.iniv = iniv;
	}

	public String getHandoverBillId() {
		return handoverBillId;
	}

	public void setHandoverBillId(String handoverBillId) {
		this.handoverBillId = handoverBillId;
	}

	public String getAllowInivType() {
		return allowInivType;
	}

	public void setAllowInivType(String allowInivType) {
		this.allowInivType = allowInivType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SelectorForm() {
	}

	public SelectorForm(boolean multiFlag, String selectedValues, String handoverBillId,String allowInivType,String iniv,
			String itemClassId,String ornaClassId,String styleItemId,String styleMiddleId,String styleOrnaId,String vender) {
		this.multiFlag = multiFlag;
		this.selectedValues = selectedValues;
		this.handoverBillId = handoverBillId;
		this.allowInivType = allowInivType;
		this.iniv = iniv;
		this.itemClassId = itemClassId;
		this.ornaClassId = ornaClassId;
		this.styleItemId = styleItemId;
		this.styleMiddleId = styleMiddleId;
		this.styleOrnaId = styleOrnaId;
		this.vender = vender;
	}
	public SelectorForm(boolean multiFlag, String selectedValues) {
		this.multiFlag = multiFlag;
		this.selectedValues = selectedValues;
	}
	public boolean isMultiFlag() {
		return multiFlag;
	}

	public void setMultiFlag(boolean multiFlag) {
		this.multiFlag = multiFlag;
	}

	public String getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(String selectedValues) {
		this.selectedValues = selectedValues;
	}

	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}

	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	@SuppressWarnings("rawtypes")
	public Map getDataMap() {
		return dataMap;
	}

	@SuppressWarnings("rawtypes")
	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}