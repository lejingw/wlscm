package com.jatools.manager.tag.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.tag.LabelTagApplyDao;
import com.jatools.dao.tag.LabelTagApplyLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.tag.LabelTagApplyManager;
import com.jatools.vo.tag.LabelReasonInfo;
import com.jatools.vo.tag.LabelTagApply;
import com.jatools.vo.tag.LabelTagApplyLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class LabelTagApplyManagerImpl extends BaseManager implements LabelTagApplyManager {

	private LabelTagApplyDao labelTagApplyDao;
	private LabelTagApplyLineDao labelTagApplyLineDao;
	private CommonDao commonDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setLabelTagApplyDao(LabelTagApplyDao labelTagApplyDao) {
		this.labelTagApplyDao = labelTagApplyDao;
	}
	public void setLabelTagApplyLineDao(LabelTagApplyLineDao labelTagApplyLineDao) {
		this.labelTagApplyLineDao = labelTagApplyLineDao;
	}

	@Override
	public LabelTagApply getLabelTagApply(String id) {
		return this.labelTagApplyDao.getLabelTagApply(id);
	}

	@Override
	public LabelTagApplyLine getLabelTagApplyLine(String lineid) {
		return this.labelTagApplyLineDao.getLabelTagApplyLine(lineid);
	}

	@Override
	public List<LabelTagApplyLine> getLabelTagApplyLines(String headid) {
		return this.labelTagApplyLineDao.getLabelTagApplyLineList(headid);
	}

	@Override
	public Pager getLabelTagApplyData(Map<String, String> condition) {
		return this.labelTagApplyDao.getLabelTagApplyData(condition);
	}

	@Override
	public void saveOrUpdateLabelTagApply(LabelTagApply labelTagApply, List<LabelTagApplyLine> lines,  String deleteIds,  String userId) {
		labelTagApply.setUpdatedate(DateUtil.getCurrentDate18());
		this.updateLabelCount(labelTagApply, lines);
		if(StringUtil.isNotBlank(labelTagApply.getId())){
			asertStatus("jat_lable_tag_apply", "id", labelTagApply.getId(), "state", DictConstant.BILL_STATUS_SAVE);
			this.labelTagApplyDao.updateLabelTagApply(labelTagApply);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_APPLY);
			labelTagApply.setNo(billno);
			labelTagApply.setCreatedate(DateUtil.getCurrentDate18());
			labelTagApply.setCreateuserid(userId);
			this.labelTagApplyDao.saveLabelTagApply(labelTagApply);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(LabelTagApplyLine line : lines){
				line.setUpdatedate(DateUtil.getCurrentDate18());
				if(StringUtil.isNotBlank(line.getId())){
					this.labelTagApplyLineDao.updateLabelTagApplyLine(line);
				} else {
					line.setHeadId(labelTagApply.getId());
					this.labelTagApplyLineDao.saveLabelTagApplyLine(line);
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						this.labelTagApplyLineDao.deleteLabelTagApplyLine(lineid);
					}
				}
			}
		}
	}

	@Override
	public void saveAndCheckLabelTagApply(LabelTagApply labelTagApply, List<LabelTagApplyLine> lines, String deleteIds,  String userId) {
		labelTagApply.setState(DictConstant.BILL_STATUS_REVIEWING);
		labelTagApply.setUpdatedate(DateUtil.getCurrentDate18());
		this.updateLabelCount(labelTagApply, lines);
		if(StringUtil.isNotBlank(labelTagApply.getId())){
			asertStatus("jat_lable_tag_apply", "id", labelTagApply.getId(), "state", DictConstant.BILL_STATUS_SAVE);
			this.labelTagApplyDao.updateLabelTagApply(labelTagApply);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_APPLY);
			labelTagApply.setNo(billno);
			labelTagApply.setCreatedate(DateUtil.getCurrentDate18());
			labelTagApply.setCreateuserid(userId);
			this.labelTagApplyDao.saveLabelTagApply(labelTagApply);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(LabelTagApplyLine line : lines){
				line.setUpdatedate(DateUtil.getCurrentDate18());
				if(StringUtil.isNotBlank(line.getId())){
					this.labelTagApplyLineDao.updateLabelTagApplyLine(line);
				} else {
					line.setHeadId(labelTagApply.getId());
					this.labelTagApplyLineDao.saveLabelTagApplyLine(line);
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						this.labelTagApplyLineDao.deleteLabelTagApplyLine(lineid);
					}
				}
			}
		}
		workflowService.enterReview(labelTagApply, userId);
	}
	/**
	 * 重置申请单件数合计
	 * @param labelTagApply
	 * @param lines
	 */
	private void updateLabelCount(LabelTagApply labelTagApply, List<LabelTagApplyLine> lines){
		if(labelTagApply != null ){
			if(CollectionUtils.isNotEmpty(lines)){
				int numTotal = Double.valueOf(labelTagApply.getNumTotal()).intValue();
				if(numTotal != lines.size()){
					labelTagApply.setNumTotal(lines.size()+"");
				}
			} else {
				labelTagApply.setNumTotal("0");
			}
		}
	}
	@Override
	public void closeLabelTagApply(LabelTagApply labelTagApply, String userId) {
		this.labelTagApplyDao.modifyLabelTagApplyStatus(labelTagApply.getId(), DictConstant.BILL_STATUS_CLOSED, userId);
	}

	@Override
	public void deleteLabelTagApply(String headid) {
		asertStatus("jat_lable_tag_apply", "id", headid, "state", DictConstant.BILL_STATUS_SAVE);
		this.labelTagApplyDao.deleteLabelTagApply(headid);
		this.labelTagApplyLineDao.deleteLabelTagApplyLineByHeadid(headid);
	}

	@Override
	public List<SelectorOption> getApplyReason() {
		return this.labelTagApplyDao.getApplyReason();
	}

	@Override
	public LabelReasonInfo getApplyReasoninfo(String labelId) {
		return this.labelTagApplyDao.getApplyReasoninfo(labelId);
	}

	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
