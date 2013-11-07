package com.jatools.manager.out.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.out.ConsumeDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.out.ConsumeManager;
import com.jatools.vo.out.Consume;
import com.jatools.vo.out.ConsumeLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class ConsumeManagerImpl extends BaseManager implements ConsumeManager{

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getConsumePageData(Map<String, String> conditionMap) {
		return this.consumeDao.getConsumePageData(conditionMap);
	}
	
	@Override
	public void saveOrUpdateConsume(Consume Consume, List<ConsumeLine> lines,
			String deleteIds, String userId) {
		this.saveUpdateCheckConsume(Consume, lines, deleteIds, userId, false);
	}
	@Override
	public void saveAndCheckConsume(Consume Consume, List<ConsumeLine> lines,
			String deleteIds, String userId) {
		this.saveUpdateCheckConsume(Consume, lines, deleteIds, userId, true);
	}
	
	private void saveUpdateCheckConsume(Consume consume, List<ConsumeLine> lines,
			String deleteIds, String userId, boolean isCheck){
		consume.setUpdateDate(DateUtil.getCurrentDate18());
		consume.setUpdateId(userId);
		if(StringUtil.isNotBlank(consume.getBillid())){
			asertStatus("JAT_PROC_MAINTAIN_CONSUME", "billid", consume.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.consumeDao.updateConsume(consume);
		} else {
			consume.setCreateDate(DateUtil.getCurrentDate18());
			consume.setCreateId(userId);
			consume.setBillno(this.commonDao.getBillno(GlobalConstant.BILL_CODE_CONSUM));
			this.consumeDao.saveConsume(consume);
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String lineids[] = deleteIds.split(";");
			for(String lineid : lineids){
				if(StringUtil.isNotBlank(lineid)){
					this.consumeDao.deleteLineById(lineid);
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(ConsumeLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				if(StringUtil.isNotBlank(line.getLineid())){
					this.consumeDao.updateLine(line);
				} else {
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					line.setBillid(consume.getBillid());
					this.consumeDao.insertLine(line);
				}
			}
		}
		if(isCheck){
			this.consumeDao.updateConsumStatus(consume.getBillid(), DictConstant.BILL_STATUS_REVIEWING, userId);
			this.workflowService.enterReview(consume, userId);
		} else {
			this.consumeDao.updateConsumStatus(consume.getBillid(), DictConstant.BILL_STATUS_SAVE, userId);
		}
	}
	
	@Override
	public Consume getConsumeById(String billid) {
		return this.consumeDao.getConsumeById(billid);
	}
	
	@Override
	public void deleteConsume(String billid) {
		asertStatus("JAT_PROC_MAINTAIN_CONSUME", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.consumeDao.deleteLineByBillid(billid);
		this.consumeDao.deleteConsume(billid);
	}
	
	@Override
	public List<ConsumeLine> getLines(String billid) {
		return this.consumeDao.getLines(billid);
	}
	
	public void closeConsume(String billid, String userId){
		asertStatus("JAT_PROC_MAINTAIN_CONSUME", "billid", billid, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.consumeDao.insertMoneyAccountByConsume(billid, userId);
		this.consumeDao.updateConsumStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
	}
	
	
	private ConsumeDao consumeDao;
	private WorkflowService workflowService;
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao(){
		return commonDao;
	}
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	
	public void setConsumeDao(ConsumeDao consumeDao) {
		this.consumeDao = consumeDao;
	}
}
