package com.jatools.manager.inout.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.inout.ReceiveHeadDao;
import com.jatools.dao.inout.ReceiveLineDao;
import com.jatools.dao.pur.HandoverDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.inout.ReceiveHeadManager;
import com.jatools.vo.inout.ReceiveHead;
import com.jatools.vo.inout.ReceiveLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class ReceiveHeadManagerImpl extends BaseManager implements ReceiveHeadManager {

	private ReceiveHeadDao receiveHeadDao;
	private ReceiveLineDao receiveLineDao;
	private CommonDao commonDao;
	private HandoverDao handoverDao;
	
	public void setHandoverDao(HandoverDao handoverDao) {
		this.handoverDao = handoverDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setReceiveHeadDao(ReceiveHeadDao receiveHeadDao) {
		this.receiveHeadDao = receiveHeadDao;
	}
	public void setReceiveLineDao(ReceiveLineDao receiveLineDao) {
		this.receiveLineDao = receiveLineDao;
	}

	@Override
	public Pager getReceiveHeadData(Map<String, String> condition) {
		return this.receiveHeadDao.getReceiveHeadData(condition);
	}

	@Override
	public void saveReceiveHead(ReceiveHead receiveHead) {
		this.receiveHeadDao.saveReceiveHead(receiveHead);
	}

	@Override
	public void saveOrUpdateReceiveHead(ReceiveHead receiveHead, List<ReceiveLine> lines, String deleteIds, String userId) {
		Set<String> handBillNos = new HashSet<String>();// 待处理的交接单号
		receiveHead.setUpdateDate(DateUtil.getCurrentDate18());
		receiveHead.setUpdateId(userId);
		if(StringUtil.isNotBlank(receiveHead.getBillid())){
			asertStatus("jat_bill_receive_head", "billid", receiveHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.receiveHeadDao.updateReceiveHead(receiveHead);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_SHOUPIAO);
			receiveHead.setBillno(billno);
			receiveHead.setCreateDate(DateUtil.getCurrentDate18());
			receiveHead.setCreateId(userId);
			this.receiveHeadDao.saveReceiveHead(receiveHead);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(ReceiveLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				if(StringUtil.isNotBlank(line.getLineid())){
					this.receiveLineDao.updateReceiveLine(line);
				} else {
					line.setBillid(receiveHead.getBillid());
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					this.receiveLineDao.saveReceiveLine(line);
					handBillNos.add(line.getHandNo());
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						this.receiveLineDao.modifyHandoverBillStatus(lineid, DictConstant.HANDOVER_IS_NOT_BILL, userId);
						this.receiveLineDao.deleteReceiveLine(lineid);
					}
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(handBillNos)){
			for(String handBillno : handBillNos){
				// 修改交接单 是否收票的状态
				this.handoverDao.modifyHandoverReceiveStatus(handBillno, DictConstant.HANDOVER_IS_BILL, userId);
			}
		}
	}
	
	public void saveAndCloseReceiveHead(ReceiveHead receiveHead, List<ReceiveLine> lines, String deleteIds, String userId) {
		Set<String> handBillNos = new HashSet<String>();// 待处理的交接单号
		receiveHead.setUpdateDate(DateUtil.getCurrentDate18());
		receiveHead.setUpdateId(userId);
		receiveHead.setStatus(DictConstant.BILL_STATUS_CLOSED);
		if(StringUtil.isNotBlank(receiveHead.getBillid())){
			asertStatus("jat_bill_receive_head", "billid", receiveHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.receiveHeadDao.updateReceiveHead(receiveHead);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_SHOUPIAO);
			receiveHead.setBillno(billno);
			receiveHead.setCreateDate(DateUtil.getCurrentDate18());
			receiveHead.setCreateId(userId);
			this.receiveHeadDao.saveReceiveHead(receiveHead);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(ReceiveLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				if(StringUtil.isNotBlank(line.getLineid())){
					this.receiveLineDao.updateReceiveLine(line);
				} else {
					line.setBillid(receiveHead.getBillid());
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					this.receiveLineDao.saveReceiveLine(line);
					handBillNos.add(line.getHandNo());
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						this.receiveLineDao.modifyHandoverBillStatus(lineid, DictConstant.HANDOVER_IS_NOT_BILL, userId);
						this.receiveLineDao.deleteReceiveLine(lineid);
					}
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(handBillNos)){
			for(String handBillno : handBillNos){
				// 修改交接单 是否收票的状态
				this.handoverDao.modifyHandoverReceiveStatus(handBillno, DictConstant.HANDOVER_IS_BILL, userId);
			}
		}
	}

	@Override
	public ReceiveHead getReceiveHead(String billid) {
		return this.receiveHeadDao.getReceiveHead(billid);
	}

	@Override
	public void updateReceiveHead(ReceiveHead receiveHead) {
		this.receiveHeadDao.updateReceiveHead(receiveHead);
	}

	@Override
	public void modifyReceiveHeadStatus(String billid, String status, String userId) {
		this.receiveHeadDao.modifyReceiveHeadStatus(billid, status, userId);
	}

	@Override
	public void deleteReceiveHead(String billid, String userId) {
		asertStatus("jat_bill_receive_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.handoverDao.modifyHandoverReceiveStatusByBillid(billid, DictConstant.HANDOVER_IS_NOT_BILL, userId);
		this.receiveHeadDao.deleteReceiveHead(billid);
		this.receiveLineDao.deleteReceiveLineByBillid(billid);
		
	}
	@Override
	public void closeReceiveBill(String billid, String userId) {
		asertStatus("jat_bill_receive_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.receiveHeadDao.modifyReceiveHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
	}
	@Override
	public Pager getReceiveHandoverList(Map<String, String> condition) {
		return this.receiveHeadDao.getReceiveHandoverList(condition);
	}

	public CommonDao getCommonDao() {
		return this.commonDao;
	}
	
	@Override
	public void reverseReceive(String billid, String userId) {
		asertStatus("jat_bill_receive_head", "billid", billid, "status", DictConstant.BILL_STATUS_CLOSED);
		this.handoverDao.modifyHandoverReceiveStatusByBillid(billid, DictConstant.HANDOVER_IS_NOT_BILL, userId);
		this.receiveHeadDao.modifyReceiveHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userId);
	}
}
