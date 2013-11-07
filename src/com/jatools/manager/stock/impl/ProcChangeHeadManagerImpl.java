package com.jatools.manager.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.dao.pur.HandoverDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.stock.ProcChangeHeadDao;
import com.jatools.dao.stock.ProcChangeLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.stock.ProcChangeHeadManager;
import com.jatools.vo.bd.Style;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.vo.stock.ProcChangeLine;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;
/**
 * 形态转换单头表Manager 实现类
 * @author ren.ming
 * <br>
 * Created 2011-11-20
 */
public class ProcChangeHeadManagerImpl extends BaseManager implements ProcChangeHeadManager {
	
	private ProcChangeHeadDao procChangeHeadDao;
	private ProcChangeLineDao ProcChangeLineDao;
	private MaterActiveDao materActiveDao;
	private BdCommonDao bdCommonDao;
	private HandoverDao handoverDao;
	private CommonDao commonDao;
	
	//private static double FAX = 1.17;
	
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	@Override
	public Pager getProcChangeHeadData(Map<String, String> condition) {
		return this.procChangeHeadDao.getProcChangeHeadData(condition);
	}

	@Override
	public void saveProcChangeHead(ProcChangeHead ProcChangeHead) {
		this.procChangeHeadDao.saveProcChangeHead(ProcChangeHead);

	}

	@Override
	public ProcChangeHead getProcChangeHead(String billid) {
		return this.procChangeHeadDao.getProcChangeHead(billid);
	}

	@Override
	public void updateProcChangeHead(ProcChangeHead ProcChangeHead) {
		this.procChangeHeadDao.updateProcChangeHead(ProcChangeHead);

	}
	
