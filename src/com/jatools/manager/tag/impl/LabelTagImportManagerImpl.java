package com.jatools.manager.tag.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.tag.LabelTagImportHeadDao;
import com.jatools.dao.tag.LabelTagImportLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.tag.LabelTagImportManager;
import com.jatools.vo.tag.LabelImportOrgGroupNo;
import com.jatools.vo.tag.LabelTagImportHead;
import com.jatools.vo.tag.LabelTagImportLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class LabelTagImportManagerImpl extends BaseManager implements LabelTagImportManager {

	private LabelTagImportHeadDao labelTagImportHeadDao;
	private LabelTagImportLineDao labelTagImportLineDao;
	private WorkflowService workflowService;
	private CommonDao commonDao;
	
	@Override
	public Pager getLabelTagImportHeadData(Map<String, String> condition) {
		return this.labelTagImportHeadDao.getLabelTagImportHeadData(condition);
	}

	public Pager getLabelTagImportLineData(Map<String, String> condition) {
		return this.labelTagImportLineDao.getLabelTagImportLinePagerData(condition);
	}
	
	@Override
	public void saveOrUpdateLabelTagImportHead(	LabelTagImportHead labelTagImportHead, List<LabelTagImportLine> lines, String deleteIds, String userId) {
		this.saveUpdateCheckLabelTagImportHead(labelTagImportHead, lines, deleteIds, userId, false);
	}

	@Override
	public void saveAndCheckLabelTagImportHead(LabelTagImportHead labelTagImportHead, List<LabelTagImportLine> lines, String deleteIds, String userId) {
		this.saveUpdateCheckLabelTagImportHead(labelTagImportHead, lines, deleteIds, userId, true);
	}
	
	public void saveOrUpdateLabelTagImportHead(	LabelTagImportHead labelTagImportHead, String seqId, String userId) {
		String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_IMPORT);
		labelTagImportHead.setBillno(billno);
		this.labelTagImportHeadDao.saveLabelTagImportHead(labelTagImportHead);
		this.labelTagImportLineDao.inertLineFromTemp(labelTagImportHead.getHeadid(), seqId, userId);
		this.labelTagImportHeadDao.modifyLabelImportCount(labelTagImportHead.getHeadid());
	}
	
	private void saveUpdateCheckLabelTagImportHead(LabelTagImportHead labelTagImportHead, List<LabelTagImportLine> lines, String deleteIds, String userId, boolean isCheck) {
		labelTagImportHead.setUpdatedate(DateUtil.getCurrentDate18());
		labelTagImportHead.setUpdateid(userId);
		if(isCheck){
			labelTagImportHead.setStatus(DictConstant.BILL_STATUS_REVIEWING);
		}
		if(StringUtil.isNotBlank(labelTagImportHead.getHeadid())){
			asertStatus("jat_lable_tag_import_head", "headid", labelTagImportHead.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.labelTagImportHeadDao.updateLabelTagImportHead(labelTagImportHead);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_IMPORT);
			labelTagImportHead.setBillno(billno);
			labelTagImportHead.setCreatedate(DateUtil.getCurrentDate18());
			labelTagImportHead.setCreateid(userId);
			this.labelTagImportHeadDao.saveLabelTagImportHead(labelTagImportHead);
		}
		// 删除行
		if(StringUtil.isNotBlank(deleteIds)){
			String ids[] = deleteIds.split(";");
			for(String lineid : ids){
				if(StringUtil.isNotBlank(lineid)){
					this.labelTagImportLineDao.deleteLabelTagImportLine(lineid);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(lines)){
			for(LabelTagImportLine line : lines){
				line.setUpdateId(userId);
				line.setUpdateDate(DateUtil.getCurrentDate18());
				if(StringUtil.isBlank(line.getLineid())){
					line.setHeadid(labelTagImportHead.getHeadid());
					line.setCreateId(userId);
					line.setCreateDate(DateUtil.getCurrentDate18());
					this.labelTagImportLineDao.saveLabelTagImportLine(line);
				} else {
					this.labelTagImportLineDao.updateLabelTagImportLine(line);
				}
			}
		}
		this.labelTagImportHeadDao.modifyLabelImportCount(labelTagImportHead.getHeadid());
		// 提交审批
		if(isCheck){
			this.workflowService.enterReview(labelTagImportHead, userId);
		}
	}
	
	@Override
	public LabelTagImportHead getLabelTagImportHead(String headid) {
		return this.labelTagImportHeadDao.getLabelTagImportHead(headid);
	}

	@Override
	public List<LabelTagImportLine> getLabelTagImportLineList(String headid) {
		return this.labelTagImportLineDao.getLabelTagImportLineList(headid);
	}
	
	
	public List<String> getValidOrnaCodes(String ornaCodes){
		return this.labelTagImportLineDao.getValidOrnaCodes(ornaCodes);
	}
	
	public List<String> getExistsOrnaCodes(String seqId){
		return this.labelTagImportLineDao.getExistsOrnaCodes(seqId);
	}

	@Override
	public void deleteLabelTagImportHead(String headid) {
		asertStatus("jat_lable_tag_import_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
		this.labelTagImportLineDao.deleteLabelTagImportLineByHeadid(headid);
		this.labelTagImportHeadDao.deleteLabelTagImportHead(headid);
	}
	
	public void closeLabelTagImport(String headid, String userId){
		// 关闭单据 同时生成 标签打印单
		asertStatus("jat_lable_tag_import_head", "headid", headid, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.labelTagImportHeadDao.insertPrintLines(headid);
		this.labelTagImportHeadDao.insertPrintHeads(userId);
		this.labelTagImportHeadDao.updatePrintLineHeadId();
		
		List<LabelImportOrgGroupNo> orgGroupNoList = this.labelTagImportHeadDao.getOrgGroupNo2();
		
		if(CollectionUtils.isNotEmpty(orgGroupNoList)){
			for(LabelImportOrgGroupNo orgGroup : orgGroupNoList){
				// 创建打印单头信息
				orgGroup.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_PRINT));
				orgGroup.setUpdateDate(DateUtil.getCurrentDate18());
				orgGroup.setUserId(userId);
				orgGroup.setImportId(headid);
				this.labelTagImportHeadDao.modifyLabelPrintBillNo(orgGroup);
			}
		}
		this.labelTagImportHeadDao.modifyLabelTagImportHeadStatus(headid, DictConstant.BILL_STATUS_CLOSED, userId);
	}
	
	public void closeLabelTagImport_bak(String headid, String userId){
		// 关闭单据 同时生成 标签打印单
		asertStatus("jat_lable_tag_import_head", "headid", headid, "status", DictConstant.BILL_STATUS_REVIEWED);
		List<LabelImportOrgGroupNo> orgGroupNoList = this.labelTagImportHeadDao.getOrgGroupNo(headid);
		if(CollectionUtils.isNotEmpty(orgGroupNoList)){
			for(LabelImportOrgGroupNo orgGroup : orgGroupNoList){
				// 创建打印单头信息
				orgGroup.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_PRINT));
				orgGroup.setUpdateDate(DateUtil.getCurrentDate18());
				orgGroup.setUserId(userId);
				orgGroup.setPrintType("公司创建");
				this.labelTagImportHeadDao.createPrintHead(orgGroup); // 插入标签打印单头
				this.labelTagImportHeadDao.createPrintLine(orgGroup);// 插入标签打印单行
				// 修改头数量
				this.labelTagImportHeadDao.modifyLabelPrintNumCount(orgGroup.getPrintId(), userId);
			}
		}
		this.labelTagImportHeadDao.modifyLabelTagImportHeadStatus(headid, DictConstant.BILL_STATUS_CLOSED, userId);
	}
	
	
	// --------------------------------------------------------------------------------------------------
	
	public String getLineTempSeq(){
		return this.labelTagImportLineDao.getLineTempSeq();
	}
	public String insertLinesTemp(List<LabelTagImportLine> lines){
		this.labelTagImportLineDao.saveTempLineList(lines);
		return "";
		/*String tempSeqId = this.labelTagImportLineDao.getLineTempSeq();
		for(LabelTagImportLine line : lines){
			line.setSeqId(tempSeqId);
			this.labelTagImportLineDao.saveTempLine(line);
		}
		return tempSeqId;*/
	}
	
	public void deleteTempLineBySeqId(String seqId){
		this.labelTagImportLineDao.deleteTempLineBySeqId(seqId);
	}
	
	public void setLabelTagImportHeadDao(LabelTagImportHeadDao labelTagImportHeadDao) {
		this.labelTagImportHeadDao = labelTagImportHeadDao;
	}

	public void setLabelTagImportLineDao(LabelTagImportLineDao labelTagImportLineDao) {
		this.labelTagImportLineDao = labelTagImportLineDao;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public List<String> getInValidOrnaCodes(String seqId) {
		return this.labelTagImportLineDao.getInValidOrnaCodes(seqId);
	}

}
