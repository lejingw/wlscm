package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.pur.HandoverDao;
import com.jatools.dao.stock.ProcExitHeadDao;
import com.jatools.dao.stock.ProcExitLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.stock.ProcExitHeadManager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.vo.stock.ProcExitHead;
import com.jatools.vo.stock.ProcExitLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;
/**
 * 退料单头表Manager 实现类
 * @author ren.ming
 * <br>
 * Created 2011-11-20
 */
public class ProcExitHeadManagerImpl extends BaseManager implements ProcExitHeadManager {
	
	private ProcExitHeadDao procExitHeadDao;
	private ProcExitLineDao procExitLineDao;
	private HandoverDao handoverDao;
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setHandoverDao(HandoverDao handoverDao) {
		this.handoverDao = handoverDao;
	}
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	@Override
	public Pager getProcExitHeadData(Map<String, String> condition) {
		return this.procExitHeadDao.getProcExitHeadData(condition);
	}public Pager getReportProcExitHeadData(Map<String, String> condition) {
		return this.procExitHeadDao.getReportProcExitHeadData(condition);
	}

	@Override
	public void saveProcExitHead(ProcExitHead ProcExitHead) {
		this.procExitHeadDao.saveProcExitHead(ProcExitHead);

	}

	@Override
	public ProcExitHead getProcExitHead(String billid) {
		return this.procExitHeadDao.getProcExitHead(billid);
	}

	@Override
	public void updateProcExitHead(ProcExitHead ProcExitHead) {
		this.procExitHeadDao.updateProcExitHead(ProcExitHead);

	}
	
