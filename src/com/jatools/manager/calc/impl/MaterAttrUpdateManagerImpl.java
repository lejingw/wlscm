package com.jatools.manager.calc.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.calc.MaterAttrUpdateDao;
import com.jatools.manager.calc.MaterAttrUpdateManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.calc.MaterAttrUpdate;
import com.jatools.vo.stock.MaterActive;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class MaterAttrUpdateManagerImpl implements MaterAttrUpdateManager{

	private MaterAttrUpdateDao materAttrUpdateDao;
	private WorkflowService workflowService;
	private CommonManager commonManager;
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	
	public void setMaterAttrUpdateDao(MaterAttrUpdateDao materAttrUpdateDao) {
		this.materAttrUpdateDao = materAttrUpdateDao;
	}

	@Override
	public Pager getMaterAttrUpdatePageData(Map<String, String> conditionMap) {
		return this.materAttrUpdateDao.getMaterAttrUpdatePageData(conditionMap);
	}

	@Override
	public void saveMaterAttrUpdate(MaterAttrUpdate MaterAttrUpdate) {
		this.materAttrUpdateDao.saveMaterAttrUpdate(MaterAttrUpdate);
	}

	@Override
	public MaterAttrUpdate getMaterAttrUpdateById(String id) {
		return this.materAttrUpdateDao.getMaterAttrUpdateById(id);
	}

	@Override
	public void updateMaterAttrUpdate(MaterAttrUpdate MaterAttrUpdate) {
		this.materAttrUpdateDao.updateMaterAttrUpdate(MaterAttrUpdate);
	}

	@Override
	public void deleteMaterAttrUpdate(String id) {
		this.materAttrUpdateDao.deleteMaterAttrUpdate(id);
	}

	public MaterActive getMaterByCode(Map<String,String> conditionMap){
		return this.materAttrUpdateDao.getMaterByCode(conditionMap);
	}
	
	public String saveUpdateMater(MaterAttrUpdate head ,HttpServletRequest req){
		if(StringUtil.isEmpty(head.getBillid())||null==head.getBillid()){
			head.setBillno(commonManager.getBillno(GlobalConstant.BILL_CODE_MATERATTRUPDATE));
			head.setCreatedate(DateUtil.getCurrentDate18());
			head.setCreateid(CommonUtil.getSessionUserId(req));
			materAttrUpdateDao.saveMaterAttrUpdate(head);
		}else{
			MaterAttrUpdate old = materAttrUpdateDao.getMaterAttrUpdateById(head.getBillid());
			if(old==null){
				return "2_保存出错,单据以被删除";
			}else if(!DictConstant.BILL_STATUS_SAVE.equals(old.getStatus())){
				return "2_保存出错,单据状态以被修改";
			}else{
				head.setUpdatedate(DateUtil.getCurrentDate18());
				head.setUpdateid(CommonUtil.getSessionUserId(req));
				materAttrUpdateDao.updateMaterAttrUpdate(head);
			}
		}
		if(DictConstant.BILL_STATUS_REVIEWING.equals(head.getStatus())){//进审核流
			workflowService.enterReview(head, head.getCreateid());
//			head.setStatus(DictConstant.BILL_STATUS_REVIEWED);
//			this.materAttrUpdateDao.updateMaterAttrUpdate(head);
		}
		return "1_操作成功";
	}

	@Override
	public String changeHeadStatus(String status, MaterAttrUpdate head,
			HttpServletRequest req) {
		if(DictConstant.BILL_STATUS_CLOSED.equals(status)){
			//关闭操作
			this.materAttrUpdateDao.updateMaterActionByMB(head);//现有量
			this.materAttrUpdateDao.updateMaterInivByMB(head);//单品
			this.materAttrUpdateDao.updatePriceByMB(head);//核价
			this.materAttrUpdateDao.modifyMaterStatus(head.getBillid(), status, CommonUtil.getSessionUserId(req));
			
		}
		return "操作成功";
	}

}
