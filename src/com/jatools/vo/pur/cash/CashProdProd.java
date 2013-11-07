package com.jatools.vo.pur.cash;

import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.web.util.StringUtil;

public class CashProdProd implements ReviewBill{

	private String chaId	;//	n			
	private String billNo	;//(20)	y			单据编号
	private String billDate	;//(20)	y			单据时间
	private String orgId	;//	y			采购组织
	private String voderId	;//	y			供应商
	private String dotype	;//	y			0期初数据1调整单据
	private String prId	;//	y			原台帐记录id
	private String materialTypeOld	;//(20)	y			原原料类型
	private String materialTypeNameOld;
	private String itemClassIdOld	;//	y			原大类id
	private String itemClassNameOld;
	private Double weightOld	;//	y			原重量
	private Double priceOld	;//	y			原单价
	private String materialTypeNew	;//	y			新原料类型
	private String materialTypeNameNew;
	private String itemClassIdNew	;//	y			新大类id
	private Double weightNew	;//	y			新重量
	private Double priceNew	;//	y			新单价
	private Double diffMoney	;//	y			差额
	private String status	;//	y			状态
	private String memo	;//(1000)	y			备注
	private String createDate	;//(20)	y			创建时间
	private String createId	;//	y			创建人员
	private String updateDate	;//(20)	y			最后修改时间
	private String updateId	;//	y			最后修改人员
	
	
	public String getItemClassNameOld() {
		return itemClassNameOld;
	}
	public void setItemClassNameOld(String itemClassNameOld) {
		this.itemClassNameOld = itemClassNameOld;
	}
	public String getMaterialTypeNameOld() {
		return materialTypeNameOld;
	}
	public void setMaterialTypeNameOld(String materialTypeNameOld) {
		this.materialTypeNameOld = materialTypeNameOld;
	}
	public String getMaterialTypeNameNew() {
		return materialTypeNameNew;
	}
	public void setMaterialTypeNameNew(String materialTypeNameNew) {
		this.materialTypeNameNew = materialTypeNameNew;
	}
	public String getChaId() {
		return chaId;
	}
	public void setChaId(String chaId) {
		this.chaId = chaId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getVoderId() {
		return voderId;
	}
	public void setVoderId(String voderId) {
		this.voderId = voderId;
	}
	public String getDotype() {
		return dotype;
	}
	public void setDotype(String dotype) {
		this.dotype = dotype;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getMaterialTypeOld() {
		return materialTypeOld;
	}
	public void setMaterialTypeOld(String materialTypeOld) {
		this.materialTypeOld = materialTypeOld;
	}
	public String getItemClassIdOld() {
		return itemClassIdOld;
	}
	public void setItemClassIdOld(String itemClassIdOld) {
		this.itemClassIdOld = itemClassIdOld;
	}
	public Double getWeightOld() {
		return weightOld;
	}
	public void setWeightOld(Double weightOld) {
		this.weightOld = weightOld;
	}
	public Double getPriceOld() {
		return priceOld;
	}
	public void setPriceOld(Double priceOld) {
		this.priceOld = priceOld;
	}
	public String getMaterialTypeNew() {
		return materialTypeNew;
	}
	public void setMaterialTypeNew(String materialTypeNew) {
		this.materialTypeNew = materialTypeNew;
	}
	public String getItemClassIdNew() {
		return itemClassIdNew;
	}
	public void setItemClassIdNew(String itemClassIdNew) {
		this.itemClassIdNew = itemClassIdNew;
	}
	public Double getWeightNew() {
		return weightNew;
	}
	public void setWeightNew(Double weightNew) {
		this.weightNew = weightNew;
	}
	public Double getPriceNew() {
		return priceNew;
	}
	public void setPriceNew(Double priceNew) {
		this.priceNew = priceNew;
	}
	public Double getDiffMoney() {
		return diffMoney;
	}
	public void setDiffMoney(Double diffMoney) {
		this.diffMoney = diffMoney;
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
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_LIAOZHUANLIAO;
	}
	@Override
	public String getBillid() {
		return this.chaId;
	}
	@Override
	public String getBillno() {
		return this.billNo;
	}
	@Override
	public String getPageUrl() {
		return StringUtil.getContextPath() + "/pur/cash/prodProd.vm?user_action=toEdit&chaId="+this.chaId;
	}
	@Override
	public String getBeanName() {
		return "cashProdProdDao";
	}
	
}
