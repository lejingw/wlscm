package com.jatools.vo.move;

import com.jatools.common.Global;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.web.cache.OrgCache;

public class MoveBillHead implements ReviewBill {
	private String headid;
	private String billno;
	private String dodate;
	private String outOrgId;
	private String inOrgId;
	private String sumCount;//件数合计
	private String sumWeight;//重量合计
	private String memo;
	private String createDate;
	private String createId;
	private String updateDate;
	private String updateId;
	private String status;
	
	
	
	
	
	private String srcBillCode;
	private String srcBillId;
	private String srcBillNo;
	private String estimateId;
	private String redEstimateId;
	private String estimateNo;
	private String redEstimateNo;
	private String outStockId;
	private String inStockId;
	private String receiveDate;
	private String receiveEmpId;
	private String sumGrains;//粒数合计
	private String sumCost;//成本合计
	private String sumMoney;//金额合计
	private String sumNewPosCost;//新总成本
	private String moveType;//调拔类型 1手工调拔 2配货调拨 2退货调拨  3退残调拨
	private String receCount;
	private String discardCount;
	private String noreceCount;
	private String printLable;
	private String isCheck;
	private String isFlag;
	private String checkDate;
	private String checkId;
	private String billType;
	private String outGroup;
	private String inGroup;
	private String backIn48Flag;//退货单使用 是否48小时退货
	private String packNo;
	
	private String inOrgName;
	private String certCount;
	private String labelApplyFlag;
	
	private String jmFlag;
	private String licenseCompleteFlag;
	
	public void setValues(){
		inOrgName = OrgCache.getInstance().getNameById(inOrgId);
	}
	public String getCertCount() {
		return certCount;
	}
	public void setCertCount(String certCount) {
		this.certCount = certCount;
	}
	public String getInOrgName() {
		return inOrgName;
	}
	public void setInOrgName(String inOrgName) {
		this.inOrgName = inOrgName;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getJmFlag() {
		return jmFlag;
	}
	public void setJmFlag(String jmFlag) {
		this.jmFlag = jmFlag;
	}
	public String getLicenseCompleteFlag() {
		return licenseCompleteFlag;
	}
	public void setLicenseCompleteFlag(String licenseCompleteFlag) {
		this.licenseCompleteFlag = licenseCompleteFlag;
	}
	public String getOutGroup() {
		return outGroup;
	}
	public void setOutGroup(String outGroup) {
		this.outGroup = outGroup;
	}
	public String getInGroup() {
		return inGroup;
	}
	public void setInGroup(String inGroup) {
		this.inGroup = inGroup;
	}
	public String getHeadid() {
		return headid;
	}
	public void setHeadid(String headid) {
		this.headid = headid;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getSrcBillCode() {
		return srcBillCode;
	}
	public void setSrcBillCode(String srcBillCode) {
		this.srcBillCode = srcBillCode;
	}
	public String getSrcBillId() {
		return srcBillId;
	}
	public void setSrcBillId(String srcBillId) {
		this.srcBillId = srcBillId;
	}
	public String getSrcBillNo() {
		return srcBillNo;
	}
	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	public String getDodate() {
		return dodate;
	}
	public void setDodate(String dodate) {
		this.dodate = dodate;
	}
	public String getOutOrgId() {
		return outOrgId;
	}
	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}
	public String getOutStockId() {
		return outStockId;
	}
	public void setOutStockId(String outStockId) {
		this.outStockId = outStockId;
	}
	public String getInOrgId() {
		return inOrgId;
	}
	public void setInOrgId(String inOrgId) {
		this.inOrgId = inOrgId;
	}
	public String getInStockId() {
		return inStockId;
	}
	public void setInStockId(String inStockId) {
		this.inStockId = inStockId;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReceiveEmpId() {
		return receiveEmpId;
	}
	public void setReceiveEmpId(String receiveEmpId) {
		this.receiveEmpId = receiveEmpId;
	}
	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}
	public String getSumWeight() {
		return sumWeight;
	}
	public void setSumWeight(String sumWeight) {
		this.sumWeight = sumWeight;
	}
	public String getSumGrains() {
		return sumGrains;
	}
	public void setSumGrains(String sumGrains) {
		this.sumGrains = sumGrains;
	}
	public String getSumCost() {
		return sumCost;
	}
	public void setSumCost(String sumCost) {
		this.sumCost = sumCost;
	}
	public String getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getSumNewPosCost() {
		return sumNewPosCost;
	}
	public void setSumNewPosCost(String sumNewPosCost) {
		this.sumNewPosCost = sumNewPosCost;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	public String getNoreceCount() {
		return noreceCount;
	}
	public void setNoreceCount(String noreceCount) {
		this.noreceCount = noreceCount;
	}
	public String getReceCount() {
		return receCount;
	}
	public void setReceCount(String receCount) {
		this.receCount = receCount;
	}
	public String getDiscardCount() {
		return discardCount;
	}
	public void setDiscardCount(String discardCount) {
		this.discardCount = discardCount;
	}
	public String getPrintLable() {
		return printLable;
	}
	public void setPrintLable(String printLable) {
		this.printLable = printLable;
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
	public String getBackIn48Flag() {
		return backIn48Flag;
	}
	public void setBackIn48Flag(String backIn48Flag) {
		this.backIn48Flag = backIn48Flag;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getLabelApplyFlag() {
		return labelApplyFlag;
	}
	public void setLabelApplyFlag(String labelApplyFlag) {
		this.labelApplyFlag = labelApplyFlag;
	}
	public String getEstimateId() {
		return estimateId;
	}
	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
	}
	public String getRedEstimateId() {
		return redEstimateId;
	}
	public void setRedEstimateId(String redEstimateId) {
		this.redEstimateId = redEstimateId;
	}
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	public String getRedEstimateNo() {
		return redEstimateNo;
	}
	public void setRedEstimateNo(String redEstimateNo) {
		this.redEstimateNo = redEstimateNo;
	}
	@Override
	public String getBillCode() {
		if(DictConstant.YES_OR_NO_YES.equals(this.jmFlag)){
			if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_DIAOBODAN_JM;
			else if(DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_TUIHUODAN_JM;
			else if(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_TUICANDAN_JM;
			else if(DictConstant.MOVE_BILL_TYPE_YIKUDAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_YIKUDAN_JM;
			else if(DictConstant.MOVE_BILL_TYPE_GUIZUDIAOBODAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_GUIZUDIAOBODAN_JM;
		}else{
			if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_DIAOBODAN;
			else if(DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_TUIHUODAN;
			else if(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_TUICANDAN;
			else if(DictConstant.MOVE_BILL_TYPE_YIKUDAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_YIKUDAN;
			else if(DictConstant.MOVE_BILL_TYPE_GUIZUDIAOBODAN.equals(getBillType()))
				return GlobalConstant.BILL_CODE_GUIZUDIAOBODAN;
		}
		return null;
	}
	@Override
	public String getBillid() {
		return headid;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT  + "/move/moveBill.vm?user_action=toEdit&jmFlag=" + this.jmFlag + "&headid=" + headid;
	}
	@Override
	public String getBeanName() {
		return "moveBillDao";
	}
	@Override
	public String getOrgId(){
		return this.outOrgId;
	}
	
	private String oldSrcBillId;

	public String getOldSrcBillId() {
		return oldSrcBillId;
	}
	public void setOldSrcBillId(String oldSrcBillId) {
		this.oldSrcBillId = oldSrcBillId;
	}
	
}
