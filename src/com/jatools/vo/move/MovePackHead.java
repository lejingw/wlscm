package com.jatools.vo.move;

import com.jatools.common.Global;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class MovePackHead implements ReviewBill {
	private String headid;
	private String billno;
	private String dodate;
	private String billType;
	private String outOrgId;
	private String inOrgId;
	private String receiveDate;
	private String receiveEmpId;
	private String expressMode;
	private String expressNo;
	private String planEndDate;
	private String grossWeight;
	private String sendOrgId;
	private String sendUserId;
	private String receOrgId;
	private String receUserIds;
	private String sumCount;
	private String sumCost;
	private String memo;
	private String createId;
	private String createDate;
	private String updateId;
	private String updateDate;
	private String checkId;
	private String checkDate;
	private String status;
	
	private String jmFlag;
	private String expressCharge;
	private String expressPremium;
	private String expressMoney;
	private String cashFlag;
	
	private String saleEstimateNo;
	private String estimateNos;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHeadid() {
		return headid;
	}

	public void setHeadid(String headid) {
		this.headid = headid;
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

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getOutOrgId() {
		return outOrgId;
	}

	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}

	public String getInOrgId() {
		return inOrgId;
	}

	public void setInOrgId(String inOrgId) {
		this.inOrgId = inOrgId;
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

	public String getExpressMode() {
		return expressMode;
	}

	public void setExpressMode(String expressMode) {
		this.expressMode = expressMode;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getSendOrgId() {
		return sendOrgId;
	}

	public void setSendOrgId(String sendOrgId) {
		this.sendOrgId = sendOrgId;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getReceOrgId() {
		return receOrgId;
	}

	public void setReceOrgId(String receOrgId) {
		this.receOrgId = receOrgId;
	}

	public String getReceUserIds() {
		return receUserIds;
	}

	public void setReceUserIds(String receUserIds){
		this.receUserIds = receUserIds;
	}

	public String getSumCount() {
		return sumCount;
	}

	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}

	public String getSumCost() {
		return sumCost;
	}

	public void setSumCost(String sumCost) {
		this.sumCost = sumCost;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getJmFlag() {
		return jmFlag;
	}

	public void setJmFlag(String jmFlag) {
		this.jmFlag = jmFlag;
	}

	public String getExpressCharge() {
		return expressCharge;
	}

	public void setExpressCharge(String expressCharge) {
		this.expressCharge = expressCharge;
	}

	public String getExpressPremium() {
		return expressPremium;
	}

	public void setExpressPremium(String expressPremium) {
		this.expressPremium = expressPremium;
	}

	public String getExpressMoney() {
		return expressMoney;
	}

	public void setExpressMoney(String expressMoney) {
		this.expressMoney = expressMoney;
	}

	public String getCashFlag() {
		return cashFlag;
	}

	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}

	public String getSaleEstimateNo() {
		return saleEstimateNo;
	}

	public void setSaleEstimateNo(String saleEstimateNo) {
		this.saleEstimateNo = saleEstimateNo;
	}

	public String getEstimateNos() {
		return estimateNos;
	}

	public void setEstimateNos(String estimateNos) {
		this.estimateNos = estimateNos;
	}

	@Override
	public String getBillCode() {
		if(DictConstant.YES_OR_NO_YES.equals(this.jmFlag)){
			return GlobalConstant.BILL_CODE_ZHUANGXIANG_ORNA_JM;
		}else{
			if(DictConstant.MOVE_PACK_BILL_TYPE_SHIPING.equals(billType))
			return GlobalConstant.BILL_CODE_ZHUANGXIANG_ORNA;
			else if(DictConstant.MOVE_PACK_BILL_TYPE_WEIXIU.equals(billType))
				return GlobalConstant.BILL_CODE_ZHUANGXIANG_WEIXIU;
			else if(DictConstant.MOVE_PACK_BILL_TYPE_JIAOJIE.equals(billType))
				return GlobalConstant.BILL_CODE_ZHUANGXIANG_JIAOJIE;
			else if(DictConstant.MOVE_PACK_BILL_TYPE_XINGTAI.equals(billType))
				return GlobalConstant.BILL_CODE_ZHUANGXIANG_XINGTAI;
			return null;
		}
	}

	@Override
	public String getBillid() {
		return headid;
	}

	@Override
	public String getPageUrl() {
		return Global.CONTEXT + "/move/movePack.vm?user_action=toEdit&jmFlag=" + this.jmFlag + "&headid="
				+ headid;
	}

	@Override
	public String getBeanName() {
		return "movePackDao";
	}

	@Override
	public String getBillno() {
		return billno;
	}

	@Override
	public String getOrgId(){
		return this.outOrgId;
	}
}
