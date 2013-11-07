package com.jatools.vo.stock;

import com.jatools.common.constant.DictConstant;
import com.jatools.web.cache.*;

/**
 * 现有量对象的子类 主要是处理一些id转化为其名称
 * @author ren.ming
 * Created 2011-11-25
 */
public class PackageMaterActive extends MaterActive {

	private String alaysisName;// 分析范围
	private String itemClassName;// 大类名称
	private String ornaClassName;// 小类名称
	private String styleName;// 款式名称
	private String saleUnitName;
	private String colorName;
	private String mainShapname;
	private String bigGraph;
	
	public String getBigGraph() {
		return bigGraph;
	}
	public void setBigGraph(String bigGraph) {
		this.bigGraph = bigGraph;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getMainShapname() {
		return mainShapname;
	}
	public void setMainShapname(String mainShapname) {
		this.mainShapname = mainShapname;
	}
	public String getAlaysisName() {
		return alaysisName;
	}
	public void setAlaysisName(String alaysisName) {
		this.alaysisName = alaysisName;
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
	public String getOrnaClassName() {
		return ornaClassName;
	}
	public void setOrnaClassName(String ornaClassName) {
		this.ornaClassName = ornaClassName;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getSaleUnitName() {
		return saleUnitName;
	}
	public void setSaleUnitName(String saleUnitName) {
		this.saleUnitName = saleUnitName;
	}
	
	private String styleitemclass  ;
	private String stylemiddleclass;
	private String styleornaclass  ;

	public String getStyleitemclass() {
		return styleitemclass;
	}
	public void setStyleitemclass(String styleitemclass) {
		this.styleitemclass = styleitemclass;
	}
	public String getStylemiddleclass() {
		return stylemiddleclass;
	}
	public void setStylemiddleclass(String stylemiddleclass) {
		this.stylemiddleclass = stylemiddleclass;
	}
	public String getStyleornaclass() {
		return styleornaclass;
	}
	public void setStyleornaclass(String styleornaclass) {
		this.styleornaclass = styleornaclass;
	}
	
	public String getStyleitemclassName() {
		return StyleItemClassCache.getInstance().getNameById(styleitemclass);
	}
	public String getStylemiddleclassName() {
		return StyleMiddleClassCache.getInstance().getNameById(stylemiddleclass);
	}
	public String getStyleornaclassName() {
		return StyleOrnaClassCache.getInstance().getNameById(styleornaclass);
	}

    private String outFlag;

    public String getOutFlag() {
        return outFlag;
    }

    public void setOutFlag(String outFlag) {
        this.outFlag = outFlag;
    }

    public String getOrgName(){
        return OrgCache.getInstance().getOrgName(this.getOrgId());
    }

    public String getOutFlagName(){
        return DictCache.getInstance().getValue(DictConstant.YES_OR_NO, this.outFlag);
    }
}
