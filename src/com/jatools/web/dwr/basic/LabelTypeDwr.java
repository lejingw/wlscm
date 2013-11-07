package com.jatools.web.dwr.basic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.LabelTypeManager;
import com.jatools.vo.basic.LabelType;
import com.jatools.web.util.DateUtil;

public class LabelTypeDwr {
	private static Logger logger = Logger.getLogger(LabelTypeDwr.class);
	private LabelTypeManager labelTypeManager;
	public static void setLogger(Logger logger) {
		LabelTypeDwr.logger = logger;
	}
	public void setLabelTypeManager(
			LabelTypeManager labelTypeManager) {
		this.labelTypeManager = labelTypeManager;
	}
	public String copyLabelType(String i ,String i2 ,String o,String o2, HttpServletRequest req){
		Map<String, String> c = new HashMap<String, String>();
		c.put("itemClassId", i);
		c.put("itemClassId2", i2);
		c.put("ornaClassId", o);
		c.put("ornaClassId2", o2);
		c.put("createDate", DateUtil.getCurrentDate18());
		c.put("createId", CommonUtil.getSessionUserId(req));
		try {
			this.labelTypeManager.copyLabelType(c);
		} catch (Exception e) {
			e.printStackTrace();
			return "2_复制出错";
		}
		return "1_复制完成";
	}
	/**
	 * 保存修改操作
	 */
	public String checkLabelType(LabelType labelType ,HttpServletRequest req){
		boolean flag = labelTypeManager.checkLabelTypeRepeat(labelType);
		if(flag){
			return "相同的大类+小类+计量单位+是否镶嵌的条件已近存在，不允许重复";
		}
		if(StringUtils.isNotEmpty(labelType.getStatus())){
			return null;
		}
		LabelType labelType2 = labelTypeManager.getLabelTypeById(labelType.getLabelTypeId());
		if(labelType2==null)
			return null;
		if(DictConstant.BILL_STATUS_REVIEWING.equals(labelType2.getStatus())){
			return "此标签类型正在审批中不能进行修改";
		}
		if(!labelType2.getLabelTypeName().equals(labelType.getLabelTypeName()) || !labelType2.getDblLabelFlag().equals(labelType.getDblLabelFlag())){
			return "-1";
		}
		return null;
	}
	/**
	 * 保存修改操作
	 */
	public String saveOrUpdateLabelType(LabelType labelType ,HttpServletRequest req){
		try {
			boolean flag = labelTypeManager.checkLabelTypeRepeat(labelType);
			if(flag){
				return "相同的大类+小类+计量单位+是否镶嵌+是否高工艺的条件已近存在，不允许重复";
			}
			LabelType labelType2 = labelTypeManager.getLabelTypeById(labelType.getLabelTypeId());
			if(labelType2!=null){
				if(DictConstant.BILL_STATUS_REVIEWING.equals(labelType2.getStatus())){
					return "此标签类型正在审批中不能进行修改";
				}
				if(!labelType2.getLabelTypeName().equals(labelType.getLabelTypeName()) || !labelType2.getDblLabelFlag().equals(labelType.getDblLabelFlag())){
					labelType.setLabelTypeNameOld(labelType2.getLabelTypeName());
					labelType.setDblLabelFlagOld(labelType2.getDblLabelFlag());
					labelType.setStatus(DictConstant.BILL_STATUS_REVIEWING);
				}else{
					labelType.setStatus(DictConstant.BILL_STATUS_SAVE);
				}
			}else
				labelType.setStatus(DictConstant.BILL_STATUS_SAVE);
			labelType.setOrgId(CommonUtil.getSessionOrgId(req));
			labelTypeManager.saveOrUpdateLabelType(labelType, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	/**
	 * 更新库存系统
	 * 库存表、标签申请单、标签打印单、调拔单、入库单、现有量表
	 * @param labelTypeId
	 * @return
	public String updateStock(String labelTypeId){
		labelTypeManager.updateStock(labelTypeId);
		return null;
	}
	 */
}
