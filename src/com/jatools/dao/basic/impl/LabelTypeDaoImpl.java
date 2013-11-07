package com.jatools.dao.basic.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.LabelTypeDao;
import com.jatools.vo.basic.LabelType;
import com.jatools.web.util.DateUtil;

public class LabelTypeDaoImpl extends BaseDao implements LabelTypeDao, ReviewActionIntf {
	@Override
	public Pager getLabelTypePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("LabelType.getLabelTypeAllPageData", "LabelType.getLabelTypeToalCount", condition);
		return pager;
	}

	@Override
	public void saveLabelType(LabelType LabelType) {
		executeInsert("LabelType.saveLabelType", LabelType);
	}

	@Override
	public LabelType getLabelTypeById(String id) {
		return (LabelType)executeQueryForObject("LabelType.getLabelTypeById", id);
	}

	@Override
	public void updateLabelType(LabelType LabelType) {
		executeUpdate("LabelType.updateLabelType", LabelType);
	}

	@Override
	public void deleteLabelType(String id) {
		executeUpdate("LabelType.deleteLabelType", id);
	}

	@Override
	public boolean checkLabelTypeRepeat(LabelType labelType) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", labelType.getItemClassId());
		condition.put("ornaClassId", labelType.getOrnaClassId());
		condition.put("unitId", labelType.getUnitId());
		condition.put("bracketFlag", labelType.getBracketFlag());
		condition.put("labelTypeId", labelType.getLabelTypeId());
		condition.put("privateType", labelType.getPrivateType());
		Integer count = (Integer)executeQueryForObject("LabelType.checkLabelTypeRepeat", condition);
		return count>0;
	}

	@Override
	public void copyLabelType(Map<String, String> condition) {
		executeUpdate("LabelType.deleteCopyLabelType", condition.get("itemClassId2"));
		executeInsert("LabelType.copyLabelType", condition);
	}
	
	private void updateLabelTypeStatus(String billId, String userid, String status, String labelTypeName, String dblLabelFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("labelTypeId", billId);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		condition.put("status", status);
		condition.put("labelTypeName", labelTypeName);
		condition.put("dblLabelFlag", dblLabelFlag);
		condition.put("labelTypeNameOld", null);
		condition.put("dblLabelFlagOld", null);
		executeUpdate("LabelType.reviewLabelType", condition);
	}

	@Override
	public void reviewSuccess(String billId, String userid) {
		LabelType labelType = getLabelTypeById(billId);
		updateLabelTypeStatus(billId, userid, DictConstant.BILL_STATUS_REVIEWED, labelType.getLabelTypeName(), labelType.getDblLabelFlag());
		/**
		 * 更新库存
		 * 库存表、标签申请单、标签打印单、调拔单、入库单、现有量表
		 * 标签名称或是否双标签
		 */
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("labelTypeId", billId);
		executeUpdate("LabelType.updateLabelApplyLine", condition);
		executeUpdate("LabelType.updateLabelPrintLine", condition);
		executeUpdate("LabelType.updateMoveLine", condition);
		executeUpdate("LabelType.updateMaterInivLine", condition);
		executeUpdate("LabelType.updateMaterActive", condition);
	}

	@Override
	public void reviewFail(String billId, String userid) {
		LabelType labelType = getLabelTypeById(billId);
		updateLabelTypeStatus(billId, userid, DictConstant.BILL_STATUS_SAVE, labelType.getLabelTypeNameOld(), labelType.getDblLabelFlagOld());
	}
}
