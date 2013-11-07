package com.jatools.vo.basic;

public class SaleChangeLabel {

	private String changelabelId     ;
	private String changelabelReason ;
	private String note              ;
	//private String archiveflag       ;
	private String createuserid      ;
	private String createdate        ;
	private String updatedate        ;
	private String updateuserid      ;
	//private String archivedate       ;
	private String isincreaseamount  ;
	private String isupdatecauses    ;
	private String labelKind         ;
	private String isbasicPrice      ;
	private String specialWorkPrice  ;
	//private String oldArchiveflag;
	
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public String getOldArchiveflag() {
//		return oldArchiveflag;
//	}
//	public void setOldArchiveflag(String oldArchiveflag) {
//		this.oldArchiveflag = oldArchiveflag;
//	}
	public String getChangelabelId() {
		return changelabelId;
	}
	public void setChangelabelId(String changelabelId) {
		this.changelabelId = changelabelId;
	}
	public String getChangelabelReason() {
		return changelabelReason;
	}
	public void setChangelabelReason(String changelabelReason) {
		this.changelabelReason = changelabelReason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	/*public String getArchiveflag() {
		return archiveflag;
	}
	public void setArchiveflag(String archiveflag) {
		this.archiveflag = archiveflag;
	}*/
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
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdateuserid() {
		return updateuserid;
	}
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	/*public String getArchivedate() {
		return archivedate;
	}
	public void setArchivedate(String archivedate) {
		this.archivedate = archivedate;
	}*/
	public String getIsincreaseamount() {
		return isincreaseamount;
	}
	public void setIsincreaseamount(String isincreaseamount) {
		this.isincreaseamount = isincreaseamount;
	}
	public String getIsupdatecauses() {
		return isupdatecauses;
	}
	public void setIsupdatecauses(String isupdatecauses) {
		this.isupdatecauses = isupdatecauses;
	}
	public String getLabelKind() {
		return labelKind;
	}
	public void setLabelKind(String labelKind) {
		this.labelKind = labelKind;
	}
	public String getIsbasicPrice() {
		return isbasicPrice;
	}
	public void setIsbasicPrice(String isbasicPrice) {
		this.isbasicPrice = isbasicPrice;
	}
	public String getSpecialWorkPrice() {
		return specialWorkPrice;
	}
	public void setSpecialWorkPrice(String specialWorkPrice) {
		this.specialWorkPrice = specialWorkPrice;
	}
	
}
