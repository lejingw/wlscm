package com.jatools.vo.stock;

/**
 * 供拆混包 入库使用
 * @author ren.ming
 * Created 2011-11-28
 */
public class MaterInStock {

	private String ornaCode;
	private String ornaBarcode;
	private String stockId;
	private String allQty;
	private String nowQty;
	private String alaysisId;
	private String state;
	private String stoneTotalNum;
	private String stoneNowNum;
	private String oldOrnaCode;
	private String realTotalCost;// 真实总成本
	private String updateDate;
	private String updateTime;
	
	private String qltyId;// 是否是磅差
	
	private String inivCost;
	
	public String getInivCost() {
		return inivCost;
	}
	public void setInivCost(String inivCost) {
		this.inivCost = inivCost;
	}
	public String getQltyId() {
		return qltyId;
	}
	public void setQltyId(String qltyId) {
		this.qltyId = qltyId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getNowQty() {
		return nowQty;
	}
	public void setNowQty(String nowQty) {
		this.nowQty = nowQty;
	}
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getOrnaBarcode() {
		return ornaBarcode;
	}
	public void setOrnaBarcode(String ornaBarcode) {
		this.ornaBarcode = ornaBarcode;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getAllQty() {
		return allQty;
	}
	public void setAllQty(String allQty) {
		this.allQty = allQty;
	}
	public String getAlaysisId() {
		return alaysisId;
	}
	public void setAlaysisId(String alaysisId) {
		this.alaysisId = alaysisId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStoneTotalNum() {
		return stoneTotalNum;
	}
	public void setStoneTotalNum(String stoneTotalNum) {
		this.stoneTotalNum = stoneTotalNum;
	}
	public String getStoneNowNum() {
		return stoneNowNum;
	}
	public void setStoneNowNum(String stoneNowNum) {
		this.stoneNowNum = stoneNowNum;
	}
	public String getOldOrnaCode() {
		return oldOrnaCode;
	}
	public void setOldOrnaCode(String oldOrnaCode) {
		this.oldOrnaCode = oldOrnaCode;
	}
	public String getRealTotalCost() {
		return realTotalCost;
	}
	public void setRealTotalCost(String realTotalCost) {
		this.realTotalCost = realTotalCost;
	}
	
}
