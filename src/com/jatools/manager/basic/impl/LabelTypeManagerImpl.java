package com.jatools.manager.basic.impl;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.basic.LabelTypeDao;
import com.jatools.manager.basic.LabelTypeManager;
import com.jatools.vo.basic.LabelType;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class LabelTypeManagerImpl implements LabelTypeManager {
	
	private LabelTypeDao labelTypeDao;
	private WorkflowService workflowService;
	
	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public LabelTypeDao getLabelTypeDao() {
		return labelTypeDao;
	}

	public void setLabelTypeDao(LabelTypeDao labelTypeDao) {
		this.labelTypeDao = labelTypeDao;
	}

	@Override
	public Pager getLabelTypePageData(Map<String, String> conditionMap) {
		return this.labelTypeDao.getLabelTypePageData(conditionMap);
	}

	@Override
	public void saveOrUpdateLabelType(LabelType labelType, String userid) {
		labelType.setUpdateDate(DateUtil.getCurrentDate18());
		labelType.setUpdateId(userid);
		if(StringUtil.isEmpty(labelType.getLabelTypeId())){
			labelType.setCreateDate(DateUtil.getCurrentDate18());
			labelType.setCreateId(userid);
		}
		if(StringUtil.isEmpty(labelType.getLabelTypeId())){
			this.labelTypeDao.saveLabelType(labelType);
		}else{
			this.labelTypeDao.updateLabelType(labelType);
		}
		if(DictConstant.BILL_STATUS_REVIEWING.equals(labelType.getStatus())){
			workflowService.enterReview(labelType, userid);
		}
	}

	@Override
	public LabelType getLabelTypeById(String id) {
		return this.labelTypeDao.getLabelTypeById(id);
	}
	
	@Override
	public void deleteLabelType(String id) {
		if(StringUtil.isNotEmpty(id)){
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				if(StringUtils.isNotEmpty(ids[i])){
					labelTypeDao.deleteLabelType(ids[i]);
				}
			}
		}
	}

	@Override
	public boolean checkLabelTypeRepeat(LabelType labelType) {
		return this.labelTypeDao.checkLabelTypeRepeat(labelType);
	}

	@Override
	public void copyLabelType(Map<String, String> condition) {
		this.labelTypeDao.copyLabelType(condition);
	}
	/**
	 * 更新库存
	 * 库存表、标签申请单、标签打印单、调拔单、入库单、现有量表
	 * 标签名称或是否双标签
	 * @param labelTypeId
	public void updateStock(String labelTypeId){
//		labelTypeDao.getLabelTypeCopy(condition);
		
	}
	 */
}
