package com.jatools.vo.pur;

import java.util.List;

public class HandoverInStock {

	private String itemClassId;
	private String itemClassName;
	private String unitId;
	private String unitName;
	private Double num;
	private Double noNum;
	private Double stockNum;
	private Double stockWeight;
	private Double stockGrain;
	
	private Double stockAllNum;
	private Double stockAllWeight;
	private Double stockAllGrain;
	
	private Integer stockCount;
	private Integer stockAllCount;

	private String analysisId;
	private String analysisName;
	
	public String getAnalysisId() {
		return analysisId;
	}
	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}
	public String getAnalysisName() {
		return analysisName;
	}
	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}
	private List<HandoverInStock> children;
	
	public List<HandoverInStock> getChildren() {
		return children;
	}
	public void setChildren(List<HandoverInStock> children) {
		this.children = children;
	}
	
	public Integer getStockAllCount() {
		if(this.stockAllCount==null)return 0;
		return stockAllCount;
	}
	public void setStockAllCount(Integer stockAllCount) {
		this.stockAllCount = stockAllCount;
	}
	public Integer getStockCount() {
		if(this.stockCount==null) return 0;
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Double getStockAllNum() {
		if(this.stockAllNum==null) return 0d;
		return stockAllNum;
	}
	public void setStockAllNum(Double stockAllNum) {
		this.stockAllNum = stockAllNum;
	}
	public Double getStockAllWeight() {
		if(this.stockAllWeight==null) return 0d;
		return stockAllWeight;
	}
	public void setStockAllWeight(Double stockAllWeight) {
		this.stockAllWeight = stockAllWeight;
	}
	public Double getStockAllGrain() {
		if(this.stockAllGrain==null) return 0d;
		return stockAllGrain;
	}
	public void setStockAllGrain(Double stockAllGrain) {
		this.stockAllGrain = stockAllGrain;
	}
	public Double getStockGrain() {
		if(stockGrain == null) return 0d;
		return stockGrain;
	}
	public void setStockGrain(Double stockGrain) {
		this.stockGrain = stockGrain;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Double getNum() {
		if(num == null) return 0d;
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public Double getNoNum() {
		if(noNum == null) return 0d;
		return noNum;
	}
	public void setNoNum(Double noNum) {
		this.noNum = noNum;
	}
	public Double getStockNum() {
		if(stockNum == null) return 0d;
		return stockNum;
	}
	public void setStockNum(Double stockNum) {
		this.stockNum = stockNum;
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
	public Double getStockWeight() {
		if(stockWeight == null) return 0d;
		return stockWeight;
	}
	public void setStockWeight(Double stockWeight) {
		this.stockWeight = stockWeight;
	}
	
}