	@Override
	public void deleteProcExitHead(String billid, String userId) {
		asertStatus("jat_proc_exit_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		//List<ProcExitLine> exitLines = this.procExitLineDao.getProcExitLineList(billid);
		this.procExitHeadDao.subProdAccountUserNums(billid, userId);
		this.procExitLineDao.deleteProcExitLineByBillid(billid);
		this.procExitHeadDao.deleteProcExitHead(billid);
	}
	
	
	public void saveOrUpdateProcExitHead(ProcExitHead procExitHead, List<ProcExitLine> exitLines, String ids, String userId) {
		this.saveUpdateCheckProcExitHead(procExitHead, exitLines, ids, userId, false);
	}
	
	public void saveAndCheckProcExitHead(ProcExitHead procExitHead, List<ProcExitLine> exitLines, String ids, String userId) {
		this.saveUpdateCheckProcExitHead(procExitHead, exitLines, ids, userId, true);
	}
	
	private void saveUpdateCheckProcExitHead(ProcExitHead procExitHead, List<ProcExitLine> exitLines, String ids, String userId, boolean isCheck) {
		procExitHead.setUpdateDate(DateUtil.getCurrentDate18());
		procExitHead.setUpdateId(userId);
		// 1、先把头表保存
		if (StringUtil.isNotBlank(procExitHead.getBillid())) {
			asertStatus("jat_proc_exit_head", "billid", procExitHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.procExitHeadDao.updateProcExitHead(procExitHead);
		} else {
			this.procExitHeadDao.saveProcExitHead(procExitHead);
		}
		// 删除行表部分数据
		if(StringUtil.isNotBlank(ids)) {
			String idArray[] = ids.split(";");
			if(!ArrayUtils.isEmpty(idArray)) {
				for(String lineid : idArray) {
					if(StringUtil.isNotBlank(lineid)) {
						//this.procExitHeadDao.modifyProdAccountStatusValid(lineid, procExitHead.getUpdateId());
						this.procExitLineDao.deleteProcExitLine(lineid, userId);
					}
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(exitLines)) {
			for(ProcExitLine exitLine : exitLines) {
				// 判断台账是否能满足条件
				this.asertProdAccount(exitLine);
				
				if(StringUtil.isNotBlank(exitLine.getLineid())) {
					exitLine.setUpdateDate(DateUtil.getCurrentDate18());
					exitLine.setUpdateId(procExitHead.getUpdateId());
					this.procExitLineDao.updateProcExitLine(exitLine);
				} else {
					exitLine.setBillid(procExitHead.getBillid());
					exitLine.setCreateDate(DateUtil.getCurrentDate18());
					exitLine.setCreateId(procExitHead.getCreateId());
					exitLine.setStatus(DictConstant.BILL_STATUS_SAVE);
					exitLine.setUpdateDate(DateUtil.getCurrentDate18());
					exitLine.setUpdateId(procExitHead.getUpdateId());
					this.procExitLineDao.saveProcExitLine(exitLine);
					
					// 修改台账为 引用状态
					//this.procExitHeadDao.modifyProdAccountStatusInValid(exitLine.getBillno(), exitLine.getOrnaNo(), procExitHead.getUpdateId());
				}
			}
		}
		if(isCheck){
			workflowService.enterReview(procExitHead, userId);
			this.procExitHeadDao.modifyProcExitHeadStatus(procExitHead.getBillid(), DictConstant.BILL_STATUS_REVIEWING, userId);
		}
	}
	
	private void asertProdAccount(ProcExitLine line){
		CashProdAccount prod = this.procExitHeadDao.getProdAccountByOut(line.getBillno(), line.getOrnaNo());
		if(prod != null){
			double userNums = 0;
			double noNums = 0;
			double curNums = 0;
			double oldCurNums = 0;
			if(StringUtil.isNotBlank(prod.getUserNums())){
				userNums = Double.valueOf(prod.getUserNums());
			}
			if(StringUtil.isNotBlank(prod.getNoNums())){
				noNums = Double.valueOf(prod.getNoNums());
			}
			if(StringUtil.isNotBlank(line.getExitNums())){
				userNums = Double.valueOf(line.getExitNums());
			}
			if(StringUtil.isNotBlank(line.getOldExitNums())){
				oldCurNums = Double.valueOf(line.getOldExitNums());
			}
			if(StringUtil.isNotBlank(line.getLineid())){
				if(curNums - oldCurNums !=0){
					if(noNums - userNums + oldCurNums - curNums <0){
						throw new RuntimeException("台账实际可核销数量不足");
					}
				}
			} else {
				if(noNums - userNums - curNums <0){
					throw new RuntimeException("台账实际可核销数量不足");
				}
			}
		} else {
			throw new RuntimeException("台账实际可核销数量为0或者台账不存在");
		}
	}
	
	@Override
	public void closeExitHead(String billid, String userId) {
		asertStatus("jat_proc_exit_head", "billid", billid, "status", DictConstant.BILL_STATUS_REVIEWED);
		// TODO : 1、修改状态为关闭
		this.procExitHeadDao.modifyProcExitHeadStatus(billid, DictConstant.BILL_STATUS_CLOSING, userId);
		// List<ProcExitLine> exitLines = this.procExitLineDao.getProcExitLineList(billid);
		
		HandoverHead handoverHead = this.handoverDao.getHandoverHeadBySrcBillId(billid, GlobalConstant.BILL_CODE_TUILIAO);// 头表
		if(null == handoverHead){ // 如果交接单 不存在则创建
			ProcExitHead head = this.procExitHeadDao.getProcExitHead(billid);
			handoverHead  = new HandoverHead();
			handoverHead.setSourceId(head.getBillid());// 来源单据id
			handoverHead.setSourceNo(head.getBillno());// 来源单据单号
			handoverHead.setSourceType(head.getBillCode());// 来源单据类型
			handoverHead.setOrgId(head.getOrgId());// 组织id
			
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
			handoverHead.setBillno(billno);
			handoverHead.setStatus(DictConstant.BILL_STATUS_SAVE);
			handoverHead.setDodate(DateUtil.getCurrentDate10());
			handoverHead.setDotype(DictConstant.BILL_JJ_CG);
			handoverHead.setCreateId(userId);
			handoverHead.setCreateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateId(userId);
			handoverHead.setVerdorId(head.getVendorId());
			this.handoverDao.saveHandoverHead(handoverHead);
		}
	}
	
	@Override
	public void checkBillHead(String billid, String userId) {
		asertStatus("jat_proc_exit_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.procExitHeadDao.modifyProcExitHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWING, userId);
		
		ProcExitHead procExitHead = procExitHeadDao.getProcExitHead(billid);
		//进入审批
		workflowService.enterReview(procExitHead, userId);
		
	}

	public void setProcExitHeadDao(ProcExitHeadDao ProcExitHeadDao) {
		this.procExitHeadDao = ProcExitHeadDao;
	}
	public void setProcExitLineDao(ProcExitLineDao procExitLineDao) {
		this.procExitLineDao = procExitLineDao;
	}
	

	public CommonDao getCommonDao() {
		return this.commonDao;
	}
	@Override
	public void cashCharge(String billid, String charge, String userId) {
		asertStatus("jat_proc_exit_head", "billid", billid, "status", DictConstant.BILL_STATUS_CLOSED);
		// 查询原工费
		String oldCharge1 = "0";
		ProcExitHead head = this.procExitHeadDao.getProcExitHead(billid);
		if(head != null ){
			if(StringUtil.isNotBlank(head.getCharge())){
				oldCharge1 = head.getCharge();
			}
		}
		if(StringUtil.isBlank(charge)){
			charge = "0";
		}
		double oldCharge = Double.valueOf(oldCharge1);
		double newCharge = Double.valueOf(charge);
		
		if(Math.abs(oldCharge-newCharge) <= 0.0001){
			throw new RuntimeException("加工费未改变，无需保存");
		}
		if(oldCharge > 0){
			// 冲销原台账
			this.procExitHeadDao.saveCashMoneyAccount(billid, 0-oldCharge, -1, userId);
		}
		if(newCharge > 0){
			// 写一笔新台账
			this.procExitHeadDao.saveCashMoneyAccount(billid, newCharge, -1, userId);
		}
		// 修改头信息
		head.setIsCheck("1");
		head.setCharge(newCharge+"");
		head.setUpdateDate(DateUtil.getCurrentDate18());
		head.setUpdateId(userId);
		this.procExitHeadDao.updateProcExitHead(head);
	}

    @Override
    public ProcExitHead getProcExitHeadByBillno(String billno) {
        return this.procExitHeadDao.getProcExitHeadByBillno(billno);
    }
}
