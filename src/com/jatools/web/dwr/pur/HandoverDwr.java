package com.jatools.web.dwr.pur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.pur.HandoverManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.HandoverCash;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class HandoverDwr {

	private static Logger logger = Logger.getLogger(HandoverDwr.class);

	private HandoverManager handoverManager;
	private CommonManager commonManager;
	
	public void setHandoverManager(HandoverManager handoverManager) {
		this.handoverManager = handoverManager;
	}
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public String saveOrUpdateHandover(HandoverHead handoverHead, List<HandoverLine> lines, List<HandoverCash> cashLines,  String ids[], HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(handoverHead.getBillid())){
				handoverHead.setCreateDate(DateUtil.getCurrentDate18());
				handoverHead.setCreateId(CommonUtil.getSessionUserId(req));
				handoverHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
				handoverHead.setBillno(billNo);
			}
			handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateId(CommonUtil.getSessionUserId(req));
			handoverHead.setSourceType(GlobalConstant.BILL_CODE_CAIGOUDAN);
			handoverManager.saveOrUpdateHandoverHead(handoverHead, lines, cashLines, ids);
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public Map<String, String> saveAndCheckHandover(HandoverHead handoverHead, List<HandoverLine> lines, List<HandoverCash> cashLines,  String ids[], HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			if(StringUtil.isEmpty(handoverHead.getBillid())){
				handoverHead.setCreateDate(DateUtil.getCurrentDate18());
				handoverHead.setCreateId(CommonUtil.getSessionUserId(req));
				handoverHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
				handoverHead.setBillno(billNo);
			}
			handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateId(CommonUtil.getSessionUserId(req));
			handoverHead.setSourceType(GlobalConstant.BILL_CODE_CAIGOUDAN);
			handoverManager.saveAndCheckHandoverHead(handoverHead, lines, cashLines, ids);
			result.put("isSuccess", "true");
			result.put("billid", handoverHead.getBillid());
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "提交审核失败");
			}
			return result;
		}
	}
		
	public String saveOrUpdateCash(HandoverHead head, List<HandoverCash> cashs, String ids, HttpServletRequest req){
		try {
			this.handoverManager.saveOrUpdateCash(head, cashs, ids, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e){
			logger.error(e);
			return "结算失败";
		}
	}
	
	/**
	 * 审核单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public Map<String, String> checkBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.checkBillhead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", "审核失败");
		}
		return result;
	}
	/**
	 * 关闭单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public Map<String, String> closeBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			boolean isSuccess = this.handoverManager.closeBillHead(billid, CommonUtil.getSessionUserId(req));
			if(isSuccess){
				result.put("isSuccess", "true");
			}else {
				result.put("isSuccess", "false");
				result.put("msg", "入库数量和交接单数量误差不在允许的范围内,不允许关闭单据");
			}
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 重开单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public Map<String, String> reopenBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.modifyHandoverHeadStatus(billid, DictConstant.BILL_STATUS_RECEIVED, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 接收单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public Map<String, String> receiveBill(String billid, List<HandoverLine> lines, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.receiveBillHead(billid , lines, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	public Map<String, String> receiveNoBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.receiveNoBillHead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	
	public Map<String, String> modifyNoNum(HandoverHead head, List<HandoverLine> lines, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.modifyNoNum(head, lines, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	/**
	 * 采购入库
	 * @param head
	 * @param req
	 * @return
	 */
	public Map<String, String> materInStock(HandoverHead head, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.materInStock(head, CommonUtil.getSessionUserId(req), CommonUtil.getSessionOrgId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "采购入库失败");
			}
		}
		return result;
	}
	
	public List<SelectorOption> getAnalysisList(){
		return this.handoverManager.getAnalysisList();
	}

	
	public Map<String, String> changeHandoverLine(HandoverHead head, List<HandoverLine> lines, String deleteLineIds,  HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try{
			result = this.handoverManager.changeHandoverLine(head, lines, deleteLineIds, CommonUtil.getSessionUserId(req));
		} catch(Exception e){
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "修改行信息失败");
			}
		}
		return result;
	}
	
	public Map<String, String> deleteHandover(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.deleteHandoverHead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "删除失败");
			}
			
		}
		return result;
	}
}
