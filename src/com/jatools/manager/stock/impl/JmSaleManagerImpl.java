package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.stock.JmSaleDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.stock.JmSaleManager;
import com.jatools.vo.stock.JmSaleHead;
import com.jatools.vo.stock.JmSaleLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class JmSaleManagerImpl extends BaseManager implements JmSaleManager {

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getJmSaleData(Map<String, String> condition) {
		return this.jmSaleDao.getJmSaleData(condition);
	}

	@Override
	public void saveOrUpdateJmSaleHead(JmSaleHead JmSaleHead,
			List<JmSaleLine> lines, String deleteIds, String userId) {
		this.saveUpdateCheckJmSaleHead(JmSaleHead, lines, deleteIds, userId, false);
	}

	@Override
	public void saveAndCheckJmSaleHead(JmSaleHead JmSaleHead,
			List<JmSaleLine> lines, String deleteIds, String userId) {
		this.saveUpdateCheckJmSaleHead(JmSaleHead, lines, deleteIds, userId, true);
	}
	
	private void saveUpdateCheckJmSaleHead(JmSaleHead JmSaleHead, List<JmSaleLine> lines, String deleteIds, String userId, boolean isCheck){
		JmSaleHead.setUpdateDate(DateUtil.getCurrentDate18());
		JmSaleHead.setUpdateId(userId);
		if(StringUtil.isNotBlank(JmSaleHead.getBillid())){
			asertStatus("jat_JmSale_head", "billid", JmSaleHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.jmSaleDao.updateJmSaleHead(JmSaleHead);
		} else {
			JmSaleHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_JM_SALE));
			JmSaleHead.setCreateDate(DateUtil.getCurrentDate18());
			JmSaleHead.setCreateId(userId);
			this.jmSaleDao.saveJmSaleHead(JmSaleHead);
		}
		if(StringUtil.isNotBlank(deleteIds)){
			String lineids[] = deleteIds.split(";");
			for(String lineid : lineids){
				if(StringUtil.isNotBlank(lineid)){
					this.jmSaleDao.deleteLineById(lineid);
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(JmSaleLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				if(StringUtil.isNotBlank(line.getLineid())){
					this.jmSaleDao.updateLine(line);
				} else {
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					line.setBillid(JmSaleHead.getBillid());
					this.jmSaleDao.insertLine(line);
				}
			}
		}
		
		if(isCheck){
			this.jmSaleDao.modifyJmSaleHeadStatus(JmSaleHead.getBillid(), DictConstant.BILL_STATUS_REVIEWING, userId);
			this.workflowService.enterReview(JmSaleHead, userId);
		} else {
			this.jmSaleDao.modifyJmSaleHeadStatus(JmSaleHead.getBillid(), DictConstant.BILL_STATUS_SAVE, userId);
		}
		
	}
	
	@Override
	public JmSaleHead getJmSaleHead(String billid) {
		return this.jmSaleDao.getJmSaleHead(billid);
	}

	@Override
	public void deleteJmSaleHead(String billid) {
		asertStatus("jat_JmSale_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.jmSaleDao.deleteLineByBillid(billid);
		this.jmSaleDao.deleteJmSaleHead(billid);
	}

	@Override
	public List<JmSaleLine> getLines(String billid) {
		return jmSaleDao.getLines(billid);
	}

	
	public void closeJmSale(String billid, String userId) {
		asertStatus("jat_JmSale_head", "billid", billid, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.jmSaleDao.modifyJmSaleHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
	}
	
	private CommonDao commonDao;
	private JmSaleDao jmSaleDao;
	private WorkflowService workflowService;
	

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}
	
	public void setJmSaleDao(JmSaleDao JmSaleDao) {
		this.jmSaleDao = JmSaleDao;
	}
	
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
}
