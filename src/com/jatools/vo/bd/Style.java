package com.jatools.vo.bd;

public class Style {

	private String styleId ;  // number(8) n   款式主键
	private String stylecode ;  // var;  // char2(40) n   款式编码
	private String styleName ;  // var;  // char2(40) n   款式名称
	private String stylesize ;  // var;  // char2(50) y   款式规格
	private String styletype ;  // var;  // char2(20) y   款式型号
	private String styleno ;  // number(8) y   所属款号
	private String articleLevelId ;  // number(8) y   商品等级
	private String ismotif ;  // char(1) n 0  是否主题    1＝是，0＝否
	private String isassist ;  // char(1) n 0  是否辅计量管理
	private String ismaterial ;  // char(1) n 0  是否物资
	private String iskits ;  // char(1) n 0  是否成套件
	private String istrinket ;  // char(1) n 0  是否饰品
	private String isdiamtrinket ;  // char(1) n 0  是否钻石饰品
	private String issingleitem ;  // char(1) n 0  是否单品管理
	private String issale ;  // char(1) n 0  是否可售
	private String ismoneyclass ;  // char(1) n 0  是否费用类
	private String isfixedasset ;  // char(1) n 0  是否固定资产
	private String islowvalue ;  // char(1) n 0  是否低耗品
	private String isoutsource ;  // char(1) n 0  是否外协
	private String israwmaterial ;  // char(1) n 0  是否原料
	private String ismosaic ;  // char(1) y 0  0无 1镶嵌规则类 2 镶嵌无规则类
	private String isbiggraph ;  // var;  // char2(100) y   大图
	private String issmallgraph ;  // var;  // char2(100) y   小图
	private String period ;  // number(8) y   周期
	private String iscustomize ;  // char(1) y 0  是否可定做
	private String isgeneral ;  // char(1) y 0  是否常规下单
	private String purchaseanalyst ;  // number(8) y   采购计量单位
	private String saleanalyst ;  // number(8) y   销售计量单位
	private String pricinganalyst ;  // number(8) y   计价计量单位
	private String assistanalyst ;  // number(8) y   辅计量单位
	private String retailanalyst ;  // number(8) y   零售计量单位
	private String diamshape ;  // number(8) y   钻石形状
	private String multiplefactor ;  // number(24,6) y   核价倍率系数
	private String iscm ;  // char(1) n 0  是否尺寸管理
	private String isarchive ;  // char(1) n 0  是否封存
	private String note ;  // var;  // char2(500) y   备注
	private String createuserid ;  // number(8) n   创建人
	private String createdate ;  // char(19) n   创建时间
	private String updateuserid ;  // number(8) y   修改人
	private String updatedate ;  // char(19) y   修改时间
	private String mainanalyst ;  // number(8) y   主计量单位
	private String ishalfproduct ;  // char(1) n 0  是否半成品
	private String itemclassid ;  // number(8) y   大类
	private String ornaclassid ;  // number(8) y   小类
	private String styleitemclass ;  // number(8) y   款式大类
	private String stylemiddleclass ;  // number(8) y   款式中类
	private String styleornaclass ;  // number(8) y   款式小类
	private String cpfrtype ;  // char(1) y 0  补货预测类型 1为适应法 0为最大最小法
	private String articletypeid ;  // number(8) y   商品类别
	private String isbracket ;  // char(1) y 0  是否托架段
	private String summaryid ;  // number(8) y   常用摘要id
	private String themeCoefficient ;  // number(24,6) y   主题系数
	private String iscolor ;  // char(1) y 0  是否颜色管理
	private String isexcise ;  // char(1) y 0  是否应交消费税
	private String isbaldric ;  // char(1) y 0  是否佩饰
	private String themeId ;  // number(8) y   主题名称id
	private String issuchinda ;  // char(1) y 0  是否高档素金
	private String zodiac ;  // number(2) y   生肖
	private String isout ;  // char(1) y 0  是否淘汰款 0没有淘汰，1淘汰
	private String isSpecialCharge;//IS_SPECIALCHARGE 是否特殊工费
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleid) {
		this.styleId = styleid;
	}
	public String getStylecode() {
		return stylecode;
	}
	public void setStylecode(String stylecode) {
		this.stylecode = stylecode;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String stylename) {
		this.styleName = stylename;
	}
	public String getStylesize() {
		return stylesize;
	}
	public void setStylesize(String stylesize) {
		this.stylesize = stylesize;
	}
	public String getStyletype() {
		return styletype;
	}
	public void setStyletype(String styletype) {
		this.styletype = styletype;
	}
	public String getStyleno() {
		return styleno;
	}
	public void setStyleno(String styleno) {
		this.styleno = styleno;
	}
	public String getArticleLevelId() {
		return articleLevelId;
	}
	public void setArticleLevelId(String articleLevelId) {
		this.articleLevelId = articleLevelId;
	}
	public String getIsmotif() {
		return ismotif;
	}
	public void setIsmotif(String ismotif) {
		this.ismotif = ismotif;
	}
	public String getIsassist() {
		return isassist;
	}
	public void setIsassist(String isassist) {
		this.isassist = isassist;
	}
	public String getIsmaterial() {
		return ismaterial;
	}
	public void setIsmaterial(String ismaterial) {
		this.ismaterial = ismaterial;
	}
	public String getIskits() {
		return iskits;
	}
	public void setIskits(String iskits) {
		this.iskits = iskits;
	}
	public String getIstrinket() {
		return istrinket;
	}
	public void setIstrinket(String istrinket) {
		this.istrinket = istrinket;
	}
	public String getIsdiamtrinket() {
		return isdiamtrinket;
	}
	public void setIsdiamtrinket(String isdiamtrinket) {
		this.isdiamtrinket = isdiamtrinket;
	}
	public String getIssingleitem() {
		return issingleitem;
	}
	public void setIssingleitem(String issingleitem) {
		this.issingleitem = issingleitem;
	}
	public String getIssale() {
		return issale;
	}
	public void setIssale(String issale) {
		this.issale = issale;
	}
	public String getIsmoneyclass() {
		return ismoneyclass;
	}
	public void setIsmoneyclass(String ismoneyclass) {
		this.ismoneyclass = ismoneyclass;
	}
	public String getIsfixedasset() {
		return isfixedasset;
	}
	public void setIsfixedasset(String isfixedasset) {
		this.isfixedasset = isfixedasset;
	}
	public String getIslowvalue() {
		return islowvalue;
	}
	public void setIslowvalue(String islowvalue) {
		this.islowvalue = islowvalue;
	}
	public String getIsoutsource() {
		return isoutsource;
	}
	public void setIsoutsource(String isoutsource) {
		this.isoutsource = isoutsource;
	}
	public String getIsrawmaterial() {
		return israwmaterial;
	}
	public void setIsrawmaterial(String israwmaterial) {
		this.israwmaterial = israwmaterial;
	}
	public String getIsmosaic() {
		return ismosaic;
	}
	public void setIsmosaic(String ismosaic) {
		this.ismosaic = ismosaic;
	}
	public String getIsbiggraph() {
		return isbiggraph;
	}
	public void setIsbiggraph(String isbiggraph) {
		this.isbiggraph = isbiggraph;
	}
	public String getIssmallgraph() {
		return issmallgraph;
	}
	public void setIssmallgraph(String issmallgraph) {
		this.issmallgraph = issmallgraph;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getIscustomize() {
		return iscustomize;
	}
	public void setIscustomize(String iscustomize) {
		this.iscustomize = iscustomize;
	}
	public String getIsgeneral() {
		return isgeneral;
	}
	public void setIsgeneral(String isgeneral) {
		this.isgeneral = isgeneral;
	}
	public String getPurchaseanalyst() {
		return purchaseanalyst;
	}
	public void setPurchaseanalyst(String purchaseanalyst) {
		this.purchaseanalyst = purchaseanalyst;
	}
	public String getSaleanalyst() {
		return saleanalyst;
	}
	public void setSaleanalyst(String saleanalyst) {
		this.saleanalyst = saleanalyst;
	}
	public String getPricinganalyst() {
		return pricinganalyst;
	}
	public void setPricinganalyst(String pricinganalyst) {
		this.pricinganalyst = pricinganalyst;
	}
	public String getAssistanalyst() {
		return assistanalyst;
	}
	public void setAssistanalyst(String assistanalyst) {
		this.assistanalyst = assistanalyst;
	}
	public String getRetailanalyst() {
		return retailanalyst;
	}
	public void setRetailanalyst(String retailanalyst) {
		this.retailanalyst = retailanalyst;
	}
	public String getDiamshape() {
		return diamshape;
	}
	public void setDiamshape(String diamshape) {
		this.diamshape = diamshape;
	}
	public String getMultiplefactor() {
		return multiplefactor;
	}
	public void setMultiplefactor(String multiplefactor) {
		this.multiplefactor = multiplefactor;
	}
	public String getIscm() {
		return iscm;
	}
	public void setIscm(String iscm) {
		this.iscm = iscm;
	}
	public String getIsarchive() {
		return isarchive;
	}
	public void setIsarchive(String isarchive) {
		this.isarchive = isarchive;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdateuserid() {
		return updateuserid;
	}
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getMainanalyst() {
		return mainanalyst;
	}
	public void setMainanalyst(String mainanalyst) {
		this.mainanalyst = mainanalyst;
	}
	public String getIshalfproduct() {
		return ishalfproduct;
	}
	public void setIshalfproduct(String ishalfproduct) {
		this.ishalfproduct = ishalfproduct;
	}
	public String getItemclassid() {
		return itemclassid;
	}
	public void setItemclassid(String itemclassid) {
		this.itemclassid = itemclassid;
	}
	public String getOrnaclassid() {
		return ornaclassid;
	}
	public void setOrnaclassid(String ornaclassid) {
		this.ornaclassid = ornaclassid;
	}
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
	public String getCpfrtype() {
		return cpfrtype;
	}
	public void setCpfrtype(String cpfrtype) {
		this.cpfrtype = cpfrtype;
	}
	public String getArticletypeid() {
		return articletypeid;
	}
	public void setArticletypeid(String articletypeid) {
		this.articletypeid = articletypeid;
	}
	public String getIsbracket() {
		return isbracket;
	}
	public void setIsbracket(String isbracket) {
		this.isbracket = isbracket;
	}
	public String getSummaryid() {
		return summaryid;
	}
	public void setSummaryid(String summaryid) {
		this.summaryid = summaryid;
	}
	public String getThemeCoefficient() {
		return themeCoefficient;
	}
	public void setThemeCoefficient(String themeCoefficient) {
		this.themeCoefficient = themeCoefficient;
	}
	public String getIscolor() {
		return iscolor;
	}
	public void setIscolor(String iscolor) {
		this.iscolor = iscolor;
	}
	public String getIsexcise() {
		return isexcise;
	}
	public void setIsexcise(String isexcise) {
		this.isexcise = isexcise;
	}
	public String getIsbaldric() {
		return isbaldric;
	}
	public void setIsbaldric(String isbaldric) {
		this.isbaldric = isbaldric;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getIssuchinda() {
		return issuchinda;
	}
	public void setIssuchinda(String issuchinda) {
		this.issuchinda = issuchinda;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public String getIsout() {
		return isout;
	}
	public void setIsout(String isout) {
		this.isout = isout;
	}
	public String getIsSpecialCharge() {
		return isSpecialCharge;
	}
	public void setIsSpecialCharge(String isSpecialCharge) {
		this.isSpecialCharge = isSpecialCharge;
	}

    private String obliquegraph;// 斜视图
    private String topviewgraph;// 俯视图

    public String getObliquegraph() {
        return obliquegraph;
    }

    public void setObliquegraph(String obliquegraph) {
        this.obliquegraph = obliquegraph;
    }

    public String getTopviewgraph() {
        return topviewgraph;
    }

    public void setTopviewgraph(String topviewgraph) {
        this.topviewgraph = topviewgraph;
    }
}
