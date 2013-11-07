package com.jatools.web.dwr.out;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.OutBackManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutBackHead;
import com.jatools.vo.out.OutBackLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class OutBackDwr {
	private static Logger logger = Logger.getLogger(OutBackDwr.class);
	private OutBackManager outBackManager;
	private CommonManager commonManager;
	public void setOutBackManager(OutBackManager outBackManager) {
		this.outBackManager = outBackManager;
	}

	/**
	 * 获取更有量饰品信息
	 * @param orgId 组织
	 * @param itemClass 大类
	 * @param code 编码或条码
	 */
	public OutOrna getOrnaInfo(String orgId,String itemClass,String code,boolean ornaFlag,String stockId){
		OutOrna orna=outBackManager.getOrnaInfo(code, ornaFlag);
		if(orna==null){
			throw new RuntimeException("不存在此饰品");
		}else if(!DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(orna.getStatus()))
			throw new RuntimeException("饰品状态不是有效状态，不允许操作");
		else if(!orgId.equals(orna.getOrgId()))
				throw new RuntimeException("此饰品不在当前组织下");
		else if(!stockId.equals(orna.getStockId()))
			throw new RuntimeException("此饰品不在当前仓库下");
		else if(itemClass!=null&&!itemClass.equals(orna.getItemClassId()))
			throw new RuntimeException("饰品大类必须和头信息中大类相同");
		return orna;
	}
	/**
	 * 保存供出单信息
	 * @param head
	 * @param lines
	 * @param delIds
	 * @param req
	 * @return
	 */
	public String saveOrUpdateOutBack(OutBackHead head,List<OutBackLine> lines,String[] delIds, HttpServletRequest req){
		try {
			if(StringUtil.isEmpty(head.getHeadId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
//				head.setStatus(DictConstant.BILL_STATUS_SAVE);
				head.setBillNo(commonManager.getBillno("JC"));
			}else{
				OutBackHead _head=outBackManager.getBackHead(head.getHeadId());
				if(_head==null)
					return "此单据已经被删除";
				else if(!DictConstant.BILL_STATUS_SAVE.equals(_head.getStatus()))
					return "此单据已经不能修改";
			}
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(CommonUtil.getSessionUserId(req));
			outBackManager.saveOrUpdateOutBack(head,lines,delIds);
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
	public String deleteBack(String headId){
		try {
			OutBackHead head=outBackManager.getBackHead(headId);
			if(head==null)
				return "此单据已经被删除";
			else if(!DictConstant.BILL_STATUS_SAVE.equals(head.getStatus()))
				return "只有保存状态才能删除";
			outBackManager.deleteBack(headId);
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
			OutBackHead head=outBackManager.getBackHead(headId);
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
			outBackManager.changeHeadStatus(head);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "操作出错";
		}
	}
	/**
	 * 获取行表信息
	 * @param headId 头id
	 * @param ornaCode 饰品编码或饰品条码
	 */
	public OutBackLine getBackLine(String headId, String ornaCode, boolean ornaFlag){
		OutBackLine line=outBackManager.getBackLine(headId, ornaCode, ornaFlag);
		if(line==null)
			throw new RuntimeException("不存在此饰品,只能归还本单据内的饰品");
		else if("1".equals(line.getIsBack()))
			throw new RuntimeException("此饰品已经还过");
		return line;
	}
	/**
	 * 归还饰品
	 * @param ids 要归还的饰品id信息
	 * @param headId 头表id
	 * @param memo 归还说明
	 * @param backSum 归还数量
	 */
	public String backLines(String ids,String headId,String memo,String backSum, HttpServletRequest req){
		try{
			if(StringUtil.isEmpty(ids))
				throw new RuntimeException("没有要归还的饰品");
			OutBackHead head=outBackManager.getBackHead(headId);
			head.setBackBody(memo);
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(CommonUtil.getSessionUserId(req));
			head.setBackDate(head.getUpdateDate());
			int oldSum=StringUtil.isEmpty(head.getBackSum())?0:Integer.parseInt(head.getBackSum());
			int sum=Integer.parseInt(backSum)+oldSum;
			if(sum-Integer.parseInt(head.getSumCount())==0)
				head.setStatus("8");
			head.setBackSum(sum+"");
			outBackManager.backLines(ids,head);
		return null;
		}catch (Exception e) {
			return "归还失败";
		}
	}

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}

}
