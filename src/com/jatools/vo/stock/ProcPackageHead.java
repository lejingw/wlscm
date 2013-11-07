package com.jatools.vo.stock;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;



/**
 * 拆包混包单头表
 * @author ren.ming
 * Created 2011-11-20
 */
public class ProcPackageHead implements ReviewBill {
	/**
	 * 单据id
	 */
	private String billid;	// number	n			单据id
	/**
	 * 单据号
	 */
	private String billno;	// varchar2(50)	n			单据号
	/**
	 * 业务时间
	 */
	private String dodate;	// varchar2(20)	n			业务时间
	/**
	 * 组织
	 */
	private String orgId;	// number	n			组织
	/**
	 * 仓库
	 */
	private String stockId;	// number	y			仓库
	/**
	 * 业务类型 0 拆包  1 混包 2原料分割
	 */
	private String dotype;	 // number	y			业务类型 0 拆包  1 混包 2原料分割
	/**
	 * 作废标志
	 */
	private String invalid;	 // number	y	0		作废标志
	/**
	 * 原总重量
	 */
	private String oldWeight;	// number	y			总重量
	/**
	 * 原总粒数
	 */
	private String oldGrains;	// number	y			总粒数
	/**
	 * 原总成本
	 */
	private String oldCost;	// number	y	
	/**
	 * 新重量
	 */
	private String newWeight; //	number	y
	/**
	 * 新总粒数
	 */
	private String newGrains; // number	y			总金额
	/**
	 * 新总成本
	 */
	private String newCost; //	number	y			总成本
	/**
	 * 状态
	 */
	private String status;	// number	y	0		状态
	/**
	 * 财务复核标志
	 */
	private String isCheck;//	number	y	0		财务复核标志
	/**
	 * 核算接口标志
	 */
	private String isFlag; //	number	y	0		核算接口标志
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
	/**
	 * 审核时间
	 */
	private String checkDate;//	varchar2(20)	y			审核时间
	/**
	 * 审核人员
	 */
	private String checkId;//	number	y			审核人员
	/**
	 * 撤单时间
	 */
	private String cancelDate;//	varchar2(20)	y			撤单时间
	/**
	 * 撤单人员
	 */
	private String cancelId;//	number	y			撤单人员
	
	private String needChecked;
	
	public String getNeedChecked() {
		return needChecked;
	}
	public void setNeedChecked(String needChecked) {
		this.needChecked = needChecked;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getDodate() {
		return dodate;
	}
	public void setDodate(String dodate) {
		this.dodate = dodate;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getDotype() {
		return dotype;
	}
	public void setDotype(String dotype) {
		this.dotype = dotype;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	public String getOldWeight() {
		return oldWeight;
	}
	public void setOldWeight(String oldWeight) {
		this.oldWeight = oldWeight;
	}
	public String getOldGrains() {
		return oldGrains;
	}
	public void setOldGrains(String oldGrains) {
		this.oldGrains = oldGrains;
	}
	public String getOldCost() {
		return oldCost;
	}
	public void setOldCost(String oldCost) {
		this.oldCost = oldCost;
	}
	public String getNewWeight() {
		return newWeight;
	}
	public void setNewWeight(String newWeight) {
		this.newWeight = newWeight;
	}
	public String getNewGrains() {
		return newGrains;
	}
	public void setNewGrains(String newGrains) {
		this.newGrains = newGrains;
	}
	public String getNewCost() {
		return newCost;
	}
	public void setNewCost(String newCost) {
		this.newCost = newCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public String getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
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
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getCancelId() {
		return cancelId;
	}
	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_CHAIHUNBAO;//拆混包
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/stock/procPackage.vm?user_action=toEdit&billid=" + billid;
	}
	@Override
	public String getBeanName(){
		return "procPackageHeadDao";
	}
	
	/*private List<ProcPackageOldline> oldLines;
	private List<ProcPackageNewline> newLines;
	
	public List<ProcPackageOldline> getOldLines() {
		return oldLines;
	}
	public void setOldLines(List<ProcPackageOldline> oldLines) {
		this.oldLines = oldLines;
	}
	public List<ProcPackageNewline> getNewLines() {
		return newLines;
	}
	public void setNewLines(List<ProcPackageNewline> newLines) {
		this.newLines = newLines;
	}
	
	public void addOldLine(ProcPackageOldline oldLine) {
		if(this.oldLines == null) {
			this.oldLines = new ArrayList<ProcPackageOldline>();
		}
		this.oldLines.add(oldLine);
	}
	public void addNewLine (ProcPackageNewline newLine) {
		if (this.newLines == null) {
			this.newLines = new ArrayList<ProcPackageNewline>();
		}
		this.newLines.add(newLine);
	}*/
}
