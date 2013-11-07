package com.jatools.vo.calc;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;

public class MaterAttrUpdate implements ReviewBill{
	private String billid;//				
	private String billno;//	//		
	private String orgid;//	//		
	private String status;//	//		
	private String rebate;//	//		
	private String newrebate;//	//		
	private String rebatetype;//	//		
	private String newrebatetype;//	//		
	private String tagtype;//	//		
	private String newtagtype;//	//		
	private String stringworkprice;//	//		
	private String newstringworkprice;//	//		
	private String specialworkprice;//	//		
	private String newspecialworkprice;//	//		
	private String ornastate;//	//		
	private String newornastate;//	//		
	private String isdbllabel;	//		
	private String newisdbllabel;	//		
	private String mainshapeid;//	//	
	private String newmainshapeid;//	//	
	private String colorid;//	//	 
	private String newcolorid;//	//	 
	private String cleanid;//	//	
	private String newcleanid;//	//	
	private String maincolorgradeid;//	//	
	private String newmaincolorgradeid;//	//	
	private String cutid;//	//	
	private String newcutid;//	//	
	private String cutwidescale;//	//		
	private String newcutwidescale;//	//		
	private String cutdeepscale;//	//		
	private String newcutdeepscale;//	//		
	private String symmetryid;//	//	
	private String newsymmetryid;//	//	
	private String polishineid;//	//	
	private String newpolishineid;//	//	
	private String fluorescenceid;//	//	
	private String newfluorescenceid;//	//	
	private String waistlineid;//	//	
	private String newwaistlineid;//	//	
	private String vertexid;//	//	
	private String newvertexid;//	//	
	private String bracketcolorid;//	//	
	private String newbracketcolorid;//	//	
	private String wearid;//	//		
	private String newwearid;//	//		
	private String styletypeid;//	//		
	private String newstyletypeid;//	//		
	private String ismutipart;	//		 0  1
	private String newismutipart;	//		 0  1
	private String partcontent;//	//		
	private String newpartcontent;//	//		
	private String zodiac;//	//		
	private String newzodiac;//	//		
	private String createdate;//	//		
	private String createid;//	//		
	private String updatedate;//	//		
	private String updateid;//	//		
	private String memo;//	//		
	private String updatereason;//	//		
	private String rebateamount;//	//		
	private String newrebateamount;//	//	
	private String ornacode;
	private String ornabarcode;
	
	
	public String getOrnabarcode() {
		return ornabarcode;
	}
	public void setOrnabarcode(String ornabarcode) {
		this.ornabarcode = ornabarcode;
	}
	public String getOrnacode() {
		return ornacode;
	}
	public void setOrnacode(String ornacode) {
		this.ornacode = ornacode;
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
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	public String getNewrebate() {
		return newrebate;
	}
	public void setNewrebate(String newrebate) {
		this.newrebate = newrebate;
	}
	public String getRebatetype() {
		return rebatetype;
	}
	public void setRebatetype(String rebatetype) {
		this.rebatetype = rebatetype;
	}
	public String getNewrebatetype() {
		return newrebatetype;
	}
	public void setNewrebatetype(String newrebatetype) {
		this.newrebatetype = newrebatetype;
	}
	public String getTagtype() {
		return tagtype;
	}
	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}
	public String getNewtagtype() {
		return newtagtype;
	}
	public void setNewtagtype(String newtagtype) {
		this.newtagtype = newtagtype;
	}
	public String getStringworkprice() {
		return stringworkprice;
	}
	public void setStringworkprice(String stringworkprice) {
		this.stringworkprice = stringworkprice;
	}
	public String getNewstringworkprice() {
		return newstringworkprice;
	}
	public void setNewstringworkprice(String newstringworkprice) {
		this.newstringworkprice = newstringworkprice;
	}
	public String getSpecialworkprice() {
		return specialworkprice;
	}
	public void setSpecialworkprice(String specialworkprice) {
		this.specialworkprice = specialworkprice;
	}
	public String getNewspecialworkprice() {
		return newspecialworkprice;
	}
	public void setNewspecialworkprice(String newspecialworkprice) {
		this.newspecialworkprice = newspecialworkprice;
	}
	public String getOrnastate() {
		return ornastate;
	}
	public void setOrnastate(String ornastate) {
		this.ornastate = ornastate;
	}
	public String getNewornastate() {
		return newornastate;
	}
	public void setNewornastate(String newornastate) {
		this.newornastate = newornastate;
	}
	public String getIsdbllabel() {
		return isdbllabel;
	}
	public void setIsdbllabel(String isdbllabel) {
		this.isdbllabel = isdbllabel;
	}
	public String getNewisdbllabel() {
		return newisdbllabel;
	}
	public void setNewisdbllabel(String newisdbllabel) {
		this.newisdbllabel = newisdbllabel;
	}
	public String getMainshapeid() {
		return mainshapeid;
	}
	public void setMainshapeid(String mainshapeid) {
		this.mainshapeid = mainshapeid;
	}
	public String getNewmainshapeid() {
		return newmainshapeid;
	}
	public void setNewmainshapeid(String newmainshapeid) {
		this.newmainshapeid = newmainshapeid;
	}
	public String getColorid() {
		return colorid;
	}
	public void setColorid(String colorid) {
		this.colorid = colorid;
	}
	public String getNewcolorid() {
		return newcolorid;
	}
	public void setNewcolorid(String newcolorid) {
		this.newcolorid = newcolorid;
	}
	public String getCleanid() {
		return cleanid;
	}
	public void setCleanid(String cleanid) {
		this.cleanid = cleanid;
	}
	public String getNewcleanid() {
		return newcleanid;
	}
	public void setNewcleanid(String newcleanid) {
		this.newcleanid = newcleanid;
	}
	public String getMaincolorgradeid() {
		return maincolorgradeid;
	}
	public void setMaincolorgradeid(String maincolorgradeid) {
		this.maincolorgradeid = maincolorgradeid;
	}
	public String getNewmaincolorgradeid() {
		return newmaincolorgradeid;
	}
	public void setNewmaincolorgradeid(String newmaincolorgradeid) {
		this.newmaincolorgradeid = newmaincolorgradeid;
	}
	public String getCutid() {
		return cutid;
	}
	public void setCutid(String cutid) {
		this.cutid = cutid;
	}
	public String getNewcutid() {
		return newcutid;
	}
	public void setNewcutid(String newcutid) {
		this.newcutid = newcutid;
	}
	public String getCutwidescale() {
		return cutwidescale;
	}
	public void setCutwidescale(String cutwidescale) {
		this.cutwidescale = cutwidescale;
	}
	public String getNewcutwidescale() {
		return newcutwidescale;
	}
	public void setNewcutwidescale(String newcutwidescale) {
		this.newcutwidescale = newcutwidescale;
	}
	public String getCutdeepscale() {
		return cutdeepscale;
	}
	public void setCutdeepscale(String cutdeepscale) {
		this.cutdeepscale = cutdeepscale;
	}
	public String getNewcutdeepscale() {
		return newcutdeepscale;
	}
	public void setNewcutdeepscale(String newcutdeepscale) {
		this.newcutdeepscale = newcutdeepscale;
	}
	public String getSymmetryid() {
		return symmetryid;
	}
	public void setSymmetryid(String symmetryid) {
		this.symmetryid = symmetryid;
	}
	public String getNewsymmetryid() {
		return newsymmetryid;
	}
	public void setNewsymmetryid(String newsymmetryid) {
		this.newsymmetryid = newsymmetryid;
	}
	public String getPolishineid() {
		return polishineid;
	}
	public void setPolishineid(String polishineid) {
		this.polishineid = polishineid;
	}
	public String getNewpolishineid() {
		return newpolishineid;
	}
	public void setNewpolishineid(String newpolishineid) {
		this.newpolishineid = newpolishineid;
	}
	public String getFluorescenceid() {
		return fluorescenceid;
	}
	public void setFluorescenceid(String fluorescenceid) {
		this.fluorescenceid = fluorescenceid;
	}
	public String getNewfluorescenceid() {
		return newfluorescenceid;
	}
	public void setNewfluorescenceid(String newfluorescenceid) {
		this.newfluorescenceid = newfluorescenceid;
	}
	public String getWaistlineid() {
		return waistlineid;
	}
	public void setWaistlineid(String waistlineid) {
		this.waistlineid = waistlineid;
	}
	public String getNewwaistlineid() {
		return newwaistlineid;
	}
	public void setNewwaistlineid(String newwaistlineid) {
		this.newwaistlineid = newwaistlineid;
	}
	public String getVertexid() {
		return vertexid;
	}
	public void setVertexid(String vertexid) {
		this.vertexid = vertexid;
	}
	public String getNewvertexid() {
		return newvertexid;
	}
	public void setNewvertexid(String newvertexid) {
		this.newvertexid = newvertexid;
	}
	public String getBracketcolorid() {
		return bracketcolorid;
	}
	public void setBracketcolorid(String bracketcolorid) {
		this.bracketcolorid = bracketcolorid;
	}
	public String getNewbracketcolorid() {
		return newbracketcolorid;
	}
	public void setNewbracketcolorid(String newbracketcolorid) {
		this.newbracketcolorid = newbracketcolorid;
	}
	public String getWearid() {
		return wearid;
	}
	public void setWearid(String wearid) {
		this.wearid = wearid;
	}
	public String getNewwearid() {
		return newwearid;
	}
	public void setNewwearid(String newwearid) {
		this.newwearid = newwearid;
	}
	public String getStyletypeid() {
		return styletypeid;
	}
	public void setStyletypeid(String styletypeid) {
		this.styletypeid = styletypeid;
	}
	public String getNewstyletypeid() {
		return newstyletypeid;
	}
	public void setNewstyletypeid(String newstyletypeid) {
		this.newstyletypeid = newstyletypeid;
	}
	public String getIsmutipart() {
		return ismutipart;
	}
	public void setIsmutipart(String ismutipart) {
		this.ismutipart = ismutipart;
	}
	public String getNewismutipart() {
		return newismutipart;
	}
	public void setNewismutipart(String newismutipart) {
		this.newismutipart = newismutipart;
	}
	public String getPartcontent() {
		return partcontent;
	}
	public void setPartcontent(String partcontent) {
		this.partcontent = partcontent;
	}
	public String getNewpartcontent() {
		return newpartcontent;
	}
	public void setNewpartcontent(String newpartcontent) {
		this.newpartcontent = newpartcontent;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public String getNewzodiac() {
		return newzodiac;
	}
	public void setNewzodiac(String newzodiac) {
		this.newzodiac = newzodiac;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
		this.createid = createid;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdateid() {
		return updateid;
	}
	public void setUpdateid(String updateid) {
		this.updateid = updateid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUpdatereason() {
		return updatereason;
	}
	public void setUpdatereason(String updatereason) {
		this.updatereason = updatereason;
	}
	public String getRebateamount() {
		return rebateamount;
	}
	public void setRebateamount(String rebateamount) {
		this.rebateamount = rebateamount;
	}
	public String getNewrebateamount() {
		return newrebateamount;
	}
	public void setNewrebateamount(String newrebateamount) {
		this.newrebateamount = newrebateamount;
	}
	@Override
	public String getBillCode() {
		return GlobalConstant.BILL_CODE_MATERATTRUPDATE;
	}
	@Override
	public String getPageUrl() {
		return Global.CONTEXT  + "/calc/materAttrUpdate.vm?user_action=toEditMaterAttrUpdate&id=" + this.billid;
	}
	@Override
	public String getBeanName() {
		return "materAttrUpdateDao";
	}
	@Override
	public String getOrgId() {
		return this.orgid;
	}
	
	
}
