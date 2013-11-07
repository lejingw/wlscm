package  com.jatools.vo.out;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.web.util.StringUtil;

public class OutStockHead implements ReviewBill {
    private String billid;
    private String billno;
    private String dodate;
    private String orgId;
    private String stockId;
    private String dotype;
    private String body;
    private String payFee;
    private String invalid;
    private String sumCount;
    private String sumWeight;
    private String sumGrains;
    private String sumCost;
    private String sumMoney;
    private String status;
    private String isCheck;
    private String isFlag;
    private String memo;
    private String createDate;
    private String createId;
    private String updateDate;
    private String updateId;
    private String checkDate;
    private String checkId;
    private String cancelDate;
    private String cancelId;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPayFee() {
		return payFee;
	}
	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
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
		if (StringUtil.equals("0", dotype)) {
			return GlobalConstant.BILL_CODE_DIUSHICHUKU;
		} else if (StringUtil.equals("1", dotype)) {
			return GlobalConstant.BILL_CODE_BAOFEICHUKU;
		} else if (StringUtil.equals("2", dotype)) {
			return GlobalConstant.BILL_CODE_LIPINCHUKU;
		}else if(StringUtil.equals("3", dotype)){
			return GlobalConstant.BILL_CODE_YINGXIAOCHUKU;
		}else if(StringUtil.equals("4", dotype)){
			return GlobalConstant.BILL_CODE_YUJINLINGYONG;
		}
		return null;
	}
	@Override
	public String getPageUrl() {
		if (StringUtil.equals("0", dotype)) {
			return Global.CONTEXT  + "/out/loseOutStock.vm?user_action=toEdit&headid=" + billid;
		} else if (StringUtil.equals("1", dotype)) {
			return Global.CONTEXT  + "/out/scrapOutStock.vm?user_action=toEdit&headid=" + billid;
		} else if (StringUtil.equals("2", dotype)) {
			return Global.CONTEXT  + "/out/giftOutStock.vm?user_action=toEdit&headid=" + billid;
		} else if (StringUtil.equals("3", dotype)) {
			return Global.CONTEXT  + "/out/marketOutStock.vm?user_action=toEdit&headid=" + billid;
		}else if (StringUtil.equals("4", dotype)) {
			return Global.CONTEXT  + "/out/leftGoldOutStock.vm?user_action=toEdit&headid=" + billid;
		}
		return null;
	}
	@Override
	public String getBeanName() {
		return "outStockDao";
	}
    
}