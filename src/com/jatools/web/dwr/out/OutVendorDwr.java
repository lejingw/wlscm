package com.jatools.web.dwr.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.OutVendorManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutVendorHead;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class OutVendorDwr {
	private static Logger logger = Logger.getLogger(OutVendorDwr.class);
	private OutVendorManager outVendorManager;
	private CommonManager commonManager;
	public void setOutVendorManager(OutVendorManager outVendorManager) {
		this.outVendorManager = outVendorManager;
	}
	
	public OutOrna getOrnaInfo(String orgId,String itemClass,String code,boolean ornaFlag,String stockId){
		OutOrna orna=outVendorManager.getOrnaInfo(code, ornaFlag);
		if(orna==null){
			throw new RuntimeException("不存在此饰品");
		}else if(!DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(orna.getStatus()))
			throw new RuntimeException("饰品状态不是有效状态，不允许操作");
		else if(!orgId.equals(orna.getOrgId()))
				throw new RuntimeException("此饰品不在当前组织下");
		else if(!stockId.equals(orna.getStockId()))
			throw new RuntimeException("此饰品不在当前仓库下");
		else if(itemClass!=null&&((","+itemClass+",").indexOf((","+orna.getItemClassId()+","))==-1))
			throw new RuntimeException("饰品大类必须包含在发料类型中");
		return beforeOrna(orna);
	}
	
	private OutOrna  beforeOrna(OutOrna orna){
		if(StringUtil.isNotBlank(orna.getMainWeight())){
			orna.setMainWeight(StringUtil.formatDouble(orna.getMainWeight(), 3));
		}
		if(StringUtil.isNotBlank(orna.getPartWeight())){
			orna.setPartWeight(StringUtil.formatDouble(orna.getPartWeight(), 3));
		}
		return orna;
	}
	public OutOrna getOrnaInfoSale(String vendorVal,String orgId,String code,boolean ornaFlag,String stockId){
		OutOrna orna=getOrnaInfo(orgId, null, code, ornaFlag,stockId);
		  if(!StringUtil.equals(orna.getVendorId(), vendorVal))
			throw new RuntimeException("此饰品不属于所选的供应商");
		return beforeOrna(orna);
	}

	
	public String saveOrUpdatePubSale(OutVendorHead head,List<OutVendorLine> lines,String[] delIds, HttpServletRequest req){
		try {
			if(StringUtil.isEmpty(head.getHeadId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
//				head.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = "";
				switch(Integer.parseInt(head.getDoType())){
				case 0:
					billNo = commonManager.getBillno("DG");
					break;
				case 1:
					billNo = commonManager.getBillno("DX");
					break;
				case 2:
					billNo = commonManager.getBillno("WW");
					break;
				}
				head.setBillNo(billNo);
			}else{
				OutVendorHead _head=outVendorManager.getVendorHead(head.getHeadId());
				if(_head==null)
					return "此单据已经被删除";
				else if(!DictConstant.BILL_STATUS_SAVE.equals(_head.getStatus()))
					return "此单据已经不能修改";
			}
//			head.setDoDate(head.getDoDate().substring(0,10));
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(CommonUtil.getSessionUserId(req));
			outVendorManager.saveOrUpdatePubSale(head,lines,delIds);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}
	/**
	 * 删除单据
	 * @param headId
	 */
	public String deleteVendor(String headId){
		try {
			OutVendorHead head=outVendorManager.getVendorHead(headId);
			if(head==null)
				return "此单据已经被删除";
			else if(!DictConstant.BILL_STATUS_SAVE.equals(head.getStatus()))
				return "只有保存状态才能删除";
			outVendorManager.deleteVendor(headId);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}
	/**
	 * 改变为单据状态
	 */
	public String changeHeadStatus(String headId,String status, HttpServletRequest req){
		try {	
			OutVendorHead head=outVendorManager.getVendorHead(headId);
			if(head==null)
				return "此单据已经被删除";
			if(status.equals(head.getStatus()))
				return "操作成功";
			head.setStatus(status);
			head.setHeadId(headId);
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(CommonUtil.getSessionUserId(req));
			head.setCheckDate(head.getUpdateDate());
			head.setCheckId(head.getUpdateId());
			head.setCancelDate(head.getUpdateDate());
			head.setCancelId(head.getUpdateId());
			outVendorManager.changeHeadStatus(head);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "操作出错";
		}
	}

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	
	public List<SelectorOption> getPurchaseByVendorId(String vendorId,HttpServletRequest req ){
		
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		if(vendorId==null||"".equals(vendorId))
			return null;
		List<PurchaseHead> head = outVendorManager.getPurchaseByVendorId(vendorId);
		for(PurchaseHead bc : head){
			sltList.add(new SelectorOption(bc.getBillno(),bc.getBillno()));
		}
		return sltList;
	}
	
	
	public Map<String, String> cashCharge(String billid, String charge, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.outVendorManager.cashCharge(billid, charge, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "修改失败");
			}
		}
		return result;
	}
}
