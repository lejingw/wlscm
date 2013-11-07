package com.jatools.vo.push;

import com.jatools.vo.BaseVO;
import com.jatools.web.cache.ArticleTypeCache;

public class GatherSetHead extends BaseVO {
	private String headid;
	private String billNo;
	private String orgId;
	private String regionId;
	private String cycleTypeId;
	private String articleTypeIds;
	private String purDateStart;
	private String purDateEnd;
	private String saleDateStart;
	private String saleDateEnd;
	private String purArriveDateEnd;
	private String saleDisRate;
	private String saleTurnRate;
	private String memo;
	
	private String cycleTypeName;
	private String regionName;

    private String isLoveStyle;

    public String getIsLoveStyle() {
        return isLoveStyle;
    }

    public void setIsLoveStyle(String loveStyle) {
        isLoveStyle = loveStyle;
    }

    public String getCycleTypeName() {
		return cycleTypeName;
	}
	public void setCycleTypeName(String cycleTypeName) {
		this.cycleTypeName = cycleTypeName;
	}
	public String getArticleTypeNames() {
		return ArticleTypeCache.getInstance().getArticleTypeName(this.articleTypeIds);
	}
	public String getHeadid() {
		return headid;
	}
	public void setHeadid(String headid) {
		this.headid = headid;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getCycleTypeId() {
		return cycleTypeId;
	}
	public void setCycleTypeId(String cycleTypeId) {
		this.cycleTypeId = cycleTypeId;
	}
	public String getArticleTypeIds() {
		return articleTypeIds;
	}
	public void setArticleTypeIds(String articleTypeIds) {
		this.articleTypeIds = articleTypeIds;
	}
	public String getPurDateStart() {
		return purDateStart;
	}
	public void setPurDateStart(String purDateStart) {
		this.purDateStart = purDateStart;
	}
	public String getPurDateEnd() {
		return purDateEnd;
	}
	public void setPurDateEnd(String purDateEnd) {
		this.purDateEnd = purDateEnd;
	}
	public String getSaleDateStart() {
		return saleDateStart;
	}
	public void setSaleDateStart(String saleDateStart) {
		this.saleDateStart = saleDateStart;
	}
	public String getSaleDateEnd() {
		return saleDateEnd;
	}
	public void setSaleDateEnd(String saleDateEnd) {
		this.saleDateEnd = saleDateEnd;
	}
	public String getPurArriveDateEnd() {
		return purArriveDateEnd;
	}
	public void setPurArriveDateEnd(String purArriveDateEnd) {
		this.purArriveDateEnd = purArriveDateEnd;
	}
	public String getSaleDisRate() {
		return saleDisRate;
	}
	public void setSaleDisRate(String saleDisRate) {
		this.saleDisRate = saleDisRate;
	}
	public String getSaleTurnRate() {
		return saleTurnRate;
	}
	public void setSaleTurnRate(String saleTurnRate) {
		this.saleTurnRate = saleTurnRate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
