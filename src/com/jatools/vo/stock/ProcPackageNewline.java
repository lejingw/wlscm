package com.jatools.vo.stock;

/**
 * 拆包单新行表
 * @author ren.ming
 * Created 2011-11-20
 */
public class ProcPackageNewline {
	/**
	 * lineid
	 */
	private String lineid;//	number	n			lineid
	/**
	 * 单据id
	 */
	private String billid;//	number	n			单据id
	/**
	 * 商品编码
	 */
	private String ornaCode;//	varchar2(20)	n			商品编码
	/**
	 * 商品条码
	 */
	private String ornaBarcode;//	varchar2(20)	n			商品条码
	/**
	 * 总重量
	 */
	private String allQty;//	number	y			总重量
	/**
	 * 粒数
	 */
	private String grains;//	number	y	0		粒数
	/**
	 * 暂估成本
	 */
	private String posCost;//	number	y			
	/**
	 * 分析范围
	 */
	private String analysisArangeId;
	private String analysisArangeName;
	/**
	 * 状态
	 */
	private String status;//	number	y			状态
	/**
	 * 备注
	 */
	private String memo;//	varchar2(1000)	y			备注
	/**
	 * 创建时间
	 */
	private String createDate;//	varchar2(20)	y			创建时间
	/**
	 * 创建人员
	 */
	private String createId;//	number	y			创建人员
	/**
	 * 最后修改时间
	 */
	private String updateDate;//	varchar2(20)	y			最后修改时间
	/**
	 * 最后修改人员
	 */
	private String updateId;//	number	y			最后修改人员
	
	private String posMoney;
	
	private String poundsDiff;
	
	private String inivCost;
	
	public String getInivCost() {
		return inivCost;
	}
	public void setInivCost(String inivCost) {
		this.inivCost = inivCost;
	}
	public String getPoundsDiff() {
		return poundsDiff;
	}
	public void setPoundsDiff(String poundsDiff) {
		this.poundsDiff = poundsDiff;
	}
	public String getPosMoney() {
		return posMoney;
	}
	public void setPosMoney(String posMoney) {
		this.posMoney = posMoney;
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
	public String getAllQty() {
		return allQty;
	}
	public void setAllQty(String allQty) {
		this.allQty = allQty;
	}
	public String getGrains() {
		return grains;
	}
	public void setGrains(String grains) {
		this.grains = grains;
	}
	public String getPosCost() {
		return posCost;
	}
	public void setPosCost(String posCost) {
		this.posCost = posCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public String getAnalysisArangeId() {
		return analysisArangeId;
	}
	public void setAnalysisArangeId(String analysisArangeId) {
		this.analysisArangeId = analysisArangeId;
	}
	public String getAnalysisArangeName() {
		return analysisArangeName;
	}
	public void setAnalysisArangeName(String analysisArangeName) {
		this.analysisArangeName = analysisArangeName;
	}
	
}
