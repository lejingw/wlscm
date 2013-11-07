package com.jatools.manager.tag.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.tag.LabelTagApplyDao;
import com.jatools.dao.tag.LabelTagApplyPrintDao;
import com.jatools.dao.tag.LabelTagApplyPrintLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.tag.LabelTagApplyPrintManager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.tag.LabelTagApplyPrint;
import com.jatools.vo.tag.LabelTagApplyPrintLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class LabelTagApplyPrintManagerImpl extends BaseManager implements LabelTagApplyPrintManager {

	private LabelTagApplyPrintDao labelTagApplyPrintDao;
	private LabelTagApplyPrintLineDao labelTagApplyPrintLineDao;
	private LabelTagApplyDao labelTagApplyDao;
	private CommonDao commonDao;
	
	private final static String ISSUCCESS = "isSuccess";
	private final static String MSG = "msg";
	private final static String TRUE = "true";
	private final static String FALSE = "false";
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setLabelTagApplyPrintDao(LabelTagApplyPrintDao LabelTagApplyPrintDao) {
		this.labelTagApplyPrintDao = LabelTagApplyPrintDao;
	}
	public void setLabelTagApplyPrintLineDao(LabelTagApplyPrintLineDao LabelTagApplyPrintLineDao) {
		this.labelTagApplyPrintLineDao = LabelTagApplyPrintLineDao;
	}
	public void setLabelTagApplyDao(LabelTagApplyDao labelTagApplyDao) {
		this.labelTagApplyDao = labelTagApplyDao;
	}
	@Override
	public LabelTagApplyPrint getLabelTagApplyPrint(String id) {
		return this.labelTagApplyPrintDao.getLabelTagApplyPrint(id);
	}

	@Override
	public LabelTagApplyPrintLine getLabelTagApplyPrintLine(String lineid) {
		return this.labelTagApplyPrintLineDao.getLabelTagApplyPrintLine(lineid);
	}

	@Override
	public List<LabelTagApplyPrintLine> getLabelTagApplyPrintLines(String headid) {
		return this.labelTagApplyPrintLineDao.getLabelTagApplyPrintLineList(headid);
	}

	@Override
	public Pager getLabelTagApplyPrintData(Map<String, String> condition) {
		return this.labelTagApplyPrintDao.getLabelTagApplyPrintData(condition);
	}

	@Override
	public void saveOrUpdateLabelTagApplyPrint(LabelTagApplyPrint labelTagApplyPrint, List<LabelTagApplyPrintLine> lines,  String deleteIds,  String userId) {
		labelTagApplyPrint.setUpdatedate(DateUtil.getCurrentDate18());
		labelTagApplyPrint.setUpdateuserid(userId);
		if(StringUtil.isNotBlank(labelTagApplyPrint.getId())){
			asertStatus("jat_lable_tag_print", "id", labelTagApplyPrint.getId(), "state", DictConstant.BILL_STATUS_SAVE);
			this.labelTagApplyPrintDao.updateLabelTagApplyPrint(labelTagApplyPrint);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_PRINT);
			labelTagApplyPrint.setNo(billno);
			labelTagApplyPrint.setCreatedate(DateUtil.getCurrentDate18());
			labelTagApplyPrint.setCreateuserid(userId);
			this.labelTagApplyPrintDao.saveLabelTagApplyPrint(labelTagApplyPrint);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(LabelTagApplyPrintLine line : lines){
				line.setUpdatedate(DateUtil.getCurrentDate18());
				if(StringUtil.isNotBlank(line.getId())){
					this.labelTagApplyPrintLineDao.updateLabelTagApplyPrintLine(line);
				} else {
					line.setHeadId(labelTagApplyPrint.getId());
					this.labelTagApplyPrintLineDao.saveLabelTagApplyPrintLine(line);
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						this.labelTagApplyPrintLineDao.deleteLabelTagApplyPrintLine(lineid);
					}
				}
			}
		}
	}

	@Override
	public void saveAndMarkLabelTagApplyPrint(LabelTagApplyPrint labelTagApplyPrint, List<LabelTagApplyPrintLine> lines, String deleteIds,  String userId) {
		labelTagApplyPrint.setState(DictConstant.BILL_STATUS_MERGED);
		labelTagApplyPrint.setUpdatedate(DateUtil.getCurrentDate18());
		labelTagApplyPrint.setUpdateuserid(userId);
		if(StringUtil.isNotBlank(labelTagApplyPrint.getId())){
			asertStatus("jat_lable_tag_print", "id", labelTagApplyPrint.getId(), "state", DictConstant.BILL_STATUS_SAVE);
			this.labelTagApplyPrintDao.updateLabelTagApplyPrint(labelTagApplyPrint);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_PRINT);
			labelTagApplyPrint.setNo(billno);
			labelTagApplyPrint.setCreatedate(DateUtil.getCurrentDate18());
			labelTagApplyPrint.setCreateuserid(userId);
			this.labelTagApplyPrintDao.saveLabelTagApplyPrint(labelTagApplyPrint);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(LabelTagApplyPrintLine line : lines){
				line.setUpdatedate(DateUtil.getCurrentDate18());
				line.setState(DictConstant.BILL_STATUS_MERGED);
				if(StringUtil.isNotBlank(line.getId())){
					this.labelTagApplyPrintLineDao.updateLabelTagApplyPrintLine(line);
				} else {
					line.setHeadId(labelTagApplyPrint.getId());
					this.labelTagApplyPrintLineDao.saveLabelTagApplyPrintLine(line);
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						this.labelTagApplyPrintLineDao.deleteLabelTagApplyPrintLine(lineid);
					}
				}
			}
		}
	}

	@Override
	public void remarkLabelTagApplyPrint(LabelTagApplyPrint LabelTagApplyPrint, String userId) {
		this.labelTagApplyPrintDao.modifyLabelTagApplyPrintStatus(LabelTagApplyPrint.getId(), DictConstant.BILL_STATUS_MERGED, userId);
		this.labelTagApplyPrintLineDao.modifyPrintLineStatusByHeadid(LabelTagApplyPrint.getId(), DictConstant.BILL_STATUS_MERGED, userId);
	}

	@Override
	public void deleteLabelTagApplyPrint(String headid) {
		asertStatus("jat_lable_tag_print", "id", headid, "state", DictConstant.BILL_STATUS_SAVE);
		this.labelTagApplyPrintDao.deleteLabelTagApplyPrint(headid);
		this.labelTagApplyPrintLineDao.deleteLabelTagApplyPrintLineByHeadid(headid);
	}
	
	@Override
	public List<Tag> getPrintData(String headid, String labelType, String lineids, String orderBy) {
		return this.labelTagApplyPrintDao.getPrintData(headid, labelType, lineids, orderBy);
	}
	
	@Override
	public Map<String, String> createLabelPrint(String orgIds, String useId) {
		Map<String, String> result = new HashMap<String, String>();
		if(StringUtil.isNotBlank(orgIds)){
			String orgArray[] = orgIds.split(",");
			if(!ArrayUtils.isEmpty(orgArray)){
				int billCount = 0;
				for(String orgId : orgArray){
					if(StringUtil.isNotBlank(orgId)){
						int numTotal = this.labelTagApplyPrintDao.getApplyCountByOrgId(orgId);
						if(numTotal >0){
							LabelTagApplyPrint print = new LabelTagApplyPrint();
							String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_PRINT);
							print.setNo(billno);
							print.setCancelNum("0");
							print.setCreatedate(DateUtil.getCurrentDate18());
							print.setCreateuserid(useId);
							print.setNumTotal(numTotal+"");
							print.setOrgId(orgId);
							print.setPrintType("门店申请");
							print.setReceiveNum("0");
							print.setState(DictConstant.BILL_STATUS_MERGED);
							print.setUpdatedate(DateUtil.getCurrentDate18());
							print.setUpdateuserid(useId);
							this.labelTagApplyPrintDao.saveLabelTagApplyPrint(print);// 插入头表
							this.labelTagApplyPrintDao.insertLines(print.getId(), orgId);// 插入行表数据
							// 修改申请单状态为 已合并 以及把打印单id写入
							this.labelTagApplyPrintDao.updateLabelApply(print.getId(), orgId);
							// 修改申请单行表状态为已合并
							this.labelTagApplyPrintDao.updateLabelApplyLines(print.getId(), orgId);
							billCount = billCount + numTotal;
						}
					}
				}
				if(billCount>0){
					result.put(ISSUCCESS, TRUE);
				} else {
					result.put(ISSUCCESS, FALSE);
					result.put(MSG, "不存在满足要求的标签申请单");
				}
			} else {
				result.put(ISSUCCESS, FALSE);
				result.put(MSG, "请选择组织。");
			}
		} else {
			result.put(ISSUCCESS, FALSE);
			result.put(MSG, "请选择组织。");
		}
		return result;
	}
	@Override
	public void updateLabelApplyStatus(String printId, String userId) {
		this.labelTagApplyPrintDao.modifyLabelTagApplyPrintStatus(printId, DictConstant.BILL_STATUS_RECEIVING, userId);
		this.labelTagApplyPrintLineDao.modifyPrintLineStatusByHeadid(printId, DictConstant.BILL_STATUS_RECEIVING, userId);
		this.labelTagApplyPrintDao.updateLabelApplyStatusByPrintId(printId);
		this.labelTagApplyPrintDao.updateLabelApplyLineStatusByPrintId(printId);
	}
	@Override
	public LabelTagApplyPrintLine getPrintLine(String labelId, String ornaCode,	String ornaBarCode) {
		return this.labelTagApplyPrintLineDao.getPrintLine(labelId, ornaCode, ornaBarCode);
	}
	@Override
	public List<LabelTagApplyPrintLine> getLabelTagApplyPrintLines(Map<String, String> condition) {
		return this.labelTagApplyPrintLineDao.getLabelTagApplyPrintLineList(condition);
	}
	@Override
	public void receiveLabelApplyPrint(LabelTagApplyPrint LabelTagApplyPrint, List<LabelTagApplyPrintLine> lines, String userId) {
		asertStatus("jat_lable_tag_print", "id", LabelTagApplyPrint.getId(), "state", DictConstant.BILL_STATUS_RECEIVING);
		LabelTagApplyPrint.setState(DictConstant.BILL_STATUS_RECEIVING);
		LabelTagApplyPrint.setUpdatedate(DateUtil.getCurrentDate18());
		LabelTagApplyPrint.setUpdateuserid(userId);
		LabelTagApplyPrint.setReceiveUserid(userId);
		this.labelTagApplyPrintDao.updateLabelTagApplyPrint(LabelTagApplyPrint);
		List<String> lineids = new ArrayList<String>();
		for(LabelTagApplyPrintLine line : lines ){
			lineids.add(line.getId());
		}
		String headid = LabelTagApplyPrint.getId();
		String recType = LabelTagApplyPrint.getLabelReceiveType();
		String total = LabelTagApplyPrint.getNumTotal();
		String recNum = LabelTagApplyPrint.getReceiveNum();
		String cancelNum = LabelTagApplyPrint.getCancelNum();
		if(total.equals(recNum)){
			this.labelTagApplyPrintLineDao.modifyPrintLineStatusByHeadid(headid, DictConstant.BILL_STATUS_RECEIVED, userId);
		}
		if(total.equals(cancelNum)){
			this.labelTagApplyPrintLineDao.modifyPrintLineStatusByHeadid(headid, DictConstant.BILL_STATUS_RECEIVING, userId);
		}
		if(!total.equals(recNum) && !total.equals(cancelNum)){
			if(lineids.size() >0){
				this.labelTagApplyPrintLineDao.updateState(headid, recType, "1", lineids);
				this.labelTagApplyPrintLineDao.updateState(headid, recType.equals("1")?"2":"1", "0", lineids);
			} else {
				if("1".equals(recType)){
					this.labelTagApplyPrintLineDao.updateState(headid, DictConstant.BILL_STATUS_RECEIVED);
				} else {
					this.labelTagApplyPrintLineDao.updateState(headid, DictConstant.BILL_STATUS_RECEIVING);
				}
			}
			
		}
	}
	@Override
	public void initLabelApplyPrint(String headid, String userId) {
		asertStatus("jat_lable_tag_print", "id", headid, "state", DictConstant.BILL_STATUS_RECEIVING);
		this.labelTagApplyPrintDao.initReceiveBill(headid, userId);
		this.labelTagApplyPrintLineDao.modifyPrintLineStatusByHeadid(headid, DictConstant.BILL_STATUS_RECEIVING, userId);
	}
	
	@Override
	public void closeLabelPrint(String headid, String ornaNotInMater, String ornaNotInOrg, String userId) {
		asertStatus("jat_lable_tag_print", "id", headid, "state", DictConstant.BILL_STATUS_RECEIVING);
		// 检查饰品的是否都在现有量中，组织是否都属于当前组织
		StringBuffer ornaCodes = new StringBuffer();
		if(StringUtil.isNotBlank(ornaNotInMater)){
			ornaCodes.append(ornaNotInMater);
		}
		if(StringUtil.isNotBlank(ornaNotInOrg)){
			if(ornaCodes.length() > 0){
				ornaCodes.append(",").append(ornaNotInOrg);
			} else {
				ornaCodes.append(ornaNotInOrg);
			}
		}
		if(StringUtil.isNotBlank(ornaCodes.toString())){
			this.labelTagApplyPrintLineDao.changeInvalidMater(headid, ornaCodes.toString(), userId);
			this.labelTagApplyPrintDao.modifyLabelPrintNum(headid, userId);
		}
		
		this.labelTagApplyPrintDao.modifyLabelTagApplyPrintStatus(headid, DictConstant.BILL_STATUS_CLOSED, userId);
		this.labelTagApplyDao.modifyLabelTagApplyStatusByPrintId(headid, DictConstant.BILL_STATUS_CLOSED, userId);
		// 修改未接收的行状态为已取消
		this.labelTagApplyPrintLineDao.udpateStateToCancel(headid);
		// 修改申请单行表状态
		this.labelTagApplyPrintDao.updateApplyLineStatus(headid);
		// 修改饰品信息
		this.labelTagApplyPrintDao.modifyMater(headid);
		// 修改现有量的分析范围
		this.labelTagApplyPrintDao.modifyMaterAnalysis(headid);
	}
	
	public Map<String, String> checkPrintOrnaCode(String headid){
		String ornaCodeNotInMater = this.labelTagApplyPrintLineDao.selectOrnaCodeNotInMater(headid);
		String ornaCodeNotInOrg = this.labelTagApplyPrintLineDao.selectOrnaCodeNotInOrg(headid);
		Map<String, String> result = new HashMap<String, String>();
		result.put("not_in_mater", ornaCodeNotInMater);
		result.put("not_in_org", ornaCodeNotInOrg);
		result.put("isSuccess", "true");
		return result;
	}
	
	
	public void updateMaterData(String billids, String userId){
		String billidArr[] = billids.split(";");
		List<Integer> billidList = new ArrayList<Integer>();
		Set<Integer> billidSet = new LinkedHashSet<Integer>();
		for(String billid: billidArr){
			if(StringUtil.isNotBlank(billid)){
				billidSet.add(new Integer(billid.trim()));
			}
		}
		billidList.addAll(billidSet);
		Collections.sort(billidList);
		
		for(Integer billid1 : billidSet){
			String billid = String.valueOf(billid1);
			// 修改现有量
			this.labelTagApplyPrintDao.modifyMater2(billid);

            // 修改现有量的分析范围
            this.labelTagApplyPrintDao.modifyMaterAnalysis2(billid);

			// 修改打印单 头 行 状态
			this.labelTagApplyPrintDao.modifyLabelTagApplyPrintStatus2(billid, DictConstant.BILL_STATUS_CLOSED, userId);
			this.labelTagApplyPrintLineDao.modifyPrintLineStatusByHeadid(billid, DictConstant.BILL_STATUS_RECEIVED, userId);
			// 修改申请单 头 行 状态
			this.labelTagApplyDao.modifyLabelTagApplyStatusByPrintId(billid, DictConstant.BILL_STATUS_CLOSED, userId);
			this.labelTagApplyPrintDao.updateApplyLineStatus(billid);
		}
	}
	
	public void cancelPrintBill(String billids, String userId){
		StringBuffer newBillids = new StringBuffer();
		if(StringUtil.isNotBlank(billids)){
			String billidArr[] = billids.split(";");
			for(String billid: billidArr){
				if(StringUtil.isNotBlank(billid)){
					if(newBillids.length()>0){
						newBillids.append(",").append(billid.trim());
					} else {
						newBillids.append(billid.trim());
					}
				}
			}
			if(newBillids.length()>0){
				this.labelTagApplyPrintDao.cancelPrintLines(newBillids.toString(), userId);
				this.labelTagApplyPrintDao.closeLabelPrints(newBillids.toString(), userId);
			}
			for(String headid : billidArr){
				if(StringUtil.isNotBlank(headid)){
					this.labelTagApplyDao.modifyLabelTagApplyStatusByPrintId(headid, DictConstant.BILL_STATUS_CLOSED, userId);
					this.labelTagApplyPrintDao.updateApplyLineStatus(headid);
				}
			}
		}
	}
	
	
	public void revocatMater(String headids, String userId) {
		if(StringUtil.isNotBlank(headids)){
			String headidArr[] = headids.split(";");
			for(String headid: headidArr){
				if(StringUtil.isNotBlank(headid)){
					asertStatus("jat_lable_tag_print", "id", headid, "state", DictConstant.BILL_STATUS_CLOSED);
					this.labelTagApplyPrintDao.revocatMater(headid);
                    // 修改现有量的分析范围
                    this.labelTagApplyPrintDao.modifyMaterAnalysis2(headid);
					this.labelTagApplyPrintDao.initReceiveBill(headid, userId);
					this.labelTagApplyPrintLineDao.modifyPrintLineStatusByHeadid(headid, DictConstant.BILL_STATUS_RECEIVING, userId);
					this.labelTagApplyPrintDao.modifyLabelTagApplyPrintStatus(headid, DictConstant.BILL_STATUS_RECEIVING, userId);
					
					this.labelTagApplyDao.modifyLabelTagApplyStatusByPrintId(headid, DictConstant.BILL_STATUS_RECEIVING, userId);
					this.labelTagApplyPrintDao.updateApplyLineStatus(headid);
				}
			}
		}
	}
	
	@Override
	public Pager getLabelTagApplyPrintLineData(Map<String, String> condition) {
		return this.labelTagApplyPrintLineDao.getLabelTagApplyPrintLineData(condition);
	}
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
