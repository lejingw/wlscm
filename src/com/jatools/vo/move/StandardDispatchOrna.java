package com.jatools.vo.move;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.DictCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.cache.UnitCache;

public class StandardDispatchOrna {
	private String ornaCode;// 饰品编码
	private String orgId;
	private String stockId;
	private String materialFlag;
	private String itemClassId;
	private String ornaClassId;
	private String analysisId;
	private String analysisName;
	private String styleItemClassId;//款式大类
	private String styleMiddleClassId;//款式中类
	private String styleOrnaClassId;//款式小类
	private String styleId;//款式
	private String styleName;
	private String bigGraph;
	private String colorGradeId;//色级
	private String cleanId;//净度
	private String colorGradeGradeId;//品质
	private String cleanGradeId;//品质
	private String sizeId;//尺寸
	private String sizeName;
	private String qualityId;//商品材质
	private String bracketcolorId;//托架颜色
	private String unitId;//计量单位
	private String status;

	private String basicPrice;// 基础价
	private String posAmount;//网点金额
	
	public String getStyleItemClassName(){
		return StyleItemClassCache.getInstance().getNameById(this.styleItemClassId);
	}
	public String getStyleMiddleClassName(){
		return StyleMiddleClassCache.getInstance().getNameById(this.styleMiddleClassId);
	}
	public String getStyleOrnaClassName(){
		return StyleOrnaClassCache.getInstance().getNameById(this.styleOrnaClassId);
	}
	public String getColorGradeName(){
		return DictCache.getInstance().getValue(DictConstant.DIA_COLORGRADE, colorGradeId);
	}
	public String getCleanName(){
		return DictCache.getInstance().getValue(DictConstant.DIA_CLEAN, cleanId);
	}
	public String getColorGradeGradeName(){
		return DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, colorGradeGradeId);
	}
	public String getCleanGradeName(){
		return DictCache.getInstance().getValue(DictConstant.GRADE_LEVEL, cleanGradeId);
	}
	public String getQualityName(){
		return QualityCache.getInstance().getNameById(this.qualityId);
	}
	public String getBracketcolorName(){
		return DictCache.getInstance().getValue(DictConstant.BRACKET_COLOR, bracketcolorId);
	}
	public String getUnitName(){
		return UnitCache.getInstance().getNameById(this.unitId);
	}
	
	
	//--------------
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getAnalysisId() {
		return analysisId;
	}
	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}
	public String getAnalysisName() {
		return analysisName;
	}
	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}
	public String getStyleItemClassId() {
		return styleItemClassId;
	}
	public void setStyleItemClassId(String styleItemClassId) {
		this.styleItemClassId = styleItemClassId;
	}
	public String getStyleMiddleClassId() {
		return styleMiddleClassId;
	}
	public void setStyleMiddleClassId(String styleMiddleClassId) {
		this.styleMiddleClassId = styleMiddleClassId;
	}
	public String getStyleOrnaClassId() {
		return styleOrnaClassId;
	}
	public void setStyleOrnaClassId(String styleOrnaClassId) {
		this.styleOrnaClassId = styleOrnaClassId;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getBigGraph() {
		return bigGraph;
	}
	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}
	public String getColorGradeId() {
		return colorGradeId;
	}
	public void setColorGradeId(String colorGradeId) {
		this.colorGradeId = colorGradeId;
	}
	public String getCleanId() {
		return cleanId;
	}
	public void setCleanId(String cleanId) {
		this.cleanId = cleanId;
	}
	public String getColorGradeGradeId() {
		return colorGradeGradeId;
	}
	public void setColorGradeGradeId(String colorGradeGradeId) {
		this.colorGradeGradeId = colorGradeGradeId;
	}
	public String getCleanGradeId() {
		return cleanGradeId;
	}
	public void setCleanGradeId(String cleanGradeId) {
		this.cleanGradeId = cleanGradeId;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getBracketcolorId() {
		return bracketcolorId;
	}
	public void setBracketcolorId(String bracketcolorId) {
		this.bracketcolorId = bracketcolorId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getMaterialFlag() {
		return materialFlag;
	}
	public void setMaterialFlag(String materialFlag) {
		this.materialFlag = materialFlag;
	}
	public String getBasicPrice() {
		return basicPrice;
	}
	public void setBasicPrice(String basicPrice) {
		this.basicPrice = basicPrice;
	}
	public String getPosAmount() {
		return posAmount;
	}
	public void setPosAmount(String posAmount) {
		this.posAmount = posAmount;
	}
}
