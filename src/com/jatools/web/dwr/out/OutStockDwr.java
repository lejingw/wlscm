package com.jatools.web.dwr.out;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.OutStockManager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutStockHead;
import com.jatools.vo.out.OutStockLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class OutStockDwr {
	private static Logger logger = Logger.getLogger(OutStockDwr.class);
	private OutStockManager outStockManager;
	
	public static void setLogger(Logger logger) {
		OutStockDwr.logger = logger;
	}

	public void setOutStockManager(OutStockManager outStockManager) {
		this.outStockManager = outStockManager;
	}
	
	/**
	 * 获取饰品信息
	 * 
	 * @param orgId
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public OutOrna getOrnaInfo(String orgId,String code,boolean ornaFlag,String stockId){
		OutOrna orna=outStockManager.getOrnaInfo(code, ornaFlag);
		if(orna==null){
			throw new RuntimeException("不存在此饰品");
		}else if(!DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(orna.getStatus()))
			throw new RuntimeException("饰品状态不是有效状态，不允许操作");
		else{
			if(!orgId.equals(orna.getOrgId()))
				throw new RuntimeException("此饰品不在当前组织下");
			if(!stockId.equals(orna.getStockId()))
				throw new RuntimeException("此饰品不在当前仓库下");
		}
		return orna;
	}
	
	/**
	 * 保存或修改出库管理信息，包括 丢失、礼品、报废、营销
	 * @param doType 出库类型：0丢失1报废2礼品3营销4余金领用
	 * @param head 出库头数据
	 * @param lines 行数据
	 * @param delIds 如果是修改表示要删除的行Id
	 * @param req
	 * @return 返回提醒
	 */
	public String saveOrUpdateOutStock(String doType,String saveType,OutStockHead head,List<OutStockLine> lines,String[] delIds, HttpServletRequest req){
		try {
			if(StringUtil.equals(saveType, "save")){
				head.setStatus(DictConstant.BILL_STATUS_SAVE);
			}else{
				head.setStatus(DictConstant.BILL_STATUS_REVIEWING);
			}
			if(StringUtil.isEmpty(head.getBillid())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
			}else{
				OutStockHead osh=outStockManager.getStockHead(head.getBillid());
				if(null==osh){
					return "此单据不存在或已被删除";
				}else if(!StringUtil.equals(DictConstant.BILL_STATUS_SAVE, osh.getStatus())){
					return "此单据已不是保存状态，不允许操作";
				}
				head.setCreateDate(osh.getCreateDate());
				head.setCreateId(osh.getCreateId());
			}
			head.setDotype(doType);
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(CommonUtil.getSessionUserId(req));
			outStockManager.saveOrUpdateOutStock(head,lines,delIds);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}

	/**
	 * 更改head的头状态
	 * 
	 * @param billId
	 * @param dotype
	 * @return
	 */
	public String changeHeadStatus(String billId,String dotype, HttpServletRequest req){
		try {
			OutStockHead osh=outStockManager.getStockHead(billId);
			if(null==osh){
				return "此单据不存在或已被删除";
			}
			if(StringUtil.equals("close", dotype)){//关闭
				osh.setStatus(DictConstant.BILL_STATUS_CLOSED);
			}else if(StringUtil.equals("invalid", dotype)){//作废
				osh.setStatus(DictConstant.BILL_STATUS_INVALID);
				osh.setInvalid("1");//表示作废，0 正常
			}
			osh.setUpdateDate(DateUtil.getCurrentDate18());
			osh.setUpdateId(CommonUtil.getSessionUserId(req));
			osh.setCheckDate(osh.getUpdateDate());
			osh.setCheckId(osh.getUpdateId());
			osh.setCancelDate(osh.getUpdateDate());
			osh.setCancelId(osh.getUpdateId());
			outStockManager.saveOrUpdateOutStock(osh, null, null);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "操作出错";
		}
	}
	/**
	 * 删除
	 * @param billId
	 * @return
	 */
	public String delOutStock(String billId){
		try {
			if(StringUtil.isNotBlank(billId)){
				OutStockHead head=outStockManager.getStockHead(billId);
				if(head==null){
					return "此单据不存在或已被删除";
				}else if(!DictConstant.BILL_STATUS_SAVE.equals(head.getStatus())){
					return "只有保存状态才能删除";
				}
				outStockManager.delOutStock(billId);
				return null;
			}else{
				return "参数传递失败";
			}
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}
}