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
import com.jatools.dao.inout.SendHeadDao;
import com.jatools.dao.inout.SendLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.inout.SendHeadManager;
import com.jatools.vo.inout.SendHead;
import com.jatools.vo.inout.SendLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class SendHeadManagerImpl extends BaseManager implements SendHeadManager {
	
	private SendHeadDao sendHeadDao;
	private SendLineDao sendLineDao;
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setSendHeadDao(SendHeadDao sendHeadDao) {
		this.sendHeadDao = sendHeadDao;
	}

	public void setSendLineDao(SendLineDao sendLineDao) {
		this.sendLineDao = sendLineDao;
	}

	@Override
	public Pager getSendHeadData(Map<String, String> condition) {
		return this.sendHeadDao.getSendHeadData(condition);
	}


	@Override
	public SendHead getSendHead(String billid) {
		return this.sendHeadDao.getSendHead(billid);
	}

	@Override
	public void modifySendHeadStatus(String billid, String status, String userId) {
		this.sendHeadDao.modifySendHeadStatus(billid, status, userId);
	}

	@Override
	public void deleteSendHead(String billid, String userId) {
		asertStatus("jat_bill_send_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.sendHeadDao.modifyOutVendorIsNotBll(billid, userId);// 改成未出票状态
		this.sendHeadDao.deleteSendHead(billid);
		this.sendLineDao.deleteSendLineByBillid(billid);
	}

	@Override
	public void saveorUpdateSendHead(SendHead sendHead, List<SendLine> lines, String deleteIds, String userId) {
		Set<String> outVendorBillnos = new HashSet<String>();
		sendHead.setUpdateDate(DateUtil.getCurrentDate18());
		sendHead.setUpdateId(userId);
		if(StringUtil.isNotBlank(sendHead.getBillid())){
			asertStatus("jat_bill_send_head", "billid", sendHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.sendHeadDao.updateSendHead(sendHead);
		} else {
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_CHUPIAO);
			sendHead.setBillno(billno);
			sendHead.setCreateDate(DateUtil.getCurrentDate18());
			sendHead.setCreateId(userId);
			this.sendHeadDao.saveSendHead(sendHead);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(SendLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				if(StringUtil.isNotBlank(line.getLineid())){
					this.sendLineDao.updateSendLine(line);
				} else {
					line.setBillid(sendHead.getBillid());
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					this.sendLineDao.saveSendLine(line);
					outVendorBillnos.add(line.getOutNo());
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						// 改成 未出票状态
						this.sendHeadDao.modifyOutVendorBillByLineid(lineid, DictConstant.OUT_VENDOR_IS_NOT_BILL, userId);
						this.sendLineDao.deleteSendLine(lineid);
					}
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(outVendorBillnos)){
			for(String billno : outVendorBillnos){
				this.sendHeadDao.modifyOutVendorIsBll(billno, userId);// 改成 已出票状态
			}
		}
	}
	
	public void saveAndCloseSendHead(SendHead sendHead, List<SendLine> lines, String deleteIds, String userId) {
		Set<String> outVendorBillnos = new HashSet<String>();
		sendHead.setUpdateDate(DateUtil.getCurrentDate18());
		sendHead.setUpdateId(userId);
		sendHead.setStatus(DictConstant.BILL_STATUS_CLOSED);
		if(StringUtil.isNotBlank(sendHead.getBillid())){
			asertStatus("jat_bill_send_head", "billid", sendHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.sendHeadDao.updateSendHead(sendHead);
		} else {
			sendHead.setCreateDate(DateUtil.getCurrentDate18());
			sendHead.setCreateId(userId);
			this.sendHeadDao.saveSendHead(sendHead);
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(SendLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				if(StringUtil.isNotBlank(line.getLineid())){
					this.sendLineDao.updateSendLine(line);
				} else {
					line.setBillid(sendHead.getBillid());
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					this.sendLineDao.saveSendLine(line);
					outVendorBillnos.add(line.getOutNo());
				}
			}
		}
		
		if(StringUtil.isNotBlank(deleteIds)){
			String idArray[] = deleteIds.split(";");
			if(!ArrayUtils.isEmpty(idArray)){
				for(String lineid : idArray){
					if(StringUtil.isNotBlank(lineid)){
						// 改成 未出票状态
						this.sendHeadDao.modifyOutVendorBillByLineid(lineid, DictConstant.OUT_VENDOR_IS_NOT_BILL, userId);
						this.sendLineDao.deleteSendLine(lineid);
					}
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(outVendorBillnos)){
			for(String billno : outVendorBillnos){
				this.sendHeadDao.modifyOutVendorIsBll(billno, userId);// 改成 已出票状态
			}
		}
	}

	@Override
	public void closeSendHead(String billid, String userId) {
		asertStatus("jat_bill_send_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.sendHeadDao.modifySendHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
	}

	@Override
	public Pager getOutVendorListData(Map<String, String> condition) {
		return this.sendHeadDao.getOutVendorListData(condition);
	}

	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
