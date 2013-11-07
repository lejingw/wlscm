package com.jatools.vo.inout;

public class ReceiveLine {
	private String lineid	;//	lineid
	private String billid	;//	单据id
	private String handNo	;//	交接单号
	private String itemClassId	;//	大类
	private String handUnit	;//	交接计量
	private Double handNum	;//	交接数量
	private Double price	;//	单价
	private Double moneys	;//	金额
	private Double tax	;//	税率
	private Double taxMoney	;//	税额
	private Double allMoney	;//	价税合计
	private String status	;//	状态
	private String createDate	;//	创建时间
	private String createId	;//	创建人员
	private String updateDate	;//	最后修改时间
	private String updateId	;//	最后修改人员
	private String memo;
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getHandNo() {
		return handNo;
	}
	public void setHandNo(String handNo) {
		this.handNo = handNo;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getHandUnit() {
		return handUnit;
	}
	public void setHandUnit(String handUnit) {
		this.handUnit = handUnit;
	}
	public Double getHandNum() {
		return handNum;
	}
	public void setHandNum(Double handNum) {
		this.handNum = handNum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMoneys() {
		return moneys;
	}
	public void setMoneys(Double moneys) {
		this.moneys = moneys;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getTaxMoney() {
		return taxMoney;
	}
	public void setTaxMoney(Double taxMoney) {
		this.taxMoney = taxMoney;
	}
	public Double getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(Double allMoney) {
		this.allMoney = allMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	
}
