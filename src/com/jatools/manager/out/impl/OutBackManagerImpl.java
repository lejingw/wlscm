package com.jatools.manager.out.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.out.OutBackDao;
import com.jatools.dao.out.OutVendorDao;
import com.jatools.manager.out.OutBackManager;
import com.jatools.vo.out.OutBackHead;
import com.jatools.vo.out.OutBackLine;
import com.jatools.vo.out.OutOrna;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class OutBackManagerImpl implements OutBackManager {
	private OutBackDao outBackDao;
	private OutVendorDao outVendorDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public void setOutBackDao(OutBackDao outBackDao) {
		this.outBackDao = outBackDao;
	}
	public Pager getBackHeadPage(Map<String, String> condition) {
		return outBackDao.getBackHeadPage(condition);
	}
	@Override
	public OutOrna getOrnaInfo(String code, boolean ornaFlag) {
		return outBackDao.getOrna(code, ornaFlag);
	}
	@Override
	public void saveOrUpdateOutBack(OutBackHead head,
			List<OutBackLine> lines, String[] delIds) {
		String headId =head.getHeadId();
		if(StringUtil.isNotEmpty(headId)){
			if(delIds!=null&&delIds.length>0){
				String ids="0";
				for(String id : delIds)
					ids+=","+id;
				outBackDao.deleteLines(ids);
			}
			outBackDao.updateHead(head);
		}else{
			headId=outBackDao.insertHead(head);
		}
		for(OutBackLine line : lines){
			line.setHeadId(headId);
			line.setUpdateDate(head.getUpdateDate());
			line.setUpdateId(head.getUpdateId());
			if(StringUtil.isNotEmpty(line.getLineId()))
				outBackDao.updateLine(line);
			else{
				line.setCreateDate(head.getUpdateDate());
				line.setCreateId(head.getUpdateId());
				outBackDao.insertLine(line);
			}
			outVendorDao.changeMaterStatus(line.getOrnaCode(),false,"JC",head.getBillNo());
			
		}
		if(DictConstant.BILL_STATUS_REVIEWING.equals(head.getStatus())){
			workflowService.enterReview(head, head.getUpdateId());//进入审批流
		}
	}
	@Override
	public OutBackHead getBackHead(String headId) {
		return outBackDao.getBackHead(headId);
	}
	@Override
	public List<OutBackLine> getBackLines(String headId) {
		return outBackDao.getBackLines(headId);
	}
	@Override
	public void deleteBack(String headId) {
		List<OutBackLine> list=outBackDao.getBackLines(headId);
		for(OutBackLine line : list){
			outVendorDao.changeMaterStatus(line.getOrnaCode(),true,null,null);
		}
		outBackDao.deleteLinesByHeadId(headId);
		outBackDao.deleteHead(headId);
	}

	private void moveOrna(OutBackHead head,boolean noActive, String ids){
		List<OutBackLine> list=outBackDao.getBackLines(head.getHeadId());
		for(OutBackLine line : list){
			if(noActive&&(","+ids+",").indexOf(","+line.getLineId()+",")==-1)
				continue;
			outVendorDao.moveOrna(line.getOrnaCode(), noActive);
			String finaceType=outVendorDao.getFinaceType("JC");
			if(noActive){
				outVendorDao.deleteOrnaNo(line.getOrnaCode());
//				outVendorDao.changeMaterStatus(line.getOrnaCode(),true);
				if(StringUtil.isNotEmpty(line.getPosCost()))
					line.setTransCost(""+(0-Double.parseDouble(line.getPosCost())));
				if(StringUtil.isNotEmpty(line.getPosMoney()))
					line.setTransMoney(""+(0-Double.parseDouble(line.getPosMoney())));
				if(StringUtil.isNotEmpty(line.getNowQty()))
					line.setTransQty(""+(0-Double.parseDouble(line.getNowQty())));
			}
			else{
				line.setTransCost(line.getPosCost());
				line.setTransMoney(line.getPosMoney());
				line.setTransQty(line.getNowQty());
				outVendorDao.deleteOrna(line.getOrnaCode());
			}
			line.setOrgId(head.getOrgId());
			line.setBillNo(head.getBillNo());
			line.setUpdateDate(head.getUpdateDate());
			line.setUpdateId(head.getUpdateId());
			line.setBillType("JC");
			line.setFinaceType(finaceType);
			line.setCreateDate(head.getDoDate());
			line.setStockId(head.getStockId());
			outBackDao.saveMaterTrans(line);
		}
	}
	@Override
	public void changeHeadStatus(OutBackHead head) {
		outBackDao.changeHeadStatus(head);
//		String status=head.getStatus();
//		if(DictConstant.BILL_STATUS_OUT.equals(status))
//			moveOrna(head,false, null);
//		else if(DictConstant.BILL_STATUS_INVALID.equals(status))
//			moveOrna(head,true, null);
			
			
	}
	public void setOutVendorDao(OutVendorDao outVendorDao) {
		this.outVendorDao = outVendorDao;
	}
	@Override
	public OutBackLine getBackLine(String headId, String ornaCode, boolean ornaFlag) {
		return outBackDao.getBackLine(headId, ornaCode, ornaFlag);
	}
	@Override
	public void backLines(String ids,OutBackHead head) {
		if(StringUtil.isNotEmpty(ids)){
			outBackDao.backLines(ids, head.getBackDate(), head.getBackBody());
			outBackDao.updateHead(head);
			List<OutBackLine> list=outBackDao.getBackLines(head.getHeadId());
			for(OutBackLine line : list){
				if((","+ids+",").indexOf(","+line.getLineId()+",")==-1)
					continue;
				outVendorDao.changeMaterStatus(line.getOrnaCode(),true,null,null);
			}
//			moveOrna(head,true, ids);
		}
	}
	
}
