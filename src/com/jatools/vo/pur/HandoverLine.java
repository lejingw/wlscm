package com.jatools.vo.pur;

import java.util.List;

/**
 * 交接单行表对象
 * @author ren.ming
 *
 */
public class HandoverLine {

	private String lineid	; //	n			lineid
	private String billid	; //	n			单据id
	private String itemClassId	; //	y			大类id
	private String unitNo	; //(50)	y			交接计量
	private String noNumNow	; //	y			当前不合格数量
	private String noNumNowOld;              // 原 当前不合格数量
	private String hangNum	; //	y			交接数量
	private String handCharge	; //	y			交接工费
	private String handMoney	; //	y			交接金额
	private String noNumLast	; //	y			前次不合格数量
	private String noNumLastOld	; //	y			原 前次不合格数量
	private String yesMoney	; //	y			合格金额
	private String diffNum	; //	y			磅差数量
	private String diffMoney	; //	y			磅差金额
	private String yesNum	; //	y			合格数量
	private String isIniv	; //	y			0否 1是
	private String status	; //	y			状态
	private String memo	; //(1000)	y			备注
	private String createDate	; //(20)	y			创建时间
	private String createId	; //	y			创建人员
	private String updateDate	; //(20)	y			最后修改时间
	private String updateId	; //	y			最后修改人员
	private String hangPrice; // 单价
	private String goldPrice;
	private String childList;
	private String deleteChildIds;
	private List<HandoverChild> children;
	
	private String lastHangNum;
	private String oldItemClassId;
	
	public String getOldItemClassId() {
		return oldItemClassId;
	}
	public void setOldItemClassId(String oldItemClassId) {
		this.oldItemClassId = oldItemClassId;
	}
	public String getLastHangNum() {
		return lastHangNum;
	}
	public void setLastHangNum(String lastHangNum) {
		this.lastHangNum = lastHangNum;
	}
	public String getDeleteChildIds() {
		return deleteChildIds;
	}
	public void setDeleteChildIds(String deleteChildIds) {
		this.deleteChildIds = deleteChildIds;
	}
	public List<HandoverChild> getChildren() {
		return children;
	}
	public void setChildren(List<HandoverChild> children) {
		this.children = children;
	}
	public String getGoldPrice() {
		return goldPrice;
	}
	public void setGoldPrice(String goldPrice) {
		this.goldPrice = goldPrice;
	}
	public String getHangPrice() {
		return hangPrice;
	}
	public void setHangPrice(String hangPrice) {
		this.hangPrice = hangPrice;
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
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	
	public String getHangNum() {
		return hangNum;
	}
	public void setHangNum(String hangNum) {
		this.hangNum = hangNum;
	}
	public String getHandCharge() {
		return handCharge;
	}
	public void setHandCharge(String handCharge) {
		this.handCharge = handCharge;
	}
	public String getHandMoney() {
		return handMoney;
	}
	public void setHandMoney(String handMoney) {
		this.handMoney = handMoney;
	}
	
	public String getNoNumNow() {
		return noNumNow;
	}
	public void setNoNumNow(String noNumNow) {
		this.noNumNow = noNumNow;
	}
	public String getNoNumLast() {
		return noNumLast;
	}
	public void setNoNumLast(String noNumLast) {
		this.noNumLast = noNumLast;
	}
	public String getYesMoney() {
		return yesMoney;
	}
	public void setYesMoney(String yesMoney) {
		this.yesMoney = yesMoney;
	}
	public String getDiffNum() {
		return diffNum;
	}
	public void setDiffNum(String diffNum) {
		this.diffNum = diffNum;
	}
	public String getDiffMoney() {
		return diffMoney;
	}
	public void setDiffMoney(String diffMoney) {
		this.diffMoney = diffMoney;
	}
	public String getYesNum() {
		return yesNum;
	}
	public void setYesNum(String yesNum) {
		this.yesNum = yesNum;
	}
	public String getIsIniv() {
		return isIniv;
	}
	public void setIsIniv(String isIniv) {
		this.isIniv = isIniv;
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
	public String getNoNumNowOld() {
		return noNumNowOld;
	}
	public void setNoNumNowOld(String noNumNowOld) {
		this.noNumNowOld = noNumNowOld;
	}
	public String getNoNumLastOld() {
		return noNumLastOld;
	}
	public void setNoNumLastOld(String noNumLastOld) {
		this.noNumLastOld = noNumLastOld;
	}
	public String getChildList() {
		return childList;
	}
	public void setChildList(String childList) {
		this.childList = childList;
	}
	
}