	@Override
	public void deleteProcChangeHead(String billid) {
		asertStatus("jat_proc_change_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		List<ProcChangeLine> oldLines = this.ProcChangeLineDao.getProcChangeLineList(billid);
		
		for(ProcChangeLine oldLine : oldLines) {// 修改现有量表数据状态
			this.materActiveDao.markMaterActiveValid(oldLine.getOrnaCode());
		}
		this.ProcChangeLineDao.deleteProcChangeLineByBillid(billid);
		this.procChangeHeadDao.deleteProcChangeHead(billid);
	}
	
	
	public void saveOrUpdateProcChangeHead(ProcChangeHead procChangeHead, List<ProcChangeLine> lines, String ids) {
		this.saveUpdateCheckProcChangeHead(procChangeHead, lines, ids, false);
	}
	
	@Override
	public void saveAndCheckProcChangeHead(ProcChangeHead procChangeHead, List<ProcChangeLine> lines, String ids) {
		this.saveUpdateCheckProcChangeHead(procChangeHead, lines, ids, true);
	}

	private void saveUpdateCheckProcChangeHead(ProcChangeHead procChangeHead, List<ProcChangeLine> lines, String ids, boolean isCheck) {
		// 1、先把头表保存
		procChangeHead.setSumWeight(StringUtil.formatDouble(procChangeHead.getSumWeight(), 2));
		procChangeHead.setSumMoney(StringUtil.formatDouble(procChangeHead.getSumMoney(), 6));
		if(isCheck){
			procChangeHead.setStatus(DictConstant.BILL_STATUS_REVIEWING);
		}
		if (StringUtil.isNotBlank(procChangeHead.getBillid())) {
			asertStatus("jat_proc_change_head", "billid", procChangeHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.procChangeHeadDao.updateProcChangeHead(procChangeHead);
		} else {
			this.procChangeHeadDao.saveProcChangeHead(procChangeHead);
		}
		
		if(StringUtil.isNotBlank(ids)) {
			String oldIds[] = ids.split(";");
			if(!ArrayUtils.isEmpty(oldIds)) {
				// TODO 删除 原饰品行表数据 ren.ming 完成
				for(String oldId: oldIds) {
					if(StringUtil.isNotBlank(oldId)) {
						String lineid = oldId.split(":")[0];
						String ornaCode = oldId.split(":")[1];
						if(StringUtil.isNotBlank(lineid) && StringUtil.isNotBlank(ornaCode)) {
							this.ProcChangeLineDao.deleteProcChangeLine(lineid);
							// TODO 修改现有量表数据状态为 900 ren.ming 完成
							
							this.materActiveDao.markMaterActiveValid(ornaCode);
						}
					}
				}
			}
		}
		StringBuffer ornaCodes = new StringBuffer();
		for(ProcChangeLine oldLine : lines) {
			if(ornaCodes.length()>0){
				ornaCodes.append(",").append(oldLine.getOrnaCode());
			} else {
				ornaCodes.append(oldLine.getOrnaCode());
			}
			if(StringUtil.isEmpty(oldLine.getGrains())) {
				oldLine.setGrains("0");
			}
			if(StringUtil.isNotBlank(oldLine.getStyleId())) {
				Style style = bdCommonDao.getStyleById(oldLine.getStyleId());
				if(style != null) {// 取款式 大类 中类 小类 id  
					oldLine.setStyleitemclass(style.getStyleitemclass());
					oldLine.setStylemiddleclass(style.getStylemiddleclass());
					oldLine.setStyleornaclass(style.getStyleornaclass());
				}
			}
			if(StringUtil.isBlank(oldLine.getLineid())) {
				// 保存
				oldLine.setBillid(procChangeHead.getBillid());
				oldLine.setCreateDate(DateUtil.getCurrentDate18());
				oldLine.setCreateId(procChangeHead.getCreateId());
				oldLine.setStatus(DictConstant.BILL_STATUS_SAVE);
				oldLine.setUpdateDate(DateUtil.getCurrentDate18());
				oldLine.setUpdateId(procChangeHead.getUpdateId());
				
				this.ProcChangeLineDao.saveProcChangeLine(oldLine);
				
				// TODO 修改现有量状态位901 待处理 ren.ming
				this.materActiveDao.markMaterActiveUsed(oldLine.getOrnaCode(), procChangeHead.getBillCode(), procChangeHead.getBillno());
			} else {
				oldLine.setUpdateDate(DateUtil.getCurrentDate18());
				oldLine.setUpdateId(procChangeHead.getUpdateId());
				this.ProcChangeLineDao.updateProcChangeLine(oldLine);
			}
		}
		if(isCheck){
			//进入审批
			workflowService.enterReview(procChangeHead, procChangeHead.getUpdateId());
		}
	}
	
	public void closeChangeHead(String billid, String userId) {
		// TODO : 1、修改状态为关闭
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", DictConstant.BILL_STATUS_CLOSING);
		this.procChangeHeadDao.modifyProcChangeHeadStatus(billid, DictConstant.BILL_STATUS_CLOSING, userId);
		
		// 如果交接单未创建   那么就在关闭单据时创建
		ProcChangeHead head = this.procChangeHeadDao.getProcChangeHead(billid);
		HandoverHead handoverHead = this.handoverDao.getHandoverHeadBySrcBillId(billid, head.getBillCode());// 头表
		if(null == handoverHead ){
			handoverHead = new HandoverHead();// 头表
			handoverHead.setSourceId(head.getBillid());// 来源单据id
			handoverHead.setSourceNo(head.getBillno());// 来源单据单号
			handoverHead.setSourceType(head.getBillCode());// 来源单据类型
			handoverHead.setOrgId(head.getOrgId());// 组织id
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
			handoverHead.setBillno(billno);
			handoverHead.setStatus(DictConstant.BILL_STATUS_SAVE);
			handoverHead.setDodate(DateUtil.getCurrentDate10());
			handoverHead.setDotype(DictConstant.BILL_JJ_ZYZH);
			handoverHead.setCreateId(userId);
			handoverHead.setCreateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateId(userId);
			String vendorId = BdCommon.getParameterValue(ParameterConstant.CHANGE_VENDOR_ID);
			handoverHead.setVerdorId(vendorId);
			this.handoverDao.saveHandoverHead(handoverHead);
		}
		
	}	
	
	
	public void checkChangeHead(String billid, String userId) {
		asertStatus("jat_proc_change_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.procChangeHeadDao.modifyProcChangeHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWING, userId);
		
		ProcChangeHead procChangeHead = procChangeHeadDao.getProcChangeHead(billid);
		//进入审批
		workflowService.enterReview(procChangeHead, userId);
	}
	
	
	public void setProcChangeLineDao(ProcChangeLineDao ProcChangeLineDao) {
		this.ProcChangeLineDao = ProcChangeLineDao;
	}

	
	public void setProcChangeHeadDao(ProcChangeHeadDao ProcChangeHeadDao) {
		this.procChangeHeadDao = ProcChangeHeadDao;
	}

	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}

	public void setHandoverDao(HandoverDao handoverDao) {
		this.handoverDao = handoverDao;
	}

	public void setBdCommonDao(BdCommonDao bdCommonDao) {
		this.bdCommonDao = bdCommonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}
	

}
