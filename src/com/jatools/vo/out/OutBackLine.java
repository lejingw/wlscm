package  com.jatools.vo.out;

import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;

public class OutBackLine {
    private String lineId;
    private String headId;
    private String itemClassId;
    private String ornaClassId;
    private String styleId;
	private String styleItemClass;
	private String styleMiddleClass;
	private String styleOrnaClass;
    private String alaysisId;
    private String ornaCode;
    private String ornaBarCode;
    private String ornaDsc;
    private String unitId;
    private String nowQty;
    private String allQty;
    private String grains;
    private String mainWeight;
    private String partWeight;
    private String isMutiPart;
    private String isDblLabel;
    private String posCost;
    private String posMoney;
    private String isPSale="0";
    private String isBack="0";
    private String status="1";
    private String memo;
    private String createDate;
    private String createId;
    private String updateDate;
    private String updateId;
    private String orgId;
    private String billNo;
	private String billType;
	private String transCost;
	private String transMoney;
	private String transQty;
    private String backDate;
    private String backBody;
	private String styleName;// 款式名称
	private String alaysisCode;// 分析范围id
	private String finaceType;//核算类型
	private String stockId;
	private String bigGraph;
	
	public String getBigGraph() {
		return bigGraph;
	}
	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getOrnaClassName() {
		return OrnaClassCache.getInstance().getOrnaClass(ornaClassId);
	}
	public String getItemClassName() {
		return ItemClassCache.getInstance().getItemClassDesc(itemClassId);
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getOrnaClassId() {
		return ornaClassId;
	}
	public void setOrnaClassId(String ornaClassId) {
		this.ornaClassId = ornaClassId;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getAlaysisId() {
		return alaysisId;
	}
	public void setAlaysisId(String alaysisId) {
		this.alaysisId = alaysisId;
	}
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getOrnaBarCode() {
		return ornaBarCode;
	}
	public void setOrnaBarCode(String ornaBarcode) {
		this.ornaBarCode = ornaBarcode;
	}
	public String getOrnaDsc() {
		return ornaDsc;
	}
	public void setOrnaDsc(String ornaDsc) {
		this.ornaDsc = ornaDsc;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getNowQty() {
		return nowQty;
	}
	public void setNowQty(String nowQty) {
		this.nowQty = nowQty;
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
	public String getMainWeight() {
		return mainWeight;
	}
	public void setMainWeight(String mainWeight) {
		this.mainWeight = mainWeight;
	}
	public String getPartWeight() {
		return partWeight;
	}
	public void setPartWeight(String partWeight) {
		this.partWeight = partWeight;
	}
	public String getIsMutiPart() {
		return isMutiPart;
	}
	public void setIsMutiPart(String isMutiPart) {
		this.isMutiPart = isMutiPart;
	}
	public String getIsDblLabel() {
		return isDblLabel;
	}
	public void setIsDblLabel(String isDblLabel) {
		this.isDblLabel = isDblLabel;
	}
	public String getPosCost() {
		return posCost;
	}
	public void setPosCost(String posCost) {
		this.posCost = posCost;
	}
	public String getPosMoney() {
		return posMoney;
	}
	public void setPosMoney(String posMoney) {
		this.posMoney = posMoney;
	}
	public String getIsPSale() {
		return isPSale;
	}
	public void setIsPSale(String isPSale) {
		this.isPSale = isPSale;
	}
	public String getIsBack() {
		return isBack;
	}
	public void setIsBack(String isBack) {
		this.isBack = isBack;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getStyleItemClass() {
		return styleItemClass;
	}
	public void setStyleItemClass(String styleItemClass) {
		this.styleItemClass = styleItemClass;
	}
	public String getStyleMiddleClass() {
		return styleMiddleClass;
	}
	public void setStyleMiddleClass(String styleMiddleClass) {
		this.styleMiddleClass = styleMiddleClass;
	}
	public String getStyleOrnaClass() {
		return styleOrnaClass;
	}
	public void setStyleOrnaClass(String styleOrnaClass) {
		this.styleOrnaClass = styleOrnaClass;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getTransCost() {
		return transCost;
	}
	public void setTransCost(String transCost) {
		this.transCost = transCost;
	}
	public String getTransMoney() {
		return transMoney;
	}
	public void setTransMoney(String transMoney) {
		this.transMoney = transMoney;
	}
	public String getTransQty() {
		return transQty;
	}
	public void setTransQty(String transQty) {
		this.transQty = transQty;
	}
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	public String getBackBody() {
		return backBody;
	}
	public void setBackBody(String backBody) {
		this.backBody = backBody;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getAlaysisCode() {
		return alaysisCode;
	}
	public void setAlaysisCode(String alaysisCode) {
		this.alaysisCode = alaysisCode;
	}
	public String getFinaceType() {
		return finaceType;
	}
	public void setFinaceType(String finaceType) {
		this.finaceType = finaceType;
	}
   
}