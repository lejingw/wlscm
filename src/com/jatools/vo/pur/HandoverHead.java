package com.jatools.vo.pur;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

/**
 * 交接单头表对象
 * @author ren.ming
 *
 */
public class HandoverHead implements ReviewBill {

	private String billid	; //	n			单据id
	private String billno	; //(50)	n			单据号
	private String sourceId	; //	y			来源单据id
	private String sourceNo	; //(50)	y			来源单据no
	private String sourceType	; //(50)	y			来源业务类型
	private String dodate	; //(20)	n			业务时间
	private String orgId	; //	n			组织
	private String verdorId	; //	y			供应商
	private String dotype	; //	y			0采购 1委外
	private String invalid	; //	y	0		作废标志
	private String sumNum	; //	y			总成数量
	private String sumMoney	; //	y			总金额
	private String sumCharge	; //	y			总工费
	private String receUser	; //	y			接收人
	private String receDate	; //(20)	y			接收时间
	private String expressNo	; //(20)	y			发货单号
	private String isPack	; //	y			0否 1是
	private String packNo	; //(20)	y			装箱单号
	private String isPsale	; //	y			0否 1是
	private String status	; //	y	0		状态
	private String isCheck	; //	y	0		复核标志
	private String isFlag	; //	y	0		核算接口标志
	private String memo	; //(1000)	y			备注
	private String createDate	; //(20)	y			创建时间
	private String createId	; //	y			创建人员
	private String updateDate	; //(20)	y			最后修改时间
	private String updateId	; //	y			最后修改人员
	private String checkDate	; //(20)	y			审核时间
	private String checkId	; //	y			审核人员
	private String cancelDate	; //(20)	y			撤单时间
	private String cancelId	; //	y			撤单人员
	private String materialId;// 是否是原料
	
	private String isBill;
	
	public String getIsBill() {
		return isBill;
	}
	public void setIsBill(String isBill) {
		this.isBill = isBill;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
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
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
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
	public String getVerdorId() {
		return verdorId;
	}
	public void setVerdorId(String verdorId) {
		this.verdorId = verdorId;
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
	public String getSumNum() {
		return sumNum;
	}
	public void setSumNum(String sumNum) {
		this.sumNum = sumNum;
	}
	public String getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getSumCharge() {
		return sumCharge;
	}
	public void setSumCharge(String sumCharge) {
		this.sumCharge = sumCharge;
	}
	public String getReceUser() {
		return receUser;
	}
	public void setReceUser(String receUser) {
		this.receUser = receUser;
	}
	public String getReceDate() {
		return receDate;
	}
	public void setReceDate(String receDate) {
		this.receDate = receDate;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getIsPack() {
		return isPack;
	}
	public void setIsPack(String isPack) {
		this.isPack = isPack;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getIsPsale() {
		return isPsale;
	}
	public void setIsPsale(String isPsale) {
		this.isPsale = isPsale;
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
		if(GlobalConstant.BILL_CODE_CAIGOUDAN.equals(sourceType)) {
			return GlobalConstant.BILL_CODE_JIAOJIEDAN;
		} else {
			return GlobalConstant.BILL_CODE_JIAOJIEDAN_OTHER;
		}
	}
	@Override
	public String getPageUrl() {
		if(GlobalConstant.BILL_CODE_CAIGOUDAN.equals(sourceType)) {
			return Global.CONTEXT + "/pur/handover.vm?user_action=toEdit&billid=" + billid;
		} else {
			return Global.CONTEXT + "/stock/handover.vm?user_action=toEdit&billid=" + billid;
		}
	}
	@Override
	public String getBeanName() {
		return "handoverDao";
	}
	
}
